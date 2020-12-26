package it.movioletto.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "vincita")
@Entity
public class VincitaEntity implements Serializable {

	@EmbeddedId
	private VincitaEntityKey id;

	public VincitaEntityKey getId() {
		return id;
	}

	public void setId(VincitaEntityKey id) {
		this.id = id;
	}
}
