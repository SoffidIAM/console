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

import org.hibernate.Hibernate;

import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.UsuariGrupEntity
 */
public class UsuariGrupEntityDaoImpl extends es.caib.seycon.ng.model.UsuariGrupEntityDaoBase {

    private void auditarUsuariGrup(String accio, String codiUsuariAuditat, String codiGrup) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setUsuari(codiUsuariAuditat);
        auditoria.setGrup(codiGrup);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_USUGRU"); //$NON-NLS-1$

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void update(UsuariGrupEntity usuariGrup) {
        try {

            if (usuariGrup.getGrup().getObsolet() != null
                    && usuariGrup.getGrup().getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
                throw new SeyconException(String.format(
                        Messages.getString("UsuariGrupEntityDaoImpl.0"), //$NON-NLS-1$
                        usuariGrup.getGrup().getCodi()));
            }
            if (usuariGrup.getUsuari().getCodi().equals(Security.getCurrentUser())) {
                throw new SeyconException(Messages.getString("UsuariGrupEntityDaoImpl.1")); //$NON-NLS-1$
            }

            super.update(usuariGrup);
            TasqueEntity         tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER);
            tasque.setUsuari(usuariGrup.getUsuari().getCodi());
            getTasqueEntityDao().create(tasque);
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_GROUP);
            tasque.setGrup(usuariGrup.getGrup().getCodi());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();

            // NOTA: en teoría no se llama a este método
            // Herencia de Roles: propagamos los roles heredados por el grupo (y
            // sus subgrupos)
            HashSet rolsAPropagar = new HashSet();
            Collection rolsAtorgatsGrupISubgrups = getRolsAtorgatsGrupIParesGrup(usuariGrup
                    .getGrup());
            if (rolsAtorgatsGrupISubgrups != null)
                rolsAPropagar.addAll(rolsAtorgatsGrupISubgrups);
            // Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);

