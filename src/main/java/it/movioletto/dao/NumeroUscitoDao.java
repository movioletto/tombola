package it.movioletto.dao;

import java.util.Date;
import java.util.Objects;

public class NumeroUscitoDao {

	private String idStanza;
	private Integer numero;
	private Date data;

	public NumeroUscitoDao() {
	}

	public NumeroUscitoDao(Integer numero) {
		this.numero = numero;
	}

	public NumeroUscitoDao(String idStanza, Integer numero, Date data) {
		this.idStanza = idStanza;
		this.numero = numero;
		this.data = data;
	}

	public String getIdStanza() {
		return idStanza;
	}

	public void setIdStanza(String idStanza) {
		this.idStanza = idStanza;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NumeroUscitoDao that = (NumeroUscitoDao) o;

		return numero.equals(that.numero);
	}

	@Override
	public int hashCode() {
		return numero.hashCode();
	}
}
