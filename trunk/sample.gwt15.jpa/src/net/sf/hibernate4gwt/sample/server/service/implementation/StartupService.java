/**
 * 
 */
package net.sf.hibernate4gwt.sample.server.service.implementation;

import java.util.Date;

import net.sf.hibernate4gwt.sample.domain.Message;
import net.sf.hibernate4gwt.sample.domain.User;
import net.sf.hibernate4gwt.sample.server.dao.IUserDAO;
import net.sf.hibernate4gwt.sample.server.service.IStartupService;

/**
 * Service used to initialize the underlying data for demo application
 * @author bruno.marchesson
 *
 */
public class StartupService implements IStartupService
{
	//----
	// Attributes
	//----
	/**
	 * The User DAO
	 */
	private IUserDAO _userDAO;
	
	//----
	// Properties
	//----
	/**
	 * @return the userDAO
	 */
	public IUserDAO getUserDAO() {
		return _userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(IUserDAO userDAO) {
		_userDAO = userDAO;
	}
	
	//-------------------------------------------------------------------------
	//
	// Public interface
	//
	//-------------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see net.sf.hibernate4gwt.sample.server.service.implementation.IStartupService#isInitialized()
	 */
	public boolean isInitialized()
	{
		return (_userDAO.countAll() > 0);
	}
	
	/* (non-Javadoc)
	 * @see net.sf.hibernate4gwt.sample.server.service.implementation.IStartupService#initialize()
	 */
	public void initialize()
	{
	//	Create guest user (no password)
	//
		User guestUser = new User();
		guestUser.setLogin("guest");
		guestUser.setFirstName("No");
		guestUser.setLastName("name");
		
		// create welcome message
		Message guestMessage = new Message();
		guestMessage.setMessage("Welcome in hibernate4gwt sample application");
		guestMessage.setDate(new Date());
		guestUser.addMessage(guestMessage);
		
		// save user (message is cascaded)
		_userDAO.saveUser(guestUser);
		
	//	Create JUnit user
	//
		User junitUser = new User();
		junitUser.setLogin("junit");
		junitUser.setPassword("junit");
		junitUser.setFirstName("Unit");
		junitUser.setLastName("Test");
		
		// create message
		Message junitMessage = new Message();
		junitMessage.setMessage("JUnit first message");
		junitMessage.setDate(new Date());
		junitUser.addMessage(junitMessage);
		
		// save user (message is cascaded)
		_userDAO.saveUser(junitUser);
	}
}
