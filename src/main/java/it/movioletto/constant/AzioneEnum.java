package it.movioletto.constant;

import lombok.Getter;

@Getter
public enum AzioneEnum {
  SALUTO(1, "Saluto"),
  NUMERO_USCITO(2, "Numero uscito"),
  RICHIESTA_PREMIO(3, "Richiesta premio"),
  CONFERMA_PREMIO(4, "Conferma premio");

  private final Integer codice;
  private final String valore;

  AzioneEnum(Integer codice, String valore) {
    this.codice = codice;
    this.valore = valore;
  }

}
