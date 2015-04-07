// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei.workflow;

import com.soffid.iam.model.RoleAccountEntity;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.servei.workflow.AltaBaixaUsuariServiceImpl.ComparaGrups;
import es.caib.seycon.ng.utils.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author u89559
 * 
 */
public class InformacioAutoritzacioServiceImpl extends
		es.caib.seycon.ng.servei.workflow.InformacioAutoritzacioServiceBase {

	/**
	 * @return retorna totes les aplicacions
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Aplicacio> handleGetAplicacions()
			throws java.lang.Exception {
		AplicacioService aplicacioService = getAplicacioService();
		Collection<Aplicacio> aplicacions = aplicacioService.getAplicacions();
		return aplicacions;
	}

	/**
	 * @param codiAplicacio:
	 *            codi de l'aplicació. Obligatori
	 * @return collection dels rols de l'apliació
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Rol> handleFindRolsByCodiAplicacio(
			java.lang.String codiAplicacio) throws java.lang.Exception {
		AplicacioService aplicacioService = getAplicacioService();
		Collection rols = aplicacioService
				.findRolsByCodiAplicacio(codiAplicacio);
		return rols;
	}
	
	/**
	 * @param codiAplicacio:
	 *            codi de l'aplicació. Obligatori
	 * @return collection dels rols de l'apliació
	 * @throws java.lang.Exception
	 */
	protected Collection<Rol> handleFindRolsByCodiAplicacioSenseRestriccions(
			String codiAplicacio) throws Exception {
		AplicacioService aplicacioService = getAplicacioService();
		Collection<Rol> rols = aplicacioService
				.findRolsByCodiAplicacioSenseRestriccions(codiAplicacio);
		return rols;
	}	

	/**
	 * @param codiUsuari:
	 *            codi de l'usuari. Obligatori
	 * @return: collection dels rols de l'usuari
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Rol> handleFindRolsByCodiUsuari(
			java.lang.String codiUsuari) throws java.lang.Exception {
		AplicacioService aplicacioService = getAplicacioService();
		return aplicacioService.findRolsByCodiUsuari(codiUsuari);
	}

	/**
	 * @param dni:
	 *            DNI de l'usuari en format LIKE d'SQL (483%, 49217421,....).
	 *            Null per ignorar-lo. No obligatori
	 * @param nom:
	 *            Nom de l'usuari en format LIKE SQL. Null per ignorar-lo. No
	 *            obligatori
	 * @param primerLlinatge:
	 *            primer llinatge de l'usuari en format LIKE d'SQL. Null per
	 *            ignorar-lo. No obligatori
	 * @param segonLlinatge:
	 *            segon llinatge de l'usuari en format LIKE d'SQL. Null per
	 *            ignorar-lo. No obligatori
	 * @return collection dels usuaris que fan matching amb tots els paràmetres
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Usuari> handleFindUsuariByDadesUsuari(
			java.lang.String dni, String nom, String primerLlinatge,
			String segonLlinatge) throws java.lang.Exception {
		UsuariService usuariService = getUsuariService();
		Collection<Usuari> usuaris = usuariService.findUsuarisByDadesBasiques("%", nom, //$NON-NLS-1$
				primerLlinatge, segonLlinatge, dni);
		return usuaris;
	}

	/**
	 * @param codiAplicacio:
	 *            codi de l'aplicació. Obligatori
	 * @return collection d'usuaris administradors de l'apliació
	 * @throws Exception
	 */
	protected Collection<Usuari> handleFindAdministradorsAplicacioByCodiAplicacio(
			String codiAplicacio) throws Exception {
		AplicacioService aplicacioService = getAplicacioService();
		Collection administradors = aplicacioService
				.findUsuarisAmbPermisosActualitzacioByCodiAplicacio(codiAplicacio);
		return administradors;
	}

	/**
	 * @param codiUsuari:
	 *            codi d'usuari. Obligatori. Obligatori
	 * @return collection de les aplicacions de les que l'usuari té rols
	 * @throws Exception
	 */
	protected Collection<Aplicacio> handleGetAplicacionsByCodiUsuari(String codiUsuari)
			throws Exception {
		UsuariService usuariService = getUsuariService();
		Collection aplicacions = usuariService
				.getAplicacionsGestionablesWFByCodiUsuari(codiUsuari);
		return aplicacions;
	}

	/**
	 * @param codiUsuari
	 *            codi d'usuari. Obligatori
	 * @return collection de rols que te l'usuari
	 * @throws Exception
	 */
	protected Collection<Rol> handleGetRolsByCodiUsuari(String codiUsuari)
			throws Exception {
		AplicacioService aplicacioService = getAplicacioService();
		Collection<Rol> aplicacions = aplicacioService.findRolsByCodiUsuari(codiUsuari);
		return aplicacions;
	}

	/**
	 * @param codiUsuari:
	 *            codi l'usuari. Obligatori
	 * @param codiAplicacio:
	 *            codi aplicació. Obligatori
	 * @return rols de l'aplicació que te l'usuari
	 * @throws Exception
	 */
	protected Collection<Rol> handleGetRolsAplicacioByCodiUsuariAndCodiAplicacio(
			String codiUsuari, String codiAplicacio) throws Exception {
		UsuariService usuariService = getUsuariService();
		Collection<Rol>  aplicacions = usuariService
				.getRolsAplicacioByCodiUsuariAndCodiAplicacio(codiUsuari,
						codiAplicacio);
		return aplicacions;
	}

	/**
	 * @param codiUsuari:
	 *            codi d'usuari. Obligatori
	 * @param codiAplicacio:
	 *            codi aplicacio. Obligatori
	 * @return retorna true si l'usuari és administrador de l'aplicació, false
	 *         altrament
	 * @throws Exception
	 */
	protected Boolean handleIsAdministradorAplicacio(String codiUsuari,
			String codiAplicacio) throws Exception {
		AplicacioService aplicacioService = getAplicacioService();
		Collection administradors = aplicacioService
				.findUsuarisAmbPermisosActualitzacioByCodiAplicacio(codiAplicacio);
		if (administradors != null) {
			Iterator administradorIterator = administradors.iterator();
			boolean isAdministrador = false;
			while (administradorIterator.hasNext() && (!isAdministrador)) {
				Usuari administrador = (Usuari) administradorIterator.next();
				isAdministrador = administrador.getCodi().compareTo(codiUsuari) == 0;
			}
			return new Boolean(isAdministrador);
		}
		return new Boolean(false);
	}

	protected Collection<Aplicacio> handleFindAplicacioByCriteri(String codi, String nom,
			String directoriFonts, String responsable,
			String directoriExecutable, String bd) throws Exception {
		AplicacioService aplicacioService = getAplicacioService();
		return aplicacioService.findAplicacioByCriteriSenseRestriccions(codi, nom,
				directoriFonts, responsable, directoriExecutable, bd, null, "S"); //$NON-NLS-1$
	}
	
	protected Aplicacio handleFindAplicacioByCodiAplicacio(String codiAplicacio)
			throws Exception {
		AplicacioService aplicacioService = getAplicacioService();
		return aplicacioService.findAplicacioByCodiAplicacioSenseRestriccions(codiAplicacio);
	}	

	protected Rol handleGetRolSistemes(String codiAplicacio) throws Exception {
		AplicacioService aplicacioService = getAplicacioService();
		Rol rol = aplicacioService.findRolByNomRolAndCodiAplicacioAndCodiDispatcher(
				"SC_DGTICSISTEMES", "SEYCON", "JBOSS"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return rol;
	}

	protected boolean handleNecessitaIntervencioSistemes(String codiAplicacio,
			String[] codisRols) throws Exception {
		return true;
	}

	protected Collection<Aplicacio> handleFindAplicacionsAdministradesByCodiUsuari(
			String codiUsuari) throws Exception {
		AplicacioService aplicacioService = getAplicacioService();
		Collection<Aplicacio> aplicacions = aplicacioService
				.findAplicacionsGestionablesWFAdministradesByCodiUsuari(codiUsuari);

		if (aplicacions != null) {
			return aplicacions;
		}
		return new LinkedList<Aplicacio>();
	}

	protected Collection<RolAccount> handleFindRolsUsuarisByCodiUsuariAndNomRolSenseRestriccions(
			String codiUsuari, String nomRol) throws Exception {
		Collection<RoleAccountEntity> rolsUsu = getRoleAccountEntityDao().findByCodiUsuariAndNomRol(codiUsuari, nomRol);
		if (rolsUsu != null) {
			return getRoleAccountEntityDao().toRolAccountList(rolsUsu);
		}
		return new LinkedList<RolAccount>();
	}

}