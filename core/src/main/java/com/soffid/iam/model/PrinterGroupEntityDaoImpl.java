// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.api.Audit;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.PrinterEntity;
import com.soffid.iam.model.PrinterGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;
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
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setPrinter(codiImpressora);
		auditoria.setGroup(codiGrup);
		auditoria.setAuthor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
		auditoria.setObject("SC_GRUIMP"); //$NON-NLS-1$

		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	public void update(PrinterGroupEntity grupImpressora) {
		try {
			super.update(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("U", grupImpressora.getGroup().getName(), grupImpressora.getPrinter().getName());
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.0"), grupImpressora.getPrinter().getName(), grupImpressora.getGroup().getName(), message));
		}
	}

	public void create(com.soffid.iam.model.PrinterGroupEntity grupImpressora) throws RuntimeException {
		try {
			super.create(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("C", grupImpressora.getGroup().getName(), grupImpressora.getPrinter().getName());
                        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
                        tasque.setDate(new Timestamp(System.currentTimeMillis()));
                        tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
                        tasque.setPrinter(grupImpressora.getPrinter().getName());
                        getTaskEntityDao().create(tasque);
                        getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.1"), grupImpressora.getPrinter().getName(), grupImpressora.getGroup().getName(), message));
		}
	}

	public void remove(com.soffid.iam.model.PrinterGroupEntity grupImpressora) throws RuntimeException {
		try {
			String codiGrup = grupImpressora.getGroup().getName();
			String codiImpressora = grupImpressora.getPrinter().getName();
			super.remove(grupImpressora);
			getSession(false).flush();
			auditarGrupImpressora("D", codiGrup, codiImpressora); //$NON-NLS-1$
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
			tasque.setPrinter(grupImpressora.getPrinter().getName());
			getTaskEntityDao().create(tasque);
			getSession().flush();
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.2"), grupImpressora.getPrinter().getName(), grupImpressora.getGroup().getName(), message));
		}
	}

	public void toPrinterGroup(com.soffid.iam.model.PrinterGroupEntity source, com.soffid.iam.api.PrinterGroup target) {
		super.toPrinterGroup(source, target);
		toGrupImpressoraCustom(source, target);
	}

	public void toGrupImpressoraCustom(com.soffid.iam.model.PrinterGroupEntity source, com.soffid.iam.api.PrinterGroup target) {
		PrinterEntity impressora = source.getPrinter();
		GroupEntity grup = source.getGroup();

		target.setPrinterCode(impressora.getName());
		target.setGroupCode(grup.getName());
		Long ordre = source.getOrder();
		if (ordre != null && ordre.equals(new Long(1))) {
			target.setEnabledByDefault(new Boolean(true));
		} else {
			target.setEnabledByDefault(new Boolean(false));
		}
		
		if (impressora != null && impressora.getServer() != null) {
			target.setPrinterServerName(impressora.getServer().getName());
		}

		
	}

	public com.soffid.iam.api.PrinterGroup toPrinterGroup(final com.soffid.iam.model.PrinterGroupEntity entity) {
		return super.toPrinterGroup(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.PrinterGroupEntity loadGrupImpressoraEntityFromGrupImpressora(com.soffid.iam.api.PrinterGroup grupImpressora) {
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
	public com.soffid.iam.model.PrinterGroupEntity printerGroupToEntity(com.soffid.iam.api.PrinterGroup grupImpressora) {
		com.soffid.iam.model.PrinterGroupEntity entity = this.loadGrupImpressoraEntityFromGrupImpressora(grupImpressora);
		this.printerGroupToEntity(grupImpressora, entity, true);
		return entity;
	}

	public void grupImpressoraToEntityCustom(com.soffid.iam.api.PrinterGroup source, com.soffid.iam.model.PrinterGroupEntity target) {
		String codiGrup = source.getGroupCode();
		PrinterEntity impressora = getPrinterEntityDao().findByName(source.getPrinterCode());
		if (impressora != null) {
			target.setPrinter(impressora);
		} else {
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.3"), source.getPrinterCode()));
		}
		GroupEntity grup = getGroupEntityDao().findByName(source.getGroupCode());
		if (grup != null) {
			target.setGroup(grup);
		} else {
			throw new SeyconException(String.format(Messages.getString("PrinterGroupEntityDaoImpl.4"), source.getGroupCode()));
		}
		if (source.getEnabledByDefault().booleanValue()) {
			target.setOrder(new Long(1));
		} else {
			target.setOrder(new Long(2));
		}
	}

	/**
	 * @see es.caib.seycon.ng.model.GrupImpressoraEntityDao#grupImpressoraToEntity(es.caib.seycon.ng.comu.GrupImpressora,
	 *      es.caib.seycon.ng.model.GrupImpressoraEntity)
	 */
	public void printerGroupToEntity(com.soffid.iam.api.PrinterGroup source, com.soffid.iam.model.PrinterGroupEntity target, boolean copyIfNull) {
		super.printerGroupToEntity(source, target, copyIfNull);
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