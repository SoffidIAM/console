//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity EntryPointAccountEntity
 */

public abstract class EntryPointAccountEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
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
	 * Attribute authorizationlevel
	 */
	private java.lang.String authorizationlevel;
	/**
	 * Gets value for attribute authorizationlevel
	 */
	public java.lang.String getAuthorizationlevel() {
		return this.authorizationlevel;
	}
	/**
	 * Sets value for attribute authorizationlevel
	 */
	public void setAuthorizationlevel(java.lang.String authorizationlevel) {
		this.authorizationlevel = authorizationlevel;
	}
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
	 * Attribute account
	 */
	private com.soffid.iam.base.model.AccountEntity account;
	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.model.AccountEntity getAccount() {
		return this.account;
	}
	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.model.AccountEntity account) {
		this.account = account;
	}
	/**
	 * Returns <code>true</code> if the argument is an EntryPointAccountEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof EntryPointAccountEntity))
		{
			return false;
		}
		final EntryPointAccountEntity that = (EntryPointAccountEntity)object;
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
