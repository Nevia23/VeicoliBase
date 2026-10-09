package com.betacom.veicoli.services;

import java.util.Map;

import com.betacom.veicoli.models.Macchina;
import com.betacom.veicoli.singleton.ArchivioVeicoli;
import com.betacom.veicoli.utils.Utilities;

public class MacchinaImpl extends VeicoloAbstract {

	@Override
	public void add(String ope, String params) throws Exception {

		System.out.println("Execute Macchina " + ope);

		Map<String, String> p = Utilities.decodeParams(params);

		Macchina mac = new Macchina();
		mac.setTipoVeicolo("macchina");

		mac = (Macchina) controlExecute(mac, p);

		mac.setCilindrata(parseIntParam(p, "cc", "Cilindrata invalida"));
		mac.setNumeroPorte(parseIntParam(p, "porte", "Numero porte invalido"));

		if (ArchivioVeicoli.getInstance().doesTargaExist(p.get("targa").toUpperCase()))
			throw new Exception("Targa già inserita");
		mac.setTarga(p.get("targa").toUpperCase());

		mac = (Macchina) ArchivioVeicoli.getInstance().insertVeicolo(mac);
		System.out.println("*** Macchina inserita ***");

	}
}
