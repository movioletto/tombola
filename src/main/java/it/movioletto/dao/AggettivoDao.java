package it.movioletto.dao;

public class AggettivoDao {

	private Integer idAggettivo;
	private String maschile;
	private String femminile;

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
