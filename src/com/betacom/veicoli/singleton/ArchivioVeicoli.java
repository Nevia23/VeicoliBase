package com.betacom.veicoli.singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.veicoli.exceptions.ExceptionVeicoli;
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

	public Veicoli insertVeicolo(Veicoli v) {
		v.setId(++counter);
		listaVeicoli.add(v);
		return v;
	}

	public void remove(Integer id) {
		listaVeicoli.removeIf(it -> it.getId() == id);

	}

	public List<Veicoli> getListaVeicoli() {
		return listaVeicoli;
	}

	public int nextId() {
		return counter++;
	}

}
