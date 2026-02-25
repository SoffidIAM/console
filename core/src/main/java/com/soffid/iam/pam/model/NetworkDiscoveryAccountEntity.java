//
// (c) 2014 Soffid
//
//

package com.soffid.iam.pam.model;

/**
 *  Entity NetworkDiscoveryAccountEntity
 */

public abstract class NetworkDiscoveryAccountEntity {

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
	 * Attribute network
	 */
	private com.soffid.iam.am.model.NetworkEntity network;
	/**
	 * Gets value for attribute network
	 */
	public com.soffid.iam.am.model.NetworkEntity getNetwork() {
		return this.network;
	}
	/**
	 * Sets value for attribute network
	 */
	public void setNetwork(com.soffid.iam.am.model.NetworkEntity network) {
		this.network = network;
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
	 * Returns <code>true</code> if the argument is an NetworkDiscoveryAccountEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof NetworkDiscoveryAccountEntity))
		{
			return false;
		}
		final NetworkDiscoveryAccountEntity that = (NetworkDiscoveryAccountEntity)object;
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
