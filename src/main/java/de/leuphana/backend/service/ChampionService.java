package de.leuphana.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;

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
	private ChampionList champList;
	
	public ChampionService(RestTemplateBuilder restTemplateBuilder) throws RiotApiException {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
		champList = api.getDataChampionList(Platform.EUW, Locale.DE_DE, null, false, ChampionListTags.ALL);
	}
	
	public int countAll() throws RiotApiException{
		logger.info("Accessing ChampionService countAll");
		Map<String, Champion> champlistData = champList.getData();
		// limit the amount of rest calls
		return champlistData.size();
	}
	
	public Stream<Champion> getChampions() throws RiotApiException {
		logger.info("Accessing ChampionService -> getChampions");
		List<Champion> championList = new ArrayList<Champion>();
		Map<String, Champion> champlistData = champList.getData();
		for (Champion champion : champlistData.values()) {
				championList.add(champion);
			}
		return championList.stream();
	}
	
	// Might be used later for searching 
	public Stream<Champion> getChampionById(int id) throws RiotApiException {
		logger.info("Accessing ChampionService -> getChampionNameById with id: " + id);
		List<Champion> championListById = new ArrayList<Champion>();
		Map<String, Champion> champlistData = champList.getData();
		for (Champion champion : champlistData.values()) {
			if (id == champion.getId()) {
				championListById.add(champion);
			}

		}
		return championListById.stream();
	}
	
	// doesnt work yet
	public String getChampionImgById(int id) throws RiotApiException, IOException{
		Image foundImage = new Image();
		String foundImageName = null;
		ChampionList champList = api.getDataChampionList(Platform.EUW, Locale.DE_DE, null, false, ChampionListTags.IMAGE);
		Map<String, Champion> champImgListData = champList.getData();
		for (Champion champion : champImgListData.values()) {
			if (id == champion.getId()) {
				foundImageName = champion.getImage().getFull();
				
				//foundImage.setSource(new ExternalResource("http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/"+foundImageName));
			}

		}
		return foundImageName; 
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
