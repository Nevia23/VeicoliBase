package com.betacom.veicoli.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bici extends Veicoli {

	private Integer numeroMarce;
	private String tipoSospensione; // senza, mono, bi
	private Boolean pieghevole;

	@Override
	public String toString() {
		return "[ID=" + getId() + ", tipo di veicolo=" + getTipoVeicolo() + ", numero di marce=" + numeroMarce
				+ ", tipo sospensione=" + tipoSospensione + ", pieghevole=" + pieghevole + ", ruote=" + getNumeroRuote()
				+ ", alimentazione=" + getTipoAlimentazione() + ", categoria=" + getCategoria() + ", colore="
				+ getColore() + ", marca=" + getMarca() + ", anno di produzione=" + getAnnoProduzione() + ", modello="
				+ getModello() + "]";
	}

}
