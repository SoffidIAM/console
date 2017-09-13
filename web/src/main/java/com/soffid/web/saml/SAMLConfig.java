package com.soffid.web.saml;

public class SAMLConfig {
	public boolean classicMethod;
	public boolean samlMethod;
	public boolean trustedAuthentication;
	public String metadata;
	public String idp;
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
}