package net.sf.hibernate4gwt.sample.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import net.sf.hibernate4gwt.pojo.java5.LazyPojo;

/**
 * User Domain class for JAVA5 server
 */
@Entity
@Table(name="user")
public class User extends LazyPojo implements Serializable
{
	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 1058354709157710766L;
	
	// Fields
	private Integer id;
	private Integer version;
	
	private String login;
	private String firstName;
	private String lastName;
	private String password;
	
	private Set<Message> messageList;

	// Properties
	@Id
	@GeneratedValue
	@Column(name="ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name="LOGIN", nullable=false, length=45)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String surname) {
		this.login = surname;
	}

	@Column(name="FIRST_NAME", nullable=false, length=45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME", nullable=false, length=45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="PASSWORD", length=45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the messageList
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	public Set<Message> getMessageList() {
		return messageList;
	}

	/**
	 * @param messageList the messageList to set
	 */
	public void setMessageList(Set<Message> messageList) {
		this.messageList = messageList;
	}

	/*
	 * (non-Javadoc)
	 * @see net.sf.hibernate4gwt.testApplication.domain.IUser#addMessage(net.sf.hibernate4gwt.testApplication.domain.IMessage)
	 */
	public void addMessage(Message message)
	{
		message.setAuthor(this);
		
		// Create message list if needed
		if (messageList == null)
		{
			messageList = new HashSet<Message>();
		}
		messageList.add((Message)message);
	}

	/*
	 * (non-Javadoc)
	 * @see net.sf.hibernate4gwt.testApplication.domain.IUser#removeMessage(net.sf.hibernate4gwt.testApplication.domain.IMessage)
	 */
	public void removeMessage(Message message)
	{
		messageList.remove(message);
	}
	
	/**
	 * Equality function
	 */
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		else if (this == obj)
		{
			return true;
		}
		
		// ID comparison
		User other = (User) obj;
		return (id == other.getId());
	}
}
