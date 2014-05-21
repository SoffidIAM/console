package es.caib.seycon.ng.servei;

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
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.caib.bpm.servei.BpmEngine;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.DadesDocent;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Impressora;
import es.caib.seycon.ng.comu.LlistaCorreu;
import es.caib.seycon.ng.comu.LlistaCorreuUsuari;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.ProcesWF;
import es.caib.seycon.ng.comu.Renovacio;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.Sessio;
import es.caib.seycon.ng.comu.SeyconServerInfo;
import es.caib.seycon.ng.comu.TargetaExtranet;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariAlumne;
import es.caib.seycon.ng.comu.UsuariCriteria;
import es.caib.seycon.ng.comu.UsuariImpressora;
import es.caib.seycon.ng.comu.UsuariSEU;
import es.caib.seycon.ng.comu.UsuariWFProcess;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.DadaUsuariEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.DominiContrasenyaEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.GrupEntityDao;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.RenovacioEntity;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.RolAssociacioRolEntity;
import es.caib.seycon.ng.model.RolAssociacioRolEntityDao;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.RolsGrupEntity;
import es.caib.seycon.ng.model.RolsGrupEntityDao;
import es.caib.seycon.ng.model.ScTarget;
import es.caib.seycon.ng.model.ServerEntity;
import es.caib.seycon.ng.model.ServerEntityDao;
import es.caib.seycon.ng.model.SessioEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.TipusUsuariEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariEntityDao;
import es.caib.seycon.ng.model.UsuariGrupEntity;
import es.caib.seycon.ng.model.UsuariImpressoraEntity;
import es.caib.seycon.ng.model.UsuariSEUEntity;
import es.caib.seycon.ng.model.UsuariWFProcessEntity;
import es.caib.seycon.ng.model.criteria.CriteriaSearchConfiguration;
import es.caib.seycon.ng.remote.RemoteServiceLocator;
import es.caib.seycon.ng.sync.servei.SyncStatusService;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.DateUtils;
import es.caib.seycon.ng.utils.LimitDates;
import es.caib.seycon.ng.utils.ProcesWFUsuari;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.ng.utils.TipusDomini;
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
//- WS verificacion de usuario red sara

