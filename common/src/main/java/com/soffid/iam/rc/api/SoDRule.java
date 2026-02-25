//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject SoDRule
 **/
public class SoDRule

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
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute number

	 */
	private java.lang.Integer number;

	/**
	 * Attribute risk

	 */
	private com.soffid.iam.rc.api.SoDRisk risk;

	/**
	 * Attribute type
	 * Type of SoDRule

	 */
	private com.soffid.iam.rc.api.SodRuleType type;

	/**
	 * Attribute application

	 */
	private java.lang.String application;

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

	public SoDRule()
	{
	}

	public SoDRule(java.lang.Long id, java.lang.String name, java.lang.Integer number, com.soffid.iam.rc.api.SoDRisk risk, com.soffid.iam.rc.api.SodRuleType type, java.lang.String application, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.risk = risk;
		this.type = type;
		this.application = application;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public SoDRule(java.lang.String name, com.soffid.iam.rc.api.SoDRisk risk, java.lang.String application)
	{
		super();
		this.name = name;
		this.risk = risk;
		this.application = application;
	}

	public SoDRule(SoDRule otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.number, otherBean.risk, otherBean.type, otherBean.application, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets value for attribute number
	 */
	public java.lang.Integer getNumber() {
		return this.number;
	}

	/**
	 * Sets value for attribute number
	 */
	public void setNumber(java.lang.Integer number) {
		this.number = number;
	}

	/**
	 * Gets value for attribute risk
	 */
	public com.soffid.iam.rc.api.SoDRisk getRisk() {
		return this.risk;
	}

	/**
	 * Sets value for attribute risk
	 */
	public void setRisk(com.soffid.iam.rc.api.SoDRisk risk) {
		this.risk = risk;
	}

	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.rc.api.SodRuleType getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.rc.api.SodRuleType type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute application
	 */
	public java.lang.String getApplication() {
		return this.application;
	}

	/**
	 * Sets value for attribute application
	 */
	public void setApplication(java.lang.String application) {
		this.application = application;
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
		b.append (", name: ");
		b.append (this.name);
		b.append (", number: ");
		b.append (this.number);
		b.append (", risk: ");
		b.append (this.risk);
		b.append (", type: ");
		b.append (this.type);
		b.append (", application: ");
		b.append (this.application);
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
