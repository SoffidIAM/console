//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity EntryPointRoleEntity
 * @see com.soffid.iam.am.model.EntryPointRoleEntity
 */
public interface EntryPointRoleEntityDao

{
	/**
	 * Operation findByRoleId
	 * @param idRol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> findByRoleId(
		java.lang.Long idRol)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> findByRoleId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long idRol)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointRoleEntity source, com.soffid.iam.am.api.AccessTreeAuthorization target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.api.AccessTreeAuthorization toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointRoleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeAuthorization> toAccessTreeAuthorizationList (java.util.Collection<com.soffid.iam.am.model.EntryPointRoleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization source, com.soffid.iam.am.model.EntryPointRoleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntity accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity>  accessTreeAuthorizationToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntity newEntryPointRoleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointRoleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointRoleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointRoleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointRoleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointRoleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointRoleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
