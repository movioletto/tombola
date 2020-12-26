package it.movioletto.constant;

public enum PremioEnum {
	AMBO(1, "Ambo"),
	TERNO(2, "Terno"),
	QUATERNA(3, "Quaterna"),
	CINQUINA(4, "Cinquina"),
	TOMBOLA(5, "Tombola");

	private Integer codice;
	private String valore;

	PremioEnum(Integer codice, String valore) {
		this.codice = codice;
		this.valore = valore;
	}

	public static PremioEnum getEnumByCodice(Integer codice) {
		for (PremioEnum v : values()) {
			if (v.getCodice().equals(codice)) {
				return v;
			}
		}
		return null;
	}

	public static String getValoreByCodice(Integer codice) {
		for (PremioEnum v : values()) {
			if (v.getCodice().equals(codice)) {
				return v.getValore();
			}
		}
		return null;
	}

	public Integer getCodice() {
		return codice;
	}

	public String getValore() {
		return valore;
	}

}
