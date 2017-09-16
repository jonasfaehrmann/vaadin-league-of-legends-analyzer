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
public class SingleMatchService extends RiotService<Match> {

	private static final Logger logger = LoggerFactory.getLogger(SingleMatchService.class);
	static String summonerName;
	static String matchId;

	public SingleMatchService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
	}

	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		SingleMatchService.summonerName = summonerName;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		SingleMatchService.matchId = matchId;
	}

	public int countAll() throws RiotApiException {
		logger.info("accessing single match");

		return 1;
	}

	@Override
	public Match findOneBySummonerName(Long id, String name) throws RiotApiException {
		id = Long.parseLong(matchId);
		name = summonerName;
		
		logger.info("Accessing FindMatch findOneBySummonerName with params: " + id + " " + name);
		Summoner summoner = api.getSummonerByName(platform, name);
		Match match = api.getMatch(platform, id, summoner.getAccountId());
		return match;
	}

	@Override
	public List<Match> findAllBySummonerName(String name) throws RiotApiException {
		return null;
	}


}