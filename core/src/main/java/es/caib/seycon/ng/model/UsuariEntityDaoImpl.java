//license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.sql.CallableStatement;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import es.caib.bpm.vo.BPMUser;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.EstatContrasenya;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.LlistaCorreu;
import es.caib.seycon.ng.comu.LlistaCorreuUsuari;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.comu.TipusUsuari;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariAnonim;
import es.caib.seycon.ng.comu.UsuariSEU;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.UsuariEntity
 */
public class UsuariEntityDaoImpl extends es.caib.seycon.ng.model.UsuariEntityDaoBase {

	public static final String NIF = "NIF"; //$NON-NLS-1$
    public static final String TELEFON = "PHONE"; //$NON-NLS-1$
    private static final String SeyconLogon_EMAIL_ADD_CODE = "E-MAIL CONTACTE"; //$NON-NLS-1$

    private void auditarUsuari(String accio, String codiUsuariAuditat, GrupEntity grupPrimari) {
        // Corregim accés sense principal (donar d'alta usuaris)
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setUsuari(codiUsuariAuditat);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Messages.getString("UsuariEntityDaoImpl.dateFormat")); //$NON-NLS-1$
        // Afegim auditoria del grup primari de l'usuari
        if (grupPrimari != null)
            auditoria.setGrup(grupPrimari.getCodi());
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_USUARI"); //$NON-NLS-1$

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void create(es.caib.seycon.ng.model.UsuariEntity usuari) throws RuntimeException {
        try {

            if (usuari.getDominiCorreu() != null && usuari.getDominiCorreu().getObsolet() != null
                    && usuari.getDominiCorreu().getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
                throw new SeyconException(String.format(Messages.getString("UsuariEntityDaoImpl.deletedDomain"), usuari.getDominiCorreu() //$NON-NLS-1$
                        .getCodi()));
            }

            usuari.setDataDarreraModificacio(GregorianCalendar.getInstance().getTime());
            super.create(usuari);
            getSession(false).flush();

            // HERÈNCIA DE ROLS: Atorgació de rols al grup primari
            // Obtenemos los roles otorgados al grupo primario
            // Los secundarios se gestionan en UsuariGrupEntityDaoImpl
            GrupEntity grupPrimari = usuari.getGrupPrimari();
            HashSet rolsAPropagar = new HashSet();
            if (grupPrimari != null) {
                Collection rolsAtorgatsGrupISubgrups = getRolsAtorgatsGrupIParesGrup(grupPrimari);
                if (rolsAtorgatsGrupISubgrups != null)
                    rolsAPropagar.addAll(rolsAtorgatsGrupISubgrups);
            }
            // Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);
            createTask(usuari);

            getSession(false).flush();
            auditarUsuari("C", usuari.getCodi(), usuari.getGrupPrimari()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UsuariEntityDaoImpl.errorCreating"), //$NON-NLS-1$
                    usuari.getCodi(), message));
        }
    }

    private void createTask(es.caib.seycon.ng.model.UsuariEntity usuari) {
        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_USER);
        tasque.setUsuari(usuari.getCodi());
        getTasqueEntityDao().create(tasque);
        tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.CREATE_FOLDER);
        tasque.setCarpet(usuari.getCodi());
        tasque.setTipcar("U"); //$NON-NLS-1$
        getTasqueEntityDao().create(tasque);
        tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_GROUP);
        tasque.setGrup(usuari.getGrupPrimari().getCodi());
        getTasqueEntityDao().create(tasque);
        if (usuari.getNomCurt() != null)
        {
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER_ALIAS);
            tasque.setUsuari(usuari.getCodi());
            getTasqueEntityDao().create(tasque);
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            getTasqueEntityDao().create(tasque);
        }
    }

    public void update(es.caib.seycon.ng.model.UsuariEntity usuari) throws RuntimeException {
        try {
            UsuariEntity actualUsuari = load (usuari.getId());

            if (usuari.getDominiCorreu() != null
                    && (actualUsuari.getDominiCorreu() == null || actualUsuari.getDominiCorreu()
                            .getCodi().compareTo(usuari.getDominiCorreu().getCodi()) != 0)
                    && usuari.getDominiCorreu().getObsolet() != null
                    && usuari.getDominiCorreu().getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.mailDomainNotFound"), usuari.getDominiCorreu()  //$NON-NLS-1$
                                .getCodi()));
            }
            // HERÈNCIA DE ROLS: Atorgació de rfindByCodi(usuari.getCodi())ols a grups
            // Obtenemos los roles otorgados al grupo primario
            // ANTES DE HACER LOS CAMBIOS EN EL USUARIO
            HashSet totGrup = new HashSet();
            GrupEntity grupPrimariAbans = getGrupEntityDao().findGrupPrimariByCodiUsuari(
                    usuari.getCodi());
            if (grupPrimariAbans != null)
                totGrup.add(grupPrimariAbans);
            usuari.setDataDarreraModificacio(GregorianCalendar.getInstance().getTime());
            super.update(usuari);
            getSession(false).flush();

            // HERÈNCIA DE ROLS: Atorgació de rols a grups
            // Només propaguem si es canvia el grup primari (codi es UK)
            if (usuari.getGrupPrimari() != null && grupPrimariAbans != null
                    && (!usuari.getGrupPrimari().getCodi().equals(grupPrimariAbans.getCodi()))) {

                // Obtenemos los roles otorgadfindByCodi(usuari.getCodi())os al grupo primario
                // TRAS EL CAMBIO (si se ha cambiado el grupo primario)
                GrupEntity grupPrimariU = usuari.getGrupPrimari();

                if (grupPrimariU != null)
                    totGrup.add(grupPrimariU);

                // Ara tenim tots els grups (d'abans del canvi i de després)
                // Podem propagar els rols dels grups anteriors i els nous
                HashSet rolsAPropagar = new HashSet();
                for (Iterator it = totGrup.iterator(); it.hasNext();) {
                    Object obj = it.next();
                    if (obj != null) {
                        GrupEntity g = (GrupEntity) obj;
                        Collection rolsAtorgatsGrupIPare = getRolsAtorgatsGrupIParesGrup(g);
                        if (rolsAtorgatsGrupIPare != null)
                            rolsAPropagar.addAll(rolsAtorgatsGrupIPare);
                    }
                }
                // Propagamos los roles de los grupos anteriores y los actuales:
                // (creamos las tareas)
                propagarRolsAtorgatsGrups(rolsAPropagar);
            }

            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER);
            tasque.setUsuari(usuari.getCodi());
            getTasqueEntityDao().create(tasque);
            if (! actualUsuari.getServidorOfimatic().getId().equals(usuari.getServidorOfimatic().getId()))
            {
                tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.CREATE_FOLDER);
                tasque.setCarpet(usuari.getCodi());
                tasque.setTipcar("U"); //$NON-NLS-1$
                getTasqueEntityDao().create(tasque);
            }
            if ( !actualUsuari.getGrupPrimari().getId().equals (usuari.getGrupPrimari().getId())) {
                tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.UPDATE_GROUP);
                tasque.setUsuari(usuari.getGrupPrimari().getCodi());
                getTasqueEntityDao().create(tasque);
                tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.UPDATE_GROUP);
                tasque.setUsuari(actualUsuari.getGrupPrimari().getCodi());
                getTasqueEntityDao().create(tasque);
            }
            tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_USER_ALIAS);
            tasque.setUsuari(usuari.getCodi());
            getTasqueEntityDao().create(tasque);
            if ( (usuari.getNomCurt() == null ? 
                        actualUsuari.getNomCurt() != null: 
                        ! usuari.getNomCurt().equals(actualUsuari.getNomCurt())) ||
                  (usuari.getDominiCorreu() == null ? 
                          actualUsuari.getDominiCorreu() != null: 
                          actualUsuari.getDominiCorreu() == null || 
                              ! usuari.getDominiCorreu().getId().equals(actualUsuari.getDominiCorreu().getId())))  
            {
                tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.UPDATE_LIST_ALIAS);
                tasque.setAlies(usuari.getNom());
                if (usuari.getDominiCorreu() != null)
                    tasque.setDomcor(usuari.getDominiCorreu().getCodi());
                getTasqueEntityDao().create(tasque);
            }

            getSession(false).flush();
            auditarUsuari("U", usuari.getCodi(), usuari.getGrupPrimari()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(String.format(
                    Messages.getString("UsuariEntityDaoImpl.errorUpdating"), usuari.getCodi(), message)); //$NON-NLS-1$
        }
    }

    public void remove(es.caib.seycon.ng.model.UsuariEntity usuari) throws RuntimeException {
        try {// ¿Esto se utiliza?.. en teoría NO
            String codiUsuari = usuari.getCodi();
            
            if (usuari.getCodi().equals(Security.getCurrentUser()))
                throw new SecurityException(Messages.getString("UsuariEntityDaoImpl.cannotGrantYourself")); //$NON-NLS-1$

            // HERÈNCIA DE ROLS: Atorgació de rols a grups
            // Obtenemos los roles otorgados al grupo primario y secundarios
            HashSet totGrup = new HashSet();
            GrupEntity grupPrimari = usuari.getGrupPrimari();
            if (grupPrimari != null)
                totGrup.add(grupPrimari);
            Collection grupsSecundaris = usuari.getGrupsSecundaris();
            if (grupsSecundaris != null)
                totGrup.add(grupsSecundaris);
            HashSet rolsAPropagar = new HashSet();
            for (Iterator it = totGrup.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj != null) {
                    GrupEntity g = (GrupEntity) obj;
                    Collection rolsAtorgatsGrupISubgrups = getRolsAtorgatsGrupIParesGrup(g);
                    if (rolsAtorgatsGrupISubgrups != null)
                        rolsAPropagar.addAll(rolsAtorgatsGrupISubgrups);
                }
            }
            super.remove(usuari);
            getSession(false).flush();
            // Herencia de Roles: Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);
            createTask(usuari);

            getSession(false).flush();
            auditarUsuari("D", codiUsuari, usuari.getGrupPrimari()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(
                    Messages.getString("UsuariEntityDaoImpl.errorDeleting"), usuari.getCodi(), message)); //$NON-NLS-1$
        }
    }

    public void toUsuari(es.caib.seycon.ng.model.UsuariEntity sourceEntity,
            es.caib.seycon.ng.comu.Usuari targetVO) {
        super.toUsuari(sourceEntity, targetVO);

        // Fem les transformacions necessàries

        // ALIES DE CORREU
        String aliesDeCorreu = ""; //$NON-NLS-1$
        for (Iterator iterator = sourceEntity.getLlistaDeCorreuUsuari().iterator(); iterator
                .hasNext();) {
            LlistaCorreuUsuariEntity llistaCorreuUsuariEntity = (LlistaCorreuUsuariEntity) iterator
                    .next();
            LlistaCorreuUsuari llistaCorreuUsuari = getLlistaCorreuUsuariEntityDao()
                    .toLlistaCorreuUsuari(llistaCorreuUsuariEntity);
            String nomLlista = llistaCorreuUsuari.getNomLlistaCorreu();
            String domini = llistaCorreuUsuari.getCodiDomini();
            aliesDeCorreu += nomLlista + (domini == null ? "" : "@" + domini) //$NON-NLS-1$ //$NON-NLS-2$
                    + (iterator.hasNext() ? ", " : ""); //$NON-NLS-1$ //$NON-NLS-2$
        }
        targetVO.setAliesCorreu(aliesDeCorreu);

        // OBTENIM EL NIF
        DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao()
                .findDadaByCodiUsuariAndCodiTipusDada(targetVO.getCodi(), "NIF"); //$NON-NLS-1$
        if (dadaUsuariEntity != null) {
            targetVO.setNIF(dadaUsuariEntity.getValorDada());
        } else {
            targetVO.setNIF(""); //$NON-NLS-1$
        }

        // TELÈFON
        dadaUsuariEntity = getDadaUsuariEntityDao().findDadaByCodiUsuariAndCodiTipusDada(
                targetVO.getCodi(), "PHONE"); //$NON-NLS-1$
        if (dadaUsuariEntity != null) {
            targetVO.setTelefon(dadaUsuariEntity.getValorDada());
        } else {
            targetVO.setTelefon(""); //$NON-NLS-1$
        }

        // DATA DE CREACIÓ
        Calendar calendar = GregorianCalendar.getInstance();
        if (sourceEntity.getDataCreacio() != null) {
            calendar.setTime(sourceEntity.getDataCreacio());
            targetVO.setDataCreacioUsuari(calendar);
        } else {// No debería pasar
            targetVO.setDataCreacioUsuari(null);
        }

        // DATA DE DARRERA MODIFICACIÓ
        calendar = GregorianCalendar.getInstance();
        if (sourceEntity.getDataDarreraModificacio() != null) {
            calendar.setTime(sourceEntity.getDataDarreraModificacio());
            targetVO.setDataDarreraModificacioUsuari(calendar);
        } else { // si no hay fecha de modificación
            targetVO.setDataDarreraModificacioUsuari(null);
        }

        // SERVIDORS DE L'USUARI
        targetVO.setServidorCorreu(sourceEntity.getServidorCorreu() == null ? null : sourceEntity
                .getServidorCorreu().getNom());
        targetVO.setServidorHome(sourceEntity.getServidorOfimatic() == null ? null : sourceEntity
                .getServidorOfimatic().getNom());
        targetVO.setServidorPerfil(sourceEntity.getServidorPerfil() == null ? null : sourceEntity
                .getServidorPerfil().getNom());

        // ACTIU?
        targetVO.setActiu(new Boolean(sourceEntity.getActiu().compareTo("S") == 0)); //$NON-NLS-1$

        // MULTISESSIO
        String multiSessio = sourceEntity.getMultiSessio();
        if (multiSessio != null) {
            targetVO.setMultiSessio(new Boolean(sourceEntity.getMultiSessio().compareTo("S") == 0)); //$NON-NLS-1$
        } else {
            targetVO.setMultiSessio(new Boolean(false));
        }

        // GRUP PRIMARI
        GrupEntity grupPrimariEntity = sourceEntity.getGrupPrimari();
        if (grupPrimariEntity != null) {
            String codiGrupPrimari = grupPrimariEntity.getCodi();
            targetVO.setCodiGrupPrimari(codiGrupPrimari);
            // A nivell descriptiu només:
            targetVO.setDescripcioGrupPrimari(grupPrimariEntity.getDescripcio());
        } else {
            targetVO.setCodiGrupPrimari(""); //$NON-NLS-1$
            targetVO.setDescripcioGrupPrimari(""); //$NON-NLS-1$
        }

        // DOMINI DE CORREU
        DominiCorreuEntity dominiCorreu = sourceEntity.getDominiCorreu();
        if (dominiCorreu != null) {
            targetVO.setDominiCorreu(dominiCorreu.getCodi());
        }

        // INFORMACIÓ DE SEU
        try {
            Collection<UsuariSEUEntity> infoSEU = sourceEntity.getInformacioSEU();
            if (infoSEU != null && infoSEU.size()>0) {
                UsuariSEU usuariSEU = getUsuariSEUEntityDao().toUsuariSEU(infoSEU.iterator().next());
                targetVO.setUsuariSEU(usuariSEU);
            }
        } catch (Throwable th) {
        }

        // DOMINI D'USUARIS
        // Camps de codi d'usuari als dominis
        /*
         * if (sourceEntity.getCodisUsuari() !=null) {
         * targetVO.setCodisUsuariDomini
         * (getCodiUsuariEntityDao().toCodiUsuariList
         * (sourceEntity.getCodisUsuari())); }
         */

        // Tipus d'usuari domini: segons taula
        if (sourceEntity.getTipusUsuari() != null) {
            TipusUsuari tipusu = getTipusUsuariEntityDao().toTipusUsuari(
                    sourceEntity.getTipusUsuari());
            targetVO.setTipusUsuari(tipusu.getCodi());
        }
        
        targetVO.setFullName(sourceEntity.getFullName());

    }

    /*
     * public String canviPassword(String codiUsuari) { String nouPassword = "";
     * try { CallableStatement stproc_stmt = super.getSession(false)
     * .connection().prepareCall("{? = call SC_ASIGNA_CONTRA(?)}");
     * stproc_stmt.registerOutParameter(1, Types.VARCHAR);
     * stproc_stmt.setString(2, codiUsuari); stproc_stmt.execute(); nouPassword
     * = stproc_stmt.getString(1); } catch (org.hibernate.HibernateException ex)
     * { throw super.convertHibernateAccessException(ex); } catch
     * (java.sql.SQLException e) { throw new
     * org.springframework.dao.InvalidDataAccessResourceUsageException(
     * e.getMessage()); } refresh(codiUsuari); //no fa res (és una query).. però
     * el deixem return nouPassword;
     * 
     * }
     */

    public String refreshCanvis(String codiUsuari) {
        String tasquesPendents = ""; //$NON-NLS-1$
        UsuariEntity usuari = findByCodi(codiUsuari);
        createTask(usuari);
        // tasquesPendents = refresh(codiUsuari);
        String[] tasques = getTasques(codiUsuari);
        if (tasques != null)
            for (int i = 0; i < tasques.length; i++) {
                tasquesPendents += tasques[i] + "\n"; // separador //$NON-NLS-1$
            }
        return tasquesPendents;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#toUsuari(es.caib.seycon.ng.model.UsuariEntity)
     */
    public es.caib.seycon.ng.comu.Usuari toUsuari(final es.caib.seycon.ng.model.UsuariEntity entity) {
        Usuari usuari = super.toUsuari(entity);
        return usuari;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.UsuariEntity loadUsuariEntityFromUsuari(
            es.caib.seycon.ng.comu.Usuari usuari) {
        es.caib.seycon.ng.model.UsuariEntity usuariEntity = null;
        if (usuari.getId() != null) {
            usuariEntity = load(usuari.getId());
        }
        if (usuariEntity == null) {
            usuariEntity = newUsuariEntity();
        }
        return usuariEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#usuariToEntity(es.caib.seycon.ng.comu.Usuari)
     */
    public es.caib.seycon.ng.model.UsuariEntity usuariToEntity(es.caib.seycon.ng.comu.Usuari usuari) {
        // @todo verify behavior of usuariToEntity
        es.caib.seycon.ng.model.UsuariEntity entity = this.loadUsuariEntityFromUsuari(usuari);
        this.usuariToEntity(usuari, entity, true);
        return entity;
    }

    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());

    }

    private boolean esPotCanviarGrupPrimari(String codiUsuari, String codiGrupPrimari) {
        if (codiGrupPrimari == null) {
            return true;
        }
        List<RolAccountEntity> rolsUsuaris = getRolAccountEntityDao().findByCodiUsuari(codiUsuari);
        // Obtenim els grups secundaris de l'usuari
        List<UsuariGrupEntity> grupsSecundarisEntity = getUsuariGrupEntityDao().findByCodiUsuari(codiUsuari);
        HashSet<String> grupsSec = new HashSet<String>();
        // Els afegim en un set
        if (grupsSecundarisEntity != null) {
            for (Iterator<UsuariGrupEntity> it = grupsSecundarisEntity.iterator(); it.hasNext();) {
                UsuariGrupEntity uge = (UsuariGrupEntity) it.next();
                if (uge.getGrup() != null)
                    grupsSec.add(uge.getGrup().getCodi());
            }
        }

        if (rolsUsuaris != null && rolsUsuaris.size() > 0) {
            Iterator<RolAccountEntity> iterator = rolsUsuaris.iterator();
            while (iterator.hasNext()) {
                RolAccountEntity rolUsuari = iterator.next();
                if (rolUsuari.getTipusDomini().compareTo(TipusDomini.GRUPS_USUARI) == 0) {
                    String codiGrupValorDomini = rolUsuari.getGrup().getCodi();
                    // Mirem que no el tinga com a grup secundari
                    if (codiGrupValorDomini.compareTo(codiGrupPrimari) == 0
                            && !grupsSec.contains(codiGrupValorDomini)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void usuariToEntityCustom(es.caib.seycon.ng.comu.Usuari sourceVO,
            es.caib.seycon.ng.model.UsuariEntity targetEntity) {
        // removeOldAlias(targetEntity);

        if (sourceVO.getDataDarreraModificacioUsuari() != null) {
            targetEntity.setDataDarreraModificacio(sourceVO.getDataDarreraModificacioUsuari()
                    .getTime());
        } else {
            targetEntity.setDataDarreraModificacio(GregorianCalendar.getInstance().getTime());
        }

        String dominiCorreu = sourceVO.getDominiCorreu();
        if (! sourceVO.getActiu())
        {
        	// Skip mail check
        }
        else if (dominiCorreu != null && dominiCorreu.trim().compareTo("") != 0) { //$NON-NLS-1$
            DominiCorreuEntity dominiCorreuEntity = getDominiCorreuEntityDao().findByCodi(
                    dominiCorreu);
            if (dominiCorreuEntity != null) {
                LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao()
                        .findByNomAndCodiDomini(sourceVO.getNomCurt(), sourceVO.getDominiCorreu());
                if (llistaCorreuEntity != null) {
                    throw new SeyconException(
                            String.format(
                                    Messages.getString("UsuariEntityDaoImpl.invalidShortName"), sourceVO.getNomCurt(), //$NON-NLS-1$
                                    sourceVO.getDominiCorreu()));
                }
                targetEntity.setDominiCorreu(dominiCorreuEntity);
            } else {
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.emailNotFound"), dominiCorreu)); //$NON-NLS-1$
            }
        } else {
            LlistaCorreuEntity llistaCorreuEntity = getLlistaCorreuEntityDao()
                    .findByNomAndCodiDomini(sourceVO.getNomCurt(), null);
            if (llistaCorreuEntity != null) {
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.mailListCollission"), //$NON-NLS-1$
                        sourceVO.getNomCurt()));
            }
            targetEntity.setDominiCorreu(null);
        }

        // NOTA: No admetem que no s'especifique el tipus d'usuari (!!)
        /*
         * if (sourceVO.getTipusUsuari() == null) {
         * targetEntity.setTipusUsuari("E"); }
         */

        if (sourceVO.getTipusUsuari() == null || "".equals(sourceVO.getTipusUsuari().trim())) { //$NON-NLS-1$
            throw new SeyconException(Messages.getString("UsuariEntityDaoImpl.needsUserType")); //$NON-NLS-1$
        } else {
            TipusUsuariEntity tipusUsuari = getTipusUsuariEntityDao().findByCodi(
                    sourceVO.getTipusUsuari());
            if (tipusUsuari == null) {
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.wrongUserType"), //$NON-NLS-1$
                        sourceVO.getTipusUsuari()));
            }
            targetEntity.setTipusUsuari(tipusUsuari);
        }

        MaquinaEntity maquina = null;
        String nomServidor = sourceVO.getServidorCorreu();
        if (nomServidor != null) {
            maquina = getMaquinaEntityDao().findByNom(sourceVO.getServidorCorreu());
            if (maquina == null) {
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.mailServerNotFound"), //$NON-NLS-1$
                        sourceVO.getServidorCorreu()));
            }
            targetEntity.setServidorCorreu(maquina);
        } else {
            targetEntity.setServidorCorreu(null);
        }

        nomServidor = sourceVO.getServidorPerfil();
        if (nomServidor != null) {
            maquina = getMaquinaEntityDao().findByNom(sourceVO.getServidorPerfil());
            if (maquina == null) {
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.profileServerNotFound"), //$NON-NLS-1$
                        sourceVO.getServidorPerfil()));
            }
            targetEntity.setServidorPerfil(maquina);
        } else {
            targetEntity.setServidorPerfil(null);
        }

        nomServidor = sourceVO.getServidorHome();
        if (nomServidor != null) {
            maquina = getMaquinaEntityDao().findByNom(nomServidor);
            if (maquina == null) {
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.homeServerNotFound"), //$NON-NLS-1$
                        sourceVO.getServidorHome()));
            }
            targetEntity.setServidorOfimatic(maquina);
        } else {
            targetEntity.setServidorOfimatic(null);
        }

        Boolean actiu = sourceVO.getActiu();
        if (actiu != null) {
            targetEntity.setActiu(sourceVO.getActiu().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setActiu("N"); //$NON-NLS-1$
        }

        Boolean multiSessio = sourceVO.getMultiSessio();
        if (multiSessio != null) {
            targetEntity.setMultiSessio(multiSessio.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setMultiSessio("N"); //$NON-NLS-1$
        }

        String codiGrupPrimari = sourceVO.getCodiGrupPrimari();
        GrupEntity grupPrimariAnticEntity = targetEntity.getGrupPrimari();
        String codiGrupPrimariAntic = grupPrimariAnticEntity == null ? null
                : grupPrimariAnticEntity.getCodi();
        if (codiGrupPrimari == null || codiGrupPrimariAntic == null
                || codiGrupPrimariAntic.compareTo(codiGrupPrimari) != 0) {
            if (!esPotCanviarGrupPrimari(sourceVO.getCodi(), codiGrupPrimariAntic)) {
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.cannotChangeGroup"), //$NON-NLS-1$
                        codiGrupPrimariAntic, sourceVO.getCodi(), codiGrupPrimariAntic));
            } else {
                if (codiGrupPrimari != null && codiGrupPrimari.trim().compareTo("") != 0) { //$NON-NLS-1$
                    GrupEntity grupPrimariEntity = getGrupEntityDao().findByCodi(codiGrupPrimari);
                    if (grupPrimariEntity != null) {
                        if (grupPrimariEntity.getObsolet().compareTo("S") == 0) { //$NON-NLS-1$
                            throw new SeyconException(
                                    String.format(
                                            Messages.getString("UsuariEntityDaoImpl.deletedGroup"), //$NON-NLS-1$
                                            codiGrupPrimari));
                        } else {
                            targetEntity.setGrupPrimari(grupPrimariEntity);
                        }
                    } else {
                        throw new SeyconException(String.format(
                                Messages.getString("UsuariEntityDaoImpl.groupNotFound"), codiGrupPrimari)); //$NON-NLS-1$
                    }
                } else {
                    targetEntity.setGrupPrimari(null);
                }
            }
        }

        String telefon = sourceVO.getTelefon();
        if (targetEntity.getId() != null) {
            if (telefon != null && !telefon.trim().equals("")) { //$NON-NLS-1$
                /*
                 * Solo se le inserta directamente el telefono si el usuario ya
                 * existe dado que el codigo de usuario aun es temporal
                 */
                DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao()
                        .findDadaByCodiUsuariAndCodiTipusDada(sourceVO.getCodi(), TELEFON);
                if (dadaUsuariEntity == null) {
                    /*
                     * El usuario no tiene telefono, se crea uno nuevo
                     */
                    DadaUsuari dadaUsuari = new DadaUsuari();
                    dadaUsuari.setCodiDada(TELEFON);
                    dadaUsuari.setCodiUsuari(sourceVO.getCodi());
                    dadaUsuari.setValorDada(sourceVO.getTelefon());
                    dadaUsuariEntity = getDadaUsuariEntityDao().dadaUsuariToEntity(dadaUsuari);
                    getDadaUsuariEntityDao().create(dadaUsuariEntity);
                } else {
                    /*
                     * El usuario ya teía un teléfono, se actualiza
                     */
                    dadaUsuariEntity.setValorDada(sourceVO.getTelefon());
                    getDadaUsuariEntityDao().update(dadaUsuariEntity);
                }
            } else {
                DadaUsuariEntity dadaUsuari = getDadaUsuariEntityDao()
                        .findDadaByCodiUsuariAndCodiTipusDada(sourceVO.getCodi(), TELEFON);
                if (dadaUsuari != null) {
                    getDadaUsuariEntityDao().remove(dadaUsuari);
                }
            }

            String nif = sourceVO.getNIF();
            if (nif != null && !nif.trim().equals("")) { //$NON-NLS-1$
                /*
                 * El nif no es nulo, hay que actualizarlo o añadirlo si no
                 * tenía
                 */
                DadaUsuariEntity dadaUsuariEntity = getDadaUsuariEntityDao()
                        .findDadaByCodiUsuariAndCodiTipusDada(sourceVO.getCodi(), NIF);
                if (dadaUsuariEntity != null) {
                    /* Actualizar el nif */
                    dadaUsuariEntity.setValorDada(nif);
                } else {
                    /* Añadir un nif */
                    /* Si el usuario ya existe... */
                    TipusDadaEntity tipusDada = getTipusDadaEntityDao().findTipusDadaByCodi(NIF);
                    DadaUsuari dadaUsuari = new DadaUsuari();
                    dadaUsuari.setCodiDada(NIF);
                    dadaUsuari.setCodiUsuari(sourceVO.getCodi());
                    dadaUsuari.setValorDada(sourceVO.getNIF());
                    dadaUsuariEntity = getDadaUsuariEntityDao().dadaUsuariToEntity(dadaUsuari);
                    getDadaUsuariEntityDao().create(dadaUsuariEntity);
                }
            } else {
                DadaUsuariEntity dadaUsuari = getDadaUsuariEntityDao()
                        .findDadaByCodiUsuariAndCodiTipusDada(sourceVO.getCodi(), NIF);
                if (dadaUsuari != null) {
                    getDadaUsuariEntityDao().remove(dadaUsuari);
                }
            }
        }

    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#usuariToEntity(es.caib.seycon.ng.comu.Usuari,
     *      es.caib.seycon.ng.model.UsuariEntity)
     */
    public void usuariToEntity(es.caib.seycon.ng.comu.Usuari sourceVO,
            es.caib.seycon.ng.model.UsuariEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of usuariToEntity
        super.usuariToEntity(sourceVO, targetEntity, copyIfNull);
        if (copyIfNull || sourceVO.getDataDarreraModificacioUsuari() != null) {
            if (sourceVO.getDataDarreraModificacioUsuari() == null) {
                targetEntity.setDataDarreraModificacio(null);
            } else {
                targetEntity.setDataDarreraModificacio(sourceVO.getDataDarreraModificacioUsuari()
                        .getTime());
            }
        }
        if (copyIfNull || sourceVO.getDataCreacioUsuari() != null) {
            if (sourceVO.getDataCreacioUsuari() == null) {
                targetEntity.setDataCreacio(null);
            } else {
                targetEntity.setDataCreacio(sourceVO.getDataCreacioUsuari().getTime());
            }
        }
        usuariToEntityCustom(sourceVO, targetEntity);
    }

    public String[] getTasques(String codiUsuari) {
        LinkedList lista = new LinkedList();
        List rsAgentsActius = null;
        List rsAgents = null;
        HashSet agentsActius = new HashSet();
        try {
            /* Obtenim el llistat dels agents actius en aquest moment: */
        	org.hibernate.Query queryObject = getSessionFactory().getCurrentSession()
                            .createQuery("SELECT tda.codi FROM es.caib.seycon.ng.model.DispatcherEntity as tda " + //$NON-NLS-1$
                            		"where tda.url is not null order by tda.codi"); //$NON-NLS-1$
            rsAgentsActius = queryObject.list();
 
            for (Iterator it = rsAgentsActius.iterator(); it.hasNext();){
            	String codiAgent = (String) it.next();
            	agentsActius.add(codiAgent);
            }

            for (TasqueEntity t: getTasqueEntityDao().query("select tasca from es.caib.seycon.ng.model.TasqueEntity as tasca where tasca.usuari=:usuari", new Parameter[]{ //$NON-NLS-1$
            			new Parameter("usuari", codiUsuari) //$NON-NLS-1$
            				
            }))
            {
            	String transaccion = t.getTransa();
            	Timestamp datatime = t.getData();
            	
            	Date data = new Date();
                data.setTime(datatime.getTime()); // Para formatearla
                SimpleDateFormat dateFormat = new SimpleDateFormat(Messages.getString("UsuariEntityDaoImpl.dateFormat")); //$NON-NLS-1$
                String dataString = dateFormat.format(data);
                String missatge = t.getMissat(); //$NON-NLS-1$
                String rol = t.getRole() + "@" + t.getBd(); //$NON-NLS-1$
                
                // Obtenemos información de los agentes pendientes
                HashSet hAgentsPendents = new HashSet(agentsActius); // copia
                String agentsPendents = ""; //$NON-NLS-1$
                for (TaskLogEntity tl: t.getLogs())
                {
                	String codiAgentActual = tl.getDispatcher().getCodi();
                    String esComplet = tl.getComplet();
                    if ("S".equals(esComplet)) //$NON-NLS-1$
                        hAgentsPendents.remove(codiAgentActual); // si ja és
                                                                 // complet: no
                                                                 // l'afegim
                }
                // Afegim els agents que encara estan pendents
                for (Iterator it = hAgentsPendents.iterator(); it.hasNext();){
                	agentsPendents += (String) it.next() + ", "; //$NON-NLS-1$
                }
                if (agentsPendents.endsWith(", ")) //$NON-NLS-1$
                    agentsPendents = agentsPendents.substring(0, agentsPendents.length() - 2);
                lista.add(transaccion + " # " + dataString + " # " + missatge + " # " + rol + " # " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                                + agentsPendents);
            }
   
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        } 

        String[] resultat = new String[lista.size()];
        for (int i = 0; i < resultat.length; i++) {
            resultat[i] = (String) lista.get(i);
        }
        return resultat;
    }

    private long contadorCodiUsuari = 1;
    private long generaCodiUsuariLong() {
    	return contadorCodiUsuari ++;
    }

    private long contadorCodiAlumne = 1;
    private long generaCodiAlumneLong() {
    	return contadorCodiAlumne ++;
    }

    private long contadorCodiMaquina = 1;
    private long generaCodiMaquinaLong() {
    	return contadorCodiMaquina ++;
    }

    private boolean existeixCodi(String codiUsuari) {
        try {
        	org.hibernate.Query queryObject = getSessionFactory().getCurrentSession()
                            .createQuery("SELECT tda.codi FROM es.caib.seycon.ng.model.UsuariEntity as tda " + //$NON-NLS-1$
                            		"where tda.codi="+codiUsuari); //$NON-NLS-1$
            List rs = queryObject.list();
            
            if (!rs.isEmpty()) {
                return true;
            }
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
        return false;
    }

    private String toStringCodiUsuari(long value) {
        String codiUsuari = String.valueOf(value);
        while (codiUsuari.length() < 5) {
            codiUsuari = "0" + codiUsuari; //$NON-NLS-1$
        }
        return "u" + codiUsuari; //$NON-NLS-1$
    }

    private String toStringCodiUsuari(String codiUsuari) {
        while (codiUsuari.length() < 5) {
            codiUsuari = "0" + codiUsuari; //$NON-NLS-1$
        }
        return "u" + codiUsuari; //$NON-NLS-1$
    }

    private String toStringCodiFarmacia(long value) {
        String codiUsuari = String.valueOf(value);
        while (codiUsuari.length() < 5) {
            codiUsuari = "0" + codiUsuari; //$NON-NLS-1$
        }
        return "m" + codiUsuari; //$NON-NLS-1$
    }

    private String toStringCodiFarmacia(String codiUsuari) {

        while (codiUsuari.length() < 5) {
            codiUsuari = "0" + codiUsuari; //$NON-NLS-1$
        }
        return "m" + codiUsuari; //$NON-NLS-1$
    }

    private String toStringCodiUsuariAnonim(long value) {
        String codiUsuari = String.valueOf(value);
        while (codiUsuari.length() < 5) {
            codiUsuari = "0" + codiUsuari; //$NON-NLS-1$
        }
        return "a" + codiUsuari; //$NON-NLS-1$
    }

    private String toStringCodiUsuariAnonim(String codiUsuari) {
        while (codiUsuari.length() < 5) {
            codiUsuari = "0" + codiUsuari; //$NON-NLS-1$
        }
        return "a" + codiUsuari; //$NON-NLS-1$
    }

    private String toStringCodiUsuariAlumne(long value) {
        String codiUsuari = String.valueOf(value);
        while (codiUsuari.length() < 5) {
            codiUsuari = "0" + codiUsuari; //$NON-NLS-1$
        }
        return "z" + codiUsuari; //$NON-NLS-1$
    }

    private String toStringCodiUsuariMaquina(long value) {
        String codiUsuari = String.valueOf(value);
        while (codiUsuari.length() < 5) {
            codiUsuari = "0" + codiUsuari; //$NON-NLS-1$
        }
        return "h" + codiUsuari; //$NON-NLS-1$
    }

    public String getSeguentCodi() {
        String valor = ""; //$NON-NLS-1$
        boolean continua = true;
        long valorActual;
        do {
            valorActual = generaCodiUsuariLong();
            valor = toStringCodiUsuari(valorActual);
            continua = existeixCodi(valor);
        } while (continua || valorActual < 80000);
        return valor;
    }

    public String getSeguentCodiFarmacia() {
        String valor = ""; //$NON-NLS-1$
        boolean continua = true;
        long valorActual;
        do {
            valorActual = generaCodiUsuariLong();
            valor = toStringCodiFarmacia(valorActual);
            continua = existeixCodi(valor);
        } while (continua || valorActual < 80000);
        return valor;
    }

    public String getSeguentCodiAnonim() {
        String valor = ""; //$NON-NLS-1$
        boolean continua = true;
        long valorActual;
        do {
            valorActual = generaCodiUsuariLong();
            valor = toStringCodiUsuariAnonim(valorActual);
            continua = existeixCodi(valor);
        } while (continua || valorActual < 80000);
        return valor;
    }

    public String getSeguentCodiAlumne() {
        String valor = ""; //$NON-NLS-1$
        boolean continua = true;
        long valorActual;
        do {
            valorActual = generaCodiAlumneLong();
            valor = toStringCodiUsuariAlumne(valorActual);
            continua = existeixCodi(valor);
        } while (continua /* || valorActual < 80000 */);
        return valor;
    }

    public String getSeguentCodiMaquina() {
        String valor = ""; //$NON-NLS-1$
        boolean continua = true;
        long valorActual;
        do {
            valorActual = generaCodiMaquinaLong();
            valor = toStringCodiUsuariMaquina(valorActual); // constrium hXXXXX
            continua = existeixCodi(valor);
        } while (continua /* || valorActual < 80000 */);
        return valor;
    }

    /**
     * @see es.caib.seycon.ng.model.GrupEntityDao#toIdentitat(es.caib.seycon.ng.model.GrupEntity,
     *      es.caib.seycon.ng.comu.Identitat)
     */
    public void toIdentitat(es.caib.seycon.ng.model.UsuariEntity source,
            es.caib.seycon.ng.comu.Identitat target) {
        super.toIdentitat(source, target);
        toIdentitatCustom(source, target);
    }

    public void toIdentitatCustom(es.caib.seycon.ng.model.UsuariEntity source,
            es.caib.seycon.ng.comu.Identitat target) {
        String codiUsuari = source.getCodi();
        target.setCodiUsuari(codiUsuari);
        target.setCodiIdentitat(codiUsuari);
        String descripcio = source.getNom() + " " + source.getPrimerLlinatge() + " " //$NON-NLS-1$ //$NON-NLS-2$
                + source.getSegonLlinatge();
        target.setDescripcio(descripcio);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#toIdentitat(es.caib.seycon.ng.model.UsuariEntity)
     */
    public es.caib.seycon.ng.comu.Identitat toIdentitat(
            final es.caib.seycon.ng.model.UsuariEntity entity) {
        Identitat identitat = super.toIdentitat(entity);
        toIdentitatCustom(entity, identitat);
        return identitat;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.UsuariEntity loadUsuariEntityFromIdentitat(
            es.caib.seycon.ng.comu.Identitat identitat) {
        /*
         * La identitat es read only
         */
        String codiUsuari = identitat.getCodiUsuari();
        if (codiUsuari != null) {
            UsuariEntity usuariEntity = findByCodi(codiUsuari);
            if (usuariEntity != null) {
                return usuariEntity;
            } else {
                throw new SeyconException(String.format(
                        Messages.getString("UsuariEntityDaoImpl.identityNotFound"), codiUsuari)); //$NON-NLS-1$
            }
        }
        throw new SeyconException(Messages.getString("UsuariEntityDaoImpl.identityNotUser")); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat)
     */
    public es.caib.seycon.ng.model.UsuariEntity identitatToEntity(
            es.caib.seycon.ng.comu.Identitat identitat) {
        es.caib.seycon.ng.model.UsuariEntity entity = this.loadUsuariEntityFromIdentitat(identitat);
        this.identitatToEntity(identitat, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat,
     *      es.caib.seycon.ng.model.UsuariEntity)
     */
    public void identitatToEntity(es.caib.seycon.ng.comu.Identitat source,
            es.caib.seycon.ng.model.UsuariEntity target, boolean copyIfNull) {
        super.identitatToEntity(source, target, copyIfNull);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#find(int, java.lang.String,
     *      es.caib.seycon.ng.model.Parameter[])
     */
    public List<UsuariEntity> find(final java.lang.String queryString,
            final es.caib.seycon.ng.model.Parameter[] parameters) {
        try {
            java.util.List results = new QueryBuilder().query(this, queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    public UsuariEntity usuariAnonimToEntity(UsuariAnonim usuariAnonim) {
        // TODO Auto-generated method stub
        return null;
    }

    public void toUsuariAnonim(UsuariEntity source, UsuariAnonim target) {
        String correu = null;
        // busquem l'email a les dades addicionals
        Collection dades = findDadesByCodi(source.getCodi());
        Iterator it = dades.iterator();
        while (it.hasNext()) {
            DadaUsuariEntity dada = (DadaUsuariEntity) it.next();
            if (SeyconLogon_EMAIL_ADD_CODE.equals(dada.getTipusDada().getCodi())) {
                correu = dada.getValorDada();

                break;
            }
        }
        if (correu == null) {
            target = null;
            return;
        }

        // omplim els altres camps
        target.setCodiUsuari(source.getCodi());
        target.setLlinatge1(source.getPrimerLlinatge());
        target.setLlinatge2(source.getSegonLlinatge());
        target.setNom(source.getNom());
        target.setCorreuElectronic(correu);

    }

    public List<UsuariEntity> findUsuarisGrupISubgrupsByCodiGrup( // Sobreescribimos
                                                                  // el DAO
            String codiGrup) {// Correcto??
        // Obtenim els subgrups del grup (GrupEntity) [directes]
        Collection grupsISubgrups = getGrupEntityDao().findSubGrupsByCodi(codiGrup);

        // Caso de que no tenga subgrupos
        if (grupsISubgrups == null)
            grupsISubgrups = new ArrayList();

        // Añadimos el grupo Inicial
        GrupEntity grup = getGrupEntityDao().findByCodi(codiGrup);
        if (grup != null)
            grupsISubgrups.add(grup);

        // Conté els codi dels usuaris a propagar:
        HashSet usuarisPropagar = new HashSet();

        // Cerquem els usuaris que tenen com a grup primari qualque grup del
        // llistat, i els que els tenen com a grup secundari
        for (Iterator it = grupsISubgrups.iterator(); it.hasNext();) {
            GrupEntity g = (GrupEntity) it.next();
            // usuarios de grupo primario
            Collection usuGPrim = null;
            // tipo UsuariEntity:
            usuGPrim = findByGrupPrimari(g.getCodi());
            if (usuGPrim != null)
                for (Iterator gpr_it = usuGPrim.iterator(); gpr_it.hasNext();) {
                    UsuariEntity usu = (UsuariEntity) gpr_it.next();
                    usuarisPropagar.add(usu);
                }

            // Usuarios de grupo secundario (tipo UsuariGrupEntity):
            Collection usuSec = getUsuariGrupEntityDao().findByCodiGrup(g.getCodi());
            if (usuSec != null)
                for (Iterator gps_it = usuSec.iterator(); gps_it.hasNext();) {
                    UsuariGrupEntity usugru = (UsuariGrupEntity) gps_it.next();
                    usuarisPropagar.add(usugru.getUsuari());
                }
        }

        // Devolvemos los usuariEntity:
        return new ArrayList(usuarisPropagar);

    }

    public Collection findUsuarisByRolUsuariAtorgat(String nomRolAtorgat,
            String baseDeDadesRolAtorgat, String codiAplicacioRolAtorgat, String tipusDomini,
            String codiGrupDominiRolAtorgat, String codiAplicacioDominiRolAtorgat,
            String idValorDominiAplicacioDominiRolAtorgat) {// Correcte??

        LinkedList<UsuariEntity> usuarisRol = new LinkedList<UsuariEntity>();

        for (RolAccountEntity rolAccount: getRolAccountEntityDao().findByRolAndValorDomini(
                    nomRolAtorgat, baseDeDadesRolAtorgat, codiAplicacioRolAtorgat, tipusDomini,
                    codiGrupDominiRolAtorgat, codiAplicacioDominiRolAtorgat,
                    new Long(idValorDominiAplicacioDominiRolAtorgat))) 
        {
            	AccountEntity acc = rolAccount.getAccount();
            	if (acc.getType().equals (AccountType.USER) &&
            			acc.getUsers().size() == 1)
            	{
            		usuarisRol.add ( acc.getUsers().iterator().next().getUser());
            	}
        }

        return usuarisRol;
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
        Collection fillsGrup = getParesGrup(grup);
        totGrup.addAll(fillsGrup);

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

    private static long requestNumber = System.currentTimeMillis();
    private static String requestNumberLock = new String();
    public String getSeguentNumSolicitudVerificarIdentitatUsuari() {
    	synchronized (requestNumberLock)
    	{
    		return Long.toString(requestNumber++);
    	}
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariEntity) {
                    UsuariEntity entity = (UsuariEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariEntity) {
                    UsuariEntity entity = (UsuariEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof UsuariEntity) {
                    UsuariEntity entity = (UsuariEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public UsuariEntity bPMUserToEntity(BPMUser bPMUser) {
		return null;
	}

    public void toBPMUser(
        es.caib.seycon.ng.model.UsuariEntity source,
        es.caib.bpm.vo.BPMUser target)
    {
    	target.setUserName(source.getCodi());
    	target.setGivenName(source.getNom());
    	target.setGroup(source.getGrupPrimari().getCodi());
    	target.setSurName(source.getPrimerLlinatge()+ (source.getSegonLlinatge() == null? "" : " "+source.getSegonLlinatge())); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    
}
