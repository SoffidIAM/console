//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity LuceneIndexPartEntity
 * @see com.soffid.iam.base.model.LuceneIndexPartEntity
 */
public interface LuceneIndexPartEntityDao

{
	/**
	 * Operation findByIndex
	 * @param index
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity> findByIndex(
		java.lang.Long index)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity> findByIndex(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long index)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} .
	 */
	public com.soffid.iam.base.model.LuceneIndexPartEntity newLuceneIndexPartEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.LuceneIndexPartEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.LuceneIndexPartEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.LuceneIndexPartEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.LuceneIndexPartEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexPartEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexPartEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexPartEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
