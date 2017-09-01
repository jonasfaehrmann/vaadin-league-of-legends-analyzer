package de.leuphana.ui.components;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.service.MatchHistoryService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

@SpringComponent
@PrototypeScope
public class MatchHistoryDataProvider extends AbstractBackEndDataProvider<Match, Object>{

	private final MatchHistoryService matchHistoryService;

	@Autowired
	public MatchHistoryDataProvider(MatchHistoryService matchHistoryService) {
		this.matchHistoryService = matchHistoryService;
	}

	@Override
	protected Stream<Match> fetchFromBackEnd(Query<Match, Object> query) {
		try{
			return matchHistoryService.findAllBySummonerName("SkullD3mon");
		}catch(RiotApiException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected int sizeInBackEnd(Query<Match, Object> query) {
		try{
			return matchHistoryService.countAll();
		}catch(RiotApiException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public Match fetchMatchById(Long matchId){
		try{
			return matchHistoryService.findOneBySummonerName(matchId, "SkullD3mon");
		}catch(RiotApiException e){
			e.printStackTrace();
		}
		return null;
	}
	
	

}
