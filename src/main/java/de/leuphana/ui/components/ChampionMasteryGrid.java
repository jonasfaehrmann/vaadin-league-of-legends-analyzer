package de.leuphana.ui.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

import de.leuphana.backend.service.ChampionMasteryService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;

@SpringComponent
@PrototypeScope
public class ChampionMasteryGrid extends Grid<ChampionMastery> {

	@Autowired
	private ChampionMasteryDataProvider dataProvider;

	@Autowired
	private ChampionMasteryService championMasterService;

	public ChampionMasteryGrid() {
		setSizeFull();
	}

	@PostConstruct
	protected void init() {
		setDataProvider(dataProvider);
	}

	public Component getChart() throws RiotApiException {

		// top 50 champions
		List<ChampionMastery> championMasteryList = championMasterService.findAll().subList(0, 20);

		Chart chart = new Chart(ChartType.COLUMN);
		Configuration conf = chart.getConfiguration();

		conf.setTitle("Champion mastery level overview");
		conf.setSubTitle("for your top 20 champions");

		XAxis x = new XAxis();

		YAxis y = new YAxis();
		y.setMin(0);
		y.setTitle("Champion level");
		conf.addyAxis(y);

		Tooltip tooltip = new Tooltip();
		tooltip.setFormatter("'Level for '+this.x +': '+ this.y");
		conf.setTooltip(tooltip);

		PlotOptionsColumn plot = new PlotOptionsColumn();
		plot.setPointPadding(0.2);
		plot.setBorderWidth(0);

		// data configuration
		ListSeries series = new ListSeries();
		series.setName("Champion mastery");

		for (ChampionMastery championMasteryItem : championMasteryList) {
			series.addData(championMasteryItem.getChampionLevel());
			x.addCategory(String
					.valueOf(championMasterService.findChampionById(championMasteryItem.getChampionId()).getName()));
		}
		conf.addSeries(series);
		conf.addxAxis(x);

		chart.drawChart(conf);

		return chart;
	}

}