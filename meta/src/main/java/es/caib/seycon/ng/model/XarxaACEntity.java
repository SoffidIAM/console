//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_AUTXAR", translatedName = "NetworkAuthorizationEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.model.XarxaEntity.class,
		es.caib.seycon.ng.comu.NetworkAuthorization.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.RolEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class })
public abstract class XarxaACEntity {

	@Column(name = "AXA_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "AXA_NIVELL", translated = "level")
	public java.lang.Integer nivell;

	@Column(name = "AXA_MASMAQ", length = 50, translated = "hostsName")
	@Nullable
	public java.lang.String nomMaquines;

	@Column(name = "AXA_IDXAR", translated = "network")
	public es.caib.seycon.ng.model.XarxaEntity xarxa;

	@Column(name = "AXA_IDROL")
	@Nullable
	public es.caib.seycon.ng.model.RolEntity role;

	@Column(name = "AXA_IDGRU", translated = "group")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column(name = "AXA_IDUSU", translated = "user")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Operation(translated = "findByNetwork")
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByXarxa(
			es.caib.seycon.ng.model.XarxaEntity network) {
		return null;
	}

	@Operation(translated = "findByNetworkAndIdentity")
	@DaoFinder("select xarxaAC \n"
			+ "from com.soffid.iam.model.NetworkAuthorizationEntity xarxaAC \n"
			+ "left join xarxaAC.user user \n"
			+ "left join xarxaAC.role rol \n"
			+ "left join xarxaAC.group grup \n" + "where \n" + "(\n"
			+ "  (grup is not null and grup.name = :identity) or \n"
			+ "  (user is not null and user.userName = :identity) or \n"
			+ "  (rol is not null and rol.name = :identity) \n" + ") \n"
			+ "and xarxaAC.network.name = :networkName "
			+ "and xarxaAC.network.tenant.id = :tenantId ")
	public es.caib.seycon.ng.model.XarxaACEntity findByCodiXarxaAndCodiIdentiat(
			java.lang.String networkName, java.lang.String identity) {
		return null;
	}

	@Operation(translated = "findByRoleName")
	@DaoFinder("select xarxaAC\n"
			+ "from com.soffid.iam.model.NetworkAuthorizationEntity xarxaAC\n"
			+ "where xarxaAC.role.name = :roleName and "
			+ "xarxaAC.role.system.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByNomRol(
			java.lang.String roleName) {
		return null;
	}

	@Operation(translated = "findByUserName")
	@DaoFinder("select xarxaAC\n"
			+ "from com.soffid.iam.model.NetworkAuthorizationEntity xarxaAC\n"
			+ "where xarxaAC.user.userName = :userName and "
			+ "xarxaAC.user.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByCodiUsuari(
			java.lang.String userName) {
		return null;
	}

	@Operation(translated = "findByGroupName")
	@DaoFinder("select xarxaAC\n"
			+ "from com.soffid.iam.model.NetworkAuthorizationEntity xarxaAC\n"
			+ "where xarxaAC.group.name = :groupName and "
			+ "xarxaAC.group.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByCodiGrup(
			java.lang.String groupName) {
		return null;
	}

	@Operation(translated = "findByRole")
	@DaoFinder("select xarxaAC\n"
			+ "from com.soffid.iam.model.NetworkAuthorizationEntity xarxaAC\n"
			+ "left join xarxaAC.role as elrol\n"
			+ "left join elrol.informationSystem as aplica\n"
			+ "left join elrol.system as agent\n"
			+ "where elrol.name = :roleName \n"
			+ "and aplica.name = :informationSystem\n"
			+ "and agent.name = :system "
			+ "and agent.tenant.id = :tenantId \n" 
			+ "order by xarxaAC.network.name")
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByNomRolAndCodiAplicacioRolAndCodiDispatcher(
			java.lang.String roleName, java.lang.String informationSystem,
			java.lang.String system) {
		return null;
	}
}
