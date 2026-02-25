//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject BpmUserProcess
 **/
public class BpmUserProcess

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
	 * Attribute processId

	 */
	private java.lang.Long processId;

	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute finished

	 */
	private java.lang.Boolean finished = false;

	/**
	 * Attribute userNationalId

	 */
	private java.lang.String userNationalId;

	public BpmUserProcess()
	{
	}

	public BpmUserProcess(java.lang.Long id, java.lang.Long processId, java.lang.String userName, java.lang.Boolean finished, java.lang.String userNationalId)
	{
		super();
		this.id = id;
		this.processId = processId;
		this.userName = userName;
		this.finished = finished;
		this.userNationalId = userNationalId;
	}

	public BpmUserProcess(java.lang.Long processId, java.lang.Boolean finished)
	{
		super();
		this.processId = processId;
		this.finished = finished;
	}

	public BpmUserProcess(BpmUserProcess otherBean)
	{
		this(otherBean.id, otherBean.processId, otherBean.userName, otherBean.finished, otherBean.userNationalId);
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
	 * Gets value for attribute processId
	 */
	public java.lang.Long getProcessId() {
		return this.processId;
	}

	/**
	 * Sets value for attribute processId
	 */
	public void setProcessId(java.lang.Long processId) {
		this.processId = processId;
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
	 * Gets value for attribute userName
	 */
	public java.lang.String getUserCode() {
		return this.userName;
	}

	/**
	 * Sets value for attribute userName
	 */
	public void setUserCode(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * Gets value for attribute finished
	 */
	public java.lang.Boolean getFinished() {
		return this.finished;
	}

	/**
	 * Sets value for attribute finished
	 */
	public void setFinished(java.lang.Boolean finished) {
		this.finished = finished;
	}

	/**
	 * Gets value for attribute finished
	 */
	public java.lang.Boolean getTerminated() {
		return this.finished;
	}

	/**
	 * Sets value for attribute finished
	 */
	public void setTerminated(java.lang.Boolean finished) {
		this.finished = finished;
	}

	/**
	 * Gets value for attribute userNationalId
	 */
	public java.lang.String getUserNationalId() {
		return this.userNationalId;
	}

	/**
	 * Sets value for attribute userNationalId
	 */
	public void setUserNationalId(java.lang.String userNationalId) {
		this.userNationalId = userNationalId;
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
		b.append (", processId: ");
		b.append (this.processId);
		b.append (", userName: ");
		b.append (this.userName);
		b.append (", finished: ");
		b.append (this.finished);
		b.append (", userNationalId: ");
		b.append (this.userNationalId);
		b.append ("]");
		return b.toString();
	}

}
