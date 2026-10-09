package com.betacom.veicoli;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.betacom.veicoli.models.Veicoli;
import com.betacom.veicoli.process.StartVeicolo;
import com.betacom.veicoli.singleton.ArchivioVeicoli;
import com.betacom.veicoli.utils.Utilities;

public class MainVeicoli {
	public final static String PATH_OUTPUT = "output/elenco.txt";
	public final static String PATH_PARAM = "assets/parametri.txt";
	
	public static void main(String[] args) throws IOException {
		File fileJson = new File(PATH_OUTPUT);
		List<Veicoli> lV = Utilities.importJson(fileJson);
		
		ArchivioVeicoli.getInstance().caricaJson(lV);
				
		List<String> params = Utilities.readFile(PATH_PARAM);
		
		System.out.println("Start Veicoli");
		
		new StartVeicolo().execute(params);
	}

}