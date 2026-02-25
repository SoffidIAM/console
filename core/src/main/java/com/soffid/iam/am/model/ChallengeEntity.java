//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity ChallengeEntity
 */

public abstract class ChallengeEntity {

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
	 * Attribute type
	 */
	private int type;
	/**
	 * Gets value for attribute type
	 */
	public int getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(int type) {
		this.type = type;
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
	 * Attribute userKey
	 */
	private java.lang.String userKey;
	/**
	 * Gets value for attribute userKey
	 */
	public java.lang.String getUserKey() {
		return this.userKey;
	}
	/**
	 * Sets value for attribute userKey
	 */
	public void setUserKey(java.lang.String userKey) {
		this.userKey = userKey;
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
	 * Attribute centinelPort
	 */
	private java.lang.Integer centinelPort;
	/**
	 * Gets value for attribute centinelPort
	 */
	public java.lang.Integer getCentinelPort() {
		return this.centinelPort;
	}
	/**
	 * Sets value for attribute centinelPort
	 */
	public void setCentinelPort(java.lang.Integer centinelPort) {
		this.centinelPort = centinelPort;
	}
	/**
	 * Attribute otpHandler
	 */
	private java.lang.String otpHandler;
	/**
	 * Gets value for attribute otpHandler
	 */
	public java.lang.String getOtpHandler() {
		return this.otpHandler;
	}
	/**
	 * Sets value for attribute otpHandler
	 */
	public void setOtpHandler(java.lang.String otpHandler) {
		this.otpHandler = otpHandler;
	}
	/**
	 * Attribute cardNumber
	 */
	private java.lang.String cardNumber;
	/**
	 * Gets value for attribute cardNumber
	 */
	public java.lang.String getCardNumber() {
		return this.cardNumber;
	}
	/**
	 * Sets value for attribute cardNumber
	 */
	public void setCardNumber(java.lang.String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * Attribute cell
	 */
	private java.lang.String cell;
	/**
	 * Gets value for attribute cell
	 */
	public java.lang.String getCell() {
		return this.cell;
	}
	/**
	 * Sets value for attribute cell
	 */
	public void setCell(java.lang.String cell) {
		this.cell = cell;
	}
	/**
	 * Attribute value
	 */
	private java.lang.String value;
	/**
	 * Gets value for attribute value
	 */
	public java.lang.String getValue() {
		return this.value;
	}
	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	/**
	 * Attribute timeStamp
	 */
	private java.util.Date timeStamp;
	/**
	 * Gets value for attribute timeStamp
	 */
	public java.util.Date getTimeStamp() {
		return this.timeStamp;
	}
	/**
	 * Sets value for attribute timeStamp
	 */
	public void setTimeStamp(java.util.Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * Attribute clientVersion
	 */
	private java.lang.Integer clientVersion;
	/**
	 * Gets value for attribute clientVersion
	 */
	public java.lang.Integer getClientVersion() {
		return this.clientVersion;
	}
	/**
	 * Sets value for attribute clientVersion
	 */
	public void setClientVersion(java.lang.Integer clientVersion) {
		this.clientVersion = clientVersion;
	}
	/**
	 * Attribute kerberosDomain
	 */
	private java.lang.String kerberosDomain;
	/**
	 * Gets value for attribute kerberosDomain
	 */
	public java.lang.String getKerberosDomain() {
		return this.kerberosDomain;
	}
	/**
	 * Sets value for attribute kerberosDomain
	 */
	public void setKerberosDomain(java.lang.String kerberosDomain) {
		this.kerberosDomain = kerberosDomain;
	}
	/**
	 * Attribute challengeId
	 */
	private java.lang.String challengeId;
	/**
	 * Gets value for attribute challengeId
	 */
	public java.lang.String getChallengeId() {
		return this.challengeId;
	}
	/**
	 * Sets value for attribute challengeId
	 */
	public void setChallengeId(java.lang.String challengeId) {
		this.challengeId = challengeId;
	}
	/**
	 * Attribute domain
	 */
	private java.lang.String domain;
	/**
	 * Gets value for attribute domain
	 */
	public java.lang.String getDomain() {
		return this.domain;
	}
	/**
	 * Sets value for attribute domain
	 */
	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}
	/**
	 * Attribute closeOldSessions
	 */
	private boolean closeOldSessions;
	/**
	 * Gets value for attribute closeOldSessions
	 */
	public boolean isCloseOldSessions() {
		return this.closeOldSessions;
	}
	/**
	 * Sets value for attribute closeOldSessions
	 */
	public void setCloseOldSessions(boolean closeOldSessions) {
		this.closeOldSessions = closeOldSessions;
	}
	/**
	 * Attribute silent
	 */
	private boolean silent;
	/**
	 * Gets value for attribute silent
	 */
	public boolean isSilent() {
		return this.silent;
	}
	/**
	 * Sets value for attribute silent
	 */
	public void setSilent(boolean silent) {
		this.silent = silent;
	}
	/**
	 * Attribute identityProvider
	 */
	private java.lang.String identityProvider;
	/**
	 * Gets value for attribute identityProvider
	 */
	public java.lang.String getIdentityProvider() {
		return this.identityProvider;
	}
	/**
	 * Sets value for attribute identityProvider
	 */
	public void setIdentityProvider(java.lang.String identityProvider) {
		this.identityProvider = identityProvider;
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
	 * Returns <code>true</code> if the argument is an ChallengeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ChallengeEntity))
		{
			return false;
		}
		final ChallengeEntity that = (ChallengeEntity)object;
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
