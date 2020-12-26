package it.movioletto.web.tabellone.data;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;
import it.movioletto.dao.TabellaDao;
import it.movioletto.dao.VincitaDao;

import java.util.List;

public class TabelloneData {

	private StanzaDao stanza;
	private List<TabellaDao> tabellaList;
	private List<NumeroUscitoDao> numeroUscitoList;
	private List<VincitaDao> vincitaList;
	private PremioEnum premioCorrente;

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

	public List<VincitaDao> getVincitaList() {
		return vincitaList;
	}

	public void setVincitaList(List<VincitaDao> vincitaList) {
		this.vincitaList = vincitaList;
	}

	public PremioEnum getPremioCorrente() {
		return premioCorrente;
	}

	public void setPremioCorrente(PremioEnum premioCorrente) {
		this.premioCorrente = premioCorrente;
	}
}
