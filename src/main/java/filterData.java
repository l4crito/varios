import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filterData {

	public static void main(String[] args) {
		filterData fl = new filterData();
		fl.readFile("C:\\Users\\Chritian\\eclipse-workspace\\varios/noventaytres.csv", "\"","noventa y tres mil");
	}

	void readFile(String fileDir, String replace,String lote) {
		File file = new File(fileDir);
		keyDocument key;
		ArrayList<keyDocument> llaves = new ArrayList<>();
		List<List<String>> lines = new ArrayList<>();
		Scanner inputStream;
		int cantidad = 0;
		try {
			inputStream = new Scanner(file);

			while (inputStream.hasNext()) {
				key = new keyDocument();
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
			Data  dat=new Data("C:\\Users\\Chritian\\eclipse-workspace\\varios\\keys.db");

			for (keyDocument llave : llaves) {
				
				String patron=verifyPatron(llave);
				if(!patron.equals("sin patron")) {
					dat.insertarDocumento(llave, patron, lote, true);					
				}else {
					dat.insertarDocumento(llave, patron, lote, false);	
					
				}
				
				cantidad++;
				System.out.println(cantidad);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	String verifyPatron(keyDocument key) {
		//System.out.println(key.getCodigo());
	if(key.getCodigo().equals("00000000")) {
		return "00000000";		
	}else
	if(key.getCodigo().equals("00000001")) {
			
		return "00000001";		
	}else
	if(key.getCodigo().equals("12345678")) {
		
		return "12345678";		
	}else	
	if(key.getCodigo().equals(key.getSecuencial().substring(1))) {
		
		return "secuencia=codigo";		
	}else	
	if(key.getCodigo().equals(key.getRuc().substring(5))) {	
		
		return "ruc";		
	}else
	{
		return "sin patron";
		
	}
	
		
	}

}
