//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity RoleAttributeEntity
 * @see com.soffid.iam.iga.model.RoleAttributeEntity
 */
public interface RoleAttributeEntityDao

{
	/**
	 * Operation findByNameAndValue
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> findByNameAndValue(
		java.lang.String name, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> findByNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String value)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} .
	 */
	public com.soffid.iam.iga.model.RoleAttributeEntity newRoleAttributeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleAttributeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleAttributeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleAttributeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleAttributeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAttributeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAttributeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAttributeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
