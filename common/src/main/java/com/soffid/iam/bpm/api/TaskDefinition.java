//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject TaskDefinition
 **/
public class TaskDefinition

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Constant PRIORITY_HIGHEST

	 */
	public static final int PRIORITY_HIGHEST = 1;

	/**
	 * Constant PRIORITY_HIGH

	 */
	public static final int PRIORITY_HIGH = 2;

	/**
	 * Constant PRIORITY_NORMAL

	 */
	public static final int PRIORITY_NORMAL = 3;

	/**
	 * Constant PRIORITY_LOW

	 */
	public static final int PRIORITY_LOW = 4;

	/**
	 * Constant PRIORITY_LOWEST

	 */
	public static final int PRIORITY_LOWEST = 5;

	/**
	 * Attribute id

	 */
	private long id;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute blocking

	 */
	private boolean blocking;

	/**
	 * Attribute signalling

	 */
	private boolean signalling;

	public TaskDefinition()
	{
	}

	public TaskDefinition(long id, java.lang.String name, java.lang.String description, boolean blocking, boolean signalling)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.blocking = blocking;
		this.signalling = signalling;
	}

	public TaskDefinition(long id)
	{
		super();
		this.id = id;
	}

	public TaskDefinition(TaskDefinition otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.blocking, otherBean.signalling);
	}

	/**
	 * Gets value for attribute id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(long id) {
		this.id = id;
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
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets value for attribute blocking
	 */
	public boolean isBlocking() {
		return this.blocking;
	}

	/**
	 * Sets value for attribute blocking
	 */
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	/**
	 * Gets value for attribute signalling
	 */
	public boolean isSignalling() {
		return this.signalling;
	}

	/**
	 * Sets value for attribute signalling
	 */
	public void setSignalling(boolean signalling) {
		this.signalling = signalling;
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
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", blocking: ");
		b.append (this.blocking);
		b.append (", signalling: ");
		b.append (this.signalling);
		b.append ("]");
		return b.toString();
	}

}
