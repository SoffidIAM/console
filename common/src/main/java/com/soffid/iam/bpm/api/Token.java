//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject Token
 **/
public class Token

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute tokenName

	 */
	private java.lang.String tokenName;

	/**
	 * Attribute processId

	 */
	private long processId;

	/**
	 * Attribute nodeName

	 */
	private java.lang.String nodeName;

	/**
	 * Attribute finished

	 */
	private boolean finished;

	/**
	 * Attribute locked

	 */
	private boolean locked;

	/**
	 * Attribute suspended

	 */
	private boolean suspended;

	public Token()
	{
	}

	public Token(java.lang.String tokenName, long processId, java.lang.String nodeName, boolean finished, boolean locked, boolean suspended)
	{
		super();
		this.tokenName = tokenName;
		this.processId = processId;
		this.nodeName = nodeName;
		this.finished = finished;
		this.locked = locked;
		this.suspended = suspended;
	}

	public Token(long processId, boolean finished, boolean locked, boolean suspended)
	{
		super();
		this.processId = processId;
		this.finished = finished;
		this.locked = locked;
		this.suspended = suspended;
	}

	public Token(Token otherBean)
	{
		this(otherBean.tokenName, otherBean.processId, otherBean.nodeName, otherBean.finished, otherBean.locked, otherBean.suspended);
	}

	/**
	 * Gets value for attribute tokenName
	 */
	public java.lang.String getTokenName() {
		return this.tokenName;
	}

	/**
	 * Sets value for attribute tokenName
	 */
	public void setTokenName(java.lang.String tokenName) {
		this.tokenName = tokenName;
	}

	/**
	 * Gets value for attribute processId
	 */
	public long getProcessId() {
		return this.processId;
	}

	/**
	 * Sets value for attribute processId
	 */
	public void setProcessId(long processId) {
		this.processId = processId;
	}

	/**
	 * Gets value for attribute nodeName
	 */
	public java.lang.String getNodeName() {
		return this.nodeName;
	}

	/**
	 * Sets value for attribute nodeName
	 */
	public void setNodeName(java.lang.String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * Gets value for attribute finished
	 */
	public boolean isFinished() {
		return this.finished;
	}

	/**
	 * Sets value for attribute finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * Gets value for attribute locked
	 */
	public boolean isLocked() {
		return this.locked;
	}

	/**
	 * Sets value for attribute locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets value for attribute suspended
	 */
	public boolean isSuspended() {
		return this.suspended;
	}

	/**
	 * Sets value for attribute suspended
	 */
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[tokenName: ");
		b.append (this.tokenName);
		b.append (", processId: ");
		b.append (this.processId);
		b.append (", nodeName: ");
		b.append (this.nodeName);
		b.append (", finished: ");
		b.append (this.finished);
		b.append (", locked: ");
		b.append (this.locked);
		b.append (", suspended: ");
		b.append (this.suspended);
		b.append ("]");
		return b.toString();
	}

}
