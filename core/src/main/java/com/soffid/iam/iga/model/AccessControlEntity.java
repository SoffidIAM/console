//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity AccessControlEntity
 * Access control rules for Oracle agent
 */

public abstract class AccessControlEntity {

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
	 * Attribute genericUser
	 */
	private java.lang.String genericUser;
	/**
	 * Gets value for attribute genericUser
	 */
	public java.lang.String getGenericUser() {
		return this.genericUser;
	}
	/**
	 * Sets value for attribute genericUser
	 */
	public void setGenericUser(java.lang.String genericUser) {
		this.genericUser = genericUser;
	}
	/**
	 * Attribute genericHost
	 */
	private java.lang.String genericHost;
	/**
	 * Gets value for attribute genericHost
	 */
	public java.lang.String getGenericHost() {
		return this.genericHost;
	}
	/**
	 * Sets value for attribute genericHost
	 */
	public void setGenericHost(java.lang.String genericHost) {
		this.genericHost = genericHost;
	}
	/**
	 * Attribute program
	 */
	private java.lang.String program;
	/**
	 * Gets value for attribute program
	 */
	public java.lang.String getProgram() {
		return this.program;
	}
	/**
	 * Sets value for attribute program
	 */
	public void setProgram(java.lang.String program) {
		this.program = program;
	}
	/**
	 * Attribute role
	 */
	private com.soffid.iam.iga.model.RoleEntity role;
	/**
	 * Gets value for attribute role
	 */
	public com.soffid.iam.iga.model.RoleEntity getRole() {
		return this.role;
	}
	/**
	 * Sets value for attribute role
	 */
	public void setRole(com.soffid.iam.iga.model.RoleEntity role) {
		this.role = role;
	}
	/**
	 * Attribute agent
	 */
	private com.soffid.iam.iga.model.SystemEntity agent;
	/**
	 * Gets value for attribute agent
	 */
	public com.soffid.iam.iga.model.SystemEntity getAgent() {
		return this.agent;
	}
	/**
	 * Sets value for attribute agent
	 */
	public void setAgent(com.soffid.iam.iga.model.SystemEntity agent) {
		this.agent = agent;
	}
	/**
	 * Attribute propagatedIPs
	 */
	private java.lang.String propagatedIPs;
	/**
	 * Gets value for attribute propagatedIPs
	 */
	public java.lang.String getPropagatedIPs() {
		return this.propagatedIPs;
	}
	/**
	 * Sets value for attribute propagatedIPs
	 */
	public void setPropagatedIPs(java.lang.String propagatedIPs) {
		this.propagatedIPs = propagatedIPs;
	}
	/**
	 * Attribute comments
	 */
	private java.lang.String comments;
	/**
	 * Gets value for attribute comments
	 */
	public java.lang.String getComments() {
		return this.comments;
	}
	/**
	 * Sets value for attribute comments
	 */
	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}
	/**
	 * Returns <code>true</code> if the argument is an AccessControlEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AccessControlEntity))
		{
			return false;
		}
		final AccessControlEntity that = (AccessControlEntity)object;
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
