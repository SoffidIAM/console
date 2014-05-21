//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USUARI" )
@Depends ({es.caib.seycon.ng.model.ScTarget.class,
	es.caib.seycon.ng.model.RolsGrupEntity.class,
	es.caib.seycon.ng.model.UsuariSEUEntity.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.model.ContrasenyaEntity.class,
	es.caib.seycon.ng.model.DominiUsuariEntity.class,
	es.caib.seycon.ng.comu.Usuari.class,
	es.caib.seycon.ng.model.DadaUsuariEntity.class,
	es.caib.seycon.ng.model.TipusDadaEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.comu.Identitat.class,
	es.caib.seycon.ng.model.UsuariGrupEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.DominiCorreuEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuUsuariEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.bpm.vo.BPMUser.class,
	es.caib.seycon.ng.comu.UsuariAnonim.class,
	es.caib.seycon.ng.model.XarxaACEntity.class,
	es.caib.seycon.ng.model.SsoEntity.class,
	es.caib.seycon.ng.model.UsuariImpressoraEntity.class,
	es.caib.seycon.ng.model.RenovacioEntity.class,
	es.caib.seycon.ng.model.SessioEntity.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity.class,
	es.caib.seycon.ng.model.NotificacioEntity.class,
	es.caib.seycon.ng.model.SecretEntity.class,
	es.caib.seycon.ng.model.UserAccountEntity.class,
	es.caib.seycon.ng.model.AccountAccessEntity.class})
public abstract class UsuariEntity {

