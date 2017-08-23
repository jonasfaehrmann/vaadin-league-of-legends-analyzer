package de.leuphana.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ACCOUNT_ROLE")
public class Account_Role extends AbstractEntity {

	@NotNull
	private String name;
	
	public Account_Role(){
		
	}
	
	public Account_Role(String name){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
