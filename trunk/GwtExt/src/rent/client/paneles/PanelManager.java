package rent.client.paneles;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.layout.FitLayout;

public abstract class PanelManager extends Panel {
	    
		//todos los retornos seran de este panel, al que se agregaran todos los creados
	    protected Panel panelGeneral;
	    
	    protected PanelManager() {
	        setTitle(getTitle());
	        setClosable(true);
	        setTopToolbar(new Toolbar());
	        setPaddings(20);
	        setLayout(new FitLayout());
	        setBorder(false);
	        setAutoScroll(true);
	        
	    }


	    public abstract Panel getPanel();


	    public void log(Object aLoguear){
	    	System.out.println(aLoguear);
	    }
	    
}
