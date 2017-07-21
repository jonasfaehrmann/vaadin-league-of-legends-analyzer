package de.leuphana.leuphalytics.connector.restconnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.leuphana.leuphalytics.LeuphalyticsApplication;
import de.leuphana.leuphalytics.model.champion.ChampionData;
import de.leuphana.leuphalytics.model.widget.matchlist.RiotMatchList;

@Service
public class RiotClient {
	
	private static final Logger log = LoggerFactory.getLogger(LeuphalyticsApplication.class);

	private final RestTemplate restTemplate;

	public RiotClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public RiotMatchList getMatchListForUser() {
		RiotMatchList riotMatchList = restTemplate.getForObject(
				"https://euw1.api.riotgames.com/lol/match/v3/matchlists/by-account/35211455?api_key=RGAPI-aae74153-972e-407d-bce1-53aa42632b5b",
				RiotMatchList.class);
		
		log.info(riotMatchList.toString());
		
		return riotMatchList;
	}

	public ChampionData getChampions() {
		ChampionData champions = restTemplate.getForObject(
				"https://euw1.api.riotgames.com/lol/static-data/v3/champions?locale=de_DE&dataById=false&api_key=RGAPI-aae74153-972e-407d-bce1-53aa42632b5b",
				ChampionData.class);
		
		log.info(champions.toString());
		
		return champions;
	}
}
