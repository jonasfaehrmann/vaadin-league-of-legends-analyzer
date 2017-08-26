package de.leuphana.backend.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class AbstractEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "seq", sequenceName="SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
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
