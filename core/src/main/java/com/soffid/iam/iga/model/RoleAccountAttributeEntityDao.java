//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity RoleAccountAttributeEntity
 * @see com.soffid.iam.iga.model.RoleAccountAttributeEntity
 */
public interface RoleAccountAttributeEntityDao

{
	/**
	 * Operation findByNameAndValue
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountAttributeEntity> findByNameAndValue(
		java.lang.String name, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountAttributeEntity> findByNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String value)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} .
	 */
	public com.soffid.iam.iga.model.RoleAccountAttributeEntity newRoleAccountAttributeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleAccountAttributeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleAccountAttributeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleAccountAttributeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleAccountAttributeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountAttributeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountAttributeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountAttributeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountAttributeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAccountAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
