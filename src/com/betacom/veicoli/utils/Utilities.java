package com.betacom.veicoli.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.betacom.veicoli.MainVeicoli;
import com.betacom.veicoli.models.Bici;
import com.betacom.veicoli.models.Macchina;
import com.betacom.veicoli.models.Moto;
import com.betacom.veicoli.models.Veicoli;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Utilities {

	/*
	 * Read sequential file
	 */
	public static List<String> readFile(String path) {
		List<String> r = new ArrayList<String>();

		try (Stream<String> lines = Files.lines(Path.of(path))) {
			r = lines.map(s -> s.trim()).filter(s -> !s.isEmpty()).filter(s -> !s.startsWith("#"))
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return r;
	}

	public static String buildClassName(String param) {
		String n = param.trim();
		return n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase() + "Operation";
	}
	
	public static void writeFile(String path, String inp, boolean mode) {
		try (FileWriter o = new FileWriter(path, mode)) {
			o.write(inp);
			o.write("\n");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void export(List<Veicoli> lista) throws IOException {
		ObjectMapper mapper = new ObjectMapper();	
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		File jsonFile = new File(MainVeicoli.PATH_OUTPUT);
		mapper.writeValue(jsonFile, lista);
	}
	
	public static List<Veicoli> importJson(File elenco) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();

	    List<Map<String, Object>> lM = mapper.readValue(
	            elenco,
	            new TypeReference<List<Map<String, Object>>>() {}
	    );

	    List<Veicoli> lista = new ArrayList<Veicoli>();

	    for (Map<String, Object> elemento : lM) {

	        String tipo = (String) elemento.get("tipoVeicolo");

	        String nomeClasse = tipo.substring(0, 1).toUpperCase()
	                + tipo.substring(1);

	        try {
	            Class<?> classe = Class.forName(
	                    "com.betacom.veicoli.models." + nomeClasse
	            );

	            Veicoli veicolo = (Veicoli) mapper.convertValue(
	                    elemento,
	                    classe
	            );

	            lista.add(veicolo);

	        } catch (ClassNotFoundException e) {
	            throw new IOException(
	                    "Tipo di veicolo non riconosciuto: " + tipo, e
	            );
	        }
	    }

	    return lista;
	}
	
	public static Map<String, String> decodeParams(String par) {
		String[] p = par.split(",");

		Map<String, String> map = Arrays.stream(p).map(s -> s.split("=", 2))
				.collect(Collectors.toMap(arr -> arr[0].trim(), arr -> arr[1].trim()));

		return map;
	}
}



//try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
//    String line = reader.readLine();
//    while (line != null) {
//        r.add(line);
//        line = reader.readLine();
//    }
//}