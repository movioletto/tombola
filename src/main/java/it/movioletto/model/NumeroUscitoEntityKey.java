package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class NumeroUscitoEntityKey implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stanza", nullable = false)
	private StanzaEntity stanza;

	@Column(name = "numero")
	private Integer numero;

	public StanzaEntity getStanza() {
		return stanza;
	}

	public void setStanza(StanzaEntity stanza) {
		this.stanza = stanza;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
