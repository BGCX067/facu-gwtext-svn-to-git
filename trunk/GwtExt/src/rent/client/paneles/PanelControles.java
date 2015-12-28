package rent.client.paneles;

import rent.client.callbacks.CallbackComun;
import rent.client.servicios.ServicioControles;
import rent.client.servicios.ServicioControlesAsync;
import rent.client.utils.Alertas;
import rent.client.utils.ServiciosRemotos;

import com.google.gwt.core.client.JavaScriptObject;
import com.gwtext.client.widgets.Panel;


public class PanelControles extends PanelManager {

	private Panel panelTemporal = new Panel();
	private Panel panelTemporal2 = new Panel();
	
	private String myInstanceField = "caca";
	
	//TODO . declarar un panel por cada control y un callback por cada uno
	// FIXME
	//mejor aun, q un solo callback traiga una lista de strings q seran los XMLs y que agregue a esos nuevos paneles
	
	protected ServicioControlesAsync servControlesAsync = (ServicioControlesAsync) ServiciosRemotos.obtenerInstancia()
	.obtenerServicio(ServicioControles.class); 

	
	public Panel getPanel() {
		if(panelGeneral == null){
			panelGeneral = new Panel();
			
			
			panelTemporal2.setId("sliderDiv1");
			
			panelTemporal2.setHtml("<iframe name='color-picker' id='color-picker' src='tt.html' frameborder='0'></iframe><br />");
			 
			
			//AsyncCallback callbackXMLChart = new CallBackXMLControles();
			//servControlesAsync.getStringDesdeXML(callbackXMLChart);
			
			//panelGeneral.add(panelTemporal);
			
			//AsyncCallback callbackXMLChart2 = new CallBackXMLControles2();
			//servControlesAsync.getStringDesdeXML(callbackXMLChart2);
			
			panelGeneral.add(panelTemporal2);
			
			
			bar(this);
			//bar();
			System.out.println("myS = " + myInstanceField);
			
			
			  			
			
		}
		return panelGeneral;
	}
	
	
	
	public native void bar(PanelControles x) /*-{
	
		
	    // Read instance field on this
	    //var val = document.getElementById('color-picker').contentDocument.test.attacedFieldValue.value;
		var val = "2";
		//hola();
		// Write instance field on x
	    x.@rent.client.paneles.PanelControles::myInstanceField = val + " and stuff";


	}-*/;

	
	
	
		

	/****************** CALLBACKS ***************************/
	
	protected class CallBackXMLControles extends CallbackComun {
		
		public void onSuccessInternal(Object result) {
			
			String XML = ((String) result).replaceAll("\"", "'");
			System.out.println("XML en callback = ");
			System.out.println(XML);
			String tipoGrafico = "HLinearGauge.swf";
			
			StringBuffer mensaje = new StringBuffer("<object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" codebase=\"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0\""
					+ "width=\"300\" height=\"100\" id=\""+tipoGrafico+"\" >"
					+ "<param name=\"movie\" 	value=\"Charts/"+tipoGrafico+"\" />"
					+ "<param name=\"FlashVars\" value=\"&dataXML="+XML+"\">"
					+ "<param name=\"quality\" 	value=\"high\" />"
					+ "<embed src=\"Charts/"+tipoGrafico+"\" flashVars=\"&dataXML="+XML+"\" quality=\"high\" width=\"300\" height=\"100\""
					+ " name=\""+tipoGrafico+"\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" />"
					+ "</object>");  
		
			
			panelTemporal.setTitle("Informaci&oacute;n del Departamento Seleccionado Con controles");
//			panelGeneral.setClosable(false);
//			panelGeneral.setId("panelControles");
//			panelGeneral.setCollapsible(true);
//			panelGeneral.setAutoScroll(true);
//			panelGeneral.setHeight(150);
//			panelGeneral.setPaddings(5);
//			
			StringBuffer script = new StringBuffer();
			
//			script.append("<script language='javascript'>" +
//					"//FC_ChartUpdated method is called when user has changed pointer value." +
//					"function FC_ChartUpdated(DOMId){" +
//					"//Check if DOMId is that of the chart we want" +
//					"	   if (DOMId==\""+tipoGrafico+"\"){" +
//					"         //Get reference to the chart" +
//					"         var chartRef = getChartFromId(DOMId);" +
//					"         //Get the current value" +
//					"         var pointerValue = chartRef.getData(1);" +
//					"         //You can also use getDataForId method as commented below, to get the pointer value." +
//					"         //var pointerValue = chartRef.getDataForId(\"CS\");" +
//					"         //Update display" +
//					"          window.alert('si') ;"+
//					"         var divToUpdate = document.getElementById('side-nav');" +
//					"         divToUpdate.innerHTML = '<span class='text'>Your satisfaction index: <B>\" + Math.round(pointerValue) + \"%</B></span>\";" +
//					"      }" +
//					"      else   window.alert('no') ;"+
//					"}" +
//					"</script>");
//			
			panelTemporal.setHtml(/*script.toString() + "  <br>  "  + */mensaje.toString());
			
			
			
		}
		
		
		public void onFailure(Object result) {
			new Alertas().alerta("Reporte","Fallo la operacion para generar el grafico.");
		}
		
	}
	protected class CallBackXMLControles2 extends CallbackComun {
		
		public void onSuccessInternal(Object result) {
			
			String XML = ((String) result).replaceAll("\"", "'");
			System.out.println("XML en callback = ");
			System.out.println(XML);
			String tipoGrafico = "HLinearGauge.swf";
			
			StringBuffer mensaje = new StringBuffer("<object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" codebase=\"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0\""
					+ "width=\"300\" height=\"100\" id=\""+tipoGrafico+"\" >"
					+ "<param name=\"movie\" 	value=\"Charts/"+tipoGrafico+"\" />"
					+ "<param name=\"FlashVars\" value=\"&dataXML="+XML+"\">"
					+ "<param name=\"quality\" 	value=\"high\" />"
					+ "<embed src=\"Charts/"+tipoGrafico+"\" flashVars=\"&dataXML="+XML+"\" quality=\"high\" width=\"300\" height=\"100\""
					+ " name=\""+tipoGrafico+"\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" />"
					+ "</object>");  
		
			
			panelTemporal2.setTitle("Informaci&oacute;n del Departamento Seleccionado Con controles");
//			panelGeneral.setClosable(false);
//			panelGeneral.setId("panelControles");
//			panelGeneral.setCollapsible(true);
//			panelGeneral.setAutoScroll(true);
//			panelGeneral.setHeight(150);
//			panelGeneral.setPaddings(5);
//			
			
			
			panelTemporal2.setHtml(mensaje.toString());
			
			
			
		}
		public void onFailure(Object result) {
			new Alertas().alerta("Reporte","Fallo la operacion para generar el grafico.");
		}
		
	}
}
