package com.betacom.veicoli.models;

public class Macchina extends Veicoli {

	private String targa; // deve essere univoca
	private Integer cilindrata;
	private Integer numeroPorte;

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Integer getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(Integer cilindrata) {
		this.cilindrata = cilindrata;
	}

	public Integer getNumeroPorte() {
		return numeroPorte;
	}

	public void setNumeroPorte(Integer numeroPorte) {
		this.numeroPorte = numeroPorte;
	}

	@Override
	public String toString() {
		return "[ID=" + getId() + ", tipo di veicolo=" + getTipoVeicolo() + ", targa=" + targa + ", cilindrata="
				+ cilindrata + ", porte=" + numeroPorte + ", ruote=" + getNumeroRuote() + ", alimentazione="
				+ getTipoAlimentazione() + ", categoria=" + getCategoria() + ", colore=" + getColore() + ", marca="
				+ getMarca() + ", anno di produzione=" + getAnnoProduzione() + ", modello=" + getModello() + "]";
	}

}
