//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject ServerPlugin
 **/
public class ServerPlugin

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
	 * Attribute version

	 */
	private java.lang.String version;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute enabled

	 */
	private boolean enabled;

	/**
	 * Attribute author

	 */
	private java.lang.String author;

	/**
	 * Attribute deployed

	 */
	private java.util.Date deployed;

	/**
	 * Attribute modules

	 */
	private java.util.Collection<com.soffid.iam.base.api.ServerPluginModule> modules;

	public ServerPlugin()
	{
	}

	public ServerPlugin(java.lang.Long id, java.lang.String version, java.lang.String name, boolean enabled, java.lang.String author, java.util.Date deployed, java.util.Collection<com.soffid.iam.base.api.ServerPluginModule> modules)
	{
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.enabled = enabled;
		this.author = author;
		this.deployed = deployed;
		this.modules = modules;
	}

	public ServerPlugin(java.lang.Long id, java.lang.String version, java.lang.String name, boolean enabled)
	{
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.enabled = enabled;
	}

	public ServerPlugin(ServerPlugin otherBean)
	{
		this(otherBean.id, otherBean.version, otherBean.name, otherBean.enabled, otherBean.author, otherBean.deployed, otherBean.modules);
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
	 * Gets value for attribute version
	 */
	public java.lang.String getVersion() {
		return this.version;
	}

	/**
	 * Sets value for attribute version
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
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
	 * Gets value for attribute enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets value for attribute author
	 */
	public java.lang.String getAuthor() {
		return this.author;
	}

	/**
	 * Sets value for attribute author
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}

	/**
	 * Gets value for attribute deployed
	 */
	public java.util.Date getDeployed() {
		return this.deployed;
	}

	/**
	 * Sets value for attribute deployed
	 */
	public void setDeployed(java.util.Date deployed) {
		this.deployed = deployed;
	}

	/**
	 * Gets value for attribute modules
	 */
	public java.util.Collection<com.soffid.iam.base.api.ServerPluginModule> getModules() {
		return this.modules;
	}

	/**
	 * Sets value for attribute modules
	 */
	public void setModules(java.util.Collection<com.soffid.iam.base.api.ServerPluginModule> modules) {
		this.modules = modules;
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
		b.append (", version: ");
		b.append (this.version);
		b.append (", name: ");
		b.append (this.name);
		b.append (", enabled: ");
		b.append (this.enabled);
		b.append (", author: ");
		b.append (this.author);
		b.append (", deployed: ");
		b.append (this.deployed);
		b.append (", modules: ");
		b.append (this.modules);
		b.append ("]");
		return b.toString();
	}

}
