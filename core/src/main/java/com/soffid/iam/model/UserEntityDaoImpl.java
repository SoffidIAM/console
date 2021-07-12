//license-header java merge-point
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.AnonimousUser;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Identity;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;
import com.soffid.iam.api.UserMailList;
import com.soffid.iam.api.UserType;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.EmailDomainEntity;
import com.soffid.iam.model.EmailListEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.MailListGroupMemberEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEmailEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.UserTypeEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;

import com.soffid.iam.bpm.api.BPMUser;

import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.model.UsuariEntity
 */
public class UserEntityDaoImpl extends com.soffid.iam.model.UserEntityDaoBase {

	public static final String NIF = "NIF"; //$NON-NLS-1$
    public static final String TELEFON = "PHONE"; //$NON-NLS-1$
    private static final String SeyconLogon_EMAIL_ADD_CODE = "E-MAIL CONTACTE"; //$NON-NLS-1$

    private void auditarUsuari(String accio, String codiUsuariAuditat, GroupEntity grupPrimari) {
        // Corregim accés sense principal (donar d'alta usuaris)
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setUser(codiUsuariAuditat);
        auditoria.setAuthor(codiUsuari);
        // Afegim auditoria del grup primari de l'usuari
        if (grupPrimari != null)
            auditoria.setGroup(grupPrimari.getName());
        auditoria.setObject("SC_USUARI"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    public void create(com.soffid.iam.model.UserEntity usuari) throws RuntimeException {
        try {

            if (usuari.getMailDomain() != null && usuari.getMailDomain().getObsolete() != null && usuari.getMailDomain().getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
                throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.deletedDomain"), usuari.getMailDomain().getName()));
            }

            usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
            super.create(usuari);
            getSession(false).flush();

            // HERÈNCIA DE ROLS: Atorgació de rols al grup primari
            // Obtenemos los roles otorgados al grupo primario
            // Los secundarios se gestionan en UsuariGrupEntityDaoImpl
            GroupEntity grupPrimari = usuari.getPrimaryGroup();
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
            auditarUsuari("C", usuari.getUserName(), usuari.getPrimaryGroup()); //$NON-NLS-1$
            createMailTask(usuari);

        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.errorCreating"), usuari.getUserName(), message));
        }
    }

    private void createMailTask(UserEntity usuari) throws InternalErrorException {
        //  Now, updates any mail lists the users belongs
        //  First, directly
        for (UserEmailEntity lce : usuari.getUserMailList()) {
        	if (!Boolean.TRUE.equals(lce.getDisabled()))
        		getEmailListEntityDao().generateUpdateTasks(lce.getMailList());
        }
        createGroupMailListTaks(usuari.getPrimaryGroup());
        // Next, secondary groups
        for ( EmailListEntity email: getEmailListEntityDao().query("select o from com.soffid.iam.model.EmailListEntityImpl as o "
        		+ "join o.groups as emailGroup "
        		+ "join emailGroup.group.secondaryGroupUsers as userGroup "
        		+ "where userGroup.user.id = :userId", new Parameter[] { 
        				new Parameter("userId", usuari.getId())
        		})) {
           	getEmailListEntityDao().generateUpdateTasks(email);
        }
        // Finally roles
        for ( EmailListEntity email: getEmailListEntityDao().query("select o from com.soffid.iam.model.EmailListEntityImpl as o "
        		+ "join o.roles as emailRole "
        		+ "join emailRole.role.accounts as roleAccount "
        		+ "join roleAccount.account.users as userAccount "
        		+ "where userAccount.user.id = :userId", new Parameter[] { 
        				new Parameter("userId", usuari.getId())
        		})) {
           	getEmailListEntityDao().generateUpdateTasks(email);
        }
    }

	private void createGroupMailListTaks(GroupEntity grupEntity) throws InternalErrorException {
		// Second, primary group
		if (grupEntity != null && grupEntity.getMailLists() != null)
		{
	        for (MailListGroupMemberEntity mlge : grupEntity.getMailLists()) {
	            getEmailListEntityDao().generateUpdateTasks(mlge.getMailList());
	        }
		}
	}
    
    
    private void createTask(com.soffid.iam.model.UserEntity usuari) throws InternalErrorException {
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_USER);
        tasque.setUser(usuari.getUserName());
        getTaskEntityDao().createForce(tasque);
        tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        if (usuari.getPrimaryGroup() != null)
        {
	        tasque.setTransaction(TaskHandler.UPDATE_GROUP);
	        tasque.setGroup(usuari.getPrimaryGroup().getName());
	        getTaskEntityDao().createForce(tasque);
        }
        if (usuari.getShortName() != null)
        {
            tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_USER_ALIAS);
            tasque.setUser(usuari.getUserName());
            getTaskEntityDao().createForce(tasque);

        }
    }

