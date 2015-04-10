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
import com.soffid.iam.model.GroupEntity;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.lang.MessageFactory;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.servei.PasswordService;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.MissingResourceException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @see es.caib.seycon.ng.model.AuditoriaEntity
 */
public class AuditEntityDaoImpl extends
		com.soffid.iam.model.AuditEntityDaoBase {
	private static final String BUNDLE_NAME = "com.soffid.iam.model.audit.messages"; //$NON-NLS-1$
	private static final String BUNDLE_NAME2 = "es.caib.seycon.ng.model.audit.messages"; //$NON-NLS-1$


	@Override
    public AuditEntity newAuditEntity() {
		AuditEntity au = super.newAuditEntity();
		au.setDate(new Date());
		return au;
	}

	public java.lang.String[] find(java.lang.String sqlQuery) {
		throw new UnsupportedOperationException();
	}

	public void create(com.soffid.iam.model.AuditEntity auditoria) throws RuntimeException {
		try {
			super.create(auditoria);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AuditEntityDaoImpl.1"),   //$NON-NLS-1$
					auditoria.getId(),
					message));
		}
	}

	public void remove(com.soffid.iam.model.AuditEntity auditoria) throws RuntimeException {
		try {
			super.remove(auditoria);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AuditEntityDaoImpl.2"),   //$NON-NLS-1$
					auditoria.getId(),
					message));
		}
	}

	public void toAuditoria(com.soffid.iam.model.AuditEntity sourceEntity, es.caib.seycon.ng.comu.Auditoria targetVO) {
		super.toAuditoria(sourceEntity, targetVO);
		toAuditoriaCustom(sourceEntity, targetVO);
	}

	public void toAuditoriaCustom(com.soffid.iam.model.AuditEntity sourceEntity, es.caib.seycon.ng.comu.Auditoria targetVO) {
		targetVO.setAccount(sourceEntity.getAccount());
		Date data = sourceEntity.getDate();
		if (data != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
			targetVO.setData(dateFormat.format(data));
		}
		targetVO.setCalendar (Calendar.getInstance());
		targetVO.getCalendar().setTime (data);

		GroupEntity grup = sourceEntity.getGroup();
		if (grup != null) {
			targetVO.setGrup(grup.getName());
		}

		com.soffid.iam.model.AccountEntity usuari = sourceEntity.getAccountAssoc();
		if (usuari != null) {
			targetVO.setAutor(usuari.getName());
			/* afegim nom complet de l'autor i el seu grup primari */
			targetVO.setAutorNomComplet(usuari.getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
			if (usuari.getType().equals (AccountType.USER))
			{
				for (com.soffid.iam.model.UserAccountEntity ua : usuari.getUsers()) {
                    targetVO.setAutorGrupPrimari(ua.getUser().getPrimaryGroup().getName());
                }
			}
		}
		
		targetVO.setValorDomini(sourceEntity.getDomainValue());
		
		if (sourceEntity.getFileId() != null) {
			// Atenció: els fitxers es poden esborrar... per això no n'hi ha una
			// relació amb els fitxers.. la que havia era amb el seu ID
			try {
//				FitxerEntity f = getFitxerEntityDao().findById(sourceEntity.getFitxerId());
//				targetVO.setNomFitxer(f.getNom());
			} catch (Throwable th) {
				//Marquem el seu id
				targetVO.setNomFitxer(sourceEntity.getFileId() + " (id)"); //$NON-NLS-1$
			}
			targetVO.setFitxer(sourceEntity.getFileId());
		}
		
		StringBuffer key = new StringBuffer(50);
		key.append(targetVO.getObjecte()).append('/').append(targetVO.getAccio());
		try
		{
			String msg;
			try {
				msg = MessageFactory.getString(BUNDLE_NAME, key.toString());
			} 
			catch (MissingResourceException e) 
			{
				try {
					msg = MessageFactory.getString(BUNDLE_NAME2, key.toString());
				} 
				catch (MissingResourceException e2) 
				{
					try {
						msg = MessageFactory.getString(BUNDLE_NAME+"_"+targetVO.getObjecte(), targetVO.getAccio()); //$NON-NLS-1$
					} 
					catch (MissingResourceException e3) 
					{
						msg = MessageFactory.getString(BUNDLE_NAME2+"_"+targetVO.getObjecte(), targetVO.getAccio()); //$NON-NLS-1$
					}
				}
			}
			StringBuffer result = new StringBuffer();
			int processed = 0;
			do
			{
				int i = msg.indexOf("${", processed); //$NON-NLS-1$

				if ( i < 0) 
					break;
				int j = msg.indexOf("}", i); //$NON-NLS-1$
				if ( j < 0)
					break;
				String variable = msg.substring(i+2, j);
				result.append(msg.substring(processed, i));
				processed = j + 1;
				try {
					Object property = BeanUtils.getProperty(targetVO, variable);
					if (property != null)
						result.append(property.toString());
				} catch (Exception e) { 
					logger.debug(String.format(Messages.getString("AuditEntityDaoImpl.UnknownVariable"), variable, key)); //$NON-NLS-1$
					result.append ("${").append (variable).append("}"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			} while (true);
			result.append(msg.substring(processed));
			targetVO.setMessage(result.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			targetVO.setMessage(String.format(Messages.getString("AuditEntityDaoImpl.Action"), key)); //$NON-NLS-1$
		}

	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.AuditEntity loadAuditoriaEntityFromAuditoria(es.caib.seycon.ng.comu.Auditoria auditoria) {
		if (auditoria.getId() == null) {
			return newAuditEntity();
		} else {
			throw new SeyconException(Messages.getString("AuditEntityDaoImpl.4")); //$NON-NLS-1$
		}
	}

	public com.soffid.iam.model.AuditEntity auditoriaToEntity(es.caib.seycon.ng.comu.Auditoria auditoria) {
		com.soffid.iam.model.AuditEntity entity = this.loadAuditoriaEntityFromAuditoria(auditoria);
		this.auditoriaToEntity(auditoria, entity, true);
		return entity;
	}

	public void auditoriaToEntityCustom(es.caib.seycon.ng.comu.Auditoria sourceVO, com.soffid.iam.model.AuditEntity targetEntity) {
		if (sourceVO.getCalendar() != null)
		{
			targetEntity.setDate(sourceVO.getCalendar().getTime());
		}
		else
		{
    		String dateString = sourceVO.getData();
    		try {
    			SimpleDateFormat dateFormat = new SimpleDateFormat(
    					"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
    			Date date = dateFormat.parse(dateString);
    			targetEntity.setDate(date);
    		} catch (Exception e) {
    			throw new SeyconException(String.format(Messages.getString("AuditEntityDaoImpl.5"), dateString)); //$NON-NLS-1$
    		}
		}

		targetEntity.setDomainValue(sourceVO.getValorDomini());
		
		String grup = sourceVO.getGrup();
		if (grup != null && grup.trim().length() > 0) {
			GroupEntity grupEntity = getGroupEntityDao().findByName(grup);
			targetEntity.setGroup(grupEntity);
		}

		String usuari = sourceVO.getAutor();
		if (usuari != null && usuari.trim().length() > 0) {
			PasswordService passwordService = getPasswordService ();
			try
			{
				String domain = passwordService.getDefaultDispatcher();
				com.soffid.iam.model.AccountEntity usuariEntity = getAccountEntityDao().findByNameAndSystem(usuari, domain);
				targetEntity.setAccountAssoc(usuariEntity);
			}
			catch (InternalErrorException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.AuditoriaEntityDao#auditoriaToEntity(es.caib.seycon.ng.comu.Auditoria,
	 *      es.caib.seycon.ng.model.AuditoriaEntity)
	 */
	public void auditoriaToEntity(es.caib.seycon.ng.comu.Auditoria sourceVO, com.soffid.iam.model.AuditEntity targetEntity, boolean copyIfNull) {
		super.auditoriaToEntity(sourceVO, targetEntity, copyIfNull);
		if (copyIfNull || sourceVO.getAccount() != null)
		{
			targetEntity.setAccount(sourceVO.getAccount());
		}
		auditoriaToEntityCustom(sourceVO, targetEntity);
	}

}