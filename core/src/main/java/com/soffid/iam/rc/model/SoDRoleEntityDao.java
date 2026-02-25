//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity SoDRoleEntity
 * @see com.soffid.iam.rc.model.SoDRoleEntity
 */
public interface SoDRoleEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.SoDRole} object 
	 */
	public void toSoDRole(com.soffid.iam.rc.model.SoDRoleEntity source, com.soffid.iam.rc.api.SoDRole target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRole} object 
	 */
	public com.soffid.iam.rc.api.SoDRole toSoDRole(com.soffid.iam.rc.model.SoDRoleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRole} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.SoDRole> toSoDRoleList (java.util.Collection<com.soffid.iam.rc.model.SoDRoleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.SoDRole} object 
	 */
	public void soDRoleToEntity (com.soffid.iam.rc.api.SoDRole source, com.soffid.iam.rc.model.SoDRoleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRole} object 
	 */
	public com.soffid.iam.rc.model.SoDRoleEntity soDRoleToEntity (com.soffid.iam.rc.api.SoDRole instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRole} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRoleEntity>  soDRoleToEntityList (java.util.Collection<com.soffid.iam.rc.api.SoDRole> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} .
	 */
	public com.soffid.iam.rc.model.SoDRoleEntity newSoDRoleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.SoDRoleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.SoDRoleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.SoDRoleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.SoDRoleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRoleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.SoDRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRoleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.SoDRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRoleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRoleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
