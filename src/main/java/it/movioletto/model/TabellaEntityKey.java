package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TabellaEntityKey implements Serializable {

	@Column(name = "id_tabella")
	private String idTabella;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stanza", nullable = false)
	private StanzaEntity stanza;

	public String getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(String idTabella) {
		this.idTabella = idTabella;
	}

	public StanzaEntity getStanza() {
		return stanza;
	}

	public void setStanza(StanzaEntity stanza) {
		this.stanza = stanza;
	}
}
