package com.soffid.iam.service;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jpdl.el.FunctionMapper;
import org.jbpm.jpdl.el.VariableResolver;
import org.jbpm.jpdl.el.impl.ExpressionEvaluatorImpl;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.json.JSONException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.AsyncProcessTracker;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.BpmProcess;
import com.soffid.iam.api.BpmUserProcess;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.DisableObjectRule;
import com.soffid.iam.api.EventUserAction;
import com.soffid.iam.api.ExtranetCard;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.MailList;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordDomainStatus;
import com.soffid.iam.api.PolicyCheckResult;
import com.soffid.iam.api.Printer;
import com.soffid.iam.api.PrinterUser;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Session;
import com.soffid.iam.api.SyncAgentTaskLog;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.UserCriteria;
import com.soffid.iam.api.UserData;
import com.soffid.iam.api.UserMailList;
import com.soffid.iam.bpm.service.BpmEngine;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.AccessLogEntity;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AttributeParser;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.GroupEntityDao;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssueUserEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.PasswordEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.SecretEntity;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.ServerEntityDao;
import com.soffid.iam.model.ServerInstanceEntity;
import com.soffid.iam.model.SessionEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.TaskLogEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserEntityDao;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.UserPreferenceEntity;
import com.soffid.iam.model.UserPrinterEntity;
import com.soffid.iam.model.UserProcessEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.VaultFolderAccessEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.remote.RemoteServiceLocator;
import com.soffid.iam.service.impl.CertificateParser;
import com.soffid.iam.service.impl.ObjectVariableResolver;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.sync.service.SyncStatusService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.DateUtils;
import com.soffid.iam.utils.LimitDates;
import com.soffid.iam.utils.NetworkIntelligenceIssuesUtils;
import com.soffid.iam.utils.ProcesWFUsuari;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.SoffidStackTrace;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.signatura.api.ParsedCertificate;
import es.caib.signatura.api.Signature;
import es.caib.signatura.cliente.ValidadorCertificados;
import es.caib.signatura.cliente.XML;
import es.caib.signatura.utils.BitException;
import es.caib.signatura.validacion.ResultadoValidacion;

public class UserServiceImpl extends com.soffid.iam.service.UserServiceBase {
	private static final String E_MAIL_CONTACTE = "EMAIL"; //$NON-NLS-1$
	public static final String NIF = "NIF"; //$NON-NLS-1$
	public static final String TELEFON = "PHONE"; //$NON-NLS-1$
	private static final String DATA_BAIXA = "DATA_BAIXA"; //$NON-NLS-1$
	private static final String CODI_USUARI_IBSALUT = "CODI_USUARI_IBSALUT";// cambio //$NON-NLS-1$
	private final String DADA_ADDICIONAL_CODI_XESTIB = "CODI_XESTIB"; //$NON-NLS-1$
	private final String DADA_ADDICIONAL_CODI_XESTIB_GRUPALUMNE = "CODI_XESTIB_GRUPALUMNE"; //$NON-NLS-1$

	protected User handleSetServersToUser(java.lang.String codiUsuari,
			String servidorPerfil, String servidorCorreu, String servidorHome)
			throws java.lang.Exception {
		/*
		 * Se asignan los servidores
		 */
		User usuari = findUserByUserName(codiUsuari);
		if (usuari != null) {
			usuari.setMailServer(servidorCorreu);
			usuari.setProfileServer(servidorPerfil);
			usuari.setHomeServer(servidorHome);
			UserEntity usuariEntity = getUserEntityDao().userToEntity(usuari);
			/*
			 * Se actualiza el usuario
			 */
			getUserEntityDao().update(usuariEntity);
			return usuari;
		}
		return null;
	}

	protected User handleDisableUser(java.lang.String codiUsuari)
			throws java.lang.Exception {
		// autoritzacio user:delete
		// Cridat des de delete(usuari)

		UserEntity usuariEntity = getUserEntityDao().findByUserName(codiUsuari);
		if ( ! "S".equals(usuariEntity.getActive())) {
			if (!getAuthorizationService().hasPermission("user:disable", usuariEntity))
				throw new SecurityException("Access denied. Required roles: [user:disable]");
			auditChange("e", usuariEntity.getUserName(), null);
		}
		/*
		 * Se eliminan los roles de los usuarios
		 */
		getRoleAccountEntityDao().remove(
				getRoleAccountEntityDao().findByUserName(
						usuariEntity.getUserName()));

		/*
		 * Se eliminan las asociaciones a grupos
		 */
		Collection<UserGroupEntity> grups = usuariEntity.getSecondaryGroups();
		getUserGroupEntityDao().remove(grups);

		// Assign the root group
		GroupEntity group = usuariEntity.getPrimaryGroup();
		while (group.getParent() != null) {
			group = group.getParent();
		}
		usuariEntity.setPrimaryGroup(group);
		/*
		 * Se eliminan las asociaciones d'impressora
		 */
		Collection impressores = usuariEntity.getPrinters();
		this.getUserPrinterEntityDao().remove(impressores);

		/*
		 * Se eliminan las asociaciones de listas de correo
		 */
		Collection llistesDeCorreu = getMailListsService()
				.findUserMailListByUserName(usuariEntity.getUserName());
		for (Iterator it = llistesDeCorreu.iterator(); it.hasNext();) {
			UserMailList llistaCorreuUsuari = (UserMailList) it.next();
			getMailListsService().deleteUserMailList(llistaCorreuUsuari);
		}

		/*
		 * Se eliminan las asociaciones con redes
		 */
		Collection xarxes = usuariEntity.getACNetwork();
		this.getNetworkAuthorizationEntityDao().remove(xarxes);

		/*
		 * Se eliminan las asociaciones con servidores
		 */
		usuariEntity.setHomeServer(null);
		usuariEntity.setMailServer(null);
		usuariEntity.setProfileServer(null);

		usuariEntity.setLastUserModification(Security.getCurrentAccount());
		usuariEntity.setLastModificationDate(GregorianCalendar.getInstance()
				.getTime());
		usuariEntity.setShortName(null);
		usuariEntity.setMailDomain(null);

		/*
		 * Se eliminan las referencias desde aplicaciones como persona de
		 * contacto
		 */
		Collection aplicacionsSocResponsable = usuariEntity
				.getApplicationResponsible();
		if (aplicacionsSocResponsable != null) {
			for (Iterator it = aplicacionsSocResponsable.iterator(); it
					.hasNext();) {
				InformationSystemEntity app = (InformationSystemEntity) it
						.next();
				app.setContactPerson(null);
				getInformationSystemEntityDao().update(app);
			}
		}

		/*
		 * Se pone en activo
		 */
		usuariEntity.setActive("N"); //$NON-NLS-1$

		/*
		 * Se actualiza el usuario
		 */
		getUserEntityDao().update(usuariEntity);

		return getUserEntityDao().toUser(usuariEntity);
	}

	protected Collection<Printer> handleFindPrintersByUserName(
			java.lang.String codiUsuari) throws java.lang.Exception {
		java.util.List<com.soffid.iam.model.UserPrinterEntity> impressoresUsuari = getUserPrinterEntityDao()
				.findByUser(codiUsuari);
		Collection impressores = new Vector();
		if (impressoresUsuari != null) {
			for (Iterator<UserPrinterEntity> it = impressoresUsuari.iterator(); it
					.hasNext();) {
				UserPrinterEntity uimp = it.next();
				impressores.add(uimp.getPrinter());
			}
			return getPrinterEntityDao().toPrinterList(impressores);
		}
		return impressores;
	}

	protected com.soffid.iam.api.User handleFindUserByUserNif(
			java.lang.String nif) throws java.lang.Exception {
		UserEntity usuariEntity = getUserEntityDao().findByNationalID(nif);
		if (usuariEntity != null) {
			return getUserEntityDao().toUser(usuariEntity);
		}
		return null;
	}

	protected Collection<User> handleFindUsersByUserName(String codiUsuari)
			throws java.lang.Exception {
		if (codiUsuari == null || codiUsuari.trim().compareTo("") == 0) { //$NON-NLS-1$
			return new LinkedList();
		}
		return this.findUsersByCoreData(codiUsuari, null, null, null, null);

	}

	protected User handleFindUserByUserName(String codiUsuari)
			throws java.lang.Exception {
		if (codiUsuari == null || codiUsuari.trim().compareTo("") == 0) { //$NON-NLS-1$
			return null;
		}
		UserEntity usuariEntity = getUserEntityDao().findByUserName(codiUsuari);
		if (usuariEntity != null) {
			User usuari = getUserEntityDao().toUser(usuariEntity);
			return usuari;
		}
		return null;
	}

	protected String handleGeneraCodiUsuari() throws java.lang.Exception {
		String codiUsuari = getUserEntityDao().generateUserName();
		return codiUsuari;
	}

	private boolean checkIfNumber(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Mètode per verificar si el camp és un NIE o NIF
	 * 
	 * @param nifonie
	 * @return
	 */
	private boolean esNIFoNIE(String nifonie) {
		boolean esNIF = false;
		boolean esNIE = false;
		if (nifonie != null && !"".equals(nifonie.trim())) //$NON-NLS-1$
		{
			nifonie = nifonie.toUpperCase().trim();
			int longitud = nifonie.length();
			String primerCaracter = nifonie.substring(0, 1);
			String ultimCaracter = nifonie.substring(longitud - 1);

			// Primer, averiguar si és un NIF (té 9 caracters i l'últim és una
			// lletra)
			// o és un NIE (comença per X, Y o Z i l'últim és una lletra)
			if (primerCaracter.equals("X") || primerCaracter.equals("Y") || primerCaracter.equals("Z")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			{
				if (!checkIfNumber(ultimCaracter)) {
					esNIE = true;
				} else {
					esNIE = false;
				}
			} else {
				if (longitud == 9 && checkIfNumber(primerCaracter)
						&& !checkIfNumber(ultimCaracter)) {
					esNIF = true;
				} else {
					esNIF = false;
				}
			}
		}
		return esNIF || esNIE;
	}

	protected com.soffid.iam.api.User handleCreate(
			com.soffid.iam.api.User usuari) throws java.lang.Exception {

		// Comprovem que s'hagi especificat el tipus d'usuari de domini
		if (usuari.getUserType() == null) {
			throw new SeyconException(
					Messages.getString("UserServiceImpl.UserTypeNotEspecified")); //$NON-NLS-1$
		}

		UserEntity usersSameCode = getUserEntityDao().findByUserName(
				usuari.getUserName());
		if (usersSameCode != null)
			throw new SeyconException(String.format(
					Messages.getString("UserServiceImpl.CodeUserExists"),
					usuari.getUserName()));

		/* Se crea el usuario */
		UserEntity usuariEntity = getUserEntityDao().userToEntity(usuari);

		// Comprobamos autorización del usuario
		if (!getAuthorizationService().hasPermission(Security.AUTO_USER_CREATE,
				usuariEntity)) {
			throw new SeyconAccessLocalException("UsuariService", //$NON-NLS-1$
					"create (Usuari)", "user:create, user:create/*", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("UserServiceImpl.NoAuthorizedToUpdate")); //$NON-NLS-1$
		}

		/* se almacena la fecha de creación */
		Calendar calendar = GregorianCalendar.getInstance();
		Date now = calendar.getTime();
		usuariEntity.setCreationDate(now);
		/* se almacena el usuaio que lo crea */
		String codiUsuariCreacio = Security.getCurrentAccount();
		// UsuariEntity usuariCreacio =
		// this.getUsuariEntityDao().findByCodi(codiUsuariCreacio);

		usuariEntity.setCreationUser(codiUsuariCreacio);
		usuariEntity.setLastUserModification(codiUsuariCreacio);
		usuariEntity.setLastModificationDate(now);
		// Comprobamos autorización del usuario
		if (!getAuthorizationService().hasPermission(Security.AUTO_USER_CREATE, usuariEntity)) {
			throw new SeyconAccessLocalException("UsuariService", //$NON-NLS-1$
					"create (Usuari)", "user:create, user:create/*", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("UsuariServiceImpl.NoAuthorizedToUpdate")); //$NON-NLS-1$
		}
		

		getUserEntityDao().create(usuariEntity);

		// Comprobamos autorización del usuario
		if (!getAuthorizationService().hasPermission(Security.AUTO_USER_CREATE, usuariEntity)) {
			throw new SeyconAccessLocalException("UsuariService", //$NON-NLS-1$
					"create (Usuari)", "user:create, user:create/*", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("UsuariServiceImpl.NoAuthorizedToUpdate")); //$NON-NLS-1$
		}

		crearLlistaCorreu(usuari);
		
		if (usuari.getAttributes() != null)
			handleUpdateUserAttributes(usuari.getUserName(), usuari.getAttributes(), false);

		usuari.setId(usuariEntity.getId());
		/* Se devuelve la instancia de usuario creada */
		getAccountService().generateUserAccounts(usuari.getUserName());

		/* IAM-318: Propagar canvis de grup primari */
		GroupService service = getGroupService();
		service.propagateRolsChangesToDispatcher(usuari.getPrimaryGroup());

		if ( usuari.getActive() != null && usuari.getActive().booleanValue())
		{
			if (!getAuthorizationService().hasPermission("user:enable", usuariEntity))
				throw new SecurityException("Access denied. Required roles: [user:enable]");
			auditChange("E", usuari.getUserName(), null);
		}
		getRuleEvaluatorService().applyRules(usuariEntity);

		if (ConfigurationCache.isHistoryEnabled())
		{
			UserGroupEntity uge = getUserGroupEntityDao().newUserGroupEntity();
			uge.setUser(usuariEntity);
			uge.setGroup(usuariEntity.getPrimaryGroup());
			uge.setDisabled(Boolean.TRUE);
			uge.setStart(new Date());
			getUserGroupEntityDao().create(uge);
		}
		
		return getUserEntityDao().toUser(usuariEntity);
	}

	private int getPasswordMaxAge(String userType) throws NamingException,
			javax.ejb.CreateException, InternalErrorException {
		String pme;
		ConfigurationService configuracioService = getConfigurationService();
		pme = configuracioService.findParameterByNameAndNetworkName(
				"seycon.password.age." + userType, null).getValue(); //$NON-NLS-1$
		if (pme == null)
			pme = "45"; //$NON-NLS-1$
		return Integer.decode(pme).intValue();
	}

	private int getAccountMaxAge(String userType) throws NamingException,
			javax.ejb.CreateException, InternalErrorException {
		String pme;
		ConfigurationService configuracioService = getConfigurationService();
		pme = configuracioService.findParameterByNameAndNetworkName(
				"seycon.account.age." + userType, null).getValue(); //$NON-NLS-1$
		if (pme == null) {
			return 4 * 365 + 1; // 3 años de 365 + 1 año de 366
		} else {
			return Integer.decode(pme).intValue();
		}
	}

	private boolean hasCertificacioDeValidesaDeDades(
			es.caib.signatura.api.Signature signatura, Node domNode)
			throws InternalErrorException {
		ParsedCertificate parsedCertificate = signatura.getParsedCertificate();
		String nifIniciador = parsedCertificate.getNif();
		User iniciador = findUserByUserNif(nifIniciador);
		if (iniciador == null) {
			return false;
		}
		String certificacioDeCondicionsXML = getValue("CertificacioDeValidesa", //$NON-NLS-1$
				domNode);
		return certificacioDeCondicionsXML != null
				&& certificacioDeCondicionsXML.contains("certifica") //$NON-NLS-1$
				&& certificacioDeCondicionsXML.contains("dades") //$NON-NLS-1$
				&& certificacioDeCondicionsXML.contains("correctes"); //$NON-NLS-1$
	}

	private boolean hasCertificacioDeCondicions(
			es.caib.signatura.api.Signature signatura, Node domNode)
			throws InternalErrorException {
		ParsedCertificate parsedCertificate = signatura.getParsedCertificate();
		String nifIniciador = parsedCertificate.getNif();
		User iniciador = findUserByUserNif(nifIniciador);
		if (iniciador == null) {
			return false;
		}
		String certificacioDeCondicionsXML = getValue(
				"CertificacioDeCondicions", domNode); //$NON-NLS-1$
		return certificacioDeCondicionsXML != null
				&& certificacioDeCondicionsXML.contains("acceptat") //$NON-NLS-1$
				&& certificacioDeCondicionsXML.contains("obligacions") //$NON-NLS-1$
				&& certificacioDeCondicionsXML.contains("usuari"); //$NON-NLS-1$
	}

	protected Log log = LogFactory.getLog(getClass());
	// Cridat des del workflow per donar d'alta usuaris
	public User handleCreateUser(byte[] peticio,
			es.caib.signatura.api.Signature signatura) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// Comprobación de la firma del documento XML.
			InputStream in = new ByteArrayInputStream(peticio);
			if (!signatura.verify(in)) {
				throw new SeyconException(
						Messages.getString("UserServiceImpl.SignNotVerified")); //$NON-NLS-1$
			} else {
				throw new UnsupportedOperationException();
			}
		} catch (Exception e) {
			log.warn(Messages.getString("UserServiceImpl.Error"), e); //$NON-NLS-1$
			throw new SeyconException(e.getMessage(), e);
		}
	}

	/**
	 * Funcion que devuelve el valor de una camino de fichero xml. si no lo
	 * encuentra devuelve null.
	 * 
	 * @param camino
	 * @return
	 */
	private String getValue(String camino, Node domNode) {
		String resultado = null;
		StringTokenizer micamino = new StringTokenizer("Peticio/" + camino, "/"); //$NON-NLS-1$ //$NON-NLS-2$
		Node nodo = domNode;
		while (nodo != null && micamino.hasMoreTokens()) {
			NodeList nl = nodo.getChildNodes();
			String token = micamino.nextToken();
			nodo = null;
			for (int i = 0; i < nl.getLength(); i++) {
				Node sigNodo = nl.item(i);
				String name = sigNodo.getNodeName();
				if (name.equals(token)) {
					nodo = sigNodo;
					i = nl.getLength();
				}
			}
		}
		// Ahora en nodo tenemos el nodo que tiene el valor que queremos.
		// Siempre que el nodo no sea null.
		if (nodo != null) {
			if (nodo.getFirstChild() != null)
				resultado = nodo.getFirstChild().getNodeValue();
		}
		return resultado == null ? resultado : resultado.trim();
	}

