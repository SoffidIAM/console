//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject AuthoritativeChange
 **/
public class AuthoritativeChange

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = -3968123356323004507L;
	/**
	 * Attribute id

	 */
	private com.soffid.iam.sync.api.AuthoritativeChangeIdentifier id;

	/**
	 * Attribute objectType

	 */
	private com.soffid.iam.iga.api.SoffidObjectType objectType;

	/**
	 * Attribute sourceSystem

	 */
	private java.lang.String sourceSystem;

	/**
	 * Attribute user

	 */
	private com.soffid.iam.base.api.User user;

	/**
	 * Attribute attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes;

	/**
	 * Attribute groups

	 */
	private java.util.Set<java.lang.String> groups;

	/**
	 * Attribute groups2

	 */
	private java.util.Collection<com.soffid.iam.iga.api.UserGroup> groups2;

	/**
	 * Attribute group

	 */
	private com.soffid.iam.iga.api.Group group;

	/**
	 * Attribute object

	 */
	private com.soffid.iam.iga.api.CustomObject object;

	public AuthoritativeChange()
	{
	}

	public AuthoritativeChange(com.soffid.iam.sync.api.AuthoritativeChangeIdentifier id, com.soffid.iam.iga.api.SoffidObjectType objectType, java.lang.String sourceSystem, com.soffid.iam.base.api.User user, java.util.Map<java.lang.String,java.lang.Object> attributes, java.util.Set<java.lang.String> groups, java.util.Collection<com.soffid.iam.iga.api.UserGroup> groups2, com.soffid.iam.iga.api.Group group, com.soffid.iam.iga.api.CustomObject object)
	{
		super();
		this.id = id;
		this.objectType = objectType;
		this.sourceSystem = sourceSystem;
		this.user = user;
		this.attributes = attributes;
		this.groups = groups;
		this.groups2 = groups2;
		this.group = group;
		this.object = object;
	}

	public AuthoritativeChange(AuthoritativeChange otherBean)
	{
		this(otherBean.id, otherBean.objectType, otherBean.sourceSystem, otherBean.user, otherBean.attributes, otherBean.groups, otherBean.groups2, otherBean.group, otherBean.object);
	}

	/**
	 * Gets value for attribute id
	 */
	public com.soffid.iam.sync.api.AuthoritativeChangeIdentifier getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(com.soffid.iam.sync.api.AuthoritativeChangeIdentifier id) {
		this.id = id;
	}

	/**
	 * Gets value for attribute objectType
	 */
	public com.soffid.iam.iga.api.SoffidObjectType getObjectType() {
		return this.objectType;
	}

	/**
	 * Sets value for attribute objectType
	 */
	public void setObjectType(com.soffid.iam.iga.api.SoffidObjectType objectType) {
		this.objectType = objectType;
	}

	/**
	 * Gets value for attribute sourceSystem
	 */
	public java.lang.String getSourceSystem() {
		return this.sourceSystem;
	}

	/**
	 * Sets value for attribute sourceSystem
	 */
	public void setSourceSystem(java.lang.String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.api.User getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.api.User user) {
		this.user = user;
	}

	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Map<java.lang.String,java.lang.Object> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets value for attribute groups
	 */
	public java.util.Set<java.lang.String> getGroups() {
		return this.groups;
	}

	/**
	 * Sets value for attribute groups
	 */
	public void setGroups(java.util.Set<java.lang.String> groups) {
		this.groups = groups;
	}

	/**
	 * Gets value for attribute groups2
	 */
	public java.util.Collection<com.soffid.iam.iga.api.UserGroup> getGroups2() {
		return this.groups2;
	}

	/**
	 * Sets value for attribute groups2
	 */
	public void setGroups2(java.util.Collection<com.soffid.iam.iga.api.UserGroup> groups2) {
		this.groups2 = groups2;
	}

	/**
	 * Gets value for attribute group
	 */
	public com.soffid.iam.iga.api.Group getGroup() {
		return this.group;
	}

	/**
	 * Sets value for attribute group
	 */
	public void setGroup(com.soffid.iam.iga.api.Group group) {
		this.group = group;
	}

	/**
	 * Gets value for attribute object
	 */
	public com.soffid.iam.iga.api.CustomObject getObject() {
		return this.object;
	}

	/**
	 * Sets value for attribute object
	 */
	public void setObject(com.soffid.iam.iga.api.CustomObject object) {
		this.object = object;
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
		b.append (", objectType: ");
		b.append (this.objectType);
		b.append (", sourceSystem: ");
		b.append (this.sourceSystem);
		b.append (", user: ");
		b.append (this.user);
		b.append (", attributes: ");
		b.append (this.attributes);
		b.append (", groups: ");
		b.append (this.groups);
		b.append (", groups2: ");
		b.append (this.groups2);
		b.append (", group: ");
		b.append (this.group);
		b.append (", object: ");
		b.append (this.object);
		b.append ("]");
		return b.toString();
	}

}
