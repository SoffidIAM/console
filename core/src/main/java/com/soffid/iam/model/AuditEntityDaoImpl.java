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
import com.soffid.iam.config.Config;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.service.PasswordService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.ExceptionTranslator;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.LogFactory;

/**
 * @see es.caib.seycon.ng.model.AuditoriaEntity
 */
public class AuditEntityDaoImpl extends
		com.soffid.iam.model.AuditEntityDaoBase {
	private static final String BUNDLE_NAME = "com.soffid.iam.model.audit.messages"; //$NON-NLS-1$
	private static final String BUNDLE_NAME2 = "es.caib.seycon.ng.model.audit.messages"; //$NON-NLS-1$

	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());

	@Override
    public AuditEntity newAuditEntity() {
		AuditEntity au = super.newAuditEntity();
		au.setDate(new Date());
		return au;
	}

	public void create(com.soffid.iam.model.AuditEntity auditoria) throws RuntimeException {
		try {
			if (auditoria.getAccount() != null && auditoria.getDb() != null)
			{
				UserEntity ue = getUserEntityDao().findByAccount(auditoria.getAccount(), auditoria.getDb());
				if (ue != null)
					auditoria.setUser(ue.getUserName());
			}
			super.create(auditoria);
			getSession(false).flush();
			
			String syslogServer = ConfigurationCache.getProperty ("soffid.syslog.server");
			if (syslogServer != null && syslogServer.trim().length() > 0)
			{
				try {
					Audit audobj = toAudit(auditoria);
	
					InetAddress addr = InetAddress.getByName(syslogServer);
					
					sendSysLog (addr, audobj);
				} catch (Throwable t) {
					log.warn("Unable to send syslog information", t);
				}
			}
			
			
			
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AuditEntityDaoImpl.1"),   //$NON-NLS-1$
					auditoria.getId(),
					message));
		}
	}

	private void sendSysLog(InetAddress syslogServer, Audit audobj) throws IOException {
        DatagramSocket s = new DatagramSocket();
        s.connect(syslogServer, 514);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(out, true, "UTF-8");
        p.print("<134>"); // PRI
        p.print("1"); // VERSION
        p.print(" "); // SP
        Calendar d = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        p.printf("%04d-%02d-%02dT%02d:%02d:%02d.%03dZ",
                d.get(Calendar.YEAR),
                d.get(Calendar.MONTH)+1,
                d.get(Calendar.DAY_OF_MONTH),
                d.get(Calendar.HOUR_OF_DAY),
                d.get(Calendar.MINUTE),
                d.get(Calendar.SECOND),
                d.get(Calendar.MILLISECOND));
        p.print(" "); // SP
        p.print(Config.getConfig().getHostName()); // HOSTNAME
        p.print(" "); // SP
        p.print("SOFFID"); // APPNAME
        p.print(" "); // SP
        p.print("-"); // PROCID
        p.print(" "); // SP
        p.print("SOFFID"+audobj.getId()); // MSGID
        p.print(" - "); // STRUCTURED DATA
        p.print("[" + audobj.getAuthor() + "] " + audobj.getMessage());// MSG     
        p.flush ();
        
        byte buf [] = out.toByteArray();
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        s.send (packet);
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

	public void toAudit(com.soffid.iam.model.AuditEntity sourceEntity, com.soffid.iam.api.Audit targetVO) {
		super.toAudit(sourceEntity, targetVO);
		toAuditoriaCustom(sourceEntity, targetVO);
	}

	public void toAuditoriaCustom(com.soffid.iam.model.AuditEntity sourceEntity, com.soffid.iam.api.Audit targetVO) {
		targetVO.setAccount(sourceEntity.getAccount());
		Date data = sourceEntity.getDate();
		if (data != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
			targetVO.setAdditionalInfo(dateFormat.format(data));
		}
		targetVO.setCalendar (Calendar.getInstance());
		targetVO.getCalendar().setTime (data);

		GroupEntity grup = sourceEntity.getGroup();
		if (grup != null) {
			targetVO.setGroup(grup.getName());
		}

		com.soffid.iam.model.AccountEntity usuari = sourceEntity.getAccountAssoc();
		if (usuari != null) {
			targetVO.setAuthor(usuari.getName());
			/* afegim nom complet de l'autor i el seu grup primari */
			targetVO.setAuthorFullName(usuari.getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
			if (usuari.getType().equals (AccountType.USER))
			{
				for (com.soffid.iam.model.UserAccountEntity ua : usuari.getUsers()) {
                    targetVO.setPrimaryGroupAuthor(ua.getUser().getPrimaryGroup().getName());
                }
			}
		}
		
		targetVO.setDomainValue(sourceEntity.getDomainValue());
		
		if (sourceEntity.getFileId() != null) {
			// Atenció: els fitxers es poden esborrar... per això no n'hi ha una
			// relació amb els fitxers.. la que havia era amb el seu ID
			try {
//				FitxerEntity f = getFitxerEntityDao().findById(sourceEntity.getFitxerId());
//				targetVO.setNomFitxer(f.getNom());
			} catch (Throwable th) {
				//Marquem el seu id
				targetVO.setFileName(sourceEntity.getFileId() + " (id)"); //$NON-NLS-1$
			}
			targetVO.setFile(sourceEntity.getFileId());
		}
		
		StringBuffer key = new StringBuffer(50);
		key.append(targetVO.getObject()).append('/').append(targetVO.getAction());
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
						msg = MessageFactory.getString(BUNDLE_NAME + "_" + targetVO.getObject(), targetVO.getAction()); //$NON-NLS-1$
					} 
					catch (MissingResourceException e3) 
					{
						msg = MessageFactory.getString(BUNDLE_NAME2 + "_" + targetVO.getObject(), targetVO.getAction()); //$NON-NLS-1$
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
	private com.soffid.iam.model.AuditEntity loadAuditoriaEntityFromAuditoria(com.soffid.iam.api.Audit auditoria) {
		if (auditoria.getId() == null) {
			return newAuditEntity();
		} else {
			throw new SeyconException(Messages.getString("AuditEntityDaoImpl.4")); //$NON-NLS-1$
		}
	}

	public com.soffid.iam.model.AuditEntity auditToEntity(com.soffid.iam.api.Audit auditoria) {
		com.soffid.iam.model.AuditEntity entity = this.loadAuditoriaEntityFromAuditoria(auditoria);
		this.auditToEntity(auditoria, entity, true);
		return entity;
	}

	public void auditoriaToEntityCustom(com.soffid.iam.api.Audit sourceVO, com.soffid.iam.model.AuditEntity targetEntity) {
		if (sourceVO.getCalendar() != null)
		{
			targetEntity.setDate(sourceVO.getCalendar().getTime());
		}
		else
		{
    		String dateString = sourceVO.getAdditionalInfo();
    		try {
    			SimpleDateFormat dateFormat = new SimpleDateFormat(
    					"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
    			Date date = dateFormat.parse(dateString);
    			targetEntity.setDate(date);
    		} catch (Exception e) {
    			throw new SeyconException(String.format(Messages.getString("AuditEntityDaoImpl.5"), dateString)); //$NON-NLS-1$
    		}
		}

		targetEntity.setDomainValue(sourceVO.getDomainValue());
		
		String grup = sourceVO.getGroup();
		if (grup != null && grup.trim().length() > 0) {
			GroupEntity grupEntity = getGroupEntityDao().findByName(grup);
			targetEntity.setGroup(grupEntity);
		}

		String usuari = sourceVO.getAuthor();
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
	public void auditToEntity(com.soffid.iam.api.Audit sourceVO, com.soffid.iam.model.AuditEntity targetEntity, boolean copyIfNull) {
		super.auditToEntity(sourceVO, targetEntity, copyIfNull);
		if (copyIfNull || sourceVO.getAccount() != null)
		{
			targetEntity.setAccount(sourceVO.getAccount());
		}
		auditoriaToEntityCustom(sourceVO, targetEntity);
	}

	@Override
	protected void handleUnlinkAccounts(AccountEntity account) throws Exception {
		getSession().createQuery("update com.soffid.iam.model.AuditEntityImpl "
				+ "set accountAssoc = null "
				+ "where accountAssoc.id = :id")
				.setParameter("id", account.getId())
				.executeUpdate();
	}

}
