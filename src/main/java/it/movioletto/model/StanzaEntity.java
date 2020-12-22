package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "stanza")
@Entity
public class StanzaEntity implements Serializable {

	@Id
	@Column(name = "id_stanza")
	private String idStanza;

	@Column(name = "nome")
	private String nome;

	public String getIdStanza() {
		return idStanza;
	}

	public void setIdStanza(String idStanza) {
		this.idStanza = idStanza;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
