//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity MailListContainerEntity
 * @see com.soffid.iam.iga.model.MailListContainerEntity
 */
public interface MailListContainerEntityDao

{
	/**
	 * Operation findByContainerAndContained
	 * @param ownerName
	 * @param ownerDomain
	 * @param ownedName
	 * @param ownedDomain
	 * @return
	**/
	public com.soffid.iam.iga.model.MailListContainerEntity findByContainerAndContained(
		java.lang.String ownerName, 
		java.lang.String ownerDomain, 
		java.lang.String ownedName, 
		java.lang.String ownedDomain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity findByContainerAndContained(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownerName, java.lang.String ownerDomain, java.lang.String ownedName, java.lang.String ownedDomain)
	;
	/**
	 * Operation findByContained
	 * @param ownedName
	 * @param ownedDomain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContained(
		java.lang.String ownedName, 
		java.lang.String ownedDomain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContained(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownedName, java.lang.String ownedDomain)
	;
	/**
	 * Operation findByContainer
	 * @param ownerName
	 * @param ownerDomain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContainer(
		java.lang.String ownerName, 
		java.lang.String ownerDomain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContainer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownerName, java.lang.String ownerDomain)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.MailListRelationship} object 
	 */
	public void toMailListRelationship(com.soffid.iam.iga.model.MailListContainerEntity source, com.soffid.iam.iga.api.MailListRelationship target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailListRelationship} object 
	 */
	public com.soffid.iam.iga.api.MailListRelationship toMailListRelationship(com.soffid.iam.iga.model.MailListContainerEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailListRelationship} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.MailListRelationship> toMailListRelationshipList (java.util.Collection<com.soffid.iam.iga.model.MailListContainerEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.MailListRelationship} object 
	 */
	public void mailListRelationshipToEntity (com.soffid.iam.iga.api.MailListRelationship source, com.soffid.iam.iga.model.MailListContainerEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailListRelationship} object 
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity mailListRelationshipToEntity (com.soffid.iam.iga.api.MailListRelationship instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailListRelationship} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity>  mailListRelationshipToEntityList (java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} .
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity newMailListContainerEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailListContainerEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailListContainerEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailListContainerEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailListContainerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailListContainerEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailListContainerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailListContainerEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailListContainerEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
