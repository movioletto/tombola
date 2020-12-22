package it.movioletto.web.cartella.data;

import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;
import it.movioletto.dao.TabellaDao;

import java.util.List;

public class CartellaData {

	private StanzaDao stanza;
	private TabellaDao tabella;
	private List<NumeroUscitoDao> numeroUscitoList;

	public StanzaDao getStanza() {
		return stanza;
	}

	public void setStanza(StanzaDao stanza) {
		this.stanza = stanza;
	}

	public TabellaDao getTabella() {
		return tabella;
	}

	public void setTabella(TabellaDao tabella) {
		this.tabella = tabella;
	}

	public List<NumeroUscitoDao> getNumeroUscitoList() {
		return numeroUscitoList;
	}

	public void setNumeroUscitoList(List<NumeroUscitoDao> numeroUscitoList) {
		this.numeroUscitoList = numeroUscitoList;
	}
}
