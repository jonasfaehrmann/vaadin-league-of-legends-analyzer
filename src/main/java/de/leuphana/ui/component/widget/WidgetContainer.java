package de.leuphana.ui.component.widget;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public class WidgetContainer{

	List<WidgetComponent> widgets = new ArrayList<WidgetComponent>();
	
	public void addWidget(WidgetComponent c){
		this.widgets.add(c);
	}
	
	public void addWidgets(WidgetComponent... widgets){
		for (WidgetComponent widget : widgets) {
			addWidget(widget);
		}
	}
	
	public void removeWidget(int i){
		this.widgets.remove(i);
	}
	
	public WidgetComponent getWidget(int i){
		return widgets.get(i);
	}
	
	public WidgetComponent getWidgetByWidgetId(Long widgetId){
		for (WidgetComponent widget : widgets) {
				if(widgetId == widget.getWidgetId()){
					return widget;
				}
		}
		return null;
	}
	
	public List<WidgetComponent> getWidgets(){
		return this.widgets;
	}
}
