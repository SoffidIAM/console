//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import java.util.Date;
import java.util.Map;

import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.RolEntity;

@ValueObject ( translatedName="Role",
	 cache=300,
	 translatedPackage="com.soffid.iam.api")
@JsonObject(hibernateClass=RolEntity.class)
public class Rol {

	@Attribute(translated = "name", searchCriteria = true )
	public java.lang.String nom;

	@Attribute(translated = "description", searchCriteria = true )
	public java.lang.String descripcio;

	@Nullable
	public String category;
	
	@Nullable
	@Attribute(translated = "enableByDefault", hidden=true )
	public java.lang.Boolean defecte;

	@Nullable
	@JsonAttribute(hibernateAttribute="system.name")
	@Attribute(translated = "system", customUiHandler = "com.soffid.iam.web.account.SystemFieldHandler" )
	public java.lang.String baseDeDades;

	@Nullable
	@Attribute(translated = "password", hidden=true )
	public java.lang.Boolean contrasenya;

	@JsonAttribute(hibernateAttribute="informationSystem.name")
	@Attribute(translated = "informationSystemName", searchCriteria = true, type = "APPLICATION" )
	public java.lang.String codiAplicacio;

	@Nullable
	@Attribute(hidden=true)
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "domain",
		customUiHandler = "com.soffid.iam.web.application.RoleDomainFieldHandler")
	public String domini;

	@Nullable
	@JsonAttribute(hibernateAttribute="containerRoles.container")
	@Attribute(hidden = true)
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> ownerRoles;

	@Nullable
	@Attribute(hidden = true)
	@JsonAttribute(hibernateJoin="", hibernateAttribute="containerGroups.group.name")
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> ownerGroups;

	@Nullable
	@Attribute(hidden = true)
	@JsonAttribute(hibernateJoin="", hibernateAttribute="containerGroups.group.name")
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> granteeGroups;

	@Nullable
	@Attribute(hidden = true)
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> ownedRoles;

	@Nullable
	@Attribute(translated = "bpmEnabled", synonyms = {"bpmEnforced"} )
	public java.lang.Boolean gestionableWF;

	@Description("Last modification date")
	@Attribute(readonly = true)
	@Nullable
	public Date approvalStart;

	@Description("Approval date")
	@Nullable
	@Attribute(readonly = true)
	public Date approvalEnd;

	@Description ("Role custom attributes")
	@JsonAttribute(hibernateJoin="attributes")
	@Attribute(defaultValue="new java.util.HashMap<String,Object>()", hidden = true)
	@Nullable
	public Map<String,Object> attributes; 
}
