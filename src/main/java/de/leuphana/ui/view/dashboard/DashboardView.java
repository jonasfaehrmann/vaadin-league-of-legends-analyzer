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
import de.leuphana.backend.service.SummonerService;
import de.leuphana.ui.components.widgets.ChampionImagesWidget;
import de.leuphana.ui.components.widgets.MatchHistoryGridWidget;
import de.leuphana.ui.components.widgets.WidgetComponent;
import de.leuphana.ui.components.widgets.WidgetContainer;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.match.MatchDetailView;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.dto.LeagueItem;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

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

	private  BoardLabel summonerLevelLabel;
	private  BoardLabel summonerLossesLabel;
	private  BoardBox notAvailableBox;
	private  BoardLabel summonerWinsLabel;
	private  BoardLabel summonerRankLabel;

	private String summonerName;
	private WidgetContainer widgets = new WidgetContainer();
	private Row row;

	private final MatchHistoryGridWidget matchHistoryGridWidget;
	private final ChampionImagesWidget championImagesWidget;
	private final AccountService accountService;
	private final SummonerService summonerService;

	@Autowired
	public DashboardView(NavigationManager navigationManager, MatchHistoryGridWidget matchHistoryGridWidget,
			ChampionImagesWidget championImagesWidget, AccountService accountService, SummonerService summonerService, AccountService userService) {
		this.navigationManager = navigationManager;
		this.summonerService = summonerService;
		this.accountService = accountService;
		this.matchHistoryGridWidget = matchHistoryGridWidget;
		this.championImagesWidget = championImagesWidget;
	}

	@PostConstruct
	public void init() throws RiotApiException {
		summonerName = SecurityUtils.getCurrentUser(accountService).getSummonerName();
		setResponsive(true);
		setInfoLabels();

		
		// manually add all widgets to list
		widgets.addWidget(matchHistoryGridWidget);
		widgets.addWidget(championImagesWidget);

		row = board.addRow(new BoardBox(summonerLevelLabel), notAvailableBox, new BoardBox(summonerWinsLabel),
				new BoardBox(summonerRankLabel));
		row.addStyleName("board-row-group");

		Account currentAccount = SecurityUtils.getCurrentUser(accountService);
		initCurrentAccountWidgets(currentAccount.getWidgets());
	}

	private void setInfoLabels() throws RiotApiException {
		Summoner summoner = summonerService.findOneBySummonerName(summonerName);
		LeagueItem league = summonerService.getLeagueItemByName(summonerName);
		summonerLevelLabel = new BoardLabel("Games", String.valueOf(summoner.getSummonerLevel()), "level");
		summonerLossesLabel = new BoardLabel("Losses", String.valueOf(league.getLosses()), "losses");
		notAvailableBox = new BoardBox(summonerLossesLabel);
		summonerWinsLabel = new BoardLabel("Wins", String.valueOf(league.getWins()), "wins");
		summonerRankLabel = new BoardLabel("Rank", String.valueOf(league.getRank()), "rank");
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
