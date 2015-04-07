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
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.PrinterEntity;
import com.soffid.iam.model.TaskEntity;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Impressora;
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
 * @see es.caib.seycon.ng.model.ImpressoraEntity
 */
public class PrinterEntityDaoImpl extends
        com.soffid.iam.model.PrinterEntityDaoBase {
    private void auditarImpressora(String accio, String codiImpressora) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setImpressora(codiImpressora);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_IMPRES"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    private void generateTask(PrinterEntity impressora) {
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
        tasque.setPrinter(impressora.getCode());
        getTaskEntityDao().create(tasque);
    }


    public void create(com.soffid.iam.model.PrinterEntity impressora) throws RuntimeException {
        try {
            super.create(impressora);
            generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("C", impressora.getCode()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("PrinterEntityDaoImpl.0"), impressora.getCode(), message));
        }
    }

    public void remove(com.soffid.iam.model.PrinterEntity impressora) throws RuntimeException {
        try {
            String codiImpressora = impressora.getCode();
            super.remove(impressora);
            generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("D", codiImpressora); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("PrinterEntityDaoImpl.1"), impressora.getCode(), message));
        }
    }

    public void update(com.soffid.iam.model.PrinterEntity impressora) throws RuntimeException {
        try {
            PrinterEntity old = load(impressora.getId());
            super.update(impressora);
            generateTask(impressora);
            if (!old.getCode().equals(impressora.getCode()))
                generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("U", impressora.getCode()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("PrinterEntityDaoImpl.2"), impressora.getCode(), message));
        }
    }

    public void toImpressora(com.soffid.iam.model.PrinterEntity sourceEntity, es.caib.seycon.ng.comu.Impressora targetVO) {
        super.toImpressora(sourceEntity, targetVO);
        toImpressoraCustom(sourceEntity, targetVO);
    }

    public void toImpressoraCustom(com.soffid.iam.model.PrinterEntity sourceEntity, es.caib.seycon.ng.comu.Impressora targetVO) {
        HostEntity maquina = sourceEntity.getServer();
        if (maquina != null) {
            targetVO.setNomMaquina(maquina.getName());
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
    public es.caib.seycon.ng.comu.Impressora toImpressora(final com.soffid.iam.model.PrinterEntity entity) {
        Impressora impressora = super.toImpressora(entity);
        toImpressoraCustom(entity, impressora);
        return impressora;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.PrinterEntity loadImpressoraEntityFromImpressora(es.caib.seycon.ng.comu.Impressora impressora) {
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
    public com.soffid.iam.model.PrinterEntity impressoraToEntity(es.caib.seycon.ng.comu.Impressora impressora) {
        com.soffid.iam.model.PrinterEntity entity = this.loadImpressoraEntityFromImpressora(impressora);
        this.impressoraToEntity(impressora, entity, true);
        return entity;
    }

    public void impressoraToEntityCustom(es.caib.seycon.ng.comu.Impressora sourceVO, com.soffid.iam.model.PrinterEntity targetEntity) {
        String nomMaquina = sourceVO.getNomMaquina();
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
    public void impressoraToEntity(es.caib.seycon.ng.comu.Impressora sourceVO, com.soffid.iam.model.PrinterEntity targetEntity, boolean copyIfNull) {
        super.impressoraToEntity(sourceVO, targetEntity, copyIfNull);
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
