package it.movioletto.dao;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class TabellaDao {

	private String idTabella;
	private String idStanza;
	private List<List<NumeroDao>> sequenza;

	public TabellaDao() {
	}

	public TabellaDao(String sequenza) {
		this.setSequenza(sequenza);
	}

	public String getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(String idTabella) {
		this.idTabella = idTabella;
	}

	public String getIdStanza() {
		return idStanza;
	}

	public void setIdStanza(String idStanza) {
		this.idStanza = idStanza;
	}

	public List<List<NumeroDao>> getSequenza() {
		return sequenza;
	}

	public void setSequenza(List<List<NumeroDao>> sequenza) {
		this.sequenza = sequenza;
	}

	public void setSequenza(String sequenza) {
		List<List<NumeroDao>> sequenzaList = new ArrayList<>();

		for(String riga: StringUtils.split(sequenza, "|")) {
			List<NumeroDao>	rigaList = new ArrayList<>();

			for(String numero: StringUtils.split(riga, ",")) {
				NumeroDao numeroDao = new NumeroDao(Integer.valueOf(numero));
				rigaList.add(numeroDao);
			}
			sequenzaList.add(rigaList);
		}

		this.sequenza = sequenzaList;
	}

	public void setNumeriUsciti(List<NumeroUscitoDao> numeriUsciti) {
		numeriUsciti.forEach(numeroUscitoDao -> {
			this.sequenza.forEach(riga -> {
				riga.forEach(numeroDao -> {
					if(numeroDao.getNumero().equals(numeroUscitoDao.getNumero())) {
						numeroDao.setUscito(true);
					}
				});
			});
		});
	}
}
