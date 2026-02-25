//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity SoffidLicenseEntity
 * @see com.soffid.iam.base.model.SoffidLicenseEntity
 */
public interface SoffidLicenseEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} .
	 */
	public com.soffid.iam.base.model.SoffidLicenseEntity newSoffidLicenseEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.SoffidLicenseEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.SoffidLicenseEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.SoffidLicenseEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.SoffidLicenseEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.SoffidLicenseEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.SoffidLicenseEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.SoffidLicenseEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.SoffidLicenseEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.SoffidLicenseEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
