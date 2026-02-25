//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ProcessHierarchyEntity
 */

public abstract class ProcessHierarchyEntity {

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
	 * Attribute parentProcess
	 */
	private java.lang.Long parentProcess;
	/**
	 * Gets value for attribute parentProcess
	 */
	public java.lang.Long getParentProcess() {
		return this.parentProcess;
	}
	/**
	 * Sets value for attribute parentProcess
	 */
	public void setParentProcess(java.lang.Long parentProcess) {
		this.parentProcess = parentProcess;
	}
	/**
	 * Attribute childProcess
	 */
	private java.lang.Long childProcess;
	/**
	 * Gets value for attribute childProcess
	 */
	public java.lang.Long getChildProcess() {
		return this.childProcess;
	}
	/**
	 * Sets value for attribute childProcess
	 */
	public void setChildProcess(java.lang.Long childProcess) {
		this.childProcess = childProcess;
	}
	/**
	 * Returns <code>true</code> if the argument is an ProcessHierarchyEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ProcessHierarchyEntity))
		{
			return false;
		}
		final ProcessHierarchyEntity that = (ProcessHierarchyEntity)object;
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
