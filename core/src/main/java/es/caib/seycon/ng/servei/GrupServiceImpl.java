// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.model.ApplicationAttributeEntity;
import com.soffid.iam.model.GroupAttributeEntity;

import es.caib.seycon.ng.comu.Aplicacio;
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
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.GrupEntityDao;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.RolsGrupEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.TipusUsuariEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariGrupEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.Security;

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

	/*
	 * Retorna els subgrups sobre els que té permisos
	 * 
	 * @see es.caib.seycon.ng.servei.GrupServiceBase#handleFindSubGrupsByCodiGrup(java.lang.String)
	 */
	protected Collection<Grup> handleFindSubGrupsByCodiGrup(String codiGrup) throws InternalErrorException {
		// Si és administrador d'usuaris els pot llistar tots
		Collection<GrupEntity> groups = getGrupEntityDao().findSubGrupsByCodi(codiGrup);
		if (groups != null) {
			Collection<Grup> groupsList = filterGroups(groups);
			return groupsList;
		}

		return Collections.emptyList();
	}

	private List<Grup> filterGroups(Collection<GrupEntity> groups)
			throws InternalErrorException {
		List<Grup> groupsList = new LinkedList<Grup>();
		for (GrupEntity groupEntity: groups)
		{
			if (getAutoritzacioService().hasPermission(Security.AUTO_GROUP_QUERY, groupEntity))
				groupsList.add ( getGrupEntityDao().toGrup(groupEntity));
		}
		return groupsList;
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

	protected Collection<Grup> handleFindGrupsByTipusGrup(String tipusGrup) throws InternalErrorException {
		Collection<GrupEntity> grupEntities = getGrupEntityDao().findGrupsByTipus(tipusGrup);
		if (grupEntities != null) {
			return filterGroups(grupEntities);
		}
		return new Vector();
	}

	protected Maquina handleGetServidorOfimatic(Grup grup) throws Exception {
		if (grup == null)
			return null;
		String codi = grup.getCodi();
		if (codi == null)
			return null;
		GrupEntity grupEntity = getGrupEntityDao().findByCodi(codi);
		if (grupEntity == null) {
			return null;
		}
		MaquinaEntity maquinaEntity = grupEntity.getServidorOfimatic();
		if (maquinaEntity == null) {
			return null;
		}
		Maquina maquina = getMaquinaEntityDao().toMaquina(maquinaEntity);
		if (maquina != null) {
			;//System.out.println("Un dels grups te com a maquina: " + maquina.getNom());
		}
		return maquina;
	}

	/**
	 * @see es.caib.seycon.ng.servei.GrupService#getGrups()
	 */
	protected java.util.Collection<Grup> handleGetGrups() throws java.lang.Exception {
		return getGrupEntityDao().toGrupList(getGrupEntityDao().loadAll());
	}

	/**
	 * @see es.caib.seycon.ng.servei.GrupService#findGrupByCodi(java.lang.String)
	 */
	protected Grup handleFindGrupByCodiGrup(java.lang.String codi) throws java.lang.Exception {
		GrupEntity grupEntity = getGrupEntityDao().findByCodi(codi);
		if (grupEntity != null) {
			Grup grup = getGrupEntityDao().toGrup(grupEntity);
			return grup;
		}
		return null;
	}

	protected void handleSetSuperGrup(String codiSubGrup, String codiSuperGrup) throws java.lang.Exception {
		GrupEntity groupEntity  = getGrupEntityDao().findByCodi(codiSubGrup);
		
		if (groupEntity != null &&
				getAutoritzacioService().hasPermission(Security.AUTO_GROUP_UPDATE, groupEntity)) {
			getGrupEntityDao().setSuperGrup(codiSubGrup, codiSuperGrup);
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
		
		GrupEntity groupsSameCode = getGrupEntityDao().findByCodi(grup.getCodi());
		if(groupsSameCode != null)
			throw new SeyconException(String.format(Messages.getString("GrupServiceImpl.CodeGroupExists"),  //$NON-NLS-1$
							grup.getCodi())); 
		
		GrupEntity grupEntity = getGrupEntityDao().grupToEntity(grup);
		if (grupEntity != null) {
			getGrupEntityDao().create(grupEntity);
			updateGroupAttributes(grup, grupEntity);
			return getGrupEntityDao().toGrup(grupEntity);
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
		Collection<GrupEntity> grups = getGrupEntityDao().findByFiltre(codi, pare,
			unitatOfimatica, descripcio, tipus, obsolet, servidorOfimatic,
			seccioPressupostaria);
		if (grups != null)
		{
			// FILTREM per autoritzacio group:query [sense_domini O GRUPS]
			List<Grup> grupsPermis = filterGroups(grups);
			
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
		GrupEntity entity = getGrupEntityDao().grupToEntity(grup);
		getGrupEntityDao().update(entity);
		updateGroupAttributes(grup, entity);
		return getGrupEntityDao().toGrup(entity);
	}

	protected void handleAddGrupToUsuari(String codiUsuari, String codiGrup) throws Exception {
		UsuariGrup usuariGrup = new UsuariGrup();
		usuariGrup.setCodiGrup(codiGrup);
		usuariGrup.setCodiUsuari(codiUsuari);
		handleCreate(usuariGrup);
	}

	protected void handleRemoveGrupFromUsuari(String codiUsuari, String codiGrup) throws Exception {
		UsuariGrupEntity usuariGrup = getUsuariGrupEntityDao().findByCodiUsuariAndCodiGrup(codiUsuari, codiGrup);
		long userId = usuariGrup.getUsuari().getId();
		long groupId = usuariGrup.getGrup().getId();
		getUsuariGrupEntityDao().remove(usuariGrup);
		/*IAM-318*/
		handlePropagateRolsChangesToDispatcher(codiGrup);
		getAplicacioService().revokeRolesHoldedOnGroup(userId, groupId);

	}

	protected Grup handleFindGrupPrimariByCodiUsuari(String codiUsuari) throws Exception {
		GrupEntity grupEntity = this.getGrupEntityDao().findGrupPrimariByCodiUsuari(codiUsuari);
		if (grupEntity != null) {
			Grup grup = this.getGrupEntityDao().toGrup(grupEntity);
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
		Collection<GrupEntity> grups = getGrupEntityDao().findGrupsFromRolsByCodiUsuari(codiUsuari);
		if (grups != null) {
			return getGrupEntityDao().toGrupList(grups);
		}
		return new Vector();
	}

	protected Collection<Grup> handleFindGrupsFromUsuarisByCodiUsuari(String codiUsuari) throws Exception {
		Collection<GrupEntity> grups = getGrupEntityDao().findGrupsFromUsuarisByCodiUsuari(codiUsuari);
		if (grups != null) {
			return getGrupEntityDao().toGrupList(grups);
		}
		return new Vector();
	}

	protected UsuariGrup handleCreate(UsuariGrup usuariGrup) throws Exception {
		UsuariGrupEntity usuariGrupEntity = getUsuariGrupEntityDao().usuariGrupToEntity(usuariGrup);
		if (usuariGrupEntity.getUsuari().getCodi().compareTo(getPrincipal().getName()) == 0) {
			throw new SeyconException(Messages.getString("GrupServiceImpl.7")); //$NON-NLS-1$
		}
		UsuariEntity usuari = usuariGrupEntity.getUsuari();

		if (getAutoritzacioService().hasPermission(Security.AUTO_USER_GROUP_CREATE, usuariGrupEntity)) {

			usuari.setDataDarreraModificacio(GregorianCalendar.getInstance().getTime());
			usuari.setUsuariDarreraModificacio(getPrincipal().getName());
			getUsuariEntityDao().update(usuari);

			getUsuariGrupEntityDao().create(usuariGrupEntity);
			usuariGrup.setId(usuariGrupEntity.getId());
			usuariGrup = getUsuariGrupEntityDao().toUsuariGrup(usuariGrupEntity);
			/*IAM-318*/
			handlePropagateRolsChangesToDispatcher(usuariGrup.getCodiGrup());
			getRuleEvaluatorService().applyRules(usuari);

			return usuariGrup;
		} else {
			throw new SeyconAccessLocalException("grupService", "create (UsuariGrup)", "user:group:create", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$
		}
	}

	private boolean esPotEliminarUsuariGrup(UsuariGrupEntity usuariGrup) {
		// Obtenim el grup primari de l'usuari
		GrupEntity gp = usuariGrup.getUsuari().getGrupPrimari();
		String codiGrupPrimari = gp != null && gp.getCodi() != null ? gp.getCodi() : ""; //$NON-NLS-1$
		for (RolAccountEntity rolUsuari: getRolAccountEntityDao().findByCodiUsuari(usuariGrup.getUsuari().getCodi()))
		{
			if (rolUsuari.getTipusDomini().compareTo(TipusDomini.GRUPS_USUARI) == 0) {
				String codiGrupValorDomini = rolUsuari.getGrup().getCodi();
				String codiGrupGrupUsuari = usuariGrup.getGrup().getCodi();
				if (codiGrupValorDomini.compareTo(codiGrupGrupUsuari) == 0) {
					// Mirem si el grup primari és igual al del domini del
					// rol
					// si es aixina, ho donem per bó l'esborrat del
					// grupusuari secundari
					if (!codiGrupPrimari.equals(codiGrupValorDomini))
						return false;
				}
			}
		}
		return true;
	}

	protected void handleDelete(UsuariGrup usuariGrup) throws Exception {

		UsuariGrupEntity usuariGrupEntity = getUsuariGrupEntityDao().usuariGrupToEntity(usuariGrup);

		if (!esPotEliminarUsuariGrup(usuariGrupEntity)) {
			throw new SeyconException(String.format(Messages.getString("GrupServiceImpl.8"),  //$NON-NLS-1$
					usuariGrup.getCodiGrup(),
					usuariGrup.getCodiUsuari()));
		}

		// Mirem les autoritzacions
		if (getAutoritzacioService().hasPermission(Security.AUTO_USER_GROUP_CREATE, usuariGrupEntity)) {
			
			UsuariEntity usuari = usuariGrupEntity.getUsuari();
			usuari.setDataDarreraModificacio(GregorianCalendar.getInstance().getTime());
			usuari.setUsuariDarreraModificacio(getPrincipal().getName());
			getUsuariEntityDao().update(usuari);
			long groupId = usuariGrupEntity.getGrup().getId();

			getUsuariGrupEntityDao().remove(usuariGrupEntity);
			
			usuari.getGrupsSecundaris().remove(usuariGrupEntity);

			getAplicacioService().revokeRolesHoldedOnGroup(usuari.getId(), groupId);

			getRuleEvaluatorService().applyRules(usuari);
		} else {
			throw new SeyconAccessLocalException("grupService", "delete (UsuariGrup)", "user:group:delete", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to delete groups for this user"); //$NON-NLS-1$
		}
	}

	protected UsuariGrup handleUpdate(UsuariGrup usuariGrup) throws Exception {

		UsuariGrupEntity usuariGrupEntity = getUsuariGrupEntityDao().load(usuariGrup.getId());
		if (!getAutoritzacioService().hasPermission(Security.AUTO_USER_GROUP_DELETE, usuariGrupEntity)) {
			throw new SeyconAccessLocalException("grupService", "update (UsuariGrup)", "user:group:delete", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$
		}
		usuariGrupEntity = getUsuariGrupEntityDao().usuariGrupToEntity(usuariGrup);

		// En principi no ha d'existir update--- seria un create
		if (getAutoritzacioService().hasPermission(Security.AUTO_USER_GROUP_CREATE, usuariGrupEntity)) {

			UsuariEntity usuari = usuariGrupEntity.getUsuari();
			usuari.setDataDarreraModificacio(GregorianCalendar.getInstance().getTime());
			usuari.setUsuariDarreraModificacio(getPrincipal().getName());
			getUsuariEntityDao().update(usuari);

			getUsuariGrupEntityDao().update(usuariGrupEntity);
			usuariGrup = getUsuariGrupEntityDao().toUsuariGrup(usuariGrupEntity);
			return usuariGrup;
		} else {
			throw new SeyconAccessLocalException("grupService", "update (UsuariGrup)", "user:group:create", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"User's groups-based authorization: probably not authorized to create groups for this user"); //$NON-NLS-1$

		}
	}

	protected UsuariGrup handleFindUsuariGrupByCodiUsuariAndCodiGrup(String codiUsuari, String codiGrup) throws Exception {
		UsuariGrupEntity usuariGrupEntity = getUsuariGrupEntityDao().findByCodiUsuariAndCodiGrup(codiUsuari, codiGrup);
		if (usuariGrupEntity != null) {
			UsuariGrup usuariGrup = getUsuariGrupEntityDao().toUsuariGrup(usuariGrupEntity);
			return usuariGrup;
		}
		return null;
	}

	private Collection<Grup> findSubGrupsWithoutSecurityRestrictionsByCodiGrup(String codiGrup) {
		Collection<GrupEntity> groups = getGrupEntityDao().findSubGrupsByCodi(codiGrup);
		if (groups != null) {
			return getGrupEntityDao().toGrupList(groups);
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
		GrupEntity grupEntity = getGrupEntityDao().getSuperGrup(codiGrup);
		if (grupEntity != null) {
			return getGrupEntityDao().toGrup(grupEntity);
		}
		return null;
	}

	protected Collection<RolAccount> handleFindRolsUsuarisAmbGrupByCodiUsuari(String codiUsuari) throws Exception {
		Collection<RolAccount> result = new LinkedList<RolAccount>();
		
		for (RolAccountEntity ra: this.getRolAccountEntityDao().findByCodiUsuari(codiUsuari))
		{
			if (ra.getGrup() != null)
				result.add(getRolAccountEntityDao().toRolAccount(ra));
		}
		return result;
	}

	protected Collection<UsuariGrup> handleFindUsuariGrupsByCodiUsuari(String codiUsuari) throws Exception {
		Collection<UsuariGrupEntity>  usuariGrups = getUsuariGrupEntityDao().findByCodiUsuari(codiUsuari);
		if (usuariGrups != null) {
			return getUsuariGrupEntityDao().toUsuariGrupList(usuariGrups);
		}
		return new Vector();
	}

	protected Collection<UsuariGrup> handleFindUsuarisPertanyenAlGrupByCodiGrup(String codiGrup) throws Exception {

		// Obtenim els usuaris que tenen aquest grup com a grup primari
		// o com a grup secundari i filtrem per autoritzacions
		Collection<UsuariGrup> totsUsuarisGrup = new LinkedList();

		// Mirem les autoritzacions a nivell de grup per group:user:query
		// Obtenemos los grupos primarios primero
		Collection usuari = getUsuariEntityDao().findByGrupPrimari(codiGrup);
		for (Iterator it = usuari.iterator(); it.hasNext();) {
			UsuariEntity user = (UsuariEntity) it.next();
			if (getAutoritzacioService().hasPermission(Security.AUTO_USER_QUERY, user)) {
				// El segon llinatge pot ésser null
				String nomComplet = user.getNom() + " " + user.getPrimerLlinatge() //$NON-NLS-1$
						+ (user.getSegonLlinatge() != null ? " " + user.getSegonLlinatge() : ""); //$NON-NLS-1$ //$NON-NLS-2$
				UsuariGrup usugru = new UsuariGrup(user.getCodi(), user.getGrupPrimari().getCodi(), nomComplet);
				usugru.setInfo(Messages.getString("GrupServiceImpl.PrimaryGroupText")); //$NON-NLS-1$
				totsUsuarisGrup.add(usugru);
			}
		}

		// Esto obtiene los usuarios que tienen el grupo como secundario
		Collection<UsuariGrupEntity> usuaris = getUsuariGrupEntityDao().findByCodiGrup(codiGrup);
		// Los añadimos al listado anterior
		for (UsuariGrupEntity uge: usuaris) {
			if (getAutoritzacioService().hasPermission(Security.AUTO_USER_QUERY, uge)) {
				UsuariGrup ug = getUsuariGrupEntityDao().toUsuariGrup(uge);
				ug.setInfo(Messages.getString("GrupServiceImpl.SecondaryGroupText")); //$NON-NLS-1$
				totsUsuarisGrup.add(ug);
			}
		}

		// sinó tornem la llista buida
		return totsUsuarisGrup;
	}

	protected Grup handleFindGrupById(Long grupId) throws Exception {
		GrupEntity grupEntity = getGrupEntityDao().findById(grupId);
		if (grupEntity != null) {
			return getGrupEntityDao().toGrup(grupEntity);
		}
		return null;
	}

	protected Collection<Rol> handleGetRolsFromGrup(Grup grup) throws Exception {
		GrupEntity grupEntity = getGrupEntityDao().findByCodi(grup.getCodi());
		Collection rolsGrupE = grupEntity.getRolsOtorgatsGrup();
		Vector rolsGrup = new Vector(rolsGrupE);// Lo activamos
		// NOTA: Aquí obtenemos los roles, no los roles-grupo(!!) ¿Se utiliza?
		return getRolEntityDao().toRolList(rolsGrup);
	}

	protected Collection<RolsGrup> handleGetRolsFromGrup(String codiGrup) throws Exception {
		GrupEntity grupEntity = getGrupEntityDao().findByCodi(codiGrup);
		Collection rolsGrupE = grupEntity.getRolsOtorgatsGrup();
		LinkedList rolsGrup = new LinkedList(rolsGrupE);// Lo activamos

		return getRolsGrupEntityDao().toRolsGrupList(rolsGrup);
	}

	protected java.security.Principal getPrincipal() {
		return Security.getPrincipal();
	}

	protected Collection<RolAccount> handleFindRolsUsuarisTipusDominiGrupsAndGrupsUsuari(String codiGrup) throws Exception {
		Collection<RolAccountEntity> rolsUsuGrup = getRolAccountEntityDao().findByCodiGrup(codiGrup);
		return getRolAccountEntityDao().toRolAccountList(rolsUsuGrup);
	}

	protected Collection<RolsGrup> handleGetRolsFromGrupYParesGrup(Grup grup) throws Exception {
		GrupEntity entity = getGrupEntityDao().findByCodi(grup.getCodi());
		Collection<RolsGrupEntity> rolsGrupE = entity.getRolsOtorgatsGrup();
		LinkedList<RolsGrupEntity> totsRolsGrup = new LinkedList(rolsGrupE);

		// Buscamos los padres del grupo actual
		GrupEntity pare = entity.getPare();
		while (pare != null) {
			Collection <RolsGrupEntity> rolsGrupPare = pare.getRolsOtorgatsGrup();
			totsRolsGrup.addAll(rolsGrupPare);
			pare = pare.getPare();
		}

		return getRolsGrupEntityDao().toRolsGrupList(totsRolsGrup);
	}

	/*IAM-318*/
	protected void handlePropagateRolsChangesToDispatcher(String grup) throws InternalErrorException{
		GrupEntity grupEntity = getGrupEntityDao().findByCodi(grup);
		if (grupEntity != null)
		{
							
			for (RolsGrupEntity rolGrup: grupEntity.getRolsOtorgatsGrup())
			{
				RolEntity rol = rolGrup.getRolOtorgat();
	            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
	            tasque.setData(new Timestamp(System.currentTimeMillis()));
	            tasque.setTransa(TaskHandler.UPDATE_ROLE);
	            tasque.setRole(rol.getNom());
	            tasque.setBd(rol.getBaseDeDades().getCodi());
	            getTasqueEntityDao().create(tasque);
			}
		}
	}
	
	private void updateGroupAttributes (Grup group, GrupEntity entity) throws InternalErrorException
	{
		if (group.getAttributes() == null)
			group.setAttributes(new HashMap<String, Object>());
		
		HashSet<String> keys = new HashSet<String>(group.getAttributes().keySet());
		for ( GroupAttributeEntity att: entity.getAttributes())
		{
			Object v = group.getAttributes().get(att.getMetadata().getCodi());
			att.setObjectValue(v);
			keys.remove(att.getMetadata().getCodi());
		}
		List<TipusDadaEntity> md = getTipusDadaEntityDao().findByScope(MetadataScope.GROUP);
		for (String key: keys)
		{
			Object v = group.getAttributes().get(key);
			if ( v != null)
			{
				boolean found = false;
				GroupAttributeEntity aae = getGroupAttributeEntityDao().newGroupAttributeEntity ();
				for ( TipusDadaEntity d: md)
				{
					if (d.getCodi().equals(key))
					{
						aae.setMetadata(d);
						found = true;
						break;
					}
				}
				if (!found)
					throw new InternalErrorException(String.format("Unknown attribute %s", key));
				aae.setObjectValue(v);
				aae.setGroup(entity);
				getGroupAttributeEntityDao().create(aae);
			}
		}
		
		for ( TipusDadaEntity m: md)
		{
			Object o = group.getAttributes().get(m.getCodi());
			if ( o == null || "".equals(o))
			{
				if (m.getRequired() != null && m.getRequired().booleanValue())
					throw new InternalErrorException(String.format("Missing attribute %s", m.getLabel()));
			} else {
				if (m.getUnique() != null && m.getUnique().booleanValue())
				{
					if (getGroupAttributeEntityDao().findByNameAndValue(m.getCodi(), o.toString()).size() > 1)
						throw new InternalErrorException(String.format("Already exists a role with %s %s",
								m.getLabel(), o.toString()));
				}
			}
		}
	}

	
}