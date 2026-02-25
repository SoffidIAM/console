//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject AuthorizationRole
 * Contains a low-level authorization granted to a role
 **/
public class AuthorizationRole

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
	 * Attribute authorization

	 */
	private java.lang.String authorization;

	/**
	 * Attribute role

	 */
	private com.soffid.iam.iga.api.Role role;

	/**
	 * Attribute userRoleValueDomain

	 */
	private java.util.Collection<com.soffid.iam.iga.api.DomainValue> userRoleValueDomain;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute domainType

	 */
	private java.lang.String domainType;

	/**
	 * Attribute businessGroupScope

	 */
	private java.lang.String businessGroupScope;

	/**
	 * Attribute scope

	 */
	private java.lang.String scope;

	/**
	 * Attribute inherit

	 */
	private java.lang.String inherit;

	public AuthorizationRole()
	{
	}

	public AuthorizationRole(java.lang.Long id, java.lang.String authorization, com.soffid.iam.iga.api.Role role, java.util.Collection<com.soffid.iam.iga.api.DomainValue> userRoleValueDomain, java.lang.String description, java.lang.String domainType, java.lang.String businessGroupScope, java.lang.String scope, java.lang.String inherit)
	{
		super();
		this.id = id;
		this.authorization = authorization;
		this.role = role;
		this.userRoleValueDomain = userRoleValueDomain;
		this.description = description;
		this.domainType = domainType;
		this.businessGroupScope = businessGroupScope;
		this.scope = scope;
		this.inherit = inherit;
	}

	public AuthorizationRole(java.lang.String authorization, com.soffid.iam.iga.api.Role role)
	{
		super();
		this.authorization = authorization;
		this.role = role;
	}

	public AuthorizationRole(AuthorizationRole otherBean)
	{
		this(otherBean.id, otherBean.authorization, otherBean.role, otherBean.userRoleValueDomain, otherBean.description, otherBean.domainType, otherBean.businessGroupScope, otherBean.scope, otherBean.inherit);
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
	 * Gets value for attribute authorization
	 */
	public java.lang.String getAuthorization() {
		return this.authorization;
	}

	/**
	 * Sets value for attribute authorization
	 */
	public void setAuthorization(java.lang.String authorization) {
		this.authorization = authorization;
	}

	/**
	 * Gets value for attribute role
	 */
	public com.soffid.iam.iga.api.Role getRole() {
		return this.role;
	}

	/**
	 * Sets value for attribute role
	 */
	public void setRole(com.soffid.iam.iga.api.Role role) {
		this.role = role;
	}

	/**
	 * Gets value for attribute userRoleValueDomain
	 */
	public java.util.Collection<com.soffid.iam.iga.api.DomainValue> getUserRoleValueDomain() {
		return this.userRoleValueDomain;
	}

	/**
	 * Sets value for attribute userRoleValueDomain
	 */
	public void setUserRoleValueDomain(java.util.Collection<com.soffid.iam.iga.api.DomainValue> userRoleValueDomain) {
		this.userRoleValueDomain = userRoleValueDomain;
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
	 * Gets value for attribute domainType
	 */
	public java.lang.String getDomainType() {
		return this.domainType;
	}

	/**
	 * Sets value for attribute domainType
	 */
	public void setDomainType(java.lang.String domainType) {
		this.domainType = domainType;
	}

	/**
	 * Gets value for attribute businessGroupScope
	 */
	public java.lang.String getBusinessGroupScope() {
		return this.businessGroupScope;
	}

	/**
	 * Sets value for attribute businessGroupScope
	 */
	public void setBusinessGroupScope(java.lang.String businessGroupScope) {
		this.businessGroupScope = businessGroupScope;
	}

	/**
	 * Gets value for attribute scope
	 */
	public java.lang.String getScope() {
		return this.scope;
	}

	/**
	 * Sets value for attribute scope
	 */
	public void setScope(java.lang.String scope) {
		this.scope = scope;
	}

	/**
	 * Gets value for attribute inherit
	 */
	public java.lang.String getInherit() {
		return this.inherit;
	}

	/**
	 * Sets value for attribute inherit
	 */
	public void setInherit(java.lang.String inherit) {
		this.inherit = inherit;
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
		b.append (", authorization: ");
		b.append (this.authorization);
		b.append (", role: ");
		b.append (this.role);
		b.append (", userRoleValueDomain: ");
		b.append (this.userRoleValueDomain);
		b.append (", description: ");
		b.append (this.description);
		b.append (", domainType: ");
		b.append (this.domainType);
		b.append (", businessGroupScope: ");
		b.append (this.businessGroupScope);
		b.append (", scope: ");
		b.append (this.scope);
		b.append (", inherit: ");
		b.append (this.inherit);
		b.append ("]");
		return b.toString();
	}

}
