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
import es.caib.seycon.ng.comu.UsuariImpressora;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.UsuariImpressoraEntity
 */
public class UsuariImpressoraEntityDaoImpl extends
        es.caib.seycon.ng.model.UsuariImpressoraEntityDaoBase {

    private void auditarUsuariImpressora(String accio, String codiUsuariAuditat,
            String codiImpressora) {
        String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setUsuari(codiUsuariAuditat);
        auditoria.setImpressora(codiImpressora);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_USUIMP"); //$NON-NLS-1$

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void update(UsuariImpressoraEntity usuariImpressora) {
        try {
            UsuariImpressoraEntity old = load(usuariImpressora.getId());
            super.update(usuariImpressora);
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_PRINTER);
            tasque.setImpres(old.getImpressora().getCodi());
            getTasqueEntityDao().create(tasque);
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER);
            tasque.setUsuari(old.getUsuari().getCodi());
            getTasqueEntityDao().create(tasque);
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_PRINTER);
            tasque.setImpres(usuariImpressora.getImpressora().getCodi());
            getTasqueEntityDao().create(tasque);
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER);
            tasque.setUsuari(usuariImpressora.getUsuari().getCodi());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();
            auditarUsuariImpressora("U", usuariImpressora.getUsuari().getCodi(), usuariImpressora //$NON-NLS-1$
                    .getImpressora().getCodi());
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(
                    Messages.getString("UsuariImpressoraEntityDaoImpl.0"), usuariImpressora.getUsuari() //$NON-NLS-1$
                            .getCodi(), usuariImpressora.getImpressora().getCodi(), message));
        }
    }

    public void create(es.caib.seycon.ng.model.UsuariImpressoraEntity usuariImpressora)
            throws RuntimeException {
        try {
            UsuariImpressoraEntity assignacioExistent = findUsuariImpressoraByCodiUsuariAndCodiImpressora(
                    usuariImpressora.getUsuari().getCodi(), usuariImpressora.getImpressora()
                            .getCodi());

            if (assignacioExistent == null) {
                super.create(usuariImpressora);
                TasqueEntity  tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.UPDATE_PRINTER);
                tasque.setImpres(usuariImpressora.getImpressora().getCodi());
                getTasqueEntityDao().create(tasque);
                tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.UPDATE_USER);
                tasque.setUsuari(usuariImpressora.getUsuari().getCodi());
                getTasqueEntityDao().create(tasque);
                getSession(false).flush();
                auditarUsuariImpressora("C", usuariImpressora.getUsuari().getCodi(), //$NON-NLS-1$
                        usuariImpressora.getImpressora().getCodi());
            } else {
                throw new SeyconException(Messages.getString("UsuariImpressoraEntityDaoImpl.1")); //$NON-NLS-1$
            }
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(
                    String.format(
                            Messages.getString("UsuariImpressoraEntityDaoImpl.2"), //$NON-NLS-1$
                            usuariImpressora.getUsuari().getCodi(), usuariImpressora
                                    .getImpressora().getCodi(), message));
        }
    }

    public void remove(es.caib.seycon.ng.model.UsuariImpressoraEntity usuariImpressora)
            throws RuntimeException {
        try {
            String codiUsuari = usuariImpressora.getUsuari().getCodi();
            String codiImpressora = usuariImpressora.getImpressora().getCodi();
            super.remove(usuariImpressora);
            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_PRINTER);
            tasque.setImpres(usuariImpressora.getImpressora().getCodi());
            getTasqueEntityDao().create(tasque);
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER);
            tasque.setUsuari(usuariImpressora.getUsuari().getCodi());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();
            auditarUsuariImpressora("D", codiUsuari, codiImpressora); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(
                    String.format(
                            Messages.getString("UsuariImpressoraEntityDaoImpl.3"), //$NON-NLS-1$
                            usuariImpressora.getUsuari().getCodi(), usuariImpressora
                                    .getImpressora().getCodi(), message));
        }
    }

    public void toUsuariImpressora(es.caib.seycon.ng.model.UsuariImpressoraEntity sourceEntity,
            es.caib.seycon.ng.comu.UsuariImpressora targetVO) {
        // @todo verify behavior of toUsuariImpressora
        super.toUsuariImpressora(sourceEntity, targetVO);
        toUsuariImpressoraCustom(sourceEntity, targetVO);

    }

    private void toUsuariImpressoraCustom(
            es.caib.seycon.ng.model.UsuariImpressoraEntity sourceEntity,
            es.caib.seycon.ng.comu.UsuariImpressora targetVO) {
        ImpressoraEntity impressoraEntity = sourceEntity.getImpressora();
        String codiImpressora = impressoraEntity.getCodi();
        Long ordre = sourceEntity.getUimOrdre();
        if (ordre != null && ordre.equals(new Long(1))) {
            targetVO.setPerDefecte(new Boolean(true));
        } else {
            targetVO.setPerDefecte(new Boolean(false));
        }
        UsuariEntity usuariEntity = sourceEntity.getUsuari();
        String codiUsuari = usuariEntity.getCodi();
        targetVO.setCodiUsuari(codiUsuari);
        targetVO.setCodiImpressora(codiImpressora);

        StringBuffer nomComplert = new StringBuffer();
        nomComplert.append(sourceEntity.getUsuari().getNom());
        nomComplert.append(" "); //$NON-NLS-1$
        nomComplert.append(sourceEntity.getUsuari().getPrimerLlinatge());
        nomComplert.append(" "); //$NON-NLS-1$
        nomComplert.append(sourceEntity.getUsuari().getSegonLlinatge());
        targetVO.setNomComplert(nomComplert.toString());

        if (impressoraEntity != null && impressoraEntity.getServidor() != null) {
            targetVO.setNomServidorImpressora(impressoraEntity.getServidor().getNom());
        }

    }

    /**
     * @see es.caib.seycon.ng.model.UsuariImpressoraEntityDao#toUsuariImpressora(es.caib.seycon.ng.model.UsuariImpressoraEntity)
     */
    public es.caib.seycon.ng.comu.UsuariImpressora toUsuariImpressora(
            final es.caib.seycon.ng.model.UsuariImpressoraEntity entity) {
        // @todo verify behavior of toUsuariImpressora
        UsuariImpressora usuariImpressora = super.toUsuariImpressora(entity);
        toUsuariImpressoraCustom(entity, usuariImpressora);
        return usuariImpressora;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.UsuariImpressoraEntity loadUsuariImpressoraEntityFromUsuariImpressora(
            es.caib.seycon.ng.comu.UsuariImpressora usuariImpressora) {

        es.caib.seycon.ng.model.UsuariImpressoraEntity usuariImpressoraEntity = null;
        if (usuariImpressora.getId() != null) {
            usuariImpressoraEntity = load(usuariImpressora.getId());
        }
        if (usuariImpressoraEntity == null) {
            usuariImpressoraEntity = newUsuariImpressoraEntity();
        }
        return usuariImpressoraEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariImpressoraEntityDao#usuariImpressoraToEntity(es.caib.seycon.ng.comu.UsuariImpressora)
     */
    public es.caib.seycon.ng.model.UsuariImpressoraEntity usuariImpressoraToEntity(
            es.caib.seycon.ng.comu.UsuariImpressora usuariImpressora) {
        // @todo verify behavior of usuariImpressoraToEntity
        es.caib.seycon.ng.model.UsuariImpressoraEntity entity = this
                .loadUsuariImpressoraEntityFromUsuariImpressora(usuariImpressora);
        this.usuariImpressoraToEntity(usuariImpressora, entity, true);
        return entity;
    }

    private void usuariImpressoraToEntityCustom(es.caib.seycon.ng.comu.UsuariImpressora sourceVO,
            es.caib.seycon.ng.model.UsuariImpressoraEntity targetEntity) {
        UsuariEntity usuari = getUsuariEntityDao().findByCodi(sourceVO.getCodiUsuari());
        if (usuari != null) {
            targetEntity.setUsuari(usuari);
        } else {
            throw new SeyconException(String.format(Messages.getString("UsuariImpressoraEntityDaoImpl.4"), //$NON-NLS-1$
                    sourceVO.getCodiUsuari()));
        }

        ImpressoraEntity impressora = getImpressoraEntityDao().findByCodi(
                sourceVO.getCodiImpressora());
        if (impressora != null) {
            targetEntity.setImpressora(impressora);
        } else {
            throw new SeyconException(String.format(Messages.getString("UsuariImpressoraEntityDaoImpl.5") //$NON-NLS-1$
                    + sourceVO.getCodiImpressora()));
        }
        if (sourceVO.getPerDefecte() != null && sourceVO.getPerDefecte().booleanValue()) {
            // La que és per defecte te uimOrdre=1 les altres a 2
            usuari = targetEntity.getUsuari();
            Collection impressores = usuari.getImpressores();
            Iterator iterator = impressores.iterator();
            while (iterator.hasNext()) {
                UsuariImpressoraEntity usuariImpressoraEntity = (UsuariImpressoraEntity) iterator
                        .next();
                usuariImpressoraEntity.setUimOrdre(new Long(2));
            }
            targetEntity.setUimOrdre(new Long(1));
        } else {
            targetEntity.setUimOrdre(new Long(2));
        }

    }

    /**
     * @see es.caib.seycon.ng.model.UsuariImpressoraEntityDao#usuariImpressoraToEntity(es.caib.seycon.ng.comu.UsuariImpressora,
     *      es.caib.seycon.ng.model.UsuariImpressoraEntity)
     */
    public void usuariImpressoraToEntity(es.caib.seycon.ng.comu.UsuariImpressora sourceVO,
            es.caib.seycon.ng.model.UsuariImpressoraEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of usuariImpressoraToEntity
        super.usuariImpressoraToEntity(sourceVO, targetEntity, copyIfNull);
        usuariImpressoraToEntityCustom(sourceVO, targetEntity);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariImpressoraEntity) {
                    UsuariImpressoraEntity entity = (UsuariImpressoraEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariImpressoraEntity) {
                    UsuariImpressoraEntity entity = (UsuariImpressoraEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariImpressoraEntity) {
                    UsuariImpressoraEntity entity = (UsuariImpressoraEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }
}
