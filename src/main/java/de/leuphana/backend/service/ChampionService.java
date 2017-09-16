package de.leuphana.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.constant.ChampionListTags;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.endpoints.static_data.dto.Stats;
import net.rithms.riot.constant.Platform;

@Service
public class ChampionService extends RiotServiceStatic<Champion> {

	private static final Logger logger = LoggerFactory.getLogger(ChampionService.class);
	private ChampionList champList;

	public ChampionService(RestTemplateBuilder restTemplateBuilder) throws RiotApiException {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
		champList = api.getDataChampionList(Platform.EUW, RiotService.getLocale(), null, false, ChampionListTags.ALL);
	}

	public int countAll() throws RiotApiException {
		logger.info("Accessing ChampionService countAll");
		Map<String, Champion> champlistData = champList.getData();
		return 10;
	}

	public List<Champion> findAll() throws RiotApiException {
		logger.info("Accessing ChampionService -> getChampions");
		champList = api.getDataChampionList(Platform.EUW, RiotService.getLocale(), null, false, ChampionListTags.ALL);
		List<Champion> championList = new ArrayList<Champion>();
		Map<String, Champion> champlistData = champList.getData();

		int limit = 0;

		for (Champion champion : champlistData.values()) {

			if (limit <= champlistData.size()) {
				championList.add(champion);
				limit++;
			}
		}

		return championList;
	}

	// Might be used later for searching
	@Override
	public Champion findOne(int id) throws RiotApiException {
		logger.info("Accessing ChampionService -> getChampionNameById with id: " + id);
		Champion champion = new Champion();

		Map<String, Champion> champlistData = champList.getData();
		for (Champion c : champlistData.values()) {
			if (id == c.getId()) {
				champion = c;
				break;
			}
		}
		return champion;
	}

	public String getChampionImgName() throws RiotApiException, IOException {
		logger.info("Accessing getChampionImgName in ChampionService");
		String foundImageName = null;
		Map<String, Champion> champImgListData = champList.getData();
		for (Champion champion : champImgListData.values()) {
			foundImageName = champion.getImage().getFull();

			// foundImage.setSource(new
			// ExternalResource("http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/"+foundImageName));
		}
		return foundImageName;
	}

	public void getStatsById(int id) {
		logger.info("Accessing StatsById in ChampionService");
		Map<String, Champion> champImgListData = champList.getData();
		for (Champion champion : champImgListData.values()) {
			Stats stats = champion.getStats();

		}

	}

}