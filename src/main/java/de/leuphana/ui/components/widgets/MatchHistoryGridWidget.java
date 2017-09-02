package de.leuphana.ui.components.widgets;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;

import de.leuphana.ui.components.MatchHistoryDataProvider;
import net.rithms.riot.api.endpoints.match.dto.Match;

/**
 * 
 * @author Jonas Fährmann
 *
 */
@SpringComponent
@PrototypeScope
public class MatchHistoryGridWidget extends Grid<Match> implements Widget {

	private Integer id = 1;
	
	@Autowired
	private MatchHistoryDataProvider dataProvider;

	public MatchHistoryGridWidget() {
		setId("MatchHistoryGridWidget");
		setSizeFull();
		
		addStyleName("orders-grid");
		setSizeFull();
		removeHeaderRow(0);
		addColumn(Match::getGameMode);
	}

	@PostConstruct
	protected void init() {
		setDataProvider(dataProvider);
	}
	
	@Override
	public Integer getWidgetId(){
		return this.id;
	}

}
