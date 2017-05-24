//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.hibernate.Hibernate;

import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

/**
 * DAO UserAccountAccessEntity implementation
 */
public class AccountAccessEntityDaoImpl extends AccountAccessEntityDaoBase
{
	private void auditar (String accio, AccountAccessEntity entity)
	{

		String codiUsuari = Security.getCurrentAccount();
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setAccount(entity.getAccount().getName());
		auditoria.setBbdd(entity.getAccount().getDispatcher().getCodi());
		auditoria.setAutor(codiUsuari);
		if (entity.getGroup() != null)
			auditoria.setGrup(entity.getGroup().getCodi());
		if (entity.getRol() != null)
			auditoria.setRol(entity.getRol().getNom());
		if (entity.getUser() != null)
			auditoria.setUsuari(entity.getUser().getCodi());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObjecte("SC_ACCACC"); //$NON-NLS-1$
		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(
						auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}

	@Override
	public void create (AccountAccessEntity entity)
	{
		super.create(entity);
		auditar("C", entity); //$NON-NLS-1$
	}

	@Override
	public void update (AccountAccessEntity entity)
	{
		super.update(entity);
		auditar("U", entity); //$NON-NLS-1$
	}

	@Override
	public void remove (AccountAccessEntity entity)
	{
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
