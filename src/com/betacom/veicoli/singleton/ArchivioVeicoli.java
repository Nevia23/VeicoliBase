package com.betacom.veicoli.singleton;

import java.util.ArrayList;
import java.util.List;

import com.betacom.veicoli.models.Veicoli;

public class ArchivioVeicoli {
	
	private static ArchivioVeicoli instance = null;
	private List<Veicoli> listaVeicoli = new ArrayList<Veicoli>();
	private Integer counter = 1;
	
	
	private ArchivioVeicoli() {
		
	}
	
	public static ArchivioVeicoli getInstance() {
		
		if (instance == null) {
			instance = new ArchivioVeicoli();
		}
		
		return instance;
	}

	public List<Veicoli> getListaVeicoli() {
		return listaVeicoli;
	}
	
	public Integer nextId() {
		return counter++;
	}
	
}
