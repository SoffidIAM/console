//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity MailListGroupMemberEntity
 */

public abstract class MailListGroupMemberEntity {

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
	 * Attribute mailList
	 * Mail list container
	 */
	private com.soffid.iam.iga.model.MailListEntity mailList;
	/**
	 * Gets value for attribute mailList
	 */
	public com.soffid.iam.iga.model.MailListEntity getMailList() {
		return this.mailList;
	}
	/**
	 * Sets value for attribute mailList
	 */
	public void setMailList(com.soffid.iam.iga.model.MailListEntity mailList) {
		this.mailList = mailList;
	}
	/**
	 * Attribute group
	 * Group included in mail list
	 */
	private com.soffid.iam.iga.model.GroupEntity group;
	/**
	 * Gets value for attribute group
	 */
	public com.soffid.iam.iga.model.GroupEntity getGroup() {
		return this.group;
	}
	/**
	 * Sets value for attribute group
	 */
	public void setGroup(com.soffid.iam.iga.model.GroupEntity group) {
		this.group = group;
	}
	/**
	 * Returns <code>true</code> if the argument is an MailListGroupMemberEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof MailListGroupMemberEntity))
		{
			return false;
		}
		final MailListGroupMemberEntity that = (MailListGroupMemberEntity)object;
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
