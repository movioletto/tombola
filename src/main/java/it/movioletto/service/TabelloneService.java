package it.movioletto.service;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;
import it.movioletto.dao.VincitaDao;

import java.util.List;

public interface TabelloneService {

	StanzaDao creaStanza(String nome);

	StanzaDao creaStanza(String id, String nome);

	StanzaDao getStanza(String idStanza);

	List<NumeroUscitoDao> getNumeriUsciti(String idStanza);

	void saveNumeroEstratto(String idStanza, int numeroEstratto);

	boolean existStanza(String idStanza);

	List<String> getGiocatoriPresenti(String idStanza);

	List<StanzaDao> getAllStanza();

	List<VincitaDao> getVincite(String idStanza);

	PremioEnum getPremioCorrente(String idStanza);

	boolean existPremio(String idStanza, String idTabella, Integer idPremio);

	void savePremio(String idStanza, String idTabella, Integer idPremio);

}
