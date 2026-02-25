//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject PrinterGroup
 **/
public class PrinterGroup

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute enabledByDefault

	 */
	private java.lang.Boolean enabledByDefault;

	/**
	 * Attribute groupCode

	 */
	private java.lang.String groupCode;

	/**
	 * Attribute printerCode

	 */
	private java.lang.String printerCode;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute printerServerName

	 */
	private java.lang.String printerServerName;

	public PrinterGroup()
	{
	}

	public PrinterGroup(java.lang.Boolean enabledByDefault, java.lang.String groupCode, java.lang.String printerCode, java.lang.Long id, java.lang.String printerServerName)
	{
		super();
		this.enabledByDefault = enabledByDefault;
		this.groupCode = groupCode;
		this.printerCode = printerCode;
		this.id = id;
		this.printerServerName = printerServerName;
	}

	public PrinterGroup(java.lang.Boolean enabledByDefault, java.lang.String groupCode, java.lang.String printerCode)
	{
		super();
		this.enabledByDefault = enabledByDefault;
		this.groupCode = groupCode;
		this.printerCode = printerCode;
	}

	public PrinterGroup(PrinterGroup otherBean)
	{
		this(otherBean.enabledByDefault, otherBean.groupCode, otherBean.printerCode, otherBean.id, otherBean.printerServerName);
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
	 * Gets value for attribute groupCode
	 */
	public java.lang.String getGroupCode() {
		return this.groupCode;
	}

	/**
	 * Sets value for attribute groupCode
	 */
	public void setGroupCode(java.lang.String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * Gets value for attribute printerCode
	 */
	public java.lang.String getPrinterCode() {
		return this.printerCode;
	}

	/**
	 * Sets value for attribute printerCode
	 */
	public void setPrinterCode(java.lang.String printerCode) {
		this.printerCode = printerCode;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[enabledByDefault: ");
		b.append (this.enabledByDefault);
		b.append (", groupCode: ");
		b.append (this.groupCode);
		b.append (", printerCode: ");
		b.append (this.printerCode);
		b.append (", id: ");
		b.append (this.id);
		b.append (", printerServerName: ");
		b.append (this.printerServerName);
		b.append ("]");
		return b.toString();
	}

}
