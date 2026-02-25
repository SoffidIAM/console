//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity EntryPointExecutionTypeEntity
 * @see com.soffid.iam.am.model.EntryPointExecutionTypeEntity
 */
public interface EntryPointExecutionTypeEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeExecutionType} object 
	 */
	public void toAccessTreeExecutionType(com.soffid.iam.am.model.EntryPointExecutionTypeEntity source, com.soffid.iam.am.api.AccessTreeExecutionType target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeExecutionType} object 
	 */
	public com.soffid.iam.am.api.AccessTreeExecutionType toAccessTreeExecutionType(com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeExecutionType} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeExecutionType> toAccessTreeExecutionTypeList (java.util.Collection<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeExecutionType} object 
	 */
	public void accessTreeExecutionTypeToEntity (com.soffid.iam.am.api.AccessTreeExecutionType source, com.soffid.iam.am.model.EntryPointExecutionTypeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeExecutionType} object 
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity accessTreeExecutionTypeToEntity (com.soffid.iam.am.api.AccessTreeExecutionType instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeExecutionType} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity>  accessTreeExecutionTypeToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity newEntryPointExecutionTypeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutionTypeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutionTypeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutionTypeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
