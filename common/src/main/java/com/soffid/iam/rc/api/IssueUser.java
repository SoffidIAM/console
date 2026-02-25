//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject IssueUser
 **/
public class IssueUser

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute action

	 */
	private com.soffid.iam.iga.api.EventUserAction action;

	/**
	 * Attribute userId

	 */
	private java.lang.Long userId;

	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute externalId

	 */
	private java.lang.String externalId;

	public IssueUser()
	{
	}

	public IssueUser(com.soffid.iam.iga.api.EventUserAction action, java.lang.Long userId, java.lang.String userName, java.lang.String externalId)
	{
		super();
		this.action = action;
		this.userId = userId;
		this.userName = userName;
		this.externalId = externalId;
	}

	public IssueUser(com.soffid.iam.iga.api.EventUserAction action, java.lang.Long userId, java.lang.String userName)
	{
		super();
		this.action = action;
		this.userId = userId;
		this.userName = userName;
	}

	public IssueUser(IssueUser otherBean)
	{
		this(otherBean.action, otherBean.userId, otherBean.userName, otherBean.externalId);
	}

	/**
	 * Gets value for attribute action
	 */
	public com.soffid.iam.iga.api.EventUserAction getAction() {
		return this.action;
	}

	/**
	 * Sets value for attribute action
	 */
	public void setAction(com.soffid.iam.iga.api.EventUserAction action) {
		this.action = action;
	}

	/**
	 * Gets value for attribute userId
	 */
	public java.lang.Long getUserId() {
		return this.userId;
	}

	/**
	 * Sets value for attribute userId
	 */
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets value for attribute userName
	 */
	public java.lang.String getUserName() {
		return this.userName;
	}

	/**
	 * Sets value for attribute userName
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * Gets value for attribute externalId
	 */
	public java.lang.String getExternalId() {
		return this.externalId;
	}

	/**
	 * Sets value for attribute externalId
	 */
	public void setExternalId(java.lang.String externalId) {
		this.externalId = externalId;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[action: ");
		b.append (this.action);
		b.append (", userId: ");
		b.append (this.userId);
		b.append (", userName: ");
		b.append (this.userName);
		b.append (", externalId: ");
		b.append (this.externalId);
		b.append ("]");
		return b.toString();
	}

}
