package net.sf.hibernate4gwt.sample.client.ui;

import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.FitLayout;

public class PanelPrincipal extends Panel{

	   public Panel getPanel() {  
	        Panel panel = new Panel();  
	        panel.setBorder(false);  
	        panel.setPaddings(15);  
	        panel.setLayout(new FitLayout());  
	  
	        Panel borderPanel = new Panel();  
	        borderPanel.setTitle("Use of BorderLayout, AccordionLayout and ColumnLayout");  
	        borderPanel.setLayout(new BorderLayout());  
	  
	        
	        final AccordionLayout accordion = new AccordionLayout();  
	        accordion.setAnimate(true);  
	  
	        Panel westPanel = new Panel();  
	        westPanel.setTitle("West");  
	        westPanel.setCollapsible(true);  
	        westPanel.setWidth(200);  
	        westPanel.setLayout(accordion);  
	  
	        Panel navigationPanel = new Panel();  
	       // navigationPanel.setHtml(getShortBogusMarkup());  
	        navigationPanel.setTitle("Navigation");  
	        navigationPanel.setAutoScroll(true);  
	        navigationPanel.setBorder(false);  
	        navigationPanel.setIconCls("folder-icon");  
	        westPanel.add(navigationPanel);  
	  
	        Panel settingsPanel = new Panel();  
	       /// settingsPanel.setHtml(getShortBogusMarkup());  
	        settingsPanel.setTitle("Settings");  
	        settingsPanel.setAutoScroll(true);  
	        settingsPanel.setBorder(false);  
	        settingsPanel.setIconCls("settings-icon");  
	        westPanel.add(settingsPanel);  
	  
	        BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);  
	        westData.setSplit(true);  
	        westData.setMinSize(175);  
	        westData.setMaxSize(400);  
	        westData.setMargins(new Margins(35, 5, 0, 5));  
	        westData.setCMargins(new Margins(35, 5, 5, 5));  
	        borderPanel.add(westPanel, westData);  
	  
	  
	        Panel centerPanel = new Panel();  
	        centerPanel.setAutoScroll(true);  
	        centerPanel.setLayout(new ColumnLayout());  
	  
//	        Panel firstPanel = new PaddedPanel(new Panel("A Panel", getShortBogusMarkup()), 5, 5, 0, 5);  
//	        
//	        centerPanel.add(firstPanel, new ColumnLayoutData(0.33));  
//	       
	        BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);  
	        centerData.setMargins(35, 0, 5, 5);  
	        borderPanel.add(centerPanel, centerData);  
	        panel.add(borderPanel);  
	  
	        new Viewport(panel);
			return panel;  
	    } 
}
