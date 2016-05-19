package se.panok.nessie.service;

import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.panok.nessie.model.ScoutingResult;

@Service
public class NessieScoutingImpl implements NessieScouting {

	private static final int[] LAKES = new int[] { 0, 1, 2 };

	private final Random random = new Random();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public ScoutingResult scoutForNessie(final boolean switchLake) {

		logger.debug("Scouting for Nessie and I'm {}",
				(switchLake ? "switching lake to find her!" : "NOT switching lake to find her!"));

		final int nessiesLake = random.nextInt(LAKES.length);
		final int initialScoutingLake = random.nextInt(LAKES.length);
		final int fishermenLake = chooseAnotherLake(nessiesLake, initialScoutingLake);

		ScoutingResult result = null;
		if (switchLake) {
			final int changedScoutinglake = chooseAnotherLake(initialScoutingLake, fishermenLake);
			result = new ScoutingResult(changedScoutinglake, nessiesLake, switchLake);
		} else {
			result = new ScoutingResult(initialScoutingLake, nessiesLake, switchLake);
		}

		logger.debug("Result from Nessie scouting: {}", result);

		return result;
	}

	private int chooseAnotherLake(final int lake1, final int lake2) {
		return Arrays.stream(LAKES).filter(n -> n != lake1 && n != lake2).findFirst().getAsInt();
	}
}
