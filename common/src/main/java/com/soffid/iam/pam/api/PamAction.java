//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject PamAction
 **/
public class PamAction

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute policyName

	 */
	private java.lang.String policyName;

	/**
	 * Attribute ruleName

	 */
	private java.lang.String ruleName;

	/**
	 * Attribute actions

	 */
	private java.util.List<com.soffid.iam.pam.model.PamActionType> actions;

	/**
	 * Attribute author

	 */
	private java.lang.String author;

	/**
	 * Attribute date

	 */
	private java.util.Date date;

	public PamAction()
	{
	}

	public PamAction(java.lang.String policyName, java.lang.String ruleName, java.util.List<com.soffid.iam.pam.model.PamActionType> actions, java.lang.String author, java.util.Date date)
	{
		super();
		this.policyName = policyName;
		this.ruleName = ruleName;
		this.actions = actions;
		this.author = author;
		this.date = date;
	}

	public PamAction(java.lang.String policyName, java.lang.String ruleName)
	{
		super();
		this.policyName = policyName;
		this.ruleName = ruleName;
	}

	public PamAction(PamAction otherBean)
	{
		this(otherBean.policyName, otherBean.ruleName, otherBean.actions, otherBean.author, otherBean.date);
	}

	/**
	 * Gets value for attribute policyName
	 */
	public java.lang.String getPolicyName() {
		return this.policyName;
	}

	/**
	 * Sets value for attribute policyName
	 */
	public void setPolicyName(java.lang.String policyName) {
		this.policyName = policyName;
	}

	/**
	 * Gets value for attribute ruleName
	 */
	public java.lang.String getRuleName() {
		return this.ruleName;
	}

	/**
	 * Sets value for attribute ruleName
	 */
	public void setRuleName(java.lang.String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * Gets value for attribute actions
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionType> getActions() {
		return this.actions;
	}

	/**
	 * Sets value for attribute actions
	 */
	public void setActions(java.util.List<com.soffid.iam.pam.model.PamActionType> actions) {
		this.actions = actions;
	}

	/**
	 * Gets value for attribute author
	 */
	public java.lang.String getAuthor() {
		return this.author;
	}

	/**
	 * Sets value for attribute author
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}

	/**
	 * Gets value for attribute date
	 */
	public java.util.Date getDate() {
		return this.date;
	}

	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[policyName: ");
		b.append (this.policyName);
		b.append (", ruleName: ");
		b.append (this.ruleName);
		b.append (", actions: ");
		b.append (this.actions);
		b.append (", author: ");
		b.append (this.author);
		b.append (", date: ");
		b.append (this.date);
		b.append ("]");
		return b.toString();
	}

}
