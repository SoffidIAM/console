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
	 translatedPackage="com.soffid.iam.api")
@JsonObject(hibernateClass=RolEntity.class)
public class Rol {

	@Attribute(translated = "name" )
	public java.lang.String nom;

	@Attribute(translated = "description" )
	public java.lang.String descripcio;

	@Nullable
	public String category;
	
	@Nullable
	@Attribute(translated = "enableByDefault" )
	public java.lang.Boolean defecte;

	@Nullable
	@JsonAttribute(hibernateAttribute="name", hibernateJoin="system")
	@Attribute(translated = "system" )
	public java.lang.String baseDeDades;

	@Nullable
	@Attribute(translated = "password" )
	public java.lang.Boolean contrasenya;

	@JsonAttribute(hibernateAttribute="name", hibernateJoin="informationSystem")
	@Attribute(translated = "informationSystemName" )
	public java.lang.String codiAplicacio;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "domain" )
	public es.caib.seycon.ng.comu.Domini domini;

	@Nullable
	@JsonAttribute(hibernateJoin="containerRoles", hibernateAttribute="container")
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> ownerRoles;

	@Nullable
	@JsonAttribute(hibernateJoin="containerRoles", hibernateAttribute="container")
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> ownerGroups;

	@Nullable
	@JsonAttribute(hibernateJoin="containerGroups", hibernateAttribute="group")
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> granteeGroups;

	@Nullable
	@Attribute(translated = "indirectAssignment" )
	public java.lang.String assignacioIndirecta;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> ownedRoles;

	@Nullable
	@Attribute(translated = "bpmEnforced" )
	public java.lang.Boolean gestionableWF;

	@Description("Last modification date")
	@Nullable
	public Date approvalStart;

	@Description("Approval date")
	@Nullable
	public Date approvalEnd;

	@Description ("Role custom attributes")
	@JsonAttribute(hibernateJoin="attributes")
	@Nullable
	public Map<String,Object> attributes; 
}
