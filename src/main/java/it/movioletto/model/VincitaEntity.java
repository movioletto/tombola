package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "vincita")
@Entity
public class VincitaEntity implements Serializable {

	@Id
	@Column(name = "id_stanza")
	private String idStanza;

	@Column(name = "premio")
	private Integer premio;

	@Column(name = "id_tabella")
	private String idTabella;

	public String getIdStanza() {
		return idStanza;
	}

	public void setIdStanza(String idStanza) {
		this.idStanza = idStanza;
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