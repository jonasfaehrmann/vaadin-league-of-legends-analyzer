package de.leuphana.leuphalytics.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.leuphana.leuphalytics.LeuphalyticsApplication;
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
				"https://euw1.api.riotgames.com/lol/match/v3/matchlists/by-account/35211455?api_key=RGAPI-ce068453-5555-4c73-8769-c90864e19902",
				RiotMatchList.class);
		
		log.info(riotMatchList.toString());
		
		return riotMatchList;
	}

}
