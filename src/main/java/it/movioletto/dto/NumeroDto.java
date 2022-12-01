package it.movioletto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NumeroDto {

  private Integer numero;
  private Boolean uscito;

  public NumeroDto(Integer numero) {
    this.numero = numero;
    this.uscito = false;
  }

}
