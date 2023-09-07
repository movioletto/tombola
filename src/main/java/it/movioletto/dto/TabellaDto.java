package it.movioletto.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TabellaDto {

  private Integer idTabella;
  private Integer idStanza;
  private String codiceStanza;
  private String nome;
  private String aggettivo;
  private List<List<NumeroDto>> sequenza;

  public TabellaDto(String sequenza) {
    this.setSequenza(sequenza);
  }

  public void setSequenza(String sequenza) {
    List<List<NumeroDto>> sequenzaList = new ArrayList<>();

    for (String riga : StringUtils.split(sequenza, "|")) {
      List<NumeroDto> rigaList = new ArrayList<>();

      for (String numero : StringUtils.split(riga, ",")) {
        NumeroDto numeroDto = new NumeroDto(Integer.valueOf(numero));
        rigaList.add(numeroDto);
      }
      sequenzaList.add(rigaList);
    }

    this.sequenza = sequenzaList;
  }

  public void setNumeriUsciti(List<NumeroUscitoDto> numeriUsciti) {
    numeriUsciti.forEach(
        numeroUscitoDto -> this.sequenza.forEach(riga -> riga.forEach(numeroDto -> {
          if (numeroDto.getNumero().equals(numeroUscitoDto.getNumero())) {
            numeroDto.setUscito(true);
          }
        })));
  }
}
