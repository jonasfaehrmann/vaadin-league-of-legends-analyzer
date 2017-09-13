package de.leuphana.ui.components;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.service.MatchHistoryService;
import de.leuphana.backend.service.SingleMatchService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;

@SpringComponent
@PrototypeScope
public class SingleMatchDataProvider extends AbstractBackEndDataProvider<Match, Object> {

	private final SingleMatchService singleMatchService;

	@Autowired
	public SingleMatchDataProvider(SingleMatchService singleMatchService) {
		this.singleMatchService = singleMatchService;
	}

	@Override
	protected Stream<Match> fetchFromBackEnd(Query<Match, Object> query) {
		List<Match> matchList = null;
		try {
			matchList = singleMatchService.findAllBySummonerName("SkullD3mon");
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
		return matchList.stream();
	}

	@Override
	protected int sizeInBackEnd(Query<Match, Object> query) {
		try {
			return singleMatchService.countAll();
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Match fetchMatchBySummonerAndId(String summoner, Long matchId) {
		Match match = null;
		try {
			match = singleMatchService.findOneBySummonerName(matchId, summoner);
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
		return match;
	}

}