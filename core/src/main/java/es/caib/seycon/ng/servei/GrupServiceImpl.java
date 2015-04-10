// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.RolsGrup;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import com.soffid.iam.model.Parameter;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.Security;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

/**
 * @see es.caib.seycon.ng.servei.GrupService
 */
public class GrupServiceImpl extends es.caib.seycon.ng.servei.GrupServiceBase {
	/**
	 * @throws InternalErrorException 
	 * @see es.caib.seycon.ng.servei.GrupService#createGrup(es.caib.seycon.ng.comu.Grup)
	 */
	protected Collection<Grup> handleGetConselleriesAmbDireccionsGenerals() throws InternalErrorException {
		LinkedList grupsAmbDireccionsGenerals = new LinkedList();
		Collection grups = this.findGrupsByFiltre(null, null, null, null, "CONSELLERIA", null); //$NON-NLS-1$
		Iterator iterator = grups.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			Collection subgrups = findSubGrupsByCodiGrup(grup.getCodi());
			if (subgrups.size() > 0) {
				grupsAmbDireccionsGenerals.add(grup);
			}
		}
		return grupsAmbDireccionsGenerals;
	}

	protected es.caib.seycon.ng.comu.Grup handleCreateGrup(es.caib.seycon.ng.comu.Grup grup) throws java.lang.Exception {

		if (AutoritzacionsUsuari.canCreateGroup(grup.getCodi())) {
			GroupEntity entity = getGroupEntityDao().grupToEntity(grup);
			getGroupEntityDao().create(entity);
			grup.setId(entity.getId());
			return getGroupEntityDao().toGrup(entity);
		}
		throw new SeyconException(Messages.getString("GrupServiceImpl.0")); //$NON-NLS-1$
	}

	/*
	 * Retorna els subgrups sobre els que té permisos
	 * 
	 * @see es.caib.seycon.ng.servei.GrupServiceBase#handleFindSubGrupsByCodiGrup(java.lang.String)
	 */
	protected Collection<Grup> handleFindSubGrupsByCodiGrup(String codiGrup) {
		// Si és administrador d'usuaris els pot llistar tots
		if (AutoritzacionsUsuari.canQueryGrup(codiGrup)) {
			Collection groups = getGroupEntityDao().findByParent(codiGrup);
			if (groups != null) {
				return getGroupEntityDao().toGrupList(groups);
			}
		} /*else {
			//PJR taskId:837 26/05/09
			//si no és administrador d'usuaris només pot llistar aquells grups als que pertany, y els seus grups pares.
			Collection codisGrupsLectura=getCodisGrupsLectura();
			
			if (codisGrupsLectura.contains(codiGrup)) {
				Collection groups = getGrupEntityDao().findSubGrupsByCodi(codiGrup);
				
				if (groups != null) {
					Vector out=new Vector();
					getGrupEntityDao().toGrupCollection(groups);
					for(Iterator it=groups.iterator();it.hasNext();){
						Grup grupARevisar = (Grup)it.next();
						//només afegim els subgrups sobre els que té permisos
						if(codisGrupsLectura.contains(grupARevisar.getCodi())){
							out.add(grupARevisar);
						}
							
					}
					return out;
				}
			}
			}*/

		return new Vector();
	}

	protected Collection<Grup> handleGetLlistaDePares(String codiGrup) throws InternalErrorException {
		// Sense restricció:
		LinkedList<Grup> pares = new LinkedList();
		Grup pare = null;
		do {
			pare = getSuperGrup(codiGrup);
			if (pare != null) {
				pares.addFirst(pare);
				codiGrup = pare.getCodi();
			}
		} while (pare != null);
		return pares;
	}

	protected Collection<Grup> handleFindGrupsByTipusGrup(String tipusGrup) {
		Collection<GroupEntity> grupEntities = getGroupEntityDao().findByType(tipusGrup);
		if (grupEntities != null) {
			Collection<GroupEntity> grupsPermis = AutoritzacionsUsuari.filtraGrupsEntityCanQuery(grupEntities);
			return getGroupEntityDao().toGrupList(grupsPermis);
		}
		return new Vector();
	}

	protected Maquina handleGetServidorOfimatic(Grup grup) throws Exception {
		if (grup == null)
			return null;
		String codi = grup.getCodi();
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
		Maquina maquina = getHostEntityDao().toMaquina(maquinaEntity);
		if (maquina != null) {
			;//System.out.println("Un dels grups te com a maquina: " + maquina.getNom());
		}
		return maquina;
	}

	/**
	 * @see es.caib.seycon.ng.servei.GrupService#getGrups()
	 */
	protected java.util.Collection<Grup> handleGetGrups() throws java.lang.Exception {
		return getGroupEntityDao().toGrupList(getGroupEntityDao().loadAll());
	}

	/**
	 * @see es.caib.seycon.ng.servei.GrupService#findGrupByCodi(java.lang.String)
	 */
	protected Grup handleFindGrupByCodiGrup(java.lang.String codi) throws java.lang.Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByName(codi);
		if (grupEntity != null) {
			Grup grup = getGroupEntityDao().toGrup(grupEntity);
			return grup;
		}
		return null;
	}

	protected void handleSetSuperGrup(String codiSubGrup, String codiSuperGrup) throws java.lang.Exception {
		if (AutoritzacionsUsuari.canCreateGroup(codiSubGrup) || AutoritzacionsUsuari.canUpdateGrup(codiSubGrup)) {
			getGroupEntityDao().setParentGroup(codiSubGrup, codiSuperGrup);
		} else {
			throw new SeyconException(String.format(Messages.getString("GrupServiceImpl.1"), codiSuperGrup)); //$NON-NLS-1$
		}
	}

	protected Grup handleCreate(Grup grup) throws Exception {
		// Verifiquem restriccions:
		// 1) que tinga pare
		// 2) que el codi sigui lletres (minuscula) i numeros
		if (! grup.getCodi().equals("world") && (grup.getCodiPare() == null || grup.getCodiPare() != null && "".equals(grup.getCodiPare().trim()) //$NON-NLS-1$ //$NON-NLS-2$
		        ) )
		{
                    throw new SeyconException(Messages.getString("GrupServiceImpl.3")); //$NON-NLS-1$
		}
		// Verifiquem el codi (lletres i números)
		if (!grup.getCodi().matches("[a-zA-Z0-9\\-]*")) //$NON-NLS-1$
			throw new SeyconException(Messages.getString("GrupServiceImpl.4")); //$NON-NLS-1$
		
		GroupEntity groupsSameCode = getGroupEntityDao().findByName(grup.getCodi());
		if(groupsSameCode != null)
			throw new SeyconException(String.format(Messages.getString("GrupServiceImpl.CodeGroupExists"),  //$NON-NLS-1$
							grup.getCodi())); 
		
		GroupEntity grupEntity = getGroupEntityDao().grupToEntity(grup);
		if (grupEntity != null) {
			getGroupEntityDao().create(grupEntity);
			return getGroupEntityDao().toGrup(grupEntity);
		}
		return null;
	}

	protected void handleDelete(Grup grup) throws Exception {
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
		throw new SeyconException(Messages.getString("GrupServiceImpl.5")); //$NON-NLS-1$
	}

	protected Collection<Grup> handleFindGrupsByFiltre(String codi, String pare, String unitatOfimatica, String descripcio, String tipus,
			String obsolet) throws Exception {
		// Mantenim versió antiga (amb menys paràmetres)
		return handleFindGrupsByFiltre(codi, pare, unitatOfimatica, descripcio, tipus, obsolet, null, null);
	}

	protected Collection<Grup> handleFindGrupsByFiltre(String codi,
		String pare, String unitatOfimatica, String descripcio, String tipus,
		String obsolet, String servidorOfimatic, String seccioPressupostaria)
			throws Exception {// des de grups.zul
		
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
		Collection grups = getGroupEntityDao().findByCriteria(codi, pare, unitatOfimatica, descripcio, tipus, obsolet, servidorOfimatic, seccioPressupostaria);
		if (grups != null)
		{
			// FILTREM per autoritzacio group:query [sense_domini O GRUPS]
			Collection grupsPermis = AutoritzacionsUsuari.filtraGroupsCanQuery(grups);
			
			// Check maximun number of results
			if (grupsPermis.size() > limitResults)
			{
				return getGroupEntityDao().toGrupList(grupsPermis).subList(0, limitResults);
//				throw new SeyconException(Messages.getString("GrupServiceImpl.6")); //$NON-NLS-1$
			}
			return getGroupEntityDao().toGrupList(grupsPermis);
		}
		return new Vector();
	}

	private Collection<String> getCodisGrupsLectura() throws InternalErrorException {
		Collection grups = getGrupsLectura();
		Collection<String> codisGrup = new LinkedList();
		Iterator iterator = grups.iterator();
		while (iterator.hasNext()) {
			Grup grup = (Grup) iterator.next();
			codisGrup.add(grup.getCodi());
		}
		return codisGrup;
	}

	private Collection<Grup> getGrupsLectura() throws InternalErrorException {
		// Obtenemos sólo los grupos relacionados con los roles (para poder ver
		// los grupos donde
		// el usuario tiene asignado un rol: antes se filtra)
		Collection grupsFromRols = findGrupsFromRolsByCodiUsuari(getPrincipal().getName());
		Collection grups = getSubGrups(grupsFromRols);

		if (grups != null) {
			Collection<Grup> grupsToReturn = new HashSet();
			grupsToReturn.addAll(grups);
			for (Iterator iterator = grups.iterator(); iterator.hasNext();) {
				Grup grup = (Grup) iterator.next();
				Collection<Grup> currentGrups = getLlistaDePares(grup.getCodi());
				grupsToReturn.addAll(currentGrups);
			}
			return new LinkedList(grupsToReturn);
		}
		return new Vector();
	}

	protected Grup handleUpdate(Grup grup) throws Exception {
		GroupEntity entity = getGroupEntityDao().grupToEntity(grup);
		getGroupEntityDao().update(entity);
		return getGroupEntityDao().toGrup(entity);
	}

	protected void handleAddGrupToUsuari(String codiUsuari, String codiGrup) throws Exception {
		UsuariGrup usuariGrup = new UsuariGrup();
		usuariGrup.setCodiGrup(codiGrup);
		usuariGrup.setCodiUsuari(codiUsuari);
		handleCreate(usuariGrup);
	}

	protected void handleRemoveGrupFromUsuari(String codiUsuari, String codiGrup) throws Exception {
		UserGroupEntity usuariGrup = getUserGroupEntityDao().findByUserAndGroup(codiUsuari, codiGrup);
		long userId = usuariGrup.getUser().getId();
		long groupId = usuariGrup.getGroup().getId();
		getUserGroupEntityDao().remove(usuariGrup);
		/*IAM-318*/
		handlePropagateRolsChangesToDispatcher(codiGrup);
		getAplicacioService().revokeRolesHoldedOnGroup(userId, groupId);

	}

	protected Grup handleFindGrupPrimariByCodiUsuari(String codiUsuari) throws Exception {
		GroupEntity grupEntity = this.getGroupEntityDao().findPrimaryGroupByUser(codiUsuari);
		if (grupEntity != null) {
			Grup grup = this.getGroupEntityDao().toGrup(grupEntity);
			return grup;
		}
		return null;
	}

	protected Collection<Grup> handleFindGrupsByCodiUsuari(String codiUsuari) throws InternalErrorException {
		Collection<Grup> grups = new LinkedHashSet();
		// Grupo Primario
		Grup grupPrimari = findGrupPrimariByCodiUsuari(codiUsuari);
		if (grupPrimari != null) {
			grups.add(grupPrimari);
		}
		// Grupos secundarios
		Collection grupsFromUsuaris = findGrupsFromUsuarisByCodiUsuari(codiUsuari);
		grups.addAll(grupsFromUsuaris);
		// Grupos relacionados con los roles del usuario
		Collection grupsFromRols = findGrupsFromRolsByCodiUsuari(codiUsuari);
		grups.addAll(grupsFromRols);

		return getSubGrups(grups);
	}

	protected Collection<Grup> handleFindGrupsFromRolsByCodiUsuari(String codiUsuari) throws Exception {
		Collection<GroupEntity> grups = getGroupEntityDao().findByGrantedRolesToUser(codiUsuari);
		if (grups != null) {
			return getGroupEntityDao().toGrupList(grups);
		}
		return new Vector();
	}

	protected Collection<Grup> handleFindGrupsFromUsuarisByCodiUsuari(String codiUsuari) throws Exception {
		Collection<GroupEntity> grups = getGroupEntityDao().findGroupsByUser(codiUsuari);
		if (grups != null) {
			return getGroupEntityDao().toGrupList(grups);
		}
		return new Vector();
	}

	protected UsuariGrup handleCreate(UsuariGrup usuariGrup) throws Exception {
		UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().usuariGrupToEntity(usuariGrup);
		if (usuariGrupEntity.getUser().getUserName().compareTo(getPrincipal().getName()) == 0) {
			throw new SeyconException(Messages.getString("GrupServiceImpl.7")); //$NON-NLS-1$
		}
		UserEntity usuari = usuariGrupEntity.getUser();

		if (AutoritzacionsUsuari.canCreateUserGroup(usuariGrupEntity.getUser())) {

			usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
			usuari.setLastUserModification(getPrincipal().getName());
			getUserEntityDao().update(usuari);

			getUserGroupEntityDao().create(usuariGrupEntity);
			usuariGrup.setId(usuariGrupEntity.getId());
			usuariGrup = getUserGroupEntityDao().toUsuariGrup(usuariGrupEntity);
			/*IAM-318*/
			handlePropagateRolsChangesToDispatcher(usuariGrup.getCodiGrup());
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

	protected void handleDelete(UsuariGrup usuariGrup) throws Exception {

		UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().usuariGrupToEntity(usuariGrup);

		if (!esPotEliminarUsuariGrup(usuariGrupEntity)) {
			throw new SeyconException(String.format(Messages.getString("GrupServiceImpl.8"),  //$NON-NLS-1$
					usuariGrup.getCodiGrup(),
					usuariGrup.getCodiUsuari()));
		}

		// Mirem les autoritzacions
		if (AutoritzacionsUsuari.canDeleteUserGroup(usuariGrupEntity.getUser())) {
			
			UserEntity usuari = usuariGrupEntity.getUser();
			usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
			usuari.setLastUserModification(getPrincipal().getName());
			getUserEntityDao().update(usuari);
			long groupId = usuariGrupEntity.getGroup().getId();

			getUserGroupEntityDao().remove(usuariGrupEntity);
			
			usuari.getSecondaryGroups().remove(usuariGrupEntity);

			getAplicacioService().revokeRolesHoldedOnGroup(usuari.getId(), groupId);

			getRuleEvaluatorService().applyRules(usuari);
		} else {
			throw new SeyconAccessLocalException("grupService", "delete (UsuariGrup)", "user:group:delete", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to delete groups for this user"); //$NON-NLS-1$
		}
	}

	protected UsuariGrup handleUpdate(UsuariGrup usuariGrup) throws Exception {

		UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().usuariGrupToEntity(usuariGrup);

		// En principi no ha d'existir update--- seria un create
		if (AutoritzacionsUsuari.canCreateUserGroup(usuariGrupEntity.getUser())) {

			UserEntity usuari = usuariGrupEntity.getUser();
			usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
			usuari.setLastUserModification(getPrincipal().getName());
			getUserEntityDao().update(usuari);

			getUserGroupEntityDao().update(usuariGrupEntity);
			usuariGrup = getUserGroupEntityDao().toUsuariGrup(usuariGrupEntity);
			return usuariGrup;
		} else {
			throw new SeyconAccessLocalException("grupService", "update (UsuariGrup)", "user:group:create", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$

		}
	}

	protected UsuariGrup handleFindUsuariGrupByCodiUsuariAndCodiGrup(String codiUsuari, String codiGrup) throws Exception {
		UserGroupEntity usuariGrupEntity = getUserGroupEntityDao().findByUserAndGroup(codiUsuari, codiGrup);
		if (usuariGrupEntity != null) {
			UsuariGrup usuariGrup = getUserGroupEntityDao().toUsuariGrup(usuariGrupEntity);
			return usuariGrup;
		}
		return null;
	}

	private Collection<Grup> findSubGrupsWithoutSecurityRestrictionsByCodiGrup(String codiGrup) {
		Collection<GroupEntity> groups = getGroupEntityDao().findByParent(codiGrup);
		if (groups != null) {
			return getGroupEntityDao().toGrupList(groups);
		}
		return new Vector();
	}

	/**
	 * Dado un listado de grupos, obtiene sus subgrupos
	 * 
	 * @param grupsGetSubgrups
	 * @return
	 */
	private Collection<Grup> getSubGrups(Collection grupsGetSubgrups) {
		Stack stack = new Stack();
		Collection<Grup> grupsISubgrups = new HashSet(); // perquè no es repetisquen
		grupsISubgrups.addAll(grupsGetSubgrups);
		stack.addAll(grupsGetSubgrups);
		while (!stack.empty()) {
			Grup grupActual = (Grup) stack.pop();
			grupsISubgrups.add(grupActual);
			stack.addAll(findSubGrupsWithoutSecurityRestrictionsByCodiGrup(grupActual.getCodi()));
		}
		return grupsISubgrups;
	}

	protected Grup handleGetSuperGrup(String codiGrup) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByChild(codiGrup);
		if (grupEntity != null) {
			return getGroupEntityDao().toGrup(grupEntity);
		}
		return null;
	}

	protected Collection<RolAccount> handleFindRolsUsuarisAmbGrupByCodiUsuari(String codiUsuari) throws Exception {
		Collection<RolAccount> result = new LinkedList<RolAccount>();
		
		for (RoleAccountEntity ra : this.getRoleAccountEntityDao().findByUserName(codiUsuari)) {
            if (ra.getGroup() != null) result.add(getRoleAccountEntityDao().toRolAccount(ra));
        }
		return result;
	}

	protected Collection<UsuariGrup> handleFindUsuariGrupsByCodiUsuari(String codiUsuari) throws Exception {
		Collection<UserGroupEntity> usuariGrups = getUserGroupEntityDao().findByUserName(codiUsuari);
		if (usuariGrups != null) {
			return getUserGroupEntityDao().toUsuariGrupList(usuariGrups);
		}
		return new Vector();
	}

	protected Collection<UsuariGrup> handleFindUsuarisPertanyenAlGrupByCodiGrup(String codiGrup) throws Exception {

		// Obtenim els usuaris que tenen aquest grup com a grup primari
		// o com a grup secundari i filtrem per autoritzacions
		Collection<UsuariGrup> totsUsuarisGrup = new LinkedList();

		// Mirem les autoritzacions a nivell de grup per group:user:query
		if (AutoritzacionsUsuari.canQueryGroupUsers(codiGrup)) {
			// Obtenemos los grupos primarios primero
			Collection usuari = getUserEntityDao().findByPrimaryGroup(codiGrup);
			for (Iterator it = usuari.iterator(); it.hasNext(); ) {
                UserEntity user = (UserEntity) it.next();
                String nomComplet = user.getFirstName() + " " + user.getLastName() + (user.getMiddleName() != null ? " " + user.getMiddleName() : "");
                UsuariGrup usugru = new UsuariGrup(user.getUserName(), user.getPrimaryGroup().getName(), nomComplet);
                usugru.setInfo(Messages.getString("GrupServiceImpl.PrimaryGroupText"));
                totsUsuarisGrup.add(usugru);
            }

			// Esto obtiene los usuarios que tienen el grupo como secundario
			Collection<UserGroupEntity> usuaris = getUserGroupEntityDao().findByGroupName(codiGrup);
			// Los añadimos al listado anterior
			for (Iterator<UserGroupEntity> it = usuaris.iterator(); it.hasNext(); ) {
                UsuariGrup ug = getUserGroupEntityDao().toUsuariGrup(it.next());
                ug.setInfo(Messages.getString("GrupServiceImpl.SecondaryGroupText"));
                totsUsuarisGrup.add(ug);
            }
		}

		// sinó tornem la llista buida
		return totsUsuarisGrup;
	}

	protected Grup handleFindGrupById(Long grupId) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().load(grupId);
		if (grupEntity != null) {
			return getGroupEntityDao().toGrup(grupEntity);
		}
		return null;
	}

	protected Collection<Rol> handleGetRolsFromGrup(Grup grup) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByName(grup.getCodi());
		Collection rolsGrupE = grupEntity.getAllowedRolesToGroup();
		Vector rolsGrup = new Vector(rolsGrupE);// Lo activamos
		// NOTA: Aquí obtenemos los roles, no los roles-grupo(!!) ¿Se utiliza?
		return getRoleEntityDao().toRolList(rolsGrup);
	}

	protected Collection<RolsGrup> handleGetRolsFromGrup(String codiGrup) throws Exception {
		GroupEntity grupEntity = getGroupEntityDao().findByName(codiGrup);
		Collection rolsGrupE = grupEntity.getAllowedRolesToGroup();
		LinkedList rolsGrup = new LinkedList(rolsGrupE);// Lo activamos

		return getRoleGroupEntityDao().toRolsGrupList(rolsGrup);
	}

	protected java.security.Principal getPrincipal() {
		return Security.getPrincipal();
	}

	protected Collection<RolAccount> handleFindRolsUsuarisTipusDominiGrupsAndGrupsUsuari(String codiGrup) throws Exception {
		Collection<RoleAccountEntity> rolsUsuGrup = getRoleAccountEntityDao().findByGroupName(codiGrup);
		return getRoleAccountEntityDao().toRolAccountList(rolsUsuGrup);
	}

	protected Collection<RolsGrup> handleGetRolsFromGrupYParesGrup(Grup grup) throws Exception {
		GroupEntity entity = getGroupEntityDao().findByName(grup.getCodi());
		Collection<RoleGroupEntity> rolsGrupE = entity.getAllowedRolesToGroup();
		LinkedList<RoleGroupEntity> totsRolsGrup = new LinkedList(rolsGrupE);

		// Buscamos los padres del grupo actual
		GroupEntity pare = entity.getParent();
		while (pare != null) {
			Collection<RoleGroupEntity> rolsGrupPare = pare.getAllowedRolesToGroup();
			totsRolsGrup.addAll(rolsGrupPare);
			pare = pare.getParent();
		}

		return getRoleGroupEntityDao().toRolsGrupList(totsRolsGrup);
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
