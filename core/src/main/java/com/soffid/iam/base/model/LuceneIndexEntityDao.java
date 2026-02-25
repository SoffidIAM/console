//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity LuceneIndexEntity
 * @see com.soffid.iam.base.model.LuceneIndexEntity
 */
public interface LuceneIndexEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.LuceneIndexEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.LuceneIndexEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation lock
	 * @param entity
	**/
	public void lock(
		com.soffid.iam.base.model.LuceneIndexEntity entity) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation refresh
	 * @param entity
	**/
	public void refresh(
		com.soffid.iam.base.model.LuceneIndexEntity entity) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} .
	 */
	public com.soffid.iam.base.model.LuceneIndexEntity newLuceneIndexEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.LuceneIndexEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.LuceneIndexEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.LuceneIndexEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.LuceneIndexEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.LuceneIndexEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.LuceneIndexEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
