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

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupRoles;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.User;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.ApplicationAttributeEntity;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.GroupAttributeEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.MetaDataEntityDao;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.QueryBuilder;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleAccountEntityDao;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupAttributeEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.UserGroupEntityDao;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.attribute.AttributePersister;
import com.soffid.iam.service.impl.AttributeValidationService;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.servei.GrupService
 */
public class GroupServiceImpl extends com.soffid.iam.service.GroupServiceBase {
	Log log = LogFactory.getLog(getClass());
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

	/*
	 * Retorna els subgrups sobre els que té permisos
	 * 
	 * @see es.caib.seycon.ng.servei.GrupServiceBase#handleFindSubGrupsByCodiGrup(java.lang.String)
	 */
	protected Collection<Group> handleFindSubgroupsByGroupName(String codiGrup) throws InternalErrorException {
		// Si és administrador d'usuaris els pot llistar tots
		Collection<GroupEntity> groups = getGroupEntityDao().findByParent(codiGrup);
		if (groups != null) {
			List<Group> groupsList = filterGroups(groups);
			Collections.sort(groupsList, new Comparator<Group>() {
				@Override
				public int compare(Group o1, Group o2) {
					return o1.getDescription().compareTo(o2.getDescription());
				}
				
			});
			return groupsList;
		}

		return Collections.emptyList();
	}

