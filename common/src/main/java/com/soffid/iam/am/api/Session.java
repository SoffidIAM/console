//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject Session
 **/
public class Session

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute serverHostName

	 */
	private java.lang.String serverHostName;

	/**
	 * Attribute clientHostName

	 */
	private java.lang.String clientHostName;

	/**
	 * Attribute browser

	 */
	private java.lang.Long browser;

	/**
	 * Attribute country

	 */
	private java.lang.String country;

	/**
	 * Attribute port

	 */
	private java.lang.Long port;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute userFullName

	 */
	private java.lang.String userFullName;

	/**
	 * Attribute startDate

	 */
	private java.util.Calendar startDate;

	/**
	 * Attribute key

	 */
	private java.lang.String key;

	/**
	 * Attribute keepAliveDate

	 */
	private java.util.Calendar keepAliveDate;

	/**
	 * Attribute temporaryKey

	 */
	private java.lang.String temporaryKey;

	/**
	 * Attribute accessLogId

	 */
	private java.lang.Long accessLogId;

	/**
	 * Attribute url

	 */
	private java.lang.String url;

	/**
	 * Attribute authenticationMethod

	 */
	private java.lang.String authenticationMethod;

	/**
	 * Attribute type

	 */
	private com.soffid.iam.am.api.SessionType type;

	/**
	 * Attribute monitorUrl
	 * Monitoring URL

	 */
	private java.lang.String monitorUrl;

	/**
	 * Attribute sessionUrl
	 * Session URL for PAM sessions

	 */
	private java.lang.String sessionUrl;

	/**
	 * Attribute accountName
	 * Account name for PAM sessions

	 */
	private java.lang.String accountName;

	/**
	 * Attribute serviceProvider

	 */
	private java.lang.String serviceProvider;

	/**
	 * Attribute tenantName
	 * Tenant name

	 */
	private java.lang.String tenantName;

	public Session()
	{
	}

	public Session(java.lang.String userName, java.lang.String serverHostName, java.lang.String clientHostName, java.lang.Long browser, java.lang.String country, java.lang.Long port, java.lang.Long id, java.lang.String userFullName, java.util.Calendar startDate, java.lang.String key, java.util.Calendar keepAliveDate, java.lang.String temporaryKey, java.lang.Long accessLogId, java.lang.String url, java.lang.String authenticationMethod, com.soffid.iam.am.api.SessionType type, java.lang.String monitorUrl, java.lang.String sessionUrl, java.lang.String accountName, java.lang.String serviceProvider, java.lang.String tenantName)
	{
		super();
		this.userName = userName;
		this.serverHostName = serverHostName;
		this.clientHostName = clientHostName;
		this.browser = browser;
		this.country = country;
		this.port = port;
		this.id = id;
		this.userFullName = userFullName;
		this.startDate = startDate;
		this.key = key;
		this.keepAliveDate = keepAliveDate;
		this.temporaryKey = temporaryKey;
		this.accessLogId = accessLogId;
		this.url = url;
		this.authenticationMethod = authenticationMethod;
		this.type = type;
		this.monitorUrl = monitorUrl;
		this.sessionUrl = sessionUrl;
		this.accountName = accountName;
		this.serviceProvider = serviceProvider;
		this.tenantName = tenantName;
	}

	public Session(java.lang.String userName, java.util.Calendar startDate)
	{
		super();
		this.userName = userName;
		this.startDate = startDate;
	}

	public Session(Session otherBean)
	{
		this(otherBean.userName, otherBean.serverHostName, otherBean.clientHostName, otherBean.browser, otherBean.country, otherBean.port, otherBean.id, otherBean.userFullName, otherBean.startDate, otherBean.key, otherBean.keepAliveDate, otherBean.temporaryKey, otherBean.accessLogId, otherBean.url, otherBean.authenticationMethod, otherBean.type, otherBean.monitorUrl, otherBean.sessionUrl, otherBean.accountName, otherBean.serviceProvider, otherBean.tenantName);
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
	 * Gets value for attribute serverHostName
	 */
	public java.lang.String getServerHostName() {
		return this.serverHostName;
	}

	/**
	 * Sets value for attribute serverHostName
	 */
	public void setServerHostName(java.lang.String serverHostName) {
		this.serverHostName = serverHostName;
	}

	/**
	 * Gets value for attribute clientHostName
	 */
	public java.lang.String getClientHostName() {
		return this.clientHostName;
	}

	/**
	 * Sets value for attribute clientHostName
	 */
	public void setClientHostName(java.lang.String clientHostName) {
		this.clientHostName = clientHostName;
	}

	/**
	 * Gets value for attribute browser
	 */
	public java.lang.Long getBrowser() {
		return this.browser;
	}

	/**
	 * Sets value for attribute browser
	 */
	public void setBrowser(java.lang.Long browser) {
		this.browser = browser;
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
	 * Gets value for attribute port
	 */
	public java.lang.Long getPort() {
		return this.port;
	}

	/**
	 * Sets value for attribute port
	 */
	public void setPort(java.lang.Long port) {
		this.port = port;
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
	 * Gets value for attribute key
	 */
	public java.lang.String getKey() {
		return this.key;
	}

	/**
	 * Sets value for attribute key
	 */
	public void setKey(java.lang.String key) {
		this.key = key;
	}

	/**
	 * Gets value for attribute keepAliveDate
	 */
	public java.util.Calendar getKeepAliveDate() {
		return this.keepAliveDate;
	}

	/**
	 * Sets value for attribute keepAliveDate
	 */
	public void setKeepAliveDate(java.util.Calendar keepAliveDate) {
		this.keepAliveDate = keepAliveDate;
	}

	/**
	 * Gets value for attribute temporaryKey
	 */
	public java.lang.String getTemporaryKey() {
		return this.temporaryKey;
	}

	/**
	 * Sets value for attribute temporaryKey
	 */
	public void setTemporaryKey(java.lang.String temporaryKey) {
		this.temporaryKey = temporaryKey;
	}

	/**
	 * Gets value for attribute accessLogId
	 */
	public java.lang.Long getAccessLogId() {
		return this.accessLogId;
	}

	/**
	 * Sets value for attribute accessLogId
	 */
	public void setAccessLogId(java.lang.Long accessLogId) {
		this.accessLogId = accessLogId;
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
	 * Gets value for attribute authenticationMethod
	 */
	public java.lang.String getAuthenticationMethod() {
		return this.authenticationMethod;
	}

	/**
	 * Sets value for attribute authenticationMethod
	 */
	public void setAuthenticationMethod(java.lang.String authenticationMethod) {
		this.authenticationMethod = authenticationMethod;
	}

	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.am.api.SessionType getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.am.api.SessionType type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute monitorUrl
	 */
	public java.lang.String getMonitorUrl() {
		return this.monitorUrl;
	}

	/**
	 * Sets value for attribute monitorUrl
	 */
	public void setMonitorUrl(java.lang.String monitorUrl) {
		this.monitorUrl = monitorUrl;
	}

	/**
	 * Gets value for attribute sessionUrl
	 */
	public java.lang.String getSessionUrl() {
		return this.sessionUrl;
	}

	/**
	 * Sets value for attribute sessionUrl
	 */
	public void setSessionUrl(java.lang.String sessionUrl) {
		this.sessionUrl = sessionUrl;
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
	 * Gets value for attribute serviceProvider
	 */
	public java.lang.String getServiceProvider() {
		return this.serviceProvider;
	}

	/**
	 * Sets value for attribute serviceProvider
	 */
	public void setServiceProvider(java.lang.String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	/**
	 * Gets value for attribute tenantName
	 */
	public java.lang.String getTenantName() {
		return this.tenantName;
	}

	/**
	 * Sets value for attribute tenantName
	 */
	public void setTenantName(java.lang.String tenantName) {
		this.tenantName = tenantName;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[userName: ");
		b.append (this.userName);
		b.append (", serverHostName: ");
		b.append (this.serverHostName);
		b.append (", clientHostName: ");
		b.append (this.clientHostName);
		b.append (", browser: ");
		b.append (this.browser);
		b.append (", country: ");
		b.append (this.country);
		b.append (", port: ");
		b.append (this.port);
		b.append (", id: ");
		b.append (this.id);
		b.append (", userFullName: ");
		b.append (this.userFullName);
		b.append (", startDate: ");
		b.append (this.startDate);
		b.append (", key: ");
		b.append (this.key);
		b.append (", keepAliveDate: ");
		b.append (this.keepAliveDate);
		b.append (", temporaryKey: ");
		b.append (this.temporaryKey);
		b.append (", accessLogId: ");
		b.append (this.accessLogId);
		b.append (", url: ");
		b.append (this.url);
		b.append (", authenticationMethod: ");
		b.append (this.authenticationMethod);
		b.append (", type: ");
		b.append (this.type);
		b.append (", monitorUrl: ");
		b.append (this.monitorUrl);
		b.append (", sessionUrl: ");
		b.append (this.sessionUrl);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", serviceProvider: ");
		b.append (this.serviceProvider);
		b.append (", tenantName: ");
		b.append (this.tenantName);
		b.append ("]");
		return b.toString();
	}

}
