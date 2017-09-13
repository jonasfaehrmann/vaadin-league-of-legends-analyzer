package de.leuphana.ui.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import de.leuphana.backend.service.SingleMatchService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;

@SpringComponent
@PrototypeScope
public class SingleMatchComponent extends AbsoluteLayout {

	@Autowired
	private SingleMatchDataProvider dataProvider;

	@Autowired
	private SingleMatchService singleMatchService;

	public SingleMatchComponent() {

	}

	@PostConstruct
	protected void init() {
	}

	public Component getCharts() throws RiotApiException {

		Match match = singleMatchService.findOneBySummonerName(Long.parseLong(singleMatchService.getMatchId()),
				singleMatchService.getSummonerName());

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(false);
		layout.setWidth("100%");

		Chart chartA = new Chart(ChartType.PIE);
		chartA.setHeight("100%");
		chartA.setWidth("100%");
		layout.addComponent(chartA);
		// layout.setExpandRatio(chartA, 1.0f);

		Chart chartB = new Chart(ChartType.PIE);
		chartB.setHeight("100%");
		chartB.setWidth("100%");
		layout.addComponent(chartB);
		// layout.setExpandRatio(chartB, 1.0f);

		Chart chart = new Chart(ChartType.PIE);

		Configuration conf = chart.getConfiguration();

		conf.setTitle("Browser market shares at a specific website, 2010");

		PlotOptionsPie plotOptions = new PlotOptionsPie();
		plotOptions.setCursor(Cursor.POINTER);
		DataLabels dataLabels = new DataLabels();
		dataLabels.setEnabled(true);
		dataLabels.setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
		plotOptions.setDataLabels(dataLabels);
		conf.setPlotOptions(plotOptions);

		final DataSeries series = new DataSeries();
		series.add(new DataSeriesItem("Firefox", 45.0));
		series.add(new DataSeriesItem("IE", 26.8));
		DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
		chrome.setSliced(true);
		chrome.setSelected(true);
		series.add(chrome);
		series.add(new DataSeriesItem("Safari", 8.5));
		series.add(new DataSeriesItem("Opera", 6.2));
		series.add(new DataSeriesItem("Others", 0.7));
		conf.setSeries(series);

		chart.drawChart(conf);

		layout.addComponent(chart);
		System.out.println("layout in component geadded");

		return layout;

	}

}
