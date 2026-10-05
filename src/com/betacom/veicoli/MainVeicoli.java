package com.betacom.veicoli;

import java.util.ArrayList;
import java.util.List;

import com.betacom.veicoli.process.StartVeicolo;

public class MainVeicoli {
	
	/*
	 * parsing parametri
	 * controllare 1. parametri veicolo - controllare validità del valore del parametro (cat = una categoria prevista), alim
	 * 				  					  univocità della targa
	 * 									  campi numerici sono veramente numerici
	 * 			   2. veicolo specifico
	 * se va bene, inserire l'oggetto dentro una lista comune per tutti (unica con macchine, moto, bici...) (creazione d'un id progressivo -> il primo creato ha 1, il secondo 2... così poi posso fare delete)
	 * con la funzione list fare la list degli oggetti
	 */
	
	public static void main(String[] args) {
		
		List<String> param = new ArrayList<String>();
		param.add("add;macchina;ruote=4,alim=benzina,cat=strada,colore=bianco,marca=fiat,anno=2025,modello=500,porte=4,targa=el234gx,cc=1200");
		param.add("add;macchina;ruote=4,alim=diesel,cat=strada,colore=nero,marca=fiat,anno=2025,modello=500,porte=4,targa=el934gx,cc=1200");
		param.add("add;macchina;ruote=4,alim=benzina,cat=fuoristrada,colore=bianco,marca=fiat,anno=2025,modello=500,porte=4,targa=fi234kx,cc=1200");
		param.add("add;moto;ruote=2,alim=benzina,cat=strada,colore=nero,marca=Yamaha,anno=2025,modello=r1,targa=EL22239,cc=900");
		param.add("add;bici;ruote=2,alim=manuale,cat=strada,colore=nero,marca=Bianchi,anno=2025,modello=Grizl 5,marce=10,sospensione=senza,pieghevole=no");
		param.add("list");
		
		System.out.println("Start Veicoli");
		
		new StartVeicolo().execute(param);
	}

}
