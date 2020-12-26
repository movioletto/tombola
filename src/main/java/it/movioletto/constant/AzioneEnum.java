package it.movioletto.constant;

public enum AzioneEnum {
	SALUTO(1, "Saluto"),
	NUMERO_USCITO(2, "Numero uscito"),
	RICHIESTA_PREMIO(3, "Richiesta premio"),
	CONFERMA_PREMIO(4, "Conferma premio");

	private Integer codice;
	private String valore;

	AzioneEnum(Integer codice, String valore) {
		this.codice = codice;
		this.valore = valore;
	}

	public Integer getCodice() {
		return codice;
	}

	public String getValore() {
		return valore;
	}

}
