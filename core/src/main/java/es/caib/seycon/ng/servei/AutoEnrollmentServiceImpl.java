// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.spi.CharsetProvider;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PasswordValidation;
import es.caib.seycon.ng.comu.PolicyCheckResult;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariAnonim;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InvalidPasswordException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.model.DadaUsuariEntity;
import es.caib.seycon.ng.model.DadaUsuariEntityImpl;
import es.caib.seycon.ng.model.DominiContrasenyaEntity;
import es.caib.seycon.ng.model.DominiContrasenyaEntityDao;
import es.caib.seycon.ng.model.PoliticaContrasenyaEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariEntityDao;
import es.caib.seycon.ng.remote.RemoteInvokerFactory;
import es.caib.seycon.ng.remote.URLManager;

/**
 * @see es.caib.seycon.ng.servei.AutoEnrollmentService
 */
public class AutoEnrollmentServiceImpl extends es.caib.seycon.ng.servei.AutoEnrollmentServiceBase {

    private static final String SeyconLogon_JNDI_NAME = "es.caib.seycon.net/SeyconLogonEJB"; //$NON-NLS-1$
    private static final String SeyconLogon_PIN_ADD_CODE = "PIN"; //$NON-NLS-1$
    private static final String SeyconLogon_EMAIL_ADD_CODE = "E-MAIL CONTACTE"; //$NON-NLS-1$

