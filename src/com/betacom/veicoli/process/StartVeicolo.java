package com.betacom.veicoli.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.betacom.veicoli.services.BiciImpl;
import com.betacom.veicoli.services.MacchinaImpl;
import com.betacom.veicoli.services.MotoImpl;
import com.betacom.veicoli.services.VeicoloAbstract;

public class StartVeicolo {
	
	public void execute(List<String> param) {
		System.out.println("Begin StartVeicolo");
		
		// decodificare riga per riga i parametri
		List<Map<String, String>> res = new ArrayList<Map<String,String>>();
		
		for (String it : param) {
			Map<String, String> map = new HashMap<String, String>();

			String[] s = it.split(";");
			
			String[] elem = new String[3];
			elem[0] = "operazione";
			elem[1] = "veicolo";
			elem[2] = "stringa";
			
			Integer n = 0;
			
			for (String z : s) {
				map.put(elem[n], z);
				n++;
			}
			
			res.add(map);
						
			if ("add".equals(map.get("operazione")))
				doOperation(map.get("veicolo"), map.get("stringa"));
			
			if (map.get("operazione") == "list") {
				// eseguo ListImpl
			}
		}
		
//		System.out.println("Result ListArray trasformato in Array di Map");
//		
//		for (Map<String, String> it : res) {
//			System.out.println("--------- Mappa " + res.indexOf(it) + " ---------");
//			
//			for (Entry<String, String> valore : it.entrySet()) {
//				System.out.println("key:" + valore.getKey() + " valore: " + valore.getValue());
//			}
//		}
		
		
		// eseguire i diversi servizi

	}
	
	public void doOperation(String s, String param) {
		
		if("macchina".equals(s)) {
			VeicoloAbstract a = new MacchinaImpl();
			a.add(param);
		}
		
		if("moto".equals(s)) {
			VeicoloAbstract a = new MotoImpl();
			a.add(param);
		}
		
		if("bici".equals(s)) {
			VeicoloAbstract a = new BiciImpl();
			a.add(param);
		}
	}
}
