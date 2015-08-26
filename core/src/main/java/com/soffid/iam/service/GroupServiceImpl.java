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

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupRoles;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.sync.engine.TaskHandler;

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.Security;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * @see es.caib.seycon.ng.servei.GrupService
 */
public class GroupServiceImpl extends com.soffid.iam.service.GroupServiceBase {
	/**
	 * @throws InternalErrorException 
	 * @see es.caib.seycon.ng.servei.GrupService#createGrup(es.caib.seycon.ng.comu.Grup)
	 */
	protected Collection<Group> handleGetConselleriesAmbDireccionsGenerals() throws InternalErrorException {
		LinkedList grupsAmbDireccionsGenerals = new LinkedList();
		Collection grups = this.findGroupsByFilter(null, null, null, null, "CONSELLERIA", null); //$NON-NLS-1$
		Iterator iterator = grups.iterator();
		while (iterator.hasNext()) {
			Group grup = (Group) iterator.next();
			Collection subgrups = findSubgroupsByGroupName(grup.getName());
			if (subgrups.size() > 0) {
				grupsAmbDireccionsGenerals.add(grup);
			}
		}
		return grupsAmbDireccionsGenerals;
	}

	protected com.soffid.iam.api.Group handleCreateGrup(com.soffid.iam.api.Group grup) throws java.lang.Exception {

		GroupEntity entity = getGroupEntityDao().groupToEntity(grup);
		if (getAuthorizationService().hasPermission(Security.AUTO_GROUP_CREATE, entity)) {
			getGroupEntityDao().create(entity);
			grup.setId(entity.getId());
			return getGroupEntityDao().toGroup(entity);
		}
		throw new SeyconException(Messages.getString("GroupServiceImpl.0")); //$NON-NLS-1$
	}

	/*
	 * Retorna els subgrups sobre els que té permisos
	 * 
	 * @see es.caib.seycon.ng.servei.GrupServiceBase#handleFindSubGrupsByCodiGrup(java.lang.String)
	 */
	protected Collection<Group> handleFindSubgroupsByGroupName(String codiGrup) throws InternalErrorException {
		// Si és administrador d'usuaris els pot llistar tots
		Collection<GroupEntity> groups = getGroupEntityDao().findByParent(codiGrup);
		if (groups != null) {
			Collection<Group> groupsList = filterGroups(groups);
			return groupsList;
		}

		return Collections.emptyList();
	}

	private List<Group> filterGroups(Collection<GroupEntity> groups) throws InternalErrorException {
		List<Group> groupsList = new LinkedList<Group>();
		for (GroupEntity groupEntity : groups) {
            if (getAuthorizationService().hasPermission(Security.AUTO_GROUP_QUERY, groupEntity)) groupsList.add(getGroupEntityDao().toGroup(groupEntity));
        }
		return groupsList;
	}

	protected Collection<Group> handleGetParentList(String codiGrup) throws InternalErrorException {
		// Sense restricció:
		LinkedList<Group> pares = new LinkedList();
		Group pare = null;
		do {
			pare = getSuperGroup(codiGrup);
			if (pare != null) {
				pares.addFirst(pare);
				codiGrup = pare.getName();
			}
		} while (pare != null);
		return pares;
	}

	protected Collection<Group> handleFindGroupsByGroupsType(String tipusGrup) throws InternalErrorException {
		Collection<GroupEntity> grupEntities = getGroupEntityDao().findByType(tipusGrup);
		if (grupEntities != null) {
			return filterGroups(grupEntities);
		}
		return new Vector();
	}

	protected Host handleGetOfficeServer(Group grup) throws Exception {
		if (grup == null)
			return null;
		String codi = grup.getName();
		if (codi == null)
			return null;
		GroupEntity grupEntity = getGroupEntityDao().findByName(codi);
		if (grupEntity == null) {
			return null;
		}
		HostEntity maquinaEntity = grupEntity.getHomeServer();
		if (maquinaEntity == null) {
			return null;
		}
		Host maquina = getHostEntityDao().toHost(maquinaEntity);
		if (maquina != null) {
			;//System.out.println("Un dels grups te com a maquina: " + maquina.getNom());
		}
		return maquina;
	}

