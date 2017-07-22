package de.leuphana.leuphalytics.connector.restconnector;

import java.util.List;
import java.util.Map;

import org.apache.catalina.core.ApplicationFilterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.rithms.riot.*;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.MatchList.MatchList;
import net.rithms.riot.dto.MatchList.MatchReference;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Summoner.Summoner;
import de.leuphana.leuphalytics.LeuphalyticsApplication;
import de.leuphana.leuphalytics.model.champion.ChampionData;
import de.leuphana.leuphalytics.model.widget.matchlist.RiotMatchList;
import net.rithms.riot.constant.Region;
@Service
public class RiotClient {
	
	private static final Logger log = LoggerFactory.getLogger(LeuphalyticsApplication.class);

	private final RestTemplate restTemplate;
	private static RiotApi api;
	private Region region;

	public RiotClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public List<MatchReference> getMatchListForUser() throws RiotApiException {
		api = new RiotApi();
		api.setKey("RGAPI-8f505b0b-40ea-43d3-96c0-1ea8bae539b5");
		
		MatchList matchList = api.getMatchList(35211455);
		List<MatchReference> userMatchList = matchList.getMatches();
		return userMatchList;
	}

	public  Map<String, Champion> getChampions() throws RiotApiException {
		api = new RiotApi();
		api.setKey("RGAPI-8f505b0b-40ea-43d3-96c0-1ea8bae539b5");
		
		ChampionList championList = api.getDataChampionList();
		Map<String, Champion> championMap = championList.getData();
		
		return championMap;
	}
	
	public static void main(String[] args) throws RiotApiException {
		api = new RiotApi();
		api.setKey("RGAPI-8f505b0b-40ea-43d3-96c0-1ea8bae539b5");
		
		Summoner summoner = api.getSummonerByName(Region.NA, "faker");
		MatchList matchList = api.getMatchList(summoner.getId());
		List<MatchReference> userMatchList = matchList.getMatches();
		for (MatchReference matchReference : userMatchList) {
			System.out.println(matchReference.getMatchId()+"***"+matchReference.getTimestamp()+"***"+matchReference.getChampion());
		}
	}
}
