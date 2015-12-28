package rent.server.fabrica;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;



/**
 * Clase que se encarga de fabricar objetos. Utiliza Guice. A esta clase se le
 * deben pedir las instancias de las clases necesarias siempre que esten
 * definidas.
 */
public class FabricaObjetos {
	private static boolean isStandalone = false;

	private static Injector injector = null;
	private static Object mutex = new Object();

	private static Injector getInjector() {
		if (injector == null) {
			synchronized (mutex) {
				if (injector == null) {
					createInjector();
				}
			}
		}
		return injector;
	}

	private static void createInjector() {
		ModuloServicios serviceModule = new ModuloServicios();

		List modules = new ArrayList();
		modules.add(serviceModule);
		modules.add(new ModuloPersistencia(isStandalone));
		injector = Guice.createInjector(modules);

		serviceModule.injectInterceptors(injector);
	}

	public static <T> T get(Class<T> type) {
		return getInjector().getInstance(type);
	}
	
	public static void setStandalone(){
		isStandalone = true;
		System.out.println("Seteado para aplicación StandAlone");
	}
}
