package de.leuphana.backend.data.entity.neww;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.leuphana.backend.data.entity.AbstractEntity;

@Entity
public class Account extends AbstractEntity{

	private Integer id;
	private String email;
	private String password;
	private Integer roleId;
	private String name;
	
	private boolean locked = false;
	
	public Account() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isLocked() {
		return this.locked;
	}
	
	public void setLocked(boolean locked){
		this.locked = locked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
