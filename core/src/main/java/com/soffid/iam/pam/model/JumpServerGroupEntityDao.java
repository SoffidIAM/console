//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity JumpServerGroupEntity
 * @see com.soffid.iam.pam.model.JumpServerGroupEntity
 */
public interface JumpServerGroupEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.pam.model.JumpServerGroupEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.JumpServerGroup} object 
	 */
	public void toJumpServerGroup(com.soffid.iam.pam.model.JumpServerGroupEntity source, com.soffid.iam.pam.api.JumpServerGroup target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.JumpServerGroup} object 
	 */
	public com.soffid.iam.pam.api.JumpServerGroup toJumpServerGroup(com.soffid.iam.pam.model.JumpServerGroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.JumpServerGroup} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.JumpServerGroup> toJumpServerGroupList (java.util.Collection<com.soffid.iam.pam.model.JumpServerGroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.JumpServerGroup} object 
	 */
	public void jumpServerGroupToEntity (com.soffid.iam.pam.api.JumpServerGroup source, com.soffid.iam.pam.model.JumpServerGroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.JumpServerGroup} object 
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity jumpServerGroupToEntity (com.soffid.iam.pam.api.JumpServerGroup instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.JumpServerGroup} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity>  jumpServerGroupToEntityList (java.util.Collection<com.soffid.iam.pam.api.JumpServerGroup> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} .
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity newJumpServerGroupEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.JumpServerGroupEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.JumpServerGroupEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.JumpServerGroupEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerGroupEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerGroupEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerGroupEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
