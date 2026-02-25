//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity AccessControlEntity
 * Access control rules for Oracle agent
 * @see com.soffid.iam.iga.model.AccessControlEntity
 */
public interface AccessControlEntityDao

{
	/**
	 * Operation findByAgentCode
	 * @param systemName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> findByAgentCode(
		java.lang.String systemName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> findByAgentCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.AccessControl} object 
	 */
	public void toAccessControl(com.soffid.iam.iga.model.AccessControlEntity source, com.soffid.iam.iga.api.AccessControl target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AccessControl} object 
	 */
	public com.soffid.iam.iga.api.AccessControl toAccessControl(com.soffid.iam.iga.model.AccessControlEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AccessControl} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.AccessControl> toAccessControlList (java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.AccessControl} object 
	 */
	public void accessControlToEntity (com.soffid.iam.iga.api.AccessControl source, com.soffid.iam.iga.model.AccessControlEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AccessControl} object 
	 */
	public com.soffid.iam.iga.model.AccessControlEntity accessControlToEntity (com.soffid.iam.iga.api.AccessControl instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AccessControl} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.AccessControlEntity>  accessControlToEntityList (java.util.Collection<com.soffid.iam.iga.api.AccessControl> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} .
	 */
	public com.soffid.iam.iga.model.AccessControlEntity newAccessControlEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AccessControlEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AccessControlEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AccessControlEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AccessControlEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AccessControlEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AccessControlEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AccessControlEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AccessControlEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AccessControlEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AccessControlEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AccessControlEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AccessControlEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
