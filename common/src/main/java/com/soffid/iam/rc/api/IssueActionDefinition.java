//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject IssueActionDefinition
 **/
public class IssueActionDefinition

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
	 * Attribute label

	 */
	private java.lang.String label;

	/**
	 * Attribute parameters

	 */
	private java.util.List<com.soffid.iam.base.api.DataType> parameters;

	/**
	 * Attribute issueTypes

	 */
	private java.util.List<java.lang.String> issueTypes;

	/**
	 * Attribute handler

	 */
	private java.lang.String handler;

	public IssueActionDefinition()
	{
	}

	public IssueActionDefinition(java.lang.String name, java.lang.String label, java.util.List<com.soffid.iam.base.api.DataType> parameters, java.util.List<java.lang.String> issueTypes, java.lang.String handler)
	{
		super();
		this.name = name;
		this.label = label;
		this.parameters = parameters;
		this.issueTypes = issueTypes;
		this.handler = handler;
	}

	public IssueActionDefinition(IssueActionDefinition otherBean)
	{
		this(otherBean.name, otherBean.label, otherBean.parameters, otherBean.issueTypes, otherBean.handler);
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
	 * Gets value for attribute parameters
	 */
	public java.util.List<com.soffid.iam.base.api.DataType> getParameters() {
		return this.parameters;
	}

	/**
	 * Sets value for attribute parameters
	 */
	public void setParameters(java.util.List<com.soffid.iam.base.api.DataType> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Gets value for attribute issueTypes
	 */
	public java.util.List<java.lang.String> getIssueTypes() {
		return this.issueTypes;
	}

	/**
	 * Sets value for attribute issueTypes
	 */
	public void setIssueTypes(java.util.List<java.lang.String> issueTypes) {
		this.issueTypes = issueTypes;
	}

	/**
	 * Gets value for attribute handler
	 */
	public java.lang.String getHandler() {
		return this.handler;
	}

	/**
	 * Sets value for attribute handler
	 */
	public void setHandler(java.lang.String handler) {
		this.handler = handler;
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
		b.append (", label: ");
		b.append (this.label);
		b.append (", parameters: ");
		b.append (this.parameters);
		b.append (", issueTypes: ");
		b.append (this.issueTypes);
		b.append (", handler: ");
		b.append (this.handler);
		b.append ("]");
		return b.toString();
	}

}
