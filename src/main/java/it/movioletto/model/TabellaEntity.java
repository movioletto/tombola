package it.movioletto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tabella")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TabellaEntity implements Serializable {

  @EmbeddedId
  private TabellaEntityKey id;

  @Column(name = "sequenza")
  private String sequenza;

}
