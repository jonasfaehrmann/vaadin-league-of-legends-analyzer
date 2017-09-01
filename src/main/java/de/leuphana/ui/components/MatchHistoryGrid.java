package de.leuphana.ui.components;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

import net.rithms.riot.api.endpoints.match.dto.Match;

@SpringComponent
@PrototypeScope
public class MatchHistoryGrid extends Grid<Match> {

	@Autowired
	private MatchHistoryDataProvider dataProvider;

	public MatchHistoryGrid() {
		addStyleName("orders-grid");
		setSizeFull();
		removeHeaderRow(0);

		// Add stylenames to rows
		setStyleGenerator(MatchHistoryGrid::getRowStyle);

		// Due column
		Column<Match, String> dateColumn = addColumn(
				match -> twoRowCell(getTimeHeader(new Timestamp(match.getGameCreation())), String.valueOf(TimeUnit.SECONDS.toMinutes(match.getGameDuration()))),
				new HtmlRenderer());
		dateColumn.setStyleGenerator(matchReference -> "due");

		// Summary column
		Column<Match, String> summaryColumn = addColumn(match -> {
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

	private static String getRowStyle(Match match) {
		String style = match.getGameMode();

		// Avaiable roles
		// DUO, NONE, SOLO, DUO_CARRY, DUO_SUPPORT

		switch (style) {
		case "classic":
			style += " today";
			break;
		case "odin":
			style += " today";
			break;
		case "aram":
			style += " today";
			break;
		case "tutorial":
			style += " today";
			break;
		case "one_forall":
			style += " today";
			break;
		case "ascension":
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
