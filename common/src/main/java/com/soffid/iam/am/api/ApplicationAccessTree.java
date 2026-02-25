//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject ApplicationAccessTree
 **/
public class ApplicationAccessTree

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute ordre

	 */
	private java.lang.String ordre;

	/**
	 * Attribute parentId

	 */
	private java.lang.Long parentId;

	/**
	 * Attribute parentName

	 */
	private java.lang.String parentName;

	/**
	 * Attribute childId

	 */
	private java.lang.Long childId;

	/**
	 * Attribute childName

	 */
	private java.lang.String childName;

	public ApplicationAccessTree()
	{
	}

	public ApplicationAccessTree(java.lang.Long id, java.lang.String ordre, java.lang.Long parentId, java.lang.String parentName, java.lang.Long childId, java.lang.String childName)
	{
		super();
		this.id = id;
		this.ordre = ordre;
		this.parentId = parentId;
		this.parentName = parentName;
		this.childId = childId;
		this.childName = childName;
	}

	public ApplicationAccessTree(ApplicationAccessTree otherBean)
	{
		this(otherBean.id, otherBean.ordre, otherBean.parentId, otherBean.parentName, otherBean.childId, otherBean.childName);
	}

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
	 * Gets value for attribute ordre
	 */
	public java.lang.String getOrdre() {
		return this.ordre;
	}

	/**
	 * Sets value for attribute ordre
	 */
	public void setOrdre(java.lang.String ordre) {
		this.ordre = ordre;
	}

	/**
	 * Gets value for attribute parentId
	 */
	public java.lang.Long getParentId() {
		return this.parentId;
	}

	/**
	 * Sets value for attribute parentId
	 */
	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets value for attribute parentName
	 */
	public java.lang.String getParentName() {
		return this.parentName;
	}

	/**
	 * Sets value for attribute parentName
	 */
	public void setParentName(java.lang.String parentName) {
		this.parentName = parentName;
	}

	/**
	 * Gets value for attribute childId
	 */
	public java.lang.Long getChildId() {
		return this.childId;
	}

	/**
	 * Sets value for attribute childId
	 */
	public void setChildId(java.lang.Long childId) {
		this.childId = childId;
	}

	/**
	 * Gets value for attribute childName
	 */
	public java.lang.String getChildName() {
		return this.childName;
	}

	/**
	 * Sets value for attribute childName
	 */
	public void setChildName(java.lang.String childName) {
		this.childName = childName;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", ordre: ");
		b.append (this.ordre);
		b.append (", parentId: ");
		b.append (this.parentId);
		b.append (", parentName: ");
		b.append (this.parentName);
		b.append (", childId: ");
		b.append (this.childId);
		b.append (", childName: ");
		b.append (this.childName);
		b.append ("]");
		return b.toString();
	}

}
