//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject BPMUser
 **/
public class BPMUser

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute givenName

	 */
	private java.lang.String givenName;

	/**
	 * Attribute surName

	 */
	private java.lang.String surName;

	/**
	 * Attribute group

	 */
	private java.lang.String group;

	public BPMUser()
	{
	}

	public BPMUser(java.lang.String userName, java.lang.String givenName, java.lang.String surName, java.lang.String group)
	{
		super();
		this.userName = userName;
		this.givenName = givenName;
		this.surName = surName;
		this.group = group;
	}

	public BPMUser(BPMUser otherBean)
	{
		this(otherBean.userName, otherBean.givenName, otherBean.surName, otherBean.group);
	}

	/**
	 * Gets value for attribute userName
	 */
	public java.lang.String getUserName() {
		return this.userName;
	}

	/**
	 * Sets value for attribute userName
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * Gets value for attribute givenName
	 */
	public java.lang.String getGivenName() {
		return this.givenName;
	}

	/**
	 * Sets value for attribute givenName
	 */
	public void setGivenName(java.lang.String givenName) {
		this.givenName = givenName;
	}

	/**
	 * Gets value for attribute surName
	 */
	public java.lang.String getSurName() {
		return this.surName;
	}

	/**
	 * Sets value for attribute surName
	 */
	public void setSurName(java.lang.String surName) {
		this.surName = surName;
	}

	/**
	 * Gets value for attribute group
	 */
	public java.lang.String getGroup() {
		return this.group;
	}

	/**
	 * Sets value for attribute group
	 */
	public void setGroup(java.lang.String group) {
		this.group = group;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[userName: ");
		b.append (this.userName);
		b.append (", givenName: ");
		b.append (this.givenName);
		b.append (", surName: ");
		b.append (this.surName);
		b.append (", group: ");
		b.append (this.group);
		b.append ("]");
		return b.toString();
	}

}
