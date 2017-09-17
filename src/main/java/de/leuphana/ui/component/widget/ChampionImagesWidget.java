package de.leuphana.ui.component.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.tepi.imageviewer.ImageViewer;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.service.riot.staticdata.ChampionService;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.util.DDragonUrlFormatter;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;

@SpringComponent
@PrototypeScope
public class ChampionImagesWidget extends ImageViewer implements WidgetComponent {

	private long id = 4l;
	private final DDragonUrlFormatter dDragonUrlFormatter = new DDragonUrlFormatter();
	
	@Autowired
	private NavigationManager navigationManager;
	@Autowired
	private ChampionService championService;

	@Override
	public Long getWidgetId() {
		return id;
	}
	
	
	public ChampionImagesWidget() {
		setSizeFull();
		setAnimationEnabled(true);
		setSideImageRelativeWidth(0.7f);
		setAnimationDuration(3000);
		setHeight("200");
		setHiLiteEnabled(true);
		setSideImageCount(3);
		
	}

	@PostConstruct
	protected void init() throws RiotApiException {
		setImages(createImageList());
	}

	private List<ExternalResource> createImageList() throws RiotApiException {
		Random randomGenerator = new Random();
		List<Champion> championList = championService.findAll();
		List<ExternalResource> imgList = new ArrayList<ExternalResource>();
		
		for(int i=0; i<=7; i++){
			int index = randomGenerator.nextInt(championList.size());
			Champion champion = championList.get(index);
			imgList.add(new ExternalResource(dDragonUrlFormatter.getUrlByChampionImageName(champion.getImage().getFull())));
		}
		
		return imgList;
	}

}