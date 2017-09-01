package de.leuphana.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Jonas Fährmann
 *
 */
@Entity
@Table(name = "widget_type")
public class WidgetType extends AbstractEntity{

	@NotNull
	@Size(min = 0, max = 40)
	private String name;

	public WidgetType(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
