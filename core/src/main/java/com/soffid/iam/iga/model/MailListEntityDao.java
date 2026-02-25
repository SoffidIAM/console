//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity MailListEntity
 * @see com.soffid.iam.iga.model.MailListEntity
 */
public interface MailListEntityDao

{
	/**
	 * Operation findByNameAndDomain
	 * @param name
	 * @param domain
	 * @return
	**/
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomain(
		java.lang.String name, 
		java.lang.String domain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain)
	;
	/**
	 * Operation findByNameAndDomainDeleted
	 * @param name
	 * @param domain
	 * @return
	**/
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomainDeleted(
		java.lang.String name, 
		java.lang.String domain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomainDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain)
	;
	/**
	 * Operation findByData
	 * @param name
	 * @param domain
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByData(
		java.lang.String name, 
		java.lang.String domain, 
		java.lang.String description)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByData(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain, java.lang.String description)
	;
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation generateUpdateTasks
	 * @param entity
	**/
	public void generateUpdateTasks(
		com.soffid.iam.iga.model.MailListEntity entity) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.MailList} object 
	 */
	public void toMailList(com.soffid.iam.iga.model.MailListEntity source, com.soffid.iam.iga.api.MailList target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailList} object 
	 */
	public com.soffid.iam.iga.api.MailList toMailList(com.soffid.iam.iga.model.MailListEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailList} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.MailList> toMailListList (java.util.Collection<com.soffid.iam.iga.model.MailListEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.MailList} object 
	 */
	public void mailListToEntity (com.soffid.iam.iga.api.MailList source, com.soffid.iam.iga.model.MailListEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailList} object 
	 */
	public com.soffid.iam.iga.model.MailListEntity mailListToEntity (com.soffid.iam.iga.api.MailList instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailList} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity>  mailListToEntityList (java.util.Collection<com.soffid.iam.iga.api.MailList> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailListEntity} .
	 */
	public com.soffid.iam.iga.model.MailListEntity newMailListEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailListEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailListEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailListEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailListEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailListEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailListEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailListEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailListEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailListEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailListEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailListEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
