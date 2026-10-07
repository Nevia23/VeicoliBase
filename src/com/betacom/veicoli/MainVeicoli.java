package com.betacom.veicoli;

import java.util.List;

import com.betacom.veicoli.process.StartVeicolo;
import com.betacom.veicoli.utils.Utilities;

public class MainVeicoli {
	
	public static void main(String[] args) {
		
		String paramPath = "C:\\Users\\Bianca\\eclipse-workspace\\VeicoliBase\\assets\\parametri.txt";
		
		List<String> params = Utilities.readFile(paramPath);
		
		System.out.println("Start Veicoli");
		
		new StartVeicolo().execute(params);
	}

}