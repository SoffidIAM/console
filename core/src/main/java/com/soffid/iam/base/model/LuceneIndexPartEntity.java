//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity LuceneIndexPartEntity
 */

public abstract class LuceneIndexPartEntity {

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
	 * Attribute index
	 */
	private com.soffid.iam.base.model.LuceneIndexEntity index;
	/**
	 * Gets value for attribute index
	 */
	public com.soffid.iam.base.model.LuceneIndexEntity getIndex() {
		return this.index;
	}
	/**
	 * Sets value for attribute index
	 */
	public void setIndex(com.soffid.iam.base.model.LuceneIndexEntity index) {
		this.index = index;
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
	 * Attribute order
	 */
	private int order;
	/**
	 * Gets value for attribute order
	 */
	public int getOrder() {
		return this.order;
	}
	/**
	 * Sets value for attribute order
	 */
	public void setOrder(int order) {
		this.order = order;
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
	 * Attribute data
	 */
	private byte[] data;
	/**
	 * Gets value for attribute data
	 */
	public byte[] getData() {
		return this.data;
	}
	/**
	 * Sets value for attribute data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	/**
	 * Attribute timestamp
	 */
	private java.lang.Long timestamp;
	/**
	 * Gets value for attribute timestamp
	 */
	public java.lang.Long getTimestamp() {
		return this.timestamp;
	}
	/**
	 * Sets value for attribute timestamp
	 */
	public void setTimestamp(java.lang.Long timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Returns <code>true</code> if the argument is an LuceneIndexPartEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof LuceneIndexPartEntity))
		{
			return false;
		}
		final LuceneIndexPartEntity that = (LuceneIndexPartEntity)object;
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
