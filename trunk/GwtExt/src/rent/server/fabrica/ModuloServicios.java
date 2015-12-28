package rent.server.fabrica;

import rent.server.persistencia.InterceptorTransaccion;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;


/**
 * Configura los modulos, crea y setes el interceptor que se encarga de manejar
 * una transaccion en la base de datos.
 */
class ModuloServicios extends AbstractModule {
	private InterceptorTransaccion transactionInterceptor = new InterceptorTransaccion();

	
	
	public void injectInterceptors(Injector injector) {
		injector.injectMembers(transactionInterceptor);
	}



	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		
	}
}
