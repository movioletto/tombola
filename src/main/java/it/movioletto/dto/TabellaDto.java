package it.movioletto.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TabellaDto {

  private String idTabella;
  private String idStanza;
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
