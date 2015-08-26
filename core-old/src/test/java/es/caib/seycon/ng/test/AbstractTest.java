package es.caib.seycon.ng.test;

import java.io.File;
import java.util.Collection;

import javax.sql.rowset.spi.XmlReader;

import com.soffid.test.AbstractHibernateTest;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.AutoritzacioRol;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.DominiContrasenya;
import es.caib.seycon.ng.comu.DominiUsuari;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.OsType;
import es.caib.seycon.ng.comu.PoliticaContrasenya;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.comu.TipusUsuari;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.ApplicationBootService;
import es.caib.seycon.ng.servei.AutoritzacioService;
import es.caib.seycon.ng.servei.ConfiguracioService;
import es.caib.seycon.ng.servei.DadesAddicionalsService;
import es.caib.seycon.ng.servei.DispatcherService;
import es.caib.seycon.ng.servei.DominiUsuariService;
import es.caib.seycon.ng.servei.GrupService;
import es.caib.seycon.ng.servei.InternalPasswordService;
import es.caib.seycon.ng.servei.InternalPasswordServiceImpl;
import es.caib.seycon.ng.servei.PasswordService;
import es.caib.seycon.ng.servei.PuntEntradaService;
import es.caib.seycon.ng.servei.SeyconServiceLocator;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.servei.XarxaService;
import es.caib.seycon.ng.utils.Security;

public abstract class AbstractTest extends AbstractHibernateTest
{

	protected InternalPasswordServiceImpl ps;
	protected ConfiguracioService configSvc;
	protected AplicacioService appSvc;
	protected DominiUsuariService dominiSvc;
	protected DispatcherService dispatcherSvc;
	protected GrupService grupSvc;
	protected XarxaService xarxaSvc;
	protected UsuariService usuariSvc;
	protected PasswordService passSvc;
	protected AutoritzacioService autSvc;
	protected DadesAddicionalsService tdSvc;
	protected PuntEntradaService peSvc;
	protected AccountService accountSvc;
	protected InternalPasswordService internalPassSvc;

	public AbstractTest()
	{
		super();
	}

