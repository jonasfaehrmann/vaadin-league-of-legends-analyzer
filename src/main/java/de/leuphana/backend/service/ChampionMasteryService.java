package de.leuphana.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import net.rithms.riot.api.endpoints.static_data.constant.ChampionListTags;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@Service
public class ChampionMasteryService extends RiotService<ChampionMastery> {

	private ChampionList championList;

	public ChampionMasteryService(RestTemplateBuilder restTemplateBuilder) throws RiotApiException {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
		championList = api.getDataChampionList(Platform.EUW, locale, null, false, ChampionListTags.ALL);
	}

	public int countAll() throws RiotApiException {
		Summoner summoner = api.getSummonerByName(Platform.NA, "faker");
		List<ChampionMastery> championMastery = api.getChampionMasteriesBySummoner(Platform.NA, summoner.getId());
		return championMastery.size();
	}

	public List<ChampionMastery> findAll() throws RiotApiException {
		Summoner summoner = api.getSummonerByName(Platform.NA, "faker");
		List<ChampionMastery> championMastery = api.getChampionMasteriesBySummoner(Platform.NA, summoner.getId());

		return championMastery;
	}

	public Champion findChampionById(int id) throws RiotApiException {
		Champion foundChampion = null;
		Map<String, Champion> championListData = championList.getData();
		for (Champion champion : championListData.values()) {
			if (id == champion.getId()) {
				foundChampion = champion;
			}
		}
		return foundChampion;
	}

	@Override
	public List<ChampionMastery> findAllBySummonerName(String name) throws RiotApiException {
		return null;
	}

	@Override
	public ChampionMastery findOneBySummonerName(Long id, String name) throws RiotApiException {
		return null;
	}


}