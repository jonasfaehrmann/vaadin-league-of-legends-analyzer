package de.leuphana.ui.components;

import java.util.List;

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
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import de.leuphana.backend.service.SingleMatchService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;

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

		// data configuration
		Match match = singleMatchService.findOneBySummonerName((singleMatchService.getMatchId()),
				singleMatchService.getSummonerName());
		List<Participant> participants = match.getParticipants();
		ParticipantStats stats;

		// new layout which will be added
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(false);
		layout.setWidth("100%");

		// chart A
		Chart chartA = new Chart(ChartType.COLUMN);
		Configuration confA = chartA.getConfiguration();
		PlotOptionsColumn plotOptionsA = new PlotOptionsColumn();
		DataLabels dataLabelsA = new DataLabels();
		ListSeries seriesA;

		chartA.setHeight("100%");
		chartA.setWidth("100%");

		confA.setTitle("Performance");
		plotOptionsA.setCursor(Cursor.POINTER);
		dataLabelsA.setEnabled(false);
		dataLabelsA.setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
		plotOptionsA.setDataLabels(dataLabelsA);
		// plotOptionsA.setStacking(Stacking.NORMAL);
		confA.setPlotOptions(plotOptionsA);

		// x axis
		XAxis x = new XAxis();
		x.setCategories(new String[] { "Kills", "Assists", "Deaths" });
		confA.addxAxis(x);

		// chartA data configuration
		for (Participant participant : participants) {
			String participantName = String.valueOf(participant.getParticipantId());
			stats = participant.getStats();
			seriesA = new ListSeries("Participant " + participantName);
			seriesA.addData(stats.getKills());
			seriesA.addData(stats.getAssists());
			seriesA.addData(stats.getDeaths());
			confA.addSeries(seriesA);
		}

		// add chart
		chartA.drawChart(confA);
		layout.addComponent(chartA);

		// chart B
		Chart chartB = new Chart(ChartType.PIE);
		Configuration confB = chartB.getConfiguration();
		PlotOptionsPie plotOptionsB = new PlotOptionsPie();
		DataLabels dataLabelsB = new DataLabels();
		DataSeries seriesB = new DataSeries();;

		chartB.setHeight("100%");
		chartB.setWidth("100%");

		confB.setTitle("Gold earned");
		plotOptionsB.setCursor(Cursor.POINTER);
		dataLabelsB.setEnabled(true);
		dataLabelsB.setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(0) + ' %'");
		plotOptionsB.setDataLabels(dataLabelsB);
		confB.setPlotOptions(plotOptionsB);

		// chartA data configuration
		for (Participant participant : participants) {
			String participantName = String.valueOf(participant.getParticipantId());
			stats = participant.getStats();
			seriesB.add(new DataSeriesItem(participantName, stats.getGoldEarned()));
			System.out.println(stats.getGoldEarned());
		}
		confB.setSeries(seriesB);
		chartB.drawChart(confB);

		layout.addComponent(chartB);

		System.out.println("added layout in component");

		return layout;

	}

}