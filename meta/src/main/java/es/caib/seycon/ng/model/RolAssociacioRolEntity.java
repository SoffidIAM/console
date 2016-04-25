//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_ROLROL", translatedName = "RoleDependencyEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.comu.RolGrant.class,
		es.caib.seycon.ng.comu.ContenidorRol.class,
		es.caib.seycon.ng.model.AplicacioEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.model.RolEntity.class,
		es.caib.seycon.ng.model.TasqueEntity.class })
public abstract class RolAssociacioRolEntity {

	@Description("The role to be granted when the container has been granted")
	@Column(name = "RRL_CONTINGUT", translated = "contained")
	public es.caib.seycon.ng.model.RolEntity rolContingut;

	@Description("The role that grants another role")
	@Column(name = "RRL_CONTENIDOR", translated = "container")
	public es.caib.seycon.ng.model.RolEntity rolContenidor;

	@Column(name = "RRL_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "RRL_APLICA2")
	@Nullable
	public es.caib.seycon.ng.model.AplicacioEntity granteeApplicationDomain;

	@Column(name = "RRL_GRUP2")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity granteeGroupDomain;

	@Column(name = "RRL_VALDOM2")
	@Nullable
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity granteeDomainValue;

	@Column(name = "RRL_APLICA", translated = "domainApplication")
	@Nullable
	public es.caib.seycon.ng.model.AplicacioEntity grantedApplicationDomain;

	@Column(name = "RRL_GRUP", translated = "domainGroup")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grantedGroupDomain;

	@Column(name = "RRL_VALDOM", translated = "domainApplicationValue")
	@Nullable
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity grantedDomainValue;

	@Operation(translated = "findContainerByRoleNameAndApplicationCodeAndDBCode")
	@DaoFinder("select rolAssociacioRol.container\n"
			+ "from com.soffid.iam.model.RoleDependencyEntity rolAssociacioRol\n"
			+ "where \n"
			+ " rolAssociacioRol.contained.name = :roleName and\n"
			+ " rolAssociacioRol.contained.informationSystem.name = :informationSystem and\n"
			+ " rolAssociacioRol.contained.system.name = :systemName and "
			+ "rolAssociacioRol.contained.system.tenant.id = :tenantId\n")
	public java.util.List<es.caib.seycon.ng.model.RolAssociacioRolEntity> findContenidorsByNomRolCodiAplicacioCodiBBDD(
			java.lang.String roleName, java.lang.String informationSystem,
			java.lang.String systemName) {
		return null;
	}

	@Operation(translated = "findRolesAssociationRole")
	@DaoFinder("select rolAssociacioRol\n"
			+ "from com.soffid.iam.model.RoleDependencyEntity rolAssociacioRol\n"
			+ "where \n" + "   rolAssociacioRol.contained = :containedRole and "
			+ "   rolAssociacioRol.container = :containerRole\n")
	public java.util.List<es.caib.seycon.ng.model.RolAssociacioRolEntity> findRolAssociacioRol(
			es.caib.seycon.ng.model.RolEntity containedRole,
			es.caib.seycon.ng.model.RolEntity containerRole) {
		return null;
	}

	@Operation(translated = "findRolesAssociationContainerRole")
	@DaoFinder("select rolAssociacioRol\n"
			+ "from com.soffid.iam.model.RoleDependencyEntity rolAssociacioRol\n"
			+ "where rolAssociacioRol.contained = :containedRole\n")
	public java.util.List<es.caib.seycon.ng.model.RolAssociacioRolEntity> findRolAssociacioRolEsContingut(
			es.caib.seycon.ng.model.RolEntity containedRole) {
		return null;
	}

	public java.lang.String toString() {
		return null;
	}

	public boolean isAllowed(String permission) {
		return false;
	}
}
