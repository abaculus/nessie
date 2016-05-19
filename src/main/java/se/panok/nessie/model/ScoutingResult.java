package se.panok.nessie.model;

import org.springframework.core.style.ToStringCreator;

public class ScoutingResult {

	private int scoutedLake;

	private int nessiesLake;

	private boolean switchedLake;

	private boolean nessieFound;

	public ScoutingResult() {
	}

	public ScoutingResult(final int scoutedLake, final int nessiesLake, final boolean switchedLake) {
		this.scoutedLake = scoutedLake;
		this.nessiesLake = nessiesLake;
		nessieFound = scoutedLake == nessiesLake;
		this.switchedLake = switchedLake;
	}

	public int getScoutedLake() {
		return scoutedLake;
	}

	public int getNessiesLake() {
		return nessiesLake;
	}

	public boolean isSwitchedLake() {
		return switchedLake;
	}

	public boolean isNessieFound() {
		return nessieFound;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("scoutedLake", scoutedLake).append("nessiesLake", nessiesLake)
				.append("switchedLake", switchedLake).append("nessieFound", nessieFound).toString();
	}
}
