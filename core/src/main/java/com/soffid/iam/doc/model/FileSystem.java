//
// (c) 2014 Soffid
//
//

package com.soffid.iam.doc.model;

/**
 *  Entity FileSystem
 */

public abstract class FileSystem {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
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
	 * Attribute application
	 */
	private java.lang.String application;
	/**
	 * Gets value for attribute application
	 */
	public java.lang.String getApplication() {
		return this.application;
	}
	/**
	 * Sets value for attribute application
	 */
	public void setApplication(java.lang.String application) {
		this.application = application;
	}
	/**
	 * Attribute year
	 */
	private java.lang.Integer year;
	/**
	 * Gets value for attribute year
	 */
	public java.lang.Integer getYear() {
		return this.year;
	}
	/**
	 * Sets value for attribute year
	 */
	public void setYear(java.lang.Integer year) {
		this.year = year;
	}
	/**
	 * Attribute nextDocNumber
	 */
	private java.lang.Long nextDocNumber;
	/**
	 * Gets value for attribute nextDocNumber
	 */
	public java.lang.Long getNextDocNumber() {
		return this.nextDocNumber;
	}
	/**
	 * Sets value for attribute nextDocNumber
	 */
	public void setNextDocNumber(java.lang.Long nextDocNumber) {
		this.nextDocNumber = nextDocNumber;
	}
	/**
	 * Returns <code>true</code> if the argument is an FileSystem instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof FileSystem))
		{
			return false;
		}
		final FileSystem that = (FileSystem)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
