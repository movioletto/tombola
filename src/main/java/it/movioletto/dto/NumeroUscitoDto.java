package it.movioletto.dto;

import java.util.Date;
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
public class NumeroUscitoDto {

  private Integer idNumeroUscito;
  private Integer idStanza;
  private Integer numero;
  private Date data;

  public NumeroUscitoDto(Integer numero) {
    this.numero = numero;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    NumeroUscitoDto that = (NumeroUscitoDto) o;

    return numero.equals(that.numero);
  }

  @Override
  public int hashCode() {
    return numero.hashCode();
  }
}
