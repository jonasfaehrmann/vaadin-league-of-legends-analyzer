package de.leuphana.ui.components;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

import de.leuphana.backend.service.RiotService;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

@SpringComponent
@PrototypeScope
public class ChampionMasteryGrid extends Grid<ChampionMastery> {

	@Autowired
	private ChampionMasteryDataProvider dataProvider;

	public ChampionMasteryGrid() {
		addStyleName("orders-grid");
		setSizeFull();
	}

		
	@PostConstruct
	protected void init() {
		setDataProvider(dataProvider);
		setChampions(dataProvider.fetchChampions());
	}
	
	private void setChampions(Stream<ChampionMastery> stream) {
		setItems(stream);
		addColumn(ChampionMastery::getChampionId).setCaption("Champion id");
		addColumn(ChampionMastery::getChampionLevel).setCaption("Champion level");
		addColumn(ChampionMastery::getLastPlayTime).setCaption("Played last date");
	}

	





}
