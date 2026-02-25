//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity AccountSnapshotEntity
 */

public abstract class AccountSnapshotEntity {

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
	 * Attribute data
	 */
	private java.sql.Blob data;
	/**
	 * Gets value for attribute data
	 */
	public java.sql.Blob getData() {
		return this.data;
	}
	/**
	 * Sets value for attribute data
	 */
	public void setData(java.sql.Blob data) {
		this.data = data;
	}
	/**
	 * Attribute accounts

	 */
	private java.util.Collection<com.soffid.iam.base.model.AccountEntity> accounts =  new java.util.HashSet<com.soffid.iam.base.model.AccountEntity>();
	/**
	 * Gets value for attribute accounts
	 */
	public java.util.Collection<com.soffid.iam.base.model.AccountEntity> getAccounts() {
		return this.accounts;
	}
	/**
	 * Sets value for attribute accounts
	 */
	public void setAccounts(java.util.Collection<com.soffid.iam.base.model.AccountEntity> accounts) {
		this.accounts = accounts;
	}
	/**
	 * Returns <code>true</code> if the argument is an AccountSnapshotEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AccountSnapshotEntity))
		{
			return false;
		}
		final AccountSnapshotEntity that = (AccountSnapshotEntity)object;
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
