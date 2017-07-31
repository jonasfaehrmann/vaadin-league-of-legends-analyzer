package de.leuphana.ui.view.dashboard;

import java.time.MonthDay;
import java.time.Year;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.board.Row;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;

import de.leuphana.backend.data.DashboardData;
import de.leuphana.backend.data.DeliveryStats;
import de.leuphana.backend.data.entity.Order;
import de.leuphana.backend.data.entity.Product;
import de.leuphana.backend.service.OrderService;
import de.leuphana.backend.service.RiotService;
import de.leuphana.ui.components.CurrentMatchGrid;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.orderedit.OrderEditView;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

/**
 * The dashboard view showing statistics about sales and deliveries.
 * <p>
 * Created as a single View class because the logic is so simple that using a
 * pattern like MVP would add much overhead for little gain. If more complexity
 * is added to the class, you should consider splitting out a presenter.
 */
@SpringView
public class DashboardView extends DashboardViewDesign implements View {

	private static final String BOARD_ROW_PANELS = "board-row-panels";

	private final NavigationManager navigationManager;
	private final OrderService orderService;

	private final BoardLabel todayLabel = new BoardLabel("Today", "3/7", "today");
	private final BoardLabel notAvailableLabel = new BoardLabel("N/A", "1", "na");
	private final BoardBox notAvailableBox = new BoardBox(notAvailableLabel);
	private final BoardLabel newLabel = new BoardLabel("New", "2", "new");
	private final BoardLabel tomorrowLabel = new BoardLabel("Tomorrow", "4", "tomorrow");

	private final Grid<MatchReference> matchListForUser = new Grid<>();

	private ListSeries deliveriesThisMonthSeries;
	private ListSeries deliveriesThisYearSeries;
	private ListSeries[] salesPerYear;

	private DataSeries deliveriesPerProductSeries;

	private RiotService riotService;

	private final CurrentMatchGrid matchGrid;

	@Autowired
	public DashboardView(NavigationManager navigationManager, OrderService orderService, CurrentMatchGrid matchGrid,
			RiotService riotService) {
		this.navigationManager = navigationManager;
		this.orderService = orderService;
		this.matchGrid = matchGrid;
		this.riotService = riotService;
	}

	@PostConstruct
	public void init() throws RiotApiException {
		setResponsive(true);

		Row row = board.addRow(new BoardBox(todayLabel), notAvailableBox, new BoardBox(newLabel),
				new BoardBox(tomorrowLabel));
		row.addStyleName("board-row-group");

		row = board.addRow(new BoardBox(matchListForUser));
		row.addStyleName(BOARD_ROW_PANELS);

		initMatchListForUser();

	}

	private void initMatchListForUser() throws RiotApiException {
		matchListForUser.setId("matchListForUser");
		matchListForUser.setSizeFull();

		matchListForUser.setCaption("Your matches: ");
		matchListForUser.setItems(riotService.getMatchListForUser());
		matchListForUser.addColumn(MatchReference::getLane).setCaption("Lane");
		matchListForUser.addColumn(MatchReference::getQueue).setCaption("Queue");

	}

	protected void configureColumnSeries(ListSeries series) {
		PlotOptionsColumn options = new PlotOptionsColumn();
		options.setBorderWidth(1);
		options.setGroupPadding(0);
		series.setPlotOptions(options);

		YAxis yaxis = series.getConfiguration().getyAxis();
		yaxis.setGridLineWidth(0);
		yaxis.setLabels(new Labels(false));
		yaxis.setTitle("");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		DashboardData data = fetchData();
		updateLabels(data.getDeliveryStats());
		updateGraphs(data);
	}

	private DashboardData fetchData() {
		return orderService.getDashboardData(MonthDay.now().getMonthValue(), Year.now().getValue());
	}

	private void updateGraphs(DashboardData data) {
		deliveriesThisMonthSeries.setData(data.getDeliveriesThisMonth());
		deliveriesThisYearSeries.setData(data.getDeliveriesThisYear());

		for (int i = 0; i < 3; i++) {
			salesPerYear[i].setData(data.getSalesPerMonth(i));
		}

		for (Entry<Product, Integer> entry : data.getProductDeliveries().entrySet()) {
			deliveriesPerProductSeries.add(new DataSeriesItem(entry.getKey().getName(), entry.getValue()));
		}
	}

	private void updateLabels(DeliveryStats deliveryStats) {
		todayLabel.setContent(deliveryStats.getDeliveredToday() + "/" + deliveryStats.getDueToday());
		notAvailableLabel.setContent(Integer.toString(deliveryStats.getNotAvailableToday()));
		notAvailableBox.setNeedsAttention(deliveryStats.getNotAvailableToday() > 0);
		newLabel.setContent(Integer.toString(deliveryStats.getNewOrders()));
		tomorrowLabel.setContent(Integer.toString(deliveryStats.getDueTomorrow()));
	}

	/**
	 * Extends {@link PlotOptionsLine} to support zIndex. Omits getter/setter,
	 * since they are not needed in our case.
	 *
	 */
	private static class PlotOptionsLineWithZIndex extends PlotOptionsLine {
		@SuppressWarnings("unused")
		private Number zIndex;

		public PlotOptionsLineWithZIndex(Number zIndex) {
			this.zIndex = zIndex;
		};
	}

	public void selectedOrder(Order order) {
		navigationManager.navigateTo(OrderEditView.class, order.getId());
	}

}
