package it.movioletto.dao;

import java.io.Serializable;

public class StanzaDao implements Serializable {

	private String idStanza;
	private String nome;

	public StanzaDao() {
		super();
	}

	public StanzaDao(String idStanza, String nome) {
		this.idStanza = idStanza;
		this.nome = nome;
	}

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
