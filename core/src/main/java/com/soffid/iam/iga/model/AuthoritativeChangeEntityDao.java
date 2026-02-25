//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity AuthoritativeChangeEntity
 * Contains pending authoritative changes
 * @see com.soffid.iam.iga.model.AuthoritativeChangeEntity
 */
public interface AuthoritativeChangeEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} .
	 */
	public com.soffid.iam.iga.model.AuthoritativeChangeEntity newAuthoritativeChangeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AuthoritativeChangeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AuthoritativeChangeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AuthoritativeChangeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AuthoritativeChangeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
