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

import de.leuphana.ui.util.DDragonUrlFormatter;
import net.rithms.riot.api.endpoints.static_data.dto.Item;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell;


@SpringComponent
@PrototypeScope
public class SummonerSpellGrid  extends Grid<SummonerSpell>{

	@Autowired
	private SummonerSpellDataProvider sumSpellDataProvider;
	
	private DDragonUrlFormatter dDragonUrlFormatter;
	@PostConstruct
	protected void init(){
		setDataProvider(sumSpellDataProvider);
	}
	
		
		public SummonerSpellGrid() {
			
			addStyleName("SummonerSpellGrid");
			setSizeFull();
			setCaption("Summonerspells");
			dDragonUrlFormatter = new DDragonUrlFormatter();
			

			
			
			Column<SummonerSpell, ExternalResource> imageColumn = addColumn(
					spell->  new ExternalResource(dDragonUrlFormatter.getUrlbyItemImageName(spell.getImage().getFull())),
					    new ImageRenderer())
					.setCaption("Picture")
					;
			
			
			Column<SummonerSpell, String> nameColumn = addColumn(SummonerSpell::getName, new HtmlRenderer())
					.setCaption("Name")
					;
			
			Column<SummonerSpell, String> descriptionColumn = addColumn(SummonerSpell::getDescription, new HtmlRenderer())
					.setCaption("Description")
				;
			
			Column<SummonerSpell, String> toolTipColumn = addColumn(SummonerSpell::getTooltip, new HtmlRenderer())
					.setCaption("Description")
				;
							
		}

	}

