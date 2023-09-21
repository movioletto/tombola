package it.movioletto.dto;

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
public class MessaggioDto {

  private Integer azione;
  private Integer numeroUscito;
  private Integer idTabella;
  private String nomeTabella;
  private String aggettivoTabella;
  private String iconaTabella;
  private Integer idPremio;
  private String nomePremio;

}
