//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity EntryPointEntity
 * @see com.soffid.iam.am.model.EntryPointEntity
 */
public interface EntryPointEntityDao

{
	/**
	 * Operation findByCriteria
	 * @param name
	 * @param code
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> findByCriteria(
		java.lang.String name, 
		java.lang.String code)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> findByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String code)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTree} object 
	 */
	public void toAccessTree(com.soffid.iam.am.model.EntryPointEntity source, com.soffid.iam.am.api.AccessTree target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTree} object 
	 */
	public com.soffid.iam.am.api.AccessTree toAccessTree(com.soffid.iam.am.model.EntryPointEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTree} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTree> toAccessTreeList (java.util.Collection<com.soffid.iam.am.model.EntryPointEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTree} object 
	 */
	public void accessTreeToEntity (com.soffid.iam.am.api.AccessTree source, com.soffid.iam.am.model.EntryPointEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTree} object 
	 */
	public com.soffid.iam.am.model.EntryPointEntity accessTreeToEntity (com.soffid.iam.am.api.AccessTree instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTree} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity>  accessTreeToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTree> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointEntity newEntryPointEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
