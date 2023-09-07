package it.movioletto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "stanza")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StanzaEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_stanza")
  private Integer idStanza;

  @Column(name = "codice")
  private String codice;

  @Column(name = "nome")
  private String nome;

  @OneToOne(mappedBy = "stanza", fetch = FetchType.EAGER)
  private OpzioniStanzaEntity opzioniStanza;

  @OneToMany(mappedBy = "stanza", fetch = FetchType.LAZY)
  private List<TabellaEntity> tabellaList;

  @OneToMany(mappedBy = "stanza", fetch = FetchType.LAZY)
  @OrderBy("data DESC")
  private List<NumeroUscitoEntity> numeroUscitoList;

  @OneToMany(mappedBy = "stanza", fetch = FetchType.LAZY)
  private List<VincitaEntity> vincitaList;

  public StanzaEntity(Integer idStanza) {
    this.idStanza = idStanza;
  }

}
