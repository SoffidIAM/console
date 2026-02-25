//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity EntryPointGroupEntity
 */

public abstract class EntryPointGroupEntity {

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
	 * Attribute auhtorizationLevel
	 */
	private java.lang.String auhtorizationLevel;
	/**
	 * Gets value for attribute auhtorizationLevel
	 */
	public java.lang.String getAuhtorizationLevel() {
		return this.auhtorizationLevel;
	}
	/**
	 * Sets value for attribute auhtorizationLevel
	 */
	public void setAuhtorizationLevel(java.lang.String auhtorizationLevel) {
		this.auhtorizationLevel = auhtorizationLevel;
	}
	/**
	 * Attribute entryPoint
	 */
	private com.soffid.iam.am.model.EntryPointEntity entryPoint;
	/**
	 * Gets value for attribute entryPoint
	 */
	public com.soffid.iam.am.model.EntryPointEntity getEntryPoint() {
		return this.entryPoint;
	}
	/**
	 * Sets value for attribute entryPoint
	 */
	public void setEntryPoint(com.soffid.iam.am.model.EntryPointEntity entryPoint) {
		this.entryPoint = entryPoint;
	}
	/**
	 * Attribute group
	 */
	private com.soffid.iam.iga.model.GroupEntity group;
	/**
	 * Gets value for attribute group
	 */
	public com.soffid.iam.iga.model.GroupEntity getGroup() {
		return this.group;
	}
	/**
	 * Sets value for attribute group
	 */
	public void setGroup(com.soffid.iam.iga.model.GroupEntity group) {
		this.group = group;
	}
	/**
	 * Returns <code>true</code> if the argument is an EntryPointGroupEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof EntryPointGroupEntity))
		{
			return false;
		}
		final EntryPointGroupEntity that = (EntryPointGroupEntity)object;
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
