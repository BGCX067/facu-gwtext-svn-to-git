package rent.client.paneles;

import rent.client.MenuManager;
import rent.client.utils.SwitcheadorTema;

import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarTextItem;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.tree.TreePanel;

public class PanelMenu extends PanelManager {
	MenuManager menuManager;
	
	public PanelMenu(MenuManager screenManager){
		this.menuManager = screenManager;
	}
	
	
	public Panel getPanel() {
		Panel westPanel = new Panel();
        westPanel.setId("side-nav");
        westPanel.setTitle("Showcase Explorer");
        westPanel.setLayout(new FitLayout());
        westPanel.setWidth(200);
        westPanel.setCollapsible(true);

        Toolbar toolbar = new Toolbar();
        toolbar.addFill();
        toolbar.addItem(new ToolbarTextItem("Select Theme "));
        toolbar.addSpacer();
        toolbar.addField(new SwitcheadorTema());
        westPanel.setTopToolbar(toolbar);
        
        TabPanel tabPanel = new TabPanel();
        tabPanel.setActiveTab(0);
        tabPanel.setDeferredRender(true);
        tabPanel.setTabPosition(Position.BOTTOM);
        TreePanel treePanel = menuManager.getTreeNav();

        tabPanel.add(treePanel);
        tabPanel.add(menuManager.getAccordionNav());
        westPanel.add(tabPanel);

        return westPanel;
	}

}
