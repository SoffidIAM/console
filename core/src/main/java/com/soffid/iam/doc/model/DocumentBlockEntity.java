//
// (c) 2014 Soffid
//
//

package com.soffid.iam.doc.model;

/**
 *  Entity DocumentBlockEntity
 */

public abstract class DocumentBlockEntity {

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
	 * Attribute path
	 */
	private java.lang.String path;
	/**
	 * Gets value for attribute path
	 */
	public java.lang.String getPath() {
		return this.path;
	}
	/**
	 * Sets value for attribute path
	 */
	public void setPath(java.lang.String path) {
		this.path = path;
	}
	/**
	 * Attribute sequenceNumber
	 */
	private java.lang.Long sequenceNumber;
	/**
	 * Gets value for attribute sequenceNumber
	 */
	public java.lang.Long getSequenceNumber() {
		return this.sequenceNumber;
	}
	/**
	 * Sets value for attribute sequenceNumber
	 */
	public void setSequenceNumber(java.lang.Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * Attribute content
	 */
	private byte[] content;
	/**
	 * Gets value for attribute content
	 */
	public byte[] getContent() {
		return this.content;
	}
	/**
	 * Sets value for attribute content
	 */
	public void setContent(byte[] content) {
		this.content = content;
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
	 * Returns <code>true</code> if the argument is an DocumentBlockEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof DocumentBlockEntity))
		{
			return false;
		}
		final DocumentBlockEntity that = (DocumentBlockEntity)object;
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
