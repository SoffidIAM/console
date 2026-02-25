//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject NetworkIntelligence
 **/
public class NetworkIntelligence

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
	 * Attribute token

	 */
	private java.lang.String token;

	/**
	 * Attribute level

	 */
	private java.lang.String level;

	/**
	 * Attribute start

	 */
	private java.util.Date start;

	/**
	 * Attribute end

	 */
	private java.util.Date end;

	/**
	 * Attribute enabled

	 */
	private java.lang.Boolean enabled;

	public NetworkIntelligence()
	{
	}

	public NetworkIntelligence(java.lang.Long id, java.lang.String token, java.lang.String level, java.util.Date start, java.util.Date end, java.lang.Boolean enabled)
	{
		super();
		this.id = id;
		this.token = token;
		this.level = level;
		this.start = start;
		this.end = end;
		this.enabled = enabled;
	}

	public NetworkIntelligence(java.lang.Long id)
	{
		super();
		this.id = id;
	}

	public NetworkIntelligence(NetworkIntelligence otherBean)
	{
		this(otherBean.id, otherBean.token, otherBean.level, otherBean.start, otherBean.end, otherBean.enabled);
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
	 * Gets value for attribute token
	 */
	public java.lang.String getToken() {
		return this.token;
	}

	/**
	 * Sets value for attribute token
	 */
	public void setToken(java.lang.String token) {
		this.token = token;
	}

	/**
	 * Gets value for attribute level
	 */
	public java.lang.String getLevel() {
		return this.level;
	}

	/**
	 * Sets value for attribute level
	 */
	public void setLevel(java.lang.String level) {
		this.level = level;
	}

	/**
	 * Gets value for attribute start
	 */
	public java.util.Date getStart() {
		return this.start;
	}

	/**
	 * Sets value for attribute start
	 */
	public void setStart(java.util.Date start) {
		this.start = start;
	}

	/**
	 * Gets value for attribute end
	 */
	public java.util.Date getEnd() {
		return this.end;
	}

	/**
	 * Sets value for attribute end
	 */
	public void setEnd(java.util.Date end) {
		this.end = end;
	}

	/**
	 * Gets value for attribute enabled
	 */
	public java.lang.Boolean getEnabled() {
		return this.enabled;
	}

	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(java.lang.Boolean enabled) {
		this.enabled = enabled;
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
		b.append (", token: ");
		b.append (this.token);
		b.append (", level: ");
		b.append (this.level);
		b.append (", start: ");
		b.append (this.start);
		b.append (", end: ");
		b.append (this.end);
		b.append (", enabled: ");
		b.append (this.enabled);
		b.append ("]");
		return b.toString();
	}

}
