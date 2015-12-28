package rent.server.persistencia;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import rent.server.fabrica.FabricaObjetos;



/**
 * Este filtro se asegura de cerrar la sesión de Hibernate al finalizar un request
 */
public class FiltroEntityManager implements Filter {
	public void init(FilterConfig filterConfig) {
		return;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} finally {
			ensureCloseSession();
		}
	}

	private void ensureCloseSession() {
		FabricaObjetos.get(EntityManager.class).close();
	}

	public void destroy() {
		return;
	}
}
