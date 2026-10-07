package com.betacom.veicoli.services;

import java.util.Map;

import com.betacom.veicoli.models.Bici;
import com.betacom.veicoli.singleton.ArchivioVeicoli;

public class BiciImpl extends VeicoloAbstract {

	@Override
	public void add(String ope, String parametri) throws Exception {
		Map<String, String> map = decodeParams(parametri);
		Bici nuovaBici = new Bici();
		nuovaBici.setTipoVeicolo("bici");

		nuovaBici = (Bici) controlExecute(nuovaBici, map);

		nuovaBici.setNumeroMarce(parseIntParam(map, "marce", "Numero marce invalido"));

		if (!ArchivioVeicoli.getInstance().isValidValue("sospensione", map.get("sospensione")))
			throw new Exception("Tipo sospensione invalida");
		nuovaBici.setTipoSospensione(map.get("sospensione"));
		
		nuovaBici.setPieghevole(map.get("pieghevole").trim().equalsIgnoreCase("si") ? true : false);

		nuovaBici = (Bici) ArchivioVeicoli.getInstance().insertVeicolo(nuovaBici);
		
		System.out.println(".... Bici inserita");
		
	}
}
