package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "aggettivo")
@Entity
public class AggettivoEntity implements Serializable {

	@Id
	@Column(name = "id_aggettivo")
	private Integer idAggettivo;

	@Column(name = "nome")
	private String nome;

	public int getIdAggettivo() {
		return idAggettivo;
	}

	public void setIdAggettivo(int idAggettivo) {
		this.idAggettivo = idAggettivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
