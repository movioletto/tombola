package it.movioletto.dao;

import it.movioletto.constant.PremioEnum;

public class VincitaDao {

	private String idStanza;
	private Integer premio;
	private String nomePremio;
	private String idTabella;

	public VincitaDao() {
	}

	public VincitaDao(String idStanza, String idTabella, PremioEnum premioEnum) {
		this.idStanza = idStanza;
		this.premio = premioEnum.getCodice();
		this.nomePremio = premioEnum.getValore();
		this.idTabella = idTabella;
	}

	public String getIdStanza() {
		return idStanza;
	}

	public void setIdStanza(String idStanza) {
		this.idStanza = idStanza;
	}

	public Integer getPremio() {
		return premio;
	}

	public void setPremio(Integer premio) {
		this.premio = premio;
	}

	public String getNomePremio() {
		return nomePremio;
	}

	public void setNomePremio(String nomePremio) {
		this.nomePremio = nomePremio;
	}

	public String getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(String idTabella) {
		this.idTabella = idTabella;
	}
}
