package com.soffid.iam.service.workflow;

import es.caib.seycon.ng.servei.workflow.*;

import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.service.GroupService;
import com.soffid.iam.service.UserService;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.SeyconException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @author u89559
 * 
 */
public class CreateDisableUserServiceImpl extends com.soffid.iam.service.workflow.CreateDisableUserServiceBase {

	/**
	 * @param peticio
	 *            : document XML de la petici� d'alta. Obligatori
	 * @param signatura
	 *            : signatura del document XML de petici� d'alta. Obligatori
	 * @return value object Usuari
	 * @throws java.lang.Exception
	 */
	public User handleCreateUser(byte[] peticio, es.caib.signatura.api.Signature signatura) throws java.lang.Exception {
		UserService usuariService = getUserService();
		User usuari = usuariService.createUser(peticio, signatura);
		return usuari;
	}

	/**
	 * @param codiGrup
	 *            : codi del grup / unitat organitzativa de la conselleria.
	 *            Obligatori
	 * @return una col�lecci� de les direccions generals de la conselleria.
	 *         Obligatori
	 * @throws java.lang.Exception
	 */
	/*
	 
	 	public Collection handleGetDireccionsGenerals(String codiGrup) 
			throws java.lang.Exception
	    {
			GrupService grupService = getGrupService();
	    	Collection grups = grupService.findGrupsByTipusGrup("DIRECCIO_GENERAL");
	    	return grups;
	    }
		
	*/
	/**
	 * @param codiUsuari
	 *            : codi d'usuari de l'usuari a donar de baixa. Obligatori
	 * @return usauri que s'ha donat de baixa (no s'elimina de BBDD, es
	 *         converteix en ciutad�)
	 * @throws java.lang.Exception
	 */
	protected User handleDisableUser(java.lang.String codiUsuari) throws java.lang.Exception {
		UserService usuariService = getUserService();
		User usuari = usuariService.disableUser(codiUsuari);
		return usuari;
	}


	/**
	 * @param codiUsuari
	 *            : usuari a donar d'alta. Obligatori
	 * @param servidorCorreuId
	 *            : identificador del servidor de correu. No obligatori
	 * @param servidorPerfilId
	 *            : identificador del servidor de perfil. No obligatori
	 * @param servidorHomeId
	 *            : identificador del servidor home. No obligatori
	 * @return Usuari a qui s'han donat d'alta els servidors
	 * @throws java.lang.Exception
	 */
	protected User handleSetServersToUser(java.lang.String codiUsuari, String servidorCorreuId, String servidorPerfilId, String servidorHomeId) throws java.lang.Exception {
		UserService usuariService = getUserService();
		return usuariService.setServersToUser(codiUsuari, servidorCorreuId, servidorPerfilId, servidorHomeId);
	}

	/**
	 * @param codiUnitatOrganitzativa
	 *            : codi de grup /unitat organitzativa. Obligatori
	 * @return col·lecció de les unitats organitzatives que en depenen
	 * @throws Exception
	 */
	protected Collection<Group> handleGetOUDependent(String codiUnitatOrganitzativa) throws Exception {
		GroupService grupService = this.getGroupService();
		return grupService.findSubgroupsByGroupName(codiUnitatOrganitzativa);
	}

	/**
	 * @param dni
	 *            : DNI de l'usuari en format LIKE d'SQL (483%, 49217421,....).
	 *            Null per ignorar-lo. No obligatori
	 * @param nom
	 *            : Nom de l'usuari en format LIKE SQL. Null per ignorar-lo. No
	 *            obligatori
	 * @param primerLlinatge
	 *            : primer llinatge de l'usuari en format LIKE d'SQL. Null per
	 *            ignorar-lo. No obligatori
	 * @param segonLlinatge
	 *            : segon llinatge de l'usuari en format LIKE d'SQL. Null per
	 *            ignorar-lo. No obligatori
	 * @return collection dels usuaris que fan matching amb tots els par�metres
	 * @throws Exception
	 */
	protected Collection<User> handleFindUserByUserData(String dni, String nom, String primerLlinatge, String segonLlinatge) throws Exception {
		UserService usuariService = getUserService();
		Collection<User> usuaris = usuariService.findUsersByCoreData("%", nom, primerLlinatge, segonLlinatge, dni); //$NON-NLS-1$
		return usuaris;
	}

	/**
	 * @param codiUsuari
	 *            : codi d'usuari a qui s'ha d'assignar el password inicial.
	 *            Obligatori
	 * @return l'string corresponent al password inicial assignat a l'usuari
	 * @throws Exception
	 */
	protected String handleSetInitialPasswordToUser(String codiUsuari, String codiDominiContrasenyes) throws Exception {
		UserService usuariService = getUserService();
		//String dominiDefecte = getDominiCorreuEntityDao()
		String password = usuariService.setInitialPassword(codiUsuari, codiDominiContrasenyes);
		return password;
	}

	protected Collection<String> handleGetContractTypesUserCreate() throws Exception {
		LinkedList<String> list = new LinkedList();
		list.add(Messages.getString("CreateDisableUserServiceImpl.PublicAdmin")); //$NON-NLS-1$
		list.add(Messages.getString("CreateDisableUserServiceImpl.ServiceContract")); //$NON-NLS-1$
		list.add(Messages.getString("CreateDisableUserServiceImpl.User")); //$NON-NLS-1$
		return list;
	}

	private boolean isAnyValueCode(String code) {
		return code == null || code.trim().length() == 0;
	}

