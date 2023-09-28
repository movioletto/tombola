package it.movioletto.service;

import it.movioletto.dto.AggettivoDto;
import it.movioletto.dto.AnimaleDto;
import it.movioletto.dto.StanzaDto;
import java.util.List;

public interface CommonService {

  StanzaDto getStanza(String codiceStanza);

  List<AnimaleDto> getAllAnimale();

  List<AggettivoDto> getAllAggettivo();

  List<AnimaleDto> getDieciAnimaleRandom();
}
