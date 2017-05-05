// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

/**
 * @see es.caib.seycon.ng.model.RolEntity
 */
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.jbpm.JbpmContext;

import com.soffid.iam.api.RoleDependencyStatus;
import com.soffid.iam.model.GroupAttributeEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;
import com.soffid.iam.model.RoleAttributeEntity;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.BpmEngine;
import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.bpm.vo.ProcessDefinition;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.SoDRule;
import es.caib.seycon.ng.comu.Tasca;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownRoleException;
import es.caib.seycon.ng.servei.GrupServiceImpl;
import es.caib.seycon.ng.servei.ejb.GrupService;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.ng.utils.TipusContenidorRol;

public class RolEntityDaoImpl extends es.caib.seycon.ng.model.RolEntityDaoBase {

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
        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
                .auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    public void create(es.caib.seycon.ng.model.RolEntity rol)
            throws RuntimeException {
        try {
            // Importante: PRIMERO CREAMOS LA ENTIDAD ROL (obtenemos id)
            super.create(rol); // Creamos la entidad
            getSession(false).flush();

        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.1"), rol.getNom(), message));  //$NON-NLS-1$
        }
    }

    @Override
	protected void handleUpdateMailLists (RolEntity role) throws InternalErrorException
    {
        updateMailLists (role, 10);
    }
    
    private void updateMailLists (RolEntity role, int depth) throws InternalErrorException
    {
    	for ( MailListRoleMemberEntity lce: role.getMailLists())
    	{
    		getLlistaCorreuEntityDao().generateUpdateTasks(lce.getMailList());
    	}
    	if (depth > 0)
    	{
    		for (RolAssociacioRolEntity child: role.getRolAssociacioRolSocContenidor())
    		{
    			updateMailLists(child.getRolContingut(), depth - 1 );
    		}
    	}
    }
    
	private void generatePropagationTasks(
			HashSet<UsuariEntity> usuarisPropagar,
			HashSet<AccountEntity> accountsPropagar,
			HashSet<RolEntity> rolsPropagar, HashSet<GrupEntity> grupsPropagar,
			HashSet<UsuariEntity> usuarisPropagarAfter,
			HashSet<AccountEntity> accountsPropagarAfter,
			HashSet<RolEntity> rolsPropagarAfter,
			HashSet<GrupEntity> grupsPropagarAfter)
			throws InternalErrorException {
		// En update, si no se modifica la tabla sc_roles, no se lanza un
		// updateRole

		// Ara fem la diferència entre els usuaris d'abans i els nous
		// Clonem els de després:
		// USUARIS:
		HashSet<UsuariEntity> usuarisBorrar = new HashSet<UsuariEntity>(usuarisPropagar); // abans
		usuarisBorrar.removeAll(usuarisPropagarAfter);// deixen només els
		                                              // usus que ja no
		                                              // tenen el rol

		HashSet<UsuariEntity> usuarisAfegir = new HashSet<UsuariEntity>(usuarisPropagarAfter); // després
		usuarisAfegir.removeAll(usuarisPropagar); // deixem els nous
		                                          // (eliminem els q es
		                                          // mantenen)

		// Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
		// d'actualitzar tots dos)
		usuarisAfegir.addAll(usuarisBorrar);

		// ACCOUNTS:
		HashSet<AccountEntity> accountsBorrar = new HashSet<AccountEntity>(accountsPropagar); // abans
		accountsBorrar.removeAll(accountsPropagarAfter);// deixen només els
		                                              // usus que ja no
		                                              // tenen el rol

		HashSet<AccountEntity> accountsAfegir = new HashSet<AccountEntity>(accountsPropagarAfter); // després
		accountsAfegir.removeAll(accountsPropagar); // deixem els nous
		                                          // (eliminem els q es
		                                          // mantenen)

		// Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
		// d'actualitzar tots dos)
		usuarisAfegir.addAll(usuarisBorrar);

 
		// ROLS:
		HashSet<RolEntity> rolsBorrar = new HashSet<RolEntity>(rolsPropagar); // abans
		rolsBorrar.removeAll(rolsPropagarAfter); // deixem només els rols q
		                                         // ja no estan

		HashSet<RolEntity> rolsAfegir = new HashSet<RolEntity>(rolsPropagarAfter); // després
		rolsAfegir.removeAll(rolsPropagar); // deixem els nous (eliminem els
		                                    // q es mantenen)

		// Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
		// d'actualitzar tots dos)
		rolsAfegir.addAll(rolsBorrar);

		// GRUPS:
		HashSet<GrupEntity> grupsBorrar = new HashSet<GrupEntity>(grupsPropagar); // abans
		grupsBorrar.removeAll(grupsPropagarAfter);

		HashSet<GrupEntity> grupsAfegir = new HashSet<GrupEntity>(grupsPropagarAfter); // després
		grupsAfegir.removeAll(grupsPropagar);

		grupsAfegir.addAll(grupsBorrar);

		// I fem la propagació: només dels que siguen "nous"
		propagarUsuarisRolsIGrups(usuarisAfegir, accountsAfegir, rolsAfegir, grupsAfegir);
	}

	
	/**
	 * Returns true if an approval process has been initiated
	 * 
	 * @param role
	 * @return
	 * @throws InternalErrorException 
	 * @throws BPMException 
	 */
	private boolean startApprovalProcess (RolEntity role) throws InternalErrorException, BPMException
	{
		java.util.Date now = new java.util.Date();
		String approvalProcess = role.getAplicacio().getRoleDefinitionProcess();
		if (approvalProcess == null || approvalProcess.trim().isEmpty())
		{
			if (role.getApprovalStart() == null ||
					now.getTime() - role.getApprovalStart().getTime() > 2000) // Updated more than 2 secons ago
			{
				role.setApprovalEnd(now);
				role.setApprovalStart(now);
				update (role);
			}
			return false;
		}
		else
		{
			BpmEngine engine = getBpmEngine();
			Security.nestedLogin(Security.getCurrentAccount(), new String [] {
				"BPM_INTERNAL" //$NON-NLS-1$
			});
			try {
				
				if (role.getApprovalProcess() != null)
				{
					ProcessInstance oldProcess = engine.getProcess(role.getApprovalProcess());
					if (oldProcess.getEnd() == null)
					{
						if (role.getApprovalStart() != null &&
								now.getTime() - role.getApprovalStart().getTime() < 60000) // WF created less than one minute ago
						{
							return true;
						}
						else	// Cancel current WF and start a new one
							engine.cancel(oldProcess);
					}
				}
				// Starts new workflow
				List<ProcessDefinition> defs = getBpmEngine().findProcessDefinitions(approvalProcess, PredefinedProcessType.ROLE_DEFINITION_APPROVAL);
				if (defs == null || defs.isEmpty())
					throw new InternalErrorException ("No business process found with name '%s'", approvalProcess);
				ProcessDefinition def = defs.get(0);
	
				ProcessInstance pi = engine.newProcess(def, false);
				pi.getVariables().put("roleId", role.getId());
				pi.getVariables().put("roleName", role.getNom());
				pi.getVariables().put("system", role.getBaseDeDades().getCodi());
				pi.getVariables().put("application", role.getAplicacio().getNom());
				pi.getVariables().put("requester", Security.getCurrentUser());
				engine.update(pi);
				engine.startProcess(pi);
				
				role.setApprovalProcess( pi.getId());
				role.setApprovalEnd(null);
				role.setApprovalStart(new java.util.Date());
				update (role);
				
				return true;
			} finally {
				Security.nestedLogoff();
			}
		}
	}
	
	private void updateGranteeRoles(Rol role, es.caib.seycon.ng.model.RolEntity entity) throws InternalErrorException, BPMException {
		// Compraramos con los existentes anteriormente : i esborrem els que
		// ja no existeixen
		LinkedList<RolAssociacioRolEntity> currentGrants = new LinkedList<RolAssociacioRolEntity>(entity.getRolAssociacioRolSocContingut());
		if (role.getOwnerRoles() != null) {
			for (RolGrant grant: role.getOwnerRoles())
			{
				boolean found = false;
				for ( Iterator<RolAssociacioRolEntity> it = currentGrants.iterator(); it.hasNext();)
				{
					RolAssociacioRolEntity grantEntity = it.next();
					if (grantEntity.getId().equals( grant.getId()))
					{
						found = true;
						it.remove();
						updateRolDependency(grantEntity, grant);
						break;
					}
						
				}
				if ( ! found )
					createRoleDependency(grant);
			}
		}
		for ( RolAssociacioRolEntity grantEntity: currentGrants)
		{
			deleteRolDependency(grantEntity);
		}
	}

