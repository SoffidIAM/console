//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject AbstractUser
 * Contains the information of a user or identity.
 * The SCIM attribute roles is not supported.
 * The SCIM attribute accounts.account contains the user's accounts as an object of class com.soffid.iam.base.api.Account.
 * The SCIM attribute accounts.account.roles contains the user's grants as an object of class com.soffid.iam.iga.api.RoleAccount.
 * The SCIM attribute accounts.account.roles.role contains the roles granted to a user as an object of class com.soffid.iam.iga.api.Role.

 **/
public abstract class AbstractUser

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
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute firstName

	 */
	private java.lang.String firstName;

	/**
	 * Attribute lastName

	 */
	private java.lang.String lastName;

	/**
	 * Attribute middleName

	 */
	private java.lang.String middleName;

	/**
	 * Attribute fullName

	 */
	private java.lang.String fullName;

	/**
	 * Attribute userType

	 */
	private java.lang.String userType;

	/**
	 * Attribute primaryGroup

	 */
	private java.lang.String primaryGroup;

	/**
	 * Attribute primaryGroupDescription

	 */
	private java.lang.String primaryGroupDescription;

	/**
	 * Attribute homeServer

	 */
	private java.lang.String homeServer;

	/**
	 * Attribute profileServer

	 */
	private java.lang.String profileServer;

	/**
	 * Attribute emailAddress

	 */
	private java.lang.String emailAddress;

	/**
	 * Attribute mailAlias

	 */
	private java.lang.String mailAlias;

	/**
	 * Attribute mailServer

	 */
	private java.lang.String mailServer;

	/**
	 * Attribute shortName

	 */
	private java.lang.String shortName;

	/**
	 * Attribute mailDomain

	 */
	private java.lang.String mailDomain;

	/**
	 * Attribute active

	 */
	private java.lang.Boolean active = true;

	/**
	 * Attribute multiSession

	 */
	private java.lang.Boolean multiSession = true;

	/**
	 * Attribute comments

	 */
	private java.lang.String comments;

	/**
	 * Attribute createdBy

	 */
	private java.lang.String createdBy;

	/**
	 * Attribute createdOn

	 */
	private java.util.Calendar createdOn;

	/**
	 * Attribute modifiedBy

	 */
	private java.lang.String modifiedBy;

	/**
	 * Attribute modifiedOn

	 */
	private java.util.Calendar modifiedOn;

	/**
	 * Attribute attributes
	 * User attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	/**
	 * Attribute deleted


	 */
	private java.lang.Boolean deleted;

	public AbstractUser()
	{
	}

	public AbstractUser(java.lang.Long id, java.lang.String userName, java.lang.String firstName, java.lang.String lastName, java.lang.String middleName, java.lang.String fullName, java.lang.String userType, java.lang.String primaryGroup, java.lang.String primaryGroupDescription, java.lang.String homeServer, java.lang.String profileServer, java.lang.String emailAddress, java.lang.String mailAlias, java.lang.String mailServer, java.lang.String shortName, java.lang.String mailDomain, java.lang.Boolean active, java.lang.Boolean multiSession, java.lang.String comments, java.lang.String createdBy, java.util.Calendar createdOn, java.lang.String modifiedBy, java.util.Calendar modifiedOn, java.util.Map<java.lang.String,java.lang.Object> attributes, java.util.Date deletedOn, java.lang.String deletedBy, java.lang.Boolean deleted)
	{
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.fullName = fullName;
		this.userType = userType;
		this.primaryGroup = primaryGroup;
		this.primaryGroupDescription = primaryGroupDescription;
		this.homeServer = homeServer;
		this.profileServer = profileServer;
		this.emailAddress = emailAddress;
		this.mailAlias = mailAlias;
		this.mailServer = mailServer;
		this.shortName = shortName;
		this.mailDomain = mailDomain;
		this.active = active;
		this.multiSession = multiSession;
		this.comments = comments;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifiedBy = modifiedBy;
		this.modifiedOn = modifiedOn;
		this.attributes = attributes;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.deleted = deleted;
	}

	public AbstractUser(java.lang.String userName, java.lang.String firstName, java.lang.String lastName, java.lang.String userType, java.lang.String primaryGroup)
	{
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.primaryGroup = primaryGroup;
	}

	public AbstractUser(AbstractUser otherBean)
	{
		this(otherBean.id, otherBean.userName, otherBean.firstName, otherBean.lastName, otherBean.middleName, otherBean.fullName, otherBean.userType, otherBean.primaryGroup, otherBean.primaryGroupDescription, otherBean.homeServer, otherBean.profileServer, otherBean.emailAddress, otherBean.mailAlias, otherBean.mailServer, otherBean.shortName, otherBean.mailDomain, otherBean.active, otherBean.multiSession, otherBean.comments, otherBean.createdBy, otherBean.createdOn, otherBean.modifiedBy, otherBean.modifiedOn, otherBean.attributes, otherBean.deletedOn, otherBean.deletedBy, otherBean.deleted);
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
	 * Gets value for attribute firstName
	 */
	public java.lang.String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets value for attribute firstName
	 */
	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets value for attribute lastName
	 */
	public java.lang.String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets value for attribute lastName
	 */
	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets value for attribute middleName
	 */
	public java.lang.String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Sets value for attribute middleName
	 */
	public void setMiddleName(java.lang.String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets value for attribute middleName
	 */
	public java.lang.String getLastName2() {
		return this.middleName;
	}

	/**
	 * Sets value for attribute middleName
	 */
	public void setLastName2(java.lang.String middleName) {
		this.middleName = middleName;
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
	 * Gets value for attribute userType
	 */
	public java.lang.String getUserType() {
		return this.userType;
	}

	/**
	 * Sets value for attribute userType
	 */
	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	/**
	 * Gets value for attribute primaryGroup
	 */
	public java.lang.String getPrimaryGroup() {
		return this.primaryGroup;
	}

	/**
	 * Sets value for attribute primaryGroup
	 */
	public void setPrimaryGroup(java.lang.String primaryGroup) {
		this.primaryGroup = primaryGroup;
	}

	/**
	 * Gets value for attribute primaryGroupDescription
	 */
	public java.lang.String getPrimaryGroupDescription() {
		return this.primaryGroupDescription;
	}

	/**
	 * Sets value for attribute primaryGroupDescription
	 */
	public void setPrimaryGroupDescription(java.lang.String primaryGroupDescription) {
		this.primaryGroupDescription = primaryGroupDescription;
	}

	/**
	 * Gets value for attribute homeServer
	 */
	public java.lang.String getHomeServer() {
		return this.homeServer;
	}

	/**
	 * Sets value for attribute homeServer
	 */
	public void setHomeServer(java.lang.String homeServer) {
		this.homeServer = homeServer;
	}

	/**
	 * Gets value for attribute profileServer
	 */
	public java.lang.String getProfileServer() {
		return this.profileServer;
	}

	/**
	 * Sets value for attribute profileServer
	 */
	public void setProfileServer(java.lang.String profileServer) {
		this.profileServer = profileServer;
	}

	/**
	 * Gets value for attribute emailAddress
	 */
	public java.lang.String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * Sets value for attribute emailAddress
	 */
	public void setEmailAddress(java.lang.String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets value for attribute mailAlias
	 */
	public java.lang.String getMailAlias() {
		return this.mailAlias;
	}

	/**
	 * Sets value for attribute mailAlias
	 */
	public void setMailAlias(java.lang.String mailAlias) {
		this.mailAlias = mailAlias;
	}

	/**
	 * Gets value for attribute mailServer
	 */
	public java.lang.String getMailServer() {
		return this.mailServer;
	}

	/**
	 * Sets value for attribute mailServer
	 */
	public void setMailServer(java.lang.String mailServer) {
		this.mailServer = mailServer;
	}

	/**
	 * Gets value for attribute shortName
	 */
	public java.lang.String getShortName() {
		return this.shortName;
	}

	/**
	 * Sets value for attribute shortName
	 */
	public void setShortName(java.lang.String shortName) {
		this.shortName = shortName;
	}

	/**
	 * Gets value for attribute mailDomain
	 */
	public java.lang.String getMailDomain() {
		return this.mailDomain;
	}

	/**
	 * Sets value for attribute mailDomain
	 */
	public void setMailDomain(java.lang.String mailDomain) {
		this.mailDomain = mailDomain;
	}

	/**
	 * Gets value for attribute active
	 */
	public java.lang.Boolean getActive() {
		return this.active;
	}

	/**
	 * Sets value for attribute active
	 */
	public void setActive(java.lang.Boolean active) {
		this.active = active;
	}

	/**
	 * Gets value for attribute multiSession
	 */
	public java.lang.Boolean getMultiSession() {
		return this.multiSession;
	}

	/**
	 * Sets value for attribute multiSession
	 */
	public void setMultiSession(java.lang.Boolean multiSession) {
		this.multiSession = multiSession;
	}

	/**
	 * Gets value for attribute comments
	 */
	public java.lang.String getComments() {
		return this.comments;
	}

	/**
	 * Sets value for attribute comments
	 */
	public void setComments(java.lang.String comments) {
		this.comments = comments;
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
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedByUser() {
		return this.createdBy;
	}

	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedByUser(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Calendar getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Calendar createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Calendar getCreatedDate() {
		return this.createdOn;
	}

	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedDate(java.util.Calendar createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets value for attribute modifiedBy
	 */
	public java.lang.String getModifiedBy() {
		return this.modifiedBy;
	}

	/**
	 * Sets value for attribute modifiedBy
	 */
	public void setModifiedBy(java.lang.String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets value for attribute modifiedBy
	 */
	public java.lang.String getModifiedByUser() {
		return this.modifiedBy;
	}

	/**
	 * Sets value for attribute modifiedBy
	 */
	public void setModifiedByUser(java.lang.String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets value for attribute modifiedOn
	 */
	public java.util.Calendar getModifiedOn() {
		return this.modifiedOn;
	}

	/**
	 * Sets value for attribute modifiedOn
	 */
	public void setModifiedOn(java.util.Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * Gets value for attribute modifiedOn
	 */
	public java.util.Calendar getModifiedDate() {
		return this.modifiedOn;
	}

	/**
	 * Sets value for attribute modifiedOn
	 */
	public void setModifiedDate(java.util.Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
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
	 * Gets value for attribute deleted
	 */
	public java.lang.Boolean getDeleted() {
		return this.deleted;
	}

	/**
	 * Sets value for attribute deleted
	 */
	public void setDeleted(java.lang.Boolean deleted) {
		this.deleted = deleted;
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
		b.append (", userName: ");
		b.append (this.userName);
		b.append (", firstName: ");
		b.append (this.firstName);
		b.append (", lastName: ");
		b.append (this.lastName);
		b.append (", middleName: ");
		b.append (this.middleName);
		b.append (", fullName: ");
		b.append (this.fullName);
		b.append (", userType: ");
		b.append (this.userType);
		b.append (", primaryGroup: ");
		b.append (this.primaryGroup);
		b.append (", primaryGroupDescription: ");
		b.append (this.primaryGroupDescription);
		b.append (", homeServer: ");
		b.append (this.homeServer);
		b.append (", profileServer: ");
		b.append (this.profileServer);
		b.append (", emailAddress: ");
		b.append (this.emailAddress);
		b.append (", mailAlias: ");
		b.append (this.mailAlias);
		b.append (", mailServer: ");
		b.append (this.mailServer);
		b.append (", shortName: ");
		b.append (this.shortName);
		b.append (", mailDomain: ");
		b.append (this.mailDomain);
		b.append (", active: ");
		b.append (this.active);
		b.append (", multiSession: ");
		b.append (this.multiSession);
		b.append (", comments: ");
		b.append (this.comments);
		b.append (", createdBy: ");
		b.append (this.createdBy);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", modifiedBy: ");
		b.append (this.modifiedBy);
		b.append (", modifiedOn: ");
		b.append (this.modifiedOn);
		b.append (", attributes: ");
		b.append (this.attributes);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append (", deleted: ");
		b.append (this.deleted);
		b.append ("]");
		return b.toString();
	}

}
