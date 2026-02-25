//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity EntryPointTreeEntity
 */

public abstract class EntryPointTreeEntity {

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
	 * Attribute order
	 */
	private java.lang.Integer order;
	/**
	 * Gets value for attribute order
	 */
	public java.lang.Integer getOrder() {
		return this.order;
	}
	/**
	 * Sets value for attribute order
	 */
	public void setOrder(java.lang.Integer order) {
		this.order = order;
	}
	/**
	 * Attribute parent
	 */
	private com.soffid.iam.am.model.EntryPointEntity parent;
	/**
	 * Gets value for attribute parent
	 */
	public com.soffid.iam.am.model.EntryPointEntity getParent() {
		return this.parent;
	}
	/**
	 * Sets value for attribute parent
	 */
	public void setParent(com.soffid.iam.am.model.EntryPointEntity parent) {
		this.parent = parent;
	}
	/**
	 * Attribute child
	 */
	private com.soffid.iam.am.model.EntryPointEntity child;
	/**
	 * Gets value for attribute child
	 */
	public com.soffid.iam.am.model.EntryPointEntity getChild() {
		return this.child;
	}
	/**
	 * Sets value for attribute child
	 */
	public void setChild(com.soffid.iam.am.model.EntryPointEntity child) {
		this.child = child;
	}
	/**
	 * Returns <code>true</code> if the argument is an EntryPointTreeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof EntryPointTreeEntity))
		{
			return false;
		}
		final EntryPointTreeEntity that = (EntryPointTreeEntity)object;
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
