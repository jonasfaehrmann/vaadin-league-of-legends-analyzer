package de.leuphana.ui.view.match;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.leuphana.backend.service.MatchHistoryService;
import de.leuphana.backend.service.RiotService;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;

@SpringView(name = "match")
public class MatchDetailView extends MatchDetailViewDesign implements View {

	private MatchDetailView view;

	@Autowired
	private MatchHistoryService matchHistoryService;

	@Autowired
	public MatchDetailView() {
	}

	@PostConstruct
	public void init() {

	}

	@Override
	public void enter(ViewChangeEvent event) {
		String matchId = event.getParameters();
		if ("".equals(matchId)) {
			System.out.println("No Match Id");
		} else {
			Match match;
			try {
				match = matchHistoryService.findOneBySummonerName(Long.valueOf(matchId), "");
			} catch (Exception e) {
				view.showNotFound();
				return;
			}
			setMatch(match);
		}
	}

	public void setMatch(Match match) {
		
		gameId.setValue(String.valueOf(match.getGameId()));
		
		matchParticipantGrid.setSizeFull();
		matchParticipantGrid.setCaption("Participants: ");
		matchParticipantGrid.setItems(match.getParticipants());
	}

	public void showNotFound() {
		removeAllComponents();
		addComponent(new Label("Match not found"));
	}
}
