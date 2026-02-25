//
// (c) 2014 Soffid
//
//

package com.soffid.iam.doc.model;

/**
 *  Entity DocSign
 */

public abstract class DocSign {

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
	 * Attribute signType
	 */
	private java.lang.String signType;
	/**
	 * Gets value for attribute signType
	 */
	public java.lang.String getSignType() {
		return this.signType;
	}
	/**
	 * Sets value for attribute signType
	 */
	public void setSignType(java.lang.String signType) {
		this.signType = signType;
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
	 * Attribute timestamp
	 */
	private java.util.Date timestamp;
	/**
	 * Gets value for attribute timestamp
	 */
	public java.util.Date getTimestamp() {
		return this.timestamp;
	}
	/**
	 * Sets value for attribute timestamp
	 */
	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Attribute bpmDocument
	 */
	private com.soffid.iam.doc.model.DocumentEntity bpmDocument;
	/**
	 * Gets value for attribute bpmDocument
	 */
	public com.soffid.iam.doc.model.DocumentEntity getBpmDocument() {
		return this.bpmDocument;
	}
	/**
	 * Sets value for attribute bpmDocument
	 */
	public void setBpmDocument(com.soffid.iam.doc.model.DocumentEntity bpmDocument) {
		this.bpmDocument = bpmDocument;
	}
	/**
	 * Returns <code>true</code> if the argument is an DocSign instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof DocSign))
		{
			return false;
		}
		final DocSign that = (DocSign)object;
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
