package de.leuphana.ui.components;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.TextStyle;
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
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

@SpringComponent
@PrototypeScope
public class MatchHistoryGrid extends Grid<MatchReference> {

	@Autowired
	private MatchHistoryDataProvider dataProvider;

	public MatchHistoryGrid() {
		addStyleName("orders-grid");
		setSizeFull();
		removeHeaderRow(0);

		// Add stylenames to rows
		setStyleGenerator(MatchHistoryGrid::getRowStyle);

		// Due column
		Column<MatchReference, String> dateColumn = addColumn(
				matchReference -> twoRowCell(getTimeHeader(new Timestamp(matchReference.getTimestamp())), String.valueOf(matchReference.getTimestamp())),
				new HtmlRenderer());
		dateColumn.setStyleGenerator(matchReference -> "due");

		// Summary column
		Column<MatchReference, String> summaryColumn = addColumn(matchReference -> {
			Match match = dataProvider.fetchMatchById(matchReference.getGameId());
			return twoRowCell(match.getGameMode(), getMatchSummary(match));
		}, new HtmlRenderer()).setExpandRatio(1).setSortProperty("matchReference.id").setMinimumWidthFromContent(false);
		summaryColumn.setStyleGenerator(matchReference -> "summary");
	}

	@PostConstruct
	protected void init() {
		setDataProvider(dataProvider);
	}

	/**
	 * Makes date into a more readable form; "Today", "Mon 7", "12 Jun"
	 * 
	 * @param date
	 *            The date to make into a string
	 * @return A formatted string depending on how far in the future the date
	 *         is.
	 */
	private static String getTimeHeader(Timestamp timestamp) {
		LocalDate date = timestamp.toLocalDateTime().toLocalDate();
		LocalDate today = LocalDate.now();
		if (date.isEqual(today)) {
			return "Today";
		} else {
			// Show weekday for upcoming days
			LocalDate todayNextWeek = today.plusDays(7);
			if (date.isAfter(today) && date.isBefore(todayNextWeek)) {
				// "Mon 7"
				return date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US) + " "
						+ date.getDayOfMonth();
			} else {
				// In the past or more than a week in the future
				return date.getDayOfMonth() + " " + date.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
			}
		}
	}

	private static String getRowStyle(MatchReference matchReference) {
		String style = matchReference.getRole().toLowerCase();

		// Avaiable roles
		// DUO, NONE, SOLO, DUO_CARRY, DUO_SUPPORT

		switch (style) {
		case "duo":
			style += " today";
			break;
		case "none":
			style += " today";
			break;
		case "solo":
			style += " today";
			break;
		case "duo_carry":
			style += " today";
			break;
		case "duo_support":
			style += " today";
			break;
		default:
			break;
		}

		return style;
	}

	private static String getMatchSummary(Match match) {
		Stream<String> participants = match.getParticipantIdentities().stream()
				.map(participantIdentity -> participantIdentity.getPlayer().getSummonerName());
		return participants.collect(Collectors.joining(", "));
	}

	private static String twoRowCell(String header, String content) {
		return "<div class=\"header\">" + HtmlUtils.htmlEscape(header) + "</div><div class=\"content\">"
				+ HtmlUtils.htmlEscape(content) + "</div>";
	}

}
