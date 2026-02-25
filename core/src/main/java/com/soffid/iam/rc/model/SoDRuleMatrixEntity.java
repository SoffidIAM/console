//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity SoDRuleMatrixEntity
 */

public abstract class SoDRuleMatrixEntity {

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
	 * Attribute risk
	 */
	private com.soffid.iam.rc.api.SoDRisk risk;
	/**
	 * Gets value for attribute risk
	 */
	public com.soffid.iam.rc.api.SoDRisk getRisk() {
		return this.risk;
	}
	/**
	 * Sets value for attribute risk
	 */
	public void setRisk(com.soffid.iam.rc.api.SoDRisk risk) {
		this.risk = risk;
	}
	/**
	 * Attribute row
	 */
	private com.soffid.iam.rc.model.SoDRoleEntity row;
	/**
	 * Gets value for attribute row
	 */
	public com.soffid.iam.rc.model.SoDRoleEntity getRow() {
		return this.row;
	}
	/**
	 * Sets value for attribute row
	 */
	public void setRow(com.soffid.iam.rc.model.SoDRoleEntity row) {
		this.row = row;
	}
	/**
	 * Attribute column
	 */
	private com.soffid.iam.rc.model.SoDRoleEntity column;
	/**
	 * Gets value for attribute column
	 */
	public com.soffid.iam.rc.model.SoDRoleEntity getColumn() {
		return this.column;
	}
	/**
	 * Sets value for attribute column
	 */
	public void setColumn(com.soffid.iam.rc.model.SoDRoleEntity column) {
		this.column = column;
	}
	/**
	 * Returns <code>true</code> if the argument is an SoDRuleMatrixEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof SoDRuleMatrixEntity))
		{
			return false;
		}
		final SoDRuleMatrixEntity that = (SoDRuleMatrixEntity)object;
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
