package de.leuphana.ui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.spring.annotation.SpringComponent;
import de.leuphana.backend.service.SummonerSpellService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpellList;

@SpringComponent
@PrototypeScope
public class SummonerSpellDataProvider extends AbstractBackEndDataProvider<SummonerSpell, Object> {

	
	SummonerSpellService sumSpellService;
	
	
	@Autowired
	public SummonerSpellDataProvider(SummonerSpellService sumSpellService) {
		this.sumSpellService = sumSpellService;
	}
	
	@Override
	protected Stream<SummonerSpell> fetchFromBackEnd(Query<SummonerSpell, Object> query) {
		
		List<SummonerSpell> sumSpellList = new ArrayList<SummonerSpell>();
		try{
			sumSpellList = sumSpellService.getSumSpell();
		}
		catch(RiotApiException e){
			e.printStackTrace();
		}
		return sumSpellList.stream();
	}

	@Override
	protected int sizeInBackEnd(Query<SummonerSpell, Object> query) {
		try{
			return sumSpellService.countAll();
		}catch (RiotApiException e) {
			e.printStackTrace();
		}
	
		return 0;
	}

	
}
