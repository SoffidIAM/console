//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.AuditEntity;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.hibernate.Hibernate;

/**
 * DAO UserAccountAccessEntity implementation
 */
public class AccountAccessEntityDaoImpl extends com.soffid.iam.model.AccountAccessEntityDaoBase
{
	private void auditar(String accio, com.soffid.iam.model.AccountAccessEntity entity) {

		String codiUsuari = Security.getCurrentAccount();
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setAccount(entity.getAccount().getName());
		auditoria.setBbdd(entity.getAccount().getSystem().getName());
		auditoria.setAutor(codiUsuari);
		if (entity.getGroup() != null)
			auditoria.setGrup(entity.getGroup().getName());
		if (entity.getRol() != null)
			auditoria.setRol(entity.getRol().getName());
		if (entity.getUser() != null)
			auditoria.setUsuari(entity.getUser().getUserName());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObjecte("SC_ACCACC"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	@Override
    public void create(com.soffid.iam.model.AccountAccessEntity entity) {
		super.create(entity);
		auditar("C", entity); //$NON-NLS-1$
	}

	@Override
    public void update(com.soffid.iam.model.AccountAccessEntity entity) {
		super.update(entity);
		auditar("U", entity); //$NON-NLS-1$
	}

	@Override
    public void remove(com.soffid.iam.model.AccountAccessEntity entity) {
		if (Hibernate.isInitialized(entity.getAccount()) &&
				Hibernate.isInitialized(entity.getAccount().getAcl()))
		{
			entity.getAccount().getAcl().remove(entity);
		}
		super.remove(entity);
		auditar("D", entity); //$NON-NLS-1$
	}
}
