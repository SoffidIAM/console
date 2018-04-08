//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_MAQUIN", translatedName="HostEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.Maquina.class,
	es.caib.seycon.ng.model.XarxaEntity.class,
	es.caib.seycon.ng.model.AliasMaquinaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.ImpressoraEntity.class,
	es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity.class,
	es.caib.seycon.ng.model.OsTypeEntity.class})
public abstract class MaquinaEntity {

	@Column (name="MAQ_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="MAQ_NOM", length=50, translated="name")
	public java.lang.String nom;

	@Column (name="MAQ_SISOPE", length=3, translated="oldOS")
	@Nullable
	public java.lang.String oldSistemaOperatiu;

	@Column (name="MAQ_ADRIP", length=50, translated="hostIP")
	@Nullable
	public java.lang.String adreca;

	@Column (name="MAQ_DESCRI", length=50, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@Column (name="MAQ_PARDHC", length=50)
	@Nullable
	public java.lang.String dhcp;

	@Column (name="MAQ_CORREU", length=1, translated="mail")
	public java.lang.String correu;

	@Column (name="MAQ_OFIMAT", length=1, translated="folders")
	public java.lang.String ofimatica;

	@ForeignKey (foreignColumn="USU_IDMAPR", translated="userProfiles")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariEntity> usuarisPerfil;

	@ForeignKey (foreignColumn="IMP_IDMAQ", translated="printers")
	public java.util.Collection<es.caib.seycon.ng.model.ImpressoraEntity> impressores;

	@Column (name="MAQ_IDXAR", translated="network")
	@Nullable
	public es.caib.seycon.ng.model.XarxaEntity xarxa;

	@Column (name="MAQ_IMPRES", length=1, translated="printersServer")
	public java.lang.String servidorImpressores;

	@Column (name="MAQ_ADRMAC", length=25)
	@Nullable
	public java.lang.String mac;

	@ForeignKey (foreignColumn="MAL_MAQID", translated="hostAlias")
	public java.util.Collection<es.caib.seycon.ng.model.AliasMaquinaEntity> aliasMaquina;

	@ForeignKey (foreignColumn="ADM_MAQID", translated="administratorAuthorizationAccess")
	public java.util.Collection<es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity> autoritzacioAccesComAdministrador;

	@Column (name="MAQ_USUADM", length=50, translated="administratorUser")
	@Nullable
	public java.lang.String usuariAdministrador;

	@Column (name="MAQ_CONTRA", length=50, translated="administratorPassword")
	@Nullable
	public java.lang.String contrasenyaAdministrador;

	@Column (name="MAQ_DATCON", translated="administratorPasswordDate")
	@Nullable
	public java.util.Date dataContrasenyaAdministrador;

	@Column (name="MAQ_SERIAL", length=255)
	@Nullable
	public java.lang.String serialNumber;

	@Column (name="MAQ_DYNIP")
	@Nullable
	public java.lang.Boolean dynamicIP;

	@Column (name="MAQ_LASTSEEN")
	@Nullable
	public java.util.Date lastSeen;

	@Column (name="MAQ_DELETED")
	@Nullable
	public java.lang.Boolean deleted;

	@Column (name="MAQ_OST_ID")
	@Nullable
	public es.caib.seycon.ng.model.OsTypeEntity operatingSystem;

	@Column (name="MAQ_TEN_ID")
	public TenantEntity tenant;
	
	@Operation(translated="findByName")
	@DaoFinder("select maq "
			+ "from com.soffid.iam.model.HostEntity maq "
			+ "where maq.name=:name and maq.deleted = false and maq.tenant.id=:tenantId")
	public es.caib.seycon.ng.model.MaquinaEntity findByNom(
		@Nullable java.lang.String name) {
	 return null;
	}

	@Operation(translated="findHostByCriteria")
	@DaoFinder("select maquina from com.soffid.iam.model.HostEntity maquina, "
			+ "com.soffid.iam.model.SessionEntity sessio "
			+ "join maquina.operatingSystem as os "
			+ "where maquina.id=sessio.host.id and "
			+ " (:name is null or maquina.name like :name) and "
			+ "(:operatingSystem is null or os.name like :operatingSystem) and "
			+ "(:address is null or maquina.hostIP like :address) and "
			+ "(:dhcp is null or maquina.dhcp like :dhcp) and "
			+ "(:mailService is null or maquina.mail like :mailService) and "
			+ "(:folders is null or maquina.folders like :folders) and \n"
			+ "(:mac is null or maquina.mac like :mac) and \n"
			+ "(:description is null or maquina.description like :description) and  \n"
			+ "(:network is null or maquina.network.name like :network) and \n"
			+ "(:codiUsuari is null or sessio.user.userName like :codiUsuari) and \n"
			+ "maquina.deleted=false and \n"
			+ "maquina.tenant.id=:tenantId "
			+ "order by maquina.name \n")
	public java.util.List<es.caib.seycon.ng.model.MaquinaEntity> findMaquinaByFiltre(
		java.lang.String name, 
		java.lang.String operatingSystem, 
		java.lang.String address, 
		java.lang.String dhcp, 
		java.lang.String mailService, 
		java.lang.String folders, 
		java.lang.String mac, 
		java.lang.String description, 
		java.lang.String network, 
		java.lang.String codiUsuari) {
	 return null;
	}
	@Operation(translated="getTasks")
	@DaoFinder("- CUSTOM -")
	public java.lang.String[] getTasques(
		java.lang.String hostName) {
	 return null;
	}
	@Operation(translated="findByIP")
	@DaoFinder
	public Collection<es.caib.seycon.ng.model.MaquinaEntity> findByAdreca(
		@Nullable java.lang.String hostIP) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.MaquinaEntity findBySerialNumber(
		java.lang.String serialNumber) {
	 return null;
	}
}

@Index (name="MAQ_UK_IP",	unique=false,
entity=es.caib.seycon.ng.model.MaquinaEntity.class,
columns={"MAQ_TEN_ID", "MAQ_ADRIP"})
abstract class MaquinaIndex {
}

@Index (name="MAQ_UK_SERIAL",	unique=true,
entity=es.caib.seycon.ng.model.MaquinaEntity.class,
columns={"MAQ_TEN_ID", "MAQ_SERIAL"})
abstract class MaquinaSerialIndex {
}

@Index (name="MAQ_NDX_NAME",	unique=true,
entity=es.caib.seycon.ng.model.MaquinaEntity.class,
columns={"MAQ_TEN_ID", "MAQ_NOM"})
abstract class MaquinaNameIndex {
}

