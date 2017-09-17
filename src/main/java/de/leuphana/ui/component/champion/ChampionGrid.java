package de.leuphana.ui.component.champion;

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
	
	private final DDragonUrlFormatter dDragonUrlFormatter = new DDragonUrlFormatter();;
	@PostConstruct
	protected void init(){
		setDataProvider(championDataProvider);
	}
	
	
	public ChampionGrid() throws IOException {
		addStyleName("championGrid");
		setSizeFull();
		setCaption("Champions");
		getHeaderRow(0).setStyleName("championGridHeader");
		Column<Champion, ExternalResource> imageColumn = addColumn(
				champion ->  new ExternalResource(dDragonUrlFormatter.getUrlByChampionImageName(champion.getImage().getFull())),
				    new ImageRenderer())
				.setCaption("Picture")
				.setMinimumWidth(200d);
				
				;

		Column<Champion, String> nameColumn = addColumn(Champion::getName, new HtmlRenderer())
				.setCaption("Name")
				;
		
		Column<Champion, String> titleColumn = addColumn(Champion::getTitle, new HtmlRenderer())
				.setCaption("Title")
				
				;
		
		
	}

}
