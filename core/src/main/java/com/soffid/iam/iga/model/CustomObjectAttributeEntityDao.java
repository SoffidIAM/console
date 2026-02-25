//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity CustomObjectAttributeEntity
 * @see com.soffid.iam.iga.model.CustomObjectAttributeEntity
 */
public interface CustomObjectAttributeEntityDao

{
	/**
	 * Operation findByTypeNameAndValue
	 * @param type
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.CustomObjectAttributeEntity> findByTypeNameAndValue(
		java.lang.String type, 
		java.lang.String name, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectAttributeEntity> findByTypeNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String name, java.lang.String value)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} .
	 */
	public com.soffid.iam.iga.model.CustomObjectAttributeEntity newCustomObjectAttributeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.CustomObjectAttributeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.CustomObjectAttributeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.CustomObjectAttributeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.CustomObjectAttributeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectAttributeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectAttributeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectAttributeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectAttributeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
