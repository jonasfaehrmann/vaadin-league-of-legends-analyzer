package de.leuphana.ui.view.champion;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;

import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.dashboard.DashboardViewDesign;

@SpringView
public class ChampionView extends ChampionViewDesign implements View {
		
	private final NavigationManager navigationManager;
	
	@Autowired
	public ChampionView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}
	
	@PostConstruct
	public void init() {
		
	}
}
