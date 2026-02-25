//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity IssueBrowserEntity
 * @see com.soffid.iam.rc.model.IssueBrowserEntity
 */
public interface IssueBrowserEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.IssueBrowser} object 
	 */
	public void toIssueBrowser(com.soffid.iam.rc.model.IssueBrowserEntity source, com.soffid.iam.rc.api.IssueBrowser target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueBrowser} object 
	 */
	public com.soffid.iam.rc.api.IssueBrowser toIssueBrowser(com.soffid.iam.rc.model.IssueBrowserEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueBrowser} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueBrowser> toIssueBrowserList (java.util.Collection<com.soffid.iam.rc.model.IssueBrowserEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssueBrowser} object 
	 */
	public void issueBrowserToEntity (com.soffid.iam.rc.api.IssueBrowser source, com.soffid.iam.rc.model.IssueBrowserEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueBrowser} object 
	 */
	public com.soffid.iam.rc.model.IssueBrowserEntity issueBrowserToEntity (com.soffid.iam.rc.api.IssueBrowser instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueBrowser} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity>  issueBrowserToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssueBrowser> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} .
	 */
	public com.soffid.iam.rc.model.IssueBrowserEntity newIssueBrowserEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssueBrowserEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssueBrowserEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssueBrowserEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssueBrowserEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssueBrowserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssueBrowserEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssueBrowserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssueBrowserEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssueBrowserEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
