package de.leuphana.ui.view.championmastery;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button.ClickShortcut;

import de.leuphana.ui.component.championmastery.ChampionMasteryGrid;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.match.MatchDetailView;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

@SpringView
public class ChampionMasteryView extends ChampionMasteryViewDesign implements View {

	private static final String PARAMETER_SEARCH = "search";

	private static final String PARAMETER_INCLUDE_PAST = "includePast";

	private final NavigationManager navigationManager;
	
	@Autowired
	private ChampionMasteryGrid championMasteryGrid;

	@Autowired
	public ChampionMasteryView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}
	
	 
	@PostConstruct
	public void init() throws RiotApiException {
		Chart chart = (Chart) championMasteryGrid.getChart();
		chart.setSizeFull();
		addComponent(chart);
	}



}