//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity HostAliasEntity
 */

public abstract class HostAliasEntity {

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
	 * Attribute alias
	 */
	private java.lang.String alias;
	/**
	 * Gets value for attribute alias
	 */
	public java.lang.String getAlias() {
		return this.alias;
	}
	/**
	 * Sets value for attribute alias
	 */
	public void setAlias(java.lang.String alias) {
		this.alias = alias;
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
	 * Returns <code>true</code> if the argument is an HostAliasEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof HostAliasEntity))
		{
			return false;
		}
		final HostAliasEntity that = (HostAliasEntity)object;
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
