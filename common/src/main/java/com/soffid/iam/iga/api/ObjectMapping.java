//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ObjectMapping
 **/
public class ObjectMapping

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
	 * Attribute systemObject

	 */
	private java.lang.String systemObject;

	/**
	 * Attribute soffidObject

	 */
	private com.soffid.iam.iga.api.SoffidObjectType soffidObject;

	/**
	 * Attribute soffidCustomObject

	 */
	private java.lang.String soffidCustomObject;

	/**
	 * Attribute condition

	 */
	private java.lang.String condition;

	/**
	 * Attribute dispatcherId

	 */
	private java.lang.Long dispatcherId;

	/**
	 * Attribute authoritative
	 * Data on target system must be considered as authoritative

	 */
	private boolean authoritative;

	public ObjectMapping()
	{
	}

	public ObjectMapping(java.lang.Long id, java.lang.String systemObject, com.soffid.iam.iga.api.SoffidObjectType soffidObject, java.lang.String soffidCustomObject, java.lang.String condition, java.lang.Long dispatcherId, boolean authoritative)
	{
		super();
		this.id = id;
		this.systemObject = systemObject;
		this.soffidObject = soffidObject;
		this.soffidCustomObject = soffidCustomObject;
		this.condition = condition;
		this.dispatcherId = dispatcherId;
		this.authoritative = authoritative;
	}

	public ObjectMapping(java.lang.String systemObject, com.soffid.iam.iga.api.SoffidObjectType soffidObject, java.lang.Long dispatcherId, boolean authoritative)
	{
		super();
		this.systemObject = systemObject;
		this.soffidObject = soffidObject;
		this.dispatcherId = dispatcherId;
		this.authoritative = authoritative;
	}

	public ObjectMapping(ObjectMapping otherBean)
	{
		this(otherBean.id, otherBean.systemObject, otherBean.soffidObject, otherBean.soffidCustomObject, otherBean.condition, otherBean.dispatcherId, otherBean.authoritative);
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
	 * Gets value for attribute systemObject
	 */
	public java.lang.String getSystemObject() {
		return this.systemObject;
	}

	/**
	 * Sets value for attribute systemObject
	 */
	public void setSystemObject(java.lang.String systemObject) {
		this.systemObject = systemObject;
	}

	/**
	 * Gets value for attribute soffidObject
	 */
	public com.soffid.iam.iga.api.SoffidObjectType getSoffidObject() {
		return this.soffidObject;
	}

	/**
	 * Sets value for attribute soffidObject
	 */
	public void setSoffidObject(com.soffid.iam.iga.api.SoffidObjectType soffidObject) {
		this.soffidObject = soffidObject;
	}

	/**
	 * Gets value for attribute soffidCustomObject
	 */
	public java.lang.String getSoffidCustomObject() {
		return this.soffidCustomObject;
	}

	/**
	 * Sets value for attribute soffidCustomObject
	 */
	public void setSoffidCustomObject(java.lang.String soffidCustomObject) {
		this.soffidCustomObject = soffidCustomObject;
	}

	/**
	 * Gets value for attribute condition
	 */
	public java.lang.String getCondition() {
		return this.condition;
	}

	/**
	 * Sets value for attribute condition
	 */
	public void setCondition(java.lang.String condition) {
		this.condition = condition;
	}

	/**
	 * Gets value for attribute dispatcherId
	 */
	public java.lang.Long getDispatcherId() {
		return this.dispatcherId;
	}

	/**
	 * Sets value for attribute dispatcherId
	 */
	public void setDispatcherId(java.lang.Long dispatcherId) {
		this.dispatcherId = dispatcherId;
	}

	/**
	 * Gets value for attribute authoritative
	 */
	public boolean isAuthoritative() {
		return this.authoritative;
	}

	/**
	 * Sets value for attribute authoritative
	 */
	public void setAuthoritative(boolean authoritative) {
		this.authoritative = authoritative;
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
		b.append (", systemObject: ");
		b.append (this.systemObject);
		b.append (", soffidObject: ");
		b.append (this.soffidObject);
		b.append (", soffidCustomObject: ");
		b.append (this.soffidCustomObject);
		b.append (", condition: ");
		b.append (this.condition);
		b.append (", dispatcherId: ");
		b.append (this.dispatcherId);
		b.append (", authoritative: ");
		b.append (this.authoritative);
		b.append ("]");
		return b.toString();
	}

}
