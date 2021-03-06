/**
 * 
 */
package net.sf.hibernate4gwt.sample.server;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Global application context
 * @author bruno.marchesson
 *
 */
public class ApplicationContext
{
	//----
	// Constants
	//----
	/**
	 * The stateless configuration file
	 */
	private static final String STATELESS_CONTEXT_FILE = 
		"net/sf/hibernate4gwt/sample/resources/applicationContext.xml";
	
	
	//----
	// Attributes
	//----
	/**
	 * Unique instance of the singleton
	 */
	private static ApplicationContext _instance;
	
	/**
	 * Spring context
	 */
	protected GenericApplicationContext _springContext;

	//----
	// Properties
	//----
	/**
	 * @return the unique of the instance
	 */
	public static synchronized final ApplicationContext getInstance()
	{
		if (_instance == null)
		{
			_instance = new ApplicationContext();
		}
		return _instance;
	}

	//-------------------------------------------------------------------------
	//
	// Constructor
	//
	//-------------------------------------------------------------------------
	/**
	 * Constructor
	 */
	protected ApplicationContext()
	{
		initContextFile();
	}
	
	//-------------------------------------------------------------------------
	//
	// Public interface
	//
	//-------------------------------------------------------------------------
	/**
	 * Get a bean from its name
	 */
	public Object getBean(String beanName)
	{
		return _springContext.getBean(beanName);
	}
	
	//-------------------------------------------------------------------------
	//
	// Internal method
	//
	//-------------------------------------------------------------------------
	/**
	 * Init Spring context
	 */
	private void initContextFile()
	{
		_springContext = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(_springContext);
		xmlReader.loadBeanDefinitions(new ClassPathResource(STATELESS_CONTEXT_FILE));
		_springContext.refresh();
	}
}
