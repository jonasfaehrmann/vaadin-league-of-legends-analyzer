package de.leuphana.ui.components;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.service.ChampionService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.Image;

@SpringComponent
@PrototypeScope
public class ChampionDataProvider extends AbstractBackEndDataProvider<Champion, Object> {

	private final ChampionService championService;
	
	@Autowired
	public ChampionDataProvider(ChampionService championService) {
		this.championService = championService;
	}
	
	@Override
	protected Stream<Champion> fetchFromBackEnd(Query<Champion, Object> query) {
		try{
			return championService.getChampions();
		}
		catch(RiotApiException e){
			e.printStackTrace();
		}
		return null;
	}

	public Image fetchChampionImageName() throws IOException{
		try {
			championService.getChampionImgName();
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
	return null;
	}
	
	
	@Override
	protected int sizeInBackEnd(Query<Champion, Object> query) {
		try{
			return championService.countAll();
		}catch (RiotApiException e) {
			e.printStackTrace();
		}
	
		return 0;
	}

}
