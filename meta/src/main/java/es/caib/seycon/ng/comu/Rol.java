//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject ( translatedName="Role",
	 translatedPackage="com.soffid.iam.api")
public abstract class Rol {

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
	@Attribute(translated = "system" )
	public java.lang.String baseDeDades;

	@Nullable
	@Attribute(translated = "password" )
	public java.lang.Boolean contrasenya;

	@Attribute(translated = "applicationCode" )
	public java.lang.String codiAplicacio;

	@Nullable
	public java.lang.Long id;

	@Nullable
	@Attribute(translated = "domain" )
	public es.caib.seycon.ng.comu.Domini domini;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> ownerRoles;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> ownerGroups;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> granteeGroups;

	@Nullable
	@Attribute(translated = "indirectAssignment" )
	public java.lang.String assignacioIndirecta;

	@Nullable
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> ownedRoles;

	@Nullable
	@Attribute(translated = "bpmEnforced" )
	public java.lang.Boolean gestionableWF;

}
