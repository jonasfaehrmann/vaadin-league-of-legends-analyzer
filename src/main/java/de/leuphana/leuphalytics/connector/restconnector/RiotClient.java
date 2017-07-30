package de.leuphana.leuphalytics.connector.restconnector;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.leuphana.leuphalytics.LeuphalyticsApplication;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion.dto.Champion;
import net.rithms.riot.api.endpoints.champion.dto.ChampionList;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@Service
public class RiotClient {

	private static final Logger log = LoggerFactory.getLogger(LeuphalyticsApplication.class);

	private final RestTemplate restTemplate;
	private static RiotApi api;

	public RiotClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public List<MatchReference> getMatchListForUser() throws RiotApiException {
		ApiConfig config = new ApiConfig().setKey("RGAPI-e6b84cf4-c564-4afe-a2ef-c0ea20be5474");
		RiotApi api = new RiotApi(config);

		Summoner summoner = api.getSummonerByName(Platform.NA, "faker");
		MatchList matchList = api.getMatchListByAccountId(Platform.NA, summoner.getAccountId());
		List<MatchReference> matchListForUser = matchList.getMatches();
		return matchListForUser;
	}

	public List<Champion> getChampions() throws RiotApiException {
		ApiConfig config = new ApiConfig().setKey("RGAPI-e6b84cf4-c564-4afe-a2ef-c0ea20be5474");
		RiotApi api = new RiotApi(config);

		ChampionList championList = api.getChampions(Platform.NA);
		List<Champion> championMap = championList.getChampions();

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
