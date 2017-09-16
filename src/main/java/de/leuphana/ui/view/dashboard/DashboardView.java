package de.leuphana.ui.view.dashboard;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.board.Row;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;

import de.leuphana.app.security.SecurityUtils;
import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.data.entity.Widget;
import de.leuphana.backend.service.AccountService;
import de.leuphana.ui.components.widgets.ChampionImagesWidget;
import de.leuphana.ui.components.widgets.ItemGridWidget;
import de.leuphana.ui.components.widgets.MatchHistoryGridWidget;
import de.leuphana.ui.components.widgets.SummonerSpellGridWidget;
import de.leuphana.ui.components.widgets.WidgetComponent;
import de.leuphana.ui.components.widgets.WidgetContainer;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.champion.ChampionDetailView;
import de.leuphana.ui.view.match.MatchDetailView;
import net.rithms.riot.api.RiotApiException;

/**
 * The dashboard view showing statistics about sales and deliveries.
 * <p>
 * Created as a single View class because the logic is so simple that using a
 * pattern like MVP would add much overhead for little gain. If more complexity
 * is added to the class, you should consider splitting out a presenter.
 */
@SpringView
public class DashboardView extends DashboardViewDesign implements View {

	private static final String BOARD_ROW_PANELS = "board-row-panels";

	private final NavigationManager navigationManager;

	private final BoardLabel todayLabel = new BoardLabel("Today", "3/7", "today");
	private final BoardLabel notAvailableLabel = new BoardLabel("N/A", "1", "na");
	private final BoardBox notAvailableBox = new BoardBox(notAvailableLabel);
	private final BoardLabel newLabel = new BoardLabel("New", "2", "new");
	private final BoardLabel tomorrowLabel = new BoardLabel("Tomorrow", "4", "tomorrow");

	private final Button button = new Button("Hello");
	private WidgetContainer widgets = new WidgetContainer();
	private Row row;

	private final ChampionImagesWidget championImagesWidget;
	private final MatchHistoryGridWidget matchHistoryGridWidget;
	private final AccountService accountService;
	private final ItemGridWidget itemWidget;
	private final SummonerSpellGridWidget summonerSpellWidget;

	@Autowired
	public DashboardView(NavigationManager navigationManager, SummonerSpellGridWidget summonerSpellWidget, ItemGridWidget itemWidget ,MatchHistoryGridWidget matchHistoryGridWidget,
			AccountService accountService, ChampionImagesWidget championImagesWidget) {
		this.navigationManager = navigationManager;
		this.matchHistoryGridWidget = matchHistoryGridWidget;
		this.accountService = accountService;
		this.championImagesWidget = championImagesWidget;
		this.itemWidget = itemWidget;
		this.summonerSpellWidget = summonerSpellWidget;
	}

	@PostConstruct
	public void init() throws RiotApiException {
		setResponsive(true);

		// manually add all widgets to list
		widgets.addWidgets(matchHistoryGridWidget, championImagesWidget, itemWidget, summonerSpellWidget);

		row = board.addRow(new BoardBox(todayLabel), notAvailableBox, new BoardBox(newLabel),
				new BoardBox(tomorrowLabel));
		row.addStyleName("board-row-group");

		// link to different view dummy
		row = board.addRow(new BoardBox(button));
		button.addClickListener(e -> navigationManager.navigateTo(MatchDetailView.class, 1));

		Account currentAccount = SecurityUtils.getCurrentUser(accountService);
		initCurrentAccountWidgets(currentAccount.getWidgets());
	}

	private void initCurrentAccountWidgets(Set<Widget> accountWidgets) {

		for (WidgetComponent widget : widgets.getWidgets()) {
			if (accountWidgets.stream().anyMatch(accountWidget -> accountWidget.getId() == widget.getWidgetId())) {
				System.out.println("Added widget with Id " + widget.getWidgetId());
				row = board.addRow(new BoardBox(widgets.getWidgetByWidgetId(widget.getWidgetId())));
			}
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// updateLabels(data.getDeliveryStats());
		// updateGraphs(data);
	}

}
