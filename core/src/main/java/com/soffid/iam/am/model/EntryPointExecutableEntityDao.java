//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity EntryPointExecutableEntity
 * @see com.soffid.iam.am.model.EntryPointExecutableEntity
 */
public interface EntryPointExecutableEntityDao

{
	/**
	 * Operation findByEntryPoint
	 * @param entryPoint
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> findByEntryPoint(
		com.soffid.iam.am.model.EntryPointEntity entryPoint)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> findByEntryPoint(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.EntryPointEntity entryPoint)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeExecution} object 
	 */
	public void toAccessTreeExecution(com.soffid.iam.am.model.EntryPointExecutableEntity source, com.soffid.iam.am.api.AccessTreeExecution target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeExecution} object 
	 */
	public com.soffid.iam.am.api.AccessTreeExecution toAccessTreeExecution(com.soffid.iam.am.model.EntryPointExecutableEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeExecution} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeExecution> toAccessTreeExecutionList (java.util.Collection<com.soffid.iam.am.model.EntryPointExecutableEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeExecution} object 
	 */
	public void accessTreeExecutionToEntity (com.soffid.iam.am.api.AccessTreeExecution source, com.soffid.iam.am.model.EntryPointExecutableEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeExecution} object 
	 */
	public com.soffid.iam.am.model.EntryPointExecutableEntity accessTreeExecutionToEntity (com.soffid.iam.am.api.AccessTreeExecution instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeExecution} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity>  accessTreeExecutionToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointExecutableEntity newEntryPointExecutableEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointExecutableEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointExecutableEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointExecutableEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointExecutableEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutableEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutableEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutableEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
