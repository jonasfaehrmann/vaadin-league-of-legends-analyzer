package de.leuphana.leuphalytics.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.leuphana.leuphalytics.model.widget.matchlist.RiotMatchList;

@Service
public class RiotClient {

	private final RestTemplate restTemplate;

	public RiotClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public RiotMatchList getMatchListForUser() {
		RiotMatchList riotMatchList = restTemplate.getForObject(
				"https://euw1.api.riotgames.com/lol/match/v3/matchlists/by-account/35211455?api_key=RGAPI-899b2da9-9068-4166-905e-c607072cbc38",
				RiotMatchList.class);
		
		return riotMatchList;
	}
	
	
}
