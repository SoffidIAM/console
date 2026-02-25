//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity VaultFolderEntity
 */
public abstract class VaultFolderEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.VaultFolderEntityDao
{
	com.soffid.iam.impl.service.ACLService aCLService;

	/**
	 * Sets reference to <code>aCLService</code>.
	 */
	public void setACLService (com.soffid.iam.impl.service.ACLService aCLService) {
		this.aCLService = aCLService;
	}

	/**
	 * Gets reference to <code>aCLService</code>.
	 */
	public com.soffid.iam.impl.service.ACLService getACLService () {
		return aCLService;
	}

	com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

	/**
	 * Sets reference to <code>groupEntityDao</code>.
	 */
	public void setGroupEntityDao (com.soffid.iam.iga.model.GroupEntityDao groupEntityDao) {
		this.groupEntityDao = groupEntityDao;
	}

	/**
	 * Gets reference to <code>groupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupEntityDao getGroupEntityDao () {
		return groupEntityDao;
	}

	com.soffid.iam.pam.model.PamPolicyEntityDao pamPolicyEntityDao;

	/**
	 * Sets reference to <code>pamPolicyEntityDao</code>.
	 */
	public void setPamPolicyEntityDao (com.soffid.iam.pam.model.PamPolicyEntityDao pamPolicyEntityDao) {
		this.pamPolicyEntityDao = pamPolicyEntityDao;
	}

	/**
	 * Gets reference to <code>pamPolicyEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamPolicyEntityDao getPamPolicyEntityDao () {
		return pamPolicyEntityDao;
	}

	com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
	}

	com.soffid.iam.base.model.TenantEntityDao tenantEntityDao;

	/**
	 * Sets reference to <code>tenantEntityDao</code>.
	 */
	public void setTenantEntityDao (com.soffid.iam.base.model.TenantEntityDao tenantEntityDao) {
		this.tenantEntityDao = tenantEntityDao;
	}

	/**
	 * Gets reference to <code>tenantEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantEntityDao getTenantEntityDao () {
		return tenantEntityDao;
	}

	com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}

	com.soffid.iam.base.service.UserService userService;

	/**
	 * Sets reference to <code>userService</code>.
	 */
	public void setUserService (com.soffid.iam.base.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets reference to <code>userService</code>.
	 */
	public com.soffid.iam.base.service.UserService getUserService () {
		return userService;
	}

	com.soffid.iam.am.model.VaultFolderAccessEntityDao vaultFolderAccessEntityDao;

	/**
	 * Sets reference to <code>vaultFolderAccessEntityDao</code>.
	 */
	public void setVaultFolderAccessEntityDao (com.soffid.iam.am.model.VaultFolderAccessEntityDao vaultFolderAccessEntityDao) {
		this.vaultFolderAccessEntityDao = vaultFolderAccessEntityDao;
	}

	/**
	 * Gets reference to <code>vaultFolderAccessEntityDao</code>.
	 */
	public com.soffid.iam.am.model.VaultFolderAccessEntityDao getVaultFolderAccessEntityDao () {
		return vaultFolderAccessEntityDao;
	}

	com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}


