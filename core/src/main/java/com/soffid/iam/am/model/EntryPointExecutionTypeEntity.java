//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity EntryPointExecutionTypeEntity
 */

public abstract class EntryPointExecutionTypeEntity {

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
	 * Attribute mimeType
	 */
	private java.lang.String mimeType;
	/**
	 * Gets value for attribute mimeType
	 */
	public java.lang.String getMimeType() {
		return this.mimeType;
	}
	/**
	 * Sets value for attribute mimeType
	 */
	public void setMimeType(java.lang.String mimeType) {
		this.mimeType = mimeType;
	}
	/**
	 * Attribute template
	 */
	private java.lang.String template;
	/**
	 * Gets value for attribute template
	 */
	public java.lang.String getTemplate() {
		return this.template;
	}
	/**
	 * Sets value for attribute template
	 */
	public void setTemplate(java.lang.String template) {
		this.template = template;
	}
	/**
	 * Attribute javaClass
	 */
	private java.lang.String javaClass;
	/**
	 * Gets value for attribute javaClass
	 */
	public java.lang.String getJavaClass() {
		return this.javaClass;
	}
	/**
	 * Sets value for attribute javaClass
	 */
	public void setJavaClass(java.lang.String javaClass) {
		this.javaClass = javaClass;
	}
	/**
	 * Returns <code>true</code> if the argument is an EntryPointExecutionTypeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof EntryPointExecutionTypeEntity))
		{
			return false;
		}
		final EntryPointExecutionTypeEntity that = (EntryPointExecutionTypeEntity)object;
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
