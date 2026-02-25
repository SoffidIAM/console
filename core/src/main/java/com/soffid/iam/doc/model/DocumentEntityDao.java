//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.model;
/**
 * DAO for Entity DocumentEntity
 * @see com.soffid.iam.doc.model.DocumentEntity
 */
public interface DocumentEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.doc.model.DocumentEntity} .
	 */
	public com.soffid.iam.doc.model.DocumentEntity newDocumentEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.doc.model.DocumentEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.doc.model.DocumentEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.doc.model.DocumentEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.doc.model.DocumentEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.doc.model.DocumentEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public com.soffid.iam.doc.model.DocumentEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.doc.model.DocumentEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.doc.model.DocumentEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
