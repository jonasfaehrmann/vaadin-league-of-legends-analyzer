package de.leuphana.ui.components.widgets;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public class WidgetContainer{

	List<Widget> widgets = new ArrayList<Widget>();
	
	public void addWidget(Widget c){
		this.widgets.add(c);
	}
	
	public void addWidgets(Widget... widgets){
		for (Widget widget : widgets) {
			addWidget(widget);
		}
	}
	
	public void removeWidget(int i){
		this.widgets.remove(i);
	}
	
	public Widget getWidget(int i){
		return widgets.get(i);
	}
	
	public Widget getWidgetByWidgetId(int id){
		for (Widget widget : widgets) {
				if(id == widget.getWidgetId()){
					return widget;
				}
		}
		return null;
	}
}
