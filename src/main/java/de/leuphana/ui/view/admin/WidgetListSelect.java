package de.leuphana.ui.view.admin;

import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ListSelect;

import de.leuphana.backend.data.entity.AccountRole;
import de.leuphana.backend.data.entity.Widget;

@SpringComponent
@PrototypeScope
public class WidgetListSelect extends ListSelect<Widget> {
	
	private final WidgetListSelectDataProvider dataProvider;
	
	public WidgetListSelect(WidgetListSelectDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setCaption("Widgets");
		setItemCaptionGenerator(Widget::getName);
		setDataProvider(dataProvider);
	}
	
}
