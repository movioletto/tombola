package it.movioletto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "stanza")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StanzaEntity implements Serializable {

  @Id
  @Column(name = "id_stanza")
  private String idStanza;

  @Column(name = "nome")
  private String nome;

  @OneToMany(mappedBy = "id.stanza", fetch = FetchType.LAZY)
  private List<TabellaEntity> tabellaList;

  @OneToMany(mappedBy = "id.stanza", fetch = FetchType.LAZY)
  @OrderBy("data DESC")
  private List<NumeroUscitoEntity> numeroUscitoList;

  @OneToMany(mappedBy = "id.stanza", fetch = FetchType.LAZY)
  private List<VincitaEntity> vincitaList;

  public StanzaEntity(String idStanza) {
    this.idStanza = idStanza;
  }

}
