package de.leuphana.leuphalytics.model.widget.matchlist;

import java.util.List;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import de.leuphana.leuphalytics.model.match.RiotMatch;

@SpringComponent
@UIScope
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
