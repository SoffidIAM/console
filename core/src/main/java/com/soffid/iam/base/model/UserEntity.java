//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity UserEntity
 */

public abstract class UserEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
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
	 * Attribute userName
	 */
	private java.lang.String userName;
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
	 * Attribute firstName
	 */
	private java.lang.String firstName;
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
	 * Attribute lastName
	 */
	private java.lang.String lastName;
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
	 * Attribute middleName
	 */
	private java.lang.String middleName;
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
	 * Attribute fullName
	 */
	private java.lang.String fullName;
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
	 * Attribute shortName
	 */
	private java.lang.String shortName;
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
	 * Attribute emailAddress
	 */
	private java.lang.String emailAddress;
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
	 * Attribute modifiedBy
	 */
	private java.lang.String modifiedBy;
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
	 * Attribute modifiedOn
	 */
	private java.util.Date modifiedOn;
	/**
	 * Gets value for attribute modifiedOn
	 */
	public java.util.Date getModifiedOn() {
		return this.modifiedOn;
	}
	/**
	 * Sets value for attribute modifiedOn
	 */
	public void setModifiedOn(java.util.Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	/**
	 * Attribute createdOn
	 */
	private java.util.Date createdOn;
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
	 * Attribute createdBy
	 */
	private java.lang.String createdBy;
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
	 * Attribute active
	 */
	private boolean active;
	/**
	 * Gets value for attribute active
	 */
	public boolean isActive() {
		return this.active;
	}
	/**
	 * Sets value for attribute active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * Attribute comments
	 */
	private java.lang.String comments;
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
	 * Attribute ACNetwork
	 */
	private java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> ACNetwork =  new java.util.HashSet<com.soffid.iam.am.model.NetworkAuthorizationEntity>();
	/**
	 * Gets value for attribute ACNetwork
	 */
	public java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> getACNetwork() {
		return this.ACNetwork;
	}
	/**
	 * Sets value for attribute ACNetwork
	 */
	public void setACNetwork(java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> ACNetwork) {
		this.ACNetwork = ACNetwork;
	}
	/**
	 * Attribute mailServer
	 */
	private com.soffid.iam.am.model.HostEntity mailServer;
	/**
	 * Gets value for attribute mailServer
	 */
	public com.soffid.iam.am.model.HostEntity getMailServer() {
		return this.mailServer;
	}
	/**
	 * Sets value for attribute mailServer
	 */
	public void setMailServer(com.soffid.iam.am.model.HostEntity mailServer) {
		this.mailServer = mailServer;
	}
	/**
	 * Attribute homeServer
	 */
	private com.soffid.iam.am.model.HostEntity homeServer;
	/**
	 * Gets value for attribute homeServer
	 */
	public com.soffid.iam.am.model.HostEntity getHomeServer() {
		return this.homeServer;
	}
	/**
	 * Sets value for attribute homeServer
	 */
	public void setHomeServer(com.soffid.iam.am.model.HostEntity homeServer) {
		this.homeServer = homeServer;
	}
	/**
	 * Attribute mailDomain
	 */
	private com.soffid.iam.iga.model.MailDomainEntity mailDomain;
	/**
	 * Gets value for attribute mailDomain
	 */
	public com.soffid.iam.iga.model.MailDomainEntity getMailDomain() {
		return this.mailDomain;
	}
	/**
	 * Sets value for attribute mailDomain
	 */
	public void setMailDomain(com.soffid.iam.iga.model.MailDomainEntity mailDomain) {
		this.mailDomain = mailDomain;
	}
	/**
	 * Attribute profileServer
	 */
	private com.soffid.iam.am.model.HostEntity profileServer;
	/**
	 * Gets value for attribute profileServer
	 */
	public com.soffid.iam.am.model.HostEntity getProfileServer() {
		return this.profileServer;
	}
	/**
	 * Sets value for attribute profileServer
	 */
	public void setProfileServer(com.soffid.iam.am.model.HostEntity profileServer) {
		this.profileServer = profileServer;
	}
	/**
	 * Attribute primaryGroup
	 */
	private com.soffid.iam.iga.model.GroupEntity primaryGroup;
	/**
	 * Gets value for attribute primaryGroup
	 */
	public com.soffid.iam.iga.model.GroupEntity getPrimaryGroup() {
		return this.primaryGroup;
	}
	/**
	 * Sets value for attribute primaryGroup
	 */
	public void setPrimaryGroup(com.soffid.iam.iga.model.GroupEntity primaryGroup) {
		this.primaryGroup = primaryGroup;
	}
	/**
	 * Attribute attributes
	 */
	private java.util.Collection<com.soffid.iam.base.model.UserDataEntity> attributes =  new java.util.HashSet<com.soffid.iam.base.model.UserDataEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserDataEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.base.model.UserDataEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Attribute secondaryGroups
	 */
	private java.util.Collection<com.soffid.iam.iga.model.UserGroupEntity> secondaryGroups =  new java.util.HashSet<com.soffid.iam.iga.model.UserGroupEntity>();
	/**
	 * Gets value for attribute secondaryGroups
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserGroupEntity> getSecondaryGroups() {
		return this.secondaryGroups;
	}
	/**
	 * Sets value for attribute secondaryGroups
	 */
	public void setSecondaryGroups(java.util.Collection<com.soffid.iam.iga.model.UserGroupEntity> secondaryGroups) {
		this.secondaryGroups = secondaryGroups;
	}
	/**
	 * Attribute printers
	 */
	private java.util.Collection<com.soffid.iam.iga.model.UserPrinterEntity> printers =  new java.util.HashSet<com.soffid.iam.iga.model.UserPrinterEntity>();
	/**
	 * Gets value for attribute printers
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserPrinterEntity> getPrinters() {
		return this.printers;
	}
	/**
	 * Sets value for attribute printers
	 */
	public void setPrinters(java.util.Collection<com.soffid.iam.iga.model.UserPrinterEntity> printers) {
		this.printers = printers;
	}
	/**
	 * Attribute sessions
	 */
	private java.util.Collection<com.soffid.iam.am.model.SessionEntity> sessions =  new java.util.HashSet<com.soffid.iam.am.model.SessionEntity>();
	/**
	 * Gets value for attribute sessions
	 */
	public java.util.Collection<com.soffid.iam.am.model.SessionEntity> getSessions() {
		return this.sessions;
	}
	/**
	 * Sets value for attribute sessions
	 */
	public void setSessions(java.util.Collection<com.soffid.iam.am.model.SessionEntity> sessions) {
		this.sessions = sessions;
	}
	/**
	 * Attribute userMailList
	 */
	private java.util.Collection<com.soffid.iam.iga.model.UserMailEntity> userMailList =  new java.util.HashSet<com.soffid.iam.iga.model.UserMailEntity>();
	/**
	 * Gets value for attribute userMailList
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserMailEntity> getUserMailList() {
		return this.userMailList;
	}
	/**
	 * Sets value for attribute userMailList
	 */
	public void setUserMailList(java.util.Collection<com.soffid.iam.iga.model.UserMailEntity> userMailList) {
		this.userMailList = userMailList;
	}
	/**
	 * Attribute multiSession
	 */
	private java.lang.Boolean multiSession;
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
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
	}
	/**
	 * Attribute ApplicationResponsible
	 */
	private java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> ApplicationResponsible =  new java.util.HashSet<com.soffid.iam.iga.model.InformationSystemEntity>();
	/**
	 * Gets value for attribute ApplicationResponsible
	 */
	public java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> getApplicationResponsible() {
		return this.ApplicationResponsible;
	}
	/**
	 * Sets value for attribute ApplicationResponsible
	 */
	public void setApplicationResponsible(java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> ApplicationResponsible) {
		this.ApplicationResponsible = ApplicationResponsible;
	}
	/**
	 * Attribute accessHostAsAdministratorAuthorization
	 */
	private java.util.Collection<com.soffid.iam.pam.model.HostAdminEntity> accessHostAsAdministratorAuthorization =  new java.util.HashSet<com.soffid.iam.pam.model.HostAdminEntity>();
	/**
	 * Gets value for attribute accessHostAsAdministratorAuthorization
	 */
	public java.util.Collection<com.soffid.iam.pam.model.HostAdminEntity> getAccessHostAsAdministratorAuthorization() {
		return this.accessHostAsAdministratorAuthorization;
	}
	/**
	 * Sets value for attribute accessHostAsAdministratorAuthorization
	 */
	public void setAccessHostAsAdministratorAuthorization(java.util.Collection<com.soffid.iam.pam.model.HostAdminEntity> accessHostAsAdministratorAuthorization) {
		this.accessHostAsAdministratorAuthorization = accessHostAsAdministratorAuthorization;
	}
	/**
	 * Attribute userType
	 */
	private com.soffid.iam.base.model.UserTypeEntity userType;
	/**
	 * Gets value for attribute userType
	 */
	public com.soffid.iam.base.model.UserTypeEntity getUserType() {
		return this.userType;
	}
	/**
	 * Sets value for attribute userType
	 */
	public void setUserType(com.soffid.iam.base.model.UserTypeEntity userType) {
		this.userType = userType;
	}
	/**
	 * Attribute secrets
	 */
	private java.util.Collection<com.soffid.iam.am.model.SecretEntity> secrets =  new java.util.HashSet<com.soffid.iam.am.model.SecretEntity>();
	/**
	 * Gets value for attribute secrets
	 */
	public java.util.Collection<com.soffid.iam.am.model.SecretEntity> getSecrets() {
		return this.secrets;
	}
	/**
	 * Sets value for attribute secrets
	 */
	public void setSecrets(java.util.Collection<com.soffid.iam.am.model.SecretEntity> secrets) {
		this.secrets = secrets;
	}
	/**
	 * Attribute accounts
	 */
	private java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> accounts =  new java.util.HashSet<com.soffid.iam.base.model.UserAccountEntity>();
	/**
	 * Gets value for attribute accounts
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> getAccounts() {
		return this.accounts;
	}
	/**
	 * Sets value for attribute accounts
	 */
	public void setAccounts(java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> accounts) {
		this.accounts = accounts;
	}
	/**
	 * Attribute accountAccess
	 */
	private java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> accountAccess =  new java.util.HashSet<com.soffid.iam.pam.model.AccountAccessEntity>();
	/**
	 * Gets value for attribute accountAccess
	 */
	public java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> getAccountAccess() {
		return this.accountAccess;
	}
	/**
	 * Sets value for attribute accountAccess
	 */
	public void setAccountAccess(java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> accountAccess) {
		this.accountAccess = accountAccess;
	}
	/**
	 * Attribute passwords
	 */
	private java.util.Collection<com.soffid.iam.am.model.PasswordEntity> passwords =  new java.util.HashSet<com.soffid.iam.am.model.PasswordEntity>();
	/**
	 * Gets value for attribute passwords
	 */
	public java.util.Collection<com.soffid.iam.am.model.PasswordEntity> getPasswords() {
		return this.passwords;
	}
	/**
	 * Sets value for attribute passwords
	 */
	public void setPasswords(java.util.Collection<com.soffid.iam.am.model.PasswordEntity> passwords) {
		this.passwords = passwords;
	}
	/**
	 * Attribute pendingAuthoritativeChanges
	 */
	private java.util.Collection<com.soffid.iam.iga.model.AuthoritativeChangeEntity> pendingAuthoritativeChanges =  new java.util.HashSet<com.soffid.iam.iga.model.AuthoritativeChangeEntity>();
	/**
	 * Gets value for attribute pendingAuthoritativeChanges
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AuthoritativeChangeEntity> getPendingAuthoritativeChanges() {
		return this.pendingAuthoritativeChanges;
	}
	/**
	 * Sets value for attribute pendingAuthoritativeChanges
	 */
	public void setPendingAuthoritativeChanges(java.util.Collection<com.soffid.iam.iga.model.AuthoritativeChangeEntity> pendingAuthoritativeChanges) {
		this.pendingAuthoritativeChanges = pendingAuthoritativeChanges;
	}
	/**
	 * Attribute browsers

	 */
	private java.util.Collection<com.soffid.iam.am.model.BrowserEntity> browsers =  new java.util.HashSet<com.soffid.iam.am.model.BrowserEntity>();
	/**
	 * Gets value for attribute browsers
	 */
	public java.util.Collection<com.soffid.iam.am.model.BrowserEntity> getBrowsers() {
		return this.browsers;
	}
	/**
	 * Sets value for attribute browsers
	 */
	public void setBrowsers(java.util.Collection<com.soffid.iam.am.model.BrowserEntity> browsers) {
		this.browsers = browsers;
	}
	/**
	 * Attribute passwordManagerToken

	 */
	private java.util.Collection<com.soffid.iam.am.model.PasswordManagerTokenEntity> passwordManagerToken =  new java.util.HashSet<com.soffid.iam.am.model.PasswordManagerTokenEntity>();
	/**
	 * Gets value for attribute passwordManagerToken
	 */
	public java.util.Collection<com.soffid.iam.am.model.PasswordManagerTokenEntity> getPasswordManagerToken() {
		return this.passwordManagerToken;
	}
	/**
	 * Sets value for attribute passwordManagerToken
	 */
	public void setPasswordManagerToken(java.util.Collection<com.soffid.iam.am.model.PasswordManagerTokenEntity> passwordManagerToken) {
		this.passwordManagerToken = passwordManagerToken;
	}
	/**
	 * Attribute vaultFolders

	 */
	private java.util.Collection<com.soffid.iam.am.model.VaultFolderAccessEntity> vaultFolders =  new java.util.HashSet<com.soffid.iam.am.model.VaultFolderAccessEntity>();
	/**
	 * Gets value for attribute vaultFolders
	 */
	public java.util.Collection<com.soffid.iam.am.model.VaultFolderAccessEntity> getVaultFolders() {
		return this.vaultFolders;
	}
	/**
	 * Sets value for attribute vaultFolders
	 */
	public void setVaultFolders(java.util.Collection<com.soffid.iam.am.model.VaultFolderAccessEntity> vaultFolders) {
		this.vaultFolders = vaultFolders;
	}
	/**
	 * Attribute preferences

	 */
	private java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> preferences =  new java.util.HashSet<com.soffid.iam.base.model.UserPreferenceEntity>();
	/**
	 * Gets value for attribute preferences
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> getPreferences() {
		return this.preferences;
	}
	/**
	 * Sets value for attribute preferences
	 */
	public void setPreferences(java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> preferences) {
		this.preferences = preferences;
	}
	/**
	 * Attribute events

	 */
	private java.util.Collection<com.soffid.iam.rc.model.IssueUserEntity> events =  new java.util.HashSet<com.soffid.iam.rc.model.IssueUserEntity>();
	/**
	 * Gets value for attribute events
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueUserEntity> getEvents() {
		return this.events;
	}
	/**
	 * Sets value for attribute events
	 */
	public void setEvents(java.util.Collection<com.soffid.iam.rc.model.IssueUserEntity> events) {
		this.events = events;
	}
	/**
	 * Attribute deletedOn

	 */
	private java.util.Date deletedOn;
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
	 * Attribute deletedBy

	 */
	private java.lang.String deletedBy;
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
	 * Attribute deleted

	 */
	private java.lang.Boolean deleted;
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
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Operation getUserData
	 * @return
	**/
	 public abstract java.util.Collection<com.soffid.iam.base.model.UserDataEntity> getUserData();

	/**
	 * Returns <code>true</code> if the argument is an UserEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof UserEntity))
		{
			return false;
		}
		final UserEntity that = (UserEntity)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