	public void handleDesarRenovacio(Long idUsuari, Date dataRenovacio) {
	}

	/**
	 * Procés que llança un workflow de comprovació de nom quan es detecta que
	 * l'usuari que es dona d'alta mitjançant certificat te un nom + llinatges
	 * de més de 3 paraules.. per poder comprovar l'identitat
	 * 
	 * @param nomComplert
	 * @param codiUsuari
	 */
	private void llancaWFComprovacioNomDesDAltaUsuari(String nomComplert,
			String codiUsuari) {
		try {
			JbpmContext context = getBpmEngine().getContext();

			// TODO: pooledActors Per ara les reponsables es posa el grup
			// dgticseg
			// més endavant es podrà canviar per un altre...
			String responsables = "dgticseg"; //$NON-NLS-1$
			try {
				org.jbpm.graph.def.ProcessDefinition definition = context
						.getGraphSession().findLatestProcessDefinition(
								ProcesWFUsuari.PROCES_CANVIA_NOM_USUARI);

				org.jbpm.graph.exe.ProcessInstance process = new org.jbpm.graph.exe.ProcessInstance(
						definition);

				ContextInstance ci = process.getContextInstance();

				// configura variables del process [autoIniciat i codiUsuari]
				ci.setVariable("autoIniciat", new Integer(3)); //$NON-NLS-1$
				// Per tindre traces..
				ci.setVariable("codiUsuari", codiUsuari); //$NON-NLS-1$
				// update de les variables
				context.save(process);

				// start process
				process.signal();
				// Iniciem la tasca
				Collection tasks = process.getTaskMgmtInstance()
						.getTaskInstances();
				if (tasks != null) {
					TaskInstance task = (TaskInstance) tasks.iterator().next();
					// Iniciem la tasca
					// task.start();
					task.setPooledActors(new String[] { responsables });
				}
			} catch (Throwable th) {
				th.printStackTrace();
			} finally {
				context.close();
			}

		} catch (Throwable th) {
			th.printStackTrace();
		}

	}

	public String handleAddUser(Signature sig, String userType)
			throws InternalErrorException {
		try {
			if (!sig.verify()) {
				throw new InternalErrorException(
						Messages.getString("UserServiceImpl.IncorrectCertificate")); //$NON-NLS-1$
			}
			CertificateParser parser = new CertificateParser(sig.getCert());
			String nif = parser.getNif().toUpperCase();
			if (nif == null || nif.length() < 7) {
				throw new InternalErrorException(
						Messages.getString("UserServiceImpl.InvalidCertificate")); //$NON-NLS-1$
			}
			String user = "e" + nif.toLowerCase(); //$NON-NLS-1$
			if ((findUserByUserNif(nif) == null)
					&& (findUserByUserName(user) == null)) {
				// L'usuari no existeix, el creem
				User usuari = new User();
				usuari.setFirstName(parser.getGivenName());
				usuari.setMiddleName(parser.getSecondSurName());
				usuari.setLastName(parser.getFirstSurName());
				usuari.setMailServer("nul"); //$NON-NLS-1$
				usuari.setHomeServer("nul"); //$NON-NLS-1$
				usuari.setProfileServer("nul"); //$NON-NLS-1$
				usuari.setUserType(userType);
				usuari.setActive(new Boolean(true));
				usuari.setMultiSession(new Boolean(false));
				usuari.setPrimaryGroup("externs"); //$NON-NLS-1$
				usuari.setUserName(user);
				usuari.setCreatedDate(GregorianCalendar.getInstance());
				usuari.setCreatedByUser("SEYCON"); //$NON-NLS-1$

				// Creem l'usuari
				UserEntity usuariEntity = getUserEntityDao().userToEntity(
						usuari);
				getUserEntityDao().create(usuariEntity);
				/* Una vez creado, se almacena el NIF a nivell VO */
				usuari = getUserEntityDao().toUser(usuariEntity);
				// Actualizamos el usuariEntity (NIF)
				usuariEntity = getUserEntityDao().userToEntity(usuari);
				getUserEntityDao().update(usuariEntity);

				// Verifiquem que el nom + llinatge1 + llinatge2 no en tinga
				// més de 3 paraules, si les té hem de crear un WF de proposta
				// de canvi de nom
				String nomCertificat = parser.getGivenName()
						+ " " + parser.getFirstSurName() + " " + parser.getSecondSurName(); //$NON-NLS-1$ //$NON-NLS-2$
				String partsCertificat[] = nomCertificat.split(" "); //$NON-NLS-1$
				if (partsCertificat.length > 3) {
					// Llancem el procés
					llancaWFComprovacioNomDesDAltaUsuari(nomCertificat,
							usuariEntity.getUserName());
				}

				getRuleEvaluatorService().applyRules(usuariEntity);

				return usuariEntity.getUserName();
			} else {
				User usu = findUserByUserNif(nif);
				if (usu == null && findUserByUserName(user) != null)
					throw new InternalErrorException(String.format(Messages
							.getString("UserServiceImpl.ExistsUserNoNIF"), //$NON-NLS-1$
							user));
				return usu.getUserName(); // si arribem aquí existeix per nif o
											// per codi (mai serà nul)
			}
		} catch (Exception e) {
			// System.out.println("Error:"+e.getMessage());
			e.printStackTrace();
			throw new InternalErrorException(e.getMessage());
		}
	}

	private void procesaResultados(byte[] resultado_string) throws BitException {

		System.out.println(resultado_string);
		if (XML.verificaFirma(resultado_string)) {
			ArrayList lista = XML.getResultadosValidacion(resultado_string);

			ResultadoValidacion[] resultados = new ResultadoValidacion[lista
					.size()];
			lista.toArray(resultados);

			for (int i = 0; i < resultados.length; i++) {
				if (resultados[i].getValido().booleanValue()) {
					System.out
							.println("*************************************************"); //$NON-NLS-1$
					System.out.println(Messages
							.getString("UserServiceImpl.ValidMessage")); //$NON-NLS-1$
					continue;
				} else {
					System.out
							.println("*************************************************"); //$NON-NLS-1$
					System.out.println(Messages
							.getString("UserServiceImpl.NoValidMessage")); //$NON-NLS-1$
				}
				// Obtenemos las causas del error
				ArrayList lista_causas = resultados[i]
						.getListaCausasNoValidado();
				System.out.println(Messages
						.getString("UserServiceImpl.ForRequest") + i); //$NON-NLS-1$

				for (int j = 0; j < lista_causas.size(); j++) {
					BitException result = (BitException) lista_causas.get(j);
					System.out.println(result.getCode() + " : " //$NON-NLS-1$
							+ result.getTextoAdicional());
				}

			}
		} else {
			System.out.println(Messages
					.getString("UserServiceImpl.NoValidMessage")); //$NON-NLS-1$

			// Obtenemos una lista con las respuestas para cada certificado de
			// la cadena de certificacion
			ArrayList lista = XML.getResultadosValidacion(resultado_string);

			ResultadoValidacion[] resultados = new ResultadoValidacion[lista
					.size()];
			lista.toArray(resultados);

			for (int i = 0; i < resultados.length; i++) {
				// Obtenemos las causas del error
				ArrayList lista_causas = resultados[i]
						.getListaCausasNoValidado();
				System.out.println(Messages
						.getString("UserServiceImpl.ForRequest") + i); //$NON-NLS-1$

				for (int j = 0; j < lista_causas.size(); j++) {
					BitException result = (BitException) lista_causas.get(j);
					System.out.println(result.getCode() + " : " //$NON-NLS-1$
							+ result.getTextoAdicional());
				}

			}
		}

	}

	/**
	 * Realiza el alta del usuario a través de un array de certificados donde el
	 * primer elemento contiene el certificado del usuario
	 * 
	 * @param cert
	 * @param userType
	 * @return
	 * @author u88683
	 * @throws InternalErrorException
	 */
	public String handleAddUser(
			java.util.Collection<java.security.cert.X509Certificate> certs,
			String userType) throws InternalErrorException,
			java.rmi.RemoteException {
		return handleAddUsuari(certs.toArray(new X509Certificate[0]), userType);
	}

