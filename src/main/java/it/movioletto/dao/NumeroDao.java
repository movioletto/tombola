package it.movioletto.dao;

public class NumeroDao {
	private Integer numero;
	private Boolean uscito;

	public NumeroDao() {
	}

	public NumeroDao(Integer numero) {
		this.numero = numero;
		this.uscito = false;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getUscito() {
		return uscito;
	}

	public void setUscito(Boolean uscito) {
		this.uscito = uscito;
	}
}
