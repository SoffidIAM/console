//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject AccessControl
 **/
public class AccessControl

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
	 * Attribute agentId

	 */
	private java.lang.Long agentId;

	/**
	 * Attribute agentName

	 */
	private java.lang.String agentName;

	/**
	 * Attribute roleDescription

	 */
	private java.lang.String roleDescription;

	/**
	 * Attribute roleId

	 */
	private java.lang.Long roleId;

	/**
	 * Attribute hostName

	 */
	private java.lang.String hostName;

	/**
	 * Attribute hostId

	 */
	private java.lang.Long hostId;

	/**
	 * Attribute program

	 */
	private java.lang.String program;

	/**
	 * Attribute genericUser

	 */
	private java.lang.String genericUser;

	/**
	 * Attribute genericHost

	 */
	private java.lang.String genericHost;

	/**
	 * Attribute remoteIp

	 */
	private java.lang.String remoteIp;

	/**
	 * Attribute comments

	 */
	private java.lang.String comments;

	public AccessControl()
	{
	}

	public AccessControl(java.lang.Long id, java.lang.Long agentId, java.lang.String agentName, java.lang.String roleDescription, java.lang.Long roleId, java.lang.String hostName, java.lang.Long hostId, java.lang.String program, java.lang.String genericUser, java.lang.String genericHost, java.lang.String remoteIp, java.lang.String comments)
	{
		super();
		this.id = id;
		this.agentId = agentId;
		this.agentName = agentName;
		this.roleDescription = roleDescription;
		this.roleId = roleId;
		this.hostName = hostName;
		this.hostId = hostId;
		this.program = program;
		this.genericUser = genericUser;
		this.genericHost = genericHost;
		this.remoteIp = remoteIp;
		this.comments = comments;
	}

	public AccessControl(AccessControl otherBean)
	{
		this(otherBean.id, otherBean.agentId, otherBean.agentName, otherBean.roleDescription, otherBean.roleId, otherBean.hostName, otherBean.hostId, otherBean.program, otherBean.genericUser, otherBean.genericHost, otherBean.remoteIp, otherBean.comments);
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
	 * Gets value for attribute agentId
	 */
	public java.lang.Long getAgentId() {
		return this.agentId;
	}

	/**
	 * Sets value for attribute agentId
	 */
	public void setAgentId(java.lang.Long agentId) {
		this.agentId = agentId;
	}

	/**
	 * Gets value for attribute agentName
	 */
	public java.lang.String getAgentName() {
		return this.agentName;
	}

	/**
	 * Sets value for attribute agentName
	 */
	public void setAgentName(java.lang.String agentName) {
		this.agentName = agentName;
	}

	/**
	 * Gets value for attribute roleDescription
	 */
	public java.lang.String getRoleDescription() {
		return this.roleDescription;
	}

	/**
	 * Sets value for attribute roleDescription
	 */
	public void setRoleDescription(java.lang.String roleDescription) {
		this.roleDescription = roleDescription;
	}

	/**
	 * Gets value for attribute roleId
	 */
	public java.lang.Long getRoleId() {
		return this.roleId;
	}

	/**
	 * Sets value for attribute roleId
	 */
	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}

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
	 * Gets value for attribute hostId
	 */
	public java.lang.Long getHostId() {
		return this.hostId;
	}

	/**
	 * Sets value for attribute hostId
	 */
	public void setHostId(java.lang.Long hostId) {
		this.hostId = hostId;
	}

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
	 * Gets value for attribute remoteIp
	 */
	public java.lang.String getRemoteIp() {
		return this.remoteIp;
	}

	/**
	 * Sets value for attribute remoteIp
	 */
	public void setRemoteIp(java.lang.String remoteIp) {
		this.remoteIp = remoteIp;
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", agentId: ");
		b.append (this.agentId);
		b.append (", agentName: ");
		b.append (this.agentName);
		b.append (", roleDescription: ");
		b.append (this.roleDescription);
		b.append (", roleId: ");
		b.append (this.roleId);
		b.append (", hostName: ");
		b.append (this.hostName);
		b.append (", hostId: ");
		b.append (this.hostId);
		b.append (", program: ");
		b.append (this.program);
		b.append (", genericUser: ");
		b.append (this.genericUser);
		b.append (", genericHost: ");
		b.append (this.genericHost);
		b.append (", remoteIp: ");
		b.append (this.remoteIp);
		b.append (", comments: ");
		b.append (this.comments);
		b.append ("]");
		return b.toString();
	}

}
