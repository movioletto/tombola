package it.movioletto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "animale")
@Entity
public class AnimaleEntity implements Serializable {

	@Id
	@Column(name = "id_animale")
	private Integer idAnimale;

	@Column(name = "nome")
	private String nome;

	@Column(name = "genere")
	private String genere;

	public Integer getIdAnimale() {
		return idAnimale;
	}

	public void setIdAnimale(Integer idAnimale) {
		this.idAnimale = idAnimale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
}
