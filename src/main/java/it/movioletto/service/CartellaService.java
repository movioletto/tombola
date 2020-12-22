package it.movioletto.service;

import it.movioletto.dao.TabellaDao;

public interface CartellaService {

	TabellaDao creaTabella(String idStanza);

	boolean existTabella(String idTabella, String idStanza);

	boolean existSequenza(String sequenza, String idStanza);

	TabellaDao getTabella(String idTabella, String idStanza);
}
