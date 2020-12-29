package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class VincitaEntityKey implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stanza", nullable = false)
	private StanzaEntity stanza;

	@Column(name = "premio")
	private Integer premio;

	@Column(name = "id_tabella")
	private String idTabella;

	public StanzaEntity getStanza() {
		return stanza;
	}

	public void setStanza(StanzaEntity stanza) {
		this.stanza = stanza;
	}

	public Integer getPremio() {
		return premio;
	}

	public void setPremio(Integer premio) {
		this.premio = premio;
	}

	public String getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(String idTabella) {
		this.idTabella = idTabella;
	}
}
