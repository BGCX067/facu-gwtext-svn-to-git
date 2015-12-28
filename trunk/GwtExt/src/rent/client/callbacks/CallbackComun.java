package rent.client.callbacks;

import rent.client.excepciones.ExcepcionServidor;



/**
 * Clase abstracta que define la accion por defecto cuando la llamada
 * asincronica tira alguna excepcion
 */
public abstract class CallbackComun extends CallbackConEstado {
	public static final String DEFAULT_POPUP_STYLE = "default-popup";

	public abstract void onSuccessInternal(Object arg0);

	public void onFailureInternal(Throwable caught) {
		if(caught instanceof ExcepcionServidor)
			//PrincipalPanel.mostrarMessagePopUp(caught.getMessage(), DEFAULT_POPUP_STYLE);
			System.out.println("por el then: " + caught);
		else
			//PrincipalPanel.mostrarMessagePopUp("Problemas de red, intente nuevamente", DEFAULT_POPUP_STYLE);
			System.out.println("por el else: " + caught);
	}
}
