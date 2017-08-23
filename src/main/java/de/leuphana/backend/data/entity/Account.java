package de.leuphana.backend.data.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "account")
public class Account extends AbstractEntity {
	
	@NotNull
	@Size(min = 0, max = 100)
	private String email;

	@NotNull
	@Size(min = 0, max = 300)
	private String password;

	@NotNull
	@Size(min = 0, max = 255)
	private String name;

	@NotNull
	@Size(min = 1, max = 255)
	private Integer role_Id;

	@Transient
	private boolean locked = false;
	
	//Ignores this variable, because not in database
	@Transient
	private String role;

	public Account() {
		// An empty constructor is needed for all beans
	}

	public Account(String email, String name, String password, String role) {
		Objects.requireNonNull(email);
		Objects.requireNonNull(name);
		Objects.requireNonNull(password);
		Objects.requireNonNull(role);

		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRole_Id(Integer role_Id) {
		this.role_Id = role_Id;
	}
	
	public Integer getRole_Id() {
		return role_Id;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
