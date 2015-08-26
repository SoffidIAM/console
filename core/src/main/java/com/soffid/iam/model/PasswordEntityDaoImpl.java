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

import com.soffid.iam.api.PasswordStatus;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.PasswordEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.UserTypeEntity;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;

/**
 * @see es.caib.seycon.ng.model.ContrasenyaEntity
 */
public class PasswordEntityDaoImpl extends
        com.soffid.iam.model.PasswordEntityDaoBase {

    @Override
    public void update(PasswordEntity contrasenyaEntity) {
        try {
            super.update(contrasenyaEntity);
            getSession().flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
                        throw new SeyconException(String.format(Messages.getString("PasswordEntityDaoImpl.0"), contrasenyaEntity.getPassword(), message));
        }
    }

    public void create(com.soffid.iam.model.PasswordEntity contrasenya) throws RuntimeException {
        try {
            super.create(contrasenya);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PasswordEntityDaoImpl.1"), contrasenya.getPassword(), message));
        }
    }

    public void remove(com.soffid.iam.model.PasswordEntity contrasenya) throws RuntimeException {
        try {
            super.remove(contrasenya);
            getSession(false).flush();
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PasswordEntityDaoImpl.2"), contrasenya.getPassword(), message));
        }
    }

	public void toPasswordStatus(com.soffid.iam.model.PasswordEntity source, com.soffid.iam.api.PasswordStatus target) {
            target.setExpirationDate(new GregorianCalendar());
            target.getExpirationDate().setTime(source.getExpirationDate());
            target.setDate(new GregorianCalendar());
            target.getDate().setTime(source.getDate());
		// NOTA: aquí usuari es de tipus CodiUsuariEntity
		target.setUser(source.getUser().getUserName());
		PasswordDomainEntity dominiContrasenyes = source.getDomain();
		target.setPasswordDomain(dominiContrasenyes.getName());
		// Indicador de si és caducada
		target.setExpired(new Boolean(source.getExpirationDate().before(GregorianCalendar.getInstance().getTime())));
		// Obtenim la política de contrasenyes
		// obtenim el tipus d'usuari
		UserTypeEntity tipusUsuari = source.getUser().getUserType();
		Iterator<PasswordPolicyEntity> it;
		boolean trobat = false;
		// cerquem la política de contrasenyes per al tipus d'usuari 
		if (dominiContrasenyes.getPasswordPolicies() != null) {
			for (it = dominiContrasenyes.getPasswordPolicies().iterator(); !trobat && it.hasNext(); ) {
                PasswordPolicyEntity politica = it.next();
                if (tipusUsuari.getName().equals(politica.getUserType().getName())) {
                    target.setPasswordPolicyType(politica.getType());
                    trobat = true;
                }
            }
		}
        }

    public PasswordEntity passwordStatusToEntity(PasswordStatus estatContrasenya) {
        throw new UnsupportedOperationException();
    }

}
