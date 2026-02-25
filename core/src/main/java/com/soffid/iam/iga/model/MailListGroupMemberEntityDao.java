//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity MailListGroupMemberEntity
 * @see com.soffid.iam.iga.model.MailListGroupMemberEntity
 */
public interface MailListGroupMemberEntityDao

{
	/**
	 * Operation findByMailListAndGroup
	 * @param mailListId
	 * @param groupId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(
		long mailListId, 
		long groupId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long mailListId, long groupId)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} .
	 */
	public com.soffid.iam.iga.model.MailListGroupMemberEntity newMailListGroupMemberEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailListGroupMemberEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailListGroupMemberEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailListGroupMemberEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailListGroupMemberEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailListGroupMemberEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailListGroupMemberEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailListGroupMemberEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
