package it.movioletto.dto;

import java.io.Serializable;
import java.util.Map;
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
public class OpzioniStanzaDto implements Serializable {

  private Integer idOpzioni;
  private Integer idStanza;
  private Boolean codiceStanzaCustom;
  private Boolean nomiTabellaCustom;
  private Boolean tombolino;
  private Boolean iconeTabella;
  private Boolean controlloRigaGiaVinta;
  private Map<Integer, String> premiCustom;

}
