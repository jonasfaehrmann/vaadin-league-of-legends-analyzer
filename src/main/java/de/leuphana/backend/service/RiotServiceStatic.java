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
public abstract class RiotServiceStatic<T> extends RiotService<T> {
	
	public abstract List<T> findAll() throws RiotApiException;

	public abstract T findOne(int id) throws RiotApiException;
}
