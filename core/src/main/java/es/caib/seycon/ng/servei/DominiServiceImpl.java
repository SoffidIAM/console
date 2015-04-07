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

import com.soffid.iam.model.ApplicationDomainEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @see es.caib.seycon.ng.servei.DominiService
 */
public class DominiServiceImpl extends
		es.caib.seycon.ng.servei.DominiServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#create(es.caib.seycon.ng.comu.Domini)
	 */
	protected es.caib.seycon.ng.comu.Domini handleCreate(
			es.caib.seycon.ng.comu.Domini domini) throws java.lang.Exception {
		if ((domini.getNom().compareToIgnoreCase(TipusDomini.GRUPS) == 0)
				|| (domini.getNom().compareToIgnoreCase(
						TipusDomini.GRUPS_USUARI) == 0)
				|| (domini.getNom()
						.compareToIgnoreCase(TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DominiServiceImpl.0")); //$NON-NLS-1$
		}
		ApplicationDomainEntity dominiEntity = getApplicationDomainEntityDao().dominiToEntity(domini);
		getApplicationDomainEntityDao().create(dominiEntity);
		domini.setId(dominiEntity.getId());
		domini = getApplicationDomainEntityDao().toDomini(dominiEntity);
		return domini;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#delete(es.caib.seycon.ng.comu.Domini)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.Domini domini)
			throws java.lang.Exception {
		if ((domini.getNom().compareToIgnoreCase(TipusDomini.GRUPS) == 0)
				|| (domini.getNom().compareToIgnoreCase(
						TipusDomini.GRUPS_USUARI) == 0)
				|| (domini.getNom()
						.compareToIgnoreCase(TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DominiServiceImpl.1")); //$NON-NLS-1$
		}
		
		// codiExtern en dominis de tipus d'aplicació és el codi de l'aplicacio
		if (AutoritzacionsUsuari.canDeleteAplicacio(domini.getCodiExtern())) {
			ApplicationDomainEntity dominiEntity = getApplicationDomainEntityDao().dominiToEntity(domini);
			getApplicationDomainEntityDao().remove(dominiEntity);
		} else {
			throw new SeyconAccessLocalException("DominiService", //$NON-NLS-1$
					"delete (Domini)", "application:delete", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("DominiServiceImpl.2")); //$NON-NLS-1$
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#create(es.caib.seycon.ng.comu.ValorDomini)
	 */
	protected es.caib.seycon.ng.comu.ValorDomini handleCreate(
			es.caib.seycon.ng.comu.ValorDomini valorDomini)
			throws java.lang.Exception {
		if ((valorDomini.getNomDomini().compareToIgnoreCase(TipusDomini.GRUPS) == 0)
				|| (valorDomini.getNomDomini().compareToIgnoreCase(
						TipusDomini.GRUPS_USUARI) == 0)
				|| (valorDomini.getNomDomini().compareToIgnoreCase(
						TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DominiServiceImpl.3")); //$NON-NLS-1$
		}
		DomainValueEntity valorDominiAplicacioEntity = getDomainValueEntityDao().valorDominiToEntity(valorDomini);
		getDomainValueEntityDao().create(valorDominiAplicacioEntity);
		valorDomini.setId(valorDominiAplicacioEntity.getId());
		valorDomini = getDomainValueEntityDao().toValorDomini(valorDominiAplicacioEntity);
		return valorDomini;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#delete(es.caib.seycon.ng.comu.ValorDomini)
	 */
	protected void handleDelete(es.caib.seycon.ng.comu.ValorDomini valorDomini)
			throws java.lang.Exception {
		if ((valorDomini.getNomDomini().compareToIgnoreCase(TipusDomini.GRUPS) == 0)
				|| (valorDomini.getNomDomini().compareToIgnoreCase(
						TipusDomini.GRUPS_USUARI) == 0)
				|| (valorDomini.getNomDomini().compareToIgnoreCase(
						TipusDomini.APLICACIONS) == 0)) {
			throw new SeyconException(
					Messages.getString("DominiServiceImpl.4")); //$NON-NLS-1$
		}
		
		// el codi extern conté el codi de l'aplicació (sempre tindrà valor)
		if (AutoritzacionsUsuari.canCreateAplicacio(valorDomini.getCodiExternDomini()) ||
				AutoritzacionsUsuari.canUpdateAplicacio(valorDomini.getCodiExternDomini()) ||
				AutoritzacionsUsuari.canDeleteAplicacio(valorDomini.getCodiExternDomini())) {
			DomainValueEntity valorDominiAplicacioEntity = getDomainValueEntityDao().valorDominiToEntity(valorDomini);
			getDomainValueEntityDao().remove(valorDominiAplicacioEntity);
		} else {
			throw new SeyconAccessLocalException("DominiService", //$NON-NLS-1$
					"delete (ValorDomini)", "application:delete", //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("DominiServiceImpl.5")); //$NON-NLS-1$
		}
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#findDominiGrupsUsuariByCodiUsuari(java.lang.String)
	 */
	protected es.caib.seycon.ng.comu.Domini handleFindDominiGrupsUsuari()
			throws java.lang.Exception {
		Domini domini = new Domini();
		domini.setNom(TipusDomini.GRUPS_USUARI);
		return domini;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#findDominiGrups()
	 */
	protected es.caib.seycon.ng.comu.Domini handleFindDominiGrups()
			throws java.lang.Exception {
		Domini domini = new Domini();
		domini.setNom(TipusDomini.GRUPS);
		domini.setCodiExtern(null);
		return domini;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#findDominiAplicacioByCodiAplicacio(java.lang.String)
	 */
	protected Domini handleFindDominiAplicacioByNomRol(java.lang.String nomRol)
			throws java.lang.Exception {
		String query = "select rol.dominiAplicacio " + "from " //$NON-NLS-1$ //$NON-NLS-2$
				+ "es.caib.seycon.ng.model.RolEntity rol " + "where " //$NON-NLS-1$ //$NON-NLS-2$
				+ "rol.nom = :nomRol " + "order by rol.dominiAplicacio.nom"; //$NON-NLS-1$ //$NON-NLS-2$

		Parameter nomRolParameter = new Parameter("nomRol", nomRol); //$NON-NLS-1$
		Parameter[] parametres = { nomRolParameter };

		List<ApplicationDomainEntity> dominisAplicacio = getApplicationDomainEntityDao().query(query, parametres);
		if (dominisAplicacio != null) {
			Iterator<ApplicationDomainEntity> dominiAplicacioIterator = dominisAplicacio.iterator();
			if (dominiAplicacioIterator != null) {
				if (dominiAplicacioIterator.hasNext()) {
					ApplicationDomainEntity dominiAplicacio = (ApplicationDomainEntity) dominiAplicacioIterator.next();
					if (dominiAplicacio != null) {
						return getApplicationDomainEntityDao().toDomini(dominiAplicacio);
					}
				}
			}
		}
		return null;
	}

	protected Domini handleFindDominiAplicacioByNomDominiAndCodiAplicacio(
			String nomDomini, String codiAplicacio) throws Exception {
		String query = "select domini " //$NON-NLS-1$
				+ "from " //$NON-NLS-1$
				+ "es.caib.seycon.ng.model.DominiAplicacioEntity domini " //$NON-NLS-1$
				+ "left join domini.aplicacio aplicacio " //$NON-NLS-1$
				+ "where " //$NON-NLS-1$
				+ "domini.nom = :nomDomini and " //$NON-NLS-1$
				+ "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio)) " //$NON-NLS-1$
				+ "order by domini.nom"; //$NON-NLS-1$

		Parameter nomDominiParameter = new Parameter("nomDomini", nomDomini); //$NON-NLS-1$
		Parameter nomRolParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
				codiAplicacio);
		Parameter[] parametres = { nomDominiParameter, nomRolParameter };

		List<ApplicationDomainEntity> dominisAplicacio = getApplicationDomainEntityDao().query(query, parametres);
		if (dominisAplicacio != null) {
			Iterator<ApplicationDomainEntity> dominiAplicacioIterator = dominisAplicacio.iterator();
			if (dominiAplicacioIterator != null) {
				if (dominiAplicacioIterator.hasNext()) {
					ApplicationDomainEntity dominiAplicacio = (ApplicationDomainEntity) dominiAplicacioIterator.next();
					if (dominiAplicacio != null) {
						return getApplicationDomainEntityDao().toDomini(dominiAplicacio);
					}
				}
			}
		}
		return null;
	}

	/**
	 * @see es.caib.seycon.ng.servei.DominiService#findValorsDominiByDomini(es.caib.seycon.ng.comu.Domini)
	 */
	protected java.util.Collection<ValorDomini> handleFindValorsDominiByFiltre(
			es.caib.seycon.ng.comu.Domini domini, String codi,
			String descripcio, String codiUsuari) throws java.lang.Exception {
		if (domini.getNom().compareToIgnoreCase(TipusDomini.GRUPS_USUARI) == 0) {
			String query = "select grup " //$NON-NLS-1$
					+ "from " //$NON-NLS-1$
					+ "es.caib.seycon.ng.model.UsuariGrupEntity usuariGrup, " //$NON-NLS-1$
					+ "es.caib.seycon.ng.model.GrupEntity grup, " //$NON-NLS-1$
					+ "es.caib.seycon.ng.model.UsuariEntity usuari " //$NON-NLS-1$
					+ "where " //$NON-NLS-1$
					+ "usuari.codi = :codiUsuari and " //$NON-NLS-1$
					+ "usuariGrup.usuari = usuari and " //$NON-NLS-1$
					+ "usuariGrup.grup = grup and " //$NON-NLS-1$
					+ "(:codi is null or grup.codi like :codi) and " //$NON-NLS-1$
					+ "(:descripcio is null or grup.descripcio like :descripcio)"; //$NON-NLS-1$
			Parameter codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
			Parameter codiDescripcio = new Parameter("descripcio", descripcio); //$NON-NLS-1$
			Parameter codiUsuariParameter = new Parameter("codiUsuari", //$NON-NLS-1$
					codiUsuari);
			Parameter[] parameters = { codiParameter, codiDescripcio,
					codiUsuariParameter };
			List<GroupEntity> valorsDomini = getGroupEntityDao().query(query, parameters);
			if (valorsDomini == null) {
				valorsDomini = new Vector<GroupEntity>();
			}

			String queryGrupPrimari = "select grup " //$NON-NLS-1$
					+ "from " //$NON-NLS-1$
					+ "es.caib.seycon.ng.model.GrupEntity grup, " //$NON-NLS-1$
					+ "es.caib.seycon.ng.model.UsuariEntity usuari " //$NON-NLS-1$
					+ "where " //$NON-NLS-1$
					+ "usuari.codi = :codiUsuari and " //$NON-NLS-1$
					+ "usuari.grupPrimari = grup and " //$NON-NLS-1$
					+ "(:codi is null or grup.codi like :codi) and " //$NON-NLS-1$
					+ "(:descripcio is null or grup.descripcio like :descripcio)"; //$NON-NLS-1$
			codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
			codiDescripcio = new Parameter("descripcio", descripcio); //$NON-NLS-1$
			codiUsuariParameter = new Parameter("codiUsuari", codiUsuari); //$NON-NLS-1$
			Parameter[] parametersGrupPrimari = { codiParameter,
					codiDescripcio, codiUsuariParameter };
			List<GroupEntity> grupPrimari = getGroupEntityDao().query(queryGrupPrimari, parametersGrupPrimari);
			if (grupPrimari != null) {
				valorsDomini.addAll(grupPrimari);
			}
			if (valorsDomini != null) {
				List<ValorDomini> vdl = getGroupEntityDao().toValorDominiList(valorsDomini);
				Iterator<ValorDomini> iterator = vdl.iterator();
				while (iterator.hasNext()) {
					ValorDomini valorDomini = iterator.next();
					valorDomini.setNomDomini(TipusDomini.GRUPS_USUARI);
					valorDomini.setCodiExternDomini(codiUsuari);
				}
				return vdl;
			}
			return new Vector();
		}
		if (domini.getNom().compareToIgnoreCase(TipusDomini.GRUPS) == 0) {
			String query = "select grup " //$NON-NLS-1$
					+ "from es.caib.seycon.ng.model.GrupEntity grup " //$NON-NLS-1$
					+ "where " //$NON-NLS-1$
					+ "(:codi is null or grup.codi like :codi) and " //$NON-NLS-1$
					+ "(:descripcio is null or grup.descripcio like :descripcio)"; //$NON-NLS-1$
			Parameter codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
			Parameter codiDescripcio = new Parameter("descripcio", descripcio); //$NON-NLS-1$
			Parameter[] parameters = { codiParameter, codiDescripcio };
			Collection valorsDomini = getGroupEntityDao().query(query, parameters);
			if (valorsDomini != null) {
				List<ValorDomini> vdl = getGroupEntityDao().toValorDominiList(valorsDomini);
				Iterator<ValorDomini> iterator = vdl.iterator();
				while (iterator.hasNext()) {
					ValorDomini valorDomini = (ValorDomini) iterator.next();
					valorDomini.setNomDomini(TipusDomini.GRUPS);
					valorDomini.setCodiExternDomini(null);
				}
				return vdl;
			}
			return new Vector();
		}
		if (domini.getNom().compareToIgnoreCase(TipusDomini.APLICACIONS) == 0) {
			String query = "select aplicacio " //$NON-NLS-1$
					+ "from es.caib.seycon.ng.model.AplicacioEntity aplicacio " //$NON-NLS-1$
					+ "where " //$NON-NLS-1$
					+ "(:codi is null or upper(aplicacio.codi) like upper(:codi)) and " //$NON-NLS-1$
					+ "(:descripcio is null or aplicacio.nom like :descripcio)"; //$NON-NLS-1$
			Parameter codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
			Parameter codiDescripcio = new Parameter("descripcio", descripcio); //$NON-NLS-1$
			Parameter[] parameters = { codiParameter, codiDescripcio };
			Collection<InformationSystemEntity> valorsDomini = getInformationSystemEntityDao().query(query, parameters);
			if (valorsDomini != null) {
				List<ValorDomini> vdl = getInformationSystemEntityDao().toValorDominiList(valorsDomini);
				Iterator<ValorDomini> iterator = vdl.iterator();
				while (iterator.hasNext()) {
					ValorDomini valorDomini = (ValorDomini) iterator.next();
					valorDomini.setNomDomini(TipusDomini.APLICACIONS);
					valorDomini.setCodiExternDomini(null);
				}
				return vdl;
			}
			return new Vector();
		}
		// domini d'aplicació
		String query = "select valorDominiAplicacio " //$NON-NLS-1$
				+ "from " //$NON-NLS-1$
				+ "es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiAplicacio " //$NON-NLS-1$
				+ "left join valorDominiAplicacio.domini domini " //$NON-NLS-1$
				+ "left join domini.aplicacio aplicacio " //$NON-NLS-1$
				+ "where " //$NON-NLS-1$
				+ "domini.nom = :nomDomini and " //$NON-NLS-1$
				+ "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio)) " //$NON-NLS-1$
				+ "order by valorDominiAplicacio.domini.nom, valorDominiAplicacio.valor "; //$NON-NLS-1$

		String nomDomini = domini.getNom();
		String codiAplicacio = domini.getCodiExtern();

		Parameter nomDominiParameter = new Parameter("nomDomini", nomDomini); //$NON-NLS-1$
		Parameter nomRolParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
				codiAplicacio);
		Parameter[] parametres = { nomDominiParameter, nomRolParameter };

		Collection<DomainValueEntity> valorsDomini = getDomainValueEntityDao().query(query, parametres);
		if (valorsDomini != null) {
			return getDomainValueEntityDao().toValorDominiList(valorsDomini);
		}
		return new Vector();
	}

	protected ValorDomini handleFindValorDominiAplicacioByNomDominiAndCodiAplicacioDominiAndValor(
			String nomDomini, String codiAplicacio, String valor)
			throws Exception {
		String query = "select valorDominiAplicacio " //$NON-NLS-1$
				+ "from " //$NON-NLS-1$
				+ "es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiAplicacio " //$NON-NLS-1$
				+ "left join valorDominiAplicacio.domini domini " //$NON-NLS-1$
				+ "left join domini.aplicacio aplicacio " //$NON-NLS-1$
				+ "where " //$NON-NLS-1$
				+ "domini.nom = :nomDomini and " //$NON-NLS-1$
				+ "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio)) and " //$NON-NLS-1$
				+ "valorDominiAplicacio.valor = :valor " //$NON-NLS-1$
				+ "order by valorDominiAplicacio.domini.nom, valorDominiAplicacio.valor "; //$NON-NLS-1$

		Parameter nomDominiParameter = new Parameter("nomDomini", nomDomini); //$NON-NLS-1$
		Parameter codiAplicacioParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
				codiAplicacio);
		Parameter valorParameter = new Parameter("valor", valor); //$NON-NLS-1$
		Parameter[] parametres = { nomDominiParameter, codiAplicacioParameter,
				valorParameter };

		List<DomainValueEntity> valorsDomini = getDomainValueEntityDao().query(query, parametres);
		if (valorsDomini != null) {
			Iterator<DomainValueEntity> valorsDominiIterator = valorsDomini.iterator();
			if (valorsDominiIterator != null) {
				if (valorsDominiIterator.hasNext()) {
					DomainValueEntity valorDominiEntity = (DomainValueEntity) valorsDominiIterator.next();
					if (valorDominiEntity != null) {
						return getDomainValueEntityDao().toValorDomini(valorDominiEntity);
					}
				}
			}
		}
		return null;
	}

	protected Collection<Domini> handleFindDominisAplicacioByCodiAplicacio(
			String codiAplicacio) throws Exception {
		List<ApplicationDomainEntity> dominiAplicacions = getApplicationDomainEntityDao().findByApplicationCode(codiAplicacio);
		if (dominiAplicacions != null) {
			return getApplicationDomainEntityDao().toDominiList(dominiAplicacions);
		}
		return new Vector();
	}

	protected Domini handleUpdate(Domini domini) throws Exception {
		ApplicationDomainEntity dominiEntity = getApplicationDomainEntityDao().dominiToEntity(domini);
		getApplicationDomainEntityDao().update(dominiEntity);
		return this.getApplicationDomainEntityDao().toDomini(dominiEntity);
	}

	private Domini getDominiSenseDomini() {
		Domini domini = new Domini();
		domini.setNom(TipusDomini.SENSE_DOMINI);
		return domini;
	}

	private Domini getDominiAplicacions() {
		Domini domini = new Domini();
		domini.setNom(TipusDomini.APLICACIONS);
		return domini;
	}

	protected Collection<Domini> handleFindDominisByCodiAplicacio(String codiAplicacio)
			throws Exception {
		List<ApplicationDomainEntity> dominis = getApplicationDomainEntityDao().findByApplicationCodes(codiAplicacio);
		if (dominis != null) {
			List<Domini> dominisVO = getApplicationDomainEntityDao().toDominiList(dominis);
			dominisVO.add(findDominiGrups());
			dominisVO.add(findDominiGrupsUsuari());
			dominisVO.add(getDominiSenseDomini());
			dominisVO.add(getDominiAplicacions());
			return dominisVO;
		}
		return new Vector();
	}

}