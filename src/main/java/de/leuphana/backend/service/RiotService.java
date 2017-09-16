package de.leuphana.backend.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.constant.Platform;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public abstract class RiotService<T> {

	protected RestTemplate restTemplate;
	protected RiotApi api;
	protected final ApiConfig config = new ApiConfig().setKey("RGAPI-1eda2a7c-add5-416f-8f0c-43fba60eafef");
	protected final Platform platform = Platform.EUW;
	private static Locale locale;

	public static Locale getLocale() {
		if (locale == null) {
			locale = Locale.DE_DE;
		}
		return locale;
	}

	public static void setLocale(Locale locale, String language) {
		RiotService.locale = locale;
		System.out.println("Changed language to " + language);
	}

}
