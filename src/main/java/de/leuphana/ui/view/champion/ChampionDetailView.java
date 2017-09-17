package de.leuphana.ui.view.champion;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.renderers.HtmlRenderer;

import de.leuphana.backend.service.riot.dynamicdata.MatchHistoryService;
import de.leuphana.backend.service.riot.staticdata.ChampionService;
import de.leuphana.ui.util.DDragonUrlFormatter;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;

@SpringView(name = "championDetails")
public class ChampionDetailView extends ChampionDetailViewDesign implements View {

	private ChampionDetailView view;
	private static final Logger logger = LoggerFactory.getLogger(ChampionDetailView.class);
	@Autowired
	private ChampionService championService;
	
	private final DDragonUrlFormatter dDragonUrlFormatter = new DDragonUrlFormatter();
	
	@Autowired
	public ChampionDetailView() {

	}

	@PostConstruct
	public void init() {

	}

	@Override
	public void enter(ViewChangeEvent event) {
		logger.info("Accessing enter Method in ChampionDetailView.class");
		String championId = event.getParameters();
		if ("".equals(championId)) {
			System.out.println("No Champion Id");
		} else {
			Champion champion;
			try {
				champion = championService.findOne(Integer.valueOf(championId));
			} catch (Exception e) {
				view.showNotFound();
				return;
			}
			try {
				setChampion(champion);
			} catch (RiotApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setChampion(Champion champion) throws RiotApiException {
		championDetailGrid.setCaption("Champion Details");
		championDetailGrid.setSizeFull();
		championDetailGrid.setItems(championService.findOne(champion.getId()));
		championNameLabel.setValue(champion.getName());

		attackDamageLabel.setValue(String.valueOf(champion.getStats().getAttackDamage()));
		hpLabel.setValue(String.valueOf(champion.getStats().getHp()));
		armorLabel.setValue(String.valueOf(champion.getStats().getArmor()));
		championImage.setSource(new ExternalResource(dDragonUrlFormatter.getUrlByChampionImageName(champion.getImage().getFull())));
	
	
	}

	public void showNotFound() {
		removeAllComponents();
		addComponent(new Label("Champion not found"));
	}
}