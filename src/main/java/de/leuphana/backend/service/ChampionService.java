package de.leuphana.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;

public class ChampionService extends RiotService<Champion> {

	private static final Logger logger = LoggerFactory.getLogger(ChampionService.class);

	public int countAll() throws RiotApiException{
		logger.info("Accessing ChampionService countAll");
		ChampionList champlist = api.getDataChampionList(platform);
		Map<String, Champion> champlistData = champlist.getData();
		// limit the amount of rest calls
		return 3;
	}
	
	public Stream<String> getChampionNameById(int id) throws RiotApiException {
		String championName = null;
		List<String> championNameList = new ArrayList<String>();
		logger.info("Accessing ChampionService -> getChampionNameById with id: " + id);
		ChampionList champlist = api.getDataChampionList(platform);
		Map<String, Champion> champlistData = champlist.getData();
		for (Champion champion : champlistData.values()) {
			if (id == champion.getId()) {
				championName = champion.getName();
				championNameList.add(championName);
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
