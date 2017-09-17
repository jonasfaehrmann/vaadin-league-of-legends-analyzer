package de.leuphana.backend.service.riot.dynamicdata;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import de.leuphana.backend.service.riot.RiotServiceDynamic;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;
import net.rithms.riot.api.endpoints.league.dto.LeagueItem;
import net.rithms.riot.api.endpoints.league.dto.LeagueList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

@Service
public class SummonerService extends RiotServiceDynamic<Summoner> {

	private static final Logger logger = LoggerFactory.getLogger(SummonerService.class);

	public SummonerService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.api = new RiotApi(config);
	}

	public int countAll() throws RiotApiException {
		logger.info("Accessing ChampionService countAll");
		// limit the amount of rest calls
		// return champlistData.size();
		return 10;
	}
	public LeagueItem getLeagueItemByName(String name) throws RiotApiException{
		logger.info("Accessing SummonerService getLeagueItemBySummonerName with params: " + name);
		Summoner summoner = api.getSummonerByName(platform, name);
		LeagueItem leagueItem = null;
		List<LeagueList> leaguelist = api.getLeagueBySummonerId(platform, summoner.getId());
		for (LeagueList league : leaguelist) {
			leagueItem = league.getEntryBySummonerId(summoner.getId());
		}
		return leagueItem;
	}

	public LeagueItem getLeagueItemById(long id) throws RiotApiException {
		logger.info("Accessing SummonerService getLeagueItemById with params: " + id);
		LeagueItem leagueItem = null;
		List<LeagueList> leaguelist = api.getLeagueBySummonerId(platform, id);
		for (LeagueList league : leaguelist) {
			leagueItem = league.getEntryBySummonerId(id);
		}
		return leagueItem;
	}

	// for Name, Lvl and IconId
	public Summoner findOneBySummonerName(String name) throws RiotApiException {
		logger.info("Accessing SummonerService findOneBySummonerName with params: " + name);
		Summoner summoner = api.getSummonerByName(platform, name);

		return summoner;
	}

	@Override
	public List<Summoner> findAllBySummonerName(String name) throws RiotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Summoner findOneBySummonerName(Long id, String name) throws RiotApiException {
		// TODO Auto-generated method stub
		return null;
	}

}