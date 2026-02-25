//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity EntryPointExecutableEntity
 */

public abstract class EntryPointExecutableEntity {

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
	 * Attribute scope
	 */
	private java.lang.String scope;
	/**
	 * Gets value for attribute scope
	 */
	public java.lang.String getScope() {
		return this.scope;
	}
	/**
	 * Sets value for attribute scope
	 */
	public void setScope(java.lang.String scope) {
		this.scope = scope;
	}
	/**
	 * Attribute content
	 */
	private java.lang.String content;
	/**
	 * Gets value for attribute content
	 */
	public java.lang.String getContent() {
		return this.content;
	}
	/**
	 * Sets value for attribute content
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	/**
	 * Attribute entryPoint
	 */
	private com.soffid.iam.am.model.EntryPointEntity entryPoint;
	/**
	 * Gets value for attribute entryPoint
	 */
	public com.soffid.iam.am.model.EntryPointEntity getEntryPoint() {
		return this.entryPoint;
	}
	/**
	 * Sets value for attribute entryPoint
	 */
	public void setEntryPoint(com.soffid.iam.am.model.EntryPointEntity entryPoint) {
		this.entryPoint = entryPoint;
	}
	/**
	 * Attribute executionCode
	 */
	private java.lang.String executionCode;
	/**
	 * Gets value for attribute executionCode
	 */
	public java.lang.String getExecutionCode() {
		return this.executionCode;
	}
	/**
	 * Sets value for attribute executionCode
	 */
	public void setExecutionCode(java.lang.String executionCode) {
		this.executionCode = executionCode;
	}
	/**
	 * Returns <code>true</code> if the argument is an EntryPointExecutableEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof EntryPointExecutableEntity))
		{
			return false;
		}
		final EntryPointExecutableEntity that = (EntryPointExecutableEntity)object;
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
