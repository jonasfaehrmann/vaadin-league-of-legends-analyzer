package de.leuphana.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.api.endpoints.static_data.constant.SpellListTags;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpellList;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell;

import net.rithms.riot.constant.Platform;


@Service
public class SummonerSpellService extends RiotService<SummonerSpellList> {

	
	private static final Logger logger = LoggerFactory.getLogger(SummonerSpellService.class);
	private SummonerSpellList sumSpellList;

	
	public SummonerSpellService(RestTemplateBuilder restTemplateBuilder) throws RiotApiException {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
		sumSpellList = api.getDataSummonerSpellList(platform.EUW, Locale.DE_DE, null, false, SpellListTags.ALL);
		
		
		
	}
	
		public int countAll() throws RiotApiException{
			logger.info("Accessing ItemService countAll");
			Map<String, SummonerSpell> sumSpellListData = sumSpellList.getData();
			// limit the amount of rest calls
			return sumSpellListData.size();
		}
		
		public Stream<SummonerSpell> getSumSpell() throws RiotApiException {
			logger.info("Accessing ItemService -> getItems");
			List<SummonerSpell> sumSpellMapList = new ArrayList<SummonerSpell>();
			Map<String, SummonerSpell> sumSpellListData = sumSpellList.getData();
			for (SummonerSpell spell : sumSpellListData.values()) {
				sumSpellMapList.add(spell);
				}
			return sumSpellMapList.stream();
		}
		
		
			public String getSumSpellImgName() throws RiotApiException, IOException{
				String foundImageName = null;
				Map<String, SummonerSpell> sumSpellListData = sumSpellList.getData();
				for (SummonerSpell spell : sumSpellListData.values()) {
						foundImageName = spell.getImage().getFull();
					}
				return foundImageName;
			}
			
		
	
	

	@Override
	public Stream<Match> findAllBySummonerName(String name) throws RiotApiException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SummonerSpellList findOneBySummonerName(Long id, String name) throws RiotApiException {
		// TODO Auto-generated method stub
		return null;
	}
}
