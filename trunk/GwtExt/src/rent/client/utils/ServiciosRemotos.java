package rent.client.utils;

import java.util.HashMap;
import java.util.Map;

import rent.client.servicios.ServicioControles;
import rent.client.servicios.ServicioPopUp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Provee de los servicios asincronicos a la aplicacion
 */
public class ServiciosRemotos {
	private static ServiciosRemotos instancia = null;
	private static Map nombresServicios = null;
	private Map servicios = new HashMap();

	/**
	 * Crea el mapa y agrega las relaciones entre las interfases y los tokens.
	 * Aqui hay que agregar todas las nuevas interfases correspondientes a los
	 * nuevos RPC
	 */
	static {
		nombresServicios = new HashMap();
		nombresServicios.put(ServicioControles.class, "ServicioControles");
		nombresServicios.put(ServicioPopUp.class, "ServicioPopUp");
	}

	public ServiciosRemotos() {
		super();
	}

	/**
	 * Metodo que se comporta como singleton para poder trabajar con una unica
	 * instancia. Al ser un metodo estatico, se lo pide a la clase directamente
	 * 
	 * @return
	 */
	public static ServiciosRemotos obtenerInstancia() {
		if (instancia == null) {
			instancia = new ServiciosRemotos();
		}
		return instancia;
	}

	/**
	 * Este metodo es el que se invoca desde los paneles y se le pasa el .class
	 * de la interfase de la cual se quiere obtener la correspondiente
	 * asincronica. Si el sericio ya fue creado anteriormente, lo devuelve, sino
	 * lo crea.
	 * 
	 * @param clase
	 * @return
	 */
	public Object obtenerServicio(Class servicio) {
		Object servicioAsincronico = servicios.get(servicio);
		if (servicioAsincronico == null) {
			servicioAsincronico = iniciarServicio(servicio);
			servicios.put(servicio, servicioAsincronico);
		}
		return servicioAsincronico;
	}

	/**
	 * Inicia un nuevo servicio asincronico que se va a guardar en el mapa de
	 * servicios en el metodo obtenerServicio
	 * 
	 * @param clase
	 * @return
	 */
	private Object iniciarServicio(Class clase) {
		String nombreServicio = obtenerNombreServicio(clase);
		Object servicio = crearServicio(nombreServicio);
		ServiceDefTarget destinoServicio = (ServiceDefTarget) servicio;
		String direccionServicio = GWT.getModuleBaseURL() + nombreServicio;
		destinoServicio.setServiceEntryPoint(direccionServicio);
		return servicio;
	}

	/**
	 * Crea un nuevo servicio en base al nombre que le llega por parametro. A
	 * este metodo hay que agregarle todos los servicios nuevos que se crean y
	 * retornar por cada uno GWT.create(InterfaceServicio.class)
	 * 
	 * @param nombreServicio
	 * @return
	 */
	private Object crearServicio(String nombreServicio) {
		if (nombreServicio.equals("ServicioControles")) {
			return GWT.create(ServicioControles.class);
		}
		if (nombreServicio.equals("ServicioPopUp")) {
			return GWT.create(ServicioPopUp.class);
		}
		System.out.println("falta el servicio!!!!");
		return null;
	}

	/**
	 * Devuelve el token correspondiente al .class que le llega por parametro.
	 * 
	 * @param clase
	 * @return
	 */
	private String obtenerNombreServicio(Class clase) {
		return (String) nombresServicios.get(clase);
	}

}
