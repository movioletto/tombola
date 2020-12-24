package it.movioletto.dao;

import java.io.Serializable;
import java.util.List;

public class StanzaDao implements Serializable {

	private String idStanza;
	private String nome;
	private List<String> giocatorePresenteList;
	private List<NumeroUscitoDao> numeroUscitoList;

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

	public List<String> getGiocatorePresenteList() {
		return giocatorePresenteList;
	}

	public void setGiocatorePresenteList(List<String> giocatorePresenteList) {
		this.giocatorePresenteList = giocatorePresenteList;
	}

	public List<NumeroUscitoDao> getNumeroUscitoList() {
		return numeroUscitoList;
	}

	public void setNumeroUscitoList(List<NumeroUscitoDao> numeroUscitoList) {
		this.numeroUscitoList = numeroUscitoList;
	}
}
