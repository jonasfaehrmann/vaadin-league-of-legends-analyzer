package de.leuphana.ui.components.widgets;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.tepi.imageviewer.ImageViewer;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.leuphana.ui.util.DDragonUrlFormatter;

@SpringComponent
@PrototypeScope
public class ChampionImagesWidget extends ImageViewer implements WidgetComponent {

	private long id = 4l;
	private ImageViewer imageViewer;
	private TextField selectedImage;
	private final DDragonUrlFormatter dDragonUrlFormatter = new DDragonUrlFormatter();
	
	
	@Override
	public Long getWidgetId() {
		return id;
	}

	public ChampionImagesWidget() {
		setCaption("ChampionImageWidget");
		setSizeFull();
		setAnimationEnabled(false);
		setSideImageRelativeWidth(0.7f);
	}
	
	@PostConstruct
	protected void init(){
		setImages(createImageList());
	}

	private List<ExternalResource> createImageList() {
		List<ExternalResource> imgList = new ArrayList<ExternalResource>();
		imgList.add(new ExternalResource("http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/Annie.png"));
		imgList.add(new ExternalResource("http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/Amumu.png"));
		imgList.add(new ExternalResource("http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/Jax.png"));
		return imgList;
	}
	
	private Layout createControls() {
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSizeUndefined();
		hl.setMargin(false);
		hl.setSpacing(true);

		CheckBox c = new CheckBox("HiLite");
		c.addValueChangeListener(e -> {
			imageViewer.setHiLiteEnabled(e.getValue());
			imageViewer.focus();
		});

		c.setValue(true);
		hl.addComponent(c);
		hl.setComponentAlignment(c, Alignment.BOTTOM_CENTER);

		c = new CheckBox("Animate");
		c.addValueChangeListener(e -> {
			imageViewer.setAnimationEnabled(e.getValue());
			imageViewer.focus();
		});
		c.setValue(true);
		hl.addComponent(c);
		hl.setComponentAlignment(c, Alignment.BOTTOM_CENTER);

		Slider s = new Slider("Animation duration (ms)");
		s.setMax(2000);
		s.setMin(200);
		s.setWidth("120px");
		s.addValueChangeListener(e -> {
			imageViewer.setAnimationDuration((int) Math.round(e.getValue()));
			imageViewer.focus();
		});
		s.setValue(350d);
		hl.addComponent(s);
		hl.setComponentAlignment(s, Alignment.BOTTOM_CENTER);

		s = new Slider("Center image width");
		s.setResolution(2);
		s.setMax(1);
		s.setMin(0.1);
		s.setWidth("120px");
		s.addValueChangeListener(e -> {
			imageViewer.setCenterImageRelativeWidth(e.getValue().floatValue());
			imageViewer.focus();
		});
		s.setValue(0.55);
		hl.addComponent(s);
		hl.setComponentAlignment(s, Alignment.BOTTOM_CENTER);

		s = new Slider("Side image count");
		s.setMax(5);
		s.setMin(1);
		s.setWidth("120px");

		s.addValueChangeListener(e -> {
			imageViewer.setSideImageCount((int) Math.round(e.getValue()));
			imageViewer.focus();
		});
		s.setValue(2d);
		hl.addComponent(s);
		hl.setComponentAlignment(s, Alignment.BOTTOM_CENTER);

		s = new Slider("Side image width");
		s.setResolution(2);
		s.setMax(0.8);
		s.setMin(0.5);
		s.setWidth("120px");

		s.addValueChangeListener(e -> {
			imageViewer.setSideImageRelativeWidth(e.getValue().floatValue());
			imageViewer.focus();
		});

		s.setValue(0.65);
		hl.addComponent(s);
		hl.setComponentAlignment(s, Alignment.BOTTOM_CENTER);

		s = new Slider("Horizontal padding");
		s.setMax(10);
		s.setMin(0);
		s.setWidth("120px");

		s.addValueChangeListener(e -> {
			imageViewer.setImageHorizontalPadding((int) Math.round(e.getValue()));
			imageViewer.focus();
		});
		s.setValue(1d);
		hl.addComponent(s);
		hl.setComponentAlignment(s, Alignment.BOTTOM_CENTER);

		s = new Slider("Vertical padding");
		s.setMax(10);
		s.setMin(0);
		s.setWidth("120px");
		s.addValueChangeListener(e -> {
			imageViewer.setImageVerticalPadding((int) Math.round(e.getValue()));
			imageViewer.focus();
		});
		s.setValue(5d);
		hl.addComponent(s);
		hl.setComponentAlignment(s, Alignment.BOTTOM_CENTER);

//			selectedImage.setWidth("50px");
//			hl.addComponent(selectedImage);
//			hl.setComponentAlignment(selectedImage, Alignment.BOTTOM_CENTER);

		return hl;
	}
}
