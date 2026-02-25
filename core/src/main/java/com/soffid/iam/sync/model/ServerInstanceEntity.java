//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity ServerInstanceEntity
 */

public abstract class ServerInstanceEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
	/**
	 * Gets value for attribute id
	 */
	public java.lang.Long getId() {
		return this.id;
	}
	/**
	 * Sets value for attribute id
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	/**
	 * Attribute name
	 */
	private java.lang.String name;
	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * Attribute url
	 */
	private java.lang.String url;
	/**
	 * Gets value for attribute url
	 */
	public java.lang.String getUrl() {
		return this.url;
	}
	/**
	 * Sets value for attribute url
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	/**
	 * Attribute server
	 */
	private com.soffid.iam.sync.model.ServerEntity server;
	/**
	 * Gets value for attribute server
	 */
	public com.soffid.iam.sync.model.ServerEntity getServer() {
		return this.server;
	}
	/**
	 * Sets value for attribute server
	 */
	public void setServer(com.soffid.iam.sync.model.ServerEntity server) {
		this.server = server;
	}
	/**
	 * Attribute lastSeen
	 */
	private java.util.Date lastSeen;
	/**
	 * Gets value for attribute lastSeen
	 */
	public java.util.Date getLastSeen() {
		return this.lastSeen;
	}
	/**
	 * Sets value for attribute lastSeen
	 */
	public void setLastSeen(java.util.Date lastSeen) {
		this.lastSeen = lastSeen;
	}
	/**
	 * Attribute auth
	 */
	private java.lang.String auth;
	/**
	 * Gets value for attribute auth
	 */
	public java.lang.String getAuth() {
		return this.auth;
	}
	/**
	 * Sets value for attribute auth
	 */
	public void setAuth(java.lang.String auth) {
		this.auth = auth;
	}
	/**
	 * Attribute tasks
	 */
	private int tasks;
	/**
	 * Gets value for attribute tasks
	 */
	public int getTasks() {
		return this.tasks;
	}
	/**
	 * Sets value for attribute tasks
	 */
	public void setTasks(int tasks) {
		this.tasks = tasks;
	}
	/**
	 * Returns <code>true</code> if the argument is an ServerInstanceEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ServerInstanceEntity))
		{
			return false;
		}
		final ServerInstanceEntity that = (ServerInstanceEntity)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
