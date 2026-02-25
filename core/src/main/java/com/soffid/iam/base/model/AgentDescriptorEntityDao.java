//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity AgentDescriptorEntity
 * @see com.soffid.iam.base.model.AgentDescriptorEntity
 */
public interface AgentDescriptorEntityDao

{
	/**
	 * Operation findByClass
	 * @param tenant
	 * @param className
	 * @return
	**/
	public com.soffid.iam.base.model.AgentDescriptorEntity findByClass(
		java.lang.String tenant, 
		java.lang.String className)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity findByClass(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String tenant, java.lang.String className)
	;
	/**
	 * Operation findAllOnlyBasicData
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findAllOnlyBasicData()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findAllOnlyBasicData(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findByDescription
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findByDescription(
		java.lang.String description)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findByDescription(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String description)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.AgentDescriptor} object 
	 */
	public void toAgentDescriptor(com.soffid.iam.base.model.AgentDescriptorEntity source, com.soffid.iam.base.api.AgentDescriptor target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AgentDescriptor} object 
	 */
	public com.soffid.iam.base.api.AgentDescriptor toAgentDescriptor(com.soffid.iam.base.model.AgentDescriptorEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AgentDescriptor} list 
	 */
	public java.util.List<com.soffid.iam.base.api.AgentDescriptor> toAgentDescriptorList (java.util.Collection<com.soffid.iam.base.model.AgentDescriptorEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.AgentDescriptor} object 
	 */
	public void agentDescriptorToEntity (com.soffid.iam.base.api.AgentDescriptor source, com.soffid.iam.base.model.AgentDescriptorEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AgentDescriptor} object 
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity agentDescriptorToEntity (com.soffid.iam.base.api.AgentDescriptor instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AgentDescriptor} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity>  agentDescriptorToEntityList (java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} .
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity newAgentDescriptorEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AgentDescriptorEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AgentDescriptorEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AgentDescriptorEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AgentDescriptorEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AgentDescriptorEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AgentDescriptorEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AgentDescriptorEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AgentDescriptorEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
