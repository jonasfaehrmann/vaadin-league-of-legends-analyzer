package de.leuphana.ui.view.champion;

import javax.annotation.PostConstruct;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button.ClickShortcut;

import de.leuphana.ui.components.ChampionGrid;
import de.leuphana.ui.navigation.NavigationManager;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;

@SpringView
public class ChampionView extends ChampionViewDesign implements View{

	private static final String PARAMETER_SEARCH = "search";
	private final NavigationManager navigationManager;
	
	
	public ChampionView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}
	
	@PostConstruct
	public void init() {
		championGrid.addSelectionListener(e -> selectedChampion(e.getFirstSelectedItem().get()));
		searchButton.addClickListener(e -> search(searchField.getValue()));
		searchPanel.addAction(new ClickShortcut(searchButton, KeyCode.ENTER, null));
	}

	private void search(String searchTerm) {
		String parameters = PARAMETER_SEARCH + "=" + searchTerm;
		
		navigationManager.updateViewParameter(parameters);
	}

	private Object selectedChampion(Champion champion) {
		navigationManager.navigateTo(ChampionDetailView.class, String.valueOf(champion.getId()));
		
		return null;
	}
}