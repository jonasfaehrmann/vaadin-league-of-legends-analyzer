package de.leuphana.leuphalytics.view.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Title("Leuphalytics")
@SpringUI
@Theme("valo")
public class LeuphalyticsUI extends UI {
	
	private VerticalLayout root;

	@Override
	protected void init(VaadinRequest request) {
		setupLayout();
		addHeader();
	}
	
	private void setupLayout() {
		root = new VerticalLayout();
		root.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		setContent(root);
	}
	
	private void addHeader() {
		Label header = new Label("Leuphalytics");
		header.addStyleName(ValoTheme.LABEL_H1);
		root.addComponent(header);
	}
	
	
}
