//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ExternalNameEntity
 * @see com.soffid.iam.iga.model.ExternalNameEntity
 */
public interface ExternalNameEntityDao

{
	/**
	 * Operation findByAddress
	 * @param address
	 * @return
	**/
	public com.soffid.iam.iga.model.ExternalNameEntity findByAddress(
		java.lang.String address)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity findByAddress(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String address)
	;
	/**
	 * Operation findByList
	 * @param listName
	 * @param listDomain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> findByList(
		java.lang.String listName, 
		java.lang.String listDomain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> findByList(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String listName, java.lang.String listDomain)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ExternalName} object 
	 */
	public void toExternalName(com.soffid.iam.iga.model.ExternalNameEntity source, com.soffid.iam.iga.api.ExternalName target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ExternalName} object 
	 */
	public com.soffid.iam.iga.api.ExternalName toExternalName(com.soffid.iam.iga.model.ExternalNameEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ExternalName} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ExternalName> toExternalNameList (java.util.Collection<com.soffid.iam.iga.model.ExternalNameEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ExternalName} object 
	 */
	public void externalNameToEntity (com.soffid.iam.iga.api.ExternalName source, com.soffid.iam.iga.model.ExternalNameEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ExternalName} object 
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity externalNameToEntity (com.soffid.iam.iga.api.ExternalName instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ExternalName} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity>  externalNameToEntityList (java.util.Collection<com.soffid.iam.iga.api.ExternalName> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} .
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity newExternalNameEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ExternalNameEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ExternalNameEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ExternalNameEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ExternalNameEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ExternalNameEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ExternalNameEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ExternalNameEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ExternalNameEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
