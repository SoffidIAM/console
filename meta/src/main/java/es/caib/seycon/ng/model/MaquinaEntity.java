//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
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

	@Column (name="MAQ_ADRIP", length=25, translated="hostIP")
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

	@Column (name="MAQ_OFIMAT", length=1, translated="office")
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

	@Operation(translated="findByName")
	@DaoFinder("select maq\nfrom es.caib.seycon.ng.model.MaquinaEntity maq\nwhere maq.nom=:nom and maq.deleted = false")
	public es.caib.seycon.ng.model.MaquinaEntity findByNom(
		@Nullable java.lang.String nom) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.MaquinaEntity findById(
		java.lang.Long id) {
	 return null;
	}
	@Operation(translated="findHostByCriteria")
	@DaoFinder("select maquina from es.caib.seycon.ng.model.MaquinaEntity maquina, es.caib.seycon.ng.model.SessioEntity sessio \njoin maquina.operatingSystem as os\nwhere maquina.id=sessio.maquina.id and (:nom is null or maquina.nom like :nom) and (:oldSistemaOperatiu is null or os.name like :oldSistemaOperatiu) and (:adreca is null or maquina.adreca like :adreca) and (:dhcp is null or maquina.dhcp like :dhcp) and (:correu is null or maquina.correu like :correu) and (:ofimatica is null or maquina.ofimatica like :ofimatica) and (:mac is null or maquina.mac like :mac) and (:descripcio is null or maquina.descripcio like :descripcio) and (:xarxa is null or maquina.xarxa.codi like :xarxa) and (:codiUsuari is null or sessio.usuari.codi like :codiUsuari) and maquina.deleted=false\norder by maquina.nom \n")
	public java.util.List<es.caib.seycon.ng.model.MaquinaEntity> findMaquinaByFiltre(
		java.lang.String nom, 
		java.lang.String oldSistemaOperatiu, 
		java.lang.String adreca, 
		java.lang.String dhcp, 
		java.lang.String correu, 
		java.lang.String ofimatica, 
		java.lang.String mac, 
		java.lang.String descripcio, 
		java.lang.String xarxa, 
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.MaquinaEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@Operation(translated="getTasks")
	@DaoFinder
	public java.lang.String[] getTasques(
		java.lang.String nomMaquina) {
	 return null;
	}
	@Operation(translated="findByIP")
	@DaoFinder
	public es.caib.seycon.ng.model.MaquinaEntity findByAdreca(
		@Nullable java.lang.String adreca) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.MaquinaEntity findBySerialNumber(
		java.lang.String serialNumber) {
	 return null;
	}
}
