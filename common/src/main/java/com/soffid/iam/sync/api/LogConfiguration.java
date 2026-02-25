//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject LogConfiguration
 **/
public class LogConfiguration

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
	 * Attribute debug

	 */
	private boolean debug;

	/**
	 * Attribute numRows

	 */
	private long numRows;

	/**
	 * Attribute maxFiles

	 */
	private long maxFiles;

	public LogConfiguration()
	{
	}

	public LogConfiguration(java.lang.String name, boolean debug, long numRows, long maxFiles)
	{
		super();
		this.name = name;
		this.debug = debug;
		this.numRows = numRows;
		this.maxFiles = maxFiles;
	}

	public LogConfiguration(LogConfiguration otherBean)
	{
		this(otherBean.name, otherBean.debug, otherBean.numRows, otherBean.maxFiles);
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
	 * Gets value for attribute debug
	 */
	public boolean isDebug() {
		return this.debug;
	}

	/**
	 * Sets value for attribute debug
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * Gets value for attribute numRows
	 */
	public long getNumRows() {
		return this.numRows;
	}

	/**
	 * Sets value for attribute numRows
	 */
	public void setNumRows(long numRows) {
		this.numRows = numRows;
	}

	/**
	 * Gets value for attribute maxFiles
	 */
	public long getMaxFiles() {
		return this.maxFiles;
	}

	/**
	 * Sets value for attribute maxFiles
	 */
	public void setMaxFiles(long maxFiles) {
		this.maxFiles = maxFiles;
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
		b.append (", debug: ");
		b.append (this.debug);
		b.append (", numRows: ");
		b.append (this.numRows);
		b.append (", maxFiles: ");
		b.append (this.maxFiles);
		b.append ("]");
		return b.toString();
	}

}
