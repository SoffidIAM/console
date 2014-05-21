package es.caib.seycon.ng.legacy;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.xml.rpc.ServiceException;

import org.apache.catalina.Globals;
import org.apache.catalina.HttpRequest;
import org.apache.catalina.LifecycleException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.mx.util.MBeanProxy;
import org.jboss.mx.util.MBeanProxyCreationException;
import org.jboss.mx.util.MBeanServerLocator;
import org.jboss.security.Util;

import es.caib.loginModule.auth.ConstantesAutenticacion;
import es.caib.loginModule.client.AuthenticationFailureException;
import es.caib.loginModule.client.AuthorizationToken;
import es.caib.loginModule.client.LockedUserException;
import es.caib.loginModule.client.SesionEJBProxy;
import es.caib.loginModule.client.SessionIdentifier;
import es.caib.loginModule.client.SeyconPrincipal;
import es.caib.loginModule.jmx.SesionData;
import es.caib.loginModule.jmx.SeyconRealmServerMBean;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.RegistreAcces;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.AutoritzacioService;
import es.caib.seycon.ng.servei.PasswordService;
import es.caib.seycon.ng.servei.RegistreAccesService;
import es.caib.seycon.ng.servei.SeyconServiceLocator;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.utils.Security;
import es.caib.signatura.api.Signature;
import es.caib.signatura.api.SignatureProviderException;
import es.caib.signatura.api.SignatureVerifyException;
import es.caib.signatura.cliente.ValidadorCertificados;
import es.caib.signatura.cliente.XML;
import es.caib.signatura.validacion.ResultadoValidacion;

/**
 * @ejb.bean name="SesionEJB" display-name="SesionEJB"
 *           description="EJB que almacena datos de autenticación para una sesión"
 *           jndi-name="es.caib.loginModule.SesionEJB" type="BMP"
 *           view-type="remote"
 * 
 * @ejb.pk class = "java.lang.String"
 * 
 * @ejb.transaction type = "NotSupported"
 * @ejb.env-entry name="principalsQueryByCodi" value=
 *                "select usu_codi,usu_pass,usu_datcad,usu_nom,usu_nif from sc_wl_usuari where usu_codi=?"
 * @ejb.env-entry name="principalsQueryByNif" value=
 *                "select usu_codi,usu_pass,usu_datcad,usu_nom,usu_nif from sc_wl_usuari where usu_nif=?"
 * @ejb.env-entry name="rolesQuery"
 *                value="select ugr_codgru, 'Roles' from sc_wl_usugru where ugr_codusu=?"
 * @ejb.env-entry name="logQuery" value=
 *                "insert into sc_wl_logaut (log_dat,log_tipo,log_login,log_codusu,log_resul,log_certif, log_ip) values (sysdate,?,?,?,?,?,?)"
 * @ejb.env-entry name="unauthenticatedIdentity" value="nobody"
 * @ejb.env-entry name="lockMaxFails" value="5"
 * @ejb.env-entry name="lockPeriod" value="30"
 * 
 * @ejb.env-entry name="defaultCertDomain" value="seycon"
 * @ejb.env-entry name="defaultCertRoles" value="tothom,usuari-tipus-e"
 * 
 * @jboss.container-configuration name = "Allways Remote EntityBean"
 * 
 */
public class SesionEJB implements EntityBean {
    /**
         * 
         */
    private static final long serialVersionUID = 1L;
    private static Log log = LogFactory.getLog(SesionEJB.class);

    // Unathenticated identity
    private transient String unauthenticatedIdentity;

    // Parametros LockOuts
    private transient int lockMaxFails;
    private transient long lockPeriod;

    // Parametros encriptación contraseñas
    private transient String hashDsEncoding;
    private transient String hashDsCharset;

    // Información de la sesión
    private SesionData sesionInfo;

    private transient EntityContext context;

    // Proxies SSL fiables
    private static Hashtable trustedProxies;

    private static Random random = new Random(System.currentTimeMillis());

    // MBean de configuración
    private transient MBeanServer mbeanServer;
    private transient SeyconRealmServerMBean mbean;
    private transient boolean dirty;

    // Començament i fi del certificat
    private final String BEGIN_CERTIFICATE = "-----BEGIN CERTIFICATE-----\r\n"; //$NON-NLS-1$
    private final String END_CERTIFICATE = "\r\n-----END CERTIFICATE-----"; //$NON-NLS-1$

    public SesionEJB() {
        super();
    }

    public void setEntityContext(EntityContext ctx) throws EJBException, RemoteException {
        context = ctx;
        readConfiguration();
    }

    private void readConfiguration() {
        // Obtenemos variables entorno
        try {
            InitialContext c = new InitialContext();
            unauthenticatedIdentity = (String) c.lookup("java:comp/env/unauthenticatedIdentity"); //$NON-NLS-1$
            lockMaxFails = Integer.parseInt((String) c.lookup("java:comp/env/lockMaxFails")); //$NON-NLS-1$
            lockPeriod = 60000 * Integer.parseInt((String) c.lookup("java:comp/env/lockPeriod")); //$NON-NLS-1$

            try {
                hashDsEncoding = (String) c.lookup("java:comp/env/hashDsEncoding"); //$NON-NLS-1$
            } catch (Exception ex) {
                hashDsEncoding = Util.BASE64_ENCODING;
            }
            try {
                hashDsCharset = (String) c.lookup("java:comp/env/hashDsCharset"); //$NON-NLS-1$
            } catch (Exception ex) {
                hashDsCharset = "UTF-8"; //$NON-NLS-1$
            }
            //
            initProxiesKeyStore();
            mbeanServer = MBeanServerLocator.locateJBoss();
            ObjectInstance obj = mbeanServer.getObjectInstance(new ObjectName("jboss.security", //$NON-NLS-1$
                    "service", "SeyconRealm")); //$NON-NLS-1$ //$NON-NLS-2$
            mbean = (SeyconRealmServerMBean) MBeanProxy.get(SeyconRealmServerMBean.class,
                    obj.getObjectName(), mbeanServer);

        } catch (Exception ex) {
            log.error(Messages.getString("SesionEJB.AccessEnvVarsError"), ex); //$NON-NLS-1$
            throw new EJBException(Messages.getString("SesionEJB.AccessEnvVarsError"), ex); //$NON-NLS-1$
        }
    }

    public void ejbRemove() throws EJBException, RemoteException {
        if (log.isDebugEnabled())
            log.debug(Messages.getString("SesionEJB.SessionAutEJBDeleted")); //$NON-NLS-1$
        try {
        	deleteSessionFile();
        } catch (IOException e) {
        	throw new EJBException(e);
        }
        sesionInfo = null;
    }

    public void ejbActivate() throws EJBException, RemoteException {
        if (log.isDebugEnabled())
            log.debug(Messages.getString("SesionEJB.SessionAutEJBActivated")); //$NON-NLS-1$
        readConfiguration();
        sesionInfo = null;
    }

    public void ejbPassivate() throws EJBException, RemoteException {
        if (log.isDebugEnabled())
            log.debug(Messages.getString("SesionEJB.SessionAutEJBPaused")); //$NON-NLS-1$

        try {
        	purgeSession();
        } catch (IOException e) {
        	throw new EJBException(e);
        }
    }

    private void purgeSession() throws FileNotFoundException, IOException {
        if (sesionInfo != null && sesionInfo.isExpired(mbean.getDuration())) {
            deleteSessionFile();
        }
    }

    private void deleteSessionFile() throws FileNotFoundException, IOException {
        File f = getSesionFile(sesionInfo.getNumeroSesion());
        if (f.canRead())
        {
            if (!f.delete())
            {
            	new FileOutputStream(f).close(); // Truncate
            }
        }
    }

