//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject Tenant
 **/
public class Tenant

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
	 * Attribute enabled

	 */
	private boolean enabled = true;

	public Tenant()
	{
	}

	public Tenant(java.lang.Long id, java.lang.String name, java.lang.String description, boolean enabled)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}

	public Tenant(java.lang.String name, java.lang.String description, boolean enabled)
	{
		super();
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}

	public Tenant(Tenant otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.enabled);
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
		b.append (", enabled: ");
		b.append (this.enabled);
		b.append ("]");
		return b.toString();
	}

}
