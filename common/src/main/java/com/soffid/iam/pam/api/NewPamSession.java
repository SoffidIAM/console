//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject NewPamSession
 **/
public class NewPamSession

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute sessionId

	 */
	private java.lang.String sessionId;

	/**
	 * Attribute jumpServerGroup

	 */
	private java.lang.String jumpServerGroup;

	/**
	 * Attribute url

	 */
	private java.net.URL url;

	public NewPamSession()
	{
	}

	public NewPamSession(java.lang.String sessionId, java.lang.String jumpServerGroup, java.net.URL url)
	{
		super();
		this.sessionId = sessionId;
		this.jumpServerGroup = jumpServerGroup;
		this.url = url;
	}

	public NewPamSession(java.lang.String sessionId)
	{
		super();
		this.sessionId = sessionId;
	}

	public NewPamSession(NewPamSession otherBean)
	{
		this(otherBean.sessionId, otherBean.jumpServerGroup, otherBean.url);
	}

	/**
	 * Gets value for attribute sessionId
	 */
	public java.lang.String getSessionId() {
		return this.sessionId;
	}

	/**
	 * Sets value for attribute sessionId
	 */
	public void setSessionId(java.lang.String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets value for attribute jumpServerGroup
	 */
	public java.lang.String getJumpServerGroup() {
		return this.jumpServerGroup;
	}

	/**
	 * Sets value for attribute jumpServerGroup
	 */
	public void setJumpServerGroup(java.lang.String jumpServerGroup) {
		this.jumpServerGroup = jumpServerGroup;
	}

	/**
	 * Gets value for attribute url
	 */
	public java.net.URL getUrl() {
		return this.url;
	}

	/**
	 * Sets value for attribute url
	 */
	public void setUrl(java.net.URL url) {
		this.url = url;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[sessionId: ");
		b.append (this.sessionId);
		b.append (", jumpServerGroup: ");
		b.append (this.jumpServerGroup);
		b.append (", url: ");
		b.append (this.url);
		b.append ("]");
		return b.toString();
	}

}
