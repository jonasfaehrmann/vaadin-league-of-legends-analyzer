package de.leuphana.leuphalytics.model.widget.matchlist;

import java.util.List;

import de.leuphana.leuphalytics.model.match.Match;
import de.leuphana.leuphalytics.model.match.RiotMatch;

public class RiotMatchList extends MatchList {
	
	private List<RiotMatch> matches; 
	
	public void setMatches(List<RiotMatch> matches) {
		this.matches = matches;
	}
	

	@Override
	public String toString() {

		String matchList = " ";

		for (RiotMatch riotMatch : matches) {
			matchList += riotMatch.getChampion() + " *** ";
		}

		return matchList;
	}

}
