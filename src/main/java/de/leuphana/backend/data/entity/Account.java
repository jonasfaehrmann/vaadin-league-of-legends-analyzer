package de.leuphana.backend.data.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Jonas Fährmann
 *
 */
@Entity
@Table(name = "ACCOUNT")
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

	@Transient
	private boolean locked = false;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_Id")
	private AccountRole role;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_has_widget", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = { @JoinColumn(name = "widget_id") })
    private Set<Widget> widgets = new HashSet<Widget>();

	public Account() {
		// An empty constructor is needed for all beans
	}

	public Account(String email, String name, String password, AccountRole role) {
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
	
	public void setRole(AccountRole role) {
		this.role = role;
	}
	
	public AccountRole getRole() {
		return role;
	}

	public boolean isLocked() {
		return locked;
	}
	
	public Set<Widget> getWidgets() {
		return widgets;
	}

	public void setWidgets(Set<Widget> widgets) {
		this.widgets = widgets;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
}
