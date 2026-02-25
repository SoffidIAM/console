//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ProcessHierarchyEntity
 * @see com.soffid.iam.iga.model.ProcessHierarchyEntity
 */
public interface ProcessHierarchyEntityDao

{
	/**
	 * Operation findByChildren
	 * @param childProcess
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByChildren(
		java.lang.Long childProcess)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByChildren(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long childProcess)
	;
	/**
	 * Operation findByParent
	 * @param parentProcess
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByParent(
		java.lang.Long parentProcess)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long parentProcess)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} .
	 */
	public com.soffid.iam.iga.model.ProcessHierarchyEntity newProcessHierarchyEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ProcessHierarchyEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ProcessHierarchyEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ProcessHierarchyEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ProcessHierarchyEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ProcessHierarchyEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ProcessHierarchyEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ProcessHierarchyEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
