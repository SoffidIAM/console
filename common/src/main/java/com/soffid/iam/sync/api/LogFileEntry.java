//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject LogFileEntry
 **/
public class LogFileEntry

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute hour

	 */
	private java.lang.String hour;

	/**
	 * Attribute level

	 */
	private java.lang.String level;

	/**
	 * Attribute thread

	 */
	private java.lang.String thread;

	/**
	 * Attribute source

	 */
	private java.lang.String source;

	/**
	 * Attribute message

	 */
	private java.lang.String message;

	public LogFileEntry()
	{
	}

	public LogFileEntry(java.lang.String hour, java.lang.String level, java.lang.String thread, java.lang.String source, java.lang.String message)
	{
		super();
		this.hour = hour;
		this.level = level;
		this.thread = thread;
		this.source = source;
		this.message = message;
	}

	public LogFileEntry(LogFileEntry otherBean)
	{
		this(otherBean.hour, otherBean.level, otherBean.thread, otherBean.source, otherBean.message);
	}

	/**
	 * Gets value for attribute hour
	 */
	public java.lang.String getHour() {
		return this.hour;
	}

	/**
	 * Sets value for attribute hour
	 */
	public void setHour(java.lang.String hour) {
		this.hour = hour;
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
	 * Gets value for attribute thread
	 */
	public java.lang.String getThread() {
		return this.thread;
	}

	/**
	 * Sets value for attribute thread
	 */
	public void setThread(java.lang.String thread) {
		this.thread = thread;
	}

	/**
	 * Gets value for attribute source
	 */
	public java.lang.String getSource() {
		return this.source;
	}

	/**
	 * Sets value for attribute source
	 */
	public void setSource(java.lang.String source) {
		this.source = source;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[hour: ");
		b.append (this.hour);
		b.append (", level: ");
		b.append (this.level);
		b.append (", thread: ");
		b.append (this.thread);
		b.append (", source: ");
		b.append (this.source);
		b.append (", message: ");
		b.append (this.message);
		b.append ("]");
		return b.toString();
	}

}
