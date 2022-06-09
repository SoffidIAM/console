//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Date;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolAccountEntity;

@ValueObject ( translatedName="RoleAccount",
	 translatedPackage="com.soffid.iam.api")
@JsonObject(hibernateClass = RolAccountEntity.class)
public class RolAccount {

	@Nullable
	public java.lang.Long accountId;

	@Nullable
	@JsonAttribute(hibernateAttribute = "account.name")
	public java.lang.String accountName;

	@Nullable
	@Attribute(translated="accountSystem")
	@JsonAttribute(hibernateAttribute = "account.system.name")
	public java.lang.String accountDispatcher;

	@Nullable
	@JsonAttribute(hibernateAttribute = "role.name")
	@Attribute(translated = "roleName" )
	public java.lang.String nomRol;

	@Nullable
	@JsonAttribute(hibernateAttribute = "role.name")
	public java.lang.String roleCategory;

	@Nullable
	@JsonAttribute(hibernateAttribute = "role.informationSystem.name")
	@Attribute(translated = "informationSystemName" )
	public java.lang.String codiAplicacio;

	@Nullable
	@JsonAttribute(hibernateAttribute = "role.description")
	@Attribute(translated = "roleDescription" )
	public java.lang.String descripcioRol;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "userFullName" )
	public java.lang.String nomComplertUsuari;

	@Nullable
	@JsonAttribute(hibernateAttribute = "account.users.user.primaryGroup.description")
	@Attribute(translated = "groupDescription" )
	public java.lang.String descripcioGrup;

	@Nullable
	@Attribute(translated = "domainValue" )
	public es.caib.seycon.ng.comu.ValorDomini valorDomini;

	@Nullable
	@JsonAttribute(hibernateAttribute = "role.system.name")
	@Attribute(translated = "system" )
	public java.lang.String baseDeDades;

	@Nullable
	@JsonAttribute(hibernateAttribute = "account.users.user.primaryGroup.name")
	@Attribute(translated = "userGroupCode" )
	public java.lang.String codiGrupUsuari;

	@Nullable
	@Attribute(translated = "bpmEnforced" )
	public java.lang.String gestionableWF;

	@Nullable
	@JsonAttribute(hibernateAttribute = "account.users.user.userName")
	@Attribute(translated = "userCode" )
	public java.lang.String codiUsuari;

	@Description("Rule that has cretaed the role assignment")
	@Nullable
	@JsonAttribute(hibernateAttribute = "rule.id")
	public java.lang.Long ruleId;

	@Nullable
	@JsonAttribute(hibernateAttribute = "rule.description")
	public java.lang.String ruleDescription;

	@Nullable
	public es.caib.seycon.ng.comu.SoDRisk sodRisk;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.SoDRule> sodRules;

	@Description ("Rol assignment start date. Null means since now")
	@Nullable
	public Date startDate;
	
	@Description ("Rol assignment end date. Null means forever")
	@Nullable
	public Date endDate;

	@Description ("True if the role is enabled or not. When the start/end date reaches, the flag should change")
	@Attribute(defaultValue="true")
	public boolean enabled;
	
	@Attribute(defaultValue="true")
	public boolean approvalPending;
	
	@Attribute(defaultValue = "false")
	@Nullable
	public Boolean removalPending;
	
	@Description("This attribute holds the group name that is bound to this the role assignment. Not applicable for shared accounts")
	@Nullable
	@JsonAttribute(hibernateAttribute = "holderGroup.name")
	public String holderGroup;

	@Description("When an aproval process is needed to enable this rol assignment")
	@Nullable
	public Long approvalProcess;

	@Description("Last certification date")
	@Nullable
	public Date certificationDate;
	
	@Description("Parent grant id, for optinal role to role grant")
	@Nullable
	public Long parentGrant;

	@Description("Delegation status")
	@Nullable
	public com.soffid.iam.api.DelegationStatus delegationStatus;

	@Description("Entitled account who delegatse to delegateAccount")
	@Nullable
	public String ownerAccount;

	@Description("Delegate account")
	@Nullable
	public String delegateAccount;

	@Description("Delegate since date")
	@Nullable
	public Date delegateSince;

	@Description("Delegate until date")
	@Nullable
	public Date delegateUntil;

}
