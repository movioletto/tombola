package it.movioletto.web.stats.data;

import it.movioletto.dao.StanzaDao;

import java.util.List;

public class StatsData {

	private List<StanzaDao> stanzaList;

	public List<StanzaDao> getStanzaList() {
		return stanzaList;
	}

	public void setStanzaList(List<StanzaDao> stanzaList) {
		this.stanzaList = stanzaList;
	}
}
