package de.leuphana.leuphalytics.view;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.leuphana.leuphalytics.model.widget.Widget;

public class WidgetLayout extends VerticalLayout {
	
	private final TextField widgetId;
	private final TextField widgetName;
	
	public WidgetLayout(Widget widget) {
		widgetId = new TextField(String.valueOf(widget.getWidgetId()));
		widgetName = new TextField(widget.getWidgetName());
		
		addComponents(widgetId, widgetName);
		
//		Binder<Widget> binder = new Binder<>(Widget.class);
//		
//		binder.bindInstanceFields(this);
//		binder.setBean(widget);
 		
		
	}

}
