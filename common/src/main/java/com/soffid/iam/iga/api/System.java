//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject System
 **/
public class System

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
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute className

	 */
	private java.lang.String className;

	/**
	 * Attribute url

	 */
	private java.lang.String url;

	/**
	 * Attribute url2

	 */
	private java.lang.String url2;

	/**
	 * Attribute rolebased

	 */
	private java.lang.Boolean rolebased;

	/**
	 * Attribute trusted

	 */
	private java.lang.Boolean trusted;

	/**
	 * Attribute userTypes

	 */
	private java.util.List<java.lang.String> userTypes;

	/**
	 * Attribute manualAccountCreation

	 */
	private java.lang.Boolean manualAccountCreation;

	/**
	 * Attribute fullReconciliation

	 */
	private boolean fullReconciliation = false;

	/**
	 * Attribute generateTasksOnLoad
	 * Forrward changes to each agent after load

	 */
	private boolean generateTasksOnLoad = false;

	/**
	 * Attribute groupsList

	 */
	private java.util.List<java.lang.String> groupsList;

	/**
	 * Attribute accessControl

	 */
	private java.lang.Boolean accessControl;

	/**
	 * Attribute passwordsDomainId

	 */
	private java.lang.Long passwordsDomainId;

	/**
	 * Attribute passwordsDomain

	 */
	private java.lang.String passwordsDomain;

	/**
	 * Attribute usersDomain

	 */
	private java.lang.String usersDomain;

	/**
	 * Attribute readOnly

	 */
	private boolean readOnly = false;

	/**
	 * Attribute pause

	 */
	private boolean pause = false;

	/**
	 * Attribute authoritative

	 */
	private boolean authoritative = false;

	/**
	 * Attribute timeStamp

	 */
	private java.util.Calendar timeStamp;

	/**
	 * Attribute created

	 */
	private java.util.Calendar created;

	/**
	 * Attribute authoritativeProcess

	 */
	private java.lang.String authoritativeProcess;

	/**
	 * Attribute sharedDispatcher
	 * false to use a dedicated server thread. true to use a shared server thread

	 */
	private java.lang.Boolean sharedDispatcher;

	/**
	 * Attribute threads
	 * Number of concurrent threads to process this agent tasks

	 */
	private java.lang.Long threads = 1L;

	/**
	 * Attribute timeout
	 * Time out for normal operations (milliseconds)

	 */
	private java.lang.Long timeout;

	/**
	 * Attribute longTimeout
	 * Time out for long operations (milliseconds)

	 */
	private java.lang.Long longTimeout;

	/**
	 * Attribute usage
	 * Type of dispatcher: PAM, IAM or SSE

	 */
	private java.lang.String usage = "IAM";

	/**
	 * Attribute deltaChanges
	 * Apply incremental changes

	 */
	private java.lang.Boolean deltaChanges = true;

	/**
	 * Attribute removeRolesFromDisabledAccounts
	 * Remove roles from disabled accounts

	 */
	private java.lang.Boolean removeRolesFromDisabledAccounts = true;

	/**
	 * Attribute createDisabledAccounts
	 * Create disabled accounts

	 */
	private java.lang.Boolean createDisabledAccounts = false;

	/**
	 * Attribute tenant
	 * Owner tenant

	 */
	private java.lang.String tenant;

	/**
	 * Attribute attributes
	 * Custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap();

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

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	public System()
	{
	}

	public System(java.lang.Long id, java.lang.String name, java.lang.String description, java.lang.String className, java.lang.String url, java.lang.String url2, java.lang.Boolean rolebased, java.lang.Boolean trusted, java.util.List<java.lang.String> userTypes, java.lang.Boolean manualAccountCreation, boolean fullReconciliation, boolean generateTasksOnLoad, java.util.List<java.lang.String> groupsList, java.lang.Boolean accessControl, java.lang.Long passwordsDomainId, java.lang.String passwordsDomain, java.lang.String usersDomain, boolean readOnly, boolean pause, boolean authoritative, java.util.Calendar timeStamp, java.util.Calendar created, java.lang.String authoritativeProcess, java.lang.Boolean sharedDispatcher, java.lang.Long threads, java.lang.Long timeout, java.lang.Long longTimeout, java.lang.String usage, java.lang.Boolean deltaChanges, java.lang.Boolean removeRolesFromDisabledAccounts, java.lang.Boolean createDisabledAccounts, java.lang.String tenant, java.util.Map<java.lang.String,java.lang.Object> attributes, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy, java.util.Date createdOn)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.className = className;
		this.url = url;
		this.url2 = url2;
		this.rolebased = rolebased;
		this.trusted = trusted;
		this.userTypes = userTypes;
		this.manualAccountCreation = manualAccountCreation;
		this.fullReconciliation = fullReconciliation;
		this.generateTasksOnLoad = generateTasksOnLoad;
		this.groupsList = groupsList;
		this.accessControl = accessControl;
		this.passwordsDomainId = passwordsDomainId;
		this.passwordsDomain = passwordsDomain;
		this.usersDomain = usersDomain;
		this.readOnly = readOnly;
		this.pause = pause;
		this.authoritative = authoritative;
		this.timeStamp = timeStamp;
		this.created = created;
		this.authoritativeProcess = authoritativeProcess;
		this.sharedDispatcher = sharedDispatcher;
		this.threads = threads;
		this.timeout = timeout;
		this.longTimeout = longTimeout;
		this.usage = usage;
		this.deltaChanges = deltaChanges;
		this.removeRolesFromDisabledAccounts = removeRolesFromDisabledAccounts;
		this.createDisabledAccounts = createDisabledAccounts;
		this.tenant = tenant;
		this.attributes = attributes;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.createdOn = createdOn;
	}

	public System(java.lang.String name, boolean fullReconciliation, boolean readOnly, boolean pause)
	{
		super();
		this.name = name;
		this.fullReconciliation = fullReconciliation;
		this.readOnly = readOnly;
		this.pause = pause;
	}

	public System(System otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.className, otherBean.url, otherBean.url2, otherBean.rolebased, otherBean.trusted, otherBean.userTypes, otherBean.manualAccountCreation, otherBean.fullReconciliation, otherBean.generateTasksOnLoad, otherBean.groupsList, otherBean.accessControl, otherBean.passwordsDomainId, otherBean.passwordsDomain, otherBean.usersDomain, otherBean.readOnly, otherBean.pause, otherBean.authoritative, otherBean.timeStamp, otherBean.created, otherBean.authoritativeProcess, otherBean.sharedDispatcher, otherBean.threads, otherBean.timeout, otherBean.longTimeout, otherBean.usage, otherBean.deltaChanges, otherBean.removeRolesFromDisabledAccounts, otherBean.createDisabledAccounts, otherBean.tenant, otherBean.attributes, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy, otherBean.createdOn);
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
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets value for attribute className
	 */
	public java.lang.String getClassName() {
		return this.className;
	}

	/**
	 * Sets value for attribute className
	 */
	public void setClassName(java.lang.String className) {
		this.className = className;
	}

	/**
	 * Gets value for attribute url
	 */
	public java.lang.String getUrl() {
		return this.url;
	}

	/**
	 * Sets value for attribute url
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	/**
	 * Gets value for attribute url2
	 */
	public java.lang.String getUrl2() {
		return this.url2;
	}

	/**
	 * Sets value for attribute url2
	 */
	public void setUrl2(java.lang.String url2) {
		this.url2 = url2;
	}

	/**
	 * Gets value for attribute rolebased
	 */
	public java.lang.Boolean getRolebased() {
		return this.rolebased;
	}

	/**
	 * Sets value for attribute rolebased
	 */
	public void setRolebased(java.lang.Boolean rolebased) {
		this.rolebased = rolebased;
	}

	/**
	 * Gets value for attribute trusted
	 */
	public java.lang.Boolean getTrusted() {
		return this.trusted;
	}

	/**
	 * Sets value for attribute trusted
	 */
	public void setTrusted(java.lang.Boolean trusted) {
		this.trusted = trusted;
	}

	/**
	 * Gets value for attribute userTypes
	 */
	public java.util.List<java.lang.String> getUserTypes() {
		return this.userTypes;
	}

	/**
	 * Sets value for attribute userTypes
	 */
	public void setUserTypes(java.util.List<java.lang.String> userTypes) {
		this.userTypes = userTypes;
	}

	/**
	 * Gets value for attribute manualAccountCreation
	 */
	public java.lang.Boolean getManualAccountCreation() {
		return this.manualAccountCreation;
	}

	/**
	 * Sets value for attribute manualAccountCreation
	 */
	public void setManualAccountCreation(java.lang.Boolean manualAccountCreation) {
		this.manualAccountCreation = manualAccountCreation;
	}

	/**
	 * Gets value for attribute fullReconciliation
	 */
	public boolean isFullReconciliation() {
		return this.fullReconciliation;
	}

	/**
	 * Sets value for attribute fullReconciliation
	 */
	public void setFullReconciliation(boolean fullReconciliation) {
		this.fullReconciliation = fullReconciliation;
	}

	/**
	 * Gets value for attribute generateTasksOnLoad
	 */
	public boolean isGenerateTasksOnLoad() {
		return this.generateTasksOnLoad;
	}

	/**
	 * Sets value for attribute generateTasksOnLoad
	 */
	public void setGenerateTasksOnLoad(boolean generateTasksOnLoad) {
		this.generateTasksOnLoad = generateTasksOnLoad;
	}

	/**
	 * Gets value for attribute groupsList
	 */
	public java.util.List<java.lang.String> getGroupsList() {
		return this.groupsList;
	}

	/**
	 * Sets value for attribute groupsList
	 */
	public void setGroupsList(java.util.List<java.lang.String> groupsList) {
		this.groupsList = groupsList;
	}

	/**
	 * Gets value for attribute accessControl
	 */
	public java.lang.Boolean getAccessControl() {
		return this.accessControl;
	}

	/**
	 * Sets value for attribute accessControl
	 */
	public void setAccessControl(java.lang.Boolean accessControl) {
		this.accessControl = accessControl;
	}

	/**
	 * Gets value for attribute passwordsDomainId
	 */
	public java.lang.Long getPasswordsDomainId() {
		return this.passwordsDomainId;
	}

	/**
	 * Sets value for attribute passwordsDomainId
	 */
	public void setPasswordsDomainId(java.lang.Long passwordsDomainId) {
		this.passwordsDomainId = passwordsDomainId;
	}

	/**
	 * Gets value for attribute passwordsDomain
	 */
	public java.lang.String getPasswordsDomain() {
		return this.passwordsDomain;
	}

	/**
	 * Sets value for attribute passwordsDomain
	 */
	public void setPasswordsDomain(java.lang.String passwordsDomain) {
		this.passwordsDomain = passwordsDomain;
	}

	/**
	 * Gets value for attribute usersDomain
	 */
	public java.lang.String getUsersDomain() {
		return this.usersDomain;
	}

	/**
	 * Sets value for attribute usersDomain
	 */
	public void setUsersDomain(java.lang.String usersDomain) {
		this.usersDomain = usersDomain;
	}

	/**
	 * Gets value for attribute readOnly
	 */
	public boolean isReadOnly() {
		return this.readOnly;
	}

	/**
	 * Sets value for attribute readOnly
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * Gets value for attribute pause
	 */
	public boolean isPause() {
		return this.pause;
	}

	/**
	 * Sets value for attribute pause
	 */
	public void setPause(boolean pause) {
		this.pause = pause;
	}

	/**
	 * Gets value for attribute authoritative
	 */
	public boolean isAuthoritative() {
		return this.authoritative;
	}

	/**
	 * Sets value for attribute authoritative
	 */
	public void setAuthoritative(boolean authoritative) {
		this.authoritative = authoritative;
	}

	/**
	 * Gets value for attribute timeStamp
	 */
	public java.util.Calendar getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * Sets value for attribute timeStamp
	 */
	public void setTimeStamp(java.util.Calendar timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets value for attribute created
	 */
	public java.util.Calendar getCreated() {
		return this.created;
	}

	/**
	 * Sets value for attribute created
	 */
	public void setCreated(java.util.Calendar created) {
		this.created = created;
	}

	/**
	 * Gets value for attribute authoritativeProcess
	 */
	public java.lang.String getAuthoritativeProcess() {
		return this.authoritativeProcess;
	}

	/**
	 * Sets value for attribute authoritativeProcess
	 */
	public void setAuthoritativeProcess(java.lang.String authoritativeProcess) {
		this.authoritativeProcess = authoritativeProcess;
	}

	/**
	 * Gets value for attribute sharedDispatcher
	 */
	public java.lang.Boolean getSharedDispatcher() {
		return this.sharedDispatcher;
	}

	/**
	 * Sets value for attribute sharedDispatcher
	 */
	public void setSharedDispatcher(java.lang.Boolean sharedDispatcher) {
		this.sharedDispatcher = sharedDispatcher;
	}

	/**
	 * Gets value for attribute threads
	 */
	public java.lang.Long getThreads() {
		return this.threads;
	}

	/**
	 * Sets value for attribute threads
	 */
	public void setThreads(java.lang.Long threads) {
		this.threads = threads;
	}

	/**
	 * Gets value for attribute timeout
	 */
	public java.lang.Long getTimeout() {
		return this.timeout;
	}

	/**
	 * Sets value for attribute timeout
	 */
	public void setTimeout(java.lang.Long timeout) {
		this.timeout = timeout;
	}

	/**
	 * Gets value for attribute longTimeout
	 */
	public java.lang.Long getLongTimeout() {
		return this.longTimeout;
	}

	/**
	 * Sets value for attribute longTimeout
	 */
	public void setLongTimeout(java.lang.Long longTimeout) {
		this.longTimeout = longTimeout;
	}

	/**
	 * Gets value for attribute usage
	 */
	public java.lang.String getUsage() {
		return this.usage;
	}

	/**
	 * Sets value for attribute usage
	 */
	public void setUsage(java.lang.String usage) {
		this.usage = usage;
	}

	/**
	 * Gets value for attribute deltaChanges
	 */
	public java.lang.Boolean getDeltaChanges() {
		return this.deltaChanges;
	}

	/**
	 * Sets value for attribute deltaChanges
	 */
	public void setDeltaChanges(java.lang.Boolean deltaChanges) {
		this.deltaChanges = deltaChanges;
	}

	/**
	 * Gets value for attribute removeRolesFromDisabledAccounts
	 */
	public java.lang.Boolean getRemoveRolesFromDisabledAccounts() {
		return this.removeRolesFromDisabledAccounts;
	}

	/**
	 * Sets value for attribute removeRolesFromDisabledAccounts
	 */
	public void setRemoveRolesFromDisabledAccounts(java.lang.Boolean removeRolesFromDisabledAccounts) {
		this.removeRolesFromDisabledAccounts = removeRolesFromDisabledAccounts;
	}

	/**
	 * Gets value for attribute createDisabledAccounts
	 */
	public java.lang.Boolean getCreateDisabledAccounts() {
		return this.createDisabledAccounts;
	}

	/**
	 * Sets value for attribute createDisabledAccounts
	 */
	public void setCreateDisabledAccounts(java.lang.Boolean createDisabledAccounts) {
		this.createDisabledAccounts = createDisabledAccounts;
	}

	/**
	 * Gets value for attribute tenant
	 */
	public java.lang.String getTenant() {
		return this.tenant;
	}

	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(java.lang.String tenant) {
		this.tenant = tenant;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", className: ");
		b.append (this.className);
		b.append (", url: ");
		b.append (this.url);
		b.append (", url2: ");
		b.append (this.url2);
		b.append (", rolebased: ");
		b.append (this.rolebased);
		b.append (", trusted: ");
		b.append (this.trusted);
		b.append (", userTypes: ");
		b.append (this.userTypes);
		b.append (", manualAccountCreation: ");
		b.append (this.manualAccountCreation);
		b.append (", fullReconciliation: ");
		b.append (this.fullReconciliation);
		b.append (", generateTasksOnLoad: ");
		b.append (this.generateTasksOnLoad);
		b.append (", groupsList: ");
		b.append (this.groupsList);
		b.append (", accessControl: ");
		b.append (this.accessControl);
		b.append (", passwordsDomainId: ");
		b.append (this.passwordsDomainId);
		b.append (", passwordsDomain: ");
		b.append (this.passwordsDomain);
		b.append (", usersDomain: ");
		b.append (this.usersDomain);
		b.append (", readOnly: ");
		b.append (this.readOnly);
		b.append (", pause: ");
		b.append (this.pause);
		b.append (", authoritative: ");
		b.append (this.authoritative);
		b.append (", timeStamp: ");
		b.append (this.timeStamp);
		b.append (", created: ");
		b.append (this.created);
		b.append (", authoritativeProcess: ");
		b.append (this.authoritativeProcess);
		b.append (", sharedDispatcher: ");
		b.append (this.sharedDispatcher);
		b.append (", threads: ");
		b.append (this.threads);
		b.append (", timeout: ");
		b.append (this.timeout);
		b.append (", longTimeout: ");
		b.append (this.longTimeout);
		b.append (", usage: ");
		b.append (this.usage);
		b.append (", deltaChanges: ");
		b.append (this.deltaChanges);
		b.append (", removeRolesFromDisabledAccounts: ");
		b.append (this.removeRolesFromDisabledAccounts);
		b.append (", createDisabledAccounts: ");
		b.append (this.createDisabledAccounts);
		b.append (", tenant: ");
		b.append (this.tenant);
		b.append (", attributes: ");
		b.append (this.attributes);
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
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append ("]");
		return b.toString();
	}

}
