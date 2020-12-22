package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TabellaEntityKey implements Serializable {

	@Column(name = "id_tabella")
	private String idTabella;

	@Column(name = "id_stanza")
	private String idStanza;

	public String getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(String idTabella) {
		this.idTabella = idTabella;
	}

	public String getIdStanza() {
		return idStanza;
	}

	public void setIdStanza(String idStanza) {
		this.idStanza = idStanza;
	}

}
