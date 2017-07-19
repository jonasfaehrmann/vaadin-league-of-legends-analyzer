package de.leuphana.leuphalytics.model.widget;

public class Widget {

	private int widgetId;
	private String widgetName;
	
	public Widget(int id, String name) {
		this.widgetId = id;
		this.widgetName = name;
	}

	public int getWidgetId() {
		return widgetId;
	}

	public void setWidgetId(int widgetId) {
		this.widgetId = widgetId;
	}

	public String getWidgetName() {
		return widgetName;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

}
