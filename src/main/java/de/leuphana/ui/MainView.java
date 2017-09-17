package de.leuphana.ui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.vaadin.spring.access.SecuredViewAccessControl;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.navigator.ViewLeaveAction;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;

import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.admin.account.AccountAdminView;
import de.leuphana.ui.view.champion.ChampionView;
import de.leuphana.ui.view.championMastery.ChampionMasteryView;
import de.leuphana.ui.view.dashboard.DashboardView;
import de.leuphana.ui.view.item.ItemView;
import de.leuphana.ui.view.matchHistory.MatchHistoryView;
import de.leuphana.ui.view.singleMatch.SingleMatchView;
import de.leuphana.ui.view.summoner.SummonerView;
import de.leuphana.ui.view.summonerSpell.SpellView;
import de.leuphana.ui.view.user.widget.UserWidgetView;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

@SpringViewDisplay
@UIScope
public class MainView extends MainViewDesign implements ViewDisplay {

	private final Map<Class<? extends View>, Button> navigationButtons = new HashMap<>();
	private final NavigationManager navigationManager;
	private final SecuredViewAccessControl viewAccessControl;

	@Autowired
	public MainView(NavigationManager navigationManager, SecuredViewAccessControl viewAccessControl) {
		this.navigationManager = navigationManager;
		this.viewAccessControl = viewAccessControl;
	}

	@PostConstruct
	public void init() {
		attachNavigation(dashboard, DashboardView.class);
		attachNavigation(accounts, AccountAdminView.class);
		attachNavigation(matchHistory, MatchHistoryView.class);
		attachNavigation(widgets, UserWidgetView.class);
		attachNavigation(champion, ChampionView.class);
		attachNavigation(championMastery, ChampionMasteryView.class);
		attachNavigation(singleMatch, SingleMatchView.class);
		attachNavigation(summoner, SummonerView.class);
		attachNavigation(summonerSpell, SpellView.class);
		attachNavigation(item, ItemView.class);

		logout.addClickListener(e -> logout());
	}

	private void attachNavigation(Button navigationButton, Class<? extends View> targetView) {
		boolean hasAccessToView = viewAccessControl.isAccessGranted(targetView);
		navigationButton.setVisible(hasAccessToView);

		if (hasAccessToView) {
			navigationButtons.put(targetView, navigationButton);
			navigationButton.addClickListener(e -> navigationManager.navigateTo(targetView));
		}
	}

	@Override
	public void showView(View view) {
		content.removeAllComponents();
		content.addComponent(view.getViewComponent());

		navigationButtons.forEach((viewClass, button) -> button.setStyleName("selected", viewClass == view.getClass()));

		Button menuItem = navigationButtons.get(view.getClass());
		String viewName = "";
		if (menuItem != null) {
			viewName = menuItem.getCaption();
		}
		activeViewName.setValue(viewName);
	}

	public void logout() {
		ViewLeaveAction doLogout = () -> {
			UI ui = getUI();
			ui.getSession().getSession().invalidate();
			ui.getPage().reload();
		};

		navigationManager.runAfterLeaveConfirmation(doLogout);
	}

}
