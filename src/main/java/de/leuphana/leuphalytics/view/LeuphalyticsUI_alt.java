package de.leuphana.leuphalytics.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.leuphana.leuphalytics.connector.dbconnector.WidgetService;
import de.leuphana.leuphalytics.connector.restconnector.RiotClient;
import de.leuphana.leuphalytics.model.match.RiotMatch;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;


public class LeuphalyticsUI_alt extends UI {

	private VerticalLayout root;

	@Autowired
	DashboardLayout dashboardLayout;

	private final RiotClient riotClient;
	private RiotApi api;

	public LeuphalyticsUI_alt(RiotClient riotClient) {
		this.riotClient = riotClient;
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		/*
		 * initialize UI elements Buttons Big elements etc.
		 */
		setupLayout();
		addHeader();
		try {
			addDashboard();
		} catch (RiotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addDashboard() throws RiotApiException {

		// match list example
		// Grid<RiotMatch> grid = new Grid<>();
		// grid.setCaption("Your matches: ");
		// grid.setItems(riotClient.getMatchListForUser().getMatches());
		// grid.addColumn(RiotMatch::getChampion).setCaption("Champion");
		// grid.addColumn(RiotMatch::getSeason).setCaption("Season");
		// grid.addColumn(RiotMatch::getQueue).setCaption("Queue");
		// grid.addColumn(RiotMatch::getTimestamp).setCaption("Timestamp");
		// grid.addColumn(RiotMatch::getLane).setCaption("Lane");
		// grid.setSizeFull();
		// root.addComponent(grid);
		// root.setExpandRatio(grid, 1); // Expand to fill

		// matchList with RiotApi.jar

		


		root.addComponent(dashboardLayout);

	}
	public void getChampionNamebyId() {
		
	}
	
	private void addHeader() {
		Label header = new Label("Leuphalytics");
		header.addStyleName(ValoTheme.LABEL_H1);
		root.addComponent(header);
	}

	private void setupLayout() {
		root = new VerticalLayout();
		root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(root);
	}

}