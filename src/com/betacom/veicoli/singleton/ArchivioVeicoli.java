package com.betacom.veicoli.singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.veicoli.exceptions.ExceptionVeicoli;
import com.betacom.veicoli.models.Macchina;
import com.betacom.veicoli.models.Moto;
import com.betacom.veicoli.models.Veicoli;
import com.betacom.veicoli.utils.Utilities;

public class ArchivioVeicoli {

	private static ArchivioVeicoli instance = null;

	Map<String, String[]> controlli = new HashMap<String, String[]>();
	Map<String, String> lTarghe = new HashMap<String, String>();
	private List<Veicoli> listaVeicoli = new ArrayList<Veicoli>();
	private Integer counter = 0;

	private ArchivioVeicoli() {

	}

	public static ArchivioVeicoli getInstance() {
		if (instance == null) {
			instance = new ArchivioVeicoli();
		}

		return instance;
	}

	public void loadConstant(String path) {
		Utilities.readFile(path).forEach(it -> {
			String[] el = it.split("=", 2);
			controlli.put(el[0].trim(), el[1].split(","));
		});
	}

	public boolean isValidValue(String key, String value) {
		String[] values = controlli.get(key);

		if (values == null)
			throw new ExceptionVeicoli("Chiave di controllo non prevista: " + key);

		return Arrays.stream(values).anyMatch(x -> x.equalsIgnoreCase(value));
	}

	public boolean doesTargaExist(String targa) {
		if (lTarghe.containsKey(targa))
			return true;

		lTarghe.put(targa.toUpperCase(), "");
		return false;
	}

	public void caricaJson(List<Veicoli> lV) {
		listaVeicoli = lV;
		
		if (!listaVeicoli.isEmpty()) {
			Integer maxId = lV.stream()
					.map(v -> v.getId())
	                .max((a, b) -> Integer.compare(a, b))
	                .orElse(0);
			
			counter = maxId;
		} else {
			counter = 0;
		}
	}

	public Veicoli insertVeicolo(Veicoli v) {
		v.setId(++counter);
		listaVeicoli.add(v);
		return v;
	}

	public String getTarga(Veicoli v) {
		if (v instanceof Macchina)
			return ((Macchina) v).getTarga();
		if (v instanceof Moto)
			return ((Moto) v).getTarga();
		return null; // la bici non ha targa
	}

	public void remove(Integer id) {
		Veicoli v = listaVeicoli.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
		if (v == null)
			throw new ExceptionVeicoli("Veicolo non trovato: " + id);
		String t = getTarga(v);
		if (t != null)
			lTarghe.remove(t.toUpperCase());
		listaVeicoli.remove(v);
	}

	public List<Veicoli> getListaVeicoli() {
		return listaVeicoli;
	}

	public int nextId() {
		return counter++;
	}

}
