package de.leuphana.ui.view.summonerSpell;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;

import de.leuphana.ui.navigation.NavigationManager;

@SpringView
public class SpellView extends SummonerSpellViewDesign implements View{

	
	private final NavigationManager navigationManager;
	
	public SpellView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}
	
	@PostConstruct
	public void init() {
		
	}
}
