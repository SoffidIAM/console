//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity LuceneIndexEntity
 */

public abstract class LuceneIndexEntity {

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
	 * Attribute name
	 */
	private java.lang.String name;
	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
	}
	/**
	 * Attribute timestamp
	 */
	private long timestamp;
	/**
	 * Gets value for attribute timestamp
	 */
	public long getTimestamp() {
		return this.timestamp;
	}
	/**
	 * Sets value for attribute timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Attribute parts

	 */
	private java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity> parts =  new java.util.HashSet<com.soffid.iam.base.model.LuceneIndexPartEntity>();
	/**
	 * Gets value for attribute parts
	 */
	public java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity> getParts() {
		return this.parts;
	}
	/**
	 * Sets value for attribute parts
	 */
	public void setParts(java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity> parts) {
		this.parts = parts;
	}
	/**
	 * Returns <code>true</code> if the argument is an LuceneIndexEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof LuceneIndexEntity))
		{
			return false;
		}
		final LuceneIndexEntity that = (LuceneIndexEntity)object;
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
