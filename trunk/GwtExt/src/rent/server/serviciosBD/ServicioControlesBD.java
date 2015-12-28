package rent.server.serviciosBD;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServicioControlesBD {

	public String getStringDesdeXML() {

		String fileName = "src/rent/public/Charts/Linear3.xml";

		try {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

		String s = null;

		StringBuffer strB = new StringBuffer();
		
	
			while (( s = entrada.readLine()) != null){
				strB.append(s);
			}
		
		entrada.close();
		// TODO . borrar
		System.out.println("todo bien");
		System.out.println("strB = ");
		System.out.println(strB.toString());
		return strB.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
