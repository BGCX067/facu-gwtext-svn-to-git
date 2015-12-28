package rent.server.fabrica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import net.sf.hibernate4gwt.core.HibernateLazyManager;

import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.HibernateEntityManagerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletScopes;

/**
 * Configuración de la persistencia de la aplicacion.
 * 
 */
class ModuloPersistencia extends AbstractModule {
	protected EntityManagerFactory entityManagerFactory;
	protected boolean isStandalone;

	public ModuloPersistencia(boolean isStandalone) {
		this.isStandalone = isStandalone;
		entityManagerFactory = createEntityManagerFactory();
	}

	@Override
	protected void configure() {
		if(!isStandalone){
		bind(EntityManager.class).toProvider(createEntityManagerProvider()).in(
				ServletScopes.REQUEST);
		}else{
			bind(EntityManager.class).toProvider(createEntityManagerProvider()).in(
					Scopes.SINGLETON);			
		}
	}

	private Provider<EntityManager> createEntityManagerProvider() {
		return new Provider<EntityManager>() {
			public EntityManager get() {
				return entityManagerFactory.createEntityManager();
			}
		};
	}

	private EntityManagerFactory createEntityManagerFactory() {
		Ejb3Configuration c = new Ejb3Configuration()
				.configure("/hibernate/hibernate.cfg.xml");
		EntityManagerFactory emFactory = c.buildEntityManagerFactory();
				
		HibernateLazyManager.getInstance().setSessionFactory(((HibernateEntityManagerFactory) emFactory).getSessionFactory());
		return emFactory;
	}
}
