//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity ServerCertificateEntity
 */

public abstract class ServerCertificateEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute server
	 */
	private com.soffid.iam.sync.model.ServerEntity server;
	/**
	 * Gets value for attribute server
	 */
	public com.soffid.iam.sync.model.ServerEntity getServer() {
		return this.server;
	}
	/**
	 * Sets value for attribute server
	 */
	public void setServer(com.soffid.iam.sync.model.ServerEntity server) {
		this.server = server;
	}
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
	 * Attribute cert
	 */
	private byte[] cert;
	/**
	 * Gets value for attribute cert
	 */
	public byte[] getCert() {
		return this.cert;
	}
	/**
	 * Sets value for attribute cert
	 */
	public void setCert(byte[] cert) {
		this.cert = cert;
	}
	/**
	 * Attribute since
	 */
	private java.util.Date since;
	/**
	 * Gets value for attribute since
	 */
	public java.util.Date getSince() {
		return this.since;
	}
	/**
	 * Sets value for attribute since
	 */
	public void setSince(java.util.Date since) {
		this.since = since;
	}
	/**
	 * Attribute until
	 */
	private java.util.Date until;
	/**
	 * Gets value for attribute until
	 */
	public java.util.Date getUntil() {
		return this.until;
	}
	/**
	 * Sets value for attribute until
	 */
	public void setUntil(java.util.Date until) {
		this.until = until;
	}
	/**
	 * Returns <code>true</code> if the argument is an ServerCertificateEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ServerCertificateEntity))
		{
			return false;
		}
		final ServerCertificateEntity that = (ServerCertificateEntity)object;
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