            getSession(false).flush();
            auditarUsuariGrup("U", usuariGrup.getUsuari().getCodi(), usuariGrup.getGrup().getCodi()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(
                    String.format(
                            Messages.getString("UsuariGrupEntityDaoImpl.2"), //$NON-NLS-1$
                            usuariGrup.getUsuari().getCodi(), usuariGrup.getGrup().getCodi(),
                            message));
        }
    }

    public void create(es.caib.seycon.ng.model.UsuariGrupEntity usuariGrup) throws RuntimeException {
        try {

            if (usuariGrup.getGrup().getObsolet() != null
                    && usuariGrup.getGrup().getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
                throw new SeyconException(String.format(
                        Messages.getString("UsuariGrupEntityDaoImpl.3"), usuariGrup.getGrup().getCodi())); //$NON-NLS-1$
            }

            if (usuariGrup.getUsuari().getCodi().equals(Security.getCurrentUser())) {
                throw new SeyconException(Messages.getString("UsuariGrupEntityDaoImpl.4")); //$NON-NLS-1$
            }

            super.create(usuariGrup);
            getSession(false).flush();

            // Herencia de Roles: propagamos los roles heredados por el grupo (y
            // de sus padres)
            HashSet rolsAPropagar = new HashSet();
            Collection rolsAtorgatsGrupIPares = getRolsAtorgatsGrupIParesGrup(usuariGrup.getGrup());
            if (rolsAtorgatsGrupIPares != null)
                rolsAPropagar.addAll(rolsAtorgatsGrupIPares);
            // Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);

            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER);
            tasque.setUsuari(usuariGrup.getUsuari().getCodi());
            getTasqueEntityDao().create(tasque);
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_GROUP);
            tasque.setGrup(usuariGrup.getGrup().getCodi());
            getTasqueEntityDao().create(tasque);
            getSession(false).flush();
            auditarUsuariGrup("C", usuariGrup.getUsuari().getCodi(), usuariGrup.getGrup().getCodi()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(
                    String.format(
                            Messages.getString("UsuariGrupEntityDaoImpl.5"), //$NON-NLS-1$
                            usuariGrup.getUsuari().getCodi(), usuariGrup.getGrup().getCodi(),
                            message));
        }
    }

    public void remove(es.caib.seycon.ng.model.UsuariGrupEntity usuariGrup) throws RuntimeException {
        try {
            if (usuariGrup.getUsuari().getCodi().equals(Security.getCurrentUser())) {
                throw new SeyconException(Messages.getString("UsuariGrupEntityDaoImpl.6")); //$NON-NLS-1$
            }
            
            super.remove(usuariGrup);
            getSession(false).flush();


            if (Hibernate.isInitialized(usuariGrup.getUsuari()) &&
            				Hibernate.isInitialized(usuariGrup.getUsuari().getGrupsSecundaris()))
            {
            	usuariGrup.getUsuari().getGrupsSecundaris().remove(usuariGrup);
            }


            if (Hibernate.isInitialized(usuariGrup.getGrup()) &&
            				Hibernate.isInitialized(usuariGrup.getGrup().getUsuarisGrupSecundari()))
            {
            	usuariGrup.getGrup().getUsuarisGrupSecundari().remove(usuariGrup);
            }

            String codiUsuari = usuariGrup.getUsuari().getCodi();
            String codiGrup = usuariGrup.getGrup().getCodi();
            TasqueEntity  tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER);
            tasque.setUsuari(usuariGrup.getUsuari().getCodi());
            getTasqueEntityDao().create(tasque);
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_GROUP);
            tasque.setGrup(usuariGrup.getGrup().getCodi());
            getTasqueEntityDao().create(tasque);

            // Herencia de Roles: propagamos los roles heredados por el grupo (y
            // sus subgrupos)
            HashSet rolsAPropagar = new HashSet();
            Collection rolsAtorgatsGrupISubgrups = getRolsAtorgatsGrupIParesGrup(usuariGrup
                    .getGrup());
            if (rolsAtorgatsGrupISubgrups != null)
                rolsAPropagar.addAll(rolsAtorgatsGrupISubgrups);
            // Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);

            getSession(false).flush();
            auditarUsuariGrup("D", codiUsuari, codiGrup); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(
                    String.format(
                            Messages.getString("UsuariGrupEntityDaoImpl.7"), //$NON-NLS-1$
                            usuariGrup.getUsuari().getCodi(), usuariGrup.getGrup().getCodi(),
                            message));
        }
    }

    public void toUsuariGrup(es.caib.seycon.ng.model.UsuariGrupEntity sourceEntity,
            es.caib.seycon.ng.comu.UsuariGrup targetVO) {
        super.toUsuariGrup(sourceEntity, targetVO);
    }

    /**
     * Mètode que omple els valors del VO en la transformació. Per cada nou camp
     * que s'afegeixi al VO, s'ha d'implementar el codi corresponent aquí.
     * 
     * @param sourceEntity
     * @param targetVO
     */
    private void toUsuariGrupCustom(es.caib.seycon.ng.model.UsuariGrupEntity sourceEntity,
            es.caib.seycon.ng.comu.UsuariGrup targetVO) {
        targetVO.setCodiGrup(sourceEntity.getGrup().getCodi());
        targetVO.setCodiUsuari(sourceEntity.getUsuari().getCodi());
        targetVO.setDescripcioGrup(sourceEntity.getGrup().getDescripcio());
        UsuariEntity user = sourceEntity.getUsuari();
        String nomComplet = user.getNom() + " " + user.getPrimerLlinatge() //$NON-NLS-1$
                + (user.getSegonLlinatge() != null ? " " + user.getSegonLlinatge() : ""); //$NON-NLS-1$ //$NON-NLS-2$
        targetVO.setNomComplet(nomComplet);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariGrupEntityDao#toUsuariGrup(es.caib.seycon.ng.model.UsuariGrupEntity)
     */
    public es.caib.seycon.ng.comu.UsuariGrup toUsuariGrup(
            final es.caib.seycon.ng.model.UsuariGrupEntity entity) {
        // @todo verify behavior of toUsuariGrup
        UsuariGrup usuariGrup = super.toUsuariGrup(entity);
        toUsuariGrupCustom(entity, usuariGrup);
        return usuariGrup;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.UsuariGrupEntity loadUsuariGrupEntityFromUsuariGrup(
            es.caib.seycon.ng.comu.UsuariGrup usuariGrup) {
        es.caib.seycon.ng.model.UsuariGrupEntity usuariGrupEntity = null;
        if (usuariGrup.getId() != null) {
            usuariGrupEntity = load(usuariGrup.getId());
        }
        if (usuariGrupEntity == null) {
            usuariGrupEntity = newUsuariGrupEntity();
        }
        return usuariGrupEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariGrupEntityDao#usuariGrupToEntity(es.caib.seycon.ng.comu.UsuariGrup)
     */
    public es.caib.seycon.ng.model.UsuariGrupEntity usuariGrupToEntity(
            es.caib.seycon.ng.comu.UsuariGrup usuariGrup) {
        // @todo verify behavior of usuariGrupToEntity
        es.caib.seycon.ng.model.UsuariGrupEntity entity = this
                .loadUsuariGrupEntityFromUsuariGrup(usuariGrup);
        this.usuariGrupToEntity(usuariGrup, entity, true);
        return entity;
    }

    /**
     * Mètode que omple els valors del Entity en la transformació. Per cada nou
     * camp que s'afegeixi al Entity, s'ha d'implementar el codi corresponent
     * aquí.
     * 
     * @param sourceVO
     * @param targetEntity
     */
    private void usuariGrupToEntityCustom(es.caib.seycon.ng.comu.UsuariGrup sourceVO,
            es.caib.seycon.ng.model.UsuariGrupEntity targetEntity) {
        UsuariEntity usuari = getUsuariEntityDao().findByCodi(sourceVO.getCodiUsuari());
        if (usuari == null) {
            throw new SeyconException(String.format(Messages.getString("UsuariGrupEntityDaoImpl.8"), //$NON-NLS-1$
                    sourceVO.getCodiUsuari()));
        }
        targetEntity.setUsuari(usuari);
        GrupEntity grup = getGrupEntityDao().findByCodi(sourceVO.getCodiGrup());
        if (grup == null) {
            throw new SeyconException(String.format(Messages.getString("UsuariGrupEntityDaoImpl.9"), //$NON-NLS-1$
                    sourceVO.getCodiGrup()));
        } else {
            targetEntity.setGrup(grup);
        }
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariGrupEntityDao#usuariGrupToEntity(es.caib.seycon.ng.comu.UsuariGrup,
     *      es.caib.seycon.ng.model.UsuariGrupEntity)
     */
    public void usuariGrupToEntity(es.caib.seycon.ng.comu.UsuariGrup sourceVO,
            es.caib.seycon.ng.model.UsuariGrupEntity targetEntity, boolean copyIfNull) {
        super.usuariGrupToEntity(sourceVO, targetEntity, copyIfNull);
        usuariGrupToEntityCustom(sourceVO, targetEntity);
    }

    private Collection getParesGrup(GrupEntity grupAnalitzar) {

        Collection totsPares = new HashSet();
        GrupEntity pare = grupAnalitzar.getPare();
        while (pare != null) {
            totsPares.add(pare);
            pare = pare.getPare();
        }

        return totsPares;
    }

    private Collection getRolsContingutsPerPropagar(RolEntity rol) {
        // Si rol té atorgats d'altres rols (és conetnidor dele rols)
        // s'han de propagar tots els rols que conté (per assignar-lo a
        // l'usuari)
        HashSet rolsPropagar = new HashSet();
        // Sólo hemos de propagar a los usuarios que tienen el rol contenedor
        // con valor de dominio correspondiente (o si es SENSE_DOMINI o a
        // qualque valor)
        // Montamos un FIFO De roles (puede haber cadena de
        // herencia A atorgat B[sense domini] atorgat C ... atorgat Z[amb
        // domini]
        LinkedList rolsAnalitzar = new LinkedList(); // FIFO
        rolsAnalitzar.add(rol);
        RolEntity rolActual = null;
        while ((rolActual = (RolEntity) rolsAnalitzar.poll()) != null) {
            Collection socContenidor = rolActual.getRolAssociacioRolSocContenidor();

            if (socContenidor != null)
                for (Iterator it = socContenidor.iterator(); it.hasNext();) {
                    RolAssociacioRolEntity associacio = (RolAssociacioRolEntity) it.next();
                    // Obtenemos el rol contenido
                    RolEntity rolContingut = associacio.getRolContingut();
                    // Guardamos el rol para propagarlo
                    rolsPropagar.add(rolContingut);
                    // Añadimos el rol contenido para analizar si a su vez es
                    // contenido en otro (atorgat)
                    rolsAnalitzar.add(rolContingut);
                }
        }
        return rolsPropagar;
    }

    /**
     * Obtiene dado un grupo, los roles otorgados al grupo (y los roles
     * otorgados a los padres del grupo indicado)
     * 
     * @param grup
     * @return
     */
    private Collection getRolsAtorgatsGrupIParesGrup(GrupEntity grup) {

        // 1) Obtenim els grups pares del grup
        HashSet totGrup = new HashSet();
        totGrup.add(grup);
        Collection paresGrup = getParesGrup(grup);
        totGrup.addAll(paresGrup);

        // 2) Obtenim els rols atorgats al grup i els grups pare
        HashSet totRolAtorgatGrup = new HashSet();
        for (Iterator it = totGrup.iterator(); it.hasNext();) {
            Object obj = it.next();
            if (obj != null) {
                GrupEntity g = (GrupEntity) obj;
                Collection rolsAtorgatsG = g.getRolsOtorgatsGrup();
                if (rolsAtorgatsG != null)
                    totRolAtorgatGrup.addAll(rolsAtorgatsG);
            }
        }

        // 3) Obtenim els rols atorgats als rols:
        HashSet rolsPropagar = new HashSet();
        for (Iterator it = totRolAtorgatGrup.iterator(); it.hasNext();) {
            Object obj = it.next();
            if (obj != null) {
                RolsGrupEntity rolgrup = (RolsGrupEntity) obj;
                // Añadimos el rol actual para propagarlo junto a sus hijos:
                rolsPropagar.add(rolgrup.getRolOtorgat());
                // Miramos si tiene roles otorgados
                Collection rolsAtorgatsRol = getRolsContingutsPerPropagar(rolgrup.getRolOtorgat());
                if (rolsAtorgatsRol != null)
                    rolsPropagar.addAll(rolsAtorgatsRol);
            }
        }

        return new ArrayList(rolsPropagar);
    }

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
                    updateRole.setTransa("UpdateRole");// Actualització del rol //$NON-NLS-1$
                    updateRole.setDataTasca(Calendar.getInstance());
                    updateRole.setStatus("P");// Posem com a pendent //$NON-NLS-1$
                    updateRole.setRole(role.getNom());
                    updateRole.setBd(role.getBaseDeDades().getCodi());
                    TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(updateRole);
                    getTasqueEntityDao().create(tasca);
                }

            }
        }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariGrupEntity) {
                    UsuariGrupEntity entity = (UsuariGrupEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariGrupEntity) {
                    UsuariGrupEntity entity = (UsuariGrupEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariGrupEntity) {
                    UsuariGrupEntity entity = (UsuariGrupEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }

}
