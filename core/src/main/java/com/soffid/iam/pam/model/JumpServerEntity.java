//
// (c) 2014 Soffid
//
//

package com.soffid.iam.pam.model;

/**
 *  Entity JumpServerEntity
 */

public abstract class JumpServerEntity {

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
	 * Attribute url
	 */
	private java.lang.String url;
	/**
	 * Gets value for attribute url
	 */
	public java.lang.String getUrl() {
		return this.url;
	}
	/**
	 * Sets value for attribute url
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	/**
	 * Attribute jumpServerGroup
	 */
	private com.soffid.iam.pam.model.JumpServerGroupEntity jumpServerGroup;
	/**
	 * Gets value for attribute jumpServerGroup
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity getJumpServerGroup() {
		return this.jumpServerGroup;
	}
	/**
	 * Sets value for attribute jumpServerGroup
	 */
	public void setJumpServerGroup(com.soffid.iam.pam.model.JumpServerGroupEntity jumpServerGroup) {
		this.jumpServerGroup = jumpServerGroup;
	}
	/**
	 * Returns <code>true</code> if the argument is an JumpServerEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof JumpServerEntity))
		{
			return false;
		}
		final JumpServerEntity that = (JumpServerEntity)object;
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
