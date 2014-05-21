// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.GrupEntity
 */
public class GrupEntityDaoImpl extends
		es.caib.seycon.ng.model.GrupEntityDaoBase {

	private void auditarGrup(String accio, String codiGrup) {
		String codiUsuari = Security.getCurrentAccount();
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setGrup(codiGrup);
		auditoria.setAutor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_GRUP"); //$NON-NLS-1$
		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}

	public void create(es.caib.seycon.ng.model.GrupEntity grup)
			throws RuntimeException {
		try {
			super.create(grup);
			getSession(false).flush();
			
			// PROPAGAMOS LOS ROLES OTORGADOS A LOS GRUPOS PADRE 
			//  el grupo todavía no existe: no tiene roles otorgados
			if (grup.getPare()!=null) {
				// Herencia de Roles: propagamos los roles heredados por el grupo (y de sus padres)
				HashSet rolsAPropagar =new HashSet();
				Collection rolsAtorgatsGrupIPares = getRolsAtorgatsGrupIParesGrup(grup);
				if (rolsAtorgatsGrupIPares!=null) rolsAPropagar.addAll(rolsAtorgatsGrupIPares);
				// Propagamos los roles: (creamos las tareas)
				propagarRolsAtorgatsGrups(rolsAPropagar);						
			}
			
                        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
                        tasque.setData(new Timestamp(System.currentTimeMillis()));
                        tasque.setTransa(TaskHandler.UPDATE_GROUP);
                        tasque.setGrup(grup.getCodi());
                        getTasqueEntityDao().create(tasque);
                        if (grup.getServidorOfimatic() != null) {
                            tasque = getTasqueEntityDao().newTasqueEntity();
                            tasque.setData(new Timestamp(System.currentTimeMillis()));
                            tasque.setTransa(TaskHandler.CREATE_FOLDER);
                            tasque.setCarpet(grup.getCodi());
                            tasque.setTipcar("G"); //$NON-NLS-1$
                            getTasqueEntityDao().create(tasque);
                        }
			auditarGrup("C", grup.getCodi()); //$NON-NLS-1$
                        getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("GrupEntityDaoImpl.0"),  //$NON-NLS-1$
					grup.getCodi(), 
					message));
		}
	}

	public void remove(es.caib.seycon.ng.model.GrupEntity grup)
			throws RuntimeException { //En principi NO ES FA MAI
		try {
			String codiGrup = grup.getCodi();
			super.remove(grup);
			getSession(false).flush();
			
			// Herencia de Roles: propagamos los roles heredados por el grupo (y de sus padres)
			HashSet rolsAPropagar =new HashSet();
			Collection rolsAtorgatsGrupIPares = getRolsAtorgatsGrupIParesGrup(grup);
			if (rolsAtorgatsGrupIPares!=null) rolsAPropagar.addAll(rolsAtorgatsGrupIPares);
			// Propagamos los roles: (creamos las tareas)
			propagarRolsAtorgatsGrups(rolsAPropagar);						

                        TasqueEntity tasque;
                        tasque = getTasqueEntityDao().newTasqueEntity();
                        tasque.setData(new Timestamp(System.currentTimeMillis()));
                        tasque.setTransa(TaskHandler.UPDATE_GROUP);
                        tasque.setGrup(grup.getCodi());
                        getTasqueEntityDao().create(tasque);
                        if (grup.getServidorOfimatic() != null) {
                            tasque = getTasqueEntityDao().newTasqueEntity();
                            tasque.setData(new Timestamp(System.currentTimeMillis()));
                            tasque.setTransa(TaskHandler.CREATE_FOLDER);
                            tasque.setCarpet(grup.getCodi());
                            tasque.setTipcar("G"); //$NON-NLS-1$
                            getTasqueEntityDao().create(tasque);
                        }
			auditarGrup("D", codiGrup); //$NON-NLS-1$
			getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("GrupEntityDaoImpl.1"),  //$NON-NLS-1$
					grup.getCodi(), 
					message));
		}
	}

	public void update(es.caib.seycon.ng.model.GrupEntity grup)
			throws RuntimeException {
		try {
			// Buscamos el padre anterior (para ver si cambia)
			GrupEntity old = load(grup.getId());
			GrupEntity pareGrupAbansUpdate = old.getPare();
			Collection pares = new HashSet();
			pares.add(pareGrupAbansUpdate);
			pares.add(grup.getPare());
			
			// Actualizamos al grupo
			super.update(grup);
			getSession(false).flush();
			
			// PROPAGAMOS LOS ROLES OTORGADOS A LOS PADRES DEL GRUPO
			// Si n'hi ha més d'un pare: ha canviat
			// O si només hi ha un pare (i és nou o ha desaparegut)
			if (pares.size() > 1
					|| (pareGrupAbansUpdate == null && grup.getPare() != null)
					|| (pareGrupAbansUpdate != null && grup.getPare() == null)) {

				// Herencia de Roles: propagamos los roles heredados por el grupo (y de sus padres)
				HashSet rolsAPropagar =new HashSet();
				for (Iterator it = pares.iterator(); it.hasNext(); ) {
					Object obj = it.next();
					if (obj != null) {
						GrupEntity g = (GrupEntity) obj;
						Collection rolsAtorgatsGrupIPares = getRolsAtorgatsGrupIParesGrup(g);
						if (rolsAtorgatsGrupIPares!=null) rolsAPropagar.addAll(rolsAtorgatsGrupIPares);

					}
				}
				// Propagamos los ROLES (del grupo padre antiguo y nuevo): (creamos las tareas)
				propagarRolsAtorgatsGrups(rolsAPropagar);
				// Propagamos LOS USUARIOS: de este grupo
				Collection usuarisPropagar = getUsuarisPertanyenGrup(grup.getCodi());
				if (usuarisPropagar!=null) {
					propagarUsuarisGrup(usuarisPropagar);
				}
				
				
			}
                        TasqueEntity tasque;
                        tasque = getTasqueEntityDao().newTasqueEntity();
                        tasque.setData(new Timestamp(System.currentTimeMillis()));
                        tasque.setTransa(TaskHandler.UPDATE_GROUP);
                        tasque.setGrup(grup.getCodi());
                        getTasqueEntityDao().create(tasque);
                        if (grup.getServidorOfimatic() != null ? 
                                old.getServidorOfimatic() == null || 
                                    !old.getServidorOfimatic().getId().equals(grup.getServidorOfimatic().getId()) :
                                old.getServidorOfimatic() != null) {
                            tasque = getTasqueEntityDao().newTasqueEntity();
                            tasque.setData(new Timestamp(System.currentTimeMillis()));
                            tasque.setTransa(TaskHandler.CREATE_FOLDER);
                            tasque.setCarpet(grup.getCodi());
                            tasque.setTipcar("G"); //$NON-NLS-1$
                            getTasqueEntityDao().create(tasque);
                        }
			auditarGrup("U", grup.getCodi()); //$NON-NLS-1$
			getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("GrupEntityDaoImpl.2"),  //$NON-NLS-1$
					grup.getCodi(), 
					message));
		}
	}

	public void toGrup(es.caib.seycon.ng.model.GrupEntity sourceEntity,
			es.caib.seycon.ng.comu.Grup targetVO) {
		super.toGrup(sourceEntity, targetVO);
		toGrupCustom(targetVO, sourceEntity);
	}

	private void toGrupCustom(Grup grup, final GrupEntity entity) {
		String organitzatiu = entity.getOrganitzatiu();
		if (organitzatiu != null && organitzatiu.trim().compareTo("") != 0) { //$NON-NLS-1$
			grup.setOrganitzatiu(new Boolean(organitzatiu.trim()
					.compareToIgnoreCase("S") == 0 ? true : false)); //$NON-NLS-1$
		} else {
			grup.setOrganitzatiu(new Boolean(false));
		}

		GrupEntity grupPare = entity.getPare();
		if (grupPare != null) {
			String codiPare = grupPare.getCodi();
			grup.setCodiPare(codiPare);
		}

		MaquinaEntity servidorOfimatic = entity.getServidorOfimatic();
		if (servidorOfimatic != null) {
			String nomServidorOfimatic = servidorOfimatic.getNom();
			grup.setNomServidorOfimatic(nomServidorOfimatic);
		}
		

		GrupEntity grupEntity = entity; //findByCodi(entity.getCodi()); //¿para qué lo cargamos si lo tenemos?
		TipusUnitatOrganitzativaEntity tipusUnitatOrganitzativa = grupEntity
				.getTipusUnitatOrganizativa();
		if (tipusUnitatOrganitzativa != null) {
			String codiTipus = tipusUnitatOrganitzativa.getCodi();
			grup.setTipus(codiTipus); // Unitat Organizativa
		} else {
			grup.setTipus(null); // Unitat Organizativa
		}

		String obsolet = entity.getObsolet();
		if (obsolet != null && obsolet.compareTo("") != 0) { //$NON-NLS-1$
			grup.setObsolet(obsolet.compareTo("S") == 0 ? new Boolean(true) //$NON-NLS-1$
					: new Boolean(false));
		} else {
			grup.setObsolet(new Boolean(false));
		}

		if (entity.getQuotaGrup() != null) {
			grup.setQuota(String.valueOf(entity.getQuotaGrup().longValue()));
		} else {
			grup.setQuota("0"); //$NON-NLS-1$
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#toGrup(es.caib.seycon.ng.model.GrupEntity)
	 */
	public es.caib.seycon.ng.comu.Grup toGrup(
			final es.caib.seycon.ng.model.GrupEntity entity) {
		Grup grup = super.toGrup(entity);
		toGrupCustom(grup, entity);
		return grup;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.GrupEntity loadGrupEntityFromGrup(
			es.caib.seycon.ng.comu.Grup grup) {
		es.caib.seycon.ng.model.GrupEntity grupEntity = null;
		if (grup.getId() != null) {
			grupEntity = load(grup.getId());
		}
		if (grupEntity == null) {
			grupEntity = newGrupEntity();
		}
		return grupEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#grupToEntity(es.caib.seycon.ng.comu.Grup)
	 */
	public es.caib.seycon.ng.model.GrupEntity grupToEntity(
			es.caib.seycon.ng.comu.Grup grup) {
		es.caib.seycon.ng.model.GrupEntity entity = this
				.loadGrupEntityFromGrup(grup);
		this.grupToEntity(grup, entity, true);
		return entity;
	}

	private void grupToEntityCustom(es.caib.seycon.ng.comu.Grup sourceVO,
			es.caib.seycon.ng.model.GrupEntity targetEntity) {
		Boolean organitzatiu = sourceVO.getOrganitzatiu();
		if (organitzatiu != null) {
			targetEntity.setOrganitzatiu(organitzatiu.booleanValue() ? "S" //$NON-NLS-1$
					: "N"); //$NON-NLS-1$
		}else{
			targetEntity.setOrganitzatiu("N"); //$NON-NLS-1$
		}

		String codiPare = sourceVO.getCodiPare();
		if (codiPare != null && codiPare.trim().compareTo("") != 0) { //$NON-NLS-1$
			GrupEntity grupPare = findByCodi(sourceVO.getCodiPare());
			if (grupPare == null) {
				throw new SeyconException(String.format(Messages.getString("GrupEntityDaoImpl.3"), codiPare)); //$NON-NLS-1$
			} else {
				if (grupPare.getObsolet() != null
						&& grupPare.getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
					throw new SeyconException(
							Messages.getString("GrupEntityDaoImpl.4")); //$NON-NLS-1$
				} else {
					targetEntity.setPare(grupPare);
				}
			}
		} else {
			targetEntity.setPare(null);
		}

		String nomServidorOfimatic = sourceVO.getNomServidorOfimatic();
		if (nomServidorOfimatic != null
				&& nomServidorOfimatic.trim().compareTo("") != 0) { //$NON-NLS-1$
			MaquinaEntity servidorOfimatic = getMaquinaEntityDao().findByNom(
					nomServidorOfimatic);
			if (servidorOfimatic != null) {
				targetEntity.setServidorOfimatic(servidorOfimatic);
			} else {
				throw new SeyconException(String.format(Messages.getString("GrupEntityDaoImpl.5"),  //$NON-NLS-1$
						nomServidorOfimatic));
			}
		} else {
			targetEntity.setServidorOfimatic(null);
		}

		String codiTipus = sourceVO.getTipus(); // Unitat Organizativa
		if (codiTipus != null && codiTipus.trim().compareTo("") != 0) { //$NON-NLS-1$
			TipusUnitatOrganitzativaEntity tipusEntity = getTipusUnitatOrganitzativaEntityDao()
					.findByCodi(codiTipus);
			if (tipusEntity != null) {
				targetEntity.setTipusUnitatOrganizativa(tipusEntity);
			} else {
				throw new SeyconException(String.format(Messages.getString("GrupEntityDaoImpl.6"),  //$NON-NLS-1$
						codiTipus));
			}
		} else {
			targetEntity.setTipusUnitatOrganizativa(null);
		}

		if (sourceVO.getObsolet() != null) {
			targetEntity.setObsolet(sourceVO.getObsolet().booleanValue() ? "S" //$NON-NLS-1$
					: "N"); //$NON-NLS-1$
		} else {
			targetEntity.setObsolet("N"); //$NON-NLS-1$
		}

		try {
			if (sourceVO.getQuota() != null
					&& sourceVO.getQuota().compareTo("") != 0) { //$NON-NLS-1$
				targetEntity.setQuotaGrup(Long.valueOf(sourceVO.getQuota()));
			} else {
				targetEntity.setQuotaGrup(new Long(0));
			}
		} catch (Exception e) {
			throw new SeyconException(String.format(Messages.getString("GrupEntityDaoImpl.7"),  //$NON-NLS-1$
					sourceVO.getQuota()));
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#grupToEntity(es.caib.seycon.ng.comu.Grup,
	 *      es.caib.seycon.ng.model.GrupEntity)
	 */
	public void grupToEntity(es.caib.seycon.ng.comu.Grup sourceVO,
			es.caib.seycon.ng.model.GrupEntity targetEntity, boolean copyIfNull) {
		super.grupToEntity(sourceVO, targetEntity, copyIfNull);
		grupToEntityCustom(sourceVO, targetEntity);
	}

	protected void handleSetSuperGrup(String codiSubGrup, String codiSuperGrup)
			throws Exception {
		GrupEntity superGrup = findByCodi(codiSuperGrup);
		GrupEntity subGrup = findByCodi(codiSubGrup);
		/*
		 * No crea ciclo: desde el padre no se puede llegar al hijo
		 */
		GrupEntity pareGrup = superGrup; //comencem pel nou pare
		while (pareGrup != null) {
			if (pareGrup.getCodi().compareTo(codiSubGrup) == 0) {
				throw new Exception(String.format(Messages.getString("GrupEntityDaoImpl.8"),  //$NON-NLS-1$
						codiSuperGrup, 
						codiSubGrup));
			}
			pareGrup = pareGrup.getPare();
		}

		// Afegim com a pare del subgrup el supergrup
		subGrup.setPare(superGrup);
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#toIdentitat(es.caib.seycon.ng.model.GrupEntity,
	 *      es.caib.seycon.ng.comu.Identitat)
	 */
	public void toIdentitat(es.caib.seycon.ng.model.GrupEntity source,
			es.caib.seycon.ng.comu.Identitat target) {
		super.toIdentitat(source, target);
		toIdentitatCustom(source, target);
	}

	public void toIdentitatCustom(es.caib.seycon.ng.model.GrupEntity source,
			es.caib.seycon.ng.comu.Identitat target) {
		String codiGrup = source.getCodi();
		target.setCodiGrup(codiGrup);
		target.setCodiIdentitat(codiGrup);
		String descripcio = source.getDescripcio();
		target.setDescripcio(descripcio);
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#toIdentitat(es.caib.seycon.ng.model.GrupEntity)
	 */
	public es.caib.seycon.ng.comu.Identitat toIdentitat(
			final es.caib.seycon.ng.model.GrupEntity entity) {
		Identitat identitat = super.toIdentitat(entity);
		toIdentitatCustom(entity, identitat);
		return identitat;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.GrupEntity loadGrupEntityFromIdentitat(
			es.caib.seycon.ng.comu.Identitat identitat) {
		/*
		 * La identitat és read only
		 */
		String codiGrup = identitat.getCodiGrup();
		if (codiGrup != null) {
			GrupEntity grupEntity = findByCodi(codiGrup);
			if (grupEntity != null) {
				return grupEntity;
			} else {
				throw new SeyconException(String.format(Messages.getString("GrupEntityDaoImpl.9"),  //$NON-NLS-1$
						codiGrup));
			}
		}
		throw new SeyconException(Messages.getString("GrupEntityDaoImpl.10")); //$NON-NLS-1$
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat)
	 */
	public es.caib.seycon.ng.model.GrupEntity identitatToEntity(
			es.caib.seycon.ng.comu.Identitat identitat) {
		// @todo verify behavior of identitatToEntity
		es.caib.seycon.ng.model.GrupEntity entity = this
				.loadGrupEntityFromIdentitat(identitat);
		this.identitatToEntity(identitat, entity, true);
		return entity;
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat,
	 *      es.caib.seycon.ng.model.GrupEntity)
	 */
	public void identitatToEntity(es.caib.seycon.ng.comu.Identitat source,
			es.caib.seycon.ng.model.GrupEntity target, boolean copyIfNull) {
		super.identitatToEntity(source, target, copyIfNull);
	}

	public GrupEntity valorDominiToEntity(ValorDomini valorDomini) {
		// els tipus domini son read-only
		return null;
	}

	public ValorDomini toValorDomini(GrupEntity entity) {
		ValorDomini valorDomini = super.toValorDomini(entity);
		valorDomini.setDescripcio(entity.getDescripcio());
		// pot ser GRUPS o GRUPS_USUARI
		// domini service ho emplena
		valorDomini.setNomDomini(null);
		valorDomini.setValor(entity.getCodi());
		// encara no es sap l'usuari del grup si aquest és GRUPS_USUARI
		valorDomini.setCodiExternDomini(null);
		return valorDomini;
	}

	/**
	 * @see es.caib.seycon.ng.model.UsuariEntityDao#find(int, java.lang.String,
	 *      es.caib.seycon.ng.model.Parameter[])
	 */
	public List find(final java.lang.String queryString,
			final es.caib.seycon.ng.model.Parameter[] parameters) {
		try {
			java.util.List results = new QueryBuilder().query(this,
					queryString, parameters);
			return results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}
	

	/* HERENCIA DE ROLES  */
	
	/**
	 * ATORGACIO DE ROLS
	 * @param grupAnalitzar
	 * @return
	 */
	private Collection getParesGrup(GrupEntity grupAnalitzar) {

		Collection totsPares = new HashSet();
		GrupEntity pare = grupAnalitzar.getPare();
		while (pare !=null) {
			totsPares.add(pare);
			pare = pare.getPare();
		}

		return totsPares;
	}
	
	/**
	 * ATORGACIO DE ROLS
	 * @param rol
	 * @return
	 */
	private Collection getRolsContingutsPerPropagar (RolEntity rol) {
		// Si rol té atorgats d'altres rols (és contenidor del rols) 
		// s'han de propagar tots els rols que conté (per assignar-lo a l'usuari)
		HashSet rolsPropagar = new HashSet();
		// Sólo hemos de propagar a los usuarios que tienen el rol contenedor 
		// con valor de dominio correspondiente (o si es SENSE_DOMINI o a qualque valor)
		// Montamos un FIFO De roles (puede haber cadena de 
		// herencia A atorgat B[sense domini] atorgat C ... atorgat Z[amb domini]
		LinkedList rolsAnalitzar = new LinkedList(); // FIFO
		rolsAnalitzar.add(rol);
		RolEntity rolActual = null;
		while ( (rolActual = (RolEntity) rolsAnalitzar.poll()) !=null) {
			Collection socContenidor = rolActual.getRolAssociacioRolSocContenidor();
				
			if (socContenidor!=null) for (Iterator it = socContenidor.iterator(); it.hasNext();) {
				RolAssociacioRolEntity associacio = (RolAssociacioRolEntity) it.next();
				// Obtenemos el rol contenido
				RolEntity rolContingut = associacio.getRolContingut();
				// Guardamos el rol para propagarlo
				rolsPropagar.add(rolContingut); 
				// Añadimos el rol contenido para analizar si a su vez es contenedor de otro rol (el té atorgat)
				rolsAnalitzar.add(rolContingut);
			}
		}
		return rolsPropagar;
	}		
	
	/**
	 * ATORGACIÓ DE ROLS
	 * Obtiene dado un grupo, los roles otorgados al grupo (y
	 * los roles otorgados a los padres del grupo indicado)
	 * @param grup
	 * @return
	 */
	private Collection getRolsAtorgatsGrupIParesGrup (GrupEntity grup) {
		
		// 1) Obtenim els grups pares del grup 
		HashSet totGrup = new HashSet();
		totGrup.add(grup);
		Collection paresGrup = getParesGrup(grup);
		totGrup.addAll(paresGrup);
		
		// 2) Obtenim els rols atorgats al grup i els grups pare
		HashSet totRolAtorgatGrup = new HashSet();
		for (Iterator it = totGrup.iterator(); it.hasNext(); ) {
			Object obj = it.next();
			if (obj != null) {
				GrupEntity g = (GrupEntity) obj;
				Collection rolsAtorgatsG = g.getRolsOtorgatsGrup();
				if (rolsAtorgatsG!=null) totRolAtorgatGrup.addAll(rolsAtorgatsG);
			}
		}
		
		// 3) Obtenim els rols atorgats als rols:
		HashSet rolsPropagar = new HashSet();
		for (Iterator it = totRolAtorgatGrup.iterator(); it.hasNext(); ) {
			Object obj = it.next();
			if (obj !=null) {
				RolsGrupEntity rolgrup = (RolsGrupEntity) obj;
				// Añadimos el rol actual para propagarlo junto a sus hijos:
				rolsPropagar.add(rolgrup.getRolOtorgat());
				// Miramos si tiene roles otorgados
				Collection rolsAtorgatsRol = getRolsContingutsPerPropagar(rolgrup.getRolOtorgat());
				if (rolsAtorgatsRol!=null) rolsPropagar.addAll(rolsAtorgatsRol);
			}
		}
		
		return new ArrayList(rolsPropagar);		
	}
	
	/**
	 * Atorgació de rols: Propaga els rols indicats 
	 * @param rolsPropagar
	 */
	private void propagarRolsAtorgatsGrups(Collection rolsPropagar) {
		// Propaguem els rols
		if (rolsPropagar != null) {
			for (Iterator it = rolsPropagar.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj != null) {
					RolEntity role = (RolEntity) obj;
					// insert into sc_tasque
					// (tas_id,tas_role,tas_bd,tas_status,tas_data,tas_transa)
					// values
					// (sc_tas_seq.nextval,codi_role,codi_bd,'P',sysdate,'UpdateRole');
					Tasca updateRole = new Tasca();
					updateRole.setTransa("UpdateRole");// Actualització del rol  //$NON-NLS-1$
					updateRole.setDataTasca(Calendar.getInstance());
					updateRole.setStatus("P");// Posem com a pendent  //$NON-NLS-1$
					updateRole.setRole(role.getNom());
					updateRole.setBd(role.getBaseDeDades().getCodi());
					TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(
							updateRole);
					getTasqueEntityDao().create(tasca);
				}

			}
		}
	}
	
	private Collection getUsuarisPertanyenGrup(String codiGrup) {
		HashSet totsUsuaris = new HashSet();
		// Obtenemos los grupos primarios primero
		Collection usuarisGrupComGrupPrimari = getUsuariEntityDao().findByGrupPrimari(codiGrup);
		for (Iterator it = usuarisGrupComGrupPrimari.iterator(); it.hasNext();) {
			UsuariEntity user = (UsuariEntity) it.next();
			totsUsuaris.add(user.getCodi());
		}
		
		// Esto obtiene los usuarios que tienen el grupo como secundario
		Collection usuarisGrupComGrupSec=getUsuariGrupEntityDao().findByCodiGrup(codiGrup);
		for (Iterator it = usuarisGrupComGrupSec.iterator(); it.hasNext();) {
			UsuariGrupEntity usugru = (UsuariGrupEntity) it.next();
			totsUsuaris.add(usugru.getUsuari().getCodi());
		}
		
		return new ArrayList(totsUsuaris);
		
		
	}
	
	private void propagarUsuarisGrup (Collection usuarisPropagar) {
		if (usuarisPropagar != null)
			for (Iterator it = usuarisPropagar.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj != null) {
					String codiUsuari = (String) obj;
					Tasca updateUser = new Tasca();
					// Actualització de l'usuari a tots els agents
					updateUser.setTransa("UpdateUser");  //$NON-NLS-1$
					updateUser.setDataTasca(Calendar.getInstance());
					updateUser.setUsuari(codiUsuari);
					updateUser.setStatus("P");// Posem com a pendent  //$NON-NLS-1$
					TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(
							updateUser);
					getTasqueEntityDao().create(tasca);
				}
			}		
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof GrupEntity) {
				GrupEntity entity = (GrupEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof GrupEntity) {
				GrupEntity entity = (GrupEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof GrupEntity) {
				GrupEntity entity = (GrupEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}
	
}