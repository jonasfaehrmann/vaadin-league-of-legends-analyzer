package de.leuphana.backend.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
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
	protected final ApiConfig config = new ApiConfig().setKey("RGAPI-d09a0fb4-d45f-4aa6-9b21-154e95adf0e1");
	protected final Platform platform = Platform.EUW;
	
	public abstract List<T> findAllBySummonerName(String name) throws RiotApiException;

	public abstract T findOneBySummonerName(Long id, String name) throws RiotApiException;
}
