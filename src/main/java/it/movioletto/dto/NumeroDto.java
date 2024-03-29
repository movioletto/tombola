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
public class NumeroDto {

  private Integer numero;
  private Boolean uscito;

  public NumeroDto(Integer numero) {
    this.numero = numero;
    this.uscito = false;
  }

}
