//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject AccessTree
 **/
public class AccessTree

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
	 * Attribute code

	 */
	private java.lang.String code;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute visible

	 */
	private boolean visible;

	/**
	 * Attribute menu

	 */
	private boolean menu;

	/**
	 * Attribute columnsNumber

	 */
	private java.lang.Long columnsNumber;

	/**
	 * Attribute publicAccess

	 */
	private boolean publicAccess;

	/**
	 * Attribute menuType

	 */
	private java.lang.String menuType;

	/**
	 * Attribute authorizations

	 */
	private java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> authorizations;

	/**
	 * Attribute executions

	 */
	private java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> executions;

	/**
	 * Attribute icon1Image

	 */
	private byte[] icon1Image;

	/**
	 * Attribute icon2Image

	 */
	private byte[] icon2Image;

	/**
	 * Attribute informationSystem

	 */
	private java.lang.String informationSystem;

	/**
	 * Attribute parentId

	 */
	private java.lang.Long parentId;

	/**
	 * Attribute order

	 */
	private java.lang.String order;

	/**
	 * Attribute icon1Id

	 */
	private java.lang.Long icon1Id;

	/**
	 * Attribute icon2Id

	 */
	private java.lang.Long icon2Id;

	/**
	 * Attribute xmlAccessTree

	 */
	private java.lang.String xmlAccessTree;

	/**
	 * Attribute system

	 */
	private java.lang.String system;

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	/**
	 * Attribute createdBy


	 */
	private java.lang.String createdBy;

	/**
	 * Attribute updatedOn


	 */
	private java.util.Date updatedOn;

	/**
	 * Attribute updatedBy


	 */
	private java.lang.String updatedBy;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	public AccessTree()
	{
	}

	public AccessTree(java.lang.Long id, java.lang.String code, java.lang.String name, java.lang.String description, boolean visible, boolean menu, java.lang.Long columnsNumber, boolean publicAccess, java.lang.String menuType, java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> authorizations, java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> executions, byte[] icon1Image, byte[] icon2Image, java.lang.String informationSystem, java.lang.Long parentId, java.lang.String order, java.lang.Long icon1Id, java.lang.Long icon2Id, java.lang.String xmlAccessTree, java.lang.String system, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.visible = visible;
		this.menu = menu;
		this.columnsNumber = columnsNumber;
		this.publicAccess = publicAccess;
		this.menuType = menuType;
		this.authorizations = authorizations;
		this.executions = executions;
		this.icon1Image = icon1Image;
		this.icon2Image = icon2Image;
		this.informationSystem = informationSystem;
		this.parentId = parentId;
		this.order = order;
		this.icon1Id = icon1Id;
		this.icon2Id = icon2Id;
		this.xmlAccessTree = xmlAccessTree;
		this.system = system;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public AccessTree(java.lang.String name, boolean visible, boolean menu, boolean publicAccess)
	{
		super();
		this.name = name;
		this.visible = visible;
		this.menu = menu;
		this.publicAccess = publicAccess;
	}

	public AccessTree(AccessTree otherBean)
	{
		this(otherBean.id, otherBean.code, otherBean.name, otherBean.description, otherBean.visible, otherBean.menu, otherBean.columnsNumber, otherBean.publicAccess, otherBean.menuType, otherBean.authorizations, otherBean.executions, otherBean.icon1Image, otherBean.icon2Image, otherBean.informationSystem, otherBean.parentId, otherBean.order, otherBean.icon1Id, otherBean.icon2Id, otherBean.xmlAccessTree, otherBean.system, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute code
	 */
	public java.lang.String getCode() {
		return this.code;
	}

	/**
	 * Sets value for attribute code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

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
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets value for attribute visible
	 */
	public boolean isVisible() {
		return this.visible;
	}

	/**
	 * Sets value for attribute visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Gets value for attribute menu
	 */
	public boolean isMenu() {
		return this.menu;
	}

	/**
	 * Sets value for attribute menu
	 */
	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	/**
	 * Gets value for attribute columnsNumber
	 */
	public java.lang.Long getColumnsNumber() {
		return this.columnsNumber;
	}

	/**
	 * Sets value for attribute columnsNumber
	 */
	public void setColumnsNumber(java.lang.Long columnsNumber) {
		this.columnsNumber = columnsNumber;
	}

	/**
	 * Gets value for attribute publicAccess
	 */
	public boolean isPublicAccess() {
		return this.publicAccess;
	}

	/**
	 * Sets value for attribute publicAccess
	 */
	public void setPublicAccess(boolean publicAccess) {
		this.publicAccess = publicAccess;
	}

	/**
	 * Gets value for attribute menuType
	 */
	public java.lang.String getMenuType() {
		return this.menuType;
	}

	/**
	 * Sets value for attribute menuType
	 */
	public void setMenuType(java.lang.String menuType) {
		this.menuType = menuType;
	}

	/**
	 * Gets value for attribute authorizations
	 */
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> getAuthorizations() {
		return this.authorizations;
	}

	/**
	 * Sets value for attribute authorizations
	 */
	public void setAuthorizations(java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> authorizations) {
		this.authorizations = authorizations;
	}

	/**
	 * Gets value for attribute executions
	 */
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> getExecutions() {
		return this.executions;
	}

	/**
	 * Sets value for attribute executions
	 */
	public void setExecutions(java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> executions) {
		this.executions = executions;
	}

	/**
	 * Gets value for attribute icon1Image
	 */
	public byte[] getIcon1Image() {
		return this.icon1Image;
	}

	/**
	 * Sets value for attribute icon1Image
	 */
	public void setIcon1Image(byte[] icon1Image) {
		this.icon1Image = icon1Image;
	}

	/**
	 * Gets value for attribute icon2Image
	 */
	public byte[] getIcon2Image() {
		return this.icon2Image;
	}

	/**
	 * Sets value for attribute icon2Image
	 */
	public void setIcon2Image(byte[] icon2Image) {
		this.icon2Image = icon2Image;
	}

	/**
	 * Gets value for attribute informationSystem
	 */
	public java.lang.String getInformationSystem() {
		return this.informationSystem;
	}

	/**
	 * Sets value for attribute informationSystem
	 */
	public void setInformationSystem(java.lang.String informationSystem) {
		this.informationSystem = informationSystem;
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
	 * Gets value for attribute order
	 */
	public java.lang.String getOrder() {
		return this.order;
	}

	/**
	 * Sets value for attribute order
	 */
	public void setOrder(java.lang.String order) {
		this.order = order;
	}

	/**
	 * Gets value for attribute icon1Id
	 */
	public java.lang.Long getIcon1Id() {
		return this.icon1Id;
	}

	/**
	 * Sets value for attribute icon1Id
	 */
	public void setIcon1Id(java.lang.Long icon1Id) {
		this.icon1Id = icon1Id;
	}

	/**
	 * Gets value for attribute icon2Id
	 */
	public java.lang.Long getIcon2Id() {
		return this.icon2Id;
	}

	/**
	 * Sets value for attribute icon2Id
	 */
	public void setIcon2Id(java.lang.Long icon2Id) {
		this.icon2Id = icon2Id;
	}

	/**
	 * Gets value for attribute xmlAccessTree
	 */
	public java.lang.String getXmlAccessTree() {
		return this.xmlAccessTree;
	}

	/**
	 * Sets value for attribute xmlAccessTree
	 */
	public void setXmlAccessTree(java.lang.String xmlAccessTree) {
		this.xmlAccessTree = xmlAccessTree;
	}

	/**
	 * Gets value for attribute system
	 */
	public java.lang.String getSystem() {
		return this.system;
	}

	/**
	 * Sets value for attribute system
	 */
	public void setSystem(java.lang.String system) {
		this.system = system;
	}

	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets value for attribute updatedOn
	 */
	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}

	/**
	 * Sets value for attribute updatedOn
	 */
	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * Gets value for attribute updatedBy
	 */
	public java.lang.String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * Sets value for attribute updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets value for attribute deletedOn
	 */
	public java.util.Date getDeletedOn() {
		return this.deletedOn;
	}

	/**
	 * Sets value for attribute deletedOn
	 */
	public void setDeletedOn(java.util.Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	/**
	 * Gets value for attribute deletedBy
	 */
	public java.lang.String getDeletedBy() {
		return this.deletedBy;
	}

	/**
	 * Sets value for attribute deletedBy
	 */
	public void setDeletedBy(java.lang.String deletedBy) {
		this.deletedBy = deletedBy;
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
		b.append (", code: ");
		b.append (this.code);
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", visible: ");
		b.append (this.visible);
		b.append (", menu: ");
		b.append (this.menu);
		b.append (", columnsNumber: ");
		b.append (this.columnsNumber);
		b.append (", publicAccess: ");
		b.append (this.publicAccess);
		b.append (", menuType: ");
		b.append (this.menuType);
		b.append (", authorizations: ");
		b.append (this.authorizations);
		b.append (", executions: ");
		b.append (this.executions);
		b.append (", icon1Image: ");
		b.append (this.icon1Image);
		b.append (", icon2Image: ");
		b.append (this.icon2Image);
		b.append (", informationSystem: ");
		b.append (this.informationSystem);
		b.append (", parentId: ");
		b.append (this.parentId);
		b.append (", order: ");
		b.append (this.order);
		b.append (", icon1Id: ");
		b.append (this.icon1Id);
		b.append (", icon2Id: ");
		b.append (this.icon2Id);
		b.append (", xmlAccessTree: ");
		b.append (this.xmlAccessTree);
		b.append (", system: ");
		b.append (this.system);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", createdBy: ");
		b.append (this.createdBy);
		b.append (", updatedOn: ");
		b.append (this.updatedOn);
		b.append (", updatedBy: ");
		b.append (this.updatedBy);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append ("]");
		return b.toString();
	}

}
