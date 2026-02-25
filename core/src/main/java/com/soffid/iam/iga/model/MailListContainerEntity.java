//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity MailListContainerEntity
 */

public abstract class MailListContainerEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute contains
	 */
	private com.soffid.iam.iga.model.MailListEntity contains;
	/**
	 * Gets value for attribute contains
	 */
	public com.soffid.iam.iga.model.MailListEntity getContains() {
		return this.contains;
	}
	/**
	 * Sets value for attribute contains
	 */
	public void setContains(com.soffid.iam.iga.model.MailListEntity contains) {
		this.contains = contains;
	}
	/**
	 * Attribute pertains
	 */
	private com.soffid.iam.iga.model.MailListEntity pertains;
	/**
	 * Gets value for attribute pertains
	 */
	public com.soffid.iam.iga.model.MailListEntity getPertains() {
		return this.pertains;
	}
	/**
	 * Sets value for attribute pertains
	 */
	public void setPertains(com.soffid.iam.iga.model.MailListEntity pertains) {
		this.pertains = pertains;
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
	 * Returns <code>true</code> if the argument is an MailListContainerEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof MailListContainerEntity))
		{
			return false;
		}
		final MailListContainerEntity that = (MailListContainerEntity)object;
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
