package de.leuphana.ui.components.widgets;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.ImageRenderer;

import de.leuphana.ui.components.ItemDataProvider;
import de.leuphana.ui.util.DDragonUrlFormatter;
import net.rithms.riot.api.endpoints.static_data.dto.Item;

@SpringComponent
@PrototypeScope
public class ItemGridWidget extends Grid<Item> implements WidgetComponent {

	private Long id = 3l;

	@Autowired
	private ItemDataProvider itemDataProvider;

	private DDragonUrlFormatter dDragonUrlFormatter;

	public ItemGridWidget() throws IOException {
		setId("ItemGridWidget");
		setSizeFull();
		removeHeaderRow(0);
		addStyleName("ItemGrid");

		dDragonUrlFormatter = new DDragonUrlFormatter();


	}

	@PostConstruct
	protected void init() {
		setDataProvider(itemDataProvider);
		addColumn(item -> new ExternalResource(dDragonUrlFormatter.getUrlbyItemImageName("1028.png")),
				new ImageRenderer()).setCaption("Picture");

	}

	@Override
	public Long getWidgetId() {
		// TODO Auto-generated method stub
		return this.id;
	}

}