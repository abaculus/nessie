package se.panok.nessie.service;

import se.panok.nessie.model.ScoutingResult;

public interface NessieScouting {

	ScoutingResult scoutForNessie(final boolean switchLake);
}
