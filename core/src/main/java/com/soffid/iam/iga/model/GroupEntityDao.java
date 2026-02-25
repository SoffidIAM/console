//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity GroupEntity
 * @see com.soffid.iam.iga.model.GroupEntity
 */
public interface GroupEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.GroupEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByNameAndDate
	 * @param name
	 * @param d
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findByNameAndDate(
		java.lang.String name, 
		java.util.Date d)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.GroupEntity findByNameAndDate(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.util.Date d)
	;
	/**
	 * Operation findByNameDeleted
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findByNameDeleted(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.GroupEntity findByNameDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findPrimaryGroupByUser
	 * @param userName
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findPrimaryGroupByUser(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.GroupEntity findPrimaryGroupByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findByChild
	 * @param groupName
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findByChild(
		java.lang.String groupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.GroupEntity findByChild(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	;
	/**
	 * Operation findByText
	 * @param text
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.GroupEntity> findByText(
		java.lang.String text)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.GroupEntity> findByText(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	;
	/**
	 * Operation findGroupNames
	 * @return
	**/
	public java.util.Collection<java.lang.String> findGroupNames()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<java.lang.String> findGroupNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findByParent
	 * @param parent
	 * @param d
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(
		java.lang.String parent, 
		java.util.Date d)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String parent, java.util.Date d)
	;
	/**
	 * Operation findByType
	 * @param unitType
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByType(
		java.lang.String unitType)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String unitType)
	;
	/**
	 * Operation findByGrantedRolesToUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByGrantedRolesToUser(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByGrantedRolesToUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findGroupsByUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findGroupsByUser(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findGroupsByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findByParent
	 * @param parent
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(
		java.lang.String parent)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String parent)
	;
	/**
	 * Operation setParentGroup
	 * @param codiSubGrup
	 * @param codiSuperGrup
	**/
	public void setParentGroup(
		java.lang.String codiSubGrup, 
		java.lang.String codiSuperGrup) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void toDomainValue(com.soffid.iam.iga.model.GroupEntity source, com.soffid.iam.iga.api.DomainValue target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.api.DomainValue toDomainValue(com.soffid.iam.iga.model.GroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.DomainValue> toDomainValueList (java.util.Collection<com.soffid.iam.iga.model.GroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void domainValueToEntity (com.soffid.iam.iga.api.DomainValue source, com.soffid.iam.iga.model.GroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.model.GroupEntity domainValueToEntity (com.soffid.iam.iga.api.DomainValue instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity>  domainValueToEntityList (java.util.Collection<com.soffid.iam.iga.api.DomainValue> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Group} object 
	 */
	public void toGroup(com.soffid.iam.iga.model.GroupEntity source, com.soffid.iam.iga.api.Group target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Group} object 
	 */
	public com.soffid.iam.iga.api.Group toGroup(com.soffid.iam.iga.model.GroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Group} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Group> toGroupList (java.util.Collection<com.soffid.iam.iga.model.GroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Group} object 
	 */
	public void groupToEntity (com.soffid.iam.iga.api.Group source, com.soffid.iam.iga.model.GroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Group} object 
	 */
	public com.soffid.iam.iga.model.GroupEntity groupToEntity (com.soffid.iam.iga.api.Group instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Group} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity>  groupToEntityList (java.util.Collection<com.soffid.iam.iga.api.Group> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void toIdentity(com.soffid.iam.iga.model.GroupEntity source, com.soffid.iam.base.api.Identity target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.base.api.Identity toIdentity(com.soffid.iam.iga.model.GroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Identity> toIdentityList (java.util.Collection<com.soffid.iam.iga.model.GroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void identityToEntity (com.soffid.iam.base.api.Identity source, com.soffid.iam.iga.model.GroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.iga.model.GroupEntity identityToEntity (com.soffid.iam.base.api.Identity instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity>  identityToEntityList (java.util.Collection<com.soffid.iam.base.api.Identity> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.GroupEntity} .
	 */
	public com.soffid.iam.iga.model.GroupEntity newGroupEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.GroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.GroupEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.GroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.GroupEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.GroupEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.GroupEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.GroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.GroupEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.GroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.GroupEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.GroupEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
