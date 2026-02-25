//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity IssueEntity
 */

public abstract class IssueEntity {

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
	 * Attribute number
	 */
	private java.lang.Long number;
	/**
	 * Gets value for attribute number
	 */
	public java.lang.Long getNumber() {
		return this.number;
	}
	/**
	 * Sets value for attribute number
	 */
	public void setNumber(java.lang.Long number) {
		this.number = number;
	}
	/**
	 * Attribute type
	 */
	private java.lang.String type;
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
	 * Attribute status
	 */
	private com.soffid.iam.rc.api.IssueStatus status;
	/**
	 * Gets value for attribute status
	 */
	public com.soffid.iam.rc.api.IssueStatus getStatus() {
		return this.status;
	}
	/**
	 * Sets value for attribute status
	 */
	public void setStatus(com.soffid.iam.rc.api.IssueStatus status) {
		this.status = status;
	}
	/**
	 * Attribute created
	 */
	private java.util.Date created;
	/**
	 * Gets value for attribute created
	 */
	public java.util.Date getCreated() {
		return this.created;
	}
	/**
	 * Sets value for attribute created
	 */
	public void setCreated(java.util.Date created) {
		this.created = created;
	}
	/**
	 * Attribute acknowledged
	 */
	private java.util.Date acknowledged;
	/**
	 * Gets value for attribute acknowledged
	 */
	public java.util.Date getAcknowledged() {
		return this.acknowledged;
	}
	/**
	 * Sets value for attribute acknowledged
	 */
	public void setAcknowledged(java.util.Date acknowledged) {
		this.acknowledged = acknowledged;
	}
	/**
	 * Attribute solved
	 */
	private java.util.Date solved;
	/**
	 * Gets value for attribute solved
	 */
	public java.util.Date getSolved() {
		return this.solved;
	}
	/**
	 * Sets value for attribute solved
	 */
	public void setSolved(java.util.Date solved) {
		this.solved = solved;
	}
	/**
	 * Attribute failedLoginPct
	 */
	private java.lang.Double failedLoginPct;
	/**
	 * Gets value for attribute failedLoginPct
	 */
	public java.lang.Double getFailedLoginPct() {
		return this.failedLoginPct;
	}
	/**
	 * Sets value for attribute failedLoginPct
	 */
	public void setFailedLoginPct(java.lang.Double failedLoginPct) {
		this.failedLoginPct = failedLoginPct;
	}
	/**
	 * Attribute humanConfidence
	 */
	private java.lang.Double humanConfidence;
	/**
	 * Gets value for attribute humanConfidence
	 */
	public java.lang.Double getHumanConfidence() {
		return this.humanConfidence;
	}
	/**
	 * Sets value for attribute humanConfidence
	 */
	public void setHumanConfidence(java.lang.Double humanConfidence) {
		this.humanConfidence = humanConfidence;
	}
	/**
	 * Attribute system
	 */
	private com.soffid.iam.iga.model.SystemEntity system;
	/**
	 * Gets value for attribute system
	 */
	public com.soffid.iam.iga.model.SystemEntity getSystem() {
		return this.system;
	}
	/**
	 * Sets value for attribute system
	 */
	public void setSystem(com.soffid.iam.iga.model.SystemEntity system) {
		this.system = system;
	}
	/**
	 * Attribute otpDevice
	 */
	private java.lang.String otpDevice;
	/**
	 * Gets value for attribute otpDevice
	 */
	public java.lang.String getOtpDevice() {
		return this.otpDevice;
	}
	/**
	 * Sets value for attribute otpDevice
	 */
	public void setOtpDevice(java.lang.String otpDevice) {
		this.otpDevice = otpDevice;
	}
	/**
	 * Attribute exception
	 */
	private java.lang.String exception;
	/**
	 * Gets value for attribute exception
	 */
	public java.lang.String getException() {
		return this.exception;
	}
	/**
	 * Sets value for attribute exception
	 */
	public void setException(java.lang.String exception) {
		this.exception = exception;
	}
	/**
	 * Attribute ip
	 */
	private java.lang.String ip;
	/**
	 * Gets value for attribute ip
	 */
	public java.lang.String getIp() {
		return this.ip;
	}
	/**
	 * Sets value for attribute ip
	 */
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
	/**
	 * Attribute country
	 */
	private java.lang.String country;
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
	 * Attribute roleAccount
	 */
	private com.soffid.iam.iga.model.RoleAccountEntity roleAccount;
	/**
	 * Gets value for attribute roleAccount
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity getRoleAccount() {
		return this.roleAccount;
	}
	/**
	 * Sets value for attribute roleAccount
	 */
	public void setRoleAccount(com.soffid.iam.iga.model.RoleAccountEntity roleAccount) {
		this.roleAccount = roleAccount;
	}
	/**
	 * Attribute performedActions
	 */
	private java.lang.String performedActions;
	/**
	 * Gets value for attribute performedActions
	 */
	public java.lang.String getPerformedActions() {
		return this.performedActions;
	}
	/**
	 * Sets value for attribute performedActions
	 */
	public void setPerformedActions(java.lang.String performedActions) {
		this.performedActions = performedActions;
	}
	/**
	 * Attribute rule
	 */
	private com.soffid.iam.pam.model.PamRuleEntity rule;
	/**
	 * Gets value for attribute rule
	 */
	public com.soffid.iam.pam.model.PamRuleEntity getRule() {
		return this.rule;
	}
	/**
	 * Sets value for attribute rule
	 */
	public void setRule(com.soffid.iam.pam.model.PamRuleEntity rule) {
		this.rule = rule;
	}
	/**
	 * Attribute account
	 */
	private com.soffid.iam.base.model.AccountEntity account;
	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.model.AccountEntity getAccount() {
		return this.account;
	}
	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.model.AccountEntity account) {
		this.account = account;
	}
	/**
	 * Attribute actor
	 */
	private java.lang.String actor;
	/**
	 * Gets value for attribute actor
	 */
	public java.lang.String getActor() {
		return this.actor;
	}
	/**
	 * Sets value for attribute actor
	 */
	public void setActor(java.lang.String actor) {
		this.actor = actor;
	}
	/**
	 * Attribute loginName
	 */
	private java.lang.String loginName;
	/**
	 * Gets value for attribute loginName
	 */
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	/**
	 * Sets value for attribute loginName
	 */
	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}
	/**
	 * Attribute jobName
	 */
	private java.lang.String jobName;
	/**
	 * Gets value for attribute jobName
	 */
	public java.lang.String getJobName() {
		return this.jobName;
	}
	/**
	 * Sets value for attribute jobName
	 */
	public void setJobName(java.lang.String jobName) {
		this.jobName = jobName;
	}
	/**
	 * Attribute requester
	 */
	private com.soffid.iam.base.model.AccountEntity requester;
	/**
	 * Gets value for attribute requester
	 */
	public com.soffid.iam.base.model.AccountEntity getRequester() {
		return this.requester;
	}
	/**
	 * Sets value for attribute requester
	 */
	public void setRequester(com.soffid.iam.base.model.AccountEntity requester) {
		this.requester = requester;
	}
	/**
	 * Attribute risk
	 */
	private com.soffid.iam.rc.api.SoDRisk risk;
	/**
	 * Gets value for attribute risk
	 */
	public com.soffid.iam.rc.api.SoDRisk getRisk() {
		return this.risk;
	}
	/**
	 * Sets value for attribute risk
	 */
	public void setRisk(com.soffid.iam.rc.api.SoDRisk risk) {
		this.risk = risk;
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
	 * Attribute hash
	 */
	private java.lang.String hash;
	/**
	 * Gets value for attribute hash
	 */
	public java.lang.String getHash() {
		return this.hash;
	}
	/**
	 * Sets value for attribute hash
	 */
	public void setHash(java.lang.String hash) {
		this.hash = hash;
	}
	/**
	 * Attribute times
	 */
	private java.lang.Integer times;
	/**
	 * Gets value for attribute times
	 */
	public java.lang.Integer getTimes() {
		return this.times;
	}
	/**
	 * Sets value for attribute times
	 */
	public void setTimes(java.lang.Integer times) {
		this.times = times;
	}
	/**
	 * Attribute breachedEmail
	 */
	private java.lang.String breachedEmail;
	/**
	 * Gets value for attribute breachedEmail
	 */
	public java.lang.String getBreachedEmail() {
		return this.breachedEmail;
	}
	/**
	 * Sets value for attribute breachedEmail
	 */
	public void setBreachedEmail(java.lang.String breachedEmail) {
		this.breachedEmail = breachedEmail;
	}
	/**
	 * Attribute dataBreach
	 */
	private java.lang.String dataBreach;
	/**
	 * Gets value for attribute dataBreach
	 */
	public java.lang.String getDataBreach() {
		return this.dataBreach;
	}
	/**
	 * Sets value for attribute dataBreach
	 */
	public void setDataBreach(java.lang.String dataBreach) {
		this.dataBreach = dataBreach;
	}
	/**
	 * Attribute htmlDescription
	 */
	private java.lang.String htmlDescription;
	/**
	 * Gets value for attribute htmlDescription
	 */
	public java.lang.String getHtmlDescription() {
		return this.htmlDescription;
	}
	/**
	 * Sets value for attribute htmlDescription
	 */
	public void setHtmlDescription(java.lang.String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
	/**
	 * Attribute brosers

	 */
	private java.util.Collection<com.soffid.iam.rc.model.IssueBrowserEntity> brosers =  new java.util.HashSet<com.soffid.iam.rc.model.IssueBrowserEntity>();
	/**
	 * Gets value for attribute brosers
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueBrowserEntity> getBrosers() {
		return this.brosers;
	}
	/**
	 * Sets value for attribute brosers
	 */
	public void setBrosers(java.util.Collection<com.soffid.iam.rc.model.IssueBrowserEntity> brosers) {
		this.brosers = brosers;
	}
	/**
	 * Attribute hosts

	 */
	private java.util.Collection<com.soffid.iam.rc.model.IssueHostEntity> hosts =  new java.util.HashSet<com.soffid.iam.rc.model.IssueHostEntity>();
	/**
	 * Gets value for attribute hosts
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueHostEntity> getHosts() {
		return this.hosts;
	}
	/**
	 * Sets value for attribute hosts
	 */
	public void setHosts(java.util.Collection<com.soffid.iam.rc.model.IssueHostEntity> hosts) {
		this.hosts = hosts;
	}
	/**
	 * Attribute users

	 */
	private java.util.Collection<com.soffid.iam.rc.model.IssueUserEntity> users =  new java.util.HashSet<com.soffid.iam.rc.model.IssueUserEntity>();
	/**
	 * Gets value for attribute users
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueUserEntity> getUsers() {
		return this.users;
	}
	/**
	 * Sets value for attribute users
	 */
	public void setUsers(java.util.Collection<com.soffid.iam.rc.model.IssueUserEntity> users) {
		this.users = users;
	}
	/**
	 * Attribute updatedOn

	 */
	private java.util.Date updatedOn;
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
	 * Attribute updatedBy

	 */
	private java.lang.String updatedBy;
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
	 * Returns <code>true</code> if the argument is an IssueEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof IssueEntity))
		{
			return false;
		}
		final IssueEntity that = (IssueEntity)object;
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
