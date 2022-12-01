package it.movioletto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "numero_uscito")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumeroUscitoEntity implements Serializable {

  @EmbeddedId
  private NumeroUscitoEntityKey id;

  @Column(name = "data")
  private Date data;

}