    /**
     * @throws CreateException
     * @throws NamingException
     * @throws MessagingException
     * @throws IOException
     * @see es.caib.seycon.ng.servei.AutoEnrollmentService#alta(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    protected java.lang.Boolean handleAlta(java.lang.String nom, java.lang.String llinatge1,
            String llinatge2, java.lang.String correuElectronic, String urlServidor)
            throws SeyconException {

        UsuariEntity usuari = null;

        try {
            usuari = findUsuariExistent(correuElectronic);
        } catch (Throwable sex) {
            sex.printStackTrace();
            SeyconException ex = new SeyconException(Messages.getString("AutoEnrollmentServiceImpl.0")); //$NON-NLS-1$
            ex.setStackTrace(sex.getStackTrace());
            throw ex;
        }
        if (usuari == null) {
            // si l'usuari no existeix el donem d'alta
            try {
                // consultem si hi ha servei de correu
                Session session = (Session) PortableRemoteObject.narrow(
                        new InitialContext().lookup("java:/es.caib.seycon.mail.smtp"), //$NON-NLS-1$
                        Session.class);

                // si hi ha servei de correu, creem l'usuari
                usuari = crearUsuari(nom, llinatge1, llinatge2, correuElectronic);

                // notifiquem l'alta a l'usuari
                crearPINiEnviarPerCorreuElectronic(session, usuari, correuElectronic, urlServidor);
            } catch (Throwable sex) {
                sex.printStackTrace();
                SeyconException ex = new SeyconException(sex.getMessage());
                ex.setStackTrace(sex.getStackTrace());
                throw ex;
            }
        } else {
            throw new SeyconException(
                    Messages.getString("AutoEnrollmentServiceImpl.1")); //$NON-NLS-1$
        }

        return new Boolean(true);
    }

    private void crearPINiEnviarPerCorreuElectronic(Session session, UsuariEntity usuari,
            String correuElectronic, String urlServidor) throws IOException, NamingException,
            CreateException, MessagingException, SeyconException {
        // mirem que no tingui assignat ja una dada addicional de tipus PIN per
        // a evitar el PrimaryKey constraint

        // Creem el PIN i el guardem com a dada addicional
        String PIN = String.valueOf(Math.round(Math.random() * 100000));// cambiamos
                                                                        // a 5
                                                                        // dígitos
                                                                        // de
                                                                        // pin
                                                                        // (u88683
                                                                        // -
                                                                        // 03/05/2011)
        String PINEncriptat = new Password(PIN).toString();

        DadaUsuari dadaUsuariPIN = new DadaUsuari();
        dadaUsuariPIN.setCodiDada(SeyconLogon_PIN_ADD_CODE);
        dadaUsuariPIN.setCodiUsuari(usuari.getCodi());
        dadaUsuariPIN.setValorDada(PINEncriptat);

        // si ja te PIN, fem un update, sinó un insert
        // obtenim l'EJBObject
        boolean hasPIN = false;

        Collection dades = getUsuariEntityDao().findDadesByCodi(usuari.getCodi());
        Iterator it = dades.iterator();
        while (it.hasNext()) {
            DadaUsuariEntity dada = (DadaUsuariEntity) it.next();
            if (SeyconLogon_PIN_ADD_CODE.equals(dada.getTipusDada().getCodi())) {
                hasPIN = true;
                dadaUsuariPIN = getDadaUsuariEntityDao().toDadaUsuari(dada);
                dadaUsuariPIN.setValorDada(PINEncriptat);
                break;
            }
        }

        if (!hasPIN) {
            // cridem al ejb per a crear la dada adicional
            DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao().dadaUsuariToEntity(
                    dadaUsuariPIN);
            getDadaUsuariEntityDao().create(dadaUsuariEntity);
        } else {
            // cridem al ejb per a actualitzar la dada adicional
            DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao().dadaUsuariToEntity(
                    dadaUsuariPIN);
            getDadaUsuariEntityDao().update(dadaUsuariEntity);
        }

        // Enviament del correu Electrònic de notificació del PIN
        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream(
                "/" + this.getClass().getPackage().getName().replaceAll("[.]", "/") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        + "/mail_template.txt"), "UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$
        StringBuffer str_buf = new StringBuffer();

        char[] buf = new char[128];
        int readed = 0;
        try {
            do {
                readed = in.read(buf, 0, 128);
                if (readed != -1)
                    str_buf.append(new String(buf).substring(0, readed));
            } while (readed != -1);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        String content = str_buf.toString().replaceAll("@EMAIL@", correuElectronic) //$NON-NLS-1$
                .replaceAll("@PIN@", PIN); //$NON-NLS-1$

        String content2 = null;
        if (urlServidor == null) {
            // eliminem el enllaç a la pàgina de assignar password a partir de
            // PIN
            content2 = content.replaceAll("@URL_SERVIDOR_INICIO@[^@]*@URL_SERVIDOR_FIN@", ""); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            // afegim la URL de modificació de password amb el PIN
            content2 = content.replaceAll("@URL_SERVIDOR_INICIO@(.)*", "$1") //$NON-NLS-1$ //$NON-NLS-2$
                    .replaceAll("@URL_SERVIDOR@", urlServidor).replaceAll("@URL_SERVIDOR_FIN@", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        PIN = null;

        // Enviem el correu. Si hi algun error durant l'enviament, l'usuari
        // haurà de tornar a registrar-se.
        send(session, "scorlin1.caib.es", correuElectronic, "www.caib.es@caib.es", //$NON-NLS-1$ //$NON-NLS-2$
                "CAIB - Alta de l'usuari anònim " + correuElectronic, content2); //$NON-NLS-1$

    }

    private UsuariEntity crearUsuari(String nom, String llinatge1, String llinatge2,
            String correuElectronic) throws RemoteException, CreateException, NamingException {

        // creem el VO
        Usuari usuari = new Usuari();

        usuari.setCodi(getUsuariEntityDao().getSeguentCodiAnonim());
        usuari.setActiu(new Boolean(false));
        //usuari.setContrasenyaCaducada(new Boolean(true));//no en té efecte
        usuari.setDataCreacioUsuari(GregorianCalendar.getInstance());
        usuari.setMultiSessio(new Boolean(false));
        usuari.setNom(nom);
        usuari.setPrimerLlinatge(llinatge1);
        usuari.setSegonLlinatge(llinatge2);
        usuari.setTipusUsuari("A"); //$NON-NLS-1$
        usuari.setCodiGrupPrimari("nul"); // FIXME: canviar el codi del grup //$NON-NLS-1$
                                          // primari per un nou q ue s'ha de
                                          // crear
        usuari.setServidorCorreu("nul"); // FIXME: canviar el servidor de correu //$NON-NLS-1$
        usuari.setServidorHome("nul"); // FIXME: canviar el servidor home //$NON-NLS-1$
        usuari.setServidorPerfil("nul"); // FIXME: canviar el servidor perfil //$NON-NLS-1$
        usuari.setUsuariCreacio(getPrincipal().getName()); // FIXME: canviar
                                                           // l'usuari de
                                                           // creació

        // cridem l'EJB per a que crei l'usuari
        UsuariEntity usuariEntity = getUsuariEntityDao().usuariToEntity(usuari);
        getUsuariEntityDao().create(usuariEntity);

        // creem el correuElectronic com a dada addicional
        DadaUsuari dadaUsuariCorreu = new DadaUsuari();

        dadaUsuariCorreu.setCodiDada(SeyconLogon_EMAIL_ADD_CODE);
        dadaUsuariCorreu.setCodiUsuari(usuari.getCodi());
        dadaUsuariCorreu.setValorDada(correuElectronic);

        // cridem al ejb per a crear la dada adicional

        DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao().dadaUsuariToEntity(
                dadaUsuariCorreu);
        getDadaUsuariEntityDao().create(dadaUsuariEntity);

        // retornem el nou usuari
        UsuariEntity usuariOut = getUsuariEntityDao().findByCodi(usuari.getCodi());

        return usuariOut;
    }

    private UsuariEntity findUsuariExistent(String correuElectronic) {
        // obtenim el codi d'usuari a partir del correu electrònic

        UsuariEntity usuariExistent = getUsuariEntityDao().findUsuariByCodiTipusDadaIValorDada(
                SeyconLogon_EMAIL_ADD_CODE, correuElectronic);

        return usuariExistent;
    }

    /**
     * @throws CreateException
     * @throws NamingException
     * @throws RemoteException
     * @see es.caib.seycon.ng.servei.AutoEnrollmentService#assignarPassword(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    protected java.lang.Boolean handleAssignarPassword(java.lang.String correuElectronic,
            java.lang.String PIN, java.lang.String newPassword) throws UnknownUserException,
            BadPasswordException, InvalidPasswordException, SeyconException {
        try {
            UsuariEntity usuari = findUsuariExistent(correuElectronic);

            if (usuari == null)
                throw new UnknownUserException();

            Context context = new InitialContext();

            // establim l'estat de l'usuari a actiu
            usuari.setActiu("S"); //$NON-NLS-1$
            getUsuariEntityDao().update(usuari);

            InternalPasswordService ips = getInternalPasswordService();
            if (ips.checkPin(usuari, PIN)) {
                DominiContrasenyaEntityDao dceDao = getDominiContrasenyaEntityDao();
                DominiContrasenyaEntity dc = dceDao.findDefaultDomain(usuari.getId());
                for (PoliticaContrasenyaEntity politica : dc.getPoliticaContrasenyes()) {
                    if (politica.getTipusUsuariDomini().equals(usuari.getTipusUsuari())) {
                        PolicyCheckResult result = ips.checkPolicy(usuari, politica, new Password(
                                newPassword));
                        if (!result.isValid())
                            throw new BadPasswordException(result.getReason());
                    }
                }
                ips.storeAndForwardPassword(usuari, dc, new Password(newPassword), false);
            } else {
                throw new InvalidPasswordException();
            }
        } catch (Throwable sex) {
            sex.printStackTrace();
            SeyconException ex = new SeyconException(Messages.getString("AutoEnrollmentServiceImpl.2")); //$NON-NLS-1$
            ex.setStackTrace(sex.getStackTrace());
            throw ex;
        }

        return new Boolean(true);
    }

    /**
     * @throws InvalidPasswordException
     *             , Exception
     * @throws BadPasswordException
     * @throws InternalErrorException
     * @throws UnknownUserException
     * @throws CreateException
     * @throws NamingException
     * @throws RemoteException
     * @see es.caib.seycon.ng.servei.AutoEnrollmentService#resetejarPassword(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    protected java.lang.Boolean handleResetejarPassword(java.lang.String correuElectronic,
            java.lang.String oldPassword, java.lang.String newPassword)
            throws UnknownUserException, BadPasswordException, InvalidPasswordException,
            SeyconException, InternalErrorException {
        UsuariEntity usuari = findUsuariExistent(correuElectronic);

        DominiContrasenyaEntityDao dceDao = getDominiContrasenyaEntityDao();
        DominiContrasenyaEntity dc = dceDao.findDefaultDomain(usuari.getId());
        InternalPasswordService ips = getInternalPasswordService();
        if (ips.checkPassword(usuari, dc, new Password(oldPassword), false, true) != PasswordValidation.PASSWORD_WRONG) {
            for (PoliticaContrasenyaEntity politica : dc.getPoliticaContrasenyes()) {
                if (politica.getTipusUsuariDomini().equals(usuari.getTipusUsuari())) {
                    PolicyCheckResult result = ips.checkPolicy(usuari, politica, new Password(
                            newPassword));
                    if (!result.isValid())
                        throw new BadPasswordException(result.getReason());
                }
            }
            ips.storeAndForwardPassword(usuari, dc, new Password(newPassword), false);
        } else {
            throw new InvalidPasswordException();
        }
        return new Boolean(true);
    }


    /**
     * @see es.caib.seycon.ng.servei.AutoEnrollmentService#modificarDades(String,
     *      String, String)
     */
    protected java.lang.Boolean handleModificarDades(String nom, String llinatge1,
            String llinatge2, String correuElectronic, String codiUsuariAnonim)
            throws SeyconException, UnknownUserException {
        Usuari usuari = null;
        try {
            // obtenemos el usuario con los datos antiguos a partir del
            // principal
            UsuariService usuariService = ServiceLocator.instance().getUsuariService();
            usuari = usuariService.findUsuariByCodiUsuari(codiUsuariAnonim);

        } catch (Throwable sex) {
            sex.printStackTrace();
            SeyconException ex = new SeyconException(Messages.getString("AutoEnrollmentServiceImpl.2")); //$NON-NLS-1$
            ex.setStackTrace(sex.getStackTrace());
            throw ex;
        }

        if (usuari == null) {
            throw new UnknownUserException();
        } else {
            usuari.setNom(nom);
            usuari.setPrimerLlinatge(llinatge1);
            usuari.setSegonLlinatge(llinatge2);
            UsuariEntity usuariEntity = getUsuariEntityDao().usuariToEntity(usuari);
            getUsuariEntityDao().update(usuariEntity);

            // No se pot actualitzar el email de contacte (!!)
            /*
             * DadaUsuariEntity dadaEntity=getDadaUsuariEntityDao().
             * findDadaByCodiUsuariAndCodiTipusDada(usuari.getCodi(),
             * SeyconLogon_EMAIL_ADD_CODE);
             * dadaEntity.setValorDada(correuElectronic);
             * getDadaUsuariEntityDao().update(dadaEntity);
             */

        }

        return new Boolean(true);
    }