	@Column (name="USU_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="USU_CODI", length=150)
	public java.lang.String codi;

	@Column (name="USU_NOM", length=50)
	public java.lang.String nom;

	@Column (name="USU_PRILLI", length=50)
	public java.lang.String primerLlinatge;

	@Column (name="USU_NOMCUR", length=25)
	@Nullable
	public java.lang.String nomCurt;

	@Column (name="USU_USUMOD", length=50)
	@Nullable
	public java.lang.String usuariDarreraModificacio;

	@Column (name="USU_DATMOD")
	@Nullable
	public java.util.Date dataDarreraModificacio;

	@Column (name="USU_DATCRE")
	public java.util.Date dataCreacio;

	@Column (name="USU_USUCRE", length=50)
	@Nullable
	public java.lang.String usuariCreacio;

	@Column (name="USU_ACTIU", length=1)
	public java.lang.String actiu;

	@Column (name="USU_SEGLLI", length=50)
	@Nullable
	public java.lang.String segonLlinatge;

	@Column (name="USU_COMENT", length=1024)
	@Nullable
	public java.lang.String comentari;

	@ForeignKey (foreignColumn="AXA_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.XarxaACEntity> xarxesAC;

	@Column (name="USU_IDMACO")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidorCorreu;

	@Column (name="USU_IDMAQ")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidorOfimatic;

	@Column (name="USU_IDDCO")
	@Nullable
	public es.caib.seycon.ng.model.DominiCorreuEntity dominiCorreu;

	@Column (name="USU_IDMAPR")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidorPerfil;

	@Column (name="USU_IDGRU")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grupPrimari;

	@ForeignKey (foreignColumn="TAR_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.ScTarget> targetesExtranet;

	@ForeignKey (foreignColumn="DUS_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.DadaUsuariEntity> dadaUsuari;

	@ForeignKey (foreignColumn="UGR_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariGrupEntity> grupsSecundaris;

	@ForeignKey (foreignColumn="UIM_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariImpressoraEntity> impressores;

	@ForeignKey (foreignColumn="FAR_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.RenovacioEntity> renovacions;

	@ForeignKey (foreignColumn="SES_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.SessioEntity> sessions;

	@ForeignKey (foreignColumn="ULC_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> llistaDeCorreuUsuari;

	@Column (name="USU_MULSES", length=1)
	@Nullable
	public java.lang.String multiSessio;

	@Column (name="USU_ALCOAN", length=240)
	@Nullable
	public java.lang.String aliesCorreu;

	@ForeignKey (foreignColumn="APL_IDCONTACT")
	public java.util.Collection<es.caib.seycon.ng.model.AplicacioEntity> aplicacioSocPersonaResponsable;

	@ForeignKey (foreignColumn="ADM_USUID")
	public java.util.Collection<es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity> autoritzacioAccesHostComAdministrador;

	@ForeignKey (foreignColumn="USE_USUID")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariSEUEntity> informacioSEU;

	@Column (name="USU_TUS_ID")
	public es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuari;

	@ForeignKey (foreignColumn="SEC_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.SecretEntity> secrets;

	@ForeignKey (foreignColumn="UAC_USU_ID")
	public java.util.Collection<es.caib.seycon.ng.model.UserAccountEntity> accounts;

	@ForeignKey (foreignColumn="AAC_USU_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountAccessEntity> accountAccess;

	@ForeignKey (foreignColumn="CTR_IDUSU")
	public java.util.Collection<es.caib.seycon.ng.model.ContrasenyaEntity> contrasenyes;

	@ForeignKey (foreignColumn="PAU_USU_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AuthoritativeChangeEntity> pendingAuthoritativeChanges;

	
	/************************ DAOs **********************************/
	@DaoFinder("from es.caib.seycon.ng.model.UsuariEntity  where codi = :codi")
	public es.caib.seycon.ng.model.UsuariEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@DaoFinder
	public String generaCodiUsuari() {
	 return null;
	}
	@DaoFinder("from es.caib.seycon.ng.model.UsuariEntity where id = :id")
	public es.caib.seycon.ng.model.UsuariEntity findById(
		java.lang.Long id) {
	 return null;
	}
	@DaoFinder("select usuari from es.caib.seycon.ng.model.UsuariEntity usuari, es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari where usuari = dadaUsuari.usuari and dadaUsuari.tipusDada.codi = 'NIF' and dadaUsuari.valorDada = :nif")
	public es.caib.seycon.ng.model.UsuariEntity findByNIF(
		java.lang.String nif) {
	 return null;
	}
	@DaoFinder("select usuariGrupEntity.grup\nfrom es.caib.seycon.ng.model.UsuariGrupEntity usuariGrupEntity, es.caib.seycon.ng.model.UsuariEntity usuariEntity where usuariGrupEntity.usuari = usuariEntity and usuariEntity.codi = :codiUsuari")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findGrupsByCodi(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select dadaUsuari from es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari, es.caib.seycon.ng.model.UsuariEntity usuari where dadaUsuari.usuari = usuari and usuari.codi = :codiUsuari")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findDadesByCodi(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select usuariImpressora\nfrom es.caib.seycon.ng.model.UsuariEntity usuari, es.caib.seycon.ng.model.UsuariImpressoraEntity usuariImpressora\nwhere usuariImpressora.usuari = usuari and usuari.codi = :codiUsuari")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findUsuariImpressoraByCodi(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public java.lang.Boolean updateUser(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.model.UsuariEntity findByNomCurt(
		java.lang.String nomCurt) {
	 return null;
	}
	@DaoFinder
	public java.lang.Integer executeQuery(
		java.lang.String hqlQuery) {
	 return null;
	}
	@DaoFinder
	public java.lang.String refreshCanvis(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public java.lang.String[] getTasques(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public java.lang.String getSeguentCodi() {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder
	public java.lang.String getSeguentCodiFarmacia() {
	 return null;
	}
	@DaoFinder("SELECT distinct usu \nFROM es.caib.seycon.ng.model.UsuariEntity usu, es.caib.seycon.ng.model.DadaUsuariEntity dada \nWHERE \nusu.codi=dada.usuari.codi and\n dada.tipusDada.codi = :codiTipusDada  and \ndada.valorDada = :valorDada")
	public es.caib.seycon.ng.model.UsuariEntity findUsuariByCodiTipusDadaIValorDada(
		java.lang.String codiTipusDada, 
		java.lang.String valorDada) {
	 return null;
	}
	@DaoFinder
	public java.lang.String getSeguentCodiAnonim() {
	 return null;
	}
	@DaoFinder("select usuari from es.caib.seycon.ng.model.UsuariEntity usuari, es.caib.seycon.ng.model.GrupEntity grup where usuari.grupPrimari=grup and grup.codi=:codiGrupPrimari")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findByGrupPrimari(
		java.lang.String codiGrupPrimari) {
	 return null;
	}
	@DaoFinder("select usuari from es.caib.seycon.ng.model.UsuariEntity usuari, es.caib.seycon.ng.model.DadaUsuariEntity dadaUsuari where usuari = dadaUsuari.usuari and dadaUsuari.tipusDada.codi = 'NIF' and dadaUsuari.valorDada = :nif")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findUsuarisByNIF(
		java.lang.String nif) {
	 return null;
	}
	@DaoFinder
	public java.lang.String getSeguentCodiAlumne() {
	 return null;
	}
	@DaoFinder("select targetes from es.caib.seycon.ng.model.ScTarget targetes\nwhere targetes.usuari.codi = :codiUsuari and\n (:activa is  not null and targetes.actiu like :activa) order by targetes.dataCaducitat")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findTargetesExtranetByCodi(
		java.lang.String codiUsuari, 
		@Nullable java.lang.String activa) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findUsuarisGrupISubgrupsByCodiGrup(
		java.lang.String codiGrup) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findUsuarisByRolUsuariAtorgat(
		java.lang.String nomRolAtorgat, 
		java.lang.String baseDeDadesRolAtorgat, 
		java.lang.String codiAplicacioRolAtorgat, 
		java.lang.String tipusDomini, 
		java.lang.String codiGrupDominiRolAtorgat, 
		java.lang.String codiAplicacioDominiRolAtorgat, 
		java.lang.Long idValorDominiAplicacioDominiRolAtorgat) {
	 return null;
	}
	@DaoFinder
	public java.lang.String getSeguentNumSolicitudVerificarIdentitatUsuari() {
	 return null;
	}
	@DaoFinder
	public java.lang.String getSeguentCodiMaquina() {
	 return null;
	}
	@DaoFinder
	public es.caib.seycon.ng.comu.EstatContrasenya getPasswordsStatus(
		es.caib.seycon.ng.model.UsuariEntity usuariEntity, 
		es.caib.seycon.ng.model.DominiContrasenyaEntity dominiContrasenyes) {
	 return null;
	}
	@DaoFinder("select ue\nfrom es.caib.seycon.ng.model.UsuariEntity ue\njoin ue.accounts as accounts\njoin accounts.account as acc\njoin acc.dispatcher as dispatcher\nwhere acc.type='U' and dispatcher.codi=:dispatcher and acc.name=:account\n")
	public es.caib.seycon.ng.model.UsuariEntity findByAccount(
		java.lang.String account, 
		java.lang.String dispatcher) {
	 return null;
	}
	public java.lang.String getFullName() {
	 return null;
	}
}
