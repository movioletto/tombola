package it.movioletto.model;

import it.movioletto.model.converter.StringToPremiCustom;
import java.io.Serializable;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "opzioni_stanza")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OpzioniStanzaEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_opzioni", nullable = false)
  private Integer idOpzioni;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_stanza", nullable = false)
  private StanzaEntity stanza;

  @Column(name = "codice_stanza_custom")
  private Boolean codiceStanzaCustom;

  @Column(name = "nomi_tabella_custom")
  private Boolean nomiTabellaCustom;

  @Column(name = "tombolino")
  private Boolean tombolino;

  @Column(name = "icone_tabella")
  private Boolean iconeTabella;

  @Column(name = "controllo_riga_gia_vinta")
  private Boolean controlloRigaGiaVinta;

  @Convert(converter = StringToPremiCustom.class)
  @Column(name = "premi_custom")
  private Map<Integer, String> premiCustom;

}