	protected Collection<Group> handleFindSubgroupsByGroupNameAndDate(String codiGrup, Date d) throws InternalErrorException {
		if (d == null)
			return handleFindSubgroupsByGroupName(codiGrup);
		// Si és administrador d'usuaris els pot llistar tots
		Collection<GroupEntity> groups = getGroupEntityDao().findByParent(codiGrup, d);
		if (groups != null) {
			List<Group> groupsList = filterGroups(groups);
			Collections.sort(groupsList, new Comparator<Group>() {
				@Override
				public int compare(Group o1, Group o2) {
					return o1.getDescription().compareTo(o2.getDescription());
				}
				
			});
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
		HostEntity maquinaEntity = grupEntity.getDriveServer();
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

	protected Group handleFindGroupByGroupNameAndDate(java.lang.String codi, Date d) throws java.lang.Exception {
		if (d == null)
			return handleFindGroupByGroupName(codi);
		GroupEntity grupEntity = getGroupEntityDao().findByNameAndDate(codi, d);
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
//		if (!grup.getName().equals("world") && (grup.getParentGroup() == null || grup.getParentGroup() != null && "".equals(grup.getParentGroup().trim())))
//		{
//                    throw new SeyconException(Messages.getString("GroupServiceImpl.3")); //$NON-NLS-1$
//		}
		
		GroupEntity groupsSameCode = getGroupEntityDao().findByName(grup.getName());
		if(groupsSameCode != null)
			throw new SeyconException(String.format(Messages.getString("GroupServiceImpl.CodeGroupExists"), grup.getName())); 
		
		GroupEntity grupEntity = getGroupEntityDao().groupToEntity(grup);
		if (grupEntity != null) {
			if (ConfigurationCache.isHistoryEnabled()) {
				if (! "S".equals(grupEntity.getObsolete())) {
					if (grupEntity.getStartDate() == null)
						grupEntity.setStartDate(new Date());
					grupEntity.setEndDate(null);
				}
			}
			getGroupEntityDao().create(grupEntity);
			updateGroupAttributes(grup, grupEntity);
			return getGroupEntityDao().toGroup(grupEntity);
		}
		return null;
	}

	protected void handleDelete(Group grup) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByName(grup.getName());
		if (grupEntity == null) {
			throw new SeyconException("Group not found: " + grup.getName());
		}
		for (UserEntity u: grupEntity.getPrimaryGroupUsers())
		{
			u.setPrimaryGroup(grupEntity.getParent());
			getUserEntityDao().update(u);
		}
		
		for (RoleAccountEntity ra: grupEntity.getHoldedRoleAssignments())
		{
			ra.setHolderGroup(grupEntity.getParent());
			getRoleAccountEntityDao().update(ra);
		}
		
		for (GroupEntity ch: grupEntity.getChildren())
		{
			ch.setParent(grupEntity.getParent());
			getGroupEntityDao().update(ch);
		}
		if (ConfigurationCache.isHistoryEnabled()) {
			grupEntity.setObsolete("S");
			if (grup.getEndDate() != null)
				grupEntity.setEndDate(grupEntity.getEndDate());
			else
				grupEntity.setEndDate(new Date());
			getGroupEntityDao().update(grupEntity);
		}
		else {
			getGroupEntityDao().remove(grupEntity);
			
		}
	}

	protected Collection<Group> handleFindGroupsByFilter(String codi, String pare, String unitatOfimatica, String descripcio, String tipus, String obsolet) throws Exception {
		// Mantenim versió antiga (amb menys paràmetres)
		return handleFindGroupsByFilter(codi, pare, unitatOfimatica, descripcio, tipus, obsolet, null, null);
	}

	protected Collection<Group> handleFindGroupsByFilter(String codi, String pare, String unitatOfimatica, String descripcio, String tipus, String obsolet, String servidorOfimatic, String seccioPressupostaria) throws Exception {// des de grups.zul
		
		int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
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

		Principal principal = Security.getSoffidPrincipal();
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
		Collection grupsFromRols = findGroupsFromRolesByUserName(Security.getCurrentUser());
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
		if (ConfigurationCache.isHistoryEnabled()) {
			GroupEntity old = getGroupEntityDao().load(grup.getId());
			makeACopy(old, grup);
		}
		GroupEntity entity = getGroupEntityDao().groupToEntity(grup);
		// Check for loops
		for (GroupEntity e = entity.getParent(); e != null; e = e.getParent()) {
			if (e == entity) {
				throw new InternalErrorException("The new parent for group "+grup.getName()+" cannot be "+grup.getParentGroup()+", as it would create a loop");
			}
		}
		getGroupEntityDao().update(entity);
		updateGroupAttributes(grup, entity);
		return getGroupEntityDao().toGroup(entity);
	}

	protected Group handleCreateHistoric(Group grup) throws Exception {
		if (ConfigurationCache.isHistoryEnabled() && Boolean.TRUE.equals(grup.getObsolete())) {
			GroupEntity entity = getGroupEntityDao().groupToEntity(grup);
			for (GroupEntity e = entity.getParent(); e != null; e = e.getParent()) {
				if (e == entity) {
					throw new InternalErrorException("The new parent for group "+grup.getName()+" cannot be "+grup.getParentGroup()+", as it would create a loop");
				}
			}
			getGroupEntityDao().create(entity);
			updateGroupAttributes(grup, entity);
			return getGroupEntityDao().toGroup(entity);
		} else {
			return handleUpdate(grup);
		}
	}

	private void makeACopy(GroupEntity old, Group grup) throws InternalErrorException {
		if (Boolean.TRUE.equals(grup.getObsolete())) {
			if (grup.getEndDate() != null)
				grup.setEndDate(new Date());
		} else {
			Group copyObject = getGroupEntityDao().toGroup(old);
			copyObject.setId(null);
			if (grup.getStartDate() != null) {
				copyObject.setEndDate(grup.getStartDate());
			} else {
				copyObject.setEndDate(new Date());
				grup.setStartDate(copyObject.getEndDate());
			}
			GroupEntity copy = getGroupEntityDao().groupToEntity(copyObject);
			copy.setObsolete("S");
			copy.setEndDate(new Date());
			getGroupEntityDao().create(copy);
			updateGroupAttributes(copyObject, copy);
		}
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

		usuariGrup.getUser().getSecondaryGroups().remove(usuariGrup);
		usuariGrup.getGroup().getSecondaryGroupUsers().remove(usuariGrup);
		getUserGroupEntityDao().remove(usuariGrup);


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
		usuariGrupEntity.setStart(new Date());
		usuariGrupEntity.setEnd(null);
		usuariGrupEntity.setDisabled(Boolean.FALSE);
		if (usuariGrupEntity.getUser().getUserName().equals (Security.getCurrentUser())) {
			throw new SeyconException(Messages.getString("GroupServiceImpl.7")); //$NON-NLS-1$
		}
		UserEntity usuari = usuariGrupEntity.getUser();

		if (getAuthorizationService().hasPermission(Security.AUTO_USER_GROUP_CREATE, usuariGrupEntity)) {

			usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
			usuari.setLastUserModification(Security.getCurrentAccount());
			getUserEntityDao().update(usuari);

			getUserGroupEntityDao().create(usuariGrupEntity);
			
			usuari.getSecondaryGroups().add(usuariGrupEntity);
			usuariGrupEntity.getGroup().getSecondaryGroupUsers().add(usuariGrupEntity);
			
			new UserGroupAttributePersister().updateAttributes(usuariGrup.getAttributes(), usuariGrupEntity);
			usuariGrup = getUserGroupEntityDao().toGroupUser(usuariGrupEntity);
			
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
            if (TipusDomini.GRUPS_USUARI.equals(rolUsuari.getDomainType()) ||
            		TipusDomini.MEMBERSHIPS.equals(rolUsuari.getDomainType())) {
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
			usuari.setLastUserModification(Security.getCurrentAccount());
			getUserEntityDao().update(usuari);
			long groupId = usuariGrupEntity.getGroup().getId();

			
			if ( ConfigurationCache.isHistoryEnabled())
			{
				usuariGrupEntity.setDisabled(true);
				usuariGrupEntity.setEnd(new Date());
				getUserGroupEntityDao().update(usuariGrupEntity);
			}
			else
			{
				usuari.getSecondaryGroups().remove(usuariGrupEntity);
				usuariGrupEntity.getGroup().getSecondaryGroupUsers().remove(usuariGrupEntity);
				getUserGroupEntityDao().remove(usuariGrupEntity);
			}

			getApplicationService().revokeRolesHoldedOnGroup(usuari.getId(), groupId);

			getRuleEvaluatorService().applyRules(usuari);
		} else {
			throw new SeyconAccessLocalException("grupService", "delete (UsuariGrup)", "user:group:delete", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to delete groups for this user"); //$NON-NLS-1$
		}
	}

	protected GroupUser handleUpdate(GroupUser usuariGrup) throws Exception {
		if (Boolean.TRUE.equals(usuariGrup.getPrimaryGroup())) {
			User u = getUserService().findUserByUserName(usuariGrup.getUser());
			if (u == null) {
				throw new InternalErrorException(String.format("Cannot modify user's primary group. User %s does not exist", usuariGrup.getUser()));
			}
			u.setPrimaryGroup(usuariGrup.getGroup());
			getUserService().update(u);
			return usuariGrup;
		} else {
			UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().load(usuariGrup.getId());
			if ( usuariGrup.getUser().equals(usuariGrupEntity.getUser().getUserName()) && 
					usuariGrup.getGroup().equals(usuariGrupEntity.getGroup().getName()))
			{
				new UserGroupAttributePersister().updateAttributes(usuariGrup.getAttributes(), usuariGrupEntity) ;
				return usuariGrup;
			}
			else
			{
				if (!getAuthorizationService().hasPermission(Security.AUTO_USER_GROUP_DELETE, usuariGrupEntity)) {
					throw new SeyconAccessLocalException("grupService", "update (UsuariGrup)", "user:group:delete", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$
				}
				usuariGrup.setEnd(usuariGrupEntity.getEnd());
				usuariGrup.setStart(usuariGrupEntity.getStart());
				usuariGrup.setDisabled(usuariGrupEntity.getDisabled());
				usuariGrupEntity = getUserGroupEntityDao().groupUserToEntity(usuariGrup);
				new UserGroupAttributePersister().updateAttributes(usuariGrup.getAttributes(), usuariGrupEntity);
		
				// En principi no ha d'existir update--- seria un create
				if (getAuthorizationService().hasPermission(Security.AUTO_USER_GROUP_CREATE, usuariGrupEntity)) {
		
					UserEntity usuari = usuariGrupEntity.getUser();
					usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
					usuari.setLastUserModification(Security.getCurrentAccount());
					getUserEntityDao().update(usuari);
		
					getUserGroupEntityDao().update(usuariGrupEntity);
					usuariGrup = getUserGroupEntityDao().toGroupUser(usuariGrupEntity);
					return usuariGrup;
				} else {
					throw new SeyconAccessLocalException("grupService", "update (UsuariGrup)", "user:group:create", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$
		
				}
			}
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
		AsyncList<GroupUser> result = new AsyncList<GroupUser>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		findGroupUsers(codiGrup, result);
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}

	protected Collection<GroupUser> handleFindUsersBelongtoGroupByGroupName(String codiGrup, Date date) throws Exception {
		if (date == null)
			return handleFindUsersBelongtoGroupByGroupName(codiGrup);
		AsyncList<GroupUser> result = new AsyncList<GroupUser>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		Collection<UserGroupEntity> usuaris = getUserGroupEntityDao().findByGroupName(codiGrup, date);
		// Los añadimos al listado anterior
		for (UserGroupEntity uge : usuaris) {
			if (result.isCancelled())
				TimeOutUtils.generateException();
            if (getAuthorizationService().hasPermission(Security.AUTO_USER_QUERY, uge)) {
                GroupUser ug = getUserGroupEntityDao().toGroupUser(uge);
                if (Boolean.TRUE.equals(ug.getPrimaryGroup()))
                    ug.setInfo(Messages.getString("GroupServiceImpl.PrimaryGroupText"));
                else
                	ug.setInfo(Messages.getString("GroupServiceImpl.SecondaryGroupText"));
                result.add(ug);
            }
        }
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}

	private void findGroupUsers(String codiGrup, AsyncList<GroupUser> result) throws InternalErrorException {
		// Mirem les autoritzacions a nivell de grup per group:user:query
		Collection usuari = getUserEntityDao().findByPrimaryGroup(codiGrup);
		for (Iterator it = usuari.iterator(); it.hasNext(); ) {
			if (result.isCancelled())
				return;
            UserEntity user = (UserEntity) it.next();
            if (getAuthorizationService().hasPermission(Security.AUTO_USER_QUERY, user)) {
                String nomComplet = user.getFullName();
                GroupUser usugru = new GroupUser();
                usugru.setUser(user.getUserName());
                usugru.setGroup(user.getPrimaryGroup().getName());
                usugru.setFullName(nomComplet);
                usugru.setGroupDescription(user.getPrimaryGroup().getDescription());
                usugru.setInfo(Messages.getString("GroupServiceImpl.PrimaryGroupText"));
                usugru.setPrimaryGroup(true);
                result.add(usugru);
            }
        }

		Collection<UserGroupEntity> usuaris = getUserGroupEntityDao().findByGroupName(codiGrup);
		// Los añadimos al listado anterior
		for (UserGroupEntity uge : usuaris) {
			if (result.isCancelled())
				return;
            if (getAuthorizationService().hasPermission(Security.AUTO_USER_QUERY, uge)) {
                GroupUser ug = getUserGroupEntityDao().toGroupUser(uge);
                ug.setInfo(Messages.getString("GroupServiceImpl.SecondaryGroupText"));
                result.add(ug);
            }
        }
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
		Collection rolsGrupE = grupEntity.getGrantedRoles();
		Vector rolsGrup = new Vector(rolsGrupE);// Lo activamos
		// NOTA: Aquí obtenemos los roles, no los roles-grupo(!!) ¿Se utiliza?
		return getRoleEntityDao().toRoleList(rolsGrup);
	}

	protected Collection<GroupRoles> handleGetRolesFromGroup(String codiGrup) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByName(codiGrup);
		Collection rolsGrupE = grupEntity.getGrantedRoles();
		LinkedList rolsGrup = new LinkedList(rolsGrupE);// Lo activamos

		return getRoleGroupEntityDao().toGroupRolesList(rolsGrup);
	}

	protected Collection<RoleAccount> handleFindUsersRolesDomainTypeAndUserGroups(String codiGrup) throws Exception {
		Collection<RoleAccountEntity> rolsUsuGrup = getRoleAccountEntityDao().findByGroupName(codiGrup);
		return getRoleAccountEntityDao().toRoleAccountList(rolsUsuGrup);
	}

	protected Collection<GroupRoles> handleGetRolesFromGroupAndParentGroup(Group grup) throws Exception {
		GroupEntity entity = getGroupEntityDao().findByName(grup.getName());
		Collection<RoleGroupEntity> rolsGrupE = entity.getGrantedRoles();
		LinkedList<RoleGroupEntity> totsRolsGrup = new LinkedList(rolsGrupE);

		// Buscamos los padres del grupo actual
		GroupEntity pare = entity.getParent();
		while (pare != null) {
			Collection<RoleGroupEntity> rolsGrupPare = pare.getGrantedRoles();
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
							
			for (RoleGroupEntity rolGrup : grupEntity.getGrantedRoles()) {
                RoleEntity rol = rolGrup.getGrantedRole();
                TaskEntity tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_ROLE);
                tasque.setRole(rol.getName());
                tasque.setDb(rol.getSystem().getName());
                getTaskEntityDao().create(tasque);
            }
		}
	}

	private void updateGroupAttributes (Group app, GroupEntity entity) throws InternalErrorException
	{
		new GroupAttributePersister().updateAttributes(app.getAttributes(), entity);
	}


	String generateQuickSearchQuery (String text) {
		if (text == null )
			return  "";
		List<MetaDataEntity> atts = getMetaDataEntityDao().findByScope(MetadataScope.GROUP);
		String[] split = ScimHelper.split(text);
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < split.length; i++)
		{
			String t = split[i].replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\\\"");
			if (sb.length() > 0)
				sb.append(" and ");
			sb.append("(");
			sb.append("name co \""+t+"\"");
			sb.append(" or description co \""+t+"\"");
			for (MetaDataEntity att: atts)
			{
				if (att.getSearchCriteria() != null && att.getSearchCriteria().booleanValue())
				{
					if (! att.getName().equals("name") && ! att.getName().equals("description")) {
						if (Boolean.TRUE.equals(att.getBuiltin()))
							sb.append(" or "+att.getName()+" co \""+t+"\"");
						else
							sb.append(" or attributes."+att.getName()+" co \""+t+"\"");
					}
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}

	@Override
	protected AsyncList<Group> handleFindGroupByTextAndFilterAsync(String text, String filter) throws Exception {
		String q = generateQuickSearchQuery(text, filter);
		return handleFindGroupByJsonQueryAsync(q);
			
	}

	@Override
	protected AsyncList<Group> handleFindGroupHistoryByTextAndFilterAsync(String text, String filter, Date date) throws Exception {
		String q = generateQuickSearchQuery(text, filter);
		if (date == null)
			return handleFindGroupByJsonQueryAsync(q);
		else {
			String s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
			final AsyncList<Group> result = new AsyncList<Group>();
			final String q2 = "("+q+") and (not startDate pr or startDate le \""+s+"\") and"
					+ "(not endDate pr or endDate ge \""+s+"\")";
			getAsyncRunnerService().run(new Runnable() {
				public void run() {
					try {
						findByJsonQuery(result, q2, new CriteriaSearchConfiguration());
					} catch (Exception e) {
						result.cancel(e);
					}
				}
			}, result);
			return result;
		}
	}

	@Override
	protected List<Group> handleFindGroupByTextAndFilter(String text, String filter) throws Exception {
		String q = generateQueryCurrent(text, filter);

		return handleFindGroupByJsonQuery(q);
	}

	@Override
	protected PagedResult<Group> handleFindGroupByTextAndFilter(String text, String filter, Integer first, Integer pageSize) throws Exception {
		String q = generateQuickSearchQuery(text, filter);
		return handleFindGroupByJsonQuery(q, first, pageSize);
	}

	private String generateQuickSearchQuery(String text, String filter) {
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		return q;
	}

	protected String generateQueryCurrent(String text, String filter) {
		String s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
		
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+") and ( not endDate pr  or endDate ge \""+s+"\")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = "("+filter+")  and ( not endDate pr  or endDate ge \""+s+"\")";
		else if (!q.isEmpty())
			q = "("+q+")  and ( not endDate pr or endDate ge \""+s+"\")";
		else
			q = "(not endDate pr) or endDate ge \""+s+"\"";
		return q;
	}

	@Override
	protected List<Group> handleFindGroupByText(String text) throws Exception {
		String q = generateQueryCurrent(text, null);
		return handleFindGroupByJsonQuery(q);
	}
	
	@Override
	protected AsyncList<Group> handleFindGroupByTextAsync(final String text) throws Exception {
		String q = generateQuickSearchQuery(text);
		return handleFindGroupByJsonQueryAsync(q);
	}



	protected PagedResult<Group> findByJsonQuery ( AsyncList<Group> result, String query, CriteriaSearchConfiguration cs) throws EvalException, InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException, TokenMgrError, InterruptedException, ExecutionException
	{
		return findByJsonQuery(result, query, null, null);
	}

	protected PagedResult<Group> findByJsonQuery ( AsyncList<Group> result, String query, Integer first, Integer pageSize) throws EvalException, InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException, TokenMgrError, InterruptedException, ExecutionException
	{
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();

		// Prepare query HQL
		AbstractExpression expression = ExpressionParser.parse(query);
		expression.setOracleWorkaround( CustomDialect.isOracle());
		HQLQuery hql = expression.generateHSQLString(Group.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.tenant.id = :tenantId";
		else
			qs = "(" + qs + ") and o.tenant.id = :tenantId";
		hql.setWhereString(new StringBuffer(qs));

		// Include HQL parameters
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size() + 1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());

		CriteriaSearchConfiguration cfg = new CriteriaSearchConfiguration();
		cfg.setFirstResult(first);
		cfg.setMaximumResultSize(pageSize);
		// Execute HQL and generate result
		int totalResults = 0;
		for (GroupEntity ge : getGroupEntityDao().query(hql.toString(), paramArray, cfg )) {
			if (result.isCancelled())
				return null;
			Group g = getGroupEntityDao().toGroup(ge);
			if (!hql.isNonHQLAttributeUsed() || expression.evaluate(g)) {
				if (getAuthorizationService().hasPermission(Security.AUTO_GROUP_QUERY, ge)) {
					result.add(g);
					totalResults ++;
				}
			}
		}
		PagedResult<Group> pagedResult = new PagedResult<Group>();
		pagedResult.setResources(result);
		pagedResult.setStartIndex( first != null ? first: 0);
		pagedResult.setItemsPerPage( pageSize );
		if ( pageSize  != null) {
			@SuppressWarnings("unchecked")
			List <Long> ll = ( List <Long>) new QueryBuilder()
					.query( hql.toCountString(), 
							paramArray);
			for ( Long l: ll ) {
				pagedResult.setTotalResults( new Integer(l.intValue()) );
			}
		} else {
			pagedResult.setTotalResults(totalResults);
		}
		return pagedResult;
	}

	@Override
	protected List<Group> handleFindGroupByJsonQuery(String query) throws InternalErrorException, Exception {
		AsyncList<Group> result = new AsyncList<Group>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());

		String q = generateQueryCurrent(null, query);
		findByJsonQuery(result, q, new CriteriaSearchConfiguration());
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}

	@Override
	protected PagedResult<Group> handleFindGroupByJsonQuery(String query, Integer first, Integer pageSize) throws InternalErrorException, Exception {
		AsyncList<Group> result = new AsyncList<Group>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		String q = generateQueryCurrent(null, query);
		PagedResult<Group> pr = findByJsonQuery(result, q, first, pageSize);
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return pr;
	}


	@Override
	protected AsyncList<Group> handleFindGroupByJsonQueryAsync(final String query) throws Exception {
		final AsyncList<Group> result = new AsyncList<Group>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					String q = generateQueryCurrent(null, query);
					findByJsonQuery(result, q, new CriteriaSearchConfiguration());
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	@Override
	protected Collection<String> handleFindGroupNames() throws Exception {
		return getGroupEntityDao().findGroupNames();
	}

	@Override
	protected AsyncList<GroupUser> handleFindUsersBelongtoGroupByGroupNameAsync(final String codiGrup) throws Exception {
		final AsyncList<GroupUser> result = new AsyncList<GroupUser>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					findGroupUsers(codiGrup, result);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}
	
	class GroupAttributePersister extends AttributePersister<GroupEntity,GroupAttributeEntity> {
		@Override
		protected List<GroupAttributeEntity> findAttributeEntityByNameAndValue(MetaDataEntity m, String v) {
			return getGroupAttributeEntityDao().findByNameAndValue(m.getName(), v);
		}

		@Override
		protected void updateEntity(GroupEntity entity) {
			getGroupEntityDao().update(entity);
		}

		@Override
		protected String getMetadataScope() {
			return Group.class.getName();
		}

		@Override
		protected Collection<GroupAttributeEntity> getEntityAttributes(GroupEntity entity) {
			return entity.getAttributes();
		}

		@Override
		protected GroupAttributeEntity createNewAttribute(GroupEntity entity, MetaDataEntity metadata, Object value) {
			GroupAttributeEntity aae = getGroupAttributeEntityDao().newGroupAttributeEntity();
			aae.setGroup(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getGroupAttributeEntityDao().create(aae);
			return aae;
		}

		@Override
		protected GroupAttributeEntity findAttributeEntity(LinkedList<GroupAttributeEntity> entities, String key,
				Object o) {
			for (GroupAttributeEntity aae: entities)
			{
				if (aae.getMetadata().getName().equals(key))
				{
					if (aae.getObjectValue() != null && aae.getObjectValue().equals(o))
						return aae;
				}
			}
			return null;
		}

		@Override
		protected MetaDataEntityDao getMetaDataEntityDao() {
			return GroupServiceImpl.this.getMetaDataEntityDao();
		}

		@Override
		protected AttributeValidationService getAttributeValidationService() {
			return GroupServiceImpl.this.getAttributeValidationService();
		}

		@Override
		protected void removeAttributes(Collection<GroupAttributeEntity> entities) {
			getGroupAttributeEntityDao().remove(entities);
		}
		
	}

	class UserGroupAttributePersister extends AttributePersister<UserGroupEntity,UserGroupAttributeEntity> {
		@Override
		protected List<UserGroupAttributeEntity> findAttributeEntityByNameAndValue(MetaDataEntity m, String v) {
			return getUserGroupAttributeEntityDao().findByNameAndValue(m.getName(), v);
		}

		@Override
		protected void updateEntity(UserGroupEntity entity) {
			getUserGroupEntityDao().update(entity);
		}

		@Override
		protected String getMetadataScope() {
			return GroupUser.class.getName();
		}

		@Override
		protected Collection<UserGroupAttributeEntity> getEntityAttributes(UserGroupEntity entity) {
			return entity.getAttributes();
		}

		@Override
		protected UserGroupAttributeEntity createNewAttribute(UserGroupEntity entity, MetaDataEntity metadata, Object value) {
			UserGroupAttributeEntity aae = getUserGroupAttributeEntityDao().newUserGroupAttributeEntity();
			aae.setUserGroup(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getUserGroupAttributeEntityDao().create(aae);
			return aae;
		}

		@Override
		protected UserGroupAttributeEntity findAttributeEntity(LinkedList<UserGroupAttributeEntity> entities, String key,
				Object o) {
			for (UserGroupAttributeEntity aae: entities)
			{
				if (aae.getMetadata().getName().equals(key))
				{
					if (aae.getObjectValue() != null && aae.getObjectValue().equals(o))
						return aae;
				}
			}
			return null;
		}

		@Override
		protected MetaDataEntityDao getMetaDataEntityDao() {
			return GroupServiceImpl.this.getMetaDataEntityDao();
		}

		@Override
		protected AttributeValidationService getAttributeValidationService() {
			return GroupServiceImpl.this.getAttributeValidationService();
		}

		@Override
		protected void removeAttributes(Collection<UserGroupAttributeEntity> entities) {
			getUserGroupAttributeEntityDao().remove(entities);
		}
		
	}

	@Override
	protected Collection<GroupUser> handleFindUserGroupHistoryByUserName(String userName) throws Exception {
		UserEntity userEntity = getUserEntityDao().findByUserName(userName);
		if (userEntity == null)
			return new LinkedList<GroupUser>();
		
		return getUserGroupEntityDao().toGroupUserList(userEntity.getSecondaryGroups());
	}

	@Override
	protected PagedResult<GroupUser> handleFindGroupUserByJsonQuery(String query, Integer startIndex, Integer count)
			throws Exception {
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();
		
		final UserGroupEntityDao dao = getUserGroupEntityDao();
		ScimHelper h = new ScimHelper(GroupUser.class);
		h.setPrimaryAttributes(new String[] { "user", "group"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(startIndex);
		config.setMaximumResultSize(count);
		h.setConfig(config);
		h.setTenantFilter("user.tenant.id");
		h.setGenerator((entity) -> {
			return dao.toGroupUser((UserGroupEntity) entity);
		}); 

		
		LinkedList<GroupUser> result = new LinkedList<GroupUser>();
		h.search(null, query, (Collection) result); 

		PagedResult<GroupUser> pr = new PagedResult<>();
		pr.setStartIndex(startIndex);
		pr.setItemsPerPage(count);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}

}
