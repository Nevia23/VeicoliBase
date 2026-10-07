package com.betacom.veicoli.models;

public class Moto extends Veicoli {

	private String targa;
	private Integer cc;

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

	@Override
	public String toString() {
		return "[ID=" + getId() + ", tipo di veicolo=" + getTipoVeicolo() + ", targa=" + targa + ", cilindrata=" + cc
				+ ", ruote=" + getNumeroRuote() + ", alimentazione=" + getTipoAlimentazione() + ", categoria="
				+ getCategoria() + ", colore=" + getColore() + ", marca=" + getMarca() + ", anno di produzione="
				+ getAnnoProduzione() + ", modello=" + getModello() + "]";
	}

}