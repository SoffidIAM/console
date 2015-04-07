// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.PrinterEntity;
import com.soffid.iam.model.PrinterGroupEntity;
import com.soffid.iam.model.TaskEntity;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.GrupImpressoraEntity
 */
public class PrinterGroupEntityDaoImpl extends
		com.soffid.iam.model.PrinterGroupEntityDaoBase {

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

		AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	public void update(PrinterGroupEntity grupImpressora) {
		try {
			super.update(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("U", grupImpressora.getGroup().getCode(), grupImpressora.getPrinter().getCode());
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.0"), grupImpressora.getPrinter().getCode(), grupImpressora.getGroup().getCode(), message));
		}
	}

	public void create(com.soffid.iam.model.PrinterGroupEntity grupImpressora) throws RuntimeException {
		try {
			super.create(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("C", grupImpressora.getGroup().getCode(), grupImpressora.getPrinter().getCode());
                        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
                        tasque.setDate(new Timestamp(System.currentTimeMillis()));
                        tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
                        tasque.setPrinter(grupImpressora.getPrinter().getCode());
                        getTaskEntityDao().create(tasque);
                        getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.1"), grupImpressora.getPrinter().getCode(), grupImpressora.getGroup().getCode(), message));
		}
	}

	public void remove(com.soffid.iam.model.PrinterGroupEntity grupImpressora) throws RuntimeException {
		try {
			String codiGrup = grupImpressora.getGroup().getCode();
			String codiImpressora = grupImpressora.getPrinter().getCode();
			super.remove(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("D", codiGrup, codiImpressora); //$NON-NLS-1$
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
			tasque.setPrinter(grupImpressora.getPrinter().getCode());
			getTaskEntityDao().create(tasque);
			getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.2"), grupImpressora.getPrinter().getCode(), grupImpressora.getGroup().getCode(), message));
		}
	}

	public void toGrupImpressora(com.soffid.iam.model.PrinterGroupEntity source, es.caib.seycon.ng.comu.GrupImpressora target) {
		super.toGrupImpressora(source, target);
		toGrupImpressoraCustom(source, target);
	}

	public void toGrupImpressoraCustom(com.soffid.iam.model.PrinterGroupEntity source, es.caib.seycon.ng.comu.GrupImpressora target) {
		PrinterEntity impressora = source.getPrinter();
		GroupEntity grup = source.getGroup();

		target.setCodiImpressora(impressora.getCode());
		target.setCodiGrup(grup.getCode());
		Long ordre = source.getOrder();
		if (ordre != null && ordre.equals(new Long(1))) {
			target.setPerDefecte(new Boolean(true));
		} else {
			target.setPerDefecte(new Boolean(false));
		}
		
		if (impressora != null && impressora.getServer() != null) {
			target.setNomServidorImpressora(impressora.getServer().getName());
		}

		
	}

	public es.caib.seycon.ng.comu.GrupImpressora toGrupImpressora(final com.soffid.iam.model.PrinterGroupEntity entity) {
		return super.toGrupImpressora(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.PrinterGroupEntity loadGrupImpressoraEntityFromGrupImpressora(es.caib.seycon.ng.comu.GrupImpressora grupImpressora) {
		com.soffid.iam.model.PrinterGroupEntity grupImpressoraEntity = null;
		if (grupImpressora.getId() != null) {
			grupImpressoraEntity = load(grupImpressora.getId());
		}
		if (grupImpressoraEntity == null) {
			grupImpressoraEntity = newPrinterGroupEntity();
		}
		return grupImpressoraEntity;
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupImpressoraEntityDao#grupImpressoraToEntity(es.caib.seycon.ng.comu.GrupImpressora)
	 */
	public com.soffid.iam.model.PrinterGroupEntity grupImpressoraToEntity(es.caib.seycon.ng.comu.GrupImpressora grupImpressora) {
		com.soffid.iam.model.PrinterGroupEntity entity = this.loadGrupImpressoraEntityFromGrupImpressora(grupImpressora);
		this.grupImpressoraToEntity(grupImpressora, entity, true);
		return entity;
	}

	public void grupImpressoraToEntityCustom(es.caib.seycon.ng.comu.GrupImpressora source, com.soffid.iam.model.PrinterGroupEntity target) {
		String codiGrup = source.getCodiGrup();
		PrinterEntity impressora = getPrinterEntityDao().findByCode(source.getCodiImpressora());
		if (impressora != null) {
			target.setPrinter(impressora);
		} else {
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.3"),  //$NON-NLS-1$
					source.getCodiImpressora()));
		}
		GroupEntity grup = getGroupEntityDao().findByCode(source.getCodiGrup());
		if (grup != null) {
			target.setGroup(grup);
		} else {
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.4"),  //$NON-NLS-1$
					source.getCodiGrup()));
		}
		if (source.getPerDefecte().booleanValue()) {
			target.setOrder(new Long(1));
		} else {
			target.setOrder(new Long(2));
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupImpressoraEntityDao#grupImpressoraToEntity(es.caib.seycon.ng.comu.GrupImpressora,
	 *      es.caib.seycon.ng.model.GrupImpressoraEntity)
	 */
	public void grupImpressoraToEntity(es.caib.seycon.ng.comu.GrupImpressora source, com.soffid.iam.model.PrinterGroupEntity target, boolean copyIfNull) {
		super.grupImpressoraToEntity(source, target, copyIfNull);
		grupImpressoraToEntityCustom(source, target);
	}

	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof PrinterGroupEntity) {
                PrinterGroupEntity entity = (PrinterGroupEntity) obj;
                this.create(entity);
            }
        }
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof PrinterGroupEntity) {
                PrinterGroupEntity entity = (PrinterGroupEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof PrinterGroupEntity) {
                PrinterGroupEntity entity = (PrinterGroupEntity) obj;
                this.remove(entity);
            }
        }
	}

}