	private void updateGranteeGroups(Rol role, es.caib.seycon.ng.model.RolEntity entity) throws InternalErrorException, BPMException {
		// Compraramos con los existentes anteriormente : i esborrem els que
		// ja no existeixen
		LinkedList<RolsGrupEntity> currentGrants = new LinkedList<RolsGrupEntity>(entity.getGrupsPosseidorsRol());
		if (role.getGranteeGroups() != null) {
			for (RolGrant grant: role.getGranteeGroups())
			{
				boolean found = false;
				for ( Iterator<RolsGrupEntity> it = currentGrants.iterator(); it.hasNext();)
				{
					RolsGrupEntity grantEntity = it.next();
					if (grantEntity.getId().equals( grant.getId()))
					{
						found = true;
						it.remove();
						// NO update allowed
//						updateRoleGroupDependency(grantEntity, grant);
						break;
					}
						
				}
				if ( ! found )
					createRoleGroupDependency(grant);
			}
		}
		for ( RolsGrupEntity grantEntity: currentGrants)
		{
			deleteRoleGroupDependency(grantEntity);
		}
	}

	private void updateGrantedRoles(Rol role, es.caib.seycon.ng.model.RolEntity entity) throws InternalErrorException, BPMException {
		// Compraramos con los existentes anteriormente : i esborrem els que
		// ja no existeixen
		LinkedList<RolAssociacioRolEntity> currentGrants = new LinkedList<RolAssociacioRolEntity>(entity.getRolAssociacioRolSocContenidor());
		if (role.getOwnedRoles() != null) {
			for (RolGrant grant: role.getOwnedRoles())
			{
				boolean found = false;
				for ( Iterator<RolAssociacioRolEntity> it = currentGrants.iterator(); it.hasNext();)
				{
					RolAssociacioRolEntity grantEntity = it.next();
					if (grantEntity.getId().equals( grant.getId()))
					{
						found = true;
						it.remove();
						updateRolDependency(grantEntity, grant);
						break;
					}
						
				}
				if ( ! found )
					createRoleDependency(grant);
			}
		}
		for ( RolAssociacioRolEntity grantEntity: currentGrants)
		{
			deleteRolDependency(grantEntity);
		}
	}

	private void createRoleDependency(RolGrant grant) throws InternalErrorException, BPMException {
		StringBuffer path = new StringBuffer();
        RolEntity ownerRole = load (grant.getOwnerRol()); 
        RolEntity ownedRole = load (grant.getIdRol()); 
        
        if (checkNoCycles( grant, path)) {
        	RolAssociacioRolEntity entity = getRolAssociacioRolEntityDao().newRolAssociacioRolEntity();
        	
	        entity.setRolContingut(ownedRole);
	        entity.setRolContenidor(ownerRole);

	        assignDomainValue(entity, grant, ownedRole,
            		ownerRole);

            assignGranteeDomainValue(entity, grant, ownedRole,
            		ownerRole);

            if ( Hibernate.isInitialized(ownedRole.getRolAssociacioRolSocContingut()))
            	ownedRole.getRolAssociacioRolSocContingut().add(entity);
            
            if ( Hibernate.isInitialized(ownerRole.getRolAssociacioRolSocContenidor()))
            	ownerRole.getRolAssociacioRolSocContenidor().add(entity);

            if (startApprovalProcess(ownerRole))
            	entity.setStatus(RoleDependencyStatus.STATUS_TOAPPROVE);
            else
            	entity.setStatus(RoleDependencyStatus.STATUS_ACTIVE);
            
            getRolAssociacioRolEntityDao().create(entity);

        } else {
			throw new InternalErrorException(String.format(Messages.getString("RolEntityDaoImpl.0"),   //$NON-NLS-1$
					ownedRole.toDescripcioRol(), 
					ownerRole.toDescripcioRol(), 
					path));
        }
	}


	private void createRoleGroupDependency(RolGrant grant) throws InternalErrorException, BPMException {
		StringBuffer path = new StringBuffer();
        GrupEntity ownerGroup = getGrupEntityDao().findByCodi(grant.getOwnerGroup()); 
        RolEntity ownedRole = load (grant.getIdRol()); 
    
    	RolsGrupEntity entity = getRolsGrupEntityDao().newRolsGrupEntity();
    	
        entity.setRolOtorgat(ownedRole);
        entity.setGrupPosseidor(ownerGroup);

        String nomDomini = ownedRole.getTipusDomini();
        if (TipusDomini.APLICACIONS.equals(nomDomini))
        {
        	entity.setGrantedApplicationDomain(getAplicacioEntityDao().findByCodi(grant.getDomainValue()));
        }
        else if (TipusDomini.GRUPS.equals(nomDomini) || TipusDomini.GRUPS_USUARI.equals(nomDomini))
        {
        	entity.setGrantedGroupDomain(getGrupEntityDao().findByCodi(grant.getDomainValue()));
        }
        else if (TipusDomini.DOMINI_APLICACIO.equals(nomDomini))
        {
        	entity.setGrantedDomainValue(
        			getValorDominiAplicacioEntityDao()
        				.findByApplicationDomainValue(
        						ownedRole.getAplicacio().getCodi(), 
        						ownedRole.getDominiAplicacio().getNom(), 
        						grant.getDomainValue()));
        }

        if ( Hibernate.isInitialized(ownedRole.getGrupsPosseidorsRol()))
        	ownedRole.getGrupsPosseidorsRol().add(entity);
        
        if ( Hibernate.isInitialized(ownerGroup.getRolsOtorgatsGrup()))
        	ownerGroup.getRolsOtorgatsGrup().add(entity);

        getRolsGrupEntityDao().create(entity);

	}

	private void deleteRoleGroupDependency(RolsGrupEntity entity) throws InternalErrorException, BPMException {
		getRolsGrupEntityDao().remove(entity);
	}
	
	private void deleteRolDependency(RolAssociacioRolEntity entity) throws InternalErrorException, BPMException {
        RolEntity ownerRole = entity.getRolContenidor();
        
        // Delete a not approved yet role or no approval is needed
        if (RoleDependencyStatus.STATUS_TOAPPROVE.equals( entity.getStatus()) || 
        		!startApprovalProcess(ownerRole))
        {
        	getRolAssociacioRolEntityDao().remove(entity);
        } else {
        	entity.setStatus(RoleDependencyStatus.STATUS_TOREMOVE);
        	getRolAssociacioRolEntityDao().update(entity);
        }
	}

	private void updateRolDependency(RolAssociacioRolEntity entity, RolGrant grant) throws InternalErrorException, BPMException {
		if (grant.getStatus().equals(RoleDependencyStatus.STATUS_TOREMOVE))
			deleteRolDependency(entity);
		// No update is allowed
	}

