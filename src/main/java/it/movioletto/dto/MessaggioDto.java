package it.movioletto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessaggioDto {

  private Integer azione;
  private Integer numeroUscito;
  private String idTabella;
  private Integer idPremio;
  private String nomePremio;

}
