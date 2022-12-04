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
package com.soffid.iam.service;

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.User;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.DateUtils;
import com.soffid.iam.utils.LimitDates;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Vector;

import org.json.JSONException;

/**
 * @see es.caib.seycon.ng.servei.AuditoriaService
 */
public class AuditServiceImpl extends
		com.soffid.iam.service.AuditServiceBase {


	protected Collection<Audit> handleFindAuditsByCriteria(String data, String autor, String objecte, String usuari, String accio) throws Exception {
		return handleFindAuditsByCriteria(data, autor, objecte, usuari, null, null, null, accio);
	}

	protected Audit handleFindAuditById(Long id) throws Exception {
		AuditEntity auditoriaEntity = getAuditEntityDao().findById(id);
		if (auditoriaEntity != null) {
			Audit auditoria = getAuditEntityDao().toAudit(auditoriaEntity);
			return auditoria;
		}
		return null;
	}

	private void auditaQuery(String query) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction("Q"); // Administrador //$NON-NLS-1$
        auditoria.setObject("SC_AUDITO"); //$NON-NLS-1$
        auditoria.setAuthor(codiUsuari);
        auditoria.setComment(query);

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

	// Reemplacem anterior
	protected Collection<Audit> handleFindAuditsByCriteria(String dataIni, String dataFi, String autor, String objecte, String usuari, String objecteAuditat, String valorOA, String accio) throws Exception {
		int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		//Aquest és el que es crida desde auditoria.zul
		// Si dataFi es nulo, empleamos el método anterior
		if (dataFi == null)
			return handleFindAuditsByCriteria(dataIni, autor, objecte, usuari, objecteAuditat, valorOA, accio);

		StringBuffer msg = new StringBuffer();
		Date d_dataIni = null, d_dataFi = null;

		if (dataIni != null && dataIni.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataIni.trim().compareTo("%") != 0) { //$NON-NLS-1$
			d_dataIni = DateUtils.stringToDate(dataIni, false);
			msg.append ("from date:").append(dataIni).append(' '); //$NON-NLS-1$
		} else
			throw new Exception(Messages.getString("AuditServiceImpl.0")); //$NON-NLS-1$

		if (dataFi != null && dataFi.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataFi.trim().compareTo("%") != 0) { //$NON-NLS-1$
			d_dataFi = DateUtils.stringToDate(dataFi, true);
			msg.append ("to date:").append(dataFi).append(' '); //$NON-NLS-1$
		} else
			throw new Exception(Messages.getString("AuditServiceImpl.1")); //$NON-NLS-1$

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
				return getAuditEntityDao().toAuditList(auditories).subList(0, limitResults);
			}
			
			return getAuditEntityDao().toAuditList(auditories);
		}
		
		return new Vector();
	}

	protected Collection<Audit> handleFindAuditsByCriteria(String dataIni, String dataFi, String autor, String objecte, String usuari, String accio) throws Exception {
		return handleFindAuditsByCriteria(dataIni, dataFi, autor, objecte, usuari, null, null, accio);
	}

	protected Collection<Audit> handleFindAuditsByCriteria(String data, String autor, String objecte, String usuari, String objecteAuditat, String valorOA, String accio) throws Exception {
		LimitDates limitDates = null;
		StringBuffer msg = new StringBuffer();
		int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$

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
				return getAuditEntityDao().toAuditList(auditories).subList(0, limitResults);
			}
			
			return getAuditEntityDao().toAuditList(auditories);
		}
		
		return new Vector();
	}

	@Override
    protected Audit handleCreate(Audit auditoria) throws Exception {
		
		AuditEntity entity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(entity);
		return getAuditEntityDao().toAudit(entity);
	}

	@Override
	protected Collection<Audit> handleFindAuditByJsonQuery(String query)
			throws InternalErrorException, Exception {

		auditaQuery(query);

		LinkedList<Audit> result = new LinkedList<Audit>();

		internalSearchAuditsByJson(query, result);
		
		return result;
	}

	private void internalSearchAuditsByJson(String query, Collection<Audit> result)
			throws UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException, TokenMgrError,
			EvalException, InternalErrorException {

		AbstractExpression expr = ExpressionParser.parse(query);
		expr.setOracleWorkaround( CustomDialect.isOracle());
		HQLQuery hql = expr.generateHSQLString(Audit.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.tenant.id = :tenantId";
		else
			qs = "("+qs+") and o.tenant.id = :tenantId";

		if (hql.getOrderByString().length() == 0) {
			if (hql.getOrderByString().length() == 0) {
				LinkedList<String> l = new LinkedList<>();
				l.add("o.date");
				hql.setOrderBy(l);
			}

		}

		hql.setWhereString(new StringBuffer(qs));
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size()+1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());
		TimeOutUtils tou = new TimeOutUtils();
		for (AuditEntity ue : getAuditEntityDao().query(hql.toString(),
				paramArray)) {
			if (result instanceof AsyncList)
			{
				if (((AsyncList) result).isCancelled())
					return;
			}
			else
			{
				tou.checkTimeOut();
			}
			Audit u = getAuditEntityDao().toAudit(ue);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(u)) {
				result.add(u);
			}
		}
	}

	@Override
	protected AsyncList<Audit> handleFindAuditByJsonQueryAsync(final String query)
			throws InternalErrorException, Exception {
		
		final AsyncList<Audit> result = new AsyncList<Audit>();
		
		auditaQuery(query);
		getAsyncRunnerService().run(new Runnable() {

			@Override
			public void run() {
				try {
					internalSearchAuditsByJson(query, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
			
		}, result);

		return result;
	}

}