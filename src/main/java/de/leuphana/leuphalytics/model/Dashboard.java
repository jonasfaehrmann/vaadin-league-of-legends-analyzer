package de.leuphana.leuphalytics.model;

import java.util.List;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

import de.leuphana.leuphalytics.model.widget.Widget;

@SpringComponent
@UIScope
public abstract class Dashboard extends VerticalLayout {
	
	private List<Widget> widgetList;
	
}
