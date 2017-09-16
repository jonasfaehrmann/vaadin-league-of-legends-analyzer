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
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import de.leuphana.app.security.SecurityUtils;
import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.data.entity.Widget;
import de.leuphana.backend.service.AccountService;
import de.leuphana.backend.service.SummonerService;
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

	private final NavigationManager navigationManager;

	private  BoardLabel summonerLevelLabel;
	private  BoardLabel summonerLossesLabel;
	private  BoardBox notAvailableBox;
	private  BoardLabel summonerWinsLabel;
	private  BoardLabel summonerRankLabel;
	private Account currentAccount;

	private final Button button = new Button("Hello");
	private WidgetContainer widgets = new WidgetContainer();
	private Row row;

	private final ChampionImagesWidget championImagesWidget;
	private final MatchHistoryGridWidget matchHistoryGridWidget;
	private final AccountService accountService;
	private final SummonerService summonerService;
	
	private final ItemGridWidget itemWidget;
	private final SummonerSpellGridWidget summonerSpellWidget;

	@Autowired
	public DashboardView(NavigationManager navigationManager, SummonerSpellGridWidget summonerSpellWidget, ItemGridWidget itemWidget ,MatchHistoryGridWidget matchHistoryGridWidget,
			AccountService accountService, ChampionImagesWidget championImagesWidget, SummonerService summonerService) {
		this.navigationManager = navigationManager;
		this.matchHistoryGridWidget = matchHistoryGridWidget;
		this.accountService = accountService;
		this.championImagesWidget = championImagesWidget;
		this.itemWidget = itemWidget;
		this.summonerSpellWidget = summonerSpellWidget;
		this.summonerService = summonerService;
	}

	@PostConstruct
	public void init() throws RiotApiException {
		setResponsive(true);

		currentAccount = SecurityUtils.getCurrentUser(accountService);

		// manually add all widgets to list
		widgets.addWidgets(matchHistoryGridWidget, championImagesWidget, itemWidget, summonerSpellWidget);

		setInfoLabels();
		
		row = board.addRow(new BoardBox(button));
		button.addClickListener(e -> navigationManager.navigateTo(MatchDetailView.class, 1));
		
		row.addStyleName("board-row-group");

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

	private void setInfoLabels() throws RiotApiException {
		Summoner summoner = summonerService.findOneBySummonerName(currentAccount.getSummonerName());
		LeagueItem league = summonerService.getLeagueItemByName(currentAccount.getSummonerName());
		summonerLevelLabel = new BoardLabel("Games", String.valueOf(summoner.getSummonerLevel()), "level");
		summonerLossesLabel = new BoardLabel("Losses", String.valueOf(league.getLosses()), "losses");
		notAvailableBox = new BoardBox(summonerLossesLabel);
		summonerWinsLabel = new BoardLabel("Wins", String.valueOf(league.getWins()), "wins");
		summonerRankLabel = new BoardLabel("Rank", String.valueOf(league.getRank()), "rank");
	}

}
