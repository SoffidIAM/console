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
import es.caib.seycon.ng.comu.Impressora;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.ImpressoraEntity
 */
public class ImpressoraEntityDaoImpl extends
        es.caib.seycon.ng.model.ImpressoraEntityDaoBase {
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
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    private void generateTask(ImpressoraEntity impressora) {
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_PRINTER);
        tasque.setImpres(impressora.getCodi());
        getTasqueEntityDao().create(tasque);
    }


    public void create(es.caib.seycon.ng.model.ImpressoraEntity impressora)
            throws RuntimeException {
        try {
            super.create(impressora);
            generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("C", impressora.getCodi()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("ImpressoraEntityDaoImpl.0"),  //$NON-NLS-1$
					impressora.getCodi(), 
					message));
        }
    }

    public void remove(es.caib.seycon.ng.model.ImpressoraEntity impressora)
            throws RuntimeException {
        try {
            String codiImpressora = impressora.getCodi();
            super.remove(impressora);
            generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("D", codiImpressora); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

			throw new SeyconException(
					String.format(Messages.getString("ImpressoraEntityDaoImpl.1"),  //$NON-NLS-1$
							impressora.getCodi(), 
							message));
        }
    }

    public void update(es.caib.seycon.ng.model.ImpressoraEntity impressora)
            throws RuntimeException {
        try {
            ImpressoraEntity old = load(impressora.getId());
            super.update(impressora);
            generateTask(impressora);
            if (!old.getCodi().equals (impressora.getCodi()))
                generateTask(impressora);
            getSession(false).flush();
            auditarImpressora("U", impressora.getCodi()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

			throw new SeyconException(String.format(Messages.getString("ImpressoraEntityDaoImpl.2"),  //$NON-NLS-1$
					impressora.getCodi(),
					message));
        }
    }

    public void toImpressora(
            es.caib.seycon.ng.model.ImpressoraEntity sourceEntity,
            es.caib.seycon.ng.comu.Impressora targetVO) {
        super.toImpressora(sourceEntity, targetVO);
        toImpressoraCustom(sourceEntity, targetVO);
    }

    public void toImpressoraCustom(
            es.caib.seycon.ng.model.ImpressoraEntity sourceEntity,
            es.caib.seycon.ng.comu.Impressora targetVO) {
        MaquinaEntity maquina = sourceEntity.getServidor();
        if (maquina != null) {
            targetVO.setNomMaquina(maquina.getNom());
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
    public es.caib.seycon.ng.comu.Impressora toImpressora(
            final es.caib.seycon.ng.model.ImpressoraEntity entity) {
        Impressora impressora = super.toImpressora(entity);
        toImpressoraCustom(entity, impressora);
        return impressora;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.ImpressoraEntity loadImpressoraEntityFromImpressora(
            es.caib.seycon.ng.comu.Impressora impressora) {
        ImpressoraEntity impressoraEntity = null;
        if (impressora.getId() != null) {
            impressoraEntity = load(impressora.getId());
        }
        if (impressoraEntity == null) {
            impressoraEntity = newImpressoraEntity();
        }
        return impressoraEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.ImpressoraEntityDao#impressoraToEntity(es.caib.seycon.ng.comu.Impressora)
     */
    public es.caib.seycon.ng.model.ImpressoraEntity impressoraToEntity(
            es.caib.seycon.ng.comu.Impressora impressora) {
        es.caib.seycon.ng.model.ImpressoraEntity entity = this
                .loadImpressoraEntityFromImpressora(impressora);
        this.impressoraToEntity(impressora, entity, true);
        return entity;
    }

    public void impressoraToEntityCustom(
            es.caib.seycon.ng.comu.Impressora sourceVO,
            es.caib.seycon.ng.model.ImpressoraEntity targetEntity) {
        String nomMaquina = sourceVO.getNomMaquina();
        if (nomMaquina != null && nomMaquina.trim().compareTo("") != 0) { //$NON-NLS-1$
            MaquinaEntity maquinaEntity = getMaquinaEntityDao().findByNom(
                    nomMaquina);
            if (maquinaEntity != null) {
                // Comprobamos que sea un servidor d'impressores (!!) - NOU AL
                // SEU 1.2.14 (1.3)
                if (!maquinaEntity.getServidorImpressores().equals("S")) //$NON-NLS-1$
                    throw new SeyconException(
                            Messages.getString("ImpressoraEntityDaoImpl.3")); //$NON-NLS-1$
                targetEntity.setServidor(maquinaEntity);
            } else {
				throw new SeyconException(String.format(Messages.getString("ImpressoraEntityDaoImpl.4"),  //$NON-NLS-1$
						nomMaquina));
            }
        } else {
            throw new SeyconException(
                    Messages.getString("ImpressoraEntityDaoImpl.5")); //$NON-NLS-1$
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
    public void impressoraToEntity(es.caib.seycon.ng.comu.Impressora sourceVO,
            es.caib.seycon.ng.model.ImpressoraEntity targetEntity,
            boolean copyIfNull) {
        super.impressoraToEntity(sourceVO, targetEntity, copyIfNull);
        impressoraToEntityCustom(sourceVO, targetEntity);
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof ImpressoraEntity) {
                    ImpressoraEntity entity = (ImpressoraEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof ImpressoraEntity) {
                    ImpressoraEntity entity = (ImpressoraEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof ImpressoraEntity) {
                    ImpressoraEntity entity = (ImpressoraEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }

}
