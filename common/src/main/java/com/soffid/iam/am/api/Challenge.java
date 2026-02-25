//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject Challenge
 **/
public class Challenge

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Constant CARD_REQUIRED

	 */
	public static final int CARD_REQUIRED = 1;

	/**
	 * Constant CARD_IFABLE

	 */
	public static final int CARD_IFABLE = 2;

	/**
	 * Constant CARD_IFNEEDED

	 */
	public static final int CARD_IFNEEDED = 3;

	/**
	 * Constant CARD_DISABLED

	 */
	public static final int CARD_DISABLED = 4;

	/**
	 * Constant TYPE_RMI

	 */
	public static final int TYPE_RMI = 0;

	/**
	 * Constant TYPE_KERBEROS

	 */
	public static final int TYPE_KERBEROS = 2;

	/**
	 * Constant TYPE_CERT

	 */
	public static final int TYPE_CERT = 3;

	/**
	 * Constant TYPE_PASSWORD

	 */
	public static final int TYPE_PASSWORD = 3;

	/**
	 * Attribute password

	 */
	private com.soffid.iam.am.api.Password password;

	/**
	 * Attribute type

	 */
	private int type;

	/**
	 * Attribute user

	 */
	private com.soffid.iam.base.api.User user;

	/**
	 * Attribute account

	 */
	private com.soffid.iam.base.api.Account account;

	/**
	 * Attribute userKey

	 */
	private java.lang.String userKey;

	/**
	 * Attribute host

	 */
	private com.soffid.iam.am.api.Host host;

	/**
	 * Attribute clientHost

	 */
	private com.soffid.iam.am.api.Host clientHost;

	/**
	 * Attribute centinelPort

	 */
	private int centinelPort;

	/**
	 * Attribute otpHandler

	 */
	private java.lang.String otpHandler;

	/**
	 * Attribute cardNumber

	 */
	private java.lang.String cardNumber;

	/**
	 * Attribute cell

	 */
	private java.lang.String cell;

	/**
	 * Attribute value

	 */
	private java.lang.String value;

	/**
	 * Attribute timeStamp

	 */
	private java.sql.Timestamp timeStamp;

	/**
	 * Attribute clientVersion

	 */
	private int clientVersion;

	/**
	 * Attribute kerberosDomain

	 */
	private java.lang.String kerberosDomain;

	/**
	 * Attribute challengeId

	 */
	private java.lang.String challengeId;

	/**
	 * Attribute kerberosContext

	 */
	private org.ietf.jgss.GSSContext kerberosContext;

	/**
	 * Attribute domain

	 */
	private java.lang.String domain;

	/**
	 * Attribute closeOldSessions

	 */
	private boolean closeOldSessions;

	/**
	 * Attribute silent

	 */
	private boolean silent;

	/**
	 * Attribute alternativeMethodAvailable

	 */
	private boolean alternativeMethodAvailable;

	/**
	 * Attribute resendAvailable

	 */
	private boolean resendAvailable;

	/**
	 * Attribute additionalData

	 */
	private java.lang.Object additionalData;

	/**
	 * Attribute identityProvider

	 */
	private java.lang.String identityProvider;

	public Challenge()
	{
	}

	public Challenge(com.soffid.iam.am.api.Password password, int type, com.soffid.iam.base.api.User user, com.soffid.iam.base.api.Account account, java.lang.String userKey, com.soffid.iam.am.api.Host host, com.soffid.iam.am.api.Host clientHost, int centinelPort, java.lang.String otpHandler, java.lang.String cardNumber, java.lang.String cell, java.lang.String value, java.sql.Timestamp timeStamp, int clientVersion, java.lang.String kerberosDomain, java.lang.String challengeId, org.ietf.jgss.GSSContext kerberosContext, java.lang.String domain, boolean closeOldSessions, boolean silent, boolean alternativeMethodAvailable, boolean resendAvailable, java.lang.Object additionalData, java.lang.String identityProvider)
	{
		super();
		this.password = password;
		this.type = type;
		this.user = user;
		this.account = account;
		this.userKey = userKey;
		this.host = host;
		this.clientHost = clientHost;
		this.centinelPort = centinelPort;
		this.otpHandler = otpHandler;
		this.cardNumber = cardNumber;
		this.cell = cell;
		this.value = value;
		this.timeStamp = timeStamp;
		this.clientVersion = clientVersion;
		this.kerberosDomain = kerberosDomain;
		this.challengeId = challengeId;
		this.kerberosContext = kerberosContext;
		this.domain = domain;
		this.closeOldSessions = closeOldSessions;
		this.silent = silent;
		this.alternativeMethodAvailable = alternativeMethodAvailable;
		this.resendAvailable = resendAvailable;
		this.additionalData = additionalData;
		this.identityProvider = identityProvider;
	}

	public Challenge(int type, boolean closeOldSessions, boolean silent, boolean alternativeMethodAvailable, boolean resendAvailable)
	{
		super();
		this.type = type;
		this.closeOldSessions = closeOldSessions;
		this.silent = silent;
		this.alternativeMethodAvailable = alternativeMethodAvailable;
		this.resendAvailable = resendAvailable;
	}

	public Challenge(Challenge otherBean)
	{
		this(otherBean.password, otherBean.type, otherBean.user, otherBean.account, otherBean.userKey, otherBean.host, otherBean.clientHost, otherBean.centinelPort, otherBean.otpHandler, otherBean.cardNumber, otherBean.cell, otherBean.value, otherBean.timeStamp, otherBean.clientVersion, otherBean.kerberosDomain, otherBean.challengeId, otherBean.kerberosContext, otherBean.domain, otherBean.closeOldSessions, otherBean.silent, otherBean.alternativeMethodAvailable, otherBean.resendAvailable, otherBean.additionalData, otherBean.identityProvider);
	}

	/**
	 * Gets value for attribute password
	 */
	public com.soffid.iam.am.api.Password getPassword() {
		return this.password;
	}

	/**
	 * Sets value for attribute password
	 */
	public void setPassword(com.soffid.iam.am.api.Password password) {
		this.password = password;
	}

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
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.api.User getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.api.User user) {
		this.user = user;
	}

	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.api.Account getAccount() {
		return this.account;
	}

	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.api.Account account) {
		this.account = account;
	}

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
	 * Gets value for attribute host
	 */
	public com.soffid.iam.am.api.Host getHost() {
		return this.host;
	}

	/**
	 * Sets value for attribute host
	 */
	public void setHost(com.soffid.iam.am.api.Host host) {
		this.host = host;
	}

	/**
	 * Gets value for attribute clientHost
	 */
	public com.soffid.iam.am.api.Host getClientHost() {
		return this.clientHost;
	}

	/**
	 * Sets value for attribute clientHost
	 */
	public void setClientHost(com.soffid.iam.am.api.Host clientHost) {
		this.clientHost = clientHost;
	}

	/**
	 * Gets value for attribute centinelPort
	 */
	public int getCentinelPort() {
		return this.centinelPort;
	}

	/**
	 * Sets value for attribute centinelPort
	 */
	public void setCentinelPort(int centinelPort) {
		this.centinelPort = centinelPort;
	}

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
	 * Gets value for attribute timeStamp
	 */
	public java.sql.Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * Sets value for attribute timeStamp
	 */
	public void setTimeStamp(java.sql.Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets value for attribute clientVersion
	 */
	public int getClientVersion() {
		return this.clientVersion;
	}

	/**
	 * Sets value for attribute clientVersion
	 */
	public void setClientVersion(int clientVersion) {
		this.clientVersion = clientVersion;
	}

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
	 * Gets value for attribute kerberosContext
	 */
	public org.ietf.jgss.GSSContext getKerberosContext() {
		return this.kerberosContext;
	}

	/**
	 * Sets value for attribute kerberosContext
	 */
	public void setKerberosContext(org.ietf.jgss.GSSContext kerberosContext) {
		this.kerberosContext = kerberosContext;
	}

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
	 * Gets value for attribute alternativeMethodAvailable
	 */
	public boolean isAlternativeMethodAvailable() {
		return this.alternativeMethodAvailable;
	}

	/**
	 * Sets value for attribute alternativeMethodAvailable
	 */
	public void setAlternativeMethodAvailable(boolean alternativeMethodAvailable) {
		this.alternativeMethodAvailable = alternativeMethodAvailable;
	}

	/**
	 * Gets value for attribute resendAvailable
	 */
	public boolean isResendAvailable() {
		return this.resendAvailable;
	}

	/**
	 * Sets value for attribute resendAvailable
	 */
	public void setResendAvailable(boolean resendAvailable) {
		this.resendAvailable = resendAvailable;
	}

	/**
	 * Gets value for attribute additionalData
	 */
	public java.lang.Object getAdditionalData() {
		return this.additionalData;
	}

	/**
	 * Sets value for attribute additionalData
	 */
	public void setAdditionalData(java.lang.Object additionalData) {
		this.additionalData = additionalData;
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[password: ");
		b.append (this.password);
		b.append (", type: ");
		b.append (this.type);
		b.append (", user: ");
		b.append (this.user);
		b.append (", account: ");
		b.append (this.account);
		b.append (", userKey: ");
		b.append (this.userKey);
		b.append (", host: ");
		b.append (this.host);
		b.append (", clientHost: ");
		b.append (this.clientHost);
		b.append (", centinelPort: ");
		b.append (this.centinelPort);
		b.append (", otpHandler: ");
		b.append (this.otpHandler);
		b.append (", cardNumber: ");
		b.append (this.cardNumber);
		b.append (", cell: ");
		b.append (this.cell);
		b.append (", value: ");
		b.append (this.value);
		b.append (", timeStamp: ");
		b.append (this.timeStamp);
		b.append (", clientVersion: ");
		b.append (this.clientVersion);
		b.append (", kerberosDomain: ");
		b.append (this.kerberosDomain);
		b.append (", challengeId: ");
		b.append (this.challengeId);
		b.append (", kerberosContext: ");
		b.append (this.kerberosContext);
		b.append (", domain: ");
		b.append (this.domain);
		b.append (", closeOldSessions: ");
		b.append (this.closeOldSessions);
		b.append (", silent: ");
		b.append (this.silent);
		b.append (", alternativeMethodAvailable: ");
		b.append (this.alternativeMethodAvailable);
		b.append (", resendAvailable: ");
		b.append (this.resendAvailable);
		b.append (", additionalData: ");
		b.append (this.additionalData);
		b.append (", identityProvider: ");
		b.append (this.identityProvider);
		b.append ("]");
		return b.toString();
	}

}
