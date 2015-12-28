package rent.server.model;

import java.util.Date;

public class DataDpto extends BasicPersistentObject{
	
	private int idDpto = 0;
    private String presentacionCorta = null;
    private String descripcion = null;
    private String barrio = null;
    private int metrosCuadrados = 0;
    private int cantAmbientes = 0;
    private String  rutaArchivo = null;
    private Date fechaAlta; 
    private boolean activo = true;
    
   
	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getCantAmbientes() {
		return cantAmbientes;
	}

	public void setCantAmbientes(int cantAmbientes) {
		this.cantAmbientes = cantAmbientes;
	}

	public String getPresentacionCorta() {
		return presentacionCorta;
	}

	public void setPresentacionCorta(String presentacionCorta) {
		this.presentacionCorta = presentacionCorta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public int getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(int metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getIdDpto() {
		return idDpto;
	}

	public void setIdDpto(int idDpto) {
		this.idDpto = idDpto;
	}

	

	
	
    
	
}
