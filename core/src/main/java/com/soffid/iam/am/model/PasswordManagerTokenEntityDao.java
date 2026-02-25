//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity PasswordManagerTokenEntity
 * @see com.soffid.iam.am.model.PasswordManagerTokenEntity
 */
public interface PasswordManagerTokenEntityDao

{
	/**
	 * Operation findByOldToken
	 * @param oldToken
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordManagerTokenEntity findByOldToken(
		java.lang.String oldToken)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.PasswordManagerTokenEntity findByOldToken(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String oldToken)
	;
	/**
	 * Operation findByToken
	 * @param token
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordManagerTokenEntity findByToken(
		java.lang.String token)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.PasswordManagerTokenEntity findByToken(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String token)
	;
	/**
	 * Operation deleteExpired
	**/
	public void deleteExpired() throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} .
	 */
	public com.soffid.iam.am.model.PasswordManagerTokenEntity newPasswordManagerTokenEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.PasswordManagerTokenEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.PasswordManagerTokenEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.PasswordManagerTokenEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.PasswordManagerTokenEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordManagerTokenEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.PasswordManagerTokenEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.PasswordManagerTokenEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.PasswordManagerTokenEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordManagerTokenEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordManagerTokenEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordManagerTokenEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
