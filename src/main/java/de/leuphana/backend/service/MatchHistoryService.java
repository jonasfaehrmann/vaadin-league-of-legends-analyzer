package de.leuphana.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@Service
public class MatchHistoryService extends RiotService<Match> {

	private static final Logger logger = LoggerFactory.getLogger(MatchHistoryService.class);

	public MatchHistoryService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
	}

	public int countAll() throws RiotApiException {
		logger.info("Accessing MatchHistory countAll");
		Summoner summoner = api.getSummonerByName(Platform.EUW, "SkullD3mon");
		MatchList matchList = api.getMatchListByAccountId(Platform.EUW, summoner.getAccountId());

//		Limit the amount of rest calls
		return 3;
	}

	public Stream<Match> findAllBySummonerName(String name) throws RiotApiException {
		logger.info("Accessing MatchHistory findAllBySummonerName with params: " + name);
		Summoner summoner = api.getSummonerByName(platform, name);
		MatchList matchReferenceList = api.getMatchListByAccountId(platform, summoner.getAccountId());

		List<Match> matchList = new ArrayList<Match>();

//		Limit the amount of rest calls
//		Riot limits for non production users: 
//		20 requests every 1 seconds 
//		100 requests every 2 minutes 
		int limit = 0;
		for (MatchReference matchReference : matchReferenceList.getMatches()) {
			if (limit <= 3) {
				Match match = new Match();
				try {
					match = api.getMatch(platform, matchReference.getGameId());
				} catch (RiotApiException e) {
					logger.error(e.toString());
				}
				matchList.add(match);
			}else{
				break;
			}
			limit++;
		}

		return matchList.stream();
	}

	@Override
	public Match findOneBySummonerName(Long id, String name) throws RiotApiException {
		logger.info("Accessing MatchHistory findOneBySummonerName with params: " + id + " " + name);
		Summoner summoner = api.getSummonerByName(platform, name);
		Match match = api.getMatch(platform, id, summoner.getAccountId());
		return match;
	}

}
