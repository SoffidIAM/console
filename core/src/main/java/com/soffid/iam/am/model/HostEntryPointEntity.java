//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity HostEntryPointEntity
 */

public abstract class HostEntryPointEntity {

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
	 * Attribute host
	 */
	private com.soffid.iam.am.model.HostEntity host;
	/**
	 * Gets value for attribute host
	 */
	public com.soffid.iam.am.model.HostEntity getHost() {
		return this.host;
	}
	/**
	 * Sets value for attribute host
	 */
	public void setHost(com.soffid.iam.am.model.HostEntity host) {
		this.host = host;
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
	 * Returns <code>true</code> if the argument is an HostEntryPointEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof HostEntryPointEntity))
		{
			return false;
		}
		final HostEntryPointEntity that = (HostEntryPointEntity)object;
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
