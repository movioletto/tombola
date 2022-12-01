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

@Table(name = "aggettivo")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AggettivoEntity implements Serializable {

  @Id
  @Column(name = "id_aggettivo")
  private Integer idAggettivo;

  @Column(name = "maschile")
  private String maschile;

  @Column(name = "femminile")
  private String femminile;

}
