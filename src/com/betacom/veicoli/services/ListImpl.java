package com.betacom.veicoli.services;

import com.betacom.veicoli.singleton.ArchivioVeicoli;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListImpl {

	public void list() {
		System.out.println("**** Elenco veicoli  *****");
		
		ArchivioVeicoli.getInstance().getListaVeicoli().stream()
//		.filter(im -> im.getId() > 3)
//		.filter(im -> im.getAnnoProduzione() < 2000)
//		.filter(im -> "fuoristrada".equals(im.getCategoria())
		.forEach(im -> log.debug(im.toString()));
		
	}
}