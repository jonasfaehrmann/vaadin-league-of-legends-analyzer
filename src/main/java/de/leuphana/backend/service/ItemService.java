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

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.static_data.constant.ItemListTags;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.api.endpoints.static_data.dto.Item;
import net.rithms.riot.api.endpoints.static_data.dto.ItemList;
import net.rithms.riot.constant.Platform;


@Service
public class ItemService extends RiotServiceDynamic<ItemList> {

	private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
	private ItemList itemList;
	
	public ItemService(RestTemplateBuilder restTemplateBuilder) throws RiotApiException {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
		itemList = api.getDataItemList(platform.EUW, Locale.DE_DE, null, ItemListTags.ALL);
		
		
		
	}
	
	public int countAll() throws RiotApiException{
		logger.info("Accessing ItemService countAll");
		Map<String, Item> itemlistData = itemList.getData();
		// limit the amount of rest calls
		return itemlistData.size();
	}
	
	
	public List<Item> getItems() throws RiotApiException {
		logger.info("Accessing ItemService -> getItems");
		List<Item> itemMapList = new ArrayList<Item>();
		
		Map<String, Item> itemlistData = itemList.getData();
		for (Item item : itemlistData.values()) {
				itemMapList.add(item);
			}
		return itemMapList;
	}
	
		public String getItemImgName() throws RiotApiException, IOException{
			String foundImageName = null;
			Map<String, Item> itemImgListData = itemList.getData();
			for (Item item : itemImgListData.values()) {
					foundImageName = item.getImage().getFull();
					
				}
			return foundImageName;
		}

		@Override
		public List<ItemList> findAllBySummonerName(String name) throws RiotApiException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ItemList findOneBySummonerName(Long id, String name) throws RiotApiException {
			// TODO Auto-generated method stub
			return null;
		}
	

	
	

}
