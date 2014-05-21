// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.CorreuExtern;
import es.caib.seycon.ng.comu.LlistaCorreu;
import es.caib.seycon.ng.comu.LlistaCorreuUsuari;
import es.caib.seycon.ng.comu.RelacioLlistaCorreu;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.servei.LlistesDeCorreuService;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.LlistaCorreuEntity
 */
public class LlistaCorreuEntityDaoImpl extends
		es.caib.seycon.ng.model.LlistaCorreuEntityDaoBase {

	private void auditarLlistaDeCorreu(String accio, String nomLlistaDeCorreu,
			String dominiLlistaCorreu) {
		String codiUsuari = Security.getCurrentAccount();
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setLlistaCorreu(nomLlistaDeCorreu);
		auditoria.setDominiCorreu(dominiLlistaCorreu);
		auditoria.setAutor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Messages.getString("LlistaCorreuEntityDaoImpl.dateFormat")); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_LLICOR"); //$NON-NLS-1$
		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}

	public void create(
			es.caib.seycon.ng.model.LlistaCorreuEntity llistaCorreu)
			throws RuntimeException {
		try {
			super.create(llistaCorreu);
			getSession(false).flush();
			String domini = llistaCorreu.getDomini() == null ? null
					: llistaCorreu.getDomini().getCodi();
			auditarLlistaDeCorreu("C", llistaCorreu.getNom(), domini); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuEntityDaoImpl.4"),  //$NON-NLS-1$
					llistaCorreu.getNom(),
					message));
		}
	}

	public void update(es.caib.seycon.ng.model.LlistaCorreuEntity llistaCorreu)
			throws RuntimeException {
		try {
			super.update(llistaCorreu);
			getSession(false).flush();
			String domini = llistaCorreu.getDomini() == null ? null
					: llistaCorreu.getDomini().getCodi();
			auditarLlistaDeCorreu("U", llistaCorreu.getNom(), domini); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuEntityDaoImpl.6"), //$NON-NLS-1$
					llistaCorreu.getNom(), 
					message));
		}
	}

	public void remove(es.caib.seycon.ng.model.LlistaCorreuEntity llistaCorreu)
			throws RuntimeException {
		try {
			String nomLlistaDeCorreu = llistaCorreu.getNom();
			String domini = llistaCorreu.getDomini() == null ? null
					: llistaCorreu.getDomini().getCodi();
			super.remove(llistaCorreu);
			getSession(false).flush();
			auditarLlistaDeCorreu("D", nomLlistaDeCorreu, domini); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("LlistaCorreuEntityDaoImpl.8"),  //$NON-NLS-1$
					llistaCorreu.getNom(),
					message));
		}
	}

	private String findLlistaCompactaExternsByNomLlistaCorreuAndCodiDomini(
			String nomLlistaCorreu, String codiDomini) {
		String llistat = ""; //$NON-NLS-1$
		LlistaCorreuEntity llistaCorreuEntity = this.findByNomAndCodiDomini(
				nomLlistaCorreu, codiDomini);
		Collection correusExterns = llistaCorreuEntity.getExterns();
		if (correusExterns != null) {
			Iterator iterator = correusExterns.iterator();
			while (iterator.hasNext()) {
				CorreuExternEntity correuExtern = (CorreuExternEntity) iterator
						.next();
				llistat += correuExtern.getAdreca() + ", "; //$NON-NLS-1$
			}
		}
		if (llistat == "") { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	private String findLlistaCompactaLlistesByNomLlistaCorreuAndCodiDomini(
			String nomLlistaCorreu, String codiDomini) {
		String llistat = ""; //$NON-NLS-1$
		Collection llistesCorreuEntities = getRelacioLlistaCorreuEntityDao()
				.findCollectionByNomConteAndCodiDomini(nomLlistaCorreu,
						codiDomini);
		if (llistesCorreuEntities != null) {
			Iterator iterator = llistesCorreuEntities.iterator();
			while (iterator.hasNext()) {
				RelacioLlistaCorreuEntity relacioLlistaCorreuEntity = (RelacioLlistaCorreuEntity) iterator
						.next();
				LlistaCorreuEntity llistaCorreuEntityPertany = relacioLlistaCorreuEntity
						.getPertany();
				String codiDominiCurrent = llistaCorreuEntityPertany
						.getDomini() == null ? null : llistaCorreuEntityPertany
						.getDomini().getCodi();
				llistat += llistaCorreuEntityPertany.getNom() + "@" //$NON-NLS-1$
						+ codiDominiCurrent + ", "; //$NON-NLS-1$
			}
		}
		if (llistat == "") { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	private String findLlistaCompactaUsuarisByNomLlistaCorreuAndCodiDomini(
			String nomLlistaCorreu, String codiDomini) {
		String llistat = ""; //$NON-NLS-1$
		LlistaCorreuEntity llistaCorreuEntity = this.findByNomAndCodiDomini(
				nomLlistaCorreu, codiDomini);
		Collection llistaCorreuUsuaris = llistaCorreuEntity
				.getLlistaDeCorreuUsuari();
		if (llistaCorreuUsuaris != null) {
			Iterator iterator = llistaCorreuUsuaris.iterator();
			while (iterator.hasNext()) {
				LlistaCorreuUsuariEntity llistaCorreuUsuari = (LlistaCorreuUsuariEntity) iterator
						.next();
				llistat += llistaCorreuUsuari.getUsuari().getCodi() + ", "; //$NON-NLS-1$
			}
		}
		if (llistat == "") { //$NON-NLS-1$
			return llistat;
		} else {
			return llistat.substring(0, llistat.length() - 2);
		}
	}

	public void toLlistaCorreu(
			es.caib.seycon.ng.model.LlistaCorreuEntity sourceEntity,
			es.caib.seycon.ng.comu.LlistaCorreu targetVO) {
		// @todo verify behavior of toLlistaCorreu
		super.toLlistaCorreu(sourceEntity, targetVO);
		toLlistaCorreuCustom(sourceEntity, targetVO);
	}

	void toLlistaCorreuCustom(
			es.caib.seycon.ng.model.LlistaCorreuEntity sourceEntity,
			es.caib.seycon.ng.comu.LlistaCorreu targetVO) {

		DominiCorreuEntity domini = sourceEntity.getDomini();
		if (domini != null) {
			targetVO.setCodiDomini(domini.getCodi());
		}

		String nomLlista = sourceEntity.getNom();
		String codiDomini = sourceEntity.getDomini() == null ? null
				: sourceEntity.getDomini().getCodi();
		targetVO
				.setLlistaLlistes(findLlistaCompactaLlistesByNomLlistaCorreuAndCodiDomini(
						nomLlista, codiDomini));
		targetVO
				.setLlistaUsuaris(findLlistaCompactaUsuarisByNomLlistaCorreuAndCodiDomini(
						nomLlista, codiDomini));
		targetVO
				.setLlistaExterns(findLlistaCompactaExternsByNomLlistaCorreuAndCodiDomini(
						nomLlista, codiDomini));
		Collection col_llistesPertany = sourceEntity.getRelacioLlistaCorreuFromPertany();
		String llistesPertany = ""; //$NON-NLS-1$
		if (col_llistesPertany != null) for (Iterator it = col_llistesPertany.iterator(); it.hasNext(); ) {
			RelacioLlistaCorreuEntity rel = (RelacioLlistaCorreuEntity) it.next();
			LlistaCorreuEntity pertanyA = rel.getConte(); //la que conté a la llista actual
			String codiDominiCurrent = pertanyA.getDomini() == null ? null : pertanyA.getDomini().getCodi();
			llistesPertany += pertanyA.getNom() + "@" + codiDominiCurrent + ", "; //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (llistesPertany == "") {//Llevem coma final //$NON-NLS-1$
			targetVO.setLlistaLlistesOnPertany(llistesPertany);
		} else {
			targetVO.setLlistaLlistesOnPertany(llistesPertany.substring(0, llistesPertany.length() - 2));
		}

	}

	/**
	 * @see es.caib.seycon.ng.model.LlistaCorreuEntityDao#toLlistaCorreu(es.caib.seycon.ng.model.LlistaCorreuEntity)
	 */
	public es.caib.seycon.ng.comu.LlistaCorreu toLlistaCorreu(
			final es.caib.seycon.ng.model.LlistaCorreuEntity entity) {
		LlistaCorreu llistaCorreu = super.toLlistaCorreu(entity);
		return llistaCorreu;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.LlistaCorreuEntity loadLlistaCorreuEntityFromLlistaCorreu(
			es.caib.seycon.ng.comu.LlistaCorreu llistaCorreu) {
		es.caib.seycon.ng.model.LlistaCorreuEntity llistaCorreuEntity = null;
		if (llistaCorreu.getId() != null) {
			llistaCorreuEntity = load(llistaCorreu.getId());
		}
		if (llistaCorreuEntity == null) {
			llistaCorreuEntity = newLlistaCorreuEntity();
		}
		return llistaCorreuEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.LlistaCorreuEntityDao#llistaCorreuToEntity(es.caib.seycon.ng.comu.LlistaCorreu)
	 */
	public es.caib.seycon.ng.model.LlistaCorreuEntity llistaCorreuToEntity(
			es.caib.seycon.ng.comu.LlistaCorreu llistaCorreu) {
		es.caib.seycon.ng.model.LlistaCorreuEntity entity = this
				.loadLlistaCorreuEntityFromLlistaCorreu(llistaCorreu);
		this.llistaCorreuToEntity(llistaCorreu, entity, true);
		return entity;
	}

	private void llistaCorreuToEntityCustom(
			es.caib.seycon.ng.comu.LlistaCorreu sourceVO,
			es.caib.seycon.ng.model.LlistaCorreuEntity targetEntity) {
		String codiDomini = sourceVO.getCodiDomini();
		if (codiDomini != null && codiDomini.trim().compareTo("") != 0) { //$NON-NLS-1$
			DominiCorreuEntity dominiCorreu = getDominiCorreuEntityDao()
					.findByCodi(codiDomini);
			if (dominiCorreu != null) {
				if (sourceVO.getCodiDomini() != null
						&& (targetEntity.getDomini() == null || sourceVO
								.getCodiDomini().compareTo(
										targetEntity.getDomini().getCodi()) != 0)
						&& dominiCorreu.getObsolet() != null
						&& dominiCorreu.getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
					throw new SeyconException(String.format(Messages.getString("LlistaCorreuEntityDaoImpl.obsoleteError"),  //$NON-NLS-1$
							sourceVO.getCodiDomini()));
				}else{
					targetEntity.setDomini(dominiCorreu);
				}
			} else {
				throw new SeyconException(String.format(Messages.getString("LlistaCorreuEntityDaoImpl.unknownError"), codiDomini)); //$NON-NLS-1$
			}
		} else {
			targetEntity.setDomini(null);
		}
		
		// correus externs
		/*
		 * String llistaExterns = sourceVO.getLlistaExterns(); Collection
		 * externs = targetEntity.getExterns(); if (externs != null) { Iterator
		 * iterator = externs.iterator(); while (iterator.hasNext()) {
		 * CorreuExternEntity correuExtern = (CorreuExternEntity) iterator
		 * .next(); getCorreuExternEntityDao().remove(correuExtern); } } if
		 * (llistaExterns != null && llistaExterns.trim().compareTo("") != 0) {
		 * String[] arrayExterns = llistaExterns.split(","); for (int i = 0; i <
		 * arrayExterns.length; i++) { String correuExtern = arrayExterns[i];
		 * correuExtern = correuExtern.trim(); CorreuExtern correuExternVO = new
		 * CorreuExtern(); correuExternVO.setAdreca(correuExtern);
		 * correuExternVO.setLlistaCorreuNom(sourceVO.getNom());
		 * correuExternVO.setCodiDomini(sourceVO.getCodiDomini());
		 * CorreuExternEntity correuExternEntity = getCorreuExternEntityDao()
		 * .correuExternToEntity(correuExternVO);
		 * getCorreuExternEntityDao().create(correuExternEntity); } }
		 */

		// llistes de correu
		/*
		 * String llistaCorreus = sourceVO.getLlistaLlistes(); Collection
		 * llistesDeCorreus = targetEntity .getRelacioLlistaCorreuFromConte();
		 * if (llistesDeCorreus != null) { Iterator iterator =
		 * llistesDeCorreus.iterator(); while (iterator.hasNext()) {
		 * RelacioLlistaCorreuEntity realacioLlistaCorreu =
		 * (RelacioLlistaCorreuEntity) iterator .next();
		 * this.getRelacioLlistaCorreuEntityDao().remove( realacioLlistaCorreu); } }
		 * if (llistaCorreus != null && llistaCorreus.trim().compareTo("") != 0) {
		 * String[] arrayLlistesCorreu = llistaCorreus.split(","); for (int i =
		 * 0; i < arrayLlistesCorreu.length; i++) { String llistaCorreu =
		 * arrayLlistesCorreu[i]; llistaCorreu = llistaCorreu.trim(); String[]
		 * nomDomini = llistaCorreu.split("@"); String nomLlista = nomDomini[0];
		 * String codiDominiLlista = null; if (nomDomini.length > 1) {
		 * codiDominiLlista = nomDomini[1]; } RelacioLlistaCorreu llistaCorreuVO =
		 * new RelacioLlistaCorreu();
		 * llistaCorreuVO.setNomLlistaCorreuPertany(nomLlista);
		 * llistaCorreuVO.setNomLlistaCorreuConte(sourceVO.getNom());
		 * llistaCorreuVO.setCodiDominiCorreuPertany(codiDominiLlista);
		 * llistaCorreuVO.setCodiDominiCorreuConte(sourceVO .getCodiDomini());
		 * RelacioLlistaCorreuEntity relacioLlistaCorreuEntity =
		 * getRelacioLlistaCorreuEntityDao()
		 * .relacioLlistaCorreuToEntity(llistaCorreuVO);
		 * getRelacioLlistaCorreuEntityDao().create( relacioLlistaCorreuEntity); } }
		 */

		// llistes d'usuaris
		/*
		 * String llistaUsuaris = sourceVO.getLlistaUsuaris(); Collection
		 * llistesDUsuaris = targetEntity.getLlistaDeCorreuUsuari(); if
		 * (llistesDUsuaris != null) { Iterator iterator =
		 * llistesDUsuaris.iterator(); while (iterator.hasNext()) {
		 * LlistaCorreuUsuariEntity llistaCorreuUsuariEntity =
		 * (LlistaCorreuUsuariEntity) iterator .next();
		 * getLlistaCorreuUsuariEntityDao().remove( llistaCorreuUsuariEntity); } }
		 * if (llistaUsuaris != null && llistaUsuaris.trim().compareTo("") != 0) {
		 * String[] arrayLlistesCorreu = llistaUsuaris.split(","); for (int i =
		 * 0; i < arrayLlistesCorreu.length; i++) { String codiUsuari =
		 * arrayLlistesCorreu[i].trim(); LlistaCorreuUsuari llistaCorreuUsuariVO =
		 * new LlistaCorreuUsuari();
		 * llistaCorreuUsuariVO.setCodiDomini(sourceVO.getCodiDomini());
		 * llistaCorreuUsuariVO.setNomLlistaCorreu(sourceVO.getNom());
		 * llistaCorreuUsuariVO.setCodiUsuari(codiUsuari);
		 * LlistaCorreuUsuariEntity llistaCorreuUsuariEntity =
		 * getLlistaCorreuUsuariEntityDao()
		 * .llistaCorreuUsuariToEntity(llistaCorreuUsuariVO);
		 * getLlistaCorreuUsuariEntityDao().create(llistaCorreuUsuariEntity); } }
		 */

	}

	/**
	 * @see es.caib.seycon.ng.model.LlistaCorreuEntityDao#llistaCorreuToEntity(es.caib.seycon.ng.comu.LlistaCorreu,
	 *      es.caib.seycon.ng.model.LlistaCorreuEntity)
	 */
	public void llistaCorreuToEntity(
			es.caib.seycon.ng.comu.LlistaCorreu sourceVO,
			es.caib.seycon.ng.model.LlistaCorreuEntity targetEntity,
			boolean copyIfNull) {
		// @todo verify behavior of llistaCorreuToEntity
		super.llistaCorreuToEntity(sourceVO, targetEntity, copyIfNull);
		llistaCorreuToEntityCustom(sourceVO, targetEntity);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof LlistaCorreuEntity) {
				LlistaCorreuEntity entity = (LlistaCorreuEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof LlistaCorreuEntity) {
				LlistaCorreuEntity entity = (LlistaCorreuEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof LlistaCorreuEntity) {
				LlistaCorreuEntity entity = (LlistaCorreuEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}

}