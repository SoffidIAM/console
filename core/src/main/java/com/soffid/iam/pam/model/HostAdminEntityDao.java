//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity HostAdminEntity
 * @see com.soffid.iam.pam.model.HostAdminEntity
 */
public interface HostAdminEntityDao

{
	/**
	 * Operation findByHostNameAndRequestDate
	 * @param nomHost
	 * @param requestDate
	 * @param expirationDate
	 * @param nullDate
	 * @return
	**/
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> findByHostNameAndRequestDate(
		java.lang.String nomHost, 
		java.util.Date requestDate, 
		java.util.Date expirationDate, 
		java.util.Date nullDate)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> findByHostNameAndRequestDate(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomHost, java.util.Date requestDate, java.util.Date expirationDate, java.util.Date nullDate)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.HostAdmin} object 
	 */
	public void toHostAdmin(com.soffid.iam.pam.model.HostAdminEntity source, com.soffid.iam.pam.api.HostAdmin target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostAdmin} object 
	 */
	public com.soffid.iam.pam.api.HostAdmin toHostAdmin(com.soffid.iam.pam.model.HostAdminEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostAdmin} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.HostAdmin> toHostAdminList (java.util.Collection<com.soffid.iam.pam.model.HostAdminEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.HostAdmin} object 
	 */
	public void hostAdminToEntity (com.soffid.iam.pam.api.HostAdmin source, com.soffid.iam.pam.model.HostAdminEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostAdmin} object 
	 */
	public com.soffid.iam.pam.model.HostAdminEntity hostAdminToEntity (com.soffid.iam.pam.api.HostAdmin instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostAdmin} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity>  hostAdminToEntityList (java.util.Collection<com.soffid.iam.pam.api.HostAdmin> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} .
	 */
	public com.soffid.iam.pam.model.HostAdminEntity newHostAdminEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.HostAdminEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.HostAdminEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.HostAdminEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.HostAdminEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.HostAdminEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.HostAdminEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.HostAdminEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.HostAdminEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.HostAdminEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
