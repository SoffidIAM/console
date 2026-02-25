//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity NoticeEntity
 */

public abstract class NoticeEntity {

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
	 * Attribute modificationDate
	 */
	private java.util.Date modificationDate;
	/**
	 * Gets value for attribute modificationDate
	 */
	public java.util.Date getModificationDate() {
		return this.modificationDate;
	}
	/**
	 * Sets value for attribute modificationDate
	 */
	public void setModificationDate(java.util.Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	/**
	 * Attribute information
	 */
	private java.lang.String information;
	/**
	 * Gets value for attribute information
	 */
	public java.lang.String getInformation() {
		return this.information;
	}
	/**
	 * Sets value for attribute information
	 */
	public void setInformation(java.lang.String information) {
		this.information = information;
	}
	/**
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
	}
	/**
	 * Attribute role
	 */
	private com.soffid.iam.iga.model.RoleEntity role;
	/**
	 * Gets value for attribute role
	 */
	public com.soffid.iam.iga.model.RoleEntity getRole() {
		return this.role;
	}
	/**
	 * Sets value for attribute role
	 */
	public void setRole(com.soffid.iam.iga.model.RoleEntity role) {
		this.role = role;
	}
	/**
	 * Attribute application
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity application;
	/**
	 * Gets value for attribute application
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getApplication() {
		return this.application;
	}
	/**
	 * Sets value for attribute application
	 */
	public void setApplication(com.soffid.iam.iga.model.InformationSystemEntity application) {
		this.application = application;
	}
	/**
	 * Returns <code>true</code> if the argument is an NoticeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof NoticeEntity))
		{
			return false;
		}
		final NoticeEntity that = (NoticeEntity)object;
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
