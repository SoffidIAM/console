//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject VaultFolderPermissions
 **/
public class VaultFolderPermissions

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute vaultId

	 */
	private java.lang.Long vaultId;

	/**
	 * Attribute grantee
	 * List of users / roles / groups

	 */
	private java.util.Vector<java.lang.Object> grantee;

	/**
	 * Attribute accounts
	 * List of accounts in the folder

	 */
	private java.util.Vector<com.soffid.iam.am.api.VaultFolderAccountPermissions> accounts;

	public VaultFolderPermissions()
	{
	}

	public VaultFolderPermissions(java.lang.Long vaultId, java.util.Vector<java.lang.Object> grantee, java.util.Vector<com.soffid.iam.am.api.VaultFolderAccountPermissions> accounts)
	{
		super();
		this.vaultId = vaultId;
		this.grantee = grantee;
		this.accounts = accounts;
	}

	public VaultFolderPermissions(VaultFolderPermissions otherBean)
	{
		this(otherBean.vaultId, otherBean.grantee, otherBean.accounts);
	}

	/**
	 * Gets value for attribute vaultId
	 */
	public java.lang.Long getVaultId() {
		return this.vaultId;
	}

	/**
	 * Sets value for attribute vaultId
	 */
	public void setVaultId(java.lang.Long vaultId) {
		this.vaultId = vaultId;
	}

	/**
	 * Gets value for attribute grantee
	 */
	public java.util.Vector<java.lang.Object> getGrantee() {
		return this.grantee;
	}

	/**
	 * Sets value for attribute grantee
	 */
	public void setGrantee(java.util.Vector<java.lang.Object> grantee) {
		this.grantee = grantee;
	}

	/**
	 * Gets value for attribute accounts
	 */
	public java.util.Vector<com.soffid.iam.am.api.VaultFolderAccountPermissions> getAccounts() {
		return this.accounts;
	}

	/**
	 * Sets value for attribute accounts
	 */
	public void setAccounts(java.util.Vector<com.soffid.iam.am.api.VaultFolderAccountPermissions> accounts) {
		this.accounts = accounts;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[vaultId: ");
		b.append (this.vaultId);
		b.append (", grantee: ");
		b.append (this.grantee);
		b.append (", accounts: ");
		b.append (this.accounts);
		b.append ("]");
		return b.toString();
	}

}
