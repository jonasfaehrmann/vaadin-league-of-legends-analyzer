package de.leuphana.ui.view.championMastery;

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

import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.match.MatchDetailView;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

/**
 * The storefront view showing upcoming orders.
 * <p>
 * Created as a single View class because the logic is so simple that using a
 * pattern like MVP would add much overhead for little gain. If more complexity
 * is added to the class, you should consider splitting out a presenter.
 */
@SpringView
public class ChampionMasteryView extends ChampionMasteryViewDesign implements View {

	private static final String PARAMETER_SEARCH = "search";

	private static final String PARAMETER_INCLUDE_PAST = "includePast";

	private final NavigationManager navigationManager;

	@Autowired
	public ChampionMasteryView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}

	/**
	 * This method is invoked once each time an instance of the view is created.
	 * <p>
	 * This typically happens whenever a user opens the URL for the view, or
	 * refreshes the browser as long as the view is set to {@link ViewScope}. If
	 * we set the view to {@link UIScope}, the instance will be kept in memory
	 * (in the session) as long as the UI exists in memory and the same view
	 * instance will be reused whenever the user enters the view.
	 * <p>
	 * Here we set up listeners and attach data providers and otherwise
	 * configure the components for the parts which only need to be done once.
	 */
	@PostConstruct
	public void init() {
		Chart chart = new Chart();
		chart.getConfiguration().addSeries( new ListSeries( 1, 2, 3 ) );
		addComponent(chart);
	}



}
