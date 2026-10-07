package com.betacom.veicoli.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.betacom.veicoli.models.Veicoli;
import com.betacom.veicoli.singleton.ArchivioVeicoli;

public abstract class VeicoloAbstract {

	public abstract void add(String ope, String parametri) throws Exception;

	public Map<String, String> decodeParams(String par) {
		String[] p = par.split(",");

		Map<String, String> map = Arrays.stream(p).map(s -> s.split("=", 2))
				.collect(Collectors.toMap(arr -> arr[0].trim(), arr -> arr[1].trim()));

		return map;
	}

	public Veicoli controlExecute(Veicoli veicolo, Map<String, String> params) throws Exception {

		veicolo.setTipoAlimentazione(validaValore(params, "alim", "Tipo alimentazione invalida"));
		veicolo.setCategoria(validaValore(params, "cat", "Categoria invalida"));
		veicolo.setColore(validaValore(params, "colore", "Colore invalido"));
		veicolo.setMarca(validaValore(params, "marca", "Marca invalida"));

//		----- versione precedente -----
//		if (!ArchivioVeicoli.getInstance().isValidValue("cat", params.get("cat")))
//			throw new Exception("Categoria invalida");
//		veicolo.setCategoria(params.get("cat"));

		veicolo.setNumeroRuote(parseIntParam(params, "ruote", "Numero ruote non valido"));
		veicolo.setAnnoProduzione(parseIntParam(params, "anno", "Anno produzione invalida"));
		
		if (veicolo.getAnnoProduzione() < LocalDate.now().getYear() - 20
				|| veicolo.getAnnoProduzione() > LocalDate.now().getYear())
			throw new Exception("Anno produzione invalida");

		veicolo.setModello(params.get("modello"));

		return veicolo;

	}

	private String validaValore(Map<String, String> params, String chiave, String messaggio) throws Exception {
		if (!ArchivioVeicoli.getInstance().isValidValue(chiave, params.get(chiave)))
			throw new Exception(messaggio);
		return params.get(chiave);
	}

	protected Integer parseIntParam(Map<String, String> params, String chiave, String messaggio) throws Exception {
		try {
			return Integer.parseInt(params.get(chiave));
		} catch (NumberFormatException e) {
			throw new Exception(messaggio);
		}
	}

}
