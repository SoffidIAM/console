//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject AccessTreeExecution
 **/
public class AccessTreeExecution

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
	 * Attribute scope

	 */
	private java.lang.String scope;

	/**
	 * Attribute content

	 */
	private java.lang.String content;

	/**
	 * Attribute executionTypeCode

	 */
	private java.lang.String executionTypeCode;

	/**
	 * Attribute typeMimeExecution

	 */
	private java.lang.String typeMimeExecution;

	/**
	 * Attribute AccessTreeId

	 */
	private java.lang.Long AccessTreeId;

	public AccessTreeExecution()
	{
	}

	public AccessTreeExecution(java.lang.Long id, java.lang.String scope, java.lang.String content, java.lang.String executionTypeCode, java.lang.String typeMimeExecution, java.lang.Long AccessTreeId)
	{
		super();
		this.id = id;
		this.scope = scope;
		this.content = content;
		this.executionTypeCode = executionTypeCode;
		this.typeMimeExecution = typeMimeExecution;
		this.AccessTreeId = AccessTreeId;
	}

	public AccessTreeExecution(java.lang.String scope)
	{
		super();
		this.scope = scope;
	}

	public AccessTreeExecution(AccessTreeExecution otherBean)
	{
		this(otherBean.id, otherBean.scope, otherBean.content, otherBean.executionTypeCode, otherBean.typeMimeExecution, otherBean.AccessTreeId);
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
	 * Gets value for attribute scope
	 */
	public java.lang.String getScope() {
		return this.scope;
	}

	/**
	 * Sets value for attribute scope
	 */
	public void setScope(java.lang.String scope) {
		this.scope = scope;
	}

	/**
	 * Gets value for attribute content
	 */
	public java.lang.String getContent() {
		return this.content;
	}

	/**
	 * Sets value for attribute content
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 * Gets value for attribute executionTypeCode
	 */
	public java.lang.String getExecutionTypeCode() {
		return this.executionTypeCode;
	}

	/**
	 * Sets value for attribute executionTypeCode
	 */
	public void setExecutionTypeCode(java.lang.String executionTypeCode) {
		this.executionTypeCode = executionTypeCode;
	}

	/**
	 * Gets value for attribute typeMimeExecution
	 */
	public java.lang.String getTypeMimeExecution() {
		return this.typeMimeExecution;
	}

	/**
	 * Sets value for attribute typeMimeExecution
	 */
	public void setTypeMimeExecution(java.lang.String typeMimeExecution) {
		this.typeMimeExecution = typeMimeExecution;
	}

	/**
	 * Gets value for attribute AccessTreeId
	 */
	public java.lang.Long getAccessTreeId() {
		return this.AccessTreeId;
	}

	/**
	 * Sets value for attribute AccessTreeId
	 */
	public void setAccessTreeId(java.lang.Long AccessTreeId) {
		this.AccessTreeId = AccessTreeId;
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
		b.append (", scope: ");
		b.append (this.scope);
		b.append (", content: ");
		b.append (this.content);
		b.append (", executionTypeCode: ");
		b.append (this.executionTypeCode);
		b.append (", typeMimeExecution: ");
		b.append (this.typeMimeExecution);
		b.append (", AccessTreeId: ");
		b.append (this.AccessTreeId);
		b.append ("]");
		return b.toString();
	}

}
