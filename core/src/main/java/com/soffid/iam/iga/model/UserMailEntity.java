//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity UserMailEntity
 */

public abstract class UserMailEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute mailList
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
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
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
	 * Attribute start
	 */
	private java.util.Date start;
	/**
	 * Gets value for attribute start
	 */
	public java.util.Date getStart() {
		return this.start;
	}
	/**
	 * Sets value for attribute start
	 */
	public void setStart(java.util.Date start) {
		this.start = start;
	}
	/**
	 * Attribute end
	 */
	private java.util.Date end;
	/**
	 * Gets value for attribute end
	 */
	public java.util.Date getEnd() {
		return this.end;
	}
	/**
	 * Sets value for attribute end
	 */
	public void setEnd(java.util.Date end) {
		this.end = end;
	}
	/**
	 * Attribute disabled
	 */
	private java.lang.Boolean disabled;
	/**
	 * Gets value for attribute disabled
	 */
	public java.lang.Boolean getDisabled() {
		return this.disabled;
	}
	/**
	 * Sets value for attribute disabled
	 */
	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}
	/**
	 * Returns <code>true</code> if the argument is an UserMailEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof UserMailEntity))
		{
			return false;
		}
		final UserMailEntity that = (UserMailEntity)object;
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
