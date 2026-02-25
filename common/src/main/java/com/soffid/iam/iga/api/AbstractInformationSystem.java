//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject AbstractInformationSystem
 **/
public abstract class AbstractInformationSystem

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
	 * Attribute type
	 * Business process or application

	 */
	private com.soffid.iam.iga.api.ApplicationType type = com.soffid.iam.iga.api.ApplicationType.APPLICATION;

	/**
	 * Attribute parent

	 */
	private java.lang.String parent;

	/**
	 * Attribute relativeName

	 */
	private java.lang.String relativeName;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute owner
	 * User name of the owner of the application.

	 */
	private java.lang.String owner;

	/**
	 * Attribute sourceDir
	 * where source files are located

	 */
	private java.lang.String sourceDir;

	/**
	 * Attribute targetDir
	 * Where the executables are located

	 */
	private java.lang.String targetDir;

	/**
	 * Attribute database

	 */
	private java.lang.String database;

	/**
	 * Attribute bpmEnabled

	 */
	private java.lang.Boolean bpmEnabled;

	/**
	 * Attribute notificationEmails

	 */
	private java.lang.String notificationEmails;

	/**
	 * Attribute approvalProcess
	 * Approval process needed for workflow managed roles belonging to this application. Null value means no approval process is needed

	 */
	private java.lang.String approvalProcess;

	/**
	 * Attribute roleDefinitionProcess
	 * Process needed for any change applied to this application roles. Null value means no approval process is needed

	 */
	private java.lang.String roleDefinitionProcess;

	/**
	 * Attribute singleRole
	 * Only one single role can be assigned to each user

	 */
	private boolean singleRole;

	/**
	 * Attribute attributes
	 * Application custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

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

	public AbstractInformationSystem()
	{
	}

	public AbstractInformationSystem(java.lang.Long id, com.soffid.iam.iga.api.ApplicationType type, java.lang.String parent, java.lang.String relativeName, java.lang.String name, java.lang.String description, java.lang.String owner, java.lang.String sourceDir, java.lang.String targetDir, java.lang.String database, java.lang.Boolean bpmEnabled, java.lang.String notificationEmails, java.lang.String approvalProcess, java.lang.String roleDefinitionProcess, boolean singleRole, java.util.Map<java.lang.String,java.lang.Object> attributes, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.type = type;
		this.parent = parent;
		this.relativeName = relativeName;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.sourceDir = sourceDir;
		this.targetDir = targetDir;
		this.database = database;
		this.bpmEnabled = bpmEnabled;
		this.notificationEmails = notificationEmails;
		this.approvalProcess = approvalProcess;
		this.roleDefinitionProcess = roleDefinitionProcess;
		this.singleRole = singleRole;
		this.attributes = attributes;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public AbstractInformationSystem(java.lang.String relativeName, java.lang.String name, java.lang.String description)
	{
		super();
		this.relativeName = relativeName;
		this.name = name;
		this.description = description;
	}

	public AbstractInformationSystem(AbstractInformationSystem otherBean)
	{
		this(otherBean.id, otherBean.type, otherBean.parent, otherBean.relativeName, otherBean.name, otherBean.description, otherBean.owner, otherBean.sourceDir, otherBean.targetDir, otherBean.database, otherBean.bpmEnabled, otherBean.notificationEmails, otherBean.approvalProcess, otherBean.roleDefinitionProcess, otherBean.singleRole, otherBean.attributes, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute type
	 */
	public com.soffid.iam.iga.api.ApplicationType getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.iga.api.ApplicationType type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute parent
	 */
	public java.lang.String getParent() {
		return this.parent;
	}

	/**
	 * Sets value for attribute parent
	 */
	public void setParent(java.lang.String parent) {
		this.parent = parent;
	}

	/**
	 * Gets value for attribute relativeName
	 */
	public java.lang.String getRelativeName() {
		return this.relativeName;
	}

	/**
	 * Sets value for attribute relativeName
	 */
	public void setRelativeName(java.lang.String relativeName) {
		this.relativeName = relativeName;
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
	 * Gets value for attribute owner
	 */
	public java.lang.String getOwner() {
		return this.owner;
	}

	/**
	 * Sets value for attribute owner
	 */
	public void setOwner(java.lang.String owner) {
		this.owner = owner;
	}

	/**
	 * Gets value for attribute sourceDir
	 */
	public java.lang.String getSourceDir() {
		return this.sourceDir;
	}

	/**
	 * Sets value for attribute sourceDir
	 */
	public void setSourceDir(java.lang.String sourceDir) {
		this.sourceDir = sourceDir;
	}

	/**
	 * Gets value for attribute sourceDir
	 */
	public java.lang.String getSource() {
		return this.sourceDir;
	}

	/**
	 * Sets value for attribute sourceDir
	 */
	public void setSource(java.lang.String sourceDir) {
		this.sourceDir = sourceDir;
	}

	/**
	 * Gets value for attribute targetDir
	 */
	public java.lang.String getTargetDir() {
		return this.targetDir;
	}

	/**
	 * Sets value for attribute targetDir
	 */
	public void setTargetDir(java.lang.String targetDir) {
		this.targetDir = targetDir;
	}

	/**
	 * Gets value for attribute targetDir
	 */
	public java.lang.String getExecutable() {
		return this.targetDir;
	}

	/**
	 * Sets value for attribute targetDir
	 */
	public void setExecutable(java.lang.String targetDir) {
		this.targetDir = targetDir;
	}

	/**
	 * Gets value for attribute database
	 */
	public java.lang.String getDatabase() {
		return this.database;
	}

	/**
	 * Sets value for attribute database
	 */
	public void setDatabase(java.lang.String database) {
		this.database = database;
	}

	/**
	 * Gets value for attribute bpmEnabled
	 */
	public java.lang.Boolean getBpmEnabled() {
		return this.bpmEnabled;
	}

	/**
	 * Sets value for attribute bpmEnabled
	 */
	public void setBpmEnabled(java.lang.Boolean bpmEnabled) {
		this.bpmEnabled = bpmEnabled;
	}

	/**
	 * Gets value for attribute bpmEnabled
	 */
	public java.lang.Boolean getBpmEnforced() {
		return this.bpmEnabled;
	}

	/**
	 * Sets value for attribute bpmEnabled
	 */
	public void setBpmEnforced(java.lang.Boolean bpmEnabled) {
		this.bpmEnabled = bpmEnabled;
	}

	/**
	 * Gets value for attribute notificationEmails
	 */
	public java.lang.String getNotificationEmails() {
		return this.notificationEmails;
	}

	/**
	 * Sets value for attribute notificationEmails
	 */
	public void setNotificationEmails(java.lang.String notificationEmails) {
		this.notificationEmails = notificationEmails;
	}

	/**
	 * Gets value for attribute approvalProcess
	 */
	public java.lang.String getApprovalProcess() {
		return this.approvalProcess;
	}

	/**
	 * Sets value for attribute approvalProcess
	 */
	public void setApprovalProcess(java.lang.String approvalProcess) {
		this.approvalProcess = approvalProcess;
	}

	/**
	 * Gets value for attribute roleDefinitionProcess
	 */
	public java.lang.String getRoleDefinitionProcess() {
		return this.roleDefinitionProcess;
	}

	/**
	 * Sets value for attribute roleDefinitionProcess
	 */
	public void setRoleDefinitionProcess(java.lang.String roleDefinitionProcess) {
		this.roleDefinitionProcess = roleDefinitionProcess;
	}

	/**
	 * Gets value for attribute singleRole
	 */
	public boolean isSingleRole() {
		return this.singleRole;
	}

	/**
	 * Sets value for attribute singleRole
	 */
	public void setSingleRole(boolean singleRole) {
		this.singleRole = singleRole;
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
		b.append (", type: ");
		b.append (this.type);
		b.append (", parent: ");
		b.append (this.parent);
		b.append (", relativeName: ");
		b.append (this.relativeName);
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", owner: ");
		b.append (this.owner);
		b.append (", sourceDir: ");
		b.append (this.sourceDir);
		b.append (", targetDir: ");
		b.append (this.targetDir);
		b.append (", database: ");
		b.append (this.database);
		b.append (", bpmEnabled: ");
		b.append (this.bpmEnabled);
		b.append (", notificationEmails: ");
		b.append (this.notificationEmails);
		b.append (", approvalProcess: ");
		b.append (this.approvalProcess);
		b.append (", roleDefinitionProcess: ");
		b.append (this.roleDefinitionProcess);
		b.append (", singleRole: ");
		b.append (this.singleRole);
		b.append (", attributes: ");
		b.append (this.attributes);
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
