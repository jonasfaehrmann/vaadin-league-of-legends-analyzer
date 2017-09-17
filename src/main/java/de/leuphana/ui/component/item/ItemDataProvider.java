package de.leuphana.ui.component.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.service.riot.staticdata.ItemService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Item;



@SpringComponent
@PrototypeScope
public class ItemDataProvider extends AbstractBackEndDataProvider<Item, Object> {

	
	private final ItemService itemService;
	
	
	@Autowired
	public ItemDataProvider(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@Override
	protected Stream<Item> fetchFromBackEnd(Query<Item, Object> query) {
		
		List<Item> itemList = new ArrayList<Item>();
		try{
			
			itemList = itemService.findAll();
		}
		catch(RiotApiException e){
			e.printStackTrace();
		}
		return itemList.stream();
	}

	@Override
	protected int sizeInBackEnd(Query<Item, Object> query) {
		try{
			return itemService.countAll();
		}catch (RiotApiException e) {
			e.printStackTrace();
		}
	
		return 0;
	}


	
}
