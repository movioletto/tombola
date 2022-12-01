package it.movioletto.service;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.VincitaDto;
import java.util.List;

public interface TabelloneService {

  StanzaDto creaStanza(String nome);

  StanzaDto creaStanza(String id, String nome);

  StanzaDto getStanza(String idStanza);

  List<NumeroUscitoDto> getNumeriUsciti(String idStanza);

  void saveNumeroEstratto(String idStanza, int numeroEstratto);

  boolean existStanza(String idStanza);

  List<String> getGiocatoriPresenti(String idStanza);

  List<StanzaDto> getAllStanza();

  List<VincitaDto> getVincite(String idStanza);

  PremioEnum getPremioCorrente(String idStanza);

  boolean existPremio(String idStanza, String idTabella, Integer idPremio);

  void savePremio(String idStanza, String idTabella, Integer idPremio);

}
