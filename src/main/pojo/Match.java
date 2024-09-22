package main.pojo;

import java.io.Serializable;
import java.util.Objects;

public class Match implements Serializable {
	private static final long serialVersionUID = 1L;
	private String localTeam = null;
	private String rivalTeam = null;
	private int localGoals = 0;
	private int rivalGoals = 0;
	private String matchPlace = null;
	private String matchDate = null;

	public Match(String localTeam, String rivalTeam, int localGoals, int rivalGoals, String matchPlace,
			String matchDate) {
		this.localTeam = localTeam;
		this.rivalTeam = rivalTeam;
		this.localGoals = localGoals;
		this.rivalGoals = rivalGoals;
		this.matchPlace = matchPlace;
		this.matchDate = matchDate;
	}

	public String getLocalTeam() {
		return localTeam;
	}

	public void setLocalTeam(String localTeam) {
		this.localTeam = localTeam;
	}

	public String getRivalTeam() {
		return rivalTeam;
	}

	public void setRivalTeam(String rivalTeam) {
		this.rivalTeam = rivalTeam;
	}

	public int getLocalGoals() {
		return localGoals;
	}

	public void setLocalGoals(int localGoals) {
		this.localGoals = localGoals;
	}

	public int getRivalGoals() {
		return rivalGoals;
	}

	public void setRivalGoals(int rivalGoals) {
		this.rivalGoals = rivalGoals;
	}

	public String getMatchPlace() {
		return matchPlace;
	}

	public void setMatchPlace(String matchPlace) {
		this.matchPlace = matchPlace;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(localGoals, localTeam, matchDate, matchPlace, rivalGoals, rivalTeam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return localGoals == other.localGoals && Objects.equals(localTeam, other.localTeam)
				&& Objects.equals(matchDate, other.matchDate) && Objects.equals(matchPlace, other.matchPlace)
				&& rivalGoals == other.rivalGoals && Objects.equals(rivalTeam, other.rivalTeam);
	}

	@Override
	public String toString() {
		return "Match [localTeam=" + localTeam + ", rivalTeam=" + rivalTeam + ", localGoals=" + localGoals
				+ ", rivalGoals=" + rivalGoals + ", matchPlace=" + matchPlace + ", matchDate=" + matchDate + "]";
	}
}
