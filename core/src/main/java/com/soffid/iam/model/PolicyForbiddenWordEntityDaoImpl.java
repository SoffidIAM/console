// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.ForbiddenWord;
import com.soffid.iam.api.PasswordPolicy;
import com.soffid.iam.model.ForbiddenWordEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;

import es.caib.seycon.ng.model.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity
 */
public class PolicyForbiddenWordEntityDaoImpl
    extends com.soffid.iam.model.PolicyForbiddenWordEntityDaoBase
{
    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#toParaulaProhibidaPoliticaContrasenya(es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity, es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya)
     */
    public void toPasswordPolicyForbbidenWord(com.soffid.iam.model.PolicyForbiddenWordEntity source, com.soffid.iam.api.PasswordPolicyForbbidenWord target) {
        // @todo verify behavior of toParaulaProhibidaPoliticaContrasenya
        super.toPasswordPolicyForbbidenWord(source, target);
        // WARNING! No conversion for target.paraulaProhibida (can't convert source.getParaulaProhibida():es.caib.seycon.ng.model.ParaulesProhibidesEntity to java.lang.String
        
        // Fem els canvis necessaris
        if (source.getForbiddenWord() != null) {
        	ForbiddenWord p = getForbiddenWordEntityDao().toForbiddenWord(source.getForbiddenWord());
        	target.setForbiddenWord(p);
        }
        
        if (source.getPasswordPolicy() != null) {
        	PasswordPolicy pc = getPasswordPolicyEntityDao().toPasswordPolicy(source.getPasswordPolicy());
        	target.setPasswordDomainPolicy(pc);
        }
        
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#toParaulaProhibidaPoliticaContrasenya(es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity)
     */
    public com.soffid.iam.api.PasswordPolicyForbbidenWord toPasswordPolicyForbbidenWord(final com.soffid.iam.model.PolicyForbiddenWordEntity entity) {
        // @todo verify behavior of toParaulaProhibidaPoliticaContrasenya
        return super.toPasswordPolicyForbbidenWord(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.PolicyForbiddenWordEntity loadParaulaProhibidaPoliticaContrasenyaEntityFromParaulaProhibidaPoliticaContrasenya(com.soffid.iam.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenyaDomini) {
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
    public com.soffid.iam.model.PolicyForbiddenWordEntity passwordPolicyForbbidenWordToEntity(com.soffid.iam.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenyaDomini) {
        // @todo verify behavior of paraulaProhibidaContrasenyaDominiToEntity
        com.soffid.iam.model.PolicyForbiddenWordEntity entity = this.loadParaulaProhibidaPoliticaContrasenyaEntityFromParaulaProhibidaPoliticaContrasenya(paraulaProhibidaContrasenyaDomini);
        this.passwordPolicyForbbidenWordToEntity(paraulaProhibidaContrasenyaDomini, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntityDao#paraulaProhibidaContrasenyaDominiToEntity(es.caib.seycon.ng.comu.ParaulaProhibidaPoliticaContrasenya, es.caib.seycon.ng.model.ParaulaProhibidaPoliticaContrasenyaEntity)
     */
    public void passwordPolicyForbbidenWordToEntity(com.soffid.iam.api.PasswordPolicyForbbidenWord source, com.soffid.iam.model.PolicyForbiddenWordEntity target, boolean copyIfNull) {
        // @todo verify behavior of paraulaProhibidaContrasenyaDominiToEntity
        super.passwordPolicyForbbidenWordToEntity(source, target, copyIfNull);
        
        // Fem els canvis necessaris
        //TODO: revisar
        if (source.getForbiddenWord() != null) {
        	ForbiddenWordEntity p = getForbiddenWordEntityDao().load(source.getForbiddenWord().getId());
        	if (p!=null) target.setForbiddenWord(p);
        }
        
        if (source.getPasswordDomainPolicy() != null) {
        	PasswordPolicy pcd = source.getPasswordDomainPolicy();
        	PasswordPolicyEntity pc = null;
        	if (pcd.getId()!=null) {
        		pc = getPasswordPolicyEntityDao().load(source.getPasswordDomainPolicy().getId());
        	} else {
        		Collection<PasswordPolicyEntity> pcdc = getPasswordPolicyEntityDao().findByPasswordDomain(pcd.getPasswordDomainCode());
        		boolean trobat = false;
        		if (pcdc!=null) for (Iterator<PasswordPolicyEntity> it = pcdc.iterator(); !trobat && it.hasNext(); ) {
                    PasswordPolicyEntity p = it.next();
                    if (p.getUserType().getName().equals(pcd.getUserType())) {
                        trobat = true;
                        pc = p;
                    }
                }
        		
        	}
        	if (pc!=null) target.setPasswordPolicy(pc);
        }
        
    }


}