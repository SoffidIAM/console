//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject Comment
 **/
public class Comment

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute message

	 */
	private java.lang.String message;

	/**
	 * Attribute actor

	 */
	private java.lang.String actor;

	/**
	 * Attribute node

	 */
	private java.lang.String node;

	/**
	 * Attribute time

	 */
	private java.util.Date time;

	public Comment()
	{
	}

	public Comment(java.lang.String message, java.lang.String actor, java.lang.String node, java.util.Date time)
	{
		super();
		this.message = message;
		this.actor = actor;
		this.node = node;
		this.time = time;
	}

	public Comment(Comment otherBean)
	{
		this(otherBean.message, otherBean.actor, otherBean.node, otherBean.time);
	}

	/**
	 * Gets value for attribute message
	 */
	public java.lang.String getMessage() {
		return this.message;
	}

	/**
	 * Sets value for attribute message
	 */
	public void setMessage(java.lang.String message) {
		this.message = message;
	}

	/**
	 * Gets value for attribute actor
	 */
	public java.lang.String getActor() {
		return this.actor;
	}

	/**
	 * Sets value for attribute actor
	 */
	public void setActor(java.lang.String actor) {
		this.actor = actor;
	}

	/**
	 * Gets value for attribute node
	 */
	public java.lang.String getNode() {
		return this.node;
	}

	/**
	 * Sets value for attribute node
	 */
	public void setNode(java.lang.String node) {
		this.node = node;
	}

	/**
	 * Gets value for attribute time
	 */
	public java.util.Date getTime() {
		return this.time;
	}

	/**
	 * Sets value for attribute time
	 */
	public void setTime(java.util.Date time) {
		this.time = time;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[message: ");
		b.append (this.message);
		b.append (", actor: ");
		b.append (this.actor);
		b.append (", node: ");
		b.append (this.node);
		b.append (", time: ");
		b.append (this.time);
		b.append ("]");
		return b.toString();
	}

}
