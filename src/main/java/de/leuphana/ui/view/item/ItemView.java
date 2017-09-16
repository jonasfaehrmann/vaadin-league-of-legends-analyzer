package de.leuphana.ui.view.item;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;

import de.leuphana.ui.navigation.NavigationManager;

@SpringView
public class ItemView extends ItemViewDesign implements View{

	
	private final NavigationManager navigationManager;
	
	public ItemView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}
	
	@PostConstruct
	public void init() {
		
	}
}
