//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity AttributeMappingEntity
 * @see com.soffid.iam.iga.model.AttributeMappingEntity
 */
public interface AttributeMappingEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.AttributeMapping} object 
	 */
	public void toAttributeMapping(com.soffid.iam.iga.model.AttributeMappingEntity source, com.soffid.iam.iga.api.AttributeMapping target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AttributeMapping} object 
	 */
	public com.soffid.iam.iga.api.AttributeMapping toAttributeMapping(com.soffid.iam.iga.model.AttributeMappingEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AttributeMapping} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.AttributeMapping> toAttributeMappingList (java.util.Collection<com.soffid.iam.iga.model.AttributeMappingEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.AttributeMapping} object 
	 */
	public void attributeMappingToEntity (com.soffid.iam.iga.api.AttributeMapping source, com.soffid.iam.iga.model.AttributeMappingEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AttributeMapping} object 
	 */
	public com.soffid.iam.iga.model.AttributeMappingEntity attributeMappingToEntity (com.soffid.iam.iga.api.AttributeMapping instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AttributeMapping} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity>  attributeMappingToEntityList (java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} .
	 */
	public com.soffid.iam.iga.model.AttributeMappingEntity newAttributeMappingEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AttributeMappingEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AttributeMappingEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AttributeMappingEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AttributeMappingEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AttributeMappingEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeMappingEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AttributeMappingEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeMappingEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeMappingEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
