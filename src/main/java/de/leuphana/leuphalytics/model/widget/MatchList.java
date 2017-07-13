package de.leuphana.leuphalytics.model.widget;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

import de.leuphana.leuphalytics.model.match.Match;

public abstract class MatchList {
	
	private Value matches; 
	
	public Value getMatches() {
		return matches;
	}

	public void setMatches(Value matches) {
		this.matches = matches;
	}

	public MatchList() {
	}
	
	@Override
	public String toString() {
		return "Matches: " + matches;
	}

	
	

}
