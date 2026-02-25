//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject AccessLog
 **/
public class AccessLog

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
	 * Attribute sessionId

	 */
	private java.lang.String sessionId;

	/**
	 * Attribute startDate

	 */
	private java.util.Calendar startDate;

	/**
	 * Attribute endDate

	 */
	private java.util.Calendar endDate;

	/**
	 * Attribute codeAge

	 */
	private java.lang.String codeAge;

	/**
	 * Attribute information

	 */
	private java.lang.String information;

	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute serverName

	 */
	private java.lang.String serverName;

	/**
	 * Attribute clientName

	 */
	private java.lang.String clientName;

	/**
	 * Attribute clientAddress

	 */
	private java.lang.String clientAddress;

	/**
	 * Attribute country

	 */
	private java.lang.String country;

	/**
	 * Attribute accessType

	 */
	private java.lang.String accessType;

	/**
	 * Attribute accessProtocol

	 */
	private java.lang.String accessProtocol;

	/**
	 * Attribute userFullName

	 */
	private java.lang.String userFullName;

	/**
	 * Attribute jumpServerGroup
	 * Jump server for PAM Sessions

	 */
	private java.lang.String jumpServerGroup;

	/**
	 * Attribute accountName
	 * Account name for PAM Sessions

	 */
	private java.lang.String accountName;

	/**
	 * Attribute targetApplication
	 * Target application

	 */
	private java.lang.String targetApplication;

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

	public AccessLog()
	{
	}

	public AccessLog(java.lang.Long id, java.lang.String sessionId, java.util.Calendar startDate, java.util.Calendar endDate, java.lang.String codeAge, java.lang.String information, java.lang.String userName, java.lang.String serverName, java.lang.String clientName, java.lang.String clientAddress, java.lang.String country, java.lang.String accessType, java.lang.String accessProtocol, java.lang.String userFullName, java.lang.String jumpServerGroup, java.lang.String accountName, java.lang.String targetApplication, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.sessionId = sessionId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.codeAge = codeAge;
		this.information = information;
		this.userName = userName;
		this.serverName = serverName;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.country = country;
		this.accessType = accessType;
		this.accessProtocol = accessProtocol;
		this.userFullName = userFullName;
		this.jumpServerGroup = jumpServerGroup;
		this.accountName = accountName;
		this.targetApplication = targetApplication;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public AccessLog(java.lang.Long id)
	{
		super();
		this.id = id;
	}

	public AccessLog(AccessLog otherBean)
	{
		this(otherBean.id, otherBean.sessionId, otherBean.startDate, otherBean.endDate, otherBean.codeAge, otherBean.information, otherBean.userName, otherBean.serverName, otherBean.clientName, otherBean.clientAddress, otherBean.country, otherBean.accessType, otherBean.accessProtocol, otherBean.userFullName, otherBean.jumpServerGroup, otherBean.accountName, otherBean.targetApplication, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute sessionId
	 */
	public java.lang.String getSessionId() {
		return this.sessionId;
	}

	/**
	 * Sets value for attribute sessionId
	 */
	public void setSessionId(java.lang.String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets value for attribute startDate
	 */
	public java.util.Calendar getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets value for attribute startDate
	 */
	public void setStartDate(java.util.Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets value for attribute endDate
	 */
	public java.util.Calendar getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets value for attribute endDate
	 */
	public void setEndDate(java.util.Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets value for attribute codeAge
	 */
	public java.lang.String getCodeAge() {
		return this.codeAge;
	}

	/**
	 * Sets value for attribute codeAge
	 */
	public void setCodeAge(java.lang.String codeAge) {
		this.codeAge = codeAge;
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
	 * Gets value for attribute userName
	 */
	public java.lang.String getUserCode() {
		return this.userName;
	}

	/**
	 * Sets value for attribute userName
	 */
	public void setUserCode(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * Gets value for attribute serverName
	 */
	public java.lang.String getServerName() {
		return this.serverName;
	}

	/**
	 * Sets value for attribute serverName
	 */
	public void setServerName(java.lang.String serverName) {
		this.serverName = serverName;
	}

	/**
	 * Gets value for attribute clientName
	 */
	public java.lang.String getClientName() {
		return this.clientName;
	}

	/**
	 * Sets value for attribute clientName
	 */
	public void setClientName(java.lang.String clientName) {
		this.clientName = clientName;
	}

	/**
	 * Gets value for attribute clientAddress
	 */
	public java.lang.String getClientAddress() {
		return this.clientAddress;
	}

	/**
	 * Sets value for attribute clientAddress
	 */
	public void setClientAddress(java.lang.String clientAddress) {
		this.clientAddress = clientAddress;
	}

	/**
	 * Gets value for attribute country
	 */
	public java.lang.String getCountry() {
		return this.country;
	}

	/**
	 * Sets value for attribute country
	 */
	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	/**
	 * Gets value for attribute accessType
	 */
	public java.lang.String getAccessType() {
		return this.accessType;
	}

	/**
	 * Sets value for attribute accessType
	 */
	public void setAccessType(java.lang.String accessType) {
		this.accessType = accessType;
	}

	/**
	 * Gets value for attribute accessProtocol
	 */
	public java.lang.String getAccessProtocol() {
		return this.accessProtocol;
	}

	/**
	 * Sets value for attribute accessProtocol
	 */
	public void setAccessProtocol(java.lang.String accessProtocol) {
		this.accessProtocol = accessProtocol;
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
	 * Gets value for attribute jumpServerGroup
	 */
	public java.lang.String getJumpServerGroup() {
		return this.jumpServerGroup;
	}

	/**
	 * Sets value for attribute jumpServerGroup
	 */
	public void setJumpServerGroup(java.lang.String jumpServerGroup) {
		this.jumpServerGroup = jumpServerGroup;
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
	 * Gets value for attribute targetApplication
	 */
	public java.lang.String getTargetApplication() {
		return this.targetApplication;
	}

	/**
	 * Sets value for attribute targetApplication
	 */
	public void setTargetApplication(java.lang.String targetApplication) {
		this.targetApplication = targetApplication;
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
		b.append (", sessionId: ");
		b.append (this.sessionId);
		b.append (", startDate: ");
		b.append (this.startDate);
		b.append (", endDate: ");
		b.append (this.endDate);
		b.append (", codeAge: ");
		b.append (this.codeAge);
		b.append (", information: ");
		b.append (this.information);
		b.append (", userName: ");
		b.append (this.userName);
		b.append (", serverName: ");
		b.append (this.serverName);
		b.append (", clientName: ");
		b.append (this.clientName);
		b.append (", clientAddress: ");
		b.append (this.clientAddress);
		b.append (", country: ");
		b.append (this.country);
		b.append (", accessType: ");
		b.append (this.accessType);
		b.append (", accessProtocol: ");
		b.append (this.accessProtocol);
		b.append (", userFullName: ");
		b.append (this.userFullName);
		b.append (", jumpServerGroup: ");
		b.append (this.jumpServerGroup);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", targetApplication: ");
		b.append (this.targetApplication);
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
