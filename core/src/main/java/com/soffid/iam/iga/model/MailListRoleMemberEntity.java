//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity MailListRoleMemberEntity
 */

public abstract class MailListRoleMemberEntity {

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
	 * Attribute mailList
	 * Mail list container
	 */
	private com.soffid.iam.iga.model.MailListEntity mailList;
	/**
	 * Gets value for attribute mailList
	 */
	public com.soffid.iam.iga.model.MailListEntity getMailList() {
		return this.mailList;
	}
	/**
	 * Sets value for attribute mailList
	 */
	public void setMailList(com.soffid.iam.iga.model.MailListEntity mailList) {
		this.mailList = mailList;
	}
	/**
	 * Attribute role
	 * Role included in mail list
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
	 * Attribute informationSystemScope
	 * Optional application scope for role grant
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity informationSystemScope;
	/**
	 * Gets value for attribute informationSystemScope
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getInformationSystemScope() {
		return this.informationSystemScope;
	}
	/**
	 * Sets value for attribute informationSystemScope
	 */
	public void setInformationSystemScope(com.soffid.iam.iga.model.InformationSystemEntity informationSystemScope) {
		this.informationSystemScope = informationSystemScope;
	}
	/**
	 * Attribute groupScope
	 * Optional group scope for role grant
	 */
	private com.soffid.iam.iga.model.GroupEntity groupScope;
	/**
	 * Gets value for attribute groupScope
	 */
	public com.soffid.iam.iga.model.GroupEntity getGroupScope() {
		return this.groupScope;
	}
	/**
	 * Sets value for attribute groupScope
	 */
	public void setGroupScope(com.soffid.iam.iga.model.GroupEntity groupScope) {
		this.groupScope = groupScope;
	}
	/**
	 * Attribute domainValueScope
	 * Optional application value scope for role grant
	 */
	private com.soffid.iam.iga.model.DomainValueEntity domainValueScope;
	/**
	 * Gets value for attribute domainValueScope
	 */
	public com.soffid.iam.iga.model.DomainValueEntity getDomainValueScope() {
		return this.domainValueScope;
	}
	/**
	 * Sets value for attribute domainValueScope
	 */
	public void setDomainValueScope(com.soffid.iam.iga.model.DomainValueEntity domainValueScope) {
		this.domainValueScope = domainValueScope;
	}
	/**
	 * Returns <code>true</code> if the argument is an MailListRoleMemberEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof MailListRoleMemberEntity))
		{
			return false;
		}
		final MailListRoleMemberEntity that = (MailListRoleMemberEntity)object;
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
