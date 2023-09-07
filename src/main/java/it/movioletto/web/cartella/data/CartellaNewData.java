package it.movioletto.web.cartella.data;

import it.movioletto.dto.OpzioniStanzaDto;
import it.movioletto.dto.TabellaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartellaNewData {

  private TabellaDto tabella;
  private OpzioniStanzaDto opzioniStanza;
}
