/**
 * 
 */
package net.sf.hibernate4gwt.sample.server.gwt;


import java.util.List;

import net.sf.hibernate4gwt.core.HibernateBeanManager;
import net.sf.hibernate4gwt.gwt.HibernateRemoteService;
import net.sf.hibernate4gwt.sample.client.message.MessageRemote;
import net.sf.hibernate4gwt.sample.domain.Message;
import net.sf.hibernate4gwt.sample.server.ApplicationContext;
import net.sf.hibernate4gwt.sample.server.service.IMessageService;

/**
 * Message remote service
 * @author bruno.marchesson
 *
 */
public class MessageRemoteImpl extends HibernateRemoteService
							   implements MessageRemote
{
	//----
	// Attributes
	//----
	/**
	 * Serialisation ID																																	
	 */
	private static final long serialVersionUID = -7208813584472295675L;

	/**
	 * The message Service
	 */
	private IMessageService messageService;
	
	//----
	// Properties
	//----
	/**
	 * @return the messageService
	 */
	public IMessageService getMessageService() {
		return messageService;
	}

	/**
	 * @param messageService the messageService to set
	 */
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	//-------------------------------------------------------------------------
	//
	// Constructor
	//
	//-------------------------------------------------------------------------
	/**
	 * Constructor
	 */
	public MessageRemoteImpl()
	{
		setBeanManager((HibernateBeanManager)ApplicationContext.getInstance().getBean("hibernateBeanManager"));
		messageService = (IMessageService) ApplicationContext.getInstance().getBean(IMessageService.NAME);
	}

	//-------------------------------------------------------------------------
	//
	// Team management
	//
	//-------------------------------------------------------------------------
	/**
	 * Return the last posted messages
	 */
	public List<Message> getAllMessages(int startIndex, int maxResult)
	{
	//	Load the message
	//
		return messageService.loadAllMessage(startIndex, maxResult);
		
	}
	
	/**
	 * Count all posted messages
	 */
	public int countAllMessages()
	{
		return messageService.countAllMessage();
	}
	
	/**
	 * Return the last message
	 */
	public Message getLastMessage()
	{
	//	Load the last message
	//
		return messageService.loadLastMessage();
	}
	
	/**
	 * @return the Message loaded with all associations
	 */
	public Message getMessageDetails(Message message)
	{
	//	Call the service
	//
		return messageService.loadMessageDetails(message);
	}
	
	/**
	 * Save the argument message
	 */
	public Message saveMessage(Message message)
	{
	//	Save the message
	//
		return messageService.saveMessage(message);
	}
	
	/**
	 * Delete the argument message
	 */
	public void deleteMessage(Message message)
	{
	//	Delete the message
	//
		messageService.deleteMessage(message);
	}
}
