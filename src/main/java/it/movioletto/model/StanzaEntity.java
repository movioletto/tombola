package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "stanza")
@Entity
public class StanzaEntity implements Serializable {

	@Id
	@Column(name = "id_stanza")
	private String idStanza;

	@Column(name = "nome")
	private String nome;

	@OneToMany(mappedBy = "id.stanza", fetch = FetchType.LAZY)
	private List<TabellaEntity> tabellaList;

	@OneToMany(mappedBy = "id.stanza", fetch = FetchType.LAZY)
	@OrderBy("data DESC")
	private List<NumeroUscitoEntity> numeroUscitoList;

	@OneToMany(mappedBy = "id.stanza", fetch = FetchType.LAZY)
	private List<VincitaEntity> vincitaList;

	public StanzaEntity() {
	}

	public StanzaEntity(String idStanza) {
		this.idStanza = idStanza;
	}

	public String getIdStanza() {
		return idStanza;
	}

	public void setIdStanza(String idStanza) {
		this.idStanza = idStanza;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TabellaEntity> getTabellaList() {
		return tabellaList;
	}

	public void setTabellaList(List<TabellaEntity> tabellaList) {
		this.tabellaList = tabellaList;
	}

	public List<NumeroUscitoEntity> getNumeroUscitoList() {
		return numeroUscitoList;
	}

	public void setNumeroUscitoList(List<NumeroUscitoEntity> numeroUscitoList) {
		this.numeroUscitoList = numeroUscitoList;
	}

	public List<VincitaEntity> getVincitaList() {
		return vincitaList;
	}

	public void setVincitaList(List<VincitaEntity> vincitaList) {
		this.vincitaList = vincitaList;
	}
}
