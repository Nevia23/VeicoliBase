package com.betacom.veicoli.services;

public class BiciImpl extends VeicoloAbstract {

	public Boolean isTipoAlimentazioneEligible(String tipoAlimentazione) {
		if (tipoAlimentazione.equals("elettrica") || tipoAlimentazione.equals("manuale")) {
			return true;
		} else {
			return false;
		}
	}
}
