//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject VaultElement
 **/
public class VaultElement

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
	 * Attribute parentId

	 */
	private java.lang.Long parentId;

	/**
	 * Attribute type
	 * type can be account or folder

	 */
	private java.lang.String type;

	/**
	 * Attribute account
	 * Account

	 */
	private com.soffid.iam.base.api.Account account;

	/**
	 * Attribute folder
	 * Falder

	 */
	private com.soffid.iam.am.api.VaultFolder folder;

	public VaultElement()
	{
	}

	public VaultElement(java.lang.Long id, java.lang.Long parentId, java.lang.String type, com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.VaultFolder folder)
	{
		super();
		this.id = id;
		this.parentId = parentId;
		this.type = type;
		this.account = account;
		this.folder = folder;
	}

	public VaultElement(java.lang.String type)
	{
		super();
		this.type = type;
	}

	public VaultElement(VaultElement otherBean)
	{
		this(otherBean.id, otherBean.parentId, otherBean.type, otherBean.account, otherBean.folder);
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
	 * Gets value for attribute parentId
	 */
	public java.lang.Long getParentId() {
		return this.parentId;
	}

	/**
	 * Sets value for attribute parentId
	 */
	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets value for attribute type
	 */
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
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
	 * Gets value for attribute folder
	 */
	public com.soffid.iam.am.api.VaultFolder getFolder() {
		return this.folder;
	}

	/**
	 * Sets value for attribute folder
	 */
	public void setFolder(com.soffid.iam.am.api.VaultFolder folder) {
		this.folder = folder;
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
		b.append (", parentId: ");
		b.append (this.parentId);
		b.append (", type: ");
		b.append (this.type);
		b.append (", account: ");
		b.append (this.account);
		b.append (", folder: ");
		b.append (this.folder);
		b.append ("]");
		return b.toString();
	}

}
