package it.movioletto.service;

import it.movioletto.dto.OpzioniStanzaDto;
import it.movioletto.dto.TabellaDto;

public interface CartellaService {

  TabellaDto creaTabella(TabellaDto dto);

  boolean isNotExistTabella(Integer idTabella, Integer idStanza);

  boolean existTabella(String nome, Integer idStanza);

  boolean existTabella(String nome, String aggettivo, Integer idStanza);

  boolean existSequenza(String sequenza, Integer idStanza);

  TabellaDto getTabella(Integer idTabella, Integer idStanza);

  boolean isValidaNuovaCartella(TabellaDto dto, OpzioniStanzaDto opzioniStanza);
}
