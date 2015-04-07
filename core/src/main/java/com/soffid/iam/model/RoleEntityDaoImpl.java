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

import com.soffid.iam.model.ApplicationDomainEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleDependencyEntityDaoImpl;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.servei.GrupServiceImpl;
import es.caib.seycon.ng.servei.ejb.GrupService;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.ng.utils.TipusContenidorRol;
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
import java.util.List;
import java.util.Vector;

public class RoleEntityDaoImpl extends com.soffid.iam.model.RoleEntityDaoBase {

    private void auditarRol(String accio, String nomRol, String codiAplicacio,
            String bbdd) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio);
        auditoria.setAplicacio(codiAplicacio);
        auditoria.setRol(nomRol);
        auditoria.setAutor(codiUsuari);
        auditoria.setBbdd(bbdd);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
                .getTime()));
        auditoria.setObjecte("SC_ROLES"); //$NON-NLS-1$
        AuditEntity auditoriaEntity = getAuditEntityDao().auditoriaToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void create(com.soffid.iam.model.RoleEntity rol) throws RuntimeException {
        try {
            // Importante: PRIMERO CREAMOS LA ENTIDAD ROL (obtenemos id)
            super.create(rol); // Creamos la entidad
            getSession(false).flush();

            // Creamos las relaciones con los roles padre (a qui sóc atorgat) y
            // con los grupos
            // Creamos la asociación con el rol (sóc contingut-atorgat)
            if (rol.getRoleAssociationContent() != null)
                for (Iterator it = rol.getRoleAssociationContent().iterator(); it.hasNext(); ) {
                RoleDependencyEntity associacio = (RoleDependencyEntity) it.next();
                StringBuffer cami = new StringBuffer("");
                if (RoleDependencyEntityDaoImpl.verificaAssociacioSenseCicles(associacio, cami)) {
                    getRoleDependencyEntityDao().create(associacio);
                } else {
                    throw new Exception(String.format(Messages.getString("RoleEntityDaoImpl.0"), associacio.getRoleContent().toRoleDescription(), associacio.getRoleContainer().toRoleDescription(), cami));
                }
            }
            // Creamos la asociación con los grupos (directamente)
            Collection grupsPosseidors = rol.getGroupsOwnerRole();
            if (grupsPosseidors != null)
                for (Iterator it = grupsPosseidors.iterator(); it.hasNext(); ) {
                RoleGroupEntity rolsgrup = (RoleGroupEntity) it.next();
                getRoleGroupEntityDao().create(rolsgrup);
            }

            // Propaguem usuaris, grups i rols
            // Herencia:
            // ROL: Atorgació del rol (aquest rol) a un altre rol (contenidor) :
            // hem de fer
            // updateRole(contenidor) i
            // updateUser(per_a_tot_usuari_ROL_contenidor)
            // GRUP: atorgació del rol (aquest rol) a un grup: hem de fer
            // updateUser(per_a_tot_usuari_GRUP_i_SUBGRUPS_del_GRUP_posseidor)
            // updateGrup(grup_posseidor_i_SUBGRUPS)
            // Els usuaris ho fem només una vegada
            HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();
            HashSet<com.soffid.iam.model.AccountEntity> accountsPropagar = new HashSet<com.soffid.iam.model.AccountEntity>();
            HashSet<RoleEntity> rolsPropagar = new HashSet<RoleEntity>();
            HashSet<GroupEntity> grupsPropagar = new HashSet<GroupEntity>();
            // Obtenim el resultat de rols a la creació del rol
            getHerenciaRol_Usuaris_Rols_Grups(rol, usuarisPropagar, accountsPropagar,
                    rolsPropagar, grupsPropagar, false);
            propagarUsuarisRolsIGrups(usuarisPropagar, accountsPropagar, rolsPropagar,
                    grupsPropagar);
            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_ROLE);
            tasque.setRole(rol.getName());
            tasque.setDb(rol.getDatabases().getCode());
            getTaskEntityDao().createNoFlush(tasque);

            getSession(false).flush();
            auditarRol("C", rol.getName(), rol.getApplication().getCode(), rol.getDatabases().getCode());
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.1"), rol.getName(), message));  //$NON-NLS-1$
        }
    }

    @Override
    protected void handleUpdateMailLists(RoleEntity role) throws InternalErrorException {
        updateMailLists (role, 10);
    }
    
    private void updateMailLists(RoleEntity role, int depth) throws InternalErrorException {
    	for (MailListRoleMemberEntity lce : role.getMailLists()) {
            getEmailListEntityDao().generateUpdateTasks(lce.getMailList());
        }
    	if (depth > 0)
    	{
    		for (RoleDependencyEntity child : role.getRolAssociationContainer()) {
                updateMailLists(child.getRoleContent(), depth - 1);
            }
    	}
    }
    
    public void update(com.soffid.iam.model.RoleEntity rol) throws RuntimeException {
        try {
            RoleEntity oldRol = load(rol.getId());
            // Actualitzem el rol a la base de dades
            super.update(rol);
            getSession(false).flush();

            // 0) Obtenim els usuaris, grups i rols afectats abans del canvi
            HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();
            HashSet<com.soffid.iam.model.AccountEntity> accountsPropagar = new HashSet<com.soffid.iam.model.AccountEntity>();
            HashSet<RoleEntity> rolsPropagar = new HashSet<RoleEntity>();
            HashSet<GroupEntity> grupsPropagar = new HashSet<GroupEntity>();
            // Obtenim informació del rol abans de fer l'update (darrer
            // paràmetre a true)
            getHerenciaRol_Usuaris_Rols_Grups(rol, usuarisPropagar, accountsPropagar,
                    rolsPropagar, grupsPropagar, true);

            // Comprobamos los ciclos (antes de crearlos) en rol-rol
            if (rol.getRoleAssociationContent() != null)
                for (Iterator it = rol.getRoleAssociationContent().iterator(); it.hasNext(); ) {
                RoleDependencyEntity relacio = (RoleDependencyEntity) it.next();
                StringBuffer cami = new StringBuffer("");
                if (!RoleDependencyEntityDaoImpl.verificaAssociacioSenseCicles(relacio, cami)) {
                    throw new Exception(String.format(Messages.getString("RoleEntityDaoImpl.0"), relacio.getRoleContent().toRoleDescription(), relacio.getRoleContainer().toRoleDescription(), cami));
                }
            }

            // Relaciones Rol-Rol
            // 1) Obtenemos la lista actual de relaciones rol-rol
            Collection rolAsocRolActual = rol.getRoleAssociationContent();
            // 2) Hacemos una copia (para trabajar con ella)
            ArrayList copiaRolAsocRolActual = new ArrayList(rolAsocRolActual);

            // 3) Obtenemos las relaciones con otros roles ya existentes
            // en la Entidad (desde la base de datos)
            Collection rolAssociacioRolContingutAbans = getRoleDependencyEntityDao().findRolesAssociationContainerRole(rol); // atorgat

            // Compraramos con los existentes anteriormente : i esborrem els que
            // ja no existeixen
            if (rolAssociacioRolContingutAbans != null) {
                Iterator itrol = rolAssociacioRolContingutAbans.iterator();
                while (itrol.hasNext()) {
                    RoleDependencyEntity relacio = (RoleDependencyEntity) itrol.next();
                    boolean trobat = false;
                    Iterator rit = copiaRolAsocRolActual.iterator();
                    while (!trobat && rit.hasNext()) {
                        RoleDependencyEntity rar = (RoleDependencyEntity) rit.next();
                        if (rar.equals(relacio)) { // Comparem..
                            trobat = true;
                            rit.remove();// l'eliminem de la còpia (per no
                                         // crear-lo
                                         // de nou)
                        }
                    }
                    if (!trobat) {
                        // L'eliminem de la base de dades (ja no existeix)
                        getRoleDependencyEntityDao().remove(relacio);
                    }
                }
            }
            // Creem la resta
            if (copiaRolAsocRolActual.size() != 0)
                getRoleDependencyEntityDao().create(copiaRolAsocRolActual);

            // Relació amb GRUPS
            // 1) Obtenemos la lista actual de atorgar rol-grup
            Collection rolsGrupActual = rol.getGroupsOwnerRole();
            // 2) Hacemos una copia (para trabajar con ella)
            ArrayList copiaRolAsocGrupActual = new ArrayList(rolsGrupActual);
            // 3) Obtenemos las relaciones con otros grupos ya existentes
            // en la Entidad (desde la base de datos)
            Collection rolsGrupAbans = getRoleGroupEntityDao().findOwnerGroupsByRole(rol);
            Iterator itgrup = rolsGrupAbans.iterator();
            while (itgrup.hasNext()) {
                RoleGroupEntity relacio = (RoleGroupEntity) itgrup.next();
                boolean trobat = false;
                Iterator git = copiaRolAsocGrupActual.iterator();
                while (!trobat && git.hasNext()) {
                    RoleGroupEntity rag = (RoleGroupEntity) git.next();
                    if (rag.getOwnerGroup().getId().equals(relacio.getOwnerGroup().getId())) { // Comparem
                                                                           // el
                                                                           // grup..
                        trobat = true;
                        git.remove(); // l'eliminem (ja existeix: no tornarem a
                                      // crear la relació)
                    }
                }
                if (!trobat) {
                    // L'eliminem de la base de dades (ja no existeix la relació
                    // R-G)
                    getRoleGroupEntityDao().remove(relacio);
                }
            }
            // Creem la resta
            if (copiaRolAsocGrupActual.size() != 0)
                getRoleGroupEntityDao().create(copiaRolAsocGrupActual);

            getSession(false).flush();
            auditarRol("U", rol.getName(), rol.getApplication().getCode(), rol.getDatabases().getCode());

            // Obtenim el rol una vegada s'hagi actualitzat (conté els afectats
            // abans del canvi)
            HashSet<UserEntity> usuarisPropagarAfter = new HashSet<UserEntity>();
            HashSet<com.soffid.iam.model.AccountEntity> accountsPropagarAfter = new HashSet<com.soffid.iam.model.AccountEntity>();
            HashSet<RoleEntity> rolsPropagarAfter = new HashSet<RoleEntity>();
            HashSet<GroupEntity> grupsPropagarAfter = new HashSet<GroupEntity>();

            getHerenciaRol_Usuaris_Rols_Grups(rol, usuarisPropagarAfter,
                    accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

            // En update, si no se modifica la tabla sc_roles, no se lanza un
            // updateRole

            // Ara fem la diferència entre els usuaris d'abans i els nous
            // Clonem els de després:
            // USUARIS:
            HashSet<UserEntity> usuarisBorrar = new HashSet<UserEntity>(usuarisPropagar); // abans
            usuarisBorrar.removeAll(usuarisPropagarAfter);// deixen només els
                                                          // usus que ja no
                                                          // tenen el rol

            HashSet<UserEntity> usuarisAfegir = new HashSet<UserEntity>(usuarisPropagarAfter); // després
            usuarisAfegir.removeAll(usuarisPropagar); // deixem els nous
                                                      // (eliminem els q es
                                                      // mantenen)

            // Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
            // d'actualitzar tots dos)
            usuarisAfegir.addAll(usuarisBorrar);

            // ACCOUNTS:
            HashSet<com.soffid.iam.model.AccountEntity> accountsBorrar = new HashSet<com.soffid.iam.model.AccountEntity>(accountsPropagar); // abans
            accountsBorrar.removeAll(accountsPropagarAfter);// deixen només els
                                                          // usus que ja no
                                                          // tenen el rol

            HashSet<com.soffid.iam.model.AccountEntity> accountsAfegir = new HashSet<com.soffid.iam.model.AccountEntity>(accountsPropagarAfter); // després
            accountsAfegir.removeAll(accountsPropagar); // deixem els nous
                                                      // (eliminem els q es
                                                      // mantenen)

            // Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
            // d'actualitzar tots dos)
            usuarisAfegir.addAll(usuarisBorrar);

 
            // ROLS:
            HashSet<RoleEntity> rolsBorrar = new HashSet<RoleEntity>(rolsPropagar); // abans
            rolsBorrar.removeAll(rolsPropagarAfter); // deixem només els rols q
                                                     // ja no estan

            HashSet<RoleEntity> rolsAfegir = new HashSet<RoleEntity>(rolsPropagarAfter); // després
            rolsAfegir.removeAll(rolsPropagar); // deixem els nous (eliminem els
                                                // q es mantenen)

            // Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
            // d'actualitzar tots dos)
            rolsAfegir.addAll(rolsBorrar);

            // GRUPS:
            HashSet<GroupEntity> grupsBorrar = new HashSet<GroupEntity>(grupsPropagar); // abans
            grupsBorrar.removeAll(grupsPropagarAfter);

            HashSet<GroupEntity> grupsAfegir = new HashSet<GroupEntity>(grupsPropagarAfter); // després
            grupsAfegir.removeAll(grupsPropagar);

            grupsAfegir.addAll(grupsBorrar);

            // I fem la propagació: només dels que siguen "nous"
            propagarUsuarisRolsIGrups(usuarisAfegir, accountsAfegir, rolsAfegir, grupsAfegir);

            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_ROLE);
            tasque.setRole(rol.getName());
            tasque.setDb(rol.getDatabases().getCode());
            getTaskEntityDao().createNoFlush(tasque);
            if (!rol.getName().equals(oldRol.getName()) || !rol.getDatabases().getId().equals(oldRol.getDatabases().getId()))
            {
                tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_ROLE);
                tasque.setRole(oldRol.getName());
                tasque.setDb(oldRol.getDatabases().getCode());
                getTaskEntityDao().createNoFlush(tasque);
            }
            getSession(false).flush();

            updateMailLists (rol);
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.2"), rol.getName(), message));  //$NON-NLS-1$
        }
    }

    public void remove(com.soffid.iam.model.RoleEntity rol) throws RuntimeException {
        try {
            updateMailLists (rol);
            // NO SE PUEDE BORRAR UN ROL SI TIENE RELACIONES EXTERNAS
            // SE DA UN AVISO Y NO SE DEJA BORRAR EL ROL

            // Obtenemos sus relaciones con otros roles (como contenedor o
            // contenido)
            Collection rolAssociacioRolSocContenidor = rol.getRolAssociationContainer();
            Collection rolAssociacioRolSocContingut = rol.getRoleAssociationContent();
            Collection grupsPosseidors = rol.getGroupsOwnerRole();
//            Collection rolFitxers = rol.getRolFitxers();
            Collection rolsUsuari = rol.getAccounts();
            Collection rolsAutoritzacioXarxa = rol.getNetworkAuthorization();

            String msgError = ""; //$NON-NLS-1$
            if (rolAssociacioRolSocContenidor.size() != 0) {
                msgError += Messages.getString("RoleEntityDaoImpl.3");  //$NON-NLS-1$
            } else if (rolAssociacioRolSocContingut.size() != 0) {
                msgError += Messages.getString("RoleEntityDaoImpl.4");  //$NON-NLS-1$
            } else if (grupsPosseidors.size() != 0) {
                msgError += Messages.getString("RoleEntityDaoImpl.5");  //$NON-NLS-1$
//          } else if (rolFitxers.size() != 0) {
//                msgError += Messages.getString("RoleEntityDaoImpl.6");  //$NON-NLS-1$
            } else if (rolsUsuari.size() != 0) {
                msgError += Messages.getString("RoleEntityDaoImpl.7");  //$NON-NLS-1$
            } else if (rolsAutoritzacioXarxa.size() != 0) {
                msgError += Messages.getString("RoleEntityDaoImpl.8");  //$NON-NLS-1$
            } else if (rol.getNotificationEntities().size() != 0) {
                msgError += Messages.getString("RoleEntityDaoImpl.9");  //$NON-NLS-1$
            }

            // Generamos error si se cumple alguna de las condiciones
            if (!"".equals(msgError)) //$NON-NLS-1$
                throw new Exception(String.format(
                        Messages.getString("RoleEntityDaoImpl.10"), msgError));  //$NON-NLS-1$

            // Eliminamos las asociaciones con otros ROLES (en ambos casos)
            // No se borra porque NO PUEDEN EXISTIR para poder borrar el rol(!!)
            /*
             * for (Iterator it= rolAssociacioRolSocContenidor.iterator();
             * it.hasNext();) { // El método remove(Collection) can be a little
             * bit dangerous RolAssociacioRolEntity associacio =
             * (RolAssociacioRolEntity) it.next();
             * getRolAssociacioRolEntityDao().remove(associacio); }
             * rolAssociacioRolSocContenidor.clear();
             * 
             * 
             * for (Iterator it = rolAssociacioRolSocContingut.iterator();
             * it.hasNext();) { RolAssociacioRolEntity associacio =
             * (RolAssociacioRolEntity) it.next();
             * getRolAssociacioRolEntityDao().remove(associacio); }
             * rolAssociacioRolSocContingut.clear();
             * 
             * // Eliminamos relaciones con roles (padres) y con grupos //
             * Obtenemos las relaciones con GRUPOS for (Iterator it=
             * grupsPosseidors.iterator(); it.hasNext();) { RolsGrupEntity
             * rolsgrup = (RolsGrupEntity) it.next();
             * getRolsGrupEntityDao().remove(rolsgrup); }
             * grupsPosseidors.clear();
             * 
             * // Roles: relaciones con Ficheros for (Iterator it =
             * rolFitxers.iterator(); it.hasNext();) { RolFitxerEntity rolfitxer
             * = (RolFitxerEntity) it.next();
             * getRolFitxerEntityDao().remove(rolfitxer); } rolFitxers.clear();
             * 
             * // Realaciones con Usuarios for (Iterator it =
             * rolsUsuari.iterator(); it.hasNext();) { RolsUsuarisEntity
             * rolusuari = (RolsUsuarisEntity) it.next();
             * getRolsUsuarisEntityDao().remove(rolusuari); }
             * rolsUsuari.clear(); // Relaciones con XarxaAC for (Iterator it =
             * rolsAutoritzacioXarxa.iterator(); it.hasNext();) { XarxaACEntity
             * autoritzacio = (XarxaACEntity) it.next();
             * getXarxaACEntityDao().remove (autoritzacio); }
             */

            String nomRol = rol.getName();
            String codiBaseDeDades = rol.getDatabases().getCode();
            String codiAplicacio = rol.getApplication().getCode();

            // Abans d'eliminar el rol, obtenim els grups, rols i usuaris
            // afectats indirectament (per herència)
            HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();
            HashSet<com.soffid.iam.model.AccountEntity> accountsPropagar = new HashSet<com.soffid.iam.model.AccountEntity>();
            HashSet<RoleEntity> rolsPropagar = new HashSet<RoleEntity>();
            HashSet<GroupEntity> grupsPropagar = new HashSet<GroupEntity>();
            // Obtenim el resultat de rols a la creació del rol
            // Ho cerquem a la base de dades (relació amb rols-grups): encara
            // que no hauria de tindre cap relació.. per si de cas
            getHerenciaRol_Usuaris_Rols_Grups(rol, usuarisPropagar, accountsPropagar,
                    rolsPropagar, grupsPropagar, true);

            super.remove(rol);
            getSession(false).flush();

            // Propaguem els canvis d'eliminar l'herència:
            propagarUsuarisRolsIGrups(usuarisPropagar, accountsPropagar, rolsPropagar,
                    grupsPropagar);

            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_ROLE);
            tasque.setRole(rol.getName());
            tasque.setDb(rol.getDatabases().getCode());
            getTaskEntityDao().createNoFlush(tasque);
            auditarRol("D", nomRol, codiAplicacio, codiBaseDeDades); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.11"), rol.getName(), message));  //$NON-NLS-1$
        }
    }

    public void toRol(com.soffid.iam.model.RoleEntity sourceEntity, es.caib.seycon.ng.comu.Rol targetVO) {
        super.toRol(sourceEntity, targetVO);
        toRolCustom(sourceEntity, targetVO);
    }

    private void toRolCustom(com.soffid.iam.model.RoleEntity sourceEntity, es.caib.seycon.ng.comu.Rol targetVO) {

        // Obtenemos la relación entre este rol y los grupos (1:N)
        Collection grupsEntityPosseidors = sourceEntity.getGroupsOwnerRole(); // tipo
                                                                                 // RolsGrupEntity
        Collection<Grup> grupsPosseidors = new ArrayList<Grup>(); // tipo Grup
        Collection<RolGrant> granteeGroups = new ArrayList<RolGrant>(); // tipo Grup
        if (grupsEntityPosseidors != null) {
            for (Iterator it = grupsEntityPosseidors.iterator(); it.hasNext(); ) {
                RoleGroupEntity rg = (RoleGroupEntity) it.next();
                if (rg.getOwnerGroup() != null) {
                    GroupEntity posseidor = (GroupEntity) rg.getOwnerGroup();
                    Grup grupo = getGroupEntityDao().toGrup(posseidor);
                    grupsPosseidors.add(grupo);
                    granteeGroups.add(getRoleGroupEntityDao().toRolGrant(rg));
                }
            }
        }
        targetVO.setOwnerGroups(grupsPosseidors);
        targetVO.setGranteeGroups(granteeGroups);

        String tipusDomini = sourceEntity.getDomainType();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0) {
            ApplicationDomainEntity dominiAplicacioEntity = sourceEntity.getApplicationDomain();
            Domini domini = getApplicationDomainEntityDao().toDomini(dominiAplicacioEntity);
            targetVO.setDomini(domini);
        } else if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0
                || tipusDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0) {
            Domini domini = new Domini();
            domini.setCodiExtern(null);
            if (tipusDomini.compareTo(TipusDomini.GRUPS) == 0) {
                domini.setNom(TipusDomini.GRUPS);
                domini.setDescripcio(TipusDomini.Descripcio.GRUPS);
            } else {
                domini.setNom(TipusDomini.GRUPS_USUARI);
                domini.setDescripcio(TipusDomini.Descripcio.GRUPS_USUARI);
            }
            targetVO.setDomini(domini);
        } else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0) {
            Domini domini = new Domini();
            domini.setNom(TipusDomini.APLICACIONS);
            domini.setDescripcio(TipusDomini.Descripcio.APLICACIONS);
            targetVO.setDomini(domini);
        } else /* tipusDomini == SENSE_DOMINI */{
            Domini senseDomini = new Domini();
            senseDomini.setNom(TipusDomini.SENSE_DOMINI);
            senseDomini.setDescripcio(TipusDomini.Descripcio.SENSE_DOMINI);
            targetVO.setDomini(senseDomini);
        }

        InformationSystemEntity aplicacioEntity = sourceEntity.getApplication();
        if (aplicacioEntity != null) {
            targetVO.setCodiAplicacio(aplicacioEntity.getCode());
        }
        targetVO.setDefecte(new Boolean(sourceEntity.getDefaultRole().compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setGestionableWF(new Boolean(sourceEntity.getManageableWF().compareTo("S") == 0)); //$NON-NLS-1$
        String contrasenya = sourceEntity.getPassword();
        if (contrasenya != null && contrasenya.trim().compareTo("") != 0) { //$NON-NLS-1$
            targetVO.setContrasenya(new Boolean(sourceEntity.getPassword().compareTo("S") == 0)); //$NON-NLS-1$
        }
        SystemEntity baseDeDades = sourceEntity.getDatabases();
        if (baseDeDades != null) {
            targetVO.setBaseDeDades(baseDeDades.getCode());
        }

        // Obtenemos los roles padres (en los que estamos contenidos) - somos
        // otorgados
        Collection<RoleDependencyEntity> pares = sourceEntity.getRoleAssociationContent();
        Collection<RolGrant> rolsPosseidorsRol = new LinkedList<RolGrant>();
        if (pares != null) {
            for (Iterator<RoleDependencyEntity> iterator = pares.iterator(); iterator.hasNext(); ) {
                RoleDependencyEntity currentPareRolAssociacioRol = (RoleDependencyEntity) iterator.next();
                RolGrant rg = getRoleDependencyEntityDao().toRolGrant(currentPareRolAssociacioRol);
                rolsPosseidorsRol.add(rg);
            }
        }
        targetVO.setOwnerRoles(rolsPosseidorsRol);

        // Obtenim els rols que tinc atorgats com a "fills"
        Collection<RoleDependencyEntity> fills = sourceEntity.getRolAssociationContainer();
        Collection<RolGrant> rolsAtorgatsRol = new LinkedList<RolGrant>();
        if (fills != null) {
            for (Iterator<RoleDependencyEntity> iterator = fills.iterator(); iterator.hasNext(); ) {
                RoleDependencyEntity currentPareRolAssociacioRol = (RoleDependencyEntity) iterator.next();
                RolGrant rg = getRoleDependencyEntityDao().toRolGrant(currentPareRolAssociacioRol);
                rolsAtorgatsRol.add(rg);
            }
        }
        targetVO.setOwnedRoles(rolsAtorgatsRol); 

        // Indicador de si está otorgado a roles o a grupos (*=true)
        targetVO.setAssignacioIndirecta((rolsPosseidorsRol.size() != 0 || grupsPosseidors
                .size() != 0) ? "*" : ""); //$NON-NLS-1$ //$NON-NLS-2$

    }

    /**
     * @see es.caib.seycon.ng.model.RolEntityDao#toRol(es.caib.seycon.ng.model.RolEntity)
     */
    public es.caib.seycon.ng.comu.Rol toRol(final com.soffid.iam.model.RoleEntity entity) {
        es.caib.seycon.ng.comu.Rol role = super.toRol(entity);
        // toRolCustom(entity, role); //NO ES FA ACI (!!)
        return role;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.RoleEntity loadRolEntityFromRol(es.caib.seycon.ng.comu.Rol role) {
        RoleEntity rolEntity = null;
        if (role.getId() != null) {
            rolEntity = load(role.getId());
        }
        if (rolEntity == null) {
            rolEntity = newRoleEntity();
        }
        return rolEntity;
    }

    public com.soffid.iam.model.RoleEntity rolToEntity(es.caib.seycon.ng.comu.Rol role) {
        com.soffid.iam.model.RoleEntity entity = this.loadRolEntityFromRol(role);
        rolToEntity(role, entity, true);
        return entity;
    }

    private DomainValueEntity findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(String nomDomini, String codiAplicacio, String valor) {
        String query = "select valorDominiAplicacio " //$NON-NLS-1$
                + "from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiAplicacio " //$NON-NLS-1$
                + "left join valorDominiAplicacio.domini domini " //$NON-NLS-1$
                + "left join domini.aplicacio aplicacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "domini.nom = :nomDomini and " //$NON-NLS-1$
                + "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio)) and " //$NON-NLS-1$
                + "valorDominiAplicacio.valor = :valor"; //$NON-NLS-1$

        Parameter nomDominiParameter = new Parameter("nomDomini", nomDomini); //$NON-NLS-1$
        Parameter codiAplicacioParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
                codiAplicacio);
        Parameter valorParameter = new Parameter("valor", valor); //$NON-NLS-1$
        Parameter[] parametres = { nomDominiParameter, codiAplicacioParameter,
                valorParameter };

        Collection valorsDomini = getDomainValueEntityDao().query(query, parametres);
        if (valorsDomini != null) {
            Iterator valorsDominiIterator = valorsDomini.iterator();
            if (valorsDominiIterator != null) {
                if (valorsDominiIterator.hasNext()) {
                    DomainValueEntity valorDominiEntity = (DomainValueEntity) valorsDominiIterator.next();
                    return valorDominiEntity;
                }
            }
        }
        return null;
    }

    private void rolToEntityCustom(es.caib.seycon.ng.comu.Rol sourceVO, com.soffid.iam.model.RoleEntity targetEntity) {// de VO a Entity

        // Transformación a nivel de Objeto (NO ACCESO BBDD)
        // targetEntity puede estar vacía (CREATE) o tener referencias (UPDATE)
        // [importante]

        updateEntityDomainType(sourceVO, targetEntity);
        updateEntityApplication(sourceVO, targetEntity);
        upateEntityOthers(sourceVO, targetEntity);

    	// GRUPOS POSEEDORES DEL ROL: NUEVO
        // Collección de Grups posseidors (VO)
        updateEntityGranteeGroups(sourceVO, targetEntity);

        // JERARQUÍA DE ROLES PADRES DEL ROL: NUEVO
        // Creamos una nueva (luego en el update - si corresponde - se verifican
        // los existentes)
        // Eliminamos referencias existentes
        updateEntityGranteeRoles(sourceVO, targetEntity);


    }

	private void upateEntityOthers(es.caib.seycon.ng.comu.Rol sourceVO, com.soffid.iam.model.RoleEntity targetEntity) {
		Boolean perDefecte = sourceVO.getDefecte();
        if (perDefecte != null) {
            targetEntity.setDefaultRole(sourceVO.getDefecte().booleanValue() ? "S" : "N"); //$NON-NLS-1$
        } else {
            targetEntity.setDefaultRole("N"); //$NON-NLS-1$
        }
        Boolean contrasenya = sourceVO.getContrasenya();
        if (contrasenya != null) {
            targetEntity.setPassword(sourceVO.getContrasenya().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setPassword("N"); //$NON-NLS-1$
        }
        Boolean gestionableWF = sourceVO.getGestionableWF();
        if (gestionableWF != null) {
            targetEntity.setManageableWF(sourceVO.getGestionableWF().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else
            targetEntity.setManageableWF("N"); //$NON-NLS-1$
        String codiDispatcher = sourceVO.getBaseDeDades();
        if (codiDispatcher != null && codiDispatcher.trim().compareTo("") != 0) { //$NON-NLS-1$
            SystemEntity dispatcherEntity = this.getSystemEntityDao().findByCode(codiDispatcher);
            if (dispatcherEntity != null) {
                targetEntity.setDatabases(dispatcherEntity);
            } else {
				throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.18"),   //$NON-NLS-1$
						codiDispatcher));
            }
        } else {
            targetEntity.setDatabases(null);
        }
	}

	private void updateEntityApplication(es.caib.seycon.ng.comu.Rol sourceVO, com.soffid.iam.model.RoleEntity targetEntity) {
		String codiAplicacio = sourceVO.getCodiAplicacio();
        if (codiAplicacio != null) {
            InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(codiAplicacio);
            targetEntity.setApplication(aplicacioEntity);
        } else {
            targetEntity.setApplication(null);
        }
	}

	private void updateEntityGranteeRoles(es.caib.seycon.ng.comu.Rol sourceVO, com.soffid.iam.model.RoleEntity targetEntity) {
		Collection<RoleDependencyEntity> rolAssociacioRolSocContingut = new HashSet<RoleDependencyEntity>();
        // Los que somos el contenedor (socContenidor) no aparece en el VO
        if (sourceVO.getOwnerRoles() != null) {
            // Creamos las relaciones nuevas
            for (Iterator<RolGrant> iterator = sourceVO.getOwnerRoles().iterator(); iterator.hasNext(); ) {
                RolGrant currentGrant = iterator.next();
                if (currentGrant != null) {
                    RoleEntity rolEntityFound = load(currentGrant.getOwnerRol());
                    RoleDependencyEntity rare = getRoleDependencyEntityDao().newRoleDependencyEntity();
                    rare.setRoleContent(targetEntity);
                    rare.setRoleContainer(rolEntityFound);
                    assignDomainValue(rare, currentGrant, targetEntity, rolEntityFound);
                    assignGranteeDomainValue(rare, currentGrant, targetEntity, rolEntityFound);
                    rolAssociacioRolSocContingut.add(rare);
                }
            }
            targetEntity.setRoleAssociationContent(rolAssociacioRolSocContingut);
        }
	}

	private void updateEntityGranteeGroups(es.caib.seycon.ng.comu.Rol sourceVO, com.soffid.iam.model.RoleEntity targetEntity) {
		Collection<Grup> grupsPosseidors = sourceVO.getOwnerGroups();
        Collection<RolGrant> granteeGroups = sourceVO.getGranteeGroups();
        // Eliminamos las referencias existentes
        Collection<RoleGroupEntity> grupsPosseidorsRolEntity = new HashSet<RoleGroupEntity>();
        if (granteeGroups == null && grupsPosseidors != null) {
            // Creamos las relaciones existentes con los grupos
            for (Iterator<Grup> it = grupsPosseidors.iterator(); it.hasNext(); ) {
                Grup grup = it.next();
                GroupEntity grupEntity = getGroupEntityDao().findById(grup.getId());
                RoleGroupEntity rge = getRoleGroupEntityDao().newRoleGroupEntity();
                rge.setAssignedRole(targetEntity);
                rge.setOwnerGroup(grupEntity);
                grupsPosseidorsRolEntity.add(rge);
            }
        } else if (granteeGroups != null) {
            // Creamos las relaciones existentes con los grupos
            for (Iterator<RolGrant> it = granteeGroups.iterator(); it.hasNext(); ) {
                RolGrant grant = it.next();
                GroupEntity grupEntity = getGroupEntityDao().findByCode(grant.getOwnerGroup());
                if (grupEntity == null) throw new java.lang.IllegalArgumentException("group " + grant.getOwnerGroup());
                RoleGroupEntity rge = getRoleGroupEntityDao().newRoleGroupEntity();
                rge.setAssignedRole(targetEntity);
                rge.setOwnerGroup(grupEntity);
                Domini domini = sourceVO.getDomini();
                String nomDomini = targetEntity.getDomainType();
                if (TipusDomini.APLICACIONS.equals(nomDomini)) {
                    rge.setGrantedApplicationDomain(getInformationSystemEntityDao().findByCode(grant.getDomainValue()));
                } else if (TipusDomini.GRUPS.equals(nomDomini) || TipusDomini.GRUPS_USUARI.equals(nomDomini)) {
                    rge.setGrantedGroupDomain(getGroupEntityDao().findByCode(grant.getDomainValue()));
                } else if (TipusDomini.DOMINI_APLICACIO.equals(nomDomini)) {
                    rge.setGrantedDomainValue(getDomainValueEntityDao().findByApplicationDomainValue(targetEntity.getApplication().getCode(), targetEntity.getApplicationDomain().getName(), grant.getDomainValue()));
                }
                grupsPosseidorsRolEntity.add(rge);
            }
        	
        }
        targetEntity.setGroupsOwnerRole(grupsPosseidorsRolEntity);
	}

	private void updateEntityDomainType(es.caib.seycon.ng.comu.Rol sourceVO, com.soffid.iam.model.RoleEntity targetEntity) {
		Domini domini = sourceVO.getDomini();
        String nomDomini = domini.getNom();
        if (nomDomini == null || nomDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            nomDomini = TipusDomini.SENSE_DOMINI;
        }
        if (domini.getId() == null &&
        		(nomDomini.compareTo(TipusDomini.GRUPS) == 0
                 || nomDomini.compareTo(TipusDomini.GRUPS_USUARI) == 0
                 || nomDomini.compareTo(TipusDomini.APLICACIONS) == 0
                 || nomDomini.compareTo(TipusDomini.SENSE_DOMINI) == 0)) {
            targetEntity.setApplicationDomain(null);
            targetEntity.setDomainType(nomDomini);
        } else {
            ApplicationDomainEntity dominiAplicacioEntity = findDominiByNomAndCodiApliacio(domini.getNom(), sourceVO.getCodiAplicacio());
            if (dominiAplicacioEntity != null) {
                targetEntity.setApplicationDomain(dominiAplicacioEntity);
                targetEntity.setDomainType(TipusDomini.DOMINI_APLICACIO);
            } else {
				throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.19"),   //$NON-NLS-1$
						domini.getNom(), 
						domini.getCodiExtern()));
            }

            /*
             * Si el domini d'aplicació està associat a una aplicació llavors el
             * rol ha de pertanyer a l'aplicació
             */
            if (domini != null && domini.getCodiExtern() != null) {
                if (domini.getCodiExtern().compareTo(
                        sourceVO.getCodiAplicacio()) != 0) {
					throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.20"),   //$NON-NLS-1$
							sourceVO.getNom(), 
							domini.getNom()));
                }
            }
        }
	}

	private void assignDomainValue(RoleDependencyEntity rare, RolGrant currentPare, com.soffid.iam.model.RoleEntity grantedRole, RoleEntity granteeRole) {
		// Añadimos la relación con el padre
		if (granteeRole != null) {
		    // Podemos tener dos casos: que el rol no tenga Dominio
		    // o que si tenga
		    String tipusDominiAsoc = grantedRole.getDomainType();
		    // Primer mirem que no siga sense valor domini (si té
		    // valor de domini)
		    if (currentPare.getDomainValue() == null ||
		    		currentPare.getDomainValue().trim().length () == 0 ||
		    		tipusDominiAsoc == null
		            || TipusDomini.SENSE_DOMINI
		                    .equals(tipusDominiAsoc)) {
		    } else if (TipusDomini.GRUPS.equals(tipusDominiAsoc)
		            || TipusDomini.GRUPS_USUARI
		                    .equals(tipusDominiAsoc)) {
		        GroupEntity grupAsoc = getGroupEntityDao().findByCode(currentPare.getDomainValue());
		        if (grupAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.14"),   //$NON-NLS-1$
							currentPare.getDomainValue()));
		        }
		        rare.setDomainGroup(grupAsoc);
		    } else if (TipusDomini.APLICACIONS
		            .equals(tipusDominiAsoc)) {
		        InformationSystemEntity appAsoc = getInformationSystemEntityDao().findByCode(currentPare.getDomainValue());
		        if (appAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.15"),  //$NON-NLS-1$
							currentPare.getDomainValue()));
		        }
		        rare.setDomainApplication(appAsoc);
		    } else if (TipusDomini.DOMINI_APLICACIO
		            .equals(tipusDominiAsoc)) {
		        DomainValueEntity valdomAsoc = getDomainValueEntityDao().findDomainValueAndDomainNameAndDomainRoleNameAndDomainValue(grantedRole.getApplicationDomain().getName(), grantedRole.getName(), currentPare.getDomainValue());
		        if (valdomAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.16"), granteeRole.getApplicationDomain().getName(), currentPare.getDomainValue()));
		        }
		        rare.setDomainApplicationValue(valdomAsoc);
		    }
		} else {
			throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.17"),   //$NON-NLS-1$
					currentPare.getOwnerRolName(), 
					currentPare.getOwnerRol(), 
					currentPare.getOwnerDispatcher()));
		}
	}

	private void assignGranteeDomainValue(RoleDependencyEntity rare, RolGrant grant, com.soffid.iam.model.RoleEntity grantedRole, RoleEntity granteeRole) {
		// Añadimos la relación con el padre
		if (granteeRole != null) {
		    // Podemos tener dos casos: que el rol no tenga Dominio
		    // o que si tenga
		    String tipusDominiAsoc = granteeRole.getDomainType();
		    // Primer mirem que no siga sense valor domini (si té
		    // valor de domini)
		    if (grant.getOwnerRolDomainValue() == null ||
		    		grant.getOwnerRolDomainValue().trim().length () == 0 ||
		    		tipusDominiAsoc == null
		            || TipusDomini.SENSE_DOMINI
		                    .equals(tipusDominiAsoc)) {
		    } else if (TipusDomini.GRUPS.equals(tipusDominiAsoc)
		            || TipusDomini.GRUPS_USUARI
		                    .equals(tipusDominiAsoc)) {
		        GroupEntity grupAsoc = getGroupEntityDao().findByCode(grant.getOwnerRolDomainValue());
		        if (grupAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.14"),   //$NON-NLS-1$
							grant.getDomainValue()));
		        }
		        rare.setGranteeGroupDomain(grupAsoc);
		    } else if (TipusDomini.APLICACIONS
		            .equals(tipusDominiAsoc)) {
		        InformationSystemEntity appAsoc = getInformationSystemEntityDao().findByCode(grant.getOwnerRolDomainValue());
		        if (appAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.15"),  //$NON-NLS-1$
							grant.getDomainValue()));
		        }
		        rare.setGranteeApplicationDomain(appAsoc);
		    } else if (TipusDomini.DOMINI_APLICACIO
		            .equals(tipusDominiAsoc)) {
		        DomainValueEntity valdomAsoc = getDomainValueEntityDao().findDomainValueAndDomainNameAndDomainRoleNameAndDomainValue(granteeRole.getApplicationDomain().getName(), granteeRole.getName(), grant.getOwnerRolDomainValue());
		        if (valdomAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.16"), granteeRole.getApplicationDomain().getName(), grant.getDomainValue()));
		        }
		        rare.setGranteeDomainValue(valdomAsoc);
		    }
		} else {
			throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.17"),   //$NON-NLS-1$
					grant.getOwnerRolName(), 
					grant.getOwnerRol(), 
					grant.getOwnerDispatcher()));
		}
	}

	private ApplicationDomainEntity findDominiByNomAndCodiApliacio(String nom, String codiAplicacio) {
        String query = "select domini " //$NON-NLS-1$
                + "from es.caib.seycon.ng.model.DominiAplicacioEntity domini " //$NON-NLS-1$
                + "left join domini.aplicacio aplicacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio)) and " //$NON-NLS-1$
                + "domini.nom = :nom"; //$NON-NLS-1$
        Parameter codiAplicacioParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
                codiAplicacio);
        Parameter nomParameter = new Parameter("nom", nom); //$NON-NLS-1$
        Parameter[] parameters = { codiAplicacioParameter, nomParameter };
        Collection dominis = getApplicationDomainEntityDao().query(query, parameters);
        if (dominis != null) {
            return (ApplicationDomainEntity) dominis.iterator().next();
        }
        return null;
    }

    public void rolToEntity(es.caib.seycon.ng.comu.Rol sourceVO, com.soffid.iam.model.RoleEntity targetEntity, boolean copyIfNull) {
        super.rolToEntity(sourceVO, targetEntity, copyIfNull);
        rolToEntityCustom(sourceVO, targetEntity);
    }

    /**
     * @see es.caib.seycon.ng.model.GrupEntityDao#toIdentitat(es.caib.seycon.ng.model.GrupEntity,
     *      es.caib.seycon.ng.comu.Identitat)
     */
    public void toIdentitat(com.soffid.iam.model.RoleEntity source, es.caib.seycon.ng.comu.Identitat target) {
        super.toIdentitat(source, target);
        toIdentitatCustom(source, target);
    }

    public void toIdentitatCustom(com.soffid.iam.model.RoleEntity source, es.caib.seycon.ng.comu.Identitat target) {
        String nomRol = source.getName();
        InformationSystemEntity aplicacio = source.getApplication();
        SystemEntity dispatcher = source.getDatabases();
        target.setNomRol(nomRol + "@" + dispatcher.getCode() + ">" + aplicacio.getCode());
        target.setCodiIdentitat(nomRol + "@" + dispatcher.getCode() + ">" + aplicacio.getCode());
        String descripcio = source.getDescription();
        target.setDescripcio(descripcio);
    }

    /**
     * @see es.caib.seycon.ng.model.RolEntityDao#toIdentitat(es.caib.seycon.ng.model.RolEntity)
     */
    public es.caib.seycon.ng.comu.Identitat toIdentitat(final com.soffid.iam.model.RoleEntity entity) {
        Identitat identitat = super.toIdentitat(entity);
        toIdentitatCustom(entity, identitat);
        return identitat;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.RoleEntity loadRolEntityFromIdentitat(es.caib.seycon.ng.comu.Identitat identitat) {
        /*
         * La identitat és read only
         */
        String nomRolComplert = identitat.getNomRol();
        if (nomRolComplert != null) {
            RoleEntity rolEntity = null;
            String[] partsNomRol = nomRolComplert.split("@"); //$NON-NLS-1$
            String[] partsNomRol2 = partsNomRol[1].split(">"); //$NON-NLS-1$
            rolEntity = findRoleByRoleNameAndApplicationCodeAndSystemCode(partsNomRol[0], partsNomRol2[1], partsNomRol2[0]);
            if (rolEntity != null) {
                return rolEntity;
            } else {
				throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.21"), partsNomRol[0]));  //$NON-NLS-1$
            }
        }
        throw new SeyconException(Messages.getString("RoleEntityDaoImpl.22"));  //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.model.RolEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat)
     */
    public com.soffid.iam.model.RoleEntity identitatToEntity(es.caib.seycon.ng.comu.Identitat identitat) {
        com.soffid.iam.model.RoleEntity entity = this.loadRolEntityFromIdentitat(identitat);
        this.identitatToEntity(identitat, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.RolEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat,
     *      es.caib.seycon.ng.model.RolEntity)
     */
    public void identitatToEntity(es.caib.seycon.ng.comu.Identitat source, com.soffid.iam.model.RoleEntity target, boolean copyIfNull) {
        super.identitatToEntity(source, target, copyIfNull);
    }

    public List<RoleEntity> find(final java.lang.String queryString, final es.caib.seycon.ng.model.Parameter[] parameters) {
        try {
            java.util.List<RoleEntity> results = new QueryBuilder().query(this, queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    public RoleEntity contenidorRolToEntity(ContenidorRol contenidorRol) {
        // TODO Auto-generated method stub
        return null;
    }

    public ContenidorRol toContenidorRol(RoleEntity entity) {
        ContenidorRol contenidorRol = super.toContenidorRol(entity); // Pasamos
                                                                     // el id
        contenidorRol.setTipus(TipusContenidorRol.ROL_ENTITY);
        // Información específica:
        contenidorRol.setInfoContenidor(entity.getName() + "@" + entity.getDatabases().getCode() + " (" + entity.getApplication().getCode() + ")"); //$NON-NLS-1$

        return contenidorRol;
    }

    public void contenidorRolToEntity(ContenidorRol source, RoleEntity target, boolean copyIfNull) {
        // TODO Auto-generated method stub
        super.contenidorRolToEntity(source, target, copyIfNull);
    }

    private Collection<GroupEntity> getTotsFillsGrup(GroupEntity grupAnalitzar) {
        Collection<GroupEntity> fills = grupAnalitzar.getChildrens();
        Collection<GroupEntity> totsFills = new HashSet<GroupEntity>();

        for (Iterator<GroupEntity> it = fills.iterator(); it.hasNext(); ) {
            GroupEntity fill = it.next();
            totsFills.add(fill);
            totsFills.addAll(getTotsFillsGrup(fill));
        }
        return totsFills;
    }

    private Collection<UserEntity> findCodiUsuarisPertanyenGrups(Collection<GroupEntity> grupsISubgrups) {
        // Conté els codi dels usuaris a propagar:
        HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();

        // Cerquem els usuaris que tenen com a grup primari qualque grup del
        // llistat i els que els tenen com a grup secundari
        for (Iterator<GroupEntity> it = grupsISubgrups.iterator(); it.hasNext(); ) {
            GroupEntity g = it.next();
            Collection<UserEntity> usuGPrim = null;
            usuGPrim = getUserEntityDao().findbyPrimaryGroup(g.getCode());
            if (usuGPrim != null) for (Iterator<UserEntity> gpr_it = usuGPrim.iterator(); gpr_it.hasNext(); ) {
                UserEntity usu = gpr_it.next();
                usuarisPropagar.add(usu);
            }
            Collection<UserGroupEntity> usuSec = g.getSecondaryGroupUsers();
            if (usuSec != null) for (Iterator<UserGroupEntity> gps_it = usuSec.iterator(); gps_it.hasNext(); ) {
                UserGroupEntity usugru = gps_it.next();
                usuarisPropagar.add(usugru.getUser());
            }
        }

        // Devolvemos los usuariEntity:
        return usuarisPropagar;
    }

    /**
     * Obté els usuaris, grups i rols que conté un rol a l'estat actual
     * 
     * @param rolsPropagar
     * @param grupsPropagar
     * @param usuarisPropagar
     * @param grupsPropagarAfter 
     */
    private void getHerenciaRol_Usuaris_Rols_Grups(RoleEntity rol, HashSet<UserEntity> usuarisPropagar, HashSet<com.soffid.iam.model.AccountEntity> accountsPropagar, HashSet<RoleEntity> rolsPropagar, HashSet<GroupEntity> grupsPropagar, boolean cercaRolABaseDades) {

        // Sólo hemos de propagar a los usuarios que tienen el rol contenedor
        // con valor de dominio correspondiente (o si es SENSE_DOMINI o a
        // qualque valor)
        // Montamos un FIFO De roles (puede haber cadena de
        // herencia A atorgat B[sense domini] atorgat C ... atorgat Z[amb
        // domini]
        LinkedList<RoleEntity> rolsAnalitzar = new LinkedList<RoleEntity>(); // FIFO
        rolsAnalitzar.add(rol);
        RoleEntity rolActual = null;

        // Añadimos el rol actual a roles a propagar (se ha deshabilitado el
        // TRIGGER SC_ROLES_UPD !!)
        rolsPropagar.add(rol);

        while ((rolActual = rolsAnalitzar.poll()) != null) {
            // Ho cerquem a la base de dades si ja existeix (update)
            Collection<RoleDependencyEntity> socContingut = null;
            if (cercaRolABaseDades) // ho fem a l'update (per saber l'estat
                                    // abans)
                socContingut = getRoleDependencyEntityDao().findRolesAssociationContainerRole(rolActual);
            else
                socContingut = rolActual.getRoleAssociationContent();

            if (socContingut != null)
                for (Iterator<RoleDependencyEntity> it = socContingut.iterator(); it.hasNext(); ) {
                RoleDependencyEntity associacio = (RoleDependencyEntity) it.next();
                RoleEntity rolContenidor = associacio.getRoleContainer();
                rolsPropagar.add(rolContenidor);
                rolsAnalitzar.add(rolContenidor);
                Collection<RoleAccountEntity> rolsUsuarisRolContenidor = new ArrayList<RoleAccountEntity>();
                if (associacio.getDomainGroup() != null || associacio.getDomainApplication() != null || associacio.getDomainApplicationValue() != null) {
                    rolsUsuarisRolContenidor = getRoleAccountEntityDao().findByRoleAndDomainValue(rolContenidor.getName(), rolContenidor.getDatabases().getCode(), rolContenidor.getApplication().getCode(), rolContenidor.getDomainType(), associacio.getDomainGroup() != null ? associacio.getDomainGroup().getCode() : null, associacio.getDomainApplication() != null ? associacio.getDomainApplication().getCode() : null, associacio.getDomainApplication() != null ? associacio.getDomainApplication().getId() : null);
                } else {
                    rolsUsuarisRolContenidor = getRoleAccountEntityDao().findByRoleAndDomainType(rolContenidor.getName(), rolContenidor.getDatabases().getCode(), rolContenidor.getApplication().getCode(), rolContenidor.getDomainType());
                }
                if (rolsUsuarisRolContenidor != null) for (Iterator ruit = rolsUsuarisRolContenidor.iterator(); ruit.hasNext(); ) {
                    RoleAccountEntity rui = (RoleAccountEntity) ruit.next();
                    if (rui.getAccount().getType().equals(AccountType.USER) && rui.getAccount().getUsers().size() == 1) usuarisPropagar.add(rui.getAccount().getUsers().iterator().next().getUser()); else accountsPropagar.add(rui.getAccount());
                }
            }
        }

        // Obtenemos las relaciones con GRUPOS
        // Buscamos en la base de datos (cas d'update, abans de fer els canvis)
        Collection<RoleGroupEntity> grupsPosseidors = null;
        if (cercaRolABaseDades)
            grupsPosseidors = getRoleGroupEntityDao().findOwnerGroupsByRole(rol);
        else
            grupsPosseidors = rol.getGroupsOwnerRole();

        for (Iterator<RoleGroupEntity> it = grupsPosseidors.iterator(); it.hasNext(); ) {
            RoleGroupEntity rolsgrup = it.next();
            GroupEntity grupPosseidor = getGroupEntityDao().findById(rolsgrup.getOwnerGroup().getId());
            grupsPropagar.add(grupPosseidor);
            Collection<GroupEntity> subgrups = getTotsFillsGrup(grupPosseidor);
            if (subgrups != null) grupsPropagar.addAll(subgrups);
            Collection<UserEntity> usuGrupIsubGrups = findCodiUsuarisPertanyenGrups(grupsPropagar);
            usuarisPropagar.addAll(usuGrupIsubGrups);
        }

    }

    private void propagarUsuarisRolsIGrups(Collection<UserEntity> usuarisPropagar, Collection<com.soffid.iam.model.AccountEntity> accountsPropagar, Collection<RoleEntity> rolsPropagar, Collection<GroupEntity> grupsPropagar) throws InternalErrorException {

        // Herencia:
        // ROL: Atorgació del rol (aquest rol) a un altre rol (contenidor) : hem
        // de fer
        // updateRole(contenidor) i
        // updateUser(per_a_tot_usuari_ROL_contenidor)
        // GRUP: atorgació del rol (aquest rol) a un grup: hem de fer
        // updateUser(per_a_tot_usuari_GRUP_i_SUBGRUPS_del_GRUP_posseidor)
        // updateGrup(grup_posseidor_i_SUBGRUPS)
        // Els usuaris ho fem només una vegada

        // Creem les tasques

        // 1) Propaguem els rols
        if (rolsPropagar != null)
            for (Iterator<RoleEntity> it = rolsPropagar.iterator(); it.hasNext(); ) {
            RoleEntity role = it.next();
            Tasca updateRole = new Tasca();
            updateRole.setTransa("UpdateRole");
            updateRole.setDataTasca(Calendar.getInstance());
            updateRole.setStatus("P");
            updateRole.setRole(role.getName());
            updateRole.setBd(role.getDatabases().getCode());
            TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateRole);
            getTaskEntityDao().createNoFlush(tasca);
        }

        // 2) Propaguem els usuaris
        if (usuarisPropagar != null)
            for (Iterator<UserEntity> it = usuarisPropagar.iterator(); it.hasNext(); ) {
            UserEntity usu = it.next();
            Tasca updateUser = new Tasca();
            updateUser.setTransa("UpdateUser");
            updateUser.setDataTasca(Calendar.getInstance());
            updateUser.setUsuari(usu.getUserName());
            updateUser.setStatus("P");
            TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateUser);
            getTaskEntityDao().createNoFlush(tasca);
        }

        // 3) Propaguem els accounts
        if (grupsPropagar != null)
            for (Iterator<com.soffid.iam.model.AccountEntity> it = accountsPropagar.iterator(); it.hasNext(); ) {
            com.soffid.iam.model.AccountEntity acc = it.next();
            Tasca updateAccount = new Tasca();
            updateAccount.setTransa(TaskHandler.UPDATE_ACCOUNT);
            updateAccount.setDataTasca(Calendar.getInstance());
            updateAccount.setStatus("P");
            updateAccount.setUsuari(acc.getName());
            updateAccount.setBd(acc.getSystem().getCode());
            TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateAccount);
            getTaskEntityDao().createNoFlush(tasca);
        }
        // 4) Propaguem els grups
        if (grupsPropagar != null)
            for (Iterator<GroupEntity> it = grupsPropagar.iterator(); it.hasNext(); ) {
            GroupEntity grup = it.next();
            Tasca updateGrup = new Tasca();
            updateGrup.setTransa("UpdateGroup");
            updateGrup.setDataTasca(Calendar.getInstance());
            updateGrup.setStatus("P");
            updateGrup.setGrup(grup.getCode());
            TaskEntity tasca = getTaskEntityDao().tascaToEntity(updateGrup);
            getTaskEntityDao().createNoFlush(tasca);
        }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof RoleEntity) {
                RoleEntity entity = (RoleEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof RoleEntity) {
                RoleEntity entity = (RoleEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof RoleEntity) {
                RoleEntity entity = (RoleEntity) obj;
                this.remove(entity);
            }
        }
    }
}
