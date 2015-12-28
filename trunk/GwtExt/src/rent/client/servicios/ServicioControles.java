package rent.client.servicios;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Interfaz correspondiente a GWT con los metodos implementados del lado del
 * servidor de GWT
 */
public interface ServicioControles extends RemoteService {

	public String getStringDesdeXML();
}
