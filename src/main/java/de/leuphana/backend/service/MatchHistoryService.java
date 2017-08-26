package de.leuphana.backend.service;

import java.util.stream.Stream;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@Service
public class MatchHistoryService {
	
	private final RestTemplate restTemplate;
	private static RiotApi api;
	private static ApiConfig config = new ApiConfig().setKey("RGAPI-01742e2e-d9fc-4e23-be04-969f200a775e");

	public MatchHistoryService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
	}
	
	public Match findMatchById(long matchId) throws RiotApiException{
		Match match = api.getMatch(Platform.NA, matchId);
		
		return match;
	}
	
	public int countAll() throws RiotApiException{
		Summoner summoner = api.getSummonerByName(Platform.NA, "faker");
		MatchList matchList = api.getMatchListByAccountId(Platform.NA, summoner.getAccountId());
		
		return matchList.getEndIndex();
	}
	
	public Stream<MatchReference> findAll() throws RiotApiException{
		Summoner summoner = api.getSummonerByName(Platform.NA, "faker");
		MatchList matchList = api.getMatchListByAccountId(Platform.NA, summoner.getAccountId());
		
//		needs to be limited because riot limits amount of requests
//		20 requests every 1 seconds 
//		100 requests every 2 minutes
		return matchList.getMatches().stream().limit(3);
	}
	
}
