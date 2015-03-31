//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USUGRU" )
@Depends ({es.caib.seycon.ng.model.RolsGrupEntity.class,
	LlistaCorreuEntity.class,
	es.caib.seycon.ng.comu.UsuariGrup.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class UsuariGrupEntity {

	@Column (name="UGR_IDUSU", translated="user")
	@Nullable
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="UGR_IDGRU", translated="group")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column (name="UGR_ID")
	@Identifier
	public java.lang.Long id;

	@Operation(translated="findByUserCodeAndGroupCode")
	@DaoFinder("select usuariGrup from es.caib.seycon.ng.model.UsuariGrupEntity usuariGrup where usuariGrup.grup.codi = :codiGrup and usuariGrup.usuari.codi = :codiUsuari \norder by \nusuariGrup.usuari.codi, \nusuariGrup.grup.codi")
	public es.caib.seycon.ng.model.UsuariGrupEntity findByCodiUsuariAndCodiGrup(
		java.lang.String codiUsuari, 
		java.lang.String codiGrup) {
	 return null;
	}
	@Operation(translated="fidnByUserCode")
	@DaoFinder("select usuariGrup from es.caib.seycon.ng.model.UsuariGrupEntity usuariGrup where usuariGrup.usuari.codi = :codiUsuari \norder by \nusuariGrup.usuari.codi, \nusuariGrup.grup.codi")
	public java.util.List<es.caib.seycon.ng.model.UsuariGrupEntity> findByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.UsuariGrupEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
	@Operation(translated="findByGroupCode")
	@DaoFinder("select usuGru from es.caib.seycon.ng.model.UsuariGrupEntity usuGru where usuGru.grup.codi=:codiGrup")
	public java.util.List<es.caib.seycon.ng.model.UsuariGrupEntity> findByCodiGrup(
		java.lang.String codiGrup) {
	 return null;
	}
}
