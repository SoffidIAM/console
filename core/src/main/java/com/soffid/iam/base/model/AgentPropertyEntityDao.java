//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity AgentPropertyEntity
 * @see com.soffid.iam.base.model.AgentPropertyEntity
 */
public interface AgentPropertyEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.AgentProperty} object 
	 */
	public void toAgentProperty(com.soffid.iam.base.model.AgentPropertyEntity source, com.soffid.iam.base.api.AgentProperty target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AgentProperty} object 
	 */
	public com.soffid.iam.base.api.AgentProperty toAgentProperty(com.soffid.iam.base.model.AgentPropertyEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AgentProperty} list 
	 */
	public java.util.List<com.soffid.iam.base.api.AgentProperty> toAgentPropertyList (java.util.Collection<com.soffid.iam.base.model.AgentPropertyEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.AgentProperty} object 
	 */
	public void agentPropertyToEntity (com.soffid.iam.base.api.AgentProperty source, com.soffid.iam.base.model.AgentPropertyEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AgentProperty} object 
	 */
	public com.soffid.iam.base.model.AgentPropertyEntity agentPropertyToEntity (com.soffid.iam.base.api.AgentProperty instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AgentProperty} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AgentPropertyEntity>  agentPropertyToEntityList (java.util.Collection<com.soffid.iam.base.api.AgentProperty> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} .
	 */
	public com.soffid.iam.base.model.AgentPropertyEntity newAgentPropertyEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AgentPropertyEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AgentPropertyEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AgentPropertyEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AgentPropertyEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AgentPropertyEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AgentPropertyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AgentPropertyEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AgentPropertyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AgentPropertyEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AgentPropertyEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AgentPropertyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AgentPropertyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
