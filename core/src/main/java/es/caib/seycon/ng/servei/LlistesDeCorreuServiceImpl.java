// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.rmi.activation.UnknownGroupException;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.soffid.iam.api.MailListRoleMember;
import com.soffid.iam.model.MailListGroupMemberEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;

import es.caib.seycon.ng.comu.CorreuExtern;
import es.caib.seycon.ng.comu.DominiCorreu;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.LlistaCorreu;
import es.caib.seycon.ng.comu.LlistaCorreuUsuari;
import es.caib.seycon.ng.comu.RelacioLlistaCorreu;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownApplicationException;
import es.caib.seycon.ng.exception.UnknownMailListException;
import es.caib.seycon.ng.exception.UnknownRoleException;
import es.caib.seycon.ng.model.CorreuExternEntity;
import es.caib.seycon.ng.model.DominiCorreuEntity;
import es.caib.seycon.ng.model.LlistaCorreuEntity;
import es.caib.seycon.ng.model.LlistaCorreuUsuariEntity;
import es.caib.seycon.ng.model.RelacioLlistaCorreuEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.servei.Messages;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.criteria.CriteriaSearchConfiguration;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.servei.LlistesDeCorreuService
 */
public class LlistesDeCorreuServiceImpl extends es.caib.seycon.ng.servei.LlistesDeCorreuServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.LlistesDeCorreuService#getLlistesDeCorreu()
	 */
	protected java.util.Collection<LlistaCorreu> handleGetLlistesDeCorreu() throws java.lang.Exception {
		return getLlistaCorreuEntityDao().toLlistaCorreuList(getLlistaCorreuEntityDao().loadAll());
	}

	protected Collection<CorreuExtern> handleFindCorreusExternsByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		Collection<CorreuExternEntity> externs = getCorreuExternEntityDao().findCorreusExternsByNomLlistaCorreuAndCodiDomini(
				nomLlistaCorreu, codiDomini);
		if (externs != null) {
			return getCorreuExternEntityDao().toCorreuExternList(externs);
		}
		return new Vector();
	}

	protected DominiCorreu handleFindDominiCorreuByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		LlistaCorreuEntity llistaCorreu = getLlistaCorreuEntityDao().findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
		if (llistaCorreu != null) {
			DominiCorreuEntity dominiCorreu = llistaCorreu.getDomini();
			if (dominiCorreu != null) {
				DominiCorreu dominiCorreuVO = getDominiCorreuEntityDao().toDominiCorreu(dominiCorreu);
				return dominiCorreuVO;
			}
		}
		return null;
	}

	protected Collection<Usuari> handleFindUsuarisByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		LlistaCorreuEntity llista = getLlistaCorreuEntityDao().findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
		Collection <UsuariEntity>usuaris = new LinkedList<UsuariEntity>();
		for (Iterator<LlistaCorreuUsuariEntity> it = llista.getLlistaDeCorreuUsuari().iterator(); it.hasNext();) {
			usuaris.add(it.next().getUsuari());
		}
		return getUsuariEntityDao().toUsuariList(usuaris);
	}

	protected Collection<LlistaCorreu> handleFindLlistesDeCorreuByDades(
		String nom, String domini, String descripcio, String membres)
		throws Exception
	{
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		if (nom != null && (nom.trim().compareTo("") == 0)) { //$NON-NLS-1$
			nom = null;
		}
		
		if (domini != null && (domini.trim().compareTo("") == 0 || domini.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			domini = null;
		}
		
		if (descripcio != null && (descripcio.trim().compareTo("") == 0 || descripcio.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			descripcio = null;
		}
		
		if (membres != null && (membres.trim().compareTo("") == 0 || membres.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
			membres = null;
		}
		
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setMaximumResultSize(limitResults + 1);
		Collection<LlistaCorreuEntity> llistesCorreu = getLlistaCorreuEntityDao().
			findByDades(config, nom, domini, descripcio);
		if (llistesCorreu != null)
		{
			// Check maximum number of results
			if (llistesCorreu.size() > limitResults)
			{
				return getLlistaCorreuEntityDao()
					.toLlistaCorreuList(llistesCorreu).subList(0, limitResults);
			}
			
			return getLlistaCorreuEntityDao().toLlistaCorreuList(llistesCorreu);
		}
		
		return new Vector();
	}

	protected LlistaCorreu handleCreate(LlistaCorreu llistaCorreu) throws Exception {
		LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao().llistaCorreuToEntity(llistaCorreu);
		getLlistaCorreuEntityDao().create(llistaCorreuEntity);
		llistaCorreu.setId(llistaCorreuEntity.getId());
		return getLlistaCorreuEntityDao().toLlistaCorreu(llistaCorreuEntity);
	}

	protected void handleDelete(LlistaCorreu llistaCorreu) throws Exception {
		LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao().llistaCorreuToEntity(llistaCorreu);
		if(!llistaCorreuEntity.getExterns().isEmpty() || !llistaCorreuEntity.getLlistaDeCorreuUsuari().isEmpty() || 
						!llistaCorreuEntity.getRelacioLlistaCorreuFromConte().isEmpty())
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuEntityDaoImpl.IntegrityException"),  //$NON-NLS-1$
							llistaCorreu.getNom()));
		getLlistaCorreuEntityDao().remove(llistaCorreuEntity);
	}

	protected Collection<LlistaCorreuUsuari> handleFindLlistaCorreuUsuariByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu,
			String codiDomini) throws Exception {
		Collection<LlistaCorreuUsuariEntity> llistaCorreuUsuari = getLlistaCorreuUsuariEntityDao().findByNomLlistaCorreuAndCodiDomini(
				nomLlistaCorreu, codiDomini);
		if (llistaCorreuUsuari != null) {
			return getLlistaCorreuUsuariEntityDao().toLlistaCorreuUsuariList(llistaCorreuUsuari);
		}
		return new Vector();
	}

	protected LlistaCorreu handleUpdate(LlistaCorreu llistaCorreu) throws Exception {
		LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao().llistaCorreuToEntity(llistaCorreu);
		// Fem cas específic per a llistes que s'han quedat sense membres
		// des de l'inferficie d'usuari del seu (es borren al quedar-se sense
		// membres). Per al cas de que després d'esborrar usuaris vullguen
		// afegir
		// nous o modificar la llista...
		if (llistaCorreuEntity.getId() != null)
			getLlistaCorreuEntityDao().update(llistaCorreuEntity);
		else
			getLlistaCorreuEntityDao().create(llistaCorreuEntity);
		return getLlistaCorreuEntityDao().toLlistaCorreu(llistaCorreuEntity);
	}

	protected LlistaCorreuUsuari handleCreate(LlistaCorreuUsuari llistaCorreuUsuari) throws Exception {
		LlistaCorreuUsuariEntity llistaCorreuUsuariEntity = getLlistaCorreuUsuariEntityDao().llistaCorreuUsuariToEntity(
				llistaCorreuUsuari);
		if (llistaCorreuUsuariEntity.getUsuari().getCodi().compareTo(Security.getCurrentUser()) == 0) {
			throw new SeyconException(Messages.getString("LlistesDeCorreuServiceImpl.1")); //$NON-NLS-1$
		}

		getLlistaCorreuUsuariEntityDao().create(llistaCorreuUsuariEntity);

		// Marquem l'usuari com a modificat
		updateUserModification(llistaCorreuUsuariEntity.getUsuari());

		return getLlistaCorreuUsuariEntityDao().toLlistaCorreuUsuari(llistaCorreuUsuariEntity);
	}

	protected CorreuExtern handleCreate(CorreuExtern correuExtern) throws Exception {
		CorreuExternEntity correuExternEntity = getCorreuExternEntityDao().correuExternToEntity(correuExtern);
		getCorreuExternEntityDao().create(correuExternEntity);
		correuExtern.setId(correuExternEntity.getId());
		return getCorreuExternEntityDao().toCorreuExtern(correuExternEntity);
	}

	protected void handleDelete(LlistaCorreuUsuari llistaCorreuUsuari) throws Exception {
		LlistaCorreuUsuariEntity llistaCorreuUsuariEntity = getLlistaCorreuUsuariEntityDao().llistaCorreuUsuariToEntity(
				llistaCorreuUsuari);
		// Dades per fer feina...
		UsuariEntity usuariLlista = llistaCorreuUsuariEntity.getUsuari();
		String alies = llistaCorreuUsuari.getNomLlistaCorreu();
		String domini = llistaCorreuUsuari.getCodiDomini();

		// L'esborrem
		getLlistaCorreuUsuariEntityDao().remove(llistaCorreuUsuariEntity);

		// Mirem si hem de fer neteja de la llista:
		netejaLlistaCorreuBuida(alies, domini);

		// Marquem l'usuari com a modificat
		updateUserModification(usuariLlista);
	}

	// Marquem l'usuari com a modificat quan es modifica una llista de correu
	// d'usuari
	private void updateUserModification(UsuariEntity usuariEntity) {// OK
		String usuModifica = getPrincipal() != null ? getPrincipal().getName() : "SEYCON"; //$NON-NLS-1$
		usuariEntity.setUsuariDarreraModificacio(usuModifica);
		usuariEntity.setDataDarreraModificacio(GregorianCalendar.getInstance().getTime());
		getUsuariEntityDao().update(usuariEntity);
	}

	protected void handleDelete(CorreuExtern correuExtern) throws Exception {
		CorreuExternEntity correuExternEntity = getCorreuExternEntityDao().correuExternToEntity(correuExtern);

		// Dades per fer feina...
		String alies = correuExtern.getLlistaCorreuNom();
		String domini = correuExtern.getCodiDomini();

		// L'esborrem
		getCorreuExternEntityDao().remove(correuExternEntity);

		// Mirem si hem de fer neteja de la llista:
		netejaLlistaCorreuBuida(alies, domini);

	}

	protected LlistaCorreuUsuari handleFindLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(String nomLlistaCorreu,
			String codiDomini, String codiUsuari) throws Exception {
		LlistaCorreuUsuariEntity llistaCorreuUsuari = getLlistaCorreuUsuariEntityDao()
				.findByNomLlistaCorreuAndCodiDominiAndCodiUsuari(nomLlistaCorreu, codiDomini, codiUsuari);
		if (llistaCorreuUsuari != null) {
			return getLlistaCorreuUsuariEntityDao().toLlistaCorreuUsuari(llistaCorreuUsuari);
		}
		return null;
	}

	protected LlistaCorreuUsuari handleUpdate(LlistaCorreuUsuari llistaCorreuUsuari) throws Exception {
		// NOTA: en principi no s'hauria d'utilitzar..
		LlistaCorreuUsuariEntity llistaCorreu = getLlistaCorreuUsuariEntityDao().llistaCorreuUsuariToEntity(llistaCorreuUsuari);
		getLlistaCorreuUsuariEntityDao().update(llistaCorreu);

		// Marquem l'usuari com a modificat
		updateUserModification(llistaCorreu.getUsuari());
		return getLlistaCorreuUsuariEntityDao().toLlistaCorreuUsuari(llistaCorreu);
	}

	protected DominiCorreu handleCreate(DominiCorreu dominiCorreu) throws Exception {
		DominiCorreuEntity domainsSameCode = getDominiCorreuEntityDao().findByCodi(dominiCorreu.getCodi());
		if(domainsSameCode != null)
			throw new SeyconException(String.format(Messages.getString("LlistesDeCorreuServiceImpl.CodeDomainExists"),  //$NON-NLS-1$
							dominiCorreu.getCodi())); 
		DominiCorreuEntity dominiCorreuEntity = this.getDominiCorreuEntityDao().dominiCorreuToEntity(dominiCorreu);
		getDominiCorreuEntityDao().create(dominiCorreuEntity);
		dominiCorreu.setId(dominiCorreuEntity.getId());
		return getDominiCorreuEntityDao().toDominiCorreu(dominiCorreuEntity);
	}

	protected void handleDelete(DominiCorreu dominiCorreu) throws Exception {
		DominiCorreuEntity dominiCorreuEntity = this.getDominiCorreuEntityDao().dominiCorreuToEntity(dominiCorreu);
		if (!dominiCorreuEntity.getLlistesCorreu().isEmpty())
			throw new SeyconException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.IntegrityException"),  //$NON-NLS-1$
							dominiCorreu.getCodi()));
		if (!dominiCorreuEntity.getUsuaris().isEmpty())
			throw new SeyconException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.IntegrityException2"),  //$NON-NLS-1$
							dominiCorreu.getCodi()));
		getDominiCorreuEntityDao().remove(dominiCorreuEntity);
	}

	protected CorreuExtern handleFindCorreuExternByAdreca(String adreca) throws Exception {
		CorreuExternEntity correuExternEntity = getCorreuExternEntityDao().findByAdreca(adreca);
		if (correuExternEntity != null) {
			CorreuExtern correuExtern = getCorreuExternEntityDao().toCorreuExtern(correuExternEntity);
			return correuExtern;
		}
		return null;
	}

	protected DominiCorreu handleFindDominiCorreuByCodi(String codi) throws Exception {
		DominiCorreuEntity dominiCorreuEntity = getDominiCorreuEntityDao().findByCodi(codi);
		if (dominiCorreuEntity != null) {
			DominiCorreu dominiCorreu = getDominiCorreuEntityDao().toDominiCorreu(dominiCorreuEntity);
			return dominiCorreu;
		}
		return null;
	}

	protected Collection<DominiCorreu> handleGetDominiCorreus() throws Exception {
		return getDominiCorreuEntityDao().toDominiCorreuList(getDominiCorreuEntityDao().loadAll());
	}

	protected DominiCorreu handleUpdate(DominiCorreu dominiCorreu) throws Exception {
		DominiCorreuEntity dominiCorreuEntity = this.getDominiCorreuEntityDao().dominiCorreuToEntity(dominiCorreu);
		getDominiCorreuEntityDao().update(dominiCorreuEntity);
		return getDominiCorreuEntityDao().toDominiCorreu(dominiCorreuEntity);
	}

	protected LlistaCorreu handleFindLlistaCorreuByNomAndCodiDomini(String nomLlistaCorreu, String codiDomini) throws Exception {
		LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao().findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
		if (llistaCorreuEntity != null) {
			LlistaCorreu llistaCorreu = getLlistaCorreuEntityDao().toLlistaCorreu(llistaCorreuEntity);
			return llistaCorreu;
		}
		return null;
	}

	protected RelacioLlistaCorreu handleCreate(RelacioLlistaCorreu relacioLlistaCorreu) throws Exception {
		RelacioLlistaCorreuEntity relacioLlistaCorreuEntity = getRelacioLlistaCorreuEntityDao().relacioLlistaCorreuToEntity(
				relacioLlistaCorreu);
		getRelacioLlistaCorreuEntityDao().create(relacioLlistaCorreuEntity);
		relacioLlistaCorreu.setId(relacioLlistaCorreuEntity.getId());
		relacioLlistaCorreu = getRelacioLlistaCorreuEntityDao().toRelacioLlistaCorreu(relacioLlistaCorreuEntity);
		return relacioLlistaCorreu;
	}

	protected void handleDelete(RelacioLlistaCorreu relacioLlistaCorreu) throws Exception {
		RelacioLlistaCorreuEntity relacioLlistaCorreuEntity = getRelacioLlistaCorreuEntityDao().relacioLlistaCorreuToEntity(
				relacioLlistaCorreu);
		LlistaCorreuEntity llistaConte = relacioLlistaCorreuEntity.getConte();
		// Per procesar després la baixa de llistes buides (de la contenidora)
		String alies = llistaConte.getNom();
		String domini = llistaConte.getDomini() != null ? llistaConte.getDomini().getCodi() : ""; //$NON-NLS-1$

		// Esborrem la relació entre llistes
		getRelacioLlistaCorreuEntityDao().remove(relacioLlistaCorreuEntity);

		// Mirem si hem de fer neteja de la llista:
		netejaLlistaCorreuBuida(alies, domini);
	}

	protected RelacioLlistaCorreu handleFindRelacioLlistaCorreuByNomAndCodiLlistaCorreuPertanyAndNomAndCodiLlistaCorreuConte(
			String nomPertany, String dominiCorreuPertany, String nomConte, String dominiCorreuConte) throws Exception {
		RelacioLlistaCorreuEntity relacioLlistaCorreuEntity = getRelacioLlistaCorreuEntityDao()
				.findByNomPertanyAndDominiPertanyAndNomConteAndDominiConte(nomPertany, dominiCorreuPertany, nomConte,
						dominiCorreuConte);
		if (relacioLlistaCorreuEntity != null) {
			RelacioLlistaCorreu relacioLlistaCorreu = getRelacioLlistaCorreuEntityDao().toRelacioLlistaCorreu(
					relacioLlistaCorreuEntity);
			return relacioLlistaCorreu;
		}
		return null;
	}

	protected Collection<RelacioLlistaCorreu> handleFindRelacionsLlistaCorreuByNomLlistaCorreuConteAndCodiDomini(String nomLlistaCorreuConte,
			String codiDomini) throws Exception {
		Collection<RelacioLlistaCorreuEntity> relacionsLlistaCorreu = getRelacioLlistaCorreuEntityDao().findCollectionByNomConteAndCodiDomini(
				nomLlistaCorreuConte, codiDomini);
		if (relacionsLlistaCorreu != null) {
			return getRelacioLlistaCorreuEntityDao().toRelacioLlistaCorreuList(relacionsLlistaCorreu);
		}
		return new Vector();
	}

	protected Collection<RelacioLlistaCorreu> handleFindRelacionsLlistaCorreuByNomLlistaCorreuPertanyAndCodiDomini(
			String nomLlistaCorreuPertany, String codiDomini) throws Exception {
		Collection<RelacioLlistaCorreuEntity> relacionsLlistaCorreu = getRelacioLlistaCorreuEntityDao()
				.findCollectionByNomPertanyAndCodiDomini(nomLlistaCorreuPertany, codiDomini);
		if (relacionsLlistaCorreu != null) {
			return getRelacioLlistaCorreuEntityDao().toRelacioLlistaCorreuList(relacionsLlistaCorreu);
		}
		return new Vector();
	}

	protected RelacioLlistaCorreu handleUpdate(RelacioLlistaCorreu relacioLlistaCorreu) throws Exception {
		RelacioLlistaCorreuEntity relacioLlistaCorreuEntity = getRelacioLlistaCorreuEntityDao().relacioLlistaCorreuToEntity(
				relacioLlistaCorreu);
		getRelacioLlistaCorreuEntityDao().update(relacioLlistaCorreuEntity);
		relacioLlistaCorreu = getRelacioLlistaCorreuEntityDao().toRelacioLlistaCorreu(relacioLlistaCorreuEntity);
		return relacioLlistaCorreu;
	}

	protected Collection<DominiCorreu> handleFindDominisCorreuByFiltre(
		String codi, String descripcio, String obsolet) throws Exception
	{
		int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
		
		if (codi != null && ((codi.trim().compareTo("") == 0) || (codi.trim().compareTo("%") == 0))) { //$NON-NLS-1$ //$NON-NLS-2$
			codi = null;
		}
		
		if (descripcio != null && ((descripcio.trim().compareTo("") == 0) || (descripcio.trim().compareTo("%") == 0))) { //$NON-NLS-1$ //$NON-NLS-2$
			descripcio = null;
		}
		
		if (obsolet != null && ((obsolet.trim().compareTo("") == 0) || (obsolet.trim().compareTo("%") == 0))) { //$NON-NLS-1$ //$NON-NLS-2$
			obsolet = null;
		}
		
		Collection<DominiCorreuEntity>  dominisDeCorreu = getDominiCorreuEntityDao().findByFiltre(codi, descripcio, obsolet);
		if (dominisDeCorreu != null)
		{
			// Check maximum number of results
			if (dominisDeCorreu.size() > limitResults)
			{
				return getDominiCorreuEntityDao()
					.toDominiCorreuList(dominisDeCorreu)
					.subList(0, limitResults);
			}
			
			return getDominiCorreuEntityDao().toDominiCorreuList(dominisDeCorreu);
		}
		
		return new Vector();
	}

	protected Collection<LlistaCorreuUsuari> handleFindLlistaCorreuUsuariByCodiUsuari(String codiUsuari) throws Exception {
		Collection<LlistaCorreuUsuariEntity> llistaCorreuUsuaris = getLlistaCorreuUsuariEntityDao().findByCodiUsuari(codiUsuari);
		if (llistaCorreuUsuaris != null) {
			return getLlistaCorreuUsuariEntityDao().toLlistaCorreuUsuariList(llistaCorreuUsuaris);
		}
		return new Vector();
	}

	protected String handleFindLlistaCompactaExternsByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		String llistat = ""; //$NON-NLS-1$
		LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao().findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
		Collection correusExterns = llistaCorreuEntity.getExterns();
		if (correusExterns != null) {
			Iterator iterator = correusExterns.iterator();
			while (iterator.hasNext()) {
				CorreuExternEntity correuExtern = (CorreuExternEntity) iterator.next();
				llistat += correuExtern.getAdreca() + ", "; //$NON-NLS-1$
			}
		}
		if ("".equals(llistat)) { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	protected String handleFindLlistaCompactaLlistesByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		String llistat = ""; //$NON-NLS-1$
		Collection llistesCorreuEntities = getRelacioLlistaCorreuEntityDao().findCollectionByNomConteAndCodiDomini(nomLlistaCorreu,
				codiDomini);
		if (llistesCorreuEntities != null) {
			Iterator iterator = llistesCorreuEntities.iterator();
			while (iterator.hasNext()) {
				RelacioLlistaCorreuEntity relacioLlistaCorreuEntity = (RelacioLlistaCorreuEntity) iterator.next();
				LlistaCorreuEntity llistaCorreuEntityPertany = relacioLlistaCorreuEntity.getPertany();
				llistat += llistaCorreuEntityPertany.getNom() + "@" + llistaCorreuEntityPertany.getDomini() + ", "; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		if ("".equals(llistat)) { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	protected String handleFindLlistaCompactaUsuarisByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		String llistat = ""; //$NON-NLS-1$
		LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao().findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
		Collection llistaCorreuUsuaris = llistaCorreuEntity.getLlistaDeCorreuUsuari();
		if (llistaCorreuUsuaris != null) {
			Iterator iterator = llistaCorreuUsuaris.iterator();
			while (iterator.hasNext()) {
				LlistaCorreuUsuariEntity llistaCorreuUsuari = (LlistaCorreuUsuariEntity) iterator.next();
				llistat += llistaCorreuUsuari.getUsuari().getCodi() + ", "; //$NON-NLS-1$
			}
		}
		if ("".equals(llistat)) { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	protected void handleNetejaLlistaCorreuBuida(String nomLlistaCorreu, String codiDomini) throws Exception {
		// Mirem si en queda cap membre a la llista per
		// veure si l'hem d'esborrar:
		Collection correusExterns = findCorreusExternsByNomLlistaCorreuAndCodiDomini(nomLlistaCorreu, codiDomini);
		Collection usuaris = findLlistaCorreuUsuariByNomLlistaCorreuAndCodiDomini(nomLlistaCorreu, codiDomini);
		Collection llistesDeCorreuConte = findRelacionsLlistaCorreuByNomLlistaCorreuConteAndCodiDomini(nomLlistaCorreu, codiDomini);
		// no pot tindre tampoc llistes on pertany (donaria error)
		Collection llistesDeCorreuPertany = findRelacionsLlistaCorreuByNomLlistaCorreuPertanyAndCodiDomini(nomLlistaCorreu, codiDomini);
		if (correusExterns.size() == 0 && usuaris.size() == 0 && llistesDeCorreuConte.size() == 0
				&& llistesDeCorreuPertany.size() == 0) {
			LlistaCorreuEntity llistaEsborrar = getLlistaCorreuEntityDao().findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
			if (llistaEsborrar != null)
				getLlistaCorreuEntityDao().remove(llistaEsborrar);
		}

	}

	// és equivalent a handleDelete però no es fa neteja de llistes
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(LlistaCorreuUsuari llistaCorreuUsuari) throws Exception {
		LlistaCorreuUsuariEntity llistaCorreuUsuariEntity = getLlistaCorreuUsuariEntityDao().llistaCorreuUsuariToEntity(
				llistaCorreuUsuari);
		// Dades per fer feina...
		UsuariEntity usuariLlista = llistaCorreuUsuariEntity.getUsuari();

		// L'esborrem
		getLlistaCorreuUsuariEntityDao().remove(llistaCorreuUsuariEntity);

		// Marquem l'usuari com a modificat
		updateUserModification(usuariLlista);
	}

	// és equivalent a handleDelete però no es fa neteja de llistes
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(CorreuExtern correuExtern) throws Exception {
		CorreuExternEntity correuExternEntity = getCorreuExternEntityDao().correuExternToEntity(correuExtern);

		// Dades per fer feina...
		String alies = correuExtern.getLlistaCorreuNom();
		String domini = correuExtern.getCodiDomini();

		// L'esborrem
		getCorreuExternEntityDao().remove(correuExternEntity);
	}

	// és equivalent a handleDelete però no es fa neteja de la llista continguda
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(RelacioLlistaCorreu relacioLlistaCorreu) throws Exception {
		RelacioLlistaCorreuEntity relacioLlistaCorreuEntity = getRelacioLlistaCorreuEntityDao().relacioLlistaCorreuToEntity(
				relacioLlistaCorreu);

		// Esborrem la relació entre llistes
		getRelacioLlistaCorreuEntityDao().remove(relacioLlistaCorreuEntity);
	}

	@Override
	protected Collection<Grup> handleFindGroupMembers(String nomLlistaCorreu,
			String codiDomini) throws Exception {
		
		LlistaCorreuEntity list = getLlistaCorreuEntityDao().findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
		List<Grup> grups = new LinkedList<Grup>();
		for ( MailListGroupMemberEntity member: list.getGroups())
		{
			grups.add ( getGrupEntityDao().toGrup(member.getGroup()));
		}
		return grups;
	}

	@Override
	protected Collection<MailListRoleMember> handleFindRoleMembers(
			String nomLlistaCorreu, String codiDomini) throws Exception {
		LlistaCorreuEntity list = getLlistaCorreuEntityDao().findByNomAndCodiDomini(nomLlistaCorreu, codiDomini);
		return getMailListRoleMemberEntityDao().toMailListRoleMemberList(list.getRoles());
	}

	@Override
	protected void handleSubscribeGroup(String mailListName,
			String mailListDomain, String groupName) throws Exception {
		MailListGroupMemberEntity entity = getMailListGroupMemberEntityDao().newMailListGroupMemberEntity();
		LlistaCorreuEntity list = getLlistaCorreuEntityDao().findByNomAndCodiDomini(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		entity.setMailList(list);
		entity.setGroup(getGrupEntityDao().findByCodi(groupName));
		if (entity.getGroup() == null)
			throw new UnknownGroupException(groupName);
		getMailListGroupMemberEntityDao().create(entity);
	}

	@Override
	protected void handleUnsubscribeGroup(String mailListName,
			String mailListDomain, String groupName) throws Exception {
		
		LlistaCorreuEntity list = getLlistaCorreuEntityDao().findByNomAndCodiDomini(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		for ( MailListGroupMemberEntity group: list.getGroups())
		{
			if (group.getGroup().getCodi().equals(groupName))
				getMailListGroupMemberEntityDao().remove(group);
		}	
		
	}

	@Override
	protected MailListRoleMember handleSubscribeRole(String mailListName,
			String mailListDomain, MailListRoleMember roleMember)
			throws Exception {
		MailListRoleMemberEntity entity = getMailListRoleMemberEntityDao().newMailListRoleMemberEntity();
		LlistaCorreuEntity list = getLlistaCorreuEntityDao().findByNomAndCodiDomini(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		entity.setMailList(list);
		RolEntity role = getRolEntityDao().findByNameAndDispatcher(roleMember.getRoleName(), roleMember.getDispatcherName());
		if (role == null)
			throw new UnknownRoleException(roleMember.getRoleName()+"@"+roleMember.getDispatcherName());
		entity.setRole(role);
		if (roleMember.getScope() != null && roleMember.getScope().length() > 0)
		{
			if (TipusDomini.APLICACIONS.equals(role.getTipusDomini()))
			{
				entity.setInformationSystemScope(getAplicacioEntityDao().findByCodi(roleMember.getScope()));
				if (entity.getInformationSystemScope() == null)
					throw new UnknownApplicationException(roleMember.getScope());
			}
			else if (TipusDomini.GRUPS.equals(role.getTipusDomini()) ||
					TipusDomini.GRUPS_USUARI.equals(role.getTipusDomini()))
			{
				entity.setGroupScope(getGrupEntityDao().findByCodi(roleMember.getScope()));
				if (entity.getInformationSystemScope() == null)
					throw new UnknownGroupException(roleMember.getScope());
			}
			if (TipusDomini.DOMINI_APLICACIO.equals(role.getTipusDomini()))
			{
				entity.setDomainValueScope(getValorDominiAplicacioEntityDao().findByApplicationDomainValue(
						role.getAplicacio().getCodi(), 
						role.getDominiAplicacio().getNom(), 
						roleMember.getScope()));
				if (entity.getDomainValueScope() == null)
					throw new IllegalArgumentException(roleMember.getScope());
			}
			
		}
		entity.setRole(role);
		getMailListRoleMemberEntityDao().create(entity);
		return getMailListRoleMemberEntityDao().toMailListRoleMember(entity);
	}

	@Override
	protected void handleUnsubscribeRole(String mailListName,
			String mailListDomain, MailListRoleMember roleMember)
			throws Exception {
		MailListRoleMemberEntity entity = getMailListRoleMemberEntityDao().newMailListRoleMemberEntity();
		LlistaCorreuEntity list = getLlistaCorreuEntityDao().findByNomAndCodiDomini(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		for (MailListRoleMemberEntity member: list.getRoles())
		{
			entity.setMailList(list);
			RolEntity role = member.getRole();
			if (role.getNom().equals (roleMember.getRoleName()) &&
					role.getBaseDeDades().getCodi().equals(roleMember.getDispatcherName()))
			{
				if (roleMember.getScope() == null || roleMember.getScope().length() == 0)
				{
					if (member.getDomainValueScope() == null &&
							member.getGroupScope() == null &&
							member.getInformationSystemScope() == null)
					{
						getMailListRoleMemberEntityDao().remove(member);
					}
				}
				else
				{
					if (TipusDomini.APLICACIONS.equals(role.getTipusDomini()) &&
							member.getInformationSystemScope() != null &&
							member.getInformationSystemScope().getCodi().equals(roleMember.getScope()))
					{
						getMailListRoleMemberEntityDao().remove(member);
					}
					else if ( (TipusDomini.GRUPS.equals(role.getTipusDomini()) ||
							  TipusDomini.GRUPS_USUARI.equals(role.getTipusDomini())) &&
							  member.getGroupScope() != null &&
							  member.getGroupScope().getCodi().equals(roleMember.getScope()))
					{
						getMailListRoleMemberEntityDao().remove(member);
					}
					else if (TipusDomini.DOMINI_APLICACIO.equals(role.getTipusDomini()) &&
							  member.getDomainValueScope() != null &&
							  member.getDomainValueScope().getValor().equals(roleMember.getScope()))
					{
						getMailListRoleMemberEntityDao().remove(member);
					}
					
				}
				
			}
		}
	}

}
