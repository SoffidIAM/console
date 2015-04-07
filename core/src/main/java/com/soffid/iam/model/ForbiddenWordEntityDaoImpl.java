// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;
/**
 * @see es.caib.seycon.ng.model.ParaulesProhibidesEntity
 */
public class ForbiddenWordEntityDaoImpl
    extends com.soffid.iam.model.ForbiddenWordEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ParaulesProhibidesEntityDao#toParaulaProhibida(es.caib.seycon.ng.model.ParaulesProhibidesEntity, es.caib.seycon.ng.comu.ParaulaProhibida)
     */
    public void toParaulaProhibida(com.soffid.iam.model.ForbiddenWordEntity source, es.caib.seycon.ng.comu.ParaulaProhibida target) {
        // @todo verify behavior of toParaulaProhibida
        super.toParaulaProhibida(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulesProhibidesEntityDao#toParaulaProhibida(es.caib.seycon.ng.model.ParaulesProhibidesEntity)
     */
    public es.caib.seycon.ng.comu.ParaulaProhibida toParaulaProhibida(final com.soffid.iam.model.ForbiddenWordEntity entity) {
        // @todo verify behavior of toParaulaProhibida
        return super.toParaulaProhibida(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.ForbiddenWordEntity loadParaulesProhibidesEntityFromParaulaProhibida(es.caib.seycon.ng.comu.ParaulaProhibida paraulaProhibida) {
		com.soffid.iam.model.ForbiddenWordEntity paraulesProhibidesEntity = null;
		if (paraulaProhibida.getId() != null) {
			paraulesProhibidesEntity = this.load(paraulaProhibida.getId());
		}
		if (paraulesProhibidesEntity == null) {
			paraulesProhibidesEntity = newForbiddenWordEntity();
		}
		return paraulesProhibidesEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ParaulesProhibidesEntityDao#paraulaProhibidaToEntity(es.caib.seycon.ng.comu.ParaulaProhibida)
     */
    public com.soffid.iam.model.ForbiddenWordEntity paraulaProhibidaToEntity(es.caib.seycon.ng.comu.ParaulaProhibida paraulaProhibida) {
        // @todo verify behavior of paraulaProhibidaToEntity
        com.soffid.iam.model.ForbiddenWordEntity entity = this.loadParaulesProhibidesEntityFromParaulaProhibida(paraulaProhibida);
        this.paraulaProhibidaToEntity(paraulaProhibida, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulesProhibidesEntityDao#paraulaProhibidaToEntity(es.caib.seycon.ng.comu.ParaulaProhibida, es.caib.seycon.ng.model.ParaulesProhibidesEntity)
     */
    public void paraulaProhibidaToEntity(es.caib.seycon.ng.comu.ParaulaProhibida source, com.soffid.iam.model.ForbiddenWordEntity target, boolean copyIfNull) {
        // @todo verify behavior of paraulaProhibidaToEntity
        super.paraulaProhibidaToEntity(source, target, copyIfNull);
    }

}