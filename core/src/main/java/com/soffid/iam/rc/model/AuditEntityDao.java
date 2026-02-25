//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity AuditEntity
 * @see com.soffid.iam.rc.model.AuditEntity
 */
public interface AuditEntityDao

{
	/**
	 * Operation findById
	 * @param id
	 * @return
	**/
	public com.soffid.iam.rc.model.AuditEntity findById(
		java.lang.Long id)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.rc.model.AuditEntity findById(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	;
	/**
	 * Operation findByIndex
	 * @param searchIndex
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.AuditEntity> findByIndex(
		java.lang.String searchIndex)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.rc.model.AuditEntity> findByIndex(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String searchIndex)
	;
	/**
	 * Operation unlinkAccounts
	 * Unlinks audit logs from account that is going to be removed
	 * @param account
	**/
	public void unlinkAccounts(
		com.soffid.iam.base.model.AccountEntity account) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.Audit} object 
	 */
	public void toAudit(com.soffid.iam.rc.model.AuditEntity source, com.soffid.iam.rc.api.Audit target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.Audit} object 
	 */
	public com.soffid.iam.rc.api.Audit toAudit(com.soffid.iam.rc.model.AuditEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.Audit} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.Audit> toAuditList (java.util.Collection<com.soffid.iam.rc.model.AuditEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.Audit} object 
	 */
	public void auditToEntity (com.soffid.iam.rc.api.Audit source, com.soffid.iam.rc.model.AuditEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.Audit} object 
	 */
	public com.soffid.iam.rc.model.AuditEntity auditToEntity (com.soffid.iam.rc.api.Audit instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.Audit} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.AuditEntity>  auditToEntityList (java.util.Collection<com.soffid.iam.rc.api.Audit> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.AuditEntity} .
	 */
	public com.soffid.iam.rc.model.AuditEntity newAuditEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.AuditEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.AuditEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.AuditEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.AuditEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.AuditEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.AuditEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.AuditEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.AuditEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.AuditEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.AuditEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.AuditEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.AuditEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.AuditEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.AuditEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
