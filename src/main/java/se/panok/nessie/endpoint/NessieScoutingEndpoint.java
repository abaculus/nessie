package se.panok.nessie.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.panok.nessie.model.ScoutingResult;
import se.panok.nessie.service.NessieScouting;

@RestController
public class NessieScoutingEndpoint {

	@Autowired
	private NessieScouting nessieScouting;

	@RequestMapping(value = "/nessie/scout", method = RequestMethod.GET)
	public ScoutingResult scoutForNessie(
			@RequestParam(value = "switch_lake", defaultValue = "false") final boolean switchLake) {
		return nessieScouting.scoutForNessie(switchLake);
	}
}