    /**
     * Default create method
     * 
     * @throws CreateException
     * @ejb.create-method
     * @ejb.permission unchecked = "true"
     */
    public String ejbCreate() throws CreateException {
        if (log.isDebugEnabled())
            log.debug(Messages.getString("SesionEJB.SessionAutEJBCreated")); //$NON-NLS-1$

        // Creamos datos de sesión y número de sesión
        sesionInfo = new SesionData();
        String numeroSesion = generarNumeroSesion();
        sesionInfo.setNumeroSesion(numeroSesion);
        setDirty(true);
        // Generar una clave primaria
        return numeroSesion;
    }

    /**
     * @ejb.
     * 
     */
    public void ejbPostCreate() {
        // Nothing to do
    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     * @ejb.transaction type = "RequiresNew"
     */
    public String autenticar() throws AuthenticationFailureException {
        if (sesionInfo == null) {
            log.info(String.format(Messages.getString("SesionEJB.SessionExpiredOnAuthentication"), //$NON-NLS-1$
            		context.getPrimaryKey().toString()));
            throw new AuthenticationFailureException(AuthenticationFailureException.EXPIRED_SESSION);
        }

        if (sesionInfo.getClaveSesion() != null) {
            log.info(String.format(Messages.getString("SesionEJB.SessionAlreadyAuthenticated"), //$NON-NLS-1$
            		context.getPrimaryKey().toString()));
            throw new AuthenticationFailureException(
                    AuthenticationFailureException.ALREADY_IDENTIFIED);
        }

        // Validamos credenciales
        if (log.isDebugEnabled())
            log.debug(String.format(Messages.getString("SesionEJB.AnonimAuthenticationUser"), this.unauthenticatedIdentity)); //$NON-NLS-1$
        sesionInfo.setPrincipal(new SeyconPrincipal());
        generarClaveSesion();
        logAutenticacion(ConstantesAutenticacion.AUTH_ANONIMO, getPrincipalUser(),
                getPrincipalUser(), ConstantesAutenticacion.OK, null);
        return sesionInfo.getClaveSesion();
    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     */
    public String autenticar(Signature firma) throws AuthenticationFailureException {
        if (sesionInfo == null) {
            log.info(String.format(Messages.getString("SesionEJB.SessionExpiredOnAuthentication"), //$NON-NLS-1$
            		context.getPrimaryKey().toString()));
            throw new AuthenticationFailureException(AuthenticationFailureException.EXPIRED_SESSION);
        }
        if (sesionInfo.getClaveSesion() != null) {
            log.info(String.format(Messages.getString("SesionEJB.SessionAlreadyAuthenticated"), //$NON-NLS-1$
            		context.getPrimaryKey().toString()));
            throw new AuthenticationFailureException(
                    AuthenticationFailureException.ALREADY_IDENTIFIED);
        }

        X509Certificate certs[] = null;
        try {
            // Obtenemos certificado
            try {
                certs = new X509Certificate[] { firma.getCert() };
            } catch (Exception e) {
                log.warn(Messages.getString("SesionEJB.ObtainCertificateUnexpectedException"), e); //$NON-NLS-1$
                logAutenticacion(ConstantesAutenticacion.AUTH_CERTIFICADO, null, null,
                        ConstantesAutenticacion.ERROR_DESCONOCIDO, certs[0]);
                throw new AuthenticationFailureException(e);
            }

            if (certs == null) {
                throw generateAuthenticationException(certs,
                        AuthenticationFailureException.CANNOT_RETRIEVE_CERT);
            }

            sesionInfo.setFirma(firma);
            // Recuperamos el objeto firmado y lo validamos
            String sesion = sesionInfo.getNumeroSesion();
            ByteArrayInputStream in = new ByteArrayInputStream(sesion.getBytes("UTF-8")); //$NON-NLS-1$
            try {
                if (!firma.verify()) {
                    log.warn(String.format(Messages.getString("SesionEJB.NotValidCertificate"), certs[0].getSubjectDN())); //$NON-NLS-1$
                    logAutenticacion(ConstantesAutenticacion.AUTH_CERTIFICADO, null, null,
                            ConstantesAutenticacion.ERROR_PASSWORDINCORRECTO, certs[0]);
                    throw new AuthenticationFailureException(
                            AuthenticationFailureException.INVALID_CERT);
                }
            } catch (SignatureVerifyException e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw, true);
                e.printStackTrace(pw);
                pw.flush();
                sw.flush();
                log.error(sw.toString());
                throw new AuthenticationFailureException(
                        AuthenticationFailureException.CANNOT_VALIDATE_CERT);
            }

            try {
                if (!firma.verify(in)) {
                    log.warn(String.format(Messages.getString("SesionEJB.CorruptSignature"), certs[0].getSubjectDN())); //$NON-NLS-1$
                    logAutenticacion(ConstantesAutenticacion.AUTH_CERTIFICADO, null, null,
                            ConstantesAutenticacion.ERROR_PASSWORDINCORRECTO, certs[0]);
                    throw new AuthenticationFailureException(
                            AuthenticationFailureException.INVALID_SIGNATURE);
                }
            } catch (SignatureProviderException e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw, true);
                e.printStackTrace(pw);
                pw.flush();
                sw.flush();
                log.error(sw.toString());
                throw new AuthenticationFailureException(
                        AuthenticationFailureException.CANNOT_VALIDATE_CERT);
            } catch (SignatureVerifyException e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw, true);
                e.printStackTrace(pw);
                pw.flush();
                sw.flush();
                log.error(sw.toString());
                throw new AuthenticationFailureException(
                        AuthenticationFailureException.CANNOT_VALIDATE_CERT);
            }
            in.close();
            return autenticarFirma();
        } catch (CertificateException e) {
            throw generateAuthenticationException(certs, e);
        } catch (UnsupportedEncodingException e) {
            throw generateAuthenticationException(certs, e);
        } catch (AuthenticationFailureException e) {
            throw generateAuthenticationException(certs, e);
        } catch (IOException e) {
            throw generateAuthenticationException(certs, e);
        } catch (NamingException e) {
            throw generateAuthenticationException(certs, e);
        } catch (CreateException e) {
            throw generateAuthenticationException(certs, e);
        } catch (InternalErrorException e) {
            throw generateAuthenticationException(certs, e);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     * @ejb.transaction type = "RequiresNew"
     * 
     */
    public String autenticar(Object obj) throws AuthenticationFailureException {
        HttpRequest request = (HttpRequest) obj;
        if (sesionInfo == null) {
            log.info(String.format(Messages.getString("SesionEJB.SessionExpiredOnAuthentication"), //$NON-NLS-1$
            		context.getPrimaryKey().toString()));
            throw new AuthenticationFailureException(AuthenticationFailureException.EXPIRED_SESSION);
        }
        if (sesionInfo.getClaveSesion() != null) {
            log.info(String.format(Messages.getString("SesionEJB.SessionAlreadyAuthenticated"), //$NON-NLS-1$
            		context.getPrimaryKey().toString()));
            throw new AuthenticationFailureException(
                    AuthenticationFailureException.ALREADY_IDENTIFIED);
        }

        X509Certificate certs[] = (X509Certificate[]) request.getRequest().getAttribute(
                Globals.CERTIFICATES_ATTR);
        if ((certs == null) || (certs.length < 1))
            certs = (X509Certificate[]) request.getRequest().getAttribute(
                    Globals.SSL_CERTIFICATE_ATTR);

        if (certs == null || certs.length == 0) {
            throw generateAuthenticationException(certs,
                    AuthenticationFailureException.CANNOT_RETRIEVE_CERT);
        }

        try {
            if (trustedProxies.get(certs[0]) != null) {
                log.debug(Messages.getString("SesionEJB.IsProxy")); //$NON-NLS-1$
                X509Certificate embeddedCert = getEmbeddedCertificate(request);
                if (embeddedCert != null) {
                    log.debug(String.format(Messages.getString("SesionEJB.Embedded"), embeddedCert.getSubjectDN().getName()));  //$NON-NLS-1$
                    X509Certificate certs2[] = new X509Certificate[certs.length + 1];
                    certs2[0] = embeddedCert;
                    for (int i = 0; i < certs.length; i++) {
                        certs2[i + 1] = certs[i];
                    }
                    certs = certs2;
                }
            }

            // TODO: VErificar validez del certificado
            try {
                certs[0].checkValidity();
            } catch (Exception e) {
                log.info(String.format(Messages.getString("SesionEJB.UnableCheckCertificate"), certs[0], e)); //$NON-NLS-1$
                throw new AuthenticationFailureException(
                        AuthenticationFailureException.INVALID_CERT);
            }
            sesionInfo.setCertificadoX509(certs);

            return autenticarFirma();
        } catch (CertificateException e) {
            throw generateAuthenticationException(certs, e);
        } catch (UnsupportedEncodingException e) {
            throw generateAuthenticationException(certs, e);
        } catch (AuthenticationFailureException e) {
            throw generateAuthenticationException(certs, e);
        } catch (IOException e) {
            throw generateAuthenticationException(certs, e);
        } catch (NamingException e) {
            throw generateAuthenticationException(certs, e);
        } catch (CreateException e) {
            throw generateAuthenticationException(certs, e);
        } catch (InternalErrorException e) {
            throw generateAuthenticationException(certs, e);
        }
    }

    /**
     * Autenticación de usuarios mediante certificado digital.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     * @ejb.transaction type = "RequiresNew"
     * 
     */
    public String autenticar(X509Certificate[] certificateChain)
            throws AuthenticationFailureException {

        if (certificateChain == null || certificateChain.length == 0) {
            throw generateAuthenticationException(certificateChain,
                    AuthenticationFailureException.CANNOT_RETRIEVE_CERT);
        }

        try {
            // Certificate chain validation
            MBeanServer mbeanServer;
            ObjectInstance obj;
            SeyconRealmServerMBean mbean;
            mbeanServer = MBeanServerLocator.locateJBoss();
            obj = mbeanServer.getObjectInstance(new ObjectName("jboss.security", "service", //$NON-NLS-1$ //$NON-NLS-2$
                    "SeyconRealm")); //$NON-NLS-1$
            mbean = (SeyconRealmServerMBean) MBeanProxy.get(SeyconRealmServerMBean.class,
                    obj.getObjectName(), mbeanServer);

            // Certificate validation
            String urlServidor = mbean.getCertificateValidationWebServiceUrl();
            String entorno = mbean.getCertificateValidationEnvironment();
            Properties config = new Properties();
            config.put("URL_SERVIDOR", urlServidor); //$NON-NLS-1$
            config.put("ENTORNO", entorno); //$NON-NLS-1$

            ValidadorCertificados validadorCertificados = new ValidadorCertificados(config);

            ByteArrayInputStream[] certChain = new ByteArrayInputStream[certificateChain.length];
            for (int i = 0; i < certificateChain.length; i++) {
                certChain[i] = new ByteArrayInputStream(certificateChain[certificateChain.length
                        - i - 1].getEncoded());
            }

            // We need to create another InputStream in order to treat correctly
            // the user's certificate.
            ByteArrayInputStream cert0 = new ByteArrayInputStream(certificateChain[0].getEncoded());

            // Certificate chain validation web service call
            byte wsRespByte[] = validadorCertificados.validarCertificadoAutenticacion(cert0);
            String wsResponse = new String(wsRespByte, "UTF-8"); //$NON-NLS-1$

            log.info(String.format(Messages.getString("SesionEJB.WebServiceReply"), wsResponse)); //$NON-NLS-1$

            ArrayList resultadosValidacion = XML.getResultadosValidacion(wsRespByte);
            for (Iterator i = resultadosValidacion.iterator(); i.hasNext();) {
                ResultadoValidacion resultadoValidacion = (ResultadoValidacion) i.next();
                if (!resultadoValidacion.getValidado().booleanValue()) {
                    List causas = resultadoValidacion.getListaCausasNoValidado();
                    String textoExcepcion = ""; //$NON-NLS-1$
                    for (int j = 0; j < causas.size(); j++) {
                        textoExcepcion += causas.get(j) + "; "; //$NON-NLS-1$
                    }
                    throw generateAuthenticationException(certificateChain, new Exception(
                            textoExcepcion));
                } else if (!resultadoValidacion.getValido().booleanValue()) {
                    throw generateAuthenticationException(certificateChain, new Exception(
                            resultadoValidacion.getCausaNoValido()));
                }
            }

            sesionInfo.setCertificadoX509(certificateChain);

            return autenticarCertificado();
        } catch (CertificateException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (UnsupportedEncodingException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (AuthenticationFailureException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (IOException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (NamingException e) {
            log.error(Messages.getString("SesionEJB.CreateUserLookupError")); //$NON-NLS-1$
            throw generateAuthenticationException(certificateChain, e);
        } catch (CreateException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (InternalErrorException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (ServiceException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (InstanceNotFoundException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (MalformedObjectNameException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (NullPointerException e) {
            throw generateAuthenticationException(certificateChain, e);
        } catch (MBeanProxyCreationException e) {
            throw generateAuthenticationException(certificateChain, e);
        }
    }

    private String autenticarFirma() throws NamingException, AuthenticationFailureException,
            CertificateException, IOException, CreateException, InternalErrorException {
        X509Certificate certs[] = sesionInfo.getCertificadoX509();
        sesionInfo.setPrincipal(new SeyconPrincipal(certs[0]));
        if (sesionInfo.getPrincipal().getIntranetUser() == null) {
            if (!fetchIntranetUser()) {
                throw new AuthenticationFailureException(
                        AuthenticationFailureException.USER_UNKNOWN);
            }
        }

        if (log.isDebugEnabled())
            log.debug(String.format(Messages.getString("SesionEJB.AuthenticationCertificateNIF"), sesionInfo.getPrincipal().getName())); //$NON-NLS-1$
        // generar clave de sesion
        generarClaveSesion();
        logAutenticacion(ConstantesAutenticacion.AUTH_CERTIFICADO, getPrincipalNif(),
                getPrincipalUser(), ConstantesAutenticacion.OK, certs[0]);
        return sesionInfo.getClaveSesion();
    }

    private String autenticarCertificado() throws NamingException, AuthenticationFailureException,
            CertificateException, IOException, CreateException, InternalErrorException {
        X509Certificate certs[] = sesionInfo.getCertificadoX509();
        sesionInfo.setPrincipal(new SeyconPrincipal(certs[0]));
        if (sesionInfo.getPrincipal().getIntranetUser() == null) {
            if (!fetchIntranetUserByCertificate()) {
                throw new AuthenticationFailureException(
                        AuthenticationFailureException.USER_UNKNOWN);
            }
        }

        if (log.isDebugEnabled())
            log.debug(String.format(Messages.getString("SesionEJB.AuthenticationCertificateNIF"), sesionInfo.getPrincipal().getName())); //$NON-NLS-1$
        // generar clave de sesion
        generarClaveSesion();
        logAutenticacion(ConstantesAutenticacion.AUTH_CERTIFICADO, getPrincipalNif(),
                getPrincipalUser(), ConstantesAutenticacion.OK, certs[0]);
        return sesionInfo.getClaveSesion();
    }

    private boolean fetchIntranetUserByCertificate() throws NamingException, CertificateException,
            CreateException, IOException, InternalErrorException {
        Enumeration e = mbean.getDomains().keys();
        while (e.hasMoreElements()) {
            try {
                if (fetchIntranetUser((String) e.nextElement()))
                    return true;
            } catch (NamingException e1) {
                if (!e.hasMoreElements())
                    throw e1;
            }
        }
        // El usuario no existe lo damos de alta a partir del Certificado.
        String httpUrlServer = mbean.getCreateUserServlet();
        X509Certificate certs[] = sesionInfo.getCertificadoX509();

        if (httpUrlServer != null
                && (sesionInfo.getFirma() != null || sesionInfo.getCertificadoX509() != null)) {
            // String httpUrlServer =
            // "http://tticlin2.test.lab:8080/seycon-webservice/createuserservice/";

            // Construct data: afegim començament i fi del certificat
            String strFileContent = BEGIN_CERTIFICATE
                    + new String(Base64.encodeBase64(certs[0].getEncoded()), "ISO-8859-1") //$NON-NLS-1$
                    + END_CERTIFICATE;

            String data;
            try {
                data = URLEncoder.encode("certificatePEM", "UTF-8") + "=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        + URLEncoder.encode(strFileContent, "UTF-8"); //$NON-NLS-1$

                // Send data
                URL url;
                url = new URL(httpUrlServer);
                URLConnection conn;
                conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr;
                wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                /**
                 * Result if ok OK|USUARI else NameExcepcion|MessageError
                 */
                while ((line = rd.readLine()) != null) {
                    if (line.startsWith("OK|")) { //$NON-NLS-1$
                        sesionInfo.getPrincipal().setIntranetUser(line.substring(3));
                    } else {
                        String[] error = line.split("\\|"); //$NON-NLS-1$
                        CertificateParsingException ex = new CertificateParsingException(error[0]
                                + ": " + error[1]); //$NON-NLS-1$
                        ex.printStackTrace();
                        log.error(error[0] + " : " + error[1]); //$NON-NLS-1$
                        // Si s'ha produit una excepció en el procesat del
                        // certificat la llancem i no deixem continuar el login
                        throw (ex);
                    }
                }
                wr.close();
                rd.close();
            } catch (UnsupportedEncodingException e1) {
                log.warn(Messages.getString("SesionEJB.UTF-8CodError"), e1); //$NON-NLS-1$
                log.warn(Messages.getString("SesionEJB.SeyconUserNotCreated"), e1); //$NON-NLS-1$
                throw e1;
            } catch (MalformedURLException e2) {
                log.warn(Messages.getString("SesionEJB.IncorrectURLCreateUserServlet"), e2); //$NON-NLS-1$
                log.warn(Messages.getString("SesionEJB.SeyconUserNotCreated"), e2); //$NON-NLS-1$
                throw e2;
            } catch (IOException e3) {
                log.warn(Messages.getString("SesionEJB.ProblemInSignProcess"), e3); //$NON-NLS-1$
                log.warn(Messages.getString("SesionEJB.SeyconUserNotCreated"), e3); //$NON-NLS-1$
                throw e3;
            } catch (CertificateParsingException e4) {
                log.warn(Messages.getString("SesionEJB.ProblemAnaliceSign"), e4); //$NON-NLS-1$
                log.warn(Messages.getString("SesionEJB.SeyconUserNotCreated"), e4); //$NON-NLS-1$
                throw e4;
            }

            // Agregar los roles por defecto
            InitialContext c = new InitialContext();
            String defaultDomain = (String) c.lookup("java:comp/env/defaultCertDomain"); //$NON-NLS-1$
            String roles = (String) c.lookup("java:comp/env/defaultCertRoles"); //$NON-NLS-1$
            sesionInfo.setRoles(defaultDomain, Arrays.asList(roles.split("[, ]+"))); //$NON-NLS-1$
            return true;

        } else if (httpUrlServer == null) {
            log.error(Messages.getString("SesionEJB.NoSeyconServletConfigurated") //$NON-NLS-1$
                    + (sesionInfo.getPrincipal() != null ? sesionInfo.getPrincipal().getName()
                            : "-")); //$NON-NLS-1$
        } else {
            log.error(Messages.getString("SesionEJB.CertificatesNotFound") //$NON-NLS-1$
                    + (sesionInfo.getPrincipal() != null ? sesionInfo.getPrincipal().getName()
                            : "-")); //$NON-NLS-1$
        }
        return false;

    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     * @ejb.transaction type = "RequiresNew"
     * 
     */
    public String autenticar(AuthorizationToken token) throws AuthenticationFailureException {

        if (sesionInfo == null) {
            throw new AuthenticationFailureException(AuthenticationFailureException.EXPIRED_SESSION);
        }
        String user = ""; //$NON-NLS-1$
        try {
            SesionEJBProxy remote = token.getIdentifier().getProxy();

            SeyconPrincipal principal = remote.obtenerPrincipal();
            user = principal.getName();

            SessionIdentifier id = token.getIdentifier();

            if (!id.verify(getPublicKey())) {
                log.warn(Messages.getString("SesionEJB.BadTokenSign")); //$NON-NLS-1$
                throw new AuthenticationFailureException(
                        AuthenticationFailureException.INVALID_TOKEN);
            }

            if (!remote.verify(token.getPassword())) {
                log.warn(Messages.getString("SesionEJB.BadTokenSessionKey")); //$NON-NLS-1$
                throw new AuthenticationFailureException(
                        AuthenticationFailureException.INVALID_TOKEN);
            }

            sesionInfo.setPrincipal(remote.obtenerPrincipal());
        } catch (InvalidKeyException e) {
            throw generateAuthenticationException(user, e);
        } catch (SignatureException e) {
            throw generateAuthenticationException(user, e);
        } catch (NoSuchAlgorithmException e) {
            throw generateAuthenticationException(user, e);
        } catch (FileNotFoundException e) {
            throw generateAuthenticationException(user, e);
        } catch (RemoteException e) {
            throw generateAuthenticationException(user, e);
        } catch (IOException e) {
            throw generateAuthenticationException(user, e);
        } catch (ClassNotFoundException e) {
            throw generateAuthenticationException(user, e);
        } catch (NamingException e) {
            throw generateAuthenticationException(user, e);
        } catch (CreateException e) {
            throw generateAuthenticationException(user, e);
        }

        generarClaveSesion();

        return sesionInfo.getClaveSesion();

    }

    /**
     */
    private SeyconPrincipal createIdentity(X509Certificate cert[])
            throws AuthenticationFailureException {
        try {
            if (validateCertificate(cert))
                return new SeyconPrincipal(cert[0]);
            else
                return null;
        } catch (CertificateEncodingException e) {
            throw generateAuthenticationException(cert, e);
        } catch (UnsupportedEncodingException e) {
            throw generateAuthenticationException(cert, e);
        } catch (IOException e) {
            throw generateAuthenticationException(cert, e);
        }
    }

    /**
     * @throws InternalErrorException
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     */
    public SeyconPrincipal createIdentity(String usuari) throws AuthenticationFailureException {
        SeyconPrincipal principal = null;
        Enumeration e = mbean.getDomains().keys();        
        try {
            while (principal == null && e.hasMoreElements()) {
                principal = createSeyconPrincipal((String) e.nextElement(), usuari);
            }
        } catch (InternalErrorException e1) {
            throw generateAuthenticationException(usuari, e1);
        }
        return principal;
    }

    private AuthenticationFailureException generateAuthenticationException(X509Certificate cert[],
            int cause) {
        logAutenticacion(ConstantesAutenticacion.AUTH_CERTIFICADO, getPrincipalNif(),
                getPrincipalUser(), ConstantesAutenticacion.ERROR_DESCONOCIDO, cert == null ? null
                        : cert[0]);
        AuthenticationFailureException ex = new AuthenticationFailureException(cause);

        log.warn(String.format(Messages.getString("SesionEJB.SesionEJB.UserAuthenticationError"), ex.getMessage())); //$NON-NLS-1$
        if (cert != null && cert.length > 0 && cert[0] != null)
            log.info(cert[0].toString());
        return ex;
    }

    private AuthenticationFailureException generateAuthenticationException(X509Certificate cert[],
            Exception e) {
        logAutenticacion(ConstantesAutenticacion.AUTH_CERTIFICADO, getPrincipalNif(),
                getPrincipalUser(), ConstantesAutenticacion.ERROR_DESCONOCIDO, cert == null ? null
                        : cert[0]);
        AuthenticationFailureException ex = new AuthenticationFailureException(e);
        log.warn(Messages.getString("SesionEJB.AuthenticationUserUnexpectedError"), ex); //$NON-NLS-1$
        if (cert != null && cert.length > 0 && cert[0] != null)
            log.info(cert[0]);
        return ex;
    }

    private AuthenticationFailureException generateAuthenticationException(X509Certificate cert[],
            AuthenticationFailureException e) {
        logAutenticacion(ConstantesAutenticacion.AUTH_CERTIFICADO, getPrincipalNif(),
                getPrincipalUser(), ConstantesAutenticacion.ERROR_DESCONOCIDO, cert == null ? null
                        : cert[0]);
        log.info(String.format(Messages.getString("SesionEJB.UserAuthenticationError"), e.getMessage())); //$NON-NLS-1$
        if (cert != null && cert.length > 0 && cert[0] != null)
            log.info(cert[0].toString());
        return e;
    }

    private AuthenticationFailureException generateAuthenticationException(String user, int cause) {
        logAutenticacion(ConstantesAutenticacion.AUTH_USUARIO, getPrincipalNif(), user,
                ConstantesAutenticacion.ERROR_DESCONOCIDO, null);
        AuthenticationFailureException ex = new AuthenticationFailureException(cause);
        log.info(String.format(Messages.getString("SesionEJB.UserAuthenticationError2"), user, ex.getMessage())); //$NON-NLS-1$
        return ex;
    }

    private AuthenticationFailureException generateAuthenticationException(String user,
            AuthenticationFailureException e) {
        logAutenticacion(ConstantesAutenticacion.AUTH_USUARIO, getPrincipalNif(), user,
                ConstantesAutenticacion.ERROR_DESCONOCIDO, null);
        log.info(String.format(Messages.getString("SesionEJB.NotAuthenticateUser"), e.getMessage())); //$NON-NLS-1$
        return (AuthenticationFailureException) e;
    }

    private AuthenticationFailureException generateAuthenticationException(String user, Exception e) {
        logAutenticacion(ConstantesAutenticacion.AUTH_USUARIO, getPrincipalNif(), user,
                ConstantesAutenticacion.ERROR_DESCONOCIDO, null);
        AuthenticationFailureException ex = new AuthenticationFailureException(e);
        log.warn(Messages.getString("SesionEJB.AuthenticationUserUnexpectedError"), e); //$NON-NLS-1$
        return ex;
    }

    private String getPrincipalUser() {
        if (sesionInfo == null)
            return null;
        if (sesionInfo.getPrincipal() == null)
            return null;
        return sesionInfo.getPrincipal().getIntranetUser();
    }

    private String getPrincipalNif() {
        if (sesionInfo == null)
            return null;
        if (sesionInfo.getPrincipal() == null)
            return null;
        return sesionInfo.getPrincipal().getNif();
    }

    private boolean validateCertificate(X509Certificate cert[]) {
        try {
            cert[0].checkValidity();
            return true;
        } catch (CertificateExpiredException e) {
            return false;
        } catch (CertificateNotYetValidException e) {
            return false;
        }
    }

    /**
     * @throws AuthenticationFailureException
     * @throws InternalErrorException 
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     * @ejb.transaction type = "RequiresNew"
     * 
     */
    public String autenticar(String usuario, String password) throws AuthenticationFailureException {
        if (sesionInfo == null) {
            log.warn(String.format(Messages.getString("SesionEJB.TryAuthenticateExpiredSession"), //$NON-NLS-1$
            		context.getPrimaryKey().toString()));
            throw new AuthenticationFailureException(AuthenticationFailureException.EXPIRED_SESSION);
        }
        if (sesionInfo.getClaveSesion() != null) {
            log.warn(String.format(Messages.getString("SesionEJB.AlreadyAuthenticateSession"), //$NON-NLS-1$
            		context.getPrimaryKey().toString()));
            throw new AuthenticationFailureException(
                    AuthenticationFailureException.ALREADY_IDENTIFIED);
        }

        // Validamos credenciales
        if (log.isDebugEnabled())
            log.debug(String.format(Messages.getString("SesionEJB.AuthenticateUserPass"), usuario)); //$NON-NLS-1$

        // Comprobamos que el usuario no sea el unauthenticatedIdentity
        if (usuario.equals(unauthenticatedIdentity)) {
            throw generateAuthenticationException(usuario,
                    AuthenticationFailureException.USER_UNKNOWN);
        }

        try {
            SeyconPrincipal principal;
            principal = createIdentity(usuario);
            if (principal == null)
                throw generateAuthenticationException(usuario,
                        AuthenticationFailureException.WRONG_PASSWORD);

            // Validamos credenciales
            if (validarCredenciales(principal, password)) {
                if (log.isDebugEnabled())
                    log.debug(Messages.getString("SesionEJB.AccessOK")); //$NON-NLS-1$
                generarClaveSesion();
                logAutenticacion(ConstantesAutenticacion.AUTH_USUARIO, principal.getIntranetUser(),
                        principal.getIntranetUser(), ConstantesAutenticacion.OK, null);
                return sesionInfo.getClaveSesion();

            } else {
                throw generateAuthenticationException(usuario,
                        AuthenticationFailureException.WRONG_PASSWORD);
            }
        } catch (NamingException e) {
            throw generateAuthenticationException(usuario, e);
        } catch (InternalErrorException e) {
            throw generateAuthenticationException(usuario, e);
        } catch (LockedUserException e) {
            generateAuthenticationException(usuario, e);
            throw e;
        }
    }

    private boolean validarCredenciales(SeyconPrincipal principal, String password)
            throws LockedUserException, NamingException, InternalErrorException {
        Enumeration e = mbean.getDomains().keys();
        while (e.hasMoreElements()) {
            if (validarCredenciales((String) e.nextElement(), principal, password))
                return true;
        }
        return false;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     */
    public boolean verify(String claveSesion) {
        if (sesionInfo == null)
            throw new EJBException(Messages.getString("SesionEJB.ExpiredSession")); //$NON-NLS-1$
        boolean expired = sesionInfo.isExpired(mbean.getDuration());
        java.util.Date lastUpdate = sesionInfo.getLastUpdate();
        // En caso de no haberse identificado todavía
        if (sesionInfo.getClaveSesion() == null && claveSesion == null)
            return true;
        // Validamos clave de sesion
        if (sesionInfo.getClaveSesion() == null || claveSesion == null
                || !sesionInfo.getClaveSesion().equals(claveSesion) || expired) {
            if (log.isDebugEnabled()) {
                if (expired) {
                    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                            DateFormat.LONG);
                    log.debug(String.format(Messages.getString("SesionEJB.ExpiredSessionInfo"), //$NON-NLS-1$
                    		df.format(lastUpdate), mbean.getDuration()));
                }
                if (claveSesion == null || !claveSesion.equals(sesionInfo.getClaveSesion()))
                    log.debug(String.format(Messages.getString("SesionEJB.SessionKeyNotAgree"), //$NON-NLS-1$
                    		sesionInfo.getClaveSesion(), claveSesion));
            }
            sesionInfo.setClaveSesion(null);
            sesionInfo.setCertificadoX509(null);
            sesionInfo.setPrincipal(null);
            sesionInfo.clearRoles();
            return false;
        }
        setDirty(true);
        return true;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     */
    public String obtenerSesion() {
        if (sesionInfo == null)
            throw new EJBException(Messages.getString("SesionEJB.ExpiredSession")); //$NON-NLS-1$
        return sesionInfo.getNumeroSesion();
    }

    private List obtenerRoles(String realm) throws NamingException, InternalErrorException {
        if (sesionInfo.isNeedsChangePassword()) {
            List roles = new ArrayList(1);
            roles.add("SEYCON_CHANGE_PASSWORD"); //$NON-NLS-1$
            return roles;
        } else {
            List roles = sesionInfo.getRoles(realm);
            if (roles == null) {
                roles = populateRoles(realm);
                sesionInfo.setRoles(realm, roles);
            }
            return roles;
        }
    }

    /**
     * @throws InternalErrorException 
     * @throws NamingException
     * @throws SQLException
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     */
    public List obtenerRoles(String realms[]) throws AuthenticationFailureException {
        String currentRealm = "<null>"; //$NON-NLS-1$
        try {
            if (realms.length == 0)
                return null;
            if (realms.length == 1) {
                currentRealm = realms[0];
                return obtenerRoles(currentRealm);
            }
            ArrayList roles = new ArrayList();
            for (int i = 0; i < realms.length; i++) {
                currentRealm = realms[i];
                roles.addAll(obtenerRoles(currentRealm));
            }
            return roles;
        } catch (InternalErrorException e1) {
            throw generateAuthenticationException(sesionInfo.getPrincipal().getName(), e1);
        } catch (NamingException e) {
            log.warn(String.format(Messages.getString("SesionEJB.RealmRolsNotObtained"), currentRealm), e); //$NON-NLS-1$
            throw new AuthenticationFailureException(e);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     */
    public SeyconPrincipal obtenerPrincipal() {
        if (sesionInfo == null)
            throw new EJBException(Messages.getString("SesionEJB.ExpiredSession")); //$NON-NLS-1$
        return sesionInfo.getPrincipal();
    }

    //
    // ------ Funciones auxiliares
    //
    /**
     * Valida credenciales
     * 
     * @throws LockedUserException
     * @throws InternalErrorException 
     */

    private boolean validarCredenciales(String realm, SeyconPrincipal principal, String pass)
            throws NamingException, LockedUserException, InternalErrorException {
        // Obtenemos metodo autenticación

        Security.nestedLogin("$$INTERNAL$$", new String[] { Security.AUTO_USER_QUERY //$NON-NLS-1$
                + Security.AUTO_ALL });
        try {
            // Valida usuario/dni contra BBDD

            UsuariService us = SeyconServiceLocator.instance().getUsuariService();
            AccountService as = SeyconServiceLocator.instance().getAccountService();
            PasswordService ps = SeyconServiceLocator.instance().getPasswordService();

            String dispatcher = ps.getDefaultDispatcher();
            Account acc = as.findAccount(principal.getIntranetUser(), dispatcher);
            if (acc == null)
                return false;

            // Comprobamos si el usuario esta bloqueado (excepto anónimos)
            if (LockoutTracker.isLocked(principal.getIntranetUser(), acc.getLastPasswordSet())) { //$NON-NLS-1$
                log.info(String.format(Messages.getString("SesionEJB.UserLocked"), principal.getIntranetUser())); //$NON-NLS-1$
                throw new LockedUserException();
            }

            String passwordDomain = ps.getDefaultDispatcher();
            if (ps.checkPassword(principal.getIntranetUser(), passwordDomain, new Password(pass), false,
                    false)) {
                sesionInfo.setNeedsChangePassword(false);
                LockoutTracker.registerSuccess(principal.getIntranetUser());
                return true;
            } else if (ps.checkPassword(principal.getIntranetUser(), passwordDomain, new Password(pass),
                    false, true)) {
                sesionInfo.setNeedsChangePassword(true);
                LockoutTracker.registerSuccess(principal.getIntranetUser());
                return true;
            } else {
                log.debug(String.format(Messages.getString("SesionEJB.NotValidUserPass"), principal.getIntranetUser())); //$NON-NLS-1$
                LockoutTracker.registerFailure(principal.getIntranetUser(), lockMaxFails,
                        lockPeriod, acc.getLastPasswordSet()); //$NON-NLS-1$
                return false;
            }
        } finally {
            Security.nestedLogoff();
        }
    }

    /**
     * Obtener el código de usuario a partir del NIF Actualiza
     * sesioninfo.principal
     * 
     * @throws CertificateEncodingException
     * @throws CreateException
     * @throws RemoteException
     * @throws InternalErrorException
     * 
     */

    private boolean fetchIntranetUser() throws NamingException, CertificateException,
            CreateException, UnsupportedEncodingException, MalformedURLException, IOException, InternalErrorException {
        Enumeration e = mbean.getDomains().keys();
        while (e.hasMoreElements()) {
            try {
                if (fetchIntranetUser((String) e.nextElement()))
                    return true;
            } catch (NamingException e1) {
                if (!e.hasMoreElements())
                    throw e1;
            }
        }
        // El usuario no existe lo damos de alta a partir del Certificado.
        String httpUrlServer = mbean.getCreateUserServlet();
        if (httpUrlServer != null && sesionInfo.getFirma() != null) {
            // String httpUrlServer =
            // "http://tticlin2.test.lab:8080/seycon-webservice/createuserservice/";

            // Construct data
            // Obtenim el certificat de la firma i el convertim a base 64
            String strFileContent = BEGIN_CERTIFICATE
                    + new String(Base64.encodeBase64(sesionInfo.getFirma().getCert().getEncoded()),
                            "ISO-8859-1") + END_CERTIFICATE; //$NON-NLS-1$
            String data;
            try {
                data = URLEncoder.encode("certificatePEM", "UTF-8") + "=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        + URLEncoder.encode(strFileContent, "UTF-8"); //$NON-NLS-1$

                // Send data
                URL url;
                url = new URL(httpUrlServer);
                URLConnection conn;
                conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr;
                wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                /**
                 * Result if ok OK|USUARI else NameExcepcion|MessageError
                 */
                while ((line = rd.readLine()) != null) {
                    if (line.startsWith("OK|")) { //$NON-NLS-1$
                        sesionInfo.getPrincipal().setIntranetUser(line.substring(3));
                    } else {
                        String[] error = line.split("\\|"); //$NON-NLS-1$
                        CertificateParsingException ex = new CertificateParsingException(error[0]
                                + ": " + error[1]); //$NON-NLS-1$
                        ex.printStackTrace();
                        log.error(error[0] + " : " + error[1]); //$NON-NLS-1$
                        // Si s'ha produit una excepció en el procesat del
                        // certificat la llancem i no deixem continuar el login
                        throw (ex);
                    }

                }
                wr.close();
                rd.close();
            } catch (UnsupportedEncodingException e1) {
                log.warn(Messages.getString("SesionEJB.UTF-8CodError"), e1); //$NON-NLS-1$
                log.warn(Messages.getString("SesionEJB.SeyconUserNotCreated"), e1); //$NON-NLS-1$
                throw e1;
            } catch (MalformedURLException e2) {
                log.warn(Messages.getString("SesionEJB.IncorrectURLCreateUserServlet"), e2); //$NON-NLS-1$
                log.warn(Messages.getString("SesionEJB.SeyconUserNotCreated"), e2); //$NON-NLS-1$
                throw e2;
            } catch (IOException e3) {
                log.warn(Messages.getString("SesionEJB.ProblemInSignProcess"), e3); //$NON-NLS-1$
                log.warn(Messages.getString("SesionEJB.SeyconUserNotCreated"), e3); //$NON-NLS-1$
                throw e3;
            } catch (CertificateParsingException e4) {
                log.warn(Messages.getString("SesionEJB.ProblemAnaliceSign"), e4); //$NON-NLS-1$
                log.warn(Messages.getString("SesionEJB.SeyconUserNotCreated"), e4); //$NON-NLS-1$
                throw e4;
            }

            // Agregar los roles por defecto
            InitialContext c = new InitialContext();
            String defaultDomain = (String) c.lookup("java:comp/env/defaultCertDomain"); //$NON-NLS-1$
            String roles = (String) c.lookup("java:comp/env/defaultCertRoles"); //$NON-NLS-1$
            sesionInfo.setRoles(defaultDomain, Arrays.asList(roles.split("[, ]+"))); //$NON-NLS-1$
            return true;
        } else if (httpUrlServer != null && sesionInfo.getCertificadoX509() != null) {
            // autenticación via certificado digital

        } else if (httpUrlServer == null && sesionInfo.getFirma() != null) {
            log.info(Messages.getString("SesionEJB.SeyconEJBNotConfigurated") //$NON-NLS-1$
                    + sesionInfo.getPrincipal().getName());
        }
        return false;

        /*
         * String jndi = mbean.getCreateUserBean(); if (jndi != null &&
         * sesionInfo.getFirma() != null) { InitialContext context = new
         * InitialContext(); Object o = context.lookup(jndi); UsuarisCertEJBHome
         * home =
         * (UsuarisCertEJBHome)PortableRemoteObject.narrow(o,UsuarisCertEJBHome
         * .class); UsuarisCertEJB usuarisCert = home.create(); try {
         * sesionInfo.
         * getPrincipal().setIntranetUser(usuarisCert.addUsuari(sesionInfo
         * .getFirma())); } catch (RemoteException e1) {
         * log.warn("Imposible crear usuario SEYCON", e1); throw e1; } catch
         * (InternalErrorException e1) {
         * log.warn("Imposible crear usuario SEYCON", e1); throw e1; } //
         * Agregar los roles por defecto InitialContext c = new
         * InitialContext(); String defaultDomain = (String)
         * c.lookup("java:comp/env/defaultCertDomain"); String roles = (String)
         * c.lookup("java:comp/env/defaultCertRoles"); sesionInfo.setRoles
         * (defaultDomain, Arrays.asList(roles.split("[, ]+"))); return true; }
         * else if (jndi != null && sesionInfo.getCertificadoX509() != null) {
         * // autenticación via certificado digital
         * 
         * } else if (jndi == null && sesionInfo.getFirma() != null) { log.info(
         * "No hay configurado EJB seycon para poder dar de alta el usuario: "
         * +sesionInfo.getPrincipal().getName()); } return false;
         */
    }

    /**
     * Obtener el código de usuario a partir del NIF Actualiza
     * sesioninfo.principal
     * 
     * @throws NamingException
     * @throws CreateException
     * @throws RemoteException
     * @throws InternalErrorException
     * @throws CertificateEncodingException
     * 
     */

    private boolean fetchIntranetUser(String realm) throws NamingException, InternalErrorException {
        SeyconPrincipal principal = sesionInfo.getPrincipal();
        if (principal == null)
            return false;
        if (principal.getNif() == null)
            return false;

        Security.nestedLogin("$$INTERNAL$$", new String[] { Security.AUTO_USER_QUERY //$NON-NLS-1$
                + Security.AUTO_ALL });
        try {
            UsuariService us = SeyconServiceLocator.instance().getUsuariService();

            Usuari usuari = us.findUsuariByNIFUsuari(principal.getNif());
            if (usuari == null) {
                return false;
            }
            principal.setIntranetUser(usuari.getCodi());
            return true;
        } finally {
            Security.nestedLogoff();
        }
    }

    private static int contador = 0;

    /**
     * Obtener los roles a partir del código de usuario Actualiza el miembro
     * roles
     * 
     * @throws InternalErrorException
     * 
     * @throws SQLException
     * @throws NamingException
     */
    private ArrayList populateRoles(String domain) throws InternalErrorException {

        Security.nestedLogin("$$INTERNAL$$", new String[] { Security.AUTO_USER_QUERY //$NON-NLS-1$
                + Security.AUTO_ALL });
        try {
            ArrayList roles = new ArrayList();
            if (getPrincipalUser() == null) {
                roles.add("nobody"); //$NON-NLS-1$
                roles.add("BPM_INTERNAL"); //$NON-NLS-1$
            } else {
                AutoritzacioService us = SeyconServiceLocator.instance().getAutoritzacioService();
    
                String[] rolesArray = us.getUserAuthorizationsString(getPrincipalUser());
                for (int i = 0; i < rolesArray.length; i++) {
                    roles.add(rolesArray[i]);
                }
                roles.add("tothom"); //$NON-NLS-1$
            }
            return roles;
        } finally {
            Security.nestedLogoff();
        }
    }

    private SeyconPrincipal createSeyconPrincipal(String domain, String cod)
            throws InternalErrorException {
        String userBd = null;

        Security.nestedLogin("$$INTERNAL$$", new String[] { Security.AUTO_USER_QUERY //$NON-NLS-1$
                + Security.AUTO_ALL });
        try {

            UsuariService us = SeyconServiceLocator.instance().getUsuariService();
            AccountService as = SeyconServiceLocator.instance().getAccountService();
            PasswordService ps = SeyconServiceLocator.instance().getPasswordService();

            String dispatcher = ps.getDefaultDispatcher();
            Account acc = as.findAccount(cod, dispatcher);
            Usuari usuari = null;
            if (acc == null)
            {
                return null;
            }
            if (acc instanceof UserAccount){
            	
            	usuari = us.findUsuariByCodiUsuari( ((UserAccount) acc).getUser());;
            }

            userBd = acc.getName();
            String nomBd = usuari == null ? acc.getDescription(): usuari.getFullName(); //$NON-NLS-1$ //$NON-NLS-2$
            String nifBd = usuari == null ? null: usuari.getNIF();

            SeyconPrincipal principal = new SeyconPrincipal(userBd, nomBd, nifBd);
            sesionInfo.setPrincipal(principal);
            return principal;
        } finally {
            Security.nestedLogoff();
        }
    }

    // Realiza apunte en tabla de logs
    private void logAutenticacion(char metodo, String usuNIF, String usuSeycon, char autenticado,
            X509Certificate certificadoX509) {
        log.info(String.format(Messages.getString("SesionEJB.LoginInfo"), sesionInfo.getIpRoute(), //$NON-NLS-1$
        		(usuNIF == null ? usuSeycon : usuNIF), metodo, autenticado));
        try {
            RegistreAccesService service = SeyconServiceLocator.instance().getRegistreAccesService();
            RegistreAcces rac = new RegistreAcces ();
            rac.setId(new Long(0));
            String hostName = InetAddress.getLocalHost().getHostName();
            if (hostName.indexOf('.') > 0)
                hostName = hostName.substring(0, hostName.indexOf('.'));
            rac.setCodeAge(hostName);
            rac.setCodiUsuari(usuSeycon);
            rac.setDataFi(Calendar.getInstance());
            rac.setDataInici(Calendar.getInstance());
            rac.setNomServidor(hostName);
            rac.setInformacio(sesionInfo.getIpRoute());
            rac.setProtocolAcces("HTTP"); //$NON-NLS-1$
            if (autenticado == 'S')
                rac.setTipusAcces("L"); //$NON-NLS-1$
            else
                rac.setTipusAcces("D"); //$NON-NLS-1$
            if (certificadoX509 != null) {
                byte certBase64[] = Base64.encodeBase64(certificadoX509.getEncoded());
                rac.setInformacio("Cert:"+new String(certBase64, "ISO-8859-1")); //$NON-NLS-1$ //$NON-NLS-2$
            }
            rac.setNomClinet(sesionInfo.getIpRoute());
            service.create(rac);
        } catch (Exception e) {
            log.error(Messages.getString("SesionEJB.AuthenticationLogError"), e); //$NON-NLS-1$
        }
    }

    private void generarClaveSesion() {
        setDirty(true);
        sesionInfo.setClaveSesion(Long.toString(random.nextLong()));
    }

    private String generarNumeroSesion() {
        return (Long.toString(System.currentTimeMillis()) + Long.toString(this.hashCode()));
    }

    /**
     * @throws KeyStoreException
     * @throws LifecycleException
     * 
     */
    private static void initProxiesKeyStore() throws KeyStoreException {
        if (trustedProxies == null) {
            trustedProxies = new Hashtable();
            KeyStore trustedProxiesKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            File homeDir = new File(System.getProperty("jboss.server.home.dir")); //$NON-NLS-1$
            File confDir = new File(homeDir, "conf"); //$NON-NLS-1$
            File proxies = new File(confDir, "proxies.keystore"); //$NON-NLS-1$
            if (proxies.canRead()) {
                try {
                    trustedProxiesKeyStore.load(new FileInputStream(proxies), null);
                    Enumeration e = trustedProxiesKeyStore.aliases();
                    while (e.hasMoreElements()) {
                        String alias = (String) e.nextElement();
                        if (trustedProxiesKeyStore.isCertificateEntry(alias)) {
                            Certificate cert = trustedProxiesKeyStore.getCertificate(alias);
                            if (cert != null) {
                                if (cert instanceof X509Certificate)
                                    log.info(String.format(Messages.getString("SesionEJB.TrustProxy"), //$NON-NLS-1$
                                    		((X509Certificate) cert).getSubjectDN().getName()));
                                trustedProxies.put(cert, "trusted"); //$NON-NLS-1$
                            }
                        }
                    }
                } catch (Exception e1) {
                    log.warn(Messages.getString("SesionEJB.proxies.keystoreimposibleRead"), e1); //$NON-NLS-1$
                }
            }
        }
    }

    private X509Certificate getEmbeddedCertificate(HttpRequest request) throws CertificateException {
        String s = getDerClientCertificate(request);
        if (s == null)
            return null;

        log.debug(String.format(Messages.getString("SesionEJB.EmbeddedCertInfo"), s)); //$NON-NLS-1$
        InputStream is = new ByteArrayInputStream(s.getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509"); //$NON-NLS-1$

        X509Certificate cert = (X509Certificate) cf.generateCertificate(is);
        log.debug(String.format(Messages.getString("SesionEJB.EmbeddedCertInfo"), cert)); //$NON-NLS-1$
        try {
            is.close();
        } catch (IOException e) {
        }
        return cert;

    }

    /**
     * Obtiene el certificado cliente codificado en DER.
     * 
     * @param request
     * @return
     */
    private String getDerClientCertificate(HttpRequest request) {
        HttpServletRequest req = (HttpServletRequest) request.getRequest();
        String value = req.getHeader("x-ssl-client-cert"); //$NON-NLS-1$
        if (value == null)
            return null;
        else
            return value.replace('~', '\n');
    }

    /**
     * @ejb.interface-method
     */
    public void setIPRoute(String route) {
        if (sesionInfo == null)
            throw new EJBException(Messages.getString("SesionEJB.SessionExpired")); //$NON-NLS-1$
        if (sesionInfo.getClaveSesion() == null)
            sesionInfo.setIpRoute(route);
    }

    /**
     * @ejb.interface-method
     */
    public String getIPRoute() {
        if (sesionInfo == null)
            throw new EJBException(Messages.getString("SesionEJB.SessionExpired")); //$NON-NLS-1$
        return sesionInfo.getIpRoute();
    }

    public void unsetEntityContext() throws EJBException, RemoteException {
        // Nothing to do

    }

    private File getSesionFile(String id) {
        String dir = System.getProperty("jboss.server.home.dir"); //$NON-NLS-1$
        File f = new File(dir + "/work/seyconSession"); //$NON-NLS-1$
        if (!f.isDirectory())
            f.mkdirs();
        f = new File(f, id);
        return f;
    }

    public void ejbLoad() throws EJBException, RemoteException {
        if (sesionInfo == null) {
            try {
                File f = getSesionFile((String) context.getPrimaryKey());
                if (f.canRead()) {
                	FileInputStream fin = new FileInputStream(f);
                    ObjectInputStream in = new ObjectInputStream(fin);
                    sesionInfo = (SesionData) in.readObject();
                    in.close();
                    fin.close();
                } else {
                    log.info(Messages.getString("SesionEJB.SessionExpired")); //$NON-NLS-1$
                    throw new EJBException(Messages.getString("SesionEJB.SessionExpired")); //$NON-NLS-1$
                }
            } catch (Exception e) {
                throw new EJBException(e);
            }
        }
    }

    public void ejbStore() throws EJBException, RemoteException {
        if (isDirty() && sesionInfo != null) {
            try {
                File f = getSesionFile((String) context.getPrimaryKey());
                FileOutputStream fout = new FileOutputStream(f);
                ObjectOutputStream out = new ObjectOutputStream(fout);
                out.writeObject(sesionInfo);
                out.close ();
                fout.close();
                setDirty(false);
            } catch (Exception e) {
                throw new EJBException(e);
            }
        }
    }

    public String ejbFindByPrimaryKey(String key) throws EJBException {
        if (key.indexOf("/") >= 0 || key.indexOf("\\") >= 0 || key.indexOf(".") >= 0) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return null;
        if (getSesionFile(key).canRead())
            return key;
        else
            return null;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    /**
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws CreateException
     * @throws NamingException
     * @ejb.interface-method
     * 
     */
    public PublicKey getPublicKey() throws FileNotFoundException, IOException,
            ClassNotFoundException, NamingException, CreateException {
        return mbean.getPublicKey();
    }

    /**
     * @ejb.interface-method
     * 
     * @return
     */
    public SessionIdentifier getSessionIdentifier() {
        SessionIdentifier id;
        try {
            if (mbean.isLegacy()) {
                id = new SessionIdentifier(context.getEJBObject());
            } else {
                if (mbean.getServletURL() == null)
                    throw new EJBException(
                            Messages.getString("SesionEJB.ServletURLNotFound")); //$NON-NLS-1$
                URL url = new URL(mbean.getServletURL() + "/" + sesionInfo.getNumeroSesion()); //$NON-NLS-1$
                id = new SessionIdentifier(url);
            }
            id.sign(mbean.getPrivateKey());
        } catch (FileNotFoundException e) {
            throw new EJBException(
                    Messages.getString("SesionEJB.PKNotFound"), //$NON-NLS-1$
                    e);
        } catch (MalformedURLException e) {
            throw new EJBException(
                    Messages.getString("SesionEJB.ServletURLMisconfigured"), //$NON-NLS-1$
                    e);
        } catch (Exception e) {
            throw new EJBException(Messages.getString("SesionEJB.UnableSign"), e); //$NON-NLS-1$
        }
        return id;
    }
}
