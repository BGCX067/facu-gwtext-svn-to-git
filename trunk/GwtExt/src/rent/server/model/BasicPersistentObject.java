package rent.server.model;

import java.util.Date;
import net.sf.hibernate4gwt.pojo.gwt.LazyGwtPojo;

/**
 * Bean básico de persistencia. Todas las clases persistentes deben extender
 * de esta clase.
 */
public class BasicPersistentObject extends LazyGwtPojo {

	private static final long serialVersionUID = 1L;
	
	protected Date fechaCreacion;
	protected Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof BasicPersistentObject)) {
			return false;
		}
		
		BasicPersistentObject bpo = (BasicPersistentObject) obj;
		return this.id.equals(bpo.getId());
	}
	
	public int hashCode() {
		if(this.id == null)
			return -1;
		
		return this.id.hashCode();
	}
	
}
