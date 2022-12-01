package it.movioletto.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "vincita")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VincitaEntity implements Serializable {

  @EmbeddedId
  private VincitaEntityKey id;

}
