package it.movioletto.model;

import java.io.Serializable;
import java.util.Date;
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

@Table(name = "numero_uscito")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NumeroUscitoEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_numero_uscito", nullable = false)
  private Integer idNumeroUscito;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_stanza", nullable = false)
  private StanzaEntity stanza;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "data")
  private Date data;
}
