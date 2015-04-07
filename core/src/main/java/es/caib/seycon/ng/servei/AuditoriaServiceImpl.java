// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.lang.MessageFactory;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.DateUtils;
import es.caib.seycon.ng.utils.LimitDates;
import es.caib.seycon.ng.utils.Security;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.MissingResourceException;
import java.util.Vector;

/**
 * @see es.caib.seycon.ng.servei.AuditoriaService
 */
public class AuditoriaServiceImpl extends
		es.caib.seycon.ng.servei.AuditoriaServiceBase {


	protected Collection<Auditoria> handleFindAuditoriesByCriteri(String data,
			String autor, String objecte, String usuari, String accio) throws Exception {
		return handleFindAuditoriesByCriteri(data, autor, objecte, usuari, null, null, null, accio);
	}

	protected Auditoria handleFindAuditoriaById(Long id) throws Exception {
		AuditEntity auditoriaEntity = getAuditEntityDao().findById(id);
		if (auditoriaEntity != null) {
			Auditoria auditoria = getAuditEntityDao().toAuditoria(auditoriaEntity);
			return auditoria;
		}
		return null;
	}

	public String[] handleFind(String sqlQuery) {
		if (sqlQuery != null) {
			String[] sqlQueryResult = getAuditEntityDao().find(sqlQuery);
			return sqlQueryResult;
		}
		return new String[0];
	}

	private void auditaQuery(String query) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio("Q"); // Administrador //$NON-NLS-1$
        auditoria.setObjecte("SC_AUDITO"); //$NON-NLS-1$
        auditoria.setAutor(codiUsuari);
        auditoria.setMessage(query);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));

        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

	// Reemplacem anterior
	protected Collection<Auditoria> handleFindAuditoriesByCriteri(String dataIni,
		String dataFi, String autor, String objecte, String usuari,
		String objecteAuditat, String valorOA, String accio)
		throws Exception
	{
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		//Aquest és el que es crida desde auditoria.zul
		// Si dataFi es nulo, empleamos el método anterior
		if (dataFi == null)
			return handleFindAuditoriesByCriteri(dataIni, autor, objecte, usuari, objecteAuditat, valorOA, accio);

		StringBuffer msg = new StringBuffer();
		Date d_dataIni = null, d_dataFi = null;

		if (dataIni != null && dataIni.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataIni.trim().compareTo("%") != 0) { //$NON-NLS-1$
			d_dataIni = DateUtils.stringToDate(dataIni, false);
			msg.append ("from date:").append(dataIni).append(' '); //$NON-NLS-1$
		} else
			throw new Exception(Messages.getString("AuditoriaServiceImpl.0")); //$NON-NLS-1$

		if (dataFi != null && dataFi.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataFi.trim().compareTo("%") != 0) { //$NON-NLS-1$
			d_dataFi = DateUtils.stringToDate(dataFi, true);
			msg.append ("to date:").append(dataFi).append(' '); //$NON-NLS-1$
		} else
			throw new Exception(Messages.getString("AuditoriaServiceImpl.1")); //$NON-NLS-1$

		if (autor != null
				&& (autor.trim().compareTo("") == 0 || autor.trim().compareTo( //$NON-NLS-1$
						"%") == 0)) { //$NON-NLS-1$
			autor = null;
		} else 
			msg.append ("author:").append(autor).append(' '); //$NON-NLS-1$

		if (objecte != null
				&& (objecte.trim().compareTo("") == 0 || objecte.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			objecte = null;
		} else
			msg.append ("object:").append(objecte).append(' '); //$NON-NLS-1$
		
		if (usuari != null
				&& (usuari.trim().compareTo("") == 0 || usuari.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			usuari = null;
		}
		else
			msg.append ("user:").append(usuari).append(' '); //$NON-NLS-1$

		if (accio != null
				&& (accio.trim().compareTo("") == 0 || accio.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			accio = null;
		}
		else
			msg.append ("action:").append(accio).append(' '); //$NON-NLS-1$
		// Cerquem si han especificat filtre per objecte auditat
		if (objecteAuditat != null
			 && (objecteAuditat.trim().compareTo("") == 0 || objecteAuditat //$NON-NLS-1$
					.trim().compareTo("%") == 0) //$NON-NLS-1$
					|| (valorOA != null && (valorOA.trim().compareTo("") == 0 || valorOA //$NON-NLS-1$
							.trim().compareTo("%") == 0))) { //$NON-NLS-1$
				objecteAuditat = null;
				valorOA = null;
		} else {
			msg.append (objecteAuditat).append(':').append(valorOA);
		}
		

		Collection auditories = new Vector();
		if (objecteAuditat == null ) { // Mètode antic
			auditories = getAuditEntityDao().findAuditByCriteria3(DateUtils.nullDate, d_dataFi, d_dataIni, autor, objecte, usuari, accio);
		} else {
			auditories = getAuditEntityDao().findAuditByCriteria4(DateUtils.nullDate, d_dataFi, d_dataIni, autor, objecte, usuari, objecteAuditat, valorOA, accio); //afegim objecteAuditat i el seu valor
		}
		
		auditaQuery(msg.toString());
		if (auditories != null)
		{
			// Check maximum number of results
			if (auditories.size() > limitResults)
			{
				return getAuditEntityDao().toAuditoriaList(auditories).subList(0, limitResults);
			}
			
			return getAuditEntityDao().toAuditoriaList(auditories);
		}
		
		return new Vector();
	}

	protected Collection<Auditoria> handleFindAuditoriesByCriteri(String dataIni,
			String dataFi, String autor, String objecte, String usuari, String accio) throws Exception {
		return handleFindAuditoriesByCriteri(dataIni, dataFi, autor, objecte, usuari, null, null, accio);
	}

	protected Collection<Auditoria> handleFindAuditoriesByCriteri(String data,
		String autor, String objecte, String usuari, String objecteAuditat,
		String valorOA, String accio) throws Exception
	{
		LimitDates limitDates = null;
		StringBuffer msg = new StringBuffer();
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$

		if (data != null && data.trim().compareTo("") != 0 //$NON-NLS-1$
				&& data.trim().compareTo("%") != 0) { //$NON-NLS-1$
			data = data.trim();
			limitDates = DateUtils.getLimitDatesFromQuery(data);
			msg.append ("from date:").append(data).append(' '); //$NON-NLS-1$
		}

		if (autor != null
				&& (autor.trim().compareTo("") == 0 || autor.trim().compareTo( //$NON-NLS-1$
						"%") == 0)) { //$NON-NLS-1$
			autor = null;
		} else 
			msg.append ("author:").append(autor).append(' '); //$NON-NLS-1$
		if (objecte != null
				&& (objecte.trim().compareTo("") == 0 || objecte.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			objecte = null;
		} else
			msg.append ("object:").append(objecte).append(' '); //$NON-NLS-1$
		if (usuari != null
				&& (usuari.trim().compareTo("") == 0 || usuari.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			usuari = null;
		}
		else
			msg.append ("user:").append(usuari).append(' '); //$NON-NLS-1$

		if (accio != null
				&& (accio.trim().compareTo("") == 0 || accio.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			accio = null;
		}		
		else
			msg.append ("action:").append(accio).append(' '); //$NON-NLS-1$

		// Cerquem si han especificat filtre per objecte auditat
		if (objecteAuditat != null && (objecteAuditat.trim().compareTo("") == 0 || objecteAuditat //$NON-NLS-1$
					.trim().compareTo("%") == 0) //$NON-NLS-1$
					|| (valorOA != null && (valorOA.trim().compareTo("") == 0 || valorOA //$NON-NLS-1$
							.trim().compareTo("%") == 0))) { //$NON-NLS-1$
			objecteAuditat = null;
			valorOA = null;
		} else {
			msg.append (objecteAuditat).append(':').append(valorOA);
		}

		CriteriaSearchConfiguration csc = new CriteriaSearchConfiguration();
		csc.setMaximumResultSize(10001);
		Collection auditories = new Vector();
		auditaQuery(msg.toString());
		if (objecteAuditat != null ) { 
			if (limitDates != null) {
				auditories = getAuditEntityDao().findAuditByCriteria4(csc, DateUtils.nullDate, limitDates.getMaximum(), limitDates.getMinimum(), autor, objecte, usuari, objecteAuditat, valorOA, accio);
			} else {
				auditories = getAuditEntityDao().findAuditByCriteria2(csc, autor, objecte, usuari, objecteAuditat, valorOA, accio);
			}			
		} else { // Mètode antic		
			if (limitDates != null) {
				auditories = getAuditEntityDao().findAuditByCriteria3(csc, DateUtils.nullDate, limitDates.getMaximum(), limitDates.getMinimum(), autor, objecte, usuari, accio);
			} else {
				auditories = getAuditEntityDao().findAuditByCriteria1(csc, autor, objecte, usuari, accio);
			}
		}
		if (auditories != null)
		{
			// Check maximum number of results
			if (auditories.size() > limitResults)
			{
				return getAuditEntityDao().toAuditoriaList(auditories).subList(0, limitResults);
			}
			
			return getAuditEntityDao().toAuditoriaList(auditories);
		}
		
		return new Vector();
	}

	@Override
	protected Auditoria handleCreate(Auditoria auditoria) throws Exception {
		
		AuditEntity entity = getAuditEntityDao().auditoriaToEntity(auditoria);
		getAuditEntityDao().create(entity);
		return getAuditEntityDao().toAuditoria(entity);
	}
}