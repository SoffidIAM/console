//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject Configuration
 **/
public class Configuration

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute value

	 */
	private java.lang.String value;

	/**
	 * Attribute networkName

	 */
	private java.lang.String networkName;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute hidden
	 * Value should be hidden

	 */
	private java.lang.Boolean hidden;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

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

	public Configuration()
	{
	}

	public Configuration(java.lang.String name, java.lang.String value, java.lang.String networkName, java.lang.String description, java.lang.Boolean hidden, java.lang.Long id, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.name = name;
		this.value = value;
		this.networkName = networkName;
		this.description = description;
		this.hidden = hidden;
		this.id = id;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public Configuration(java.lang.String name, java.lang.String value)
	{
		super();
		this.name = name;
		this.value = value;
	}

	public Configuration(Configuration otherBean)
	{
		this(otherBean.name, otherBean.value, otherBean.networkName, otherBean.description, otherBean.hidden, otherBean.id, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute name
	 */
	public java.lang.String getCode() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setCode(java.lang.String name) {
		this.name = name;
	}

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
	 * Gets value for attribute networkName
	 */
	public java.lang.String getNetworkName() {
		return this.networkName;
	}

	/**
	 * Sets value for attribute networkName
	 */
	public void setNetworkName(java.lang.String networkName) {
		this.networkName = networkName;
	}

	/**
	 * Gets value for attribute networkName
	 */
	public java.lang.String getNetworkCode() {
		return this.networkName;
	}

	/**
	 * Sets value for attribute networkName
	 */
	public void setNetworkCode(java.lang.String networkName) {
		this.networkName = networkName;
	}

	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets value for attribute hidden
	 */
	public java.lang.Boolean getHidden() {
		return this.hidden;
	}

	/**
	 * Sets value for attribute hidden
	 */
	public void setHidden(java.lang.Boolean hidden) {
		this.hidden = hidden;
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
		b.append ("[name: ");
		b.append (this.name);
		b.append (", value: ");
		b.append (this.value);
		b.append (", networkName: ");
		b.append (this.networkName);
		b.append (", description: ");
		b.append (this.description);
		b.append (", hidden: ");
		b.append (this.hidden);
		b.append (", id: ");
		b.append (this.id);
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
