package es.caib.seycon.ng.test;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.DominiContrasenya;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.TipusUnitatOrganitzativa;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.servei.TipusUnitatOrganitzativaService;

public class RoleDependencyTest extends AbstractTest
{

	private TipusUnitatOrganitzativaService tuoSvc;

	public void testMultiRole () throws InternalErrorException
	{
		com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");
		Security.nestedLogin("Test", new String[] {Security.AUTO_AUTHORIZATION_ALL});
		try {
			DominiContrasenya dc = dominiSvc.findDominiContrasenyaByCodi("DEFAULT");
			
			Dispatcher dis = new Dispatcher ();
			dis.setBasRol(new Boolean(false));
			dis.setCodi("dis1"); //$NON-NLS-1$
			dis.setControlAccess(new Boolean(false));
			dis.setDominiContrasenyes(dc.getCodi());
			dis.setDominiUsuaris("DEFAULT");
			dis.setIdDominiContrasenyes(dc.getId());
			dis.setNomCla("- no class -"); //$NON-NLS-1$
			dis.setRelacioLaboral("I"); //$NON-NLS-1$
			dis = dispatcherSvc.create(dis);
			
			Dispatcher dis2 = new Dispatcher ();
			dis2.setBasRol(new Boolean(true));
			dis2.setCodi("dis2"); //$NON-NLS-1$
			dis2.setUrl("local");
			dis2.setControlAccess(new Boolean(false));
			dis2.setDominiContrasenyes(dc.getCodi());
			dis2.setDominiUsuaris("DEFAULT");
			dis2.setIdDominiContrasenyes(dc.getId());
			dis2.setNomCla("- no class -"); //$NON-NLS-1$
			dis2.setRelacioLaboral("I"); //$NON-NLS-1$
			dis2 = dispatcherSvc.create(dis2);

			Usuari u = new Usuari ();
			u.setCodi("user1");
			u.setNom("user1");
			u.setPrimerLlinatge("user1");
			u.setServidorCorreu("null");
			u.setServidorHome("null");
			u.setServidorPerfil("null");
			u.setCodiGrupPrimari("enterprise");
			u.setActiu(true);
			u.setTipusUsuari("I");
			u = usuariSvc.create(u);


			Aplicacio app = appSvc.findAplicacioByCodiAplicacio("SOFFID"); //$NON-NLS-1$

			Rol rol1 = createRol(dis, app, "ROL_1");
			Rol rol2 = createRol(dis, app, "ROL_2");
			Rol rol3 = createRol(dis, app, "ROL_3");
			Rol rol4 = createRol(dis2, app, "ROL_4");
			Rol rol5 = createRol(dis, app, "ROL_5");
			
			
			
			grant (u, rol1);
			grant (u, rol2);
			RolGrant rg = new RolGrant();
			rg.setIdRol(rol3.getId());
			rg.setDispatcher(rol3.getBaseDeDades());
			rg.setRolName(rol3.getNom());
			rg.setDomainValue(null);
			rg.setHasDomain(false);
			rg.setOwnerRol(rol2.getId());
			rg.setOwnerDispatcher(rol2.getBaseDeDades());
			rg.setOwnerRolName(rol2.getNom());
			rol3.getOwnerRoles().add(rg);
			appSvc.update(rol3);

			rg = new RolGrant();
			rg.setOwnerGroup(grupSvc.findGrupByCodiGrup("enterprise").getCodi());
			rg.setIdRol(rol4.getId());
			rg.setDispatcher(rol4.getBaseDeDades());
			rg.setRolName(rol4.getNom());
			rg.setDomainValue(null);
			rg.setHasDomain(false);
			rol4.getGranteeGroups().add(rg);
			
			String f = appSvc.generateChangesReport(rol4);
			System.out.println("Generated changes report "+f);
			File file = new File(f);
			assertTrue(file.exists());;
			file.delete();
			appSvc.update(rol4);


			rg = new RolGrant();
			rg.setOwnerGroup(grupSvc.findGrupByCodiGrup("world").getCodi());
			rg.setIdRol(rol5.getId());
			rg.setDispatcher(rol5.getBaseDeDades());
			rg.setRolName(rol5.getNom());
			rg.setDomainValue(null);
			rg.setHasDomain(false);
			rol5.getGranteeGroups().add(rg);
			appSvc.update(rol5);

			List<UserAccount> accounts = accountSvc.listUserAccounts(u);
			for (UserAccount account: accounts)
			{
				System.out.println ("Account: "+account.getName()+" on "+account.getDispatcher());
				Collection<RolGrant> grants = appSvc.findRolGrantByAccount(account.getId());
				System.out.println (">>> Roles:");
				for (RolGrant ru : grants)
				{
					System.out.println (">>>   ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() + " account (" + ru.getOwnerAccountName()+")");
				}
				System.out.println ("<<< End");
			}
			assertEquals(accounts.size(), 1);
			
			System.out.println ("Direct grants");
			Collection<RolAccount> rols = appSvc.findRolsUsuarisByCodiUsuari(u.getCodi());
			for (RolAccount ru : rols)
			{
				System.out.println ("ROL Assigned: "+ru.getNomRol()+ " on " + ru.getBaseDeDades() + " account (" + ru.getAccountName()+")");
			}
			assertEquals(rols.size(), 2);


			System.out.println ("All grants");
			Collection<RolGrant> grants = appSvc.findEffectiveRolGrantByUser(u.getId());
			for (RolGrant ru : grants)
			{
				System.out.print ("ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() );
				if (ru.getOwnerAccountName() != null)
					System.out.println( " account (" + ru.getOwnerAccountName()+")");
				if (ru.getOwnerRolName() != null)
					System.out.println( " rol (" + ru.getOwnerRolName()+"@"+ru.getOwnerDispatcher()+")");
				if (ru.getOwnerGroup() != null)
					System.out.println( " group (" + ru.getOwnerGroup()+")");
			}
			assertEquals (grants.size(), 5);

			System.out.println ("Generate user accounts");
			accountSvc.generateUserAccounts(u.getCodi()); 
			accounts = accountSvc.listUserAccounts(u);
			for (UserAccount account: accounts)
			{
				System.out.println ("Account: "+account.getName()+" on "+account.getDispatcher() + "("+account.getId()+")");
				Collection<RolGrant> grants2 = appSvc.findRolGrantByAccount(account.getId());
				System.out.println (">>> Roles:");
				for (RolGrant ru : grants2)
				{
					System.out.println (">>>   ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() + " account (" + ru.getOwnerAccountName()+")");
				}
				if (account.getDispatcher().equals("dis1"))
					assertEquals(grants2.size(), 2);
				else
					assertEquals(grants2.size(), 0);
				

				grants2 = appSvc.findEffectiveRolGrantByAccount(account.getId());
				System.out.println (">>>  EfectiveRoles:");
				for (RolGrant ru : grants2)
				{
					System.out.println (">>>   ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() + " account (" + ru.getOwnerAccountName()+")");
				}
				System.out.println ("<<< End");
				if (account.getDispatcher().equals("dis1"))
					assertEquals(grants2.size(), 4);
				else
					assertEquals(grants2.size(), 1);
			}
			
			
			assertEquals(accounts.size(), 2);
			
			
			grants = appSvc.findEffectiveRolGrantsByRolId(rol3.getId());
			System.out.println ("Assignments for rol "+rol3.getNom());
			for (RolGrant ru : grants)
			{
				System.out.print ("ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() );
				if (ru.getOwnerAccountName() != null)
					System.out.println( " account (" + ru.getOwnerAccountName()+")");
				if (ru.getOwnerRolName() != null)
					System.out.println( " rol (" + ru.getOwnerRolName()+"@"+ru.getOwnerDispatcher()+")");
				if (ru.getOwnerGroup() != null)
					System.out.println( " group (" + ru.getOwnerGroup()+")");
			}
			
			assertEquals(grants.size(), 1);
		} finally {
			Security.nestedLogoff();
		}
	}

	private RolAccount grant (Usuari usu, Rol rol) throws InternalErrorException
	{
		return grant (usu, rol, null);
	}
	
	private RolAccount grant (Usuari usu, Rol rol, String holderGroup) throws InternalErrorException
	{
		boolean found = false;
		for (UserAccount acc: accountSvc.getUserAccounts(usu))
		{
			if (acc.getDispatcher().equals (rol.getBaseDeDades()))
			{
				for (RolAccount ru : appSvc.findRolAccountByAccount(acc.getId())) {
		            if (ru.getBaseDeDades().equals(rol.getBaseDeDades()) &&
		            		ru.getNomRol().equals(rol.getNom()))
		            	return ru;
		        }
			}
		}

		RolAccount ru = new RolAccount();
		ru.setBaseDeDades(rol.getBaseDeDades());
		ru.setCodiAplicacio(rol.getCodiAplicacio());
		ru.setCodiUsuari(usu.getCodi());
		ru.setNomRol(rol.getNom());
		ru.setHolderGroup(holderGroup);
		appSvc.create(ru);
		return ru;
	
	}

	private Rol createRol(Dispatcher dis, Aplicacio app, String name)
			throws InternalErrorException
	{
		Rol rol = appSvc.findRolByNomRolAndCodiAplicacioAndCodiDispatcher(
				name, //$NON-NLS-1$
				app.getCodi(), dis.getCodi()); //$NON-NLS-1$
		if (rol == null)
		{
			rol = new Rol();
			rol.setCodiAplicacio(app.getCodi());
			rol.setBaseDeDades(dis.getCodi()); //$NON-NLS-1$
			rol.setContrasenya(new Boolean(false));
			rol.setDefecte(new Boolean(true));
			rol.setDescripcio(name); //$NON-NLS-1$
			rol.setGestionableWF(new Boolean(false));
			rol.setNom(name); //$NON-NLS-1$
			rol.setDomini(new Domini());
			rol = appSvc.create(rol);
		}
		return rol;
	}

	public int countRoles (Usuari u) throws InternalErrorException
	{
		List<UserAccount> accounts = accountSvc.listUserAccounts(u);
		int i = 0;
		for (UserAccount account: accounts)
		{
			System.out.println ("Account: "+account.getName()+" on "+account.getDispatcher());
			Collection<RolGrant> grants = appSvc.findRolGrantByAccount(account.getId());
			System.out.println (">>> Roles:");
			for (RolGrant ru : grants)
			{
				System.out.println (">>>   ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() + " account (" + ru.getOwnerAccountName()+")");
				i ++;
			}
			System.out.println ("<<< End");
		}
		return i;
	}
	
	public void testRoleHolder () throws InternalErrorException
	{
		
		Security.nestedLogin("Test", new String[] {Security.AUTO_AUTHORIZATION_ALL});
		try {
			System.setProperty("soffid.entitlement.group.holder", "optional");
			DominiContrasenya dc = dominiSvc.findDominiContrasenyaByCodi("DEFAULT");
			
			Dispatcher dis = dispatcherSvc.findDispatcherByCodi("soffid");
			assertNotNull(dis);

			tuoSvc = ServiceLocator.instance().getTipusUnitatOrganitzativaService();
			TipusUnitatOrganitzativa tuo = new TipusUnitatOrganitzativa();
			tuo.setCodi("grup");
			tuo.setDescripcio("Grup");
			tuo.setRoleHolder(true);
			tuoSvc.create(tuo);
			
			Grup gr = new Grup ();
			gr.setCodi("group1");
			gr.setDescripcio("Group1");
			gr.setCodiPare("enterprise");
			gr.setTipus(tuo.getCodi());
			grupSvc.create(gr);
			
			
			Usuari u = new Usuari ();
			u.setCodi("user2");
			u.setNom("user2");
			u.setPrimerLlinatge("user2");
			u.setServidorCorreu("null");
			u.setServidorHome("null");
			u.setServidorPerfil("null");
			u.setCodiGrupPrimari(gr.getCodi());
			u.setActiu(true);
			u.setTipusUsuari("I");
			u = usuariSvc.create(u);


			Aplicacio app = appSvc.findAplicacioByCodiAplicacio("SOFFID"); //$NON-NLS-1$

			Rol rol1 = createRol(dis, app, "TEST_ROL_1");
			
			// Test 1 => Remove from priamry group
			grant (u, rol1, gr.getCodi());

			assertEquals(countRoles(u), 1);

			u.setCodiGrupPrimari("enterprise");
			usuariSvc.update(u);
			
			assertEquals(countRoles(u), 0);

			// Test 2 => Remove from secondary group
			grupSvc.addGrupToUsuari(u.getCodi(), gr.getCodi());

			grant (u, rol1, gr.getCodi());

			assertEquals(countRoles(u), 1);

			grupSvc.removeGrupFromUsuari(u.getCodi(), gr.getCodi());
			assertEquals(countRoles(u), 0);

			// Test 3 => Remove from secondary group but keep primary group
			u.setCodiGrupPrimari(gr.getCodi());
			usuariSvc.update(u);
			grupSvc.addGrupToUsuari(u.getCodi(), gr.getCodi());
			grant (u, rol1, gr.getCodi());

			assertEquals(countRoles(u), 1);

			grupSvc.removeGrupFromUsuari(u.getCodi(), gr.getCodi());
			assertEquals(countRoles(u), 1);
			u.setCodiGrupPrimari("enterprise");
			usuariSvc.update(u);
			assertEquals(countRoles(u), 0);

			// Test 3 => Remove from primary group but keep secondary group
			u.setCodiGrupPrimari(gr.getCodi());
			usuariSvc.update(u);
			grupSvc.addGrupToUsuari(u.getCodi(), gr.getCodi());
			grant (u, rol1, gr.getCodi());

			assertEquals(countRoles(u), 1);

			u.setCodiGrupPrimari("enterprise");
			usuariSvc.update(u);
			assertEquals(countRoles(u), 1);
			grupSvc.removeGrupFromUsuari(u.getCodi(), gr.getCodi());
			assertEquals(countRoles(u), 0);
		} finally {
			Security.nestedLogoff();
		}
	}

	public void testTranistive () throws InternalErrorException, NeedsAccountNameException, AccountAlreadyExistsException
	{
		Security.nestedLogin("Test", new String[] {Security.AUTO_AUTHORIZATION_ALL});
		try {
			DominiContrasenya dc = dominiSvc.findDominiContrasenyaByCodi("DEFAULT");
			
			Dispatcher dis = new Dispatcher ();
			dis.setBasRol(new Boolean(false));
			dis.setCodi("dis4"); //$NON-NLS-1$
			dis.setControlAccess(new Boolean(false));
			dis.setDominiContrasenyes(dc.getCodi());
			dis.setDominiUsuaris("DEFAULT");
			dis.setIdDominiContrasenyes(dc.getId());
			dis.setNomCla("- no class -"); //$NON-NLS-1$
			dis.setRelacioLaboral("I"); //$NON-NLS-1$
			dis = dispatcherSvc.create(dis);
			
			Grup g = new Grup ();
			g.setCodi("g1");
			g.setCodiPare("enterprise");
			g.setDescripcio("Group 1");
			grupSvc.create(g);
			
			Usuari u = new Usuari ();
			u.setCodi("user3");
			u.setNom("user3");
			u.setPrimerLlinatge("user3");
			u.setServidorCorreu("null");
			u.setServidorHome("null");
			u.setServidorPerfil("null");
			u.setCodiGrupPrimari(g.getCodi());
			u.setActiu(true);
			u.setTipusUsuari("I");
			u = usuariSvc.create(u);
			
			accountSvc.createAccount(u, dis, "user3");


			Aplicacio app = appSvc.findAplicacioByCodiAplicacio("SOFFID"); //$NON-NLS-1$

			Rol rol1 = createRol(dis, app, "ROLE_1");
			Rol rol2 = createRol(dis, app, "ROLE_2");
			Rol rol3 = createRol(dis, app, "ROLE_3");
			Rol rol5 = createRol(dis, app, "ROLE_4");
			
			
			// role3 => role2 => group world => user u
			RolGrant rg = new RolGrant();
			rg.setOwnerGroup(g.getCodi());
			rg.setIdRol(rol2.getId());
			rg.setDispatcher(rol2.getBaseDeDades());
			rg.setRolName(rol2.getNom());
			rg.setDomainValue(null);
			rg.setHasDomain(false);
			rol2.getGranteeGroups().add(rg);
			rol2.getOwnerGroups().add(g);
			appSvc.update(rol2);

			rg = new RolGrant();
			rg.setOwnerRol(rol2.getId());
			rg.setIdRol(rol3.getId());
			rg.setDispatcher(rol3.getBaseDeDades());
			rg.setRolName(rol3.getNom());
			rg.setDomainValue(null);
			rg.setHasDomain(false);
			rol3.getOwnerRoles().add(rg);
			appSvc.update(rol3);

			System.out.println ("User with granted role 2");
			Collection<RolGrant> rols = appSvc.findEffectiveRolGrantsByRolId(rol2.getId());
			for (RolGrant ru : rols)
			{
				System.out.println ("ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() + " account (" + ru.getOwnerAccountName()+")");
			}
			assertEquals(1, rols.size());
			System.out.println ("User with granted role 3");
			rols = appSvc.findEffectiveRolGrantsByRolId(rol3.getId());
			for (RolGrant ru : rols)
			{
				System.out.println ("ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() + " account (" + ru.getOwnerAccountName()+")");
			}
			assertEquals(1, rols.size());
			
			System.out.println ("Roles granted to user user3");
			rols = appSvc.findEffectiveRolGrantByUser(u.getId());
			for (RolGrant ru : rols)
			{
				System.out.println ("ROL Assigned: "+ru.getRolName()+ " on " + ru.getDispatcher() + " account (" + ru.getOwnerAccountName()+")");
			}
			assertEquals(true, rols.size() >= 2);

		} finally {
			Security.nestedLogoff();
		}
	}
}
