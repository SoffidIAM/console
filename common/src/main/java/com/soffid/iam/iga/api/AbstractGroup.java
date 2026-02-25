//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject AbstractGroup
 **/
public abstract class AbstractGroup

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
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute quota

	 */
	private java.lang.String quota;

	/**
	 * Attribute driveLetter

	 */
	private java.lang.String driveLetter;

	/**
	 * Attribute parentGroup

	 */
	private java.lang.String parentGroup;

	/**
	 * Attribute type

	 */
	private java.lang.String type;

	/**
	 * Attribute driveServerName

	 */
	private java.lang.String driveServerName;

	/**
	 * Attribute obsolete

	 */
	private java.lang.Boolean obsolete = false;

	/**
	 * Attribute startDate

	 */
	private java.util.Date startDate;

	/**
	 * Attribute endDate

	 */
	private java.util.Date endDate;

	/**
	 * Attribute attributes
	 * Group custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

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

	public AbstractGroup()
	{
	}

	public AbstractGroup(java.lang.Long id, java.lang.String name, java.lang.String description, java.lang.String quota, java.lang.String driveLetter, java.lang.String parentGroup, java.lang.String type, java.lang.String driveServerName, java.lang.Boolean obsolete, java.util.Date startDate, java.util.Date endDate, java.util.Map<java.lang.String,java.lang.Object> attributes, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quota = quota;
		this.driveLetter = driveLetter;
		this.parentGroup = parentGroup;
		this.type = type;
		this.driveServerName = driveServerName;
		this.obsolete = obsolete;
		this.startDate = startDate;
		this.endDate = endDate;
		this.attributes = attributes;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public AbstractGroup(java.lang.String name, java.lang.String description)
	{
		super();
		this.name = name;
		this.description = description;
	}

	public AbstractGroup(AbstractGroup otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.quota, otherBean.driveLetter, otherBean.parentGroup, otherBean.type, otherBean.driveServerName, otherBean.obsolete, otherBean.startDate, otherBean.endDate, otherBean.attributes, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute quota
	 */
	public java.lang.String getQuota() {
		return this.quota;
	}

	/**
	 * Sets value for attribute quota
	 */
	public void setQuota(java.lang.String quota) {
		this.quota = quota;
	}

	/**
	 * Gets value for attribute driveLetter
	 */
	public java.lang.String getDriveLetter() {
		return this.driveLetter;
	}

	/**
	 * Sets value for attribute driveLetter
	 */
	public void setDriveLetter(java.lang.String driveLetter) {
		this.driveLetter = driveLetter;
	}

	/**
	 * Gets value for attribute parentGroup
	 */
	public java.lang.String getParentGroup() {
		return this.parentGroup;
	}

	/**
	 * Sets value for attribute parentGroup
	 */
	public void setParentGroup(java.lang.String parentGroup) {
		this.parentGroup = parentGroup;
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
	 * Gets value for attribute driveServerName
	 */
	public java.lang.String getDriveServerName() {
		return this.driveServerName;
	}

	/**
	 * Sets value for attribute driveServerName
	 */
	public void setDriveServerName(java.lang.String driveServerName) {
		this.driveServerName = driveServerName;
	}

	/**
	 * Gets value for attribute obsolete
	 */
	public java.lang.Boolean getObsolete() {
		return this.obsolete;
	}

	/**
	 * Sets value for attribute obsolete
	 */
	public void setObsolete(java.lang.Boolean obsolete) {
		this.obsolete = obsolete;
	}

	/**
	 * Gets value for attribute startDate
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets value for attribute startDate
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets value for attribute endDate
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets value for attribute endDate
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Map<java.lang.String,java.lang.Object> attributes) {
		this.attributes = attributes;
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
		b.append (", description: ");
		b.append (this.description);
		b.append (", quota: ");
		b.append (this.quota);
		b.append (", driveLetter: ");
		b.append (this.driveLetter);
		b.append (", parentGroup: ");
		b.append (this.parentGroup);
		b.append (", type: ");
		b.append (this.type);
		b.append (", driveServerName: ");
		b.append (this.driveServerName);
		b.append (", obsolete: ");
		b.append (this.obsolete);
		b.append (", startDate: ");
		b.append (this.startDate);
		b.append (", endDate: ");
		b.append (this.endDate);
		b.append (", attributes: ");
		b.append (this.attributes);
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
