//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity UserTypeEntity
 * @see com.soffid.iam.base.model.UserTypeEntity
 */
public interface UserTypeEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.UserTypeEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.UserTypeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserType} object 
	 */
	public void toUserType(com.soffid.iam.base.model.UserTypeEntity source, com.soffid.iam.iga.api.UserType target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserType} object 
	 */
	public com.soffid.iam.iga.api.UserType toUserType(com.soffid.iam.base.model.UserTypeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserType} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserType> toUserTypeList (java.util.Collection<com.soffid.iam.base.model.UserTypeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserType} object 
	 */
	public void userTypeToEntity (com.soffid.iam.iga.api.UserType source, com.soffid.iam.base.model.UserTypeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserType} object 
	 */
	public com.soffid.iam.base.model.UserTypeEntity userTypeToEntity (com.soffid.iam.iga.api.UserType instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserType} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserTypeEntity>  userTypeToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserType> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserTypeEntity} .
	 */
	public com.soffid.iam.base.model.UserTypeEntity newUserTypeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserTypeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserTypeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserTypeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserTypeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserTypeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserTypeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserTypeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserTypeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
