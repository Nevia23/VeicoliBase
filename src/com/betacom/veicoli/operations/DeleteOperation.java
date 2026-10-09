package com.betacom.veicoli.operations;

import com.betacom.veicoli.interfaces.OperationInterface;
import com.betacom.veicoli.singleton.ArchivioVeicoli;
import com.betacom.veicoli.utils.Utilities;

public class DeleteOperation implements OperationInterface {

	private String parametri;
	
	@Override
	public void execute() throws Exception {
		String[] pezzi = parametri.split("=");
		Integer id = Integer.parseInt(pezzi[1].trim());
		ArchivioVeicoli.getInstance().remove(id);
		
		Utilities.export(ArchivioVeicoli.getInstance().getListaVeicoli());
	}

	@Override
	public void setParametri(String tipo, String parametri) {
		this.parametri = parametri;
	}

}
