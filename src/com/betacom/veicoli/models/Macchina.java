package com.betacom.veicoli.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Macchina extends Veicoli {

	private String targa; // deve essere univoca
	private Integer cilindrata;
	private Integer numeroPorte;

	@Override
	public String toString() {
		return "[ID=" + getId() + ", tipo di veicolo=" + getTipoVeicolo() + ", targa=" + targa + ", cilindrata="
				+ cilindrata + ", porte=" + numeroPorte + ", ruote=" + getNumeroRuote() + ", alimentazione="
				+ getTipoAlimentazione() + ", categoria=" + getCategoria() + ", colore=" + getColore() + ", marca="
				+ getMarca() + ", anno di produzione=" + getAnnoProduzione() + ", modello=" + getModello() + "]";
	}

}
