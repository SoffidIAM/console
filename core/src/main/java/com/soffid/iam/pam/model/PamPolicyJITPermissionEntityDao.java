//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity PamPolicyJITPermissionEntity
 * @see com.soffid.iam.pam.model.PamPolicyJITPermissionEntity
 */
public interface PamPolicyJITPermissionEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} .
	 */
	public com.soffid.iam.pam.model.PamPolicyJITPermissionEntity newPamPolicyJITPermissionEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.PamPolicyJITPermissionEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
