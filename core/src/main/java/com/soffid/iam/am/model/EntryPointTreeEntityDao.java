//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity EntryPointTreeEntity
 * @see com.soffid.iam.am.model.EntryPointTreeEntity
 */
public interface EntryPointTreeEntityDao

{
	/**
	 * Operation findByChildren
	 * @param childId
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByChildren(
		java.lang.Long childId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByChildren(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long childId)
	;
	/**
	 * Operation findByParent
	 * @param parentId
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByParent(
		java.lang.Long parentId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long parentId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.ApplicationAccessTree} object 
	 */
	public void toApplicationAccessTree(com.soffid.iam.am.model.EntryPointTreeEntity source, com.soffid.iam.am.api.ApplicationAccessTree target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.ApplicationAccessTree} object 
	 */
	public com.soffid.iam.am.api.ApplicationAccessTree toApplicationAccessTree(com.soffid.iam.am.model.EntryPointTreeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.ApplicationAccessTree} list 
	 */
	public java.util.List<com.soffid.iam.am.api.ApplicationAccessTree> toApplicationAccessTreeList (java.util.Collection<com.soffid.iam.am.model.EntryPointTreeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.ApplicationAccessTree} object 
	 */
	public void applicationAccessTreeToEntity (com.soffid.iam.am.api.ApplicationAccessTree source, com.soffid.iam.am.model.EntryPointTreeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.ApplicationAccessTree} object 
	 */
	public com.soffid.iam.am.model.EntryPointTreeEntity applicationAccessTreeToEntity (com.soffid.iam.am.api.ApplicationAccessTree instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.ApplicationAccessTree} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity>  applicationAccessTreeToEntityList (java.util.Collection<com.soffid.iam.am.api.ApplicationAccessTree> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointTreeEntity newEntryPointTreeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointTreeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointTreeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointTreeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointTreeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointTreeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointTreeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointTreeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointTreeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointTreeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
