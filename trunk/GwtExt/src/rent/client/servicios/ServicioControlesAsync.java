package rent.client.servicios;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfaz correspondiente a GWT con los metodos implementados del lado del
 * servidor de GWT
 */
public interface ServicioControlesAsync {

	public void getStringDesdeXML(AsyncCallback callback);
}
