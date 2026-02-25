//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity InformationSystemEntity
 * @see com.soffid.iam.iga.model.InformationSystemEntity
 */
public interface InformationSystemEntityDao

{
	/**
	 * Operation findByCode
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.InformationSystemEntity findByCode(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity findByCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByText
	 * @param text
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> findByText(
		java.lang.String text)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> findByText(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	;
	/**
	 * Operation findByUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findByUser(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findManageableByUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findManageableByUser(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findManageableByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void toDomainValue(com.soffid.iam.iga.model.InformationSystemEntity source, com.soffid.iam.iga.api.DomainValue target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.api.DomainValue toDomainValue(com.soffid.iam.iga.model.InformationSystemEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.DomainValue> toDomainValueList (java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void domainValueToEntity (com.soffid.iam.iga.api.DomainValue source, com.soffid.iam.iga.model.InformationSystemEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity domainValueToEntity (com.soffid.iam.iga.api.DomainValue instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity>  domainValueToEntityList (java.util.Collection<com.soffid.iam.iga.api.DomainValue> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.InformationSystem} object 
	 */
	public void toInformationSystem(com.soffid.iam.iga.model.InformationSystemEntity source, com.soffid.iam.iga.api.InformationSystem target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.InformationSystem} object 
	 */
	public com.soffid.iam.iga.api.InformationSystem toInformationSystem(com.soffid.iam.iga.model.InformationSystemEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.InformationSystem} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.InformationSystem> toInformationSystemList (java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.InformationSystem} object 
	 */
	public void informationSystemToEntity (com.soffid.iam.iga.api.InformationSystem source, com.soffid.iam.iga.model.InformationSystemEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.InformationSystem} object 
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity informationSystemToEntity (com.soffid.iam.iga.api.InformationSystem instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.InformationSystem} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity>  informationSystemToEntityList (java.util.Collection<com.soffid.iam.iga.api.InformationSystem> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} .
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity newInformationSystemEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.InformationSystemEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.InformationSystemEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.InformationSystemEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.InformationSystemEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.InformationSystemEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
