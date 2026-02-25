//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ExternalNameEntity
 */

public abstract class ExternalNameEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute address
	 */
	private java.lang.String address;
	/**
	 * Gets value for attribute address
	 */
	public java.lang.String getAddress() {
		return this.address;
	}
	/**
	 * Sets value for attribute address
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
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
	 * Returns <code>true</code> if the argument is an ExternalNameEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ExternalNameEntity))
		{
			return false;
		}
		final ExternalNameEntity that = (ExternalNameEntity)object;
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
