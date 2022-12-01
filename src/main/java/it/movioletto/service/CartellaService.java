package it.movioletto.service;

import it.movioletto.dto.TabellaDto;

public interface CartellaService {

  TabellaDto creaTabella(String idStanza);

  boolean existTabella(String idTabella, String idStanza);

  boolean existSequenza(String sequenza, String idStanza);

  TabellaDto getTabella(String idTabella, String idStanza);
}
