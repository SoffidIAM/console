//
// (c) 2014 Soffid
//
//

package com.soffid.iam.doc.model;

/**
 *  Entity DocumentEntity
 */

public abstract class DocumentEntity {

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
	 * Attribute mimeType
	 */
	private java.lang.String mimeType;
	/**
	 * Gets value for attribute mimeType
	 */
	public java.lang.String getMimeType() {
		return this.mimeType;
	}
	/**
	 * Sets value for attribute mimeType
	 */
	public void setMimeType(java.lang.String mimeType) {
		this.mimeType = mimeType;
	}
	/**
	 * Attribute externalName
	 */
	private java.lang.String externalName;
	/**
	 * Gets value for attribute externalName
	 */
	public java.lang.String getExternalName() {
		return this.externalName;
	}
	/**
	 * Sets value for attribute externalName
	 */
	public void setExternalName(java.lang.String externalName) {
		this.externalName = externalName;
	}
	/**
	 * Attribute hash
	 */
	private java.lang.String hash;
	/**
	 * Gets value for attribute hash
	 */
	public java.lang.String getHash() {
		return this.hash;
	}
	/**
	 * Sets value for attribute hash
	 */
	public void setHash(java.lang.String hash) {
		this.hash = hash;
	}
	/**
	 * Attribute fsPath
	 */
	private java.lang.String fsPath;
	/**
	 * Gets value for attribute fsPath
	 */
	public java.lang.String getFsPath() {
		return this.fsPath;
	}
	/**
	 * Sets value for attribute fsPath
	 */
	public void setFsPath(java.lang.String fsPath) {
		this.fsPath = fsPath;
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
	 * Attribute signs

	 */
	private java.util.Collection<com.soffid.iam.doc.model.DocSign> signs =  new java.util.HashSet<com.soffid.iam.doc.model.DocSign>();
	/**
	 * Gets value for attribute signs
	 */
	public java.util.Collection<com.soffid.iam.doc.model.DocSign> getSigns() {
		return this.signs;
	}
	/**
	 * Sets value for attribute signs
	 */
	public void setSigns(java.util.Collection<com.soffid.iam.doc.model.DocSign> signs) {
		this.signs = signs;
	}
	/**
	 * Operation getYear
	 * @return
	**/
	 public abstract int getYear();

	/**
	 * Operation getApplication
	 * @return
	**/
	 public abstract java.lang.String getApplication();

	/**
	 * Returns <code>true</code> if the argument is an DocumentEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof DocumentEntity))
		{
			return false;
		}
		final DocumentEntity that = (DocumentEntity)object;
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
