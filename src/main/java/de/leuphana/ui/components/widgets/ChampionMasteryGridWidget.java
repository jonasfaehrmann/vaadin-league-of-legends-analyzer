package de.leuphana.ui.components.widgets;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;

import de.leuphana.ui.components.ChampionMasteryDataProvider;
import de.leuphana.ui.components.MatchHistoryDataProvider;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import net.rithms.riot.api.endpoints.match.dto.Match;

@SpringComponent
@PrototypeScope
public class ChampionMasteryGridWidget extends Grid<ChampionMastery> implements WidgetComponent {

	private Long id = 6l;
	
	@Autowired
	private ChampionMasteryDataProvider dataProvider;

	public ChampionMasteryGridWidget() {
		setCaption("ChampionMasteryGridWidget");
		setSizeFull();
		addStyleName("orders-grid");
		setSizeFull();
		removeHeaderRow(0);
		addColumn(ChampionMastery::getChampionId).setCaption("Champion id");
		addColumn(ChampionMastery::getChampionLevel).setCaption("Mastery Level");
	}

	@PostConstruct
	protected void init() {
		setDataProvider(dataProvider);
		setItems(dataProvider.getChampionMasteryList());
	}
	
	@Override
	public Long getWidgetId(){
		return this.id;
	}

}
