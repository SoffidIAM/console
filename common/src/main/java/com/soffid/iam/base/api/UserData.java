//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject UserData
 **/
public class UserData

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute attribute

	 */
	private java.lang.String attribute;

	/**
	 * Attribute value

	 */
	private java.lang.String value;

	/**
	 * Attribute user
	 * User name, when the data applies to a user. Null when applies to an account

	 */
	private java.lang.String user;

	/**
	 * Attribute accountName
	 * Account name, when the data applies to an account. Null when applies to a user

	 */
	private java.lang.String accountName;

	/**
	 * Attribute systemName
	 * Account system, when the data applies to an account. Null when applies to a user

	 */
	private java.lang.String systemName;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute blobDataValue

	 */
	private byte[] blobDataValue;

	/**
	 * Attribute dateValue

	 */
	private java.util.Calendar dateValue;

	/**
	 * Attribute dataLabel

	 */
	private java.lang.String dataLabel;

	/**
	 * Attribute visibility

	 */
	private com.soffid.iam.base.api.AttributeVisibilityEnum visibility;

	public UserData()
	{
	}

	public UserData(java.lang.String attribute, java.lang.String value, java.lang.String user, java.lang.String accountName, java.lang.String systemName, java.lang.Long id, byte[] blobDataValue, java.util.Calendar dateValue, java.lang.String dataLabel, com.soffid.iam.base.api.AttributeVisibilityEnum visibility)
	{
		super();
		this.attribute = attribute;
		this.value = value;
		this.user = user;
		this.accountName = accountName;
		this.systemName = systemName;
		this.id = id;
		this.blobDataValue = blobDataValue;
		this.dateValue = dateValue;
		this.dataLabel = dataLabel;
		this.visibility = visibility;
	}

	public UserData(java.lang.String attribute)
	{
		super();
		this.attribute = attribute;
	}

	public UserData(UserData otherBean)
	{
		this(otherBean.attribute, otherBean.value, otherBean.user, otherBean.accountName, otherBean.systemName, otherBean.id, otherBean.blobDataValue, otherBean.dateValue, otherBean.dataLabel, otherBean.visibility);
	}

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
	 * Gets value for attribute accountName
	 */
	public java.lang.String getAccountName() {
		return this.accountName;
	}

	/**
	 * Sets value for attribute accountName
	 */
	public void setAccountName(java.lang.String accountName) {
		this.accountName = accountName;
	}

	/**
	 * Gets value for attribute systemName
	 */
	public java.lang.String getSystemName() {
		return this.systemName;
	}

	/**
	 * Sets value for attribute systemName
	 */
	public void setSystemName(java.lang.String systemName) {
		this.systemName = systemName;
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
	 * Gets value for attribute blobDataValue
	 */
	public byte[] getBlobDataValue() {
		return this.blobDataValue;
	}

	/**
	 * Sets value for attribute blobDataValue
	 */
	public void setBlobDataValue(byte[] blobDataValue) {
		this.blobDataValue = blobDataValue;
	}

	/**
	 * Gets value for attribute dateValue
	 */
	public java.util.Calendar getDateValue() {
		return this.dateValue;
	}

	/**
	 * Sets value for attribute dateValue
	 */
	public void setDateValue(java.util.Calendar dateValue) {
		this.dateValue = dateValue;
	}

	/**
	 * Gets value for attribute dataLabel
	 */
	public java.lang.String getDataLabel() {
		return this.dataLabel;
	}

	/**
	 * Sets value for attribute dataLabel
	 */
	public void setDataLabel(java.lang.String dataLabel) {
		this.dataLabel = dataLabel;
	}

	/**
	 * Gets value for attribute visibility
	 */
	public com.soffid.iam.base.api.AttributeVisibilityEnum getVisibility() {
		return this.visibility;
	}

	/**
	 * Sets value for attribute visibility
	 */
	public void setVisibility(com.soffid.iam.base.api.AttributeVisibilityEnum visibility) {
		this.visibility = visibility;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[attribute: ");
		b.append (this.attribute);
		b.append (", value: ");
		b.append (this.value);
		b.append (", user: ");
		b.append (this.user);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", systemName: ");
		b.append (this.systemName);
		b.append (", id: ");
		b.append (this.id);
		b.append (", blobDataValue: ");
		b.append (this.blobDataValue);
		b.append (", dateValue: ");
		b.append (this.dateValue);
		b.append (", dataLabel: ");
		b.append (this.dataLabel);
		b.append (", visibility: ");
		b.append (this.visibility);
		b.append ("]");
		return b.toString();
	}

}