    public void remove(es.caib.seycon.ng.model.RolEntity rol)
            throws RuntimeException {
        try {
            updateMailLists (rol);
            // NO SE PUEDE BORRAR UN ROL SI TIENE RELACIONES EXTERNAS
            // SE DA UN AVISO Y NO SE DEJA BORRAR EL ROL

            // Obtenemos sus relaciones con otros roles (como contenedor o
            // contenido)
            Collection rolAssociacioRolSocContenidor = rol
                    .getRolAssociacioRolSocContenidor();
            Collection rolAssociacioRolSocContingut = rol
                    .getRolAssociacioRolSocContingut();
            Collection grupsPosseidors = rol.getGrupsPosseidorsRol();
//            Collection rolFitxers = rol.getRolFitxers();
            Collection rolsUsuari = rol.getAccounts();
            Collection rolsAutoritzacioXarxa = rol.getAutoritzacionsXarxa();

            String msgError = ""; //$NON-NLS-1$
            if (rolAssociacioRolSocContenidor.size() != 0) {
                msgError += Messages.getString("RolEntityDaoImpl.3");  //$NON-NLS-1$
            } else if (rolAssociacioRolSocContingut.size() != 0) {
                msgError += Messages.getString("RolEntityDaoImpl.4");  //$NON-NLS-1$
            } else if (grupsPosseidors.size() != 0) {
                msgError += Messages.getString("RolEntityDaoImpl.5");  //$NON-NLS-1$
//          } else if (rolFitxers.size() != 0) {
//                msgError += Messages.getString("RolEntityDaoImpl.6");  //$NON-NLS-1$
            } else if (rolsUsuari.size() != 0) {
                msgError += Messages.getString("RolEntityDaoImpl.7");  //$NON-NLS-1$
            } else if (rolsAutoritzacioXarxa.size() != 0) {
                msgError += Messages.getString("RolEntityDaoImpl.8");  //$NON-NLS-1$
            } else if (rol.getNotificacioEntities().size() != 0) {
                msgError += Messages.getString("RolEntityDaoImpl.9");  //$NON-NLS-1$
            }

            // Generamos error si se cumple alguna de las condiciones
            if (!"".equals(msgError)) //$NON-NLS-1$
                throw new Exception(String.format(
                        Messages.getString("RolEntityDaoImpl.10"), msgError));  //$NON-NLS-1$

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

            String nomRol = rol.getNom();
            String codiBaseDeDades = rol.getBaseDeDades().getCodi();
            String codiAplicacio = rol.getAplicacio().getCodi();

            // Abans d'eliminar el rol, obtenim els grups, rols i usuaris
            // afectats indirectament (per herència)
            HashSet<UsuariEntity> usuarisPropagar = new HashSet<UsuariEntity>();
            HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
            HashSet<RolEntity> rolsPropagar = new HashSet<RolEntity>();
            HashSet<GrupEntity> grupsPropagar = new HashSet<GrupEntity>();
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

            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_ROLE);
            tasque.setRole(rol.getNom());
            tasque.setBd(rol.getBaseDeDades().getCodi());
            getTasqueEntityDao().createNoFlush(tasque);
            auditarRol("D", nomRol, codiAplicacio, codiBaseDeDades); //$NON-NLS-1$
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.11"), rol.getNom(), message));  //$NON-NLS-1$
        }
    }

    public void toRol(es.caib.seycon.ng.model.RolEntity sourceEntity,
            es.caib.seycon.ng.comu.Rol targetVO) {
        super.toRol(sourceEntity, targetVO);
        toRolCustom(sourceEntity, targetVO);
    }

    private void toRolCustom(es.caib.seycon.ng.model.RolEntity sourceEntity,
            es.caib.seycon.ng.comu.Rol targetVO) {

        // Obtenemos la relación entre este rol y los grupos (1:N)
        Collection grupsEntityPosseidors = sourceEntity.getGrupsPosseidorsRol(); // tipo
                                                                                 // RolsGrupEntity
        Collection<Grup> grupsPosseidors = new ArrayList<Grup>(); // tipo Grup
        Collection<RolGrant> granteeGroups = new ArrayList<RolGrant>(); // tipo Grup
        if (grupsEntityPosseidors != null) {
            for (Iterator it = grupsEntityPosseidors.iterator(); it.hasNext();) {
                RolsGrupEntity rg = (RolsGrupEntity) it.next(); // Rol-Grup
                                                                // (Entity)
                if (rg.getGrupPosseidor() != null) {
                    GrupEntity posseidor = (GrupEntity) rg.getGrupPosseidor();
                    Grup grupo = getGrupEntityDao().toGrup(posseidor); // Grup
                                                                            // VO
                    grupsPosseidors.add(grupo);
                    granteeGroups.add ( getRolsGrupEntityDao().toRolGrant(rg));
                }
            }
        }
        targetVO.setOwnerGroups(grupsPosseidors);
        targetVO.setGranteeGroups(granteeGroups);

        String tipusDomini = sourceEntity.getTipusDomini();
        if (tipusDomini == null || tipusDomini.trim().compareTo("") == 0) { //$NON-NLS-1$
            tipusDomini = TipusDomini.SENSE_DOMINI;
        }
        if (tipusDomini.compareTo(TipusDomini.DOMINI_APLICACIO) == 0) {
            DominiAplicacioEntity dominiAplicacioEntity = sourceEntity
                    .getDominiAplicacio();
            Domini domini = getDominiAplicacioEntityDao().toDomini(
                    dominiAplicacioEntity);
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

        AplicacioEntity aplicacioEntity = sourceEntity.getAplicacio();
        if (aplicacioEntity != null) {
            targetVO.setCodiAplicacio(aplicacioEntity.getCodi());
        }
        targetVO.setDefecte(new Boolean(sourceEntity.getDefecte()
                .compareTo("S") == 0)); //$NON-NLS-1$
        targetVO.setGestionableWF(new Boolean(sourceEntity.getGestionableWF()
                .compareTo("S") == 0)); //$NON-NLS-1$
        String contrasenya = sourceEntity.getContrasenya();
        if (contrasenya != null && contrasenya.trim().compareTo("") != 0) { //$NON-NLS-1$
            targetVO.setContrasenya(new Boolean(sourceEntity.getContrasenya()
                    .compareTo("S") == 0)); //$NON-NLS-1$
        }
        DispatcherEntity baseDeDades = sourceEntity.getBaseDeDades();
        if (baseDeDades != null) {
            targetVO.setBaseDeDades(baseDeDades.getCodi());
        }

        // Obtenemos los roles padres (en los que estamos contenidos) - somos
        // otorgados
        Collection<RolAssociacioRolEntity> pares = sourceEntity.getRolAssociacioRolSocContingut();
        Collection<RolGrant> rolsPosseidorsRol = new LinkedList<RolGrant>();
        if (pares != null) {
            for (Iterator<RolAssociacioRolEntity> iterator = pares.iterator(); iterator.hasNext();) {
                RolAssociacioRolEntity currentPareRolAssociacioRol = (RolAssociacioRolEntity) iterator
                        .next();
                RolGrant rg = getRolAssociacioRolEntityDao().toRolGrant(currentPareRolAssociacioRol);
                rolsPosseidorsRol.add(rg);

            }
        }
        targetVO.setOwnerRoles(rolsPosseidorsRol);

        // Obtenim els rols que tinc atorgats com a "fills"
        Collection<RolAssociacioRolEntity> fills = sourceEntity.getRolAssociacioRolSocContenidor();
        Collection<RolGrant> rolsAtorgatsRol = new LinkedList<RolGrant>();
        if (fills != null) {
            for (Iterator<RolAssociacioRolEntity> iterator = fills.iterator(); iterator.hasNext();) {
                RolAssociacioRolEntity currentPareRolAssociacioRol = (RolAssociacioRolEntity) iterator
                        .next();
                RolGrant rg = getRolAssociacioRolEntityDao().toRolGrant(currentPareRolAssociacioRol);
                rolsAtorgatsRol.add(rg);

            }
        }
        targetVO.setOwnedRoles(rolsAtorgatsRol); 

        // Indicador de si está otorgado a roles o a grupos (*=true)
        targetVO.setAssignacioIndirecta((rolsPosseidorsRol.size() != 0 || grupsPosseidors
                .size() != 0) ? "*" : ""); //$NON-NLS-1$ //$NON-NLS-2$


        targetVO.setAttributes(new HashMap<String, Object>());
		for ( RoleAttributeEntity att: sourceEntity.getAttributes())
		{
			targetVO.getAttributes().put(att.getMetadata().getCodi(), att.getObjectValue());
		}
    }

    /**
     * @see es.caib.seycon.ng.model.RolEntityDao#toRol(es.caib.seycon.ng.model.RolEntity)
     */
    public es.caib.seycon.ng.comu.Rol toRol(
            final es.caib.seycon.ng.model.RolEntity entity) {
        es.caib.seycon.ng.comu.Rol role = super.toRol(entity);
        // toRolCustom(entity, role); //NO ES FA ACI (!!)
        return role;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.RolEntity loadRolEntityFromRol(
            es.caib.seycon.ng.comu.Rol role) {
        RolEntity rolEntity = null;
        if (role.getId() != null) {
            rolEntity = load(role.getId());
        }
        if (rolEntity == null) {
            rolEntity = newRolEntity();
        }
        return rolEntity;
    }

    public es.caib.seycon.ng.model.RolEntity rolToEntity(
            es.caib.seycon.ng.comu.Rol role) {
        es.caib.seycon.ng.model.RolEntity entity = this
                .loadRolEntityFromRol(role);
        rolToEntity(role, entity, true);
        return entity;
    }

    private ValorDominiAplicacioEntity findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(
            String nomDomini, String codiAplicacio, String valor) {
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

        Collection valorsDomini = getValorDominiAplicacioEntityDao().query(
                query, parametres);
        if (valorsDomini != null) {
            Iterator valorsDominiIterator = valorsDomini.iterator();
            if (valorsDominiIterator != null) {
                if (valorsDominiIterator.hasNext()) {
                    ValorDominiAplicacioEntity valorDominiEntity = (ValorDominiAplicacioEntity) valorsDominiIterator
                            .next();
                    return valorDominiEntity;
                }
            }
        }
        return null;
    }

    private void rolToEntityCustom(es.caib.seycon.ng.comu.Rol sourceVO,
            es.caib.seycon.ng.model.RolEntity targetEntity) {// de VO a Entity

        // Transformación a nivel de Objeto (NO ACCESO BBDD)
        // targetEntity puede estar vacía (CREATE) o tener referencias (UPDATE)
        // [importante]

        updateEntityDomainType(sourceVO, targetEntity);
        updateEntityApplication(sourceVO, targetEntity);
        upateEntityOthers(sourceVO, targetEntity);

    }

	private void upateEntityOthers(es.caib.seycon.ng.comu.Rol sourceVO,
			es.caib.seycon.ng.model.RolEntity targetEntity) {
		Boolean perDefecte = sourceVO.getDefecte();
        if (perDefecte != null) {
            targetEntity.setDefecte(sourceVO.getDefecte().booleanValue() ? "S" //$NON-NLS-1$
                    : "N"); //$NON-NLS-1$
        } else {
            targetEntity.setDefecte("N"); //$NON-NLS-1$
        }
        Boolean contrasenya = sourceVO.getContrasenya();
        if (contrasenya != null) {
            targetEntity.setContrasenya(sourceVO.getContrasenya()
                    .booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            targetEntity.setContrasenya("N"); //$NON-NLS-1$
        }
        Boolean gestionableWF = sourceVO.getGestionableWF();
        if (gestionableWF != null) {
            targetEntity.setGestionableWF(sourceVO.getGestionableWF()
                    .booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
        } else
            targetEntity.setGestionableWF("N"); //$NON-NLS-1$
        String codiDispatcher = sourceVO.getBaseDeDades();
        if (codiDispatcher != null && codiDispatcher.trim().compareTo("") != 0) { //$NON-NLS-1$
            DispatcherEntity dispatcherEntity = this.getDispatcherEntityDao()
                    .findByCodi(codiDispatcher);
            if (dispatcherEntity != null) {
                targetEntity.setBaseDeDades(dispatcherEntity);
            } else {
				throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.18"),   //$NON-NLS-1$
						codiDispatcher));
            }
        } else {
            targetEntity.setBaseDeDades(null);
        }
	}

	private void updateEntityApplication(es.caib.seycon.ng.comu.Rol sourceVO,
			es.caib.seycon.ng.model.RolEntity targetEntity) {
		String codiAplicacio = sourceVO.getCodiAplicacio();
        if (codiAplicacio != null) {
            AplicacioEntity aplicacioEntity = getAplicacioEntityDao()
                    .findByCodi(codiAplicacio);
            targetEntity.setAplicacio(aplicacioEntity);
        } else {
            targetEntity.setAplicacio(null);
        }
	}

	private void updateEntityGranteeRoles(es.caib.seycon.ng.comu.Rol sourceVO,
			es.caib.seycon.ng.model.RolEntity targetEntity) {
		Collection<RolAssociacioRolEntity> rolAssociacioRolSocContingut = new HashSet<RolAssociacioRolEntity>();
        // Los que somos el contenedor (socContenidor) no aparece en el VO
        if (sourceVO.getOwnerRoles() != null) {
            // Creamos las relaciones nuevas
            for (Iterator<RolGrant> iterator = sourceVO.getOwnerRoles().iterator(); iterator
                    .hasNext();) {
                // Los pares son una cadena
                // nomrol@dispatcher>aplicacio{tipusdomini:valor[descripcio]}
                // (la part del domini és opcional)
                RolGrant currentGrant = iterator.next();
                if (currentGrant != null) { //$NON-NLS-1$
                    // Obtenemos la entidad Rol padre desde la BBDD
                    RolEntity rolEntityFound = load (currentGrant.getOwnerRol()); 
    		        RolAssociacioRolEntity rare = getRolAssociacioRolEntityDao().newRolAssociacioRolEntity();
    		        
    		        rare.setRolContingut(targetEntity);
    		        rare.setRolContenidor(rolEntityFound);

    		        assignDomainValue(rare, currentGrant, targetEntity,
                    		rolEntityFound);

                    assignGranteeDomainValue(rare, currentGrant, targetEntity,
                    		rolEntityFound);

    		        rolAssociacioRolSocContingut.add(rare);
                }

            }
            targetEntity
                    .setRolAssociacioRolSocContingut(rolAssociacioRolSocContingut);
        }
	}

	private void updateEntityGranteeGroups(es.caib.seycon.ng.comu.Rol sourceVO,
			es.caib.seycon.ng.model.RolEntity targetEntity) {
		Collection<Grup> grupsPosseidors = sourceVO.getOwnerGroups();
        Collection<RolGrant> granteeGroups = sourceVO.getGranteeGroups();
        // Eliminamos las referencias existentes
        Collection<RolsGrupEntity> grupsPosseidorsRolEntity = new HashSet<RolsGrupEntity>();
        if (granteeGroups == null && grupsPosseidors != null) {
            // Creamos las relaciones existentes con los grupos
            for (Iterator<Grup> it = grupsPosseidors.iterator(); it.hasNext();) {
                // El VO grup siempre tendrá ID (!!)
                Grup grup = it.next();
                GrupEntity grupEntity = getGrupEntityDao().findById(
                        grup.getId());
                // creamos la instancia A NIVEL DE OBJETO
                RolsGrupEntity rge = getRolsGrupEntityDao().newRolsGrupEntity();
                rge.setRolOtorgat(targetEntity);
                rge.setGrupPosseidor(grupEntity);
                grupsPosseidorsRolEntity.add(rge);
            }
        } else if (granteeGroups != null) {
            // Creamos las relaciones existentes con los grupos
            for (Iterator<RolGrant> it = granteeGroups.iterator(); it.hasNext();) {
                // El VO grup siempre tendrá ID (!!)
                RolGrant grant = it.next();
                GrupEntity grupEntity = getGrupEntityDao().findByCodi(grant.getOwnerGroup());
                if (grupEntity == null)
                	throw new java.lang.IllegalArgumentException("group "+grant.getOwnerGroup());
                // creamos la instancia A NIVEL DE OBJETO
                RolsGrupEntity rge = getRolsGrupEntityDao().newRolsGrupEntity();
                rge.setRolOtorgat(targetEntity);
                rge.setGrupPosseidor(grupEntity);
                Domini domini = sourceVO.getDomini();
                String nomDomini = targetEntity.getTipusDomini();
                if (TipusDomini.APLICACIONS.equals(nomDomini))
                {
                	rge.setGrantedApplicationDomain(getAplicacioEntityDao().findByCodi(grant.getDomainValue()));
                }
                else if (TipusDomini.GRUPS.equals(nomDomini) || TipusDomini.GRUPS_USUARI.equals(nomDomini))
                {
                	rge.setGrantedGroupDomain(getGrupEntityDao().findByCodi(grant.getDomainValue()));
                }
                else if (TipusDomini.DOMINI_APLICACIO.equals(nomDomini))
                {
                	rge.setGrantedDomainValue(
                			getValorDominiAplicacioEntityDao()
                				.findByApplicationDomainValue(
                						targetEntity.getAplicacio().getCodi(), 
                						targetEntity.getDominiAplicacio().getNom(), 
                						grant.getDomainValue()));
                }
                grupsPosseidorsRolEntity.add(rge);
            }
        	
        }
        targetEntity.setGrupsPosseidorsRol(grupsPosseidorsRolEntity);
	}

	private void updateEntityDomainType(es.caib.seycon.ng.comu.Rol sourceVO,
			es.caib.seycon.ng.model.RolEntity targetEntity) {
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
            targetEntity.setDominiAplicacio(null);
            targetEntity.setTipusDomini(nomDomini);
        } else {
            DominiAplicacioEntity dominiAplicacioEntity = findDominiByNomAndCodiApliacio(
                    domini.getNom(), sourceVO.getCodiAplicacio());
            if (dominiAplicacioEntity != null) {
                targetEntity.setDominiAplicacio(dominiAplicacioEntity);
                targetEntity.setTipusDomini(TipusDomini.DOMINI_APLICACIO);
            } else {
				throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.19"),   //$NON-NLS-1$
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
					throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.20"),   //$NON-NLS-1$
							sourceVO.getNom(), 
							domini.getNom()));
                }
            }
        }
	}

	private void assignDomainValue(
			RolAssociacioRolEntity rare,
			RolGrant currentPare, 
			es.caib.seycon.ng.model.RolEntity grantedRole,
			RolEntity granteeRole) {
		// Añadimos la relación con el padre
		if (granteeRole != null) {
		    // Podemos tener dos casos: que el rol no tenga Dominio
		    // o que si tenga
		    String tipusDominiAsoc = grantedRole.getTipusDomini();
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
		        GrupEntity grupAsoc = getGrupEntityDao()
		                .findByCodi(currentPare.getDomainValue());
		        if (grupAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.14"),   //$NON-NLS-1$
							currentPare.getDomainValue()));
		        }
		        rare.setGrantedGroupDomain(grupAsoc);
		    } else if (TipusDomini.APLICACIONS
		            .equals(tipusDominiAsoc)) {
		        AplicacioEntity appAsoc = getAplicacioEntityDao()
		                .findByCodi(currentPare.getDomainValue());
		        if (appAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.15"),  //$NON-NLS-1$
							currentPare.getDomainValue()));
		        }
		        rare.setGrantedApplicationDomain(appAsoc);
		    } else if (TipusDomini.DOMINI_APLICACIO
		            .equals(tipusDominiAsoc)) {
		        ValorDominiAplicacioEntity valdomAsoc = getValorDominiAplicacioEntityDao()
		                .findValorDominiByNomDominiAndNomRolDominiAndValorDomini(
		                        grantedRole.getDominiAplicacio().getNom(), 
		                        grantedRole.getNom(),
		                        currentPare.getDomainValue());
		        if (valdomAsoc == null) {
					throw new SeyconException(String.format(
							Messages.getString("RolEntityDaoImpl.16"),   //$NON-NLS-1$
							grantedRole.getDominiAplicacio().getNom(),
							currentPare.getDomainValue()));
		        }
		        rare.setGrantedDomainValue(valdomAsoc);
		    }
		} else {
			throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.17"),   //$NON-NLS-1$
					currentPare.getOwnerRolName(), 
					currentPare.getOwnerRol(), 
					currentPare.getOwnerDispatcher()));
		}
	}

	private void assignGranteeDomainValue(
			RolAssociacioRolEntity rare,
			RolGrant grant, 
			es.caib.seycon.ng.model.RolEntity grantedRole,
			RolEntity granteeRole) {
		// Añadimos la relación con el padre
		if (granteeRole != null) {
		    // Podemos tener dos casos: que el rol no tenga Dominio
		    // o que si tenga
		    String tipusDominiAsoc = granteeRole.getTipusDomini();
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
		        GrupEntity grupAsoc = getGrupEntityDao()
		                .findByCodi(grant.getOwnerRolDomainValue());
		        if (grupAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.14"),   //$NON-NLS-1$
							grant.getDomainValue()));
		        }
		        rare.setGranteeGroupDomain(grupAsoc);
		    } else if (TipusDomini.APLICACIONS
		            .equals(tipusDominiAsoc)) {
		        AplicacioEntity appAsoc = getAplicacioEntityDao()
		                .findByCodi(grant.getOwnerRolDomainValue());
		        if (appAsoc == null) {
					throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.15"),  //$NON-NLS-1$
							grant.getDomainValue()));
		        }
		        rare.setGranteeApplicationDomain(appAsoc);
		    } else if (TipusDomini.DOMINI_APLICACIO
		            .equals(tipusDominiAsoc)) {
		        ValorDominiAplicacioEntity valdomAsoc = getValorDominiAplicacioEntityDao()
		                .findValorDominiByNomDominiAndNomRolDominiAndValorDomini(
		                        granteeRole.getDominiAplicacio().getNom(), 
		                        granteeRole.getNom(),
		                        grant.getOwnerRolDomainValue());
		        if (valdomAsoc == null) {
					throw new SeyconException(String.format(
							Messages.getString("RolEntityDaoImpl.16"),   //$NON-NLS-1$
							granteeRole.getDominiAplicacio().getNom(),
							grant.getDomainValue()));
		        }
		        rare.setGranteeDomainValue(valdomAsoc);
		    }
		} else {
			throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.17"),   //$NON-NLS-1$
					grant.getOwnerRolName(), 
					grant.getOwnerRol(), 
					grant.getOwnerDispatcher()));
		}
	}

	private DominiAplicacioEntity findDominiByNomAndCodiApliacio(String nom,
            String codiAplicacio) {
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
        Collection dominis = getDominiAplicacioEntityDao().query(query,
                parameters);
        if (dominis != null) {
            return (DominiAplicacioEntity) dominis.iterator().next();
        }
        return null;
    }

    public void rolToEntity(es.caib.seycon.ng.comu.Rol sourceVO,
            es.caib.seycon.ng.model.RolEntity targetEntity, boolean copyIfNull) {
        super.rolToEntity(sourceVO, targetEntity, copyIfNull);
        rolToEntityCustom(sourceVO, targetEntity);
    }

    /**
     * @see es.caib.seycon.ng.model.GrupEntityDao#toIdentitat(es.caib.seycon.ng.model.GrupEntity,
     *      es.caib.seycon.ng.comu.Identitat)
     */
    public void toIdentitat(es.caib.seycon.ng.model.RolEntity source,
            es.caib.seycon.ng.comu.Identitat target) {
        super.toIdentitat(source, target);
        toIdentitatCustom(source, target);
    }

    public void toIdentitatCustom(es.caib.seycon.ng.model.RolEntity source,
            es.caib.seycon.ng.comu.Identitat target) {
        String nomRol = source.getNom();
        AplicacioEntity aplicacio = source.getAplicacio();
        DispatcherEntity dispatcher = source.getBaseDeDades();
        target.setNomRol(nomRol + "@" + dispatcher.getCodi() + ">" //$NON-NLS-1$ //$NON-NLS-2$
                + aplicacio.getCodi());
        target.setCodiIdentitat(nomRol + "@" + dispatcher.getCodi() + ">" //$NON-NLS-1$ //$NON-NLS-2$
                + aplicacio.getCodi());
        String descripcio = source.getDescripcio();
        target.setDescripcio(descripcio);
    }

    /**
     * @see es.caib.seycon.ng.model.RolEntityDao#toIdentitat(es.caib.seycon.ng.model.RolEntity)
     */
    public es.caib.seycon.ng.comu.Identitat toIdentitat(
            final es.caib.seycon.ng.model.RolEntity entity) {
        Identitat identitat = super.toIdentitat(entity);
        toIdentitatCustom(entity, identitat);
        return identitat;
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object from the object store. If no such entity object exists in the
     * object store, a new, blank entity is created
     */
    private es.caib.seycon.ng.model.RolEntity loadRolEntityFromIdentitat(
            es.caib.seycon.ng.comu.Identitat identitat) {
        /*
         * La identitat és read only
         */
        String nomRolComplert = identitat.getNomRol();
        if (nomRolComplert != null) {
            RolEntity rolEntity = null;
            String[] partsNomRol = nomRolComplert.split("@"); //$NON-NLS-1$
            String[] partsNomRol2 = partsNomRol[1].split(">"); //$NON-NLS-1$
            rolEntity = findByNomRolAndCodiAplicacioAndCodiDispatcher(
                    partsNomRol[0], partsNomRol2[1], partsNomRol2[0]);
            if (rolEntity != null) {
                return rolEntity;
            } else {
				throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.21"), partsNomRol[0]));  //$NON-NLS-1$
            }
        }
        throw new SeyconException(Messages.getString("RolEntityDaoImpl.22"));  //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.model.RolEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat)
     */
    public es.caib.seycon.ng.model.RolEntity identitatToEntity(
            es.caib.seycon.ng.comu.Identitat identitat) {
        es.caib.seycon.ng.model.RolEntity entity = this
                .loadRolEntityFromIdentitat(identitat);
        this.identitatToEntity(identitat, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.RolEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat,
     *      es.caib.seycon.ng.model.RolEntity)
     */
    public void identitatToEntity(es.caib.seycon.ng.comu.Identitat source,
            es.caib.seycon.ng.model.RolEntity target, boolean copyIfNull) {
        super.identitatToEntity(source, target, copyIfNull);
    }

    public List<RolEntity> find(
            final java.lang.String queryString,
            final es.caib.seycon.ng.model.Parameter[] parameters) {
        try {
            java.util.List<RolEntity> results = new QueryBuilder().query(this,
                    queryString, parameters);
            return results;
        } catch (org.hibernate.HibernateException ex) {
            throw super.convertHibernateAccessException(ex);
        }
    }

    public RolEntity contenidorRolToEntity(ContenidorRol contenidorRol) {
        // TODO Auto-generated method stub
        return null;
    }

    public ContenidorRol toContenidorRol(RolEntity entity) {
        ContenidorRol contenidorRol = super.toContenidorRol(entity); // Pasamos
                                                                     // el id
        contenidorRol.setTipus(TipusContenidorRol.ROL_ENTITY);
        // Información específica:
        contenidorRol.setInfoContenidor(entity.getNom() + "@" //$NON-NLS-1$
                + entity.getBaseDeDades().getCodi() + " (" //$NON-NLS-1$
                + entity.getAplicacio().getCodi() + ")"); //$NON-NLS-1$

        return contenidorRol;
    }

    public void contenidorRolToEntity(ContenidorRol source, RolEntity target,
            boolean copyIfNull) {
        // TODO Auto-generated method stub
        super.contenidorRolToEntity(source, target, copyIfNull);
    }

    private Collection<GrupEntity> getTotsFillsGrup(GrupEntity grupAnalitzar) {
        Collection<GrupEntity> fills = grupAnalitzar.getFills();
        Collection<GrupEntity> totsFills = new HashSet<GrupEntity>();

        for (Iterator<GrupEntity> it = fills.iterator(); it.hasNext();) {
            GrupEntity fill = it.next();
            totsFills.add(fill);
            totsFills.addAll(getTotsFillsGrup(fill));
        }
        return totsFills;
    }

    private Collection<UsuariEntity> findCodiUsuarisPertanyenGrups(Collection<GrupEntity> grupsISubgrups) {
        // Conté els codi dels usuaris a propagar:
        HashSet<UsuariEntity> usuarisPropagar = new HashSet<UsuariEntity>();

        // Cerquem els usuaris que tenen com a grup primari qualque grup del
        // llistat i els que els tenen com a grup secundari
        for (Iterator<GrupEntity> it = grupsISubgrups.iterator(); it.hasNext();) {
            GrupEntity g = it.next();
            // usuarios de grupo primario
            Collection<UsuariEntity> usuGPrim = null;
            // tipo UsuariEntity:
            usuGPrim = getUsuariEntityDao().findByGrupPrimari(g.getCodi());
            if (usuGPrim != null)
                for (Iterator<UsuariEntity> gpr_it = usuGPrim.iterator(); gpr_it.hasNext();) {
                    UsuariEntity usu = gpr_it.next();
                    usuarisPropagar.add(usu);
                }

            // Usuarios de grupo secundario (tipo UsuariGrupEntity):
            Collection<UsuariGrupEntity> usuSec = g.getUsuarisGrupSecundari(); // només de tipus
                                                             // secundari
            if (usuSec != null)
                for (Iterator<UsuariGrupEntity> gps_it = usuSec.iterator(); gps_it.hasNext();) {
                    UsuariGrupEntity usugru = gps_it.next();
                    usuarisPropagar.add(usugru.getUsuari());
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
    private void getHerenciaRol_Usuaris_Rols_Grups(RolEntity rol,
            HashSet<UsuariEntity> usuarisPropagar, HashSet<AccountEntity> accountsPropagar,
            HashSet<RolEntity> rolsPropagar,
            HashSet<GrupEntity> grupsPropagar, boolean cercaRolABaseDades) {

        // Sólo hemos de propagar a los usuarios que tienen el rol contenedor
        // con valor de dominio correspondiente (o si es SENSE_DOMINI o a
        // qualque valor)
        // Montamos un FIFO De roles (puede haber cadena de
        // herencia A atorgat B[sense domini] atorgat C ... atorgat Z[amb
        // domini]
        LinkedList<RolEntity> rolsAnalitzar = new LinkedList<RolEntity>(); // FIFO
        rolsAnalitzar.add(rol);
        RolEntity rolActual = null;

        // Añadimos el rol actual a roles a propagar (se ha deshabilitado el
        // TRIGGER SC_ROLES_UPD !!)
        rolsPropagar.add(rol);

        while ((rolActual = rolsAnalitzar.poll()) != null) {
            // Ho cerquem a la base de dades si ja existeix (update)
            Collection<RolAssociacioRolEntity> socContingut = null;
            if (cercaRolABaseDades) // ho fem a l'update (per saber l'estat
                                    // abans)
                socContingut = getRolAssociacioRolEntityDao()
                        .findRolAssociacioRolEsContingut(rolActual);
            else
                socContingut = rolActual.getRolAssociacioRolSocContingut();

            if (socContingut != null)
                for (Iterator<RolAssociacioRolEntity> it = socContingut.iterator(); it.hasNext();) {
                    RolAssociacioRolEntity associacio = (RolAssociacioRolEntity) it
                            .next();
                    if (associacio.getStatus() == null ||
                    		associacio.getStatus().equals (RoleDependencyStatus.STATUS_ACTIVE) ||
                    		associacio.getStatus().equals(RoleDependencyStatus.STATUS_TOREMOVE))
                    {
	                    // Obtenemos los usuarios del contenedor
	                    RolEntity rolContenidor = associacio.getRolContenidor();
	                    // Guardamos el rol para propagarlo
	                    rolsPropagar.add(rolContenidor);
	                    // Añadimos el rol contenedor para analizar si a su vez es
	                    // contenido en otro (atorgat)
	                    rolsAnalitzar.add(rolContenidor);
	                    Collection<RolAccountEntity> rolsUsuarisRolContenidor = new ArrayList<RolAccountEntity>();
	                    // Cerquem usuaris amb el rol d'usuari amb valor de domini
	                    // corresponent
	                    if (associacio.getGranteeGroupDomain() != null
	                            || associacio.getGranteeApplicationDomain() != null
	                            || associacio.getGranteeDomainValue() != null) {
	                        rolsUsuarisRolContenidor = getRolAccountEntityDao()
	                                .findByRolAndValorDomini(
	                                        rolContenidor.getNom(),
	                                        rolContenidor.getBaseDeDades()
	                                                .getCodi(),
	                                        rolContenidor.getAplicacio().getCodi(),
	                                        rolContenidor.getTipusDomini(),
	                                        associacio.getGranteeGroupDomain() != null ? associacio
	                                                .getGranteeGroupDomain().getCodi()
	                                                : null,
	                                        associacio.getGranteeApplicationDomain() != null ? associacio
	                                                .getGranteeApplicationDomain().getCodi()
	                                                : null,
	                                        associacio.getGranteeApplicationDomain() != null ? associacio
	                                                .getGranteeApplicationDomain()
	                                                .getId() : null);
	                    } else {// Cerquem a tots els valors de domini (sense_domini
	                            // o qualque_valor)
	                        rolsUsuarisRolContenidor = getRolAccountEntityDao()
	                                .findByRolAndTipusDomini(
	                                        rolContenidor.getNom(),
	                                        rolContenidor.getBaseDeDades()
	                                                .getCodi(),
	                                        rolContenidor.getAplicacio().getCodi(),
	                                        rolContenidor.getTipusDomini());
	                    }
	
	                    // Guardem el codi d'usuari (per propagar-los)
	                    if (rolsUsuarisRolContenidor != null)
	                        for (Iterator ruit = rolsUsuarisRolContenidor
	                                .iterator(); ruit.hasNext();) {
	                            RolAccountEntity rui = (RolAccountEntity) ruit
	                                    .next();
	                            if (rui.getAccount().getType().equals(AccountType.USER) &&
	                            		rui.getAccount().getUsers().size() == 1)
	                            	usuarisPropagar.add(rui.getAccount().getUsers().iterator().next().getUser());
	                            else
	                            	accountsPropagar.add(rui.getAccount());
	                        }
                    }
                }
        }

        // Obtenemos las relaciones con GRUPOS
        // Buscamos en la base de datos (cas d'update, abans de fer els canvis)
        Collection<RolsGrupEntity> grupsPosseidors = null;
        if (cercaRolABaseDades)
            grupsPosseidors = getRolsGrupEntityDao()
                    .findGrupsPosseidorsRol(rol);
        else
            grupsPosseidors = rol.getGrupsPosseidorsRol();

        for (Iterator<RolsGrupEntity> it = grupsPosseidors.iterator(); it.hasNext();) {
            RolsGrupEntity rolsgrup = it.next();
            GrupEntity grupPosseidor = getGrupEntityDao().findById(
                    rolsgrup.getGrupPosseidor().getId());
            // Guardamos el grupo
            grupsPropagar.add(grupPosseidor);

            // y sus subgrupos:
            Collection<GrupEntity> subgrups = getTotsFillsGrup(grupPosseidor);
            if (subgrups != null)
                grupsPropagar.addAll(subgrups);
            // Obtenemos los usuarios del grupo y sus subgrupos
            Collection<UsuariEntity> usuGrupIsubGrups = findCodiUsuarisPertanyenGrups(grupsPropagar);
            usuarisPropagar.addAll(usuGrupIsubGrups);
        }

    }

    private void propagarUsuarisRolsIGrups(Collection<UsuariEntity> usuarisPropagar,
    		Collection<AccountEntity> accountsPropagar,
            Collection<RolEntity> rolsPropagar, Collection<GrupEntity> grupsPropagar) throws InternalErrorException {

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
            for (Iterator<RolEntity> it = rolsPropagar.iterator(); it.hasNext();) {
                    RolEntity role = it.next();
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
                    TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(
                            updateRole);
                    getTasqueEntityDao().createNoFlush(tasca);
            }

        // 2) Propaguem els usuaris
        if (usuarisPropagar != null)
            for (Iterator<UsuariEntity> it = usuarisPropagar.iterator(); it.hasNext();) {
            	UsuariEntity usu = it.next();
                Tasca updateUser = new Tasca();
                // Actualització de l'usuari a tots els agents
                updateUser.setTransa("UpdateUser"); //$NON-NLS-1$
                updateUser.setDataTasca(Calendar.getInstance());
                updateUser.setUsuari(usu.getCodi());
                updateUser.setStatus("P");// Posem com a pendent //$NON-NLS-1$
                TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(
                        updateUser);
                getTasqueEntityDao().createNoFlush(tasca);
            }

        // 3) Propaguem els accounts
        if (accountsPropagar != null)
            for (Iterator<AccountEntity> it = accountsPropagar.iterator(); it.hasNext();) {
                AccountEntity acc = it.next();
                // insert into sc_tasque
                // (tas_id,tas_grup,tas_status,tas_data,tas_transa)
                // values
                // (sc_tas_seq.nextval,:new.gru_codi,'P',sysdate,'UpdateGroup');
                Tasca updateAccount = new Tasca();
                updateAccount.setTransa(TaskHandler.UPDATE_ACCOUNT);// Actualització del rol //$NON-NLS-1$
                updateAccount.setDataTasca(Calendar.getInstance());
                updateAccount.setStatus("P");// Posem com a pendent //$NON-NLS-1$
                updateAccount.setUsuari(acc.getName());
                updateAccount.setBd(acc.getDispatcher().getCodi());
                updateAccount.setCoddis(acc.getDispatcher().getCodi());
                TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(
                        updateAccount);
                getTasqueEntityDao().createNoFlush(tasca);
            }
        // 4) Propaguem els grups
        if (grupsPropagar != null)
            for (Iterator<GrupEntity> it = grupsPropagar.iterator(); it.hasNext();) {
                GrupEntity grup = it.next();
                // insert into sc_tasque
                // (tas_id,tas_grup,tas_status,tas_data,tas_transa)
                // values
                // (sc_tas_seq.nextval,:new.gru_codi,'P',sysdate,'UpdateGroup');
                Tasca updateGrup = new Tasca();
                updateGrup.setTransa("UpdateGroup");// Actualització del rol //$NON-NLS-1$
                updateGrup.setDataTasca(Calendar.getInstance());
                updateGrup.setStatus("P");// Posem com a pendent //$NON-NLS-1$
                updateGrup.setGrup(grup.getCodi());
                TasqueEntity tasca = getTasqueEntityDao().tascaToEntity(
                        updateGrup);
                getTasqueEntityDao().createNoFlush(tasca);
            }
    }

    public void create(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RolEntity) {
                    RolEntity entity = (RolEntity) obj;
                    this.create(entity); // cridem al mètode 1 per 1
                }
            }
    }

    public void update(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RolEntity) {
                    RolEntity entity = (RolEntity) obj;
                    this.update(entity);// cridem al mètode 1 per 1
                }
            }
    }

    public void remove(Collection entities) {
        if (entities != null)
            for (Iterator it = entities.iterator(); it.hasNext();) {
                Object obj = it.next();
                if (obj instanceof RolEntity) {
                    RolEntity entity = (RolEntity) obj;
                    this.remove(entity);// cridem al mètode 1 per 1
                }
            }
    }

	@Override
	protected void handleRemove(Rol role) throws Exception {
        RolEntity entity = load (role.getId());
        remove(entity);

	}

    private boolean checkNoCycles(
            RolGrant grant, StringBuffer cami) {
        RolEntity contingut = load ( grant.getIdRol());
        RolEntity pare = load (grant.getOwnerRol());

        // Método: Para todo T,D / T & D son RolEntity
        // no existe C(D,D1): D está contenido en D1 (contenedor) tal que
        // (versión breve)
        // exista un camino C(D1, T): D1 está contenido en T
        //
        // Obtenemos dónde está contenido el padre (el contenedor del rol)
        // return true;
        cami.append(contingut.getNom() + " => "); //$NON-NLS-1$
        return checkNoCycles(contingut, pare, cami);
    }

    private boolean checkNoCycles(RolEntity fill,
            RolEntity pare, StringBuffer cami) {
        Collection pareEsContingut = pare.getRolAssociacioRolSocContingut();
        boolean senseCicles = true;
        cami.append(pare.getNom() + " => "); //$NON-NLS-1$
        for (Iterator it = pareEsContingut.iterator(); senseCicles
                && it.hasNext();) {
            RolAssociacioRolEntity relacio = (RolAssociacioRolEntity) it.next();
            RolEntity parePare = relacio.getRolContenidor();
            if (parePare.equals(fill)) {
                senseCicles = false;
                cami.append(parePare.getNom());
                return false; // S'ha trobat un cicle
            } else {
                // Verificamos la descendencia del contenedor (padre)
                senseCicles = checkNoCycles(fill, parePare,
                        cami);
            }
        }
        return senseCicles;
    }


	@Override
	protected RolEntity handleCreate(Rol role, boolean updateOwnedRoles) throws Exception {
        try {
        	RolEntity entity = newRolEntity();
           	rolToEntity(role, entity, true);
            
            // 0) Obtenim els usuaris, grups i rols afectats abans del canvi
            HashSet<UsuariEntity> usuarisPropagar = new HashSet<UsuariEntity>();
            HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
            HashSet<RolEntity> rolsPropagar = new HashSet<RolEntity>();
            HashSet<GrupEntity> grupsPropagar = new HashSet<GrupEntity>();
            // Obtenim informació del rol abans de fer l'update (darrer
            // paràmetre a true)

            // Actualitzem el rol a la base de dades
            create (entity);

            if (role.getOwnedRoles() != null)
	            for (RolGrant rg: role.getOwnedRoles())
	            	rg.setOwnerRol(entity.getId());
            
            if (role.getOwnerRoles() != null)
	            for (RolGrant rg: role.getOwnerRoles())
	            	rg.setIdRol(entity.getId());
            
            updateGranteeRoles(role, entity);
            
            if (updateOwnedRoles)
            	updateGrantedRoles(role, entity);

            updateGranteeGroups(role, entity);

            getSession(false).flush();
            auditarRol("C", entity.getNom(), entity.getAplicacio().getCodi(), entity //$NON-NLS-1$
                    .getBaseDeDades().getCodi());

            // Obtenim el rol una vegada s'hagi actualitzat (conté els afectats
            // abans del canvi)
            HashSet<UsuariEntity> usuarisPropagarAfter = new HashSet<UsuariEntity>();
            HashSet<AccountEntity> accountsPropagarAfter = new HashSet<AccountEntity>();
            HashSet<RolEntity> rolsPropagarAfter = new HashSet<RolEntity>();
            HashSet<GrupEntity> grupsPropagarAfter = new HashSet<GrupEntity>();

            getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagarAfter,
                    accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

            generatePropagationTasks(usuarisPropagar, accountsPropagar,
					rolsPropagar, grupsPropagar, usuarisPropagarAfter,
					accountsPropagarAfter, rolsPropagarAfter,
					grupsPropagarAfter);

            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_ROLE);
            tasque.setRole(entity.getNom());
            tasque.setBd(entity.getBaseDeDades().getCodi());
            getTasqueEntityDao().createNoFlush(tasque);

            getSession(false).flush();

            updateMailLists (entity);

            return entity;
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.2"), role.getNom(), message));  //$NON-NLS-1$
        }
	}

	@Override
	protected RolEntity handleUpdate(Rol role, boolean updateOwnedRoles) throws Exception {
        try {
            RolEntity entity = load (role.getId());
            if (entity == null)
            	throw new UnknownRoleException("id: "+role.getId());

            String oldName = entity.getNom();
            String oldSystem = entity.getBaseDeDades().getCodi();
            boolean sameName = role.getNom().equals ( oldName ) &&
            		role.getBaseDeDades().equals (oldSystem);
            	
           	rolToEntity(role, entity, true);
            
            // 0) Obtenim els usuaris, grups i rols afectats abans del canvi
            HashSet<UsuariEntity> usuarisPropagar = new HashSet<UsuariEntity>();
            HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
            HashSet<RolEntity> rolsPropagar = new HashSet<RolEntity>();
            HashSet<GrupEntity> grupsPropagar = new HashSet<GrupEntity>();
            // Obtenim informació del rol abans de fer l'update (darrer
            // paràmetre a true)
            getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagar, accountsPropagar,
                    rolsPropagar, grupsPropagar, true);

            // Actualitzem el rol a la base de dades
            update (entity);
            
            updateGranteeRoles(role, entity);

            if (updateOwnedRoles)
            	updateGrantedRoles(role, entity);

            updateGranteeGroups(role, entity);

            getSession(false).flush();
            auditarRol("U", entity.getNom(), entity.getAplicacio().getCodi(), entity //$NON-NLS-1$
                    .getBaseDeDades().getCodi());

            // Obtenim el rol una vegada s'hagi actualitzat (conté els afectats
            // abans del canvi)
            HashSet<UsuariEntity> usuarisPropagarAfter = new HashSet<UsuariEntity>();
            HashSet<AccountEntity> accountsPropagarAfter = new HashSet<AccountEntity>();
            HashSet<RolEntity> rolsPropagarAfter = new HashSet<RolEntity>();
            HashSet<GrupEntity> grupsPropagarAfter = new HashSet<GrupEntity>();

            getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagarAfter,
                    accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

            generatePropagationTasks(usuarisPropagar, accountsPropagar,
					rolsPropagar, grupsPropagar, usuarisPropagarAfter,
					accountsPropagarAfter, rolsPropagarAfter,
					grupsPropagarAfter);

            TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
            tasque.setData(new Timestamp(System.currentTimeMillis()));
            tasque.setTransa(TaskHandler.UPDATE_ROLE);
            tasque.setRole(entity.getNom());
            tasque.setBd(entity.getBaseDeDades().getCodi());
            getTasqueEntityDao().createNoFlush(tasque);
            if (! sameName)
            {
                tasque = getTasqueEntityDao().newTasqueEntity();
                tasque.setData(new Timestamp(System.currentTimeMillis()));
                tasque.setTransa(TaskHandler.UPDATE_ROLE);
                tasque.setRole(oldName);
                tasque.setBd(oldSystem);
                getTasqueEntityDao().createNoFlush(tasque);
            }
            getSession(false).flush();

            updateMailLists (entity);
            return entity;
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RolEntityDaoImpl.2"), role.getNom(), message));  //$NON-NLS-1$
        }
	}

	@Override
	protected void handleCommitDefinition(RolEntity entity) throws Exception {
        // Calculate current users
        HashSet<UsuariEntity> usuarisPropagar = new HashSet<UsuariEntity>();
        HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
        HashSet<RolEntity> rolsPropagar = new HashSet<RolEntity>();
        HashSet<GrupEntity> grupsPropagar = new HashSet<GrupEntity>();
        getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagar, accountsPropagar,
                rolsPropagar, grupsPropagar, true);

        // Commit changes
        for ( RolAssociacioRolEntity grant: entity.getRolAssociacioRolSocContenidor())
    	{
    		if ( RoleDependencyStatus.STATUS_TOAPPROVE.equals(grant.getStatus() ))
    		{
    			grant.setStatus(RoleDependencyStatus.STATUS_ACTIVE);
    			getRolAssociacioRolEntityDao().update(grant);
    		}
    		if ( RoleDependencyStatus.STATUS_TOREMOVE.equals(grant.getStatus() ))
    		{
    			getRolAssociacioRolEntityDao().remove(grant);
    		}
    	}

        // Calculate new users
        HashSet<UsuariEntity> usuarisPropagarAfter = new HashSet<UsuariEntity>();
        HashSet<AccountEntity> accountsPropagarAfter = new HashSet<AccountEntity>();
        HashSet<RolEntity> rolsPropagarAfter = new HashSet<RolEntity>();
        HashSet<GrupEntity> grupsPropagarAfter = new HashSet<GrupEntity>();

        // Generate propagation tasks
        getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagarAfter,
                accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

        generatePropagationTasks(usuarisPropagar, accountsPropagar,
				rolsPropagar, grupsPropagar, usuarisPropagarAfter,
				accountsPropagarAfter, rolsPropagarAfter,
				grupsPropagarAfter);

        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_ROLE);
        tasque.setRole(entity.getNom());
        tasque.setBd(entity.getBaseDeDades().getCodi());
        getTasqueEntityDao().createNoFlush(tasque);

        entity.setApprovalEnd(new java.util.Date());
        entity.setApprovalProcess(null);
	}

	@Override
	protected void handleRollbackDefinition(RolEntity entity) throws Exception {
        // Calculate current users
        HashSet<UsuariEntity> usuarisPropagar = new HashSet<UsuariEntity>();
        HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
        HashSet<RolEntity> rolsPropagar = new HashSet<RolEntity>();
        HashSet<GrupEntity> grupsPropagar = new HashSet<GrupEntity>();
        getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagar, accountsPropagar,
                rolsPropagar, grupsPropagar, true);

        // Commit changes
        for ( RolAssociacioRolEntity grant: entity.getRolAssociacioRolSocContenidor())
    	{
    		if ( RoleDependencyStatus.STATUS_TOREMOVE.equals(grant.getStatus() ))
    		{
    			grant.setStatus(RoleDependencyStatus.STATUS_ACTIVE);
    			getRolAssociacioRolEntityDao().update(grant);
    		}
    		if ( RoleDependencyStatus.STATUS_TOAPPROVE.equals(grant.getStatus() ))
    		{
    			getRolAssociacioRolEntityDao().remove(grant);
    		}
    	}

        // Calculate new users
        HashSet<UsuariEntity> usuarisPropagarAfter = new HashSet<UsuariEntity>();
        HashSet<AccountEntity> accountsPropagarAfter = new HashSet<AccountEntity>();
        HashSet<RolEntity> rolsPropagarAfter = new HashSet<RolEntity>();
        HashSet<GrupEntity> grupsPropagarAfter = new HashSet<GrupEntity>();

        // Generate propagation tasks
        getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagarAfter,
                accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

        generatePropagationTasks(usuarisPropagar, accountsPropagar,
				rolsPropagar, grupsPropagar, usuarisPropagarAfter,
				accountsPropagarAfter, rolsPropagarAfter,
				grupsPropagarAfter);

        TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
        tasque.setData(new Timestamp(System.currentTimeMillis()));
        tasque.setTransa(TaskHandler.UPDATE_ROLE);
        tasque.setRole(entity.getNom());
        tasque.setBd(entity.getBaseDeDades().getCodi());
        getTasqueEntityDao().createNoFlush(tasque);

        entity.setApprovalEnd(new java.util.Date());
        entity.setApprovalProcess(null);
	}

}
