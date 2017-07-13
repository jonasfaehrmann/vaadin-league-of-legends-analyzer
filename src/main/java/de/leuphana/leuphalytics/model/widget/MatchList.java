package de.leuphana.leuphalytics.model.widget;

import java.util.List;

import de.leuphana.leuphalytics.model.match.Match;

public class MatchList {
	
	private List<Match> matches; 
	
	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public MatchList() {
	}
	
	@Override
	public String toString() {
		return "Matches: " + matches;
	}

	
	

}
