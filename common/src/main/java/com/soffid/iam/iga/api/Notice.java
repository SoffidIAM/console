//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject Notice
 **/
public class Notice

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute applicationName

	 */
	private java.lang.String applicationName;

	/**
	 * Attribute roleName

	 */
	private java.lang.String roleName;

	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute userFullName

	 */
	private java.lang.String userFullName;

	/**
	 * Attribute information

	 */
	private java.lang.String information;

	/**
	 * Attribute assignmentDate

	 */
	private java.lang.String assignmentDate;

	public Notice()
	{
	}

	public Notice(java.lang.String applicationName, java.lang.String roleName, java.lang.String userName, java.lang.String userFullName, java.lang.String information, java.lang.String assignmentDate)
	{
		super();
		this.applicationName = applicationName;
		this.roleName = roleName;
		this.userName = userName;
		this.userFullName = userFullName;
		this.information = information;
		this.assignmentDate = assignmentDate;
	}

	public Notice(java.lang.String applicationName, java.lang.String roleName, java.lang.String userName, java.lang.String userFullName)
	{
		super();
		this.applicationName = applicationName;
		this.roleName = roleName;
		this.userName = userName;
		this.userFullName = userFullName;
	}

	public Notice(Notice otherBean)
	{
		this(otherBean.applicationName, otherBean.roleName, otherBean.userName, otherBean.userFullName, otherBean.information, otherBean.assignmentDate);
	}

	/**
	 * Gets value for attribute applicationName
	 */
	public java.lang.String getApplicationName() {
		return this.applicationName;
	}

	/**
	 * Sets value for attribute applicationName
	 */
	public void setApplicationName(java.lang.String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * Gets value for attribute roleName
	 */
	public java.lang.String getRoleName() {
		return this.roleName;
	}

	/**
	 * Sets value for attribute roleName
	 */
	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
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
	 * Gets value for attribute userFullName
	 */
	public java.lang.String getUserFullName() {
		return this.userFullName;
	}

	/**
	 * Sets value for attribute userFullName
	 */
	public void setUserFullName(java.lang.String userFullName) {
		this.userFullName = userFullName;
	}

	/**
	 * Gets value for attribute information
	 */
	public java.lang.String getInformation() {
		return this.information;
	}

	/**
	 * Sets value for attribute information
	 */
	public void setInformation(java.lang.String information) {
		this.information = information;
	}

	/**
	 * Gets value for attribute assignmentDate
	 */
	public java.lang.String getAssignmentDate() {
		return this.assignmentDate;
	}

	/**
	 * Sets value for attribute assignmentDate
	 */
	public void setAssignmentDate(java.lang.String assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[applicationName: ");
		b.append (this.applicationName);
		b.append (", roleName: ");
		b.append (this.roleName);
		b.append (", userName: ");
		b.append (this.userName);
		b.append (", userFullName: ");
		b.append (this.userFullName);
		b.append (", information: ");
		b.append (this.information);
		b.append (", assignmentDate: ");
		b.append (this.assignmentDate);
		b.append ("]");
		return b.toString();
	}

}
