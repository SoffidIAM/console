package com.soffid.web.saml;

import java.util.List;

public class SAMLConfig {
	public boolean classicMethod;
	public boolean samlMethod;
	public boolean trustedAuthentication;
	public String metadata;
	public String idp;
	public int cache;
	public boolean alwaysTrust;
	public String hostName;
	protected boolean enableLinotp;
	protected String linotpUser;
	protected String linotpPassword;
	protected String linotpServer;
	protected String linotpUserDomain;
	protected String requireToken;
	protected String optionalToken;
	protected Long tokenTimeout;
	public boolean maintenanceMode;
	protected String principalAttribute;
	protected boolean samlDebug;
	String motd;
	public Integer sessionTimeout;
	boolean userPasswordWebservice;
	boolean jwtWebservice;
	String jwtConfigurationUrl;
	String jwtIssuer;
	List<String> jwtAudience;
	
	public boolean isClassicMethod() {
		return classicMethod;
	}
	public void setClassicMethod(boolean classicMethod) {
		this.classicMethod = classicMethod;
	}
	public boolean isSamlMethod() {
		return samlMethod;
	}
	public void setSamlMethod(boolean samlMethod) {
		this.samlMethod = samlMethod;
	}
	public boolean isTrustedAuthentication() {
		return trustedAuthentication;
	}
	public void setTrustedAuthentication(boolean trustedAuthentication) {
		this.trustedAuthentication = trustedAuthentication;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public String getIdp() {
		return idp;
	}
	public void setIdp(String idp) {
		this.idp = idp;
	}
	
	public int getCache() {
		return cache;
	}
	
	public void setCache(int cache) {
		this.cache = cache;
	}
	
	public boolean isAlwaysTrust() {
		return alwaysTrust;
	}
	
	public void setAlwaysTrust(boolean alwaysTrust) {
		this.alwaysTrust = alwaysTrust;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public boolean isEnableLinotp() {
		return enableLinotp;
	}
	
	public void setEnableLinotp(boolean enableLinotp) {
		this.enableLinotp = enableLinotp;
	}
	
	public String getLinotpUser() {
		return linotpUser;
	}
	
	public void setLinotpUser(String linotpUser) {
		this.linotpUser = linotpUser;
	}
	
	public String getLinotpPassword() {
		return linotpPassword;
	}
	
	public void setLinotpPassword(String linotpPassword) {
		this.linotpPassword = linotpPassword;
	}
	
	public String getLinotpServer() {
		return linotpServer;
	}
	
	public void setLinotpServer(String linotpServer) {
		this.linotpServer = linotpServer;
	}
	
	public String getLinotpUserDomain() {
		return linotpUserDomain;
	}
	
	public void setLinotpUserDomain(String linotpUserDomain) {
		this.linotpUserDomain = linotpUserDomain;
	}
	
	public String getRequireToken() {
		return requireToken;
	}
	
	public void setRequireToken(String requireToken) {
		this.requireToken = requireToken;
	}
	
	public String getOptionalToken() {
		return optionalToken;
	}
	
	public void setOptionalToken(String optionalToken) {
		this.optionalToken = optionalToken;
	}
	
	public Long getTokenTimeout() {
		return tokenTimeout;
	}
	
	public void setTokenTimeout(Long tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}
	
	public boolean isMaintenanceMode() {
		return maintenanceMode;
	}
	
	public void setMaintenanceMode(boolean maintenanceMode) {
		this.maintenanceMode = maintenanceMode;
	}
	
	public String getPrincipalAttribute() {
		return principalAttribute;
	}
	
	public void setPrincipalAttribute(String principalAttribute) {
		this.principalAttribute = principalAttribute;
	}
	
	public boolean isSamlDebug() {
		return samlDebug;
	}
	
	public void setSamlDebug(boolean samlDebug) {
		this.samlDebug = samlDebug;
	}
	
	public String getMotd() {
		return motd;
	}
	
	public void setMotd(String motd) {
		this.motd = motd;
	}
	
	public Integer getSessionTimeout() {
		return sessionTimeout;
	}
	
	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	
	public boolean isUserPasswordWebservice() {
		return userPasswordWebservice;
	}
	
	public void setUserPasswordWebservice(boolean userPasswordWebservice) {
		this.userPasswordWebservice = userPasswordWebservice;
	}
	
	public boolean isJwtWebservice() {
		return jwtWebservice;
	}
	
	public void setJwtWebservice(boolean jwtWebservice) {
		this.jwtWebservice = jwtWebservice;
	}
	
	public String getJwtConfigurationUrl() {
		return jwtConfigurationUrl;
	}
	
	public void setJwtConfigurationUrl(String jwtConfigurationUrl) {
		this.jwtConfigurationUrl = jwtConfigurationUrl;
	}
	
	public String getJwtIssuer() {
		return jwtIssuer;
	}
	
	public void setJwtIssuer(String jwtIssuer) {
		this.jwtIssuer = jwtIssuer;
	}
	
	public List<String> getJwtAudience() {
		return jwtAudience;
	}
	
	public void setJwtAudience(List<String> jwtAudience) {
		this.jwtAudience = jwtAudience;
	}
}