//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject AccessTreeAuthorization
 **/
public class AccessTreeAuthorization

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
	 * Attribute authorizationLevelDescription

	 */
	private java.lang.String authorizationLevelDescription;

	/**
	 * Attribute accessTreeId

	 */
	private java.lang.Long accessTreeId;

	/**
	 * Attribute authorizationEntityType

	 */
	private java.lang.String authorizationEntityType;

	/**
	 * Attribute authorizationEntityId

	 */
	private java.lang.Long authorizationEntityId;

	/**
	 * Attribute authorizedEntityDescription

	 */
	private java.lang.String authorizedEntityDescription;

	/**
	 * Attribute authorizedEntityCode

	 */
	private java.lang.String authorizedEntityCode;

	public AccessTreeAuthorization()
	{
	}

	public AccessTreeAuthorization(java.lang.Long id, java.lang.String authorizationLevelDescription, java.lang.Long accessTreeId, java.lang.String authorizationEntityType, java.lang.Long authorizationEntityId, java.lang.String authorizedEntityDescription, java.lang.String authorizedEntityCode)
	{
		super();
		this.id = id;
		this.authorizationLevelDescription = authorizationLevelDescription;
		this.accessTreeId = accessTreeId;
		this.authorizationEntityType = authorizationEntityType;
		this.authorizationEntityId = authorizationEntityId;
		this.authorizedEntityDescription = authorizedEntityDescription;
		this.authorizedEntityCode = authorizedEntityCode;
	}

	public AccessTreeAuthorization(java.lang.String authorizationLevelDescription, java.lang.String authorizationEntityType, java.lang.String authorizedEntityDescription, java.lang.String authorizedEntityCode)
	{
		super();
		this.authorizationLevelDescription = authorizationLevelDescription;
		this.authorizationEntityType = authorizationEntityType;
		this.authorizedEntityDescription = authorizedEntityDescription;
		this.authorizedEntityCode = authorizedEntityCode;
	}

	public AccessTreeAuthorization(AccessTreeAuthorization otherBean)
	{
		this(otherBean.id, otherBean.authorizationLevelDescription, otherBean.accessTreeId, otherBean.authorizationEntityType, otherBean.authorizationEntityId, otherBean.authorizedEntityDescription, otherBean.authorizedEntityCode);
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
	 * Gets value for attribute authorizationLevelDescription
	 */
	public java.lang.String getAuthorizationLevelDescription() {
		return this.authorizationLevelDescription;
	}

	/**
	 * Sets value for attribute authorizationLevelDescription
	 */
	public void setAuthorizationLevelDescription(java.lang.String authorizationLevelDescription) {
		this.authorizationLevelDescription = authorizationLevelDescription;
	}

	/**
	 * Gets value for attribute accessTreeId
	 */
	public java.lang.Long getAccessTreeId() {
		return this.accessTreeId;
	}

	/**
	 * Sets value for attribute accessTreeId
	 */
	public void setAccessTreeId(java.lang.Long accessTreeId) {
		this.accessTreeId = accessTreeId;
	}

	/**
	 * Gets value for attribute authorizationEntityType
	 */
	public java.lang.String getAuthorizationEntityType() {
		return this.authorizationEntityType;
	}

	/**
	 * Sets value for attribute authorizationEntityType
	 */
	public void setAuthorizationEntityType(java.lang.String authorizationEntityType) {
		this.authorizationEntityType = authorizationEntityType;
	}

	/**
	 * Gets value for attribute authorizationEntityId
	 */
	public java.lang.Long getAuthorizationEntityId() {
		return this.authorizationEntityId;
	}

	/**
	 * Sets value for attribute authorizationEntityId
	 */
	public void setAuthorizationEntityId(java.lang.Long authorizationEntityId) {
		this.authorizationEntityId = authorizationEntityId;
	}

	/**
	 * Gets value for attribute authorizedEntityDescription
	 */
	public java.lang.String getAuthorizedEntityDescription() {
		return this.authorizedEntityDescription;
	}

	/**
	 * Sets value for attribute authorizedEntityDescription
	 */
	public void setAuthorizedEntityDescription(java.lang.String authorizedEntityDescription) {
		this.authorizedEntityDescription = authorizedEntityDescription;
	}

	/**
	 * Gets value for attribute authorizedEntityCode
	 */
	public java.lang.String getAuthorizedEntityCode() {
		return this.authorizedEntityCode;
	}

	/**
	 * Sets value for attribute authorizedEntityCode
	 */
	public void setAuthorizedEntityCode(java.lang.String authorizedEntityCode) {
		this.authorizedEntityCode = authorizedEntityCode;
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
		b.append (", authorizationLevelDescription: ");
		b.append (this.authorizationLevelDescription);
		b.append (", accessTreeId: ");
		b.append (this.accessTreeId);
		b.append (", authorizationEntityType: ");
		b.append (this.authorizationEntityType);
		b.append (", authorizationEntityId: ");
		b.append (this.authorizationEntityId);
		b.append (", authorizedEntityDescription: ");
		b.append (this.authorizedEntityDescription);
		b.append (", authorizedEntityCode: ");
		b.append (this.authorizedEntityCode);
		b.append ("]");
		return b.toString();
	}

}
