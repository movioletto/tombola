package it.movioletto.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StanzaDto implements Serializable {

  private String idStanza;
  private String nome;
  private List<String> giocatorePresenteList;
  private List<NumeroUscitoDto> numeroUscitoList;
  private List<VincitaDto> vincitaList;

}
