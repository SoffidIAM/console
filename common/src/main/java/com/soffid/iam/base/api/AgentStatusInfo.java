//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject AgentStatusInfo
 **/
public class AgentStatusInfo

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute agentName

	 */
	private java.lang.String agentName;

	/**
	 * Attribute className

	 */
	private java.lang.String className;

	/**
	 * Attribute pendingTasks

	 */
	private java.lang.Integer pendingTasks;

	/**
	 * Attribute status

	 */
	private java.lang.String status;

	/**
	 * Attribute url

	 */
	private java.lang.String url;

	/**
	 * Attribute statusMessage

	 */
	private java.lang.String statusMessage;

	/**
	 * Attribute stackTrace

	 */
	private java.lang.String stackTrace;

	/**
	 * Attribute version

	 */
	private java.lang.String version;

	public AgentStatusInfo()
	{
	}

	public AgentStatusInfo(java.lang.String agentName, java.lang.String className, java.lang.Integer pendingTasks, java.lang.String status, java.lang.String url, java.lang.String statusMessage, java.lang.String stackTrace, java.lang.String version)
	{
		super();
		this.agentName = agentName;
		this.className = className;
		this.pendingTasks = pendingTasks;
		this.status = status;
		this.url = url;
		this.statusMessage = statusMessage;
		this.stackTrace = stackTrace;
		this.version = version;
	}

	public AgentStatusInfo(java.lang.String agentName, java.lang.String className, java.lang.Integer pendingTasks, java.lang.String status, java.lang.String url)
	{
		super();
		this.agentName = agentName;
		this.className = className;
		this.pendingTasks = pendingTasks;
		this.status = status;
		this.url = url;
	}

	public AgentStatusInfo(AgentStatusInfo otherBean)
	{
		this(otherBean.agentName, otherBean.className, otherBean.pendingTasks, otherBean.status, otherBean.url, otherBean.statusMessage, otherBean.stackTrace, otherBean.version);
	}

	/**
	 * Gets value for attribute agentName
	 */
	public java.lang.String getAgentName() {
		return this.agentName;
	}

	/**
	 * Sets value for attribute agentName
	 */
	public void setAgentName(java.lang.String agentName) {
		this.agentName = agentName;
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
	 * Gets value for attribute pendingTasks
	 */
	public java.lang.Integer getPendingTasks() {
		return this.pendingTasks;
	}

	/**
	 * Sets value for attribute pendingTasks
	 */
	public void setPendingTasks(java.lang.Integer pendingTasks) {
		this.pendingTasks = pendingTasks;
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
	 * Gets value for attribute statusMessage
	 */
	public java.lang.String getStatusMessage() {
		return this.statusMessage;
	}

	/**
	 * Sets value for attribute statusMessage
	 */
	public void setStatusMessage(java.lang.String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * Gets value for attribute stackTrace
	 */
	public java.lang.String getStackTrace() {
		return this.stackTrace;
	}

	/**
	 * Sets value for attribute stackTrace
	 */
	public void setStackTrace(java.lang.String stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * Gets value for attribute version
	 */
	public java.lang.String getVersion() {
		return this.version;
	}

	/**
	 * Sets value for attribute version
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[agentName: ");
		b.append (this.agentName);
		b.append (", className: ");
		b.append (this.className);
		b.append (", pendingTasks: ");
		b.append (this.pendingTasks);
		b.append (", status: ");
		b.append (this.status);
		b.append (", url: ");
		b.append (this.url);
		b.append (", statusMessage: ");
		b.append (this.statusMessage);
		b.append (", stackTrace: ");
		b.append (this.stackTrace);
		b.append (", version: ");
		b.append (this.version);
		b.append ("]");
		return b.toString();
	}

}
