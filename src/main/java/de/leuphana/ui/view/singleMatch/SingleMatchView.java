package de.leuphana.ui.view.singleMatch;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;

import de.leuphana.backend.service.SingleMatchService;
import de.leuphana.ui.components.SingleMatchComponent;
import de.leuphana.ui.navigation.NavigationManager;
import net.rithms.riot.api.RiotApiException;

@SpringView
public class SingleMatchView extends SingleMatchViewDesign implements View {

	private static final String PARAMETER_SEARCH = "search";

	private static final String PARAMETER_INCLUDE_PAST = "includePast";

	private final NavigationManager navigationManager;
	
	@Autowired
	SingleMatchComponent singleMatchComponent;
	
	@Autowired
	SingleMatchService singleMatchService;

	@Autowired
	public SingleMatchView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}

	
	@PostConstruct
	public void init() throws RiotApiException {
		
		optionMatch.addValueChangeListener(event -> {
			
			String matchIdString = String.valueOf(event.getValue());
			
			String matchId = matchIdString.substring(1, matchIdString.length()-1);
			
		    inputMatchId.setValue(matchId);
		});
		
		buttonCheck.addClickListener(e -> {
			try {
				setData();
			} catch (RiotApiException e1) {
				e1.printStackTrace();
			}
		});
		
		
		
	}
	
	private void setData() throws RiotApiException {
		singleMatchService.setSummonerName(inputSummoner.getValue());
		singleMatchService.setMatchId(inputMatchId.getValue());
		createCharts();
	}


	private void createCharts() throws RiotApiException {
		HorizontalLayout horizontalLayout = (HorizontalLayout) singleMatchComponent.getCharts();
		horizontalLayout.setSizeFull();
		addComponent(horizontalLayout);
		setExpandRatio(horizontalLayout, 1);
	}


	public void search(String searchTerm, boolean includePast) {
		String parameters = PARAMETER_SEARCH + "=" + searchTerm;
		if (includePast) {
			parameters += "&" + PARAMETER_INCLUDE_PAST;
		}
		navigationManager.updateViewParameter(parameters);
	}

}