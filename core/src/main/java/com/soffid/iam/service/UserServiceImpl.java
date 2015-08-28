package com.soffid.iam.service;

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.BpmProcess;
import com.soffid.iam.api.BpmUserProcess;
import com.soffid.iam.api.ConsoleProperties;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.ExtranetCard;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.MailList;
import com.soffid.iam.api.Printer;
import com.soffid.iam.api.PrinterUser;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Session;
import com.soffid.iam.api.Student;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.UserCriteria;
import com.soffid.iam.api.UserData;
import com.soffid.iam.api.UserMailList;
import com.soffid.iam.bpm.service.BpmEngine;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.CardEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.GroupEntityDao;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.PasswordDomainEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleDependencyEntityDao;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.RoleGroupEntityDao;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.ServerEntityDao;
import com.soffid.iam.model.SessionEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserEntityDao;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.UserPreferencesEntity;
import com.soffid.iam.model.UserPrinterEntity;
import com.soffid.iam.model.UserProcessEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.ConfigurationService;
import com.soffid.iam.service.GroupService;
import com.soffid.iam.service.MailListsService;
import com.soffid.iam.service.impl.CertificateParser;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.DateUtils;
import com.soffid.iam.utils.LimitDates;
import com.soffid.iam.utils.ProcesWFUsuari;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.conf.ClassConfig;
import com.soffid.scimquery.conf.Configuration;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;

import es.caib.seycon.ng.comu.AccountType;

