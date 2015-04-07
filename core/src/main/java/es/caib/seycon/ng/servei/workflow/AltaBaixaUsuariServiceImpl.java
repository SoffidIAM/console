package es.caib.seycon.ng.servei.workflow;

import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.UserEntity;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.AutoritzacioRol;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.servei.GrupService;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.utils.Security;
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
public class AltaBaixaUsuariServiceImpl extends es.caib.seycon.ng.servei.workflow.AltaBaixaUsuariServiceBase {

	/**
	 * @param peticio
	 *            : document XML de la petici� d'alta. Obligatori
	 * @param signatura
	 *            : signatura del document XML de petici� d'alta. Obligatori
	 * @return value object Usuari
	 * @throws java.lang.Exception
	 */
	public Usuari handleAltaUsuari(byte[] peticio, es.caib.signatura.api.Signature signatura) throws java.lang.Exception {
		UsuariService usuariService = getUsuariService();
		Usuari usuari = usuariService.altaUsuari(peticio, signatura);
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
	protected Usuari handleBaixaUsuari(java.lang.String codiUsuari) throws java.lang.Exception {
		UsuariService usuariService = getUsuariService();
		Usuari usuari = usuariService.baixaUsuari(codiUsuari);
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
	protected Usuari handleSetServidorsToUsuari(java.lang.String codiUsuari, String servidorCorreuId, String servidorPerfilId,
			String servidorHomeId) throws java.lang.Exception {
		UsuariService usuariService = getUsuariService();
		return usuariService.setServidorsToUsuari(codiUsuari, servidorCorreuId, servidorPerfilId, servidorHomeId);
	}

	/**
	 * @param codiUnitatOrganitzativa
	 *            : codi de grup /unitat organitzativa. Obligatori
	 * @return col·lecció de les unitats organitzatives que en depenen
	 * @throws Exception
	 */
	protected Collection<Grup> handleGetUnitatsOrganitzativesDepenents(String codiUnitatOrganitzativa) throws Exception {
		GrupService grupService = this.getGrupService();
		return grupService.findSubGrupsByCodiGrup(codiUnitatOrganitzativa);
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
	protected Collection<Usuari> handleFindUsuariByDadesUsuari(String dni, String nom, String primerLlinatge, String segonLlinatge)
			throws Exception {
		UsuariService usuariService = getUsuariService();
		Collection<Usuari> usuaris = usuariService.findUsuarisByDadesBasiques("%", nom, primerLlinatge, segonLlinatge, dni); //$NON-NLS-1$
		return usuaris;
	}

	/**
	 * @param codiUsuari
	 *            : codi d'usuari a qui s'ha d'assignar el password inicial.
	 *            Obligatori
	 * @return l'string corresponent al password inicial assignat a l'usuari
	 * @throws Exception
	 */
	protected String handleSetPasswordInicialToUsuari(String codiUsuari, String codiDominiContrasenyes) throws Exception {
		UsuariService usuariService = getUsuariService();
		//String dominiDefecte = getDominiCorreuEntityDao()
		String password = usuariService.assignaPasswordInicial(codiUsuari, codiDominiContrasenyes);
		return password;
	}

	protected Collection<String> handleGetTipusContractesAltaUsuari() throws Exception {
		LinkedList<String> list = new LinkedList();
		list.add(Messages.getString("AltaBaixaUsuariServiceImpl.PublicAdmin")); //$NON-NLS-1$
		list.add(Messages.getString("AltaBaixaUsuariServiceImpl.ServiceContract")); //$NON-NLS-1$
		list.add(Messages.getString("AltaBaixaUsuariServiceImpl.User")); //$NON-NLS-1$
		return list;
	}

	private boolean isAnyValueCode(String code) {
		return code == null || code.trim().length() == 0;
	}

	protected Collection<Usuari> handleFindUsuariByDadesUsuari(String codiUsuari, String dni, String nom, String primerLlinatge,
			String segonLlinatge) throws Exception {
		UsuariService usuariService = getUsuariService();
		if (isAnyValueCode(codiUsuari) && isAnyValueCode(dni) && isAnyValueCode(nom) && isAnyValueCode(primerLlinatge)
				&& isAnyValueCode(segonLlinatge)) {
			throw new SeyconException(Messages.getString("AltaBaixaUsuariServiceImpl.RestrictionAlert")); //$NON-NLS-1$
		}
		Collection<Usuari> usuaris = usuariService.findUsuarisByDadesBasiques(codiUsuari, nom, primerLlinatge, segonLlinatge, dni);
		return usuaris;
	}

	protected Collection<Grup> handleFindGrupsByCodiUsuari(String codiUsuari) throws Exception {
		GrupService grupService = getGrupService();
		return grupService.findGrupsByCodiUsuari(codiUsuari);
	}

	class ComparaGrups implements Comparator {
		// Nos permite ordenar los resultados de grupos por código

		public int compare(Object arg0, Object arg1) {
			if (arg0 instanceof Grup && arg1 instanceof Grup) {
				Grup g1 = (Grup) arg0;
				Grup g2 = (Grup) arg1;
				return g1.getCodi().compareTo(g2.getCodi());
			}
			return 0;
		}

	}

	protected Collection<Grup> handleGetManagedGroups() throws Exception {
		String user = Security.getCurrentUser();
		return handleGetManagedGroups(user);
	}

	protected Collection<Grup> handleGetManagedGroups(String user) throws Exception {
		UserEntity usuari = getUserEntityDao().findByCode(user);
		
		String auts[] = getAutoritzacioService().getUserAuthorizationString(Security.AUTO_USER_CREATE, user);
		LinkedList<Grup> groups = new LinkedList<Grup>();
		HashSet<String> groupNames = new HashSet<String>();
		for ( String aut: auts)
		{
			if (aut.equals(Security.AUTO_USER_CREATE+Security.AUTO_ALL) )
			{
				groups = new LinkedList<Grup>( getGrupService().findGrupsByFiltre("%", null, null, null, null, "N") ); //$NON-NLS-1$ //$NON-NLS-2$
				break;
			}
			else if (aut.length() > Security.AUTO_USER_CREATE.length()+1)
			{
				String groupName = aut.substring(Security.AUTO_USER_CREATE.length()+1);
				if (!groupNames.contains(groupName))
				{
					groupNames.add(groupName);
					groups.add(getGrupService().findGrupByCodiGrup(groupName));
				}
			}
		}
		
		Collections.sort(groups, new ComparaGrups());
		return groups;
	}

	protected Boolean handleExisteixNomCurt(String nomCurt) throws Exception {
		UsuariService usuariService = getUsuariService();
		return usuariService.existeixNomCurt(nomCurt);
	}

	protected Grup handleGetSuperGrup(String codiSubGrup) throws Exception {
		GrupService grupService = getGrupService();
		return grupService.getSuperGrup(codiSubGrup);
	}

	protected Usuari handleFindUsuariByNomCurt(String nomCurt) throws Exception {
		UsuariService usuariService = getUsuariService();
		return usuariService.findByNomCurt(nomCurt);
	}

	private void addGroupAndDescendants(Collection<Grup> grups, GroupEntity grup) {
		if (grup != null) {
			Grup grupVO = getGroupEntityDao().toGrup(grup);
			if (!grupsContainsGrup(grups, grupVO)) {
				grups.add(grupVO);
				Iterator it = grup.getChildrens().iterator();
				while (it.hasNext()) {
					addGroupAndDescendants(grups, (GroupEntity) it.next());
				}
			}
		}
	}

	private boolean grupsContainsGrup(Collection<Grup> grups, Grup grup) {
		boolean contains = false;
		Iterator grupsIterator = grups.iterator();
		while (grupsIterator.hasNext() && !contains) {
			Grup currentGrup = (Grup) grupsIterator.next();
			contains = currentGrup.getCodi().compareTo(grup.getCodi()) == 0;
		}
		return contains;
	}

	protected Rol handleGetRolAdministradorByGrup(String codiGrup) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected Collection<Usuari> handleGetUsuarisByNIFSenseRestriccions(String nif) throws Exception {
		Collection<UserEntity> usuaris = getUserEntityDao().findUsersByNationalID(nif);
		return getUserEntityDao().toUsuariList(usuaris);
	}

	protected Collection<Grup> handleFindGrupsByFiltreSenseRestriccions(String codi, String pare, String unitatOfimatica, String descripcio,
			String tipus, String obsolet) throws Exception {

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
		Principal principal = this.getPrincipal();
		if (principal == null) {
			return new Vector();
		}
		Collection<GroupEntity> grups = getGroupEntityDao().findByCriteria(codi, pare, unitatOfimatica, descripcio, tipus, obsolet);
		if (grups != null) {
			if (grups.size() >= 201) { // PJR: poso >= en comptes de ==
				throw new SeyconException(Messages.getString("AltaBaixaUsuariServiceImpl.BigSearchResults")); //$NON-NLS-1$
			}
			return getGroupEntityDao().toGrupList(grups);
		}
		return new LinkedList<Grup>();
	}
}