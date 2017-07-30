package de.leuphana.leuphalytics.view.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.board.Board;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Title("Leuphalytics")
@SpringUI
@Theme("valo")
public class LeuphalyticsUI extends UI {

	private VerticalLayout root;

	// @Override
	// protected void init(VaadinRequest request) {
	// setupLayout();
	// addHeader();
	// }
	//
	// private void setupLayout() {
	// root = new VerticalLayout();
	// root.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
	// setContent(root);
	// }
	//
	// private void addHeader() {
	// Label header = new Label("Leuphalytics");
	// header.addStyleName(ValoTheme.LABEL_H1);
	// root.addComponent(header);
	// 

	protected void init(VaadinRequest request) {
		Board board = new Board();
		board.setSizeFull();
		Label lbl1 = createLabel("LABEL1");
		Label lbl2 = createLabel("LABEL2");
		Label lbl3 = createLabel("LABEL3");
		Label lbl4 = createLabel("LABEL4");

		board.addRow(lbl1, lbl2, lbl3, lbl4);
		setContent(board);
	}

	private Label createLabel(String content) {
		Label label = new Label(content);
		label.setSizeFull();
		label.addStyleName(ValoTheme.LABEL_H1);
		label.addStyleName("mystyle");
		return label;
	}

}
