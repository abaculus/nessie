package se.panok.nessie.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import se.panok.nessie.Application;
import se.panok.nessie.model.ScoutingResult;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
@ComponentScan
@WebAppConfiguration
@IntegrationTest({ "server.port:0" })
public class NessieScoutingEndpointIT {

	private static final String NESSIE_SCOUTING_URL = "http://localhost:%d/nessie/scout";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${local.server.port}")
	private int port;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSingleScoutingWithSwitchingLake() throws Exception {
		runScoutingForNessie(1, true);
	}

	@Test
	public void testSingleScoutingWithoutSwitchingLake() throws Exception {
		runScoutingForNessie(1, false);
	}

	@Test
	public void testMultipleScoutingWithSwitchingLake() throws Exception {
		runScoutingForNessie(1_000, true);
	}

	@Test
	public void testMultipleScoutingWithoutSwitchingLake() throws Exception {
		runScoutingForNessie(1_000, false);
	}

	private void runScoutingForNessie(final int noOfNessieScoutingRuns, final boolean shouldSwitchLake)
			throws Exception {

		int found = 0;
		int notFound = 0;

		final ObjectMapper objectMapper = new ObjectMapper();

		for (int i = 0; i < noOfNessieScoutingRuns; i++) {
			final MvcResult result = this.mockMvc
					.perform(get(String.format(NESSIE_SCOUTING_URL, port)).param("switch_lake",
							String.valueOf(shouldSwitchLake)))
					.andExpect(status().isOk()).andExpect(jsonPath("$.switchedLake").value(shouldSwitchLake))
					.andReturn();
			final String jsonContent = result.getResponse().getContentAsString();
			// convert json string to object
			final ScoutingResult scoutingResult = objectMapper.readValue(jsonContent, ScoutingResult.class);
			final boolean isNessieFound = scoutingResult.isNessieFound();

			if (isNessieFound) {
				found++;
			} else {
				notFound++;
			}
		}

		printResultStats(shouldSwitchLake, noOfNessieScoutingRuns, found, notFound);
	}

	private void printResultStats(final boolean switchingLake, final int runs, final int found, final int notFound) {
		logger.info("Switch Lake:\t" + switchingLake);
		logger.info("Runs:\t\t" + runs);
		logger.info("Found:\t\t" + found);
		logger.info("Not Found:\t" + notFound);
		logger.info("Found (%):\t"
				+ BigDecimal.valueOf(found).divide(BigDecimal.valueOf(runs)).multiply(BigDecimal.valueOf(100)));
	}
}
