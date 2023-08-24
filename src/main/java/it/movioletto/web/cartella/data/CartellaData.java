package it.movioletto.web.cartella.data;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dto.AnimaleDto;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.dto.VincitaDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartellaData {

  private StanzaDto stanza;
  private TabellaDto tabella;
  private List<NumeroUscitoDto> numeroUscitoList;
  private List<VincitaDto> vincitaList;
  private PremioEnum premioCorrente;
  private List<AnimaleDto> animaleList;

}
