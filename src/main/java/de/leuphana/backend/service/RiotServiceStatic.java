package de.leuphana.backend.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Platform;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public abstract class RiotServiceStatic<T> {
	
	protected ApiConfig config = new ApiConfig().setKey("RGAPI-1eda2a7c-add5-416f-8f0c-43fba60eafef");
	
	protected RestTemplate restTemplate;
	protected RiotApi api;
	protected final Platform platform = Platform.EUW;
	
	public abstract List<T> findAll() throws RiotApiException;

	public abstract T findOne(int id) throws RiotApiException;
}
