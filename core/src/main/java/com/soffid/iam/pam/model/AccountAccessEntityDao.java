//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity AccountAccessEntity
 * Contains the access control list for an account
 * @see com.soffid.iam.pam.model.AccountAccessEntity
 */
public interface AccountAccessEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} .
	 */
	public com.soffid.iam.pam.model.AccountAccessEntity newAccountAccessEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.AccountAccessEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.AccountAccessEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.AccountAccessEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.AccountAccessEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.AccountAccessEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.AccountAccessEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.AccountAccessEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.AccountAccessEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.AccountAccessEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.AccountAccessEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.AccountAccessEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.AccountAccessEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
