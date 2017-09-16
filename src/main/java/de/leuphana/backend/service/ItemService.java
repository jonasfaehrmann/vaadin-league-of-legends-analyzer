package de.leuphana.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.constant.ItemListTags;
import net.rithms.riot.api.endpoints.static_data.dto.Item;
import net.rithms.riot.api.endpoints.static_data.dto.ItemList;

@Service
public class ItemService extends RiotServiceStatic<Item> {

	private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
	private ItemList itemList;

	public ItemService(RestTemplateBuilder restTemplateBuilder) throws RiotApiException {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
		itemList = api.getDataItemList(platform.EUW, RiotService.getLocale(), null, ItemListTags.ALL);

	}

	public int countAll() throws RiotApiException {
		logger.info("Accessing ItemService countAll");
		Map<String, Item> itemlistData = itemList.getData();
		// limit the amount of rest calls
		return itemlistData.size();
	}

	public List<Item> findAll() throws RiotApiException {
		logger.info("Accessing ItemService -> getItems");
		List<Item> itemMapList = new ArrayList<Item>();

		Map<String, Item> itemlistData = itemList.getData();
		for (Item item : itemlistData.values()) {
			itemMapList.add(item);
		}
		return itemMapList;
	}

	@Override
	public Item findOne(int id) throws RiotApiException {
		// TODO Auto-generated method stub
		return null;
	}

}
