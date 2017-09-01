package de.leuphana.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Jonas Fährmann
 *
 */
@Entity
@Table(name = "widget")
public class Widget extends AbstractEntity{

	@NotNull
	@Size(min = 0, max = 40)
	private String name;

	@OneToOne
	@JoinColumn(name = "category_id")
	private WidgetCategory category;
	

	@OneToOne
	@JoinColumn(name = "type_id")
	private WidgetType type;
	
	public Widget(){
		
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public WidgetCategory getCategory() {
		return category;
	}


	public void setCategory(WidgetCategory category) {
		this.category = category;
	}


	public WidgetType getType() {
		return type;
	}


	public void setType(WidgetType type) {
		this.type = type;
	}

}
