package de.leuphana.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.static_data.constant.ChampionListTags;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.constant.Platform;


@Service
public class ChampionService extends RiotService<Champion> {

	private static final Logger logger = LoggerFactory.getLogger(ChampionService.class);

	public ChampionService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
	
	}
	
	public int countAll() throws RiotApiException{
		logger.info("Accessing ChampionService countAll");
		ChampionList champlist = api.getDataChampionList(Platform.EUW, Locale.DE_DE, null, false, ChampionListTags.ALL);
		Map<String, Champion> champlistData = champlist.getData();
		// limit the amount of rest calls
		return 3;
	}
	
	public Stream<Champion> getChampionById(int id) throws RiotApiException {
		String championName = null;
		List<Champion> championNameList = new ArrayList<Champion>();
		logger.info("Accessing ChampionService -> getChampionNameById with id: " + id);
		ChampionList champlist = api.getDataChampionList(Platform.EUW, Locale.DE_DE, null, false, ChampionListTags.ALL);
		Map<String, Champion> champlistData = champlist.getData();
		for (Champion champion : champlistData.values()) {
			if (id == champion.getId()) {
				championNameList.add(champion);
			}

		}
		return championNameList.stream();
	}

	@Override
	public Stream<Match> findAllBySummonerName(String name) throws RiotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Champion findOneBySummonerName(Long id, String name) throws RiotApiException {
		// TODO Auto-generated method stub
		return null;
	}

}
