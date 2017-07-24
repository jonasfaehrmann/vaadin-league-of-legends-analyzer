package de.leuphana.leuphalytics.connector.restconnector;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.leuphana.leuphalytics.LeuphalyticsApplication;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.MatchList.MatchList;
import net.rithms.riot.dto.MatchList.MatchReference;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Summoner.Summoner;

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
		api.setKey("RGAPI-0249e022-6362-4b4a-94ba-6648331e2b30");

		Summoner summoner = api.getSummonerByName(Region.NA, "faker");
		MatchList matchList = api.getMatchList(summoner.getId());
		List<MatchReference> matchListForUser = matchList.getMatches();
		return matchListForUser;
	}

	public Map<String, Champion> getChampions() throws RiotApiException {
		api = new RiotApi();
		api.setKey("RGAPI-0249e022-6362-4b4a-94ba-6648331e2b30");

		ChampionList championList = api.getDataChampionList();
		Map<String, Champion> championMap = championList.getData();

		return championMap;
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
