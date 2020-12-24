package it.movioletto.service;

import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;

import java.util.List;

public interface TabelloneService {

	StanzaDao creaStanza(String nome);

	StanzaDao getStanza(String idStanza);

	List<NumeroUscitoDao> getNumeriUsciti(String idStanza);

	void saveNumeroEstratto(String idStanza, int numeroEstratto);

	boolean existStanza(String idStanza);

	List<String> getGiocatoriPresenti(String idStanza);
}
