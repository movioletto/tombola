package it.movioletto.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StanzaDto implements Serializable {

  private Integer idStanza;
  private String codice;
  private String nome;
  private OpzioniStanzaDto opzioniStanza;
  private List<TabellaDto> giocatorePresenteList;
  private List<NumeroUscitoDto> numeroUscitoList;
  private List<VincitaDto> vincitaList;

}