	protected org.apache.commons.collections.map.LRUMap mapVaultFolder = new org.apache.commons.collections.map.LRUMap(100);
	protected int mapVaultFolderTimeout = 5000;
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("select distinct v from com.soffid.iam.am.model.VaultFolderEntity as v where v.name like :name and tenant.id=:tenantId",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.VaultFolderEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByParent
	 * @param parent
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByParent(
	    com.soffid.iam.am.model.VaultFolderEntity parent)
	
	{
		return findByParent((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, parent);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.VaultFolderEntity parent)
	
	{
		return findByParent("from com.soffid.iam.am.model.VaultFolderEntity where tenant.id=:tenantId and parent=:parent",
			criteria, parent);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByParent(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.VaultFolderEntity parent)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("parent", parent);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.VaultFolderEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findPersonalFolders
	 * @param user
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPersonalFolders(
	    java.lang.String user)
	
	{
		return findPersonalFolders((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPersonalFolders(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		return findPersonalFolders("select distinct v from com.soffid.iam.am.model.VaultFolderEntity as v join v.acl as acl join acl.user as user where v.parent is null and v.personal = true and user.userName=:user and user.tenant.id=:tenantId",
			criteria, user);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPersonalFolders(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.VaultFolderEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findPublicRoots
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPublicRoots(
)
	
	{
		return findPublicRoots((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPublicRoots(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findPublicRoots("select distinct v from com.soffid.iam.am.model.VaultFolderEntity as v where v.parent is null and v.personal = false and tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPublicRoots(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.VaultFolderEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRoots
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findRoots(
)
	
	{
		return findRoots((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findRoots(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findRoots("select distinct v from com.soffid.iam.am.model.VaultFolderEntity as v where v.parent is null and tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findRoots(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.VaultFolderEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.VaultFolder} object 
	 */
	public void toVaultFolder(com.soffid.iam.am.model.VaultFolderEntity source, com.soffid.iam.am.api.VaultFolder target) {
		// Attributes for VaultFolder
		target.setId(source.getId());
		target.setName(source.getName());
		target.setPersonal(java.lang.Boolean.TRUE.equals(source.getPersonal()));
		target.setDescription(source.getDescription());
		// Missing attribute parentId on entity
		// Missing attribute parentFolder on entity
		// Missing attribute grantedGroups on entity
		// Missing attribute grantedUsers on entity
		// Missing attribute grantedRoles on entity
		// Missing attribute managerGroups on entity
		// Missing attribute managerUsers on entity
		// Missing attribute managerRoles on entity
		// Missing attribute ownerGroups on entity
		// Missing attribute ownerUsers on entity
		// Missing attribute ownerRoles on entity
		// Missing attribute navigateGroups on entity
		// Missing attribute navigateUsers on entity
		// Missing attribute navigateRoles on entity
		// Missing attribute accessLevel on entity
		// Incompatible types source.pamPolicy and target.pamPolicy
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.VaultFolder} object 
	 */
	/**
	 *  Stores {@link com.soffid.iam.am.api.VaultFolder} in cache 
	 */
	protected synchronized void storeVaultFolderCacheEntry (java.lang.Long id, com.soffid.iam.am.api.VaultFolder vaultFolder)
	{
		VaultFolderCacheEntry entry = new VaultFolderCacheEntry ();
		entry.vaultFolder = new com.soffid.iam.am.api.VaultFolder(vaultFolder);
		entry.timeStamp = System.currentTimeMillis();
		mapVaultFolder.put(com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id, entry);
	}

	/**
	 *  Retrieves {@link com.soffid.iam.am.api.VaultFolder} from cache 
	 */
	protected synchronized com.soffid.iam.am.api.VaultFolder getVaultFolderCacheEntry (java.lang.Long id)
	{
		VaultFolderCacheEntry entry = (VaultFolderCacheEntry) mapVaultFolder.get (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
		if (entry == null) return null;
		if (entry.timeStamp + mapVaultFolderTimeout < System.currentTimeMillis())
		{
			mapVaultFolder.remove (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
			return null;
		}
		return new com.soffid.iam.am.api.VaultFolder(entry.vaultFolder);
	}

	/**
	 *  Removes {@link com.soffid.iam.am.api.VaultFolder} from cache 
	 */
	protected synchronized void removeVaultFolderCacheEntry (java.lang.Long id)
	{
		mapVaultFolder.remove (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
	}

	public com.soffid.iam.am.api.VaultFolder toVaultFolder(com.soffid.iam.am.model.VaultFolderEntity entity) {
		com.soffid.iam.am.api.VaultFolder target = es.caib.seycon.ng.utils.Security.isSyncServer() ? 
			null : 
			getVaultFolderCacheEntry(entity.getId());
		if (target != null)
			return target;
		else
		{
			target = new com.soffid.iam.am.api.VaultFolder();
			this.toVaultFolder(entity, target);
			if (!es.caib.seycon.ng.utils.Security.isSyncServer() )
				storeVaultFolderCacheEntry(entity.getId(), target);
			return target;
		}
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.VaultFolder} list 
	 */
	public java.util.List<com.soffid.iam.am.api.VaultFolder> toVaultFolderList (java.util.Collection<com.soffid.iam.am.model.VaultFolderEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.VaultFolder> list =
				new java.util.LinkedList<com.soffid.iam.am.api.VaultFolder>();
			for (final com.soffid.iam.am.model.VaultFolderEntity instance: instances)
			{
				list.add( toVaultFolder(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.VaultFolder} object 
	 */
	public void vaultFolderToEntity (com.soffid.iam.am.api.VaultFolder source, com.soffid.iam.am.model.VaultFolderEntity target, boolean copyIfNull) {
		// Attributes for VaultFolderEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute parent on entity
		target.setPersonal(new java.lang.Boolean(source.isPersonal()));
		if (copyIfNull || source.getPamPolicy() != null)
		{
			// Incompatible types source.pamPolicy and target.pamPolicy
		}
		// Missing attribute tenant on entity
		// Missing attribute acl on entity
		// Missing attribute children on entity
		// Missing attribute accounts on entity
		if (copyIfNull || source.getCreatedOn() != null)
		{
			target.setCreatedOn(source.getCreatedOn());
		}
		if (copyIfNull || source.getCreatedBy() != null)
		{
			target.setCreatedBy(source.getCreatedBy());
		}
		if (copyIfNull || source.getUpdatedOn() != null)
		{
			target.setUpdatedOn(source.getUpdatedOn());
		}
		if (copyIfNull || source.getUpdatedBy() != null)
		{
			target.setUpdatedBy(source.getUpdatedBy());
		}
		if (copyIfNull || source.getDeletedOn() != null)
		{
			target.setDeletedOn(source.getDeletedOn());
		}
		if (copyIfNull || source.getDeletedBy() != null)
		{
			target.setDeletedBy(source.getDeletedBy());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.VaultFolder} object 
	 */
	public com.soffid.iam.am.model.VaultFolderEntity vaultFolderToEntity (com.soffid.iam.am.api.VaultFolder instance) {
		com.soffid.iam.am.model.VaultFolderEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newVaultFolderEntity();
		vaultFolderToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.VaultFolder} list 
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity>  vaultFolderToEntityList (java.util.Collection<com.soffid.iam.am.api.VaultFolder> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.VaultFolderEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.VaultFolderEntity>();
		for (com.soffid.iam.am.api.VaultFolder instance: instances)
		{
			list.add (vaultFolderToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} .
	 */
	public com.soffid.iam.am.model.VaultFolderEntity newVaultFolderEntity()
	{
		return new com.soffid.iam.am.model.VaultFolderEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.VaultFolderEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.VaultFolderEntity result = (com.soffid.iam.am.model.VaultFolderEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.VaultFolderEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.VaultFolderEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.VaultFolderEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.VaultFolderEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"VaultFolderEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		removeVaultFolderCacheEntry(entity.getId());
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.VaultFolderEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"VaultFolderEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
		removeVaultFolderCacheEntry(entity.getId());
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.VaultFolderEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"VaultFolderEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
		removeVaultFolderCacheEntry(entity.getId());
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.VaultFolderEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"VaultFolderEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.VaultFolderEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.VaultFolderEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"VaultFolderEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.VaultFolderEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"VaultFolderEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.VaultFolderEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"VaultFolderEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.VaultFolderEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.VaultFolderEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.VaultFolderEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
class VaultFolderCacheEntry {
	public com.soffid.iam.am.api.VaultFolder vaultFolder;
	public long timeStamp;
}