	/**
	 * @see es.caib.seycon.ng.servei.GrupService#getGrups()
	 */
	protected java.util.Collection<Group> handleGetGroups() throws java.lang.Exception {
		return getGroupEntityDao().toGroupList(getGroupEntityDao().loadAll());
	}

	/**
	 * @see es.caib.seycon.ng.servei.GrupService#findGrupByCodi(java.lang.String)
	 */
	protected Group handleFindGroupByGroupName(java.lang.String codi) throws java.lang.Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByName(codi);
		if (grupEntity != null) {
			Group grup = getGroupEntityDao().toGroup(grupEntity);
			return grup;
		}
		return null;
	}

	protected void handleSetSuperGroup(String codiSubGrup, String codiSuperGrup) throws java.lang.Exception {
		GroupEntity groupEntity  = getGroupEntityDao().findByName(codiSubGrup);
		if (groupEntity != null && getAuthorizationService().hasPermission(Security.AUTO_GROUP_UPDATE, groupEntity)) {
			getGroupEntityDao().setParentGroup(codiSubGrup, codiSuperGrup);
		} else {
			throw new SeyconException(String.format(Messages.getString("GroupServiceImpl.1"), codiSuperGrup)); //$NON-NLS-1$
		}
	}

	protected Group handleCreate(Group grup) throws Exception {
		// Verifiquem restriccions:
		// 1) que tinga pare
		// 2) que el codi sigui lletres (minuscula) i numeros
		if (!grup.getName().equals("world") && (grup.getParentGroup() == null || grup.getParentGroup() != null && "".equals(grup.getParentGroup().trim())))
		{
                    throw new SeyconException(Messages.getString("GroupServiceImpl.3")); //$NON-NLS-1$
		}
		// Verifiquem el codi (lletres i números)
		if (!grup.getName().matches("[a-zA-Z0-9\\-]*")) //$NON-NLS-1$
			throw new SeyconException(Messages.getString("GroupServiceImpl.4")); //$NON-NLS-1$
		
		GroupEntity groupsSameCode = getGroupEntityDao().findByName(grup.getName());
		if(groupsSameCode != null)
			throw new SeyconException(String.format(Messages.getString("GroupServiceImpl.CodeGroupExists"), grup.getName())); 
		
		GroupEntity grupEntity = getGroupEntityDao().groupToEntity(grup);
		if (grupEntity != null) {
			getGroupEntityDao().create(grupEntity);
			return getGroupEntityDao().toGroup(grupEntity);
		}
		return null;
	}

	protected void handleDelete(Group grup) throws Exception {
		// Aquesta operació no ha d'estar permitida
		/*if (esAdministradorUsuaris()) {
			GrupEntity grupEntity = getGrupEntityDao().findByCodi(
					grup.getCodi());
			if (grupEntity == null) {
				throw new SeyconException("Grup amb codi '" + grup.getCodi()
						+ "' no trobat.");
			}
			getGrupEntityDao().remove(grupEntity);
		} else {
			throw new SeyconException(
					"No té permisos per eliminar el grup amb codi '"
							+ grup.getCodi() + "'.");
		}*/
		throw new SeyconException(Messages.getString("GroupServiceImpl.5")); //$NON-NLS-1$
	}

	protected Collection<Group> handleFindGroupsByFilter(String codi, String pare, String unitatOfimatica, String descripcio, String tipus, String obsolet) throws Exception {
		// Mantenim versió antiga (amb menys paràmetres)
		return handleFindGroupsByFilter(codi, pare, unitatOfimatica, descripcio, tipus, obsolet, null, null);
	}

	protected Collection<Group> handleFindGroupsByFilter(String codi, String pare, String unitatOfimatica, String descripcio, String tipus, String obsolet, String servidorOfimatic, String seccioPressupostaria) throws Exception {// des de grups.zul
		
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
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
		if (servidorOfimatic != null && (servidorOfimatic.trim().compareTo("") == 0 || servidorOfimatic.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			servidorOfimatic = null;
		}
		if (seccioPressupostaria != null
				&& (seccioPressupostaria.trim().compareTo("") == 0 || seccioPressupostaria.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			seccioPressupostaria = null;
		}

		Principal principal = this.getPrincipal();
		if (principal == null) {
			return new Vector();
		}
		Collection<GroupEntity> grups = getGroupEntityDao().findByCriteria(codi, pare, unitatOfimatica, descripcio, tipus, obsolet, servidorOfimatic, seccioPressupostaria);
		if (grups != null)
		{
			// FILTREM per autoritzacio group:query [sense_domini O GRUPS]
			List<Group> grupsPermis = filterGroups(grups);
			
			// Check maximun number of results
			if (grupsPermis.size() > limitResults)
			{
				return grupsPermis.subList(0, limitResults);
			}
			return grupsPermis;
		}
		return new Vector();
	}

	private Collection<String> getCodisGrupsLectura() throws InternalErrorException {
		Collection grups = getGrupsLectura();
		Collection<String> codisGrup = new LinkedList();
		Iterator iterator = grups.iterator();
		while (iterator.hasNext()) {
			Group grup = (Group) iterator.next();
			codisGrup.add(grup.getName());
		}
		return codisGrup;
	}

	private Collection<Group> getGrupsLectura() throws InternalErrorException {
		// Obtenemos sólo los grupos relacionados con los roles (para poder ver
		// los grupos donde
		// el usuario tiene asignado un rol: antes se filtra)
		Collection grupsFromRols = findGroupsFromRolesByUserName(getPrincipal().getName());
		Collection grups = getSubGrups(grupsFromRols);

		if (grups != null) {
			Collection<Group> grupsToReturn = new HashSet();
			grupsToReturn.addAll(grups);
			for (Iterator iterator = grups.iterator(); iterator.hasNext(); ) {
                Group grup = (Group) iterator.next();
                Collection<Group> currentGrups = getParentList(grup.getName());
                grupsToReturn.addAll(currentGrups);
            }
			return new LinkedList(grupsToReturn);
		}
		return new Vector();
	}

	protected Group handleUpdate(Group grup) throws Exception {
		GroupEntity entity = getGroupEntityDao().groupToEntity(grup);
		getGroupEntityDao().update(entity);
		return getGroupEntityDao().toGroup(entity);
	}

	protected void handleAddGroupToUser(String codiUsuari, String codiGrup) throws Exception {
		GroupUser usuariGrup = new GroupUser();
		usuariGrup.setGroup(codiGrup);
		usuariGrup.setUser(codiUsuari);
		handleCreate(usuariGrup);
	}

	protected void handleRemoveGroupFormUser(String codiUsuari, String codiGrup) throws Exception {
		UserGroupEntity usuariGrup = getUserGroupEntityDao().findByUserAndGroup(codiUsuari, codiGrup);
		long userId = usuariGrup.getUser().getId();
		long groupId = usuariGrup.getGroup().getId();
		getUserGroupEntityDao().remove(usuariGrup);
		/*IAM-318*/
		handlePropagateRolsChangesToDispatcher(codiGrup);
		getApplicationService().revokeRolesHoldedOnGroup(userId, groupId);

	}

	protected Group handleFindPrimaryGroupByUserName(String codiUsuari) throws Exception {
		GroupEntity grupEntity = this.getGroupEntityDao().findPrimaryGroupByUser(codiUsuari);
		if (grupEntity != null) {
			Group grup = this.getGroupEntityDao().toGroup(grupEntity);
			return grup;
		}
		return null;
	}

	protected Collection<Group> handleFindGroupsByUserName(String codiUsuari) throws InternalErrorException {
		Collection<Group> grups = new LinkedHashSet();
		// Grupo Primario
		Group grupPrimari = findPrimaryGroupByUserName(codiUsuari);
		if (grupPrimari != null) {
			grups.add(grupPrimari);
		}
		// Grupos secundarios
		Collection grupsFromUsuaris = findGroupsFromUsersByUserName(codiUsuari);
		grups.addAll(grupsFromUsuaris);
		// Grupos relacionados con los roles del usuario
		Collection grupsFromRols = findGroupsFromRolesByUserName(codiUsuari);
		grups.addAll(grupsFromRols);

		return getSubGrups(grups);
	}

	protected Collection<Group> handleFindGroupsFromRolesByUserName(String codiUsuari) throws Exception {
		Collection<GroupEntity> grups = getGroupEntityDao().findByGrantedRolesToUser(codiUsuari);
		if (grups != null) {
			return getGroupEntityDao().toGroupList(grups);
		}
		return new Vector();
	}

	protected Collection<Group> handleFindGroupsFromUsersByUserName(String codiUsuari) throws Exception {
		Collection<GroupEntity> grups = getGroupEntityDao().findGroupsByUser(codiUsuari);
		if (grups != null) {
			return getGroupEntityDao().toGroupList(grups);
		}
		return new Vector();
	}

	protected GroupUser handleCreate(GroupUser usuariGrup) throws Exception {
		UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().groupUserToEntity(usuariGrup);
		if (usuariGrupEntity.getUser().getUserName().compareTo(getPrincipal().getName()) == 0) {
			throw new SeyconException(Messages.getString("GroupServiceImpl.7")); //$NON-NLS-1$
		}
		UserEntity usuari = usuariGrupEntity.getUser();

		if (getAuthorizationService().hasPermission(Security.AUTO_USER_GROUP_CREATE, usuariGrupEntity)) {

			usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
			usuari.setLastUserModification(getPrincipal().getName());
			getUserEntityDao().update(usuari);

			getUserGroupEntityDao().create(usuariGrupEntity);
			usuariGrup.setId(usuariGrupEntity.getId());
			usuariGrup = getUserGroupEntityDao().toGroupUser(usuariGrupEntity);
			/*IAM-318*/
			handlePropagateRolsChangesToDispatcher(usuariGrup.getGroup());
			getRuleEvaluatorService().applyRules(usuari);

			return usuariGrup;
		} else {
			throw new SeyconAccessLocalException("grupService", "create (UsuariGrup)", "user:group:create", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$
		}
	}

	private boolean esPotEliminarUsuariGrup(UserGroupEntity usuariGrup) {
		// Obtenim el grup primari de l'usuari
		GroupEntity gp = usuariGrup.getUser().getPrimaryGroup();
		String codiGrupPrimari = gp != null && gp.getName() != null ? gp.getName() : ""; //$NON-NLS-1$
		for (RoleAccountEntity rolUsuari : getRoleAccountEntityDao().findByUserName(usuariGrup.getUser().getUserName())) {
            if (rolUsuari.getDomainType().compareTo(TipusDomini.GRUPS_USUARI) == 0) {
                String codiGrupValorDomini = rolUsuari.getGroup().getName();
                String codiGrupGrupUsuari = usuariGrup.getGroup().getName();
                if (codiGrupValorDomini.compareTo(codiGrupGrupUsuari) == 0) {
                    if (!codiGrupPrimari.equals(codiGrupValorDomini)) return false;
                }
            }
        }
		return true;
	}

	protected void handleDelete(GroupUser usuariGrup) throws Exception {

		UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().groupUserToEntity(usuariGrup);

		if (!esPotEliminarUsuariGrup(usuariGrupEntity)) {
			throw new SeyconException(String.format(Messages.getString("GroupServiceImpl.8"), usuariGrup.getGroup(), usuariGrup.getUser()));
		}

		// Mirem les autoritzacions
		if (getAuthorizationService().hasPermission(Security.AUTO_USER_GROUP_CREATE, usuariGrupEntity)) {
			
			UserEntity usuari = usuariGrupEntity.getUser();
			usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
			usuari.setLastUserModification(getPrincipal().getName());
			getUserEntityDao().update(usuari);
			long groupId = usuariGrupEntity.getGroup().getId();

			getUserGroupEntityDao().remove(usuariGrupEntity);
			
			usuari.getSecondaryGroups().remove(usuariGrupEntity);

			getApplicationService().revokeRolesHoldedOnGroup(usuari.getId(), groupId);

			getRuleEvaluatorService().applyRules(usuari);
		} else {
			throw new SeyconAccessLocalException("grupService", "delete (UsuariGrup)", "user:group:delete", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to delete groups for this user"); //$NON-NLS-1$
		}
	}

	protected GroupUser handleUpdate(GroupUser usuariGrup) throws Exception {

		UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().load(usuariGrup.getId());
		if (!getAuthorizationService().hasPermission(Security.AUTO_USER_GROUP_DELETE, usuariGrupEntity)) {
			throw new SeyconAccessLocalException("grupService", "update (UsuariGrup)", "user:group:delete", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$
		}
		usuariGrupEntity = getUserGroupEntityDao().groupUserToEntity(usuariGrup);

		// En principi no ha d'existir update--- seria un create
		if (getAuthorizationService().hasPermission(Security.AUTO_USER_GROUP_CREATE, usuariGrupEntity)) {

			UserEntity usuari = usuariGrupEntity.getUser();
			usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
			usuari.setLastUserModification(getPrincipal().getName());
			getUserEntityDao().update(usuari);

			getUserGroupEntityDao().update(usuariGrupEntity);
			usuariGrup = getUserGroupEntityDao().toGroupUser(usuariGrupEntity);
			return usuariGrup;
		} else {
			throw new SeyconAccessLocalException("grupService", "update (UsuariGrup)", "user:group:create", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$

		}
	}

	protected GroupUser handleFindUserGroupByUserNameAndGroupName(String codiUsuari, String codiGrup) throws Exception {
		UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().findByUserAndGroup(codiUsuari, codiGrup);
		if (usuariGrupEntity != null) {
			GroupUser usuariGrup = getUserGroupEntityDao().toGroupUser(usuariGrupEntity);
			return usuariGrup;
		}
		return null;
	}

	private Collection<Group> findSubGrupsWithoutSecurityRestrictionsByCodiGrup(String codiGrup) {
		Collection<GroupEntity> groups = getGroupEntityDao().findByParent(codiGrup);
		if (groups != null) {
			return getGroupEntityDao().toGroupList(groups);
		}
		return new Vector();
	}

	/**
	 * Dado un listado de grupos, obtiene sus subgrupos
	 * 
	 * @param grupsGetSubgrups
	 * @return
	 */
	private Collection<Group> getSubGrups(Collection grupsGetSubgrups) {
		Stack stack = new Stack();
		Collection<Group> grupsISubgrups = new HashSet(); // perquè no es repetisquen
		grupsISubgrups.addAll(grupsGetSubgrups);
		stack.addAll(grupsGetSubgrups);
		while (!stack.empty()) {
			Group grupActual = (Group) stack.pop();
			grupsISubgrups.add(grupActual);
			stack.addAll(findSubGrupsWithoutSecurityRestrictionsByCodiGrup(grupActual.getName()));
		}
		return grupsISubgrups;
	}

	protected Group handleGetSuperGroup(String codiGrup) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByChild(codiGrup);
		if (grupEntity != null) {
			return getGroupEntityDao().toGroup(grupEntity);
		}
		return null;
	}

	protected Collection<RoleAccount> handleFindUsersRolesWithGroupByUserName(String codiUsuari) throws Exception {
		Collection<RoleAccount> result = new LinkedList<RoleAccount>();
		
		for (RoleAccountEntity ra : this.getRoleAccountEntityDao().findByUserName(codiUsuari)) {
            if (ra.getGroup() != null) result.add(getRoleAccountEntityDao().toRoleAccount(ra));
        }
		return result;
	}

	protected Collection<GroupUser> handleFindUsersGroupByUserName(String codiUsuari) throws Exception {
		Collection<UserGroupEntity> usuariGrups = getUserGroupEntityDao().findByUserName(codiUsuari);
		if (usuariGrups != null) {
			return getUserGroupEntityDao().toGroupUserList(usuariGrups);
		}
		return new Vector();
	}

	protected Collection<GroupUser> handleFindUsersBelongtoGroupByGroupName(String codiGrup) throws Exception {

		// Obtenim els usuaris que tenen aquest grup com a grup primari
		// o com a grup secundari i filtrem per autoritzacions
		Collection<GroupUser> totsUsuarisGrup = new LinkedList();

		// Mirem les autoritzacions a nivell de grup per group:user:query
		Collection usuari = getUserEntityDao().findByPrimaryGroup(codiGrup);
		for (Iterator it = usuari.iterator(); it.hasNext(); ) {
            UserEntity user = (UserEntity) it.next();
            if (getAuthorizationService().hasPermission(Security.AUTO_USER_QUERY, user)) {
                String nomComplet = user.getFullName();
                GroupUser usugru = new GroupUser(user.getUserName(), user.getPrimaryGroup().getName(), nomComplet);
                usugru.setInfo(Messages.getString("GroupServiceImpl.PrimaryGroupText"));
                totsUsuarisGrup.add(usugru);
            }
        }

		Collection<UserGroupEntity> usuaris = getUserGroupEntityDao().findByGroupName(codiGrup);
		// Los añadimos al listado anterior
		for (UserGroupEntity uge : usuaris) {
            if (getAuthorizationService().hasPermission(Security.AUTO_USER_QUERY, uge)) {
                GroupUser ug = getUserGroupEntityDao().toGroupUser(uge);
                ug.setInfo(Messages.getString("GroupServiceImpl.SecondaryGroupText"));
                totsUsuarisGrup.add(ug);
            }
        }

		// sinó tornem la llista buida
		return totsUsuarisGrup;
	}

	protected Group handleFindGroupById(Long grupId) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().load(grupId);
		if (grupEntity != null) {
			return getGroupEntityDao().toGroup(grupEntity);
		}
		return null;
	}

	protected Collection<Role> handleGetRolesFromGroup(Group grup) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByName(grup.getName());
		Collection rolsGrupE = grupEntity.getAllowedRolesToGroup();
		Vector rolsGrup = new Vector(rolsGrupE);// Lo activamos
		// NOTA: Aquí obtenemos los roles, no los roles-grupo(!!) ¿Se utiliza?
		return getRoleEntityDao().toRoleList(rolsGrup);
	}

	protected Collection<GroupRoles> handleGetRolesFromGroup(String codiGrup) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByName(codiGrup);
		Collection rolsGrupE = grupEntity.getAllowedRolesToGroup();
		LinkedList rolsGrup = new LinkedList(rolsGrupE);// Lo activamos

		return getRoleGroupEntityDao().toGroupRolesList(rolsGrup);
	}

	protected java.security.Principal getPrincipal() {
		return Security.getPrincipal();
	}

	protected Collection<RoleAccount> handleFindUsersRolesDomainTypeAndUserGroups(String codiGrup) throws Exception {
		Collection<RoleAccountEntity> rolsUsuGrup = getRoleAccountEntityDao().findByGroupName(codiGrup);
		return getRoleAccountEntityDao().toRoleAccountList(rolsUsuGrup);
	}

	protected Collection<GroupRoles> handleGetRolesFromGroupAndParentGroup(Group grup) throws Exception {
		GroupEntity entity = getGroupEntityDao().findByName(grup.getName());
		Collection<RoleGroupEntity> rolsGrupE = entity.getAllowedRolesToGroup();
		LinkedList<RoleGroupEntity> totsRolsGrup = new LinkedList(rolsGrupE);

		// Buscamos los padres del grupo actual
		GroupEntity pare = entity.getParent();
		while (pare != null) {
			Collection<RoleGroupEntity> rolsGrupPare = pare.getAllowedRolesToGroup();
			totsRolsGrup.addAll(rolsGrupPare);
			pare = pare.getParent();
		}

		return getRoleGroupEntityDao().toGroupRolesList(totsRolsGrup);
	}

	/*IAM-318*/
	protected void handlePropagateRolsChangesToDispatcher(String grup) throws InternalErrorException{
		GroupEntity grupEntity = getGroupEntityDao().findByName(grup);
		if (grupEntity != null)
		{
							
			for (RoleGroupEntity rolGrup : grupEntity.getAllowedRolesToGroup()) {
                RoleEntity rol = rolGrup.getAssignedRole();
                TaskEntity tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_ROLE);
                tasque.setRole(rol.getName());
                tasque.setDb(rol.getSystem().getName());
                getTaskEntityDao().create(tasque);
            }
		}
	}
}
