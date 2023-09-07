package it.movioletto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "vincita")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VincitaEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_vincita")
  private Integer idVincita;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_stanza", nullable = false)
  private StanzaEntity stanza;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_tabella", nullable = false)
  private TabellaEntity tabella;

  @Column(name = "premio")
  private Integer premio;

}
