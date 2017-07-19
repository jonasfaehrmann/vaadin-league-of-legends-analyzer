package de.leuphana.leuphalytics.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;

import de.leuphana.leuphalytics.connector.dbconnector.WidgetRepository;
import de.leuphana.leuphalytics.model.widget.Widget;

@SpringComponent
public class DashboardLayout extends VerticalLayout {

	@Autowired
	WidgetRepository widgetRepository;

	@PostConstruct
	void init() { 	
//		setWidgets(dashboard.getWidgets());
	}

	private void setWidgets(List<Widget> widgets) {
		removeAllComponents();
		
	}

}
