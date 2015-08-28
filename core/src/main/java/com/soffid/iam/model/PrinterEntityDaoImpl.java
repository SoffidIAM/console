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
import com.soffid.iam.api.Printer;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.PrinterEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * @see es.caib.seycon.ng.model.ImpressoraEntity
 */
public class PrinterEntityDaoImpl extends
        com.soffid.iam.model.PrinterEntityDaoBase {
    private void auditarImpressora(String accio, String codiImpressora) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setPrinter(codiImpressora);
        auditoria.setAuthor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObject("SC_IMPRES"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    private void generateTask(PrinterEntity impressora) {
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
        tasque.setPrinter(impressora.getName());
        getTaskEntityDao().create(tasque);
    }


    public void create(com.soffid.iam.model.PrinterEntity impressora) throws RuntimeException {
        try {
            super.create(impressora);
            generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("C", impressora.getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PrinterEntityDaoImpl.0"), impressora.getName(), message));
        }
    }

    public void remove(com.soffid.iam.model.PrinterEntity impressora) throws RuntimeException {
        try {
            String codiImpressora = impressora.getName();
            super.remove(impressora);
            generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("D", codiImpressora); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("PrinterEntityDaoImpl.1"), impressora.getName(), message));
        }
    }

    public void update(com.soffid.iam.model.PrinterEntity impressora) throws RuntimeException {
        try {
            PrinterEntity old = load(impressora.getId());
            super.update(impressora);
            generateTask(impressora);
            if (!old.getName().equals(impressora.getName()))
                generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("U", impressora.getName()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("PrinterEntityDaoImpl.2"), impressora.getName(), message));
        }
    }

    public void toPrinter(com.soffid.iam.model.PrinterEntity sourceEntity, com.soffid.iam.api.Printer targetVO) {
        super.toPrinter(sourceEntity, targetVO);
        toImpressoraCustom(sourceEntity, targetVO);
    }

    public void toImpressoraCustom(com.soffid.iam.model.PrinterEntity sourceEntity, com.soffid.iam.api.Printer targetVO) {
        HostEntity maquina = sourceEntity.getServer();
        if (maquina != null) {
            targetVO.setHostName(maquina.getName());
        }

        String local = sourceEntity.getLocal();
        if (local != null) {
            targetVO.setLocal(new Boolean(local.compareTo("S") == 0)); //$NON-NLS-1$
        } else {
            targetVO.setLocal(new Boolean(false));
        }
    }

    /**
     * @see es.caib.seycon.ng.model.ImpressoraEntityDao#toImpressora(es.caib.seycon.ng.model.ImpressoraEntity)
     */
    public com.soffid.iam.api.Printer toPrinter(final com.soffid.iam.model.PrinterEntity entity) {
        Printer impressora = super.toPrinter(entity);
        toImpressoraCustom(entity, impressora);
        return impressora;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.PrinterEntity loadImpressoraEntityFromImpressora(com.soffid.iam.api.Printer impressora) {
        PrinterEntity impressoraEntity = null;
        if (impressora.getId() != null) {
            impressoraEntity = load(impressora.getId());
        }
        if (impressoraEntity == null) {
            impressoraEntity = newPrinterEntity();
        }
        return impressoraEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.ImpressoraEntityDao#impressoraToEntity(es.caib.seycon.ng.comu.Impressora)
     */
    public com.soffid.iam.model.PrinterEntity printerToEntity(com.soffid.iam.api.Printer impressora) {
        com.soffid.iam.model.PrinterEntity entity = this.loadImpressoraEntityFromImpressora(impressora);
        this.printerToEntity(impressora, entity, true);
        return entity;
    }

    public void impressoraToEntityCustom(com.soffid.iam.api.Printer sourceVO, com.soffid.iam.model.PrinterEntity targetEntity) {
        String nomMaquina = sourceVO.getHostName();
        if (nomMaquina != null && nomMaquina.trim().compareTo("") != 0) { //$NON-NLS-1$
            HostEntity maquinaEntity = getHostEntityDao().findByName(nomMaquina);
            if (maquinaEntity != null) {
                // Comprobamos que sea un servidor d'impressores (!!) - NOU AL
                // SEU 1.2.14 (1.3)
                if (!maquinaEntity.getPrintersServer().equals("S")) //$NON-NLS-1$
                    throw new SeyconException(
                            Messages.getString("PrinterEntityDaoImpl.3")); //$NON-NLS-1$
                targetEntity.setServer(maquinaEntity);
            } else {
				throw new SeyconException(String.format(Messages.getString("PrinterEntityDaoImpl.4"),  //$NON-NLS-1$
						nomMaquina));
            }
        } else {
            throw new SeyconException(
                    Messages.getString("PrinterEntityDaoImpl.5")); //$NON-NLS-1$
        }

        Boolean local = sourceVO.getLocal();
        if (local != null) {
            targetEntity.setLocal(sourceVO.getLocal().booleanValue() ? "S" //$NON-NLS-1$
                    : "N"); //$NON-NLS-1$
        } else {
            targetEntity.setLocal("N"); //$NON-NLS-1$
        }

    }

    /**
     * @see es.caib.seycon.ng.model.ImpressoraEntityDao#impressoraToEntity(es.caib.seycon.ng.comu.Impressora,
     *      es.caib.seycon.ng.model.ImpressoraEntity)
     */
    public void printerToEntity(com.soffid.iam.api.Printer sourceVO, com.soffid.iam.model.PrinterEntity targetEntity, boolean copyIfNull) {
        super.printerToEntity(sourceVO, targetEntity, copyIfNull);
        impressoraToEntityCustom(sourceVO, targetEntity);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof PrinterEntity) {
                PrinterEntity entity = (PrinterEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof PrinterEntity) {
                PrinterEntity entity = (PrinterEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof PrinterEntity) {
                PrinterEntity entity = (PrinterEntity) obj;
                this.remove(entity);
            }
        }
    }

}
