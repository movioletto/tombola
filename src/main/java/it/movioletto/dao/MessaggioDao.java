package it.movioletto.dao;

public class MessaggioDao {
	//	Cartella
	private int numeroUscito;

	//	Tabellone
	private String idTabella;

	public MessaggioDao(int numeroUscito) {
		this.numeroUscito = numeroUscito;
	}

	public MessaggioDao(String idTabella) {
		this.idTabella = idTabella;
	}

	public int getNumeroUscito() {
		return numeroUscito;
	}

	public void setNumeroUscito(int numeroUscito) {
		this.numeroUscito = numeroUscito;
	}

	public String getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(String idTabella) {
		this.idTabella = idTabella;
	}
}
