package de.leuphana.ui.view.champion;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;

import de.leuphana.ui.navigation.NavigationManager;

@SpringView
public class ChampionView extends ChampionViewDesign implements View{

	
	private final NavigationManager navigationManager;
	
	public ChampionView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}
	
	@PostConstruct
	public void init() {
		
	}
}
