package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tabella")
@Entity
public class TabellaEntity implements Serializable {

	@EmbeddedId
	private TabellaEntityKey id;

	@Column(name = "sequenza")
	private String sequenza;

	public TabellaEntityKey getId() {
		return id;
	}

	public void setId(TabellaEntityKey id) {
		this.id = id;
	}

	public String getSequenza() {
		return sequenza;
	}

	public void setSequenza(String sequenza) {
		this.sequenza = sequenza;
	}
}
