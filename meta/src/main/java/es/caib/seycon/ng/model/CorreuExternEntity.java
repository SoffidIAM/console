//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_EXTLCO" , translatedName="ExternEmailEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.comu.CorreuExtern.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class CorreuExternEntity {

	@Column (name="ELC_ADRECA", length=100, translated="address")
	public java.lang.String adreca;

	@Column (name="ELC_IDLCO", translated="mailList")
	public es.caib.seycon.ng.model.LlistaCorreuEntity llistaCorreu;

	@Column (name="ELC_ID")
	@Identifier
	public java.lang.Long id;

	@Operation(translated="findByAddress")
	@DaoFinder
	public es.caib.seycon.ng.model.CorreuExternEntity findByAdreca(
		java.lang.String adreca) {
	 return null;
	}
	@Operation(translated="findByOListNameAndDomainCode")
	@DaoFinder("select llicor.externs as extern \nfrom es.caib.seycon.ng.model.LlistaCorreuEntity llicor left join llicor.domini as dominiCorreu where (:nomLlistaCorreu is null or llicor.nom = :nomLlistaCorreu) and  ( (:codiDomini is null and dominiCorreu is null) or (:codiDomini is not null and dominiCorreu is not null and dominiCorreu.codi = :codiDomini))")
	public java.util.List<es.caib.seycon.ng.model.CorreuExternEntity> findCorreusExternsByNomLlistaCorreuAndCodiDomini(
		java.lang.String nomLlistaCorreu, 
		java.lang.String codiDomini) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.CorreuExternEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
