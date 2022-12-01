package it.movioletto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VincitaEntityKey implements Serializable {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_stanza", nullable = false)
  private StanzaEntity stanza;

  @Column(name = "premio")
  private Integer premio;

  @Column(name = "id_tabella")
  private String idTabella;

}
