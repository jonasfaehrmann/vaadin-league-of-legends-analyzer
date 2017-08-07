package de.leuphana.ui.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import de.leuphana.backend.service.RiotService;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.match.MatchDetailView;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

import com.vaadin.ui.Grid;

@SpringComponent
@PrototypeScope
public class CurrentMatchGrid extends Grid<MatchReference>{
	
	@Autowired
	private RiotService riotService;
	
	@Autowired
	private NavigationManager navigationManager;
	
	@PostConstruct
	void init() throws RiotApiException { 
		setId("matchListForUser");
		setSizeFull();
		removeHeaderRow(0);
		setMatches(riotService.getMatchListForUser());
		addSelectionListener(e -> selectedMatch(e.getFirstSelectedItem().get()));
	}

	private void setMatches(List<MatchReference> matches) {
		setItems(matches);
		addColumn(MatchReference::getLane).setCaption("Lane");
		addColumn(MatchReference::getQueue).setCaption("Queue");
	}
	
	public void selectedMatch(MatchReference matchReference) {
		navigationManager.navigateTo(MatchDetailView.class, matchReference.getGameId());
	}

}
