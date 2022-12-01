package it.movioletto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AggettivoDto {

  private Integer idAggettivo;
  private String maschile;
  private String femminile;

}
