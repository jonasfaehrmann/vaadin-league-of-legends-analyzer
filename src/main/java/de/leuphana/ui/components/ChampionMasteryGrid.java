package de.leuphana.ui.components;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.xerces.impl.dtd.models.CMLeaf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

import de.leuphana.backend.service.ChampionMasteryService;
import de.leuphana.backend.service.RiotService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

@SpringComponent
@PrototypeScope
public class ChampionMasteryGrid extends Grid<ChampionMastery> {

	@Autowired
	private ChampionMasteryDataProvider dataProvider;

	@Autowired
	private ChampionMasteryService championMasterService;

	public ChampionMasteryGrid() {
		addStyleName("orders-grid");
		setSizeFull();
	}

	@PostConstruct
	protected void init() {
		setDataProvider(dataProvider);
	}

	// private void setChampions(Stream<ChampionMastery> stream) {
	// setItems(stream);
	// addColumn(ChampionMastery::getChampionId).setCaption("Champion id");
	// addColumn(ChampionMastery::getChampionLevel).setCaption("Champion level");
	// addColumn(ChampionMastery::getLastPlayTime).setCaption("Played last date");
	// }

	public Component getChart() throws RiotApiException {

		// top 50 champions
		List<ChampionMastery> championMasteryList = championMasterService.findAll().subList(0, 20);

		Chart chart = new Chart(ChartType.COLUMN);
		Configuration conf = chart.getConfiguration();

		conf.setTitle("Champion mastery level overview");
		conf.setSubTitle("for your top 20 champions");

		XAxis x = new XAxis();
		for (ChampionMastery championMasteryItem : championMasteryList) {
			x.addCategory(String
					.valueOf(championMasterService.findChampionById(championMasteryItem.getChampionId()).getName()));
		}
		conf.addxAxis(x);

		YAxis y = new YAxis();
		y.setMin(0);
		y.setTitle("Champion level");
		conf.addyAxis(y);

		// Legend legend = new Legend();
		// legend.setLayout(LayoutDirection.VERTICAL);
		// legend.setBackgroundColor(new SolidColor("#FFFFFF"));
		// legend.setAlign(HorizontalAlign.LEFT);
		// legend.setVerticalAlign(VerticalAlign.TOP);
		// legend.setX(100);
		// legend.setY(70);
		// legend.setFloating(true);
		// legend.setShadow(true);
		// conf.setLegend(legend);

		Tooltip tooltip = new Tooltip();
		tooltip.setFormatter("'Level for '+this.x +': '+ this.y");
		conf.setTooltip(tooltip);

		PlotOptionsColumn plot = new PlotOptionsColumn();
		plot.setPointPadding(0.2);
		plot.setBorderWidth(0);

		// data configuration
		ListSeries series = new ListSeries();
		// series.setName(String.valueOf(championMasteryItem.getChampionId()));
		series.setName("Champion mastery");

		for (ChampionMastery championMasteryItem : championMasteryList) {
			series.addData(championMasteryItem.getChampionLevel());
		}
		conf.addSeries(series);

		chart.drawChart(conf);

		return chart;
	}

}
