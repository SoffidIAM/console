//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity SoDRoleEntity
 */

public abstract class SoDRoleEntity {

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
	 * Attribute rule
	 */
	private com.soffid.iam.rc.model.SoDRuleEntity rule;
	/**
	 * Gets value for attribute rule
	 */
	public com.soffid.iam.rc.model.SoDRuleEntity getRule() {
		return this.rule;
	}
	/**
	 * Sets value for attribute rule
	 */
	public void setRule(com.soffid.iam.rc.model.SoDRuleEntity rule) {
		this.rule = rule;
	}
	/**
	 * Attribute columns

	 */
	private java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> columns =  new java.util.HashSet<com.soffid.iam.rc.model.SoDRuleMatrixEntity>();
	/**
	 * Gets value for attribute columns
	 */
	public java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> getColumns() {
		return this.columns;
	}
	/**
	 * Sets value for attribute columns
	 */
	public void setColumns(java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> columns) {
		this.columns = columns;
	}
	/**
	 * Attribute rows

	 */
	private java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> rows =  new java.util.HashSet<com.soffid.iam.rc.model.SoDRuleMatrixEntity>();
	/**
	 * Gets value for attribute rows
	 */
	public java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> getRows() {
		return this.rows;
	}
	/**
	 * Sets value for attribute rows
	 */
	public void setRows(java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> rows) {
		this.rows = rows;
	}
	/**
	 * Returns <code>true</code> if the argument is an SoDRoleEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof SoDRoleEntity))
		{
			return false;
		}
		final SoDRoleEntity that = (SoDRoleEntity)object;
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
