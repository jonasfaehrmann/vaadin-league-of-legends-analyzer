package de.leuphana.ui.util;

import java.util.Set;

import com.vaadin.spring.annotation.SpringComponent;
import de.leuphana.backend.data.entity.Widget;

@SpringComponent
public class WidgetSetToCommaSeparatedStringConverter {

	public String convertToPresentation(Set<Widget> widgets) {
		
		if(widgets.isEmpty()){
			return null;
		}
		
		String widgetNamesConcatinated = "";
		for (Widget widget : widgets) {
			widgetNamesConcatinated += " " + widget.getName();
		}
		
		return widgetNamesConcatinated;
	}

}