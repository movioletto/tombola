package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "animale")
@Entity
public class AnimaleEntity implements Serializable {

	@Id
	@Column(name = "id_animale")
	private Integer idAnimale;

	@Column(name = "nome")
	private String nome;

	public int getIdAnimale() {
		return idAnimale;
	}

	public void setIdAnimale(int idAnimale) {
		this.idAnimale = idAnimale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
