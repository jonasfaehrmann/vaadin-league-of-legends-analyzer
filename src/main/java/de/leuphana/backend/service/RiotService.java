package de.leuphana.backend.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.client.RestTemplate;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.constant.Platform;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public abstract class RiotService<T> {

	protected RestTemplate restTemplate;
	protected RiotApi api;
	protected final ApiConfig config = new ApiConfig().setKey("RGAPI-2a0d8b1f-08a7-48df-bbce-43a36cb9477d");
	protected final Platform platform = Platform.EUW;
	
	public abstract Stream<Match> findAllBySummonerName(String name) throws RiotApiException;

	public abstract T findOneBySummonerName(Long id, String name) throws RiotApiException;
}
