package varios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class FilterData {

	Logger logger = Logger.getLogger(Data.class.getName());

	public static void main(String[] args) {
		FilterData fl = new FilterData();
		fl.readFile("subir", "\"", "prueba");
//		String siguiente=fl.moduleEleven("100120190107037161180012001002000003817123456781");
//		System.out.println(siguiente);
	}

	int readFile(String fileDir, String replace, String lote) {
		File file = new File(fileDir);
		KeyDocument key;
		ArrayList<KeyDocument> llaves = new ArrayList<>();

		Scanner inputStream;
		int cantidad = 0;
		try {
			inputStream = new Scanner(file);

			while (inputStream.hasNext()) {
				key = new KeyDocument();
				String line = inputStream.next();
				key.setLlave(line.replace(replace, "").trim());

				if (key.getLlave().length() == 49) {
					key.setFecha(key.getLlave().substring(0, 8));
					key.setTipo(key.getLlave().substring(8, 10));
					key.setRuc(key.getLlave().substring(10, 23));
					key.setAmbiente(key.getLlave().substring(23));
					key.setSerie(key.getLlave().substring(24, 30));
					key.setSecuencial(key.getLlave().substring(30, 39));
					key.setCodigo(key.getLlave().substring(39, 47));
					key.setEmision(key.getLlave().substring(47));
					key.setVerificador(key.getLlave().substring(48));
					llaves.add(key);

				}

			}

			inputStream.close();
			Data dat = new Data("keys.db");

			for (KeyDocument llave : llaves) {

				String patron = verifyPatron(llave);
				if (patron.equals("sin patron")) {
					dat.insertarDocumento(llave, patron, lote, false);
				} else {
					dat.insertarDocumento(llave, patron, lote, true);

				}

				cantidad++;
				System.out.println(cantidad);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return cantidad;

	}

	String verifyPatron(KeyDocument key) {

		if (key.getCodigo().equals("00000000")) {
			return "00000000";
		} else if (key.getCodigo().equals("00000001")) {

			return "00000001";
		} else if (key.getCodigo().equals("12345678")) {

			return "12345678";
		} else if (key.getCodigo().equals(key.getSecuencial().substring(1))) {

			return "secuencia=codigo";
		} else if (key.getCodigo().equals(key.getRuc().substring(5))) {

			return "ruc";
		} else {
			return "sin patron";

		}

	}
	
	String moduleEleven(String key) {
		String retornar="";
		if(key.length()==48 ||key.length()==49) {
			retornar=key.substring(0, 48);
			retornar=retornar+(calModuleEleven(retornar));			
		}		
		return retornar;		
	}
	
	public int calModuleEleven(String key) {
		int pivote = 2;
		int verificateDigit = 0;
		for( String charString : new StringBuilder(key).reverse().toString().split("")) {
			pivote = pivote == 8 ? 2 : pivote;
			verificateDigit += Integer.parseInt(charString) * pivote;
			pivote++;
		}
		verificateDigit = 11 - verificateDigit % 11;
		if (verificateDigit == 10) {
			return 1;
		}
		else if(verificateDigit == 11) {
			return 0;
		}
		else {
			return verificateDigit;
		}
	}

}
