//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity GroupAttributeEntity
 * @see com.soffid.iam.iga.model.GroupAttributeEntity
 */
public interface GroupAttributeEntityDao

{
	/**
	 * Operation findByNameAndValue
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupAttributeEntity> findByNameAndValue(
		java.lang.String name, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupAttributeEntity> findByNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String value)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.GroupAttributeEntity} .
	 */
	public com.soffid.iam.iga.model.GroupAttributeEntity newGroupAttributeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.GroupAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.GroupAttributeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.GroupAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.GroupAttributeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.GroupAttributeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.GroupAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.GroupAttributeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.GroupAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupAttributeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.GroupAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.GroupAttributeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.GroupAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.GroupAttributeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.GroupAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.GroupAttributeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
