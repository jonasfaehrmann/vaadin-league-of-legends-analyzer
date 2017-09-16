package de.leuphana.ui.components;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.service.ChampionMasteryService;
import de.leuphana.backend.service.ChampionService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;

@SpringComponent
@PrototypeScope
public class ChampionMasteryDataProvider extends AbstractBackEndDataProvider<ChampionMastery, Object>{

	private final ChampionMasteryService championMasteryService;
	private final ChampionService championService;

	@Autowired
	public ChampionMasteryDataProvider(ChampionMasteryService championMasteryService, ChampionService championService) {
		this.championMasteryService = championMasteryService;
		this.championService = championService;
	}

	@Override
	protected Stream<ChampionMastery> fetchFromBackEnd(Query<ChampionMastery, Object> query) {
		try{
			return championMasteryService.findAll().stream();
		}catch(RiotApiException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected int sizeInBackEnd(Query<ChampionMastery, Object> query) {
		try{
			return championMasteryService.countAll();
		}catch(RiotApiException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public Champion fetchOne(int id){
		Champion champion = new Champion();
		
		try{
			champion = championService.findOne(id);
		}catch(RiotApiException e){
			e.printStackTrace();
		}
		return champion;
 
	}
	
	

}