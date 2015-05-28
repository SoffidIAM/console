package es.caib.seycon.ng.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.model.AccountMetadataEntity;

import es.caib.seycon.net.SeyconServiceLocator;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.GrupEntityDao;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.Messages;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariGrupEntity;
import es.caib.seycon.ng.model.UsuariImpressoraEntity;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.InternalPasswordService;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.servei.XarxaService;
import es.caib.seycon.ng.servei.XarxaServiceImpl;
import es.caib.seycon.ng.sync.servei.ServerService;

public class AutoritzacionsUsuari
{

	public static Usuari getCurrentUsuari () throws InternalErrorException
	{
		InternalPasswordService ips = ServiceLocator.instance()
						.getInternalPasswordService();
		AccountService as = ServiceLocator.instance().getAccountService();
		UsuariService us = ServiceLocator.instance().getUsuariService();
		String dispatcher = ips.getDefaultDispatcher();
		Account caller = as.findAccount(Security.getPrincipal().getName(), dispatcher);
		if (caller == null)
			return null;
		if (caller instanceof UserAccount)
		{
			String codi = ((UserAccount) caller).getUser();
			return us.findUsuariByCodiUsuari(codi);
		}
		return null;
	}

	/*
	 * A NIVELL D'USUARI
	 */

	public static boolean hasCreateUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_CREATE);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canCreateUser (Usuari usuari, GrupEntityDao grupEntityDao)
	{
		// user:create [GRUPS]
		// Sólo es necesario comprobar los grupos primarios y
		// secundarios, no sus padres (ya están en la lista de
		// autorizaciones)
		boolean trobat = false;
		if (usuari.getCodiGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_CREATE + "/" //$NON-NLS-1$
										+ usuari.getCodiGrupPrimari()))
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			Collection grupsSecundaris = grupEntityDao
							.findGrupsFromUsuarisByCodiUsuari(usuari.getCodi()); // GrupEntity
			for (Iterator itGS = grupsSecundaris.iterator(); !trobat && itGS.hasNext();)
			{
				GrupEntity grupS = (GrupEntity) itGS.next();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_CREATE
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					return true;
			}
		}

		return false;

	}

	/*
	 * Determina si l'usuari té permis per crear usuaris d'aquest grup
	 */
	public static boolean canCreateUsersOnGroup (String codiGrup)
	{
		// user:create [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_CREATE + "/" + codiGrup); //$NON-NLS-1$

	}

	public static boolean hasUpdateUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_UPDATE);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canUpdateUser (Usuari usuari, GrupEntityDao grupEntityDao) throws InternalErrorException
	{
		Usuari currentUser = getCurrentUsuari();
		
        if (currentUser != null && currentUser.getId().equals(usuari.getId()))
        	return false;
		// user:update [GRUPS]
		// Sólo es necesario comprobar los grupos primarios y
		// secundarios, no sus padres (ya están en la lista de
		// autorizaciones)

		// Si pot actualitzar tots els usuaris
		if (Security.isUserInRole(Security.AUTO_USER_UPDATE + Security.AUTO_ALL))
			return true;

		boolean trobat = false;
		if (usuari.getCodiGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_UPDATE + "/" //$NON-NLS-1$
										+ usuari.getCodiGrupPrimari()))
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			// Retorna grupEntitys:
			Collection grupsSecundaris = grupEntityDao
							.findGrupsFromUsuarisByCodiUsuari(usuari.getCodi());
			for (Iterator itGS = grupsSecundaris.iterator(); !trobat && itGS.hasNext();)
			{
				GrupEntity grupS = (GrupEntity) itGS.next();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_UPDATE
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					return true;
			}
		}

		return false;

	}

	@SuppressWarnings ("rawtypes")
	public static boolean canDeleteUser (Usuari usuari, GrupEntityDao grupEntityDao)
	{
		// user:delete [GRUPS]
		// Sólo es necesario comprobar los grupos primarios y
		// secundarios, no sus padres (ya están en la lista de
		// autorizaciones)
		boolean trobat = false;
		if (usuari.getCodiGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_DELETE + "/" //$NON-NLS-1$
										+ usuari.getCodiGrupPrimari()))
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			// retorna grupEntity
			Collection grupsSecundaris = grupEntityDao
							.findGrupsFromUsuarisByCodiUsuari(usuari.getCodi());
			for (Iterator itGS = grupsSecundaris.iterator(); !trobat && itGS.hasNext();)
			{
				GrupEntity grupS = (GrupEntity) itGS.next();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_DELETE
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					return true;
			}
		}

		return false;

	}

	public static boolean hasUpdateCustomUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_UPDATE_CUSTOM);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canUpdateCustomUser (UsuariEntity userEntity)
	{
		// user:custom:update [GRUPS]
		// Sólo es necesario comprobar los grupos primarios y
		// secundarios, no sus padres (ya están en la lista de
		// autorizaciones)

		// Si pot actualitzar tots els usuaris
		if (Security.isUserInRole(Security.AUTO_USER_UPDATE_CUSTOM + Security.AUTO_ALL))
			return true;

		if (userEntity.getGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_UPDATE_CUSTOM + "/" //$NON-NLS-1$
										+ userEntity.getGrupPrimari().getCodi()))
			return true;

		Collection grupsSecundaris = userEntity.getGrupsSecundaris();
		if (grupsSecundaris != null)
			for (Iterator itGS = grupsSecundaris.iterator(); itGS.hasNext();)
			{
				UsuariGrupEntity ug = (UsuariGrupEntity) itGS.next();
				GrupEntity grupS = ug.getGrup();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_UPDATE_CUSTOM
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					return true;
			}
		return false;
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canUpdateCustomUser (Usuari usuari, GrupEntityDao grupEntityDao)
	{
		// user:custom:update [GRUPS]
		// Sólo es necesario comprobar los grupos primarios y
		// secundarios, no sus padres (ya están en la lista de
		// autorizaciones)
		boolean trobat = false;
		if (usuari.getCodiGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_UPDATE_CUSTOM + "/" //$NON-NLS-1$
										+ usuari.getCodiGrupPrimari()))
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			// retorna grupEntity
			Collection grupsSecundaris = grupEntityDao
							.findGrupsFromUsuarisByCodiUsuari(usuari.getCodi());
			for (Iterator itGS = grupsSecundaris.iterator(); !trobat && itGS.hasNext();)
			{
				GrupEntity grupS = (GrupEntity) itGS.next();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_UPDATE_CUSTOM
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					return true;
			}
		}
		return false;
	}

	public static boolean hasUpdateUserPassword ()
	{
		return Security.isUserInRole(Security.AUTO_USER_UPDATE_PASSWORD);
	}

	public static boolean hasQueryUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_QUERY);
	}

	public static boolean hasRefreshUser ()
	{
		return Security.isUserInRole(Security.AUTO_USER_PROPAGATE);
	}

	public static boolean hasQueryUserRole ()
	{
		return Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY);
	}

	public static boolean canQueryAllUserRole ()
	{
		return Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY + Security.AUTO_ALL);
	}

	@SuppressWarnings ("rawtypes")
	public static Collection<ContenidorRol> filtraContenidorRolCanQuery (
					String codiUsuari, Collection<ContenidorRol> contenidorsRol,
					GrupEntityDao grupEntityDao)
	{

		// user:role:query [SENSE_DOMINI, GRUPS, APLICACIONS]
		if (canQueryAllUserRole())
		{
			return contenidorsRol;
		}

		// GRUPS: si te un rol dels grups de l'usuari, pot veure TOTS els seus
		// rols heretats
		GrupEntity grupPrimari = grupEntityDao.findGrupPrimariByCodiUsuari(codiUsuari);
		if (grupPrimari != null)
		{
			if (Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY + "/" //$NON-NLS-1$
							+ grupPrimari.getCodi()))
				return contenidorsRol;
		}
		// Grupos secundarios
		// retorna grupentity
		Collection grupsFromUsuaris = grupEntityDao
						.findGrupsFromUsuarisByCodiUsuari(codiUsuari);
		for (Iterator it = grupsFromUsuaris.iterator(); it.hasNext();)
		{
			GrupEntity g = (GrupEntity) it.next();
			if (g != null
							&& Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY
											+ "/" + g.getCodi())) //$NON-NLS-1$
				return contenidorsRol;
		}

		// APLICACIONS: només els rols de les aplicacions on té l'autorització
		Collection<ContenidorRol> contenidorRolFiltrats = new ArrayList<ContenidorRol>();

		// Filtrem només els rols que pot veure l'usuari
		for (Iterator<ContenidorRol> it = contenidorsRol.iterator(); it.hasNext();)
		{
			ContenidorRol contenidor = it.next();
			String infoRol = contenidor.getInfoContenidor();
			String appRol = infoRol.substring(infoRol.indexOf('>') + 1);
			if (Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY + "/" //$NON-NLS-1$
							+ appRol))
				contenidorRolFiltrats.add(contenidor);
		}

		return contenidorRolFiltrats;
	}

	public static boolean hasQueryUserSession ()
	{
		// user:session:query [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_SESSION_QUERY);
	}

	public static boolean canQueryAllUserSession ()
	{
		// user:session:query [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_SESSION_QUERY
						+ Security.AUTO_ALL);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canQueryUserSession (Usuari usuari, GrupEntityDao grupEntityDao)
	{
		// user:session:query [GRUPS]
		if (canQueryAllUserSession())
			return true;

		// Mirem a nivell de grups
		boolean trobat = false;
		if (usuari.getCodiGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_SESSION_QUERY + "/" //$NON-NLS-1$
										+ usuari.getCodiGrupPrimari()))
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			// Retorna grupEntitys:
			Collection grupsSecundaris = grupEntityDao
							.findGrupsFromUsuarisByCodiUsuari(usuari.getCodi());
			for (Iterator itGS = grupsSecundaris.iterator(); !trobat && itGS.hasNext();)
			{
				GrupEntity grupS = (GrupEntity) itGS.next();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_SESSION_QUERY
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					return true;
			}
		}

		return false;
	}

	/*
	 * public static boolean canCreateUserMetadata() { return
	 * Security.isUserInRole(Security.AUTO_USER_METADATA_CREATE); }
	 */
	public static boolean hasUpdateUserMetadata ()
	{
		// user:metadata:update [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_METADATA_UPDATE);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canUpdateUserMetadata (UsuariEntity usuari)
	{
		return canUpdateUserMetadata(usuari,Security.AUTO_USER_METADATA_UPDATE  );
	}
	
	@SuppressWarnings ("rawtypes")
	public static boolean canUpdateAccountMetadata (UsuariEntity usuari)
	{
		return canUpdateUserMetadata(usuari,Security.AUTO_ACCOUNT_ATTRIBUTE_UPDATE  );
	}
	
	@SuppressWarnings ("rawtypes")
	public static boolean canQueryAccountMetadata (UsuariEntity usuari)
	{
		return canUpdateUserMetadata(usuari,Security.AUTO_ACCOUNT_ATTRIBUTE_QUERY  );
	}
	
	@SuppressWarnings ("rawtypes")
	public static boolean canUpdateUserMetadata (UsuariEntity usuari, String auth)
	{
		// user:metadata:update [GRUPS]
		if (! hasUpdateUserMetadata())
			return false;

		// Si pot actualitzar tots els usuaris
		if (Security.isUserInRole(auth + Security.AUTO_ALL))
			return true;

		boolean trobat = false;
		if (usuari.getGrupPrimari() != null
						&& Security.isUserInRole(auth
										+ "/" + usuari.getGrupPrimari().getCodi())) //$NON-NLS-1$
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			// Retorna grupEntitys:
			Collection grupsSecundaris = usuari.getGrupsSecundaris();
			if (grupsSecundaris != null)
				for (Iterator itGS = grupsSecundaris.iterator(); !trobat
								&& itGS.hasNext();)
				{
					UsuariGrupEntity ug = (UsuariGrupEntity) itGS.next();
					GrupEntity grupS = ug.getGrup();
					if (grupS != null
									&& Security.isUserInRole(auth
													+ "/" + grupS.getCodi())) //$NON-NLS-1$
						return true;
				}
		}

		return false;
	}

	public static boolean canUpdateAllUserMetadata ()
	{
		// user:metadata:update [GRUPS]
		return Security.isUserInRole(Security.AUTO_USER_METADATA_UPDATE
						+ Security.AUTO_ALL);
	}

	public static boolean hasCreateUserRole ()
	{
		return Security.isUserInRole(Security.AUTO_USER_ROLE_CREATE);
	}

	public static boolean hasDeleteUserRole ()
	{
		return Security.isUserInRole(Security.AUTO_USER_ROLE_DELETE);
	}

	public static boolean hasCreateUserGroup ()
	{
		// user:group:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_GROUP_CREATE);
	}

	public static boolean canCreateAllUserGroup ()
	{
		// user:group:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_GROUP_CREATE + Security.AUTO_ALL);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canCreateUserGroup (UsuariEntity usuari)
	{
		// user:group:create [GRUPS] - children
		if (canCreateAllUserGroup())
			return true;

		if (usuari.getGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_GROUP_CREATE + "/" //$NON-NLS-1$
										+ usuari.getGrupPrimari().getCodi()))
			return true;
		Collection grupsSecundaris = usuari.getGrupsSecundaris(); // UsuariGrupEntity
		if (grupsSecundaris != null)
			for (Iterator itGS = grupsSecundaris.iterator(); itGS.hasNext();)
			{
				UsuariGrupEntity usuGrupActual = (UsuariGrupEntity) itGS.next();
				GrupEntity grupS = usuGrupActual.getGrup();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_GROUP_CREATE
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					return true;
			}
		return false;
	}

	public static boolean hasDeleteUserGroup ()
	{
		// user:group:delete [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_GROUP_DELETE);
	}

	public static boolean canDeleteAllUserGroup ()
	{
		// user:group:delete [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_GROUP_DELETE + Security.AUTO_ALL);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canDeleteUserGroup (UsuariEntity usuari)
	{
		// user:group:delete [GRUPS] - children
		if (canDeleteAllUserGroup())
			return true;

		if (usuari.getGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_GROUP_DELETE + "/" //$NON-NLS-1$
										+ usuari.getGrupPrimari().getCodi()))
			return true;
		Collection grupsSecundaris = usuari.getGrupsSecundaris(); // UsuariGrupEntity
		if (grupsSecundaris != null)
			for (Iterator itGS = grupsSecundaris.iterator(); itGS.hasNext();)
			{
				UsuariGrupEntity usuGrupActual = (UsuariGrupEntity) itGS.next();
				GrupEntity grupS = usuGrupActual.getGrup();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_GROUP_DELETE
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					return true;
			}

		return false;

	}

	// A nivell de ZUL (si ha d'apareixer el butó)
	public static boolean hasCreateUserPrinter ()
	{
		// user:printer:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_PRINTER_CREATE)
						|| Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_CREATE);
	}

	public static boolean canCreateAllUserPrinter ()
	{
		// user:printer:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_PRINTER_CREATE
						+ Security.AUTO_ALL);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canCreateUserPrinter (
					UsuariImpressoraEntity usuariImpressoraEntity,
					XarxaService xarxaService) throws InternalErrorException
	{
		// user:printer:create [GRUPS] - children
		if (canCreateAllUserPrinter())
			return true;

		UsuariEntity usuari = usuariImpressoraEntity.getUsuari();
		// Només ho comprovem si en té l'autorització
		if (usuari != null && Security.isUserInRole(Security.AUTO_USER_PRINTER_CREATE))
		{
			if (usuari.getGrupPrimari() != null
							&& Security.isUserInRole(Security.AUTO_USER_PRINTER_CREATE
											+ "/" + usuari.getGrupPrimari().getCodi())) //$NON-NLS-1$
				return true;
			Collection grupsSecundaris = usuari.getGrupsSecundaris(); // UsuariGrupEntity
			if (grupsSecundaris != null)
				for (Iterator itGS = grupsSecundaris.iterator(); itGS.hasNext();)
				{
					UsuariGrupEntity usuGrupActual = (UsuariGrupEntity) itGS.next();
					GrupEntity grupS = usuGrupActual.getGrup();
					if (grupS != null
									&& Security.isUserInRole(Security.AUTO_USER_PRINTER_CREATE
													+ "/" + grupS.getCodi())) //$NON-NLS-1$
						return true;
				}
		}

		// Permís a les impressores amb ACL (a nivell de la xarxa servidor
		// d'impressores)
		// Només ho comprovem si en té l'autorització
		if (Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_CREATE))
		{
			// Obtenim el nivell d'accés a la màquina servidora d'impressores
			MaquinaEntity serverImp = usuariImpressoraEntity.getImpressora()
							.getServidor();
			Long nivell = xarxaService.findNivellAccesByNomMaquinaAndCodiXarxa(
							serverImp.getNom(), serverImp.getXarxa().getCodi());

			// Nivell mínim: suport
			if (nivell >= XarxaServiceImpl.SUPORT)
				return true;

		}

		return false;
	}

	// A nivell de ZUL (si ha d'apareixer el butó)
	public static boolean hasDeleteUserPrinter ()
	{ // Totes les impressores o
	  // les que en té ACL
		return Security.isUserInRole(Security.AUTO_USER_PRINTER_DELETE)
						|| Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_DELETE);
	}

	public static boolean canDeleteAllUserPrinter ()
	{
		// user:printer:delete [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_PRINTER_DELETE
						+ Security.AUTO_ALL);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canDeleteUserPrinter (
					UsuariImpressoraEntity usuariImpressoraEntity,
					XarxaService xarxaService) throws InternalErrorException
	{
		// user:printer:delete [GRUPS] - children
		if (canDeleteAllUserPrinter())
			return true;

		// Només ho comprovem si en té l'autorització
		if (Security.isUserInRole(Security.AUTO_USER_PRINTER_DELETE))
		{
			// Permís de TOTES les impressores (a nivell de grup)
			UsuariEntity usuari = usuariImpressoraEntity.getUsuari();
			if (usuari != null)
			{
				if (usuari.getGrupPrimari() != null
								&& Security.isUserInRole(Security.AUTO_USER_PRINTER_DELETE
												+ "/" //$NON-NLS-1$
												+ usuari.getGrupPrimari().getCodi()))
					return true;

				Collection grupsSecundaris = usuari.getGrupsSecundaris(); // UsuariGrupEntity
				if (grupsSecundaris != null)
					for (Iterator itGS = grupsSecundaris.iterator(); itGS.hasNext();)
					{
						UsuariGrupEntity usuGrupActual = (UsuariGrupEntity) itGS.next();
						GrupEntity grupS = usuGrupActual.getGrup();
						if (grupS != null
										&& Security.isUserInRole(Security.AUTO_USER_PRINTER_DELETE
														+ "/" + grupS.getCodi())) //$NON-NLS-1$
							return true;
					}
			}
		}

		// Permís a les impressores amb ACL (a nivell de la xarxa servidor
		// d'impressores)
		// Només ho comprovem si en té l'autorització
		if (Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_DELETE))
		{
			// Obtenim el nivell d'accés a la màquina servidora d'impressores
			MaquinaEntity serverImp = usuariImpressoraEntity.getImpressora()
							.getServidor();
			Long nivell = xarxaService.findNivellAccesByNomMaquinaAndCodiXarxa(
							serverImp.getNom(), serverImp.getXarxa().getCodi());

			// Nivell mínim: suport
			if (nivell >= XarxaServiceImpl.SUPORT)
				return true;
		}

		return false;
	}

	public static boolean hasQueryUserAccessRegister ()
	{
		// user:accessRegister:query [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_ACCESSREGISTER_QUERY);
	}

	public static boolean hasQueryUserMazinger ()
	{
		// user:mazinger:query [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_MAZINGER_QUERY);
	}

	public static boolean canQueryAllUserAccessRegister ()
	{
		// user:accessRegister:query [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_USER_ACCESSREGISTER_QUERY
						+ Security.AUTO_ALL);
	}

	@SuppressWarnings ("rawtypes")
	public static boolean canQueryUserAccessRegister (String codiUsuari,
					GrupEntityDao grupEntityDao)
	{
		// user:accessRegister:query [GRUPS] - children
		if (canQueryAllUserAccessRegister())
			return true;

		// Grupo Primario
		GrupEntity grupPrimari = grupEntityDao.findGrupPrimariByCodiUsuari(codiUsuari);
		if (grupPrimari != null)
		{
			if (Security.isUserInRole(Security.AUTO_USER_ACCESSREGISTER_QUERY
							+ "/" + grupPrimari.getCodi())) //$NON-NLS-1$
				return true;
		}
		// Grupos secundarios
		// retorna grupentity
		Collection grupsFromUsuaris = grupEntityDao
						.findGrupsFromUsuarisByCodiUsuari(codiUsuari);
		if (grupsFromUsuaris != null)
			for (Iterator it = grupsFromUsuaris.iterator(); it.hasNext();)
			{
				GrupEntity g = (GrupEntity) it.next();
				if (Security.isUserInRole(Security.AUTO_USER_ACCESSREGISTER_QUERY
								+ "/" + g.getCodi())) //$NON-NLS-1$
					return true;
			}

		return false;
	}

	/*
	 * A NIVELL DE GRUPs
	 */
	public static boolean hasCreateGroup ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_CREATE);
	}

	public static boolean hasUpdateGroup ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_UPDATE);
	}

	public static boolean hasQueryGroup ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_QUERY);
	}

	public static boolean canQueryAllGroups ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_QUERY + Security.AUTO_ALL);
	}

	public static boolean hasQueryGroupRoles ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_ROLE_QUERY);
	}

	public static boolean canQueryAllGroupRoles ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_ROLE_QUERY + Security.AUTO_ALL);
	}

	public static boolean canQueryGroupRoles (String codiGrup)
	{
		return (Security.isUserInRole(Security.AUTO_GROUP_ROLE_QUERY + Security.AUTO_ALL) || Security
						.isUserInRole(Security.AUTO_GROUP_ROLE_QUERY + "/" + codiGrup)); //$NON-NLS-1$
	}

	public static boolean hasQueryGroupUsers ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_USER_QUERY);
	}

	public static boolean canQueryGroupUsers (String codiGrup)
	{
		// group:user:query [SENSE_DOMINI o GRUPS]
		return Security.isUserInRole(Security.AUTO_GROUP_USER_QUERY + Security.AUTO_ALL)
						|| Security.isUserInRole(Security.AUTO_GROUP_USER_QUERY + "/" //$NON-NLS-1$
										+ codiGrup);
	}

	public static boolean canQueryAllGroupUsers ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_USER_QUERY + Security.AUTO_ALL);
	}

	public Collection<UsuariGrup> filtraUsuarisGroupVOCanQuery (
					Collection<UsuariGrup> usuarisGrupVO)
	{
		// group:user:query [SENSE_DOMINI o GRUPS]

		Collection<UsuariGrup> usuarisGrupVOPermis = new ArrayList<UsuariGrup>();

		if (Security.isUserInRole(Security.AUTO_GROUP_USER_QUERY + Security.AUTO_ALL))
		{ // sense domini
			usuarisGrupVOPermis = usuarisGrupVO;
		}
		else
		{ // per GRUP
			// Mirem si tenim permis en el grup dels usuari
			for (Iterator<UsuariGrup> it = usuarisGrupVO.iterator(); it.hasNext();)
			{
				UsuariGrup ug = it.next();
				if (ug != null
								&& ug.getCodiGrup() != null
								&& Security.isUserInRole(Security.AUTO_GROUP_USER_QUERY
												+ "/" + ug.getCodiGrup())) //$NON-NLS-1$
					usuarisGrupVOPermis.add(ug);
			}
		}

		return usuarisGrupVOPermis;
	}

	public static boolean hasCreateGroupPrinter ()
	{
		// group:printer:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_CREATE);
	}

	public static boolean canCreateAllGroupPrinter ()
	{
		// group:printer:create [GRUPS] - children
		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_CREATE
						+ Security.AUTO_ALL);
	}

	public static boolean canCreateGroupPrinter (String codiGrup)
	{
		// group:printer:create [GRUPS] - children
		if (canCreateAllGroupPrinter())
			return true;

		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_CREATE + "/" //$NON-NLS-1$
						+ codiGrup);

	}

	public static boolean hasDeleteGroupPrinter ()
	{
		// group:printer:delete [GRUPS]
		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_DELETE);
	}

	public static boolean canDeleteAllGroupPrinter ()
	{
		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_DELETE
						+ Security.AUTO_ALL);
	}

	public static boolean canDeleteGroupPrinter (String codiGrup)
	{
		// group:printer:delete [GRUPS]
		if (canDeleteAllGroupPrinter())
			return true;

		return Security.isUserInRole(Security.AUTO_GROUP_PRINTER_DELETE + "/" //$NON-NLS-1$
						+ codiGrup);
	}

	public static Collection<GrupEntity> filtraGroupsCanQuery (
					Collection<GrupEntity> groupsEntity)
	{
		// group:query [SENSE_DOMINI o GRUPS]
		Collection<GrupEntity> grupsPermis = new ArrayList<GrupEntity>();
		if (Security.isUserInRole(Security.AUTO_GROUP_QUERY + Security.AUTO_ALL))
		{
			grupsPermis = groupsEntity;
		}
		else
		{
			// filtrem
			for (Iterator<GrupEntity> it = groupsEntity.iterator(); it.hasNext();)
			{
				GrupEntity g = it.next();
				if (g != null
								&& Security.isUserInRole(Security.AUTO_GROUP_QUERY
												+ "/" + g.getCodi())) { //$NON-NLS-1$
					grupsPermis.add(g);
				}
			}
		}
		return grupsPermis;
	}

	/*
	 * A NIVELL DE TIPUS D'UNITAT ORGANITZATIVA
	 */
	public static boolean hasCreateOrganizationalUnit ()
	{
		return Security.isUserInRole(Security.AUTO_ORGANIZATIONALUNIT_CREATE);
	}

	public static boolean hasUpdateOrganizationalUnit ()
	{
		return Security.isUserInRole(Security.AUTO_ORGANIZATIONALUNIT_UPDATE);
	}

	public static boolean hasDeleteOrganizationalUnit ()
	{
		return Security.isUserInRole(Security.AUTO_ORGANIZATIONALUNIT_DELETE);
	}

	public static boolean hasQueryOrganizationalUnit ()
	{
		return Security.isUserInRole(Security.AUTO_ORGANIZATIONALUNIT_QUERY);
	}

	/*
	 * A NIVELL DE MÀQUINES
	 */
	public static boolean hasCreateAllHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_CREATE);
	}

	public static boolean hasUpdateAllHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_UPDATE);
	}

	// Des del zul no és permés eliminar màquines
	public static boolean hasDeleteAllHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_DELETE);
	}

	public static boolean hasQueryHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_QUERY);
	}

	public static boolean hasQueryAllHost ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_QUERY);
	}

	public static boolean hasUpdateHostOS ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_UPDATE_OS);
	}

	public static boolean canUpdateHostOS ()
	{
		// host:os:update [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_UPDATE_OS + Security.AUTO_ALL);
	}

	public static boolean hasQueryHostAdmin ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_QUERY_ADMINISTRATOR_ACCESS);
	}

	public static boolean hasSupportHost_VNC ()
	{
		return Security.isUserInRole(Security.AUTO_HOST_ALL_SUPPORT_VNC);
	}

	/*
	 * A NIVELL DE XARXES
	 */
	public static boolean hasCreateNetwork ()
	{
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_CREATE);
	}

	public static boolean hasUpdateAllNetwork ()
	{
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_UPDATE);
	}

	public static boolean hasDeleteAllNetwork ()
	{
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_DELETE);
	}

	public static boolean hasQueryAllNetwork ()
	{
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_QUERY);
	}

	/*
	 * A NIVELL D'IMPRESSORES
	 */
	public static boolean hasCreatePrinter ()
	{// crear impressores
		return Security.isUserInRole(Security.AUTO_PRINTER_CREATE);
	}

	public static boolean hasUpdatePrinter ()
	{
		return Security.isUserInRole(Security.AUTO_PRINTER_UPDATE);
	}

	public static boolean hasDeletePrinter ()
	{
		return Security.isUserInRole(Security.AUTO_PRINTER_DELETE);
	}

	// Totes les impressores
	public static boolean hasQueryAllPrinter ()
	{
		return Security.isUserInRole(Security.AUTO_PRINTER_QUERY);
	}

	// Les impresores que pertanyen a màquinas on l'usuari te ACL
	public static boolean hasQueryACLPrinter ()
	{
		return Security.isUserInRole(Security.AUTO_PRINTER_ACL_QUERY);
	}

	/*
	 * A NIVELL D'APLICACIONS
	 */
	public static boolean hasCreateAplicacio ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_CREATE);
	}

	public static boolean hasUpdateAplicacio ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_UPDATE);
	}

	public static boolean hasDeleteAplicacio ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_DELETE);
	}

	public static boolean hasQueryAplicacio ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_QUERY);
	}

	/*
	 * A NIVELL DE REGISTRES D'ACCÉS
	 */
	public static boolean hasQueryRegistresAcces ()
	{
		return Security.isUserInRole(Security.AUTO_ACCESSREGISTER_QUERY);
	}

	/*
	 * A NIVELL DE DADES ADDICIONALS
	 */
	public static boolean hasCreateMetadata ()
	{
		return Security.isUserInRole(Security.AUTO_METADATA_CREATE);
	}

	public static boolean hasUpdateMetadata ()
	{
		return Security.isUserInRole(Security.AUTO_METADATA_UPDATE);
	}

	public static boolean hasDeleteMetadata ()
	{
		return Security.isUserInRole(Security.AUTO_METADATA_DELETE);
	}

	public static boolean hasQueryMetadata ()
	{
		return Security.isUserInRole(Security.AUTO_METADATA_QUERY);
	}

	/*
	 * A NIVELL DE SERVEIS
	 */
	public static boolean hasCreateServeis ()
	{
		return Security.isUserInRole(Security.AUTO_SERVICE_CREATE);
	}

	public static boolean hasUpdateServeis ()
	{
		return Security.isUserInRole(Security.AUTO_SERVICE_UPDATE);
	}

	public static boolean hasDeleteServeis ()
	{
		return Security.isUserInRole(Security.AUTO_SERVICE_DELETE);
	}

	public static boolean hasQueryServeis ()
	{
		return Security.isUserInRole(Security.AUTO_SERVICE_QUERY);
	}

	/*
	 * A NIVELL DE DOMINIS I LLISTES DE CORREU
	 */
	public static boolean hasCreateMail ()
	{
		return Security.isUserInRole(Security.AUTO_MAIL_CREATE);
	}

	public static boolean hasUpdateMail ()
	{
		return Security.isUserInRole(Security.AUTO_MAIL_UPDATE);
	}

	public static boolean hasDeleteMail ()
	{
		return Security.isUserInRole(Security.AUTO_MAIL_DELETE);
	}

	public static boolean hasQueryMail ()
	{
		return Security.isUserInRole(Security.AUTO_MAIL_QUERY);
	}

	/*
	 * A NIVELL DE LOPD
	 */
	public static boolean hasCreateLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_CREATE);
	}

	public static boolean canCreateLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_CREATE + Security.AUTO_ALL);
	}

	public static boolean hasUpdateLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_UPDATE);
	}

	public static boolean canUpdateLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_UPDATE + Security.AUTO_ALL);
	}

	public static boolean hasDeleteLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_DELETE);
	}

	public static boolean canDeleteLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_DELETE + Security.AUTO_ALL);
	}

	public static boolean hasQueryLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_QUERY);
	}

	public static boolean canQueryLopd ()
	{
		return Security.isUserInRole(Security.AUTO_LOPD_QUERY + Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE PARÀMETRES
	 */
	public static boolean hasCreateParameter ()
	{
		return Security.isUserInRole(Security.AUTO_PARAMETER_CREATE);
	}

	public static boolean hasUpdateParameter ()
	{
		return Security.isUserInRole(Security.AUTO_PARAMETER_UPDATE);
	}

	public static boolean hasDeleteParameter ()
	{
		return Security.isUserInRole(Security.AUTO_PARAMETER_DELETE);
	}

	public static boolean hasQueryParameter ()
	{
		return Security.isUserInRole(Security.AUTO_PARAMETER_QUERY);
	}

	/*
	 * A NIVELL D'AGENTS
	 */
	public static boolean hasCreateAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_CREATE);
	}

	public static boolean hasUpdateAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_UPDATE);
	}

	public static boolean hasDeleteAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_DELETE);
	}

	public static boolean hasQueryAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_QUERY);
	}

	public static boolean hasPropagateAgentUsers ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_PROPAGATE_USERS);
	}

	public static boolean hasPropagateAgentRoles ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_PROPAGATE_ROLES);
	}

	public static boolean hasPropagateAgentGroups ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_PROPAGATE_GROUPS);
	}

	public static boolean hasCreateAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_CREATE);
	}

	public static boolean hasUpdateAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_UPDATE);
	}

	public static boolean hasDeleteAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_DELETE);
	}

	public static boolean hasQueryAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_QUERY);
	}

	public static boolean hasSetAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_SET);
	}

	public static boolean canCreateAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_CREATE
						+ Security.AUTO_ALL);
	}

	public static boolean canUpdateAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_UPDATE
						+ Security.AUTO_ALL);
	}

	public static boolean canDeleteAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_DELETE
						+ Security.AUTO_ALL);
	}

	public static boolean canQueryAccessControlAgent ()
	{
		return Security.isUserInRole(Security.AUTO_AGENT_ACCESSCONTROL_QUERY
						+ Security.AUTO_ALL);
	}

	public static boolean canManageServers ()
	{
		return Security.isUserInRole(Security.AUTO_SERVER_MANAGE);
	}

	/*
	 * A NIVELL D'AUDITORIA
	 */
	public static boolean hasQueryAuditoria ()
	{
		return Security.isUserInRole(Security.AUTO_AUDIT_QUERY);
	}

	public static boolean hasQueryCustomAuditoria ()
	{
		return Security.isUserInRole(Security.AUTO_AUDIT_CUSTOM_QUERY);
	}

	public static boolean canQueryCustomAuditoria ()
	{
		return Security.isUserInRole(Security.AUTO_AUDIT_CUSTOM_QUERY
						+ Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE MENUS DE LA INTRANET
	 */
	public static boolean hasQueryAllMenusIntranet ()
	{
		return Security.isUserInRole(Security.AUTO_INTRANETMENUS_ALL_QUERY);
	}

	public static boolean hasAdminMenusIntranet ()
	{
		return Security.isUserInRole(Security.AUTO_INTRANETMENUS_ADMIN);
	}

	public static boolean canAdminMenusIntranet ()
	{
		return Security.isUserInRole(Security.AUTO_INTRANETMENUS_ADMIN
						+ Security.AUTO_ALL);
	}

	public static boolean canQueryAllMenusIntranet ()
	{
		return Security.isUserInRole(Security.AUTO_INTRANETMENUS_ALL_QUERY
						+ Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE SEYCON-BASE
	 */
	/*
	 * public static boolean hasUpdateBase() { return
	 * Security.isUserInRole(Security.AUTO_BASE_UPDATE); } public static boolean
	 * hasRestartBase() { return Security.isUserInRole(Security.AUTO_BASE_RESTART); }
	 * public static boolean hasQueryBase() { return
	 * Security.isUserInRole(Security.AUTO_BASE_QUERY); }
	 */

	public static boolean hasQueryServerListBase ()
	{
		return Security.isUserInRole(Security.AUTO_MONITOR_SERVER_LIST);
	}

	public static boolean hasQueryAgentListBase ()
	{
		return Security.isUserInRole(Security.AUTO_MONITOR_AGENT_LIST);
	}

	public static boolean hasRestartAgentBase ()
	{
		return Security.isUserInRole(Security.AUTO_MONITOR_AGENT_RESTART);
	}

	public static boolean hasQueryLogBase ()
	{
		return Security.isUserInRole(Security.AUTO_BASE_LOG_QUERY);
	}

	public static boolean hasUpdatePlugins ()
	{
		return Security.isUserInRole(Security.AUTO_PLUGINS_UPDATE);
	}

	public static boolean hasQueryPlugins ()
	{
		return Security.isUserInRole(Security.AUTO_PLUGINS_QUERY);
	}

	/*
	 * A NIVELL D'AUTORITZACIONS
	 */
	public static boolean hasCreateAuthorizationRol ()
	{
		return Security.isUserInRole(Security.AUTO_AUTHORIZATION_ROL_CREATE);
	}

	public static boolean hasDeleteAuthorizationRol ()
	{
		return Security.isUserInRole(Security.AUTO_AUTHORIZATION_ROL_DELETE);
	}

	public static boolean hasQueryAuthorization ()
	{
		return Security.isUserInRole(Security.AUTO_AUTHORIZATION_QUERY);
	}

	/*
	 * A NIVELL D'USUARIS DE TIPUS ALUMNE
	 */
	public static boolean hasCreatePupil ()
	{
		return Security.isUserInRole(Security.AUTO_PUPIL_CREATE);
	}

	/*
	 * A NIVELL DE WORKFLOWS
	 */
	public static boolean canAdminWorkflows ()
	{
		return Security.isUserInRole(Security.AUTO_WORKFLOW_ADMIN + Security.AUTO_ALL);
	}

	/*
	 * A NIVELL D'INTERFICIE DEL SEU
	 */
	public static boolean hasViewAgentsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_AGENTS);
	}

	public static boolean hasViewAplicacionsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_APLICACIONS);
	}

	public static boolean hasViewAuditoriaSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_AUDITORIA);
	}

	public static boolean hasViewAutoritzacionsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_AUTORITZACIONS);
	}

	public static boolean hasViewCorreuSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_CORREU);
	}

	public static boolean hasViewDominisCorreuSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_DOMINISCORREU);
	}

	public static boolean hasViewDadesAddicionalsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_DADESADDICIONALS);
	}

	public static boolean hasViewGrupsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_GRUPS);
	}

	public static boolean hasViewImpressoresSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_IMPRESSORES);
	}

	public static boolean hasViewLopdSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_LOPD);
	}

	public static boolean hasViewParametresSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_PARAMETRES);
	}

	public static boolean hasViewPluginsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_PARAMETRES);
	}

	public static boolean hasViewRegistreAccesSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_REGISTREACCES);
	}

	public static boolean hasViewServeisSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_SERVEIS);
	}

	public static boolean hasViewTipusUOSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_TIPUSUO);
	}

	public static boolean hasViewUsuarisSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_USUARIS);
	}

	public static boolean hasViewMenusIntranetSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_MENUSINTRANET);
	}

	public static boolean hasViewSeyconServerSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_SEYCONSERVER);
	}

	public static boolean hasViewFederacioIdentitatsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_FEDERACIOIDENTITATS);
	}

	public static boolean hasViewDominiUsuaris ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_USERS_DOMAIN);
	}

	/*
	 * MÈTODES LOCALS D'ALTRES SERVICES (per centralitzar l'ús)
	 */

	//
	// A NIVELL D'APLICACIONS
	//

	public static boolean canQueryAllAplication ()
	{
		return Security.isUserInRole(Security.AUTO_APPLICATION_QUERY + Security.AUTO_ALL);
	}

	/**
	 * Mètode que retorna les aplicacions que pot veure l'usuari actual
	 * 
	 * @param aplicacionsEntity
	 * @return
	 */
	public static Collection<AplicacioEntity> filtraAplicationsCanQuery (
					Collection<AplicacioEntity> aplicacionsEntity)
	{
		ArrayList<AplicacioEntity> appsCanQuery = new ArrayList<AplicacioEntity>();

		// L'autorització application:query [APLICACIONS, SENSE_DOMINI]
		// si té application:query/* pot veure totes
		if (canQueryAllAplication())
			return aplicacionsEntity;
		// Sinó filtrem per autorització d'aplicació
		for (Iterator<AplicacioEntity> it = aplicacionsEntity.iterator(); it.hasNext();)
		{
			AplicacioEntity app = it.next();
			if (app != null
							&& Security.isUserInRole(Security.AUTO_APPLICATION_QUERY
											+ "/" + app.getCodi())) { //$NON-NLS-1$
				appsCanQuery.add(app);
			}
		}

		return appsCanQuery;
	}

	/**
	 * Mètode que retorna les aplicacions que pot veure l'usuari actual
	 * 
	 * @param aplicacionsEntity
	 * @return
	 */
	public static Collection<Aplicacio> filtraAplicationsVOCanQuery (
					Collection<Aplicacio> aplicacionsVO)
	{
		ArrayList<Aplicacio> appsCanQuery = new ArrayList<Aplicacio>();

		// L'autorització application:query [APLICACIONS, SENSE_DOMINI]
		// si té application:query/* pot veure totes

		if (canQueryAllAplication())
			return aplicacionsVO;
		// Sinó filtrem per autorització d'aplicació
		for (Iterator<Aplicacio> it = aplicacionsVO.iterator(); it.hasNext();)
		{
			Aplicacio app = it.next();
			if (app != null
							&& app.getCodi() != null
							&& Security.isUserInRole(Security.AUTO_APPLICATION_QUERY
											+ "/" + app.getCodi())) { //$NON-NLS-1$
				appsCanQuery.add(app);
			}
		}

		return appsCanQuery;
	}

	public static boolean canQueryAplicacio (String codiAplicacio)
	{
		// L'autorització application:query [APLICACIONS, SENSE_DOMINI]
		// si té application:query/* pot veure totes
		if (canQueryAllAplication()
						|| Security.isUserInRole(Security.AUTO_APPLICATION_QUERY + "/" //$NON-NLS-1$
										+ codiAplicacio))
			return true;

		return false;
	}

	public static boolean canDeleteAplicacio (String codiAplicacio)
	{
		// L'autorització application:delete [APLICACIONS, SENSE_DOMINI]
		// si té application:delete/* pot veure totes
		return (Security.isUserInRole(Security.AUTO_APPLICATION_DELETE
						+ Security.AUTO_ALL) || Security
						.isUserInRole(Security.AUTO_APPLICATION_DELETE + "/" //$NON-NLS-1$
										+ codiAplicacio));
	}

	public static boolean canCreateAplicacio (String codiAplicacio)
	{
		// SENSE_DOMINI
		return (Security.isUserInRole(Security.AUTO_APPLICATION_CREATE
						+ Security.AUTO_ALL));
	}

	public static boolean canUpdateAplicacio (String codiAplicacio)
	{
		// L'autorització application:delete [APLICACIONS, SENSE_DOMINI]
		// si té application:update/* pot actualitzar totes
		return (Security.isUserInRole(Security.AUTO_APPLICATION_UPDATE
						+ Security.AUTO_ALL) || Security
						.isUserInRole(Security.AUTO_APPLICATION_UPDATE + "/" //$NON-NLS-1$
										+ codiAplicacio));
	}

	public static Collection<RolEntity> filtraRolsAplicationsCanQuery (
					Collection<RolEntity> rolsEntity)
	{
		// application:query [APLICACIONS]
		// o user:role:query [GRUPS, APLICACIONS]: ací en el cas d'aplicacions
		// només
		Collection<RolEntity> rolsPermis = new LinkedList<RolEntity>();
		if (canQueryAllAplication())
			return rolsEntity;
		else
		{
			// Filtrem els rols per l'aplicació on té autorització
			// application:query l'usuari
			for (Iterator<RolEntity> it = rolsEntity.iterator(); it.hasNext();)
			{
				RolEntity r = it.next();
				if (r != null
								&& r.getAplicacio() != null
								&& (Security.isUserInRole(Security.AUTO_APPLICATION_QUERY
												+ "/" + r.getAplicacio().getCodi()) || Security //$NON-NLS-1$
													.isUserInRole(Security.AUTO_USER_ROLE_QUERY
																	+ "/" + r.getAplicacio().getCodi()))) { //$NON-NLS-1$
					rolsPermis.add(r);
				}

			}
		}
		return rolsPermis;
	}

	/**
	 * Mètode per saber si un usuari pot crear assignació de rol a un usuari
	 * 
	 * @param rolsUsuaris
	 * @return
	 */
	public static boolean canCreateUserRole (RolAccount rolAccount,
					GrupEntityDao getGrupEntityDao)
	{
		// 1 rolsUsuaris només
		// autorització user:role:create [sense_domini, GRUPS, APLICACIONS]

		// SENSE_DOMINI
		if (Security.isUserInRole(Security.AUTO_USER_ROLE_CREATE + Security.AUTO_ALL))
			return true;

		// FILTRE PER APLICACIO
		if (Security.isUserInRole(Security.AUTO_USER_ROLE_CREATE + "/" //$NON-NLS-1$
						+ rolAccount.getCodiAplicacio()))
		{
			return true;
		}

		// FILTRE PER GRUPS
		// Obtenemos grupos del usuario de la asignación de rol
		// Grupo Primario
		if (rolAccount.getCodiUsuari() != null)
		{
			GrupEntity grupPrimari = getGrupEntityDao
							.findGrupPrimariByCodiUsuari(rolAccount.getCodiUsuari());
			if (grupPrimari != null)
			{
				if (Security.isUserInRole(Security.AUTO_USER_ROLE_CREATE + "/" //$NON-NLS-1$
								+ grupPrimari.getCodi()))
					return true;
			}
			// Grupos secundarios
			// retorna grupentity
			List<GrupEntity> grupsFromUsuaris = getGrupEntityDao
							.findGrupsFromUsuarisByCodiUsuari(rolAccount.getCodiUsuari());
			for (Iterator<GrupEntity> it = grupsFromUsuaris.iterator(); it.hasNext();)
			{
				GrupEntity g = (GrupEntity) it.next();
				if (Security.isUserInRole(Security.AUTO_USER_ROLE_CREATE + "/" //$NON-NLS-1$
								+ g.getCodi()))
					return true;
			}
		}

		return false;

	}

	public static boolean canDeleteUserRole (RolAccount rolsUsuaris,
					GrupEntityDao grupEntityDao)
	{
		// 1 rolsUsuaris només
		// autorització user:role:delete [sense_domini, GRUPS, APLICACIONS]
		if (Security.isUserInRole(Security.AUTO_USER_ROLE_DELETE + Security.AUTO_ALL))
			return true;

		// FILTRE PER APLICACIO
		if (Security.isUserInRole(Security.AUTO_USER_ROLE_DELETE + "/" //$NON-NLS-1$
						+ rolsUsuaris.getCodiAplicacio()))
			return true;

		// FILTRE PER GRUPS
		// Obtenemos grupos del usuario sobre el que se piden
		// Grupo Primario
		if (rolsUsuaris.getCodiUsuari() != null)
		{
			GrupEntity grupPrimari = grupEntityDao
							.findGrupPrimariByCodiUsuari(rolsUsuaris.getCodiUsuari());
			if (grupPrimari != null)
			{
				if (Security.isUserInRole(Security.AUTO_USER_ROLE_DELETE + "/" //$NON-NLS-1$
								+ grupPrimari.getCodi()))
					return true;
			}
			// Grupos secundarios
			List<GrupEntity> grupsFromUsuaris = grupEntityDao
							.findGrupsFromUsuarisByCodiUsuari(rolsUsuaris
											.getCodiUsuari());
			for (Iterator<GrupEntity> it = grupsFromUsuaris.iterator(); it.hasNext();)
			{
				GrupEntity g = it.next();
				if (Security.isUserInRole(Security.AUTO_USER_ROLE_DELETE + "/" //$NON-NLS-1$
								+ g.getCodi()))
					return true;
			}
		}

		return false;
	}

	@SuppressWarnings ("rawtypes")
	public static Collection<RolAccountEntity> filtraRolsUsuariAplicationsCanQuery (
					Collection<RolAccountEntity> rolsUsuariEntity)
	{
		// Autoritzacio user:role:query [sense_domini, GRUPS, APLICACIONS]
		Collection<RolAccountEntity> rolsPermis = new ArrayList<RolAccountEntity>();

		if (canQueryAllUserRole())
		{
			return rolsUsuariEntity; // eixim: té permis
		}
		else
		{
			// Recorremos los roles y hacemos 2 filtrados: por GRUPO y
			// APLICACIÓN
			//
			// Filtramos por GRUPO (si tenemos permiso sobre grupo(e hijos)
			// usuario: se devuelven todos)
			// y por APLICACIÓN: sólo se muestran los roles de apps q tengamos
			// autorización

			for (Iterator<RolAccountEntity> rit = rolsUsuariEntity.iterator(); rit
							.hasNext();)
			{
				RolAccountEntity rue = rit.next();
				AccountEntity account = rue.getAccount();
				// PRIMER FILTRE: PER GRUPS
				// Obtenemos grupos del usuario sobre el que se piden
				// Grupo Primario
				boolean tienePermisoGrupoUsuario = false;
				// String codiUsuari = rue.getUsuari().getCodi();//usuari del
				// rol
				// SEGONS FILTRE: PER APLICACIONS
				// 1) l'aplicació del RolEntity
				if (Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY
								+ "/" + rue.getRol().getAplicacio().getCodi())) { //$NON-NLS-1$
					rolsPermis.add(rue);
				}
				else
				{
					for (UserAccountEntity ua : account.getUsers())
					{
						UsuariEntity usuari = ua.getUser();

						GrupEntity grupPrimari = usuari.getGrupPrimari(); // grupEntityDao.findGrupPrimariByCodiUsuari(codiUsuari);
						if (grupPrimari != null)
						{
							if (Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY
											+ "/" + grupPrimari.getCodi())) //$NON-NLS-1$
								tienePermisoGrupoUsuario = true;
						}
						if (!tienePermisoGrupoUsuario)
						{// si encara no tenim permis,
						 // mirem els secundaris
							Collection grupsFromUsuaris = usuari.getGrupsSecundaris(); // UsuariGrupEntity
							for (Iterator it = grupsFromUsuaris.iterator(); !tienePermisoGrupoUsuario
											&& it.hasNext();)
							{
								UsuariGrupEntity g = (UsuariGrupEntity) it.next();
								if (g != null
												&& Security.isUserInRole(Security.AUTO_USER_ROLE_QUERY
																+ "/" + g.getGrup().getCodi())) //$NON-NLS-1$
									tienePermisoGrupoUsuario = true;
							}

						}
						if (tienePermisoGrupoUsuario)
						{
							rolsPermis.add(rue); // L'afegim
							break;
						}
					} // for
				}
			} // for
			return new Vector<RolAccountEntity>(rolsPermis);

		}

	}

	//
	// A NIVELL DE GRUPS
	//

	public static boolean canCreateGroup (String codiGrup)
	{
		// SENSE_DOMINI
		return (Security.isUserInRole(Security.AUTO_GROUP_CREATE + Security.AUTO_ALL));
	}

	public static Collection<GrupEntity> filtraGrupsEntityCanQuery (
					Collection<GrupEntity> grupsEntity)
	{
		// L'autorització group:query [SENSE_DOMINI, GRUPS ]
		// si té group:query/* pot veure totes

		if (canQueryAllGroups())
			return grupsEntity;
		// Sinó filtrem per autorització de grup
		Collection<GrupEntity> grupsCanQuery = new ArrayList<GrupEntity>();
		for (Iterator<GrupEntity> it = grupsEntity.iterator(); it.hasNext();)
		{
			GrupEntity grup = it.next();
			if (grup != null && Security.isUserInRole(Security.AUTO_GROUP_QUERY + "/" //$NON-NLS-1$
							+ grup.getCodi()))
			{
				grupsCanQuery.add(grup);
			}
		}

		return grupsCanQuery;
	}

	public static boolean canQueryGrup (String codiGrup)
	{
		// L'autorització group:query [GRUPS, SENSE_DOMINI]

		// si té group:query/* pot veure tots
		if (canQueryAllGroups() || Security.isUserInRole(Security.AUTO_GROUP_QUERY + "/" //$NON-NLS-1$
						+ codiGrup))
			return true;

		return false;
	}

	public static boolean canUpdateGrup (String codiGrup)
	{
		// SENSE_DOMINI, GRUPS
		if (Security.isUserInRole(Security.AUTO_GROUP_UPDATE + Security.AUTO_ALL)
						|| Security.isUserInRole(Security.AUTO_GROUP_UPDATE + "/" //$NON-NLS-1$
										+ codiGrup))
			return true;

		return false;
	}

	//
	// A NIVELL D'USUARIS
	//

	@SuppressWarnings ("rawtypes")
	public static Collection<UsuariEntity> filtraUsuariEntityCanQuery (
					Collection<UsuariEntity> usuarisEntity)
	{
		// user:query [SENSE_DOMINI o GRUPS]
		Collection<UsuariEntity> usuarisPermis = new ArrayList<UsuariEntity>();

		if (Security.isUserInRole(Security.AUTO_USER_QUERY + Security.AUTO_ALL))
			return usuarisEntity;
		else
		{
			for (Iterator<UsuariEntity> it = usuarisEntity.iterator(); it.hasNext();)
			{
				UsuariEntity usuariActual = it.next();
				// Sólo es necesario comprobar los grupos primarios y
				// secundarios, no sus padres (ya están en la lista de
				// autorizaciones)
				boolean trobat = canQueryUser(usuariActual);
				if (trobat)
				{
					usuarisPermis.add(usuariActual);
				}
			}
		}

		return usuarisPermis;
	}

	public static boolean canQueryUser(UsuariEntity user) {
		boolean trobat = false;

		if (Security.isUserInRole(Security.AUTO_USER_QUERY + Security.AUTO_ALL))
			return true;

		if (user.getGrupPrimari() != null
						&& Security.isUserInRole(Security.AUTO_USER_QUERY
										+ "/" //$NON-NLS-1$
										+ user.getGrupPrimari()
														.getCodi()))
			trobat = true;
		if (!trobat)
		{ // mirem grups secundaris
			Collection grupsSecundaris = user.getGrupsSecundaris(); // UsuariGrupEntity
			for (Iterator itGS = grupsSecundaris.iterator(); !trobat
							&& itGS.hasNext();)
			{
				UsuariGrupEntity usuGrupActual = (UsuariGrupEntity) itGS.next();
				GrupEntity grupS = usuGrupActual.getGrup();
				if (grupS != null
								&& Security.isUserInRole(Security.AUTO_USER_QUERY
												+ "/" + grupS.getCodi())) //$NON-NLS-1$
					trobat = true;
			}
		}
		return trobat;
	}

	public static boolean canUpdateUserPassword (String codiGrup)
	{

		if (Security.isUserInRole(Security.AUTO_USER_UPDATE_PASSWORD + Security.AUTO_ALL)
						||
						// Mirem si té atorgat drets sobre el grup
						Security.isUserInRole(Security.AUTO_USER_UPDATE_PASSWORD + "/" //$NON-NLS-1$
										+ codiGrup))
			return true;

		return false;
	}

	public static boolean canSetUserPassword (String codiGrup)
	{

		if (Security.isUserInRole(Security.AUTO_USER_SET_PASSWORD + Security.AUTO_ALL)
						||
						// Mirem si té atorgat drets sobre el grup
						Security.isUserInRole(Security.AUTO_USER_SET_PASSWORD + "/" //$NON-NLS-1$
										+ codiGrup))
			return true;

		return false;
	}

	//
	// A NIVELL DE XARXES
	//

	public static boolean canCreateAllNetworks ()
	{
		// network:all:create [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_CREATE
						+ Security.AUTO_ALL);
	}

	public static boolean canUpdateAllNetworks ()
	{
		// network:all:update [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_UPDATE
						+ Security.AUTO_ALL);
	}

	public static boolean canDeleteAllNetworks ()
	{
		// network:all:delete [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_DELETE
						+ Security.AUTO_ALL);
	}

	public static boolean canQueryAllNetworks ()
	{
		// network:all:query [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_NETWORK_ALL_QUERY + Security.AUTO_ALL);
	}

	public static boolean canCreateAllHosts ()
	{
		// host:all:create [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_ALL_CREATE + Security.AUTO_ALL);
	}

	public static boolean canUpdateAllHosts ()
	{
		// host:all:update [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_ALL_UPDATE + Security.AUTO_ALL);
	}

	public static boolean canQueryAllHosts ()
	{
		// host:query [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_ALL_QUERY + Security.AUTO_ALL);
	}

	public static boolean canDeleteAllHosts ()
	{
		// host:delete [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_HOST_ALL_DELETE + Security.AUTO_ALL);
	}

	public static boolean canSupportAllNetworks_VNC ()
	{
		// host:support [SENSE_DOMINI]
		// NOTA: S'ha de comprovar a nivel d'ACLs quan corresponga
		return Security.isUserInRole(Security.AUTO_HOST_ALL_SUPPORT_VNC
						+ Security.AUTO_ALL);
	}

	// A NIVELL DE FEDERACIÓ D'IDENTITATS

	public static boolean canQueryAllIdentityFederation ()
	{
		// federacioIdentitats:query [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_IDENTITY_FEDERATION_QUERY
						+ Security.AUTO_ALL);
	}

	public static boolean canDeleteAllIdentityFederation ()
	{
		// federacioIdentitats:delete [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_IDENTITY_FEDERATION_DELETE
						+ Security.AUTO_ALL);
	}

	public static boolean canCreateAllIdentityFederation ()
	{
		// federacioIdentitats:create [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_IDENTITY_FEDERATION_CREATE
						+ Security.AUTO_ALL);
	}

	public static boolean canUpdateAllIdentityFederation ()
	{
		// federacioIdentitats:upadate [SENSE_DOMINI]
		return Security.isUserInRole(Security.AUTO_IDENTITY_FEDERATION_UPDATE
						+ Security.AUTO_ALL);
	}

	/*
	 * A NIVELL DE DOMINIS D'USUARIS
	 */
	public static boolean hasCreateDominisUsuari ()
	{
		return Security.isUserInRole(Security.AUTO_USERS_DOMAIN_CREATE);
	}

	public static boolean hasUpdateDominisUsuari ()
	{
		return Security.isUserInRole(Security.AUTO_USERS_DOMAIN_UPDATE);
	}

	public static boolean hasDeleteDominisUsuari ()
	{
		return Security.isUserInRole(Security.AUTO_USERS_DOMAIN_DELETE);
	}

	public static boolean hasQueryDominisUsuari ()
	{
		return Security.isUserInRole(Security.AUTO_USERS_DOMAIN_QUERY);
	}

	/*
	 * Accounts level
	 */
	public static boolean hasCreateAccount ()
	{
		return Security.isUserInRole(Security.AUTO_ACCOUNT_CREATE);
	}

	public static boolean hasUpdateAccount ()
	{
		return Security.isUserInRole(Security.AUTO_ACCOUNT_UPDATE);
	}

	public static boolean hasDeleteAccount ()
	{
		return Security.isUserInRole(Security.AUTO_ACCOUNT_DELETE);
	}

	public static boolean hasQueryAccount ()
	{
		return Security.isUserInRole(Security.AUTO_ACCOUNT_QUERY);
	}

	public static boolean hasViewAccountsSEU ()
	{
		return Security.isUserInRole(Security.AUTO_SEU_VIEW_ACCOUNTS);
	}

	/**
	 * Method to check if user can create new OS types.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to create OS types. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to create OS types. </li>
	 *       </ul>
	 */
	public static boolean canCreateOSType ()
	{
		return Security.isUserInRole(Security.AUTO_OS_CREATE);
	}

	/**
	 * Method to check if user can delete exiting OS types.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to delete OS types. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to delete OS types. </li>
	 *       </ul>
	 */
	public static boolean canDeleteOSType ()
	{
		return Security.isUserInRole(Security.AUTO_OS_DELETE);
	}

	/**
	 * Method to check if user can update existing OS types.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to update OS types. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to update OS types. </li>
	 *       </ul>
	 */
	public static boolean canUpdateOSType ()
	{
		return Security.isUserInRole(Security.AUTO_OS_UPDATE);
	}

	/**
	 * Method to check if user can query existing OS types.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to query OS types. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to query OS types. </li>
	 *       </ul>
	 */
	public static boolean canQueryOSType ()
	{
		return Security.isUserInRole(Security.AUTO_OS_QUERY);
	}

	/**
	 * Method to check if user can create new retrieve password questions.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to create retrieve password questions. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to create retrieve password questions.
	 *       </li>
	 *       </ul>
	 */
	public static boolean canCreateRetrivePassword ()
	{
		return Security.isUserInRole(Security.AUTO_REMEMBER_PASSWORD_CREATE);
	}

	/**
	 * Method to check if user can delete exiting retrieve password questions..
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to delete retrieve password questions. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to delete retrieve password questions.
	 *       </li>
	 *       </ul>
	 */
	public static boolean canDeleteRetrievePassword ()
	{
		return Security.isUserInRole(Security.AUTO_REMEMBER_PASSWORD_DELETE);
	}

	/**
	 * Method to check if user can update existing retrieve password questions.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to update retrieve password questions. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to update retrieve password questions.
	 *       </li>
	 *       </ul>
	 */
	public static boolean canUpdateRetrivePassword ()
	{
		return Security.isUserInRole(Security.AUTO_REMEMBER_PASSWORD_UPDATE);
	}

	/**
	 * Method to check if user can query existing retrieve password questions.
	 * 
	 * @return <ul>
	 *         <li>
	 * @code TRUE If user is authorized to query retrieve password questions. </li>
	 * 
	 *       <li>
	 * @code FALSE If user is not authorized to query retrieve password questions. 
	 *       </li>
	 *       </ul>
	 */
	public static boolean canQueryRetrieve ()
	{
		return Security.isUserInRole(Security.AUTO_REMEMBER_PASSWORD_QUERY);
	}

	public static AttributeVisibilityEnum getAttributeVisibility(UsuariEntity user, TipusDadaEntity tda) {
		if (Security.getCurrentUser() != null && Security.getCurrentUser().equals(user.getCodi()))
			return tda.getUserVisibility() == null ? AttributeVisibilityEnum.HIDDEN: tda.getUserVisibility();
		else if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
			return tda.getAdminVisibility() == null ? AttributeVisibilityEnum.EDITABLE: tda.getAdminVisibility();
		else if (AutoritzacionsUsuari.canUpdateUserMetadata(user))
			return tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.EDITABLE: tda.getOperatorVisibility();
		else if (AutoritzacionsUsuari.canQueryUser(user))
		{
			AttributeVisibilityEnum v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.READONLY: tda.getOperatorVisibility();
			if (AttributeVisibilityEnum.EDITABLE.equals (v))
				v = AttributeVisibilityEnum.READONLY;
			return v;
		}
		else
			return AttributeVisibilityEnum.HIDDEN;
	}

	public static AttributeVisibilityEnum getAttributeVisibility(AccountEntity account, AccountMetadataEntity tda) {
		if (account.getType().equals (AccountType.USER))
		{
			for (UserAccountEntity uae: account.getUsers())
			{
				return getAttributeVisibility(uae.getUser(), tda);
			}
		}
		
		if (Security.isUserInRole(Security.AUTO_METADATA_UPDATE_ALL))
			return AttributeVisibilityEnum.EDITABLE;
		else if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
			return tda.getAdminVisibility() == null ? AttributeVisibilityEnum.EDITABLE: tda.getAdminVisibility();
		else if (Security.isUserInRole(Security.AUTO_ACCOUNT_ATTRIBUTE_UPDATE+Security.AUTO_AUTHORIZATION_ALL) )
			return tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.EDITABLE: tda.getOperatorVisibility();
		else if (Security.isUserInRole(Security.AUTO_ACCOUNT_ATTRIBUTE_QUERY+Security.AUTO_AUTHORIZATION_ALL) )
		{
			AttributeVisibilityEnum v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.READONLY: tda.getOperatorVisibility();
			if (AttributeVisibilityEnum.EDITABLE.equals (v))
				v = AttributeVisibilityEnum.READONLY;
			return v;
		}
		else
			return AttributeVisibilityEnum.HIDDEN;
	}

	public static AttributeVisibilityEnum getAttributeVisibility(UsuariEntity user, AccountMetadataEntity tda) {
		if (Security.getCurrentUser() != null && Security.getCurrentUser().equals(user.getCodi()))
			return tda.getUserVisibility() == null ? AttributeVisibilityEnum.HIDDEN: tda.getUserVisibility();
		else if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
			return tda.getAdminVisibility() == null ? AttributeVisibilityEnum.EDITABLE: tda.getAdminVisibility();
		else if (AutoritzacionsUsuari.canUpdateAccountMetadata(user))
			return tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.EDITABLE: tda.getOperatorVisibility();
		else if (AutoritzacionsUsuari.canQueryAccountMetadata(user))
		{
			AttributeVisibilityEnum v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.READONLY: tda.getOperatorVisibility();
			if (AttributeVisibilityEnum.EDITABLE.equals (v))
				v = AttributeVisibilityEnum.READONLY;
			return v;
		}
		else
			return AttributeVisibilityEnum.HIDDEN;
	}
}

