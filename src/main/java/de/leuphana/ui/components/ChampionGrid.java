package de.leuphana.ui.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;


@SpringComponent
@PrototypeScope
public class ChampionGrid extends Grid<Champion> {

	@Autowired
	private ChampionDataProvider champDataProvider;
	
	@PostConstruct
	protected void init(){
		setDataProvider(champDataProvider);
	}
	
	
	public ChampionGrid() {
		addStyleName("championGrid");
		setSizeFull();
		setCaption("Champions");
		

		Column<Champion, String> nameColumn = addColumn(Champion::getName, new HtmlRenderer())
				.setCaption("Name")
				;
		
		Column<Champion, String> loreColumn = addColumn(Champion::getLore, new HtmlRenderer())
				.setCaption("Lore")
				;
	}

}
