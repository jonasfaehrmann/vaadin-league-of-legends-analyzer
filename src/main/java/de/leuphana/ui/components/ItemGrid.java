package de.leuphana.ui.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.ImageRenderer;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.Item;

@SpringComponent
@PrototypeScope
public class ItemGrid extends Grid<Item>{

	@Autowired
	private ItemDataProvider itemDataProvider;
	
	@PostConstruct
	protected void init(){
		setDataProvider(itemDataProvider);
	}
	
	
	public ItemGrid() {
		
		addStyleName("ItemGrid");
		setSizeFull();
		setCaption("Item");
		
		Column<Item, ExternalResource> imageColumn = addColumn( 
			    p -> new ExternalResource("http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/Annie.png"),
			    new ImageRenderer()) 
				.setCaption("Image")
		
				;
		
		Column<Item, String> nameColumn = addColumn(Item::getName, new HtmlRenderer())
				.setCaption("Name")
				;
		
		Column<Item, String> descriptionColumn = addColumn(Item::getDescription, new HtmlRenderer())
				.setCaption("Description")
			;
						
	}

}