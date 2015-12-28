package rent.client.paneles;

import com.gwtext.client.widgets.HTMLPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class PanelInferior extends PanelManager {

	private Panel	panel;

	private String idChart = null; 
	
	public Panel getPanel() {
		if (panel == null) {
			panel = new Panel();

			Panel panelInferiorDatos = new Panel();
			panelInferiorDatos.setTitle("Comparador de Departamentos");
			panelInferiorDatos.setClosable(false);
			panelInferiorDatos.setId("panelInferior");
			panelInferiorDatos.setCollapsible(true);
			panelInferiorDatos.setAutoScroll(true);
			panelInferiorDatos.setHeight(220);
			panelInferiorDatos.setPaddings(5);
			
			
			panelInferiorDatos.setHtml("<span id=\"comparador\"></span>");

            
			Panel vp = new Panel();
			vp.setLayout(new VerticalLayout(15));
			
			vp.setTitle("vp");
			
			vp.add(new HTMLPanel("hola"));
			
			panelInferiorDatos.add(vp);
			panel.add(panelInferiorDatos);

		}

		return panel;
	}
	
	public native void FC_ChartUpdated() /*-{

	//Check if DOMId is that of the chart we want
      if (DOMId=="facu"){
         //Get reference to the chart
         var chartRef = getChartFromId(DOMId);
         //Get the current value
         var pointerValue = chartRef.getData(1);
         alert("facu");
         //You can also use getDataForId method as commented below, to get the pointer value.
         //var pointerValue = chartRef.getDataForId("CS");
         //Update display
         var divToUpdate = document.getElementById("contentDiv");
         divToUpdate.innerHTML = "<span class='text'>Your satisfaction index: <B>" + Math.round(pointerValue) + "%</B></span>";
      }	else  alert("facu2");


    }-*/; 

	
}
