package rent.client.servicios;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ServicioPopUp extends RemoteService {

	public List getInformacionDeFotos(int idDpto);
}
