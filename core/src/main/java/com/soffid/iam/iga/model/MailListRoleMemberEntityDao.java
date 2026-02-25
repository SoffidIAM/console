//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity MailListRoleMemberEntity
 * @see com.soffid.iam.iga.model.MailListRoleMemberEntity
 */
public interface MailListRoleMemberEntityDao

{
	/**
	 * Operation findByMailListAndGroup
	 * @param mailListId
	 * @param roleId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(
		long mailListId, 
		long roleId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long mailListId, long roleId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.MailListRoleMember} object 
	 */
	public void toMailListRoleMember(com.soffid.iam.iga.model.MailListRoleMemberEntity source, com.soffid.iam.iga.api.MailListRoleMember target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailListRoleMember} object 
	 */
	public com.soffid.iam.iga.api.MailListRoleMember toMailListRoleMember(com.soffid.iam.iga.model.MailListRoleMemberEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailListRoleMember} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.MailListRoleMember> toMailListRoleMemberList (java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.MailListRoleMember} object 
	 */
	public void mailListRoleMemberToEntity (com.soffid.iam.iga.api.MailListRoleMember source, com.soffid.iam.iga.model.MailListRoleMemberEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailListRoleMember} object 
	 */
	public com.soffid.iam.iga.model.MailListRoleMemberEntity mailListRoleMemberToEntity (com.soffid.iam.iga.api.MailListRoleMember instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailListRoleMember} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity>  mailListRoleMemberToEntityList (java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} .
	 */
	public com.soffid.iam.iga.model.MailListRoleMemberEntity newMailListRoleMemberEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailListRoleMemberEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailListRoleMemberEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailListRoleMemberEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailListRoleMemberEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailListRoleMemberEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailListRoleMemberEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailListRoleMemberEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
