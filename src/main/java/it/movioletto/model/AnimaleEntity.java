package it.movioletto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "animale")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimaleEntity implements Serializable {

  @Id
  @Column(name = "id_animale")
  private Integer idAnimale;

  @Column(name = "nome")
  private String nome;

  @Column(name = "genere")
  private String genere;

}
