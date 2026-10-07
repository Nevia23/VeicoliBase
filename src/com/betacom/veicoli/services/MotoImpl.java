package com.betacom.veicoli.services;

import java.util.Map;

import com.betacom.veicoli.models.Moto;
import com.betacom.veicoli.singleton.ArchivioVeicoli;

public class MotoImpl extends VeicoloAbstract {

	@Override
	public void add(String ope, String params) throws Exception {
		Map<String, String> p = decodeParams(params);
		
		Moto moto = new Moto();
		moto.setTipoVeicolo("moto");
		
		moto = (Moto) controlExecute(moto, p);
		
		if (ArchivioVeicoli.getInstance().doesTargaExist(p.get("targa").toUpperCase()))
			throw new Exception("Targa già inserita");
		moto.setTarga(p.get("targa").toUpperCase());
		
		moto.setCc(parseIntParam(p, "cc", "Cilindrata invalida"));
		
		moto = (Moto) ArchivioVeicoli.getInstance().insertVeicolo(moto);
		System.out.println("Moto inserita");
		
	}


}