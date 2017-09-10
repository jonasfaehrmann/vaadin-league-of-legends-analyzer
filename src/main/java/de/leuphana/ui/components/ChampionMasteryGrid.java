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
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

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

	public Component getChart() {
		Chart chart = new Chart(ChartType.BAR);

		Configuration conf = chart.getConfiguration();

		conf.setTitle("Historic World Population by Region");
		conf.setSubTitle("Source: Wikipedia.org");

		XAxis x = new XAxis();
		x.setCategories("Africa", "America", "Asia", "Europe", "Oceania");
		x.setTitle((String) null);
		conf.addxAxis(x);

		YAxis y = new YAxis();
		y.setMin(0);
		AxisTitle title = new AxisTitle("Population (millions)");
		title.setAlign(VerticalAlign.MIDDLE);
		y.setTitle(title);
		conf.addyAxis(y);

		Tooltip tooltip = new Tooltip();
		tooltip.setFormatter("this.series.name +': '+ this.y +' millions'");
		conf.setTooltip(tooltip);

		PlotOptionsBar plot = new PlotOptionsBar();
		plot.setDataLabels(new DataLabels(true));
		conf.setPlotOptions(plot);

		Legend legend = new Legend();
		legend.setLayout(LayoutDirection.VERTICAL);
		legend.setAlign(HorizontalAlign.RIGHT);
		legend.setVerticalAlign(VerticalAlign.TOP);
		legend.setX(-100);
		legend.setY(100);
		legend.setFloating(true);
		legend.setBorderWidth(1);
		legend.setBackgroundColor(new SolidColor("#FFFFFF"));
		legend.setShadow(true);
		conf.setLegend(legend);

		conf.disableCredits();

		List<Series> series = new ArrayList<Series>();
		series.add(new ListSeries("Year 1800", 107, 31, 635, 203, 2));
		series.add(new ListSeries("Year 1900", 133, 156, 947, 408, 6));
		series.add(new ListSeries("Year 2008", 973, 914, 4054, 732, 34));
		conf.setSeries(series);

		chart.drawChart(conf);

		return chart;
	}

}
