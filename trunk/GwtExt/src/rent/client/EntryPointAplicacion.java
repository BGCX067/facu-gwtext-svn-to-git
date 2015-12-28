package rent.client;

import rent.client.data.ImageChooserSample;
import rent.client.paneles.PanelControles;
import rent.client.paneles.PanelInferior;
import rent.client.paneles.PanelMenu;

import com.google.gwt.core.client.EntryPoint;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

public class EntryPointAplicacion implements EntryPoint {

	public void onModuleLoad() {
		//create the main panel and assign it a BorderLayout
		Panel panelPrincipal = new Panel();
		panelPrincipal.setLayout(new BorderLayout());

		//voy a crear los paneles, los layouts
		//y agregarlos al panelPrincipal

		//Panel sur (info)
		BorderLayoutData southLayoutData = new BorderLayoutData(RegionPosition.SOUTH);
        southLayoutData.setSplit(true);
        southLayoutData.setMargins(new Margins(5, 5, 5, 5));

		//TODO cambiar uno por otro
		Panel panelInferiorInfo = new PanelControles().getPanel();
		//Panel panelInferiorInfo = new PanelInferior().getPanel();

		panelPrincipal.add(panelInferiorInfo, southLayoutData);

		//Panel Menu
		BorderLayoutData westLayoutData = new BorderLayoutData(RegionPosition.WEST);
		westLayoutData.setMargins(new Margins(5, 5, 0, 5));
		westLayoutData.setCMargins(new Margins(5, 5, 5, 5));
		westLayoutData.setMinSize(100);
		westLayoutData.setMaxSize(300);
		westLayoutData.setSplit(true);

		MenuManager menuManager = new MenuManager();
		Panel pIzq = new PanelMenu(menuManager).getPanel();
		panelPrincipal.add(pIzq, westLayoutData);

		//Panel Central
		BorderLayoutData centerLayoutData = new BorderLayoutData(RegionPosition.CENTER);
		centerLayoutData.setMargins(new Margins(5, 0, 5, 5));

		//Panel panelCentral = new CuadriculaDptosDataView(panelInferiorDatos).getPanel();

		Panel panelCentral = new ImageChooserSample().getPanel();

		Panel encapsuladorPanelCentral = new Panel();
		encapsuladorPanelCentral.setLayout(new FitLayout());
		encapsuladorPanelCentral.setBorder(false);
		encapsuladorPanelCentral.setBodyBorder(false);

		encapsuladorPanelCentral.add(panelCentral);

		panelPrincipal.add(encapsuladorPanelCentral, centerLayoutData);

		Viewport viewport = new Viewport(panelPrincipal);

	}

}
