//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject FilteredGroupVO
 **/
public class FilteredGroupVO

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
	 * Attribute ignored

	 */
	private java.lang.Boolean ignored;

	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute type

	 */
	private java.lang.Integer type;

	/**
	 * Attribute pooledActorsStyleName

	 */
	private java.lang.String pooledActorsStyleName;

	public FilteredGroupVO()
	{
	}

	public FilteredGroupVO(java.lang.String name, java.lang.Boolean ignored, java.lang.String userName, java.lang.Integer type, java.lang.String pooledActorsStyleName)
	{
		super();
		this.name = name;
		this.ignored = ignored;
		this.userName = userName;
		this.type = type;
		this.pooledActorsStyleName = pooledActorsStyleName;
	}

	public FilteredGroupVO(FilteredGroupVO otherBean)
	{
		this(otherBean.name, otherBean.ignored, otherBean.userName, otherBean.type, otherBean.pooledActorsStyleName);
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
	 * Gets value for attribute ignored
	 */
	public java.lang.Boolean getIgnored() {
		return this.ignored;
	}

	/**
	 * Sets value for attribute ignored
	 */
	public void setIgnored(java.lang.Boolean ignored) {
		this.ignored = ignored;
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
	 * Gets value for attribute type
	 */
	public java.lang.Integer getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute pooledActorsStyleName
	 */
	public java.lang.String getPooledActorsStyleName() {
		return this.pooledActorsStyleName;
	}

	/**
	 * Sets value for attribute pooledActorsStyleName
	 */
	public void setPooledActorsStyleName(java.lang.String pooledActorsStyleName) {
		this.pooledActorsStyleName = pooledActorsStyleName;
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
		b.append (", ignored: ");
		b.append (this.ignored);
		b.append (", userName: ");
		b.append (this.userName);
		b.append (", type: ");
		b.append (this.type);
		b.append (", pooledActorsStyleName: ");
		b.append (this.pooledActorsStyleName);
		b.append ("]");
		return b.toString();
	}

}
