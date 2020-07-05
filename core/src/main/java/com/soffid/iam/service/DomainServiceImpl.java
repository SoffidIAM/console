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
import com.soffid.iam.api.Domain;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.System;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.ApplicationDomainEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.DomainValueEntityDao;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.json.JSONException;

/**
 * @see es.caib.seycon.ng.servei.DominiService
 */
public class DomainServiceImpl extends
		com.soffid.iam.service.DomainServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#create(es.caib.seycon.ng.comu.Domini)
	 */
	protected com.soffid.iam.api.Domain handleCreate(com.soffid.iam.api.Domain domini) throws java.lang.Exception {
		if ((domini.getName().compareToIgnoreCase(TipusDomini.GRUPS) == 0) || 
				(domini.getName().compareToIgnoreCase(TipusDomini.GRUPS_USUARI) == 0) || 
				(domini.getName().compareToIgnoreCase(TipusDomini.GROUPS) == 0) || 
				(domini.getName().compareToIgnoreCase(TipusDomini.MEMBERSHIPS) == 0) || 
				(domini.getName().compareToIgnoreCase(TipusDomini.APPLICATIONS) == 0) || 
				(domini.getName().compareToIgnoreCase(TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DomainServiceImpl.0")); //$NON-NLS-1$
		}
		ApplicationDomainEntity dominiEntity = getApplicationDomainEntityDao().domainToEntity(domini);
		getApplicationDomainEntityDao().create(dominiEntity);
		domini.setId(dominiEntity.getId());
		domini = getApplicationDomainEntityDao().toDomain(dominiEntity);
		return domini;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#delete(es.caib.seycon.ng.comu.Domini)
	 */
	protected void handleDelete(com.soffid.iam.api.Domain domini) throws java.lang.Exception {
		if ((domini.getName().compareToIgnoreCase(TipusDomini.GRUPS) == 0) || (domini.getName().compareToIgnoreCase(TipusDomini.GRUPS_USUARI) == 0) || (domini.getName().compareToIgnoreCase(TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DomainServiceImpl.1")); //$NON-NLS-1$
		}
		
		ApplicationDomainEntity dominiEntity = getApplicationDomainEntityDao().domainToEntity(domini);
		// codiExtern en dominis de tipus d'aplicació és el codi de l'aplicacio
		if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_UPDATE, dominiEntity.getInformationSystem())) {
			getApplicationDomainEntityDao().remove(dominiEntity);
		} else {
			throw new SeyconAccessLocalException("DominiService", //$NON-NLS-1$
					"delete (Domini)", "application:delete", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("DomainServiceImpl.2")); //$NON-NLS-1$
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#create(es.caib.seycon.ng.comu.ValorDomini)
	 */
	protected com.soffid.iam.api.DomainValue handleCreate(com.soffid.iam.api.DomainValue valorDomini) throws java.lang.Exception {
		if ((valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.GRUPS) == 0) || 
				(valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.GRUPS_USUARI) == 0) || 
				(valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DomainServiceImpl.3")); //$NON-NLS-1$
		}
		DomainValueEntity valorDominiAplicacioEntity = getDomainValueEntityDao().domainValueToEntity(valorDomini);
		getDomainValueEntityDao().create(valorDominiAplicacioEntity);
		valorDomini.setId(valorDominiAplicacioEntity.getId());
		valorDomini = getDomainValueEntityDao().toDomainValue(valorDominiAplicacioEntity);
		return valorDomini;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#create(es.caib.seycon.ng.comu.ValorDomini)
	 */
	protected com.soffid.iam.api.DomainValue handleUpdate(com.soffid.iam.api.DomainValue valorDomini) throws java.lang.Exception {
		if ((valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.GRUPS) == 0) || 
				(valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.GRUPS_USUARI) == 0) || 
				(valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DomainServiceImpl.3")); //$NON-NLS-1$
		}
		DomainValueEntity entity = getDomainValueEntityDao().load(valorDomini.getId());
		getDomainValueEntityDao().domainValueToEntity(valorDomini, entity, false);
		getDomainValueEntityDao().update(entity);
		valorDomini = getDomainValueEntityDao().toDomainValue(entity);
		return valorDomini;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#delete(es.caib.seycon.ng.comu.ValorDomini)
	 */
	protected void handleDelete(com.soffid.iam.api.DomainValue valorDomini) throws java.lang.Exception {
		if ((valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.GRUPS) == 0) || 
				(valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.GRUPS_USUARI) == 0) ||
				(valorDomini.getDomainName().compareToIgnoreCase(TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DomainServiceImpl.4")); //$NON-NLS-1$
		}
		
		DomainValueEntity valorDominiAplicacioEntity = getDomainValueEntityDao().domainValueToEntity(valorDomini);
		if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_UPDATE, valorDominiAplicacioEntity.getDomain().getInformationSystem())) {
			getDomainValueEntityDao().remove(valorDominiAplicacioEntity);
		} else {
			throw new SeyconAccessLocalException("DominiService", //$NON-NLS-1$
					"delete (ValorDomini)", "application:delete", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("DomainServiceImpl.5")); //$NON-NLS-1$
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#findDominiGrupsUsuariByCodiUsuari(java.lang.String)
	 */
	protected com.soffid.iam.api.Domain handleFindUserDomainGroup() throws java.lang.Exception {
		Domain domini = new Domain();
		domini.setName(TipusDomini.MEMBERSHIPS);
		return domini;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#findDominiGrups()
	 */
	protected com.soffid.iam.api.Domain handleFindGroupsDomain() throws java.lang.Exception {
		Domain domini = new Domain();
		domini.setName(TipusDomini.GROUPS);
		domini.setExternalCode(null);
		return domini;
	}

	protected Domain handleFindApplicationDomainByDomianNameAndApplicationName(String nomDomini, String codiAplicacio) throws Exception {
		
		ApplicationDomainEntity ad = getApplicationDomainEntityDao().findByName(nomDomini, codiAplicacio);
		
		if (ad == null)
			return null;
		else
			return getApplicationDomainEntityDao().toDomain(ad);
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#findValorsDominiByDomini(es.caib.seycon.ng.comu.Domini)
	 */
	protected java.util.Collection<DomainValue> handleFindDomainValuesByFilter(com.soffid.iam.api.Domain domini, String codi, String descripcio, String codiUsuari) throws java.lang.Exception {
		if (domini.getName().compareToIgnoreCase(TipusDomini.GRUPS_USUARI) == 0 ||
			domini.getName().compareToIgnoreCase(TipusDomini.MEMBERSHIPS) == 0) {
			String query = "select grp " //$NON-NLS-1$
					+ "from com.soffid.iam.model.GroupEntity grp " //$NON-NLS-1$
					+ "join grp.secondaryGroupUsers as sg " //$NON-NLS-1$
					+ "join sg.user as user " //$NON-NLS-1$
					+ "where " //$NON-NLS-1$
					+ "user.userName = :codiUsuari and " //$NON-NLS-1$
					+ "(:codi is null or grp.name like :codi) and " //$NON-NLS-1$
					+ "(:descripcio is null or grp.description like :descripcio)"; //$NON-NLS-1$
			Parameter codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
			Parameter codiDescripcio = new Parameter("descripcio", descripcio); //$NON-NLS-1$
			Parameter codiUsuariParameter = new Parameter("codiUsuari", //$NON-NLS-1$
					codiUsuari);
			Parameter[] parameters = { codiParameter, codiDescripcio,
					codiUsuariParameter };
			List<GroupEntity> valorsDomini = getGroupEntityDao().query(query, parameters);
			if (valorsDomini == null) {
				valorsDomini = new Vector<GroupEntity>();
			}

			String queryGrupPrimari = "select grp " //$NON-NLS-1$
					+ "from com.soffid.iam.model.GroupEntity grp " //$NON-NLS-1$
					+ "join grp.primaryGroupUsers as user " //$NON-NLS-1$
					+ "where " //$NON-NLS-1$
					+ "user.userName = :codiUsuari and " //$NON-NLS-1$
					+ "(:codi is null or grp.name like :codi) and " //$NON-NLS-1$
					+ "(:descripcio is null or grp.description like :descripcio)"; //$NON-NLS-1$
			codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
			codiDescripcio = new Parameter("descripcio", descripcio); //$NON-NLS-1$
			codiUsuariParameter = new Parameter("codiUsuari", codiUsuari); //$NON-NLS-1$
			Parameter[] parametersGrupPrimari = { codiParameter,
					codiDescripcio, codiUsuariParameter };
			List<GroupEntity> grupPrimari = getGroupEntityDao().query(queryGrupPrimari, parametersGrupPrimari);
			if (grupPrimari != null) {
				valorsDomini.addAll(grupPrimari);
			}
			if (valorsDomini != null) {
				List<DomainValue> vdl = getGroupEntityDao().toDomainValueList(valorsDomini);
				Iterator<DomainValue> iterator = vdl.iterator();
				while (iterator.hasNext()) {
					DomainValue valorDomini = iterator.next();
					valorDomini.setDomainName(TipusDomini.MEMBERSHIPS);
					valorDomini.setExternalCodeDomain(codiUsuari);
				}
				return vdl;
			}
			return new Vector();
		}
		if (domini.getName().compareToIgnoreCase(TipusDomini.GRUPS) == 0 ||
				domini.getName().compareToIgnoreCase(TipusDomini.GROUPS) == 0) {
			List<GroupEntity> valorsDomini = getGroupEntityDao()
					.findByCriteria(codi, null, null, descripcio, null, null);
			if (valorsDomini != null) {
				List<DomainValue> vdl = getGroupEntityDao().toDomainValueList(valorsDomini);
				Iterator<DomainValue> iterator = vdl.iterator();
				while (iterator.hasNext()) {
					DomainValue valorDomini = (DomainValue) iterator.next();
					valorDomini.setDomainName(TipusDomini.GROUPS);
					valorDomini.setExternalCodeDomain(null);
				}
				return vdl;
			}
			return new Vector();
		}
		if (domini.getName().compareToIgnoreCase(TipusDomini.APLICACIONS) == 0 ||
				domini.getName().compareToIgnoreCase(TipusDomini.APPLICATIONS) == 0) {
			Collection<InformationSystemEntity> valorsDomini =
					getInformationSystemEntityDao().findByFilter(
							codi, descripcio, null, null, null, null, null);
			if (valorsDomini != null) {
				List<DomainValue> vdl = getInformationSystemEntityDao().toDomainValueList(valorsDomini);
				Iterator<DomainValue> iterator = vdl.iterator();
				while (iterator.hasNext()) {
					DomainValue valorDomini = (DomainValue) iterator.next();
					valorDomini.setDomainName(TipusDomini.APPLICATIONS);
					valorDomini.setExternalCodeDomain(null);
				}
				return vdl;
			}
			return new Vector();
		}
		
		// domini d'aplicació
		String nomDomini = domini.getName();
		String codiAplicacio = domini.getExternalCode();
		Collection<DomainValueEntity> valorsDomini = 
			getDomainValueEntityDao().findByInformationSystem(codiAplicacio, nomDomini);

		if (valorsDomini != null) {
			return getDomainValueEntityDao().toDomainValueList(valorsDomini);
		}
		return new Vector();
	}

	protected DomainValue handleFindApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(String nomDomini, String codiAplicacio, String valor) throws Exception {
		
		DomainValueEntity valorDominiEntity = 
				getDomainValueEntityDao()
					.findByApplicationDomainValue(codiAplicacio, nomDomini, valor);
		if (valorDominiEntity != null) {
			return getDomainValueEntityDao().toDomainValue(valorDominiEntity);
		}
		return null;
	}

	protected Collection<Domain> handleFindApplicationDomainsByApplicationName(String codiAplicacio) throws Exception {
		List<ApplicationDomainEntity> dominiAplicacions = getApplicationDomainEntityDao().findByInformationSystem(codiAplicacio);
		if (dominiAplicacions != null) {
			return getApplicationDomainEntityDao().toDomainList(dominiAplicacions);
		}
		return new Vector();
	}

	protected Domain handleUpdate(Domain domini) throws Exception {
		ApplicationDomainEntity dominiEntity = getApplicationDomainEntityDao().domainToEntity(domini);
		getApplicationDomainEntityDao().update(dominiEntity);
		return this.getApplicationDomainEntityDao().toDomain(dominiEntity);
	}

	private Domain getDominiSenseDomini() {
		Domain domini = new Domain();
		domini.setName(TipusDomini.SENSE_DOMINI);
		return domini;
	}

	private Domain getDominiAplicacions() {
		Domain domini = new Domain();
		domini.setName(TipusDomini.APLICACIONS);
		return domini;
	}

	protected Collection<Domain> handleFindDomainsByApplicationName(String codiAplicacio) throws Exception {
		List<ApplicationDomainEntity> dominis = getApplicationDomainEntityDao().findByInformationSystemPattern(codiAplicacio);
		if (dominis != null) {
			List<Domain> dominisVO = getApplicationDomainEntityDao().toDomainList(dominis);
			dominisVO.add(findGroupsDomain());
			dominisVO.add(findUserDomainGroup());
			dominisVO.add(getDominiSenseDomini());
			dominisVO.add(getDominiAplicacions());
			return dominisVO;
		}
		return new Vector();
	}

	@Override
	protected AsyncList<DomainValue> handleFindDomainValueByTextAndFilterAsync(String text, String query)
			throws Exception {
		final AsyncList<DomainValue> result = new AsyncList<DomainValue>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindDomainValueByTextAndJsonQuery(text, query, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	@Override
	protected Domain handleFindDomainByApplicationAndName(String informationSystem, String name) throws Exception {
		ApplicationDomainEntity entity = getApplicationDomainEntityDao().findByName(name, informationSystem);
		if (entity == null)
			return null;
		else
			return getApplicationDomainEntityDao().toDomain(entity);
	}

	@Override
	protected List<DomainValue> handleFindDomainValueByTextAndFilter(String text, String query, Integer first,
			Integer pageSize) throws Exception {
		final LinkedList<DomainValue> result = new LinkedList<DomainValue>();
		doFindDomainValueByTextAndJsonQuery(text, query, first, pageSize, result);
		return result;
	}

	private void doFindDomainValueByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			Collection<DomainValue> result) throws ClassNotFoundException, InternalErrorException, UnsupportedEncodingException, JSONException, EvalException, ParseException, TokenMgrError {
		final DomainValueEntityDao dao = getDomainValueEntityDao();
		ScimHelper h = new ScimHelper(DomainValue.class);
		h.setPrimaryAttributes(new String[] { "name", "description"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("domain.informationSystem.tenant.id");
		h.setGenerator((entity) -> {
			return dao.toDomainValue((DomainValueEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
	}
	
}
