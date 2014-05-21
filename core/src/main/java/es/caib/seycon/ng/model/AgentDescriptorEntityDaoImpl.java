// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;
/**
 * @see es.caib.seycon.ng.model.AgentDescriptorEntity
 */
public class AgentDescriptorEntityDaoImpl
    extends es.caib.seycon.ng.model.AgentDescriptorEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.AgentDescriptorEntityDao#toAgentDescriptor(es.caib.seycon.ng.model.AgentDescriptorEntity, es.caib.seycon.ng.comu.AgentDescriptor)
     */
    public void toAgentDescriptor(
        es.caib.seycon.ng.model.AgentDescriptorEntity source,
        es.caib.seycon.ng.comu.AgentDescriptor target)
    {
        super.toAgentDescriptor(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.AgentDescriptorEntityDao#toAgentDescriptor(es.caib.seycon.ng.model.AgentDescriptorEntity)
     */
    public es.caib.seycon.ng.comu.AgentDescriptor toAgentDescriptor(final es.caib.seycon.ng.model.AgentDescriptorEntity entity)
    {
        return super.toAgentDescriptor(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.AgentDescriptorEntity loadAgentDescriptorEntityFromAgentDescriptor(es.caib.seycon.ng.comu.AgentDescriptor agentDescriptor)
    {
        es.caib.seycon.ng.model.AgentDescriptorEntity agentDescriptorEntity = this.load(agentDescriptor.getId());
        if (agentDescriptorEntity == null)
        {
            agentDescriptorEntity = newAgentDescriptorEntity();
        }
        return agentDescriptorEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AgentDescriptorEntityDao#agentDescriptorToEntity(es.caib.seycon.ng.comu.AgentDescriptor)
     */
    public es.caib.seycon.ng.model.AgentDescriptorEntity agentDescriptorToEntity(es.caib.seycon.ng.comu.AgentDescriptor agentDescriptor)
    {
        es.caib.seycon.ng.model.AgentDescriptorEntity entity = this.loadAgentDescriptorEntityFromAgentDescriptor(agentDescriptor);
        this.agentDescriptorToEntity(agentDescriptor, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.AgentDescriptorEntityDao#agentDescriptorToEntity(es.caib.seycon.ng.comu.AgentDescriptor, es.caib.seycon.ng.model.AgentDescriptorEntity)
     */
    public void agentDescriptorToEntity(
        es.caib.seycon.ng.comu.AgentDescriptor source,
        es.caib.seycon.ng.model.AgentDescriptorEntity target,
        boolean copyIfNull)
    {
        super.agentDescriptorToEntity(source, target, copyIfNull);
    }

}