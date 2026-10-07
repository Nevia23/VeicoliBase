package com.betacom.veicoli.operations;

import com.betacom.veicoli.interfaces.OperationInterface;
import com.betacom.veicoli.singleton.ArchivioVeicoli;

public class ListOperation implements OperationInterface {

	@Override
	public void setParametri(String tipo, String parametri) {
	}

	@Override
	public void execute() throws Exception {
		list();
	}

	public void list() {
		System.out.println("**** Elenco veicoli  *****");
		ArchivioVeicoli.getInstance().getListaVeicoli().stream()
				.forEach(System.out::println);
	}

	public void printAll() {
		list();
	}
}
