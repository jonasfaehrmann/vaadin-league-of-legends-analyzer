package de.leuphana.ui.components;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.service.ChampionMasteryService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion_mastery.dto.ChampionMastery;

@SpringComponent
@PrototypeScope
public class ChampionMasteryDataProvider extends AbstractBackEndDataProvider<ChampionMastery, Object>{

	private final ChampionMasteryService championMasteryService;

	@Autowired
	public ChampionMasteryDataProvider(ChampionMasteryService championMasteryService) {
		this.championMasteryService = championMasteryService;
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
	
	public Stream<ChampionMastery> fetchChampions(){
		try{
			return championMasteryService.findAll().stream();
		}catch(RiotApiException e){
			e.printStackTrace();
		}
		return null;
	}
	
	

}