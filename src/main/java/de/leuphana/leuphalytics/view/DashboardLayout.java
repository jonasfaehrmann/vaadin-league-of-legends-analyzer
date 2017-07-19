package de.leuphana.leuphalytics.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;

import de.leuphana.leuphalytics.connector.dbconnector.template.WidgetJDBCTemplate;
import de.leuphana.leuphalytics.model.widget.Widget;

@SpringComponent
public class DashboardLayout extends VerticalLayout {
	
	public ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

	@Autowired
	WidgetJDBCTemplate widgetJDBCTemplate;

	@PostConstruct
	void init() { 
		setWidgets(widgetJDBCTemplate.listWidgets());
	}

	private void setWidgets(List<Widget> widgets) {
		removeAllComponents();
		
	}

}
