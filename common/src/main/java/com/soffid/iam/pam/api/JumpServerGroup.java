//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject JumpServerGroup
 **/
public class JumpServerGroup

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute storeUrl

	 */
	private java.lang.String storeUrl;

	/**
	 * Attribute storeUserName

	 */
	private java.lang.String storeUserName;

	/**
	 * Attribute password

	 */
	private java.lang.String password;

	/**
	 * Attribute jumpServers

	 */
	private java.util.List<java.lang.String> jumpServers = new java.util.LinkedList<String>();

	public JumpServerGroup()
	{
	}

	public JumpServerGroup(java.lang.Long id, java.lang.String name, java.lang.String description, java.lang.String storeUrl, java.lang.String storeUserName, java.lang.String password, java.util.List<java.lang.String> jumpServers)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.storeUrl = storeUrl;
		this.storeUserName = storeUserName;
		this.password = password;
		this.jumpServers = jumpServers;
	}

	public JumpServerGroup(java.lang.String name, java.lang.String storeUrl, java.lang.String storeUserName, java.lang.String password)
	{
		super();
		this.name = name;
		this.storeUrl = storeUrl;
		this.storeUserName = storeUserName;
		this.password = password;
	}

	public JumpServerGroup(JumpServerGroup otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.storeUrl, otherBean.storeUserName, otherBean.password, otherBean.jumpServers);
	}

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
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets value for attribute storeUrl
	 */
	public java.lang.String getStoreUrl() {
		return this.storeUrl;
	}

	/**
	 * Sets value for attribute storeUrl
	 */
	public void setStoreUrl(java.lang.String storeUrl) {
		this.storeUrl = storeUrl;
	}

	/**
	 * Gets value for attribute storeUserName
	 */
	public java.lang.String getStoreUserName() {
		return this.storeUserName;
	}

	/**
	 * Sets value for attribute storeUserName
	 */
	public void setStoreUserName(java.lang.String storeUserName) {
		this.storeUserName = storeUserName;
	}

	/**
	 * Gets value for attribute password
	 */
	public java.lang.String getPassword() {
		return this.password;
	}

	/**
	 * Sets value for attribute password
	 */
	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	/**
	 * Gets value for attribute jumpServers
	 */
	public java.util.List<java.lang.String> getJumpServers() {
		return this.jumpServers;
	}

	/**
	 * Sets value for attribute jumpServers
	 */
	public void setJumpServers(java.util.List<java.lang.String> jumpServers) {
		this.jumpServers = jumpServers;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", storeUrl: ");
		b.append (this.storeUrl);
		b.append (", storeUserName: ");
		b.append (this.storeUserName);
		b.append (", password: ");
		b.append (this.password);
		b.append (", jumpServers: ");
		b.append (this.jumpServers);
		b.append ("]");
		return b.toString();
	}

}
