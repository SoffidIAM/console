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

import com.soffid.iam.api.MailListRoleMember;
import com.soffid.iam.model.EmailDomainEntity;
import com.soffid.iam.model.EmailListContainerEntity;
import com.soffid.iam.model.EmailListEntity;
import com.soffid.iam.model.ExternEmailEntity;
import com.soffid.iam.model.MailListGroupMemberEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.UserEmailEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
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
import es.caib.seycon.ng.servei.Messages;
import es.caib.seycon.ng.utils.Security;
import java.rmi.activation.UnknownGroupException;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @see es.caib.seycon.ng.servei.LlistesDeCorreuService
 */
public class LlistesDeCorreuServiceImpl extends es.caib.seycon.ng.servei.LlistesDeCorreuServiceBase {

	/**
	 * @see es.caib.seycon.ng.servei.LlistesDeCorreuService#getLlistesDeCorreu()
	 */
	protected java.util.Collection<LlistaCorreu> handleGetLlistesDeCorreu() throws java.lang.Exception {
		return getEmailListEntityDao().toLlistaCorreuList(getEmailListEntityDao().loadAll());
	}

	protected Collection<CorreuExtern> handleFindCorreusExternsByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		Collection<ExternEmailEntity> externs = getExternEmailEntityDao().findByList(nomLlistaCorreu, codiDomini);
		if (externs != null) {
			return getExternEmailEntityDao().toCorreuExternList(externs);
		}
		return new Vector();
	}

	protected DominiCorreu handleFindDominiCorreuByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		EmailListEntity llistaCorreu = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		if (llistaCorreu != null) {
			EmailDomainEntity dominiCorreu = llistaCorreu.getDomain();
			if (dominiCorreu != null) {
				DominiCorreu dominiCorreuVO = getEmailDomainEntityDao().toDominiCorreu(dominiCorreu);
				return dominiCorreuVO;
			}
		}
		return null;
	}

	protected Collection<Usuari> handleFindUsuarisByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		EmailListEntity llista = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		Collection<UserEntity> usuaris = new LinkedList<UserEntity>();
		for (Iterator<UserEmailEntity> it = llista.getUserMailLists().iterator(); it.hasNext(); ) {
            usuaris.add(it.next().getUser());
        }
		return getUserEntityDao().toUsuariList(usuaris);
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
		Collection<EmailListEntity> llistesCorreu = getEmailListEntityDao().findByData(config, nom, domini, descripcio);
		if (llistesCorreu != null)
		{
			// Check maximum number of results
			if (llistesCorreu.size() > limitResults)
			{
				return getEmailListEntityDao().toLlistaCorreuList(llistesCorreu).subList(0, limitResults);
			}
			
			return getEmailListEntityDao().toLlistaCorreuList(llistesCorreu);
		}
		
		return new Vector();
	}

	protected LlistaCorreu handleCreate(LlistaCorreu llistaCorreu) throws Exception {
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().llistaCorreuToEntity(llistaCorreu);
		getEmailListEntityDao().create(llistaCorreuEntity);
		llistaCorreu.setId(llistaCorreuEntity.getId());
		return getEmailListEntityDao().toLlistaCorreu(llistaCorreuEntity);
	}

	protected void handleDelete(LlistaCorreu llistaCorreu) throws Exception {
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().llistaCorreuToEntity(llistaCorreu);
		if(!llistaCorreuEntity.getExternals().isEmpty() || !llistaCorreuEntity.getUserMailLists().isEmpty() || !llistaCorreuEntity.getMailListContent().isEmpty())
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuEntityDaoImpl.IntegrityException"),  //$NON-NLS-1$
							llistaCorreu.getNom()));
		getEmailListEntityDao().remove(llistaCorreuEntity);
	}

	protected Collection<LlistaCorreuUsuari> handleFindLlistaCorreuUsuariByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu,
			String codiDomini) throws Exception {
		Collection<UserEmailEntity> llistaCorreuUsuari = getUserEmailEntityDao().findByMailList(nomLlistaCorreu, codiDomini);
		if (llistaCorreuUsuari != null) {
			return getUserEmailEntityDao().toLlistaCorreuUsuariList(llistaCorreuUsuari);
		}
		return new Vector();
	}

	protected LlistaCorreu handleUpdate(LlistaCorreu llistaCorreu) throws Exception {
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().llistaCorreuToEntity(llistaCorreu);
		// Fem cas específic per a llistes que s'han quedat sense membres
		// des de l'inferficie d'usuari del seu (es borren al quedar-se sense
		// membres). Per al cas de que després d'esborrar usuaris vullguen
		// afegir
		// nous o modificar la llista...
		if (llistaCorreuEntity.getId() != null)
			getEmailListEntityDao().update(llistaCorreuEntity);
		else
			getEmailListEntityDao().create(llistaCorreuEntity);
		return getEmailListEntityDao().toLlistaCorreu(llistaCorreuEntity);
	}

	protected LlistaCorreuUsuari handleCreate(LlistaCorreuUsuari llistaCorreuUsuari) throws Exception {
		UserEmailEntity llistaCorreuUsuariEntity = getUserEmailEntityDao().llistaCorreuUsuariToEntity(llistaCorreuUsuari);
		if (llistaCorreuUsuariEntity.getUser().getUserName().compareTo(Security.getCurrentUser()) == 0) {
			throw new SeyconException(Messages.getString("LlistesDeCorreuServiceImpl.1")); //$NON-NLS-1$
		}

		getUserEmailEntityDao().create(llistaCorreuUsuariEntity);

		// Marquem l'usuari com a modificat
		updateUserModification(llistaCorreuUsuariEntity.getUser());

		return getUserEmailEntityDao().toLlistaCorreuUsuari(llistaCorreuUsuariEntity);
	}

	protected CorreuExtern handleCreate(CorreuExtern correuExtern) throws Exception {
		ExternEmailEntity correuExternEntity = getExternEmailEntityDao().correuExternToEntity(correuExtern);
		getExternEmailEntityDao().create(correuExternEntity);
		correuExtern.setId(correuExternEntity.getId());
		return getExternEmailEntityDao().toCorreuExtern(correuExternEntity);
	}

	protected void handleDelete(LlistaCorreuUsuari llistaCorreuUsuari) throws Exception {
		UserEmailEntity llistaCorreuUsuariEntity = getUserEmailEntityDao().llistaCorreuUsuariToEntity(llistaCorreuUsuari);
		// Dades per fer feina...
		UserEntity usuariLlista = llistaCorreuUsuariEntity.getUser();
		String alies = llistaCorreuUsuari.getNomLlistaCorreu();
		String domini = llistaCorreuUsuari.getCodiDomini();

		// L'esborrem
		getUserEmailEntityDao().remove(llistaCorreuUsuariEntity);

		// Mirem si hem de fer neteja de la llista:
		netejaLlistaCorreuBuida(alies, domini);

		// Marquem l'usuari com a modificat
		updateUserModification(usuariLlista);
	}

	// Marquem l'usuari com a modificat quan es modifica una llista de correu
	// d'usuari
	private void updateUserModification(UserEntity usuariEntity) {// OK
		String usuModifica = getPrincipal() != null ? getPrincipal().getName() : "SEYCON"; //$NON-NLS-1$
		usuariEntity.setLastUserModification(usuModifica);
		usuariEntity.setLastModificationDate(GregorianCalendar.getInstance().getTime());
		getUserEntityDao().update(usuariEntity);
	}

	protected void handleDelete(CorreuExtern correuExtern) throws Exception {
		ExternEmailEntity correuExternEntity = getExternEmailEntityDao().correuExternToEntity(correuExtern);

		// Dades per fer feina...
		String alies = correuExtern.getLlistaCorreuNom();
		String domini = correuExtern.getCodiDomini();

		// L'esborrem
		getExternEmailEntityDao().remove(correuExternEntity);

		// Mirem si hem de fer neteja de la llista:
		netejaLlistaCorreuBuida(alies, domini);

	}

	protected LlistaCorreuUsuari handleFindLlistaCorreuUsuariByNomLlistaCorreuAndCodiDominiAndCodiUsuari(String nomLlistaCorreu,
			String codiDomini, String codiUsuari) throws Exception {
		UserEmailEntity llistaCorreuUsuari = getUserEmailEntityDao().findByListAndUser(nomLlistaCorreu, codiDomini, codiUsuari);
		if (llistaCorreuUsuari != null) {
			return getUserEmailEntityDao().toLlistaCorreuUsuari(llistaCorreuUsuari);
		}
		return null;
	}

	protected LlistaCorreuUsuari handleUpdate(LlistaCorreuUsuari llistaCorreuUsuari) throws Exception {
		// NOTA: en principi no s'hauria d'utilitzar..
		UserEmailEntity llistaCorreu = getUserEmailEntityDao().llistaCorreuUsuariToEntity(llistaCorreuUsuari);
		getUserEmailEntityDao().update(llistaCorreu);

		// Marquem l'usuari com a modificat
		updateUserModification(llistaCorreu.getUser());
		return getUserEmailEntityDao().toLlistaCorreuUsuari(llistaCorreu);
	}

	protected DominiCorreu handleCreate(DominiCorreu dominiCorreu) throws Exception {
		EmailDomainEntity domainsSameCode = getEmailDomainEntityDao().findByCode(dominiCorreu.getCodi());
		if(domainsSameCode != null)
			throw new SeyconException(String.format(Messages.getString("LlistesDeCorreuServiceImpl.CodeDomainExists"),  //$NON-NLS-1$
							dominiCorreu.getCodi())); 
		EmailDomainEntity dominiCorreuEntity = this.getEmailDomainEntityDao().dominiCorreuToEntity(dominiCorreu);
		getEmailDomainEntityDao().create(dominiCorreuEntity);
		dominiCorreu.setId(dominiCorreuEntity.getId());
		return getEmailDomainEntityDao().toDominiCorreu(dominiCorreuEntity);
	}

	protected void handleDelete(DominiCorreu dominiCorreu) throws Exception {
		EmailDomainEntity dominiCorreuEntity = this.getEmailDomainEntityDao().dominiCorreuToEntity(dominiCorreu);
		if (!dominiCorreuEntity.getMailLists().isEmpty())
			throw new SeyconException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.IntegrityException"),  //$NON-NLS-1$
							dominiCorreu.getCodi()));
		if (!dominiCorreuEntity.getUsers().isEmpty())
			throw new SeyconException(String.format(Messages.getString("DominiCorreuEntityDaoImpl.IntegrityException2"),  //$NON-NLS-1$
							dominiCorreu.getCodi()));
		getEmailDomainEntityDao().remove(dominiCorreuEntity);
	}

	protected CorreuExtern handleFindCorreuExternByAdreca(String adreca) throws Exception {
		ExternEmailEntity correuExternEntity = getExternEmailEntityDao().findByAddress(adreca);
		if (correuExternEntity != null) {
			CorreuExtern correuExtern = getExternEmailEntityDao().toCorreuExtern(correuExternEntity);
			return correuExtern;
		}
		return null;
	}

	protected DominiCorreu handleFindDominiCorreuByCodi(String codi) throws Exception {
		EmailDomainEntity dominiCorreuEntity = getEmailDomainEntityDao().findByCode(codi);
		if (dominiCorreuEntity != null) {
			DominiCorreu dominiCorreu = getEmailDomainEntityDao().toDominiCorreu(dominiCorreuEntity);
			return dominiCorreu;
		}
		return null;
	}

	protected Collection<DominiCorreu> handleGetDominiCorreus() throws Exception {
		return getEmailDomainEntityDao().toDominiCorreuList(getEmailDomainEntityDao().loadAll());
	}

	protected DominiCorreu handleUpdate(DominiCorreu dominiCorreu) throws Exception {
		EmailDomainEntity dominiCorreuEntity = this.getEmailDomainEntityDao().dominiCorreuToEntity(dominiCorreu);
		getEmailDomainEntityDao().update(dominiCorreuEntity);
		return getEmailDomainEntityDao().toDominiCorreu(dominiCorreuEntity);
	}

	protected LlistaCorreu handleFindLlistaCorreuByNomAndCodiDomini(String nomLlistaCorreu, String codiDomini) throws Exception {
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		if (llistaCorreuEntity != null) {
			LlistaCorreu llistaCorreu = getEmailListEntityDao().toLlistaCorreu(llistaCorreuEntity);
			return llistaCorreu;
		}
		return null;
	}

	protected RelacioLlistaCorreu handleCreate(RelacioLlistaCorreu relacioLlistaCorreu) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().relacioLlistaCorreuToEntity(relacioLlistaCorreu);
		getEmailListContainerEntityDao().create(relacioLlistaCorreuEntity);
		relacioLlistaCorreu.setId(relacioLlistaCorreuEntity.getId());
		relacioLlistaCorreu = getEmailListContainerEntityDao().toRelacioLlistaCorreu(relacioLlistaCorreuEntity);
		return relacioLlistaCorreu;
	}

	protected void handleDelete(RelacioLlistaCorreu relacioLlistaCorreu) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().relacioLlistaCorreuToEntity(relacioLlistaCorreu);
		EmailListEntity llistaConte = relacioLlistaCorreuEntity.getContains();
		// Per procesar després la baixa de llistes buides (de la contenidora)
		String alies = llistaConte.getName();
		String domini = llistaConte.getDomain() != null ? llistaConte.getDomain().getName() : ""; //$NON-NLS-1$

		// Esborrem la relació entre llistes
		getEmailListContainerEntityDao().remove(relacioLlistaCorreuEntity);

		// Mirem si hem de fer neteja de la llista:
		netejaLlistaCorreuBuida(alies, domini);
	}

	protected RelacioLlistaCorreu handleFindRelacioLlistaCorreuByNomAndCodiLlistaCorreuPertanyAndNomAndCodiLlistaCorreuConte(
			String nomPertany, String dominiCorreuPertany, String nomConte, String dominiCorreuConte) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().findByContainerAndContained(nomPertany, dominiCorreuPertany, nomConte, dominiCorreuConte);
		if (relacioLlistaCorreuEntity != null) {
			RelacioLlistaCorreu relacioLlistaCorreu = getEmailListContainerEntityDao().toRelacioLlistaCorreu(relacioLlistaCorreuEntity);
			return relacioLlistaCorreu;
		}
		return null;
	}

	protected Collection<RelacioLlistaCorreu> handleFindRelacionsLlistaCorreuByNomLlistaCorreuConteAndCodiDomini(String nomLlistaCorreuConte,
			String codiDomini) throws Exception {
		Collection<EmailListContainerEntity> relacionsLlistaCorreu = getEmailListContainerEntityDao().findByContained(nomLlistaCorreuConte, codiDomini);
		if (relacionsLlistaCorreu != null) {
			return getEmailListContainerEntityDao().toRelacioLlistaCorreuList(relacionsLlistaCorreu);
		}
		return new Vector();
	}

	protected Collection<RelacioLlistaCorreu> handleFindRelacionsLlistaCorreuByNomLlistaCorreuPertanyAndCodiDomini(
			String nomLlistaCorreuPertany, String codiDomini) throws Exception {
		Collection<EmailListContainerEntity> relacionsLlistaCorreu = getEmailListContainerEntityDao().findByContainer(nomLlistaCorreuPertany, codiDomini);
		if (relacionsLlistaCorreu != null) {
			return getEmailListContainerEntityDao().toRelacioLlistaCorreuList(relacionsLlistaCorreu);
		}
		return new Vector();
	}

	protected RelacioLlistaCorreu handleUpdate(RelacioLlistaCorreu relacioLlistaCorreu) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().relacioLlistaCorreuToEntity(relacioLlistaCorreu);
		getEmailListContainerEntityDao().update(relacioLlistaCorreuEntity);
		relacioLlistaCorreu = getEmailListContainerEntityDao().toRelacioLlistaCorreu(relacioLlistaCorreuEntity);
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
		
		Collection<EmailDomainEntity> dominisDeCorreu = getEmailDomainEntityDao().findByCriteria(codi, descripcio, obsolet);
		if (dominisDeCorreu != null)
		{
			// Check maximum number of results
			if (dominisDeCorreu.size() > limitResults)
			{
				return getEmailDomainEntityDao().toDominiCorreuList(dominisDeCorreu).subList(0, limitResults);
			}
			
			return getEmailDomainEntityDao().toDominiCorreuList(dominisDeCorreu);
		}
		
		return new Vector();
	}

	protected Collection<LlistaCorreuUsuari> handleFindLlistaCorreuUsuariByCodiUsuari(String codiUsuari) throws Exception {
		Collection<UserEmailEntity> llistaCorreuUsuaris = getUserEmailEntityDao().findByUser(codiUsuari);
		if (llistaCorreuUsuaris != null) {
			return getUserEmailEntityDao().toLlistaCorreuUsuariList(llistaCorreuUsuaris);
		}
		return new Vector();
	}

	protected String handleFindLlistaCompactaExternsByNomLlistaCorreuAndCodiDomini(String nomLlistaCorreu, String codiDomini)
			throws Exception {
		String llistat = ""; //$NON-NLS-1$
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		Collection correusExterns = llistaCorreuEntity.getExternals();
		if (correusExterns != null) {
			Iterator iterator = correusExterns.iterator();
			while (iterator.hasNext()) {
				ExternEmailEntity correuExtern = (ExternEmailEntity) iterator.next();
				llistat += correuExtern.getAddress() + ", "; //$NON-NLS-1$
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
		Collection llistesCorreuEntities = getEmailListContainerEntityDao().findByContained(nomLlistaCorreu, codiDomini);
		if (llistesCorreuEntities != null) {
			Iterator iterator = llistesCorreuEntities.iterator();
			while (iterator.hasNext()) {
				EmailListContainerEntity relacioLlistaCorreuEntity = (EmailListContainerEntity) iterator.next();
				EmailListEntity llistaCorreuEntityPertany = relacioLlistaCorreuEntity.getPertains();
				llistat += llistaCorreuEntityPertany.getName() + "@" + llistaCorreuEntityPertany.getDomain() + ", "; //$NON-NLS-1$ //$NON-NLS-2$
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
		EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		Collection llistaCorreuUsuaris = llistaCorreuEntity.getUserMailLists();
		if (llistaCorreuUsuaris != null) {
			Iterator iterator = llistaCorreuUsuaris.iterator();
			while (iterator.hasNext()) {
				UserEmailEntity llistaCorreuUsuari = (UserEmailEntity) iterator.next();
				llistat += llistaCorreuUsuari.getUser().getUserName() + ", "; //$NON-NLS-1$
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
			EmailListEntity llistaEsborrar = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
			if (llistaEsborrar != null)
				getEmailListEntityDao().remove(llistaEsborrar);
		}

	}

	// és equivalent a handleDelete però no es fa neteja de llistes
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(LlistaCorreuUsuari llistaCorreuUsuari) throws Exception {
		UserEmailEntity llistaCorreuUsuariEntity = getUserEmailEntityDao().llistaCorreuUsuariToEntity(llistaCorreuUsuari);
		// Dades per fer feina...
		UserEntity usuariLlista = llistaCorreuUsuariEntity.getUser();

		// L'esborrem
		getUserEmailEntityDao().remove(llistaCorreuUsuariEntity);

		// Marquem l'usuari com a modificat
		updateUserModification(usuariLlista);
	}

	// és equivalent a handleDelete però no es fa neteja de llistes
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(CorreuExtern correuExtern) throws Exception {
		ExternEmailEntity correuExternEntity = getExternEmailEntityDao().correuExternToEntity(correuExtern);

		// Dades per fer feina...
		String alies = correuExtern.getLlistaCorreuNom();
		String domini = correuExtern.getCodiDomini();

		// L'esborrem
		getExternEmailEntityDao().remove(correuExternEntity);
	}

	// és equivalent a handleDelete però no es fa neteja de la llista continguda
	// perquè es puga emprar des dels WF
	protected void handleDeleteAtomic(RelacioLlistaCorreu relacioLlistaCorreu) throws Exception {
		EmailListContainerEntity relacioLlistaCorreuEntity = getEmailListContainerEntityDao().relacioLlistaCorreuToEntity(relacioLlistaCorreu);

		// Esborrem la relació entre llistes
		getEmailListContainerEntityDao().remove(relacioLlistaCorreuEntity);
	}

	@Override
	protected Collection<Grup> handleFindGroupMembers(String nomLlistaCorreu,
			String codiDomini) throws Exception {
		
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		List<Grup> grups = new LinkedList<Grup>();
		for (MailListGroupMemberEntity member : list.getGroups()) {
            grups.add(getGroupEntityDao().toGrup(member.getGroup()));
        }
		return grups;
	}

	@Override
	protected Collection<MailListRoleMember> handleFindRoleMembers(
			String nomLlistaCorreu, String codiDomini) throws Exception {
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(nomLlistaCorreu, codiDomini);
		return getMailListRoleMemberEntityDao().toMailListRoleMemberList(list.getRoles());
	}

	@Override
	protected void handleSubscribeGroup(String mailListName,
			String mailListDomain, String groupName) throws Exception {
		MailListGroupMemberEntity entity = getMailListGroupMemberEntityDao().newMailListGroupMemberEntity();
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		entity.setMailList(list);
		entity.setGroup(getGroupEntityDao().findByName(groupName));
		if (entity.getGroup() == null)
			throw new UnknownGroupException(groupName);
		getMailListGroupMemberEntityDao().create(entity);
	}

	@Override
	protected void handleUnsubscribeGroup(String mailListName,
			String mailListDomain, String groupName) throws Exception {
		
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		for (MailListGroupMemberEntity group : list.getGroups()) {
            if (group.getGroup().getName().equals(groupName)) getMailListGroupMemberEntityDao().remove(group);
        }	
		
	}

	@Override
	protected MailListRoleMember handleSubscribeRole(String mailListName,
			String mailListDomain, MailListRoleMember roleMember)
			throws Exception {
		MailListRoleMemberEntity entity = getMailListRoleMemberEntityDao().newMailListRoleMemberEntity();
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		entity.setMailList(list);
		RoleEntity role = getRoleEntityDao().findByNameAndSystem(roleMember.getRoleName(), roleMember.getDispatcherName());
		if (role == null)
			throw new UnknownRoleException(roleMember.getRoleName()+"@"+roleMember.getDispatcherName());
		entity.setRole(role);
		if (roleMember.getScope() != null && roleMember.getScope().length() > 0)
		{
			if (TipusDomini.APLICACIONS.equals(role.getDomainType()))
			{
				entity.setInformationSystemScope(getInformationSystemEntityDao().findByCode(roleMember.getScope()));
				if (entity.getInformationSystemScope() == null)
					throw new UnknownApplicationException(roleMember.getScope());
			}
			else if (TipusDomini.GRUPS.equals(role.getDomainType()) || TipusDomini.GRUPS_USUARI.equals(role.getDomainType()))
			{
				entity.setGroupScope(getGroupEntityDao().findByName(roleMember.getScope()));
				if (entity.getGroupScope() == null)
					throw new UnknownGroupException(roleMember.getScope());
			}
			if (TipusDomini.DOMINI_APLICACIO.equals(role.getDomainType()))
			{
				entity.setDomainValueScope(getDomainValueEntityDao().findByApplicationDomainValue(role.getInformationSystem().getName(), role.getApplicationDomain().getName(), roleMember.getScope()));
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
		EmailListEntity list = getEmailListEntityDao().findByNameAndDomain(mailListName, mailListDomain);
		if (list == null)
			throw new UnknownMailListException(mailListName+"@"+mailListDomain);
		for (MailListRoleMemberEntity member : list.getRoles()) {
            entity.setMailList(list);
            RoleEntity role = member.getRole();
            if (role.getName().equals(roleMember.getRoleName()) && role.getSystem().getName().equals(roleMember.getDispatcherName())) {
                if (roleMember.getScope() == null || roleMember.getScope().length() == 0) {
                    if (member.getDomainValueScope() == null && member.getGroupScope() == null && member.getInformationSystemScope() == null) {
                        getMailListRoleMemberEntityDao().remove(member);
                    }
                } else {
                    if (TipusDomini.APLICACIONS.equals(role.getDomainType()) && member.getInformationSystemScope() != null && member.getInformationSystemScope().getName().equals(roleMember.getScope())) {
                        getMailListRoleMemberEntityDao().remove(member);
                    } else if ((TipusDomini.GRUPS.equals(role.getDomainType()) || TipusDomini.GRUPS_USUARI.equals(role.getDomainType())) && member.getGroupScope() != null && member.getGroupScope().getName().equals(roleMember.getScope())) {
                        getMailListRoleMemberEntityDao().remove(member);
                    } else if (TipusDomini.DOMINI_APLICACIO.equals(role.getDomainType()) && member.getDomainValueScope() != null && member.getDomainValueScope().getValue().equals(roleMember.getScope())) {
                        getMailListRoleMemberEntityDao().remove(member);
                    }
                }
            }
        }
	}

}
