// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;
/**
 * @see es.caib.seycon.ng.model.AgentDescriptorEntity
 */
public class AgentDescriptorEntityDaoImpl
    extends com.soffid.iam.model.AgentDescriptorEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AgentDescriptorEntityDao#toAgentDescriptor(es.caib.seycon.ng.model.AgentDescriptorEntity, es.caib.seycon.ng.comu.AgentDescriptor)
     */
    public void toAgentDescriptor(com.soffid.iam.model.AgentDescriptorEntity source, com.soffid.iam.api.AgentDescriptor target) {
        super.toAgentDescriptor(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.AgentDescriptorEntityDao#toAgentDescriptor(es.caib.seycon.ng.model.AgentDescriptorEntity)
     */
    public com.soffid.iam.api.AgentDescriptor toAgentDescriptor(final com.soffid.iam.model.AgentDescriptorEntity entity) {
        return super.toAgentDescriptor(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.AgentDescriptorEntity loadAgentDescriptorEntityFromAgentDescriptor(com.soffid.iam.api.AgentDescriptor agentDescriptor) {
        com.soffid.iam.model.AgentDescriptorEntity agentDescriptorEntity = this.load(agentDescriptor.getId());
        if (agentDescriptorEntity == null)
        {
            agentDescriptorEntity = newAgentDescriptorEntity();
        }
        return agentDescriptorEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AgentDescriptorEntityDao#agentDescriptorToEntity(es.caib.seycon.ng.comu.AgentDescriptor)
     */
    public com.soffid.iam.model.AgentDescriptorEntity agentDescriptorToEntity(com.soffid.iam.api.AgentDescriptor agentDescriptor) {
        com.soffid.iam.model.AgentDescriptorEntity entity = this.loadAgentDescriptorEntityFromAgentDescriptor(agentDescriptor);
        this.agentDescriptorToEntity(agentDescriptor, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AgentDescriptorEntityDao#agentDescriptorToEntity(es.caib.seycon.ng.comu.AgentDescriptor, es.caib.seycon.ng.model.AgentDescriptorEntity)
     */
    public void agentDescriptorToEntity(com.soffid.iam.api.AgentDescriptor source, com.soffid.iam.model.AgentDescriptorEntity target, boolean copyIfNull) {
        super.agentDescriptorToEntity(source, target, copyIfNull);
    }

}