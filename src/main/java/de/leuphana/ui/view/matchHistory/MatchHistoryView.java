package de.leuphana.ui.view.matchHistory;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button.ClickShortcut;

import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.match.MatchDetailView;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

/**
 *
 *
 * @author Hendrik Zevenhuizen
 *
 */
@SpringView
public class MatchHistoryView extends MatchHistoryViewDesign implements View {

	private static final String PARAMETER_SEARCH = "search";

	private static final String PARAMETER_INCLUDE_PAST = "includePast";

	private final NavigationManager navigationManager;

	@Autowired
	public MatchHistoryView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}

	
	@PostConstruct
	public void init() {
		matchHistoryGrid.addSelectionListener(e -> selectedMatch(e.getFirstSelectedItem().get()));
		searchButton.addClickListener(e -> search(searchField.getValue(), includePast.getValue()));

		// We don't want a global shortcut for enter, scope it to the panel
		searchPanel.addAction(new ClickShortcut(searchButton, KeyCode.ENTER, null));
	}

	public void selectedMatch(Match match) {
		navigationManager.navigateTo(MatchDetailView.class, match.getGameId());
	}

	public void newOrder() {
		navigationManager.navigateTo(MatchHistoryView.class);
	}

	public void search(String searchTerm, boolean includePast) {
		String parameters = PARAMETER_SEARCH + "=" + searchTerm;
		if (includePast) {
			parameters += "&" + PARAMETER_INCLUDE_PAST;
		}
		navigationManager.updateViewParameter(parameters);
	}

}