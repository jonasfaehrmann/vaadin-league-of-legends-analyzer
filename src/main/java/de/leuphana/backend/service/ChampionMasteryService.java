package de.leuphana.backend.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@Service
public class ChampionMasteryService extends RiotService<ChampionMastery> {
	
	public ChampionMasteryService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
	}
	
	public int countAll() throws RiotApiException{
		Summoner summoner = api.getSummonerByName(Platform.NA, "faker");
		List<ChampionMastery> championMastery = api.getChampionMasteriesBySummoner(Platform.NA, summoner.getId());
		
		return championMastery.size();
	}
	
	public List<ChampionMastery> findAll() throws RiotApiException{
		Summoner summoner = api.getSummonerByName(Platform.NA, "faker");
		List<ChampionMastery> championMastery = api.getChampionMasteriesBySummoner(Platform.NA, summoner.getId());
		
		
//		needs to be limited because riot limits amount of requests
//		20 requests every 1 seconds 
//		100 requests every 2 minutes
		return championMastery;
	}

	@Override
	public List<Match> findAllBySummonerName(String name) throws RiotApiException {
		return null;
	}

	@Override
	public ChampionMastery findOneBySummonerName(Long id, String name) throws RiotApiException {
		return null;
	}
	
}
