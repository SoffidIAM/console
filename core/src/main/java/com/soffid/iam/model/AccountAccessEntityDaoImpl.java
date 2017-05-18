//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.Audit;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.Hibernate;

/**
 * DAO UserAccountAccessEntity implementation
 */
public class AccountAccessEntityDaoImpl extends com.soffid.iam.model.AccountAccessEntityDaoBase
{
	private void auditar(String accio, com.soffid.iam.model.AccountAccessEntity entity) {

		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setAccount(entity.getAccount().getName());
		auditoria.setDatabase(entity.getAccount().getSystem().getName());
		auditoria.setAuthor(codiUsuari);
		if (entity.getGroup() != null)
			auditoria.setGroup(entity.getGroup().getName());
		if (entity.getRole() != null)
			auditoria.setRole(entity.getRole().getName());
		if (entity.getUser() != null)
			auditoria.setUser(entity.getUser().getUserName());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setCalendar(Calendar.getInstance());
		auditoria.setObject("SC_ACCACC"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
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
			getSession().flush();
		}
		else
			super.remove(entity);
		auditar("D", entity); //$NON-NLS-1$
	}
}
