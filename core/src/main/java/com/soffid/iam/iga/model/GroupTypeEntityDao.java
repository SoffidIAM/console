//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity GroupTypeEntity
 * @see com.soffid.iam.iga.model.GroupTypeEntity
 */
public interface GroupTypeEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupTypeEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByFilter
	 * @param name
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> findByFilter(
		java.lang.String name, 
		java.lang.String description)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> findByFilter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String description)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.GroupType} object 
	 */
	public void toGroupType(com.soffid.iam.iga.model.GroupTypeEntity source, com.soffid.iam.iga.api.GroupType target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.GroupType} object 
	 */
	public com.soffid.iam.iga.api.GroupType toGroupType(com.soffid.iam.iga.model.GroupTypeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.GroupType} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.GroupType> toGroupTypeList (java.util.Collection<com.soffid.iam.iga.model.GroupTypeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.GroupType} object 
	 */
	public void groupTypeToEntity (com.soffid.iam.iga.api.GroupType source, com.soffid.iam.iga.model.GroupTypeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.GroupType} object 
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity groupTypeToEntity (com.soffid.iam.iga.api.GroupType instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.GroupType} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity>  groupTypeToEntityList (java.util.Collection<com.soffid.iam.iga.api.GroupType> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} .
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity newGroupTypeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.GroupTypeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.GroupTypeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.GroupTypeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.GroupTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.GroupTypeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.GroupTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.GroupTypeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.GroupTypeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
