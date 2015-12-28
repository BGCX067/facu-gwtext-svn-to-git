package rent.client.callbacks;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;

public abstract class CallbackConEstado implements AsyncCallback {

	private static MessageBoxConfig configuracionMensaje = new MessageBoxConfig() {
		{
			setTitle("Cargando");
			setMsg("Por favor espere...");
			setWidth(240);
			setProgress(false);
			setClosable(false);
		}
	};

	public CallbackConEstado() {
		setLoading(true);
	}

	public final void onSuccess(Object object) {
		setLoading(false);
		onSuccessInternal(object);
	}

	public final void onFailure(Throwable throwable) {
		setLoading(false);
		onFailureInternal(throwable);
	}

	protected abstract void onSuccessInternal(Object object);

	protected abstract void onFailureInternal(Throwable throwable);

	private void setLoading(boolean isLoading) {
		if (isLoading) {
			MessageBox.show(configuracionMensaje);
		} else {
			MessageBox.hide();
		}
		// ExtElement panelOeste = Ext.get("west-panel");
		// ExtElement panelCentral = Ext.get("center-panel");
		// if (isLoading) {
		// if (panelOeste != null)
		// panelOeste.mask();
		// if (panelCentral != null)
		// panelCentral.mask();
		// } else {
		// if (panelOeste != null)
		// panelOeste.unmask();
		// if (panelCentral != null)
		// panelCentral.unmask();
		// }
	}

}
