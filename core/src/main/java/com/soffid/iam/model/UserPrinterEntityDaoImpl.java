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
import com.soffid.iam.api.PrinterUser;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.PrinterEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserPrinterEntity;
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
 * @see es.caib.seycon.ng.model.UsuariImpressoraEntity
 */
public class UserPrinterEntityDaoImpl extends
        com.soffid.iam.model.UserPrinterEntityDaoBase {

    private void auditarUsuariImpressora(String accio, String codiUsuariAuditat,
            String codiImpressora) {
        String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setUser(codiUsuariAuditat);
        auditoria.setPrinter(codiImpressora);
        auditoria.setAuthor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setAdditionalInfo(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObject("SC_USUIMP"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void update(UserPrinterEntity usuariImpressora) {
        try {
            UserPrinterEntity old = load(usuariImpressora.getId());
            super.update(usuariImpressora);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
            tasque.setPrinter(old.getPrinter().getName());
            getTaskEntityDao().create(tasque);
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_USER);
            tasque.setUser(old.getUser().getUserName());
            getTaskEntityDao().create(tasque);
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
            tasque.setPrinter(usuariImpressora.getPrinter().getName());
            getTaskEntityDao().create(tasque);
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_USER);
            tasque.setUser(usuariImpressora.getUser().getUserName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
            auditarUsuariImpressora("U", usuariImpressora.getUser().getUserName(), usuariImpressora.getPrinter().getName());
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UserPrinterEntityDaoImpl.0"), usuariImpressora.getUser().getUserName(), usuariImpressora.getPrinter().getName(), message));
        }
    }

    public void create(com.soffid.iam.model.UserPrinterEntity usuariImpressora) throws RuntimeException {
        try {
            UserPrinterEntity assignacioExistent = findUserByUserAndPrinter(usuariImpressora.getUser().getUserName(), usuariImpressora.getPrinter().getName());

            if (assignacioExistent == null) {
                super.create(usuariImpressora);
                TaskEntity tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
                tasque.setPrinter(usuariImpressora.getPrinter().getName());
                getTaskEntityDao().create(tasque);
                tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_USER);
                tasque.setUser(usuariImpressora.getUser().getUserName());
                getTaskEntityDao().create(tasque);
                getSession(false).flush();
                auditarUsuariImpressora("C", usuariImpressora.getUser().getUserName(), usuariImpressora.getPrinter().getName());
            } else {
                throw new SeyconException(Messages.getString("UserPrinterEntityDaoImpl.1")); //$NON-NLS-1$
            }
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UserPrinterEntityDaoImpl.2"), usuariImpressora.getUser().getUserName(), usuariImpressora.getPrinter().getName(), message));
        }
    }

    public void remove(com.soffid.iam.model.UserPrinterEntity usuariImpressora) throws RuntimeException {
        try {
            String codiUsuari = usuariImpressora.getUser().getUserName();
            String codiImpressora = usuariImpressora.getPrinter().getName();
            super.remove(usuariImpressora);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_PRINTER);
            tasque.setPrinter(usuariImpressora.getPrinter().getName());
            getTaskEntityDao().create(tasque);
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_USER);
            tasque.setUser(usuariImpressora.getUser().getUserName());
            getTaskEntityDao().create(tasque);
            getSession(false).flush();
            auditarUsuariImpressora("D", codiUsuari, codiImpressora); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UserPrinterEntityDaoImpl.3"), usuariImpressora.getUser().getUserName(), usuariImpressora.getPrinter().getName(), message));
        }
    }

    public void toPrinterUser(com.soffid.iam.model.UserPrinterEntity sourceEntity, com.soffid.iam.api.PrinterUser targetVO) {
        // @todo verify behavior of toUsuariImpressora
        super.toPrinterUser(sourceEntity, targetVO);
        toUsuariImpressoraCustom(sourceEntity, targetVO);

    }

    private void toUsuariImpressoraCustom(com.soffid.iam.model.UserPrinterEntity sourceEntity, com.soffid.iam.api.PrinterUser targetVO) {
        PrinterEntity impressoraEntity = sourceEntity.getPrinter();
        String codiImpressora = impressoraEntity.getName();
        Long ordre = sourceEntity.getOrder();
        if (ordre != null && ordre.equals(new Long(1))) {
            targetVO.setEnabledByDefault(new Boolean(true));
        } else {
            targetVO.setEnabledByDefault(new Boolean(false));
        }
        UserEntity usuariEntity = sourceEntity.getUser();
        String codiUsuari = usuariEntity.getUserName();
        targetVO.setUser(codiUsuari);
        targetVO.setPrinter(codiImpressora);

        StringBuffer nomComplert = new StringBuffer();
        nomComplert.append(sourceEntity.getUser().getFirstName());
        nomComplert.append(" "); //$NON-NLS-1$
        nomComplert.append(sourceEntity.getUser().getLastName());
        nomComplert.append(" "); //$NON-NLS-1$
        nomComplert.append(sourceEntity.getUser().getMiddleName());
        targetVO.setFullName(nomComplert.toString());

        if (impressoraEntity != null && impressoraEntity.getServer() != null) {
            targetVO.setPrinterServerName(impressoraEntity.getServer().getName());
        }

    }

    /**
     * @see es.caib.seycon.ng.model.UsuariImpressoraEntityDao#toUsuariImpressora(es.caib.seycon.ng.model.UsuariImpressoraEntity)
     */
    public com.soffid.iam.api.PrinterUser toPrinterUser(final com.soffid.iam.model.UserPrinterEntity entity) {
        // @todo verify behavior of toUsuariImpressora
        PrinterUser usuariImpressora = super.toPrinterUser(entity);
        toUsuariImpressoraCustom(entity, usuariImpressora);
        return usuariImpressora;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.UserPrinterEntity loadUsuariImpressoraEntityFromUsuariImpressora(com.soffid.iam.api.PrinterUser usuariImpressora) {

        com.soffid.iam.model.UserPrinterEntity usuariImpressoraEntity = null;
        if (usuariImpressora.getId() != null) {
            usuariImpressoraEntity = load(usuariImpressora.getId());
        }
        if (usuariImpressoraEntity == null) {
            usuariImpressoraEntity = newUserPrinterEntity();
        }
        return usuariImpressoraEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariImpressoraEntityDao#usuariImpressoraToEntity(es.caib.seycon.ng.comu.UsuariImpressora)
     */
    public com.soffid.iam.model.UserPrinterEntity printerUserToEntity(com.soffid.iam.api.PrinterUser usuariImpressora) {
        // @todo verify behavior of usuariImpressoraToEntity
        com.soffid.iam.model.UserPrinterEntity entity = this.loadUsuariImpressoraEntityFromUsuariImpressora(usuariImpressora);
        this.printerUserToEntity(usuariImpressora, entity, true);
        return entity;
    }

    private void usuariImpressoraToEntityCustom(com.soffid.iam.api.PrinterUser sourceVO, com.soffid.iam.model.UserPrinterEntity targetEntity) {
        UserEntity usuari = getUserEntityDao().findByUserName(sourceVO.getUser());
        if (usuari != null) {
            targetEntity.setUser(usuari);
        } else {
            throw new SeyconException(String.format(Messages.getString("UserPrinterEntityDaoImpl.4"), sourceVO.getUser()));
        }

        PrinterEntity impressora = getPrinterEntityDao().findByName(sourceVO.getPrinter());
        if (impressora != null) {
            targetEntity.setPrinter(impressora);
        } else {
            throw new SeyconException(String.format(Messages.getString("UserPrinterEntityDaoImpl.5") + sourceVO.getPrinter()));
        }
        if (sourceVO.getEnabledByDefault() != null && sourceVO.getEnabledByDefault().booleanValue()) {
            // La que és per defecte te uimOrdre=1 les altres a 2
            usuari = targetEntity.getUser();
            Collection impressores = usuari.getPrinters();
            Iterator iterator = impressores.iterator();
            while (iterator.hasNext()) {
                UserPrinterEntity usuariImpressoraEntity = (UserPrinterEntity) iterator.next();
                usuariImpressoraEntity.setOrder(new Long(2));
            }
            targetEntity.setOrder(new Long(1));
        } else {
            targetEntity.setOrder(new Long(2));
        }

    }

    /**
     * @see es.caib.seycon.ng.model.UsuariImpressoraEntityDao#usuariImpressoraToEntity(es.caib.seycon.ng.comu.UsuariImpressora,
     *      es.caib.seycon.ng.model.UsuariImpressoraEntity)
     */
    public void printerUserToEntity(com.soffid.iam.api.PrinterUser sourceVO, com.soffid.iam.model.UserPrinterEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of usuariImpressoraToEntity
        super.printerUserToEntity(sourceVO, targetEntity, copyIfNull);
        usuariImpressoraToEntityCustom(sourceVO, targetEntity);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserPrinterEntity) {
                UserPrinterEntity entity = (UserPrinterEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserPrinterEntity) {
                UserPrinterEntity entity = (UserPrinterEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserPrinterEntity) {
                UserPrinterEntity entity = (UserPrinterEntity) obj;
                this.remove(entity);
            }
        }
    }
}
