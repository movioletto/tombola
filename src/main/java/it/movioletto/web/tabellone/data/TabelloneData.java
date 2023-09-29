package it.movioletto.web.tabellone.data;

import it.movioletto.dto.AnimaleDto;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.PremioDto;
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
public class TabelloneData {

  private StanzaDto stanza;
  private List<TabellaDto> tabellaList;
  private List<NumeroUscitoDto> numeroUscitoList;
  private List<VincitaDto> vincitaList;
  private PremioDto premioCorrente;
  private List<AnimaleDto> animaleList;

}
