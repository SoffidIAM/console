//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject SystemAccessControl
 **/
public class SystemAccessControl

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute system

	 */
	private java.lang.String system;

	/**
	 * Attribute enabled

	 */
	private java.lang.Boolean enabled;

	/**
	 * Attribute controlAcces
	 * Acces control rules

	 */
	private java.util.List<com.soffid.iam.iga.api.AccessControl> controlAcces;

	public SystemAccessControl()
	{
	}

	public SystemAccessControl(java.lang.String system, java.lang.Boolean enabled, java.util.List<com.soffid.iam.iga.api.AccessControl> controlAcces)
	{
		super();
		this.system = system;
		this.enabled = enabled;
		this.controlAcces = controlAcces;
	}

	public SystemAccessControl(SystemAccessControl otherBean)
	{
		this(otherBean.system, otherBean.enabled, otherBean.controlAcces);
	}

	/**
	 * Gets value for attribute system
	 */
	public java.lang.String getSystem() {
		return this.system;
	}

	/**
	 * Sets value for attribute system
	 */
	public void setSystem(java.lang.String system) {
		this.system = system;
	}

	/**
	 * Gets value for attribute enabled
	 */
	public java.lang.Boolean getEnabled() {
		return this.enabled;
	}

	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(java.lang.Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets value for attribute controlAcces
	 */
	public java.util.List<com.soffid.iam.iga.api.AccessControl> getControlAcces() {
		return this.controlAcces;
	}

	/**
	 * Sets value for attribute controlAcces
	 */
	public void setControlAcces(java.util.List<com.soffid.iam.iga.api.AccessControl> controlAcces) {
		this.controlAcces = controlAcces;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[system: ");
		b.append (this.system);
		b.append (", enabled: ");
		b.append (this.enabled);
		b.append (", controlAcces: ");
		b.append (this.controlAcces);
		b.append ("]");
		return b.toString();
	}

}
