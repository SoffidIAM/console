//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity EntryPointAccountEntity
 * @see com.soffid.iam.am.model.EntryPointAccountEntity
 */
public interface EntryPointAccountEntityDao

{
	/**
	 * Operation findAll
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointAccountEntity> findAll()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointAccountEntity> findAll(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findByAccountId
	 * @param accountId
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> findByAccountId(
		java.lang.Long accountId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> findByAccountId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long accountId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointAccountEntity source, com.soffid.iam.am.api.AccessTreeAuthorization target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.api.AccessTreeAuthorization toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointAccountEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeAuthorization> toAccessTreeAuthorizationList (java.util.Collection<com.soffid.iam.am.model.EntryPointAccountEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization source, com.soffid.iam.am.model.EntryPointAccountEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.model.EntryPointAccountEntity accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointAccountEntity>  accessTreeAuthorizationToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointAccountEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointAccountEntity newEntryPointAccountEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointAccountEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointAccountEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointAccountEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointAccountEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointAccountEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointAccountEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointAccountEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointAccountEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
