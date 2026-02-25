//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.model;
/**
 * DAO for Entity DocumentBlockEntity
 * @see com.soffid.iam.doc.model.DocumentBlockEntity
 */
public interface DocumentBlockEntityDao

{
	/**
	 * Operation findByPath
	 * @param path
	 * @return
	**/
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> findByPath(
		java.lang.String path)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> findByPath(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String path)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} .
	 */
	public com.soffid.iam.doc.model.DocumentBlockEntity newDocumentBlockEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.doc.model.DocumentBlockEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.doc.model.DocumentBlockEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.doc.model.DocumentBlockEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public com.soffid.iam.doc.model.DocumentBlockEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.doc.model.DocumentBlockEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentBlockEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.doc.model.DocumentBlockEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentBlockEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentBlockEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
