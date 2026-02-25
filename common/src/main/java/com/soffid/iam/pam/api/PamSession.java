//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject PamSession
 **/
public class PamSession

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.String id;

	/**
	 * Attribute user

	 */
	private java.lang.String user;

	/**
	 * Attribute accountName

	 */
	private java.lang.String accountName;

	/**
	 * Attribute jumpServerGroup

	 */
	private java.lang.String jumpServerGroup;

	/**
	 * Attribute serverUrl

	 */
	private java.lang.String serverUrl;

	/**
	 * Attribute path

	 */
	private java.lang.String path;

	/**
	 * Attribute chapters

	 */
	private java.util.List<java.lang.Long> chapters;

	/**
	 * Attribute serverStart

	 */
	private java.util.Date serverStart;

	/**
	 * Attribute serverEnd

	 */
	private java.util.Date serverEnd;

	/**
	 * Attribute bookmarks

	 */
	private java.util.List<java.lang.Long> bookmarks;

	public PamSession()
	{
	}

	public PamSession(java.lang.String id, java.lang.String user, java.lang.String accountName, java.lang.String jumpServerGroup, java.lang.String serverUrl, java.lang.String path, java.util.List<java.lang.Long> chapters, java.util.Date serverStart, java.util.Date serverEnd, java.util.List<java.lang.Long> bookmarks)
	{
		super();
		this.id = id;
		this.user = user;
		this.accountName = accountName;
		this.jumpServerGroup = jumpServerGroup;
		this.serverUrl = serverUrl;
		this.path = path;
		this.chapters = chapters;
		this.serverStart = serverStart;
		this.serverEnd = serverEnd;
		this.bookmarks = bookmarks;
	}

	public PamSession(PamSession otherBean)
	{
		this(otherBean.id, otherBean.user, otherBean.accountName, otherBean.jumpServerGroup, otherBean.serverUrl, otherBean.path, otherBean.chapters, otherBean.serverStart, otherBean.serverEnd, otherBean.bookmarks);
	}

	/**
	 * Gets value for attribute id
	 */
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * Gets value for attribute user
	 */
	public java.lang.String getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(java.lang.String user) {
		this.user = user;
	}

	/**
	 * Gets value for attribute accountName
	 */
	public java.lang.String getAccountName() {
		return this.accountName;
	}

	/**
	 * Sets value for attribute accountName
	 */
	public void setAccountName(java.lang.String accountName) {
		this.accountName = accountName;
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
	 * Gets value for attribute serverUrl
	 */
	public java.lang.String getServerUrl() {
		return this.serverUrl;
	}

	/**
	 * Sets value for attribute serverUrl
	 */
	public void setServerUrl(java.lang.String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * Gets value for attribute path
	 */
	public java.lang.String getPath() {
		return this.path;
	}

	/**
	 * Sets value for attribute path
	 */
	public void setPath(java.lang.String path) {
		this.path = path;
	}

	/**
	 * Gets value for attribute chapters
	 */
	public java.util.List<java.lang.Long> getChapters() {
		return this.chapters;
	}

	/**
	 * Sets value for attribute chapters
	 */
	public void setChapters(java.util.List<java.lang.Long> chapters) {
		this.chapters = chapters;
	}

	/**
	 * Gets value for attribute serverStart
	 */
	public java.util.Date getServerStart() {
		return this.serverStart;
	}

	/**
	 * Sets value for attribute serverStart
	 */
	public void setServerStart(java.util.Date serverStart) {
		this.serverStart = serverStart;
	}

	/**
	 * Gets value for attribute serverEnd
	 */
	public java.util.Date getServerEnd() {
		return this.serverEnd;
	}

	/**
	 * Sets value for attribute serverEnd
	 */
	public void setServerEnd(java.util.Date serverEnd) {
		this.serverEnd = serverEnd;
	}

	/**
	 * Gets value for attribute bookmarks
	 */
	public java.util.List<java.lang.Long> getBookmarks() {
		return this.bookmarks;
	}

	/**
	 * Sets value for attribute bookmarks
	 */
	public void setBookmarks(java.util.List<java.lang.Long> bookmarks) {
		this.bookmarks = bookmarks;
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
		b.append (", user: ");
		b.append (this.user);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", jumpServerGroup: ");
		b.append (this.jumpServerGroup);
		b.append (", serverUrl: ");
		b.append (this.serverUrl);
		b.append (", path: ");
		b.append (this.path);
		b.append (", chapters: ");
		b.append (this.chapters);
		b.append (", serverStart: ");
		b.append (this.serverStart);
		b.append (", serverEnd: ");
		b.append (this.serverEnd);
		b.append (", bookmarks: ");
		b.append (this.bookmarks);
		b.append ("]");
		return b.toString();
	}

}
