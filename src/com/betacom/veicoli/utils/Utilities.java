package com.betacom.veicoli.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

}

//try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
//    String line = reader.readLine();
//    while (line != null) {
//        r.add(line);
//        line = reader.readLine();
//    }
//}