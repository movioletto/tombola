package it.movioletto.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "numero_uscito")
@Entity
public class NumeroUscitoEntity implements Serializable {

	@EmbeddedId
	private NumeroUscitoEntityKey id;

	@Column(name = "data")
	private Date data;

	public NumeroUscitoEntityKey getId() {
		return id;
	}

	public void setId(NumeroUscitoEntityKey id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
