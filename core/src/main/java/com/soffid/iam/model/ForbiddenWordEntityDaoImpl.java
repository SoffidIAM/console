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
    public void toForbiddenWord(com.soffid.iam.model.ForbiddenWordEntity source, com.soffid.iam.api.ForbiddenWord target) {
        // @todo verify behavior of toParaulaProhibida
        super.toForbiddenWord(source, target);
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulesProhibidesEntityDao#toParaulaProhibida(es.caib.seycon.ng.model.ParaulesProhibidesEntity)
     */
    public com.soffid.iam.api.ForbiddenWord toForbiddenWord(final com.soffid.iam.model.ForbiddenWordEntity entity) {
        // @todo verify behavior of toParaulaProhibida
        return super.toForbiddenWord(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.ForbiddenWordEntity loadParaulesProhibidesEntityFromParaulaProhibida(com.soffid.iam.api.ForbiddenWord paraulaProhibida) {
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
    public com.soffid.iam.model.ForbiddenWordEntity forbiddenWordToEntity(com.soffid.iam.api.ForbiddenWord paraulaProhibida) {
        // @todo verify behavior of paraulaProhibidaToEntity
        com.soffid.iam.model.ForbiddenWordEntity entity = this.loadParaulesProhibidesEntityFromParaulaProhibida(paraulaProhibida);
        this.forbiddenWordToEntity(paraulaProhibida, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulesProhibidesEntityDao#paraulaProhibidaToEntity(es.caib.seycon.ng.comu.ParaulaProhibida, es.caib.seycon.ng.model.ParaulesProhibidesEntity)
     */
    public void forbiddenWordToEntity(com.soffid.iam.api.ForbiddenWord source, com.soffid.iam.model.ForbiddenWordEntity target, boolean copyIfNull) {
        // @todo verify behavior of paraulaProhibidaToEntity
        super.forbiddenWordToEntity(source, target, copyIfNull);
    }

}