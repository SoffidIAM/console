//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity DomainValueEntity
 * @see com.soffid.iam.iga.model.DomainValueEntity
 */
public interface DomainValueEntityDao

{
	/**
	 * Operation findByApplicationDomainValue
	 * @param app
	 * @param domain
	 * @param value
	 * @return
	**/
	public com.soffid.iam.iga.model.DomainValueEntity findByApplicationDomainValue(
		java.lang.String app, 
		java.lang.String domain, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.DomainValueEntity findByApplicationDomainValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String app, java.lang.String domain, java.lang.String value)
	;
	/**
	 * Operation findByRoleAndValue
	 * @param roleId
	 * @param value
	 * @return
	**/
	public com.soffid.iam.iga.model.DomainValueEntity findByRoleAndValue(
		java.lang.Long roleId, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.DomainValueEntity findByRoleAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId, java.lang.String value)
	;
	/**
	 * Operation findByText
	 * @param domain
	 * @param text
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> findByText(
		com.soffid.iam.iga.api.Domain domain, 
		java.lang.String text)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> findByText(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.api.Domain domain, java.lang.String text)
	;
	/**
	 * Operation findByInformationSystem
	 * @param informationSystem
	 * @param domain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> findByInformationSystem(
		java.lang.String informationSystem, 
		java.lang.String domain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> findByInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem, java.lang.String domain)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void toDomainValue(com.soffid.iam.iga.model.DomainValueEntity source, com.soffid.iam.iga.api.DomainValue target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.api.DomainValue toDomainValue(com.soffid.iam.iga.model.DomainValueEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.DomainValue> toDomainValueList (java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void domainValueToEntity (com.soffid.iam.iga.api.DomainValue source, com.soffid.iam.iga.model.DomainValueEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.model.DomainValueEntity domainValueToEntity (com.soffid.iam.iga.api.DomainValue instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity>  domainValueToEntityList (java.util.Collection<com.soffid.iam.iga.api.DomainValue> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} .
	 */
	public com.soffid.iam.iga.model.DomainValueEntity newDomainValueEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.DomainValueEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.DomainValueEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.DomainValueEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.DomainValueEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.DomainValueEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.DomainValueEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.DomainValueEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.DomainValueEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.DomainValueEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