public class UsuariServiceImpl extends
		es.caib.seycon.ng.servei.UsuariServiceBase {
	private static final String E_MAIL_CONTACTE = "EMAIL"; //$NON-NLS-1$
	public static final String NIF = "NIF"; //$NON-NLS-1$
	public static final String TELEFON = "PHONE"; //$NON-NLS-1$
	private static final String DATA_BAIXA = "DATA_BAIXA"; //$NON-NLS-1$
	private static final String CODI_USUARI_IBSALUT = "CODI_USUARI_IBSALUT";// cambio //$NON-NLS-1$
	private final String DADA_ADDICIONAL_CODI_XESTIB = "CODI_XESTIB"; //$NON-NLS-1$
	private final String DADA_ADDICIONAL_CODI_XESTIB_GRUPALUMNE = "CODI_XESTIB_GRUPALUMNE"; //$NON-NLS-1$


	protected Usuari handleSetServidorsToUsuari(java.lang.String codiUsuari,
			String servidorPerfil, String servidorCorreu, String servidorHome)
			throws java.lang.Exception {
		/*
		 * Se asignan los servidores
		 */
		Usuari usuari = findUsuariByCodiUsuari(codiUsuari);
		if (usuari != null) {
			usuari.setServidorCorreu(servidorCorreu);
			usuari.setServidorPerfil(servidorPerfil);
			usuari.setServidorHome(servidorHome);
			UsuariEntity usuariEntity = getUsuariEntityDao().usuariToEntity(
					usuari);
			/*
			 * Se actualiza el usuario
			 */
			getUsuariEntityDao().update(usuariEntity);
			return usuari;
		}
		return null;
	}

	protected Usuari handleBaixaUsuari(java.lang.String codiUsuari)
			throws java.lang.Exception {
		// autoritzacio user:delete
		// Cridat des de delete(usuari)
		
		UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(codiUsuari);
		
		// Esborrem les associacions amb llistes de correu
		// S'esborren les llistes de correu òrfenes i les seves associacions
		Usuari usu = getUsuariEntityDao().toUsuari(usuariEntity);
		usu.setAliesCorreu("");//Per esborrarles //$NON-NLS-1$
		arreglaAlias(usu);
		

		/*
		 * Se eliminan los roles de los usuarios
		 */
		getRolAccountEntityDao().remove(getRolAccountEntityDao().findByCodiUsuari(usuariEntity.getCodi()));

		/*
		 * Se eliminan las asociaciones a grupos
		 */
		Collection grups = usuariEntity.getGrupsSecundaris();
		getUsuariGrupEntityDao().remove(grups);

		/*
		 * Se elimina la asociación con el grupo primario y se le pone como
		 * grupo primario "portal"
		 */
		GrupEntity grupEntity = getGrupEntityDao().findByCodi("portal"); //$NON-NLS-1$
		usuariEntity.setGrupPrimari(grupEntity);
		
		/*
		 * Se elimina el dato adicional CODI_USUARI_IBSALUT
		 */
		DadaUsuariEntity dadaCodiIBSALUT = getDadaUsuariEntityDao()
				.findDadaByCodiTipusDada(codiUsuari, "CODI_USUARI_IBSALUT"); //$NON-NLS-1$
		if (dadaCodiIBSALUT != null) {
			getDadaUsuariEntityDao().remove(dadaCodiIBSALUT);
		}

		/*
		 * Se eliminan las asociaciones d'impressora
		 */
		Collection impressores = usuariEntity.getImpressores();
		this.getUsuariImpressoraEntityDao().remove(impressores);

		/*
		 * Se eliminan las asociaciones de listas de correo
		 */
		Collection llistesDeCorreu = getLlistesDeCorreuService()
				.findLlistaCorreuUsuariByCodiUsuari(usuariEntity.getCodi());
		for (Iterator it = llistesDeCorreu.iterator(); it.hasNext();) {
			LlistaCorreuUsuari llistaCorreuUsuari = (LlistaCorreuUsuari) it
					.next();
			getLlistesDeCorreuService().delete(llistaCorreuUsuari);
		}
		
		/*
		 * Se eliminan las asociaciones con redes
		 */
		Collection xarxes = usuariEntity.getXarxesAC();
		this.getXarxaACEntityDao().remove(xarxes);

		/*
		 * Se eliminan las asociaciones con servidores
		 */
		MaquinaEntity maquinaNul = getMaquinaEntityDao().findByNom("nul"); //$NON-NLS-1$
		usuariEntity.setServidorOfimatic(maquinaNul);
		usuariEntity.setServidorCorreu(maquinaNul);
		usuariEntity.setServidorPerfil(maquinaNul);

		usuariEntity.setUsuariDarreraModificacio(Security.getCurrentAccount());
		usuariEntity.setDataDarreraModificacio(GregorianCalendar.getInstance()
				.getTime());
		usuariEntity.setNomCurt(null);
		usuariEntity.setDominiCorreu(null);

		/*
		 * Se eliminan las tarjetas
		 */
		/*Collection targetes = usuariEntity.getTargetesCPD();
		if (targetes != null) {
			getTarjaCPDEntityDao().remove(targetes);
		}*/

		/*
		 * Se eliminan las referencias desde aplicaciones como
		 * persona de contacto
		 */
		Collection aplicacionsSocResponsable = usuariEntity.getAplicacioSocPersonaResponsable();
		if (aplicacionsSocResponsable != null) {
			for (Iterator it = aplicacionsSocResponsable.iterator(); it.hasNext(); ) {
				AplicacioEntity app = (AplicacioEntity) it.next();
				app.setPersonaContacte(null);
				getAplicacioEntityDao().update(app);
			}	
		}

		/*
		 * Se asigna usuario de tipo externo
		 */
		//TODO: En principi ha d'existir el tipus d'usuari (E)xtern
		TipusUsuariEntity tipusE = getTipusUsuariEntityDao().findByCodi("E"); //$NON-NLS-1$
		usuariEntity.setTipusUsuari(tipusE);

		/*
		 * Se pone en activo
		 */
		usuariEntity.setActiu("S"); //$NON-NLS-1$

		/*
		 * Se actualiza el usuario
		 */
		getUsuariEntityDao().update(usuariEntity);
		
		return getUsuariEntityDao().toUsuari(usuariEntity);
	}

	protected Collection<Impressora> handleFindImpressoresByCodiUsuari(
			java.lang.String codiUsuari) throws java.lang.Exception {
		java.util.List<es.caib.seycon.ng.model.UsuariImpressoraEntity> impressoresUsuari = getUsuariImpressoraEntityDao().findUsuariImpressoresByCodiUsuari(
				codiUsuari);
		Collection impressores = new Vector();
		if (impressoresUsuari != null) {
			for (Iterator<UsuariImpressoraEntity> it  = impressoresUsuari.iterator(); it.hasNext(); ) {
				UsuariImpressoraEntity uimp = it.next();
				impressores.add(uimp.getImpressora());
			}
			return getImpressoraEntityDao().toImpressoraList(impressores);
		}
		return impressores;
	}

	protected es.caib.seycon.ng.comu.Usuari handleFindUsuariByNIFUsuari(
			java.lang.String nif) throws java.lang.Exception {
		UsuariEntity usuariEntity = getUsuariEntityDao().findByNIF(nif);
		if (usuariEntity != null) {
			return getUsuariEntityDao().toUsuari(usuariEntity);
		}
		return null;
	}

	protected Collection<Usuari> handleFindUsuarisByCodiUsuari(String codiUsuari)
			throws java.lang.Exception {
		if (codiUsuari == null || codiUsuari.trim().compareTo("") == 0) { //$NON-NLS-1$
			return new LinkedList();
		}
		return this.findUsuarisByDadesBasiques(codiUsuari, null, null, null,
				null);

	}

	protected Usuari handleFindUsuariByCodiUsuari(String codiUsuari)
			throws java.lang.Exception {
		if (codiUsuari == null || codiUsuari.trim().compareTo("") == 0) { //$NON-NLS-1$
			return null;
		}
		UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(codiUsuari);
		if (usuariEntity != null) {
			Usuari usuari = getUsuariEntityDao().toUsuari(usuariEntity);
			return usuari;
		}
		return null;
	}

	protected String handleGeneraCodiUsuari() throws java.lang.Exception {
		String codiUsuari = getUsuariEntityDao().generaCodiUsuari();
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
	 * @param nifonie
	 * @return
	 */
	private boolean esNIFoNIE (String nifonie) {
		boolean esNIF = false;
		boolean esNIE = false;
		if (nifonie != null && !"".equals(nifonie.trim()))  //$NON-NLS-1$
        {
            nifonie = nifonie.toUpperCase().trim();
            int longitud = nifonie.length();
            String primerCaracter = nifonie.substring(0,1);
            String ultimCaracter = nifonie.substring(longitud-1);

			// Primer, averiguar si és un NIF (té 9 caracters i l'últim és una lletra) 
            // o és un NIE (comença per X, Y o Z i l'últim és una lletra)
            if (primerCaracter.equals("X") || primerCaracter.equals("Y") || primerCaracter.equals("Z")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            {
                if (!checkIfNumber(ultimCaracter)) {
                    esNIE = true;
                } else {
                    esNIE = false;
                }
            } else {
                if (longitud == 9 && checkIfNumber(primerCaracter) && !checkIfNumber(ultimCaracter)) {
                    esNIF = true;
                } else {     
                    esNIF = false;
                }                
            }
        }
		return esNIF || esNIE;
	}

	protected es.caib.seycon.ng.comu.Usuari handleCreate(
			es.caib.seycon.ng.comu.Usuari usuari) throws java.lang.Exception {
		
		// Comprobamos autorización del usuario
		if (!AutoritzacionsUsuari.canCreateUser(usuari, getGrupEntityDao())) {
			throw new SeyconAccessLocalException("UsuariService", //$NON-NLS-1$
					"create (Usuari)", "user:create, user:create/*", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("UsuariServiceImpl.NoAuthorizedToUpdate")); //$NON-NLS-1$
		}
		
		// Comprovem que s'hagi especificat el tipus d'usuari de domini
		if (usuari.getTipusUsuari() == null) {
			throw new SeyconException (Messages.getString("UsuariServiceImpl.UserTypeNotEspecified")); //$NON-NLS-1$
		}
		
		// Comprobamos que no exista ya el usuario a crear (puede haber ya varios)
		String NIF = usuari.getNIF();
		if (NIF!=null && !"".equals(NIF.trim())) { //$NON-NLS-1$
			NIF = NIF.trim();
			Parameter params[] = new Parameter[]{new Parameter("nif", NIF)}; //$NON-NLS-1$
			Collection usuarisMateixNIF = getUsuariEntityDao().query("select usuari from es.caib.seycon.ng.model.UsuariEntity usuari, es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari where usuari = dadaUsuari.usuari and dadaUsuari.tipusDada.codi = 'NIF' and dadaUsuari.valorDada = :nif", params); //$NON-NLS-1$
			if (usuarisMateixNIF!=null && usuarisMateixNIF.size()!=0) {
				String codiUsuaris=""; //$NON-NLS-1$
				for (Iterator it = usuarisMateixNIF.iterator(); it.hasNext();) {
					codiUsuaris+= "'"+((UsuariEntity) it.next()).getCodi()+"', "; //$NON-NLS-1$ //$NON-NLS-2$
				}
				codiUsuaris = codiUsuaris.substring(0,codiUsuaris.length()-2);
				throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.ExistsUser"), //$NON-NLS-1$
						codiUsuaris)); 
			}
		}
		
		UsuariEntity usersSameCode = getUsuariEntityDao().findByCodi(usuari.getCodi());
		if(usersSameCode != null)
			throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.CodeUserExists"),  //$NON-NLS-1$
							usuari.getCodi())); 

		// VERIFICACIÓN DE IDENTIDAD: comprobación WS de red SARA
		String [] resultat = null;
		try {
			// Permitim que es lleve el NIF d'un usuari
			if (esNIFoNIE(usuari.getNIF()) /*usuari.getNIF()!=null &&  !"".equals(usuari.getNIF())*/) {
				// Verificamos con el servicio web de la red sara que los datos del usuario sean correctos:
				if (getConfiguracioServeiVerificacioIndentitatUsuari()!=null) { //només si el servei és actiu
					resultat  = verificarIdentitatUsuari(usuari.getNom(), usuari.getPrimerLlinatge(), usuari.getSegonLlinatge(), usuari.getNIF());
				}
			}
		} catch (Throwable th) {
			// Capturamos errores de falta de parámetro:
			if (th.getCause() instanceof SeyconException) throw new Exception(th.getCause()); 
			
			// Evitamos problemas que pueden surgir con la llamada a los servicios web
			resultat = null;
			
			if (resultat == null) {
				throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.VerificationServiceError"), th.getMessage())); //$NON-NLS-1$
			}
		}
		
		if (resultat !=null && !"0003".equals(resultat[0])) //$NON-NLS-1$
			if (resultat != null && !"0003".equals(resultat[0])) //$NON-NLS-1$
				throw new SeyconException(String.format(
						Messages.getString("UsuariServiceImpl.VerificationServiceCodError"), resultat[1], resultat[0])); //$NON-NLS-1$
		
		/* Se crea el usuario */
		UsuariEntity usuariEntity = getUsuariEntityDao().usuariToEntity(usuari);

		/* se almacena la fecha de creación */
		Calendar calendar = GregorianCalendar.getInstance();
		Date now = calendar.getTime();
		usuariEntity.setDataCreacio(now);
		/* se almacena el usuaio que lo crea */
		String codiUsuariCreacio = Security.getCurrentAccount();
		// UsuariEntity usuariCreacio =
		// this.getUsuariEntityDao().findByCodi(codiUsuariCreacio);

		usuariEntity.setUsuariCreacio(codiUsuariCreacio);

		getUsuariEntityDao().create(usuariEntity);

		/* Una vez creado, se almacena el NIF */
		DadaUsuari dadaUsuari = new DadaUsuari();
		dadaUsuari.setCodiDada("NIF"); //$NON-NLS-1$
		dadaUsuari.setCodiUsuari(usuari.getCodi());
		dadaUsuari.setValorDada(usuari.getNIF());
		dadaUsuari.setBlobDataValue(null);
		DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao()
				.dadaUsuariToEntity(dadaUsuari);
		getDadaUsuariEntityDao().create(dadaUsuariEntity);

		/* El teléfon es guarda quan ja s'ha creat l'usuari */
		if (usuari.getTelefon() != null)
		{
        	DadaUsuari telf = new DadaUsuari();
        	telf.setCodiDada("PHONE"); //$NON-NLS-1$
        	telf.setCodiUsuari(usuari.getCodi());
        	telf.setValorDada(usuari.getTelefon());
        	DadaUsuariEntity telfEntity = getDadaUsuariEntityDao().dadaUsuariToEntity(telf);
        	getDadaUsuariEntityDao().create(telfEntity);
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
		getAccountService().generateUserAccounts(usuari.getCodi());
		
		/*IAM-318: Propagar canvis de grup primari*/
		GrupService service = getGrupService();
		service.propagateRolsChangesToDispatcher(usuari.getCodiGrupPrimari());
		
		getRuleEvaluatorService().applyRules(usuariEntity);
				
		return getUsuariEntityDao().toUsuari(usuariEntity);
	}

	private int getPasswordMaxAge(String userType) throws NamingException,
			javax.ejb.CreateException, InternalErrorException {
		String pme;
		ConfiguracioService configuracioService = getConfiguracioService();
		pme = configuracioService.findParametreByCodiAndCodiXarxa(
				"seycon.password.age." + userType, null).getValor(); //$NON-NLS-1$
		if (pme == null)
			pme = "45"; //$NON-NLS-1$
		return Integer.decode(pme).intValue();
	}

	private int getAccountMaxAge(String userType) throws NamingException,
			javax.ejb.CreateException, InternalErrorException {
		String pme;
		ConfiguracioService configuracioService = getConfiguracioService();
		pme = configuracioService.findParametreByCodiAndCodiXarxa(
				"seycon.account.age." + userType, null).getValor(); //$NON-NLS-1$
		if (pme == null) {
			return 4 * 365 + 1; // 3 años de 365 + 1 año de 366
		} else {
			return Integer.decode(pme).intValue();
		}
	}

	private boolean hasCertificacioDeValidesaDeDades(
			es.caib.signatura.api.Signature signatura, Node domNode) throws InternalErrorException {
		ParsedCertificate parsedCertificate = signatura.getParsedCertificate();
		String nifIniciador = parsedCertificate.getNif();
		Usuari iniciador = findUsuariByNIFUsuari(nifIniciador);
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
			es.caib.signatura.api.Signature signatura, Node domNode) throws InternalErrorException{
		ParsedCertificate parsedCertificate = signatura.getParsedCertificate();
		String nifIniciador = parsedCertificate.getNif();
		Usuari iniciador = findUsuariByNIFUsuari(nifIniciador);
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

	private boolean hasNewUserCredentials(String unitatOrganitzativa)
			throws Exception {
		return AutoritzacionsUsuari.canCreateUsersOnGroup(unitatOrganitzativa);
	}

	// Cridat des del workflow per donar d'alta usuaris
	public Usuari handleAltaUsuari(byte[] peticio,
			es.caib.signatura.api.Signature signatura) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// Comprobación de la firma del documento XML.
			InputStream in = new ByteArrayInputStream(peticio);
			if (!signatura.verify(in)) {
				throw new SeyconException(Messages.getString("UsuariServiceImpl.SignNotVerified")); //$NON-NLS-1$
			} else {
				throw new UnsupportedOperationException();
			}
		} catch (Exception e) {
			System.out.println(Messages.getString("UsuariServiceImpl.Error") + e); //$NON-NLS-1$
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
					+ Messages.getString("UsuariServiceImpl.FirstSurname"); //$NON-NLS-1$
		}
		/*
		 * if (this.getValue("SegonLlinatge", document) == null) { resultado +=
		 * (resultado.length() == 0 ? "" : ",") + " segón llinatge"; }
		 */
		if (this.getValue("UnitatOrganitzativa", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") //$NON-NLS-1$ //$NON-NLS-2$
					+ Messages.getString("UsuariServiceImpl.OrgUnity"); //$NON-NLS-1$
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
					+ Messages.getString("UsuariServiceImpl.Certification"); //$NON-NLS-1$
		}
		if (this.getValue("CertificacioDeCondicions", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") //$NON-NLS-1$ //$NON-NLS-2$
					+ Messages.getString("UsuariServiceImpl.CondCertification"); //$NON-NLS-1$
		}
		if (this.getValue("TipusUsuari", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") //$NON-NLS-1$ //$NON-NLS-2$
					+ Messages.getString("UsuariServiceImpl.UserType"); //$NON-NLS-1$
		}
		if (this.getValue("Telefon", document) == null) { //$NON-NLS-1$
			resultado += (resultado.length() == 0 ? "" : ",") + Messages.getString("UsuariServiceImpl.UserPhone"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		UsuariEntity usuariEntity = getUsuariEntityDao().findById(idUsuari);
		Collection renovacioEntities = getRenovacioEntityDao().findByUsuari(
				usuariEntity);
		Iterator i = renovacioEntities.iterator();
		Renovacio renovacio = new Renovacio();
		while (i.hasNext()) {
			RenovacioEntity renovacioEnity = (RenovacioEntity) i.next();
			renovacioEnity.setActiu("N"); //$NON-NLS-1$
			getRenovacioEntityDao().update(renovacioEnity);
		}
		renovacio = new Renovacio();
		renovacio.setCodiUsuari(usuariEntity.getCodi());
		renovacio.setActiu("S"); //$NON-NLS-1$
		Calendar calRenovacio = Calendar.getInstance();
		calRenovacio.setTime(dataRenovacio);
		renovacio.setDataRenovacio(calRenovacio);
		Calendar data = GregorianCalendar.getInstance();
		renovacio.setData(data);
		RenovacioEntity renovacioEntity = getRenovacioEntityDao()
				.renovacioToEntity(renovacio);
		getRenovacioEntityDao().create(renovacioEntity);
	}
	
	/**
	 * Procés que llança un workflow de comprovació de nom quan es detecta
	 * que l'usuari que es dona d'alta mitjançant certificat te un nom + llinatges
	 * de més de 3 paraules.. per poder comprovar l'identitat
	 * @param nomComplert
	 * @param codiUsuari
	 */
	private void llancaWFComprovacioNomDesDAltaUsuari(String nomComplert, String codiUsuari) {
		try {
			JbpmContext context = getBpmEngine().getContext(); 

			//TODO: pooledActors Per ara les reponsables es posa el grup dgticseg
			// més endavant es podrà canviar per un altre...
			String responsables = "dgticseg"; //$NON-NLS-1$
			try {
				org.jbpm.graph.def.ProcessDefinition definition = context.getGraphSession().findLatestProcessDefinition(
						ProcesWFUsuari.PROCES_CANVIA_NOM_USUARI);

				org.jbpm.graph.exe.ProcessInstance process = new org.jbpm.graph.exe.ProcessInstance(definition);

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
				Collection tasks = process.getTaskMgmtInstance().getTaskInstances();
				if (tasks != null) {
					TaskInstance task = (TaskInstance) tasks.iterator().next();
					// Iniciem la tasca
					//task.start();
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

	public String handleAddUsuari(Signature sig, String userType)
			throws InternalErrorException {
		try {
			Context ctx = new InitialContext();
			if (!sig.verify()) {
				throw new InternalErrorException(Messages.getString("UsuariServiceImpl.IncorrectCertificate")); //$NON-NLS-1$
			}
			CertificateParser parser = new CertificateParser(sig.getCert());
			String nif = parser.getNif().toUpperCase();
			if (nif == null || nif.length() < 7) {
				throw new InternalErrorException(Messages.getString("UsuariServiceImpl.InvalidCertificate")); //$NON-NLS-1$
			}
			String user = "e" + nif.toLowerCase(); //$NON-NLS-1$
			if ((findUsuariByNIFUsuari(nif) == null)
					&& (findUsuariByCodiUsuari(user) == null)) {
				// L'usuari no existeix, el creem
				Usuari usuari = new Usuari();
				usuari.setNom(parser.getGivenName());
				usuari.setSegonLlinatge(parser.getSecondSurName());
				usuari.setPrimerLlinatge(parser.getFirstSurName());
				usuari.setServidorCorreu("nul"); //$NON-NLS-1$
				usuari.setServidorHome("nul"); //$NON-NLS-1$
				usuari.setServidorPerfil("nul"); //$NON-NLS-1$
				usuari.setTipusUsuari(userType);
				usuari.setActiu(new Boolean(true));
				usuari.setMultiSessio(new Boolean(false));
				usuari.setCodiGrupPrimari("externs"); //$NON-NLS-1$
				Configuracio configuracio = getConfiguracioService()
						.findParametreByCodiAndCodiXarxa(
								"seycon.password.age." //$NON-NLS-1$
										+ usuari.getTipusUsuari(), null);
				if (configuracio == null) {
					usuari.setPasswordMaxAge(new Long(45));
				} else {
					usuari.setPasswordMaxAge(new Long(Long
							.parseLong(configuracio.getValor())));
				}
				usuari.setCodi(user);
				usuari.setDataCreacioUsuari(GregorianCalendar.getInstance());
				usuari.setUsuariCreacio("SEYCON"); //$NON-NLS-1$
				usuari.setNIF(nif);
				
				// Creem l'usuari
				UsuariEntity usuariEntity = getUsuariEntityDao().usuariToEntity(usuari);
				getUsuariEntityDao().create(usuariEntity);
				/* Una vez creado, se almacena el NIF a nivell VO*/
				usuari= getUsuariEntityDao().toUsuari(usuariEntity);
				usuari.setNIF(nif);
				// Actualizamos el usuariEntity (NIF)
				usuariEntity = getUsuariEntityDao().usuariToEntity(usuari);
				getUsuariEntityDao().update(usuariEntity);	
				
				// Verifiquem que el nom + llinatge1 + llinatge2 no en tinga
				// més de 3 paraules, si les té hem de crear un WF de proposta
				// de canvi de nom
				String nomCertificat = parser.getGivenName() + " " + parser.getFirstSurName() + " " + parser.getSecondSurName(); //$NON-NLS-1$ //$NON-NLS-2$
				String partsCertificat[] = nomCertificat.split(" "); //$NON-NLS-1$
				if (partsCertificat.length > 3) {
					// Llancem el procés
					llancaWFComprovacioNomDesDAltaUsuari(nomCertificat, usuariEntity.getCodi());
				}
				
				getRuleEvaluatorService().applyRules(usuariEntity);

				return usuariEntity.getCodi();				
			} else {
				Usuari usu = findUsuariByNIFUsuari(nif);
				if (usu==null && findUsuariByCodiUsuari(user)!=null)
					throw new InternalErrorException(String.format(Messages.getString("UsuariServiceImpl.ExistsUserNoNIF"), //$NON-NLS-1$
							user)); 
				return usu.getCodi(); //si arribem aquí existeix per nif o per codi (mai serà nul)
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
					System.out.println(Messages.getString("UsuariServiceImpl.ValidMessage")); //$NON-NLS-1$
					continue;
				} else {
					System.out
							.println("*************************************************"); //$NON-NLS-1$
					System.out.println(Messages.getString("UsuariServiceImpl.NoValidMessage")); //$NON-NLS-1$
				}
				// Obtenemos las causas del error
				ArrayList lista_causas = resultados[i]
						.getListaCausasNoValidado();
				System.out.println(Messages.getString("UsuariServiceImpl.ForRequest") + i); //$NON-NLS-1$

				for (int j = 0; j < lista_causas.size(); j++) {
					BitException result = (BitException) lista_causas.get(j);
					System.out.println(result.getCode() + " : " //$NON-NLS-1$
							+ result.getTextoAdicional());
				}

			}
		} else {
			System.out.println(Messages.getString("UsuariServiceImpl.NoValidMessage")); //$NON-NLS-1$

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
				System.out.println(Messages.getString("UsuariServiceImpl.ForRequest") + i); //$NON-NLS-1$

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
	public String handleAddUsuari(java.util.Collection<java.security.cert.X509Certificate> certs,
			String userType) throws InternalErrorException,
			java.rmi.RemoteException 
	{
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
				ByteArrayInputStream fileIS = new ByteArrayInputStream(certs[i]
						.getEncoded());
				byte[] resultado_string = validador
						.validarCertificadoAutenticacion(fileIS);
				procesaResultados(resultado_string);
			}

			// Parseamos el certificado del usuario (primer elemento del array)
			CertificateParser parser = new CertificateParser(certUser);
			String nif = parser.getNif().toUpperCase();
			if (nif == null || nif.length() < 7) {
				throw new InternalErrorException(Messages.getString("UsuariServiceImpl.InvalidCertificate")); //$NON-NLS-1$
			}
			String user = "e" + nif.toLowerCase(); //$NON-NLS-1$
			if ((findUsuariByNIFUsuari(nif) == null)
					&& (findUsuariByCodiUsuari(user) == null)) {
				// L'usuari no existeix (el creem)
				Usuari usuari = new Usuari();
				usuari.setNom(parser.getGivenName());
				usuari.setSegonLlinatge(parser.getSecondSurName());
				usuari.setPrimerLlinatge(parser.getFirstSurName());
				usuari.setServidorCorreu("nul"); //$NON-NLS-1$
				usuari.setServidorHome("nul"); //$NON-NLS-1$
				usuari.setServidorPerfil("nul"); //$NON-NLS-1$
				usuari.setTipusUsuari(userType);
				usuari.setActiu(new Boolean(true));
				usuari.setMultiSessio(new Boolean(false));
				usuari.setCodiGrupPrimari("externs"); //$NON-NLS-1$
				Configuracio configuracio = getConfiguracioService()
						.findParametreByCodiAndCodiXarxa(
								"seycon.password.age." //$NON-NLS-1$
										+ usuari.getTipusUsuari(), null);
				if (configuracio == null) {
					usuari.setPasswordMaxAge(new Long(45));
				} else {
					usuari.setPasswordMaxAge(new Long(Long
							.parseLong(configuracio.getValor())));
				}
				usuari.setCodi(user);
				usuari.setDataCreacioUsuari(GregorianCalendar.getInstance());
				usuari.setUsuariCreacio("SEYCON"); //$NON-NLS-1$
				usuari.setNIF(nif);
				
				// Creem l'usuari
				UsuariEntity usuariEntity = getUsuariEntityDao().usuariToEntity(usuari);
				getUsuariEntityDao().create(usuariEntity);
				/* Una vez creado, se almacena el NIF a nivell VO*/
				usuari= getUsuariEntityDao().toUsuari(usuariEntity);
				usuari.setNIF(nif);
				// Actualizamos el usuariEntity (NIF)
				usuariEntity = getUsuariEntityDao().usuariToEntity(usuari);
				getUsuariEntityDao().update(usuariEntity);	
				
				// Verifiquem que el nom + llinatge1 + llinatge2 no en tinga
				// més de 3 paraules, si les té hem de crear un WF de proposta
				// de canvi de nom
				String nomCertificat = parser.getGivenName() + " " + parser.getFirstSurName() + " " + parser.getSecondSurName(); //$NON-NLS-1$ //$NON-NLS-2$
				String partsCertificat[] = nomCertificat.split(" "); //$NON-NLS-1$
				if (partsCertificat.length > 3) {
					// Llancem el procés
					llancaWFComprovacioNomDesDAltaUsuari(nomCertificat, usuariEntity.getCodi());
				}

				getRuleEvaluatorService().applyRules(usuariEntity);
				
				return usuariEntity.getCodi();
			} else {
				Usuari usu = findUsuariByNIFUsuari(nif);
				if (usu==null && findUsuariByCodiUsuari(user)!=null)
					throw new InternalErrorException(String.format(
							Messages.getString("UsuariServiceImpl.ExistsUserChangeNIF"), user)); //$NON-NLS-1$
				return usu.getCodi(); //si arribem aquí existeix per nif o per codi (mai serà nul)
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalErrorException(e.getMessage(),e);
		}
	}

	private Collection findUsuarisByCriteri(String codi, String nom,
			String primerLlinatge, String nomCurt, String dataCreacio,
			String usuariCreacio, String actiu, String segonLlinatge,
			String multiSessio, String comentari, String tipusUsuari,
			String servidorPerfil, String servidorHome, String servidorCorreu,
			String codiGrupPrimari, String dni, String dominiCorreu,
			String grupSecundari, Boolean restringeixCerca) {
		String query = "select usuari " //$NON-NLS-1$
				+ "from es.caib.seycon.ng.model.UsuariEntity usuari " //$NON-NLS-1$
				+ "left join usuari.servidorPerfil as servidorPerfil " //$NON-NLS-1$
				+ "left join usuari.servidorOfimatic as servidorOfimatic " //$NON-NLS-1$
				+ "left join usuari.servidorCorreu as servidorCorreu " //$NON-NLS-1$
				+ "left join usuari.grupPrimari as grupPrimari " //$NON-NLS-1$
				+ "left join usuari.dominiCorreu as dominiCorreu " //$NON-NLS-1$
				+ "left join usuari.tipusUsuari as tipusUsuariDomini "; //$NON-NLS-1$
		if (dni != null) {
			query += "left join usuari.dadaUsuari as dadaUsuari " //$NON-NLS-1$
					+ "left join dadaUsuari.tipusDada as tipusDada "; //$NON-NLS-1$
		}
		query += "where " //$NON-NLS-1$
				// + (restringeixCerca.booleanValue() ? "(rownum < 202) and " : "")
				+ "(:codi is null or usuari.codi like :codi) and (:nom is null or upper(usuari.nom) like upper(:nom)) and " //$NON-NLS-1$
				+ "(:primerLlinatge is null or upper(usuari.primerLlinatge) like upper(:primerLlinatge)) and " //$NON-NLS-1$
				+ "(:nomCurt is null or usuari.nomCurt like :nomCurt) and " //$NON-NLS-1$
				+ "(:usuariCreacio is null or usuari.usuariCreacio like :usuariCreacio) and " //$NON-NLS-1$
				+ "(:actiu is null or usuari.actiu = :actiu) and " //$NON-NLS-1$
				+ "(:segonLlinatge is null or upper(coalesce(usuari.segonLlinatge,' ')) like upper(:segonLlinatge)) and " //$NON-NLS-1$
				+ "(:multiSessio is null or usuari.multiSessio = :multiSessio) and " //$NON-NLS-1$
				+ "(:comentari is null or usuari.comentari like :comentari) and " //$NON-NLS-1$
				+ "(:tipusUsuari is null or tipusUsuariDomini.codi = :tipusUsuari) and " //$NON-NLS-1$
				+ "(:servidorPerfil is null or servidorPerfil.nom like :servidorPerfil) and " //$NON-NLS-1$
				+ "(:servidorHome is null or servidorOfimatic.nom like :servidorHome) and " //$NON-NLS-1$
				+ "(:servidorCorreu is null or servidorCorreu.nom like :servidorCorreu) and " //$NON-NLS-1$
				+ "(:codiGrupPrimari is null or grupPrimari.codi like :codiGrupPrimari) and " //$NON-NLS-1$
				+ "(:dominiCorreu is null or dominiCorreu.codi like :dominiCorreu) "; //$NON-NLS-1$
		if (dni != null) {
			query += " and (dadaUsuari.valorDada like :dni and tipusDada.codi = 'NIF') "; //$NON-NLS-1$
		}
		if (grupSecundari != null) {
			query += " and usuari.codi in " //$NON-NLS-1$
					+ "(select grupUsuari.usuari.codi " //$NON-NLS-1$
					+ "from es.caib.seycon.ng.model.UsuariGrupEntity grupUsuari " //$NON-NLS-1$
					+ "where grupUsuari.grup.codi like :grupSecundari ) "; //$NON-NLS-1$
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
				for (Parameter parameter : new Parameter[]{ codiParameter, nomParameter,
						primerLlinatgeParameter, nomCurtParameter,
						usuariCreacioParameter, actiuParameter,
						segonLlinatgeParameter, multiSessioParameter,
						comentariParameter, tipusUsuariParameter,
						servidorPerfilParameter, servidorHomeParameter,
						servidorCorreuParameter, codiGrupPrimariParameter,
						dniParameter, dominiCorreuParameter,
						codiGrupSecundariParameter })
				{
					finalParameters.add(parameter);
				}
				
			} else {
				for (Parameter parameter : new Parameter[]{ codiParameter, nomParameter,
						primerLlinatgeParameter, nomCurtParameter,
						usuariCreacioParameter, actiuParameter,
						segonLlinatgeParameter, multiSessioParameter,
						comentariParameter, tipusUsuariParameter,
						servidorPerfilParameter, servidorHomeParameter,
						servidorCorreuParameter, codiGrupPrimariParameter,
						dniParameter, dominiCorreuParameter })
				{
					finalParameters.add(parameter);
				}
			}
		} else {
			if (grupSecundari != null) {
				for (Parameter parameter : new Parameter[]{  codiParameter, nomParameter,
						primerLlinatgeParameter, nomCurtParameter,
						usuariCreacioParameter, actiuParameter,
						segonLlinatgeParameter, multiSessioParameter,
						comentariParameter, tipusUsuariParameter,
						servidorPerfilParameter, servidorHomeParameter,
						servidorCorreuParameter, codiGrupPrimariParameter,
						dominiCorreuParameter, codiGrupSecundariParameter })
				{
					finalParameters.add(parameter);
				}
			} else {
				for (Parameter parameter : new Parameter[]{  codiParameter, nomParameter,
						primerLlinatgeParameter, nomCurtParameter,
						usuariCreacioParameter, actiuParameter,
						segonLlinatgeParameter, multiSessioParameter,
						comentariParameter, tipusUsuariParameter,
						servidorPerfilParameter, servidorHomeParameter,
						servidorCorreuParameter, codiGrupPrimariParameter,
						dominiCorreuParameter })
				{
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
		query += " order by usuari.codi"; //$NON-NLS-1$
		if (restringeixCerca.booleanValue())
		{
			CriteriaSearchConfiguration csc = new CriteriaSearchConfiguration();
			csc.setMaximumResultSize(201);
            return getUsuariEntityDao().query(query, finalParameters.toArray(new Parameter[0]), csc);
		}
		else
		    return getUsuariEntityDao().query(query, finalParameters.toArray(new Parameter[0]));
	}

	protected Collection<Usuari> handleFindUsuariByCriteri(String codi,
		String nom, String primerLlinatge, String nomCurt, String dataCreacio,
		String usuariCreacio, String actiu, String segonLlinatge,
		String multiSessio, String comentari, String tipusUsuari,
		String servidorPerfil, String servidorHome, String servidorCorreu,
		String codiGrupPrimari, String dni, String dominiCorreu,
		String grupSecundari, Boolean restringeixCerca) throws Exception {
		// Utilizado para hacer búsqueda desde usuaris.zul
		
		int limitResults = Integer.MAX_VALUE;
		try {
			limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		} catch (NumberFormatException e) {}

		LimitDates limitDates = null;
		if (dataCreacio != null && dataCreacio.trim().compareTo("") != 0 //$NON-NLS-1$
				&& dataCreacio.trim().compareTo("%") != 0) { //$NON-NLS-1$
			dataCreacio = dataCreacio.trim();
			String dataCreacioClean = null;
			limitDates = DateUtils.getLimitDatesFromQuery(dataCreacio);
		}

		if (codi != null
				&& (codi.trim().compareTo("") == 0 || codi.trim() //$NON-NLS-1$
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

		Collection usuaris = findUsuarisByCriteri(codi, nom, primerLlinatge,
			nomCurt, dataCreacio, usuariCreacio, actiu, segonLlinatge,
			multiSessio, comentari, tipusUsuari, servidorPerfil, servidorHome,
			servidorCorreu, codiGrupPrimari, dni, dominiCorreu, grupSecundari,
			restringeixCerca); //UsuariEntity
		
		if (usuaris != null && usuaris.size() != 0)
		{
			// Ya tenemos los grupos del usuario con permisos
			Collection usuarisPermis = AutoritzacionsUsuari.filtraUsuariEntityCanQuery(usuaris);
			
			// Check maximum number of results
			if ((restringeixCerca != null) &&
				(restringeixCerca.booleanValue()) &&
				(usuarisPermis.size() > limitResults))
			{
				return getUsuariEntityDao().toUsuariList(usuarisPermis)
					.subList(0, limitResults);
			}

			if (usuarisPermis != null && usuarisPermis.size() != 0) {
				return getUsuariEntityDao().toUsuariList(usuarisPermis);
			}
		}
		return new Vector();
	}

	protected void handleDelete(Usuari usuari) throws Exception {
		if (!AutoritzacionsUsuari.canDeleteUser(usuari, getGrupEntityDao())) {
			throw new SeyconAccessLocalException("UsuariService", "delete (Usuari)", "user:delete, user:delete/*", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("UsuariServiceImpl.NoAuthorizedToDelete")); //$NON-NLS-1$
		}
		UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(
				usuari.getCodi());
		if (usuariEntity != null) {
			// els usuaris mai s'eliminen, es fa un downgrade
			baixaUsuari(usuari.getCodi());
			removeOldAlias(usuari);
		}
	}

	protected Collection<es.caib.seycon.ng.comu.NetworkAuthorization> handleFindXarxesACByCodiUsuari(String codiUsuari)
			throws Exception {
		UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(codiUsuari);
		if (usuariEntity != null) {
			Collection xarxesAC = usuariEntity.getXarxesAC();
			if (xarxesAC != null) {
				return getXarxaACEntityDao()
						.toNetworkAuthorizationList(xarxesAC);
			}
		}
		return null;
	}

	private void crearLlistaCorreu(Usuari usuari) throws InternalErrorException{
		// El cridem des de create(Usuari)
		String aliesDeCorreuCollection = usuari.getAliesCorreu();
		if (aliesDeCorreuCollection != null
				&& aliesDeCorreuCollection.trim().compareTo("") != 0) { //$NON-NLS-1$
			String aliesDeCorreu[] = aliesDeCorreuCollection.split(","); //$NON-NLS-1$
			for (int i = 0; i < aliesDeCorreu.length; i++) {
				String aliesDeCorreuParticionada[] = aliesDeCorreu[i].trim()
						.split("@"); //$NON-NLS-1$
				String alies = aliesDeCorreuParticionada[0];
				String domini = null;
				if (aliesDeCorreuParticionada.length == 2) {
					domini = aliesDeCorreuParticionada[1];
				}

				LlistesDeCorreuService llistesCorreuService = getLlistesDeCorreuService();
				LlistaCorreu llistaCorreu = llistesCorreuService
						.findLlistaCorreuByNomAndCodiDomini(alies, domini);
				if (llistaCorreu == null) {
					llistaCorreu = new LlistaCorreu();
					llistaCorreu.setCodiDomini(domini);
					llistaCorreu.setNom(alies);
					llistesCorreuService.create(llistaCorreu);
				}

				LlistaCorreuUsuari llistaCorreuUsuari = llistesCorreuService
						.findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
								alies, domini, usuari.getCodi());
				if (llistaCorreuUsuari == null) {
					llistaCorreuUsuari = new LlistaCorreuUsuari();
					llistaCorreuUsuari.setCodiDomini(domini);
					llistaCorreuUsuari.setCodiUsuari(usuari.getCodi());
					llistaCorreuUsuari.setNomLlistaCorreu(alies);
					llistesCorreuService.create(llistaCorreuUsuari);
				}
			}
		}
	}

	private void removeOldAlias(Usuari usuari) throws InternalErrorException{
		// Des de delete(Usuari)
		String aliesDeCorreuCollection = usuari.getAliesCorreu();
		if (aliesDeCorreuCollection != null
				&& aliesDeCorreuCollection.trim().compareTo("") != 0) { //$NON-NLS-1$
			String aliesDeCorreu[] = aliesDeCorreuCollection.split(","); //$NON-NLS-1$
			for (int i = 0; i < aliesDeCorreu.length; i++) {
				String aliesDeCorreuParticionada[] = aliesDeCorreu[i].trim()
						.split("@"); //$NON-NLS-1$
				String alies = aliesDeCorreuParticionada[0];
				String domini = null;
				if (aliesDeCorreuParticionada.length == 2) {
					domini = aliesDeCorreuParticionada[1];
				}
				LlistaCorreu llistaDeCorreu = this.getLlistesDeCorreuService()
						.findLlistaCorreuByNomAndCodiDomini(alies, domini);
				if (llistaDeCorreu != null) {
					LlistaCorreuUsuari llistaCorreuUsuari = this
							.getLlistesDeCorreuService()
							.findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
									alies, domini, usuari.getCodi());
					if (llistaCorreuUsuari != null) {
						getLlistesDeCorreuService().delete(llistaCorreuUsuari);
					}

					Collection usuaris = getLlistesDeCorreuService()
							.findUsuarisByNomLlistaCorreuAndCodiDomini(alies,
									domini);
					if (usuaris == null || usuaris.size() == 0) {
						getLlistesDeCorreuService().delete(llistaDeCorreu);
					}
				}
			}
		}
	}

	private void arreglaAlias(Usuari usuari) throws InternalErrorException { 
		// des de update(usuari), altaUsuari(Signatura) i baixaUsuari
		String aliesDeCorreuCollectionNou = usuari.getAliesCorreu();
		Usuari usuariVell = findUsuariByCodiUsuari(usuari.getCodi());
		String aliesDeCorreuCollectionVell = null;
		if (usuariVell != null) {
			aliesDeCorreuCollectionVell = usuariVell.getAliesCorreu();
		} else {
			aliesDeCorreuCollectionVell = ""; //$NON-NLS-1$
		}

		String aliesDeCorreuNou[];
		boolean aliesTrobatNou[];
		if (aliesDeCorreuCollectionNou != null
				&& aliesDeCorreuCollectionNou.trim().compareTo("") != 0) { //$NON-NLS-1$
			aliesDeCorreuNou = aliesDeCorreuCollectionNou.split(","); //$NON-NLS-1$
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
			aliesDeCorreuVell = aliesDeCorreuCollectionVell.split(","); //$NON-NLS-1$
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
				// crear i associar llista
				crearIAssociarLlista(aliesDeCorreuNou[i], usuari.getCodi());
			}
		}

		for (int i = 0; i < aliesDeCorreuVell.length; i++) {
			if (!aliesTrobatVell[i]  && aliesDeCorreuVell[i].length() > 0) {
				// esborrar i desassociar llista
				desassociaIEsborraLlista(aliesDeCorreuVell[i], usuari.getCodi());
			}
		}

	}
	
	private void desassociaIEsborraLlista(String aliesDeCorreu,
			String codiUsuari) throws InternalErrorException{
		// des de arreglaAlias - update (usuari)
		String parts[] = aliesDeCorreu.split("@"); //$NON-NLS-1$
		String alies = parts[0];
		String domini = null;
		if (parts.length == 2) {
			domini = parts[1];
		}

		LlistaCorreuUsuari llistaCorreuUsuari = getLlistesDeCorreuService()
				.findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
						alies, domini, codiUsuari);
		if (llistaCorreuUsuari != null) {
			getLlistesDeCorreuService().delete(llistaCorreuUsuari);
		}
		
		// La neteja de llistes es fa al delete de listaCorreuUsuari
		// Això es comprova ara a nivell de llistesDeCorreuUsuari.delete()
		
		/*LlistaCorreu llistaCorreu = getLlistesDeCorreuService()
				.findLlistaCorreuByNomAndCodiDomini(alies, domini);
		if (llistaCorreu != null) {
			Collection correuseExterns = getLlistesDeCorreuService()
					.findCorreusExternsByNomLlistaCorreuAndCodiDomini(alies,
							domini);
			Collection usuaris = getLlistesDeCorreuService()
					.findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDomini(
							alies, domini);
			Collection llistesDeCorreuConte = getLlistesDeCorreuService()
					.findRelacionsLlistaCorreuByNomLlistaCorreuConteAndCodiDomini(
							alies, domini);
			Collection llistesDeCorreuPertany = getLlistesDeCorreuService()
					.findRelacionsLlistaCorreuByNomLlistaCorreuPertanyAndCodiDomini(
							alies, domini);
			if (correuseExterns.size() == 0 && usuaris.size() == 0
					&& llistesDeCorreuConte.size() == 0
					&& llistesDeCorreuPertany.size() == 0) {
				getLlistesDeCorreuService().delete(llistaCorreu);
			}
		}*/

	}

	private void crearIAssociarLlista(String aliesDeCorreu, String codiUsuari) throws InternalErrorException {
		// de arreglaAlias(usuari) - update(usuari) 
		String parts[] = aliesDeCorreu.split("@"); //$NON-NLS-1$
		String alies = parts[0];
		String domini = null;
		if (parts.length == 2) {
			domini = parts[1];

    		if (getLlistesDeCorreuService().findLlistaCorreuByNomAndCodiDomini(
    				alies, domini) == null) {
    			LlistaCorreu llistaDeCorreu = new LlistaCorreu();
    			llistaDeCorreu.setCodiDomini(domini);
    			llistaDeCorreu.setNom(alies);
    			getLlistesDeCorreuService().create(llistaDeCorreu);
    		}
    
    		LlistaCorreuUsuari llistaCorreuUsuari = new LlistaCorreuUsuari();
    		llistaCorreuUsuari.setCodiDomini(domini);
    		llistaCorreuUsuari.setNomLlistaCorreu(alies);
    		llistaCorreuUsuari.setCodiUsuari(codiUsuari);
    		getLlistesDeCorreuService().create(llistaCorreuUsuari);
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

	protected Usuari handleUpdate(Usuari usuari) throws Exception {
		
		boolean canUpdateEsteUser =AutoritzacionsUsuari.canUpdateUser(usuari, getGrupEntityDao()); 
		
		// Comprobamos autorización del usuario
		// Si tiene customUpdate también puede pasar (!!) aunque en teoría (!!) no puede actualizar nada
		if (!canUpdateEsteUser && 
				!AutoritzacionsUsuari.canUpdateCustomUser(usuari, getGrupEntityDao())) {
			throw new SeyconAccessLocalException("UsuariService", "update (Usuari)", "user:update, user:update/*",  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("UsuariServiceImpl.NoAuthorizedToUpdate")); //$NON-NLS-1$
		}
		
		// Comprovem que s'hagi especificat el tipus d'usuari de domini
		if (usuari.getTipusUsuari() == null) {
			throw new SeyconException (Messages.getString("UsuariServiceImpl.UserTypeNotEspecified")); //$NON-NLS-1$
		}
		
		// Si sólo puede actualizar datos adicionales (teléfono, actualizamos sólo eso!!)
		if (!canUpdateEsteUser) {
			//Només actualitzem el telèfon (mantenim la resta de dades..)
			if (usuari.getId() != null) {
				UsuariEntity usu = getUsuariEntityDao().findById(usuari.getId());
				if (usu!=null) {
					Usuari usuTrobat = getUsuariEntityDao().toUsuari(usu);
					if (!usuTrobat.getTelefon().equals(usuari.getTelefon())) {
						// Només si ha canviat el número de telèfon
						usuTrobat.setUsuariDarreraModificacio(Security.getCurrentAccount());
						usuTrobat.setDataDarreraModificacioUsuari(GregorianCalendar.getInstance());
						usuTrobat.setTelefon(usuari.getTelefon()); // Guardem el telèfon
						UsuariEntity entity = getUsuariEntityDao().usuariToEntity(usuTrobat);
						if (entity != null) {
							getUsuariEntityDao().update(entity);
							return getUsuariEntityDao().toUsuari(entity);
						}
					} // Si no ha canviat el telèfon: donem error... no ha de tindre permis per canviar res més
					else if (!usuTrobat.getComentari().equals(usuari.getComentari())) {
						// Deixem que s'actualitzen les observacions (!!)
						usuTrobat.setUsuariDarreraModificacio(Security.getCurrentAccount());
						usuTrobat.setDataDarreraModificacioUsuari(GregorianCalendar.getInstance());
						usuTrobat.setComentari(usuari.getComentari()); // Guardem les observacions
						UsuariEntity entity = getUsuariEntityDao().usuariToEntity(usuTrobat);
						if (entity != null) {
							getUsuariEntityDao().update(entity);
							return getUsuariEntityDao().toUsuari(entity);
						}						
					}
				}
			}
			// Donem error si no s'ha trobat (??)
			throw new SeyconAccessLocalException("UsuariService", "update (Usuari)", "user:update, user:update/*",  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("UsuariServiceImpl.NoAuthorizedToUpdate"));			 //$NON-NLS-1$
		}
		
		// Comprobamos que no exista ya el usuario a crear (puede haber ya varios)
		String NIF = usuari.getNIF();
		if (NIF!=null && !"".equals(NIF.trim())) { //$NON-NLS-1$
			NIF = NIF.trim();
			Parameter params[] = new Parameter[]{new Parameter("nif",NIF)}; //$NON-NLS-1$
			Collection usuarisMateixNIF = getUsuariEntityDao().query("select usuari from es.caib.seycon.ng.model.UsuariEntity usuari, es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari where usuari = dadaUsuari.usuari and dadaUsuari.tipusDada.codi = 'NIF' and dadaUsuari.valorDada = :nif", params); //$NON-NLS-1$
			if (usuarisMateixNIF!=null) {
				if (usuarisMateixNIF.size()==1) {//comprobamos que no sea al mismo !!
					UsuariEntity usuariExist = (UsuariEntity) usuarisMateixNIF.iterator().next();
					if (!usuari.getCodi().equals(usuariExist.getCodi()))
						throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.ExistsUser"), //$NON-NLS-1$
								usuariExist.getCodi())); 
				} else if (usuarisMateixNIF.size()!=0) { // hay más de 1
					String codiUsuaris=""; //$NON-NLS-1$
					for (Iterator it = usuarisMateixNIF.iterator(); it.hasNext();) {
						codiUsuaris+= "'"+((UsuariEntity) it.next()).getCodi()+"', "; //$NON-NLS-1$ //$NON-NLS-2$
					}
					codiUsuaris = codiUsuaris.substring(0,codiUsuaris.length()-2);
					throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.ExistsUser"), //$NON-NLS-1$
							codiUsuaris)); 
				}
			}
		}
		
		// Ara hem de comprovar que si es modifica l'usuari [nom,llinatges o DNI, es verifique que siga correcte]
		UsuariEntity usuariAbans = getUsuariEntityDao().findByCodi(usuari.getCodi());
		// Verifiquem si hem de fer la comprovació de la identitat:
		// s'ha modifcat el nif de l'usuari??
		DadaUsuariEntity nifAnterior = getDadaUsuariEntityDao().findDadaByCodiTipusDada(usuari.getCodi(), "NIF"); //$NON-NLS-1$
		// verifiquem canvis al nom, llinatges i nif de l'usuari
		if (usuariAbans != null
				&& (usuariAbans.getNom()!=null && !usuariAbans.getNom().equals(usuari.getNom()))
				|| (usuariAbans.getPrimerLlinatge()!=null && !usuariAbans.getPrimerLlinatge().equals(
						usuari.getPrimerLlinatge()))
				|| (usuariAbans.getSegonLlinatge()!=null && !usuariAbans.getSegonLlinatge().equals(
						usuari.getSegonLlinatge())) 
				|| (usuariAbans.getSegonLlinatge()==null && usuari.getSegonLlinatge()!=null) //cas de que abans no tingues segon llinatge
				|| (nifAnterior != null && nifAnterior.getValorDada() != null && !nifAnterior
						.getValorDada().equals(usuari.getNIF()))
				|| (nifAnterior == null && usuari.getNIF()!=null) //abans no en tenia nif i ara si que en té
			) { 
			// Verificamos datos con red SARA:
			String[] resultat = null;
			try {
				// Sólo si és un nif o nie i el servei està activat
				if (esNIFoNIE(usuari.getNIF()) /*usuari.getNIF() != null && !"".equals(usuari.getNIF()*/ && 
						getConfiguracioServeiVerificacioIndentitatUsuari()!=null) { //si és actiu el servei
					// Verificamos con el servicio web de la red sara que los datos
					// del usuario sean correctos:
					resultat = verificarIdentitatUsuari(usuari.getNom(),
							usuari.getPrimerLlinatge(),
							usuari.getSegonLlinatge(), usuari.getNIF());
				}
			} catch (Throwable th) {
				// Capturamos errores de falta de parámetro:
				if (th.getCause() instanceof SeyconException) throw new Exception(th.getCause()); 
				
				// Evitamos problemas que pueden surgir con la llamada a los
				// servicios web
				resultat = null;
				if (resultat == null) {
					throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.VerificationDataServerError"), //$NON-NLS-1$
							th.getMessage()));
				}
			}

			if (resultat != null && !"0003".equals(resultat[0])) { //$NON-NLS-1$
				String msgError203 ="0233".equals(resultat[0])?Messages.getString("UsuariServiceImpl.CheckUserName"):""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.VerificationServiceErrorCod"), //$NON-NLS-1$
						resultat[0], resultat[1], msgError203)); 
			}
		}
		
		/*IAM-318: Propagar canvis de grup primari*/
		GrupService service = getGrupService();
		if(!usuari.getCodiGrupPrimari().equals(usuariAbans.getGrupPrimari().getCodi()))
		{
			service.propagateRolsChangesToDispatcher(usuari.getCodiGrupPrimari());
			service.propagateRolsChangesToDispatcher(usuariAbans.getGrupPrimari().getCodi());
		}
		
		arreglaAlias(usuari);
		usuari.setUsuariDarreraModificacio(Security.getCurrentAccount());
		usuari.setDataDarreraModificacioUsuari(GregorianCalendar.getInstance());
		UsuariEntity entity = getUsuariEntityDao().usuariToEntity(usuari);
		if (entity != null) {
			getUsuariEntityDao().update(entity);
			getAccountService().generateUserAccounts(usuari.getCodi());
			getRuleEvaluatorService().applyRules(entity);
			return getUsuariEntityDao().toUsuari(entity);
		}
		
		return null;
	}

	protected Grup handleFindGrupPrimariByCodiUsuari(String codiUsuari)
			throws Exception {
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
		GrupEntity grupPrimariEntity = usuari.getGrupPrimari();
		Grup grupPrimari = getGrupEntityDao().toGrup(grupPrimariEntity);
		return grupPrimari;
	}

	protected Maquina handleFindServidorCorreuByCodiUsuari(String codiUsuari)
			throws Exception {
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
		MaquinaEntity maquinaEntity = usuari.getServidorCorreu();
		if (maquinaEntity != null) {
			Maquina maquina = getMaquinaEntityDao().toMaquina(maquinaEntity);
			return maquina;
		}
		return null;
	}

	protected Maquina handleFindServidorHomeByCodiUsuari(String codiUsuari)
			throws Exception {
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
		MaquinaEntity maquinaEntity = usuari.getServidorOfimatic();
		if (maquinaEntity != null) {
			Maquina maquina = getMaquinaEntityDao().toMaquina(maquinaEntity);
			return maquina;
		}
		return null;
	}

	protected Maquina handleFindServidorPerfilByCodiUsuari(String codiUsuari)
			throws Exception {
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
		MaquinaEntity maquinaEntity = usuari.getServidorPerfil();
		if (maquinaEntity != null) {
			Maquina maquina = getMaquinaEntityDao().toMaquina(maquinaEntity);
			return maquina;
		}
		return null;

	}

	
	/* Aquí obtenim totes les dades de l'usuari 
	 * EXCEPTE el número de TELÈFON i el NIF (!!)
	 * (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.UsuariServiceBase#handleFindDadesUsuariByCodiUsuari(java.lang.String)
	 */
	protected Collection<DadaUsuari> handleFindDadesUsuariByCodiUsuari(String codiUsuari)
			throws Exception {
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
		Collection<DadaUsuari> dades = getDadaUsuariEntityDao().
		        toDadaUsuariList(usuari.getDadaUsuari());

		List<TipusDadaEntity> tipusDades = getTipusDadaEntityDao().loadAll();
		Collections.sort(tipusDades, new Comparator<TipusDadaEntity>(){
			public int compare(TipusDadaEntity o1, TipusDadaEntity o2) {
				return o1.getOrdre().compareTo(o2.getOrdre());
			}	
		});
		
		Iterator<TipusDadaEntity> tipusDadesIterator = tipusDades.iterator();
		while (tipusDadesIterator.hasNext()) {
			TipusDadaEntity tipusDada = tipusDadesIterator.next();
			if (tipusDada.getCodi().compareTo(NIF) != 0
					&& tipusDada.getCodi().compareTo(TELEFON) != 0) {
				Iterator<DadaUsuari> dadesIterator = dades.iterator();
				boolean teTipusDada = false;
				while (dadesIterator.hasNext()) {
					DadaUsuari dada = dadesIterator.next();
					teTipusDada = teTipusDada
							|| dada.getCodiDada()
									.compareTo(tipusDada.getCodi()) == 0;
					if (dada.getCodiDada().compareTo(NIF) == 0
							|| dada.getCodiDada().compareTo(TELEFON) == 0) {
						dadesIterator.remove();
					}
				}
				if (!teTipusDada) {
					dades.add(new DadaUsuari(tipusDada.getCodi(), null, //$NON-NLS-1$
							codiUsuari, null, null, null));
				}
			}
		}

		return dades;
	}

	protected DadaUsuari handleFindDadaByCodiTipusDada(String codiUsuari,
			String codiTipusDada) throws Exception {
		DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao()
				.findDadaByCodiTipusDada(codiUsuari, codiTipusDada);
		if (dadaUsuariEntity != null) {
			DadaUsuari dadaUsuari = getDadaUsuariEntityDao().toDadaUsuari(
					dadaUsuariEntity);
			return dadaUsuari;
		}
		return null;
	}

	protected Collection<TipusDada> handleGetTipusDades() throws Exception {
	    return getTipusDadaEntityDao().toTipusDadaList(getTipusDadaEntityDao().loadAll());
	}

	protected Collection<Sessio> handleFindSessionsByCodiUsuari(String codiUsuari)
			throws Exception {
		Usuari usuari = findUsuariByCodiUsuari(codiUsuari);
		
		if (usuari !=null && AutoritzacionsUsuari.canQueryUserSession(usuari, getGrupEntityDao())) {
			List<SessioEntity> sessions = getSessioEntityDao().findSessionsByCodiUsuari(
					codiUsuari);
			if (sessions != null) {
				return getSessioEntityDao().toSessioList(sessions);
			}
		} 
		//TODO: Aquí no donem error de que no disposa permisos ... (!!)
		return new Vector();
	}

	protected Collection handleFindUsuariImpressoraByCodiUsuari(
			String codiUsuari) throws Exception {
		Collection<UsuariImpressoraEntity> usuariImpressores = getUsuariEntityDao()
				.findByCodi(codiUsuari).getImpressores();
		if (usuariImpressores != null) {
			return getUsuariImpressoraEntityDao().toUsuariImpressoraList(
					usuariImpressores);
		}
		return new Vector();
	}

	protected void handleAddGrupToUsuari(String codiUsuari, String codiGrup)
			throws Exception {
		/*UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(codiUsuari);
		GrupEntity GrupEntity = getGrupEntityDao().findByCodi(codiGrup);
		UsuariGrup usuariGrup = new UsuariGrup();
		usuariGrup.setCodiUsuari(codiUsuari);
		usuariGrup.setCodiGrup(codiGrup);
		UsuariGrupEntity usuariGrupEntity = getUsuariGrupEntityDao()
				.usuariGrupToEntity(usuariGrup);
		this.getUsuariGrupEntityDao().create(usuariGrupEntity);*/
		GrupService service = getGrupService();
		service.addGrupToUsuari(codiUsuari, codiGrup);
	}

	protected Collection<Aplicacio> handleGetAplicacionsByCodiUsuari(String codiUsuari)
			throws Exception {
		Collection aplicacions = getAplicacioEntityDao().findByUser(codiUsuari);
		if (aplicacions != null) {
			return getAplicacioEntityDao().toAplicacioList(aplicacions);
		}
		return new LinkedList();
	}
	
	protected Collection<Aplicacio> handleGetAplicacionsGestionablesWFByCodiUsuari(
			String codiUsuari) throws Exception {
		Collection aplicacions = getAplicacioEntityDao().findManageableByUser(codiUsuari);

		if (aplicacions != null) {
			return getAplicacioEntityDao().toAplicacioList(aplicacions);
		}
		return new LinkedList();
	}

	protected Collection<Rol> handleGetRolsAplicacioByCodiUsuariAndCodiAplicacio(
			String codiUsuari, String codiAplicacio) throws Exception {
		Collection rols = getRolEntityDao()
				.getRolsAplicacioByCodiUsuariAndCodiAplicacio(codiUsuari,
						codiAplicacio);
		if (rols != null) {
			return getRolEntityDao().toRolList(rols);
		}
		return new Vector();
	}

	protected Collection handleGetRolsByCodiUsuari(String codiUsuari)
			throws Exception {
		Collection rols = getRolEntityDao().findRolsByCodiUsuari(codiUsuari);
		if (rols != null) {
			return getRolEntityDao().toRolList(rols);
		}
		return new LinkedList();
	}

	protected String handleAssignaPasswordInicial(String codiUsuari, String codiDominiContrasenyes)
			throws Exception {
		return handleCanviPassword(codiUsuari, codiDominiContrasenyes);
	}

	private Grup getGrupByTipus(String codiGrup, String tipus) throws InternalErrorException {
		if (codiGrup != null) {//?
			Grup grup = getGrupService().findGrupByCodiGrup(codiGrup);
			String tipusGrup = grup.getTipus(); // Unitat Organizativa
			if (tipusGrup != null) {
				if (tipusGrup.compareTo(tipus) == 0) {
					return grup;
				} else {
					return getGrupByTipus(grup.getCodiPare(), tipus);
				}
			}
		}
		return null;
	}

	protected Collection<Grup> handleGetConselleriesByCodiUsuari(String codiUsuari)
			throws Exception {
		String conselleriaCode = "CONSELLERIA"; //$NON-NLS-1$
		Collection<Grup> conselleries = new LinkedHashSet();
		Grup grupPrimari = this.getGrupService().findGrupPrimariByCodiUsuari(
				codiUsuari);
		Grup conselleria = null;
		if (grupPrimari != null) {
			conselleria = getGrupByTipus(grupPrimari.getCodi(), conselleriaCode);
			if (conselleria != null) {
				conselleries.add(conselleria);
			}
		}
		Collection grupsFromUsuaris = getGrupService()
				.findGrupsFromUsuarisByCodiUsuari(codiUsuari);
		Iterator iterator = grupsFromUsuaris.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			conselleria = getGrupByTipus(grup.getCodi(), conselleriaCode);
			if (conselleria != null) {
				conselleries.add(conselleria);
			}
		}
		Collection grupsFromRols = getGrupService()
				.findGrupsFromRolsByCodiUsuari(codiUsuari);
		iterator = grupsFromRols.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			conselleria = getGrupByTipus(grup.getCodi(), conselleriaCode);
			if (conselleria != null) {
				conselleries.add(conselleria);
			}
		}
		return conselleries;
	}

	protected Collection<Grup> handleGetDireccionsGeneralsByCodiUsuari(
			String codiUsuari) throws Exception {
		String direccioGeneralCode = "DIRECCIO_GENERAL"; //$NON-NLS-1$
		Collection<Grup> direccionsGenerals = new LinkedHashSet();
		Grup grupPrimari = this.getGrupService().findGrupPrimariByCodiUsuari(
				codiUsuari);
		Grup direccioGeneral = null;
		if (grupPrimari != null) {
			direccioGeneral = getGrupByTipus(grupPrimari.getCodi(),
					direccioGeneralCode);
			if (direccioGeneral != null) {
				direccionsGenerals.add(direccioGeneral);
			}
		}
		Collection grupsFromUsuaris = getGrupService()
				.findGrupsFromUsuarisByCodiUsuari(codiUsuari);
		Iterator iterator = grupsFromUsuaris.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			direccioGeneral = getGrupByTipus(grup.getCodi(),
					direccioGeneralCode);
			if (direccioGeneral != null) {
				direccionsGenerals.add(direccioGeneral);
			}
		}
		Collection grupsFromRols = getGrupService()
				.findGrupsFromRolsByCodiUsuari(codiUsuari);
		iterator = grupsFromRols.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			direccioGeneral = getGrupByTipus(grup.getCodi(),
					direccioGeneralCode);
			if (direccioGeneral != null) {
				direccionsGenerals.add(direccioGeneral);
			}
		}
		return direccionsGenerals;
	}

	protected Boolean handleExisteixNomCurt(String nomCurt) throws Exception {
		UsuariEntity usuariEntity = this.getUsuariEntityDao().findByNomCurt(
				nomCurt);
		return new Boolean(usuariEntity != null);
	}

	protected Usuari handleFindByNomCurt(String nomCurt) throws Exception {
		UsuariEntity usuariEntity = getUsuariEntityDao().findByNomCurt(nomCurt);
		if (usuariEntity != null) {
			Usuari usuari = getUsuariEntityDao().toUsuari(usuariEntity);
			return usuari;
		}
		return null;
	}

	protected Collection<Usuari> handleFindUsuarisByDadesBasiques(String codi,
			String nom, String primerLlinatge, String segonLlinatge, String dni)
			throws Exception {
		return findUsuariByCriteri(codi, nom, primerLlinatge, null, null, null,
				null, segonLlinatge, null, null, null, null, null, null, null,
				dni, null, null, new Boolean(true));
	}

	protected Collection<UsuariImpressora>  handleFindUsuariImpressoresByCodiUsuari(
			String codiUsuari) throws Exception {
		Collection usuariImpressores = getUsuariImpressoraEntityDao()
				.findUsuariImpressoresByCodiUsuari(codiUsuari);
		if (usuariImpressores != null) {
			return getUsuariImpressoraEntityDao().toUsuariImpressoraList(
					usuariImpressores);
		}
		return new Vector();
	}

	protected String[] handleRefreshCanvis(String codiUsuari) throws Exception {
		String tasques = getUsuariEntityDao().refreshCanvis(codiUsuari);
		if (tasques != null) {
			try {
				return tasques.split("\n"); //$NON-NLS-1$
			} catch (Exception e) {
				return new String[] { tasques };
			}
		}
		return new String[0];
	}

	protected String handleCanviPassword(String codiUsuari, String codiDominiContrasenyes) throws Exception {
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
		if (usuari != null && "S".equals(usuari.getActiu())) { //$NON-NLS-1$
			if ( AutoritzacionsUsuari.canUpdateUserPassword(usuari.getGrupPrimari().getCodi()) ) {
				DominiContrasenyaEntity dominiContrasenyes = getDominiContrasenyaEntityDao().findByCodi(codiDominiContrasenyes);
				Password pass = getInternalPasswordService().generateNewPassword(usuari, dominiContrasenyes, true);
				auditaCanviPassword(codiUsuari, dominiContrasenyes.getCodi());
				return pass.getPassword();
			} else {
				throw new SecurityException(String.format(Messages.getString("UsuariServiceImpl.NoAuthorizedToChangePass"), codiUsuari)); //$NON-NLS-1$
			}
		} else {
			throw new SeyconException(Messages.getString("UsuariServiceImpl.UserInactiveToChangePass")); //$NON-NLS-1$
		}
	}
	
	transient private Random random = new Random(new Date().getTime());
	

	private void auditaCanviPassword(String codiUsuariAuditat, String codiDominiContrasenyes) {
		String codiUsuari = Security.getCurrentAccount();
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio("P"); //$NON-NLS-1$
		auditoria.setUsuari(codiUsuariAuditat);
		auditoria.setAutor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_USUARI"); //$NON-NLS-1$
		auditoria.setPasswordDomain(codiDominiContrasenyes);

		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}

	protected String[] handleGetTasques(String codiUsuari) throws Exception {
		String[] resultats = getUsuariEntityDao().getTasques(codiUsuari);
		return resultats;
	}

	protected String handleGetSeguentCodi() throws Exception {
		String codi = getUsuariEntityDao().getSeguentCodi();
		return codi;
	}

	protected Collection<RolAccount> handleFindRolsUsuarisAmbRolsDAplicacioByCodiAplicacio(
			String codiAplicacio) throws Exception {
		List<RolAccountEntity> rolsUsuari = getRolAccountEntityDao().query(
				"select rolusu " //$NON-NLS-1$
				+ "from " //$NON-NLS-1$
				+ "es.caib.seycon.ng.model.RolAccountEntity rolusu " //$NON-NLS-1$
				+ "join rolusu.account.users as users "  //$NON-NLS-1$
				+ "join users.user as usuari " //$NON-NLS-1$
				+ "where " //$NON-NLS-1$
				+ "rolusu.rol.aplicacio.codi = :codiAplicacio " //$NON-NLS-1$
				+ "order by usuari.codi, rolusu.rol.nom, rolusu.rol.baseDeDades.codi", //$NON-NLS-1$
				new Parameter[] {
						new Parameter("codiAplicacio", codiAplicacio) //$NON-NLS-1$
				}
			);
		return getRolAccountEntityDao().toRolAccountList(rolsUsuari);
	}

	protected Usuari handleFindUsuariByIdUsuari(Long idUsuari) throws Exception {
		UsuariEntity usuariEntity = getUsuariEntityDao().findById(idUsuari);
		if (usuariEntity != null) {
			return getUsuariEntityDao().toUsuari(usuariEntity);
		}
		return null;
	}

	protected Usuari handleFindUsuariByCodiTipusDadaIValorTipusDada(
			String codiTipusDada, String valorTipusDada) throws Exception {
		// utilitzat en el autoenrollment versió 3.1
		UsuariEntity usuariEntity = getUsuariEntityDao()
				.findUsuariByCodiTipusDadaIValorDada(codiTipusDada,
						valorTipusDada);
		if (usuariEntity != null)
			return getUsuariEntityDao().toUsuari(usuariEntity);
		else
			return null;

	}

	protected java.security.Principal getPrincipal() {
		return Security.getPrincipal();
	}

	private UsuariEntity findUsuariExistentperCodiXestib(String codiXestib) {
		// obtenim el codi d'usuari a partir del codi Xestib
		try {
			UsuariEntity usuariExistent = getUsuariEntityDao()
				.findUsuariByCodiTipusDadaIValorDada(DADA_ADDICIONAL_CODI_XESTIB,
						codiXestib);
			return usuariExistent;
		} catch (Throwable th) {
			return null;
		}
	}
	
	private GrupEntity gestionaGrupAlumne(String codiCentre) throws Exception {
		GrupEntity grupCentreAlumne = getGrupEntityDao().findByCodi(
				"ce" + codiCentre + "-a"); //$NON-NLS-1$ //$NON-NLS-2$
		
		if (grupCentreAlumne != null)
			return grupCentreAlumne;
		else {
			// Creem el nou grup
			// Han de descendre del grupo d'alumnes
			GrupEntity g_alumnes = getGrupEntityDao().findByCodi("alumnes"); //$NON-NLS-1$
			if (g_alumnes == null)
				throw new SeyconException(
						Messages.getString("UsuariServiceImpl.AlumGourpNotFounded")); //$NON-NLS-1$
			GrupEntity g_centre = getGrupEntityDao().findByCodi(
					"ce" + codiCentre); //$NON-NLS-1$
			if (g_centre == null)
				throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.NoExistsGroup"), codiCentre)); //$NON-NLS-1$
			// Creem el grup de l'alumne
			Grup grupAlumne = new Grup();
			grupAlumne.setCodi("ce" + codiCentre + "-a"); //$NON-NLS-1$ //$NON-NLS-2$
			grupAlumne.setDescripcio("Alumnes de " + g_centre.getDescripcio()); //$NON-NLS-1$
			grupAlumne.setQuota("0"); //$NON-NLS-1$
			grupAlumne.setCodiPare(g_alumnes.getCodi()); // Descentent del grup
															// "alumnes"
			grupAlumne.setTipus("ALUMNE"); // de tipus ALUMNE //$NON-NLS-1$
			GrupEntity grupAlumneE = getGrupEntityDao()
					.grupToEntity(grupAlumne);
			getGrupEntityDao().create(grupAlumneE);
			return grupCentreAlumne;
		}
	}
	
	/**
	 * Generem una contrasenya de lletras i digits (2 digits)
	 * @param longitud
	 * @return
	 */
	private String generaContrasenyaAlumne(int longitud) {
		char[] pw = new char[longitud];
		int c = 'A';
		int r1 = 0;
		int numDigits = 0;
		// Generem longitud-2 lletres i 2 números
		for (int i=0; i< longitud -2 ; i++) {
			c = 'a' + (int) (Math.random() * 26);
			pw[i] = (char) c;
		}
		for (int i= longitud-2; i>=0 && i < longitud ; i++) {
			c = '0' + (int) (Math.random() * 10);
			pw[i] = (char) c;
		}
		
		return new String(pw);
	}
	
	protected UsuariAlumne handleAltaUsuariAlumne(UsuariAlumne usuariAlumne)
			throws Exception {
		
		// Verifiquem que l'usuari no existisca ja (pot tornar null)
		UsuariEntity usu = findUsuariExistentperCodiXestib(usuariAlumne.getCodiXestib());
		
		if (usu!=null && !"Z".equals(usu.getTipusUsuari().getCodi())) { // Si no és de tipus alumne, no permetem donar d'alta com alumne //$NON-NLS-1$
			throw new SeyconException(String.format(Messages.getString("UsuariServiceImpl.UserAlreadyExists"), //$NON-NLS-1$
					usuariAlumne.getCodiXestib(), usu.getTipusUsuari().getCodi()));
		}
		
		if (usu == null) {//creem el usuari VO nou
			Usuari usuari=new Usuari();
			
			usuari.setCodi(getUsuariEntityDao().getSeguentCodiAlumne());
			usuari.setDataCreacioUsuari(GregorianCalendar.getInstance());
			usuari.setMultiSessio(new Boolean(false));
			
			usuari.setActiu(new Boolean(true));
			// No en té efecte i ja no existeix al VO
			//usuari.setContrasenyaCaducada(new Boolean(true)); //li asignarem una nova
			
			// dades bàsiques:
			usuari.setNom(usuariAlumne.getNom());
			usuari.setPrimerLlinatge(usuariAlumne.getLlinatge1());
			usuari.setSegonLlinatge(usuariAlumne.getLlinatge2());
			usuari.setTipusUsuari("Z"); //Tipus d'usuari alumne //$NON-NLS-1$
			
			// Assignem el GRUP primari de l'alumne: (el creem si no existeix encara)
			GrupEntity grupCentreAlumne = gestionaGrupAlumne(usuariAlumne.getCodiCentre());
			usuari.setCodiGrupPrimari(grupCentreAlumne.getCodi());
			
			usuari.setServidorCorreu("nul");  //$NON-NLS-1$
			usuari.setServidorHome("nul");  //$NON-NLS-1$
			usuari.setServidorPerfil("nul");  //$NON-NLS-1$
			
			usuari.setUsuariCreacio(getPrincipal().getName()); 
		
			//Cridem l'EJB per a que cree l'usuari
			usu = getUsuariEntityDao().usuariToEntity(usuari);
			getUsuariEntityDao().create(usu);			

			//creem el codi Xestib com a dada addicional
			DadaUsuari dadaUsuariXestib=new DadaUsuari();
			dadaUsuariXestib.setCodiDada(DADA_ADDICIONAL_CODI_XESTIB);
			dadaUsuariXestib.setCodiUsuari(usu.getCodi());
			dadaUsuariXestib.setValorDada(usuariAlumne.getCodiXestib());		
			DadaUsuariEntity dadaUsuariEntity=getDadaUsuariEntityDao().dadaUsuariToEntity(dadaUsuariXestib);
			getDadaUsuariEntityDao().create(dadaUsuariEntity);
			
			//creem la descripció del grup de l'alumne com a dada adicional
			DadaUsuari dadaUsuariGrupDescripcioXestib=new DadaUsuari();
			dadaUsuariGrupDescripcioXestib.setCodiDada(DADA_ADDICIONAL_CODI_XESTIB_GRUPALUMNE);
			dadaUsuariGrupDescripcioXestib.setCodiUsuari(usu.getCodi());
			dadaUsuariGrupDescripcioXestib.setValorDada(usuariAlumne.getGrupAlumne());		
			DadaUsuariEntity dadaUsuariGrupDescripcioEntity=getDadaUsuariEntityDao().dadaUsuariToEntity(dadaUsuariGrupDescripcioXestib);
			getDadaUsuariEntityDao().create(dadaUsuariGrupDescripcioEntity);
			
			// creem l'adreça de correu extern com a dada addicional
			if (usuariAlumne.getCorreuElectronic()!=null && !"".equals(usuariAlumne.getCorreuElectronic())) { //$NON-NLS-1$
				DadaUsuari dadaUsuariCorreuElectronic=new DadaUsuari();
				dadaUsuariCorreuElectronic.setCodiDada(E_MAIL_CONTACTE);
				dadaUsuariCorreuElectronic.setCodiUsuari(usu.getCodi());
				dadaUsuariCorreuElectronic.setValorDada(usuariAlumne.getCorreuElectronic());		
				DadaUsuariEntity dadaUsuariCorreuElectronicEntity=getDadaUsuariEntityDao().dadaUsuariToEntity(dadaUsuariCorreuElectronic);
				getDadaUsuariEntityDao().create(dadaUsuariCorreuElectronicEntity);
			}
			
		} else {
			// L'usuari ja existeix:
			// El transformem a VO
			Usuari usuari = getUsuariEntityDao().toUsuari(usu);
			
			usuari.setActiu(new Boolean(true));
			// No en té efecte i ja no existeix al VO:
			//usuari.setContrasenyaCaducada(new Boolean(true));
			
			// Actualitzem les seues dades bàsiques:
			usuari.setNom(usuariAlumne.getNom());
			usuari.setPrimerLlinatge(usuariAlumne.getLlinatge1());
			usuari.setSegonLlinatge(usuariAlumne.getLlinatge2());
			usuari.setTipusUsuari("Z"); //Tipus d'usuari alumne //$NON-NLS-1$
			
			// Indiquem l'usuari que fa le modificacions
			usuari.setUsuariDarreraModificacio(getPrincipal().getName());
			
			// Transformem a entity i actualitzem les dades
			usu = getUsuariEntityDao().usuariToEntity(usuari);
			getUsuariEntityDao().update(usu);
			
			// actualitzem el seu centre (grup primari de l'usuari) i les dades addicionals
			GrupEntity grupCentreAlumne = gestionaGrupAlumne(usuariAlumne.getCodiCentre());
			usu.setGrupPrimari(grupCentreAlumne); // Grup Primari
			// Actualitzem dades addicionals (descripció grup de l'alumne)
			DadaUsuariEntity d_grupAlumne = null;
			DadaUsuariEntity d_correuElectronic = null; //correu electrònic

			Collection dadesAddicionals = new HashSet(usu.getDadaUsuari()); //you're so lazy
			for (Iterator it = dadesAddicionals.iterator(); it.hasNext();) {
				DadaUsuariEntity dada = (DadaUsuariEntity) it.next();
				/*if (dada.getTipusDada().getCodi().equals(DADA_ADDICIONAL_CODI_XESTIB)) {
					d_codiXestib = dada;
				} else */
				if (dada.getTipusDada().getCodi().equals(DADA_ADDICIONAL_CODI_XESTIB_GRUPALUMNE)) {
					d_grupAlumne = dada;
				} else if (dada.getTipusDada().getCodi().equals(E_MAIL_CONTACTE)) {
					d_correuElectronic = dada;
				}
			}
			// Actualitzem la dada addicional del grup del alumne
			if (d_grupAlumne!=null) {
				d_grupAlumne.setValorDada(usuariAlumne.getGrupAlumne());
				getDadaUsuariEntityDao().update(d_grupAlumne);
			} else {
				DadaUsuari dadaUsuariGrupDescripcioXestib=new DadaUsuari();
				dadaUsuariGrupDescripcioXestib.setCodiDada(DADA_ADDICIONAL_CODI_XESTIB_GRUPALUMNE);
				dadaUsuariGrupDescripcioXestib.setCodiUsuari(usu.getCodi());
				dadaUsuariGrupDescripcioXestib.setValorDada(usuariAlumne.getGrupAlumne());		
				DadaUsuariEntity dadaUsuariGrupDescripcioEntity=getDadaUsuariEntityDao().dadaUsuariToEntity(dadaUsuariGrupDescripcioXestib);
				getDadaUsuariEntityDao().create(dadaUsuariGrupDescripcioEntity);
			}
			
			// Actualitzem la dada addicional de correu electrònic
			String correuElectronic = usuariAlumne.getCorreuElectronic();
			
			// Si l'han esborrat el correu electrònic i existeix ja: l'esborrem
			if (correuElectronic == null || "".equals(correuElectronic.trim())) { //$NON-NLS-1$
				if (d_correuElectronic !=null) {
					getDadaUsuariEntityDao().remove(d_correuElectronic);
				}
			}
			else {//s'ha d'establir la dada addicional
				if (d_correuElectronic!=null) {//ja existeix
					d_correuElectronic.setValorDada(correuElectronic);
					getDadaUsuariEntityDao().update(d_correuElectronic);
				} else {//no existeix: el creem
					DadaUsuari dadaUsuariCorreuElectronic=new DadaUsuari();
					dadaUsuariCorreuElectronic.setCodiDada(E_MAIL_CONTACTE);
					dadaUsuariCorreuElectronic.setCodiUsuari(usu.getCodi());
					dadaUsuariCorreuElectronic.setValorDada(usuariAlumne.getCorreuElectronic());		
					DadaUsuariEntity dadaUsuariCorreuElectronicEntity=getDadaUsuariEntityDao().dadaUsuariToEntity(dadaUsuariCorreuElectronic);
					getDadaUsuariEntityDao().create(dadaUsuariCorreuElectronicEntity);
				}
			}
			
			// Actualitzem l'usuari (dades addicionals)
			getUsuariEntityDao().update(usu);
		}
		
		// Renovem contrasenya:
		String contrasenya = generaContrasenyaAlumne(6);
		// Establim contrasenya a l'usuari
		// Generem la tasca
		Tasca canviaPass = new Tasca();
		canviaPass.setTransa("UpdateUserPassword");//Actualització del password de l'usuari //$NON-NLS-1$
		canviaPass.setDataTasca(Calendar.getInstance());
		canviaPass.setUsuari(usu.getCodi());
		canviaPass.setContra(contrasenya);
		canviaPass.setCancon("N");//Posem que no ha de Canviar contrasenya //$NON-NLS-1$
		TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(canviaPass);
		getTasqueEntityDao().create(tasca);

		usuariAlumne.setContrasenya(contrasenya);
		
		// Retornem el codi de l'usuari
		usuariAlumne.setCodiUsuari(usu.getCodi());

		// Actualitzem els canvis al UsuariEntity
		getUsuariEntityDao().update(usu);
		
		getRuleEvaluatorService().applyRules(usu);

		return usuariAlumne;
	}

	protected TargetaExtranet handleCreaTargetaExtranet(String codiUsuari)
			throws Exception {
		
		ScTarget entity = getScTargetDao().creaTargetaExtranet(codiUsuari);
		if (entity!=null)
			return getScTargetDao().toTargetaExtranet(entity);
	
		return null;
	}

	protected Collection<TargetaExtranet> handleFindTargetesExtranetByCodiUsuari(
			String codiUsuari, String activa) throws Exception {
		
		Collection targetes = getUsuariEntityDao().findTargetesExtranetByCodi(codiUsuari, activa);
		if (targetes !=null) {
			//targetes = new Vector(targetes);
			return getScTargetDao().toTargetaExtranetList(targetes);			
		}
		return null;
	}

	protected TargetaExtranet handleUpdate(TargetaExtranet targetaExtranet)
			throws Exception {
		ScTarget entity = getScTargetDao().targetaExtranetToEntity(targetaExtranet);
		if (entity != null) {
			getScTargetDao().update(entity);
			return getScTargetDao().toTargetaExtranet(entity);
		}
		return null;
	}

	

	protected TargetaExtranet handleFindTargetaExtranetByCodiUsuariAndCodiTargeta(
			String codiUsuari, String codiTargeta) throws Exception {
		ScTarget entity = getScTargetDao().findByCodiTargetaAndCodiUsuari(codiTargeta, codiUsuari);
		if (entity==null)
			throw new SeyconException(Messages.getString("UsuariServiceImpl.NoCardFounded")); //$NON-NLS-1$
		return getScTargetDao().toTargetaExtranet(entity);
	}

	
	
	protected Collection<Rol> handleFindJerarquiaRolsUsuariByCodiUsuari(
			String codiUsuari) throws Exception {
		
		return handleFindJerarquiaRolsUsuariByCodiUsuari(codiUsuari,new Boolean(true));
		
	}
	
	protected Collection<Rol> handleFindJerarquiaRolsUsuariByCodiUsuari (
			String codiUsuari, Boolean incloureRolsDirectes) throws Exception {
		// NOTA IMPORTANT!!
		// Si es modifica aquest mètode, modificar també el se AplicacioServiceImpl
		// mètode: handleFindInformacioTextualJerarquiaRolsUsuariByCodiUsuari
		//
		// TINDRE EN COMPTE TAMBÉN EL MÉTODE AplicacioServiceImpl
		// findInformacioTextualJerarquiaRolsByRolAplicacioAndDispatcher
		//
		// Aquest mètode a diferència del de Aplicació retorna RolEntity
		// S'empra per obtindre els punts d'entrada atorgats a l'usuari
		
		
		// Obtenemos los roles heredados del usuario
		Collection<Rol> jerarquiaRolsUsuari = new ArrayList();
		
		// Obtenemos el usuario
		UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);
		
		if (usuari == null) return new ArrayList(); //usuari nobody
		
		// Sus roles (RU)
		HashMap totRol = new HashMap();
		// Los añadimos al listado de roles (hash = Id)
		List<RolAccountEntity> rolsUsuaris = new LinkedList<RolAccountEntity>();
		for (UserAccountEntity uae: usuari.getAccounts())
		{
			AccountEntity account = uae.getAccount();
			if (account.getType().equals(AccountType.USER) ) 
			{
				for (RolAccountEntity ra: account.getRoles())
				{
					RolEntity rol = (RolEntity) ra.getRol();
					rolsUsuaris.add(ra);
					totRol.put(rol.getId(), rol);
					// A veces no interesa incluir los roles directos (ej: usuari - roles heredados)
					if (incloureRolsDirectes.booleanValue()) {
						Rol r = getRolEntityDao().toRol(rol);
						jerarquiaRolsUsuari.add(r);
					}
				}
			}
		}
		
		
		// Obtener grupos : primario y secundarios (y sus padres)
		LinkedList grupsUsuariAnalizar = new LinkedList(); // FIFO
		grupsUsuariAnalizar.add(usuari.getGrupPrimari()); // Grupo Primario
		Collection grupsSecundaris = usuari.getGrupsSecundaris(); // Grupos secundarios
		for (Iterator it = grupsSecundaris.iterator(); it.hasNext(); ) {
			UsuariGrupEntity uge = (UsuariGrupEntity) it.next();
			grupsUsuariAnalizar.add(uge.getGrup()); 
		}
		
		// Buscamos los padres de estos grupos, para obtener sus roles
		HashMap totsGrups = new HashMap(); // hash = ID
		GrupEntity grupActual = null;
		while ( (grupActual = (GrupEntity) grupsUsuariAnalizar.poll()) !=null) {
			if (!totsGrups.containsKey(grupActual.getId()) ) {
				// Nuevo
				totsGrups.put(grupActual.getId(), grupActual);
				// Su padre
				GrupEntity pare = grupActual.getPare(); 
				if (pare!=null && !totsGrups.containsKey(pare.getId()) ) {
					// Padre no analizado, lo añadimos para procesarlo
					grupsUsuariAnalizar.add(pare);
				}
			}
		}
		
		
		// Ya tenemos los grupos, obtenemos los roles de estos grupos
		for (Iterator it = totsGrups.entrySet().iterator(); it.hasNext(); ) {
			Entry entryGrup = (Entry) it.next();
			GrupEntity grup = (GrupEntity) entryGrup.getValue();
			// Sólo nos interesan los roles otorgados (los de usuario del grupo
			// ya los tenemos a partir del usuario)
			Collection rolsAtorgats = grup.getRolsOtorgatsGrup();
			for (Iterator ait = rolsAtorgats.iterator(); ait.hasNext(); ) {
				RolsGrupEntity rge = (RolsGrupEntity)ait.next();
				RolEntity rol = rge.getRolOtorgat();
				totRol.put(rol.getId(),rol);
				Rol r = getRolEntityDao().toRol(rol);
				jerarquiaRolsUsuari.add(r); //Añadimos el rol otorgado al grupo				
			}
		}

 		// Montamos la jerarquía de roles
		if (totRol != null) {
			// Añadimos todos los roles del usuario 
			// Usamos la estructura de cola (LIFO)
			LinkedList rolesAnalizar = new LinkedList();
			for (Iterator it = totRol.entrySet().iterator(); it.hasNext(); ) {
				Entry es = (Entry) it.next(); 
				RolEntity rol = (RolEntity) es.getValue(); //El rol
				rolesAnalizar.add(rol);
			}
			
			// Aquí metemos toda la jerarquía (su hash es el id)
			HashMap jerarquiaRolesAnalizar = new HashMap();
			
			RolEntity rolActual = null;
			
			// Recorremos cola hasta que no queden elementos
			while ( (rolActual = (RolEntity) rolesAnalizar.poll()) !=null) {
				// Guardamos el rolActual (si no se ha analizado ya)
				if (!jerarquiaRolesAnalizar.containsKey(rolActual.getId())) {
					// Si no lo tiene, lo añadimos y buscamos dónde está contenido
					jerarquiaRolesAnalizar.put(rolActual.getId(), rolActual);
					// Obtenemos los roles que tiene otorgados (están contenidos en mi)
					Collection rolsSocContingut = rolActual.getRolAssociacioRolSocContenidor();
					for (Iterator it = rolsSocContingut.iterator(); it.hasNext();) {
						RolAssociacioRolEntity rar = (RolAssociacioRolEntity) it.next();
						// Analizamos el tipo de Dominio del rol otorgado
						boolean afegir = false;
						// Si la asociación no tiene dominio la agregamos (puede ser nulo)
						if (TipusDomini.SENSE_DOMINI.equals(rar.getTipusDomini()) || rar.getTipusDomini()==null)
							afegir = true;
						else {
							// En el caso de que la asociación tenga dominio, hay que mirar el rol contingut (atorgado)
							// Y comprobar en los roles del USUARIO si tiene el mismo dominio que el rol atorgat

							// Si no tiene valor de dominio (QUALQUE_VALOR_DOMINI), las entidades referenciadas son nulas:
							if (rar.getAplicacioDomini()==null && rar.getGrupDomini()==null && rar.getValorDominiAplicacio()==null)
								afegir = true;
							else {
								// Recorremos los roles del usuario hasta encontrar el rol que corresponda con la asociación:
								// Si es sin valor de dominio, lo agregamos
								for (RolAccountEntity ruEntity: rolsUsuaris ) {
									if (ruEntity.getRol().getId().equals(rar.getRolContenidor().getId())) { // Es el rol correspondiente 
										String tipusDominiRolUsuari = ruEntity.getTipusDomini();
										if (TipusDomini.GRUPS.equals(tipusDominiRolUsuari) || TipusDomini.GRUPS_USUARI.equals(tipusDominiRolUsuari) ) {
											// Grups
											if (rar.getGrupDomini()==null 
													|| (rar.getGrupDomini()!=null && 
															rar.getGrupDomini().getId().equals(ruEntity.getGrup().getId())) )
													afegir = true;
										} else if (TipusDomini.APLICACIONS.equals(tipusDominiRolUsuari)) {
											// Aplicacions
											if (rar.getAplicacioDomini() == null
													|| (rar.getAplicacioDomini() != null && 
															rar.getAplicacioDomini().getId().equals(ruEntity.getAplicacioAdministrada().getId())))
												afegir = true;
										} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDominiRolUsuari)) {
											// Valor de domini de aplicació
											if (rar.getValorDominiAplicacio()==null 
													|| (rar.getValorDominiAplicacio()!=null && 
															rar.getValorDominiAplicacio().getId().equals(ruEntity.getValorDominiAplicacio().getId())))
												afegir = true;
										} else if (TipusDomini.SENSE_DOMINI.equals(rar.getTipusDomini()) || rar.getTipusDomini()==null) {
											// incloem ací els de SENSE_DOMINI (l'usuari ha de tindre el rol concedit..)
											afegir = true; // No hem de comparar res
										}
									}
								}
							}
						}

						
						if (afegir) {
							// Añadimos el rol contenido al listado:
							Rol rc = getRolEntityDao().toRol(rar.getRolContingut());
							jerarquiaRolsUsuari.add(rc);
							
							RolEntity contingut = rar.getRolContingut();
							// ¿Hemos analizado ya el rol que contenemos?
							if (!jerarquiaRolesAnalizar.containsKey(contingut.getId())) {
								// Nuevo, lo añadimos a la lista de roles a analizar
								rolesAnalizar.add(contingut);							
							}
						}
					}
				}
			}
			
		}
		
		return jerarquiaRolsUsuari; //Tots de tipus RolEntity		
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
		
	protected String[] handleVerificarIdentitatUsuari(String nom,
			String llinatge1, String llinatge2, String document)
			throws Exception {
		String tipusDocument = "DNI"; //$NON-NLS-1$
		if (document != null
				&& (document.startsWith("X") ||  //$NON-NLS-1$
						document.startsWith("Y") ||  //$NON-NLS-1$
						document.startsWith("Z"))) { //$NON-NLS-1$
			tipusDocument = "NIE"; //$NON-NLS-1$
		}
		String numSolicitud = getUsuariEntityDao().getSeguentNumSolicitudVerificarIdentitatUsuari();
		return handleVerificarIdentitatUsuari(nom, llinatge1, llinatge2, tipusDocument, document, numSolicitud);
	}
	

	/**
	 * Obté la configuració del servei de verificació d'identitat de l'usuari 
	 * o null si no és actiu (per estalviar ús de la seqüència)
	 * @return
	 */
	private Configuracio getConfiguracioServeiVerificacioIndentitatUsuari() {
		try {
			return getConfiguracioService().findParametreByCodiAndCodiXarxa(
					"webserviceSVDI", null); //$NON-NLS-1$
		} catch (Throwable th) {

		}
		return null;
	}
	
	/**
	 * Obté la configuració del servei de comprovació d'identitat de l'usuari 
	 * o null si no és actiu (per estalviar ús de la seqüència)
	 * @return
	 */
	private Configuracio getConfiguracioServeiComprovacioIndentitatUsuari() {
		try {
			return getConfiguracioService().findParametreByCodiAndCodiXarxa(
					"webserviceSCDI", null); //$NON-NLS-1$
		} catch (Throwable th) {

		}
		return null;
	}

	protected String[] handleVerificarIdentitatUsuari(String nom,
			String llinatge1, String llinatge2, String tipusDocument,
			String document, String numSolicitud) throws Exception {
		
		String urlWS =""; //$NON-NLS-1$
		
		try {
			
			if (document == null
					|| (document != null && "".equals(document.trim()))) //$NON-NLS-1$
				return new String[]{"0003",""}; // Si no té nif/nie no es fa la comprovació (0003 = OK) //$NON-NLS-1$ //$NON-NLS-2$
		
			String resultat[] = new String[2];
	
			String identificador = "S0711001H"; //$NON-NLS-1$
			
			Configuracio configWS = getConfiguracioService().findParametreByCodiAndCodiXarxa("webserviceSVDI", null); //$NON-NLS-1$
			//Valor proves: https://intermediacionpp.redsara.es/peticionSVDI/services/VerificacionIdentidad
			if (configWS == null || (configWS!=null && configWS.getValor()==null)) {
				/*throw new SeyconException*/ System.err.println(Messages.getString("UsuariServiceImpl.SVDISeyconParamNotDefinied"));  //$NON-NLS-1$
				return new String[]{"0003",""}; // Eixim sense donar error: no s'ha establert el paràmetre //$NON-NLS-1$ //$NON-NLS-2$
			}
			urlWS = configWS.getValor();
	
			java.net.URL endpoint = new java.net.URL(urlWS);
	
			EngineConfiguration config = new FileProvider("/"+this.getClass().getPackage().getName().replaceAll("[.]","/")+"/ws_sara_client_config.wsdd"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			VerificacionIdentidadLocator locator = new VerificacionIdentidadLocator(
					config);
			VerificacionIdentidadSoapBindingStub verificacionStub = new VerificacionIdentidadSoapBindingStub(
					endpoint, locator);
			
			// Obtenemos el timeout (si está establecido)
			// Si no tiene valor, se deja el por defecto (60 segundos?)
			Configuracio configWSTimeout = getConfiguracioService().findParametreByCodiAndCodiXarxa("webserviceSVDI_timeout", null); //$NON-NLS-1$
			if (configWSTimeout != null && configWSTimeout.getValor()!=null) {
				int timeout = Integer.parseInt(configWSTimeout.getValor());
				verificacionStub.setTimeout(timeout*1000);//en milisegundos
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
			SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //$NON-NLS-1$
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
					// transResposta és un array de solicituds, però com només hi ha
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
					resultat[1] = Messages.getString("UsuariServiceImpl.UnknowError"); //$NON-NLS-1$
					return resultat;
				}
			}
		} catch (SeyconException ex) { // Capturem aquesta per mostrar error de configuració
			throw ex;
		} catch (Exception e) {
			throw new InternalErrorException(String.format(Messages.getString("UsuariServiceImpl.ErrorID"), e.getMessage()), e); //$NON-NLS-1$
		}
	}

	/* 
	 * UsuariSEU: guarda informació de l'usuari al programa SEU 
	 */
	protected UsuariSEU handleUpdate(UsuariSEU usuariSEU) throws Exception {
		UsuariSEUEntity entity = getUsuariSEUEntityDao().usuariSEUToEntity(usuariSEU);
		getUsuariSEUEntityDao().update(entity);
		return getUsuariSEUEntityDao().toUsuariSEU(entity);		
	}

	/* 
	 * UsuariSEU: guarda informació de l'usuari al programa SEU 
	 */
	protected UsuariSEU handleCreate(UsuariSEU usuari) throws Exception {
		UsuariSEUEntity entity = getUsuariSEUEntityDao().usuariSEUToEntity(usuari);
		getUsuariSEUEntityDao().create(entity);
		return getUsuariSEUEntityDao().toUsuariSEU(entity);		

	}

	/* 
	 * UsuariSEU: guarda informació de l'usuari al programa SEU 
	 */
	protected UsuariSEU handleFindUsuariSEUByCodiUsuari(String codiUsuari)
			throws Exception {
		UsuariSEUEntity entity = getUsuariSEUEntityDao().findByCodiUsuari(codiUsuari);
		if (entity != null)
			return getUsuariSEUEntityDao().toUsuariSEU(entity);
		return null;
	}

	protected java.util.Collection<es.caib.seycon.ng.comu.EstatContrasenya> handleGetContrasenyesTipusUsuari(Date dataInici,
			Date dataFi, String tipusUsuari) throws Exception {
	    TipusUsuariEntity tipus = getTipusUsuariEntityDao().findByCodi(tipusUsuari);
	    if (tipus == null)
	        throw new IllegalArgumentException(String.format(Messages.getString("UsuariServiceImpl.InvalidUserType"), tipusUsuari)); //$NON-NLS-1$
	    return getInternalPasswordService().getExpiredPasswords(dataInici, dataFi, tipus);
	    
	}

	protected String[] handleConsultarIdentitatUsuari(String document)
			throws Exception {
		String tipusDocument = "DNI"; //$NON-NLS-1$
		if (document != null
				&& (document.startsWith("X") ||  //$NON-NLS-1$
						document.startsWith("Y") ||  //$NON-NLS-1$
						document.startsWith("Z"))) { //$NON-NLS-1$
			tipusDocument = "NIE"; //$NON-NLS-1$
		}
		String numSolicitud = getUsuariEntityDao().getSeguentNumSolicitudVerificarIdentitatUsuari();
		return handleConsultarIdentitatUsuari(tipusDocument, document, numSolicitud);
	}

	protected String[] handleConsultarIdentitatUsuari(String tipusDocument,
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

			Configuracio configWS = getConfiguracioServeiComprovacioIndentitatUsuari();
			// Valor proves:
			// https://intermediacionpp.redsara.es/peticionSVDI/services/VerificacionIdentidad
			if (configWS == null
					|| (configWS != null && configWS.getValor() == null)) {
				/*throw new SeyconException */System.err
						.println(Messages.getString("UsuariServiceImpl.SCDISeyconParamNotDefinied"));  //$NON-NLS-1$
				return new String[] { "9999", Messages.getString("UsuariServiceImpl.NoEstablishedWebServiceSCDIParam"), "", "", "" }; // no s'ha establert el paràmetre //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			}
			urlWS = configWS.getValor();

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
			solicitante.setConsentimiento(es.map2.www.scsp.esquemas.V2.peticion.Consentimiento.Si);
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
			es.map2.www.scsp.esquemas.V2.peticion.Peticion peticio = new es.map2.www.scsp.esquemas.V2.peticion.Peticion(atributs, solicitudes);

			// Cridar a l'operació VERIFICAR IDENTITAT
			es.map2.www.scsp.esquemas.V2.respuesta.Respuesta resposta = consultaStub.consultarIdentidad(peticio);

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
						resultat[5] = transResposta[0].getDatosGenericos().getTitular().getNombreCompleto();
					}
					return resultat;
				} else {
					resultat[0] = "999"; //$NON-NLS-1$
					resultat[1] = Messages.getString("UsuariServiceImpl.UnknowError"); //$NON-NLS-1$
					return resultat;
				}
			}
		} catch (SeyconException ex) { // Capturem aquesta per mostrar error de
										// configuració
			throw ex;
		} catch (Exception e) {
			throw new InternalErrorException(String.format(Messages.getString("UsuariServiceImpl.ErrorID"), e.getMessage()), e); //$NON-NLS-1$
		}
	}

	protected Usuari handleAltaUsuariMaquina(String nom, String descripcio)
			throws Exception {
	    if (true)
	        throw new RuntimeException (Messages.getString("UsuariServiceImpl.NotImplementedMessage")); //$NON-NLS-1$

		/* Es crea l'usuari */
		Usuari usuariMaquina = new Usuari();
		
		// Li assignem el següent codi de màquina lliure
		usuariMaquina.setCodi(getUsuariEntityDao()
				.getSeguentCodiMaquina());
		
		// dades bàsiques:
		usuariMaquina.setNom(nom);
		usuariMaquina.setPrimerLlinatge(descripcio);
		usuariMaquina.setTipusUsuari("H"); //Tipus d'usuari màquina //$NON-NLS-1$
		
		/* se almacena la fecha de creación */
		usuariMaquina.setDataCreacioUsuari(GregorianCalendar.getInstance());
		/* se almacena el usuario que lo crea */
		usuariMaquina.setUsuariCreacio(Security.getCurrentAccount());
		
		// Li assignem certs paràmetres...
		usuariMaquina.setActiu(new Boolean(true));
		usuariMaquina.setMultiSessio(new Boolean(false));
		// No en té efecte i ja no existeix al VO
		//usuariMaquina.setContrasenyaCaducada(new Boolean(true));

		// Establim grup primari i servidors de correu.. 
		usuariMaquina.setCodiGrupPrimari("nul"); //TODO: li hem d'assingar un altre grup primari? //$NON-NLS-1$

		usuariMaquina.setServidorCorreu("nul"); //$NON-NLS-1$
		usuariMaquina.setServidorHome("nul"); //$NON-NLS-1$
		usuariMaquina.setServidorPerfil("nul");  //$NON-NLS-1$

		UsuariEntity usuariEntity = getUsuariEntityDao().usuariToEntity(usuariMaquina);


		getUsuariEntityDao().create(usuariEntity);
		
		getRuleEvaluatorService().applyRules(usuariEntity);

		return getUsuariEntityDao().toUsuari(usuariEntity);		
	}
	
	
	protected String handleCanviPasswordUsuariMaquina(String codiUsuari, String codiDominiContrasenyes)
			throws Exception {
	    throw new RuntimeException (Messages.getString("UsuariServiceImpl.NotImplementedMessage")); //$NON-NLS-1$

	}	
	


	protected void handlePropagaContrasenya(String codiUsuari,
			String contrasenya) throws Exception {
		throw new RuntimeException (Messages.getString("UsuariServiceImpl.NotImplementedMessage")); //$NON-NLS-1$
		
	}

	protected String handleGeneraPasswordRandom() throws Exception {
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

	protected Usuari handleUpdateDadesBasiquesUsuari(Usuari usuari)
			throws Exception {
		
		if (usuari.getId()==null || usuari.getCodi()==null) {
			throw new SeyconException (Messages.getString("UsuariServiceImpl.UserNotFounded")); //$NON-NLS-1$
		}
		UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(usuari.getCodi());
		
		if (usuariEntity==null)
			throw new SeyconException (Messages.getString("UsuariServiceImpl.UserNotFounded")); //$NON-NLS-1$
		
		usuariEntity.setNom(usuari.getNom());
		usuariEntity.setPrimerLlinatge(usuari.getPrimerLlinatge());
		usuariEntity.setSegonLlinatge(usuari.getSegonLlinatge());
		
		// Marquem que l'usuari ha estat modificat
		usuariEntity.setUsuariDarreraModificacio(getPrincipal()!=null ? getPrincipal().getName() : "SEYCON"); //$NON-NLS-1$
		usuariEntity.setDataDarreraModificacio(GregorianCalendar.getInstance().getTime());
		
		// ací s'audita el canvi
		getUsuariEntityDao().update(usuariEntity);

		return getUsuariEntityDao().toUsuari(usuariEntity);
	}
	
	private TaskInstance iniciaTasca(String nomProces, Object[][] parametres, String codiUsuari) throws Exception {
		BpmEngine engine = getBpmEngine();
		JbpmContext ctx = engine.getContext();
		try {
			ProcessDefinition def = ctx.getGraphSession().findLatestProcessDefinition(nomProces);
			if (def == null)
				throw new Exception(String.format(Messages.getString("UsuariServiceImpl.NotExistsProcess"), nomProces)); //$NON-NLS-1$
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
	
	/*private void cancelaTasquesProces (String nomProces, Long idProces) throws Exception {
		BpmEngineLocal engine = null;
		try {
			engine = BpmApplication.getEngine();
			JbpmContext ctx = engine.getContext();
			//-			engine = BpmApplication.getEngine();

			GraphSession gs = ctx.getGraphSession();
			List defs = gs.findAllProcessDefinitionVersions(nomProces);
			for (Iterator it = defs.iterator(); it.hasNext();) {
				ProcessDefinition def = (ProcessDefinition) it.next();
				List procs = gs.findProcessInstances(def.getId());
				for (Iterator it2 = procs.iterator(); it2.hasNext();) {
					ProcessInstance pi = (ProcessInstance) it2.next();
					if (!pi.hasEnded() && idProces!=null && pi.getId() != idProces) {
						//System.out.println ("Stopping procés '" + nomProces + "' amb id=" + pi.getId());
						pi.getContextInstance().setVariable("end", "end");
						pi.end();
						ctx.save(pi);
					}
				}
			}			
		} finally {
			if (engine != null)
				engine.remove();
		}
		
		
	}*/
	
	
	protected String handleCreaNouProcesUsuari(String nomProces, String codiUsuari, boolean canviaAProces) throws Exception {
		// Processos que es llancen des de la finestra d'usuaris

		TaskInstance task = null;

		// Cerquem l'usuari
		UsuariEntity usuariE = getUsuariEntityDao().findByCodi(codiUsuari);
		if (usuariE == null)
			throw new Exception(String.format(Messages.getString("UsuariServiceImpl.UserCodNotFounded"), codiUsuari)); //$NON-NLS-1$

		List defs = getBpmEngine().findProcessDefinitions("initiator",true); //$NON-NLS-1$
		boolean found = false;
		for (Iterator it = defs.iterator(); !found && it.hasNext();)
		{
			es.caib.bpm.vo.ProcessDefinition def = (es.caib.bpm.vo.ProcessDefinition) it.next();
			if (def.getName().equals(nomProces))
				found = true;
		}
		if (!found)
			throw new Exception(String.format(Messages.getString("UsuariServiceImpl.ProcessCallNotAllowed"), nomProces)); //$NON-NLS-1$

		Object[][] params = { { "autoStarted", new Integer(1) }, { "userName", codiUsuari } }; //$NON-NLS-1$ //$NON-NLS-2$
		task = iniciaTasca(nomProces, params, codiUsuari);
			

		if (task == null)
			throw new Exception(String.format(Messages.getString("UsuariServiceImpl.TaskNotLaunched"), nomProces)); //$NON-NLS-1$

		// Aqui task !=null
		if (canviaAProces) {
			// tornem la tasca perquè es mostre la seua finestra
			return "/wf/task.zul?id=" + task.getId(); //$NON-NLS-1$
		}

		return null;
	}

	protected Collection<ProcesWF> handleObteLlistaProcessosWFUsuari() throws Exception {
		BpmEngine engine = getBpmEngine();
		// Aqui tenim els processos que ens interesa que puga
		// iniciar l'usuari des de la finestra d'usuaris
		HashMap<String, ProcesWF> hProcessosUsuari = new HashMap();

		LinkedList<ProcesWF> resultat = new LinkedList<ProcesWF>();
		// Afegim una buida al llistat
		resultat.add(new ProcesWF(null, null));
		try {
			// Obtenim els processos que l'usuari actual pot iniciar (i són
			// habilitats)
			List<es.caib.bpm.vo.ProcessDefinition> processosUsuari = engine.findInitiatorProcessDefinitions();
			if (processosUsuari != null)
				for (Iterator<es.caib.bpm.vo.ProcessDefinition> it = processosUsuari.iterator(); it.hasNext();) 
				{
					es.caib.bpm.vo.ProcessDefinition def = it.next();
					if (def != null && def.getName() != null &&
						"user".equals(def.getAppliesTo()) && def.isEnabled()) //$NON-NLS-1$
						resultat.add(new ProcesWF (def.getName(), def.getName()));
				}

		} finally {
		}

		return resultat;

	}

	@Override
	protected UsuariWFProcess handleCreate(UsuariWFProcess usuariWFProces) throws Exception {
		UsuariWFProcessEntity entity = getUsuariWFProcessEntityDao().usuariWFProcessToEntity(usuariWFProces);
		getUsuariWFProcessEntityDao().create(entity);
		return getUsuariWFProcessEntityDao().toUsuariWFProcess(entity);		
	}

	@Override
	protected UsuariWFProcess handleUpdate(UsuariWFProcess usuariWFProces) throws Exception {
		UsuariWFProcessEntity entity = getUsuariWFProcessEntityDao().usuariWFProcessToEntity(usuariWFProces);
		getUsuariWFProcessEntityDao().update(entity);
		return getUsuariWFProcessEntityDao().toUsuariWFProcess(entity);		
	}

	@Override
	protected void handleDelete(UsuariWFProcess usuariWFProces) throws Exception {
		UsuariWFProcessEntity entity = getUsuariWFProcessEntityDao().usuariWFProcessToEntity(usuariWFProces);
		getUsuariWFProcessEntityDao().remove(entity);
	}

	@Override
	protected Collection<UsuariWFProcess> handleFindProcessosWFUsuariByCodiUsuari(String codiUsuari) throws Exception {
		Collection<UsuariWFProcessEntity> usuproc = getUsuariWFProcessEntityDao().findByCodiUsuari(codiUsuari);
		return getUsuariWFProcessEntityDao().toUsuariWFProcessList(usuproc);
	}

	@Override
	protected Collection<UsuariWFProcess> handleFindProcessosWFUsuariByIdProces(Long idProces) throws Exception {
		Collection<UsuariWFProcessEntity> usuproc = getUsuariWFProcessEntityDao().findByIdProces(idProces);
		return getUsuariWFProcessEntityDao().toUsuariWFProcessList(usuproc);
	}

	@Override
	protected Collection<UsuariWFProcess> handleFindProcessosWFUsuariByNIFUsuari(String nifUsuari) throws Exception {
		Collection<UsuariWFProcessEntity> usuproc = getUsuariWFProcessEntityDao().findByNifUsuari(nifUsuari);
		return getUsuariWFProcessEntityDao().toUsuariWFProcessList(usuproc);
	}

	@Override
	protected Collection<es.caib.bpm.vo.ProcessInstance> handleFindProcessInstanceWFUsuariByCodiUsuari(String codiUsuari)
			throws Exception {
		Collection<es.caib.bpm.vo.ProcessInstance> processos = new LinkedList<es.caib.bpm.vo.ProcessInstance>();
		// Cerquem els processos de l'usuari
		Collection<UsuariWFProcessEntity> usuproc = getUsuariWFProcessEntityDao().findByCodiUsuari(codiUsuari);
		if (usuproc != null) {
			for (UsuariWFProcessEntity up : usuproc) {
				try {
    				es.caib.bpm.vo.ProcessInstance pi = getBpmEngine().getProcess(up.getIdProces());
    				if (pi != null) {
    					processos.add(pi);
    				}
				} catch (Exception e)
				{
					
				}
			}
		}

		return processos;
	}

    @Override
    protected Usuari handleGetUserInfo(String user)
            throws Exception {
        UsuariEntityDao dao = getUsuariEntityDao();

        UsuariEntity entity = null;

        entity = dao.findByCodi(user);
        if (entity == null)
            throw new UnknownUserException(user);
        return dao.toUsuari(entity);
    }


    @Override
    protected Collection<Grup> handleGetUserGroups(long userId) throws Exception {
        UsuariEntityDao dao = getUsuariEntityDao();
        DispatcherEntity dispatcher = null;

        UsuariEntity entity = dao.load(userId);
        if (entity == null)
            throw new UnknownUserException(Long.toString(userId));

        GrupEntityDao grupDao = getGrupEntityDao();
        LinkedList<Grup> grups = new LinkedList<Grup>();
        grups.add(grupDao.toGrup(entity.getGrupPrimari()));
        for (Iterator<UsuariGrupEntity> it = entity.getGrupsSecundaris().iterator(); it.hasNext();) {
            UsuariGrupEntity uge = it.next();
            grups.add(grupDao.toGrup(uge.getGrup()));
        }
        return grups;
    }

    @Override
    protected Collection<Grup> handleGetUserGroupsHierarchy(long userId)
            throws Exception {
        UsuariEntityDao dao = getUsuariEntityDao();
        UsuariEntity entity = dao.load(userId);
        if (entity == null)
            throw new UnknownUserException(Long.toString(userId));
        GrupEntityDao grupDao = getGrupEntityDao();
        LinkedList<GrupEntity> grups = new LinkedList<GrupEntity>();
        HashMap<String, Grup> result = new HashMap<String, Grup>();

        grups.add(entity.getGrupPrimari());
        for (Iterator<UsuariGrupEntity> it = entity.getGrupsSecundaris().iterator(); it.hasNext();) {
            UsuariGrupEntity uge = it.next();
            grups.add(uge.getGrup());
        }

        while (!grups.isEmpty()) {
            GrupEntity head = grups.getFirst();
            if (result.get(head.getCodi()) == null) {
                result.put(head.getCodi(), grupDao.toGrup(head));
                if (head.getPare() != null)
                    grups.add(head.getPare());
            }
            grups.removeFirst();
        }
        LinkedList<Grup> grupsList = new LinkedList<Grup>();
        grupsList.addAll(result.values());
        return grupsList;
    }

    private Collection<RolGrant> getUserRoles(long userId, boolean explicit)
            throws Exception {
        UsuariEntityDao dao = getUsuariEntityDao();
        UsuariEntity entity = dao.load(userId);
        if (entity == null)
            throw new UnknownUserException(Long.toString(userId));

        // Recuperar rols explicits de l'usuari
        List<RolAccountEntity> originalGrants = getRolAccountEntityDao().findByCodiUsuari(entity.getCodi());
        List<RolGrant> rols = getRolAccountEntityDao().toRolGrantList(originalGrants);

        // Recuprear rols implicits dels rols
        if (!explicit) {
        	for (RolAccountEntity rau: originalGrants)
        	{
        		populateRolRoles (rau.getRol(), rols);
        	}
            populateGroupRoles(entity.getGrupPrimari(), rols);
            for (Iterator<UsuariGrupEntity> it = entity.getGrupsSecundaris().iterator(); it
                    .hasNext();) {
                UsuariGrupEntity ug = it.next();
                populateGroupRoles(ug.getGrup(), rols);
            }
        }

        return rols;

    }

    private void populateGroupRoles(GrupEntity grup,
            List<RolGrant> rols) {
        RolsGrupEntityDao dao = getRolsGrupEntityDao();
        for (Iterator<RolsGrupEntity> it = grup.getRolsOtorgatsGrup().iterator(); it.hasNext();) {
            RolsGrupEntity rg = it.next();
            rols.add(dao.toRolGrant(rg));
            populateRolRoles(rg.getRolOtorgat(), rols);
        }
    }

    private void populateRolRoles(RolEntity rol, List<RolGrant> rols) {
        RolAssociacioRolEntityDao dao = getRolAssociacioRolEntityDao();
        for (Iterator<RolAssociacioRolEntity> it = rol.getRolAssociacioRolSocContenidor()
                .iterator(); it.hasNext();) {
            RolAssociacioRolEntity rarEntity = it.next();
            // Falta filtrar
            RolGrant rg = dao.toRolGrant(rarEntity);
            rols.add(rg);
            populateRolRoles(rarEntity.getRolContingut(), rols);
        }
    }

    @Override
    protected Collection<RolGrant> handleGetUserRoles(long userId)
            throws Exception {
        return getUserRoles(userId, false);
    }


    @Override
    protected Collection<RolGrant> handleGetUserExplicitRoles(long userId)
            throws Exception {
        return getUserRoles(userId, true);
    }

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.UsuariServiceBase#handleGetCurrentUsuari()
	 */
	@Override
	protected Usuari handleGetCurrentUsuari () throws Exception
	{
		Principal p = Security.getPrincipal();
		if (p == null)
			return null;
		
		String dispatcherName = getInternalPasswordService().getDefaultDispatcher();
		Account acc = getAccountService().findAccount(p.getName(), dispatcherName);
		if (acc == null)
			return  null;
		else if (acc instanceof UserAccount)
			return findUsuariByCodiUsuari(((UserAccount)acc).getUser());
		else
			return null;
	}

    private String getServerList() throws InternalErrorException, SQLException, NamingException {
        ConfiguracioService configuracioService = getConfiguracioService(); 
        Configuracio parametre = configuracioService.findParametreByCodiAndCodiXarxa(
                "seycon.server.list", null); //$NON-NLS-1$
        return parametre.getValor();
    }

    private String getServerPort() throws InternalErrorException, SQLException, NamingException {
        ConfiguracioService configuracioService = getConfiguracioService();
        Configuracio parametre = configuracioService.findParametreByCodiAndCodiXarxa(
                "seycon.https.port", null); //$NON-NLS-1$
        return parametre.getValor();
    }

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.UsuariServiceBase#handleGetMazingerRules(java.lang.String)
	 */
	@Override
	protected byte[] handleGetMazingerRules (String user) throws Exception
	{
        String name = getServerList();
        String port = getServerPort();
        LinkedList<SeyconServerInfo> serversInfo = new LinkedList<SeyconServerInfo>();
        Config.configureClient(name, port);
        ServerEntityDao sedao = getServerEntityDao();
        Exception lastException = new InternalErrorException(Messages.getString("UsuariServiceImpl.NoSyncserverDefined")); //$NON-NLS-1$
        for (ServerEntity server: sedao.loadAll())
        {
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

    private RemoteServiceLocator createRemoteServiceLocator(ServerEntity server) throws IOException, InternalErrorException {
        RemoteServiceLocator rsl = new RemoteServiceLocator(server.getNom());
        rsl.setAuthToken(server.getAuth());
        return rsl;
    }


    private void addString (String value, String hqlAttribute, String joinArray[], List<String> joins, List<String> queries, List<Parameter> params)
    {
		String param = "param"+params.size(); //$NON-NLS-1$
		addString2(value, param, hqlAttribute+ " like :"+param, joinArray, joins, queries, params); //$NON-NLS-1$
    }
    
    private void addString2 (String value, String param, String hqlCondition, String joinArray[], List<String> joins, List<String> queries, List<Parameter> params)
    {
		if (value != null && value.trim().compareTo("") != 0 //$NON-NLS-1$
						&& value.trim().compareTo("%") != 0) { //$NON-NLS-1$
			value = value.trim();
			queries.add (hqlCondition);
			params.add(new Parameter (param, value));
			if (joinArray != null)
				for (String join: joinArray)
				{
					if (! joins.contains(join))
						joins.add(join);
				}
			
		}
    }
    
    private void addDateRange (String value, String hqlAttribute, String joinArray[], List<String> joins, List<String> queries, List<Parameter> params)
    {
		if (value != null && value.trim().compareTo("") != 0 //$NON-NLS-1$
						&& value.trim().compareTo("%") != 0) { //$NON-NLS-1$
			value = value.trim();
			LimitDates limitDates = DateUtils.getLimitDatesFromQuery(value);
			if (limitDates.getMaximum() != null)
			{
				String param = "param"+params.size(); //$NON-NLS-1$
				queries.add (hqlAttribute+" <= :"+param); //$NON-NLS-1$
				params.add(new Parameter (param, limitDates.getMaximum()));
			}
    	
			if (limitDates.getMinimum() != null)
			{
				String param = "param"+params.size(); //$NON-NLS-1$
				queries.add (hqlAttribute+" >= :"+param); //$NON-NLS-1$
				params.add(new Parameter (param, limitDates.getMinimum()));
			}
			if (joinArray != null)
				for (String join: joinArray)
				{
					if (! joins.contains(join))
						joins.add(join);
				}
		}
    }
    
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.UsuariServiceBase#handleFindUserByCriteria(es.caib.seycon.ng.servei.UsuariCriteria)
	 */
	@Override
	protected Collection<Usuari> handleFindUserByCriteria (UsuariCriteria criteria)
					throws Exception
	{
		int limitResults = Integer.MAX_VALUE;
		try {
			limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		} catch (NumberFormatException e) {}

		LinkedList<String> joins = new LinkedList<String> ();
		List<String> queries = new LinkedList<String>();
		List<Parameter> params = new LinkedList<Parameter>();

		addDateRange (criteria.getDataCreacioUsuari(), "usuari.dataCreacio", null, joins, queries, params); //$NON-NLS-1$
		addDateRange (criteria.getDataDarreraModificacioUsuari(), "usuari.dataModificacio", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getAccountName(), "account.name", new String [] {"inner join usuari.accounts as accounts", "inner join accounts.account as account"}, joins, queries, params); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		addString(criteria.getAccountSystem(), "dispatcher.codi", new String [] { //$NON-NLS-1$
									"inner join usuari.accounts as accounts",  //$NON-NLS-1$
									"inner join accounts.account as account", //$NON-NLS-1$
									"inner join account.dispatcher as dispatcher"}, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getCodi(), "usuari.codi", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getComentari(), "usuari.comentari", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getCodiGrupPrimari(), "grup.codi", new String[] {"left outer join usuari.grupPrimari as grup"}, joins, queries, params); //$NON-NLS-1$ //$NON-NLS-2$
		addString(criteria.getNom(), "usuari.nom", null, joins, queries, params); //$NON-NLS-1$

		addString(criteria.getDescripcioGrupPrimari(), "grup.codi", new String[] {"inner join usuari.grupPrimari as grup"}, joins, queries, params); //$NON-NLS-1$ //$NON-NLS-2$
		addString(criteria.getNIF(), "dadaUsuari.valorDada",  //$NON-NLS-1$
						new String[] {
						"inner join usuari.dadaUsuari as dadaUsuari", //$NON-NLS-1$
						"inner join dadaUsuari.tipusDada as tipusDada with tipusDada.codi='NIF'"}, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getTelefon(), "dadaUsuari2.valorDada",  //$NON-NLS-1$
						new String[] {
						"inner join usuari.dadaUsuari as dadaUsuari2", //$NON-NLS-1$
						"inner join dadaUsuari2.tipusDada as tipusDada2 with tipusDada2.codi='PHONE'"}, joins, queries, params); //$NON-NLS-1$

		addString(criteria.getNomCurt(), "usuari.nomCurt", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getPrimerLlinatge(), "usuari.primerLlinatge", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getSegonLlinatge(), "usuari.segonLlinatge", null, joins, queries, params); //$NON-NLS-1$
		
		addString(criteria.getRolName(), "rol.nom", new String[] { //$NON-NLS-1$
			"inner join usuari.accounts as accounts",  //$NON-NLS-1$
			"inner join accounts.account as account", //$NON-NLS-1$
			"inner join account.roles as roles", //$NON-NLS-1$
			"inner join roles.rol as rol" //$NON-NLS-1$
		}, joins, queries, params);
		
		addString(criteria.getRolSystem(), "dispatcher2.codi", new String[] { //$NON-NLS-1$
			"inner join usuari.accounts as accounts",  //$NON-NLS-1$
			"inner join accounts.account as account", //$NON-NLS-1$
			"inner join account.roles as roles", //$NON-NLS-1$
			"inner join roles.rol as rol", //$NON-NLS-1$
			"inner join rol.baseDeDades as dispatcher2" //$NON-NLS-1$
		}, joins, queries, params);

		addString(criteria.getServidorCorreu(), "usuari.servidorCorreu.nom", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getServidorHome(), "usuari.servidorOfimatic.nom", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getServidorPerfil(), "usuari.servidorPerfil.nom", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getTipusUsuari(), "usuari.tipusUsuari.codi", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getUsuariCreacio(), "usuari.usuariCreacio", null, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getUsuariDarreraModificacio(), "usuari.usuariDarreraModificacio", null, joins, queries, params); //$NON-NLS-1$
		addString2(criteria.getSecondaryGroup(), "grupSecundari",  //$NON-NLS-1$
				"(grup.codi like :grupSecundari or grupB.codi like :grupSecundari)", //$NON-NLS-1$
				new String[] {"left outer join usuari.grupPrimari as grup", //$NON-NLS-1$
							  "left outer join usuari.grupsSecundaris as grupsSecundaris", //$NON-NLS-1$
							  "left outer join grupsSecundaris.grup as grupB" }, joins, queries, params); //$NON-NLS-1$
		addString(criteria.getDominiCorreu(), "mailDomain.codi", //$NON-NLS-1$
						new String [] { "left outer join usuari.dominiCorreu as mailDomain"}, //$NON-NLS-1$
						joins, queries, params);
		if (criteria.getActiu() != null)
			addString(criteria.getActiu().booleanValue()?"S":"N", "usuari.actiu", null, joins, queries, params); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		StringBuffer sb = new StringBuffer ("select usuari from es.caib.seycon.ng.model.UsuariEntity as usuari"); //$NON-NLS-1$
		for (String join: joins)
		{
			sb.append(' ').append (join);
		}
		boolean first = true;
		for (String query: queries)
		{
			if (first)
				sb.append(" where "); //$NON-NLS-1$
			else
				sb.append(" and "); //$NON-NLS-1$
			sb.append(query);
			first = false;
		}

		String s = sb.toString ();
		Collection usuaris = getUsuariEntityDao().query(s, params.toArray(new Parameter[0])); 
		if (usuaris != null && usuaris.size() != 0)
		{
			// Ya tenemos los grupos del usuario con permisos
			Collection usuarisPermis = AutoritzacionsUsuari.filtraUsuariEntityCanQuery(usuaris);
			
			if (usuarisPermis != null && usuarisPermis.size() != 0) {
				List<Usuari> vos = getUsuariEntityDao().toUsuariList(usuarisPermis);
				if (criteria.getAliesCorreu() != null && criteria.getAliesCorreu().trim().length () > 0 && ! criteria.equals("%")) //$NON-NLS-1$
				{
					Pattern p = Pattern.compile(
									criteria.getAliesCorreu().replaceAll(".", "\\.") //$NON-NLS-1$ //$NON-NLS-2$
										.replaceAll("%", ".*") //$NON-NLS-1$ //$NON-NLS-2$
										.replaceAll("_", ".")); //$NON-NLS-1$ //$NON-NLS-2$
					for (Iterator<Usuari> it =vos.iterator(); it.hasNext();)
					{
						Usuari usu = it.next();
						if (! p.matcher(usu.getAliesCorreu()).matches())
							it.remove ();
					}
				}
				// Check maximum number of results
				if (vos.size() > limitResults)
				{
					return vos.subList(0, limitResults);
				}
				return vos;
			}


		}
		return new Vector();
	}
}
