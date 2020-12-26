package it.movioletto.dao;

public class MessaggioDao {

	private Integer azione;
	private Integer numeroUscito;
	private String idTabella;
	private Integer idPremio;
	private String nomePremio;

	public Integer getAzione() {
		return azione;
	}

	public void setAzione(Integer azione) {
		this.azione = azione;
	}

	public Integer getNumeroUscito() {
		return numeroUscito;
	}

	public void setNumeroUscito(Integer numeroUscito) {
		this.numeroUscito = numeroUscito;
	}

	public String getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(String idTabella) {
		this.idTabella = idTabella;
	}

	public Integer getIdPremio() {
		return idPremio;
	}

	public void setIdPremio(Integer idPremio) {
		this.idPremio = idPremio;
	}

	public String getNomePremio() {
		return nomePremio;
	}

	public void setNomePremio(String nomePremio) {
		this.nomePremio = nomePremio;
	}

}
