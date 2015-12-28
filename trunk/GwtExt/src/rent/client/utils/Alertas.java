package rent.client.utils;

import com.gwtext.client.widgets.MessageBox;

public class Alertas {
	
	public void alerta (String titulo, String mensaje) {
		MessageBox.alert(titulo, mensaje, new MessageBox.AlertCallback(){
			public void execute(){
				
			}
		});
	}
	
}