    public void update(com.soffid.iam.model.UserEntity usuari) throws RuntimeException {
        try {
            super.update(usuari);

            auditarUsuari("U", usuari.getUserName(), usuari.getPrimaryGroup()); //$NON-NLS-1$

    		TaskEntity tasque = getTaskEntityDao().newTaskEntity();
    		tasque.setDate(new Timestamp(System.currentTimeMillis()));
    		tasque.setTransaction(TaskHandler.UPDATE_USER);
    		tasque.setUser(usuari.getUserName());
    		getTaskEntityDao().create(tasque);


        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);

            throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.errorUpdating"), usuari.getUserName(), message)); //$NON-NLS-1$
        }
    }

    public void remove(com.soffid.iam.model.UserEntity usuari) throws RuntimeException {
        try {
            createMailTask(usuari);

            String codiUsuari = usuari.getUserName();
            
            if (usuari.getUserName().equals(Security.getCurrentUser()))
                throw new SecurityException(Messages.getString("UserEntityDaoImpl.cannotGrantYourself")); //$NON-NLS-1$

            // HERÈNCIA DE ROLS: Atorgació de rols a grups
            // Obtenemos los roles otorgados al grupo primario y secundarios
            HashSet totGrup = new HashSet();
            GroupEntity grupPrimari = usuari.getPrimaryGroup();
            if (grupPrimari != null)
                totGrup.add(grupPrimari);
            Collection grupsSecundaris = usuari.getSecondaryGroups();
            if (grupsSecundaris != null && !grupsSecundaris.isEmpty())
                totGrup.add(grupsSecundaris);
            HashSet rolsAPropagar = new HashSet();
            for (Iterator it = totGrup.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj != null) {
                    GroupEntity g = (GroupEntity) obj;
                    Collection rolsAtorgatsGrupISubgrups = getRolsAtorgatsGrupIParesGrup(g);
                    if (rolsAtorgatsGrupISubgrups != null  && !rolsAtorgatsGrupISubgrups.isEmpty())
                    	rolsAPropagar.addAll(rolsAtorgatsGrupISubgrups);
                }
            }
            // Herencia de Roles: Propagamos los roles: (creamos las tareas)
            propagarRolsAtorgatsGrups(rolsAPropagar);
            createTask(usuari);

            super.remove(usuari);

            auditarUsuari("D", codiUsuari, usuari.getPrimaryGroup()); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.errorDeleting"), usuari.getUserName(), message)); //$NON-NLS-1$
        }
    }

    public void toUser(com.soffid.iam.model.UserEntity sourceEntity, com.soffid.iam.api.User targetVO) {
        super.toUser(sourceEntity, targetVO);

        // Fem les transformacions necessàries

        // ALIES DE CORREU
        String aliesDeCorreu = ""; //$NON-NLS-1$
        for (Iterator iterator = sourceEntity.getUserMailList().iterator(); iterator.hasNext(); ) {
            UserEmailEntity llistaCorreuUsuariEntity = (UserEmailEntity) iterator.next();
            if ( ! Boolean.TRUE.equals( llistaCorreuUsuariEntity.getDisabled()))
            {
            	if ( ! aliesDeCorreu.isEmpty())
            		aliesDeCorreu += ", ";
	            UserMailList llistaCorreuUsuari = getUserEmailEntityDao().toUserMailList(llistaCorreuUsuariEntity);
	            String nomLlista = llistaCorreuUsuari.getMailListName();
	            String domini = llistaCorreuUsuari.getDomainCode();
	            aliesDeCorreu += nomLlista + (domini == null ? "" : "@" + domini);
            }
        }
        targetVO.setMailAlias(aliesDeCorreu);

        // DATA DE CREACIÓ
        Calendar calendar = GregorianCalendar.getInstance();
        if (sourceEntity.getCreationDate() != null) {
            calendar.setTime(sourceEntity.getCreationDate());
            targetVO.setCreatedDate(calendar);
        } else {// No debería pasar
            targetVO.setCreatedDate(null);
        }

        // DATA DE DARRERA MODIFICACIÓ
        calendar = GregorianCalendar.getInstance();
        if (sourceEntity.getLastModificationDate() != null) {
            calendar.setTime(sourceEntity.getLastModificationDate());
            targetVO.setModifiedDate(calendar);
        } else { // si no hay fecha de modificación
            targetVO.setModifiedDate(null);
        }

        // SERVIDORS DE L'USUARI
        targetVO.setMailServer(sourceEntity.getMailServer() == null ? null : sourceEntity.getMailServer().getName());
        targetVO.setHomeServer(sourceEntity.getHomeServer() == null ? null : sourceEntity.getHomeServer().getName());
        targetVO.setProfileServer(sourceEntity.getProfileServer() == null ? null : sourceEntity.getProfileServer().getName());

        // ACTIU?
        targetVO.setActive(new Boolean(sourceEntity.getActive().compareTo("S") == 0)); //$NON-NLS-1$

        // MULTISESSIO
        String multiSessio = sourceEntity.getMultiSessio();
        if (multiSessio != null) {
            targetVO.setMultiSession(new Boolean(sourceEntity.getMultiSessio().compareTo("S") == 0)); //$NON-NLS-1$
        } else {
            targetVO.setMultiSession(new Boolean(false));
        }

        // GRUP PRIMARI
        GroupEntity grupPrimariEntity = sourceEntity.getPrimaryGroup();
        if (grupPrimariEntity != null) {
            String codiGrupPrimari = grupPrimariEntity.getName();
            targetVO.setPrimaryGroup(codiGrupPrimari);
            // A nivell descriptiu només:
            targetVO.setPrimaryGroupDescription(grupPrimariEntity.getDescription());
        } else {
            targetVO.setPrimaryGroup(""); //$NON-NLS-1$
            targetVO.setPrimaryGroupDescription(""); //$NON-NLS-1$
        }

        // DOMINI DE CORREU
        EmailDomainEntity dominiCorreu = sourceEntity.getMailDomain();
        if (dominiCorreu != null) {
            targetVO.setMailDomain(dominiCorreu.getName());
        }

        // Tipus d'usuari domini: segons taula
        if (sourceEntity.getUserType() != null) {
            UserType tipusu = getUserTypeEntityDao().toUserType(sourceEntity.getUserType());
            targetVO.setUserType(tipusu.getCode());
        }
        
        targetVO.setFullName(sourceEntity.getFullName());

        fetchAttributes(targetVO, sourceEntity);
        
    }


    private void fetchAttributes(User target, UserEntity source) {
		target.setAttributes(new HashMap<String, Object>());
		Map<String, Object> attributes = target.getAttributes();
		for (UserDataEntity att : source.getUserData()) {
			if (att.getDataType().getMultiValued() != null && att.getDataType().getMultiValued().booleanValue())
			{
				LinkedList<Object> r = (LinkedList<Object>) attributes.get(att.getDataType().getName());
				if (r == null)
				{
					r = new LinkedList<Object>();
					attributes.put(att.getDataType().getName(), r);
				}
				r.add(att.getObjectValue());
			}
			else
			{
				attributes.put(att.getDataType().getName(),att.getObjectValue());
			}
		}
		for (Object o: attributes.values())
		{
			if (o != null && o instanceof List) Collections.sort((List) o);
		}
	}

	@Override
    public String handleRefreshCanvis(String codiUsuari) throws InternalErrorException {
        UserEntity usuari = findByUserName(codiUsuari);
        createTask(usuari);
        createMailTask(usuari);
        // tasquesPendents = refresh(codiUsuari);
        return "Synchronization tasks submitted";
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#toUsuari(es.caib.seycon.ng.model.UsuariEntity)
     */
    public com.soffid.iam.api.User toUser(final com.soffid.iam.model.UserEntity entity) {
        User usuari = super.toUser(entity);
        return usuari;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.UserEntity loadUsuariEntityFromUsuari(com.soffid.iam.api.User usuari) {
        com.soffid.iam.model.UserEntity usuariEntity = null;
        if (usuari.getId() != null) {
            usuariEntity = load(usuari.getId());
        }
        if (usuariEntity == null) {
            usuariEntity = newUserEntity();
        }
        return usuariEntity;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#usuariToEntity(es.caib.seycon.ng.comu.Usuari)
     */
    public com.soffid.iam.model.UserEntity userToEntity(com.soffid.iam.api.User usuari) {
        // @todo verify behavior of usuariToEntity
        com.soffid.iam.model.UserEntity entity = this.loadUsuariEntityFromUsuari(usuari);
        this.userToEntity(usuari, entity, true);
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
        List<RoleAccountEntity> rolsUsuaris = getRoleAccountEntityDao().findByUserName(codiUsuari);
        // Obtenim els grups secundaris de l'usuari
        List<UserGroupEntity> grupsSecundarisEntity = getUserGroupEntityDao().findByUserName(codiUsuari);
        HashSet<String> grupsSec = new HashSet<String>();
        // Els afegim en un set
        if (grupsSecundarisEntity != null) {
            for (Iterator<UserGroupEntity> it = grupsSecundarisEntity.iterator(); it.hasNext(); ) {
                UserGroupEntity uge = (UserGroupEntity) it.next();
                if (uge.getGroup() != null) grupsSec.add(uge.getGroup().getName());
            }
        }

        if (rolsUsuaris != null && rolsUsuaris.size() > 0) {
            Iterator<RoleAccountEntity> iterator = rolsUsuaris.iterator();
            while (iterator.hasNext()) {
                RoleAccountEntity rolUsuari = iterator.next();
                if (TipusDomini.GRUPS_USUARI.equals(rolUsuari.getDomainType()) ||
                		TipusDomini.MEMBERSHIPS.equals(rolUsuari.getDomainType())) {
                    String codiGrupValorDomini = rolUsuari.getGroup().getName();
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

    private void usuariToEntityCustom(com.soffid.iam.api.User sourceVO, com.soffid.iam.model.UserEntity targetEntity) {
        // removeOldAlias(targetEntity);

        if (sourceVO.getModifiedDate() != null) {
            targetEntity.setLastModificationDate(sourceVO.getModifiedDate().getTime());
        } else {
            targetEntity.setLastModificationDate(GregorianCalendar.getInstance().getTime());
        }

        String dominiCorreu = sourceVO.getMailDomain();
        if (sourceVO.getActive () == null || !sourceVO.getActive())
        {
        	// Skip mail check
            EmailDomainEntity dominiCorreuEntity = getEmailDomainEntityDao().findByCode(dominiCorreu);
            targetEntity.setMailDomain(dominiCorreuEntity);
        }
        else if (dominiCorreu != null && dominiCorreu.trim().compareTo("") != 0) { //$NON-NLS-1$
            EmailDomainEntity dominiCorreuEntity = getEmailDomainEntityDao().findByCode(dominiCorreu);
            if (dominiCorreuEntity != null) {
                EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(sourceVO.getShortName(), sourceVO.getMailDomain());
                if (llistaCorreuEntity != null) {
                    throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.invalidShortName"), sourceVO.getShortName(), sourceVO.getMailDomain()));
                }
                targetEntity.setMailDomain(dominiCorreuEntity);
            } else {
                throw new SeyconException(String.format(
                        Messages.getString("UserEntityDaoImpl.emailNotFound"), dominiCorreu)); //$NON-NLS-1$
            }
        } else {
            EmailListEntity llistaCorreuEntity = getEmailListEntityDao().findByNameAndDomain(sourceVO.getShortName(), null);
            if (llistaCorreuEntity != null) {
                throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.mailListCollission"), sourceVO.getShortName()));
            }
            targetEntity.setMailDomain(null);
        }

        // NOTA: No admetem que no s'especifique el tipus d'usuari (!!)
        /*
         * if (sourceVO.getTipusUsuari() == null) {
         * targetEntity.setTipusUsuari("E"); }
         */

        if (sourceVO.getUserType() == null || "".equals(sourceVO.getUserType().trim())) { //$NON-NLS-1$
            throw new SeyconException(Messages.getString("UserEntityDaoImpl.needsUserType")); //$NON-NLS-1$
        } else {
            UserTypeEntity tipusUsuari = getUserTypeEntityDao().findByName(sourceVO.getUserType());
            if (tipusUsuari == null) {
                throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.wrongUserType"), sourceVO.getUserType()));
            }
            targetEntity.setUserType(tipusUsuari);
        }

        HostEntity maquina = null;
        String nomServidor = sourceVO.getMailServer();
        if (nomServidor != null && ! nomServidor.trim().isEmpty()) {
            maquina = getHostEntityDao().findByName(sourceVO.getMailServer());
            if (maquina == null) {
                throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.mailServerNotFound"), sourceVO.getMailServer()));
            }
            targetEntity.setMailServer(maquina);
        } else {
            targetEntity.setMailServer(null);
        }

        nomServidor = sourceVO.getProfileServer();
        if (nomServidor != null && ! nomServidor.trim().isEmpty()) {
            maquina = getHostEntityDao().findByName(sourceVO.getProfileServer());
            if (maquina == null) {
                throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.profileServerNotFound"), sourceVO.getProfileServer()));
            }
            targetEntity.setProfileServer(maquina);
        } else {
            targetEntity.setProfileServer(null);
        }

        nomServidor = sourceVO.getHomeServer();
        if (nomServidor != null && ! nomServidor.trim().isEmpty()) {
            maquina = getHostEntityDao().findByName(nomServidor);
            if (maquina == null) {
                throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.homeServerNotFound"), sourceVO.getHomeServer()));
            }
            targetEntity.setHomeServer(maquina);
        } else {
            targetEntity.setHomeServer(null);
        }

        Boolean actiu = sourceVO.getActive();
        if (actiu != null) {
            targetEntity.setActive(sourceVO.getActive().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setActive("N"); //$NON-NLS-1$
        }

        Boolean multiSessio = sourceVO.getMultiSession();
        if (multiSessio != null) {
            targetEntity.setMultiSessio(multiSessio.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setMultiSessio("N"); //$NON-NLS-1$
        }

        String codiGrupPrimari = sourceVO.getPrimaryGroup();
        GroupEntity grupPrimariAnticEntity = targetEntity.getPrimaryGroup();
        String codiGrupPrimariAntic = grupPrimariAnticEntity == null ? null : grupPrimariAnticEntity.getName();
        if (codiGrupPrimari == null || codiGrupPrimariAntic == null
                || codiGrupPrimariAntic.compareTo(codiGrupPrimari) != 0) {
            if (!esPotCanviarGrupPrimari(sourceVO.getUserName(), codiGrupPrimariAntic)) {
                throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.cannotChangeGroup"), codiGrupPrimariAntic, sourceVO.getUserName(), codiGrupPrimariAntic));
            } else {
                if (codiGrupPrimari != null && codiGrupPrimari.trim().compareTo("") != 0) { //$NON-NLS-1$
                    GroupEntity grupPrimariEntity = getGroupEntityDao().findByName(codiGrupPrimari);
                    if (grupPrimariEntity != null) {
                        if (grupPrimariEntity.getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
                            throw new SeyconException(
                                    String.format(
                                            Messages.getString("UserEntityDaoImpl.deletedGroup"), //$NON-NLS-1$
                                            codiGrupPrimari));
                        } else {
                            targetEntity.setPrimaryGroup(grupPrimariEntity);
                        }
                    } else {
                        throw new SeyconException(String.format(
                                Messages.getString("UserEntityDaoImpl.groupNotFound"), codiGrupPrimari)); //$NON-NLS-1$
                    }
                } else {
                    targetEntity.setPrimaryGroup(null);
                }
            }
        }


    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#usuariToEntity(es.caib.seycon.ng.comu.Usuari,
     *      es.caib.seycon.ng.model.UsuariEntity)
     */
    public void userToEntity(com.soffid.iam.api.User sourceVO, com.soffid.iam.model.UserEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of usuariToEntity
        super.userToEntity(sourceVO, targetEntity, copyIfNull);
        if (copyIfNull || sourceVO.getModifiedDate() != null) {
            if (sourceVO.getModifiedDate() == null) {
                targetEntity.setLastModificationDate(null);
            } else {
                targetEntity.setLastModificationDate(sourceVO.getModifiedDate().getTime());
            }
        }
        if (copyIfNull || sourceVO.getCreatedDate() != null) {
            if (sourceVO.getCreatedDate() == null) {
                targetEntity.setCreationDate(null);
            } else {
                targetEntity.setCreationDate(sourceVO.getCreatedDate().getTime());
            }
        }
        usuariToEntityCustom(sourceVO, targetEntity);
    }

    public String[] getTasks(String codiUsuari) {
        LinkedList<String> lista = new LinkedList<String>();
        HashSet<String> agentsActius = new HashSet<String>();
        try {
            /* Obtenim el llistat dels agents actius en aquest moment: */
        	for (SystemEntity system: getSystemEntityDao().findActives())
        	{
            	String codiAgent = system.getName();
            	agentsActius.add(codiAgent);
            }

            for (TaskEntity t : getTaskEntityDao().findByUser(codiUsuari)) {
                String transaccion = t.getTransaction();
                Timestamp datatime = t.getDate();
                Date data = new Date();
                data.setTime(datatime.getTime());
                SimpleDateFormat dateFormat = new SimpleDateFormat(Messages.getString("UserEntityDaoImpl.dateFormat"));
                String dataString = dateFormat.format(data);
                String missatge = t.getMessage();
                String rol = t.getRole() + "@" + t.getDb();
                HashSet<String> hAgentsPendents = new HashSet<String>(agentsActius);
                String agentsPendents = "";
                for (com.soffid.iam.model.TaskLogEntity tl : t.getLogs()) {
                    String codiAgentActual = tl.getSystem().getName();
                    String esComplet = tl.getCompleted();
                    if ("S".equals(esComplet)) hAgentsPendents.remove(codiAgentActual);
                }
                for (Iterator it = hAgentsPendents.iterator(); it.hasNext(); ) {
                    agentsPendents += (String) it.next() + ", ";
                }
                if (agentsPendents.endsWith(", ")) agentsPendents = agentsPendents.substring(0, agentsPendents.length() - 2);
                lista.add(transaccion + " # " + dataString + " # " + missatge + " # " + rol + " # " + agentsPendents);
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
       	return findByUserName(codiUsuari) != null;
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

    public String getNextUserName() {
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

    public String getNextAnonimUser() {
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

    public String getNextHostUserName() {
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
    public void toIdentity(com.soffid.iam.model.UserEntity source, com.soffid.iam.api.Identity target) {
        super.toIdentity(source, target);
        toIdentitatCustom(source, target);
    }

    public void toIdentitatCustom(com.soffid.iam.model.UserEntity source, com.soffid.iam.api.Identity target) {
        String codiUsuari = source.getUserName();
        target.setUserCode(codiUsuari);
        target.setIdentityCode(codiUsuari);
        String descripcio = source.getFirstName() + " " + source.getLastName() + " " + source.getMiddleName();
        target.setDescription(descripcio);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#toIdentitat(es.caib.seycon.ng.model.UsuariEntity)
     */
    public com.soffid.iam.api.Identity toIdentity(final com.soffid.iam.model.UserEntity entity) {
        Identity identitat = super.toIdentity(entity);
        toIdentitatCustom(entity, identitat);
        return identitat;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private com.soffid.iam.model.UserEntity loadUsuariEntityFromIdentitat(com.soffid.iam.api.Identity identitat) {
        /*
         * La identitat es read only
         */
        String codiUsuari = identitat.getUserCode();
        if (codiUsuari != null) {
            UserEntity usuariEntity = findByUserName(codiUsuari);
            if (usuariEntity != null) {
                return usuariEntity;
            } else {
                throw new SeyconException(String.format(
                        Messages.getString("UserEntityDaoImpl.identityNotFound"), codiUsuari)); //$NON-NLS-1$
            }
        }
        throw new SeyconException(Messages.getString("UserEntityDaoImpl.identityNotUser")); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat)
     */
    public com.soffid.iam.model.UserEntity identityToEntity(com.soffid.iam.api.Identity identitat) {
        com.soffid.iam.model.UserEntity entity = this.loadUsuariEntityFromIdentitat(identitat);
        this.identityToEntity(identitat, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat,
     *      es.caib.seycon.ng.model.UsuariEntity)
     */
    public void identityToEntity(com.soffid.iam.api.Identity source, com.soffid.iam.model.UserEntity target, boolean copyIfNull) {
        super.identityToEntity(source, target, copyIfNull);
    }

    /**
     * @see es.caib.seycon.ng.model.UsuariEntityDao#find(int, java.lang.String,
     *      es.caib.seycon.ng.model.Parameter[])
     */

    public UserEntity anonimousUserToEntity(AnonimousUser usuariAnonim) {
        return null;
    }

    public List<UserEntity> findUsersGroupAndSubgroupsByGroupCode(String codiGrup) {// Correcto??
        // Obtenim els subgrups del grup (GrupEntity) [directes]
        Collection grupsISubgrups = getGroupEntityDao().findByParent(codiGrup);

        // Caso de que no tenga subgrupos
        if (grupsISubgrups == null)
            grupsISubgrups = new ArrayList();

        // Añadimos el grupo Inicial
        GroupEntity grup = getGroupEntityDao().findByName(codiGrup);
        if (grup != null)
            grupsISubgrups.add(grup);

        // Conté els codi dels usuaris a propagar:
        HashSet usuarisPropagar = new HashSet();

        // Cerquem els usuaris que tenen com a grup primari qualque grup del
        // llistat, i els que els tenen com a grup secundari
        for (Iterator it = grupsISubgrups.iterator(); it.hasNext(); ) {
            GroupEntity g = (GroupEntity) it.next();
            Collection usuGPrim = null;
            usuGPrim = findByPrimaryGroup(g.getName());
            if (usuGPrim != null) for (Iterator gpr_it = usuGPrim.iterator(); gpr_it.hasNext(); ) {
                UserEntity usu = (UserEntity) gpr_it.next();
                usuarisPropagar.add(usu);
            }
            Collection usuSec = getUserGroupEntityDao().findByGroupName(g.getName());
            if (usuSec != null) for (Iterator gps_it = usuSec.iterator(); gps_it.hasNext(); ) {
                UserGroupEntity usugru = (UserGroupEntity) gps_it.next();
                usuarisPropagar.add(usugru.getUser());
            }
        }

        // Devolvemos los usuariEntity:
        return new ArrayList(usuarisPropagar);

    }

    public Collection findUsuarisByRolUsuariAtorgat(String nomRolAtorgat,
            String baseDeDadesRolAtorgat, String codiAplicacioRolAtorgat, String tipusDomini,
            String codiGrupDominiRolAtorgat, String codiAplicacioDominiRolAtorgat,
            String idValorDominiAplicacioDominiRolAtorgat) {// Correcte??

        LinkedList<UserEntity> usuarisRol = new LinkedList<UserEntity>();

        for (RoleAccountEntity rolAccount : getRoleAccountEntityDao().findByRoleAndDomainValue(nomRolAtorgat, baseDeDadesRolAtorgat, tipusDomini, codiGrupDominiRolAtorgat, codiAplicacioDominiRolAtorgat, new Long(idValorDominiAplicacioDominiRolAtorgat))) {
            com.soffid.iam.model.AccountEntity acc = rolAccount.getAccount();
            if (acc.getType().equals(AccountType.USER) && acc.getUsers().size() == 1) {
                usuarisRol.add(acc.getUsers().iterator().next().getUser());
            }
        }

        return usuarisRol;
    }

    private Collection getParesGrup(GroupEntity grupAnalitzar) {

        Collection totsPares = new HashSet();
        GroupEntity pare = grupAnalitzar.getParent();
        while (pare != null) {
            totsPares.add(pare);
            pare = pare.getParent();
        }

        return totsPares;
    }

    private Collection getRolsContingutsPerPropagar(RoleEntity rol) {
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
        RoleEntity rolActual = null;
        while ((rolActual = (RoleEntity) rolsAnalitzar.poll()) != null) {
            Collection socContenidor = rolActual.getContainedRoles();

            if (socContenidor != null)
                for (Iterator it = socContenidor.iterator(); it.hasNext(); ) {
                RoleDependencyEntity associacio = (RoleDependencyEntity) it.next();
                RoleEntity rolContingut = associacio.getContained();
                rolsPropagar.add(rolContingut);
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
    private Collection getRolsAtorgatsGrupIParesGrup(GroupEntity grup) {

        // 1) Obtenim els grups pares del grup
        HashSet totGrup = new HashSet();
        totGrup.add(grup);
        Collection fillsGrup = getParesGrup(grup);
        totGrup.addAll(fillsGrup);

        // 2) Obtenim els rols atorgats al grup i els grups pare
        HashSet totRolAtorgatGrup = new HashSet();
        for (Iterator it = totGrup.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj != null) {
                GroupEntity g = (GroupEntity) obj;
                Collection rolsAtorgatsG = g.getGrantedRoles();
                if (rolsAtorgatsG != null) totRolAtorgatGrup.addAll(rolsAtorgatsG);
            }
        }

        // 3) Obtenim els rols atorgats als rols:
        HashSet rolsPropagar = new HashSet();
        for (Iterator it = totRolAtorgatGrup.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj != null) {
                RoleGroupEntity rolgrup = (RoleGroupEntity) obj;
                rolsPropagar.add(rolgrup.getGrantedRole());
                Collection rolsAtorgatsRol = getRolsContingutsPerPropagar(rolgrup.getGrantedRole());
                if (rolsAtorgatsRol != null) rolsPropagar.addAll(rolsAtorgatsRol);
            }
        }

        return new ArrayList(rolsPropagar);
    }

    private void propagarRolsAtorgatsGrups(Collection rolsPropagar) {
        // Propaguem els rols
        if (rolsPropagar != null) {
            for (Iterator it = rolsPropagar.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj != null) {
                    RoleEntity role = (RoleEntity) obj;
                    Task updateRole = new Task();
                    updateRole.setTransaction("UpdateRole");
                    updateRole.setTaskDate(Calendar.getInstance());
                    updateRole.setStatus("P");
                    updateRole.setRole(role.getName());
                    updateRole.setDatabase(role.getSystem().getName());
                    TaskEntity tasca = getTaskEntityDao().taskToEntity(updateRole);
                    getTaskEntityDao().create(tasca);
                }
            }
        }
    }

    private static long requestNumber = System.currentTimeMillis();
    private static String requestNumberLock = new String();
    public String getNextUserIDRequest() {
    	synchronized (requestNumberLock)
    	{
    		return Long.toString(requestNumber++);
    	}
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserEntity) {
                UserEntity entity = (UserEntity) obj;
                this.create(entity);
            }
        }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserEntity) {
                UserEntity entity = (UserEntity) obj;
                this.update(entity);
            }
        }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof UserEntity) {
                UserEntity entity = (UserEntity) obj;
                this.remove(entity);
            }
        }
    }

    public UserEntity bPMUserToEntity(BPMUser bPMUser) {
		return null;
	}

    public void toBPMUser(com.soffid.iam.model.UserEntity source, BPMUser target) {
    	target.setUserName(source.getUserName());
    	target.setGivenName(source.getFirstName());
    	target.setGroup(source.getPrimaryGroup().getName());
    	target.setSurName(source.getLastName() + (source.getMiddleName() == null ? "" : " " + source.getMiddleName())); //$NON-NLS-1$ //$NON-NLS-2$
    }

	@Override
    protected void handleCreateUpdateTasks(UserEntity usuari, User oldValue) throws Exception {
		if (usuari.getMailDomain() != null && (oldValue.getMailDomain() == null || !oldValue.getMailDomain().equals(usuari.getMailDomain().getName())) && usuari.getMailDomain().getObsolete() != null && usuari.getMailDomain().getObsolete().compareTo("S") == 0) { //$NON-NLS-1$
		    throw new SeyconException(String.format(Messages.getString("UserEntityDaoImpl.mailDomainNotFound"), usuari.getMailDomain().getName()));
		}
		
		String mailBefore = oldValue.getShortName() + "@" + (oldValue.getMailDomain() == null ? "" : oldValue.getMailDomain()); 
		String mailAfter = usuari.getShortName() + "@" + (usuari.getMailDomain() == null ? "" : usuari.getMailDomain().getName());


		if (! mailBefore.equals(mailAfter) || usuari.getActive().equals("S") != oldValue.getActive().booleanValue() )
		{
			createMailTask(usuari);
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
		    tasque.setDate(new Timestamp(System.currentTimeMillis()));
		    tasque.setTransaction(TaskHandler.UPDATE_LIST_ALIAS);
		    tasque.setAlias(oldValue.getShortName());
		    if (oldValue.getMailDomain() != null)
		        tasque.setMailDomain(oldValue.getMailDomain());
		    getTaskEntityDao().create(tasque);

			tasque = getTaskEntityDao().newTaskEntity();
		    tasque.setDate(new Timestamp(System.currentTimeMillis()));
		    tasque.setTransaction(TaskHandler.UPDATE_LIST_ALIAS);
		    tasque.setAlias(usuari.getShortName());
		    if (usuari.getMailDomain() != null)
		        tasque.setMailDomain(usuari.getMailDomain().getName());
		    getTaskEntityDao().create(tasque);

			tasque = getTaskEntityDao().newTaskEntity();
			tasque.setDate(new Timestamp(System.currentTimeMillis()));
			tasque.setTransaction(TaskHandler.UPDATE_USER_ALIAS);
			tasque.setUser(usuari.getUserName());
			getTaskEntityDao().create(tasque);
		}
		// HERÈNCIA DE ROLS: Atorgació de rfindByCodi(usuari.getCodi())ols a grups
		// Obtenemos los roles otorgados al grupo primario
		// ANTES DE HACER LOS CAMBIOS EN EL USUARIO
		HashSet totGrup = new HashSet();
		GroupEntity grupPrimariAbans = getGroupEntityDao().findPrimaryGroupByUser(usuari.getUserName());
		if (grupPrimariAbans != null)
		    totGrup.add(grupPrimariAbans);
		usuari.setLastModificationDate(GregorianCalendar.getInstance().getTime());
		super.update(usuari);
		getSession(false).flush();

		// HERÈNCIA DE ROLS: Atorgació de rols a grups
		// Només propaguem si es canvia el grup primari (codi es UK)
		if (usuari.getPrimaryGroup() != null && grupPrimariAbans != null && (!usuari.getPrimaryGroup().getName().equals(grupPrimariAbans.getName()))) {

		    // Obtenemos los roles otorgadfindByCodi(usuari.getCodi())os al grupo primario
		    // TRAS EL CAMBIO (si se ha cambiado el grupo primario)
		    GroupEntity grupPrimariU = usuari.getPrimaryGroup();

		    if (grupPrimariU != null)
		        totGrup.add(grupPrimariU);

		    // Ara tenim tots els grups (d'abans del canvi i de després)
		    // Podem propagar els rols dels grups anteriors i els nous
		    HashSet rolsAPropagar = new HashSet();
		    for (Iterator it = totGrup.iterator(); it.hasNext(); ) {
                Object obj = it.next();
                if (obj != null) {
                    GroupEntity g = (GroupEntity) obj;
                    Collection rolsAtorgatsGrupIPare = getRolsAtorgatsGrupIParesGrup(g);
                    if (rolsAtorgatsGrupIPare != null) rolsAPropagar.addAll(rolsAtorgatsGrupIPare);
                }
            }
		    // Propagamos los roles de los grupos anteriores y los actuales:
		    // (creamos las tareas)
		    propagarRolsAtorgatsGrups(rolsAPropagar);
		}

		TaskEntity tasque;
		if (!oldValue.getPrimaryGroup().equals(usuari.getPrimaryGroup().getName())) {
		    tasque = getTaskEntityDao().newTaskEntity();
		    tasque.setDate(new Timestamp(System.currentTimeMillis()));
		    tasque.setTransaction(TaskHandler.UPDATE_GROUP);
		    tasque.setGroup(usuari.getPrimaryGroup().getName());
		    getTaskEntityDao().create(tasque);
		    
			createGroupMailListTaks(usuari.getPrimaryGroup());

			if (oldValue.getPrimaryGroup() != null)
		    {
			    tasque = getTaskEntityDao().newTaskEntity();
			    tasque.setDate(new Timestamp(System.currentTimeMillis()));
			    tasque.setTransaction(TaskHandler.UPDATE_GROUP);
			    tasque.setGroup(oldValue.getPrimaryGroup());
			    getTaskEntityDao().create(tasque);
			    
			    GroupEntity ge = getGroupEntityDao().findByName(oldValue.getPrimaryGroup());
			    
			    if (ge != null)
			    	createGroupMailListTaks(ge);
		    }
		}
		
	}

	@Override
	public Collection<UserEntity> findByText(CriteriaSearchConfiguration criteria, String text) {
		String[] split = text.trim().split(" +");
		Parameter[] params = new Parameter[split.length + 1];
		
		StringBuffer sb = new StringBuffer("select u "
				+ "from com.soffid.iam.model.UserEntity as u "
				+ "where u.tenant.id = :tenantId");
		params[0] = new Parameter("tenantId", Security.getCurrentTenantId());
		for (int i = 0; i < split.length; i++)
		{
			sb.append(" and ");
			params[i+1] = new Parameter("param"+i, "%"+split[i].toUpperCase()+"%");
			sb.append("(upper(u.userName) like :param")
				.append(i)
				.append(" or upper(u.firstName) like :param")
				.append(i)
				.append(" or upper(u.lastName) like :param")
				.append(i)
				.append(" or upper(u.middleName) like :param")
				.append(i)
				.append(")");
		}
		return query(sb.toString(), params);
	}
    
    
}
