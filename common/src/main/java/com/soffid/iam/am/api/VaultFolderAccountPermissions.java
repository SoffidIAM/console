//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject VaultFolderAccountPermissions
 **/
public class VaultFolderAccountPermissions

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute account

	 */
	private com.soffid.iam.base.api.Account account;

	/**
	 * Attribute permissions

	 */
	private java.util.Vector<com.soffid.iam.base.api.AccountAccessLevelEnum> permissions;

	public VaultFolderAccountPermissions()
	{
	}

	public VaultFolderAccountPermissions(com.soffid.iam.base.api.Account account, java.util.Vector<com.soffid.iam.base.api.AccountAccessLevelEnum> permissions)
	{
		super();
		this.account = account;
		this.permissions = permissions;
	}

	public VaultFolderAccountPermissions(VaultFolderAccountPermissions otherBean)
	{
		this(otherBean.account, otherBean.permissions);
	}

	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.api.Account getAccount() {
		return this.account;
	}

	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.api.Account account) {
		this.account = account;
	}

	/**
	 * Gets value for attribute permissions
	 */
	public java.util.Vector<com.soffid.iam.base.api.AccountAccessLevelEnum> getPermissions() {
		return this.permissions;
	}

	/**
	 * Sets value for attribute permissions
	 */
	public void setPermissions(java.util.Vector<com.soffid.iam.base.api.AccountAccessLevelEnum> permissions) {
		this.permissions = permissions;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[account: ");
		b.append (this.account);
		b.append (", permissions: ");
		b.append (this.permissions);
		b.append ("]");
		return b.toString();
	}

}
