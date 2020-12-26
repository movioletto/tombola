package it.movioletto.web.cartella.data;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;
import it.movioletto.dao.TabellaDao;
import it.movioletto.dao.VincitaDao;

import java.util.List;

public class CartellaData {

	private StanzaDao stanza;
	private TabellaDao tabella;
	private List<NumeroUscitoDao> numeroUscitoList;
	private List<VincitaDao> vincitaList;
	private PremioEnum premioCorrente;

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
