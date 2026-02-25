//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity ScheduledTaskHandlerEntity
 */

public abstract class ScheduledTaskHandlerEntity {

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
	 * Attribute className
	 */
	private java.lang.String className;
	/**
	 * Gets value for attribute className
	 */
	public java.lang.String getClassName() {
		return this.className;
	}
	/**
	 * Sets value for attribute className
	 */
	public void setClassName(java.lang.String className) {
		this.className = className;
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
	 * Attribute tasks
	 */
	private java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> tasks =  new java.util.HashSet<com.soffid.iam.sync.model.ScheduledTaskEntity>();
	/**
	 * Gets value for attribute tasks
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> getTasks() {
		return this.tasks;
	}
	/**
	 * Sets value for attribute tasks
	 */
	public void setTasks(java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> tasks) {
		this.tasks = tasks;
	}
	/**
	 * Returns <code>true</code> if the argument is an ScheduledTaskHandlerEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ScheduledTaskHandlerEntity))
		{
			return false;
		}
		final ScheduledTaskHandlerEntity that = (ScheduledTaskHandlerEntity)object;
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
