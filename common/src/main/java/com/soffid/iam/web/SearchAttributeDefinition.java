//
// (C) 2020 Soffid
//
//

package com.soffid.iam.web;
/**
 * ValueObject SearchAttributeDefinition
 **/
public class SearchAttributeDefinition

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute localizedName

	 */
	private java.lang.String localizedName;

	/**
	 * Attribute labelName

	 */
	private java.lang.String labelName;

	/**
	 * Attribute type

	 */
	private com.soffid.iam.base.api.TypeEnumeration type;

	/**
	 * Attribute javaType

	 */
	private java.lang.Class javaType;

	/**
	 * Attribute values

	 */
	private java.util.List<java.lang.String> values;

	/**
	 * Attribute labels

	 */
	private java.util.List<java.lang.String> labels;

	public SearchAttributeDefinition()
	{
	}

	public SearchAttributeDefinition(java.lang.String name, java.lang.String localizedName, java.lang.String labelName, com.soffid.iam.base.api.TypeEnumeration type, java.lang.Class javaType, java.util.List<java.lang.String> values, java.util.List<java.lang.String> labels)
	{
		super();
		this.name = name;
		this.localizedName = localizedName;
		this.labelName = labelName;
		this.type = type;
		this.javaType = javaType;
		this.values = values;
		this.labels = labels;
	}

	public SearchAttributeDefinition(java.lang.String name, com.soffid.iam.base.api.TypeEnumeration type, java.lang.Class javaType)
	{
		super();
		this.name = name;
		this.type = type;
		this.javaType = javaType;
	}

	public SearchAttributeDefinition(SearchAttributeDefinition otherBean)
	{
		this(otherBean.name, otherBean.localizedName, otherBean.labelName, otherBean.type, otherBean.javaType, otherBean.values, otherBean.labels);
	}

	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets value for attribute localizedName
	 */
	public java.lang.String getLocalizedName() {
		return this.localizedName;
	}

	/**
	 * Sets value for attribute localizedName
	 */
	public void setLocalizedName(java.lang.String localizedName) {
		this.localizedName = localizedName;
	}

	/**
	 * Gets value for attribute labelName
	 */
	public java.lang.String getLabelName() {
		return this.labelName;
	}

	/**
	 * Sets value for attribute labelName
	 */
	public void setLabelName(java.lang.String labelName) {
		this.labelName = labelName;
	}

	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.base.api.TypeEnumeration getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.base.api.TypeEnumeration type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute javaType
	 */
	public java.lang.Class getJavaType() {
		return this.javaType;
	}

	/**
	 * Sets value for attribute javaType
	 */
	public void setJavaType(java.lang.Class javaType) {
		this.javaType = javaType;
	}

	/**
	 * Gets value for attribute values
	 */
	public java.util.List<java.lang.String> getValues() {
		return this.values;
	}

	/**
	 * Sets value for attribute values
	 */
	public void setValues(java.util.List<java.lang.String> values) {
		this.values = values;
	}

	/**
	 * Gets value for attribute labels
	 */
	public java.util.List<java.lang.String> getLabels() {
		return this.labels;
	}

	/**
	 * Sets value for attribute labels
	 */
	public void setLabels(java.util.List<java.lang.String> labels) {
		this.labels = labels;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[name: ");
		b.append (this.name);
		b.append (", localizedName: ");
		b.append (this.localizedName);
		b.append (", labelName: ");
		b.append (this.labelName);
		b.append (", type: ");
		b.append (this.type);
		b.append (", javaType: ");
		b.append (this.javaType);
		b.append (", values: ");
		b.append (this.values);
		b.append (", labels: ");
		b.append (this.labels);
		b.append ("]");
		return b.toString();
	}

}
