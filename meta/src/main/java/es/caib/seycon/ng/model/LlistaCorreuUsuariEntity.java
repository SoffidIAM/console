//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_USULCO" , translatedName="UserEmailEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.comu.LlistaCorreuUsuari.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class LlistaCorreuUsuariEntity {

	@Column (name="ULC_IDLCO", translated="mailList")
	public es.caib.seycon.ng.model.LlistaCorreuEntity llistaDeCorreu;

	@Column (name="ULC_IDUSU", translated="user")
	public es.caib.seycon.ng.model.UsuariEntity usuari;

	@Column (name="ULC_ID")
	@Identifier
	public java.lang.Long id;

	@Operation(translated="findByMailListNameAndDomainCodeAndUserCode")
	@DaoFinder("select liu from es.caib.seycon.ng.model.LlistaCorreuUsuariEntity liu left join liu.llistaDeCorreu.domini as dominiCorreu where liu.llistaDeCorreu.nom = :nomLlistaCorreu and ((:codiDomini is null and dominiCorreu is null) or ( :codiDomini is not null and dominiCorreu is not null and dominiCorreu.codi = :codiDomini)) and liu.usuari.codi = :codiUsuari ")
	public es.caib.seycon.ng.model.LlistaCorreuUsuariEntity findByNomLlistaCorreuAndCodiDominiAndCodiUsuari(
		java.lang.String nomLlistaCorreu, 
		java.lang.String codiDomini, 
		java.lang.String codiUsuari) {
	 return null;
	}
	@Operation(translated="findByUserCode")
	@DaoFinder("from es.caib.seycon.ng.model.LlistaCorreuUsuariEntity liu where liu.usuari.codi = :codiUsuari")
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> findByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@Operation(translated="findByMailListNameAndDomainCode")
	@DaoFinder("select liu from es.caib.seycon.ng.model.LlistaCorreuUsuariEntity liu left join liu.llistaDeCorreu.domini as dominiCorreu where liu.llistaDeCorreu.nom = :nomLlistaCorreu and ((:codiDomini is null and dominiCorreu is null) or ( :codiDomini is not null and dominiCorreu is not null and dominiCorreu.codi = :codiDomini))")
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> findByNomLlistaCorreuAndCodiDomini(
		java.lang.String nomLlistaCorreu, 
		java.lang.String codiDomini) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
