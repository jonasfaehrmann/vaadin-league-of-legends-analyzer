package de.leuphana.ui.view.match;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "match")
public class MatchDetailView extends MatchDetailViewDesign implements View {
	
	@Autowired
	public MatchDetailView(){
	}

	@PostConstruct
	public void init() {
		
	}
}
