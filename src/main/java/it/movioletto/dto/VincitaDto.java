package it.movioletto.dto;

import it.movioletto.constant.PremioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VincitaDto {

  private String idStanza;
  private Integer premio;
  private String nomePremio;
  private String idTabella;

  public VincitaDto(String idStanza, String idTabella, PremioEnum premioEnum) {
    this.idStanza = idStanza;
    this.premio = premioEnum.getCodice();
    this.nomePremio = premioEnum.getValore();
    this.idTabella = idTabella;
  }

}
