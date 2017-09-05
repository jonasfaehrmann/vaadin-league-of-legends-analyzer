package de.leuphana.ui.components;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.ImageRenderer;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.Image;


@SpringComponent
@PrototypeScope
public class ChampionGrid extends Grid<Champion> {

	@Autowired
	private ChampionDataProvider champDataProvider;
	
	@PostConstruct
	protected void init(){
		setDataProvider(champDataProvider);
	}
	
	
	public ChampionGrid() throws IOException {
		addStyleName("championGrid");
		setSizeFull();
		setCaption("Champions");
		
		
		Column<Champion, ExternalResource> imageColumn = addColumn(
				p -> new ExternalResource("http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/Annie.png"),
				new ImageRenderer())
				.setCaption("Picture");
				;

		Column<Champion, String> nameColumn = addColumn(Champion::getName, new HtmlRenderer())
				.setCaption("Name")
				;
		
		Column<Champion, String> loreColumn = addColumn(Champion::getLore, new HtmlRenderer())
				.setCaption("Lore")
				;
		
		
	}

}
