// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

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

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.lang.MessageFactory;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.servei.PasswordService;
import es.caib.seycon.ng.utils.ExceptionTranslator;

/**
 * @see es.caib.seycon.ng.model.AuditoriaEntity
 */
public class AuditoriaEntityDaoImpl extends
		es.caib.seycon.ng.model.AuditoriaEntityDaoBase {
	private static final String BUNDLE_NAME = "es.caib.seycon.ng.model.audit.messages"; //$NON-NLS-1$
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());

	@Override
	public AuditoriaEntity newAuditoriaEntity()
	{
		AuditoriaEntity au = super.newAuditoriaEntity();
		au.setData(new Date());
		return au;
	}

	public java.lang.String[] find(java.lang.String sqlQuery) {
		throw new UnsupportedOperationException();
	}

	public void create(
			es.caib.seycon.ng.model.AuditoriaEntity auditoria)
			throws RuntimeException {
		try {
			super.create(auditoria);
			getSession(false).flush();
			
			String syslogServer = System.getProperty ("soffid.syslog.server");
			if (syslogServer != null && syslogServer.trim().length() > 0)
			{
				try {
					Auditoria audobj  = toAuditoria(auditoria);
	
					InetAddress addr = InetAddress.getByName(syslogServer);
					
					sendSysLog (addr, audobj);
				} catch (Throwable t) {
					log.warn("Unable to send syslog information", t);
				}
			}
			
			
			
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AuditoriaEntityDaoImpl.1"),   //$NON-NLS-1$
					auditoria.getId(),
					message));
		}
	}

	private void sendSysLog(InetAddress syslogServer, Auditoria audobj) throws IOException {
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
        p.print("["+audobj.getAutor()+"] "+audobj.getMessage());// MSG     
        p.flush ();
        
        byte buf [] = out.toByteArray();
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        s.send (packet);
	}

	public void remove(es.caib.seycon.ng.model.AuditoriaEntity auditoria)
			throws RuntimeException {
		try {
			super.remove(auditoria);
			getSession(false).flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AuditoriaEntityDaoImpl.2"),   //$NON-NLS-1$
					auditoria.getId(),
					message));
		}
	}

	public void toAuditoria(
			es.caib.seycon.ng.model.AuditoriaEntity sourceEntity,
			es.caib.seycon.ng.comu.Auditoria targetVO) {
		super.toAuditoria(sourceEntity, targetVO);
		toAuditoriaCustom(sourceEntity, targetVO);
	}

	public void toAuditoriaCustom(
			es.caib.seycon.ng.model.AuditoriaEntity sourceEntity,
			es.caib.seycon.ng.comu.Auditoria targetVO) {
		targetVO.setAccount(sourceEntity.getAccount());
		Date data = sourceEntity.getData();
		if (data != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
			targetVO.setData(dateFormat.format(data));
		}
		targetVO.setCalendar (Calendar.getInstance());
		targetVO.getCalendar().setTime (data);

		GrupEntity grup = sourceEntity.getGrup();
		if (grup != null) {
			targetVO.setGrup(grup.getCodi());
		}

		AccountEntity usuari = sourceEntity.getAccountAssoc();
		if (usuari != null) {
			targetVO.setAutor(usuari.getName());
			/* afegim nom complet de l'autor i el seu grup primari */
			targetVO.setAutorNomComplet(usuari.getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
			if (usuari.getType().equals (AccountType.USER))
			{
				for (UserAccountEntity ua: usuari.getUsers())
				{
					targetVO.setAutorGrupPrimari(ua.getUser().getGrupPrimari().getCodi());
				}
			}
		}
		
		targetVO.setValorDomini(sourceEntity.getValorDomini());
		
		if (sourceEntity.getFitxerId() !=null) {
			// Atenció: els fitxers es poden esborrar... per això no n'hi ha una
			// relació amb els fitxers.. la que havia era amb el seu ID
			try {
//				FitxerEntity f = getFitxerEntityDao().findById(sourceEntity.getFitxerId());
//				targetVO.setNomFitxer(f.getNom());
			} catch (Throwable th) {
				//Marquem el seu id
				targetVO.setNomFitxer(sourceEntity.getFitxerId()+" (id)"); //$NON-NLS-1$
			}
			targetVO.setFitxer(sourceEntity.getFitxerId());
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
				msg = MessageFactory.getString(BUNDLE_NAME+"_"+targetVO.getObjecte(), targetVO.getAccio()); //$NON-NLS-1$
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
					logger.debug(String.format(Messages.getString("AuditoriaEntityDaoImpl.UnknownVariable"), variable, key)); //$NON-NLS-1$
					result.append ("${").append (variable).append("}"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			} while (true);
			result.append(msg.substring(processed));
			targetVO.setMessage(result.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			targetVO.setMessage(String.format(Messages.getString("AuditoriaEntityDaoImpl.Action"), key)); //$NON-NLS-1$
		}

	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.AuditoriaEntity loadAuditoriaEntityFromAuditoria(
			es.caib.seycon.ng.comu.Auditoria auditoria) {
		if (auditoria.getId() == null) {
			return newAuditoriaEntity();
		} else {
			throw new SeyconException(Messages.getString("AuditoriaEntityDaoImpl.4")); //$NON-NLS-1$
		}
	}

	public es.caib.seycon.ng.model.AuditoriaEntity auditoriaToEntity(
			es.caib.seycon.ng.comu.Auditoria auditoria) {
		es.caib.seycon.ng.model.AuditoriaEntity entity = this
				.loadAuditoriaEntityFromAuditoria(auditoria);
		this.auditoriaToEntity(auditoria, entity, true);
		return entity;
	}

	public void auditoriaToEntityCustom(
			es.caib.seycon.ng.comu.Auditoria sourceVO,
			es.caib.seycon.ng.model.AuditoriaEntity targetEntity) {
		if (sourceVO.getCalendar() != null)
		{
			targetEntity.setData(sourceVO.getCalendar().getTime());
		}
		else
		{
    		String dateString = sourceVO.getData();
    		try {
    			SimpleDateFormat dateFormat = new SimpleDateFormat(
    					"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
    			Date date = dateFormat.parse(dateString);
    			targetEntity.setData(date);
    		} catch (Exception e) {
    			throw new SeyconException(String.format(Messages.getString("AuditoriaEntityDaoImpl.5"), dateString)); //$NON-NLS-1$
    		}
		}

		targetEntity.setValorDomini(sourceVO.getValorDomini());
		
		String grup = sourceVO.getGrup();
		if (grup != null && grup.trim().length() > 0) {
			GrupEntity grupEntity = getGrupEntityDao().findByCodi(grup);
			targetEntity.setGrup(grupEntity);
		}

		String usuari = sourceVO.getAutor();
		if (usuari != null && usuari.trim().length() > 0) {
			PasswordService passwordService = getPasswordService ();
			try
			{
				String domain = passwordService.getDefaultDispatcher();
				AccountEntity usuariEntity = getAccountEntityDao().findByNameAndDispatcher(usuari, domain);
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
	public void auditoriaToEntity(es.caib.seycon.ng.comu.Auditoria sourceVO,
			es.caib.seycon.ng.model.AuditoriaEntity targetEntity,
			boolean copyIfNull) {
		super.auditoriaToEntity(sourceVO, targetEntity, copyIfNull);
		if (copyIfNull || sourceVO.getAccount() != null)
		{
			targetEntity.setAccount(sourceVO.getAccount());
		}
		auditoriaToEntityCustom(sourceVO, targetEntity);
	}

}