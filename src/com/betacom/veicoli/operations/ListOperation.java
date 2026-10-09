package com.betacom.veicoli.operations;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.betacom.veicoli.MainVeicoli;
import com.betacom.veicoli.exceptions.ExceptionVeicoli;
import com.betacom.veicoli.interfaces.OperationInterface;
import com.betacom.veicoli.models.Veicoli;
import com.betacom.veicoli.singleton.ArchivioVeicoli;
import com.betacom.veicoli.utils.Utilities;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListOperation implements OperationInterface {
	private String parametri = "";

	@Override
	public void setParametri(String tipo, String parametri) {
		this.parametri = parametri;
	}

	@Override
	public void execute() throws Exception {
		if (parametri.isBlank()) {
			printAll();
			return;
		}

		Map<String, String> p = Utilities.decodeParams(parametri);

		String type = p.get("type");

		if (type == null || "all".equalsIgnoreCase(type))
			printAll();
		else if ("filter".equalsIgnoreCase(type))
			printFilter(p);
		else
			throw new ExceptionVeicoli("type non previsto: " + type);
	}

	private void printAll() throws IOException {
		stampa(v -> true, "ALL");
	}

	private void printFilter(Map<String, String> p) throws IOException {
		Predicate<Veicoli> predicate = v -> true;
		if (p.get("marca") != null)
			predicate = predicate.and(v -> p.get("marca").equalsIgnoreCase(v.getMarca()));
		if (p.get("colore") != null)
			predicate = predicate.and(v -> p.get("colore").equalsIgnoreCase(v.getColore()));
		if (p.get("modello") != null)
			predicate = predicate.and(v -> p.get("modello").equalsIgnoreCase(v.getModello()));
		if (p.get("tipoVeicolo") != null)
			predicate = predicate.and(v -> p.get("tipoVeicolo").equalsIgnoreCase(v.getTipoVeicolo()));
		stampa(predicate, parametri);
	}

	private void stampa(Predicate<Veicoli> filtro, String titolo) {
	    List<Veicoli> listaFiltrata =
	            ArchivioVeicoli.getInstance().getListaVeicoli()
	                    .stream()
	                    .filter(filtro)
	                    .collect(Collectors.toList());

	    System.out.println("**** Elenco veicoli " + titolo + " ****");

	    listaFiltrata.forEach(v -> System.out.println(v));
	}
}
