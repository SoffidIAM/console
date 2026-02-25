//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject ExtranetCardContent
 **/
public class ExtranetCardContent

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute rowColumn

	 */
	private java.lang.String rowColumn;

	/**
	 * Attribute value

	 */
	private java.lang.String value;

	/**
	 * Attribute lastUsedDate

	 */
	private java.util.Calendar lastUsedDate;

	/**
	 * Attribute row

	 */
	private java.lang.String row;

	/**
	 * Attribute column

	 */
	private java.lang.String column;

	public ExtranetCardContent()
	{
	}

	public ExtranetCardContent(java.lang.String rowColumn, java.lang.String value, java.util.Calendar lastUsedDate, java.lang.String row, java.lang.String column)
	{
		super();
		this.rowColumn = rowColumn;
		this.value = value;
		this.lastUsedDate = lastUsedDate;
		this.row = row;
		this.column = column;
	}

	public ExtranetCardContent(ExtranetCardContent otherBean)
	{
		this(otherBean.rowColumn, otherBean.value, otherBean.lastUsedDate, otherBean.row, otherBean.column);
	}

	/**
	 * Gets value for attribute rowColumn
	 */
	public java.lang.String getRowColumn() {
		return this.rowColumn;
	}

	/**
	 * Sets value for attribute rowColumn
	 */
	public void setRowColumn(java.lang.String rowColumn) {
		this.rowColumn = rowColumn;
	}

	/**
	 * Gets value for attribute value
	 */
	public java.lang.String getValue() {
		return this.value;
	}

	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}

	/**
	 * Gets value for attribute lastUsedDate
	 */
	public java.util.Calendar getLastUsedDate() {
		return this.lastUsedDate;
	}

	/**
	 * Sets value for attribute lastUsedDate
	 */
	public void setLastUsedDate(java.util.Calendar lastUsedDate) {
		this.lastUsedDate = lastUsedDate;
	}

	/**
	 * Gets value for attribute row
	 */
	public java.lang.String getRow() {
		return this.row;
	}

	/**
	 * Sets value for attribute row
	 */
	public void setRow(java.lang.String row) {
		this.row = row;
	}

	/**
	 * Gets value for attribute column
	 */
	public java.lang.String getColumn() {
		return this.column;
	}

	/**
	 * Sets value for attribute column
	 */
	public void setColumn(java.lang.String column) {
		this.column = column;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[rowColumn: ");
		b.append (this.rowColumn);
		b.append (", value: ");
		b.append (this.value);
		b.append (", lastUsedDate: ");
		b.append (this.lastUsedDate);
		b.append (", row: ");
		b.append (this.row);
		b.append (", column: ");
		b.append (this.column);
		b.append ("]");
		return b.toString();
	}

}
