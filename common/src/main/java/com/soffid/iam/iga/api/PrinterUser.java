//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject PrinterUser
 **/
public class PrinterUser

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute printer

	 */
	private java.lang.String printer;

	/**
	 * Attribute user

	 */
	private java.lang.String user;

	/**
	 * Attribute enabledByDefault

	 */
	private java.lang.Boolean enabledByDefault;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute fullName

	 */
	private java.lang.String fullName;

	/**
	 * Attribute printerServerName

	 */
	private java.lang.String printerServerName;

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

	public PrinterUser()
	{
	}

	public PrinterUser(java.lang.String printer, java.lang.String user, java.lang.Boolean enabledByDefault, java.lang.Long id, java.lang.String fullName, java.lang.String printerServerName, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.printer = printer;
		this.user = user;
		this.enabledByDefault = enabledByDefault;
		this.id = id;
		this.fullName = fullName;
		this.printerServerName = printerServerName;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public PrinterUser(java.lang.String printer, java.lang.String user)
	{
		super();
		this.printer = printer;
		this.user = user;
	}

	public PrinterUser(PrinterUser otherBean)
	{
		this(otherBean.printer, otherBean.user, otherBean.enabledByDefault, otherBean.id, otherBean.fullName, otherBean.printerServerName, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
	}

	/**
	 * Gets value for attribute printer
	 */
	public java.lang.String getPrinter() {
		return this.printer;
	}

	/**
	 * Sets value for attribute printer
	 */
	public void setPrinter(java.lang.String printer) {
		this.printer = printer;
	}

	/**
	 * Gets value for attribute user
	 */
	public java.lang.String getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(java.lang.String user) {
		this.user = user;
	}

	/**
	 * Gets value for attribute enabledByDefault
	 */
	public java.lang.Boolean getEnabledByDefault() {
		return this.enabledByDefault;
	}

	/**
	 * Sets value for attribute enabledByDefault
	 */
	public void setEnabledByDefault(java.lang.Boolean enabledByDefault) {
		this.enabledByDefault = enabledByDefault;
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
	 * Gets value for attribute fullName
	 */
	public java.lang.String getFullName() {
		return this.fullName;
	}

	/**
	 * Sets value for attribute fullName
	 */
	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets value for attribute printerServerName
	 */
	public java.lang.String getPrinterServerName() {
		return this.printerServerName;
	}

	/**
	 * Sets value for attribute printerServerName
	 */
	public void setPrinterServerName(java.lang.String printerServerName) {
		this.printerServerName = printerServerName;
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
		b.append ("[printer: ");
		b.append (this.printer);
		b.append (", user: ");
		b.append (this.user);
		b.append (", enabledByDefault: ");
		b.append (this.enabledByDefault);
		b.append (", id: ");
		b.append (this.id);
		b.append (", fullName: ");
		b.append (this.fullName);
		b.append (", printerServerName: ");
		b.append (this.printerServerName);
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
