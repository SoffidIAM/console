// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.GrupImpressoraEntity
 */
public class GrupImpressoraEntityDaoImpl extends
		es.caib.seycon.ng.model.GrupImpressoraEntityDaoBase {

	private void auditarGrupImpressora(String accio, String codiGrup,
			String codiImpressora) {
		String codiUsuari = Security.getCurrentAccount();
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setImpressora(codiImpressora);
		auditoria.setGrup(codiGrup);
		auditoria.setAutor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObjecte("SC_GRUIMP"); //$NON-NLS-1$

		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}

	public void update(GrupImpressoraEntity grupImpressora) {
		try {
			super.update(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("U", grupImpressora.getGrup().getCodi(), //$NON-NLS-1$
					grupImpressora.getImpressora().getCodi());
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("GrupImpressoraEntityDaoImpl.0"),  //$NON-NLS-1$
					grupImpressora.getImpressora().getCodi(), 
					grupImpressora.getGrup().getCodi(), 
					message));
		}
	}

	public void create(
			es.caib.seycon.ng.model.GrupImpressoraEntity grupImpressora)
			throws RuntimeException {
		try {
			super.create(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("C", grupImpressora.getGrup().getCodi(), //$NON-NLS-1$
					grupImpressora.getImpressora().getCodi());
                        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
                        tasque.setData(new Timestamp(System.currentTimeMillis()));
                        tasque.setTransa(TaskHandler.UPDATE_PRINTER);
                        tasque.setImpres(grupImpressora.getImpressora().getCodi());
                        getTasqueEntityDao().create(tasque);
                        getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("GrupImpressoraEntityDaoImpl.1"),  //$NON-NLS-1$
					grupImpressora.getImpressora().getCodi(), 
					grupImpressora.getGrup().getCodi(), 
					message));
		}
	}

	public void remove(
			es.caib.seycon.ng.model.GrupImpressoraEntity grupImpressora)
			throws RuntimeException {
		try {
			String codiGrup = grupImpressora.getGrup().getCodi();
			String codiImpressora = grupImpressora.getImpressora().getCodi();
			super.remove(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("D", codiGrup, codiImpressora); //$NON-NLS-1$
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_PRINTER);
			tasque.setImpres(grupImpressora.getImpressora().getCodi());
			getTasqueEntityDao().create(tasque);
			getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(
					Messages.getString("GrupImpressoraEntityDaoImpl.2"), //$NON-NLS-1$
							grupImpressora.getImpressora().getCodi(),
							grupImpressora.getGrup().getCodi(),
							message));
		}
	}

	public void toGrupImpressora(
			es.caib.seycon.ng.model.GrupImpressoraEntity source,
			es.caib.seycon.ng.comu.GrupImpressora target) {
		super.toGrupImpressora(source, target);
		toGrupImpressoraCustom(source, target);
	}

	public void toGrupImpressoraCustom(
			es.caib.seycon.ng.model.GrupImpressoraEntity source,
			es.caib.seycon.ng.comu.GrupImpressora target) {
		ImpressoraEntity impressora = source.getImpressora();
		GrupEntity grup = source.getGrup();

		target.setCodiImpressora(impressora.getCodi());
		target.setCodiGrup(grup.getCodi());
		Long ordre = source.getOrdre();
		if (ordre != null && ordre.equals(new Long(1))) {
			target.setPerDefecte(new Boolean(true));
		} else {
			target.setPerDefecte(new Boolean(false));
		}
		
		if (impressora!=null && impressora.getServidor()!=null) {
			target.setNomServidorImpressora(impressora.getServidor().getNom());
		}

		
	}

	public es.caib.seycon.ng.comu.GrupImpressora toGrupImpressora(
			final es.caib.seycon.ng.model.GrupImpressoraEntity entity) {
		return super.toGrupImpressora(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private es.caib.seycon.ng.model.GrupImpressoraEntity loadGrupImpressoraEntityFromGrupImpressora(
			es.caib.seycon.ng.comu.GrupImpressora grupImpressora) {
		es.caib.seycon.ng.model.GrupImpressoraEntity grupImpressoraEntity = null;
		if (grupImpressora.getId() != null) {
			grupImpressoraEntity = load(grupImpressora.getId());
		}
		if (grupImpressoraEntity == null) {
			grupImpressoraEntity = newGrupImpressoraEntity();
		}
		return grupImpressoraEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupImpressoraEntityDao#grupImpressoraToEntity(es.caib.seycon.ng.comu.GrupImpressora)
	 */
	public es.caib.seycon.ng.model.GrupImpressoraEntity grupImpressoraToEntity(
			es.caib.seycon.ng.comu.GrupImpressora grupImpressora) {
		es.caib.seycon.ng.model.GrupImpressoraEntity entity = this
				.loadGrupImpressoraEntityFromGrupImpressora(grupImpressora);
		this.grupImpressoraToEntity(grupImpressora, entity, true);
		return entity;
	}

	public void grupImpressoraToEntityCustom(
			es.caib.seycon.ng.comu.GrupImpressora source,
			es.caib.seycon.ng.model.GrupImpressoraEntity target) {
		String codiGrup = source.getCodiGrup();
		ImpressoraEntity impressora = getImpressoraEntityDao().findByCodi(
				source.getCodiImpressora());
		if (impressora != null) {
			target.setImpressora(impressora);
		} else {
			throw new SeyconException(String.format(Messages.getString("GrupImpressoraEntityDaoImpl.3"),  //$NON-NLS-1$
					source.getCodiImpressora()));
		}
		GrupEntity grup = getGrupEntityDao().findByCodi(source.getCodiGrup());
		if (grup != null) {
			target.setGrup(grup);
		} else {
			throw new SeyconException(String.format(Messages.getString("GrupImpressoraEntityDaoImpl.4"),  //$NON-NLS-1$
					source.getCodiGrup()));
		}
		if (source.getPerDefecte().booleanValue()) {
			target.setOrdre(new Long(1));
		} else {
			target.setOrdre(new Long(2));
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupImpressoraEntityDao#grupImpressoraToEntity(es.caib.seycon.ng.comu.GrupImpressora,
	 *      es.caib.seycon.ng.model.GrupImpressoraEntity)
	 */
	public void grupImpressoraToEntity(
			es.caib.seycon.ng.comu.GrupImpressora source,
			es.caib.seycon.ng.model.GrupImpressoraEntity target,
			boolean copyIfNull) {
		super.grupImpressoraToEntity(source, target, copyIfNull);
		grupImpressoraToEntityCustom(source, target);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof GrupImpressoraEntity) {
				GrupImpressoraEntity entity = (GrupImpressoraEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		}
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof GrupImpressoraEntity) {
				GrupImpressoraEntity entity = (GrupImpressoraEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof GrupImpressoraEntity) {
				GrupImpressoraEntity entity = (GrupImpressoraEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}

}