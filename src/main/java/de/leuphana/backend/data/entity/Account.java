package de.leuphana.backend.data.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
	@Size(min = 0, max = 100)
	private String name;

	private boolean locked = false;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AccountRole accountRole;

	public Account() {
		// An empty constructor is needed for all beans
	}

	public Account(String email, String name, String password, AccountRole accountRole) {
		Objects.requireNonNull(email);
		Objects.requireNonNull(name);
		Objects.requireNonNull(password);
		Objects.requireNonNull(accountRole);

		this.email = email;
		this.name = name;
		this.password = password;
		this.accountRole = accountRole;
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
	
	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
	}
	
	public AccountRole getAccountRole() {
		return accountRole;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
