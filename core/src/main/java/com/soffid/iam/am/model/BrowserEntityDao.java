//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity BrowserEntity
 * @see com.soffid.iam.am.model.BrowserEntity
 */
public interface BrowserEntityDao

{
	/**
	 * Operation findByHost
	 * @param hostId
	 * @return
	**/
	public com.soffid.iam.am.model.BrowserEntity findByHost(
		java.lang.Long hostId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.BrowserEntity findByHost(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long hostId)
	;
	/**
	 * Operation findBySerialNumber
	 * @param serialNumber
	 * @return
	**/
	public com.soffid.iam.am.model.BrowserEntity findBySerialNumber(
		java.lang.String serialNumber)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.BrowserEntity findBySerialNumber(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serialNumber)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Browser} object 
	 */
	public void toBrowser(com.soffid.iam.am.model.BrowserEntity source, com.soffid.iam.am.api.Browser target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Browser} object 
	 */
	public com.soffid.iam.am.api.Browser toBrowser(com.soffid.iam.am.model.BrowserEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Browser} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Browser> toBrowserList (java.util.Collection<com.soffid.iam.am.model.BrowserEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Browser} object 
	 */
	public void browserToEntity (com.soffid.iam.am.api.Browser source, com.soffid.iam.am.model.BrowserEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Browser} object 
	 */
	public com.soffid.iam.am.model.BrowserEntity browserToEntity (com.soffid.iam.am.api.Browser instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Browser} list 
	 */
	public java.util.List<com.soffid.iam.am.model.BrowserEntity>  browserToEntityList (java.util.Collection<com.soffid.iam.am.api.Browser> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.BrowserEntity} .
	 */
	public com.soffid.iam.am.model.BrowserEntity newBrowserEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.BrowserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.BrowserEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.BrowserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.BrowserEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.BrowserEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.BrowserEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.BrowserEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.BrowserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.BrowserEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.BrowserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.BrowserEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.BrowserEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.BrowserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.BrowserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
