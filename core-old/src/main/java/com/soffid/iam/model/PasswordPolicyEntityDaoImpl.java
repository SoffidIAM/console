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

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.UserTypeEntity;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.utils.Security;
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
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setPasswordDomain(politica.getPasswordDomain().getName());
        auditoria.setUserType(politica.getUserType().getName());
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_POCODO"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
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
    public void toPoliticaContrasenya(com.soffid.iam.model.PasswordPolicyEntity source, es.caib.seycon.ng.comu.PoliticaContrasenya target) {
        // @todo verify behavior of toPoliticaContrasenya
        super.toPoliticaContrasenya(source, target);
        target.setAllowPasswordChange(source.getAllowPasswordChange() == null ? true: source.getAllowPasswordChange().booleanValue());
        target.setAllowPasswordQuery(source.getAllowPasswordQuery() == null ? true: source.getAllowPasswordQuery().booleanValue());
        // Fem les conversions 
        if (source.getUserType() != null) {
        	UserTypeEntity tipusUsuariE = source.getUserType();
        	target.setTipusUsuari(tipusUsuariE.getName());
        	target.setDecripcioTipusUsuari(tipusUsuariE.getDescription());
        }
        if (source.getPasswordDomain() != null)
        {
        	target.setCodiDominiContrasenya(source.getPasswordDomain().getName());
        }
    }


    /**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#toPoliticaContrasenya(es.caib.seycon.ng.model.PoliticaContrasenyaEntity)
     */
    public es.caib.seycon.ng.comu.PoliticaContrasenya toPoliticaContrasenya(final com.soffid.iam.model.PasswordPolicyEntity entity) {
        // @todo verify behavior of toPoliticaContrasenya
        return super.toPoliticaContrasenya(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.PasswordPolicyEntity loadPoliticaContrasenyaEntityFromPoliticaContrasenya(es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenyaDomini) {
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
    public com.soffid.iam.model.PasswordPolicyEntity politicaContrasenyaToEntity(es.caib.seycon.ng.comu.PoliticaContrasenya politicaContrasenyaDomini) {
        // @todo verify behavior of politicaContrasenyaDominiToEntity
        com.soffid.iam.model.PasswordPolicyEntity entity = this.loadPoliticaContrasenyaEntityFromPoliticaContrasenya(politicaContrasenyaDomini);
        this.politicaContrasenyaToEntity(politicaContrasenyaDomini, entity, true);
        return entity;
    }


    /**
     * @see es.caib.seycon.ng.model.PoliticaContrasenyaEntityDao#politicaContrasenyaToEntity(es.caib.seycon.ng.comu.PoliticaContrasenya, es.caib.seycon.ng.model.PoliticaContrasenyaEntity)
     */
    public void politicaContrasenyaToEntity(es.caib.seycon.ng.comu.PoliticaContrasenya source, com.soffid.iam.model.PasswordPolicyEntity target, boolean copyIfNull) {
        // @todo verify behavior of politicaContrasenyaToEntity
        super.politicaContrasenyaToEntity(source, target, copyIfNull);
        
        target.setAllowPasswordChange(new Boolean(source.isAllowPasswordChange()));
        target.setAllowPasswordQuery(new Boolean(source.isAllowPasswordQuery()));
        // Fem els canvis necessaris
        // Tipus d'usuari
        if (source.getTipusUsuari() !=null) {
        	String codiTipusUsuari = source.getTipusUsuari();
        	UserTypeEntity tipusUsuE = getUserTypeEntityDao().findByName(codiTipusUsuari);
        	if (tipusUsuE!=null) {
        		target.setUserType(tipusUsuE);
        	} 
        }
        
        // Domini de contrasenya i d'usuaris
        if (source.getCodiDominiContrasenya()!=null) {
        	String codiDominiContrasenya = source.getCodiDominiContrasenya();
        	PasswordDomainEntity dominiContraE = getPasswordDomainEntityDao().findByName(codiDominiContrasenya);
       		target.setPasswordDomain(dominiContraE);
        }
        
        // Segons el tipus de contrasenya anulem el valor de certs atributs
        // (A)utomàtica i (M)anual
        if ("A".equals(source.getTipus())) { //$NON-NLS-1$
        	target.setAvailableTime(null);
        	target.setGracePeriodTime(null);
        } else if ("M".equals(source.getTipus())) { //$NON-NLS-1$
        	target.setRenewalTime(null);
        }
        
        
        
    }

}