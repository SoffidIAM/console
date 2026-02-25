//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.model;
/**
 * DAO for Entity DocSign
 * @see com.soffid.iam.doc.model.DocSign
 */
public interface DocSignDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.doc.model.DocSign} .
	 */
	public com.soffid.iam.doc.model.DocSign newDocSign();

	/**
	 * Adds an instance of {@link com.soffid.iam.doc.model.DocSign} to the persistent store.
	 */
	public void create (com.soffid.iam.doc.model.DocSign entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.doc.model.DocSign} at the persistent store.
	 */
	public void update (com.soffid.iam.doc.model.DocSign entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public void remove (com.soffid.iam.doc.model.DocSign entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public com.soffid.iam.doc.model.DocSign load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.doc.model.DocSign> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.doc.model.DocSign} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.doc.model.DocSign> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.doc.model.DocSign} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.doc.model.DocSign> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.doc.model.DocSign> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.doc.model.DocSign> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.doc.model.DocSign> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
