//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ReconcileAccountAttributesEntity
 */

public abstract class ReconcileAccountAttributesEntity {

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
	 * Attribute account
	 */
	private com.soffid.iam.iga.model.ReconcileAccountEntity account;
	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntity getAccount() {
		return this.account;
	}
	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.iga.model.ReconcileAccountEntity account) {
		this.account = account;
	}
	/**
	 * Attribute attribute
	 */
	private java.lang.String attribute;
	/**
	 * Gets value for attribute attribute
	 */
	public java.lang.String getAttribute() {
		return this.attribute;
	}
	/**
	 * Sets value for attribute attribute
	 */
	public void setAttribute(java.lang.String attribute) {
		this.attribute = attribute;
	}
	/**
	 * Attribute value
	 */
	private java.lang.String value;
	/**
	 * Gets value for attribute value
	 */
	public java.lang.String getValue() {
		return this.value;
	}
	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	/**
	 * Attribute dateValue
	 */
	private java.util.Date dateValue;
	/**
	 * Gets value for attribute dateValue
	 */
	public java.util.Date getDateValue() {
		return this.dateValue;
	}
	/**
	 * Sets value for attribute dateValue
	 */
	public void setDateValue(java.util.Date dateValue) {
		this.dateValue = dateValue;
	}
	/**
	 * Returns <code>true</code> if the argument is an ReconcileAccountAttributesEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ReconcileAccountAttributesEntity))
		{
			return false;
		}
		final ReconcileAccountAttributesEntity that = (ReconcileAccountAttributesEntity)object;
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
