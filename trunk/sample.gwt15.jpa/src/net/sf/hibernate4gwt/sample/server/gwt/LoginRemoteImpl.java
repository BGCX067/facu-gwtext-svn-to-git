/**
 * 
 */
package net.sf.hibernate4gwt.sample.server.gwt;

import net.sf.hibernate4gwt.core.HibernateBeanManager;
import net.sf.hibernate4gwt.gwt.HibernateRemoteService;
import net.sf.hibernate4gwt.sample.client.login.LoginRemote;
import net.sf.hibernate4gwt.sample.domain.User;
import net.sf.hibernate4gwt.sample.server.ApplicationContext;
import net.sf.hibernate4gwt.sample.server.service.IIdentificationService;

/**
 * Login remote service
 * @author bruno.marchesson
 *
 */
public class LoginRemoteImpl extends HibernateRemoteService
							 implements LoginRemote
{
	//----
	// Attributes
	//----
	/**
	 * Serialisation ID																																	
	 */
	private static final long serialVersionUID = -5399410538322914497L;
	
	/**
	 * The message Service
	 */
	private IIdentificationService identificationService;
	
	//----
	// Properties
	//----
	/**
	 * @return the identification  Service
	 */
	public IIdentificationService getIdentificationService() {
		return identificationService;
	}

	/**
	 * @param identifcationService the identification Service to set
	 */
	public void setIdentifitcationService(IIdentificationService identificationService) {
		this.identificationService = identificationService;
	}

	//-------------------------------------------------------------------------
	//
	// Constructor
	//
	//-------------------------------------------------------------------------
	/**
	 * Constructor
	 */
	public LoginRemoteImpl()
	{
		setBeanManager((HibernateBeanManager)ApplicationContext.getInstance().getBean("hibernateBeanManager"));
		identificationService = (IIdentificationService) ApplicationContext.getInstance().getBean(IIdentificationService.NAME);
	}

	//-------------------------------------------------------------------------
	//
	// Team management
	//
	//-------------------------------------------------------------------------
	/**
	 * Authenticate the user
	 */
	public User authenticate(String login, String password)
	{
	//	Authenticate the user
	//
		return  identificationService.authenticate(login, password);
	}
}
