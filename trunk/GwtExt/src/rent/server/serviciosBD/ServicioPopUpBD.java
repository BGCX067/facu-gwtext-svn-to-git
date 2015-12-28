package rent.server.serviciosBD;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import rent.server.model.DataDpto;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ServicioPopUpBD {

	@Inject
	private Provider<EntityManager>	entityManagerProvider;

	public List getInformacionDeFotos(int idDpto) {

		Query q = entityManagerProvider.get().createQuery("from " + DataDpto.class.getName() + " where " + "idDpto = :idDpto");
		q.setParameter("idDpto", idDpto);

		return q.getResultList();
	}
}
