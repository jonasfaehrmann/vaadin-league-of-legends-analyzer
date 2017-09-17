package de.leuphana.backend.data;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Jonas Fährmann
 *
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

	// Use custom generator
	// Students dont have priviledge to create sequence on Leuphana database
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "de.leuphana.backend.util.IdGenerator")
	@GeneratedValue(generator = "idGenerator")  
	private Long id;

	public boolean isNew() {
		return id == null;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}

		return 31 + id.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (id == null) {
			// New entities are only equal if the instance if the same
			return super.equals(other);
		}

		if (this == other) {
			return true;
		}
		if (!(other instanceof AbstractEntity)) {
			return false;
		}
		return id.equals(((AbstractEntity) other).id);
	}

}
