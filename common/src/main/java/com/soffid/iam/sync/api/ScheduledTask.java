//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject ScheduledTask
 **/
public class ScheduledTask

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
	 * Attribute tenant

	 */
	private java.lang.String tenant;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute params

	 */
	private java.lang.String params;

	/**
	 * Attribute handlerName

	 */
	private java.lang.String handlerName;

	/**
	 * Attribute nextExecution

	 */
	private java.util.Calendar nextExecution;

	/**
	 * Attribute lastExecution

	 */
	private java.util.Calendar lastExecution;

	/**
	 * Attribute lastEnd

	 */
	private java.util.Calendar lastEnd;

	/**
	 * Attribute logReferenceID

	 */
	private java.lang.String logReferenceID;

	/**
	 * Attribute dayPattern

	 */
	private java.lang.String dayPattern = "*";

	/**
	 * Attribute hoursPattern

	 */
	private java.lang.String hoursPattern = "0";

	/**
	 * Attribute monthsPattern

	 */
	private java.lang.String monthsPattern = "*";

	/**
	 * Attribute dayOfWeekPattern

	 */
	private java.lang.String dayOfWeekPattern = "*";

	/**
	 * Attribute minutesPattern

	 */
	private java.lang.String minutesPattern = "0";

	/**
	 * Attribute error

	 */
	private boolean error;

	/**
	 * Attribute active

	 */
	private boolean active;

	/**
	 * Attribute enabled

	 */
	private boolean enabled;

	/**
	 * Attribute status

	 */
	private java.lang.String status;

	/**
	 * Attribute percentageDone

	 */
	private java.lang.Double percentageDone;

	/**
	 * Attribute stop

	 */
	private java.lang.Boolean stop;

	/**
	 * Attribute serverName

	 */
	private java.lang.String serverName;

	/**
	 * Attribute logs

	 */
	private java.util.List<com.soffid.iam.sync.api.ScheduledTaskLog> logs = new java.util.LinkedList<>();

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

	public ScheduledTask()
	{
	}

	public ScheduledTask(java.lang.Long id, java.lang.String tenant, java.lang.String name, java.lang.String params, java.lang.String handlerName, java.util.Calendar nextExecution, java.util.Calendar lastExecution, java.util.Calendar lastEnd, java.lang.String logReferenceID, java.lang.String dayPattern, java.lang.String hoursPattern, java.lang.String monthsPattern, java.lang.String dayOfWeekPattern, java.lang.String minutesPattern, boolean error, boolean active, boolean enabled, java.lang.String status, java.lang.Double percentageDone, java.lang.Boolean stop, java.lang.String serverName, java.util.List<com.soffid.iam.sync.api.ScheduledTaskLog> logs, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.tenant = tenant;
		this.name = name;
		this.params = params;
		this.handlerName = handlerName;
		this.nextExecution = nextExecution;
		this.lastExecution = lastExecution;
		this.lastEnd = lastEnd;
		this.logReferenceID = logReferenceID;
		this.dayPattern = dayPattern;
		this.hoursPattern = hoursPattern;
		this.monthsPattern = monthsPattern;
		this.dayOfWeekPattern = dayOfWeekPattern;
		this.minutesPattern = minutesPattern;
		this.error = error;
		this.active = active;
		this.enabled = enabled;
		this.status = status;
		this.percentageDone = percentageDone;
		this.stop = stop;
		this.serverName = serverName;
		this.logs = logs;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public ScheduledTask(java.lang.String name, java.lang.String handlerName, java.lang.String dayPattern, java.lang.String hoursPattern, java.lang.String monthsPattern, java.lang.String dayOfWeekPattern, java.lang.String minutesPattern, boolean error, boolean active, boolean enabled)
	{
		super();
		this.name = name;
		this.handlerName = handlerName;
		this.dayPattern = dayPattern;
		this.hoursPattern = hoursPattern;
		this.monthsPattern = monthsPattern;
		this.dayOfWeekPattern = dayOfWeekPattern;
		this.minutesPattern = minutesPattern;
		this.error = error;
		this.active = active;
		this.enabled = enabled;
	}

	public ScheduledTask(ScheduledTask otherBean)
	{
		this(otherBean.id, otherBean.tenant, otherBean.name, otherBean.params, otherBean.handlerName, otherBean.nextExecution, otherBean.lastExecution, otherBean.lastEnd, otherBean.logReferenceID, otherBean.dayPattern, otherBean.hoursPattern, otherBean.monthsPattern, otherBean.dayOfWeekPattern, otherBean.minutesPattern, otherBean.error, otherBean.active, otherBean.enabled, otherBean.status, otherBean.percentageDone, otherBean.stop, otherBean.serverName, otherBean.logs, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute handlerName
	 */
	public java.lang.String getHandlerName() {
		return this.handlerName;
	}

	/**
	 * Sets value for attribute handlerName
	 */
	public void setHandlerName(java.lang.String handlerName) {
		this.handlerName = handlerName;
	}

	/**
	 * Gets value for attribute nextExecution
	 */
	public java.util.Calendar getNextExecution() {
		return this.nextExecution;
	}

	/**
	 * Sets value for attribute nextExecution
	 */
	public void setNextExecution(java.util.Calendar nextExecution) {
		this.nextExecution = nextExecution;
	}

	/**
	 * Gets value for attribute lastExecution
	 */
	public java.util.Calendar getLastExecution() {
		return this.lastExecution;
	}

	/**
	 * Sets value for attribute lastExecution
	 */
	public void setLastExecution(java.util.Calendar lastExecution) {
		this.lastExecution = lastExecution;
	}

	/**
	 * Gets value for attribute lastEnd
	 */
	public java.util.Calendar getLastEnd() {
		return this.lastEnd;
	}

	/**
	 * Sets value for attribute lastEnd
	 */
	public void setLastEnd(java.util.Calendar lastEnd) {
		this.lastEnd = lastEnd;
	}

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
	 * Gets value for attribute dayPattern
	 */
	public java.lang.String getDayPattern() {
		return this.dayPattern;
	}

	/**
	 * Sets value for attribute dayPattern
	 */
	public void setDayPattern(java.lang.String dayPattern) {
		this.dayPattern = dayPattern;
	}

	/**
	 * Gets value for attribute hoursPattern
	 */
	public java.lang.String getHoursPattern() {
		return this.hoursPattern;
	}

	/**
	 * Sets value for attribute hoursPattern
	 */
	public void setHoursPattern(java.lang.String hoursPattern) {
		this.hoursPattern = hoursPattern;
	}

	/**
	 * Gets value for attribute monthsPattern
	 */
	public java.lang.String getMonthsPattern() {
		return this.monthsPattern;
	}

	/**
	 * Sets value for attribute monthsPattern
	 */
	public void setMonthsPattern(java.lang.String monthsPattern) {
		this.monthsPattern = monthsPattern;
	}

	/**
	 * Gets value for attribute dayOfWeekPattern
	 */
	public java.lang.String getDayOfWeekPattern() {
		return this.dayOfWeekPattern;
	}

	/**
	 * Sets value for attribute dayOfWeekPattern
	 */
	public void setDayOfWeekPattern(java.lang.String dayOfWeekPattern) {
		this.dayOfWeekPattern = dayOfWeekPattern;
	}

	/**
	 * Gets value for attribute minutesPattern
	 */
	public java.lang.String getMinutesPattern() {
		return this.minutesPattern;
	}

	/**
	 * Sets value for attribute minutesPattern
	 */
	public void setMinutesPattern(java.lang.String minutesPattern) {
		this.minutesPattern = minutesPattern;
	}

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
	 * Gets value for attribute logs
	 */
	public java.util.List<com.soffid.iam.sync.api.ScheduledTaskLog> getLogs() {
		return this.logs;
	}

	/**
	 * Sets value for attribute logs
	 */
	public void setLogs(java.util.List<com.soffid.iam.sync.api.ScheduledTaskLog> logs) {
		this.logs = logs;
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
		b.append (", tenant: ");
		b.append (this.tenant);
		b.append (", name: ");
		b.append (this.name);
		b.append (", params: ");
		b.append (this.params);
		b.append (", handlerName: ");
		b.append (this.handlerName);
		b.append (", nextExecution: ");
		b.append (this.nextExecution);
		b.append (", lastExecution: ");
		b.append (this.lastExecution);
		b.append (", lastEnd: ");
		b.append (this.lastEnd);
		b.append (", logReferenceID: ");
		b.append (this.logReferenceID);
		b.append (", dayPattern: ");
		b.append (this.dayPattern);
		b.append (", hoursPattern: ");
		b.append (this.hoursPattern);
		b.append (", monthsPattern: ");
		b.append (this.monthsPattern);
		b.append (", dayOfWeekPattern: ");
		b.append (this.dayOfWeekPattern);
		b.append (", minutesPattern: ");
		b.append (this.minutesPattern);
		b.append (", error: ");
		b.append (this.error);
		b.append (", active: ");
		b.append (this.active);
		b.append (", enabled: ");
		b.append (this.enabled);
		b.append (", status: ");
		b.append (this.status);
		b.append (", percentageDone: ");
		b.append (this.percentageDone);
		b.append (", stop: ");
		b.append (this.stop);
		b.append (", serverName: ");
		b.append (this.serverName);
		b.append (", logs: ");
		b.append (this.logs);
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
