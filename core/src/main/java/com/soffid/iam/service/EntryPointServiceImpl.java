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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;

import org.apache.commons.jcs.access.behavior.ICacheAccess;
import org.dom4j.Document;
import org.json.JSONException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.api.AccessTreeExecutionType;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.EntryPointAccountEntity;
import com.soffid.iam.model.EntryPointEntity;
import com.soffid.iam.model.EntryPointEntityDao;
import com.soffid.iam.model.EntryPointExecutableEntity;
import com.soffid.iam.model.EntryPointExecutionTypeEntity;
import com.soffid.iam.model.EntryPointGroupEntity;
import com.soffid.iam.model.EntryPointIconEntity;
import com.soffid.iam.model.EntryPointRoleEntity;
import com.soffid.iam.model.EntryPointTreeEntity;
import com.soffid.iam.model.EntryPointUserEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.spring.JCSCacheProvider;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TipusAutoritzacioPuntEntrada;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownUserException;

/**
 * @see es.caib.seycon.ng.servei.PuntEntradaService
 */
public class EntryPointServiceImpl extends
		com.soffid.iam.service.EntryPointServiceBase  {

	private static final String ROOT_TAG = "Root"; //$NON-NLS-1$

	public EntryPointServiceImpl() {
	}

	// Informació de l'usuari actual (per comprovar les autoritzacions)

	/**
	 * @see es.caib.seycon.ng.servei.PuntEntradaService#create(es.caib.seycon.ng.comu.PuntEntrada)
	 */
	protected com.soffid.iam.api.AccessTree handleCreate(
			com.soffid.iam.api.AccessTree puntEntrada)
			throws java.lang.Exception {
		//
		// VERIFICACIONS:
		//
		Long idPare = puntEntrada.getParentId();

		// Verificamos que el padre sea de tipo menú:
		if (puntEntrada.getParentId() == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.ObtaintParentPointEntryError")); //$NON-NLS-1$

		EntryPointEntity pareE = getEntryPointEntityDao().load(
				puntEntrada.getParentId());

		if (pareE == null)
			throw new CreateException(
					Messages.getString("EntryPointServiceImpl.ParentMenuNotFounded")); //$NON-NLS-1$
		if (!"S".equals(pareE.getMenu())) { //$NON-NLS-1$
			throw new CreateException(
					Messages.getString("EntryPointServiceImpl.ParentNotAMenu")); //$NON-NLS-1$
		}
		// Verificamos autorización del padre
		AccessTree pare = getEntryPointEntityDao().toAccessTree(pareE);
		if (!canAdmin(pare)) {
			throw new SecurityException(
					Messages.getString("EntryPointServiceImpl.UnauthorizedtForAdminParentMenu")); //$NON-NLS-1$
		}

		// Si el nou node és de tipus menú, verifiquem que tinga indicat el
		// tipus de menu
		// i esborrem les execucions (si existeixen)
		if ("S".equals(puntEntrada.isMenu())) { //$NON-NLS-1$
			if (puntEntrada.getMenuType() == null)
				throw new CreateException(
						Messages.getString("EntryPointServiceImpl.MenuTypeMessage")); //$NON-NLS-1$
			puntEntrada.setExecutions(new HashSet()); // esborrem execucions
														// abans de crear
														// entitat
		}

		// Validem el XML si no és buit
		if (puntEntrada.getXmlAccessTree() != null
				&& !"".equals(puntEntrada.getXmlAccessTree())) { //$NON-NLS-1$
			String resValida = validateXMLApplicationAccess(puntEntrada);
			if (resValida != null && !"".equals(resValida.trim())) //$NON-NLS-1$
				throw new InternalErrorException(String.format(Messages
						.getString("EntryPointServiceImpl.XMLValidationError"),
						puntEntrada.getName(), resValida));
		}

		//
		// OBTENIM L'ENTITAT
		//
		EntryPointEntity entity = getEntryPointEntityDao().accessTreeToEntity(
				puntEntrada);

		// CREEM L'ENTITAT (!!)
		getEntryPointEntityDao().create(entity);

		// Creem l'ARBRE del punt d'entrada
		int ordre = 0; //$NON-NLS-1$	//String ordre = "0";
		// Obtenim L'ORDRE DE L'ARBRE des dels fills del pare (estan ordenats
		// per ordre ascendent)
		List fills = (List) getEntryPointTreeEntityDao().findByParent(
				puntEntrada.getParentId());
		if (fills != null) {// Ens quedem en el fill de major ordre
			if (fills.size() == 0) // Para nodes menú sense fills
				ordre = 0; //$NON-NLS-1$	//ordre = "0";
			else { // Obtenim el seu fill "major"
				EntryPointTreeEntity fill = (EntryPointTreeEntity) fills
						.get(fills.size() - 1);
				int ordreFillMajor = fill.getOrder(); // Integer.parseInt(fill.getOrdre());
				ordre = ordreFillMajor + 1; //$NON-NLS-1$	//"" + (ordreFillMajor + 1);
			}
		}
		EntryPointTreeEntity arbre = getEntryPointTreeEntityDao()
				.newEntryPointTreeEntity();
		arbre.setOrder(ordre);
		arbre.setChild(entity);
		arbre.setParent(pareE);
		HashSet<EntryPointTreeEntity> monArbre = new HashSet<EntryPointTreeEntity>();
		monArbre.add(arbre);
		// Establim l'arbre
		pareE.setChildrenEntryPointTree(monArbre);

		// Creem les relacions del punt d'entrada
		// Arbre
		getEntryPointTreeEntityDao().create(arbre);

		// Creem les icones
		EntryPointIconEntity icona1 = null;
		if (puntEntrada.getIcon1Image() != null
				&& puntEntrada.getIcon1Image().length != 0) {
			// Creem l'icona
			icona1 = createIcona(puntEntrada.getIcon1Image());
			entity.setIcon1(icona1.getId());
		}
		EntryPointIconEntity icona2 = null;
		if (puntEntrada.getIcon2Image() != null
				&& puntEntrada.getIcon2Image().length != 0) {
			// S'ha actualitzat l'icona: creem una nova
			icona2 = createIcona(puntEntrada.getIcon2Image());
			entity.setIcon2(icona2.getId());
		}

		// Actualitzem l'entitat (amb les relacions)
		getEntryPointEntityDao().update(entity);

		// Afegim id del pare (per poder moure'l ara mateix)
		AccessTree res = getEntryPointEntityDao().toAccessTree(entity);
		res.setParentId(idPare);

		// Assignem iconas (en el toVO encara no poden estar en la BD)
		if (icona1 != null) {
			res.setIcon1Id(icona1.getId());
			res.setIcon1Image(icona1.getIcon());
		}
		if (icona2 != null) {
			res.setIcon2Id(icona2.getId());
			res.setIcon2Image(icona2.getIcon());
		}

		auditarPuntEntrada(
				"C", res.getName() + Messages.getString("EntryPointServiceImpl.15") + pareE.getName()); //$NON-NLS-1$ //$NON-NLS-2$

		return res;
	}

	/**
	 * @see es.caib.seycon.ng.servei.PuntEntradaService#update(es.caib.seycon.ng.comu.PuntEntrada)
	 */
	protected com.soffid.iam.api.AccessTree handleUpdate(
			com.soffid.iam.api.AccessTree puntEntrada)
			throws java.lang.Exception {

    	EntryPointEntity existingEntity = getEntryPointEntityDao().load (puntEntrada.getId());
    	if (existingEntity == null)
    		return null;
    	AccessTree existing = getEntryPointEntityDao().toAccessTree(existingEntity);
		
        if (!canAdmin(existing))
			throw new SecurityException(
					Messages.getString("EntryPointServiceImpl.UnauthorizedToUpdate")); //$NON-NLS-1$

		// Validem el XML si no és buit
		if (puntEntrada.getXmlAccessTree() != null
				&& !"".equals(puntEntrada.getXmlAccessTree())) { //$NON-NLS-1$
			String resValida = validateXMLApplicationAccess(puntEntrada);
			if (resValida != null && !"".equals(resValida.trim())) //$NON-NLS-1$
				throw new InternalErrorException(String.format(Messages
						.getString("EntryPointServiceImpl.XMLValidationError"),
						puntEntrada.getName(), resValida));
		}

		// Transformem a Entity
		EntryPointEntity entity = getEntryPointEntityDao().load(
				puntEntrada.getId());
		boolean updatingRoot = entity != null
				&& ROOT_TAG.equals(entity.getCode());
		getEntryPointEntityDao().accessTreeToEntity(puntEntrada, entity, true);
		if (updatingRoot) {
			entity.setCode(ROOT_TAG);
		} else {
			if (ROOT_TAG.equals(puntEntrada.getCode())) {
				entity.setCode(null);
			}
		}

		// Si és e tipus menú, esborrem execucions:
		if (puntEntrada.isMenu()) { //$NON-NLS-1$
			entity.setExecutionMethod(new HashSet<EntryPointExecutableEntity>()); // esborrem
																					// execucions
		}

		// Verifiquem les icones:
		// ACTUALITZACIONS
		// UPDATE: Ja té icona, i s'ha posta una nova
		if (entity.getIcon1() != null && puntEntrada.getIcon1Image() != null
				&& puntEntrada.getIcon1Image().length != 0 ) {
			EntryPointIconEntity i = getEntryPointIconEntityDao().load(entity.getIcon1());
			if (i != null) {
				i.setIcon(puntEntrada.getIcon1Image());
				getEntryPointIconEntityDao().update(i);
			} else {
				EntryPointIconEntity icona1 = createIcona(puntEntrada
						.getIcon1Image());
				entity.setIcon1(icona1.getId());
			}
		}
		if (entity.getIcon2() != null && puntEntrada.getIcon2Image() != null
				&& puntEntrada.getIcon2Image().length != 0) {
			EntryPointIconEntity i = getEntryPointIconEntityDao().load(entity.getIcon2());
			if (i != null) {
				i.setIcon(puntEntrada.getIcon2Image());
				getEntryPointIconEntityDao().update(i);
			} else {
				EntryPointIconEntity icona2 = createIcona(puntEntrada
						.getIcon2Image());
				entity.setIcon2(icona2.getId());
			}
		}
		// ADD: NOVES ICONES (no existien abans)
		if (entity.getIcon1() == null && puntEntrada.getIcon1Image() != null
				&& puntEntrada.getIcon1Image().length != 0) {
			// Creem l'icona
			EntryPointIconEntity icona1 = createIcona(puntEntrada
					.getIcon1Image());
			entity.setIcon1(icona1.getId());
		}
		if (entity.getIcon2() == null && puntEntrada.getIcon2Image() != null
				&& puntEntrada.getIcon2Image().length != 0) {
			// S'ha actualitzat l'icona: creem una nova
			EntryPointIconEntity icona2 = createIcona(puntEntrada
					.getIcon2Image());
			entity.setIcon2(icona2.getId());
		}
		// DELETE: Esborrem l'icona assignada
		if (entity.getIcon1() != null && puntEntrada.getIcon1Image() == null) {
			// Esborrem l'icona anterior
			getEntryPointIconEntityDao().remove(entity.getIcon1()); // Per id
			entity.setIcon1(null);
		}
		if (entity.getIcon2() != null && puntEntrada.getIcon2Image() == null) {
			// Esborrem l'icona anterior
			getEntryPointIconEntityDao().remove(entity.getIcon2()); // Per id
			entity.setIcon2(null);
		}

		getEntryPointEntityDao().update(entity);

		auditarPuntEntrada("U", entity.getName()); //$NON-NLS-1$

		return getEntryPointEntityDao().toAccessTree(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.PuntEntradaService#delete(es.caib.seycon.ng.comu.PuntEntrada)
	 */
	protected void handleDelete(com.soffid.iam.api.AccessTree puntEntrada)
			throws java.lang.Exception {

    	EntryPointEntity existingEntity = getEntryPointEntityDao().load (puntEntrada.getId());
    	if (existingEntity == null)
    		return;
    	AccessTree existing = getEntryPointEntityDao().toAccessTree(existingEntity);
		if (!canAdmin(existing))
			throw new RemoveException("no autoritzat"); //$NON-NLS-1$

		if (puntEntrada.getId() == null)
			// Correcte per als nous //TODO: verificar
			throw new RemoveException(
					Messages.getString("EntryPointServiceImpl.NotIDEntryPoint")); //$NON-NLS-1$

		// OBTENIM L'ENTITAT
		EntryPointEntity entity = getEntryPointEntityDao().accessTreeToEntity(
				puntEntrada);

		// Analizamos los "enlaces" para saber si sólo tenemos un padre o varios
		// Si sólo hay uno, se borra el punt d'entrada, si hay varios: se borra
		// en enlace(link)
		Collection mosPares = entity.getParentEntryPointTree();

		// Par saber si hem de esborrar el punt d'entrada o només el link
		// (l'arbre)
		boolean enlazado = false;
		if (mosPares.size() == 0) { // no se pot esborrar el node root
			throw new RemoveException(
					Messages.getString("EntryPointServiceImpl.DeleteNotAllowed")); //$NON-NLS-1$
		} else if (mosPares.size() > 1) { // té més d'un pare: la deslincamos
											// del padre actual
			// throw new
			// RemoveException("Error: Té més d'un punt d'entrada pare");
			enlazado = true; // hem d'esborrar l'enllaç
		}
		// Ens quedem en el id del pare del punt d'entrada actual
		Long idPare = puntEntrada.getParentId(); // ((ArbrePuntEntradaEntity)
													// mosPares.iterator().next()).getPare().getId();

		// ARBRE DE PUE: si no estamos enlazados, no permitimos borrarnos si
		// tenemos hijos
		if (!enlazado) {
			Collection mosFills = entity.getChildrenEntryPointTree();
			if (mosFills.size() != 0) {
				throw new RemoveException(
						String.format(
								Messages.getString("EntryPointServiceImpl.ChildEntryPointError"),
								puntEntrada.getName()));
			}
		}

		// Ajustem l'ordre de l'arbre del pare
		boolean mhetrobat = false;
		// Trobem els fills (estan ordenats pel camp ordre) (!!)
		Collection<EntryPointTreeEntity> arbrePare = getEntryPointTreeEntityDao()
				.findByParent(idPare);
		EntryPointTreeEntity arbreEsborrar = null;
		for (Iterator<EntryPointTreeEntity> it = arbrePare.iterator(); it
				.hasNext();) {
			EntryPointTreeEntity actual = it.next();
			if (mhetrobat) {
				int ordre = actual.getOrder() - 1;
				actual.setOrder(ordre);
			} else if (actual.getChild().getId().equals(entity.getId())) {
				mhetrobat = true;
				it.remove();
				arbreEsborrar = actual;
			}
		}

		// Actualiztem l'arbre del pare (reordenació de nodes i esborrat del pue
		// actual)
		if (arbreEsborrar != null) {
			getEntryPointTreeEntityDao().remove(arbreEsborrar);
		}
		getEntryPointTreeEntityDao().update(arbrePare);

		if (!enlazado) {
			// Ens esborrem de l'arbre
			// getArbrePuntEntradaEntityDao().remove(entity.getArbrePuntEntradaSocFill());
			// // només
			// serà
			// 1
			entity.setParentEntryPointTree(new HashSet<EntryPointTreeEntity>());
			// Actualitzem l'arbre del pare
			// getArbrePuntEntradaEntityDao().update(arbrePare);

			// AUTORITZACIONS
			Collection autoUsu = entity.getAuthorizedUsers();
			Collection autoRol = entity.getAuthorizedRoles();
			Collection autoGrup = entity.getAuthorizedGroups();
			// les esborrem
			getEntryPointUserEntityDao().remove(autoUsu);
			getEntryPointRoleEntityDao().remove(autoRol);
			getEntryPointGroupEntityDao().remove(autoGrup);
			entity.setAuthorizedUsers(null);
			entity.setAuthorizedRoles(null);
			entity.setAuthorizedGroups(null);

			// EJECUCIONS
			Collection<EntryPointExecutableEntity> execucions = entity
					.getExecutionMethod();
			getEntryPointExecutableEntityDao().remove(execucions);
			entity.setExecutionMethod(null);

			// ICONAS
			if (entity.getIcon1() != null) {
				getEntryPointIconEntityDao().remove(entity.getIcon1());
				entity.setIcon1(null);
			}
			if (entity.getIcon2() != null) {
				getEntryPointIconEntityDao().remove(entity.getIcon2());
				entity.setIcon2(null);
			}

			getHostEntryPointEntityDao().remove(entity.getHosts());
			entity.getHosts().clear();
			
			getEntryPointEntityDao().remove(entity);
			auditarPuntEntrada(
					"D", "Esborrat punt d\'entrada \'" + entity.getName() + "\'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else {
			auditarPuntEntrada(
					"D", "Esborrat enlla\u00e7 del punt d\'entrada \'" + entity.getName() + "\'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

	}

	/**
	 * @see es.caib.seycon.ng.servei.PuntEntradaService#findRoot()
	 */
	protected com.soffid.iam.api.AccessTree handleFindRoot()
			throws java.lang.Exception {

		EntryPointEntity entity = null;
		List<EntryPointEntity> entities = getEntryPointEntityDao()
				.findByCriteria("%", ROOT_TAG); //$NON-NLS-1$
		if (entities.size() > 0)
			entity = entities.get(0);
		if (entity == null) {
			EntryPointEntity root = getEntryPointEntityDao()
					.newEntryPointEntity();
			root.setCode(ROOT_TAG);
			root.setPublicAccess("S"); //$NON-NLS-1$
			InformationSystemEntity app = getInformationSystemEntityDao()
					.findByCode("SOFFID"); //$NON-NLS-1$
			root.setInformationSystem(app);
			root.setMenu("S"); //$NON-NLS-1$
			root.setName("Corporate applications"); //$NON-NLS-1$
			root.setMenuType("L"); //$NON-NLS-1$
			root.setVisible("S"); //$NON-NLS-1$
			getEntryPointEntityDao().create(root);
			return getEntryPointEntityDao().toAccessTree(root);
		} else
			return getEntryPointEntityDao().toAccessTree(entity);
	}

	/**
	 * @see es.caib.seycon.ng.servei.PuntEntradaService#findChildren(es.caib.seycon.ng.comu.PuntEntrada)
	 */
	protected java.util.Collection<AccessTree> handleFindChildren(
			com.soffid.iam.api.AccessTree puntEntrada)
			throws java.lang.Exception {
    	EntryPointEntity existingEntity = getEntryPointEntityDao().load (puntEntrada.getId());
    	if (existingEntity == null)
    		return null;
    	AccessTree existing = getEntryPointEntityDao().toAccessTree(existingEntity);
		// Comprovem autorització
		if (!canView(existingEntity)) // No donem error
			return new LinkedList<AccessTree>();// throw new
												// InternalErrorException("no autoritzat");

		Collection<EntryPointTreeEntity> arbre = existingEntity.getChildrenEntryPointTree();
		if (arbre != null && arbre.size() != 0) {// Verificamos permisos
			List<AccessTree> fills = new LinkedList<AccessTree>();
			for (Iterator<EntryPointTreeEntity> it = arbre.iterator(); it
					.hasNext();) {
				EntryPointTreeEntity a = it.next();
                if (canView(a.getChild()))
                {
	                // Només si tenim permis
	                AccessTree pue = getEntryPointEntityDao().toAccessTree(a.getChild());
	                // Establim la posició a l'arbre del punt d'entrada (per poder
	                // moure'l)
	                pue.setParentId(a.getParent().getId());
	                pue.setOrder("" + a.getOrder());		//pue.setOrdre(a.getOrdre()); //$NON-NLS-1$
	                fills.add(pue);
                }
			}
            Collections.sort(fills, new Comparator<AccessTree>() {
				public int compare(AccessTree o1, AccessTree o2) {
					return Integer.decode(o1.getOrder()).compareTo(Integer.decode(o2.getOrder()));
				}
			});
			return fills;

		}
		return new LinkedList<AccessTree>();
	}

	protected Collection<AccessTreeExecutionType> handleGetAllMimeTypeExecution()
			throws Exception {
		List<EntryPointExecutionTypeEntity> tipusMime = getEntryPointExecutionTypeEntityDao()
				.loadAll();
		if (tipusMime.isEmpty()) {
			EntryPointExecutionTypeEntity punt = getEntryPointExecutionTypeEntityDao()
					.newEntryPointExecutionTypeEntity();
			punt.setName("URL"); //$NON-NLS-1$
			punt.setTemplate("http://"); //$NON-NLS-1$
			punt.setMimeType("text/html"); //$NON-NLS-1$
			getEntryPointExecutionTypeEntityDao().create(punt);
			tipusMime.add(punt);
			punt = getEntryPointExecutionTypeEntityDao()
					.newEntryPointExecutionTypeEntity();
			punt.setName("MZN"); //$NON-NLS-1$
			punt.setTemplate("exec ( ... );"); //$NON-NLS-1$
			punt.setMimeType("x-application/x-mazinger-script"); //$NON-NLS-1$
			getEntryPointExecutionTypeEntityDao().create(punt);
			tipusMime.add(punt);
		}
		if (tipusMime.size() == 2)
		{
			EntryPointExecutionTypeEntity punt = getEntryPointExecutionTypeEntityDao()
					.newEntryPointExecutionTypeEntity();
			punt.setName("PAM"); //$NON-NLS-1$
			punt.setTemplate("url=...\nserverGroup=..."); //$NON-NLS-1$
			punt.setMimeType("Recorded session"); //$NON-NLS-1$
			getEntryPointExecutionTypeEntityDao().create(punt);
			tipusMime.add(punt);
		}
		if (tipusMime.size() == 3)
		{
			EntryPointExecutionTypeEntity punt = getEntryPointExecutionTypeEntityDao()
					.newEntryPointExecutionTypeEntity();
			punt.setName("WSSO"); //$NON-NLS-1$
			punt.setTemplate("https://"); //$NON-NLS-1$
			punt.setMimeType("Web Single sign on"); //$NON-NLS-1$
			getEntryPointExecutionTypeEntityDao().create(punt);
			tipusMime.add(punt);
		}
		return getEntryPointExecutionTypeEntityDao()
				.toAccessTreeExecutionTypeList(tipusMime); // toVO
	}

	protected Collection<Application> handleGetAllApplications(
			Boolean aplicacioBuida) throws Exception {
		// D'aquesta manera estan ordenades per nom
		Collection<InformationSystemEntity> aplicacions = getInformationSystemEntityDao()
				.findByFilter(null, null, null, null, null, null, null);
		// getAplicacioEntityDao().toAplicacioCollection(aplicacions);//toVO
		// Les transformem "manualment"
		Collection<Application> appsVO = new LinkedList<Application>();

		if (aplicacioBuida.booleanValue()) {
			// Añadimos una aplicación vacía al principio
			Application buida = new Application(); //$NON-NLS-1$

			buida.setDescription(""); //$NON-NLS-1$
			buida.setName(null); // Sense codi d'aplicació
			appsVO.add(buida);
		}
		for (Iterator<InformationSystemEntity> it = aplicacions.iterator(); it
				.hasNext();) {
			InformationSystemEntity ap = it.next();
			Application apvo = getInformationSystemEntityDao()
					.toApplication(ap);
			apvo.setDescription(apvo.getName() + " - " + apvo.getDescription());
			appsVO.add(apvo);
		}
		return appsVO;
	}

	protected Collection<AccessTreeAuthorization> handleGetAuthorizationsApplicationAcessTree(
			AccessTree puntEntrada) throws Exception {
    	EntryPointEntity entity = getEntryPointEntityDao().load(puntEntrada.getId());
    	if (canAdmin(puntEntrada))
    	{
    		return getEntryPointEntityDao().toAccessTree(entity).getAuthorizations();
    	}
    	else
    		return new LinkedList<AccessTreeAuthorization>();
	}

	protected boolean handleCanAdmin(AccessTree puntEntrada) throws Exception {
		// return authorized (entityContext, Authorization.admin);

		return isAuthorized(puntEntrada,
				TipusAutoritzacioPuntEntrada.NIVELL_ADMIN_DESCRIPCIO);
	}

	protected boolean handleCanExecute(AccessTree puntEntrada) throws Exception {
		// return acl.canQuery (entityContext) && getExecutionMethod
		// (enviroment) != null;
		return canQuery(puntEntrada) && puntEntrada.getExecutions() != null;
	}

	protected boolean handleCanQuery(AccessTree puntEntrada) throws Exception {
		// return publicAccess || authorized (entityContext,
		// Authorization.query);
		if (puntEntrada.isPublicAccess()
				|| AutoritzacionsUsuari.canQueryAllMenusIntranet()
				|| isAuthorized(puntEntrada,
						TipusAutoritzacioPuntEntrada.NIVELL_QUERY_DESCRIPCIO))
			return true;
		return false;
	}

	protected boolean handleCanView(AccessTree puntEntrada) throws Exception {
    	EntryPointEntity entity = getEntryPointEntityDao().load(puntEntrada.getId());
    	return canView (entity);
    }

    protected boolean canView(EntryPointEntity puntEntrada) throws Exception {
		 		// return visible || publicAccess || authorized (entityContext,
		// Authorization.query);
        if ("S".equals(puntEntrada.getVisible()))
        	return true;
        if ("S".equals(puntEntrada.getPublicAccess())) //$NON-NLS-1$ //$NON-NLS-2$
        	return true;
        if (AutoritzacionsUsuari.canQueryAllMenusIntranet())
        	return true;
        if (isAuthorized(puntEntrada, TipusAutoritzacioPuntEntrada.NIVELL_ALTRES))
			return true;
		return false;
	}

	protected boolean handleIsAuthorized(AccessTree puntEntrada, String nivell)
			throws Exception {
		EntryPointEntity entity = getEntryPointEntityDao().load(puntEntrada.getId());
    	return isAuthorized (entity, 
    			nivell.equals( TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)? 
    					TipusAutoritzacioPuntEntrada.NIVELL_A: 
    					TipusAutoritzacioPuntEntrada.NIVELL_ALTRES );
    }
    
    
    HashMap<Long, EntryPointCache> entryCache = new HashMap<Long, EntryPointCache>();
    
    protected boolean isAuthorized(EntryPointEntity entry, String level) throws Exception {
		 
		// Hacemos que los administradores de los menús de la
		// intranet puedan hacerlo todo (!!)
		if (AutoritzacionsUsuari.canAdminMenusIntranet())
			return true;
        PermissionsCache permisos = getCurrentAuthorizations();
        EntryPointCache cache;
        synchronized (entryCache)
        {
        	cache = entryCache.get(entry.getId());
        }
        if (cache == null || cache.timeout < System.currentTimeMillis())
        	cache = createEntryPointCache( entry);
        
        for (Long l: cache.adminaccounts)
        	if (permisos.getAccountsPUE().contains(l))
        		return true;
        
        for (Long l: cache.adminusers)
        	if (permisos.getUserId().equals(l))
        		return true;
        
        for (Long l: cache.admingroups)
        	if (permisos.getGrupsUsuariPUE().contains(l))
        		return true;
        
        for (Long l: cache.adminroles)
        	if (permisos.getRolsUsuariPUE().contains(l))
        		return true;
        
         
        if (level.equals(TipusAutoritzacioPuntEntrada.NIVELL_ALTRES))
        {
            for (Long l: cache.accounts)
            	if (permisos.getAccountsPUE().contains(l))
            		return true;
            
            for (Long l: cache.users)
            	if (permisos.getUserId() != null && permisos.getUserId().equals(l))
            		return true;
            
            for (Long l: cache.groups)
            	if (permisos.getGrupsUsuariPUE().contains(l))
            		return true;
            
            for (Long l: cache.roles)
            	if (permisos.getRolsUsuariPUE().contains(l))
            		return true;
        }
        
        return false;
                 
    }

    private void removeEntryPointCache(EntryPointEntity entry) {
        synchronized (entryCache)
        {
        	entryCache.remove(entry.getId());
        }
    }
    private EntryPointCache createEntryPointCache(EntryPointEntity entry) {
    	Long now = System.currentTimeMillis();
    	EntryPointCache c = new EntryPointCache();
    	c.timeout = now + 600000;
    	for (EntryPointAccountEntity acc: entry.getAuthorizedAccounts())
    	{
        	if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(acc.getAuthorizationlevel()))
       			c.adminaccounts.add (acc.getAccount().getId());
        	else
       			c.accounts.add (acc.getAccount().getId());
    	}
    	        		  
    	for (EntryPointGroupEntity acc: entry.getAuthorizedGroups())
    	{
        	if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(acc.getAuhtorizationLevel()))
       			c.admingroups.add (acc.getGroup().getId());
        	else
       			c.groups.add (acc.getGroup().getId());
    	}
        for (EntryPointUserEntity acc: entry.getAuthorizedUsers())
    	{
        	if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(acc.getAuthorizationLevel()))
       			c.adminusers.add (acc.getUser().getId());
        	else
       			c.users.add (acc.getUser().getId());
    	}
    	        		          
    	for (EntryPointRoleEntity acc: entry.getAuthorizedRoles())
    	{
        	if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(acc.getAuthorizationLevel()))
       			c.adminroles.add (acc.getRole().getId());
        	else
       			c.roles.add (acc.getRole().getId());
    	}

    	synchronized (entryCache)
        {
        	entryCache.put(entry.getId(), c);
        }
        return c;
	}
    	        		        		   
	private PermissionsCache getCurrentAuthorizations()
			throws InternalErrorException {
		SoffidPrincipal p = Security.getSoffidPrincipal();
        if (p == null)
        	return new PermissionsCache();
        
		PermissionsCache entry = getCache().get(getCacheKey(p));
        if (entry != null)
        	return entry;
        
        getCache().remove(getCacheKey(p));
			 
		return calculateAuthorizations(p);
	}

	private PermissionsCache calculateAuthorizations(SoffidPrincipal p)
			throws InternalErrorException {
		
		String accountName = p.getName();
		if (accountName.contains("\\"))
			accountName = accountName.substring(accountName.indexOf('\\')+1);
		
		com.soffid.iam.api.System soffid = getDispatcherService().findSoffidDispatcher();
		
		
		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(accountName, soffid.getName());
		if (acc == null)
			return new PermissionsCache();
		
		if (acc.getType() == AccountType.USER && acc.getUsers().size() == 1)
		{
			String holderGroup = p.getHolderGroup();
			GroupEntity holderGroupObj = holderGroup == null ? null : getGroupEntityDao().findByName(holderGroup);
			
			UserEntity usuari = acc.getUsers().iterator().next().getUser();

			PermissionsCache entry = new PermissionsCache();

			entry.setUserId(usuari.getId());
			
			try {
				Collection<Group> groups;
				if (holderGroupObj != null)
					groups = getUserService().getUserGroupsHierarchy(usuari.getId(), holderGroup);
				else
					groups = getUserService().getUserGroupsHierarchy(usuari.getId());
				
				if (groups != null) {
					for (Group group: groups) {
						entry.getGrupsUsuariPUE().add(group.getId());
					}
				}
			} catch (UnknownUserException e) {
			}

			Set<Long> rp = entry.getRolsUsuariPUE();
			rp.addAll(p.getRoleIds());
			
			// Get active accounts
			entry.getAccountsPUE().addAll(p.getAccountIds());
 			// Guardem les dades de l'usuari actual
			getCache().put(getCacheKey(p), entry);
			return entry;
		} else {
			PermissionsCache entry = new PermissionsCache();

			entry.setUserId(null);

			Collection<RoleGrant> grants;
			grants = getApplicationService().findEffectiveRoleGrantByAccount(acc.getId());

			if (grants != null)
			{
				for (RoleGrant grant: grants)
				{
					entry.getRolsUsuariPUE().add(grant.getRoleId());					
				}
			}
            // Get active accounts
           	entry.getAccountsPUE().add(acc.getId());
 			// Guardem les dades de l'usuari actual
			getCache().put(getCacheKey(p), entry);
			return entry;
		}
	}

	protected String getCacheKey(SoffidPrincipal p) {
		return p.getTenant()+"\\"+p.getName();
	}

	private boolean isOrigenAncestorDesti(Long origenId, Long destiId) {

		if (origenId.equals(destiId))
			return true;
		List<EntryPointTreeEntity> paresDesti = getEntryPointTreeEntityDao()
				.findByChildren(destiId);

		if (paresDesti != null) {
			for (Iterator<EntryPointTreeEntity> it = paresDesti.iterator(); it
					.hasNext();) {
				EntryPointTreeEntity arbreDesti = it.next();
				Long p = arbreDesti.getParent().getId();
				return isOrigenAncestorDesti(origenId, p);
			}
		} else
			return false; // Ok, no tiene padre

		return false; // no deberíamos llegar aquí
	}

	/*
	 * Reordena un punt d'entrada: Pot ésser moure d'una brana a una altra o
	 * reordenar (mateixa branca) El punt d'entrada origen (puntEntradaOrdenar)
	 * pot estar a la mateixa branca (menú) = REORDENAR o a una branca diferent
	 * = MOURE
	 */
	protected boolean handleReorderApplicationAccess(
			AccessTree puntEntradaOrdenar, AccessTree puntEntradaSeguent)
			throws Exception {
		// 1) Obtenim la informació del l'arbre origen
		Long idPareArbreOrigen = puntEntradaOrdenar.getParentId();
		Long idPueOrigen = puntEntradaOrdenar.getId();

		// 2) Obtenim la informació de l'arbre destí
		Long idPareArbreDesti = puntEntradaSeguent.getParentId();
		Long idPueDesti = puntEntradaSeguent.getId();

		// Verifiquem autoritzacions a l'origen i al destí
    	EntryPointEntity existingEntity1 = getEntryPointEntityDao().load (puntEntradaOrdenar.getId());
    	if (existingEntity1 == null)
    		return false;
    	AccessTree existing1 = getEntryPointEntityDao().toAccessTree(existingEntity1);
    	EntryPointEntity existingEntity2 = getEntryPointEntityDao().load (puntEntradaSeguent.getId());
    	if (existingEntity2 == null)
    		return false;
    	AccessTree existing2 = getEntryPointEntityDao().toAccessTree(existingEntity2);
		if (!canAdmin(existing1) || !canAdmin(existing2))
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAdminPermission")); //$NON-NLS-1$

		// Analitzem l'arbre dels pares del node destí per verificar que no es
		// mou
		// un node origen dintre de la seua branca
		if (isOrigenAncestorDesti(idPueOrigen, idPareArbreDesti))
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NotNodeMoviment")); //$NON-NLS-1$

		// Podem tenir 2 casos: mateix pare (REORDENAR) o pare distint (MOURE)
		// (mateix pare = ordenar l'arbre destí)
		if (idPareArbreOrigen.equals(idPareArbreDesti)) {
			// Mateixa branca, només hem de reordenar
			List<EntryPointTreeEntity> branca = getEntryPointTreeEntityDao()
					.findByParent(idPareArbreOrigen);
			// Ja està ordenada pel camp ordre (SEMPRE tindrá fills)
			if (branca != null && branca.size() != 0) {
				// És l'arbre de fills del mateix pare ordenat per Ordre
				int posOrigen = -1;
				int posDesti = -1;
				int pos = -1;
				// Obtenim la posició de l'element a moure i la nova posició
				// (destí)
				for (Iterator<EntryPointTreeEntity> it = branca.iterator(); it
						.hasNext();) {
					pos++;
					EntryPointTreeEntity actual = it.next();
					if (actual.getChild().getId().equals(idPueOrigen)) {
						posOrigen = pos;
					} else if (actual.getChild().getId().equals(idPueDesti)) {
						posDesti = pos;
					}
				}
				if (posOrigen == -1 || posDesti == -1)
					return false; // No trobats
				// Reordenem
				if (posOrigen < posDesti) { // pugem l'aplicació
					int i = -1;
					for (Iterator<EntryPointTreeEntity> it = branca.iterator(); it
							.hasNext();) {
						i++;
						EntryPointTreeEntity actual = it.next();
						if (i == posOrigen) {
							actual.setOrder(posDesti - 1);
						} else if (i < posDesti) {
							int posActual = (i - 1);
							actual.setOrder(posActual);
						}
					}
				} else { // posOrigen > posDesti : baixem l'aplicació
					int i = -1;
					for (Iterator<EntryPointTreeEntity> it = branca.iterator(); it
							.hasNext();) {
						i++;
						EntryPointTreeEntity actual = it.next();
						if (i == posOrigen) {
							actual.setOrder(posDesti);
						} else if (i > posOrigen) {
							;
						} else {
							int posActual = (i + 1);
							actual.setOrder(posActual);
						}
					}
				}
				getEntryPointTreeEntityDao().update(branca);
				return true;
			} else
				return false; // cas no possible (origen i desti mateixa branca
								// = té fills el pare)

		} else { // són de branques diferents
			List<EntryPointTreeEntity> brancaDesti = getEntryPointTreeEntityDao()
					.findByParent(idPareArbreDesti);

			// Hem de verificar que en la branca destí no existisca ja el node
			// actual [cas de que siga un enllaç]
			if (brancaDesti != null)
				for (Iterator<EntryPointTreeEntity> it = brancaDesti.iterator(); it
						.hasNext();) {
					EntryPointTreeEntity arbreActual = it.next();
					if (arbreActual.getChild().getId().equals(idPueOrigen))
						throw new InternalErrorException(
								Messages.getString("EntryPointServiceImpl.EntryPointDuplicated"));
				}

			// Creen la nova entrada a l'arbre
			EntryPointEntity nouPare = getEntryPointEntityDao().load(
					idPareArbreDesti);
			EntryPointEntity pueMogut = getEntryPointEntityDao().load(
					idPueOrigen);

			EntryPointTreeEntity nouArbre = getEntryPointTreeEntityDao()
					.newEntryPointTreeEntity();
			nouArbre.setOrder(Integer.parseInt(puntEntradaSeguent.getOrder())); // nouArbre.setOrdre(puntEntradaSeguent.getOrdre());
			nouArbre.setChild(pueMogut);
			nouArbre.setParent(nouPare);

			// Obtenim la branca origen on estava el node a moure (el pare del
			// node mogut)
			List<EntryPointTreeEntity> brancaOrigen = getEntryPointTreeEntityDao()
					.findByParent(idPareArbreOrigen);
			EntryPointTreeEntity arbreAntic = null;
			boolean trobat = false;
			for (Iterator<EntryPointTreeEntity> it = brancaOrigen.iterator(); !trobat
					&& it.hasNext();) {
				EntryPointTreeEntity a = it.next();
				if (a.getChild().getId().equals(idPueOrigen)) {
					arbreAntic = a;
					trobat = true;
				}
			}
			// Hem de reordenar les entrades de la nova branca
			if (brancaDesti != null && brancaDesti.size() != 0) { // Pot ésser
																	// que la
																	// nova
																	// branca
																	// siga
																	// buida
				// Si la seua posició es >= posDesti, sumem 1 a l'ordre
				boolean reordenar = false;
				int i = -1;
				for (Iterator<EntryPointTreeEntity> it = brancaDesti.iterator(); it
						.hasNext();) {
					EntryPointTreeEntity actual = it.next();
					i++;
					if (reordenar) {
						int posActual = i + 1;
						actual.setOrder(posActual);
					} else if (actual.getChild().getId().equals(idPueDesti)) {
						reordenar = true;
						int posActual = actual.getOrder() + 1;
						actual.setOrder(posActual);
					}
				}
			}
			if (brancaDesti == null)
				brancaDesti = new LinkedList<EntryPointTreeEntity>();

			// Fem els canvis
			// 1) Esborrem l'arbre antic
			if (arbreAntic != null)
				getEntryPointTreeEntityDao().remove(arbreAntic);
			// 2) Creem l'accés a la nova branca
			getEntryPointTreeEntityDao().create(nouArbre);
			brancaDesti.add(nouArbre);
			// 3) Actualitzem la branca destí (hem reordenat els fills de la
			// branca)
			getEntryPointTreeEntityDao().update(brancaDesti);
			// 4) Actualitzcem el menú destí
			nouPare.setParentEntryPointTree(brancaDesti);
			getEntryPointEntityDao().update(nouPare);

			return true;
		}

	}

	/*
	 * Mou un punt d'entrada d'una branca a una altra El puntEntradaMenuDesti ha
	 * de ser de tipus menú, i el punt d'entrada origen pot ésser menú o de
	 * tipus acció i es posa a la darrera posició del menú destí
	 * (puntEtnradaMenuDesti)
	 */
	protected boolean handleMoveApplicationAccessTreeMenu(
			AccessTree puntEntradaMoure, AccessTree puntEntradaMenuDesti)
			throws Exception {// NOTA: El
		// destino
		// SIEMPRE es un
		// menú
		// 1) Verifiquem que el destí siga de tipus menú
		if (!puntEntradaMenuDesti.isMenu()) //$NON-NLS-1$
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.EntryPointTypeError")); //$NON-NLS-1$

		// 2) Verifiquem autoritzacions: origen, desti i pare del destí
		Long idParePuntEntradaMoure = puntEntradaMoure.getParentId();
		Long idPueOrigen = puntEntradaMoure.getId();
		Long idPueDesti = puntEntradaMenuDesti.getId();
		Long idParePuntEntradaDesti = (!ROOT_TAG.equals(puntEntradaMenuDesti.getCode())) ?
						       		puntEntradaMenuDesti.getParentId() : puntEntradaMenuDesti.getId();
		if (idPueOrigen == null || idParePuntEntradaMoure == null
				|| idPueDesti == null || idParePuntEntradaDesti == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.EntryPointConfirmChanges")); //$NON-NLS-1$

		// Analitzem l'arbre dels pares del node destí per verificar que no es
		// mou
		// un node origen dintre de la seua branca
		if (isOrigenAncestorDesti(idPueOrigen, idPueDesti))
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NotNodeMoviment")); //$NON-NLS-1$

		EntryPointEntity pareOrigenE = getEntryPointEntityDao().load(
				idParePuntEntradaMoure);
		AccessTree pareOrigen = getEntryPointEntityDao().toAccessTree(
				pareOrigenE);
		// PuntEntradaEntity pareDestiE =
		// getPuntEntradaEntityDao().load(idParePuntEntradaDesti);
		// PuntEntrada pareDesti =
		// getPuntEntradaEntityDao().toPuntEntrada(pareDestiE);
		// Tiene que tener permisos en el punto de entrada origen y destino (es
		// menú)
    	EntryPointEntity existingEntity1 = getEntryPointEntityDao().load (puntEntradaMoure.getId());
    	if (existingEntity1 == null)
    		return false;
    	AccessTree existing1 = getEntryPointEntityDao().toAccessTree(existingEntity1);
    	EntryPointEntity existingEntity2 = getEntryPointEntityDao().load (puntEntradaMenuDesti.getId());
    	if (existingEntity2 == null)
    		return false;
    	AccessTree existing2 = getEntryPointEntityDao().toAccessTree(existingEntity2);
		if (!canAdmin(existing1) || !canAdmin(existing2))
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NotAuthorizedToMoveEntryPoint")); //$NON-NLS-1$

		// Si el origen NO es menú tiene que tener permisos en el menú
		// contenedor padre del origen (para mover)
		if (!puntEntradaMoure.isMenu() && !canAdmin(pareOrigen)) //$NON-NLS-1$
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NotAuthorizedToMoveEntryPointNoPermission")); //$NON-NLS-1$

		// Obtenim l'arbre del punt d'entrada origen i destí
		List<EntryPointTreeEntity> brancaOrigen = getEntryPointTreeEntityDao()
				.findByParent(idParePuntEntradaMoure);
		EntryPointTreeEntity arbreAntic = null;
		int pos = 0; // per reindexar elements (ja estàn ordenats pel camp
						// ordre)
		for (Iterator<EntryPointTreeEntity> it = brancaOrigen.iterator(); it
				.hasNext();) {
			EntryPointTreeEntity a = it.next();
			if (a.getChild().getId().equals(idPueOrigen)) {
				arbreAntic = a;
				it.remove();
			} else {
				a.setOrder(pos);
				pos++;
			}
		}
		// Creen la nova entrada a l'arbre destí
		EntryPointEntity nouPare = getEntryPointEntityDao()
				.load(idPueDesti);
		EntryPointEntity pueMogut = getEntryPointEntityDao().load(
				idPueOrigen);

		// Obtenim L'ORDRE DE L'ARBRE destí
		String ordre = "0"; //$NON-NLS-1$
		Collection<EntryPointTreeEntity> fillsDesti = getEntryPointTreeEntityDao()
				.findByParent(idPueDesti);
		if (fillsDesti != null) {// Ens quedem en el fill de major ordre
			if (fillsDesti.size() == 0) // Para nodes menú sense fills
				ordre = "0"; //$NON-NLS-1$
			else { // Obtenim el seu fill "major" (de tipus List i estan
					// ordenats per query en ordre ascendent)
				EntryPointTreeEntity fill = ((List<EntryPointTreeEntity>) fillsDesti)
						.get(fillsDesti.size() - 1);
				int ordreFillMajor = fill.getOrder(); // int ordreFillMajor =
														// Integer.parseInt(fill.getOrdre());
				ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
			}
		}

		// Creem el accés al punt d'entrada mogut
		EntryPointTreeEntity nouArbre = getEntryPointTreeEntityDao()
				.newEntryPointTreeEntity();
		nouArbre.setOrder(Integer.parseInt(ordre)); // nouArbre.setOrdre(ordre);
		nouArbre.setChild(pueMogut);
		nouArbre.setParent(nouPare);

		// 1) Esborrem l'arbre antic
		if (arbreAntic != null)
			getEntryPointTreeEntityDao().remove(arbreAntic);
		// 2) Creem l'accés a la nova branca
		getEntryPointTreeEntityDao().create(nouArbre);
		if (fillsDesti == null) {
			fillsDesti = new HashSet<EntryPointTreeEntity>();
		}
		fillsDesti.add(nouArbre);
		nouPare.setParentEntryPointTree(new HashSet<EntryPointTreeEntity>(
				fillsDesti));
		// 3) Actualitzem la branca destí (hem afegit un fill)
		getEntryPointEntityDao().update(nouPare);

		// 4) Actualitzem la branca origen (hem mogut un fill)
		getEntryPointTreeEntityDao().update(brancaOrigen);
		return true;
	}

	private EntryPointEntity clonaPuntEntrada(EntryPointEntity pueClonar)
			throws InternalErrorException {
		EntryPointEntity nouPUEClonat = null;

		// Copiamos iconos
		Long icona1 = null;
		Long icona2 = null;

		// Creamos la copia y obtenemos el id de la misma
		if (pueClonar.getIcon1() != null) {
			EntryPointIconEntity ie = getEntryPointIconEntityDao().load(
					pueClonar.getIcon1()); // se
			// guarda
			// id
			// del
			// icono
			// en
			// el
			// pue
			if (ie != null) {
				EntryPointIconEntity icona = createIcona(ie.getIcon());
				icona1 = icona.getId();
			}
		}
		if (pueClonar.getIcon2() != null) {
			EntryPointIconEntity ie = getEntryPointIconEntityDao().load(
					pueClonar.getIcon2()); // se
			// guarda
			// id
			// del
			// icono
			// en
			// el
			// pue
			if (ie != null) {
				EntryPointIconEntity icona = createIcona(ie.getIcon());
				icona2 = icona.getId();
			}
		}
		// Creamos el nuevo punto de entrada
		nouPUEClonat = getEntryPointEntityDao().newEntryPointEntity();
		nouPUEClonat.setCode(pueClonar.getCode());
		nouPUEClonat.setName(pueClonar.getName());
		nouPUEClonat.setVisible(pueClonar.getVisible());
		nouPUEClonat.setMenu(pueClonar.getMenu());
		nouPUEClonat.setNumberOfColumns(pueClonar.getNumberOfColumns());
		nouPUEClonat.setPublicAccess(pueClonar.getPublicAccess());
		nouPUEClonat.setMenuType(pueClonar.getMenuType());
		nouPUEClonat.setPublicAccess(pueClonar.getPublicAccess());
		nouPUEClonat.setIcon1(icona1);
		nouPUEClonat.setIcon2(icona2);
		nouPUEClonat.setInformationSystem(pueClonar.getInformationSystem());
		nouPUEClonat.setXmlEntryPoint(pueClonar.getXmlEntryPoint());
		getEntryPointEntityDao().create(nouPUEClonat);

		// Clonamos sus AUTORIZACIONES
		Collection<EntryPointRoleEntity> _autoRol = pueClonar
				.getAuthorizedRoles();
		Collection<EntryPointGroupEntity> _autoGrup = pueClonar
				.getAuthorizedGroups();
		Collection<EntryPointUserEntity> _autoUsu = pueClonar
				.getAuthorizedUsers();
		Collection autoRol = new HashSet(), autoGrup = new HashSet(), autoUsu = new HashSet();
		// ROL
		if (_autoRol != null && _autoRol.size() != 0) {
			for (Iterator<EntryPointRoleEntity> it = _autoRol.iterator(); it
					.hasNext();) {
				EntryPointRoleEntity auto = (EntryPointRoleEntity) it.next();
				EntryPointRoleEntity apu = getEntryPointRoleEntityDao()
						.newEntryPointRoleEntity();
				apu.setRole(auto.getRole());
				apu.setAuthorizationLevel(auto.getAuthorizationLevel());
				apu.setEntryPoint(nouPUEClonat);
				getEntryPointRoleEntityDao().create(apu);
				autoRol.add(apu);
			}
		}
		nouPUEClonat.setAuthorizedRoles(autoRol);
		// GRUP
		if (_autoGrup != null && _autoGrup.size() != 0) {
			for (Iterator<EntryPointGroupEntity> it = _autoGrup.iterator(); it
					.hasNext();) {
				EntryPointGroupEntity auto = (EntryPointGroupEntity) it.next();
				EntryPointGroupEntity apu = getEntryPointGroupEntityDao()
						.newEntryPointGroupEntity();
				apu.setGroup(auto.getGroup());
				apu.setAuhtorizationLevel(auto.getAuhtorizationLevel());
				apu.setEntryPoint(nouPUEClonat);
				getEntryPointGroupEntityDao().create(apu);
				autoGrup.add(apu);
			}
		}
		nouPUEClonat.setAuthorizedGroups(autoGrup);
		// USUARI
		if (_autoUsu != null && _autoUsu.size() != 0) {
			for (Iterator<EntryPointUserEntity> it = _autoUsu.iterator(); it
					.hasNext();) {
				EntryPointUserEntity auto = (EntryPointUserEntity) it.next();
				EntryPointUserEntity apu = getEntryPointUserEntityDao()
						.newEntryPointUserEntity();
				apu.setUser(auto.getUser());
				apu.setAuthorizationLevel(auto.getAuthorizationLevel());
				apu.setEntryPoint(nouPUEClonat);
				getEntryPointUserEntityDao().create(apu);
				autoUsu.add(apu);
			}
		}
		nouPUEClonat.setAuthorizedUsers(autoUsu);
		// Clonamos sus EJECUCIONES:
		Collection _execs = pueClonar.getExecutionMethod();
		Collection<EntryPointExecutableEntity> execs = new HashSet<EntryPointExecutableEntity>();

		if (_execs != null && _execs.size() != 0) {
			for (Iterator it = _execs.iterator(); it.hasNext();) {
				EntryPointExecutableEntity exe = (EntryPointExecutableEntity) it
						.next();
				EntryPointExecutableEntity nou = getEntryPointExecutableEntityDao()
						.newEntryPointExecutableEntity();
				nou.setScope(exe.getScope());
				nou.setExecutionCode(exe.getExecutionCode());
				nou.setContent(exe.getContent());
				nou.setEntryPoint(nouPUEClonat);
				getEntryPointExecutableEntityDao().create(nou);
				execs.add(nou);
			}
		}
		nouPUEClonat.setExecutionMethod(execs);

		// Clonamos los HIJOS si los tiene:
		Collection _fills = pueClonar.getChildrenEntryPointTree();
		Collection<EntryPointTreeEntity> fills = new HashSet<EntryPointTreeEntity>();
		if (_fills != null && _fills.size() != 0) {
			for (Iterator it = _fills.iterator(); it.hasNext();) {
				EntryPointTreeEntity actual = (EntryPointTreeEntity) it.next();
				EntryPointEntity monFill = clonaPuntEntrada(actual
						.getChild());
				EntryPointTreeEntity arbre = getEntryPointTreeEntityDao()
						.newEntryPointTreeEntity();
				arbre.setChild(monFill);
				arbre.setOrder(actual.getOrder());
				arbre.setParent(nouPUEClonat);
				getEntryPointTreeEntityDao().create(arbre);
				fills.add(arbre);
			}
		}
		nouPUEClonat.setParentEntryPointTree(fills);
		// Actualizamos los cambios (árbol, ejecuciones, autorizaciones)
		getEntryPointEntityDao().update(nouPUEClonat);

		return nouPUEClonat;
	}

	protected boolean handleCopyApplicationAccess(AccessTree puntEntradaCopiar,
			AccessTree puntEntradaMenuDesti) throws Exception {
		// 1) Verifiquem que el destí siga de tipus menú
		if (!puntEntradaMenuDesti.isMenu()) //$NON-NLS-1$
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.EntryPointTypeError")); //$NON-NLS-1$

		// 2) Verifiquem autoritzacions: origen, desti i pare del destí
		Long idParePuntEntradaClonar = puntEntradaCopiar.getParentId();
		Long idPueOrigen = puntEntradaCopiar.getId();
		Long idPueDesti = puntEntradaMenuDesti.getId();
		Long idParePuntEntradaDesti = (!ROOT_TAG.equals(puntEntradaMenuDesti.getCode())) ?
				    		puntEntradaMenuDesti.getParentId() : puntEntradaMenuDesti.getId();
		if (idPueOrigen == null || idParePuntEntradaClonar == null
				|| idPueDesti == null || idParePuntEntradaDesti == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.CopyEntryPointConfirmChanges")); //$NON-NLS-1$

		// Analitzem l'arbre dels pares del node destí per verificar que no es
		// mou
		// un node origen dintre de la seua branca
		if (isOrigenAncestorDesti(idPueOrigen, idPueDesti))
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NodeCopyError")); //$NON-NLS-1$

		// Obtenim l'entitat a clonar
		EntryPointEntity pueClonar = getEntryPointEntityDao()
				.accessTreeToEntity(puntEntradaCopiar);
		// Fem una còpia (iconas, autoritzacions, execucions, fills)
		EntryPointEntity nouPUEClonat = clonaPuntEntrada(pueClonar);

		// Hem d'afegir el nou fill clonat al pue destí
		String ordre = "0"; //$NON-NLS-1$
		Collection fillsDesti = getEntryPointTreeEntityDao().findByParent(
				idPueDesti);
		if (fillsDesti != null) {// Ens quedem en el fill de major ordre
			if (fillsDesti.size() == 0) // Para nodes menú sense fills
				ordre = "0"; //$NON-NLS-1$
			else { // Obtenim el seu fill "major" (de tipus List i estan
					// ordenats per query en ordre ascendent)
				EntryPointTreeEntity fill = (EntryPointTreeEntity) ((List) fillsDesti)
						.get(fillsDesti.size() - 1);
				int ordreFillMajor = fill.getOrder(); // Integer.parseInt(fill.getOrdre());
				ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
			}
		}
		// Obtenim el pare
		EntryPointEntity pueDesti = getEntryPointEntityDao().load(
				idPueDesti);
		Collection<EntryPointTreeEntity> fillsNouPare = pueDesti
				.getChildrenEntryPointTree();

		EntryPointTreeEntity nouArbre = getEntryPointTreeEntityDao()
				.newEntryPointTreeEntity();
		nouArbre.setChild(nouPUEClonat);
		nouArbre.setOrder(Integer.parseInt(ordre)); // nouArbre.setOrdre(ordre);
		nouArbre.setParent(pueDesti);
		getEntryPointTreeEntityDao().create(nouArbre);
		fillsNouPare.add(nouArbre);
		pueDesti.setParentEntryPointTree(fillsNouPare);
		// Actualitzem el pare (hem modificat el seu arbre)
		getEntryPointEntityDao().update(pueDesti);

		return true;
	}

	protected boolean handleCopyApplicationAccessLink(
			AccessTree puntEntradaCopiar, AccessTree puntEntradaMenuDesti)
			throws Exception {// NOTA: El
		// destino
		// SIEMPRE es un
		// menú
		// 1) Verifiquem que el destí siga de tipus menú
		if (!puntEntradaMenuDesti.isMenu()) //$NON-NLS-1$
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.EntryPointTypeError")); //$NON-NLS-1$

		// 2) Verifiquem autoritzacions: origen, desti i pare del destí
		Long idParePuntEntradaCopiar = puntEntradaCopiar.getParentId();
		Long idPueOrigen = puntEntradaCopiar.getId();
		Long idPueDesti = puntEntradaMenuDesti.getId();
		Long idParePuntEntradaDesti = (!ROOT_TAG.equals(puntEntradaMenuDesti.getCode())) ? 
				        		puntEntradaMenuDesti.getParentId() : puntEntradaMenuDesti.getId();
		if (idPueOrigen == null || idParePuntEntradaCopiar == null
				|| idPueDesti == null || idParePuntEntradaDesti == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.CopyEntryPointConfirmChanges")); //$NON-NLS-1$

		// Analitzem l'arbre dels pares del node destí per verificar que no es
		// mou
		// un node origen dintre de la seua branca
		if (isOrigenAncestorDesti(idPueOrigen, idPueDesti))
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NodeCopyError")); //$NON-NLS-1$

		// Tiene que tener permisos en el punto de entrada origen y destino (es
		// menú)
    	EntryPointEntity existingEntity1 = getEntryPointEntityDao().load (puntEntradaCopiar.getId());
    	if (existingEntity1 == null)
    		return false;
    	AccessTree existing1 = getEntryPointEntityDao().toAccessTree(existingEntity1);
    	EntryPointEntity existingEntity2 = getEntryPointEntityDao().load (puntEntradaMenuDesti.getId());
    	if (existingEntity2 == null)
    		return false;
    	AccessTree existing2 = getEntryPointEntityDao().toAccessTree(existingEntity2);
		if (!canAdmin(existing1) || !canAdmin(existing2))
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAuthorizedToCopyEntryPoint")); //$NON-NLS-1$

		EntryPointEntity pareOrigenE = getEntryPointEntityDao().load(
				idParePuntEntradaCopiar);
		AccessTree pareOrigen = getEntryPointEntityDao().toAccessTree(
				pareOrigenE);
		// Si el origen NO es menú tiene que tener permisos en el menú
		// contenedor padre del origen (para copiar)
		if (!puntEntradaCopiar.isMenu() && !canAdmin(pareOrigen)) //$NON-NLS-1$
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAuthorizedToCopyEntryPointNoPermission")); //$NON-NLS-1$

		// Verificamos que no exista ya en el destino una copia del mismo
		Collection<EntryPointTreeEntity> fillsDesti = getEntryPointTreeEntityDao()
				.findByParent(idPueDesti);

		if (fillsDesti != null)
			for (Iterator<EntryPointTreeEntity> it = fillsDesti.iterator(); it
					.hasNext();) {
				EntryPointTreeEntity arbreActual = it.next();
				if (arbreActual.getChild().getId().equals(idPueOrigen))
					throw new InternalErrorException(
							Messages.getString("EntryPointServiceImpl.EntryPointDuplicated"));
			}

		// Obtenim L'ORDRE DE L'ARBRE destí (estan ordenats per ordre ascendent)
		String ordre = "0"; //$NON-NLS-1$
		if (fillsDesti != null) {// Ens quedem en el fill de major ordre
			if (fillsDesti.size() == 0) // Para nodes menú sense fills
				ordre = "0"; //$NON-NLS-1$
			else { // Obtenim el seu fill "major" (es de tipus List ordenat)
				EntryPointTreeEntity fill = ((List<EntryPointTreeEntity>) fillsDesti)
						.get(fillsDesti.size() - 1);
				int ordreFillMajor = fill.getOrder(); // int ordreFillMajor =
														// Integer.parseInt(fill.getOrdre());
				ordre = "" + (ordreFillMajor + 1); //$NON-NLS-1$
			}
		}

		// Creamos una copia del árbol en el menú destino:
		EntryPointEntity nouPare = getEntryPointEntityDao()
				.load(idPueDesti);
		EntryPointEntity pueCopiat = getEntryPointEntityDao().load(
				idPueOrigen);

		// Creen la nova entrada a l'arbre destí
		EntryPointTreeEntity nouArbre = getEntryPointTreeEntityDao()
				.newEntryPointTreeEntity();
		nouArbre.setChild(pueCopiat);
		nouArbre.setOrder(Integer.parseInt(ordre)); // nouArbre.setOrdre(ordre);
		nouArbre.setParent(nouPare);
		getEntryPointTreeEntityDao().create(nouArbre);

		// 1) Creem l'accés a la branca destí
		if (fillsDesti == null) {
			fillsDesti = new HashSet<EntryPointTreeEntity>();
		}
		fillsDesti.add(nouArbre);
		nouPare.setParentEntryPointTree(new HashSet<EntryPointTreeEntity>(
				fillsDesti));

		// 2) Actualitzem el menú destí (hem afegit un fill)
		getEntryPointEntityDao().update(nouPare);

		return true;
	}

	protected AccessTreeAuthorization handleCreateAuthorization(
			AccessTree puntEntrada, AccessTreeAuthorization autoritzacio)
			throws Exception {

		EntryPointEntity puntEntradaE = null;
		Long idEntitat = null;

		if (puntEntrada == null || puntEntrada.getId() == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$
		else {
			puntEntradaE = getEntryPointEntityDao().accessTreeToEntity(
					puntEntrada);
		}

		if (autoritzacio.getAuthorizationEntityId() == null)
			idEntitat = guessGranteeId(autoritzacio);
		else
			idEntitat = autoritzacio.getAuthorizationEntityId();

		// Verificamos autoritzación
		if (!isAuthorized(puntEntrada,
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAuthorizedToMakeAutoritations")); //$NON-NLS-1$
		}

		// Obtenemos el nivel de autorización
		String nivell = null;
		if (autoritzacio.getAuthorizationLevelDescription().equals(
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
			nivell = TipusAutoritzacioPuntEntrada.NIVELL_A;
		} else
			nivell = TipusAutoritzacioPuntEntrada.NIVELL_ALTRES;

		// Creamos la autorización
		String tipusAutoritzacio = autoritzacio.getAuthorizationEntityType();
		if (TipusAutoritzacioPuntEntrada.ROL.equals(tipusAutoritzacio)) {
			// ROL: Creamos autorización
			EntryPointRoleEntity autoRol = getEntryPointRoleEntityDao()
					.newEntryPointRoleEntity();
			autoRol.setAuthorizationLevel(nivell);
			autoRol.setRole(getRoleEntityDao().load(idEntitat));
			autoRol.setEntryPoint(puntEntradaE);
			getEntryPointRoleEntityDao().create(autoRol);

			auditarAutoritzacioPuntEntrada("C", tipusAutoritzacio,
					autoritzacio.getAuthorizationLevelDescription() + " - "
							+ autoritzacio.getAuthorizedEntityDescription()
							+ " - " + puntEntrada.getName(),
					null, null, autoRol.getRole().getName(),
					autoRol.getRole().getSystem().getName(),
					null);

			return getEntryPointRoleEntityDao().toAccessTreeAuthorization(
					autoRol);
		} else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipusAutoritzacio)) {
			// GRUP: Creamos autorización
			EntryPointGroupEntity autoGrup = getEntryPointGroupEntityDao()
					.newEntryPointGroupEntity();
			autoGrup.setAuhtorizationLevel(nivell);
			autoGrup.setGroup(getGroupEntityDao().load(idEntitat));
			autoGrup.setEntryPoint(puntEntradaE);
			getEntryPointGroupEntityDao().create(autoGrup);

			auditarAutoritzacioPuntEntrada("C", tipusAutoritzacio,
					autoritzacio.getAuthorizationLevelDescription() + " - "
							+ autoritzacio.getAuthorizedEntityDescription()
							+ " - " + puntEntrada.getName(),
						autoGrup.getGroup().getName(),
						null, null, null, null);

			return getEntryPointGroupEntityDao().toAccessTreeAuthorization(
					autoGrup);
        } else if (TipusAutoritzacioPuntEntrada.ACCOUNT.equals(tipusAutoritzacio)) {
            // GRUP: Creamos autorización
        	EntryPointAccountEntity autoAccount =
            		getEntryPointAccountEntityDao(). 
            			newEntryPointAccountEntity();
        	autoAccount.setAuthorizationlevel(nivell);
        	autoAccount.setAccount(getAccountEntityDao().load(idEntitat));
        	autoAccount.setEntryPoint(puntEntradaE);
            getEntryPointAccountEntityDao().create(autoAccount);

            auditarAutoritzacioPuntEntrada(
                    "C", //$NON-NLS-1$
                    tipusAutoritzacio,
                    autoritzacio.getAuthorizedEntityDescription() + " - " //$NON-NLS-1$
                            + autoritzacio.getAuthorizedEntityDescription() + " - " //$NON-NLS-1$
                            + puntEntrada.getName(),
                            null,
                            autoAccount.getAccount().getName(),
                            null,
                            autoAccount.getAccount().getSystem().getName(),
                            null);

            return getEntryPointAccountEntityDao().toAccessTreeAuthorization(autoAccount);
 		} else if (TipusAutoritzacioPuntEntrada.USUARI
				.equals(tipusAutoritzacio)) {
			// USUARI: Creamos autorización
			EntryPointUserEntity autoUsu = getEntryPointUserEntityDao()
					.newEntryPointUserEntity();
			autoUsu.setAuthorizationLevel(nivell);
			autoUsu.setUser(getUserEntityDao().load(idEntitat));
			autoUsu.setEntryPoint(puntEntradaE);
			getEntryPointUserEntityDao().create(autoUsu);

			auditarAutoritzacioPuntEntrada("C", tipusAutoritzacio,
					autoritzacio.getAuthorizationLevelDescription() + " - "
							+ autoritzacio.getAuthorizedEntityDescription()
							+ " - " + puntEntrada.getName(),
							null, null, null, null,
							autoUsu.getUser().getUserName());

			return getEntryPointUserEntityDao().toAccessTreeAuthorization(
					autoUsu);
		}

		return null;
	}

	public Long guessGranteeId(AccessTreeAuthorization autoritzacio) throws InternalErrorException {
		Long idEntitat = null;
		String grantee = autoritzacio.getAuthorizedEntityCode();
		if ( "user". equals(autoritzacio.getAuthorizationEntityType()))
		{
			UserEntity user = getUserEntityDao().findByUserName(grantee);
			if (user == null)
				throw new InternalErrorException(
						Messages.getString("EntryPointServiceImpl.NoAssignedEntityID")); //$NON-NLS-1$
			idEntitat = user.getId();
		}
		else if ( "role". equals(autoritzacio.getAuthorizationEntityType()))
		{
			RoleEntity role = getRoleEntityDao().findByShortName(grantee);
			if (role == null)
				throw new InternalErrorException(
						Messages.getString("EntryPointServiceImpl.NoAssignedEntityID")); //$NON-NLS-1$
			idEntitat = role.getId();
		}
		else if ( "account". equals(autoritzacio.getAuthorizationEntityType()))
		{
			int i = grantee.lastIndexOf('@');
			String name = i > 0 ? grantee.substring(0, i): grantee;
			String system = i > 0 ? grantee.substring(i+1): getDispatcherService().findSoffidDispatcher().getName();
			AccountEntity account = getAccountEntityDao().findByNameAndSystem(name, system);
			if (account == null)
				throw new InternalErrorException(
						Messages.getString("EntryPointServiceImpl.NoAssignedEntityID")); //$NON-NLS-1$
			idEntitat = account.getId();
		}
		else if ( "group". equals(autoritzacio.getAuthorizationEntityType()))
		{
			GroupEntity group = getGroupEntityDao().findByName(grantee);
			if (group == null)
				throw new InternalErrorException(
						Messages.getString("EntryPointServiceImpl.NoAssignedEntityID")); //$NON-NLS-1$
			idEntitat = group.getId();
		} else {
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAssignedEntityID")); //$NON-NLS-1$
		}
		return idEntitat; 
	}

	protected void handleDeleteAuthorization(AccessTree puntEntrada,
			AccessTreeAuthorization autoritzacio) throws Exception {

		if (puntEntrada == null || puntEntrada.getId() == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$

		// Verificamos autoritzación
		if (!isAuthorized(puntEntrada,
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAuthorizedToDeleteAuthoritations")); //$NON-NLS-1$
		}

		if (autoritzacio.getId() == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NullAuthoritationID")); // Cas //$NON-NLS-1$
		// de
		// noves??

		// Borramos la autorización
		String tipusAutoritzacio = autoritzacio.getAuthorizationEntityType();
		String role = null, group = null, account = null, user = null, system = null;
		if (TipusAutoritzacioPuntEntrada.ROL.equals(tipusAutoritzacio)) {
			EntryPointRoleEntity e = getEntryPointRoleEntityDao().load(autoritzacio.getId());
			role = e.getRole().getName();
			system = e.getRole().getSystem().getName();
			getEntryPointRoleEntityDao().remove(e);
		} else if (TipusAutoritzacioPuntEntrada.GRUP.equals(tipusAutoritzacio)) {
			EntryPointGroupEntity e = getEntryPointGroupEntityDao().load(autoritzacio.getId());
			group = e.getGroup().getName();
			getEntryPointGroupEntityDao().remove(autoritzacio.getId());
		} else if (TipusAutoritzacioPuntEntrada.USUARI
				.equals(tipusAutoritzacio)) {
			EntryPointUserEntity e = getEntryPointUserEntityDao().load(autoritzacio.getId());
			user = e.getUser().getUserName();
			getEntryPointUserEntityDao().remove(e);
        } else if (TipusAutoritzacioPuntEntrada.ACCOUNT.equals(tipusAutoritzacio)) {
			EntryPointAccountEntity e = getEntryPointAccountEntityDao().load(autoritzacio.getId());
			account = e.getAccount().getName();
			system = e.getAccount().getSystem().getName();
            getEntryPointAccountEntityDao().remove(autoritzacio.getId());
		}

		auditarAutoritzacioPuntEntrada("D", tipusAutoritzacio,
				autoritzacio.getAuthorizationLevelDescription() + " - "
						+ autoritzacio.getAuthorizedEntityDescription() + " - "
						+ puntEntrada.getName(),
						group, account, role, system, user);

	}

	protected AccessTreeExecution handleCreateExecution(AccessTree puntEntrada,
			AccessTreeExecution execucio) throws Exception {
		if (puntEntrada == null || puntEntrada.getId() == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$

		execucio.setAccessTreeId(puntEntrada.getId()); // Guardem id del punt
														// d'entrada
		// Verificamos autoritzación
		if (!isAuthorized(puntEntrada,
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAuthorizedToMakeExecutionMethods")); //$NON-NLS-1$
		}

		// Les aplicacions de tipus menu no tenen execucions
		if (puntEntrada.isMenu()) { //$NON-NLS-1$
			return execucio; // No la creem
		}

		EntryPointExecutableEntity entity = getEntryPointExecutableEntityDao()
				.accessTreeExecutionToEntity(execucio);
		getEntryPointExecutableEntityDao().create(entity);

		updateImage(puntEntrada, entity);
		
		auditarExecucioPuntEntrada(
				"C",
				puntEntrada.getName() + " tipus "
						+ execucio.getTypeMimeExecution() + " ambit "
						+ execucio.getScope());

		return getEntryPointExecutableEntityDao().toAccessTreeExecution(entity);
	}

	private void updateImage(AccessTree entryPoint, EntryPointExecutableEntity entity) {
		if (entity.getExecutionCode().equals("URL") &&
				entity.getEntryPoint().getIcon1() == null) {
			try {
				String icon = guessIconName(entity);
				if (icon != null) {
					byte[] data = downloadIcon(icon);
					if (data == null)
						data = downloadDefaultIcon(entity);
					if (data != null) {
						EntryPointIconEntity ico = getEntryPointIconEntityDao().newEntryPointIconEntity();
						ico.setIcon(data);
						getEntryPointIconEntityDao().create(ico);
						entity.getEntryPoint().setIcon1(ico.getId());
						getEntryPointEntityDao().update(entity.getEntryPoint());
						entryPoint.setIcon1Id(ico.getId());
						entryPoint.setIcon1Image(data);
					}
				}
			} catch (Exception e) {
				
			}
		}
	}

	public String guessIconName(EntryPointExecutableEntity entity)
			throws IOException, MalformedURLException, UnsupportedEncodingException {
		String icon = null;
		try {
			URL url = new URL(entity.getContent().trim());
			icon = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/favicon.ico").toString();

			HttpURLConnection.setFollowRedirects(true);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream in = conn.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			for (int i = in.read(); i >= 0; i = in.read())
				out.write(i);
			in.close();
			out.close();
			conn.disconnect();
			String ct = conn.getContentType();
			if (!ct.startsWith("text/html")) return icon;
			
			String enc = "UTF-8";
			if (ct.contains("charset=")) {
				enc = ct.substring(ct.indexOf("charset=") + 8);
				enc = enc.replaceAll("\"", "");
			}
			String s = new String( out.toByteArray(), enc);
			Pattern p = Pattern.compile("<link([^>]*)>");
			Pattern prel = Pattern.compile("rel=(\"([^\"]*)\"|'([^']*)')");
			Pattern phref = Pattern.compile("href=(\"([^\"]*)\"|'([^']*)')");
			Matcher m = p.matcher(s);
			while (m.find()) {
				String link = m.group(1);
				Matcher m2 = prel.matcher(link);
				if (m2.find()) {
					String tag = m2.group(2) == null ? m2.group(3): m2.group(2);
					if (tag.contains("icon")) {
						Matcher m3 = phref.matcher(link);
						if (m3.find()) {
							if (m3.group(2) != null) icon = m3.group(2);
							if (m3.group(3) != null) icon = m3.group(3);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
		return icon;
	}


	public byte[] downloadDefaultIcon (EntryPointExecutableEntity entity)
			throws IOException, MalformedURLException, UnsupportedEncodingException {
		String icon = null;
		try {
			URL url = new URL(entity.getContent().trim());
			icon = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/favicon.ico").toString();
			return downloadIcon(icon);
		} catch (Exception e) {
			return null;
		}
	}

	public byte[] downloadIcon(String icon)
			throws IOException, MalformedURLException, UnsupportedEncodingException {
		try {
			URL url = new URL(icon);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setFollowRedirects(true);
			conn.connect();
			InputStream in = conn.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			for (int i = in.read(); i >= 0; i = in.read())
				out.write(i);
			in.close();
			out.close();
			conn.disconnect();
			String ct = conn.getContentType();
			if (ct.startsWith("image")) return out.toByteArray();
		} catch (Exception e) {
			
		}
		return null;
	}

	protected void handleDeleteExecution(AccessTree puntEntrada,
			AccessTreeExecution execucio) throws Exception { // Necesari?? Ja és
																// fa al punt
																// d'entrada
		if (puntEntrada == null || puntEntrada.getId() == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAssignedEntryPoint")); //$NON-NLS-1$

		// Verificamos autoritzación
		if (!isAuthorized(puntEntrada,
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAuthorizedToDeleteExecutionMethods")); //$NON-NLS-1$
		}

		if (execucio.getId() == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NullExecutionID")); // Cas //$NON-NLS-1$
		// de
		// noves??

		// Transformem a Entity
		EntryPointExecutableEntity entity = getEntryPointExecutableEntityDao()
				.accessTreeExecutionToEntity(execucio);
		getEntryPointExecutableEntityDao().remove(entity);

		auditarExecucioPuntEntrada(
				"D",
				puntEntrada.getName() + " tipus "
						+ execucio.getTypeMimeExecution() + " ambit "
						+ execucio.getScope());

	}

	protected AccessTreeExecution handleUpdateExecution(AccessTree puntEntrada,
			AccessTreeExecution execucio) throws Exception {
		if (puntEntrada == null || puntEntrada.getId() == null)
			return handleCreateExecution(puntEntrada, execucio);

		// Verificamos autoritzación
		if (!isAuthorized(puntEntrada,
				TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO)) {
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NoAuthorizedToUpdateExecMethods")); //$NON-NLS-1$
		}

		if (execucio.getId() == null)
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.NullExecutionID")); // Cas //$NON-NLS-1$
		// de
		// noves??

		// Transformem a Entity
		EntryPointExecutableEntity entity = getEntryPointExecutableEntityDao()
				.accessTreeExecutionToEntity(execucio);
		getEntryPointExecutableEntityDao().update(entity);

		updateImage(puntEntrada, entity);

		auditarExecucioPuntEntrada(
				"U",
				puntEntrada.getName() + " tipus "
						+ execucio.getTypeMimeExecution() + " ambit "
						+ execucio.getScope());

		return getEntryPointExecutableEntityDao().toAccessTreeExecution(entity);
	}

	protected Collection<AccessTreeExecution> handleGetExecutions(
			AccessTree puntEntrada) throws Exception {
		// Si es vacío, devolvemos un linkedlist nuevo
		return puntEntrada.getExecutions() == null ? new LinkedList()
				: puntEntrada.getExecutions();
	}

	private void auditarPuntEntrada(String accio, String nomPuntEntrada) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria
				.setApplication(nomPuntEntrada.length() >= 100 ? nomPuntEntrada
						.substring(0, 100) : nomPuntEntrada);
		auditoria.setAuthor(codiUsuari);
		auditoria.setObject("SC_PUNENT"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	private void auditarExecucioPuntEntrada(String accio, String execucio) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setApplication(execucio.length() >= 100 ? execucio.substring(
				0, 100) : execucio);
		auditoria.setAuthor(codiUsuari);
		auditoria.setObject("SC_EXEPUE"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	private void auditarAutoritzacioPuntEntrada(String accio, String tipus,
			String autoritzacio, String group, String account, String role, String system, String user) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setApplication(autoritzacio.length() > 100 ? autoritzacio
				.substring(0, 100) : autoritzacio);
		auditoria.setAuthor(codiUsuari);
		auditoria.setAccount(account);
		auditoria.setRole(role);
		auditoria.setUser(user);
		auditoria.setGroup(group);
		auditoria.setDatabase(system);
		String taula = ""; //$NON-NLS-1$
		if (tipus.equals(TipusAutoritzacioPuntEntrada.ROL))
			taula = "SC_ROLPUE"; //$NON-NLS-1$
		else if (tipus.equals(TipusAutoritzacioPuntEntrada.USUARI))
			taula = "SC_USUPUE"; //$NON-NLS-1$
		else if (tipus.equals(TipusAutoritzacioPuntEntrada.GRUP))
			taula = "SC_GRUPUE"; //$NON-NLS-1$
        else if (tipus.equals(TipusAutoritzacioPuntEntrada.ACCOUNT))
            taula = "SC_ACCPUE"; //$NON-NLS-1$
		auditoria.setObject(taula);
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	protected java.util.Collection<AccessTree> handleFindMenuChildren(
			com.soffid.iam.api.AccessTree puntEntrada)
			throws java.lang.Exception {
    	EntryPointEntity existingEntity = getEntryPointEntityDao().load (puntEntrada.getId());
    	if (existingEntity == null)
    		return null;
    	AccessTree existing = getEntryPointEntityDao().toAccessTree(existingEntity);
		// Comprovem autorització
		if (!canView(existing))
			return new LinkedList<AccessTree>();// throw new
												// InternalErrorException("no autoritzat");

		Collection<EntryPointTreeEntity> arbre = getEntryPointTreeEntityDao()
				.findByParent(puntEntrada.getId());
		if (arbre != null && arbre.size() != 0) {// Verificamos permisos
			Collection<AccessTree> fills = new LinkedList<AccessTree>();
			for (Iterator<EntryPointTreeEntity> it = arbre.iterator(); it
					.hasNext();) {
				EntryPointTreeEntity a = it.next();
				AccessTree pue = getEntryPointEntityDao().toAccessTree(
						a.getChild());
				pue.setParentId(a.getParent().getId());
				pue.setOrder("" + a.getOrder());
				if (pue.isMenu() && canAdmin(pue))
					fills.add(pue);
			}
			return fills;

		}
		return new LinkedList<AccessTree>();
	}

	protected String handleValidateXMLApplicationAccess(AccessTree puntEntrada)
			throws Exception {
		String contingut = puntEntrada.getXmlAccessTree();

		if (!"".equals(contingut)) { //$NON-NLS-1$

			try {
				// Validem el document
				// new
				// es.caib.seycon.mazinger.compiler.Compile().parse(contingut);
				org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader(true);
				// set the validation feature to true to report validation
				// errors
				reader.setFeature(
						"http://xml.org/sax/features/validation", true); //$NON-NLS-1$

				// set the validation/schema feature to true to report
				// validation errors
				// against a schema
				reader.setFeature(
						"http://apache.org/xml/features/validation/schema", true); //$NON-NLS-1$
				// set the validation/schema-full-checking feature to true to
				// enable
				// full schema, grammar-constraint checking
				reader.setFeature(
						"http://apache.org/xml/features/validation/schema-full-checking", //$NON-NLS-1$
						true);
				// set the schema
				reader.setProperty(
						"http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", //$NON-NLS-1$
						"/es/caib/seycon/mazinger/Mazinger.xsd"); //$NON-NLS-1$
				// set the entity resolver (to load the schema with
				// getResourceAsStream)
				reader.getXMLReader().setEntityResolver(new SchemaLoader());
				reader.setEntityResolver(new SchemaLoader());

				Document doc = reader.read(new ByteArrayInputStream(contingut
						.getBytes("UTF-8"))); //$NON-NLS-1$

			} catch (Exception ex) {
				return ex.getMessage(); // Retornem l'excepció com error de
										// Validació
			}
		}
		return ""; //$NON-NLS-1$
	}

	public class SchemaLoader implements EntityResolver {
		public static final String FILE_SCHEME = "file://"; //$NON-NLS-1$

		public InputSource resolveEntity(String publicId, String systemId)
				throws SAXException {
			InputStream input = SchemaLoader.class
					.getResourceAsStream("/es/caib/seycon/mazinger/Mazinger.xsd"); //$NON-NLS-1$
			return new InputSource(input);
		}
	}

	protected Collection<AccessTree> handleFindApplicationAccessByFilter(
			java.lang.String nomPUE, java.lang.String codiPUE,
			java.lang.String codiAplicacio, java.lang.String codiRol,
			java.lang.String codiGrup, java.lang.String codiUsuari)
			throws Exception {
		if ("%".equals(nomPUE) && "%".equals(codiPUE) && "%".equals(codiAplicacio) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				&& "%".equals(codiRol) && "%".equals(codiGrup) && "%".equals(codiUsuari)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			LinkedList<AccessTree> root = new LinkedList<AccessTree>();
			root.add(findRoot());
			return root;
		} else {
			if ("%".equals(nomPUE)) //$NON-NLS-1$
				nomPUE = null;
			if ("%".equals(codiPUE)) //$NON-NLS-1$
				codiPUE = null;
			if ("%".equals(codiAplicacio)) //$NON-NLS-1$
				codiAplicacio = null;
			if ("%".equals(codiRol)) //$NON-NLS-1$
				codiRol = null;
			if ("%".equals(codiGrup)) //$NON-NLS-1$
				codiGrup = null;
			if ("%".equals(codiUsuari)) //$NON-NLS-1$
				codiUsuari = null;

			// Ara filtrem
			InformationSystemEntity aplicacio = null;
			if (codiAplicacio != null) {
				aplicacio = getInformationSystemEntityDao().findByCode(
						codiAplicacio);
				if (aplicacio == null) {
					throw new InternalErrorException(
							String.format(
									Messages.getString("EntryPointServiceImpl.ApplicationNotFounded"), codiAplicacio)); //$NON-NLS-1$
				}
			}
			Collection<EntryPointEntity> cerca = getEntryPointEntityDao()
					.findByCriteria(nomPUE, codiPUE);
			if (aplicacio != null) {
				Collection<EntryPointEntity> resFiltrats = new LinkedList<EntryPointEntity>();
				for (Iterator<EntryPointEntity> it = cerca.iterator(); it
						.hasNext();) {
					EntryPointEntity pue = it.next();
					if (pue.getInformationSystem() != null && pue.getInformationSystem().equals(aplicacio))
						resFiltrats.add(pue);
				}
				cerca = resFiltrats; // Filtrem
			}

			List<AccessTree> cercaVO = getEntryPointEntityDao()
					.toAccessTreeList(cerca);

			// Mirem les autoritzacions dels resultats de la cerca
			for (Iterator<AccessTree> it = cercaVO.iterator(); it.hasNext();) {
				AccessTree pue = it.next();
				if (!canView(pue))
					it.remove();
			}
			return cercaVO;
		}
	}

	class Graph {
		private Map<EntryPointEntity, LinkedHashSet<EntryPointEntity>> map = new HashMap<EntryPointEntity, LinkedHashSet<EntryPointEntity>>();

		public void addEdge(EntryPointEntity node1, EntryPointEntity node2) {
			LinkedHashSet<EntryPointEntity> adjacent = map.get(node1);
			if (adjacent == null) {
				adjacent = new LinkedHashSet<EntryPointEntity>();
				map.put(node1, adjacent);
			}
			adjacent.add(node2);
		}

		public void addTwoWayVertex(EntryPointEntity node1,
				EntryPointEntity node2) {
			addEdge(node1, node2);
			addEdge(node2, node1);
		}

		public boolean isConnected(EntryPointEntity node1,
				EntryPointEntity node2) {
			Set adjacent = map.get(node1);
			if (adjacent == null) {
				return false;
			}
			return adjacent.contains(node2);
		}

		public LinkedList<EntryPointEntity> adjacentNodes(EntryPointEntity last) {
			LinkedHashSet<EntryPointEntity> adjacent = map.get(last);
			if (adjacent == null) {
				return new LinkedList<EntryPointEntity>();
			}
			return new LinkedList<EntryPointEntity>(adjacent);
		}
	}

	class Search {

		private void breadthFirst(Graph graph,
				LinkedList<EntryPointEntity> visited, LinkedList<String> rutes) {
			LinkedList<EntryPointEntity> nodes = graph.adjacentNodes(visited
					.getLast());
			// examine adjacent nodes
			for (EntryPointEntity node : nodes) {
				if (visited.contains(node)) {
					continue;
				}
				if (ROOT_TAG.equals(node.getCode())) {
					visited.add(node);
					rutes.add(printPath(visited));
					visited.removeLast();
					break;
				}
			}
			// in breadth-first, recursion needs to come after visiting adjacent
			// nodes
			for (EntryPointEntity node : nodes) {
				if (visited.contains(node) || ROOT_TAG.equals(node.getCode())) {
					continue;
				}
				visited.addLast(node);
				breadthFirst(graph, visited, rutes);
				visited.removeLast();
			}
		}

		public String printPath(LinkedList<EntryPointEntity> visited) {
			String res = ""; //$NON-NLS-1$

			// Fem el cami invers
			/*
			 * for (PuntEntradaEntity node : visited) { res+= node.getNom()
			 * +" < "; } if (res.endsWith(" < ")) res =
			 * res.substring(0,res.lastIndexOf(" < "));
			 */
			for (int i = (visited.size() - 1); i >= 0; i--) {
				res += visited.get(i).getName() + " > ";
			}
			if (res.endsWith(" > ")) //$NON-NLS-1$
				res = res.substring(0, res.lastIndexOf(" > ")); //$NON-NLS-1$
			return res;
		}

	}

	private void getGraphInversPUE(EntryPointEntity pue, Graph arbre) {

		Collection pares = getEntryPointTreeEntityDao().findByChildren(
				pue.getId());

		for (Iterator it = pares.iterator(); it.hasNext();) {
			EntryPointTreeEntity a = (EntryPointTreeEntity) it.next();
			EntryPointEntity pare = a.getParent();
			arbre.addEdge(pue, pare);
			getGraphInversPUE(pare, arbre);
		}

	}

	protected Collection<String> handleGetReverseApplicationAccessTree(
			AccessTree puntEntrada) throws Exception {

		Graph arbreInvers = new Graph();
		EntryPointEntity pue = getEntryPointEntityDao().accessTreeToEntity(
				puntEntrada);
		getGraphInversPUE(pue, arbreInvers);

		LinkedList<EntryPointEntity> visited = new LinkedList<EntryPointEntity>();
		visited.add(pue);
		LinkedList<String> rutes = new LinkedList<String>();
		new Search().breadthFirst(arbreInvers, visited, rutes);
		return rutes;
	}

	/**
	 * Fem un mètode especific per a l'accés des de la Intranet (!!) - s'ha de
	 * probar
	 * 
	 * @param idPuntEntrada
	 * @param nivell
	 * @return
	 * @throws Exception
	 */
	protected boolean handleIsAuthorized(String codiUsuari, Long idPuntEntrada,
			String nivell) throws Exception {

		// Carreguem el punt d'entrada
		EntryPointEntity puntEntradaE = getEntryPointEntityDao().load(
				idPuntEntrada);
		if (puntEntradaE == null)
			return false;
		AccessTree puntEntrada = getEntryPointEntityDao().toAccessTree(
				puntEntradaE);

		String user = Security.getCurrentUser();
		Collection<AccessTreeAuthorization> autoritzacions = puntEntrada
				.getAuthorizations();
		if (autoritzacions == null)
			return false;
		Iterator<AccessTreeAuthorization> iterator = autoritzacions.iterator();
		PermissionsCache permisos = getCurrentAuthorizations();
		boolean trobat = false;

		while (!trobat && iterator.hasNext()) {
			AccessTreeAuthorization auto = iterator.next();
			// Puede ser de 3 tipos: usuario, rol o grupo
			String tipus = auto.getAuthorizationEntityType();
			String codiAuto = auto.getAuthorizedEntityCode(); // és ÚNIC !!
			if (tipus.equals(TipusAutoritzacioPuntEntrada.USUARI)) {
				if (codiAuto.equals(user)) {
					// Comprovem el nivell d'autorització
					String nivellAuto = auto.getAuthorizationLevelDescription();
					trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO
							.equals(nivellAuto) || nivellAuto.equals(nivell));
				}
			} else if (tipus.equals(TipusAutoritzacioPuntEntrada.ROL)) {
				// Lo buscamos en la hash
				if (permisos.getRolsUsuariPUE().contains(
						auto.getAuthorizationEntityId())) {// codiAuto.toUpperCase()))
					// {
					// Comprovem el nivell d'autorització
					String nivellAuto = auto.getAuthorizationLevelDescription();
					trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO
							.equals(nivellAuto) || nivellAuto.equals(nivell));
				}

			} else if (tipus.equals(TipusAutoritzacioPuntEntrada.GRUP)) {
				// Lo buscamos en la hash
				if (permisos.getGrupsUsuariPUE().contains(auto.getAuthorizationEntityId())) {
					// Comprovem el nivell d'autorització
					String nivellAuto = auto.getAuthorizationLevelDescription();
					trobat = (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO
							.equals(nivellAuto) || nivellAuto.equals(nivell));
				}
			}
		}

		return trobat;
	}

	protected boolean handleApplicationAccessTreeHasAnyACL(String codiUsuari)
			throws Exception {
		// Cerquem per ACLs (només d'administració)
		// and you're faster than the shoot from a gun
		// comencem per número de files de major a menor: rol - grup - usu

		PermissionsCache permisos = getCurrentAuthorizations();

		// ROL: només els de permís d'aministrador (!!)
		Collection autoRol = getEntryPointRoleEntityDao()
				.query("from com.soffid.iam.model.EntryPointRoleEntity where authorizationLevel=\'A\'", new Parameter[0]); //$NON-NLS-1$
		for (Iterator it = autoRol.iterator(); it.hasNext();) {
			EntryPointRoleEntity auto = (EntryPointRoleEntity) it.next();
			if (permisos.getRolsUsuariPUE().contains(auto.getRole().getId())) {
				if (TipusAutoritzacioPuntEntrada.NIVELL_A.equals(auto
						.getAuthorizationLevel()))
					return true;
			}
		}

		// GRUP: només els de permís d'aministrador (!!)
		List<EntryPointGroupEntity> autoGrup = getEntryPointGroupEntityDao()
				.query("from com.soffid.iam.model.EntryPointGroupEntity where auhtorizationLevel=\'A\'", new Parameter[0]); //$NON-NLS-1$
		List<AccessTreeAuthorization> autoGrupVO = getEntryPointGroupEntityDao()
				.toAccessTreeAuthorizationList(autoGrup);
		for (Iterator<AccessTreeAuthorization> it = autoGrupVO.iterator(); it
				.hasNext();) {
			AccessTreeAuthorization auto = it.next();
			if (permisos.getGrupsUsuariPUE().contains(
					auto.getAuthorizationEntityId())) {
				if (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO
						.equals(auto.getAuthorizationLevelDescription()))
					return true;
			}
		}

		// USUARI: només els de permís d'aministrador (!!)
		List<EntryPointUserEntity> autoUsu = getEntryPointUserEntityDao()
				.query("from com.soffid.iam.model.EntryPointUserEntity where authorizationLevel=\'A\'", new Parameter[0]); //$NON-NLS-1$
		List<AccessTreeAuthorization> autoUsuVO = getEntryPointUserEntityDao()
				.toAccessTreeAuthorizationList(autoUsu);
		for (Iterator<AccessTreeAuthorization> it = autoUsuVO.iterator(); it
				.hasNext();) {
			AccessTreeAuthorization auto = it.next();
			if (codiUsuari.equals(auto.getAuthorizedEntityCode())) {
				if (TipusAutoritzacioPuntEntrada.NIVELL_A_DESCRIPCIO
						.equals(auto.getAuthorizationLevelDescription()))
					return true;
			}
		}

		// alea jacta est
		return false;
	}

	@Override
	protected AccessTree handleFindApplicationAccessById(long id)
			throws Exception {
		EntryPointEntity pueEntity = getEntryPointEntityDao().load(id);
		AccessTree pue = getEntryPointEntityDao().toAccessTree(pueEntity);
		for (EntryPointTreeEntity p: pueEntity.getParentEntryPointTree()) {
			pue.setParentId(p.getParent().getId());
		}

		if (canView(pue))
			return pue;
		else
			return null;
	}

	protected EntryPointIconEntity createIcona(byte[] b)
			throws InternalErrorException {
		try {
			EntryPointIconEntity icona = getEntryPointIconEntityDao()
					.newEntryPointIconEntity();
			icona.setIcon(b);
			getEntryPointIconEntityDao().create(icona);
			return icona;
		}

		catch (Exception e) {
			throw new InternalErrorException(
					Messages.getString("EntryPointServiceImpl.ImageTooBigError")); //$NON-NLS-1$
		}
	}

    private int findId (List<AccessTree> children, Long entryPointId)
    {
    	int l = 0;
    	for ( AccessTree pe: children)
    	{
    		if (pe.getId().equals (entryPointId))
    			break;
    		l++;
    	}
    	return l;
    }
	@Override
	protected void handleSortChildren(long entryPointId) throws Exception {
		EntryPointEntity epe = getEntryPointEntityDao().load(entryPointId);
		if (epe != null )
		{
			AccessTree ep = getEntryPointEntityDao().toAccessTree(epe);
			if (canAdmin(ep))
			{
				List<AccessTree> children = new LinkedList<AccessTree>();
				for ( EntryPointTreeEntity child: epe.getChildrenEntryPointTree())
				{
					AccessTree p = getEntryPointEntityDao().toAccessTree(child.getChild());
					children.add(p);
				}
				Collections.sort(children, new Comparator<AccessTree>() {

					public int compare(AccessTree o1, AccessTree o2) {
						return (o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
					}
				});
				// Sort descending
				for ( EntryPointTreeEntity child: epe.getChildrenEntryPointTree())
				{
					child.setOrder(-1-findId(children, child.getChild().getId()));
					getEntryPointTreeEntityDao().update(child);
				}
				// Sort ascending
				for ( EntryPointTreeEntity child: epe.getChildrenEntryPointTree())
				{
					child.setOrder(findId(children, child.getChild().getId()));
					getEntryPointTreeEntityDao().update(child);
				}
			}
		}
	}

	
	private ICacheAccess<String, PermissionsCache> cache;
	private ICacheAccess<String, PermissionsCache> getCache()
	{ 
		if (cache == null)
			cache = JCSCacheProvider.buildCache(PermissionsCache.class.getName());
		return cache;
	}

	@Override
	protected String handleGetScopeForAddress(String address) throws Exception {
		if (address == null || address.trim().isEmpty())
			return "I";
		Network network = getNetworkService().findNetworkByIpAddress(address);
    	String defaultNetwork = ConfigurationCache.getProperty("soffid.network.internet"); //$NON-NLS-1$
    	
    	if (network == null || 
    			network.getCode().equals(defaultNetwork) || 
    			network.getMask().trim().equals("0.0.0.0") ||
    			network.getMask().trim().equals("0:0:0:0:0:0:0:0"))
    		return "I";
    	else if ( Boolean.TRUE.equals( network.getLanAccess() ) )
    		return "L";
    	else
    		return "W";
	}

	@Override
	protected AsyncList<AccessTree> handleFindAccessTreeByTextAndJsonQueryAsync(final String text, final String jsonQuery)
			throws Exception {
		final AsyncList<AccessTree> result = new AsyncList<AccessTree>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindAccessTreeByTextAndJsonQuery(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private void doFindAccessTreeByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			Collection<AccessTree> result) throws UnsupportedEncodingException, ClassNotFoundException, InternalErrorException, 
				EvalException, JSONException, ParseException, TokenMgrError {
		final EntryPointEntityDao dao = getEntryPointEntityDao();
		ScimHelper h = new ScimHelper(AccessTree.class);
		h.setPrimaryAttributes(new String[] { "name", "code"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			EntryPointEntity source = (EntryPointEntity) entity;
			AccessTree target = dao.toAccessTree(source);
			for (EntryPointTreeEntity p: source.getParentEntryPointTree()) {
				target.setParentId(p.getParent().getId());
			}
			return target;
		}); 
		h.search(text, jsonQuery, (Collection) result); 
	}

	@Override
	protected List<AccessTree> handleFindAccessTreeByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<AccessTree> result = new LinkedList<AccessTree>();
		doFindAccessTreeByTextAndJsonQuery(text, jsonQuery, start, pageSize, result);
		return result;
	}

}

class PermissionsCache {
	private Set<Long> grupsUsuariPUE;
	private Set<Long> rolsUsuariPUE;
	private Set<Long> accountsPUE;
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public PermissionsCache() {
		grupsUsuariPUE = new HashSet<Long>();
		rolsUsuariPUE = new HashSet<Long>();
		accountsPUE = new HashSet<Long>();
	}

	public void setUserId(Long id) {
		this.userId = id;
	}

	public Set<Long> getAccountsPUE() {
		return accountsPUE;
	}

	public void setAccountsPUE(Set<Long> accountsPUE) {
		this.accountsPUE = accountsPUE;
	}

	public Set<Long> getGrupsUsuariPUE() {
		return grupsUsuariPUE;
	}

	public void setGrupsUsuariPUE(Set<Long> grupsUsuariPUE) {
		this.grupsUsuariPUE = grupsUsuariPUE;
	}

	public Set<Long> getRolsUsuariPUE() {
		return rolsUsuariPUE;
	}

	public void setRolsUsuariPUE(Set<Long> rolsUsuariPUE) {
		this.rolsUsuariPUE = rolsUsuariPUE;
	}

	
}
class EntryPointCache {
	public long timeout;
	public LinkedList<Long> users = new LinkedList<Long>();
	public LinkedList<Long> adminusers = new LinkedList<Long>();
	public LinkedList<Long> groups = new LinkedList<Long>();
	public LinkedList<Long> admingroups = new LinkedList<Long>();
	public LinkedList<Long> roles = new LinkedList<Long>();
	public LinkedList<Long> adminroles = new LinkedList<Long>();
	public LinkedList<Long> accounts = new LinkedList<Long>();
	public LinkedList<Long> adminaccounts = new LinkedList<Long>();
}
