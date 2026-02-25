//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity SystemGroupEntity
 * @see com.soffid.iam.iga.model.SystemGroupEntity
 */
public interface SystemGroupEntityDao

{
	/**
	 * Operation findBySystem
	 * @param systemName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> findBySystem(
		java.lang.String systemName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.SystemGroup} object 
	 */
	public void toSystemGroup(com.soffid.iam.iga.model.SystemGroupEntity source, com.soffid.iam.iga.api.SystemGroup target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.SystemGroup} object 
	 */
	public com.soffid.iam.iga.api.SystemGroup toSystemGroup(com.soffid.iam.iga.model.SystemGroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.SystemGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.SystemGroup> toSystemGroupList (java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.SystemGroup} object 
	 */
	public void systemGroupToEntity (com.soffid.iam.iga.api.SystemGroup source, com.soffid.iam.iga.model.SystemGroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.SystemGroup} object 
	 */
	public com.soffid.iam.iga.model.SystemGroupEntity systemGroupToEntity (com.soffid.iam.iga.api.SystemGroup instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.SystemGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemGroupEntity>  systemGroupToEntityList (java.util.Collection<com.soffid.iam.iga.api.SystemGroup> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} .
	 */
	public com.soffid.iam.iga.model.SystemGroupEntity newSystemGroupEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.SystemGroupEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.SystemGroupEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.SystemGroupEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.SystemGroupEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemGroupEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.SystemGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.SystemGroupEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.SystemGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.SystemGroupEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.SystemGroupEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
