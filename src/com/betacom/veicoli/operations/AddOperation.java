package com.betacom.veicoli.operations;

import java.util.HashMap;
import java.util.Map;

import com.betacom.veicoli.exceptions.ExceptionVeicoli;
import com.betacom.veicoli.interfaces.OperationInterface;
import com.betacom.veicoli.services.VeicoloAbstract;
import com.betacom.veicoli.singleton.ArchivioVeicoli;
import com.betacom.veicoli.utils.Utilities;

public class AddOperation implements OperationInterface {

	private String tipo;
	private String parametri;
	
	@Override
	public void setParametri(String tipo, String parametri) {
		this.tipo = tipo;
		this.parametri = parametri;
	}

	@Override
	public void execute() throws Exception {
		Map<String, VeicoloAbstract> impl = new HashMap<>();
		Utilities.readFile("assets/tipi.txt").forEach(it -> {
			String[] el = it.split("=", 2);
			try {
				impl.put(el[0].trim(),
						(VeicoloAbstract) Class.forName(el[1].trim()).getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				System.out.println("Impossibile caricare il tipo " + el[0] + ": " + e.getMessage());
			}
		});

		if (!impl.containsKey(tipo))
			throw new ExceptionVeicoli("il tipo " + tipo + " non è previsto");

		impl.get(tipo).add("add", parametri);
		
		Utilities.export(ArchivioVeicoli.getInstance().getListaVeicoli());
	}
}
