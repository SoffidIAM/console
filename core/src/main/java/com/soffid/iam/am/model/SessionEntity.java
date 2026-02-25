//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity SessionEntity
 */

public abstract class SessionEntity {

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
	 * Attribute port
	 */
	private java.lang.Long port;
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
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
	}
	/**
	 * Attribute account
	 * Account used in PAM sessions
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
	 * Attribute clientHost
	 */
	private com.soffid.iam.am.model.HostEntity clientHost;
	/**
	 * Gets value for attribute clientHost
	 */
	public com.soffid.iam.am.model.HostEntity getClientHost() {
		return this.clientHost;
	}
	/**
	 * Sets value for attribute clientHost
	 */
	public void setClientHost(com.soffid.iam.am.model.HostEntity clientHost) {
		this.clientHost = clientHost;
	}
	/**
	 * Attribute host
	 */
	private com.soffid.iam.am.model.HostEntity host;
	/**
	 * Gets value for attribute host
	 */
	public com.soffid.iam.am.model.HostEntity getHost() {
		return this.host;
	}
	/**
	 * Sets value for attribute host
	 */
	public void setHost(com.soffid.iam.am.model.HostEntity host) {
		this.host = host;
	}
	/**
	 * Attribute browser
	 */
	private com.soffid.iam.am.model.BrowserEntity browser;
	/**
	 * Gets value for attribute browser
	 */
	public com.soffid.iam.am.model.BrowserEntity getBrowser() {
		return this.browser;
	}
	/**
	 * Sets value for attribute browser
	 */
	public void setBrowser(com.soffid.iam.am.model.BrowserEntity browser) {
		this.browser = browser;
	}
	/**
	 * Attribute startDate
	 */
	private java.util.Date startDate;
	/**
	 * Gets value for attribute startDate
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	/**
	 * Sets value for attribute startDate
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Attribute keepAliveDate
	 */
	private java.util.Date keepAliveDate;
	/**
	 * Gets value for attribute keepAliveDate
	 */
	public java.util.Date getKeepAliveDate() {
		return this.keepAliveDate;
	}
	/**
	 * Sets value for attribute keepAliveDate
	 */
	public void setKeepAliveDate(java.util.Date keepAliveDate) {
		this.keepAliveDate = keepAliveDate;
	}
	/**
	 * Attribute externalClientIp
	 */
	private java.lang.String externalClientIp;
	/**
	 * Gets value for attribute externalClientIp
	 */
	public java.lang.String getExternalClientIp() {
		return this.externalClientIp;
	}
	/**
	 * Sets value for attribute externalClientIp
	 */
	public void setExternalClientIp(java.lang.String externalClientIp) {
		this.externalClientIp = externalClientIp;
	}
	/**
	 * Attribute newKey
	 */
	private java.lang.String newKey;
	/**
	 * Gets value for attribute newKey
	 */
	public java.lang.String getNewKey() {
		return this.newKey;
	}
	/**
	 * Sets value for attribute newKey
	 */
	public void setNewKey(java.lang.String newKey) {
		this.newKey = newKey;
	}
	/**
	 * Attribute type
	 */
	private com.soffid.iam.am.api.SessionType type;
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
	 * Attribute webHandler
	 */
	private java.lang.String webHandler;
	/**
	 * Gets value for attribute webHandler
	 */
	public java.lang.String getWebHandler() {
		return this.webHandler;
	}
	/**
	 * Sets value for attribute webHandler
	 */
	public void setWebHandler(java.lang.String webHandler) {
		this.webHandler = webHandler;
	}
	/**
	 * Attribute key
	 */
	private java.lang.String key;
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
	 * Attribute loginLogInfo
	 */
	private com.soffid.iam.am.model.AccessLogEntity loginLogInfo;
	/**
	 * Gets value for attribute loginLogInfo
	 */
	public com.soffid.iam.am.model.AccessLogEntity getLoginLogInfo() {
		return this.loginLogInfo;
	}
	/**
	 * Sets value for attribute loginLogInfo
	 */
	public void setLoginLogInfo(com.soffid.iam.am.model.AccessLogEntity loginLogInfo) {
		this.loginLogInfo = loginLogInfo;
	}
	/**
	 * Attribute clientAddress
	 */
	private java.lang.String clientAddress;
	/**
	 * Gets value for attribute clientAddress
	 */
	public java.lang.String getClientAddress() {
		return this.clientAddress;
	}
	/**
	 * Sets value for attribute clientAddress
	 */
	public void setClientAddress(java.lang.String clientAddress) {
		this.clientAddress = clientAddress;
	}
	/**
	 * Attribute clientHostName
	 */
	private java.lang.String clientHostName;
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
	 * Attribute hostName
	 */
	private java.lang.String hostName;
	/**
	 * Gets value for attribute hostName
	 */
	public java.lang.String getHostName() {
		return this.hostName;
	}
	/**
	 * Sets value for attribute hostName
	 */
	public void setHostName(java.lang.String hostName) {
		this.hostName = hostName;
	}
	/**
	 * Attribute hostAddress
	 */
	private java.lang.String hostAddress;
	/**
	 * Gets value for attribute hostAddress
	 */
	public java.lang.String getHostAddress() {
		return this.hostAddress;
	}
	/**
	 * Sets value for attribute hostAddress
	 */
	public void setHostAddress(java.lang.String hostAddress) {
		this.hostAddress = hostAddress;
	}
	/**
	 * Attribute authenticationMethod
	 */
	private java.lang.String authenticationMethod;
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
	 * Attribute monitorUrl
	 */
	private java.lang.String monitorUrl;
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
	 * Attribute justInTimePermissionToRemove
	 */
	private java.lang.String justInTimePermissionToRemove;
	/**
	 * Gets value for attribute justInTimePermissionToRemove
	 */
	public java.lang.String getJustInTimePermissionToRemove() {
		return this.justInTimePermissionToRemove;
	}
	/**
	 * Sets value for attribute justInTimePermissionToRemove
	 */
	public void setJustInTimePermissionToRemove(java.lang.String justInTimePermissionToRemove) {
		this.justInTimePermissionToRemove = justInTimePermissionToRemove;
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
	 * Attribute serviceProvider
	 */
	private java.lang.String serviceProvider;
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
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Returns <code>true</code> if the argument is an SessionEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof SessionEntity))
		{
			return false;
		}
		final SessionEntity that = (SessionEntity)object;
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
