package de.leuphana.leuphalytics.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.leuphana.leuphalytics.connector.dbconnector.WidgetService;
import de.leuphana.leuphalytics.model.widget.Widget;

@SpringComponent
public class DashboardLayout extends VerticalLayout {
	
	@Autowired
	WidgetService service;

	private Widget widget;
	
	private Binder<Widget> binder = new Binder<>(Widget.class);
	
	private Grid<Widget> grid = new Grid(Widget.class);
	private TextField id = new TextField("ID");
    private TextField name = new TextField("NAME");
//    private Button save = new Button("Save", e -> saveWidget());

	
	@PostConstruct
	void init() { 
		setWidgets(service.findAll());
	}

	private void setWidgets(List<Widget> widgets) {
		removeAllComponents();
		widgets.forEach(widget -> addComponent(new WidgetLayout(widget)));
	}

}
