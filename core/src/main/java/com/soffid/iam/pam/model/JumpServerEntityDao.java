//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity JumpServerEntity
 * @see com.soffid.iam.pam.model.JumpServerEntity
 */
public interface JumpServerEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} .
	 */
	public com.soffid.iam.pam.model.JumpServerEntity newJumpServerEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.JumpServerEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.JumpServerEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.JumpServerEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.JumpServerEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.JumpServerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.JumpServerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
