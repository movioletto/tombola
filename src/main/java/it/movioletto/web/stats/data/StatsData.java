package it.movioletto.web.stats.data;

import it.movioletto.dto.StanzaDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsData {

  private List<StanzaDto> stanzaList;
}
