//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject TranslatedLabel
 **/
public class TranslatedLabel

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
	 * Attribute language

	 */
	private java.lang.String language;

	/**
	 * Attribute label

	 */
	private java.lang.String label;

	/**
	 * Attribute customObjectType

	 */
	private java.lang.Long customObjectType;

	/**
	 * Attribute metadata

	 */
	private java.lang.Long metadata;

	/**
	 * Attribute accountMetadata

	 */
	private java.lang.Long accountMetadata;

	public TranslatedLabel()
	{
	}

	public TranslatedLabel(java.lang.Long id, java.lang.String language, java.lang.String label, java.lang.Long customObjectType, java.lang.Long metadata, java.lang.Long accountMetadata)
	{
		super();
		this.id = id;
		this.language = language;
		this.label = label;
		this.customObjectType = customObjectType;
		this.metadata = metadata;
		this.accountMetadata = accountMetadata;
	}

	public TranslatedLabel(java.lang.String language, java.lang.String label)
	{
		super();
		this.language = language;
		this.label = label;
	}

	public TranslatedLabel(TranslatedLabel otherBean)
	{
		this(otherBean.id, otherBean.language, otherBean.label, otherBean.customObjectType, otherBean.metadata, otherBean.accountMetadata);
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
	 * Gets value for attribute language
	 */
	public java.lang.String getLanguage() {
		return this.language;
	}

	/**
	 * Sets value for attribute language
	 */
	public void setLanguage(java.lang.String language) {
		this.language = language;
	}

	/**
	 * Gets value for attribute label
	 */
	public java.lang.String getLabel() {
		return this.label;
	}

	/**
	 * Sets value for attribute label
	 */
	public void setLabel(java.lang.String label) {
		this.label = label;
	}

	/**
	 * Gets value for attribute customObjectType
	 */
	public java.lang.Long getCustomObjectType() {
		return this.customObjectType;
	}

	/**
	 * Sets value for attribute customObjectType
	 */
	public void setCustomObjectType(java.lang.Long customObjectType) {
		this.customObjectType = customObjectType;
	}

	/**
	 * Gets value for attribute metadata
	 */
	public java.lang.Long getMetadata() {
		return this.metadata;
	}

	/**
	 * Sets value for attribute metadata
	 */
	public void setMetadata(java.lang.Long metadata) {
		this.metadata = metadata;
	}

	/**
	 * Gets value for attribute accountMetadata
	 */
	public java.lang.Long getAccountMetadata() {
		return this.accountMetadata;
	}

	/**
	 * Sets value for attribute accountMetadata
	 */
	public void setAccountMetadata(java.lang.Long accountMetadata) {
		this.accountMetadata = accountMetadata;
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
		b.append (", language: ");
		b.append (this.language);
		b.append (", label: ");
		b.append (this.label);
		b.append (", customObjectType: ");
		b.append (this.customObjectType);
		b.append (", metadata: ");
		b.append (this.metadata);
		b.append (", accountMetadata: ");
		b.append (this.accountMetadata);
		b.append ("]");
		return b.toString();
	}

}
