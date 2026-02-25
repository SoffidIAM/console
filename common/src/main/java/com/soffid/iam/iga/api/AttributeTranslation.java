//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject AttributeTranslation
 **/
public class AttributeTranslation

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
	 * Attribute domain

	 */
	private java.lang.String domain;

	/**
	 * Attribute column1

	 */
	private java.lang.String column1;

	/**
	 * Attribute column2

	 */
	private java.lang.String column2;

	/**
	 * Attribute column3

	 */
	private java.lang.String column3;

	/**
	 * Attribute column4

	 */
	private java.lang.String column4;

	/**
	 * Attribute column5

	 */
	private java.lang.String column5;

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	/**
	 * Attribute createdBy


	 */
	private java.lang.String createdBy;

	/**
	 * Attribute updatedOn


	 */
	private java.util.Date updatedOn;

	/**
	 * Attribute updatedBy


	 */
	private java.lang.String updatedBy;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	public AttributeTranslation()
	{
	}

	public AttributeTranslation(java.lang.Long id, java.lang.String domain, java.lang.String column1, java.lang.String column2, java.lang.String column3, java.lang.String column4, java.lang.String column5, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.domain = domain;
		this.column1 = column1;
		this.column2 = column2;
		this.column3 = column3;
		this.column4 = column4;
		this.column5 = column5;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public AttributeTranslation(java.lang.String domain, java.lang.String column1)
	{
		super();
		this.domain = domain;
		this.column1 = column1;
	}

	public AttributeTranslation(AttributeTranslation otherBean)
	{
		this(otherBean.id, otherBean.domain, otherBean.column1, otherBean.column2, otherBean.column3, otherBean.column4, otherBean.column5, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute domain
	 */
	public java.lang.String getDomain() {
		return this.domain;
	}

	/**
	 * Sets value for attribute domain
	 */
	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}

	/**
	 * Gets value for attribute column1
	 */
	public java.lang.String getColumn1() {
		return this.column1;
	}

	/**
	 * Sets value for attribute column1
	 */
	public void setColumn1(java.lang.String column1) {
		this.column1 = column1;
	}

	/**
	 * Gets value for attribute column2
	 */
	public java.lang.String getColumn2() {
		return this.column2;
	}

	/**
	 * Sets value for attribute column2
	 */
	public void setColumn2(java.lang.String column2) {
		this.column2 = column2;
	}

	/**
	 * Gets value for attribute column3
	 */
	public java.lang.String getColumn3() {
		return this.column3;
	}

	/**
	 * Sets value for attribute column3
	 */
	public void setColumn3(java.lang.String column3) {
		this.column3 = column3;
	}

	/**
	 * Gets value for attribute column4
	 */
	public java.lang.String getColumn4() {
		return this.column4;
	}

	/**
	 * Sets value for attribute column4
	 */
	public void setColumn4(java.lang.String column4) {
		this.column4 = column4;
	}

	/**
	 * Gets value for attribute column5
	 */
	public java.lang.String getColumn5() {
		return this.column5;
	}

	/**
	 * Sets value for attribute column5
	 */
	public void setColumn5(java.lang.String column5) {
		this.column5 = column5;
	}

	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets value for attribute updatedOn
	 */
	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}

	/**
	 * Sets value for attribute updatedOn
	 */
	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * Gets value for attribute updatedBy
	 */
	public java.lang.String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * Sets value for attribute updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets value for attribute deletedOn
	 */
	public java.util.Date getDeletedOn() {
		return this.deletedOn;
	}

	/**
	 * Sets value for attribute deletedOn
	 */
	public void setDeletedOn(java.util.Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	/**
	 * Gets value for attribute deletedBy
	 */
	public java.lang.String getDeletedBy() {
		return this.deletedBy;
	}

	/**
	 * Sets value for attribute deletedBy
	 */
	public void setDeletedBy(java.lang.String deletedBy) {
		this.deletedBy = deletedBy;
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
		b.append (", domain: ");
		b.append (this.domain);
		b.append (", column1: ");
		b.append (this.column1);
		b.append (", column2: ");
		b.append (this.column2);
		b.append (", column3: ");
		b.append (this.column3);
		b.append (", column4: ");
		b.append (this.column4);
		b.append (", column5: ");
		b.append (this.column5);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", createdBy: ");
		b.append (this.createdBy);
		b.append (", updatedOn: ");
		b.append (this.updatedOn);
		b.append (", updatedBy: ");
		b.append (this.updatedBy);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append ("]");
		return b.toString();
	}

}
