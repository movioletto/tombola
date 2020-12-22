package it.movioletto.web.tabellone.data;

import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;
import it.movioletto.dao.TabellaDao;

import java.util.List;

public class TabelloneData {

	private StanzaDao stanza;
	private List<TabellaDao> tabellaList;
	private List<NumeroUscitoDao> numeroUscitoList;
	private Integer numeroEstratto;

	public StanzaDao getStanza() {
		return stanza;
	}

	public void setStanza(StanzaDao stanza) {
		this.stanza = stanza;
	}

	public List<TabellaDao> getTabellaList() {
		return tabellaList;
	}

	public void setTabellaList(List<TabellaDao> tabellaList) {
		this.tabellaList = tabellaList;
	}

	public List<NumeroUscitoDao> getNumeroUscitoList() {
		return numeroUscitoList;
	}

	public void setNumeroUscitoList(List<NumeroUscitoDao> numeroUscitoList) {
		this.numeroUscitoList = numeroUscitoList;
	}

	public Integer getNumeroEstratto() {
		return numeroEstratto;
	}

	public void setNumeroEstratto(Integer numeroEstratto) {
		this.numeroEstratto = numeroEstratto;
	}
}
