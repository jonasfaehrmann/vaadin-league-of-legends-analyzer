package de.leuphana.leuphalytics;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.leuphana.leuphalytics.rest.RiotClient;

@Title("Leuphalytics")
@SpringUI
@Theme("valo")
public class LeuphalyticsUI extends UI {

	private VerticalLayout layout;
	
	private final RiotClient riotClient;
	
	public LeuphalyticsUI(RiotClient riotClient) {
		this.riotClient = riotClient;
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		/*
		 * initialize UI elements Buttons Big elements etc.
		 */
		setupLayout();
		addHeader();
		addDashboard();
	}

	private void addDashboard() {
		//layout.addComponent(dashboard);
		TextField text = new TextField(riotClient.getMatchListForUser().toString());
		layout.addComponent(text);

	}

	private void addHeader() {
		Label header = new Label("Leuphalytics");
		header.addStyleName(ValoTheme.LABEL_H1);
		layout.addComponent(header);
	}

	private void setupLayout() {
		layout = new VerticalLayout();
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(layout);
	}
}