//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject HostAdmin
 **/
public class HostAdmin

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
	 * Attribute userCode

	 */
	private java.lang.String userCode;

	/**
	 * Attribute hostName

	 */
	private java.lang.String hostName;

	/**
	 * Attribute authorizationAccessExpirationDate

	 */
	private java.util.Calendar authorizationAccessExpirationDate;

	/**
	 * Attribute bpmProcessId

	 */
	private java.lang.Long bpmProcessId;

	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute userEmail

	 */
	private java.lang.String userEmail;

	/**
	 * Attribute hostIp

	 */
	private java.lang.String hostIp;

	/**
	 * Attribute hostNetwork

	 */
	private java.lang.String hostNetwork;

	/**
	 * Attribute hostDescription

	 */
	private java.lang.String hostDescription;

	/**
	 * Attribute requestDate

	 */
	private java.util.Calendar requestDate;

	public HostAdmin()
	{
	}

	public HostAdmin(java.lang.Long id, java.lang.String userCode, java.lang.String hostName, java.util.Calendar authorizationAccessExpirationDate, java.lang.Long bpmProcessId, java.lang.String userName, java.lang.String userEmail, java.lang.String hostIp, java.lang.String hostNetwork, java.lang.String hostDescription, java.util.Calendar requestDate)
	{
		super();
		this.id = id;
		this.userCode = userCode;
		this.hostName = hostName;
		this.authorizationAccessExpirationDate = authorizationAccessExpirationDate;
		this.bpmProcessId = bpmProcessId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.hostIp = hostIp;
		this.hostNetwork = hostNetwork;
		this.hostDescription = hostDescription;
		this.requestDate = requestDate;
	}

	public HostAdmin(java.lang.String hostName, java.util.Calendar authorizationAccessExpirationDate)
	{
		super();
		this.hostName = hostName;
		this.authorizationAccessExpirationDate = authorizationAccessExpirationDate;
	}

	public HostAdmin(HostAdmin otherBean)
	{
		this(otherBean.id, otherBean.userCode, otherBean.hostName, otherBean.authorizationAccessExpirationDate, otherBean.bpmProcessId, otherBean.userName, otherBean.userEmail, otherBean.hostIp, otherBean.hostNetwork, otherBean.hostDescription, otherBean.requestDate);
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
	 * Gets value for attribute userCode
	 */
	public java.lang.String getUserCode() {
		return this.userCode;
	}

	/**
	 * Sets value for attribute userCode
	 */
	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	/**
	 * Gets value for attribute hostName
	 */
	public java.lang.String getHostName() {
		return this.hostName;
	}

	/**
	 * Sets value for attribute hostName
	 */
	public void setHostName(java.lang.String hostName) {
		this.hostName = hostName;
	}

	/**
	 * Gets value for attribute authorizationAccessExpirationDate
	 */
	public java.util.Calendar getAuthorizationAccessExpirationDate() {
		return this.authorizationAccessExpirationDate;
	}

	/**
	 * Sets value for attribute authorizationAccessExpirationDate
	 */
	public void setAuthorizationAccessExpirationDate(java.util.Calendar authorizationAccessExpirationDate) {
		this.authorizationAccessExpirationDate = authorizationAccessExpirationDate;
	}

	/**
	 * Gets value for attribute bpmProcessId
	 */
	public java.lang.Long getBpmProcessId() {
		return this.bpmProcessId;
	}

	/**
	 * Sets value for attribute bpmProcessId
	 */
	public void setBpmProcessId(java.lang.Long bpmProcessId) {
		this.bpmProcessId = bpmProcessId;
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
	 * Gets value for attribute userEmail
	 */
	public java.lang.String getUserEmail() {
		return this.userEmail;
	}

	/**
	 * Sets value for attribute userEmail
	 */
	public void setUserEmail(java.lang.String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Gets value for attribute hostIp
	 */
	public java.lang.String getHostIp() {
		return this.hostIp;
	}

	/**
	 * Sets value for attribute hostIp
	 */
	public void setHostIp(java.lang.String hostIp) {
		this.hostIp = hostIp;
	}

	/**
	 * Gets value for attribute hostNetwork
	 */
	public java.lang.String getHostNetwork() {
		return this.hostNetwork;
	}

	/**
	 * Sets value for attribute hostNetwork
	 */
	public void setHostNetwork(java.lang.String hostNetwork) {
		this.hostNetwork = hostNetwork;
	}

	/**
	 * Gets value for attribute hostDescription
	 */
	public java.lang.String getHostDescription() {
		return this.hostDescription;
	}

	/**
	 * Sets value for attribute hostDescription
	 */
	public void setHostDescription(java.lang.String hostDescription) {
		this.hostDescription = hostDescription;
	}

	/**
	 * Gets value for attribute requestDate
	 */
	public java.util.Calendar getRequestDate() {
		return this.requestDate;
	}

	/**
	 * Sets value for attribute requestDate
	 */
	public void setRequestDate(java.util.Calendar requestDate) {
		this.requestDate = requestDate;
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
		b.append (", userCode: ");
		b.append (this.userCode);
		b.append (", hostName: ");
		b.append (this.hostName);
		b.append (", authorizationAccessExpirationDate: ");
		b.append (this.authorizationAccessExpirationDate);
		b.append (", bpmProcessId: ");
		b.append (this.bpmProcessId);
		b.append (", userName: ");
		b.append (this.userName);
		b.append (", userEmail: ");
		b.append (this.userEmail);
		b.append (", hostIp: ");
		b.append (this.hostIp);
		b.append (", hostNetwork: ");
		b.append (this.hostNetwork);
		b.append (", hostDescription: ");
		b.append (this.hostDescription);
		b.append (", requestDate: ");
		b.append (this.requestDate);
		b.append ("]");
		return b.toString();
	}

}