	void deleteDir (File f)
	{
		if (f != null && f.isDirectory())
		{
			for (File s: f.listFiles())
				deleteDir (s);
		}
		f.delete();
	}
	public void setupdb() throws InternalErrorException,
			NeedsAccountNameException
	{
		
		System.setProperty("jboss.home.dir", "target/server");
		deleteDir (new File("target/docs"));
		
		
		ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");

		ApplicationBootService bootSvc = (ApplicationBootService) context.getBean(ApplicationBootService.SERVICE_NAME);
	
		
		bootSvc.consoleBoot();
		
		configSvc = (ConfiguracioService) context.getBean(ConfiguracioService.SERVICE_NAME);
		appSvc = (AplicacioService) context.getBean(AplicacioService.SERVICE_NAME);
		grupSvc = (GrupService) context.getBean(GrupService.SERVICE_NAME);
		dominiSvc = (DominiUsuariService) context.getBean(DominiUsuariService.SERVICE_NAME);
		dispatcherSvc = (DispatcherService) context.getBean(DispatcherService.SERVICE_NAME);
		xarxaSvc = (XarxaService) context.getBean(XarxaService.SERVICE_NAME);
		usuariSvc = (UsuariService) context.getBean(UsuariService.SERVICE_NAME);
		internalPassSvc = (InternalPasswordService) context.getBean(InternalPasswordService.SERVICE_NAME);
		autSvc = (AutoritzacioService) context.getBean(AutoritzacioService.SERVICE_NAME);
		tdSvc = (DadesAddicionalsService) context.getBean(DadesAddicionalsService.SERVICE_NAME);
		peSvc = (PuntEntradaService) context.getBean(PuntEntradaService.SERVICE_NAME);
		accountSvc = (AccountService) context.getBean(AccountService.SERVICE_NAME);
		passSvc = (PasswordService) context.getBean(PasswordService.SERVICE_NAME);
	
		System.setProperty("soffid.ui.maxrows", "9999");

        Configuracio cfg = configSvc.findParametreByCodiAndCodiXarxa(
				"versionLevel", null); //$NON-NLS-1$
		if (cfg == null)
		{
			DominiUsuari du = dominiSvc
					.findDominiUsuariByCodi("DEFAULT"); //$NON-NLS-1$
			if (du == null)
			{
				du = new DominiUsuari();
				du.setCodi("DEFAULT"); //$NON-NLS-1$
				du.setDescripcio("Default user domain"); //$NON-NLS-1$
				du.setTipus(TipusDominiUsuariEnumeration.PRINCIPAL);
				du = dominiSvc.create(du);
			}
	
			DominiContrasenya dc = dominiSvc
					.findDominiContrasenyaByCodi("DEFAULT");
			if (dc == null)
			{
				dc = new DominiContrasenya();
				dc.setCodi("DEFAULT"); //$NON-NLS-1$
				dc.setDescripcio("Default password domain"); //$NON-NLS-1$
				dc = dominiSvc.create(dc);
			}
	
			Collection<TipusUsuari> tus = dominiSvc.findAllTipusUsuari();
			TipusUsuari tipUs;
			if (tus.size() > 0)
			{
				tipUs = tus.iterator().next();
			}
			else
			{
				tipUs = new TipusUsuari();
				tipUs.setCodi("I"); //$NON-NLS-1$
				tipUs.setDescripcio("Internal user"); //$NON-NLS-1$
				dominiSvc.create(tipUs);
			}
	
			Collection<PoliticaContrasenya> pcs = dominiSvc
					.findAllPolitiquesContrasenyaDomini(dc.getCodi());
			if (pcs.size() == 0)
			{
				PoliticaContrasenya pol = new PoliticaContrasenya();
				pol.setCodiDominiContrasenya(dc.getCodi());
				pol.setCodiDominiUsuaris(du.getCodi());
				pol.setDescripcio("Default password policy"); //$NON-NLS-1$
				pol.setDuradaMaxima(new Long(365));
				pol.setDuradaMaximaCaducada(new Long(365));
				pol.setTipus("M"); //$NON-NLS-1$
				pol.setTipusUsuari(tipUs.getCodi());
				dominiSvc.create(pol);
			}
	
			Dispatcher dis = dispatcherSvc.findDispatcherByCodi("seu"); //$NON-NLS-1$
			if (dis == null)
			{
				dis = new Dispatcher();
				dis.setBasRol(new Boolean(true));
				dis.setCodi("seu"); //$NON-NLS-1$
				dis.setControlAccess(new Boolean(false));
				dis.setDominiContrasenyes(dc.getCodi());
				dis.setDominiUsuaris(du.getCodi());
				dis.setIdDominiContrasenyes(dc.getId());
				dis.setNomCla("- no class -"); //$NON-NLS-1$
				dis.setRelacioLaboral("I"); //$NON-NLS-1$
				dis = dispatcherSvc.create(dis);
			}
	
			Aplicacio app = appSvc.findAplicacioByCodiAplicacio("SEU"); //$NON-NLS-1$
			if (app == null)
			{
				app = new Aplicacio();
				app.setBd("seu"); //$NON-NLS-1$
				app.setCodi("SEU"); //$NON-NLS-1$
				app.setGestionableWF(new Boolean(false));
				app.setNom("SEU Identity Manager"); //$NON-NLS-1$
				app = appSvc.create(app);
			}
	
	
	
			Rol rol = appSvc.findRolByNomRolAndCodiAplicacioAndCodiDispatcher(
					"SEU_ADMIN", //$NON-NLS-1$
					app.getCodi(), dis.getCodi()); //$NON-NLS-1$
			if (rol == null)
			{
				rol = new Rol();
				rol.setCodiAplicacio(app.getCodi());
				rol.setBaseDeDades(dis.getCodi()); //$NON-NLS-1$
				rol.setContrasenya(new Boolean(false));
				rol.setDefecte(new Boolean(true));
				rol.setDescripcio("SEU Administrator"); //$NON-NLS-1$
				rol.setGestionableWF(new Boolean(false));
				rol.setNom("SEU_ADMIN"); //$NON-NLS-1$
				rol.setDomini(new Domini());
				rol = appSvc.create(rol);
			}
	
			Xarxa x = xarxaSvc.findXarxaByCodi("loopback"); //$NON-NLS-1$
			if (x == null)
			{
				x = new Xarxa();
				x.setCodi("loopback"); //$NON-NLS-1$
				x.setAdreca("127.0.0.0"); //$NON-NLS-1$
				x.setMascara("255.255.255.0"); //$NON-NLS-1$
				x.setNormalitzada(new Boolean(false));
				xarxaSvc.create(x);
			}
			
			OsType osType = xarxaSvc.findOSTypeByName("ALT");
			if (osType == null)
			{
				osType = new OsType();
				osType.setName("ALT");
				osType.setDescription("ALT Desc");
				xarxaSvc.create(osType);
			}
	
			Maquina m = xarxaSvc.findMaquinaByNom("loopback"); //$NON-NLS-1$
			if (m == null)
			{
				m = new Maquina();
				m.setCodiXarxa("loopback"); //$NON-NLS-1$
				m.setAdreca("127.0.0.1"); //$NON-NLS-1$
				m.setCorreu(new Boolean(false));
				m.setDescripcio("Loopback host"); //$NON-NLS-1$
				m.setNom("loopback"); //$NON-NLS-1$
				m.setOfimatica(new Boolean(false));
				m.setServidorImpressores(new Boolean(false));
				m.setSistemaOperatiu("ALT"); //$NON-NLS-1$
				xarxaSvc.create(m);
			}
	
			m = xarxaSvc.findMaquinaByNom("null"); //$NON-NLS-1$
			if (m == null)
			{
				m = new Maquina();
				m.setCodiXarxa("loopback"); //$NON-NLS-1$
				m.setCorreu(new Boolean(false));
				m.setDescripcio("Void host"); //$NON-NLS-1$
				m.setNom("null"); //$NON-NLS-1$
				m.setOfimatica(new Boolean(false));
				m.setServidorImpressores(new Boolean(false));
				m.setSistemaOperatiu("ALT"); //$NON-NLS-1$
				xarxaSvc.create(m);
			}
	
			Grup grup = grupSvc.findGrupByCodiGrup("world"); //$NON-NLS-1$
			if (grup == null)
			{
				grup = new Grup();
				grup.setCodi("world"); //$NON-NLS-1$
				grup.setDescripcio("World"); //$NON-NLS-1$
				grup.setObsolet(new Boolean(false));
				grupSvc.create(grup);
			}
	
			grup = grupSvc.findGrupByCodiGrup("enterprise"); //$NON-NLS-1$
			if (grup == null)
			{
				grup = new Grup();
				grup.setCodi("enterprise"); //$NON-NLS-1$
				grup.setCodiPare("world"); //$NON-NLS-1$
				grup.setDescripcio("Entrprise"); //$NON-NLS-1$
				grup.setObsolet(new Boolean(false));
				grupSvc.create(grup);
			}
			;
			grup = grupSvc.findGrupByCodiGrup("admingroup"); //$NON-NLS-1$
			if (grup == null)
			{
				grup = new Grup();
				grup.setCodi("admingroup"); //$NON-NLS-1$
				grup.setCodiPare("enterprise"); //$NON-NLS-1$
				grup.setDescripcio("Entrprise Administrators"); //$NON-NLS-1$
				grup.setObsolet(new Boolean(false));
				grupSvc.create(grup);
			}
	
			TipusDada td = tdSvc.findTipusDadaByCodi("NIF"); //$NON-NLS-1$
			if (td == null)
			{
				td = new TipusDada();
				td.setCodi("NIF"); //$NON-NLS-1$
				td.setOrdre(new Long(1));
				tdSvc.create(td);
			}
	
			Usuari usu = usuariSvc.findUsuariByCodiUsuari("admin"); //$NON-NLS-1$
			if (usu == null)
			{
				usu = new Usuari();
				usu.setCodi("admin"); //$NON-NLS-1$
				usu.setCodiGrupPrimari("admingroup"); //$NON-NLS-1$
				usu.setComentari("Autocreated"); //$NON-NLS-1$
				usu.setMultiSessio(new Boolean(true));
				usu.setNom("Admin"); //$NON-NLS-1$
				usu.setPrimerLlinatge("Admin"); //$NON-NLS-1$
				usu.setServidorHome("null"); //$NON-NLS-1$
				usu.setServidorPerfil("null"); //$NON-NLS-1$
				usu.setServidorCorreu("null"); //$NON-NLS-1$
				usu.setTipusUsuari("I"); //$NON-NLS-1$
				usu.setActiu(true);
				usu = usuariSvc.create(usu);
	
				internalPassSvc.storePassword(usu.getCodi(), dc.getCodi(),
						"changeit", false); //$NON-NLS-1$
			}
	
			UserAccount account = null;
			for (UserAccount ua : accountSvc.listUserAccounts(usu))
			{
				if (ua.getDispatcher().equals(dis.getCodi()))
				{
					account = ua;
					break;
				}
			}
			if (account == null)
			{
				account = new UserAccount();
				account.setName("admin");
				account.setDispatcher(dis.getCodi());
				account.setUser(usu.getCodi());
				account.setType(AccountType.USER);
				try
				{
					account = accountSvc.createAccount(usu, dis, null);
					assertEquals(account.getName(), "admin");
				}
				catch (AccountAlreadyExistsException e)
				{
					throw new InternalErrorException(
							"Error creating administrator account", e);
				}
			}
	
			Collection<AutoritzacioRol> auts = autSvc
					.getRolsAutoritzacio(Security.AUTO_AUTHORIZATION_ALL);
			if (auts.isEmpty())
			{
				AutoritzacioRol aut = new AutoritzacioRol();
				aut.setRol(rol);
				aut.setAutoritzacio(Security.AUTO_AUTHORIZATION_ALL);
				autSvc.create(aut);
			}
	
			boolean found = false;
			for (RolAccount ru : appSvc.findRolsUsuarisByCodiUsuariAndNomRol(
					usu.getCodi(), rol.getNom()))
			{
				if (ru.getBaseDeDades().equals(rol.getBaseDeDades()))
					found = true;
			}
			if (!found)
			{
				RolAccount ru = new RolAccount();
				ru.setBaseDeDades(rol.getBaseDeDades());
				ru.setCodiAplicacio(app.getCodi());
				ru.setCodiUsuari(usu.getCodi());
				ru.setNomRol(rol.getNom());
				ru.setAccountName(account.getName());
				appSvc.create(ru);
			}
	
			cfg = configSvc.findParametreByCodiAndCodiXarxa(
					"seycon.server.list", null); //$NON-NLS-1$
			if (cfg == null)
			{
				cfg = new Configuracio("seycon.server.list", "https://" //$NON-NLS-1$ //$NON-NLS-2$
						+ System.getProperty("hostName") + "." //$NON-NLS-1$ //$NON-NLS-2$
						+ System.getProperty("domainName") + ":760/"); //$NON-NLS-1$ //$NON-NLS-2$
				configSvc.create(cfg);
			}
	
			cfg = configSvc.findParametreByCodiAndCodiXarxa("SSOServer", null); //$NON-NLS-1$
			if (cfg == null)
			{
	
				cfg = new Configuracio(
						"SSOServer", System.getProperty("hostName") + "." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								+ System.getProperty("domainName")); //$NON-NLS-1$
				configSvc.create(cfg);
			}
	
		}
	
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	
		Security.nestedLogin("Test", new String[] { 
				Security.AUTO_AUTHORIZATION_ALL });
		try {
			setupdb();
		} finally {
			Security.nestedLogoff();
		}
	
	}

}