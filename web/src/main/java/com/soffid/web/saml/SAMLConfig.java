package com.soffid.web.saml;

public class SAMLConfig {
	public boolean classicMethod;
	public boolean samlMethod;
	public boolean trustedAuthentication;
	public String metadata;
	public String idp;
	public int cache;
	public boolean alwaysTrust;
	public String hostName;
	
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
}