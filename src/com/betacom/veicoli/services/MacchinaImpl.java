package com.betacom.veicoli.services;

import java.util.HashMap;
import java.util.Map;

import com.betacom.veicoli.exceptions.ExceptionVeicoli;

public class MacchinaImpl extends VeicoloAbstract {

	public void add(String parametri) {
		
		System.out.println("tao");

		String[] p = parametri.split(",");
		
		Map<String, String> map = new HashMap<String, String>();
		
		for (String it : p) {
			String[] elem = it.split("=");
			map.put(elem[0].trim(), elem[1].trim());
		}
				
		for(String it : map.keySet()) {
			System.out.println("key: " + it + " valore: " + map.get(it));
		}
		
		checkInteger("ruote", map);
		
	}
	
	public void checkInteger(String s, Map<String, String> m) {
		Integer intero = null;
		
		try {
		    intero = Integer.parseInt(m.get(s));
		} catch (NumberFormatException e) {
			throw new ExceptionVeicoli("Formato di ruote non valido");
		}
	}

}