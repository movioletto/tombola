package it.movioletto.service;

import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.OpzioniStanzaDto;
import it.movioletto.dto.PremioDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.dto.VincitaDto;
import java.util.List;

public interface TabelloneService {

  StanzaDto creaStanza(StanzaDto dto);

  List<NumeroUscitoDto> getNumeriUsciti(Integer idStanza);

  void saveNumeroEstratto(Integer idStanza, int numeroEstratto);

  boolean existStanzaByCodice(String codice);

  List<TabellaDto> getGiocatoriPresenti(Integer idStanza);

  List<StanzaDto> getAllStanza();

  List<VincitaDto> getVincite(Integer idStanza, OpzioniStanzaDto opzioniStanza);

  PremioDto getPremioCorrente(Integer idStanza, OpzioniStanzaDto opzioniStanza);

  boolean existPremio(Integer idStanza, Integer idTabella, Integer idPremio);

  void savePremio(Integer idStanza, Integer idTabella, Integer idPremio);

}
