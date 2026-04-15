//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity ScheduledTaskEntity
 */

public abstract class ScheduledTaskEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute handler
	 */
	private com.soffid.iam.sync.model.ScheduledTaskHandlerEntity handler;
	/**
	 * Gets value for attribute handler
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity getHandler() {
		return this.handler;
	}
	/**
	 * Sets value for attribute handler
	 */
	public void setHandler(com.soffid.iam.sync.model.ScheduledTaskHandlerEntity handler) {
		this.handler = handler;
	}
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
	 * Attribute name
	 */
	private java.lang.String name;
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
	 * Attribute params
	 */
	private java.lang.String params;
	/**
	 * Gets value for attribute params
	 */
	public java.lang.String getParams() {
		return this.params;
	}
	/**
	 * Sets value for attribute params
	 */
	public void setParams(java.lang.String params) {
		this.params = params;
	}
	/**
	 * Attribute schedulePattern
	 */
	private java.lang.String schedulePattern;
	/**
	 * Gets value for attribute schedulePattern
	 */
	public java.lang.String getSchedulePattern() {
		return this.schedulePattern;
	}
	/**
	 * Sets value for attribute schedulePattern
	 */
	public void setSchedulePattern(java.lang.String schedulePattern) {
		this.schedulePattern = schedulePattern;
	}
	/**
	 * Attribute lastExecution
	 */
	private java.util.Date lastExecution;
	/**
	 * Gets value for attribute lastExecution
	 */
	public java.util.Date getLastExecution() {
		return this.lastExecution;
	}
	/**
	 * Sets value for attribute lastExecution
	 */
	public void setLastExecution(java.util.Date lastExecution) {
		this.lastExecution = lastExecution;
	}
	/**
	 * Attribute lastEnd
	 */
	private java.util.Date lastEnd;
	/**
	 * Gets value for attribute lastEnd
	 */
	public java.util.Date getLastEnd() {
		return this.lastEnd;
	}
	/**
	 * Sets value for attribute lastEnd
	 */
	public void setLastEnd(java.util.Date lastEnd) {
		this.lastEnd = lastEnd;
	}
	/**
	 * Attribute logReferenceID
	 */
	private java.lang.String logReferenceID;
	/**
	 * Gets value for attribute logReferenceID
	 */
	public java.lang.String getLogReferenceID() {
		return this.logReferenceID;
	}
	/**
	 * Sets value for attribute logReferenceID
	 */
	public void setLogReferenceID(java.lang.String logReferenceID) {
		this.logReferenceID = logReferenceID;
	}
	/**
	 * Attribute error
	 */
	private boolean error = false;
	/**
	 * Gets value for attribute error
	 */
	public boolean isError() {
		return this.error;
	}
	/**
	 * Sets value for attribute error
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	/**
	 * Attribute server
	 */
	private com.soffid.iam.sync.model.ServerEntity server;
	/**
	 * Gets value for attribute server
	 */
	public com.soffid.iam.sync.model.ServerEntity getServer() {
		return this.server;
	}
	/**
	 * Sets value for attribute server
	 */
	public void setServer(com.soffid.iam.sync.model.ServerEntity server) {
		this.server = server;
	}
	/**
	 * Attribute active
	 */
	private boolean active = false;
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
	 * Attribute enabled
	 */
	private boolean enabled = false;
	/**
	 * Gets value for attribute enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	 * Attribute status
	 */
	private java.lang.String status;
	/**
	 * Gets value for attribute status
	 */
	public java.lang.String getStatus() {
		return this.status;
	}
	/**
	 * Sets value for attribute status
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	/**
	 * Attribute percentageDone
	 */
	private java.lang.Double percentageDone;
	/**
	 * Gets value for attribute percentageDone
	 */
	public java.lang.Double getPercentageDone() {
		return this.percentageDone;
	}
	/**
	 * Sets value for attribute percentageDone
	 */
	public void setPercentageDone(java.lang.Double percentageDone) {
		this.percentageDone = percentageDone;
	}
	/**
	 * Attribute stop
	 */
	private java.lang.Boolean stop;
	/**
	 * Gets value for attribute stop
	 */
	public java.lang.Boolean getStop() {
		return this.stop;
	}
	/**
	 * Sets value for attribute stop
	 */
	public void setStop(java.lang.Boolean stop) {
		this.stop = stop;
	}
	/**
	 * Attribute logs

	 */
	private java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskLogEntity> logs =  new java.util.HashSet<com.soffid.iam.sync.model.ScheduledTaskLogEntity>();
	/**
	 * Gets value for attribute logs
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskLogEntity> getLogs() {
		return this.logs;
	}
	/**
	 * Sets value for attribute logs
	 */
	public void setLogs(java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskLogEntity> logs) {
		this.logs = logs;
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
	 * Returns <code>true</code> if the argument is an ScheduledTaskEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ScheduledTaskEntity))
		{
			return false;
		}
		final ScheduledTaskEntity that = (ScheduledTaskEntity)object;
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
