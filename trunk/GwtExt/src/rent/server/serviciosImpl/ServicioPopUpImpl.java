package rent.server.serviciosImpl;

import java.util.List;

import rent.client.servicios.ServicioPopUp;
import rent.server.fabrica.FabricaObjetos;
import rent.server.serviciosBD.ServicioPopUpBD;

public class ServicioPopUpImpl extends ServicioRemotoImpl implements ServicioPopUp {

	private static final long	serialVersionUID	= 1L;

	public List getInformacionDeFotos(int idDpto){
		 
		ServicioPopUpBD servicioPopUpBD = FabricaObjetos.get(ServicioPopUpBD.class);
		return servicioPopUpBD.getInformacionDeFotos(idDpto);
	}

	
}
