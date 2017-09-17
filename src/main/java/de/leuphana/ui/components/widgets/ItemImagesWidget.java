package de.leuphana.ui.components.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.tepi.imageviewer.ImageViewer;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.service.ItemService;
import de.leuphana.ui.components.ItemDataProvider;
import de.leuphana.ui.util.DDragonUrlFormatter;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.Item;

@SpringComponent
@PrototypeScope
public class ItemImagesWidget extends ImageViewer implements WidgetComponent {

	private Long id = 3l;

	@Autowired
	private ItemService itemService;

	private final DDragonUrlFormatter dDragonUrlFormatter = new DDragonUrlFormatter();

	public ItemImagesWidget() {
		setSizeFull();
		setAnimationEnabled(true);
		setSideImageRelativeWidth(0.7f);
		setAnimationDuration(3000);
		setHeight("150");
		setHiLiteEnabled(true);
		setSideImageCount(5);

	}

	@PostConstruct
	protected void init() throws RiotApiException {
		setImages(createImageList());
	}

	private List<ExternalResource> createImageList() throws RiotApiException {
		Random randomGenerator = new Random();
		List<Item> itemList = itemService.findAll();
		List<ExternalResource> imgList = new ArrayList<ExternalResource>();

		for (int i = 0; i <= 10; i++) {
			int index = randomGenerator.nextInt(itemList.size());
			Item item = itemList.get(index);
			imgList.add(new ExternalResource(dDragonUrlFormatter.getUrlbyItemImageName((item.getImage().getFull()))));
		}

		return imgList;
	}

	@Override
	public Long getWidgetId() {
		return id;
	}

}