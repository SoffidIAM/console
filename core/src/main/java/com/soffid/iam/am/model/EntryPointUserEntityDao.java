//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity EntryPointUserEntity
 * @see com.soffid.iam.am.model.EntryPointUserEntity
 */
public interface EntryPointUserEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointUserEntity source, com.soffid.iam.am.api.AccessTreeAuthorization target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.api.AccessTreeAuthorization toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointUserEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeAuthorization> toAccessTreeAuthorizationList (java.util.Collection<com.soffid.iam.am.model.EntryPointUserEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization source, com.soffid.iam.am.model.EntryPointUserEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.model.EntryPointUserEntity accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointUserEntity>  accessTreeAuthorizationToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointUserEntity newEntryPointUserEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointUserEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointUserEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointUserEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointUserEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointUserEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointUserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointUserEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointUserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointUserEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointUserEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointUserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointUserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
