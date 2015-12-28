package rent.server.serviciosImpl;

import rent.client.servicios.ServicioControles;
import rent.server.fabrica.FabricaObjetos;
import rent.server.serviciosBD.ServicioControlesBD;

/**
 * Implementacion de los metodos que ejecutan acciones que no tienen que ver con
 * la interfaz grafica. Aqui se puede usar codigo de java superior a 1.4
 */
public class ServicioControlesImpl extends ServicioRemotoImpl implements ServicioControles {

	private static final long	serialVersionUID	= 1L;

	public String getStringDesdeXML(){
		 
		ServicioControlesBD servicioControlesBD = FabricaObjetos.get(ServicioControlesBD.class);
		return servicioControlesBD.getStringDesdeXML();
	}
	
}
