//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject DisableObjectRule
 **/
public class DisableObjectRule

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute criteria

	 */
	private java.lang.String criteria;

	/**
	 * Attribute parameter

	 */
	private java.lang.Integer parameter;

	/**
	 * Attribute action

	 */
	private java.lang.String action;

	/**
	 * Attribute emailCopy

	 */
	private java.lang.String emailCopy;

	/**
	 * Attribute emailSubject

	 */
	private java.lang.String emailSubject;

	/**
	 * Attribute emailBody

	 */
	private java.lang.String emailBody;

	public DisableObjectRule()
	{
	}

	public DisableObjectRule(java.lang.String criteria, java.lang.Integer parameter, java.lang.String action, java.lang.String emailCopy, java.lang.String emailSubject, java.lang.String emailBody)
	{
		super();
		this.criteria = criteria;
		this.parameter = parameter;
		this.action = action;
		this.emailCopy = emailCopy;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
	}

	public DisableObjectRule(DisableObjectRule otherBean)
	{
		this(otherBean.criteria, otherBean.parameter, otherBean.action, otherBean.emailCopy, otherBean.emailSubject, otherBean.emailBody);
	}

	/**
	 * Gets value for attribute criteria
	 */
	public java.lang.String getCriteria() {
		return this.criteria;
	}

	/**
	 * Sets value for attribute criteria
	 */
	public void setCriteria(java.lang.String criteria) {
		this.criteria = criteria;
	}

	/**
	 * Gets value for attribute parameter
	 */
	public java.lang.Integer getParameter() {
		return this.parameter;
	}

	/**
	 * Sets value for attribute parameter
	 */
	public void setParameter(java.lang.Integer parameter) {
		this.parameter = parameter;
	}

	/**
	 * Gets value for attribute action
	 */
	public java.lang.String getAction() {
		return this.action;
	}

	/**
	 * Sets value for attribute action
	 */
	public void setAction(java.lang.String action) {
		this.action = action;
	}

	/**
	 * Gets value for attribute emailCopy
	 */
	public java.lang.String getEmailCopy() {
		return this.emailCopy;
	}

	/**
	 * Sets value for attribute emailCopy
	 */
	public void setEmailCopy(java.lang.String emailCopy) {
		this.emailCopy = emailCopy;
	}

	/**
	 * Gets value for attribute emailSubject
	 */
	public java.lang.String getEmailSubject() {
		return this.emailSubject;
	}

	/**
	 * Sets value for attribute emailSubject
	 */
	public void setEmailSubject(java.lang.String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * Gets value for attribute emailBody
	 */
	public java.lang.String getEmailBody() {
		return this.emailBody;
	}

	/**
	 * Sets value for attribute emailBody
	 */
	public void setEmailBody(java.lang.String emailBody) {
		this.emailBody = emailBody;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[criteria: ");
		b.append (this.criteria);
		b.append (", parameter: ");
		b.append (this.parameter);
		b.append (", action: ");
		b.append (this.action);
		b.append (", emailCopy: ");
		b.append (this.emailCopy);
		b.append (", emailSubject: ");
		b.append (this.emailSubject);
		b.append (", emailBody: ");
		b.append (this.emailBody);
		b.append ("]");
		return b.toString();
	}

}
