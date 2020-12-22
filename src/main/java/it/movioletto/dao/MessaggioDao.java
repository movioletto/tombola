package it.movioletto.dao;

public class MessaggioDao {
	private int numeroUscito;

	public MessaggioDao(int numeroUscito) {
		this.numeroUscito = numeroUscito;
	}

	public int getNumeroUscito() {
		return numeroUscito;
	}

	public void setNumeroUscito(int numeroUscito) {
		this.numeroUscito = numeroUscito;
	}
}
