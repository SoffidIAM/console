package com.soffid.iam.model;

import com.soffid.iam.api.PasswordStatus;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.UserTypeEntity;
import es.caib.seycon.ng.model.*;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class AccountPasswordEntityDaoImpl extends com.soffid.iam.model.AccountPasswordEntityDaoBase
{

	public void toPasswordStatus(com.soffid.iam.model.AccountPasswordEntity source, com.soffid.iam.api.PasswordStatus target) {
		target.setExpirationDate(new GregorianCalendar());
		target.getExpirationDate().setTime(source.getExpirationDate());
		target.setDate(new GregorianCalendar());
		target.getDate().setTime(source.getDate());
		// NOTA: aquí usuari es de tipus CodiUsuariEntity
		target.setAccountName(source.getAccount().getName());
		target.setDispatcher(source.getAccount().getSystem().getName());
		// Indicador de si és caducada
		target.setExpired(new Boolean(source.getExpirationDate().before(GregorianCalendar.getInstance().getTime())));
		// Obtenim la política de contrasenyes
		// obtenim el tipus d'usuari
		UserTypeEntity tipusUsuari = source.getAccount().getPasswordPolicy();
		Iterator<PasswordPolicyEntity> it;
		boolean trobat = false;
		PasswordDomainEntity dominiContrasenyes = source.getAccount().getSystem().getPasswordDomain();
		// cerquem la política de contrasenyes per al tipus d'usuari
		if (dominiContrasenyes.getPasswordPolicies() != null)
		{
			for (it = dominiContrasenyes.getPasswordPolicies().iterator(); !trobat && it.hasNext(); ) {
                PasswordPolicyEntity politica = it.next();
                if (tipusUsuari.getName().equals(politica.getUserType().getName())) {
                    target.setPasswordPolicyType(politica.getType());
                    trobat = true;
                }
            }
		}
	}

	public com.soffid.iam.model.AccountPasswordEntity passwordStatusToEntity(PasswordStatus estatContrasenya) {
		throw new UnsupportedOperationException();
	}

}
