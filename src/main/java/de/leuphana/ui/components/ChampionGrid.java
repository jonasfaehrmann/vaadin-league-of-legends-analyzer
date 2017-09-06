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

import de.leuphana.ui.util.DDragonUrlFormatter;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.Image;


@SpringComponent
@PrototypeScope
public class ChampionGrid extends Grid<Champion> {

	@Autowired
	private ChampionDataProvider championDataProvider;
	
	private DDragonUrlFormatter dDragonUrlFormatter;
	@PostConstruct
	protected void init(){
		setDataProvider(championDataProvider);
	}
	
	
	public ChampionGrid() throws IOException {
		addStyleName("championGrid");
		setSizeFull();
		setCaption("Champions");
		dDragonUrlFormatter = new DDragonUrlFormatter();
		
		Column<Champion, ExternalResource> imageColumn = addColumn(
				champion ->  new ExternalResource(dDragonUrlFormatter.getUrlByChampionImageName(champion.getImage().getFull())),
				    new ImageRenderer())
				.setCaption("Picture")
				;

		Column<Champion, String> nameColumn = addColumn(Champion::getName, new HtmlRenderer())
				.setCaption("Name")
				;
		
		Column<Champion, String> loreColumn = addColumn(Champion::getLore, new HtmlRenderer())
				.setCaption("Lore")
				;
		
		
	}

}