    private void send(Session session, String smtpServer, String to, String from, String subject,
            String body) throws MessagingException, NamingException {

        // Properties props = new Properties();

        // -- Attaching to default Session, or we could start a new one --
        // props.put("mail.smtp.host", smtpServer);
        // Session session = Session.getDefaultInstance(props, null);

        Message msg = null;
        msg = new MimeMessage(session);

        // -- Create a new message --

        // -- Set the FROM and TO fields --
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // -- Set the subject and body text --
            msg.setSubject(subject);

            // enviem en mime - utf-8, que és com ho tenim al repositori
            ((MimeMessage) msg).setText(body, "UTF-8"); //$NON-NLS-1$

            // -- Set some other header information --
            msg.setHeader("X-Mailer", "LOTONtechEmail"); //$NON-NLS-1$ //$NON-NLS-2$
            msg.setSentDate(new Date());

            // -- Send the message --
            Transport.send(msg);

        } catch (AddressException e) {
            e.printStackTrace();
            // throw e;
        } catch (MessagingException e) {
            e.printStackTrace();
            // throw e;
        }

        System.out.println("Message sent OK."); //$NON-NLS-1$

    }

    /**
     * @throws CreateException
     * @throws NamingException
     * @throws MessagingException
     * @throws IOException
     * @see es.caib.seycon.ng.servei.AutoEnrollmentService#alta(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    protected void handleEnviarNouPIN(java.lang.String correuElectronic, String urlServidor)
            throws SeyconException, UnknownUserException {

        try {
            UsuariEntity usuari = findUsuariExistent(correuElectronic);

            if (usuari == null) {
                throw new UnknownUserException();
            }
            // consultem si hi ha servei de correu
            Session session = (Session) PortableRemoteObject.narrow(
                    new InitialContext().lookup("java:/es.caib.seycon.mail.smtp"), Session.class); //$NON-NLS-1$

            // si l'usuari ja existia, generem un nou PIN i li enviem per correu
            crearPINiEnviarPerCorreuElectronic(session, usuari, correuElectronic, urlServidor);

        } catch (UnknownUserException unk) {
            throw unk;
        } catch (Throwable sex) {
            sex.printStackTrace();
            SeyconException ex = new SeyconException(Messages.getString("AutoEnrollmentServiceImpl.2")); //$NON-NLS-1$
            ex.setStackTrace(sex.getStackTrace());
            throw ex;
        }

    }

    /**
     * El codi d'usuari correspon al codi d'usuari de les taules de login del
     * jboss. Com que els usuaris anònims tenen com a codi d'usuari de jboss el
     * correu, aquí ha de venir el correu electrònic.
     * 
     * @see es.caib.seycon.ng.servei.AutoEnrollmentService#consultarDades(java.lang.String)
     */
    protected es.caib.seycon.ng.comu.UsuariAnonim handleConsultarDades(java.lang.String codiUsuari)
            throws java.lang.Exception {

        // obtenim les dades de l'usuari conectat per a què només ell pugui
        // veure les seves dades.
        // String
        // usuariLoguejat=es.caib.loginModule.client.SeyconPrincipal.getCurrent()

        // obtenim les dades que es soliciten
        UsuariEntity usuari = null;
        UsuariAnonim usuariAnonim = null;
        try {
            usuari = (UsuariEntity) getUsuariEntityDao().findUsuariByCodiTipusDadaIValorDada(
                    SeyconLogon_EMAIL_ADD_CODE, codiUsuari);
            if (usuari == null)
                throw new UnknownUserException();
            usuariAnonim = getUsuariEntityDao().toUsuariAnonim(usuari);
        } catch (Throwable sex) {
            sex.printStackTrace();
            SeyconException ex = new SeyconException(Messages.getString("AutoEnrollmentServiceImpl.2")); //$NON-NLS-1$
            ex.setStackTrace(sex.getStackTrace());
            throw ex;
        }

        return usuariAnonim;
    }

}
