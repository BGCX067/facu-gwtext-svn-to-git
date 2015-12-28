package rent.server.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Intercepta un método y comprueba si es marcado como Transaccional, en caso positivo, ejecuta una
 * transaccion.
 */
public class InterceptorTransaccion implements MethodInterceptor {
	private static final Logger log = Logger.getLogger(InterceptorTransaccion.class);

	@Inject
	private Provider<EntityManager> entityManagerProvider; 
	
	public InterceptorTransaccion() {
		super();
	}
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object ret = null;

		EntityManager entityManager = entityManagerProvider.get();
		EntityTransaction transaction = entityManager.getTransaction();

		if (log.isDebugEnabled()) {
			log.debug("Inicio de la transacción: " + transaction);
		}
		transaction.begin();
		try {
			ret = invocation.proceed();
			transaction.commit();
			if (log.isDebugEnabled()) {
				log.debug("Transacción terminada correctamente");
			}
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			transaction.rollback();
			throw e;
		}

		return ret;
	}
}
