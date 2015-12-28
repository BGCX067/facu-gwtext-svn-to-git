package rent.client.data;

import rent.client.paneles.PanelManager;
import rent.server.model.DataDpto;

import com.google.gwt.user.client.Element;
import com.gwtext.client.core.DomHelper;
import com.gwtext.client.core.ExtElement;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.VerticalLayout;

/**
 * Example that illustrates simple buttons.
 */
public class ImageChooserSample extends PanelManager {

	private ImageChooser	ic;

	public Panel getPanel() {

		if (panelGeneral == null) {
			panelGeneral = new Panel();

			Panel verticalPanel = new Panel();
			verticalPanel.setLayout(new VerticalLayout(50));
			MemoryProxy dataProxy = new MemoryProxy(getData());
			RecordDef recordDef = RecordDefs.getRecordDpto();

			ArrayReader reader = new ArrayReader(recordDef);
			final Store store = new Store(dataProxy, reader, true);
			store.load();

			/**********/

			if (ic == null) {
				ic = new ImageChooser("Image Chooser", 900, 600, store);
			}

			ic.show(new ImageChooserCallback() {
				public void imagenSeleccionadaParaComparador(DataDpto data) {
					Element el = DomHelper.append("comparador", Format.format("<img src='{0}' width='100px' height='80px'style='margin:20px;visibility:hidden;'/>", data
							.getRutaArchivo()));
					ExtElement extEl = new ExtElement(el);
					extEl.show(true).frame();
					System.out.println("agregando imagen!");
				}

			});

			verticalPanel.add(ic);

			panelGeneral.add(verticalPanel);
		}
		return panelGeneral;
	}

	private Object[][] getData() {
		return new Object[][] {
				new Object[] { new Integer(1),"Lindo dpto en belgrano", "Lindo dpto en belgrano", "Belgrano", new Integer(70), new Integer(3),
						"images/dptos/d1.jpg", new Long(0) },
						new Object[] { new Integer(2),"Acojedor", "a", "b", "c", new Integer(3), "images/dptos/d2.jpg", new Long(0) },
						new Object[] { new Integer(3),"2 ambientes a estrenar", "a", "b", "c", new Integer(2), "images/dptos/d3.jpg", new Long(0) },
						new Object[] { new Integer(4),"Dueño alquila", "a", "b", "c", new Integer(2), "images/dptos/d4.jpg", new Long(0) },
						new Object[] { new Integer(5),"Frente a subte", "a", "b", "c", new Integer(1), "images/dptos/d5.jpg", new Long(0) }

		};
	}
}