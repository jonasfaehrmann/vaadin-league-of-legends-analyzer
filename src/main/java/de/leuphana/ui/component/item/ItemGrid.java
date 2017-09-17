package de.leuphana.ui.component.item;

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

import de.leuphana.ui.util.DDragonUrlFormatter;
import net.rithms.riot.api.endpoints.static_data.dto.Item;
import net.rithms.riot.api.endpoints.static_data.dto.Image;

@SpringComponent
@PrototypeScope
public class ItemGrid extends Grid<Item>{


	@Autowired
	private ItemDataProvider itemDataProvider;
	
	private DDragonUrlFormatter dDragonUrlFormatter;
	@PostConstruct
	protected void init(){
		setDataProvider(itemDataProvider);
	}
	
	
	public ItemGrid() throws IOException {
		addStyleName("itemGrid");
		setSizeFull();
		setCaption("Items");
		dDragonUrlFormatter = new DDragonUrlFormatter();
		
		Column<Item, ExternalResource> imageColumn = addColumn(
				item -> new ExternalResource(dDragonUrlFormatter.getUrlbyItemImageName(item.getImage().getFull())),
				new ImageRenderer()).setCaption("Picture");

		Column<Item, String> nameColumn = addColumn(Item::getName, new HtmlRenderer()).setCaption("Name");

		Column<Item, String> descriptionColumn = addColumn(Item::getDescription, new HtmlRenderer())
				.setCaption("Description");
		
		
	}

}