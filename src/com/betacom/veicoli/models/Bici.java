package com.betacom.veicoli.models;

public class Bici extends Veicoli {

	private Integer numeroMarce;
	private String tipoSospensione; // senza, mono, bi
	private Boolean pieghevole;

	public Integer getNumeroMarce() {
		return numeroMarce;
	}

	public void setNumeroMarce(Integer numeroMarce) {
		this.numeroMarce = numeroMarce;
	}

	public String getTipoSospensione() {
		return tipoSospensione;
	}

	public void setTipoSospensione(String tipoSospensione) {
		this.tipoSospensione = tipoSospensione;
	}

	public Boolean getPieghevole() {
		return pieghevole;
	}

	public void setPieghevole(Boolean pieghevole) {
		this.pieghevole = pieghevole;
	}

	@Override
	public String toString() {
		return "[ID=" + getId() + ", tipo di veicolo=" + getTipoVeicolo() + ", numero di marce=" + numeroMarce
				+ ", tipo sospensione=" + tipoSospensione + ", pieghevole=" + pieghevole + ", ruote=" + getNumeroRuote()
				+ ", alimentazione=" + getTipoAlimentazione() + ", categoria=" + getCategoria() + ", colore="
				+ getColore() + ", marca=" + getMarca() + ", anno di produzione=" + getAnnoProduzione() + ", modello="
				+ getModello() + "]";
	}

}
