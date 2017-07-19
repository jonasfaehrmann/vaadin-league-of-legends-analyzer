package de.leuphana.leuphalytics.view;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.leuphana.leuphalytics.model.widget.Widget;

public class WidgetLayout extends VerticalLayout {
	
	private final TextField header;
	private final TextField content;
	
	public WidgetLayout(Widget widget) {
		header = new TextField();
		content = new TextField();
		
		addComponents(header, content);
	}

}
