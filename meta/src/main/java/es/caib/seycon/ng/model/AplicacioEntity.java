//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

import es.caib.bpm.servei.BpmEngine;

@Entity (table="SC_APLICA", translatedName="InformationSystemEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.ValorDomini.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.Aplicacio.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.DominiAplicacioEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.model.NotificacioEntity.class,
	es.caib.seycon.ng.model.SoDRuleEntity.class,
	BpmEngine.class })
public abstract class AplicacioEntity {

	@Column (name="APL_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="APL_CODI", length=20, translated="name")
	public java.lang.String codi;

	@Column (name="APL_NOM", length=50, translated="description")
	public java.lang.String nom;

	@Column (name="APL_DIRFON", length=50, translated="sourceDir")
	@Nullable
	public java.lang.String directoriFonts;

	@Column (name="APL_DIRECT", length=50, translated="targetDir")
	@Nullable
	public java.lang.String directoriExecutable;

	@Column (name="APL_BD", length=25, translated="dataBase")
	@Nullable
	public java.lang.String bd;

	@ForeignKey (foreignColumn="ROL_IDAPL", translated="roles")
	public java.util.Collection<es.caib.seycon.ng.model.RolEntity> rols;

	@Column (name="APL_IDCONTACT", translated="contactPerson")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity personaContacte;

	@Column (name="APL_GESTIONABLEWF", length=1, translated="wfManagement")
	@Nullable
	public java.lang.String gestionableWF;

	@Column (name="APL_MAILNOTIF", length=512, translated="notificationMail")
	@Nullable
	public java.lang.String correusNotificacions;
	
	@Description ("Approval process needed for workflow managed roles belonging to this application. Null value means no approval process is needed")
	@Column (name="APL_APRPRO", length=256)
	@Nullable
	public String approvalProcess;
	
	@Column(name="APL_TEN_ID")
	public TenantEntity tenant;

	@Description ("Approval process needed for any change applied to this application roles. Null value means no approval process is needed")
	@Column (name="APL_RODEPR", length=256)
	@Nullable
	public String roleDefinitionProcess;


	@Column (name="APL_SGNROL")
	@Nullable
	public Boolean singleRole;
	
	/************************** DAOS *********************************************************/
	
	@ForeignKey (foreignColumn="SOD_APL_ID")
	public java.util.Collection<es.caib.seycon.ng.model.SoDRuleEntity> sodRules;

	@Operation(translated="findByCode")
	@DaoFinder
	public es.caib.seycon.ng.model.AplicacioEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
	@Operation(translated="findByFilter")
	@DaoFinder("select aplicacioEntity \n"
			+ "from \n"
			+ "com.soffid.iam.model.InformationSystemEntity aplicacioEntity \n"
			+ "left join aplicacioEntity.contactPerson contactPerson \n"
			+ "where (:name is null or aplicacioEntity.name like :name) and \n"
			+ "(:description is null or aplicacioEntity.description like :description) and \n"
			+ "(:sourceDir is null or aplicacioEntity.sourceDir like :sourceDir) and\n"
			+ "(:contactPerson is null or contactPerson.userName like :contactPerson) and\n"
			+ "(:targetDir is null or aplicacioEntity.targetDir like :targetDir) and\n"
			+ "(:dataBase is null or aplicacioEntity.dataBase like :dataBase)  and "
			+ "(:wfManagement is null or aplicacioEntity.wfManagement like :wfManagement) and\n"
			+ "(aplicacioEntity.tenant.id = :tenantId) \n"
			+ "order by aplicacioEntity.name")
	public java.util.List<es.caib.seycon.ng.model.AplicacioEntity> findAplicacioByCriteri(
		java.lang.String name, 
		java.lang.String description, 
		java.lang.String sourceDir, 
		java.lang.String contactPerson, 
		java.lang.String targetDir, 
		java.lang.String dataBase, 
		java.lang.String wfManagement) {
	 return null;
	}
	@DaoFinder("select distinct aplicacio\n"
			+ "from com.soffid.iam.model.UserEntity as usuari\n"
			+ "join usuari.accounts as accounts\n"
			+ "join accounts.account as account\n"
			+ "join account.roles as roles\n"
			+ "join roles.role as rol\n"
			+ "join rol.informationSystem as aplicacio with aplicacio.wfManagement='S'\n"
			+ "where usuari.userName = :userName \n"
			+ "and aplicacio.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.AplicacioEntity> findManageableByUser(
		java.lang.String userName) {
	 return null;
	}
	@DaoFinder("select distinct aplicacio\n"
			+ "from com.soffid.iam.model.UserEntity as usuari\n"
			+ "join usuari.accounts as accounts\n"
			+ "join accounts.account as account\n"
			+ "join account.roles as roles\n"
			+ "join roles.role as rol\n"
			+ "join rol.informationSystem as aplicacio \n"
			+ "where usuari.userName = :userName \n"
			+ "and aplicacio.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.AplicacioEntity> findByUser(
		java.lang.String userName) {
	 return null;
	}


	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }

	@DaoFinder("from com.soffid.iam.model.AccountEntity  where :text is null")
	public Collection<AplicacioEntity>findByText (String text) { return null; }
}

@Index (name="APL_UK_CODI",	unique=true,
entity=es.caib.seycon.ng.model.AplicacioEntity.class,
columns={"APL_TEN_ID", "APL_CODI"})
abstract class AplicacioIndex {
}
