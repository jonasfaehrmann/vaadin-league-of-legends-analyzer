package de.leuphana.ui.components;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;

import de.leuphana.backend.service.ChampionService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;

public class ChampionDataProvider extends AbstractBackEndDataProvider<Champion, Object> {

	private final ChampionService championService;
	
	@Autowired
	public ChampionDataProvider(ChampionService championService) {
		// TODO Auto-generated constructor stub
		this.championService = championService;
	}
	
	@Override
protected Stream<Champion> fetchFromBackEnd(Query<Champion, Object> query) {
//		try{
//			return championService.getChampionNameById(32);
//		}
//		catch(RiotApiException e){
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	protected int sizeInBackEnd(Query<Champion, Object> query) {
		// TODO Auto-generated method stub
		return 0;
	}

}
