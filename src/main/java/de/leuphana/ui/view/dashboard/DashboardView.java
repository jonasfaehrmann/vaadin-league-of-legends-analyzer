package de.leuphana.ui.view.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.board.Row;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;

import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.match.MatchDetailView;
import net.rithms.riot.api.RiotApiException;

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

	private final BoardLabel todayLabel = new BoardLabel("Today", "3/7", "today");
	private final BoardLabel notAvailableLabel = new BoardLabel("N/A", "1", "na");
	private final BoardBox notAvailableBox = new BoardBox(notAvailableLabel);
	private final BoardLabel newLabel = new BoardLabel("New", "2", "new");
	private final BoardLabel tomorrowLabel = new BoardLabel("Tomorrow", "4", "tomorrow");

	private final Button button = new Button("Hello");

	@Autowired
	public DashboardView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}

	@PostConstruct
	public void init() throws RiotApiException {
		setResponsive(true);

		Row row = board.addRow(new BoardBox(todayLabel), notAvailableBox, new BoardBox(newLabel),
				new BoardBox(tomorrowLabel));
		row.addStyleName("board-row-group");

		// link to different view dummy
		row = board.addRow(new BoardBox(button));
		button.addClickListener(e -> navigationManager.navigateTo(MatchDetailView.class, 1));

	}

	@Override
	public void enter(ViewChangeEvent event) {

		// updateLabels(data.getDeliveryStats());
		// updateGraphs(data);
	}

}
