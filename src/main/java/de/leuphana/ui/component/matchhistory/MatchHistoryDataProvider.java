package de.leuphana.ui.component.matchhistory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.app.security.SecurityUtils;
import de.leuphana.backend.service.account.AccountService;
import de.leuphana.backend.service.riot.dynamicdata.MatchHistoryService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;

/**
 * 
 * @author Jonas Fährmann
 *
 */
@SpringComponent
@PrototypeScope
public class MatchHistoryDataProvider extends AbstractBackEndDataProvider<Match, Object> {

	private final MatchHistoryService matchHistoryService;
	private final AccountService accountService;
	private String summonerName;

	@Autowired
	public MatchHistoryDataProvider(MatchHistoryService matchHistoryService, AccountService accountService) {
		this.matchHistoryService = matchHistoryService;
		this.accountService = accountService;
		summonerName = SecurityUtils.getCurrentUser(accountService).getSummonerName();
	}

	@Override
	protected Stream<Match> fetchFromBackEnd(Query<Match, Object> query) {
		
		List<Match> matchList = new ArrayList<Match>();
		
		try {
			matchList = matchHistoryService.findAllBySummonerName(summonerName);
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
		return matchList.stream();
	}

	@Override
	protected int sizeInBackEnd(Query<Match, Object> query) {
		// limit the amount of rest calls @MatchHistoryService
		// return matchHistoryService.countAll();
		return 3;

	}

	public Match fetchMatchById(Long matchId) {
		
		Match match = new Match();
		
		try {
			match = matchHistoryService.findOneBySummonerName(matchId, summonerName);
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
		
		return match;
	}

	public Match fetchMostRecentMatch() {
		
		Match match = new Match();
		
		try {
			match =  matchHistoryService.findMostRecentMatchBySummonerName(summonerName);
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
		return match;
	}

}
