package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.PasswordPolicyEntity;
import com.soffid.iam.model.UserTypeEntity;
import es.caib.seycon.ng.comu.EstatContrasenya;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class AccountPasswordEntityDaoImpl extends com.soffid.iam.model.AccountPasswordEntityDaoBase
{

	public void toEstatContrasenya(com.soffid.iam.model.AccountPasswordEntity source, es.caib.seycon.ng.comu.EstatContrasenya target) {
		target.setCaducitat(new GregorianCalendar());
		target.getCaducitat().setTime(source.getExpirationDate());
		target.setData(new GregorianCalendar());
		target.getData().setTime(source.getDate());
		// NOTA: aquí usuari es de tipus CodiUsuariEntity
		target.setAccountName(source.getAccount().getName());
		target.setDispatcher(source.getAccount().getSystem().getCode());
		// Indicador de si és caducada
		target.setCaducada(new Boolean(source.getExpirationDate().before(
				GregorianCalendar.getInstance().getTime())));
		// Obtenim la política de contrasenyes
		// obtenim el tipus d'usuari
		UserTypeEntity tipusUsuari = source.getAccount().getPasswordPolicy();
		Iterator<PasswordPolicyEntity> it;
		boolean trobat = false;
		PasswordDomainEntity dominiContrasenyes = source.getAccount().getSystem().getDomain();
		// cerquem la política de contrasenyes per al tipus d'usuari
		if (dominiContrasenyes.getPasswordPolicies() != null)
		{
			for (it = dominiContrasenyes.getPasswordPolicies().iterator(); !trobat && it.hasNext(); ) {
                PasswordPolicyEntity politica = it.next();
                if (tipusUsuari.getCode().equals(politica.getUserDomainType().getCode())) {
                    target.setTipusPoliticaContrasenya(politica.getType());
                    trobat = true;
                }
            }
		}
	}

	public com.soffid.iam.model.AccountPasswordEntity estatContrasenyaToEntity(EstatContrasenya estatContrasenya) {
		throw new UnsupportedOperationException();
	}

}