import com.soffid.iam.api.Password;
import com.soffid.iam.api.PolicyCheckResult;

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.remote.RemoteServiceLocator;
import es.caib.seycon.ng.sync.servei.SyncStatusService;
import es.caib.signatura.api.ParsedCertificate;
import es.caib.signatura.api.Signature;
import es.caib.signatura.cliente.ValidadorCertificados;
import es.caib.signatura.cliente.XML;
import es.caib.signatura.utils.BitException;
import es.caib.signatura.validacion.ResultadoValidacion;
import es.map.www.scsp.esquemas.V2.peticion.Atributos;
import es.map.www.scsp.esquemas.V2.peticion.Consentimiento;
import es.map.www.scsp.esquemas.V2.peticion.DatosGenericos;
import es.map.www.scsp.esquemas.V2.peticion.Emisor;
import es.map.www.scsp.esquemas.V2.peticion.Peticion;
import es.map.www.scsp.esquemas.V2.peticion.Solicitante;
import es.map.www.scsp.esquemas.V2.peticion.SolicitudTransmision;
import es.map.www.scsp.esquemas.V2.peticion.Solicitudes;
import es.map.www.scsp.esquemas.V2.peticion.TipoDocumentacion;
import es.map.www.scsp.esquemas.V2.peticion.Titular;
import es.map.www.scsp.esquemas.V2.peticion.Transmision;
import es.map.www.scsp.esquemas.V2.respuesta.Respuesta;
import es.map.www.scsp.esquemas.datosespecificos.DatosEspecificos;
import es.map.www.scsp.esquemas.datosespecificos.EstadoResultado;
import es.map.www.scsp.esquemas.datosespecificos.SolicitanteDatos;
import es.map.www.scsp.esquemas.datosespecificos.Solicitud;
import es.map.www.xml_schemas.VerificacionIdentidadLocator;
import es.map.www.xml_schemas.VerificacionIdentidadSoapBindingStub;
import es.map2.www.xml_schemas.ConsultaIdentidadLocator;
import es.map2.www.xml_schemas.ConsultaIdentidadSoapBindingStub;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.configuration.FileProvider;
import org.jboss.mq.il.uil2.msgs.GetIDMsg;
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//- WS verificacion de usuario red sara

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

		// Esborrem les associacions amb llistes de correu
		// S'esborren les llistes de correu òrfenes i les seves associacions
		User usu = getUserEntityDao().toUser(usuariEntity);
		usu.setMailAlias("");//Per esborrarles //$NON-NLS-1$
		arreglaAlias(usu);

		/*
		 * Se eliminan los roles de los usuarios
		 */
		getRoleAccountEntityDao().remove(
				getRoleAccountEntityDao().findByUserName(
						usuariEntity.getUserName()));

		/*
		 * Se eliminan las asociaciones a grupos
		 */
		Collection grups = usuariEntity.getSecondaryGroups();
		getUserGroupEntityDao().remove(grups);

		/*
		 * Se elimina la asociación con el grupo primario y se le pone como
		 * grupo primario "portal"
		 */
		GroupEntity grupEntity = getGroupEntityDao().findByName("portal"); //$NON-NLS-1$
		usuariEntity.setPrimaryGroup(grupEntity);

		/*
		 * Se elimina el dato adicional CODI_USUARI_IBSALUT
		 */
		UserDataEntity dadaCodiIBSALUT = getUserDataEntityDao().findByDataType(
				codiUsuari, "CODI_USUARI_IBSALUT"); //$NON-NLS-1$
		if (dadaCodiIBSALUT != null) {
			getUserDataEntityDao().remove(dadaCodiIBSALUT);
		}

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
			getMailListsService().userMailList(llistaCorreuUsuari);
		}

		/*
		 * Se eliminan las asociaciones con redes
		 */
		Collection xarxes = usuariEntity.getACNetwork();
		this.getNetworkAuthorizationEntityDao().remove(xarxes);

		/*
		 * Se eliminan las asociaciones con servidores
		 */
		HostEntity maquinaNul = getHostEntityDao().findByName("nul"); //$NON-NLS-1$
		usuariEntity.setHomeServer(maquinaNul);
		usuariEntity.setMailServer(maquinaNul);
		usuariEntity.setProfileServer(maquinaNul);

		usuariEntity.setLastUserModification(Security.getCurrentAccount());
		usuariEntity.setLastModificationDate(GregorianCalendar.getInstance()
				.getTime());
		usuariEntity.setShortName(null);
		usuariEntity.setMailDomain(null);

		/*
		 * Se eliminan las tarjetas
		 */
		/*
		 * Collection targetes = usuariEntity.getTargetesCPD(); if (targetes !=
		 * null) { getTarjaCPDEntityDao().remove(targetes); }
		 */

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
		 * Se asigna usuario de tipo externo
		 */
		// TODO: En principi ha d'existir el tipus d'usuari (E)xtern
		UserTypeEntity tipusE = getUserTypeEntityDao().findByName("E"); //$NON-NLS-1$
		usuariEntity.setUserType(tipusE);

		/*
		 * Se pone en activo
		 */
		usuariEntity.setActive("S"); //$NON-NLS-1$

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

		// VERIFICACIÓN DE IDENTIDAD: comprobación WS de red SARA
		String[] resultat = null;
		try {
			// Permitim que es lleve el NIF d'un usuari
			if (esNIFoNIE(usuari.getNationalID())) {
				// Verificamos con el servicio web de la red sara que los datos
				// del usuario sean correctos:
				if (getConfiguracioServeiVerificacioIndentitatUsuari() != null) { // només
																					// si
																					// el
																					// servei
																					// és
																					// actiu
					resultat = checkUserIdentity(usuari.getFirstName(),
							usuari.getLastName(), usuari.getMiddleName(),
							usuari.getNationalID());
				}
			}
		} catch (Throwable th) {
			// Capturamos errores de falta de parámetro:
			if (th.getCause() instanceof SeyconException)
				throw new Exception(th.getCause());

			// Evitamos problemas que pueden surgir con la llamada a los
			// servicios web
			resultat = null;

			if (resultat == null) {
				throw new SeyconException(
						String.format(
								Messages.getString("UserServiceImpl.VerificationServiceError"), th.getMessage())); //$NON-NLS-1$
			}
		}

		if (resultat != null && !"0003".equals(resultat[0])) //$NON-NLS-1$
			if (resultat != null && !"0003".equals(resultat[0])) //$NON-NLS-1$
				throw new SeyconException(
						String.format(
								Messages.getString("UserServiceImpl.VerificationServiceCodError"), resultat[1], resultat[0])); //$NON-NLS-1$

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

		getUserEntityDao().create(usuariEntity);

		/* Una vez creado, se almacena el NIF */
		UserData dadaUsuari = new UserData();
		dadaUsuari.setAttribute("NIF"); //$NON-NLS-1$
		dadaUsuari.setUser(usuari.getUserName());
		dadaUsuari.setValue(usuari.getNationalID());
		dadaUsuari.setBlobDataValue(null);
		UserDataEntity dadaUsuariEntity = getUserDataEntityDao()
				.userDataToEntity(dadaUsuari);
		getUserDataEntityDao().create(dadaUsuariEntity);

		/* El teléfon es guarda quan ja s'ha creat l'usuari */
		if (usuari.getPhoneNumber() != null) {
			UserData telf = new UserData();
			telf.setAttribute("PHONE"); //$NON-NLS-1$
			telf.setUser(usuari.getUserName());
			telf.setValue(usuari.getPhoneNumber());
			UserDataEntity telfEntity = getUserDataEntityDao()
					.userDataToEntity(telf);
			getUserDataEntityDao().create(telfEntity);
		}

		crearLlistaCorreu(usuari);

		/*
		 * String contrasenya = getContrasenyaEntityDao().generaPasswordInicial(
		 * usuariEntity.getCodi(), usuariEntity.getId());
		 * getContrasenyaEntityDao().assignaPassword(usuariEntity.getCodi(),
		 * usuariEntity.getId(), contrasenya);
		 */

		usuari.setId(usuariEntity.getId());
		/* Se devuelve la instancia de usuario creada */
		getAccountService().generateUserAccounts(usuari.getUserName());

		/* IAM-318: Propagar canvis de grup primari */
		GroupService service = getGroupService();
		service.propagateRolsChangesToDispatcher(usuari.getPrimaryGroup());

		getRuleEvaluatorService().applyRules(usuariEntity);

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
			System.out.println(Messages.getString("UserServiceImpl.Error") + e); //$NON-NLS-1$
			e.printStackTrace();
			throw new SeyconException(e.getMessage());
		}
	}

	private String comprobarXML(Document document, boolean user) {
		String resultado = ""; //$NON-NLS-1$
		int i = 0;
		if (this.getValue("Nom", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") + " nom"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// if (this.getValue("NomCurt", document) == null) {
		// resultado += (resultado.length() == 0 ? "" : ",") + " nom curt";
		// }
		if (this.getValue("NIF", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") + " nif"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		if (this.getValue("PrimerLlinatge", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") //$NON-NLS-1$ //$NON-NLS-2$
					+ Messages.getString("UserServiceImpl.FirstSurname"); //$NON-NLS-1$
		}
		/*
		 * if (this.getValue("SegonLlinatge", document) == null) { resultado +=
		 * (resultado.length() == 0 ? "" : ",") + " segón llinatge"; }
		 */
		if (this.getValue("UnitatOrganitzativa", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") //$NON-NLS-1$ //$NON-NLS-2$
					+ Messages.getString("UserServiceImpl.OrgUnity"); //$NON-NLS-1$
		} /*
		 * else {// Cambio brújula // Modificado u93387 // Si la unidad
		 * organizativa es la correspondiente al IBSalut, habrá que cerciorarse
		 * // de que se ha introducido el código de usuario del IBSalut if
		 * (this.getValue("UnitatOrganitzativa", document).equals("X")) { //
		 * Donde X es el código correspondiente al IBSalut if
		 * (this.getValue("CodiUsuariIBSalut", document) == null) { resultado +=
		 * (resultado.length() == 0 ? "" : ",") + " codi usuari IBSalut"; } }//
		 * Fin cambio brújula }
		 */
		if (this.getValue("CertificacioDeValidesa", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") //$NON-NLS-1$ //$NON-NLS-2$
					+ Messages.getString("UserServiceImpl.Certification"); //$NON-NLS-1$
		}
		if (this.getValue("CertificacioDeCondicions", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") //$NON-NLS-1$ //$NON-NLS-2$
					+ Messages.getString("UserServiceImpl.CondCertification"); //$NON-NLS-1$
		}
		if (this.getValue("TipusUsuari", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") //$NON-NLS-1$ //$NON-NLS-2$
					+ Messages.getString("UserServiceImpl.UserType"); //$NON-NLS-1$
		}
		if (this.getValue("Telefon", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") + Messages.getString("UserServiceImpl.UserPhone"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return (resultado);
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
			Context ctx = new InitialContext();
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
				com.soffid.iam.api.Configuration configuracio = getConfigurationService()
						.findParameterByNameAndNetworkName(
								"seycon.password.age." + usuari.getUserType(),
								null);
				if (configuracio == null) {
					usuari.setPasswordMaxAge(new Long(45));
				} else {
					usuari.setPasswordMaxAge(new Long(Long
							.parseLong(configuracio.getValue())));
				}
				usuari.setUserName(user);
				usuari.setCreatedDate(GregorianCalendar.getInstance());
				usuari.setCreatedByUser("SEYCON"); //$NON-NLS-1$
				usuari.setNationalID(nif);

				// Creem l'usuari
				UserEntity usuariEntity = getUserEntityDao().userToEntity(
						usuari);
				getUserEntityDao().create(usuariEntity);
				/* Una vez creado, se almacena el NIF a nivell VO */
				usuari = getUserEntityDao().toUser(usuariEntity);
				usuari.setNationalID(nif);
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
			Context ctx = new InitialContext();
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
				com.soffid.iam.api.Configuration configuracio = getConfigurationService()
						.findParameterByNameAndNetworkName(
								"seycon.password.age." + usuari.getUserType(),
								null);
				if (configuracio == null) {
					usuari.setPasswordMaxAge(new Long(45));
				} else {
					usuari.setPasswordMaxAge(new Long(Long
							.parseLong(configuracio.getValue())));
				}
				usuari.setUserName(user);
				usuari.setCreatedDate(GregorianCalendar.getInstance());
				usuari.setCreatedByUser("SEYCON"); //$NON-NLS-1$
				usuari.setNationalID(nif);

				// Creem l'usuari
				UserEntity usuariEntity = getUserEntityDao().userToEntity(
						usuari);
				getUserEntityDao().create(usuariEntity);
				/* Una vez creado, se almacena el NIF a nivell VO */
				usuari = getUserEntityDao().toUser(usuariEntity);
				usuari.setNationalID(nif);
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
			String dominiCorreu, String grupSecundari, Boolean restringeixCerca) {
		String query = "select usuari " //$NON-NLS-1$
				+ "from com.soffid.iam.model.UserEntity usuari " //$NON-NLS-1$
				+ "left join usuari.profileServer as servidorPerfil " //$NON-NLS-1$
				+ "left join usuari.homeServer as servidorOfimatic " //$NON-NLS-1$
				+ "left join usuari.mailServer as servidorCorreu " //$NON-NLS-1$
				+ "left join usuari.primaryGroup as grupPrimari " //$NON-NLS-1$
				+ "left join usuari.mailDomain as dominiCorreu " //$NON-NLS-1$
				+ "left join usuari.userType as tipusUsuariDomini "; //$NON-NLS-1$
		if (dni != null) {
			query += "left join usuari.dadaUsuari as dadaUsuari " //$NON-NLS-1$
					+ "left join dadaUsuari.tipusDada as tipusDada "; //$NON-NLS-1$
		}
		query += "where " //$NON-NLS-1$
		// + (restringeixCerca.booleanValue() ? "(rownum < 202) and " : "")
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
			query += " and (dadaUsuari.valorDada like :dni and tipusDada.name = 'NIF') "; //$NON-NLS-1$
		}
		if (grupSecundari != null) {
			query += " and usuari.id in " //$NON-NLS-1$
					+ "(select grupUsuari.user.id " //$NON-NLS-1$
					+ "from com.soffid.iam.model.UserGroupEntity grupUsuari " //$NON-NLS-1$
					+ "where grupUsuari.group name like :grupSecundari ) "; //$NON-NLS-1$
		}

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
				for (Parameter parameter : new Parameter[] { codiParameter,
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
				for (Parameter parameter : new Parameter[] { codiParameter,
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
				for (Parameter parameter : new Parameter[] { codiParameter,
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
				for (Parameter parameter : new Parameter[] { codiParameter,
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
			limitResults = Integer.parseInt(System
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
		if (!getAuthorizationService().hasPermission(Security.AUTO_USER_DELETE,
				usuariEntity)) {
			throw new SeyconAccessLocalException(
					"UsuariService", "delete (Usuari)", "user:delete, user:delete/*", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("UserServiceImpl.NoAuthorizedToDelete")); //$NON-NLS-1$
		}
		if (usuariEntity != null) {
			// els usuaris mai s'eliminen, es fa un downgrade
			disableUser(usuari.getUserName());
			removeOldAlias(usuari);
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
						getMailListsService().userMailList(llistaCorreuUsuari);
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
			getMailListsService().userMailList(llistaCorreuUsuari);
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

		// Si sólo puede actualizar datos adicionales (teléfono, actualizamos
		// sólo eso!!)
		if (!canUpdateEsteUser) {
			// Només actualitzem el telèfon (mantenim la resta de dades..)
			if (usuari.getId() != null) {
				if (usu != null) {
					User usuTrobat = getUserEntityDao().toUser(usu);
					if (usuTrobat.getPhoneNumber() == null
							&& usuari.getPhoneNumber() != null
							|| !usuTrobat.getPhoneNumber().equals(
									usuari.getPhoneNumber())) {
						// Només si ha canviat el número de telèfon
						usuTrobat.setModifiedByUser(Security
								.getCurrentAccount());
						usuTrobat.setModifiedDate(GregorianCalendar
								.getInstance());
						usuTrobat.setPhoneNumber(usuari.getPhoneNumber()); // Guardem
																			// el
																			// telèfon
						UserEntity entity = getUserEntityDao().userToEntity(
								usuTrobat);
						if (entity != null) {
							getUserEntityDao().update(entity);
							getUserEntityDao().createUpdateTasks(entity,
									usuTrobat);

							return getUserEntityDao().toUser(entity);
						}
					} // Si no ha canviat el telèfon: donem error... no ha de
						// tindre permis per canviar res més
					else if (usuTrobat.getComments() == null
							&& usuari.getComments() != null
							|| !usuTrobat.getComments().equals(
									usuari.getComments())) {
						// Deixem que s'actualitzen les observacions (!!)
						usuTrobat.setModifiedByUser(Security
								.getCurrentAccount());
						usuTrobat.setModifiedDate(GregorianCalendar
								.getInstance());
						usuTrobat.setComments(usuari.getComments()); // Guardem
																		// les
																		// observacions
						UserEntity entity = getUserEntityDao().userToEntity(
								usuTrobat);
						if (entity != null) {
							getUserEntityDao().update(entity);
							getUserEntityDao().createUpdateTasks(entity,
									usuTrobat);
							return getUserEntityDao().toUser(entity);
						}
					}
				}
			}
			// Donem error si no s'ha trobat (??)
			throw new SeyconAccessLocalException(
					"UsuariService", "update (Usuari)", "user:update, user:update/*", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("UserServiceImpl.NoAuthorizedToUpdate")); //$NON-NLS-1$
		}

		/*
		 * // Comprobamos que no exista ya el usuario a crear (puede haber ya
		 * varios) String NIF = usuari.getNIF(); if (NIF!=null &&
		 * !"".equals(NIF.trim())) { //$NON-NLS-1$ NIF = NIF.trim(); Collection
		 * usuarisMateixNIF = getUserEntityDao().findUsersByNationalID(NIF); if
		 * (usuarisMateixNIF!=null) { if (usuarisMateixNIF.size()==1)
		 * {//comprobamos que no sea al mismo !! UserEntity usuariExist =
		 * (UserEntity) usuarisMateixNIF.iterator().next(); if
		 * (!usuari.getId().equals(usuariExist.getId())) throw new
		 * SeyconException
		 * (String.format(Messages.getString("UserServiceImpl.ExistsUser"),
		 * usuariExist.getUserName())); } else if (usuarisMateixNIF.size()!=0) {
		 * // hay más de 1 String codiUsuaris=""; //$NON-NLS-1$ for (Iterator it
		 * = usuarisMateixNIF.iterator(); it.hasNext(); ) { codiUsuaris += "\'"
		 * + ((UserEntity) it.next()).getUserName() + "\', "; } codiUsuaris =
		 * codiUsuaris.substring(0,codiUsuaris.length()-2); throw new
		 * SeyconException
		 * (String.format(Messages.getString("UserServiceImpl.ExistsUser"),
		 * //$NON-NLS-1$ codiUsuaris)); } } }
		 */

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
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				throw new InternalErrorException(
						String.format(
								"The user %s cannot be modified as it has been modified by %s on %s",
								previousUser.getUserName(), previousUser
										.getModifiedByUser(), sdf
										.format(usuariAbans
												.getLastModificationDate())));
			}
		}
		// Verifiquem si hem de fer la comprovació de la identitat:
		// s'ha modifcat el nif de l'usuari??
		UserDataEntity nifAnterior = getUserDataEntityDao().findByDataType(
				usuari.getUserName(), "NIF"); //$NON-NLS-1$
		// Updates user name (if needed)
		if (!usuariAbans.getUserName().equals(usuari.getUserName())) {
			for (UserProcessEntity upe : getUserProcessEntityDao()
					.findByUserName(usuariAbans.getUserName())) {
				upe.setUserName(usuari.getUserName());
				getUserProcessEntityDao().update(upe);
			}
			usuariAbans.setUserName(usuari.getUserName());
			getUserEntityDao().update(usuariAbans);
			getUserEntityDao().createUpdateTasks(usuariAbans, previousUser);
		}
		// verifiquem canvis al nom, llinatges i nif de l'usuari
		if (usuariAbans != null
				&& (usuariAbans.getFirstName() != null && !usuariAbans
						.getFirstName().equals(usuari.getFirstName()))
				|| (usuariAbans.getLastName() != null && !usuariAbans
						.getLastName().equals(usuari.getLastName()))
				|| (usuariAbans.getMiddleName() != null && !usuariAbans
						.getMiddleName().equals(usuari.getMiddleName()))
				|| (usuariAbans.getMiddleName() == null && usuari
						.getMiddleName() != null)
				|| (nifAnterior != null && nifAnterior.getValue() != null && !nifAnterior
						.getValue().equals(usuari.getNationalID()))
				|| (nifAnterior == null && usuari.getNationalID() != null)) {
			// Verificamos datos con red SARA:
			String[] resultat = null;
			try {
				// Sólo si és un nif o nie i el servei està activat
				if (esNIFoNIE(usuari.getNationalID())
						&& getConfiguracioServeiVerificacioIndentitatUsuari() != null) { // si
																							// és
																							// actiu
																							// el
																							// servei
					// Verificamos con el servicio web de la red sara que los
					// datos
					// del usuario sean correctos:
					resultat = checkUserIdentity(usuari.getFirstName(),
							usuari.getLastName(), usuari.getMiddleName(),
							usuari.getNationalID());
				}
			} catch (Throwable th) {
				// Capturamos errores de falta de parámetro:
				if (th.getCause() instanceof SeyconException)
					throw new Exception(th.getCause());

				// Evitamos problemas que pueden surgir con la llamada a los
				// servicios web
				resultat = null;
				if (resultat == null) {
					throw new SeyconException(
							String.format(
									Messages.getString("UserServiceImpl.VerificationDataServerError"), //$NON-NLS-1$
									th.getMessage()));
				}
			}

			if (resultat != null && !"0003".equals(resultat[0])) { //$NON-NLS-1$
				String msgError203 = "0233".equals(resultat[0]) ? Messages.getString("UserServiceImpl.CheckUserName") : ""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				throw new SeyconException(
						String.format(
								Messages.getString("UserServiceImpl.VerificationServiceErrorCod"), //$NON-NLS-1$
								resultat[0], resultat[1], msgError203));
			}
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
		}

		arreglaAlias(usuari);
		usuari.setModifiedByUser(Security.getCurrentAccount());
		usuari.setModifiedDate(GregorianCalendar.getInstance());
		UserEntity entity = getUserEntityDao().userToEntity(usuari);
		if (entity != null) {
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
		Collection<UserData> dades = getUserDataEntityDao().toUserDataList(
				usuari.getUserData());
		LinkedList<UserData> result = new LinkedList<UserData>();

		List<MetaDataEntity> tipusDades = getMetaDataEntityDao().loadAll();
		Collections.sort(tipusDades, new Comparator<MetaDataEntity>() {

			public int compare(MetaDataEntity o1, MetaDataEntity o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});

		Iterator<MetaDataEntity> tipusDadesIterator = tipusDades.iterator();
		while (tipusDadesIterator.hasNext()) {
			MetaDataEntity tipusDada = tipusDadesIterator.next();
			if (tipusDada.getName().compareTo(NIF) != 0
					&& tipusDada.getName().compareTo(TELEFON) != 0) {
				Iterator<UserData> dadesIterator = dades.iterator();
				boolean teTipusDada = false;
				while (dadesIterator.hasNext()) {
					UserData dada = dadesIterator.next();
					if (dada.getAttribute().compareTo(NIF) != 0
							&& dada.getAttribute().compareTo(TELEFON) != 0
							&& dada.getAttribute().compareTo(
									tipusDada.getName()) == 0) {
						teTipusDada = true;
						if (!dada.getVisibility().equals(
								AttributeVisibilityEnum.HIDDEN))
							result.add(dada);
					}
				}
				if (!teTipusDada) {
					UserDataEntity dus = getUserDataEntityDao()
							.newUserDataEntity();
					dus.setUser(usuari);
					dus.setDataType(tipusDada);
					if (!dus.getAttributeVisibility().equals(
							AttributeVisibilityEnum.HIDDEN)) {
						result.add(getUserDataEntityDao().toUserData(dus));
					}
				}
			}
		}

		return result;
	}

	protected UserData handleFindDataByTypeDataName(String codiUsuari,
			String codiTipusDada) throws Exception {
		UserDataEntity dadaUsuariEntity = getUserDataEntityDao()
				.findByDataType(codiUsuari, codiTipusDada);
		if (dadaUsuariEntity != null) {
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
		// TODO: Aquí no donem error de que no disposa permisos ... (!!)
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

	protected String handleSetInitialPassword(String codiUsuari,
			String codiDominiContrasenyes) throws Exception {
		return handleChangePassword(codiUsuari, codiDominiContrasenyes);
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

	protected String handleChangePassword(String codiUsuari,
			String codiDominiContrasenyes) throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		if (usuari != null && "S".equals(usuari.getActive())) { //$NON-NLS-1$
			if (getAuthorizationService().hasPermission(
					Security.AUTO_USER_UPDATE_PASSWORD, usuari)) {
				PasswordDomainEntity dominiContrasenyes = getPasswordDomainEntityDao()
						.findByName(codiDominiContrasenyes);
				Password pass = getInternalPasswordService()
						.generateNewPassword(usuari, dominiContrasenyes, true);
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

	transient private Random random = new Random(new Date().getTime());

	private void auditaCanviPassword(String codiUsuariAuditat,
			String codiDominiContrasenyes) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction("P"); //$NON-NLS-1$
		auditoria.setUser(codiUsuariAuditat);
		auditoria.setAuthor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar
				.getInstance().getTime()));
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

	protected java.security.Principal getPrincipal() {
		return Security.getPrincipal();
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

	protected Student handleCreatePupilUser(Student usuariAlumne)
			throws Exception {

		// Verifiquem que l'usuari no existisca ja (pot tornar null)
		UserEntity usu = findUsuariExistentperCodiXestib(usuariAlumne
				.getCodiXestib());

		if (usu != null && !"Z".equals(usu.getUserType().getName())) { // Si no és de tipus alumne, no permetem donar d'alta com alumne //$NON-NLS-1$
			throw new SeyconException(String.format(
					Messages.getString("UserServiceImpl.UserAlreadyExists"),
					usuariAlumne.getCodiXestib(), usu.getUserType().getName()));
		}

		if (usu == null) {// creem el usuari VO nou
			User usuari = new User();

			usuari.setUserName(getUserEntityDao().findFollowingAlumnCode());
			usuari.setCreatedDate(GregorianCalendar.getInstance());
			usuari.setMultiSession(new Boolean(false));

			usuari.setActive(new Boolean(true));
			// No en té efecte i ja no existeix al VO
			// usuari.setContrasenyaCaducada(new Boolean(true)); //li asignarem
			// una nova

			// dades bàsiques:
			usuari.setFirstName(usuariAlumne.getNom());
			usuari.setLastName(usuariAlumne.getLlinatge1());
			usuari.setMiddleName(usuariAlumne.getLlinatge2());
			usuari.setUserType("Z"); //Tipus d'usuari alumne //$NON-NLS-1$

			// Assignem el GRUP primari de l'alumne: (el creem si no existeix
			// encara)
			GroupEntity grupCentreAlumne = gestionaGrupAlumne(usuariAlumne
					.getSchool());
			usuari.setPrimaryGroup(grupCentreAlumne.getName());

			usuari.setMailServer("nul"); //$NON-NLS-1$
			usuari.setHomeServer("nul"); //$NON-NLS-1$
			usuari.setProfileServer("nul"); //$NON-NLS-1$

			usuari.setCreatedByUser(getPrincipal().getName());

			// Cridem l'EJB per a que cree l'usuari
			usu = getUserEntityDao().userToEntity(usuari);
			getUserEntityDao().create(usu);

			// creem el codi Xestib com a dada addicional
			UserData dadaUsuariXestib = new UserData();
			dadaUsuariXestib.setAttribute(DADA_ADDICIONAL_CODI_XESTIB);
			dadaUsuariXestib.setUser(usu.getUserName());
			dadaUsuariXestib.setValue(usuariAlumne.getCodiXestib());
			UserDataEntity dadaUsuariEntity = getUserDataEntityDao()
					.userDataToEntity(dadaUsuariXestib);
			getUserDataEntityDao().create(dadaUsuariEntity);

			// creem la descripció del grup de l'alumne com a dada adicional
			UserData dadaUsuariGrupDescripcioXestib = new UserData();
			dadaUsuariGrupDescripcioXestib
					.setAttribute(DADA_ADDICIONAL_CODI_XESTIB_GRUPALUMNE);
			dadaUsuariGrupDescripcioXestib.setUser(usu.getUserName());
			dadaUsuariGrupDescripcioXestib.setValue(usuariAlumne
					.getGrupAlumne());
			UserDataEntity dadaUsuariGrupDescripcioEntity = getUserDataEntityDao()
					.userDataToEntity(dadaUsuariGrupDescripcioXestib);
			getUserDataEntityDao().create(dadaUsuariGrupDescripcioEntity);

			// creem l'adreça de correu extern com a dada addicional
			if (usuariAlumne.getCorreuElectronic() != null
					&& !"".equals(usuariAlumne.getCorreuElectronic())) { //$NON-NLS-1$
				UserData dadaUsuariCorreuElectronic = new UserData();
				dadaUsuariCorreuElectronic.setAttribute(E_MAIL_CONTACTE);
				dadaUsuariCorreuElectronic.setUser(usu.getUserName());
				dadaUsuariCorreuElectronic.setValue(usuariAlumne
						.getCorreuElectronic());
				UserDataEntity dadaUsuariCorreuElectronicEntity = getUserDataEntityDao()
						.userDataToEntity(dadaUsuariCorreuElectronic);
				getUserDataEntityDao().create(dadaUsuariCorreuElectronicEntity);
			}

		} else {
			// L'usuari ja existeix:
			// El transformem a VO
			User usuari = getUserEntityDao().toUser(usu);

			usuari.setActive(new Boolean(true));
			// No en té efecte i ja no existeix al VO:
			// usuari.setContrasenyaCaducada(new Boolean(true));

			// Actualitzem les seues dades bàsiques:
			usuari.setFirstName(usuariAlumne.getNom());
			usuari.setLastName(usuariAlumne.getLlinatge1());
			usuari.setMiddleName(usuariAlumne.getLlinatge2());
			usuari.setUserType("Z"); //Tipus d'usuari alumne //$NON-NLS-1$

			// Indiquem l'usuari que fa le modificacions
			usuari.setModifiedByUser(getPrincipal().getName());

			// Transformem a entity i actualitzem les dades
			usu = getUserEntityDao().userToEntity(usuari);
			getUserEntityDao().update(usu);

			// actualitzem el seu centre (grup primari de l'usuari) i les dades
			// addicionals
			GroupEntity grupCentreAlumne = gestionaGrupAlumne(usuariAlumne
					.getSchool());
			usu.setPrimaryGroup(grupCentreAlumne); // Grup Primari
			// Actualitzem dades addicionals (descripció grup de l'alumne)
			UserDataEntity d_grupAlumne = null;
			UserDataEntity d_correuElectronic = null; // correu electrònic

			Collection dadesAddicionals = new HashSet(usu.getUserData()); // you're
																			// so
																			// lazy
			for (Iterator it = dadesAddicionals.iterator(); it.hasNext();) {
				UserDataEntity dada = (UserDataEntity) it.next();
				if (dada.getDataType().getName()
						.equals(DADA_ADDICIONAL_CODI_XESTIB_GRUPALUMNE)) {
					d_grupAlumne = dada;
				} else if (dada.getDataType().getName().equals(E_MAIL_CONTACTE)) {
					d_correuElectronic = dada;
				}
			}
			// Actualitzem la dada addicional del grup del alumne
			if (d_grupAlumne != null) {
				d_grupAlumne.setValue(usuariAlumne.getGrupAlumne());
				getUserDataEntityDao().update(d_grupAlumne);
			} else {
				UserData dadaUsuariGrupDescripcioXestib = new UserData();
				dadaUsuariGrupDescripcioXestib
						.setAttribute(DADA_ADDICIONAL_CODI_XESTIB_GRUPALUMNE);
				dadaUsuariGrupDescripcioXestib.setUser(usu.getUserName());
				dadaUsuariGrupDescripcioXestib.setValue(usuariAlumne
						.getGrupAlumne());
				UserDataEntity dadaUsuariGrupDescripcioEntity = getUserDataEntityDao()
						.userDataToEntity(dadaUsuariGrupDescripcioXestib);
				getUserDataEntityDao().create(dadaUsuariGrupDescripcioEntity);
			}

			// Actualitzem la dada addicional de correu electrònic
			String correuElectronic = usuariAlumne.getCorreuElectronic();

			// Si l'han esborrat el correu electrònic i existeix ja: l'esborrem
			if (correuElectronic == null || "".equals(correuElectronic.trim())) { //$NON-NLS-1$
				if (d_correuElectronic != null) {
					getUserDataEntityDao().remove(d_correuElectronic);
				}
			} else {// s'ha d'establir la dada addicional
				if (d_correuElectronic != null) {// ja existeix
					d_correuElectronic.setValue(correuElectronic);
					getUserDataEntityDao().update(d_correuElectronic);
				} else {// no existeix: el creem
					UserData dadaUsuariCorreuElectronic = new UserData();
					dadaUsuariCorreuElectronic.setAttribute(E_MAIL_CONTACTE);
					dadaUsuariCorreuElectronic.setUser(usu.getUserName());
					dadaUsuariCorreuElectronic.setValue(usuariAlumne
							.getCorreuElectronic());
					UserDataEntity dadaUsuariCorreuElectronicEntity = getUserDataEntityDao()
							.userDataToEntity(dadaUsuariCorreuElectronic);
					getUserDataEntityDao().create(
							dadaUsuariCorreuElectronicEntity);
				}
			}

			// Actualitzem l'usuari (dades addicionals)
			getUserEntityDao().update(usu);
		}

		// Renovem contrasenya:
		String contrasenya = generaContrasenyaAlumne(6);
		// Establim contrasenya a l'usuari
		// Generem la tasca
		Task canviaPass = new Task();
		canviaPass.setTransaction("UpdateUserPassword");//Actualització del password de l'usuari //$NON-NLS-1$
		canviaPass.setTaskDate(Calendar.getInstance());
		canviaPass.setUser(usu.getUserName());
		canviaPass.setPassword(contrasenya);
		canviaPass.setPasswordChange("N");//Posem que no ha de Canviar contrasenya //$NON-NLS-1$
		TaskEntity tasca = getTaskEntityDao().taskToEntity(canviaPass);
		getTaskEntityDao().create(tasca);

		usuariAlumne.setContrasenya(contrasenya);

		// Retornem el codi de l'usuari
		usuariAlumne.setUserCode(usu.getUserName());

		// Actualitzem els canvis al UsuariEntity
		getUserEntityDao().update(usu);

		getRuleEvaluatorService().applyRules(usu);

		return usuariAlumne;
	}

	protected ExtranetCard handleCreateExtranetCard(String codiUsuari)
			throws Exception {

		CardEntity entity = getCardEntityDao().createExtranetCard(codiUsuari);
		if (entity != null)
			return getCardEntityDao().toExtranetCard(entity);

		return null;
	}

	protected Collection<ExtranetCard> handleFindExtranetCardsByUserName(
			String codiUsuari, String activa) throws Exception {

		UserEntity ue = getUserEntityDao().findByUserName(codiUsuari);
		if (ue == null)
			return null;
		LinkedList<ExtranetCard> cards = new LinkedList<ExtranetCard>();
		for (CardEntity t : ue.getExtranetCard()) {
			if (activa.equals(t.getActive()))
				cards.add(getCardEntityDao().toExtranetCard(t));
		}
		return cards;
	}

	protected ExtranetCard handleUpdate(ExtranetCard targetaExtranet)
			throws Exception {
		CardEntity entity = getCardEntityDao().extranetCardToEntity(
				targetaExtranet);
		if (entity != null) {
			getCardEntityDao().update(entity);
			return getCardEntityDao().toExtranetCard(entity);
		}
		return null;
	}

	protected ExtranetCard handleFindExtranetCardByUserNameAndCardName(
			String codiUsuari, String codiTargeta) throws Exception {
		CardEntity entity = getCardEntityDao().findByCardCodeAndUserCode(
				codiTargeta, codiUsuari);
		if (entity == null)
			throw new SeyconException(
					Messages.getString("UserServiceImpl.NoCardFounded")); //$NON-NLS-1$
		return getCardEntityDao().toExtranetCard(entity);
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

	// Per a VerificarIdentitatUsuari
	private String formatDate(Date f) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat(
				"yyyy-MM-dd'T'hh:mm:ss.S"); //$NON-NLS-1$
		String strFecha = formatoFecha.format(f);

		SimpleDateFormat formatoTimeZone = new SimpleDateFormat("Z"); //$NON-NLS-1$
		String strTimeZone = formatoTimeZone.format(f);
		strTimeZone = new StringBuffer(strTimeZone).insert(3, ":").toString(); //$NON-NLS-1$

		return strFecha.concat(strTimeZone);
	}

	protected String[] handleCheckUserIdentity(String nom, String llinatge1,
			String llinatge2, String document) throws Exception {
		String tipusDocument = "DNI"; //$NON-NLS-1$
		if (document != null && (document.startsWith("X") || //$NON-NLS-1$
				document.startsWith("Y") || //$NON-NLS-1$
				document.startsWith("Z"))) { //$NON-NLS-1$
			tipusDocument = "NIE"; //$NON-NLS-1$
		}
		String numSolicitud = getUserEntityDao().getNextUserIDRequest();
		return handleCheckUserIdentity(nom, llinatge1, llinatge2,
				tipusDocument, document, numSolicitud);
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

	protected String[] handleCheckUserIdentity(String nom, String llinatge1,
			String llinatge2, String tipusDocument, String document,
			String numSolicitud) throws Exception {

		String urlWS = ""; //$NON-NLS-1$

		try {

			if (document == null
					|| (document != null && "".equals(document.trim()))) //$NON-NLS-1$
				return new String[] { "0003", "" }; // Si no té nif/nie no es fa la comprovació (0003 = OK) //$NON-NLS-1$ //$NON-NLS-2$

			String resultat[] = new String[2];

			String identificador = "S0711001H"; //$NON-NLS-1$

			com.soffid.iam.api.Configuration configWS = getConfigurationService()
					.findParameterByNameAndNetworkName("webserviceSVDI", null); //$NON-NLS-1$
			// Valor proves:
			// https://intermediacionpp.redsara.es/peticionSVDI/services/VerificacionIdentidad
			if (configWS == null
					|| (configWS != null && configWS.getValue() == null)) {
				/* throw new SeyconException */System.err
						.println(Messages
								.getString("UserServiceImpl.SVDISeyconParamNotDefinied")); //$NON-NLS-1$
				return new String[] { "0003", "" }; // Eixim sense donar error: no s'ha establert el paràmetre //$NON-NLS-1$ //$NON-NLS-2$
			}
			urlWS = configWS.getValue();

			java.net.URL endpoint = new java.net.URL(urlWS);

			EngineConfiguration config = new FileProvider(
					"/"		+ this.getClass().getPackage().getName().replaceAll("[.]", "/") + "/ws_sara_client_config.wsdd"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			VerificacionIdentidadLocator locator = new VerificacionIdentidadLocator(
					config);
			VerificacionIdentidadSoapBindingStub verificacionStub = new VerificacionIdentidadSoapBindingStub(
					endpoint, locator);

			// Obtenemos el timeout (si está establecido)
			// Si no tiene valor, se deja el por defecto (60 segundos?)
			com.soffid.iam.api.Configuration configWSTimeout = getConfigurationService()
					.findParameterByNameAndNetworkName(
							"webserviceSVDI_timeout", null); //$NON-NLS-1$
			if (configWSTimeout != null && configWSTimeout.getValue() != null) {
				int timeout = Integer.parseInt(configWSTimeout.getValue());
				verificacionStub.setTimeout(timeout * 1000);// en milisegundos
			}

			// 1. Crear PETICIÓ SÍNCRONA de només una solicitud

			// 1.1 Atributs
			Atributos atributs = new Atributos();
			atributs.setIdPeticion(identificador.concat(numSolicitud));
			atributs.setNumElementos(1);
			Date hoy = new Date();
			String cadenaHoy = formatDate(hoy);
			atributs.setTimeStamp(cadenaHoy);
			atributs.setCodigoCertificado("VDISFWS01"); //$NON-NLS-1$

			// 1.2. Sol·licituds
			SolicitudTransmision solicitud = new SolicitudTransmision();
			// 1.2.1. Dades genèriques
			DatosGenericos dades = new DatosGenericos();
			Emisor emisor = new Emisor();
			Solicitante solicitante = new Solicitante();
			Titular titular = new Titular();
			Transmision transmision = new Transmision();
			emisor.setNifEmisor("S0711001H"); //$NON-NLS-1$
			emisor.setNombreEmisor("COMUNITAT AUTONOMA DE LES ILLES BALEARS"); //$NON-NLS-1$
			dades.setEmisor(emisor);
			solicitante.setIdentificadorSolicitante(identificador);
			solicitante.setNombreSolicitante("SEGURETAT ELECTRONICA UNIFICADA"); //$NON-NLS-1$
			solicitante.setFinalidad("EVITAR ERRORES DE INTRODUCCIÓN"); //$NON-NLS-1$
			solicitante.setConsentimiento(Consentimiento.Si);
			dades.setSolicitante(solicitante);
			if ("DNI".equals(tipusDocument)) //$NON-NLS-1$
				titular.setTipoDocumentacion(TipoDocumentacion.DNI);
			else if ("NIE".equals(tipusDocument)) //$NON-NLS-1$
				titular.setTipoDocumentacion(TipoDocumentacion.NIE);
			titular.setDocumentacion(document);
			titular.setNombre(nom);
			titular.setApellido1(llinatge1);
			titular.setApellido2(llinatge2);
			dades.setTitular(titular);
			transmision.setIdSolicitud(identificador.concat(numSolicitud));
			transmision.setIdTransmision(identificador);
			SimpleDateFormat formato2 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss"); //$NON-NLS-1$
			String fechaCertificatValid = "2011-01-17 12:31:42"; //$NON-NLS-1$
			Date fecha = formato2.parse(fechaCertificatValid);
			String fechaGeneracion = formatDate(fecha);
			transmision.setFechaGeneracion(fechaGeneracion);
			transmision.setCodigoCertificado("VDISFWS01"); //$NON-NLS-1$
			dades.setTransmision(transmision);
			solicitud.setDatosGenericos(dades);
			// 1.2.2. Dades específiques
			DatosEspecificos dadesEsp = new DatosEspecificos();
			SolicitanteDatos solicitantDadesEsp = new SolicitanteDatos();
			dadesEsp.setSolicitanteDatos(solicitantDadesEsp);
			Solicitud solicitudDadesEsp = new Solicitud();
			dadesEsp.setSolicitud(solicitudDadesEsp);
			solicitud.setDatosEspecificos(dadesEsp);

			Solicitudes solicitudes = new Solicitudes(
					new SolicitudTransmision[] { solicitud });
			Peticion peticio = new Peticion(atributs, solicitudes);

			// Cridar a l'operació VERIFICAR IDENTITAT
			Respuesta resposta = verificacionStub.verificarIdentidad(peticio);

			// Revisar la RESPOSTA
			es.map.www.scsp.esquemas.V2.respuesta.Atributos atributsResposta = resposta
					.getAtributos();
			if (atributsResposta.getEstado() != null) {
				String errada = atributsResposta.getEstado().getCodigoEstado();
				String literalErrada = atributsResposta.getEstado()
						.getLiteralError();
				resultat[0] = errada;
				resultat[1] = literalErrada;
				return resultat;
			} else {
				es.map.www.scsp.esquemas.V2.respuesta.TransmisionDatos transResposta[] = resposta
						.getTransmisiones();
				if (transResposta != null) {
					// transResposta és un array de solicituds, però com només
					// hi ha
					// una solicitud extraurem directament transResposta[0],
					// sense recórrer l'array
					DatosEspecificos dadesEspResposta = transResposta[0]
							.getDatosEspecificos();
					EstadoResultado estatResultat = dadesEspResposta
							.getEstadoResultado();
					String errada = estatResultat.getCodigoEstado();
					String literalErrada = estatResultat.getLiteralError();
					resultat[0] = errada;
					resultat[1] = literalErrada;
					return resultat;
				} else {
					resultat[0] = "999"; //$NON-NLS-1$
					resultat[1] = Messages
							.getString("UserServiceImpl.UnknowError"); //$NON-NLS-1$
					return resultat;
				}
			}
		} catch (SeyconException ex) { // Capturem aquesta per mostrar error de
										// configuració
			throw ex;
		} catch (Exception e) {
			throw new InternalErrorException(String.format(Messages
					.getString("UserServiceImpl.ErrorID"), e.getMessage()), e); //$NON-NLS-1$
		}
	}

	/*
	 * UsuariSEU: guarda informació de l'usuari al programa SEU
	 */
	protected ConsoleProperties handleUpdate(ConsoleProperties usuariSEU)
			throws Exception {
		UserPreferencesEntity entity = getUserPreferencesEntityDao()
				.consolePropertiesToEntity(usuariSEU);
		getUserPreferencesEntityDao().update(entity);
		return getUserPreferencesEntityDao().toConsoleProperties(entity);
	}

	/*
	 * UsuariSEU: guarda informació de l'usuari al programa SEU
	 */
	protected ConsoleProperties handleCreate(ConsoleProperties usuari)
			throws Exception {
		UserPreferencesEntity entity = getUserPreferencesEntityDao()
				.consolePropertiesToEntity(usuari);
		getUserPreferencesEntityDao().create(entity);
		return getUserPreferencesEntityDao().toConsoleProperties(entity);

	}

	/*
	 * UsuariSEU: guarda informació de l'usuari al programa SEU
	 */
	protected ConsoleProperties handleFindConsoleUserByUserName(
			String codiUsuari) throws Exception {
		UserPreferencesEntity entity = getUserPreferencesEntityDao()
				.findByUserName(codiUsuari);
		if (entity != null)
			return getUserPreferencesEntityDao().toConsoleProperties(entity);
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

	protected String[] handleSeeUserIdentity(String document) throws Exception {
		String tipusDocument = "DNI"; //$NON-NLS-1$
		if (document != null && (document.startsWith("X") || //$NON-NLS-1$
				document.startsWith("Y") || //$NON-NLS-1$
				document.startsWith("Z"))) { //$NON-NLS-1$
			tipusDocument = "NIE"; //$NON-NLS-1$
		}
		String numSolicitud = getUserEntityDao().getNextUserIDRequest();
		return handleSeeUserIdentity(tipusDocument, document, numSolicitud);
	}

	protected String[] handleSeeUserIdentity(String tipusDocument,
			String document, String numSolicitud) throws Exception {

		String urlWS = ""; //$NON-NLS-1$

		try {
			String resultat[] = new String[6];

			if (document == null
					|| (document != null && "".equals(document.trim()))) { //$NON-NLS-1$
				// Si no té nif/nie no es fa la comprovació (0003 = OK)
				return new String[] { "0003", "", "", "", "" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			}

			String identificador = "S0711001H"; //$NON-NLS-1$

			com.soffid.iam.api.Configuration configWS = getConfiguracioServeiComprovacioIndentitatUsuari();
			// Valor proves:
			// https://intermediacionpp.redsara.es/peticionSVDI/services/VerificacionIdentidad
			if (configWS == null
					|| (configWS != null && configWS.getValue() == null)) {
				/* throw new SeyconException */System.err
						.println(Messages
								.getString("UserServiceImpl.SCDISeyconParamNotDefinied")); //$NON-NLS-1$
				return new String[] {
						"9999", Messages.getString("UserServiceImpl.NoEstablishedWebServiceSCDIParam"), "", "", "" }; // no s'ha establert el paràmetre //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			}
			urlWS = configWS.getValue();

			java.net.URL endpoint = new java.net.URL(urlWS);

			// EngineConfiguration config = new FileProvider(new
			// ByteArrayInputStream(WS_SECURITY_INFO.getBytes()));
			// EngineConfiguration config = new
			// FileProvider("client_config.wsdd");
			EngineConfiguration config = new FileProvider("/" //$NON-NLS-1$
					+ this.getClass().getPackage().getName()
							.replaceAll("[.]", "/") //$NON-NLS-1$ //$NON-NLS-2$
					+ "/ws_sara_client_config.wsdd"); //$NON-NLS-1$
			ConsultaIdentidadLocator locator = new ConsultaIdentidadLocator(
					config);
			ConsultaIdentidadSoapBindingStub consultaStub = new ConsultaIdentidadSoapBindingStub(
					endpoint, locator);

			// 1. Crear PETICIÓ SÍNCRONA de només una solicitud

			// 1.1 Atributs
			es.map2.www.scsp.esquemas.V2.peticion.Atributos atributs = new es.map2.www.scsp.esquemas.V2.peticion.Atributos();
			atributs.setIdPeticion(identificador.concat(numSolicitud));
			atributs.setNumElementos(1);
			Date hoy = new Date();
			String cadenaHoy = formatDate(hoy);
			atributs.setTimeStamp(cadenaHoy);
			atributs.setCodigoCertificado("CDISFWS01"); //$NON-NLS-1$

			// 1.2. Sol·licituds
			es.map2.www.scsp.esquemas.V2.peticion.SolicitudTransmision solicitud = new es.map2.www.scsp.esquemas.V2.peticion.SolicitudTransmision();
			// 1.2.1. Dades genèriques
			es.map2.www.scsp.esquemas.V2.peticion.DatosGenericos dades = new es.map2.www.scsp.esquemas.V2.peticion.DatosGenericos();
			es.map2.www.scsp.esquemas.V2.peticion.Emisor emisor = new es.map2.www.scsp.esquemas.V2.peticion.Emisor();
			es.map2.www.scsp.esquemas.V2.peticion.Solicitante solicitante = new es.map2.www.scsp.esquemas.V2.peticion.Solicitante();
			es.map2.www.scsp.esquemas.V2.peticion.Titular titular = new es.map2.www.scsp.esquemas.V2.peticion.Titular();
			es.map2.www.scsp.esquemas.V2.peticion.Transmision transmision = new es.map2.www.scsp.esquemas.V2.peticion.Transmision();
			emisor.setNifEmisor("S0711001H"); //$NON-NLS-1$
			emisor.setNombreEmisor("COMUNITAT AUTONOMA DE LES ILLES BALEARS"); //$NON-NLS-1$
			dades.setEmisor(emisor);
			solicitante.setIdentificadorSolicitante(identificador);
			solicitante.setNombreSolicitante("SEGURETAT ELECTRONICA UNIFICADA"); //$NON-NLS-1$
			solicitante.setFinalidad("EVITAR ERRORES DE INTRODUCCIÓN"); //$NON-NLS-1$
			solicitante
					.setConsentimiento(es.map2.www.scsp.esquemas.V2.peticion.Consentimiento.Si);
			dades.setSolicitante(solicitante);
			if ("DNI".equals(tipusDocument)) //$NON-NLS-1$
				titular.setTipoDocumentacion(es.map2.www.scsp.esquemas.V2.peticion.TipoDocumentacion.DNI);
			else if ("NIE".equals(tipusDocument)) //$NON-NLS-1$
				titular.setTipoDocumentacion(es.map2.www.scsp.esquemas.V2.peticion.TipoDocumentacion.NIE);
			titular.setDocumentacion(document);
			dades.setTitular(titular);
			transmision.setIdSolicitud(identificador.concat(numSolicitud));
			transmision.setIdTransmision(identificador);
			SimpleDateFormat formato2 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss"); //$NON-NLS-1$
			String fechaCertificatValid = "2011-01-17 12:31:42"; //$NON-NLS-1$
			Date fecha = formato2.parse(fechaCertificatValid);
			String fechaGeneracion = formatDate(fecha);
			transmision.setFechaGeneracion(fechaGeneracion);
			transmision.setCodigoCertificado("CDISFWS01"); //$NON-NLS-1$
			dades.setTransmision(transmision);
			solicitud.setDatosGenericos(dades);
			// 1.2.2. Dades específiques
			es.map2.www.scsp.esquemas.datosespecificos.DatosEspecificos dadesEsp = new es.map2.www.scsp.esquemas.datosespecificos.DatosEspecificos();
			es.map2.www.scsp.esquemas.datosespecificos.SolicitanteDatos solicitantDadesEsp = new es.map2.www.scsp.esquemas.datosespecificos.SolicitanteDatos();
			dadesEsp.setSolicitanteDatos(solicitantDadesEsp);
			es.map2.www.scsp.esquemas.datosespecificos.Solicitud solicitudDadesEsp = new es.map2.www.scsp.esquemas.datosespecificos.Solicitud();
			solicitudDadesEsp.setNumSoporte(""); //$NON-NLS-1$
			dadesEsp.setSolicitud(solicitudDadesEsp);
			solicitud.setDatosEspecificos(dadesEsp);
			es.map2.www.scsp.esquemas.V2.peticion.Solicitudes solicitudes = new es.map2.www.scsp.esquemas.V2.peticion.Solicitudes(
					new es.map2.www.scsp.esquemas.V2.peticion.SolicitudTransmision[] { solicitud });
			es.map2.www.scsp.esquemas.V2.peticion.Peticion peticio = new es.map2.www.scsp.esquemas.V2.peticion.Peticion(
					atributs, solicitudes);

			// Cridar a l'operació VERIFICAR IDENTITAT
			es.map2.www.scsp.esquemas.V2.respuesta.Respuesta resposta = consultaStub
					.consultarIdentidad(peticio);

			// Revisar la RESPOSTA
			es.map2.www.scsp.esquemas.V2.respuesta.Atributos atributsResposta = resposta
					.getAtributos();
			if (atributsResposta.getEstado() != null) {
				String errada = atributsResposta.getEstado().getCodigoEstado();
				String literalErrada = atributsResposta.getEstado()
						.getLiteralError();
				resultat[0] = errada;
				resultat[1] = literalErrada;
				return resultat;
			} else {
				es.map2.www.scsp.esquemas.V2.respuesta.TransmisionDatos transResposta[] = resposta
						.getTransmisiones();
				if (transResposta != null) {
					// transResposta és un array de solicituds, però com només
					// hi ha
					// una solicitud extraurem directament transResposta[0],
					// sense recórrer l'array
					/*
					 * for (int x=0; x<transResposta.length; x++) {
					 * DatosEspecificos dadesEspResposta =
					 * transResposta[x].getDatosEspecificos(); EstadoResultado
					 * estatResultat = dadesEspResposta.getEstadoResultado();
					 * String errada = estatResultat.getCodigoEstado(); String
					 * literalErrada = estatResultat.getLiteralError();
					 * resultat[0] = errada; resultat[1] = literalErrada; return
					 * resultat; }
					 */
					es.map2.www.scsp.esquemas.datosespecificos.DatosEspecificos dadesEspResposta = transResposta[0]
							.getDatosEspecificos();
					es.map2.www.scsp.esquemas.datosespecificos.EstadoResultado estatResultat = dadesEspResposta
							.getEstadoResultado();
					String errada = estatResultat.getCodigoEstado();
					String literalErrada = estatResultat.getLiteralError();
					resultat[0] = errada;
					resultat[1] = literalErrada;
					if ("0003".equals(errada)) { //$NON-NLS-1$
						String nomTitular = dadesEspResposta.getDatosTitular()
								.getNombre();
						String lli1Titular = dadesEspResposta.getDatosTitular()
								.getApellido1();
						String lli2Titular = dadesEspResposta.getDatosTitular()
								.getApellido2();
						resultat[2] = nomTitular;
						resultat[3] = lli1Titular;
						resultat[4] = lli2Titular;
						resultat[5] = transResposta[0].getDatosGenericos()
								.getTitular().getNombreCompleto();
					}
					return resultat;
				} else {
					resultat[0] = "999"; //$NON-NLS-1$
					resultat[1] = Messages
							.getString("UserServiceImpl.UnknowError"); //$NON-NLS-1$
					return resultat;
				}
			}
		} catch (SeyconException ex) { // Capturem aquesta per mostrar error de
										// configuració
			throw ex;
		} catch (Exception e) {
			throw new InternalErrorException(String.format(Messages
					.getString("UserServiceImpl.ErrorID"), e.getMessage()), e); //$NON-NLS-1$
		}
	}

	protected User handleCreateHostUser(String nom, String descripcio)
			throws Exception {
		if (true)
			throw new RuntimeException(
					Messages.getString("UserServiceImpl.NotImplementedMessage")); //$NON-NLS-1$

		/* Es crea l'usuari */
		User usuariMaquina = new User();

		// Li assignem el següent codi de màquina lliure
		usuariMaquina.setUserName(getUserEntityDao().getNextHostUserName());

		// dades bàsiques:
		usuariMaquina.setFirstName(nom);
		usuariMaquina.setLastName(descripcio);
		usuariMaquina.setUserType("H"); //Tipus d'usuari màquina //$NON-NLS-1$

		/* se almacena la fecha de creación */
		usuariMaquina.setCreatedDate(GregorianCalendar.getInstance());
		/* se almacena el usuario que lo crea */
		usuariMaquina.setCreatedByUser(Security.getCurrentAccount());

		// Li assignem certs paràmetres...
		usuariMaquina.setActive(new Boolean(true));
		usuariMaquina.setMultiSession(new Boolean(false));
		// No en té efecte i ja no existeix al VO
		// usuariMaquina.setContrasenyaCaducada(new Boolean(true));

		// Establim grup primari i servidors de correu..
		usuariMaquina.setPrimaryGroup("nul"); //TODO: li hem d'assingar un altre grup primari? //$NON-NLS-1$

		usuariMaquina.setMailServer("nul"); //$NON-NLS-1$
		usuariMaquina.setHomeServer("nul"); //$NON-NLS-1$
		usuariMaquina.setProfileServer("nul"); //$NON-NLS-1$

		UserEntity usuariEntity = getUserEntityDao()
				.userToEntity(usuariMaquina);

		getUserEntityDao().create(usuariEntity);

		getRuleEvaluatorService().applyRules(usuariEntity);

		return getUserEntityDao().toUser(usuariEntity);
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

		// Marquem que l'usuari ha estat modificat
		usuariEntity
				.setLastUserModification(getPrincipal() != null ? getPrincipal()
						.getName() : "SEYCON"); //$NON-NLS-1$
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
						processos.add(pi);
					}
				} catch (Exception e) {
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

		// Recuperar rols explicits de l'usuari
		List<RoleAccountEntity> originalGrants = getRoleAccountEntityDao()
				.findByUserName(entity.getUserName());
		List<RoleGrant> rols = getRoleAccountEntityDao().toRoleGrantList(
				originalGrants);

		// Recuprear rols implicits dels rols
		if (!explicit) {
			for (RoleAccountEntity rau : originalGrants) {
				populateRolRoles(rau.getRole(), rols);
			}
			populateGroupRoles(entity.getPrimaryGroup(), rols);
			for (Iterator<UserGroupEntity> it = entity.getSecondaryGroups()
					.iterator(); it.hasNext();) {
				UserGroupEntity ug = it.next();
				populateGroupRoles(ug.getGroup(), rols);
			}
		}

		return rols;

	}

	private void populateGroupRoles(GroupEntity grup, List<RoleGrant> rols) {
		RoleGroupEntityDao dao = getRoleGroupEntityDao();
		for (Iterator<RoleGroupEntity> it = grup.getAllowedRolesToGroup()
				.iterator(); it.hasNext();) {
			RoleGroupEntity rg = it.next();
			rols.add(dao.toRoleGrant(rg));
			populateRolRoles(rg.getAssignedRole(), rols);
		}
	}

	private void populateRolRoles(RoleEntity rol, List<RoleGrant> rols) {
		RoleDependencyEntityDao dao = getRoleDependencyEntityDao();
		for (Iterator<RoleDependencyEntity> it = rol.getContainedRole()
				.iterator(); it.hasNext();) {
			RoleDependencyEntity rarEntity = it.next();
			RoleGrant rg = dao.toRoleGrant(rarEntity);
			rols.add(rg);
			populateRolRoles(rarEntity.getContained(), rols);
		}
	}

	@Override
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
		Principal p = Security.getPrincipal();
		if (p == null)
			return null;

		String dispatcherName = getInternalPasswordService()
				.getDefaultDispatcher();
		Account acc = getAccountService().findAccount(p.getName(),
				dispatcherName);
		if (acc == null)
			return null;
		else if (acc instanceof UserAccount)
			return findUserByUserName(((UserAccount) acc).getUser());
		else
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
			List<Parameter> params) {
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
			limitResults = Integer.parseInt(System
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
				"dadaUsuari.value", new String[] { "inner join usuari.userData as dadaUsuari", "inner join dadaUsuari.dataType as tipusDada with tipusDada.name=\'NIF\'" }, joins, queries, params); //$NON-NLS-1$
		addString(
				criteria.getPhoneNumber(),
				"dadaUsuari2.valorDada", new String[] { "inner join usuari.userData as dadaUsuari2", "inner join dadaUsuari2.dataType as tipusDada2 with tipusDada2.name=\'PHONE\'" }, joins, queries, params); //$NON-NLS-1$

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
					new String[] { "inner join usuari.userData as dadaUsuari2", //$NON-NLS-1$
							"inner join dadaUsuari2.dataType as tipusDada2" }, joins, queries, params); //$NON-NLS-1$

			addString(criteria.getAttributeValue(),
					"dadaUsuari2.value", new String[] { //$NON-NLS-1$
					"inner join usuari.userData as dadaUsuari2" //$NON-NLS-1$
					}, joins, queries, params);
		}

		StringBuffer sb = new StringBuffer(
				"select usuari from com.soffid.iam.model.UserEntity as usuari"); //$NON-NLS-1$
		for (String join : joins) {
			sb.append(' ').append(join);
		}
		boolean first = true;
		for (String query : queries) {
			if (first)
				sb.append(" where "); //$NON-NLS-1$
			else
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
	protected void handleChangePassword(String codiUsuari,
			String codiDominiContrasenyes, Password newPassword)
			throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);
		if (usuari != null && "S".equals(usuari.getActive())) { //$NON-NLS-1$
			if (getAuthorizationService().hasPermission(
					Security.AUTO_USER_SET_PASSWORD, usuari)) {
				PasswordDomainEntity dominiContrasenyes = getPasswordDomainEntityDao()
						.findByName(codiDominiContrasenyes);
				PolicyCheckResult validation = getInternalPasswordService()
						.checkPolicy(usuari, dominiContrasenyes, newPassword);
				if (!validation.isValid())
					throw new BadPasswordException(validation.getReason());
				getInternalPasswordService().storeAndForwardPassword(usuari,
						dominiContrasenyes, newPassword, true);
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
	protected Collection<User> handleFindUserByJsonQuery(String query)
			throws Exception {
		ClassConfig config = Configuration
				.getClassConfig(com.soffid.iam.api.User.class);

		AbstractExpression expr = ExpressionParser.parse(query);
		HQLQuery hql = expr.generateHSQLString(User.class);

		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size()];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		LinkedList<User> result = new LinkedList<User>();
		for (UserEntity ue : getUserEntityDao().query(hql.toString(),
				paramArray)) {
			User u = getUserEntityDao().toUser(ue);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(u)) {
				if (getAuthorizationService().hasPermission(
						Security.AUTO_USER_QUERY, ue)) {
					result.add(u);
				}
			}
		}

		return result;
	}
}
