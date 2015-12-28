package rent.client.paneles;

import java.util.List;

import rent.client.callbacks.CallbackComun;
import rent.client.servicios.ServicioPopUp;
import rent.client.servicios.ServicioPopUpAsync;
import rent.client.utils.ServiciosRemotos;
import rent.server.model.DataDpto;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.core.DomHelper;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.ExtElement;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class PanelPopUpDpto extends PanelManager{

	private int idDpto = 0;
	private int			indexFotoActual	= 0;
	private List	listaFotos;
	private int			indexTope		= 0;
	final Button		botonSiguiente	= new Button("Siguiente");
	final Button		botonAnterior	= new Button("Anterior");
	Panel horizontalPanel = new Panel();
	Panel panel = new Panel();
	final Window window = new Window();
	Panel pImagen = new Panel();
	Panel infoFotoActual = new Panel();
	
	
	public PanelPopUpDpto(int idDpto) {
		this.idDpto = idDpto;
	}
	
	public Panel getPanel() {

		crearURLs();
		
		panel.setId("images-view");
		panel.setFrame(true);
		panel.setAutoHeight(true);
		panel.setLayout(new VerticalLayout(15));
		panel.setTitleCollapse(true);
		panel.setTitle("<center><b>Galeria de Imagenes del Departamento</b>");

		
		pImagen.setId("imagen");
		infoFotoActual.setId("infoFoto");
		
		botonAnterior.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				if (botonSiguiente.isDisabled())
					botonSiguiente.enable();

				//seteo el indice de imagen a buscar, respecto de la actual
				indexFotoActual--;

				Element el = DomHelper.overwrite("imagen", "<img src='" + getURL(indexFotoActual) + "' width='600px' height='600px'>");
				ExtElement extEl = new ExtElement(el);
				extEl.show(true).frame();
				
				Element el2 = DomHelper.overwrite("infoFoto", "<center>" + getDescripcion(indexFotoActual));
				ExtElement extEl2 = new ExtElement(el2);
				extEl2.show(true).frame();
				
				if (indexFotoActual <= 0)
					botonAnterior.disable();
			}
		});
		botonAnterior.disable();

		botonSiguiente.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				if (botonAnterior.isDisabled())
					botonAnterior.enable();

				//seteo el indice de imagen a buscar, respecto de la actual
				indexFotoActual++;

				Element el = DomHelper.overwrite("imagen", "<img src='" + getURL(indexFotoActual) + "' width='600px' height='600px'>");
				ExtElement extEl = new ExtElement(el);
				extEl.show(true).frame();
				
				Element el2 = DomHelper.overwrite("infoFoto", "<center>" + getDescripcion(indexFotoActual));
				ExtElement extEl2 = new ExtElement(el2);
				extEl2.show(true).frame();
				
				if (indexFotoActual >= indexTope)
					botonSiguiente.disable();

			}
		});

		horizontalPanel.setLayout(new HorizontalLayout(15));
		horizontalPanel.add(botonAnterior);
		horizontalPanel.add(pImagen);
		horizontalPanel.add(botonSiguiente);
		
		
		panel.add(horizontalPanel);
		panel.add(infoFotoActual);
		
		//genero el popup en si y agrego el contenido
		
		window.setTitle(".:: Detalles ::.");
		window.setIconCls("paste-icon");
		window.setMaximizable(true);
		window.setResizable(true);
		window.setLayout(new FitLayout());
		window.setWidth(800);
		window.setHeight(800);
		window.setModal(false);
		
		window.add(panel);
		
		return window;
	}

	private void crearURLs() {
		
		ServicioPopUpAsync servAsync = (ServicioPopUpAsync) ServiciosRemotos.obtenerInstancia()
		.obtenerServicio(ServicioPopUp.class); 

		AsyncCallback callbackFotos = new CallbackFotos();
		servAsync.getInformacionDeFotos(idDpto, callbackFotos);
	}

	private String getURL(int index) {
		return ((DataDpto) listaFotos.get(index)).getRutaArchivo();
	}
	
	private String getDescripcion(int index) {
		return ((DataDpto) listaFotos.get(index)).getDescripcion();
	}
	
	// ****************************************************************
	// CLASES CALLBACK
	// ****************************************************************
	
	protected class CallbackFotos extends CallbackComun {
		public void onSuccessInternal(Object result) {
			listaFotos = (List) result;
			indexTope = listaFotos.size()-1;
		
			pImagen.setHtml("<img src='" + getURL(0) + "' width='600px' height='600px'>");
			infoFotoActual.setHtml("<center>"+ getDescripcion(0));
			panel.add(infoFotoActual);
		}
	}
	
	
}
