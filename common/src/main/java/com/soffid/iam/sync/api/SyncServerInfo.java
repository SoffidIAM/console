//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject SyncServerInfo
 **/
public class SyncServerInfo

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute url

	 */
	private java.lang.String url;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute version

	 */
	private java.lang.String version;

	/**
	 * Attribute status

	 */
	private java.lang.String status;

	/**
	 * Attribute numberOfAgents

	 */
	private java.lang.Integer numberOfAgents;

	/**
	 * Attribute connectedAgents

	 */
	private java.lang.Integer connectedAgents;

	/**
	 * Attribute numberOfPendingTasks

	 */
	private long numberOfPendingTasks;

	/**
	 * Attribute sso

	 */
	private java.lang.String sso;

	/**
	 * Attribute jetty

	 */
	private java.lang.String jetty;

	/**
	 * Attribute ssoDaemon

	 */
	private java.lang.String ssoDaemon;

	/**
	 * Attribute taskGenerator

	 */
	private java.lang.String taskGenerator;

	/**
	 * Attribute expirationRootCertificate

	 */
	private java.util.Calendar expirationRootCertificate;

	/**
	 * Attribute expirationMainCertificate

	 */
	private java.util.Calendar expirationMainCertificate;

	/**
	 * Attribute currentServerDate

	 */
	private java.util.Calendar currentServerDate;

	/**
	 * Attribute databaseConnections

	 */
	private java.lang.String databaseConnections;

	public SyncServerInfo()
	{
	}

	public SyncServerInfo(java.lang.String url, java.lang.String description, java.lang.String version, java.lang.String status, java.lang.Integer numberOfAgents, java.lang.Integer connectedAgents, long numberOfPendingTasks, java.lang.String sso, java.lang.String jetty, java.lang.String ssoDaemon, java.lang.String taskGenerator, java.util.Calendar expirationRootCertificate, java.util.Calendar expirationMainCertificate, java.util.Calendar currentServerDate, java.lang.String databaseConnections)
	{
		super();
		this.url = url;
		this.description = description;
		this.version = version;
		this.status = status;
		this.numberOfAgents = numberOfAgents;
		this.connectedAgents = connectedAgents;
		this.numberOfPendingTasks = numberOfPendingTasks;
		this.sso = sso;
		this.jetty = jetty;
		this.ssoDaemon = ssoDaemon;
		this.taskGenerator = taskGenerator;
		this.expirationRootCertificate = expirationRootCertificate;
		this.expirationMainCertificate = expirationMainCertificate;
		this.currentServerDate = currentServerDate;
		this.databaseConnections = databaseConnections;
	}

	public SyncServerInfo(java.lang.String url, java.lang.String description)
	{
		super();
		this.url = url;
		this.description = description;
	}

	public SyncServerInfo(SyncServerInfo otherBean)
	{
		this(otherBean.url, otherBean.description, otherBean.version, otherBean.status, otherBean.numberOfAgents, otherBean.connectedAgents, otherBean.numberOfPendingTasks, otherBean.sso, otherBean.jetty, otherBean.ssoDaemon, otherBean.taskGenerator, otherBean.expirationRootCertificate, otherBean.expirationMainCertificate, otherBean.currentServerDate, otherBean.databaseConnections);
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
	 * Gets value for attribute numberOfAgents
	 */
	public java.lang.Integer getNumberOfAgents() {
		return this.numberOfAgents;
	}

	/**
	 * Sets value for attribute numberOfAgents
	 */
	public void setNumberOfAgents(java.lang.Integer numberOfAgents) {
		this.numberOfAgents = numberOfAgents;
	}

	/**
	 * Gets value for attribute connectedAgents
	 */
	public java.lang.Integer getConnectedAgents() {
		return this.connectedAgents;
	}

	/**
	 * Sets value for attribute connectedAgents
	 */
	public void setConnectedAgents(java.lang.Integer connectedAgents) {
		this.connectedAgents = connectedAgents;
	}

	/**
	 * Gets value for attribute numberOfPendingTasks
	 */
	public long getNumberOfPendingTasks() {
		return this.numberOfPendingTasks;
	}

	/**
	 * Sets value for attribute numberOfPendingTasks
	 */
	public void setNumberOfPendingTasks(long numberOfPendingTasks) {
		this.numberOfPendingTasks = numberOfPendingTasks;
	}

	/**
	 * Gets value for attribute sso
	 */
	public java.lang.String getSso() {
		return this.sso;
	}

	/**
	 * Sets value for attribute sso
	 */
	public void setSso(java.lang.String sso) {
		this.sso = sso;
	}

	/**
	 * Gets value for attribute jetty
	 */
	public java.lang.String getJetty() {
		return this.jetty;
	}

	/**
	 * Sets value for attribute jetty
	 */
	public void setJetty(java.lang.String jetty) {
		this.jetty = jetty;
	}

	/**
	 * Gets value for attribute ssoDaemon
	 */
	public java.lang.String getSsoDaemon() {
		return this.ssoDaemon;
	}

	/**
	 * Sets value for attribute ssoDaemon
	 */
	public void setSsoDaemon(java.lang.String ssoDaemon) {
		this.ssoDaemon = ssoDaemon;
	}

	/**
	 * Gets value for attribute taskGenerator
	 */
	public java.lang.String getTaskGenerator() {
		return this.taskGenerator;
	}

	/**
	 * Sets value for attribute taskGenerator
	 */
	public void setTaskGenerator(java.lang.String taskGenerator) {
		this.taskGenerator = taskGenerator;
	}

	/**
	 * Gets value for attribute expirationRootCertificate
	 */
	public java.util.Calendar getExpirationRootCertificate() {
		return this.expirationRootCertificate;
	}

	/**
	 * Sets value for attribute expirationRootCertificate
	 */
	public void setExpirationRootCertificate(java.util.Calendar expirationRootCertificate) {
		this.expirationRootCertificate = expirationRootCertificate;
	}

	/**
	 * Gets value for attribute expirationMainCertificate
	 */
	public java.util.Calendar getExpirationMainCertificate() {
		return this.expirationMainCertificate;
	}

	/**
	 * Sets value for attribute expirationMainCertificate
	 */
	public void setExpirationMainCertificate(java.util.Calendar expirationMainCertificate) {
		this.expirationMainCertificate = expirationMainCertificate;
	}

	/**
	 * Gets value for attribute currentServerDate
	 */
	public java.util.Calendar getCurrentServerDate() {
		return this.currentServerDate;
	}

	/**
	 * Sets value for attribute currentServerDate
	 */
	public void setCurrentServerDate(java.util.Calendar currentServerDate) {
		this.currentServerDate = currentServerDate;
	}

	/**
	 * Gets value for attribute databaseConnections
	 */
	public java.lang.String getDatabaseConnections() {
		return this.databaseConnections;
	}

	/**
	 * Sets value for attribute databaseConnections
	 */
	public void setDatabaseConnections(java.lang.String databaseConnections) {
		this.databaseConnections = databaseConnections;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[url: ");
		b.append (this.url);
		b.append (", description: ");
		b.append (this.description);
		b.append (", version: ");
		b.append (this.version);
		b.append (", status: ");
		b.append (this.status);
		b.append (", numberOfAgents: ");
		b.append (this.numberOfAgents);
		b.append (", connectedAgents: ");
		b.append (this.connectedAgents);
		b.append (", numberOfPendingTasks: ");
		b.append (this.numberOfPendingTasks);
		b.append (", sso: ");
		b.append (this.sso);
		b.append (", jetty: ");
		b.append (this.jetty);
		b.append (", ssoDaemon: ");
		b.append (this.ssoDaemon);
		b.append (", taskGenerator: ");
		b.append (this.taskGenerator);
		b.append (", expirationRootCertificate: ");
		b.append (this.expirationRootCertificate);
		b.append (", expirationMainCertificate: ");
		b.append (this.expirationMainCertificate);
		b.append (", currentServerDate: ");
		b.append (this.currentServerDate);
		b.append (", databaseConnections: ");
		b.append (this.databaseConnections);
		b.append ("]");
		return b.toString();
	}

}