	protected Collection<User> handleFindUserByUserData(String codiUsuari, String dni, String nom, String primerLlinatge, String segonLlinatge) throws Exception {
		UserService usuariService = getUserService();
		if (isAnyValueCode(codiUsuari) && isAnyValueCode(dni) && isAnyValueCode(nom) && isAnyValueCode(primerLlinatge)
				&& isAnyValueCode(segonLlinatge)) {
			throw new SeyconException(Messages.getString("CreateDisableUserServiceImpl.RestrictionAlert")); //$NON-NLS-1$
		}
		Collection<User> usuaris = usuariService.findUsersByCoreData(codiUsuari, nom, primerLlinatge, segonLlinatge, dni);
		return usuaris;
	}

	protected Collection<Group> handleFindGroupsByUserCode(String codiUsuari) throws Exception {
		GroupService grupService = getGroupService();
		return grupService.findGroupsByUserName(codiUsuari);
	}

	class ComparaGrups implements Comparator {
		// Nos permite ordenar los resultados de grupos por código

		public int compare(Object arg0, Object arg1) {
			if (arg0 instanceof Group && arg1 instanceof Group) {
				Group g1 = (Group) arg0;
				Group g2 = (Group) arg1;
				return g1.getName().compareTo(g2.getName());
			}
			return 0;
		}

	}

	protected Collection<Group> handleGetManagedGroups() throws Exception {
		String user = Security.getCurrentUser();
		return handleGetManagedGroups(user);
	}

	protected Collection<Group> handleGetManagedGroups(String user) throws Exception {
		UserEntity usuari = getUserEntityDao().findByUserName(user);
		
		String[] auts = getAuthorizationService().getUserAuthorizationString(Security.AUTO_USER_CREATE, user);
		LinkedList<Group> groups = new LinkedList<Group>();
		HashSet<String> groupNames = new HashSet<String>();
		for (String aut : auts) {
            if (aut.equals(Security.AUTO_USER_CREATE + Security.AUTO_ALL)) {
                groups = new LinkedList<Group>(getGroupService().findGroupsByFilter("%", null, null, null, null, "N"));
                break;
            } else if (aut.length() > Security.AUTO_USER_CREATE.length() + 1) {
                String groupName = aut.substring(Security.AUTO_USER_CREATE.length() + 1);
                if (!groupNames.contains(groupName)) {
                    groupNames.add(groupName);
                    groups.add(getGroupService().findGroupByGroupName(groupName));
                }
            }
        }
		
		Collections.sort(groups, new ComparaGrups());
		return groups;
	}

	protected Boolean handleExistShortName(String nomCurt) throws Exception {
		UserService usuariService = getUserService();
		return usuariService.shortNameExists(nomCurt);
	}

	protected Group handleGetSuperGroup(String codiSubGrup) throws Exception {
		GroupService grupService = getGroupService();
		return grupService.getSuperGroup(codiSubGrup);
	}

	protected User handleFindUserByShortName(String nomCurt) throws Exception {
		UserService usuariService = getUserService();
		return usuariService.findByShortName(nomCurt);
	}

	private void addGroupAndDescendants(Collection<Group> grups, GroupEntity grup) {
		if (grup != null) {
			Group grupVO = getGroupEntityDao().toGroup(grup);
			if (!grupsContainsGrup(grups, grupVO)) {
				grups.add(grupVO);
				Iterator it = grup.getChildren().iterator();
				while (it.hasNext()) {
					addGroupAndDescendants(grups, (GroupEntity) it.next());
				}
			}
		}
	}

	private boolean grupsContainsGrup(Collection<Group> grups, Group grup) {
		boolean contains = false;
		Iterator grupsIterator = grups.iterator();
		while (grupsIterator.hasNext() && !contains) {
			Group currentGrup = (Group) grupsIterator.next();
			contains = currentGrup.getName().compareTo(grup.getName()) == 0;
		}
		return contains;
	}

	protected Role handleGetAdministratorRoleByGroup(String codiGrup) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected Collection<User> handleGetUsersByNIF(String nif) throws Exception {
		Collection<UserEntity> usuaris = getUserEntityDao().findUsersByNationalID(nif);
		return getUserEntityDao().toUserList(usuaris);
	}

	protected Collection<Group> handleFindGroupsByFilter(String codi, String pare, String unitatOfimatica, String descripcio, String tipus, String obsolet) throws Exception {

		// Las obtenemos sin restricciones (en formato VO !!)
		if (codi != null && (codi.trim().compareTo("") == 0 || codi.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			codi = null;
		}
		if (pare != null && (pare.trim().compareTo("") == 0 || pare.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			pare = null;
		}
		if (unitatOfimatica != null && (unitatOfimatica.trim().compareTo("") == 0 || unitatOfimatica.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			unitatOfimatica = null;
		}
		if (descripcio != null && (descripcio.trim().compareTo("") == 0 || descripcio.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			descripcio = null;
		}
		if (tipus != null && (tipus.trim().compareTo("") == 0 || tipus.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			tipus = null;
		}
		if (obsolet != null && (obsolet.trim().compareTo("") == 0 || obsolet.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			obsolet = null;
		}
		Principal principal = Security.getPrincipal();
		if (principal == null) {
			return new Vector();
		}
		Collection<GroupEntity> grups = getGroupEntityDao().findByCriteria(codi, pare, unitatOfimatica, descripcio, tipus, obsolet);
		if (grups != null) {
			if (grups.size() >= 201) { // PJR: poso >= en comptes de ==
				throw new SeyconException(Messages.getString("CreateDisableUserServiceImpl.BigSearchResults")); //$NON-NLS-1$
			}
			return getGroupEntityDao().toGroupList(grups);
		}
		return new LinkedList<Group>();
	}
}