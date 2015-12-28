/*
 * GWT-Ext Widget Library
 * Copyright(c) 2007-2008, GWT-Ext.
 * licensing@gwt-ext.com
 * 
 * http://www.gwt-ext.com/license
 */
package rent.client.data;

import java.util.HashMap;

import rent.client.paneles.PanelPopUpDpto;
import rent.server.model.DataDpto;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.ExtElement;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.core.XTemplate;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.DataView;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.DataViewListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;


public class ImageChooser extends Panel {

    private Panel eastPanel;
    private Panel centerPanel;

    private TextField searchField;
    private Button okButton;
    private Button botonDetalles;
     
    
    // the view of the area displaying the different images
    private DataView view;

    // the callback once the ok is pressed. This indicates the caller what image was selected
    private ImageChooserCallback callback;

    // the data about the selected image passed to the callback method
    private DataDpto imageData;

    private HashMap imageMap;
    private Store store;

    
    
    public ImageChooser(String title, int minWidth, int minHeight, Store store) {
        imageMap = new HashMap();
        this.store = store;
        initMainPanel(title, minWidth, minHeight);
        createView();
        centerPanel.add(view);
        
        
    }

    public void show(ImageChooserCallback callback) {
        this.callback = callback;
        super.show();
    }

    private void initMainPanel(String title, int minWidth, int minHeight) {
        setLayout(new BorderLayout());
        setHeight(minHeight);
        setWidth(minWidth);
        setTitle(title);
        addClass("ychooser-dlg");

        eastPanel = new Panel();
        eastPanel.setId("east-panel");
        eastPanel.setCollapsible(true);
        eastPanel.setWidth(300);
        eastPanel.setPaddings(5);

        centerPanel = new Panel();
        centerPanel.setId("ychooser-view");
        centerPanel.setCollapsible(true);
        centerPanel.setAutoScroll(true);

        add(getToolbar(), new BorderLayoutData(RegionPosition.NORTH));
        add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));
        add(eastPanel, new BorderLayoutData(RegionPosition.EAST));

        agregarBotonComparar();
        agregarBotonDetalles();
    }

    private void agregarBotonComparar() {
        okButton = new Button("Agregar a comparador");
        okButton.disable();
        okButton.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                if (callback != null) {
                    // pass the image data to the caller
                    callback.imagenSeleccionadaParaComparador(imageData);
                }
            }
        });
        addButton(okButton);
    }
   
    
    private void agregarBotonDetalles() {
    	botonDetalles = new Button("Ver detalles");
        botonDetalles.disable();
        botonDetalles.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	 if (callback != null) {
                     final Panel popUp =  new PanelPopUpDpto(imageData.getIdDpto()).getPanel();
                 	 popUp.show();
                 }
            }
        });
        addButton(botonDetalles);
        botonDetalles.focus();
        
        
    }

    /**
     * This method creates the toolbar for the dialog.
     *
     * @return the toolbar just created to be added into the dialog
     */
    private Toolbar getToolbar() {
        Toolbar tb = new Toolbar();
        
      //create a Store using local array data
		final Store store = new SimpleStore(new String[]{"id", "cantAmbientes"}, Combos.getComboAmbientesDptos());
        store.load();

        final ComboBox cb = new ComboBox();
		cb.setForceSelection(true);
		cb.setMinChars(1);
		cb.setFieldLabel("Cantidad Ambientes");
		cb.setStore(store);
		cb.setDisplayField("cantAmbientes");
		cb.setMode(ComboBox.LOCAL);
		cb.setTriggerAction(ComboBox.ALL);
		cb.setEmptyText("Ingrese opcion");
		cb.setLoadingText("Cargando...");
		cb.setTypeAhead(true);
		cb.setSelectOnFocus(true);
		cb.setWidth(200);
        cb.setValueField("id");
        
        //this hides the dropdown besides the ComboBox field
        cb.setHideTrigger(false);

		cb.addListener(new ComboBoxListenerAdapter() {
			
			public void onSelect(ComboBox comboBox, Record record, int index) {
				System.out.println("ComboBox::onSelect('" + record.getAsString("cantAmbientes") + "')");
				System.out.println("id = " + record.getAsString("id"));
				
		        displayThumbs("cantAmbientes",record.getAsString("id"));
			}

		});

        
        searchField = new TextField();

        searchField.setId("ychooser-toolbar-searchfield");
        searchField.setMaxLength(60);
        searchField.setGrow(false);
        searchField.setSelectOnFocus(true);

        searchField.addListener(new FieldListenerAdapter() {

            /**
             * This method will be called when special characters are pressed.
             * This method is only interested in the enter key so that it can
             * load the images.  It simulates pressing the "Find" button.
             */
            public void onSpecialKey(Field field, EventObject e) {
                if (e.getKey() == EventObject.ENTER) {
                    displayThumbs("name",searchField.getValueAsString()); // load the images in the view
                }
            }
        });

        tb.addField(cb);
        
        tb.addField(searchField);

        ToolbarButton tbb = new ToolbarButton("Find");
        tbb.setIconCls("search-icon");
        tbb.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
                displayThumbs("name",searchField.getValueAsString());
            }
        });
        tb.addButton(tbb);
        
        
        tb.addButton(tbb);
        return tb;
    }

    /**
     * This method creates the two view for displaying the images.  The main
     * view is the one that displays all the images to select.  The second view
     * displays the selected images with information about the image.
     */
    private void createView() {
        
       
        String thumbTemplate[] = Templates.getThumbTemplate();
        String detailTemplate[] = Templates.getDetailTemplate();
        
		// compile the templates
        final XTemplate thumbsTemplate = new XTemplate(thumbTemplate );
        final XTemplate detailsTemplate = new XTemplate(detailTemplate);
        thumbsTemplate.compile();
        detailsTemplate.compile();

        // initialize the View using the thumb nail template
        view = new DataView("div.thumb-wrap") {
            public void prepareData(Data data) {
                DataDpto newImageData = null;
                String presentacionCorta = data.getProperty("presentacionCorta");
                String barrio  = data.getProperty("barrio");
                //FIXME aca lo puse parcial
                String ambientesString = data.getProperty("cantAmbientes");
                String idDptoStr = data.getProperty("idDpto");
                
                String rutaFotoPrincipal = data.getProperty("rutaFotoPrincipal");
                String dateString = Format.date(data.getProperty("fechaAlta"), "m/d/Y g:i a");
                data.setProperty("shortName", Format.ellipsis(data.getProperty("presentacionCorta"), 20));
                if (imageMap.containsKey(presentacionCorta)) {
                    newImageData = (DataDpto) imageMap.get(presentacionCorta);
                } else {
                    newImageData = new DataDpto();
                    imageMap.put(presentacionCorta, newImageData);
                }

                //FIXME aca arreglar esto si o si
              
              newImageData.setPresentacionCorta(presentacionCorta);
              newImageData.setRutaArchivo(rutaFotoPrincipal);
              newImageData.setIdDpto(Integer.parseInt(idDptoStr));
              //newImageData.setFechaAlta(dateString);
              
              newImageData.setCantAmbientes(Integer.parseInt(ambientesString));
              newImageData.setBarrio(barrio);
              

           }
        };
        view.setSingleSelect(true);
        view.setTpl(thumbsTemplate);
        view.setStore(store);
        view.setAutoHeight(true);
        view.setOverCls("x-view-over");

        // if there is no images that can be found, just output an message
        view.setEmptyText("<div style=\"padding:10px;\">No images match the specified filter</div>");
        view.addListener(new DataViewListenerAdapter() {

            /**
             * This method is called when a selection is made changing the previous
             * selection
             * @params view the view that this selection is for
             * @params selections a list of all selected items.  There should only be
             * one as we only allow 1 selection.
             */
            public void onSelectionChange(DataView component,
                                          Element[] selections) {
            	System.out.println("por aca");
                // The details Ext.Element
                ExtElement detailEl = eastPanel.getEl();

                // if there is a selection and show the details
                if (selections != null && selections.length > 0 && selections[0] != null) {
                    // enable the buttons, now there is a selection made
                    okButton.enable();
                    botonDetalles.enable();
                    
                    String id = DOM.getElementAttribute(selections[0], "id");
                    imageData = (DataDpto) imageMap.get(id);

                    detailEl.hide();
                    
                    //FIXME otra vez, arreglar esto
                  
                  NameValuePair vals[] = new NameValuePair[5];
                  vals[0] = new NameValuePair("presentacionCorta", imageData.getPresentacionCorta());
                  vals[1] = new NameValuePair("descripcion", imageData.getDescripcion());
                  vals[2] = new NameValuePair("barrio", imageData.getBarrio());
                  vals[3] = new NameValuePair("rutaFotoPrincipal", imageData.getRutaArchivo());
                  vals[4] = new NameValuePair("cantAmbientes", imageData.getCantAmbientes());
                  
                  //vals[5] = new NameValuePair("dateString", imageData.getLastModified());
                  String html = detailsTemplate.applyTemplate(vals);
                  detailEl.update(html);
                    detailEl.slideIn();
                } else {
                    // no selection means the ok button should be disabled and the detail
                    // area should be blanked out
                    okButton.disable();
                    botonDetalles.disable();
                    detailEl.update("");
                }
            }
        });
    }

    private void displayThumbs(String nombreCampoFiltro,String findStr) {
        if (findStr == null || findStr.equals("")) {
            store.clearFilter(true);
        } else {
        	store.filter(nombreCampoFiltro, findStr, true);
        }
        view.refresh();
    }

   
}