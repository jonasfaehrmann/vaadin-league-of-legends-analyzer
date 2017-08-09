package de.leuphana.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@Service
public class RiotService {

	private final RestTemplate restTemplate;
	private static RiotApi api;
	private static ApiConfig config = new ApiConfig().setKey("RGAPI-9237da78-c9b7-4c7a-b67e-141607b81316");

	public RiotService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
	}

	public List<MatchReference> getMatchListForUser() throws RiotApiException {
		Summoner summoner = api.getSummonerByName(Platform.NA, "faker");
		MatchList matchList = api.getMatchListByAccountId(Platform.NA, summoner.getAccountId());
		List<MatchReference> matchListForUser = matchList.getMatches();
		
		return matchListForUser;
	}

	public Map<String, Champion> getDataChampionList() throws RiotApiException {
		ChampionList championList = api.getDataChampionList(Platform.NA);
		Map<String, Champion> championMap = championList.getData();

		return championMap;
	}
	
	public Match getMatchById(long matchId) throws RiotApiException{
		Match match = api.getMatch(Platform.NA, matchId);
		
		return match;
	}
	
	// for testing
	// public static void main(String[] args) throws RiotApiException {
	// api = new RiotApi();
	// api.setKey("RGAPI-8f505b0b-40ea-43d3-96c0-1ea8bae539b5");
	//
	// Summoner summoner = api.getSummonerByName(Region.NA, "faker");
	// MatchList matchList = api.getMatchList(summoner.getId());
	// List<MatchReference> userMatchList = matchList.getMatches();
	// for (MatchReference matchReference : userMatchList) {
	// System.out.println(matchReference.getMatchId()+"***"+matchReference.getTimestamp()+"***"+matchReference.getChampion());
	// }
	// }
}
