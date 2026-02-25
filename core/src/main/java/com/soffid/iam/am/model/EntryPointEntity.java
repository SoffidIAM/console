//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity EntryPointEntity
 */

public abstract class EntryPointEntity {

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
	 * Attribute code
	 */
	private java.lang.String code;
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
	 * Attribute description
	 */
	private java.lang.String description;
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
	 * Attribute visible
	 */
	private java.lang.String visible;
	/**
	 * Gets value for attribute visible
	 */
	public java.lang.String getVisible() {
		return this.visible;
	}
	/**
	 * Sets value for attribute visible
	 */
	public void setVisible(java.lang.String visible) {
		this.visible = visible;
	}
	/**
	 * Attribute menu
	 */
	private java.lang.String menu;
	/**
	 * Gets value for attribute menu
	 */
	public java.lang.String getMenu() {
		return this.menu;
	}
	/**
	 * Sets value for attribute menu
	 */
	public void setMenu(java.lang.String menu) {
		this.menu = menu;
	}
	/**
	 * Attribute numberOfColumns
	 */
	private java.lang.Long numberOfColumns;
	/**
	 * Gets value for attribute numberOfColumns
	 */
	public java.lang.Long getNumberOfColumns() {
		return this.numberOfColumns;
	}
	/**
	 * Sets value for attribute numberOfColumns
	 */
	public void setNumberOfColumns(java.lang.Long numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
	/**
	 * Attribute publicAccess
	 */
	private java.lang.String publicAccess;
	/**
	 * Gets value for attribute publicAccess
	 */
	public java.lang.String getPublicAccess() {
		return this.publicAccess;
	}
	/**
	 * Sets value for attribute publicAccess
	 */
	public void setPublicAccess(java.lang.String publicAccess) {
		this.publicAccess = publicAccess;
	}
	/**
	 * Attribute menuType
	 */
	private java.lang.String menuType;
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
	 * Attribute system
	 */
	private com.soffid.iam.iga.model.SystemEntity system;
	/**
	 * Gets value for attribute system
	 */
	public com.soffid.iam.iga.model.SystemEntity getSystem() {
		return this.system;
	}
	/**
	 * Sets value for attribute system
	 */
	public void setSystem(com.soffid.iam.iga.model.SystemEntity system) {
		this.system = system;
	}
	/**
	 * Attribute authorizedRoles
	 */
	private java.util.Collection<com.soffid.iam.am.model.EntryPointRoleEntity> authorizedRoles =  new java.util.HashSet<com.soffid.iam.am.model.EntryPointRoleEntity>();
	/**
	 * Gets value for attribute authorizedRoles
	 */
	public java.util.Collection<com.soffid.iam.am.model.EntryPointRoleEntity> getAuthorizedRoles() {
		return this.authorizedRoles;
	}
	/**
	 * Sets value for attribute authorizedRoles
	 */
	public void setAuthorizedRoles(java.util.Collection<com.soffid.iam.am.model.EntryPointRoleEntity> authorizedRoles) {
		this.authorizedRoles = authorizedRoles;
	}
	/**
	 * Attribute authorizedUsers
	 */
	private java.util.Collection<com.soffid.iam.am.model.EntryPointUserEntity> authorizedUsers =  new java.util.HashSet<com.soffid.iam.am.model.EntryPointUserEntity>();
	/**
	 * Gets value for attribute authorizedUsers
	 */
	public java.util.Collection<com.soffid.iam.am.model.EntryPointUserEntity> getAuthorizedUsers() {
		return this.authorizedUsers;
	}
	/**
	 * Sets value for attribute authorizedUsers
	 */
	public void setAuthorizedUsers(java.util.Collection<com.soffid.iam.am.model.EntryPointUserEntity> authorizedUsers) {
		this.authorizedUsers = authorizedUsers;
	}
	/**
	 * Attribute authorizedGroups
	 */
	private java.util.Collection<com.soffid.iam.am.model.EntryPointGroupEntity> authorizedGroups =  new java.util.HashSet<com.soffid.iam.am.model.EntryPointGroupEntity>();
	/**
	 * Gets value for attribute authorizedGroups
	 */
	public java.util.Collection<com.soffid.iam.am.model.EntryPointGroupEntity> getAuthorizedGroups() {
		return this.authorizedGroups;
	}
	/**
	 * Sets value for attribute authorizedGroups
	 */
	public void setAuthorizedGroups(java.util.Collection<com.soffid.iam.am.model.EntryPointGroupEntity> authorizedGroups) {
		this.authorizedGroups = authorizedGroups;
	}
	/**
	 * Attribute executionMethod
	 */
	private java.util.Collection<com.soffid.iam.am.model.EntryPointExecutableEntity> executionMethod =  new java.util.HashSet<com.soffid.iam.am.model.EntryPointExecutableEntity>();
	/**
	 * Gets value for attribute executionMethod
	 */
	public java.util.Collection<com.soffid.iam.am.model.EntryPointExecutableEntity> getExecutionMethod() {
		return this.executionMethod;
	}
	/**
	 * Sets value for attribute executionMethod
	 */
	public void setExecutionMethod(java.util.Collection<com.soffid.iam.am.model.EntryPointExecutableEntity> executionMethod) {
		this.executionMethod = executionMethod;
	}
	/**
	 * Attribute authorizedAccounts
	 */
	private java.util.Collection<com.soffid.iam.am.model.EntryPointAccountEntity> authorizedAccounts =  new java.util.HashSet<com.soffid.iam.am.model.EntryPointAccountEntity>();
	/**
	 * Gets value for attribute authorizedAccounts
	 */
	public java.util.Collection<com.soffid.iam.am.model.EntryPointAccountEntity> getAuthorizedAccounts() {
		return this.authorizedAccounts;
	}
	/**
	 * Sets value for attribute authorizedAccounts
	 */
	public void setAuthorizedAccounts(java.util.Collection<com.soffid.iam.am.model.EntryPointAccountEntity> authorizedAccounts) {
		this.authorizedAccounts = authorizedAccounts;
	}
	/**
	 * Attribute icon1
	 */
	private java.lang.Long icon1;
	/**
	 * Gets value for attribute icon1
	 */
	public java.lang.Long getIcon1() {
		return this.icon1;
	}
	/**
	 * Sets value for attribute icon1
	 */
	public void setIcon1(java.lang.Long icon1) {
		this.icon1 = icon1;
	}
	/**
	 * Attribute icon2
	 */
	private java.lang.Long icon2;
	/**
	 * Gets value for attribute icon2
	 */
	public java.lang.Long getIcon2() {
		return this.icon2;
	}
	/**
	 * Sets value for attribute icon2
	 */
	public void setIcon2(java.lang.Long icon2) {
		this.icon2 = icon2;
	}
	/**
	 * Attribute informationSystem
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity informationSystem;
	/**
	 * Gets value for attribute informationSystem
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getInformationSystem() {
		return this.informationSystem;
	}
	/**
	 * Sets value for attribute informationSystem
	 */
	public void setInformationSystem(com.soffid.iam.iga.model.InformationSystemEntity informationSystem) {
		this.informationSystem = informationSystem;
	}
	/**
	 * Attribute parentEntryPointTree
	 */
	private java.util.Collection<com.soffid.iam.am.model.EntryPointTreeEntity> parentEntryPointTree =  new java.util.HashSet<com.soffid.iam.am.model.EntryPointTreeEntity>();
	/**
	 * Gets value for attribute parentEntryPointTree
	 */
	public java.util.Collection<com.soffid.iam.am.model.EntryPointTreeEntity> getParentEntryPointTree() {
		return this.parentEntryPointTree;
	}
	/**
	 * Sets value for attribute parentEntryPointTree
	 */
	public void setParentEntryPointTree(java.util.Collection<com.soffid.iam.am.model.EntryPointTreeEntity> parentEntryPointTree) {
		this.parentEntryPointTree = parentEntryPointTree;
	}
	/**
	 * Attribute childrenEntryPointTree
	 */
	private java.util.Collection<com.soffid.iam.am.model.EntryPointTreeEntity> childrenEntryPointTree =  new java.util.HashSet<com.soffid.iam.am.model.EntryPointTreeEntity>();
	/**
	 * Gets value for attribute childrenEntryPointTree
	 */
	public java.util.Collection<com.soffid.iam.am.model.EntryPointTreeEntity> getChildrenEntryPointTree() {
		return this.childrenEntryPointTree;
	}
	/**
	 * Sets value for attribute childrenEntryPointTree
	 */
	public void setChildrenEntryPointTree(java.util.Collection<com.soffid.iam.am.model.EntryPointTreeEntity> childrenEntryPointTree) {
		this.childrenEntryPointTree = childrenEntryPointTree;
	}
	/**
	 * Attribute xmlEntryPoint
	 */
	private java.lang.String xmlEntryPoint;
	/**
	 * Gets value for attribute xmlEntryPoint
	 */
	public java.lang.String getXmlEntryPoint() {
		return this.xmlEntryPoint;
	}
	/**
	 * Sets value for attribute xmlEntryPoint
	 */
	public void setXmlEntryPoint(java.lang.String xmlEntryPoint) {
		this.xmlEntryPoint = xmlEntryPoint;
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
	 * Attribute hosts

	 */
	private java.util.Collection<com.soffid.iam.am.model.HostEntryPointEntity> hosts =  new java.util.HashSet<com.soffid.iam.am.model.HostEntryPointEntity>();
	/**
	 * Gets value for attribute hosts
	 */
	public java.util.Collection<com.soffid.iam.am.model.HostEntryPointEntity> getHosts() {
		return this.hosts;
	}
	/**
	 * Sets value for attribute hosts
	 */
	public void setHosts(java.util.Collection<com.soffid.iam.am.model.HostEntryPointEntity> hosts) {
		this.hosts = hosts;
	}
	/**
	 * Attribute createdOn

	 */
	private java.util.Date createdOn;
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
	 * Attribute createdBy

	 */
	private java.lang.String createdBy;
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
	 * Attribute updatedOn

	 */
	private java.util.Date updatedOn;
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
	 * Attribute updatedBy

	 */
	private java.lang.String updatedBy;
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
	 * Attribute deletedOn

	 */
	private java.util.Date deletedOn;
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
	 * Attribute deletedBy

	 */
	private java.lang.String deletedBy;
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
	 * Returns <code>true</code> if the argument is an EntryPointEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof EntryPointEntity))
		{
			return false;
		}
		final EntryPointEntity that = (EntryPointEntity)object;
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
