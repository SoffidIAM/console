//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity SamlRequestEntity
 * @see com.soffid.iam.am.model.SamlRequestEntity
 */
public interface SamlRequestEntityDao

{
	/**
	 * Operation findByExternalId
	 * @param externalId
	 * @return
	**/
	public com.soffid.iam.am.model.SamlRequestEntity findByExternalId(
		java.lang.String externalId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.SamlRequestEntity findByExternalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	;
	/**
	 * Operation findExpired
	 * @param d
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.SamlRequestEntity> findExpired(
		java.util.Date d)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.am.model.SamlRequestEntity> findExpired(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date d)
	;
	/**
	 * Operation deleteExpired
	**/
	public void deleteExpired() throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} .
	 */
	public com.soffid.iam.am.model.SamlRequestEntity newSamlRequestEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.SamlRequestEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.SamlRequestEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.SamlRequestEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.SamlRequestEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.SamlRequestEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.SamlRequestEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.SamlRequestEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.SamlRequestEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.SamlRequestEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.SamlRequestEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.SamlRequestEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.SamlRequestEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
