//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity EntryPointGroupEntity
 * @see com.soffid.iam.am.model.EntryPointGroupEntity
 */
public interface EntryPointGroupEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointGroupEntity source, com.soffid.iam.am.api.AccessTreeAuthorization target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.api.AccessTreeAuthorization toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointGroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeAuthorization> toAccessTreeAuthorizationList (java.util.Collection<com.soffid.iam.am.model.EntryPointGroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization source, com.soffid.iam.am.model.EntryPointGroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.model.EntryPointGroupEntity accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointGroupEntity>  accessTreeAuthorizationToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointGroupEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointGroupEntity newEntryPointGroupEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointGroupEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointGroupEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointGroupEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointGroupEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointGroupEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointGroupEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointGroupEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointGroupEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
