//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject AgentDescriptor
 **/
public class AgentDescriptor

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
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute className

	 */
	private java.lang.String className;

	/**
	 * Attribute userInterface

	 */
	private byte[] userInterface;

	/**
	 * Attribute enableAccessControl

	 */
	private boolean enableAccessControl;

	/**
	 * Attribute authoritativeSource

	 */
	private boolean authoritativeSource;

	/**
	 * Attribute enableAttributeMapping

	 */
	private boolean enableAttributeMapping;

	/**
	 * Attribute enableObjectTriggers

	 */
	private boolean enableObjectTriggers;

	/**
	 * Attribute service

	 */
	private boolean service;

	/**
	 * Attribute properties

	 */
	private java.util.List<com.soffid.iam.base.api.AgentProperty> properties = new java.util.LinkedList();

	public AgentDescriptor()
	{
	}

	public AgentDescriptor(java.lang.Long id, java.lang.String description, java.lang.String className, byte[] userInterface, boolean enableAccessControl, boolean authoritativeSource, boolean enableAttributeMapping, boolean enableObjectTriggers, boolean service, java.util.List<com.soffid.iam.base.api.AgentProperty> properties)
	{
		super();
		this.id = id;
		this.description = description;
		this.className = className;
		this.userInterface = userInterface;
		this.enableAccessControl = enableAccessControl;
		this.authoritativeSource = authoritativeSource;
		this.enableAttributeMapping = enableAttributeMapping;
		this.enableObjectTriggers = enableObjectTriggers;
		this.service = service;
		this.properties = properties;
	}

	public AgentDescriptor(java.lang.Long id, java.lang.String description, java.lang.String className, byte[] userInterface, boolean enableAccessControl, boolean authoritativeSource, boolean enableAttributeMapping, boolean enableObjectTriggers, boolean service)
	{
		super();
		this.id = id;
		this.description = description;
		this.className = className;
		this.userInterface = userInterface;
		this.enableAccessControl = enableAccessControl;
		this.authoritativeSource = authoritativeSource;
		this.enableAttributeMapping = enableAttributeMapping;
		this.enableObjectTriggers = enableObjectTriggers;
		this.service = service;
	}

	public AgentDescriptor(AgentDescriptor otherBean)
	{
		this(otherBean.id, otherBean.description, otherBean.className, otherBean.userInterface, otherBean.enableAccessControl, otherBean.authoritativeSource, otherBean.enableAttributeMapping, otherBean.enableObjectTriggers, otherBean.service, otherBean.properties);
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
	 * Gets value for attribute className
	 */
	public java.lang.String getClassName() {
		return this.className;
	}

	/**
	 * Sets value for attribute className
	 */
	public void setClassName(java.lang.String className) {
		this.className = className;
	}

	/**
	 * Gets value for attribute userInterface
	 */
	public byte[] getUserInterface() {
		return this.userInterface;
	}

	/**
	 * Sets value for attribute userInterface
	 */
	public void setUserInterface(byte[] userInterface) {
		this.userInterface = userInterface;
	}

	/**
	 * Gets value for attribute enableAccessControl
	 */
	public boolean isEnableAccessControl() {
		return this.enableAccessControl;
	}

	/**
	 * Sets value for attribute enableAccessControl
	 */
	public void setEnableAccessControl(boolean enableAccessControl) {
		this.enableAccessControl = enableAccessControl;
	}

	/**
	 * Gets value for attribute authoritativeSource
	 */
	public boolean isAuthoritativeSource() {
		return this.authoritativeSource;
	}

	/**
	 * Sets value for attribute authoritativeSource
	 */
	public void setAuthoritativeSource(boolean authoritativeSource) {
		this.authoritativeSource = authoritativeSource;
	}

	/**
	 * Gets value for attribute enableAttributeMapping
	 */
	public boolean isEnableAttributeMapping() {
		return this.enableAttributeMapping;
	}

	/**
	 * Sets value for attribute enableAttributeMapping
	 */
	public void setEnableAttributeMapping(boolean enableAttributeMapping) {
		this.enableAttributeMapping = enableAttributeMapping;
	}

	/**
	 * Gets value for attribute enableObjectTriggers
	 */
	public boolean isEnableObjectTriggers() {
		return this.enableObjectTriggers;
	}

	/**
	 * Sets value for attribute enableObjectTriggers
	 */
	public void setEnableObjectTriggers(boolean enableObjectTriggers) {
		this.enableObjectTriggers = enableObjectTriggers;
	}

	/**
	 * Gets value for attribute service
	 */
	public boolean isService() {
		return this.service;
	}

	/**
	 * Sets value for attribute service
	 */
	public void setService(boolean service) {
		this.service = service;
	}

	/**
	 * Gets value for attribute properties
	 */
	public java.util.List<com.soffid.iam.base.api.AgentProperty> getProperties() {
		return this.properties;
	}

	/**
	 * Sets value for attribute properties
	 */
	public void setProperties(java.util.List<com.soffid.iam.base.api.AgentProperty> properties) {
		this.properties = properties;
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
		b.append (", description: ");
		b.append (this.description);
		b.append (", className: ");
		b.append (this.className);
		b.append (", userInterface: ");
		b.append (this.userInterface);
		b.append (", enableAccessControl: ");
		b.append (this.enableAccessControl);
		b.append (", authoritativeSource: ");
		b.append (this.authoritativeSource);
		b.append (", enableAttributeMapping: ");
		b.append (this.enableAttributeMapping);
		b.append (", enableObjectTriggers: ");
		b.append (this.enableObjectTriggers);
		b.append (", service: ");
		b.append (this.service);
		b.append (", properties: ");
		b.append (this.properties);
		b.append ("]");
		return b.toString();
	}

}
