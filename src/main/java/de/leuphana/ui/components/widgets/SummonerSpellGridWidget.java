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
import de.leuphana.ui.components.SummonerSpellDataProvider;
import de.leuphana.ui.util.DDragonUrlFormatter;
import net.rithms.riot.api.endpoints.static_data.dto.Item;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell;

@SpringComponent
@PrototypeScope
public class SummonerSpellGridWidget extends Grid<SummonerSpell> implements WidgetComponent {

	private Long id = 5l;
	@Autowired
	private SummonerSpellDataProvider sumSpellDataProvider;

	private DDragonUrlFormatter dDragonUrlFormatter;

	@PostConstruct
	protected void init() {
		setDataProvider(sumSpellDataProvider);
	

	}

	public SummonerSpellGridWidget() throws IOException {
		setId("SummonerSpellGridWidget");
		setSizeFull();
		removeHeaderRow(0);
		addStyleName("SummonerSpellGrid");
		
		dDragonUrlFormatter = new DDragonUrlFormatter();
		
		addColumn(summonerSpell -> new ExternalResource(dDragonUrlFormatter.getUrlbySumSpellImageName("SummonerFlash.png")),
				new ImageRenderer()).setCaption("Picture");
		

	
	}

	@Override
	public Long getWidgetId() {
		// TODO Auto-generated method stub
		return this.id;
	}

}