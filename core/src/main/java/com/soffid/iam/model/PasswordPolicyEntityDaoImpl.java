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

import com.soffid.iam.api.Audit;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.model.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntity
 */
public class PasswordPolicyEntityDaoImpl
    extends com.soffid.iam.model.PasswordPolicyEntityDaoBase
{

    private void audit(String accio, PasswordPolicyEntity politica) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setPasswordDomain(politica.getPasswordDomain().getName());
        auditoria.setUserType(politica.getUserType().getName());
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_POCODO"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }


    @Override
    public void create(PasswordPolicyEntity entity) {
		super.create(entity);
		audit("C", entity); //$NON-NLS-1$
	}


	@Override
    public void update(PasswordPolicyEntity entity) {
		super.update(entity);
		audit("U", entity); //$NON-NLS-1$
	}


	@Override
    public void remove(PasswordPolicyEntity entity) {
		audit("D", entity); //$NON-NLS-1$
		super.remove(entity);
	}


	/**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#toPoliticaContrasenya(es.caib.seycon.ng.model.PoliticaContrasenyaEntity, es.caib.seycon.ng.comu.PoliticaContrasenya)
     */
    public void toPasswordPolicy(com.soffid.iam.model.PasswordPolicyEntity source, com.soffid.iam.api.PasswordPolicy target) {
        // @todo verify behavior of toPoliticaContrasenya
        super.toPasswordPolicy(source, target);
        target.setAllowPasswordChange(source.getAllowPasswordChange() == null ? true: source.getAllowPasswordChange().booleanValue());
        target.setAllowPasswordQuery(source.getAllowPasswordQuery() == null ? true: source.getAllowPasswordQuery().booleanValue());
        // Fem les conversions 
        if (source.getUserType() != null) {
        	UserTypeEntity tipusUsuariE = source.getUserType();
        	target.setUserType(tipusUsuariE.getName());
        	target.setUserTypeDescription(tipusUsuariE.getDescription());
        }
        if (source.getPasswordDomain() != null)
        {
        	target.setPasswordDomainCode(source.getPasswordDomain().getName());
        }
    }


    /**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#toPoliticaContrasenya(es.caib.seycon.ng.model.PoliticaContrasenyaEntity)
     */
    public com.soffid.iam.api.PasswordPolicy toPasswordPolicy(final com.soffid.iam.model.PasswordPolicyEntity entity) {
        // @todo verify behavior of toPoliticaContrasenya
        return super.toPasswordPolicy(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.PasswordPolicyEntity loadPoliticaContrasenyaEntityFromPoliticaContrasenya(com.soffid.iam.api.PasswordPolicy politicaContrasenyaDomini) {
        com.soffid.iam.model.PasswordPolicyEntity politicaContrasenyaDominiEntity = null;
        if (politicaContrasenyaDomini.getId()!=null) {
        	politicaContrasenyaDominiEntity = this.load(politicaContrasenyaDomini.getId());
        }
        if (politicaContrasenyaDominiEntity == null)
        {
            politicaContrasenyaDominiEntity = newPasswordPolicyEntity();
        }
        return politicaContrasenyaDominiEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#politicaContrasenyaDominiToEntity(es.caib.seycon.ng.comu.PoliticaContrasenya)
     */
    public com.soffid.iam.model.PasswordPolicyEntity passwordPolicyToEntity(com.soffid.iam.api.PasswordPolicy politicaContrasenyaDomini) {
        // @todo verify behavior of politicaContrasenyaDominiToEntity
        com.soffid.iam.model.PasswordPolicyEntity entity = this.loadPoliticaContrasenyaEntityFromPoliticaContrasenya(politicaContrasenyaDomini);
        this.passwordPolicyToEntity(politicaContrasenyaDomini, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#politicaContrasenyaToEntity(es.caib.seycon.ng.comu.PoliticaContrasenya, es.caib.seycon.ng.model.PoliticaContrasenyaEntity)
     */
    public void passwordPolicyToEntity(com.soffid.iam.api.PasswordPolicy source, com.soffid.iam.model.PasswordPolicyEntity target, boolean copyIfNull) {
        // @todo verify behavior of politicaContrasenyaToEntity
        super.passwordPolicyToEntity(source, target, copyIfNull);
        
        target.setAllowPasswordChange(new Boolean(source.isAllowPasswordChange()));
        target.setAllowPasswordQuery(new Boolean(source.isAllowPasswordQuery()));
        // Fem els canvis necessaris
        // Tipus d'usuari
        if (source.getUserType() != null) {
        	String codiTipusUsuari = source.getUserType();
        	UserTypeEntity tipusUsuE = getUserTypeEntityDao().findByName(codiTipusUsuari);
        	if (tipusUsuE!=null) {
        		target.setUserType(tipusUsuE);
        	} 
        }
        
        // Domini de contrasenya i d'usuaris
        if (source.getPasswordDomainCode() != null) {
        	String codiDominiContrasenya = source.getPasswordDomainCode();
        	PasswordDomainEntity dominiContraE = getPasswordDomainEntityDao().findByName(codiDominiContrasenya);
       		target.setPasswordDomain(dominiContraE);
        }
        
        // Segons el tipus de contrasenya anulem el valor de certs atributs
        // (A)utomàtica i (M)anual
        if ("A".equals(source.getType())) { //$NON-NLS-1$
        	target.setAvailableTime(null);
        	target.setGracePeriodTime(null);
        } else if ("M".equals(source.getType())) { //$NON-NLS-1$
        	target.setRenewalTime(null);
        }
        
        
        
    }

}