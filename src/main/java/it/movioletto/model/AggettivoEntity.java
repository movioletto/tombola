package it.movioletto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "aggettivo")
@Entity
public class AggettivoEntity implements Serializable {

	@Id
	@Column(name = "id_aggettivo")
	private Integer idAggettivo;

	@Column(name = "maschile")
	private String maschile;

	@Column(name = "femminile")
	private String femminile;

	public Integer getIdAggettivo() {
		return idAggettivo;
	}

	public void setIdAggettivo(Integer idAggettivo) {
		this.idAggettivo = idAggettivo;
	}

	public String getMaschile() {
		return maschile;
	}

	public void setMaschile(String maschile) {
		this.maschile = maschile;
	}

	public String getFemminile() {
		return femminile;
	}

	public void setFemminile(String femminile) {
		this.femminile = femminile;
	}
}
