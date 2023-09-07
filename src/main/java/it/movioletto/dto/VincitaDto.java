package it.movioletto.dto;

import it.movioletto.constant.PremioEnum;
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
public class VincitaDto {

  private Integer idVincita;
  private Integer idStanza;
  private Integer premio;
  private String nomePremio;
  private TabellaDto tabella;

  public VincitaDto(Integer idVincita, Integer idStanza, TabellaDto tabella,
      PremioEnum premioEnum) {
    this.idVincita = idVincita;
    this.idStanza = idStanza;
    this.premio = premioEnum.getCodice();
    this.nomePremio = premioEnum.getValore();
    this.tabella = tabella;
  }

}
