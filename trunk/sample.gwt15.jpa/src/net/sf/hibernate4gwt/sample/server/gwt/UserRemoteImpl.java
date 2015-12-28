/**
 * 
 */
package net.sf.hibernate4gwt.sample.server.gwt;

import java.util.List;

import net.sf.hibernate4gwt.core.HibernateBeanManager;
import net.sf.hibernate4gwt.gwt.HibernateRemoteService;
import net.sf.hibernate4gwt.sample.client.user.UserRemote;
import net.sf.hibernate4gwt.sample.domain.User;
import net.sf.hibernate4gwt.sample.server.ApplicationContext;
import net.sf.hibernate4gwt.sample.server.service.IIdentificationService;

/**
 * User remote service
 * @author bruno.marchesson
 *
 */
public class UserRemoteImpl extends HibernateRemoteService
							implements UserRemote
{
	//----
	// Attributes
	//----
	/**
	 * Serialisation ID																																	
	 */
	private static final long serialVersionUID = 5921199904102343567L;
	
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
	public UserRemoteImpl()
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
	 * Return the user list
	 */
	public List<User> getUserList()
	{
	//	Get the user list
	//
		return identificationService.loadUserList();
	}
}
