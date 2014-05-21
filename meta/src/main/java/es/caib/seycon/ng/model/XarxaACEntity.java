//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_AUTXAR" )
@Depends ({es.caib.seycon.ng.model.XarxaEntity.class,
	es.caib.seycon.ng.comu.NetworkAuthorization.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class XarxaACEntity {

	@Column (name="AXA_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="AXA_NIVELL")
	public java.lang.Integer nivell;

	@Column (name="AXA_MASMAQ", length=50)
	@Nullable
	public java.lang.String nomMaquines;

	@Column (name="AXA_IDXAR")
	public es.caib.seycon.ng.model.XarxaEntity xarxa;

	@Column (name="AXA_IDROL")
	@Nullable
	public es.caib.seycon.ng.model.RolEntity role;

	@Column (name="AXA_IDGRU")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column (name="AXA_IDUSU")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByXarxa(
		es.caib.seycon.ng.model.XarxaEntity xarxa) {
	 return null;
	}
	@DaoFinder("select xarxaAC \nfrom \nes.caib.seycon.ng.model.XarxaACEntity xarxaAC \nleft join xarxaAC.usuari usuari \nleft join xarxaAC.role rol \nleft join xarxaAC.grup grup \nwhere \n(\n(grup is not null and grup.codi = :codiIdentitat) or \n(usuari is not null and usuari.codi = :codiIdentitat) or \n(rol is not null and rol.nom = :codiIdentitat) \n) \nand xarxaAC.xarxa.codi = :codiXarxa")
	public es.caib.seycon.ng.model.XarxaACEntity findByCodiXarxaAndCodiIdentiat(
		java.lang.String codiXarxa, 
		java.lang.String codiIdentitat) {
	 return null;
	}
	@DaoFinder("select xarxaAC\nfrom es.caib.seycon.ng.model.XarxaACEntity xarxaAC\nwhere xarxaAC.role.nom = :nomRol")
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByNomRol(
		java.lang.String nomRol) {
	 return null;
	}
	@DaoFinder("select xarxaAC\nfrom es.caib.seycon.ng.model.XarxaACEntity xarxaAC\nwhere xarxaAC.usuari.codi = :codiUsuari")
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select xarxaAC\nfrom es.caib.seycon.ng.model.XarxaACEntity xarxaAC\nwhere xarxaAC.grup.codi = :codiGrup")
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByCodiGrup(
		java.lang.String codiGrup) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@DaoFinder("select xarxaAC\nfrom es.caib.seycon.ng.model.XarxaACEntity xarxaAC\nleft join xarxaAC.role as elrol\nleft join elrol.aplicacio as aplica\nleft join elrol.baseDeDades as agent\nwhere elrol.nom = :nomRol \nand aplica.codi = :codiAplica\nand agent.codi = :codiDispat\norder by xarxaAC.xarxa.codi")
	public java.util.List<es.caib.seycon.ng.model.XarxaACEntity> findByNomRolAndCodiAplicacioRolAndCodiDispatcher(
		java.lang.String nomRol, 
		java.lang.String codiAplica, 
		java.lang.String codiDispat) {
	 return null;
	}
}
