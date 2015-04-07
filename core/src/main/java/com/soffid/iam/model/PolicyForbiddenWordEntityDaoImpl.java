// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.ForbiddenWordEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import es.caib.seycon.ng.comu.ParaulaProhibida;
import es.caib.seycon.ng.comu.PoliticaContrasenya;
import java.util.Collection;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity
 */
public class PolicyForbiddenWordEntityDaoImpl
    extends com.soffid.iam.model.PolicyForbiddenWordEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#toParaulaProhibidaPoliticaContrasenya(es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity, es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya)
     */
    public void toParaulaProhibidaPoliticaContrasenya(com.soffid.iam.model.PolicyForbiddenWordEntity source, es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya target) {
        // @todo verify behavior of toParaulaProhibidaPoliticaContrasenya
        super.toParaulaProhibidaPoliticaContrasenya(source, target);
        // WARNING! No conversion for target.paraulaProhibida (can't convert source.getParaulaProhibida():es.caib.seycon.ng.model.ParaulesProhibidesEntity to java.lang.String
        
        // Fem els canvis necessaris
        if (source.getForbiddenWord() != null) {
        	ParaulaProhibida p = getForbiddenWordEntityDao().toParaulaProhibida(source.getForbiddenWord());
        	target.setParaulaProhibida(p);
        }
        
        if (source.getPasswordPolicy() != null) {
        	PoliticaContrasenya pc = getPasswordPolicyEntityDao().toPoliticaContrasenya(source.getPasswordPolicy());
        	target.setPoliticaContrasenyaDomini(pc);
        }
        
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#toParaulaProhibidaPoliticaContrasenya(es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity)
     */
    public es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya toParaulaProhibidaPoliticaContrasenya(final com.soffid.iam.model.PolicyForbiddenWordEntity entity) {
        // @todo verify behavior of toParaulaProhibidaPoliticaContrasenya
        return super.toParaulaProhibidaPoliticaContrasenya(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.PolicyForbiddenWordEntity loadParaulaProhibidaPoliticaContrasenyaEntityFromParaulaProhibidaPoliticaContrasenya(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenyaDomini) {
        com.soffid.iam.model.PolicyForbiddenWordEntity paraulaProhibidaContrasenyaDominiEntity = null;
        if (paraulaProhibidaContrasenyaDomini.getId() !=null) {
        	paraulaProhibidaContrasenyaDominiEntity = this.load(paraulaProhibidaContrasenyaDomini.getId());
        }
        if (paraulaProhibidaContrasenyaDominiEntity == null)
        {
            paraulaProhibidaContrasenyaDominiEntity = newPolicyForbiddenWordEntity();
        }
        return paraulaProhibidaContrasenyaDominiEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#paraulaProhibidaContrasenyaDominiToEntity(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya)
     */
    public com.soffid.iam.model.PolicyForbiddenWordEntity paraulaProhibidaPoliticaContrasenyaToEntity(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya paraulaProhibidaContrasenyaDomini) {
        // @todo verify behavior of paraulaProhibidaContrasenyaDominiToEntity
        com.soffid.iam.model.PolicyForbiddenWordEntity entity = this.loadParaulaProhibidaPoliticaContrasenyaEntityFromParaulaProhibidaPoliticaContrasenya(paraulaProhibidaContrasenyaDomini);
        this.paraulaProhibidaPoliticaContrasenyaToEntity(paraulaProhibidaContrasenyaDomini, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#paraulaProhibidaContrasenyaDominiToEntity(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya, es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity)
     */
    public void paraulaProhibidaPoliticaContrasenyaToEntity(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya source, com.soffid.iam.model.PolicyForbiddenWordEntity target, boolean copyIfNull) {
        // @todo verify behavior of paraulaProhibidaContrasenyaDominiToEntity
        super.paraulaProhibidaPoliticaContrasenyaToEntity(source, target, copyIfNull);
        
        // Fem els canvis necessaris
        //TODO: revisar
        if (source.getParaulaProhibida()!=null) {
        	ForbiddenWordEntity p = getForbiddenWordEntityDao().load(source.getParaulaProhibida().getId());
        	if (p!=null) target.setForbiddenWord(p);
        }
        
        if (source.getPoliticaContrasenyaDomini() !=null) {
        	PoliticaContrasenya pcd = source.getPoliticaContrasenyaDomini();
        	PasswordPolicyEntity pc = null;
        	if (pcd.getId()!=null) {
        		pc = getPasswordPolicyEntityDao().load(source.getPoliticaContrasenyaDomini().getId());
        	} else {
        		Collection<PasswordPolicyEntity> pcdc = getPasswordPolicyEntityDao().findByPasswordDomain(pcd.getCodiDominiContrasenya());
        		boolean trobat = false;
        		if (pcdc!=null) for (Iterator<PasswordPolicyEntity> it = pcdc.iterator(); !trobat && it.hasNext(); ) {
                    PasswordPolicyEntity p = it.next();
                    if (p.getUserDomainType().getCode().equals(pcd.getTipusUsuari())) {
                        trobat = true;
                        pc = p;
                    }
                }
        		
        	}
        	if (pc!=null) target.setPasswordPolicy(pc);
        }
        
    }

}