	public String handleAddUsuari(java.security.cert.X509Certificate[] certs,
			String userType) throws InternalErrorException,
			java.rmi.RemoteException {
		try {
			X509Certificate certUser = certs[0];
			// Validación de los certificados
			ValidadorCertificados validador;
			InputStream input = this.getClass().getClassLoader()
					.getResourceAsStream("valcert.properties"); //$NON-NLS-1$
			Properties prop = new Properties();
			prop.load(input);

			validador = new ValidadorCertificados(prop);
			for (int i = 0; i < certs.length; i++) {
				ByteArrayInputStream fileIS = new ByteArrayInputStream(
						certs[i].getEncoded());
				byte[] resultado_string = validador
						.validarCertificadoAutenticacion(fileIS);
				procesaResultados(resultado_string);
			}

			// Parseamos el certificado del usuario (primer elemento del array)
			CertificateParser parser = new CertificateParser(certUser);
			String nif = parser.getNif().toUpperCase();
			if (nif == null || nif.length() < 7) {
				throw new InternalErrorException(
						Messages.getString("UserServiceImpl.InvalidCertificate")); //$NON-NLS-1$
			}
			String user = "e" + nif.toLowerCase(); //$NON-NLS-1$
			if ((findUserByUserNif(nif) == null)
					&& (findUserByUserName(user) == null)) {
				// L'usuari no existeix (el creem)
				User usuari = new User();
				usuari.setFirstName(parser.getGivenName());
				usuari.setMiddleName(parser.getSecondSurName());
				usuari.setLastName(parser.getFirstSurName());
				usuari.setMailServer("nul"); //$NON-NLS-1$
				usuari.setHomeServer("nul"); //$NON-NLS-1$
				usuari.setProfileServer("nul"); //$NON-NLS-1$
				usuari.setUserType(userType);
				usuari.setActive(new Boolean(true));
				usuari.setMultiSession(new Boolean(false));
				usuari.setPrimaryGroup("externs"); //$NON-NLS-1$
				usuari.setUserName(user);
				usuari.setCreatedDate(GregorianCalendar.getInstance());
				usuari.setCreatedByUser("SEYCON"); //$NON-NLS-1$

				// Creem l'usuari
				UserEntity usuariEntity = getUserEntityDao().userToEntity(
						usuari);
				getUserEntityDao().create(usuariEntity);
				/* Una vez creado, se almacena el NIF a nivell VO */
				usuari = getUserEntityDao().toUser(usuariEntity);
				// Actualizamos el usuariEntity (NIF)
				usuariEntity = getUserEntityDao().userToEntity(usuari);
				getUserEntityDao().update(usuariEntity);

				// Verifiquem que el nom + llinatge1 + llinatge2 no en tinga
				// més de 3 paraules, si les té hem de crear un WF de proposta
				// de canvi de nom
				String nomCertificat = parser.getGivenName()
						+ " " + parser.getFirstSurName() + " " + parser.getSecondSurName(); //$NON-NLS-1$ //$NON-NLS-2$
				String partsCertificat[] = nomCertificat.split(" "); //$NON-NLS-1$
				if (partsCertificat.length > 3) {
					// Llancem el procés
					llancaWFComprovacioNomDesDAltaUsuari(nomCertificat,
							usuariEntity.getUserName());
				}

				getRuleEvaluatorService().applyRules(usuariEntity);

				return usuariEntity.getUserName();
			} else {
				User usu = findUserByUserNif(nif);
				if (usu == null && findUserByUserName(user) != null)
					throw new InternalErrorException(
							String.format(
									Messages.getString("UserServiceImpl.ExistsUserChangeNIF"), user)); //$NON-NLS-1$
				return usu.getUserName(); // si arribem aquí existeix per nif o
											// per codi (mai serà nul)
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalErrorException(e.getMessage(), e);
		}
	}

	private Collection<UserEntity> findUsuarisByCriteri(String codi,
			String nom, String primerLlinatge, String nomCurt,
			String dataCreacio, String usuariCreacio, String actiu,
			String segonLlinatge, String multiSessio, String comentari,
			String tipusUsuari, String servidorPerfil, String servidorHome,
			String servidorCorreu, String codiGrupPrimari, String dni,
			String dominiCorreu, String grupSecundari, Boolean restringeixCerca) throws InternalErrorException {
		String query = "select usuari " //$NON-NLS-1$
				+ "from com.soffid.iam.model.UserEntity usuari " //$NON-NLS-1$
				+ "left join usuari.profileServer as servidorPerfil " //$NON-NLS-1$
				+ "left join usuari.homeServer as servidorOfimatic " //$NON-NLS-1$
				+ "left join usuari.mailServer as servidorCorreu " //$NON-NLS-1$
				+ "left join usuari.primaryGroup as grupPrimari " //$NON-NLS-1$
				+ "left join usuari.mailDomain as dominiCorreu " //$NON-NLS-1$
				+ "left join usuari.userType as tipusUsuariDomini "; //$NON-NLS-1$
		if (dni != null) {
			query += "left join usuari.attributes as dadaUsuari " //$NON-NLS-1$
					+ "left join dadaUsuari.dataType as tipusDada "; //$NON-NLS-1$
		}
		query += "where usuari.tenant.id=:tenantId and " //$NON-NLS-1$
				+ "(:codi is null or usuari.userName like :codi) and (:nom is null or upper(usuari.userName) like upper(:nom)) and " //$NON-NLS-1$
				+ "(:primerLlinatge is null or upper(usuari.lastName) like upper(:primerLlinatge)) and " //$NON-NLS-1$
				+ "(:nomCurt is null or usuari.shortName like :nomCurt) and " //$NON-NLS-1$
				+ "(:usuariCreacio is null or usuari.creationUser like :usuariCreacio) and " //$NON-NLS-1$
				+ "(:actiu is null or usuari.active = :actiu) and " //$NON-NLS-1$
				+ "(:segonLlinatge is null or upper(coalesce(usuari.middleName,' ')) like upper(:segonLlinatge)) and " //$NON-NLS-1$
				+ "(:multiSessio is null or usuari.multiSessio = :multiSessio) and " //$NON-NLS-1$
				+ "(:comentari is null or usuari.comment like :comentari) and " //$NON-NLS-1$
				+ "(:tipusUsuari is null or tipusUsuariDomini.name = :tipusUsuari) and " //$NON-NLS-1$
				+ "(:servidorPerfil is null or servidorPerfil.name like :servidorPerfil) and " //$NON-NLS-1$
				+ "(:servidorHome is null or servidorOfimatic.name like :servidorHome) and " //$NON-NLS-1$
				+ "(:servidorCorreu is null or servidorCorreu.name like :servidorCorreu) and " //$NON-NLS-1$
				+ "(:codiGrupPrimari is null or grupPrimari.name like :codiGrupPrimari) and " //$NON-NLS-1$
				+ "(:dominiCorreu is null or dominiCorreu.name like :dominiCorreu) "; //$NON-NLS-1$
		if (dni != null) {
			query += " and (dadaUsuari.value like :dni and tipusDada.name = 'NIF') "; //$NON-NLS-1$
		}
		if (grupSecundari != null) {
			query += " and usuari.id in " //$NON-NLS-1$
					+ "(select grupUsuari.user.id " //$NON-NLS-1$
					+ "from com.soffid.iam.model.UserGroupEntity grupUsuari " //$NON-NLS-1$
					+ "where grupUsuari.group name like :grupSecundari ) "; //$NON-NLS-1$
		}

		Parameter tenantParameter = new Parameter("tenantId", Security.getCurrentTenantId()); //$NON-NLS-1$
		Parameter codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
		Parameter nomParameter = new Parameter("nom", nom); //$NON-NLS-1$
		Parameter primerLlinatgeParameter = new Parameter("primerLlinatge", //$NON-NLS-1$
				primerLlinatge);
		Parameter nomCurtParameter = new Parameter("nomCurt", nomCurt); //$NON-NLS-1$
		Parameter usuariCreacioParameter = new Parameter("usuariCreacio", //$NON-NLS-1$
				usuariCreacio);
		Parameter actiuParameter = new Parameter("actiu", actiu); //$NON-NLS-1$
		Parameter segonLlinatgeParameter = new Parameter("segonLlinatge", //$NON-NLS-1$
				segonLlinatge);
		Parameter multiSessioParameter = new Parameter("multiSessio", //$NON-NLS-1$
				multiSessio);
		Parameter comentariParameter = new Parameter("comentari", comentari); //$NON-NLS-1$
		Parameter tipusUsuariParameter = new Parameter("tipusUsuari", //$NON-NLS-1$
				tipusUsuari);
		Parameter servidorPerfilParameter = new Parameter("servidorPerfil", //$NON-NLS-1$
				servidorPerfil);
		Parameter servidorHomeParameter = new Parameter("servidorHome", //$NON-NLS-1$
				servidorHome);
		Parameter servidorCorreuParameter = new Parameter("servidorCorreu", //$NON-NLS-1$
				servidorCorreu);

		Parameter codiGrupSecundariParameter = new Parameter("grupSecundari", //$NON-NLS-1$
				grupSecundari);
		Parameter codiGrupPrimariParameter = new Parameter("codiGrupPrimari", //$NON-NLS-1$
				codiGrupPrimari);
		Parameter dominiCorreuParameter = new Parameter("dominiCorreu", //$NON-NLS-1$
				dominiCorreu);

		Parameter dniParameter = new Parameter("dni", dni); //$NON-NLS-1$
		Parameter dataMaxParameter = null;
		Parameter dataMinParameter = null;
		LimitDates limitDates = null;
		if (dataCreacio != null && dataCreacio.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataCreacio.trim().compareTo("%") != 0) { //$NON-NLS-1$
			dataCreacio = dataCreacio.trim();
			String dataCreacioClean = null;
			limitDates = DateUtils.getLimitDatesFromQuery(dataCreacio);
		}
		Date dataDarreraModificacioDate = null;
		Long passwordMaxAgeLong = null;
		Collection usuaris = null;
		Vector<Parameter> finalParameters = new Vector<Parameter>();
		if (dni != null) {
			if (grupSecundari != null) {
				for (Parameter parameter : new Parameter[] { 
						tenantParameter, codiParameter,
						nomParameter, primerLlinatgeParameter,
						nomCurtParameter, usuariCreacioParameter,
						actiuParameter, segonLlinatgeParameter,
						multiSessioParameter, comentariParameter,
						tipusUsuariParameter, servidorPerfilParameter,
						servidorHomeParameter, servidorCorreuParameter,
						codiGrupPrimariParameter, dniParameter,
						dominiCorreuParameter, codiGrupSecundariParameter }) {
					finalParameters.add(parameter);
				}

			} else {
				for (Parameter parameter : new Parameter[] { 
						tenantParameter,  codiParameter,
						nomParameter, primerLlinatgeParameter,
						nomCurtParameter, usuariCreacioParameter,
						actiuParameter, segonLlinatgeParameter,
						multiSessioParameter, comentariParameter,
						tipusUsuariParameter, servidorPerfilParameter,
						servidorHomeParameter, servidorCorreuParameter,
						codiGrupPrimariParameter, dniParameter,
						dominiCorreuParameter }) {
					finalParameters.add(parameter);
				}
			}
		} else {
			if (grupSecundari != null) {
				for (Parameter parameter : new Parameter[] { 
						tenantParameter,  codiParameter,
						nomParameter, primerLlinatgeParameter,
						nomCurtParameter, usuariCreacioParameter,
						actiuParameter, segonLlinatgeParameter,
						multiSessioParameter, comentariParameter,
						tipusUsuariParameter, servidorPerfilParameter,
						servidorHomeParameter, servidorCorreuParameter,
						codiGrupPrimariParameter, dominiCorreuParameter,
						codiGrupSecundariParameter }) {
					finalParameters.add(parameter);
				}
			} else {
				for (Parameter parameter : new Parameter[] { 
						tenantParameter,  codiParameter,
						nomParameter, primerLlinatgeParameter,
						nomCurtParameter, usuariCreacioParameter,
						actiuParameter, segonLlinatgeParameter,
						multiSessioParameter, comentariParameter,
						tipusUsuariParameter, servidorPerfilParameter,
						servidorHomeParameter, servidorCorreuParameter,
						codiGrupPrimariParameter, dominiCorreuParameter }) {
					finalParameters.add(parameter);
				}
			}
		}
		if (limitDates != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
			dataMaxParameter = new Parameter("dataMax", limitDates.getMaximum()); //$NON-NLS-1$
			dataMinParameter = new Parameter("dataMin", limitDates.getMinimum()); //$NON-NLS-1$
			query += " and (usuari.dataCreacio < :dataMax ) " //$NON-NLS-1$
					+ " and (usuari.dataCreacio > :dataMin )"; //$NON-NLS-1$
			finalParameters.add(dataMaxParameter);
			finalParameters.add(dataMinParameter);
		}
		query += " order by usuari.userName"; //$NON-NLS-1$
		if (restringeixCerca.booleanValue()) {
			CriteriaSearchConfiguration csc = new CriteriaSearchConfiguration();
			csc.setMaximumResultSize(201);
			return getUserEntityDao().query(query,
					finalParameters.toArray(new Parameter[0]), csc);
		} else
			return getUserEntityDao().query(query,
					finalParameters.toArray(new Parameter[0]));
	}

	protected Collection<User> handleFindUserByCriteria(String codi,
			String nom, String primerLlinatge, String nomCurt,
			String dataCreacio, String usuariCreacio, String actiu,
			String segonLlinatge, String multiSessio, String comentari,
			String tipusUsuari, String servidorPerfil, String servidorHome,
			String servidorCorreu, String codiGrupPrimari, String dni,
			String dominiCorreu, String grupSecundari, Boolean restringeixCerca)
			throws Exception {
		// Utilizado para hacer búsqueda desde usuaris.zul

		int limitResults = Integer.MAX_VALUE;
		try {
			limitResults = Integer.parseInt(ConfigurationCache
					.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		} catch (NumberFormatException e) {
		}

		LimitDates limitDates = null;
		if (dataCreacio != null && dataCreacio.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataCreacio.trim().compareTo("%") != 0) { //$NON-NLS-1$
			dataCreacio = dataCreacio.trim();
			String dataCreacioClean = null;
			limitDates = DateUtils.getLimitDatesFromQuery(dataCreacio);
		}

		if (codi != null && (codi.trim().compareTo("") == 0 || codi.trim() //$NON-NLS-1$
				.compareTo("%") == 0)) { //$NON-NLS-1$
			codi = null;
		}
		if (nom != null
				&& (nom.trim().compareTo("") == 0 || nom.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			nom = null;
		}
		if (primerLlinatge != null
				&& (primerLlinatge.trim().compareTo("") == 0 || primerLlinatge //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			primerLlinatge = null;
		}
		if (nomCurt != null
				&& (nomCurt.trim().compareTo("") == 0 || nomCurt.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			nomCurt = null;
		}
		if (usuariCreacio != null
				&& (usuariCreacio.trim().compareTo("") == 0 || usuariCreacio //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			usuariCreacio = null;
		}
		if (actiu != null
				&& (actiu.trim().compareTo("") == 0 || actiu.trim().compareTo( //$NON-NLS-1$
						"%") == 0)) { //$NON-NLS-1$
			actiu = null;
		}
		if (segonLlinatge != null
				&& (segonLlinatge.trim().compareTo("") == 0 || segonLlinatge //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			segonLlinatge = null;
		}
		if (multiSessio != null
				&& (multiSessio.trim().compareTo("") == 0 || multiSessio.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			multiSessio = null;
		}
		if (comentari != null
				&& (comentari.trim().compareTo("") == 0 || comentari.trim() //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			comentari = null;
		}
		if (tipusUsuari != null
				&& (tipusUsuari.trim().compareTo("") == 0 || tipusUsuari.trim() //$NON-NLS-1$
						.compareTo("%") == 0)) { //$NON-NLS-1$
			tipusUsuari = null;
		}
		if (servidorPerfil != null
				&& (servidorPerfil.trim().compareTo("") == 0 || servidorPerfil //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			servidorPerfil = null;
		}
		if (servidorHome != null
				&& (servidorHome.trim().compareTo("") == 0 || servidorHome //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			servidorHome = null;
		}
		if (servidorCorreu != null
				&& (servidorCorreu.trim().compareTo("") == 0 || servidorCorreu //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			servidorCorreu = null;
		}
		if (codiGrupPrimari != null
				&& (codiGrupPrimari.trim().compareTo("") == 0 || codiGrupPrimari //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			codiGrupPrimari = null;
		}
		if (dni != null
				&& (dni.trim().compareTo("") == 0 || dni.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			dni = null;
		}
		if (dominiCorreu != null
				&& (dominiCorreu.trim().compareTo("") == 0 || dominiCorreu //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			dominiCorreu = null;
		}
		if (grupSecundari != null
				&& (grupSecundari.trim().compareTo("") == 0 || grupSecundari //$NON-NLS-1$
						.trim().compareTo("%") == 0)) { //$NON-NLS-1$
			grupSecundari = null;
		}

		Collection<UserEntity> usuaris = findUsuarisByCriteri(codi, nom,
				primerLlinatge, nomCurt, dataCreacio, usuariCreacio, actiu,
				segonLlinatge, multiSessio, comentari, tipusUsuari,
				servidorPerfil, servidorHome, servidorCorreu, codiGrupPrimari,
				dni, dominiCorreu, grupSecundari, restringeixCerca); // UsuariEntity

		if (usuaris != null && usuaris.size() != 0) {
			// Ya tenemos los grupos del usuario con permisos
			Collection<UserEntity> usuarisPermis = filterUsers(usuaris);

			// Check maximum number of results
			if ((restringeixCerca != null) && (restringeixCerca.booleanValue())
					&& (usuarisPermis.size() > limitResults)) {
				return getUserEntityDao().toUserList(usuarisPermis).subList(0,
						limitResults);
			}

			if (usuarisPermis != null && usuarisPermis.size() != 0) {
				return getUserEntityDao().toUserList(usuarisPermis);
			}
		}
		return new Vector();
	}

	protected void handleDelete(User usuari) throws Exception {
		UserEntity usuariEntity = getUserEntityDao().findByUserName(
				usuari.getUserName());
		if (usuariEntity == null) return; // Already deleted
		
		if (!getAuthorizationService().hasPermission(Security.AUTO_USER_DELETE,
				usuariEntity)) {
			throw new SeyconAccessLocalException(
					"UsuariService", "delete (Usuari)", "user:delete, user:delete/*", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("UserServiceImpl.NoAuthorizedToDelete")); //$NON-NLS-1$
		}
		if (usuariEntity != null) {
			disableUser(usuari.getUserName());
			removeOldAlias(usuari);
			getUserDataEntityDao().remove(new LinkedList<UserDataEntity>(usuariEntity.getUserData()));
			usuariEntity.getUserData().clear();
			for (VaultFolderAccessEntity ua: new LinkedList<VaultFolderAccessEntity> ( usuariEntity.getVaultFolders()))
			{
				getVaultFolderAccessEntityDao().remove(ua);
			}
			for (UserPreferenceEntity ua: new LinkedList<UserPreferenceEntity> ( usuariEntity.getPreferences()))
			{
				getUserPreferenceEntityDao().remove(ua);
			}
			usuariEntity.getPreferences().clear();
			for (UserAccountEntity ua: new LinkedList<UserAccountEntity> ( usuariEntity.getAccounts()))
			{
				AccountEntity acc = ua.getAccount();
		        getAccountEntityDao().remove(acc);
		        acc.setType(AccountType.SHARED);
		        getAccountEntityDao().propagateChanges(acc);
			}
			usuariEntity.getAccounts().clear();
			for (PasswordEntity pass : usuariEntity.getPasswords())
			{
				getPasswordEntityDao().remove(pass);
			}
			usuariEntity.getPasswords().clear();
			
			for (SecretEntity secret: usuariEntity.getSecrets())
			{
				getSecretEntityDao().remove(secret);
			}
			usuariEntity.getSecrets().clear();

			for (IssueUserEntity iu: usuariEntity.getEvents()) {
				iu.setUser(null);
				getIssueUserEntityDao().update(iu);
			}
			
			getSessionEntityDao().remove(usuariEntity.getSessions());
			
			for (AccessLogEntity al: getAccessLogEntityDao().findLastAccessLogByUserName(usuariEntity.getUserName(),null))
				getAccessLogEntityDao().remove(al);


			getUserEntityDao().remove(usuariEntity);
		}
	}

	protected Collection<com.soffid.iam.api.NetworkAuthorization> handleFindNetworksACByUserName(
			String codiUsuari) throws Exception {
		UserEntity usuariEntity = getUserEntityDao().findByUserName(codiUsuari);
		if (usuariEntity != null) {
			Collection xarxesAC = usuariEntity.getACNetwork();
			if (xarxesAC != null) {
				return getNetworkAuthorizationEntityDao()
						.toNetworkAuthorizationList(xarxesAC);
			}
		}
		return null;
	}

	private void crearLlistaCorreu(User usuari) throws InternalErrorException {
		// El cridem des de create(Usuari)
		String aliesDeCorreuCollection = usuari.getMailAlias();
		if (aliesDeCorreuCollection != null
				&& aliesDeCorreuCollection.trim().compareTo("") != 0) { //$NON-NLS-1$
			String aliesDeCorreu[] = aliesDeCorreuCollection.split("[, ]+"); //$NON-NLS-1$
			for (int i = 0; i < aliesDeCorreu.length; i++) {
				String[] aliesDeCorreuParticionada = aliesDeCorreu[i].trim()
						.split("@");
				String alies = aliesDeCorreuParticionada[0];
				String domini = null;
				if (aliesDeCorreuParticionada.length == 2) {
					domini = aliesDeCorreuParticionada[1];
				}
				MailListsService llistesCorreuService = getMailListsService();
				MailList llistaCorreu = llistesCorreuService
						.findMailListByNameAndDomainName(alies, domini);
				if (llistaCorreu == null) {
					llistaCorreu = new MailList();
					llistaCorreu.setDomainCode(domini);
					llistaCorreu.setName(alies);
					llistesCorreuService.create(llistaCorreu);
				}
				UserMailList llistaCorreuUsuari = llistesCorreuService
						.findUserMailListByListNameAndDomainNameAndUserName(
								alies, domini, usuari.getUserName());
				if (llistaCorreuUsuari == null) {
					llistaCorreuUsuari = new UserMailList();
					llistaCorreuUsuari.setDomainCode(domini);
					llistaCorreuUsuari.setUserCode(usuari.getUserName());
					llistaCorreuUsuari.setMailListName(alies);
					llistesCorreuService.create(llistaCorreuUsuari);
				}
			}
		}
	}

	private void removeOldAlias(User usuari) throws InternalErrorException {
		// Des de delete(Usuari)
		String aliesDeCorreuCollection = usuari.getMailAlias();
		if (aliesDeCorreuCollection != null
				&& aliesDeCorreuCollection.trim().compareTo("") != 0) { //$NON-NLS-1$
			String aliesDeCorreu[] = aliesDeCorreuCollection.split("[ ,]+"); //$NON-NLS-1$
			for (int i = 0; i < aliesDeCorreu.length; i++) {
				String[] aliesDeCorreuParticionada = aliesDeCorreu[i].trim()
						.split("@");
				String alies = aliesDeCorreuParticionada[0];
				String domini = null;
				if (aliesDeCorreuParticionada.length == 2) {
					domini = aliesDeCorreuParticionada[1];
				}
				MailList llistaDeCorreu = this.getMailListsService()
						.findMailListByNameAndDomainName(alies, domini);
				if (llistaDeCorreu != null) {
					UserMailList llistaCorreuUsuari = this
							.getMailListsService()
							.findUserMailListByListNameAndDomainNameAndUserName(
									alies, domini, usuari.getUserName());
					if (llistaCorreuUsuari != null) {
						getMailListsService().deleteUserMailList(llistaCorreuUsuari);
					}
					Collection usuaris = getMailListsService()
							.findUsersByMailListNameAndDomainName(alies, domini);
					if (usuaris == null || usuaris.size() == 0) {
						getMailListsService().delete(llistaDeCorreu);
					}
				}
			}
		}
	}

	private void arreglaAlias(User usuari) throws InternalErrorException {
		// des de update(usuari), altaUsuari(Signatura) i baixaUsuari
		String aliesDeCorreuCollectionNou = usuari.getMailAlias();
		User usuariVell = findUserByUserName(usuari.getUserName());
		String aliesDeCorreuCollectionVell = null;
		if (usuariVell != null) {
			aliesDeCorreuCollectionVell = usuariVell.getMailAlias();
		} else {
			aliesDeCorreuCollectionVell = ""; //$NON-NLS-1$
		}

		String aliesDeCorreuNou[];
		boolean aliesTrobatNou[];
		if (aliesDeCorreuCollectionNou != null
				&& aliesDeCorreuCollectionNou.trim().compareTo("") != 0) { //$NON-NLS-1$
			aliesDeCorreuNou = aliesDeCorreuCollectionNou.split("[ ,]+"); //$NON-NLS-1$
			for (int i = 0; i < aliesDeCorreuNou.length; i++) {
				aliesDeCorreuNou[i] = aliesDeCorreuNou[i].trim();
			}
			aliesTrobatNou = new boolean[aliesDeCorreuNou.length];
			for (int i = 0; i < aliesTrobatNou.length; i++) {
				aliesTrobatNou[i] = false;
			}
		} else {
			aliesDeCorreuNou = new String[0];
			aliesTrobatNou = new boolean[0];
		}

		String aliesDeCorreuVell[];
		boolean aliesTrobatVell[];
		if (aliesDeCorreuCollectionVell != null
				&& aliesDeCorreuCollectionVell.trim().compareTo("") != 0) { //$NON-NLS-1$
			aliesDeCorreuVell = aliesDeCorreuCollectionVell.split("[ ,]+"); //$NON-NLS-1$
			for (int i = 0; i < aliesDeCorreuVell.length; i++) {
				aliesDeCorreuVell[i] = aliesDeCorreuVell[i].trim();
			}
			aliesTrobatVell = new boolean[aliesDeCorreuVell.length];
			for (int i = 0; i < aliesTrobatVell.length; i++) {
				aliesTrobatVell[i] = false;
			}
		} else {
			aliesDeCorreuVell = new String[0];
			aliesTrobatVell = new boolean[0];
		}

		for (int i = 0; i < aliesDeCorreuNou.length; i++) {
			for (int j = 0; j < aliesDeCorreuVell.length; j++) {
				if (aliesIguals(aliesDeCorreuNou[i], aliesDeCorreuVell[j])) {
					aliesTrobatNou[i] = true;
					aliesTrobatVell[j] = true;
				}
			}
		}

		for (int i = 0; i < aliesDeCorreuNou.length; i++) {
			if (!aliesTrobatNou[i] && aliesDeCorreuNou[i].length() > 0) {
				crearIAssociarLlista(aliesDeCorreuNou[i], usuari.getUserName());
			}
		}

		for (int i = 0; i < aliesDeCorreuVell.length; i++) {
			if (!aliesTrobatVell[i] && aliesDeCorreuVell[i].length() > 0) {
				desassociaIEsborraLlista(aliesDeCorreuVell[i],
						usuari.getUserName());
			}
		}

	}

	private void desassociaIEsborraLlista(String aliesDeCorreu,
			String codiUsuari) throws InternalErrorException {
		// des de arreglaAlias - update (usuari)
		String parts[] = aliesDeCorreu.split("@"); //$NON-NLS-1$
		String alies = parts[0];
		String domini = null;
		if (parts.length == 2) {
			domini = parts[1];
		}

		UserMailList llistaCorreuUsuari = getMailListsService()
				.findUserMailListByListNameAndDomainNameAndUserName(alies,
						domini, codiUsuari);
		if (llistaCorreuUsuari != null) {
			getMailListsService().deleteUserMailList(llistaCorreuUsuari);
		}

		// La neteja de llistes es fa al delete de listaCorreuUsuari
		// Això es comprova ara a nivell de llistesDeCorreuUsuari.delete()

		/*
		 * LlistaCorreu llistaCorreu = getLlistesDeCorreuService()
		 * .findLlistaCorreuByNomAndCodiDomini(alies, domini); if (llistaCorreu
		 * != null) { Collection correuseExterns = getLlistesDeCorreuService()
		 * .findCorreusExternsByNomLlistaCorreuAndCodiDomini(alies, domini);
		 * Collection usuaris = getLlistesDeCorreuService()
		 * .findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDomini( alies,
		 * domini); Collection llistesDeCorreuConte =
		 * getLlistesDeCorreuService()
		 * .findRelacionsLlistaCorreuByNomLlistaCorreuConteAndCodiDomini( alies,
		 * domini); Collection llistesDeCorreuPertany =
		 * getLlistesDeCorreuService()
		 * .findRelacionsLlistaCorreuByNomLlistaCorreuPertanyAndCodiDomini(
		 * alies, domini); if (correuseExterns.size() == 0 && usuaris.size() ==
		 * 0 && llistesDeCorreuConte.size() == 0 &&
		 * llistesDeCorreuPertany.size() == 0) {
		 * getLlistesDeCorreuService().delete(llistaCorreu); } }
		 */

	}

	private void crearIAssociarLlista(String aliesDeCorreu, String codiUsuari)
			throws InternalErrorException {
		// de arreglaAlias(usuari) - update(usuari)
		String parts[] = aliesDeCorreu.split("@"); //$NON-NLS-1$
		String alies = parts[0];
		String domini = null;
		if (parts.length == 2) {
			domini = parts[1];

			if (getMailListsService().findMailListByNameAndDomainName(alies,
					domini) == null) {
				MailList llistaDeCorreu = new MailList();
				llistaDeCorreu.setDomainCode(domini);
				llistaDeCorreu.setName(alies);
				getMailListsService().create(llistaDeCorreu);
			}

			UserMailList llistaCorreuUsuari = new UserMailList();
			llistaCorreuUsuari.setDomainCode(domini);
			llistaCorreuUsuari.setMailListName(alies);
			llistaCorreuUsuari.setUserCode(codiUsuari);
			getMailListsService().create(llistaCorreuUsuari);
		} else {
            throw new InternalErrorException(String.format("%s is not a valid email address", aliesDeCorreu));
        }
	}

	private boolean aliesIguals(String aliesDeCorreuNou,
			String aliesDeCorreuVell) {
		// de arreglaAlias
		String partsNou[] = aliesDeCorreuNou.split("@"); //$NON-NLS-1$
		String aliesNou = partsNou[0];
		String dominiNou = null;
		if (partsNou.length == 2) {
			dominiNou = partsNou[1];
		}

		String partsVell[] = aliesDeCorreuVell.split("@"); //$NON-NLS-1$
		String aliesVell = partsVell[0];
		String dominiVell = null;
		if (partsVell.length == 2) {
			dominiVell = partsVell[1];
		}

		return ((dominiNou == null && dominiVell == null) || (dominiNou != null
				&& dominiVell != null && dominiNou.compareTo(dominiVell) == 0))
				&& aliesNou.compareTo(aliesVell) == 0;
	}

	protected User handleUpdate(User usuari) throws Exception {
		UserEntity usu = getUserEntityDao().findById(usuari.getId());

		boolean canUpdateEsteUser = getAuthorizationService().hasPermission(
				Security.AUTO_USER_UPDATE, usu);

		// Comprobamos autorización del usuario
		// Si tiene customUpdate también puede pasar (!!) aunque en teoría (!!)
		// no puede actualizar nada
		if (!canUpdateEsteUser
				&& !getAuthorizationService().hasPermission(
						Security.AUTO_USER_UPDATE_CUSTOM, usu)) {
			throw new SeyconAccessLocalException(
					"UsuariService", "update (Usuari)", "user:update, user:update/*", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("UserServiceImpl.NoAuthorizedToUpdate")); //$NON-NLS-1$
		}

		// Comprovem que s'hagi especificat el tipus d'usuari de domini
		if (usuari.getUserType() == null) {
			throw new SeyconException(
					Messages.getString("UserServiceImpl.UserTypeNotEspecified")); //$NON-NLS-1$
		}

		if (!canUpdateEsteUser) {
			throw new SeyconAccessLocalException(
					"UsuariService", "update (Usuari)", "user:update, user:update/*", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("UserServiceImpl.NoAuthorizedToUpdate")); //$NON-NLS-1$
		}

		// Ara hem de comprovar que si es modifica l'usuari [nom,llinatges o
		// DNI, es verifique que siga correcte]
		UserEntity usuariAbans = usuari.getId() != null ? getUserEntityDao()
				.load(usuari.getId()) : getUserEntityDao().findByUserName(
				usuari.getUserName());
		User previousUser = getUserEntityDao().toUser(usuariAbans);

		// Check no concurrent modificacions allowed
		if (usuari.getModifiedDate() != null
				&& usuariAbans.getLastModificationDate() != null) {
			if (!Security.getCurrentAccount().equals(
					previousUser.getModifiedByUser())
					&& !usuari.getModifiedDate().equals(
							previousUser.getModifiedDate())) {
				log.warn("User modification conflict");
				log.warn("Change requested by   :" + Security.getCurrentAccount());
				log.warn("Change timestamp      :" + usuari.getModifiedDate().getTime().getTime());
				log.warn("Last change done by   :" + previousUser.getModifiedByUser());
				log.warn("Last change timestamp :" + previousUser.getModifiedDate().getTime().getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				throw new InternalErrorException(
						String.format(
								"The user %s cannot be modified as it has been modified by %s t %s",
								previousUser.getUserName(), previousUser
										.getModifiedByUser(), sdf
										.format(usuariAbans
												.getLastModificationDate())));
			}
		}

		
		if (previousUser.getActive().booleanValue() && !usuari.getActive().booleanValue())
		{
			if (!getAuthorizationService().hasPermission("user:disable", usu))
				throw new SecurityException("Access denied. Required roles: [user:disable]");
			auditChange("e", previousUser.getUserName(), null);
		}
		if (! previousUser.getActive().booleanValue() && usuari.getActive().booleanValue())
		{
			if (!getAuthorizationService().hasPermission("user:enable", usu))
				throw new SecurityException("Access denied. Required roles: [user:enable]");
			auditChange("E", previousUser.getUserName(), null);
		}

		// Updates user name (if needed)
		if (!usuariAbans.getUserName().equals(usuari.getUserName())) {
			for (UserProcessEntity upe : getUserProcessEntityDao()
					.findByUserName(usuariAbans.getUserName())) {
				upe.setUserName(usuari.getUserName());
				getUserProcessEntityDao().update(upe);
			}
			String oldName = usuariAbans.getUserName();
			usuariAbans.setUserName(usuari.getUserName());
			getUserEntityDao().update(usuariAbans);
			getUserEntityDao().createUpdateTasks(usuariAbans, previousUser);
			getMetaDataEntityDao().renameAttributeValues(TypeEnumeration.USER_TYPE, 
					oldName, usuari.getUserName());
		}

		/* IAM-318: Propagar canvis de grup primari */
		GroupService service = getGroupService();
		boolean revokeHolderGroupRoles = false;
		Long groupHolderToRemove = null;
		if (!usuari.getPrimaryGroup().equals(
				usuariAbans.getPrimaryGroup().getName())) {
			revokeHolderGroupRoles = true;
			groupHolderToRemove = usuariAbans.getPrimaryGroup().getId();
			service.propagateRolsChangesToDispatcher(usuari.getPrimaryGroup());
			service.propagateRolsChangesToDispatcher(usuariAbans
					.getPrimaryGroup().getName());
			
			if (ConfigurationCache.isHistoryEnabled())
			{
				for (UserGroupEntity uge: usuariAbans.getSecondaryGroups())
				{
					if ( uge.getEnd() == null && Boolean.TRUE.equals ( uge.getDisabled() ) && 
							Boolean.TRUE.equals(uge.getPrimaryGroup()))
					{
						uge.setEnd(new Date());
						getUserGroupEntityDao().update(uge);
					}
				}
				UserGroupEntity uge = getUserGroupEntityDao().newUserGroupEntity();
				GroupEntity group = getGroupEntityDao().findByName( usuari.getPrimaryGroup());
				if (group != null)
				{
					uge.setGroup( group );
					uge.setUser(usuariAbans);
					uge.setDisabled(Boolean.TRUE);
					uge.setStart(new Date());
					uge.setPrimaryGroup(Boolean.TRUE);
					getUserGroupEntityDao().create(uge);
				}
			}

		}

		arreglaAlias(usuari);
		usuari.setCreatedByUser(previousUser.getCreatedByUser());
		usuari.setCreatedDate(previousUser.getCreatedDate());
		usuari.setModifiedByUser(Security.getCurrentAccount());
		usuari.setModifiedDate(GregorianCalendar.getInstance());
		auditUserChages(usuari, previousUser);
		UserEntity entity = getUserEntityDao().userToEntity(usuari);
		if (entity != null) {
			if (usuari.getAttributes() != null)
				handleUpdateUserAttributes(usuari.getUserName(), usuari.getAttributes(), false);
			getUserEntityDao().update(entity);
			
			getAccountService().generateUserAccounts(usuari.getUserName());
			if (revokeHolderGroupRoles)
				getApplicationService().revokeRolesHoldedOnGroup(
						usuariAbans.getId(), groupHolderToRemove);

			getRuleEvaluatorService().applyRules(entity);

			getUserEntityDao().createUpdateTasks(usuariAbans, previousUser);

			User u = getUserEntityDao().toUser(entity);
			usuari.setModifiedDate(u.getModifiedDate());
			usuari.setModifiedByUser(u.getModifiedByUser());
			return u;
		}

		return null;
	}

	private void auditUserChages(User usuari, User usuariAbans) throws InternalErrorException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (PropertyDescriptor p: PropertyUtils.getPropertyDescriptors(usuari)) {
			if (! "modifiedDate".equals(p.getName()) &&
					! "modifiedByUser".equals(p.getName()) &&
					! "modifiedBy".equals(p.getName()) &&
					! "modifiedOn".equals(p.getName()) &&
					! "shortName".equals(p.getName()) &&
					! "mailDomain".equals(p.getName()) &&
					! "attributes".equals(p.getName()) &&
					! "createdByUser".equals(p.getName()) &&
					! "createdBy".equals(p.getName()) &&
					! "lastName2".equals(p.getName()) &&
					! "fullName".equals(p.getName()) &&
					! "createdOn".equals(p.getName()) &&
					! "createdDate".equals(p.getName())) {
				Object v1  = PropertyUtils.getProperty(usuariAbans, p.getName());
				Object v2 = PropertyUtils.getProperty(usuari, p.getName());
				if ((v1 == null || v1.equals("")) ? 
						v2 != null && !v2.equals(""):
						! v1.equals(v2))
					auditChange(usuari.getUserName(), p.getName());
			}
		}
	}

	protected Group handleFindGrupPrimariByCodiUsuari(String codiUsuari)
			throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		GroupEntity grupPrimariEntity = usuari.getPrimaryGroup();
		Group grupPrimari = getGroupEntityDao().toGroup(grupPrimariEntity);
		return grupPrimari;
	}

	protected Host handleFindMailServerByUserName(String codiUsuari)
			throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		HostEntity maquinaEntity = usuari.getMailServer();
		if (maquinaEntity != null) {
			Host maquina = getHostEntityDao().toHost(maquinaEntity);
			return maquina;
		}
		return null;
	}

	protected Host handleFindHomeServerByUserName(String codiUsuari)
			throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		HostEntity maquinaEntity = usuari.getHomeServer();
		if (maquinaEntity != null) {
			Host maquina = getHostEntityDao().toHost(maquinaEntity);
			return maquina;
		}
		return null;
	}

	protected Host handleFindProfileServerByUserName(String codiUsuari)
			throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		HostEntity maquinaEntity = usuari.getProfileServer();
		if (maquinaEntity != null) {
			Host maquina = getHostEntityDao().toHost(maquinaEntity);
			return maquina;
		}
		return null;

	}

	/*
	 * Aquí obtenim totes les dades de l'usuari EXCEPTE el número de TELÈFON i
	 * el NIF (!!) (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.UsuariServiceBase#handleFindDadesUsuariByCodiUsuari
	 * (java.lang.String)
	 */
	protected Collection<UserData> handleFindUserDataByUserName(
			String codiUsuari) throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		Collection<UserDataEntity> dades = usuari.getUserData();
		LinkedList<UserData> result = new LinkedList<UserData>();

		List<MetaDataEntity> tipusDades = getMetaDataEntityDao().loadAll();
		Collections.sort(tipusDades, new Comparator<MetaDataEntity>() {

			public int compare(MetaDataEntity o1, MetaDataEntity o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});

		Iterator<MetaDataEntity> tipusDadesIterator = tipusDades.iterator();
		AuthorizationService authSvc = getAuthorizationService();
		while (tipusDadesIterator.hasNext()) {
			MetaDataEntity tipusDada = tipusDadesIterator.next();
			if (tipusDada.getName().compareTo(NIF) != 0
					&& tipusDada.getName().compareTo(TELEFON) != 0 &&
					(tipusDada.getScope() == null || tipusDada.getScope() == MetadataScope.USER) &&
					! Boolean.TRUE.equals( tipusDada.getBuiltin())) {
				Iterator<UserDataEntity> dadesIterator = dades.iterator();
				boolean teTipusDada = false;
				while (dadesIterator.hasNext()) {
					UserDataEntity dada = dadesIterator.next();
					if (dada.getDataType().getName().compareTo(NIF) != 0
							&& dada.getDataType().getName().compareTo(TELEFON) != 0 
							&& dada.getDataType().getName().compareTo(tipusDada.getName()) == 0)
					{
						teTipusDada = true;
						if (Security.isSyncServer() ||
								! dada.getAttributeVisibility().equals(AttributeVisibilityEnum.HIDDEN))
							result.add(getUserDataEntityDao().toUserData(dada));
					}
				}
				if (!teTipusDada) {
					UserDataEntity dus = getUserDataEntityDao()
							.newUserDataEntity();
					dus.setUser(usuari);
					dus.setDataType(tipusDada);
					if (Security.isSyncServer() ||
								! dus.getAttributeVisibility().equals(AttributeVisibilityEnum.HIDDEN))
					{
						result.add(getUserDataEntityDao().toUserData(dus));
					}
				}
			}
		}

		return result;
	}

	protected UserData handleFindDataByUserAndCode(String codiUsuari,
			String codiTipusDada) throws Exception {
		for (UserDataEntity dadaUsuariEntity: getUserDataEntityDao()
				.findByDataType(codiUsuari, codiTipusDada))
		{
			UserData dadaUsuari = getUserDataEntityDao().toUserData(
					dadaUsuariEntity);
			return dadaUsuari;
		}
		return null;
	}

	protected Collection<DataType> handleGetDataType() throws Exception {
		return getMetaDataEntityDao().toDataTypeList(
				getMetaDataEntityDao().loadAll());
	}

	protected Collection<Session> handleFindSessionByUserName(String codiUsuari)
			throws Exception {
		UserEntity userEntity = getUserEntityDao().findByUserName(codiUsuari);

		if (userEntity != null
				&& getAuthorizationService().hasPermission(
						Security.AUTO_USER_SESSION_QUERY, userEntity)) {
			Collection<SessionEntity> sessions = userEntity.getSessions();
			if (sessions != null) {
				return getSessionEntityDao().toSessionList(sessions);
			}
		}
		return Collections.emptyList();
	}

	protected Collection handleFindUsuariImpressoraByCodiUsuari(
			String codiUsuari) throws Exception {
		Collection<UserPrinterEntity> usuariImpressores = getUserEntityDao()
				.findByUserName(codiUsuari).getPrinters();
		if (usuariImpressores != null) {
			return getUserPrinterEntityDao().toPrinterUserList(
					usuariImpressores);
		}
		return new Vector();
	}

	protected void handleAddGrupToUsuari(String codiUsuari, String codiGrup)
			throws Exception {
		/*
		 * UsuariEntity usuariEntity =
		 * getUsuariEntityDao().findByCodi(codiUsuari); GrupEntity GrupEntity =
		 * getGrupEntityDao().findByCodi(codiGrup); UsuariGrup usuariGrup = new
		 * UsuariGrup(); usuariGrup.setCodiUsuari(codiUsuari);
		 * usuariGrup.setCodiGrup(codiGrup); UsuariGrupEntity usuariGrupEntity =
		 * getUsuariGrupEntityDao() .usuariGrupToEntity(usuariGrup);
		 * this.getUsuariGrupEntityDao().create(usuariGrupEntity);
		 */
		GroupService service = getGroupService();
		service.addGroupToUser(codiUsuari, codiGrup);
	}

	protected Collection<Application> handleGetApplicationsByUserName(
			String codiUsuari) throws Exception {
		Collection aplicacions = getInformationSystemEntityDao().findByUser(
				codiUsuari);
		if (aplicacions != null) {
			return getInformationSystemEntityDao().toApplicationList(
					aplicacions);
		}
		return new LinkedList();
	}

	protected Collection<Application> handleGetBpmEnabledApplicationsByUserName(
			String codiUsuari) throws Exception {
		Collection aplicacions = getInformationSystemEntityDao()
				.findManageableByUser(codiUsuari);

		if (aplicacions != null) {
			return getInformationSystemEntityDao().toApplicationList(
					aplicacions);
		}
		return new LinkedList();
	}

	protected Collection<Role> handleGetApplicationRolesByuserNameAndApplicationName(
			String codiUsuari, String codiAplicacio) throws Exception {
		Collection rols = getRoleEntityDao()
				.findApplicationRolesByUserAndInformationSystem(codiUsuari,
						codiAplicacio);
		if (rols != null) {
			return getRoleEntityDao().toRoleList(rols);
		}
		return new Vector();
	}

	protected Collection handleGetRolsByCodiUsuari(String codiUsuari)
			throws Exception {
		Collection rols = getRoleEntityDao().findRolesByUserName(codiUsuari);
		if (rols != null) {
			return getRoleEntityDao().toRoleList(rols);
		}
		return new LinkedList();
	}

	private Group getGrupByTipus(String codiGrup, String tipus)
			throws InternalErrorException {
		if (codiGrup != null) {// ?
			Group grup = getGroupService().findGroupByGroupName(codiGrup);
			String tipusGrup = grup.getType(); // Unitat Organizativa
			if (tipusGrup != null) {
				if (tipusGrup.compareTo(tipus) == 0) {
					return grup;
				} else {
					return getGrupByTipus(grup.getParentGroup(), tipus);
				}
			}
		}
		return null;
	}

	protected Collection<Group> handleGetConselleriesByCodiUsuari(
			String codiUsuari) throws Exception {
		String conselleriaCode = "CONSELLERIA"; //$NON-NLS-1$
		Collection<Group> conselleries = new LinkedHashSet();
		Group grupPrimari = this.getGroupService().findPrimaryGroupByUserName(
				codiUsuari);
		Group conselleria = null;
		if (grupPrimari != null) {
			conselleria = getGrupByTipus(grupPrimari.getName(), conselleriaCode);
			if (conselleria != null) {
				conselleries.add(conselleria);
			}
		}
		Collection grupsFromUsuaris = getGroupService()
				.findGroupsFromUsersByUserName(codiUsuari);
		Iterator iterator = grupsFromUsuaris.iterator();
		while (iterator.hasNext()) {
			Group grup = (Group) iterator.next();
			conselleria = getGrupByTipus(grup.getName(), conselleriaCode);
			if (conselleria != null) {
				conselleries.add(conselleria);
			}
		}
		Collection grupsFromRols = getGroupService()
				.findGroupsFromRolesByUserName(codiUsuari);
		iterator = grupsFromRols.iterator();
		while (iterator.hasNext()) {
			Group grup = (Group) iterator.next();
			conselleria = getGrupByTipus(grup.getName(), conselleriaCode);
			if (conselleria != null) {
				conselleries.add(conselleria);
			}
		}
		return conselleries;
	}

	protected Collection<Group> handleGetDireccionsGeneralsByCodiUsuari(
			String codiUsuari) throws Exception {
		String direccioGeneralCode = "DIRECCIO_GENERAL"; //$NON-NLS-1$
		Collection<Group> direccionsGenerals = new LinkedHashSet();
		Group grupPrimari = this.getGroupService().findPrimaryGroupByUserName(
				codiUsuari);
		Group direccioGeneral = null;
		if (grupPrimari != null) {
			direccioGeneral = getGrupByTipus(grupPrimari.getName(),
					direccioGeneralCode);
			if (direccioGeneral != null) {
				direccionsGenerals.add(direccioGeneral);
			}
		}
		Collection grupsFromUsuaris = getGroupService()
				.findGroupsFromUsersByUserName(codiUsuari);
		Iterator iterator = grupsFromUsuaris.iterator();
		while (iterator.hasNext()) {
			Group grup = (Group) iterator.next();
			direccioGeneral = getGrupByTipus(grup.getName(),
					direccioGeneralCode);
			if (direccioGeneral != null) {
				direccionsGenerals.add(direccioGeneral);
			}
		}
		Collection grupsFromRols = getGroupService()
				.findGroupsFromRolesByUserName(codiUsuari);
		iterator = grupsFromRols.iterator();
		while (iterator.hasNext()) {
			Group grup = (Group) iterator.next();
			direccioGeneral = getGrupByTipus(grup.getName(),
					direccioGeneralCode);
			if (direccioGeneral != null) {
				direccionsGenerals.add(direccioGeneral);
			}
		}
		return direccionsGenerals;
	}

	protected Boolean handleShortNameExists(String nomCurt) throws Exception {
		UserEntity usuariEntity = this.getUserEntityDao().findByShortName(
				nomCurt);
		return new Boolean(usuariEntity != null);
	}

	protected User handleFindByShortName(String nomCurt) throws Exception {
		UserEntity usuariEntity = getUserEntityDao().findByShortName(nomCurt);
		if (usuariEntity != null) {
			User usuari = getUserEntityDao().toUser(usuariEntity);
			return usuari;
		}
		return null;
	}

	protected Collection<User> handleFindUsersByCoreData(String codi,
			String nom, String primerLlinatge, String segonLlinatge, String dni)
			throws Exception {
		return findUserByCriteria(codi, nom, primerLlinatge, null, null, null,
				null, segonLlinatge, null, null, null, null, null, null, null,
				dni, null, null, new Boolean(true));
	}

	protected Collection<PrinterUser> handleFindUserPrintersByUserName(
			String codiUsuari) throws Exception {
		Collection usuariImpressores = getUserPrinterEntityDao().findByUser(
				codiUsuari);
		if (usuariImpressores != null) {
			return getUserPrinterEntityDao().toPrinterUserList(
					usuariImpressores);
		}
		return new Vector();
	}

	protected String[] handleRefreshChanges(String codiUsuari) throws Exception {
		String status = ConfigurationCache.getProperty("soffid.task.mode");
		if ("readonly".equals( status ))
			throw new InternalErrorException ("Task configuration setting is in read only mode");
		String tasques = getUserEntityDao().refreshCanvis(codiUsuari);
		if (tasques != null) {
			try {
				return tasques.split("\n"); //$NON-NLS-1$
			} catch (Exception e) {
				return new String[] { tasques };
			}
		}
		return new String[0];
	}

	@Override
	protected String handleSetTemporaryPassword(String codiUsuari,
			String codiDominiContrasenyes) throws Exception {
		String pass = setRandomPassword(codiUsuari, codiDominiContrasenyes, true);
		return pass;
	}

	@Override
	protected String handleSetPassword(String codiUsuari,
			String codiDominiContrasenyes) throws Exception {
		String pass = setRandomPassword(codiUsuari, codiDominiContrasenyes, false);
		return pass;
	}

	public String setRandomPassword(String codiUsuari, String codiDominiContrasenyes, boolean mustChange) throws InternalErrorException {
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		if (usuari != null && "S".equals(usuari.getActive())) { //$NON-NLS-1$
			if (getAuthorizationService().hasPermission(
					Security.AUTO_USER_UPDATE_PASSWORD, usuari)) {
				PasswordDomainEntity dominiContrasenyes = getPasswordDomainEntityDao()
						.findByName(codiDominiContrasenyes);
				Password pass = getInternalPasswordService()
						.generateNewPassword(usuari, dominiContrasenyes, mustChange);
				if (pass == null)
					throw new InternalErrorException("Unable to generate password. Missing password policy");
				auditaCanviPassword(codiUsuari, dominiContrasenyes.getName());
				return pass.getPassword();
			} else {
				throw new SecurityException(
						String.format(
								Messages.getString("UserServiceImpl.NoAuthorizedToChangePass"), codiUsuari)); //$NON-NLS-1$
			}
		} else {
			throw new SeyconException(
					Messages.getString("UserServiceImpl.UserInactiveToChangePass")); //$NON-NLS-1$
		}
	}

	transient private Random random = new SecureRandom();

	private void auditaCanviPassword(String codiUsuariAuditat,
			String codiDominiContrasenyes) {
		auditChange ("P", codiUsuariAuditat, codiDominiContrasenyes);
	}
	
	private void auditChange(String action, String codiUsuariAuditat, String codiDominiContrasenyes) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(action); //$NON-NLS-1$
		auditoria.setUser(codiUsuariAuditat);
		auditoria.setAuthor(codiUsuari);
		auditoria.setObject("SC_USUARI"); //$NON-NLS-1$
		auditoria.setPasswordDomain(codiDominiContrasenyes);

		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	protected String[] handleGetTasks(String codiUsuari) throws Exception {
		String[] resultats = getUserEntityDao().getTasks(codiUsuari);
		return resultats;
	}

	protected String handleGetFollowingName() throws Exception {
		String codi = getUserEntityDao().getNextUserName();
		return codi;
	}

	protected User handleFindUserByUserId(Long idUsuari) throws Exception {
		UserEntity usuariEntity = getUserEntityDao().findById(idUsuari);
		if (usuariEntity != null) {
			return getUserEntityDao().toUser(usuariEntity);
		}
		return null;
	}

	protected User handleFindUserByDataTypeNameAndDataTypeValue(
			String codiTipusDada, String valorTipusDada) throws Exception {
		// utilitzat en el autoenrollment versió 3.1
		UserEntity usuariEntity = getUserEntityDao().findUserByDataValue(
				codiTipusDada, valorTipusDada);
		if (usuariEntity != null)
			return getUserEntityDao().toUser(usuariEntity);
		else
			return null;

	}

	private UserEntity findUsuariExistentperCodiXestib(String codiXestib) {
		// obtenim el codi d'usuari a partir del codi Xestib
		try {
			UserEntity usuariExistent = getUserEntityDao().findUserByDataValue(
					DADA_ADDICIONAL_CODI_XESTIB, codiXestib);
			return usuariExistent;
		} catch (Throwable th) {
			return null;
		}
	}

	private GroupEntity gestionaGrupAlumne(String codiCentre) throws Exception {
		GroupEntity grupCentreAlumne = getGroupEntityDao().findByName(
				"ce" + codiCentre + "-a"); //$NON-NLS-1$ //$NON-NLS-2$

		if (grupCentreAlumne != null)
			return grupCentreAlumne;
		else {
			// Creem el nou grup
			// Han de descendre del grupo d'alumnes
			GroupEntity g_alumnes = getGroupEntityDao().findByName("alumnes"); //$NON-NLS-1$
			if (g_alumnes == null)
				throw new SeyconException(
						Messages.getString("UserServiceImpl.AlumGourpNotFounded")); //$NON-NLS-1$
			GroupEntity g_centre = getGroupEntityDao().findByName(
					"ce" + codiCentre); //$NON-NLS-1$
			if (g_centre == null)
				throw new SeyconException(
						String.format(
								Messages.getString("UserServiceImpl.NoExistsGroup"), codiCentre)); //$NON-NLS-1$
			// Creem el grup de l'alumne
			Group grupAlumne = new Group();
			grupAlumne.setName("ce" + codiCentre + "-a"); //$NON-NLS-1$ //$NON-NLS-2$
			grupAlumne
					.setDescription("Alumnes de " + g_centre.getDescription()); //$NON-NLS-1$
			grupAlumne.setQuota("0"); //$NON-NLS-1$
			grupAlumne.setParentGroup(g_alumnes.getName()); // Descentent del
															// grup
															// "alumnes"
			grupAlumne.setType("ALUMNE"); // de tipus ALUMNE //$NON-NLS-1$
			GroupEntity grupAlumneE = getGroupEntityDao().groupToEntity(
					grupAlumne);
			getGroupEntityDao().create(grupAlumneE);
			return grupCentreAlumne;
		}
	}

	/**
	 * Generem una contrasenya de lletras i digits (2 digits)
	 * 
	 * @param longitud
	 * @return
	 */
	private String generaContrasenyaAlumne(int longitud) {
		char[] pw = new char[longitud];
		int c = 'A';
		int r1 = 0;
		int numDigits = 0;
		// Generem longitud-2 lletres i 2 números
		for (int i = 0; i < longitud - 2; i++) {
			c = 'a' + (int) (Math.random() * 26);
			pw[i] = (char) c;
		}
		for (int i = longitud - 2; i >= 0 && i < longitud; i++) {
			c = '0' + (int) (Math.random() * 10);
			pw[i] = (char) c;
		}

		return new String(pw);
	}

	protected ExtranetCard handleCreateExtranetCard(String codiUsuari)
			throws Exception {
		
		return null;
	}

	protected Collection<ExtranetCard> handleFindExtranetCardsByUserName(
			String codiUsuari, String activa) throws Exception {

		UserEntity ue = getUserEntityDao().findByUserName(codiUsuari);
		if (ue == null)
			return null;
		LinkedList<ExtranetCard> cards = new LinkedList<ExtranetCard>();
		return cards;
	}

	protected ExtranetCard handleUpdate(ExtranetCard targetaExtranet)
			throws Exception {
		return null;
	}

	protected ExtranetCard handleFindExtranetCardByUserNameAndCardName(
			String codiUsuari, String codiTargeta) throws Exception {
			throw new SeyconException(
					Messages.getString("UserServiceImpl.NoCardFounded")); //$NON-NLS-1$
	}

	protected Collection<Role> handleFindUserRolesHierachyByUserName(
			String codiUsuari) throws Exception {

		return handleFindUserRolesHierachyByUserName(codiUsuari, new Boolean(
				true));

	}

	protected Collection<Role> handleFindUserRolesHierachyByUserName(
			String codiUsuari, Boolean incloureRolsDirectes) throws Exception {
		// Obtenemos el usuario
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);

		if (usuari == null)
			return new ArrayList(); // usuari nobody

		List<Role> roles = new LinkedList<Role>();
		for (RoleGrant rg : getApplicationService()
				.findEffectiveRoleGrantByUser(usuari.getId())) {
			if (incloureRolsDirectes.booleanValue()
					|| rg.getOwnerGroup() != null || rg.getOwnerRole() != null) {
				RoleEntity r = getRoleEntityDao().load(rg.getRoleId());
				roles.add(getRoleEntityDao().toRole(r));
			}
		}
		return roles;
	}

	/**
	 * Obté la configuració del servei de verificació d'identitat de l'usuari o
	 * null si no és actiu (per estalviar ús de la seqüència)
	 * 
	 * @return
	 */
	private com.soffid.iam.api.Configuration getConfiguracioServeiVerificacioIndentitatUsuari() {
		try {
			return getConfigurationService().findParameterByNameAndNetworkName(
					"webserviceSVDI", null); //$NON-NLS-1$
		} catch (Throwable th) {

		}
		return null;
	}

	/**
	 * Obté la configuració del servei de comprovació d'identitat de l'usuari o
	 * null si no és actiu (per estalviar ús de la seqüència)
	 * 
	 * @return
	 */
	private com.soffid.iam.api.Configuration getConfiguracioServeiComprovacioIndentitatUsuari() {
		try {
			return getConfigurationService().findParameterByNameAndNetworkName(
					"webserviceSCDI", null); //$NON-NLS-1$
		} catch (Throwable th) {

		}
		return null;
	}

	protected java.util.Collection<com.soffid.iam.api.PasswordStatus> handleGetPasswordsUserType(
			Date dataInici, Date dataFi, String tipusUsuari) throws Exception {
		UserTypeEntity tipus = getUserTypeEntityDao().findByName(tipusUsuari);
		if (tipus == null)
			throw new IllegalArgumentException(
					String.format(
							Messages.getString("UserServiceImpl.InvalidUserType"), tipusUsuari)); //$NON-NLS-1$
		return getInternalPasswordService().getExpiredPasswords(dataInici,
				dataFi, tipus);

	}

	protected String handleChangePasswordUserHost(String codiUsuari,
			String codiDominiContrasenyes) throws Exception {
		throw new RuntimeException(
				Messages.getString("UserServiceImpl.NotImplementedMessage")); //$NON-NLS-1$

	}

	protected void handleSpreadPassword(String codiUsuari, String contrasenya)
			throws Exception {
		throw new RuntimeException(
				Messages.getString("UserServiceImpl.NotImplementedMessage")); //$NON-NLS-1$

	}

	protected String handleGenerateRandomPassword() throws Exception {
		// Emprem mètode com a PasswordCache.
		boolean hasNumber = false;
		String password;
		do {
			password = ""; //$NON-NLS-1$

			while (password.length() < 8) {
				int r = random.nextInt() % 36;
				if (r < 10 && password.length() > 1) {
					password = password + ('0' + r);
					hasNumber = true;
				} else if (r >= 10) {
					password = password + (char) ('a' + (r - 10));
				}
			}
		} while (hasNumber == false);
		return password;
	}

	protected User handleUpdateUserCoreData(User usuari) throws Exception {

		if (usuari.getId() == null || usuari.getUserName() == null) {
			throw new SeyconException(
					Messages.getString("UserServiceImpl.UserNotFounded")); //$NON-NLS-1$
		}
		UserEntity usuariEntity = getUserEntityDao().findByUserName(
				usuari.getUserName());

		if (usuariEntity == null)
			throw new SeyconException(
					Messages.getString("UserServiceImpl.UserNotFounded")); //$NON-NLS-1$

		usuariEntity.setFirstName(usuari.getFirstName());
		usuariEntity.setLastName(usuari.getLastName());
		usuariEntity.setMiddleName(usuari.getMiddleName());

		String currentAccount = Security.getCurrentAccount();
		// Marquem que l'usuari ha estat modificat
		usuariEntity
				.setLastUserModification(currentAccount == null ? "Soffid": currentAccount); //$NON-NLS-1$
		usuariEntity.setLastModificationDate(GregorianCalendar.getInstance()
				.getTime());

		// ací s'audita el canvi
		getUserEntityDao().update(usuariEntity);

		return getUserEntityDao().toUser(usuariEntity);
	}

	private TaskInstance iniciaTasca(String nomProces, Object[][] parametres,
			String codiUsuari) throws Exception {
		BpmEngine engine = getBpmEngine();
		JbpmContext ctx = engine.getContext();
		try {
			ProcessDefinition def = ctx.getGraphSession()
					.findLatestProcessDefinition(nomProces);
			if (def == null)
				throw new Exception(
						String.format(
								Messages.getString("UserServiceImpl.NotExistsProcess"), nomProces)); //$NON-NLS-1$
			// Es crea el procés
			ProcessInstance pi = def.createProcessInstance();
			// Assignem les variables del procés
			ContextInstance ci = pi.getContextInstance();
			if (parametres != null) {
				for (int i = 0; i < parametres.length; i++) {
					Object[] nom_i_valor = parametres[i];
					ci.setVariable((String) nom_i_valor[0], nom_i_valor[1]);
				}
			}
			// Iniciem el procés
			pi.signal();
			// Iniciem la tasca
			Collection tasks = pi.getTaskMgmtInstance().getTaskInstances();
			if (tasks != null) {
				TaskInstance task = (TaskInstance) tasks.iterator().next();
				// Iniciem la tasca
				task.start();

				// Retornem la tasca
				return task;

			}
		} finally {
			ctx.close();
		}
		return null;
	}

	/*
	 * private void cancelaTasquesProces (String nomProces, Long idProces)
	 * throws Exception { BpmEngineLocal engine = null; try { engine =
	 * BpmApplication.getEngine(); JbpmContext ctx = engine.getContext(); //-
	 * engine = BpmApplication.getEngine();
	 * 
	 * GraphSession gs = ctx.getGraphSession(); List defs =
	 * gs.findAllProcessDefinitionVersions(nomProces); for (Iterator it =
	 * defs.iterator(); it.hasNext();) { ProcessDefinition def =
	 * (ProcessDefinition) it.next(); List procs =
	 * gs.findProcessInstances(def.getId()); for (Iterator it2 =
	 * procs.iterator(); it2.hasNext();) { ProcessInstance pi =
	 * (ProcessInstance) it2.next(); if (!pi.hasEnded() && idProces!=null &&
	 * pi.getId() != idProces) { //System.out.println ("Stopping procés '" +
	 * nomProces + "' amb id=" + pi.getId());
	 * pi.getContextInstance().setVariable("end", "end"); pi.end();
	 * ctx.save(pi); } } } } finally { if (engine != null) engine.remove(); }
	 * 
	 * 
	 * }
	 */

	protected String handleCreateNewUserProcess(String nomProces,
			String codiUsuari, boolean canviaAProces) throws Exception {
		// Processos que es llancen des de la finestra d'usuaris

		TaskInstance task = null;

		// Cerquem l'usuari
		UserEntity usuariE = getUserEntityDao().findByUserName(codiUsuari);
		if (usuariE == null)
			throw new Exception(
					String.format(
							Messages.getString("UserServiceImpl.UserCodNotFounded"), codiUsuari)); //$NON-NLS-1$

		List defs = getBpmEngine().findProcessDefinitions("initiator", true); //$NON-NLS-1$
		boolean found = false;
		for (Iterator it = defs.iterator(); !found && it.hasNext();) {
			es.caib.bpm.vo.ProcessDefinition def = (es.caib.bpm.vo.ProcessDefinition) it
					.next();
			if (def.getName().equals(nomProces))
				found = true;
		}
		if (!found)
			throw new Exception(
					String.format(
							Messages.getString("UserServiceImpl.ProcessCallNotAllowed"), nomProces)); //$NON-NLS-1$

		Object[][] params = {
				{ "autoStarted", new Integer(1) }, { "userName", codiUsuari } }; //$NON-NLS-1$ //$NON-NLS-2$
		task = iniciaTasca(nomProces, params, codiUsuari);

		if (task == null)
			throw new Exception(
					String.format(
							Messages.getString("UserServiceImpl.TaskNotLaunched"), nomProces)); //$NON-NLS-1$

		// Aqui task !=null
		if (canviaAProces) {
			// tornem la tasca perquè es mostre la seua finestra
			return "/wf/task.zul?id=" + task.getId(); //$NON-NLS-1$
		}

		return null;
	}

	protected Collection<BpmProcess> handleGetBpmUserProcessList()
			throws Exception {
		BpmEngine engine = getBpmEngine();
		// Aqui tenim els processos que ens interesa que puga
		// iniciar l'usuari des de la finestra d'usuaris
		HashMap<String, BpmProcess> hProcessosUsuari = new HashMap();

		LinkedList<BpmProcess> resultat = new LinkedList<BpmProcess>();
		// Afegim una buida al llistat
		resultat.add(new BpmProcess(null, null));
		try {
			// Obtenim els processos que l'usuari actual pot iniciar (i són
			// habilitats)
			List<com.soffid.iam.bpm.api.ProcessDefinition> processosUsuari = engine
					.findInitiatorProcessDefinitions();
			if (processosUsuari != null)
				for (Iterator<com.soffid.iam.bpm.api.ProcessDefinition> it = processosUsuari
						.iterator(); it.hasNext();) {
					com.soffid.iam.bpm.api.ProcessDefinition def = it.next();
					if (def != null && def.getName() != null
							&& "user".equals(def.getAppliesTo())
							&& def.isEnabled())
						resultat.add(new BpmProcess(def.getName(), def
								.getName()));
				}

		} finally {
		}

		return resultat;

	}

	@Override
	protected BpmUserProcess handleCreate(BpmUserProcess usuariWFProces)
			throws Exception {
		for (UserProcessEntity existing: getUserProcessEntityDao().findByUserName(usuariWFProces.getUserCode()))
		{
			if (existing.getProcessId().equals(usuariWFProces.getProcessId()))
				return getUserProcessEntityDao().toBpmUserProcess(existing);
		}
		UserProcessEntity entity = getUserProcessEntityDao()
				.bpmUserProcessToEntity(usuariWFProces);
		getUserProcessEntityDao().create(entity);
		return getUserProcessEntityDao().toBpmUserProcess(entity);
	}

	@Override
	protected BpmUserProcess handleUpdate(BpmUserProcess usuariWFProces)
			throws Exception {
		UserProcessEntity entity = getUserProcessEntityDao()
				.bpmUserProcessToEntity(usuariWFProces);
		getUserProcessEntityDao().update(entity);
		return getUserProcessEntityDao().toBpmUserProcess(entity);
	}

	@Override
	protected void handleDelete(BpmUserProcess usuariWFProces) throws Exception {
		UserProcessEntity entity = getUserProcessEntityDao()
				.bpmUserProcessToEntity(usuariWFProces);
		getUserProcessEntityDao().remove(entity);
	}

	@Override
	protected Collection<BpmUserProcess> handleFindBpmUserProcessByUserName(
			String codiUsuari) throws Exception {
		Collection<UserProcessEntity> usuproc = getUserProcessEntityDao()
				.findByUserName(codiUsuari);
		return getUserProcessEntityDao().toBpmUserProcessList(usuproc);
	}

	@Override
	protected Collection<BpmUserProcess> handleFindBpmUserProcessByProcessId(
			Long idProces) throws Exception {
		Collection<UserProcessEntity> usuproc = getUserProcessEntityDao()
				.findByProcessId(idProces);
		return getUserProcessEntityDao().toBpmUserProcessList(usuproc);
	}

	@Override
	protected Collection<BpmUserProcess> handleFindBpmUserProcessByUserNif(
			String nifUsuari) throws Exception {
		Collection<UserProcessEntity> usuproc = getUserProcessEntityDao()
				.findByUserNationalId(nifUsuari);
		return getUserProcessEntityDao().toBpmUserProcessList(usuproc);
	}

	@Override
	protected Collection<com.soffid.iam.bpm.api.ProcessInstance> handleFindBpmUserProcessInstanceByUserName(
			String codiUsuari) throws Exception {
		Collection<com.soffid.iam.bpm.api.ProcessInstance> processos = new LinkedList<com.soffid.iam.bpm.api.ProcessInstance>();
		// Cerquem els processos de l'usuari
		Collection<UserProcessEntity> usuproc = getUserProcessEntityDao()
				.findByUserName(codiUsuari);
		if (usuproc != null) {
			for (UserProcessEntity up : usuproc) {
				try {
					com.soffid.iam.bpm.api.ProcessInstance pi = getBpmEngine()
							.getProcess(up.getProcessId());
					if (pi != null) {
						pi.getVariables().clear();
						processos.add(pi);
					}
				} catch (Exception e) {
					Throwable root = e;
					while (root != null && root.getCause() != null && root.getCause() != root)
						root = root.getCause();
					if (root != null && (root instanceof OutOfMemoryError))
						throw e;
				}
			}
		}

		return processos;
	}

	@Override
	protected User handleGetUserInfo(String user) throws Exception {
		UserEntityDao dao = getUserEntityDao();

		UserEntity entity = null;

		entity = dao.findByUserName(user);
		if (entity == null)
			throw new UnknownUserException(user);
		return dao.toUser(entity);
	}

	@Override
	protected Collection<Group> handleGetUserGroups(long userId)
			throws Exception {
		UserEntityDao dao = getUserEntityDao();
		SystemEntity dispatcher = null;

		UserEntity entity = dao.load(userId);
		if (entity == null)
			throw new UnknownUserException(Long.toString(userId));

		GroupEntityDao grupDao = getGroupEntityDao();
		LinkedList<Group> grups = new LinkedList<Group>();
		grups.add(grupDao.toGroup(entity.getPrimaryGroup()));
		for (Iterator<UserGroupEntity> it = entity.getSecondaryGroups()
				.iterator(); it.hasNext();) {
			UserGroupEntity uge = it.next();
			grups.add(grupDao.toGroup(uge.getGroup()));
		}
		return grups;
	}

	@Override
	protected Collection<Group> handleGetUserGroupsHierarchy(long userId)
			throws Exception {
		UserEntityDao dao = getUserEntityDao();
		UserEntity entity = dao.load(userId);
		if (entity == null)
			throw new UnknownUserException(Long.toString(userId));
		GroupEntityDao grupDao = getGroupEntityDao();
		LinkedList<GroupEntity> grups = new LinkedList<GroupEntity>();
		HashMap<String, Group> result = new HashMap<String, Group>();

		if (entity.getPrimaryGroup() != null)
			grups.add(entity.getPrimaryGroup());
		for (Iterator<UserGroupEntity> it = entity.getSecondaryGroups()
				.iterator(); it.hasNext();) {
			UserGroupEntity uge = it.next();
			grups.add(uge.getGroup());
		}

		while (!grups.isEmpty()) {
			GroupEntity head = grups.getFirst();
			if (result.get(head.getName()) == null) {
				result.put(head.getName(), grupDao.toGroup(head));
				if (head.getParent() != null)
					grups.add(head.getParent());
			}
			grups.removeFirst();
		}
		LinkedList<Group> grupsList = new LinkedList<Group>();
		grupsList.addAll(result.values());
		return grupsList;
	}

	private Collection<RoleGrant> getUserRoles(long userId, boolean explicit)
			throws Exception {
		UserEntityDao dao = getUserEntityDao();
		UserEntity entity = dao.load(userId);
		if (entity == null)
			throw new UnknownUserException(Long.toString(userId));

		if (!explicit)
			return getApplicationService().findEffectiveRoleGrantByUser(userId);
		else
		{
			// Recuperar rols explicits de l'usuari
			List<RoleAccountEntity> originalGrants = getRoleAccountEntityDao()
					.findByUserName(entity.getUserName());
			List<RoleGrant> rols = getRoleAccountEntityDao().toRoleGrantList(
					originalGrants);
			return rols;
		}
	}

	protected Collection<RoleGrant> handleGetUserRoles(long userId)
			throws Exception {
		return getUserRoles(userId, false);
	}

	@Override
	protected Collection<RoleGrant> handleGetUserExplicitRoles(long userId)
			throws Exception {
		return getUserRoles(userId, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.seycon.ng.servei.UsuariServiceBase#handleGetCurrentUsuari()
	 */
	@Override
	protected User handleGetCurrentUser() throws Exception {
		String account = Security.getCurrentAccount();
		if (account == null)
			return null;


		// First,check from cache
		User u = (User) getSessionCacheService().getObject("currentUser");
		if (u != null)
			return u;

		
		SoffidPrincipal p = Security.getSoffidPrincipal();
		if (p != null && p.getUserId() != null) {
			UserEntity ue = getUserEntityDao().load(p.getUserId());
			if (ue != null)
			{
				u = getUserEntityDao().toUser(ue);
				getSessionCacheService().putObject("currentUser", u);
				return u;
			}
		}
		
		String userName = Security.getCurrentUser();
		if (userName != null)
		{
			UserEntity ue = getUserEntityDao().findByUserName(userName);
			if (ue != null)
			{
				u = getUserEntityDao().toUser(ue);
				getSessionCacheService().putObject("currentUser", u);
				return u;
			}
		}

		String dispatcherName = getInternalPasswordService()
				.getDefaultDispatcher();
		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(account, dispatcherName);
		if (acc == null)
			return null;
		else if (acc.getType().equals (AccountType.USER))
		{
			for (UserAccountEntity uae: acc.getUsers())
			{
				UserEntity ue = uae.getUser();
				if (ue != null)
				{
					u = getUserEntityDao().toUser(ue);
					getSessionCacheService().putObject("currentUser", u);
					return u;
				}
			}
		}
		return null;
	}

	private String getServerList() throws InternalErrorException, SQLException,
			NamingException {
		ConfigurationService configuracioService = getConfigurationService();
		com.soffid.iam.api.Configuration parametre = configuracioService
				.findParameterByNameAndNetworkName("seycon.server.list", null); //$NON-NLS-1$
		return parametre.getValue();
	}

	private String getServerPort() throws InternalErrorException, SQLException,
			NamingException {
		ConfigurationService configuracioService = getConfigurationService();
		com.soffid.iam.api.Configuration parametre = configuracioService
				.findParameterByNameAndNetworkName("seycon.https.port", null); //$NON-NLS-1$
		return parametre.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.UsuariServiceBase#handleGetMazingerRules(java
	 * .lang.String)
	 */
	@Override
	protected byte[] handleGetESSORules(String user) throws Exception {
		String name = getServerList();
		String port = getServerPort();
		LinkedList<SyncServerInfo> serversInfo = new LinkedList<SyncServerInfo>();
		Config.configureClient(name, port);
		ServerEntityDao sedao = getServerEntityDao();
		Exception lastException = new InternalErrorException(
				Messages.getString("UserServiceImpl.NoSyncserverDefined")); //$NON-NLS-1$
		for (ServerEntity server : sedao.loadAll()) {
			try {
				RemoteServiceLocator rsl = createRemoteServiceLocator(server);
				SyncStatusService sss = rsl.getSyncStatusService();
				return sss.getMazingerRules(user);
			} catch (Exception e) {
				lastException = e;
			}

		}
		throw lastException;
	}

	private RemoteServiceLocator createRemoteServiceLocator(ServerEntity server)
			throws IOException, InternalErrorException {
		RemoteServiceLocator rsl = new RemoteServiceLocator(server.getName());
		rsl.setAuthToken(server.getAuth());
		return rsl;
	}

	private void addString(String value, String hqlAttribute,
			String joinArray[], List<String> joins, List<String> queries,
			List<Parameter> params) {
		String param = "param" + params.size(); //$NON-NLS-1$
		addString2(
				value,
				param,
				hqlAttribute + " like :" + param, joinArray, joins, queries, params); //$NON-NLS-1$
	}

	private void addString2(String value, String param, String hqlCondition,
			String joinArray[], List<String> joins, List<String> queries,
			List<Parameter> params) {
		if (value != null && value.trim().compareTo("") != 0 //$NON-NLS-1$
				&& value.trim().compareTo("%") != 0) { //$NON-NLS-1$
			value = value.trim();
			queries.add(hqlCondition);
			params.add(new Parameter(param, value));
			if (joinArray != null)
				for (String join : joinArray) {
					if (!joins.contains(join))
						joins.add(join);
				}

		}
	}

	private void addDateRange(String value, String hqlAttribute,
			String joinArray[], List<String> joins, List<String> queries,
			List<Parameter> params) throws InternalErrorException {
		if (value != null && value.trim().compareTo("") != 0 //$NON-NLS-1$
				&& value.trim().compareTo("%") != 0) { //$NON-NLS-1$
			value = value.trim();
			LimitDates limitDates = DateUtils.getLimitDatesFromQuery(value);
			if (limitDates.getMaximum() != null) {
				String param = "param" + params.size(); //$NON-NLS-1$
				queries.add(hqlAttribute + " <= :" + param); //$NON-NLS-1$
				params.add(new Parameter(param, limitDates.getMaximum()));
			}

			if (limitDates.getMinimum() != null) {
				String param = "param" + params.size(); //$NON-NLS-1$
				queries.add(hqlAttribute + " >= :" + param); //$NON-NLS-1$
				params.add(new Parameter(param, limitDates.getMinimum()));
			}
			if (joinArray != null)
				for (String join : joinArray) {
					if (!joins.contains(join))
						joins.add(join);
				}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.seycon.ng.servei.UsuariServiceBase#handleFindUserByCriteria(es
	 * .caib.seycon.ng.servei.UsuariCriteria)
	 */
	@Override
	protected Collection<User> handleFindUserByCriteria(UserCriteria criteria)
			throws Exception {
		int limitResults = Integer.MAX_VALUE;
		try {
			limitResults = Integer.parseInt(ConfigurationCache
					.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		} catch (NumberFormatException e) {
		}

		LinkedList<String> joins = new LinkedList<String>();
		List<String> queries = new LinkedList<String>();
		List<Parameter> params = new LinkedList<Parameter>();

		addDateRange(criteria.getCreatedDate(),
				"usuari.creationUser", null, joins, queries, params); //$NON-NLS-1$
		addDateRange(criteria.getModifiedDate(),
				"usuari.lastModificationDate", null, joins, queries, params); //$NON-NLS-1$
		addString(
				criteria.getAccountName(),
				"account.name",
				new String[] { "inner join usuari.accounts as accounts",
						"inner join accounts.account as account" }, joins, queries, params); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		addString(
				criteria.getAccountSystem(),
				"dispatcher.name", new String[] { //$NON-NLS-1$
				"inner join usuari.accounts as accounts", //$NON-NLS-1$
						"inner join accounts.account as account", //$NON-NLS-1$
						"inner join account.system as dispatcher" }, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getUserName(),
				"usuari.userName", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getComments(),
				"usuari.comment", null, joins, queries, params); //$NON-NLS-1$
		addString(
				criteria.getPrimaryGroup(),
				"grup.name", new String[] { "left outer join usuari.primaryGroup as grup" }, joins, queries, params); //$NON-NLS-1$ //$NON-NLS-2$
		addString(criteria.getFirstName(),
				"usuari.firstName", null, joins, queries, params); //$NON-NLS-1$

		addString(
				criteria.getPrimaryGroupDescription(),
				"grup.description", new String[] { "left outer join usuari.primaryGroup as grup" }, joins, queries, params); //$NON-NLS-1$ //$NON-NLS-2$
		addString(
				criteria.getNationalID(),
				"dadaUsuari.value", new String[] { "inner join usuari.attributes as dadaUsuari", "inner join dadaUsuari.dataType as tipusDada with tipusDada.name=\'NIF\'" }, joins, queries, params); //$NON-NLS-1$
		addString(
				criteria.getPhoneNumber(),
				"dadaUsuari2.valorDada", new String[] { "inner join usuari.attributes as dadaUsuari2", "inner join dadaUsuari2.dataType as tipusDada2 with tipusDada2.name=\'PHONE\'" }, joins, queries, params); //$NON-NLS-1$

		addString(criteria.getShortName(),
				"usuari.shortName", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getLastName(),
				"usuari.lastName", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getMiddleName(),
				"usuari.middleName", null, joins, queries, params); //$NON-NLS-1$

		addString(criteria.getRolName(), "rol.name", new String[] { //$NON-NLS-1$
				"inner join usuari.accounts as accounts", //$NON-NLS-1$
						"inner join accounts.account as account", //$NON-NLS-1$
						"inner join account.roles as roles", //$NON-NLS-1$
						"inner join roles.role as rol" //$NON-NLS-1$
				}, joins, queries, params);

		addString(criteria.getRolSystem(), "dispatcher2.name", new String[] { //$NON-NLS-1$
				"inner join usuari.accounts as accounts", //$NON-NLS-1$
						"inner join accounts.account as account", //$NON-NLS-1$
						"inner join account.roles as roles", //$NON-NLS-1$
						"inner join roles.role as rol", //$NON-NLS-1$
						"inner join rol.system as dispatcher2" //$NON-NLS-1$
				}, joins, queries, params);

		addString(criteria.getMailServer(),
				"usuari.mailServer.name", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getHomeServer(),
				"usuari.homeServer.name", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getProfileServer(),
				"usuari.profileServer.name", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getUserType(),
				"usuari.userType.name", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getCreatedByUser(),
				"usuari.creationUser", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getModifiedByUser(),
				"usuari.lastUserModification", null, joins, queries, params); //$NON-NLS-1$
		addString2(
				criteria.getSecondaryGroup(),
				"grupSecundari", //$NON-NLS-1$
				"(grup.name like :grupSecundari or grupB.name like :grupSecundari)", //$NON-NLS-1$
				new String[] {
						"left outer join usuari.primaryGroup as grup", //$NON-NLS-1$
						"left outer join usuari.secondaryGroups as grupsSecundaris", //$NON-NLS-1$
						"left outer join grupsSecundaris.group as grupB" }, joins, queries, params); //$NON-NLS-1$
		addString(
				criteria.getMailDomain(),
				"mailDomain.name",
				new String[] { "left outer join usuari.mailDomain as mailDomain" },
				joins, queries, params);
		if (criteria.getActive() != null)
			addString(
					criteria.getActive().booleanValue() ? "S" : "N", "usuari.active", null, joins, queries, params); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		if (criteria.getAttributeValue() != null
				&& criteria.getAttributeName() != null
				&& criteria.getAttributeName().trim().length() > 0) {
			addString(
					criteria.getAttributeName(),
					"tipusDada2.name", //$NON-NLS-1$
					new String[] { "inner join usuari.attributes as dadaUsuari2", //$NON-NLS-1$
							"inner join dadaUsuari2.dataType as tipusDada2" }, joins, queries, params); //$NON-NLS-1$

			addString(criteria.getAttributeValue(),
					"dadaUsuari2.value", new String[] { //$NON-NLS-1$
					"inner join usuari.attributes as dadaUsuari2" //$NON-NLS-1$
					}, joins, queries, params);
		}

		StringBuffer sb = new StringBuffer(
				"select usuari from com.soffid.iam.model.UserEntity as usuari "); //$NON-NLS-1$
		for (String join : joins) {
			sb.append(' ').append(join);
		}
		sb.append (" where usuari.tenant.id = :tenantId"); //$NON-NLS-1$
		params.add(new Parameter("tenantId", Security.getCurrentTenantId()));
		boolean first = true;
		for (String query : queries) {
			sb.append(" and "); //$NON-NLS-1$
			sb.append(query);
			first = false;
		}

		String s = sb.toString();
		Collection<UserEntity> usuaris = getUserEntityDao().query(s,
				params.toArray(new Parameter[0]));
		if (usuaris != null && usuaris.size() != 0) {
			// Ya tenemos los grupos del usuario con permisos
			Collection<UserEntity> usuarisPermis = filterUsers(usuaris);

			if (usuarisPermis != null && usuarisPermis.size() != 0) {
				List<User> vos = getUserEntityDao().toUserList(usuarisPermis);
				if (criteria.getMailAlias() != null
						&& criteria.getMailAlias().trim().length() > 0
						&& !criteria.equals("%")) //$NON-NLS-1$
				{
					Pattern p = Pattern
							.compile(criteria
									.getMailAlias()
									.replaceAll(".", "\\.").replaceAll("%", ".*").replaceAll("_", ".")); //$NON-NLS-1$ //$NON-NLS-2$
					for (Iterator<User> it = vos.iterator(); it.hasNext();) {
						User usu = it.next();
						if (!p.matcher(usu.getMailAlias()).matches())
							it.remove();
					}
				}
				// Check maximum number of results
				if (vos.size() > limitResults) {
					return vos.subList(0, limitResults);
				}
				return vos;
			}

		}
		return new Vector();
	}

	private Collection<UserEntity> filterUsers(Collection<UserEntity> usuaris)
			throws InternalErrorException {
		LinkedList<UserEntity> result = new LinkedList<UserEntity>();
		for (UserEntity ue : usuaris) {
			if (getAuthorizationService().hasPermission(
					Security.AUTO_USER_QUERY, ue))
				result.add(ue);
		}
		return result;
	}

	@Override
	protected void handleSetTemporaryPassword(String codiUsuari,
			String codiDominiContrasenyes, Password newPassword)
			throws Exception {
		setPassword(codiUsuari, codiDominiContrasenyes, newPassword, true);
	}

	@Override
	protected void handleSetPassword(String codiUsuari,
			String codiDominiContrasenyes, Password newPassword)
			throws Exception {
		setPassword(codiUsuari, codiDominiContrasenyes, newPassword, false);
	}

	protected void setPassword(String codiUsuari,
			String codiDominiContrasenyes, Password newPassword, boolean mustChange)
			throws Exception {

		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		if (usuari != null && "S".equals(usuari.getActive())) { //$NON-NLS-1$
			if (getAuthorizationService().hasPermission(
					Security.AUTO_USER_UPDATE_PASSWORD, usuari)) {
				PasswordDomainEntity dominiContrasenyes = getPasswordDomainEntityDao()
						.findByName(codiDominiContrasenyes);
				PolicyCheckResult validation = getInternalPasswordService()
						.checkPolicy(usuari, dominiContrasenyes, newPassword, true);
				if (!validation.isValid())
					throw new BadPasswordException(validation.getReason());
				getInternalPasswordService().storeAndForwardPassword(usuari,
						dominiContrasenyes, newPassword, mustChange);
				auditaCanviPassword(codiUsuari, dominiContrasenyes.getName());
			} else {
				throw new SecurityException(
						String.format(
								Messages.getString("UserServiceImpl.NoAuthorizedToChangePass"), codiUsuari)); //$NON-NLS-1$
			}
		} else {
			throw new SeyconException(
					Messages.getString("UserServiceImpl.UserInactiveToChangePass")); //$NON-NLS-1$
		}
	}

	@Override
	protected PagedResult<User> handleFindUserByJsonQuery(String query, Integer start, Integer end)
			throws InternalErrorException, Exception {

		LinkedList<User> result = new LinkedList<User>();

		PagedResult<User>  r =  internalSearchUsersByJson(null, query, result, start, end);
  
		return r;
	}

	@Override
	protected List<User> handleFindUserByJsonQuery(String query)
			throws InternalErrorException, Exception {

		LinkedList<User> result = new LinkedList<User>();

		internalSearchUsersByJson(null, query, result, null, null);
		
		return result;
	}


	private PagedResult<User> internalSearchUsersByJson(String text, String filter, List<User> result,
			Integer start, Integer pageSize)
			throws UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException, TokenMgrError,
			EvalException, InternalErrorException {
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();

		final UserEntityDao dao = getUserEntityDao();
		ScimHelper h = new ScimHelper(User.class);
		h.setPrimaryAttributes(new String[] { "userName"} );
		
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			UserEntity ue = (UserEntity) entity;
			try {
				if (getAuthorizationService().hasPermission(Security.AUTO_USER_QUERY, ue))
					return getUserEntityDao().toUser(ue);
				else
					return null;
			} catch (InternalErrorException e) {
				throw new RuntimeException(e);
			}
		});
		
		h.search(text, filter, (Collection) result); 

		PagedResult<User> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}

	@Override
	protected AsyncList<User> handleFindUserByJsonQueryAsync(final String query)
			throws InternalErrorException, Exception {
		return handleFindUserByTextAndFilterAsync(null, query);
	}

	@Override
	protected AsyncList<User> handleFindUserByTextAndFilterAsync(String text, String filter) throws Exception {
		final AsyncList<User> result = new AsyncList<User>();
		
		getAsyncRunnerService().run(new Runnable() {

			@Override
			public void run() {
				try {
					internalSearchUsersByJson(text, filter, result, null, null);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
			
		}, result);

		return result;
	}

	@Override
	protected List<User> handleFindUserByTextAndFilter(String text, String filter) throws Exception {
		final LinkedList<User> result = new LinkedList<User>();
		internalSearchUsersByJson(text, filter, result, null, null);
		return result;
	}

	@Override
	protected PagedResult<User> handleFindUserByTextAndFilter(String text, String filter,
			Integer start, Integer max) throws Exception {
		final LinkedList<User> result = new LinkedList<User>();
		return internalSearchUsersByJson(text, filter, result, start, max);
	}

	@Override
	protected Collection<User> handleFindUserByText(String text) throws Exception {
		return handleFindUserByTextAndFilter(text, null);
	}

	@Override
	protected Collection<String> handleFindUserNames() throws Exception {
		return getUserEntityDao().findUserNames();
	}

	@Override
	protected AsyncList<User> handleFindUserByTextAsync(final String text) throws Exception {
		return handleFindUserByTextAndFilterAsync(text, null);
	}

	@Override
	protected Map<String, Object> handleFindUserAttributes(String codiUsuari) throws Exception {
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();

		if (codiUsuari == null || codiUsuari.trim().isEmpty())
			return attributes;
		
		UserEntity source = getUserEntityDao().findByUserName(codiUsuari);
		if (source != null)
		{
			Collection<UserDataEntity> userData = source.getUserData();
			fetchUserAttributes(attributes, userData, true);
		}
		return attributes;
	}

	private void fetchUserAttributes(Map<String, Object> attributes, Collection<UserDataEntity> userData, boolean applyRestrictions) {
		attributes.clear();

		for (UserDataEntity att : userData) {
			UserData vd = getUserDataEntityDao().toUserData(att);
			if (! applyRestrictions || att.getAttributeVisibility() != AttributeVisibilityEnum.HIDDEN)
			{
				if (att.getDataType().getMultiValued() != null && att.getDataType().getMultiValued().booleanValue())
				{
					LinkedList<Object> r = (LinkedList<Object>) attributes.get(vd.getAttribute());
					if (r == null)
					{
						r = new LinkedList<Object>();
						attributes.put(vd.getAttribute(), r);
					}
					r.add(att.getObjectValue());
				}
				else
				{
					attributes.put(vd.getAttribute(), att.getObjectValue());
				}
			}
		}
		for (Object o: attributes.values())
		{
			if (o != null && o instanceof List) Collections.sort((List) o);
		}
	}

	private void auditChange(UserDataEntity dadaUsuari, Set<String> changedAttributes) throws InternalErrorException {
		if (! changedAttributes.contains(dadaUsuari.getDataType().getName())) {
			Audit audit = new Audit();
			audit.setObject("SC_DADUSU");
			audit.setAction("U");
			audit.setAuthor(Security.getCurrentAccount());
			audit.setCalendar(Calendar.getInstance());
			audit.setConfigurationParameter(dadaUsuari.getDataType().getName());
			audit.setUser(dadaUsuari.getUser().getUserName());
			getAuditService().create(audit);
			changedAttributes.add(dadaUsuari.getDataType().getName());
		}
	}

	private void auditChange(String user, String attribute) throws InternalErrorException {
		Audit audit = new Audit();
		audit.setObject("SC_DADUSU");
		audit.setAction("U");
		audit.setAuthor(Security.getCurrentAccount());
		audit.setCalendar(Calendar.getInstance());
		audit.setConfigurationParameter(attribute);
		audit.setUser(user);
		getAuditService().create(audit);
	}

	@Override
	protected void handleUpdateUserAttributes(String codiUsuari, Map<String, Object> attributes) throws Exception {
		handleUpdateUserAttributes(codiUsuari, attributes, true);
	}

	public void handleUpdateUserAttributes(String codiUsuari, Map<String, Object> attributes, boolean updateUser)
			throws InternalErrorException {
		boolean anyChange = false;
		Set<String> changedAttributes = new HashSet<>();
		UserEntity entity = getUserEntityDao().findByUserName(codiUsuari);
		if (entity != null)
		{
			if (attributes == null)
				attributes = (new HashMap<String, Object>());
			
			LinkedList<UserDataEntity> entities = new LinkedList<UserDataEntity> (entity.getUserData());
			HashSet<String> keys = new HashSet<String>();
			for (String key: attributes.keySet() )
			{
				MetaDataEntity metadata = getMetaDataEntityDao().findDataTypeByName(key);
				if (metadata == null)
					throw new InternalErrorException("Attribute definition not found for attribute "+key);
				Object v = attributes.get(key);
				if (v == null)
				{
					// Do nothing
				}
				else if (v instanceof Collection)
				{
					Collection l = (Collection) v;
					for (Object o: (Collection) v)
					{
						if (o != null)
						{
							if (updateUserAttribute(entity, entities, key, metadata, o, changedAttributes))
								anyChange = true;
						}
					}
				}
				else
				{
					if (updateUserAttribute(entity, entities, key, metadata, v, changedAttributes))
						anyChange = true;
				}
			}

			for (UserDataEntity attribute: entities)
			{
				if (attribute.getAttributeVisibility() == AttributeVisibilityEnum.EDITABLE)
				{
					anyChange = true;
					auditChange(attribute, changedAttributes);
					getUserDataEntityDao().remove(attribute);
					entity.getUserData().remove(attribute);
				}
			}

			fetchUserAttributes(attributes, entity.getUserData(), false);
			
			Collection<MetaDataEntity> md = getMetaDataEntityDao().findByObjectTypeAndName(User.class.getName(), null);
			for ( MetaDataEntity m: md) if ( m.getBuiltin() == null || ! m.getBuiltin().booleanValue() )
			{
				Object o = attributes.get(m.getName());
				if ( o == null || "".equals(o))
				{
					if (m.getRequired() != null && m.getRequired().booleanValue())
						throw new InternalErrorException(String.format("Missing attribute %s", m.getName()));
				} else {
					if (m.getUnique() != null && m.getUnique().booleanValue())
					{
						Collection<String> l = (Collection<String>) ( o instanceof Collection? (Collection) o: Collections.singletonList(o) );
						for (String v: l)
						{
							List<UserDataEntity> p = getUserDataEntityDao().findByTypeAndValue(m.getName(), v);
							if (p.size() > 1)
								throw new InternalErrorException(String.format("Already exists a user with %s %s",
										m.getLabel(), v));
						}
					}
				}
			}
			if (updateUser) {
				getRuleEvaluatorService().applyRules(entity);
				if (anyChange)
					getUserEntityDao().update(entity);
			}
		}
	}

	private boolean updateUserAttribute(UserEntity entity, LinkedList<UserDataEntity> attributes, String key,
			MetaDataEntity metadata, Object value, Set<String> changedAttributes) throws InternalErrorException {
		boolean anyChange = false;
		UserDataEntity aae = findUserDataEntity(attributes, key, value, metadata);
		if (aae == null)
		{
			getAttributeValidationService().validate(metadata, value);
			aae = getUserDataEntityDao().newUserDataEntity();
			aae.setUser(entity);
			aae.setDataType(metadata);
			aae.setObjectValue(value);
			if (aae.getAttributeVisibility() == AttributeVisibilityEnum.EDITABLE)
			{
				getUserDataEntityDao().create(aae);
				entity.getUserData().add(aae);
				auditChange(aae, changedAttributes);
				anyChange = true;
			}
		}
		else
			attributes.remove(aae);
		return anyChange;
	}

	private UserDataEntity findUserDataEntity(LinkedList<UserDataEntity> entities, String key,
			Object o, MetaDataEntity metadata) {
		AttributeParser ap = new AttributeParser (metadata.getName(), metadata.getType(), o);
		for (UserDataEntity aae: entities)
		{
			if (aae.getDataType().getName().equals(key))
			{
				if (aae.getBlobDataValue() == null) {
					if (ap.getBlobValue() == null) 
						if (aae.getValue() == null ? ap.getValue() == null :
							aae.getValue().equals(ap.getValue()))
							return aae;
				} else {
					if (ap.getBlobValue() != null)
						if (Arrays.equals(aae.getBlobDataValue(), ap.getBlobValue()))
							return aae;
				}
			}
		}
		return null;
	}

	@Override
	public Collection<Group> handleGetUserGroupsHierarchy(long userId, String holderGroup)
			throws InternalErrorException, InternalErrorException, UnknownUserException {
		UserEntityDao dao = getUserEntityDao();
		UserEntity entity = dao.load(userId);
		if (entity == null)
			throw new UnknownUserException(Long.toString(userId));
		GroupEntityDao grupDao = getGroupEntityDao();
		LinkedList<GroupEntity> grups = new LinkedList<GroupEntity>();
		HashMap<String, Group> result = new HashMap<String, Group>();

		grups.add(entity.getPrimaryGroup());
		for (Iterator<UserGroupEntity> it = entity.getSecondaryGroups()
				.iterator(); it.hasNext();) {
			UserGroupEntity uge = it.next();
			GroupEntity g = uge.getGroup();
			if ( g.getName().equals(holderGroup) || ! isHolderGroup(g))
				grups.add(g);
		}

		while (!grups.isEmpty()) {
			GroupEntity head = grups.getFirst();
			if (result.get(head.getName()) == null) {
				result.put(head.getName(), grupDao.toGroup(head));
				if (head.getParent() != null)
					grups.add(head.getParent());
			}
			grups.removeFirst();
		}
		LinkedList<Group> grupsList = new LinkedList<Group>();
		grupsList.addAll(result.values());
		return grupsList;
	}
	
	boolean isHolderGroup (GroupEntity group)
	{
		if (group.getUnitType() != null && group.getUnitType().isRoleHolder())
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AccountServiceBase#handleIsUpdatePending(es.caib.seycon.ng.comu.Account)
	 */
	@Override
	protected int handleIsUpdatePendingExtended (String userName) throws Exception
	{
		int status = 0;
		UserEntity user = getUserEntityDao().findByUserName(userName);
		if (user == null)
			return 0;


		for ( AccountEntity account: getAccountEntityDao().findByUser(user.getId())) 
		{
			if (account.getSystem().getUrl() != null &&
					account.getType() != AccountType.IGNORED &&
					Boolean.FALSE.equals(account.getPasswordPolicy().getUnmanaged())) {
				List<TaskEntity> coll = getTaskEntityDao().findByAccount(account.getName(), account.getSystem().getName());
				for (TaskEntity tasque : coll) {
					if (tasque.getTransaction().equals(TaskHandler.UPDATE_ACCOUNT) || tasque.getTransaction().equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD) || tasque.getTransaction().equals(TaskHandler.PROPAGATE_ACCOUNT_PASSWORD)) {
						if ("X".equals( tasque.getStatus()) && status < 1)
							status = 1;
						else if ("E".equals( tasque.getStatus()) && status < 3)
							status = 3;
						else if (status < 2) 
							status = 2;
					}
				}
			}
		}

        List<TaskEntity> coll = getTaskEntityDao().findByUser(userName);
        for (TaskEntity tasque : coll) {
            if (tasque.getTransaction().equals(TaskHandler.UPDATE_USER) || 
            		tasque.getTransaction().equals(TaskHandler.UPDATE_USER_PASSWORD) || 
            		tasque.getTransaction().equals(TaskHandler.PROPAGATE_PASSWORD)) {
            	if ("X".equals( tasque.getStatus()))
            	{
            		if (status < 1) status = 1;
            	} else {
            		if (status < 2) status = 2 ;
            		boolean found = false;
                    for (TaskLogEntity tl : tasque.getLogs()) {
                        if (!"S".equals(tl.getCompleted())) {
                        	if (tl.getExecutionsNumber() != null && tl.getExecutionsNumber().longValue() > 1L && status < 3)
                        		status = 3;
                        }
                    }
            	}
            }
        }

        return status;
	}

	@Override
	protected Collection<SyncAgentTaskLog> handleGetActiveTasks(String userName) throws Exception {
		List<SyncAgentTaskLog> r = new LinkedList<>();
		UserEntity user = getUserEntityDao().findByUserName(userName);
		if (user == null)
			return r;

		List<AccountEntity> accounts = getAccountEntityDao().findByUser(user.getId());
		for ( AccountEntity account: accounts) 
		{
			if (account.getSystem().getUrl() != null &&
					account.getType() != AccountType.IGNORED &&
					Boolean.FALSE.equals(account.getPasswordPolicy().getUnmanaged())) {
				List<TaskEntity> coll = getTaskEntityDao().findByAccount(account.getName(), account.getSystem().getName());
				for (TaskEntity task : coll) {
					SyncAgentTaskLog tl;
					tl = new SyncAgentTaskLog();
					tl.setTaskId(task.getId());
					tl.setAgentCode(account.getSystem().getName());
					tl.setComplete( "X".equals(task.getStatus()) ? "ON HOLD": "PENDING");
					tl.setCreationDate(Calendar.getInstance());
					tl.getCreationDate().setTime(task.getDate());
					tl.setExecutionsNumber(0L);
					tl.setPriority(task.getPriority());
					tl.setTaskDescription(task.toString());
					for ( TaskLogEntity tle: task.getLogs()) {
						if (tle.getLastExecution() != null) {
							tl.setLastExecution(tle.getLastExecution());
							tl.setLastExecutionDate(Calendar.getInstance());
							tl.getLastExecutionDate().setTime(new Date(tle.getLastExecution()));
						}
						if (tle.getNextExecution() != null) {
							tl.setNextExecution(tle.getNextExecution());
							tl.setNextExecutionDate(Calendar.getInstance());
							tl.getNextExecutionDate().setTime(new Date(tle.getNextExecution()));	        								
						}
						if ("S".equals(tle.getCompleted())) {
							tl.setComplete("DONE");
						} else if (tle.getMessage() != null) {
							tl.setComplete("ERROR");
							tl.setMessage(tle.getMessage());
						}
						tl.setExecutionsNumber(tle.getExecutionsNumber());
	                } 
					r.add(tl);
				}
			}
		}

        List<TaskEntity> coll = getTaskEntityDao().findByUser(userName);
        for (TaskEntity task : coll) {
            if (task.getTransaction().equals(TaskHandler.UPDATE_USER) || 
            		task.getTransaction().equals(TaskHandler.UPDATE_USER_PASSWORD) || 
            		task.getTransaction().equals(TaskHandler.PROPAGATE_PASSWORD)) {
            	if ("X".equals( task.getStatus()))
            	{
    				SyncAgentTaskLog tl;
    				tl = new SyncAgentTaskLog();
    				tl.setTaskId(task.getId());
    				tl.setComplete( "ON HOLD");
    				tl.setCreationDate(Calendar.getInstance());
    				tl.getCreationDate().setTime(task.getDate());
    				tl.setExecutionsNumber(0L);
    				tl.setPriority(task.getPriority());
    				tl.setTaskDescription(task.toString());
    				r.add(tl);
            	} else {
            		for (AccountEntity account: accounts) {
            			if (account.getSystem().getUrl() != null &&
            					account.getType() != AccountType.IGNORED &&
            					Boolean.FALSE.equals(account.getPasswordPolicy().getUnmanaged())) {
	        				SyncAgentTaskLog tl;
	        				tl = new SyncAgentTaskLog();
	        				tl.setTaskId(task.getId());
	        				tl.setComplete( "PENDING");
	        				tl.setAgentCode(account.getSystem().getName());
	        				tl.setCreationDate(Calendar.getInstance());
	        				tl.getCreationDate().setTime(task.getDate());
	        				tl.setExecutionsNumber(0L);
	        				tl.setPriority(task.getPriority());
	        				tl.setTaskDescription(task.toString());
	        				boolean add = true;
	        				for (TaskLogEntity tle : task.getLogs()) {
	        					if (tle.getSystem() == account.getSystem()) {
	        						if ("S".equals(tle.getCompleted())) add = false;
	        						else {
	        							if (tle.getLastExecution() != null) {
	        								tl.setLastExecution(tle.getLastExecution());
	        								tl.setLastExecutionDate(Calendar.getInstance());
	        								tl.getLastExecutionDate().setTime(new Date(tle.getLastExecution()));
	        							}
	        							if (tle.getNextExecution() != null) {
	        								tl.setNextExecution(tle.getNextExecution());
	        								tl.setNextExecutionDate(Calendar.getInstance());
	        								tl.getNextExecutionDate().setTime(new Date(tle.getNextExecution()));	        								
	        							}
        								if ("S".equals(tle.getCompleted())) {
        									tl.setComplete("DONE");
        									add = false;
        								} else if (tle.getMessage() != null) {
        									tl.setComplete("ERROR");
        									tl.setMessage(tle.getMessage());
        								}
	        							tl.setExecutionsNumber(tle.getExecutionsNumber());
	        						}
	        					}
	        				}
	        				if ( add )
	        					r.add(tl);
            			}
            		}
            	}
            }
        }
        return r;
	}

	@Override
	protected void handleSendPassword(String userName, String passwordDomain) throws Exception {
		UserEntity user = getUserEntityDao().findByUserName(userName);
		
		Password result = null;
		
		if ( "S".equals(user.getActive()))
		{
			auditChange("S", userName, passwordDomain);
			for (ServerEntity se : getServerEntityDao().loadAll()) {
	            if (se.getType().equals(ServerType.MASTERSERVER)) {
	            	if (se.getInstances().isEmpty()) {
	            		if (resendPasswordNow(user, passwordDomain, se.getUrl(), se.getAuth())) 
	            			return;
	            	} else {
	            		for (ServerInstanceEntity si: se.getInstances()) {
		            		if (resendPasswordNow(user, passwordDomain, si.getUrl(), si.getAuth())) 
		            			return;
	            			
	            		}
	            	}
	            }
	        }
		}
	}

	private boolean resendPasswordNow(UserEntity user, String passwordDomain, String url, String auth)
			throws InternalErrorException {
		com.soffid.iam.sync.service.SyncStatusService sss = null;
		try {
		    RemoteServiceLocator rsl = new RemoteServiceLocator(url);
		    rsl.setAuthToken(auth);
			rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount());
		    sss = rsl.getSyncStatusService();
		} catch (Exception e) {
			log.warn("Error sending password", e);
		}
		if (sss != null)
		{
			sss.resendUserPassword(user.getUserName(), passwordDomain);
			return true;
		} else
			return false;
	}

	@Override
	protected AsyncProcessTracker handleDisableUsers(String scimQuery, List<DisableObjectRule> rules) throws Exception {
		AsyncProcessTracker t = new AsyncProcessTracker();
		t.setStart(new Date());
		t.setProgress((float)0.0);
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		new Thread( () -> {
			Security.nestedLogin(principal);
			try {
				getAsyncRunnerService().runNewTransaction(() -> {
					HashSet<Long> processedUsers = new HashSet<>();
					int steps = 0;
					for (DisableObjectRule rule: rules) {
						if (rule.getCriteria() != null && rule.getAction() != null)
							applyRule (t, scimQuery, rule, processedUsers, null);
						steps ++ ;
						t.setProgress((float)steps / rules.size());
					}
					return null;
				});
			} catch (InternalErrorException e) {
				log.warn("Error processing task", e);
				t.setErrorMessage(SoffidStackTrace.generateShortDescription(e));
			} finally {
				Security.nestedLogoff();				
				t.setEnd(new Date());
				t.setFinished(true);
			}
		}).start();
		return t;
	}

	@Override
	protected AsyncProcessTracker handleDisableUsersPreview(String scimQuery, List<DisableObjectRule> rules, List<Object[]> actions) throws Exception {
		AsyncProcessTracker t = new AsyncProcessTracker();
		t.setStart(new Date());
		t.setProgress((float)0.0);
		getAsyncRunnerService().run(() -> {
			try {
				HashSet<Long> processedUsers = new HashSet<>();
				int steps = 0;
				for (DisableObjectRule rule: rules) {
					try {
						if (rule.getCriteria() != null)
							applyRule (t, scimQuery, rule, processedUsers, actions);
						steps ++ ;
						t.setProgress((float)steps / rules.size());
					} catch (Exception e) {
						log.warn("Error processing task", e);
						t.setErrorMessage(SoffidStackTrace.generateShortDescription(e));
					}
				}
			} finally {
				t.setEnd(new Date());
				t.setFinished(true);
			}
		}, new AsyncList<>());
		return t;
	}

	private void applyRule(AsyncProcessTracker t, String scimQuery, DisableObjectRule rule, HashSet<Long> processedUsers, List<Object[]> actions) throws UnsupportedEncodingException, ClassNotFoundException, JSONException, InternalErrorException, EvalException, ParseException, TokenMgrError {
		String query;
		ScimHelper h = new ScimHelper(User.class);
		h.setTenantFilter("tenant.id");
		if ("P".equals(rule.getCriteria())) {
			HashMap m = new HashMap<>();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, - rule.getParameter());
			m.put("limit", c.getTime());
			h.setExtraWhere("(select max(pass.expirationDate) from com.soffid.iam.model.PasswordEntityImpl as pass where pass.user.id=o.id) < :limit");
			h.setExtraParameters(m);
		}
		else if ("L".equals(rule.getCriteria())) {
			HashMap m = new HashMap<>();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, - rule.getParameter());
			m.put("limit", c.getTime());
			h.setExtraWhere("(select max(acc.lastLogin) from com.soffid.iam.model.UserAccountEntityImpl as ua join ua.account as acc where ua.user.id=o.id) < :limit");
			h.setExtraParameters(m);
		} else {
			return;
		}
		
		
		h.setOrder("o.id");
		h.setPageSize(100);
		AsyncList<Object> list = new AsyncList<>();
		h.setGenerator((data) -> {
			if (t.isCancelled())
				list.cancel();
			UserEntity user = (UserEntity) data;
			if (! processedUsers.contains(user.getId())) {
				processedUsers.add(user.getId());
				if (actions != null) {
					synchronized(actions) {
						actions.add(new Object[] {user.getUserName(), user.getFullName(), rule.getAction()});
					}
				} else {
					try {
						processAction (user, rule);
					} catch (Exception e) {
						throw new SeyconException("Error processing user "+user.getUserName(), e);
					}
				}
			}
			return null;
		});
		h.search(null, scimQuery, list);
	}

	private void processAction(UserEntity user, DisableObjectRule rule) throws Exception {
		User userObject = getUserEntityDao().toUser(user);
		if (rule.getAction().equals("E")) {
			List<String> actors = new LinkedList<>();
			
			try {
				if (rule.getEmailCopy() != null && ! rule.getEmailCopy().trim().isEmpty()) {
					String actorsString = replace(rule.getEmailCopy(), userObject);
					if (actorsString.startsWith("[") && actorsString.endsWith("]"))
						actorsString = actorsString.substring(1, actorsString.length()-1);
					for (String actor: actorsString.split("[ ,]+")) {
						actors.add(actor);
					}
				}
				else
					actors.add(user.getUserName());

				if (rule.getEmailBody() != null && !rule.getEmailBody().trim().isEmpty() &&
						rule.getEmailSubject() != null && !rule.getEmailSubject().trim().isEmpty())
					getMailService().sendHtmlMailToActors(actors.toArray(new String[actors.size()]),
						replace(rule.getEmailSubject(), userObject),
						replace(rule.getEmailBody(), userObject));
			} catch (InternalErrorException e) {
				log.warn("Error sending notification email to "+user.getUserName(), e);
			}
		}
		if (rule.getAction().equals("D")) {
			if (userObject.getActive().booleanValue() ) {
				userObject.setActive(false);
				handleUpdate(userObject);
			}
		}
		if (rule.getAction().equals("R")) {
			for (UserAccount acc: getAccountService().getUserAccounts(getCurrentUser())) {
				if ( acc.getStatus() != AccountStatus.REMOVED) {
					acc.setStatus(AccountStatus.REMOVED);
					getAccountService().updateAccount2(acc);
				}
			}
		}
	}

	private String replace(String text, User user) {
		
		text = text.replace("#{", "${");
		
		VariableResolver pResolver = new ObjectVariableResolver (user);
		ExpressionEvaluatorImpl ee = new ExpressionEvaluatorImpl();
		FunctionMapper functions  = null;
		return (String) ee.evaluate(text, String.class, pResolver , functions);
		
	}

	@Override
	protected void handleMerge(Long srcId, Long targetId, Long eventId) throws Exception {
		UserEntity su = getUserEntityDao().load(srcId);
		Collection<IssueUserEntity> events = new LinkedList<>( su.getEvents() );
		
		UserEntity tu = getUserEntityDao().load(targetId);
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction("M"); //$NON-NLS-1$
		auditoria.setUser(tu.getUserName());
		auditoria.setAuthor(codiUsuari);
		auditoria.setObject("SC_USUARI"); //$NON-NLS-1$
		auditoria.setComment(su.getUserName());

		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
		getUserEntityDao().merge(srcId, targetId);
		
		fixTransitiveIssues(events, eventId);
	}
	
	private void fixTransitiveIssues(Collection<IssueUserEntity> events, Long eventId) throws IOException, InternalErrorException {
		for (IssueUserEntity ue2: events) {
			final IssueEntity issue2 = ue2.getIssue();
			if (issue2 != null &&
					! issue2.getId().equals(eventId) &&
					issue2.getStatus() != IssueStatus.SOLVED &&
					issue2.getStatus() != IssueStatus.SOLVED_NOTADUPLICATE &&
					issue2.getType().equals("duplicated-user")) {
				ue2.setUser(null);
				ue2.setAction(EventUserAction.DIFFERENT_USER);
				getIssueUserEntityDao().update(ue2);
				if (noCandidateUsers(issue2)) {
					Issue i = getIssueEntityDao().toIssue(issue2);
					for (IssueUser iu3: i.getUsers()) {
						iu3.setAction(EventUserAction.DIFFERENT_USER);
					}
					i.setStatus(IssueStatus.SOLVED);
					getIssueService().update(i);
				}
			}
		}
	}
	
	private boolean noCandidateUsers(IssueEntity issue) {
		int n = 0;
		for (IssueUserEntity iu: issue.getUsers()) {
			if (iu.getUser() != null)
				n ++;
		}
		return n <= 1;
	}

	@Override
	protected List<PasswordDomainStatus> handleFindPasswordDomainStatus(String user) throws Exception {
		UserEntity entity = getUserEntityDao().findByUserName(user);
		List<PasswordDomainStatus> r = new LinkedList<>();
		for (PasswordDomainEntity domain: getPasswordDomainEntityDao().findByUser(entity.getId())) {
			PasswordDomainStatus status = new PasswordDomainStatus();
			status.setDomainName(domain.getName());
			for (PasswordEntity pass: getPasswordEntityDao().findByUserDomain(entity, domain)) {
				if (pass.getOrder() == null || pass.getOrder().longValue() == 0) {
					status.setFailures(pass.getFails());
					status.setLockedUntil(pass.getUnlockDate());
					break;
				}
			}
			r.add(status);
		}
		return r;
	}

	@Override
	protected void handleUnlockPasswordDomain(String user, String passwordDomain)
			throws Exception {
		UserEntity entity = getUserEntityDao().findByUserName(user);
		List<PasswordDomainStatus> r = new LinkedList<>();
		PasswordDomainEntity pd = getPasswordDomainEntityDao().findByName(passwordDomain);
		if (entity != null && pd != null) {
			for (PasswordEntity pass: getPasswordEntityDao().findByUserDomain(entity, pd)) {
				if (pass.getOrder() == null || pass.getOrder().longValue() == 0) {
					pass.setFails(0);
					pass.setUnlockDate(null);
					getPasswordEntityDao().update(pass);
				}
			}
		}
	}
}
