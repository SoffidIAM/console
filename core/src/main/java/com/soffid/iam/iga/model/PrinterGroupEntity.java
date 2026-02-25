//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity PrinterGroupEntity
 */

public abstract class PrinterGroupEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute order
	 */
	private java.lang.Long order;
	/**
	 * Gets value for attribute order
	 */
	public java.lang.Long getOrder() {
		return this.order;
	}
	/**
	 * Sets value for attribute order
	 */
	public void setOrder(java.lang.Long order) {
		this.order = order;
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
	 * Attribute printer
	 */
	private com.soffid.iam.iga.model.PrinterEntity printer;
	/**
	 * Gets value for attribute printer
	 */
	public com.soffid.iam.iga.model.PrinterEntity getPrinter() {
		return this.printer;
	}
	/**
	 * Sets value for attribute printer
	 */
	public void setPrinter(com.soffid.iam.iga.model.PrinterEntity printer) {
		this.printer = printer;
	}
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
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Returns <code>true</code> if the argument is an PrinterGroupEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PrinterGroupEntity))
		{
			return false;
		}
		final PrinterGroupEntity that = (PrinterGroupEntity)object;
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
