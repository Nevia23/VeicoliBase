package com.betacom.veicoli.process;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.betacom.veicoli.exceptions.ExceptionVeicoli;
import com.betacom.veicoli.interfaces.OperationInterface;
import com.betacom.veicoli.singleton.ArchivioVeicoli;
import com.betacom.veicoli.utils.Utilities;

public class StartVeicolo {

	public final static int OPERATION = 0;
	public final static int TIPO_VEICOLO = 1;
	public final static int PARAMETERS = 2;
	private final static String PATH_OPERATIONS = "com.betacom.veicoli.operations";

	public void execute(List<String> param) {
		System.out.println("Begin StartVeicolo");
		System.out.println("Numero parametri: " + param.size());
		
		for (String par : param) {
		    System.out.println("PARAMETRO: " + par);
		}

//		Map<String, VeicoloAbstract> impl = new HashMap<>();
//		Utilities.readFile("assets/tipi.txt").forEach(it -> {
//			String[] el = it.split("=", 2);
//			try {
//				impl.put(el[0].trim(),
//						(VeicoloAbstract) Class.forName(el[1].trim()).getDeclaredConstructor().newInstance());
//			} catch (Exception e) {
//				System.out.println("Impossibile caricare il tipo " + el[0] + ": " + e.getMessage());
//			}
//		});

		ArchivioVeicoli.getInstance().loadConstant("assets/costanti.txt");

		for (String para : param) {
			try {
				String[] inp = para.split(";");
				String operation = inp[OPERATION].trim();
				String tipo = inp.length > TIPO_VEICOLO ? inp[TIPO_VEICOLO].trim() : "";
				String parametri = inp.length > PARAMETERS ? inp[PARAMETERS].trim() : "";

				OperationInterface op = (OperationInterface) loadProcess(operation);
				op.setParametri(tipo, parametri);
				executeOperation(op);

			} catch (Exception e) {
				System.out.println("Error found: " + e.getMessage());
			}
		}
		
//		for (String para : param) {
//			String[] inp = para.split(";");
//			String operation = inp[OPERATION].trim();
//
//			if ("list".equalsIgnoreCase(operation)) {
//				System.out.println(">>>" + operation);
//				new ListImpl().list();
//			} else {
//				if (impl.containsKey(inp[TIPO_VEICOLO])) {
//					if (operation.equalsIgnoreCase("add")) {
//						VeicoloAbstract veicolo = impl.get(inp[TIPO_VEICOLO]);
//						try {
//							System.out.println(inp[PARAMETERS]);
//							veicolo.add(operation, inp[PARAMETERS]);
//
//						} catch (Exception e) {
//							System.err.println("Error found:" + e.getMessage());
//						}
//					}
//
//				} else
//					System.err.println("il tipo " + inp[TIPO_VEICOLO] + " non é previsto.");
//			}
//
//		}
	}

	private Object loadProcess(String name) throws Exception {
		try {
			Class<?> cl = Class.forName(PATH_OPERATIONS + "." + Utilities.buildClassName(name));
			return cl.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new ExceptionVeicoli("Operazione non prevista: " + name);
		}
	}
	
	private void executeOperation(OperationInterface op) throws Exception {
		try {
			Method method = op.getClass().getMethod("execute");
			method.invoke(op);
		} catch (InvocationTargetException e) {
			throw new ExceptionVeicoli(e.getCause().getMessage());
		} catch (NoSuchMethodException e) {
			throw new ExceptionVeicoli("metodo execute non trovato");
		} catch (Exception e) {
			throw new ExceptionVeicoli("errore di reflection: " + e.getMessage());
		}
	}
}