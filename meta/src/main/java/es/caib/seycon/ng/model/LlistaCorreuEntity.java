//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_LLICOR" )
@Depends ({es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.DominiCorreuEntity.class,
	es.caib.seycon.ng.model.CorreuExternEntity.class,
	es.caib.seycon.ng.comu.LlistaCorreu.class,
	es.caib.seycon.ng.model.RelacioLlistaCorreuEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuUsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.servei.LlistesDeCorreuService.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class LlistaCorreuEntity {

	@Column (name="LCO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="LCO_DESCRI", length=50)
	@Nullable
	public java.lang.String descripcio;

	@Column (name="LCO_NOM", length=50)
	public java.lang.String nom;

	@ForeignKey (foreignColumn="ELC_IDLCO")
	public java.util.Collection<es.caib.seycon.ng.model.CorreuExternEntity> externs;

	@Column (name="LCO_IDDCO")
	@Nullable
	public es.caib.seycon.ng.model.DominiCorreuEntity domini;

	@ForeignKey (foreignColumn="ULC_IDLCO")
	public java.util.Collection<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> llistaDeCorreuUsuari;

	@ForeignKey (foreignColumn="LLC_IDLCO1")
	public java.util.Collection<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> relacioLlistaCorreuFromConte;

	@ForeignKey (foreignColumn="LLC_IDLCO2")
	public java.util.Collection<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> relacioLlistaCorreuFromPertany;

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuEntity> findByNom(
		java.lang.String nom) {
	 return null;
	}
	@DaoFinder("select llistaCorreu \nfrom \nes.caib.seycon.ng.model.LlistaCorreuEntity llistaCorreu \nleft join llistaCorreu.domini as domini \nwhere \n(:domini is null or domini.codi like :domini) and \n(:descripcio is null or llistaCorreu.descripcio like :descripcio) and \n(:nom is null or llistaCorreu.nom like :nom) \norder by llistaCorreu.nom, llistaCorreu.domini.codi")
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuEntity> findByDades(
		java.lang.String nom, 
		java.lang.String domini, 
		java.lang.String descripcio) {
	 return null;
	}
	@DaoFinder("select llistaCorreuUsuari.usuari from es.caib.seycon.ng.model.LlistaCorreuUsuariEntity as llistaCorreuUsuari left join llistaCorreuUsuari.llistaDeCorreu as llistaCorreu left join llistaCorreu.domini as dominiCorreu where llistaCorreu.nom = :nomLlistaCorreu and (( :codiDomini is null and dominiCorreu is null) or ( :codiDomini is not null and dominiCorreu.codi = :codiDomini))")
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuEntity> findUsuarisByNomAndCodiDomini(
		java.lang.String nomLlistaCorreu, 
		java.lang.String codiDomini) {
	 return null;
	}
	@DaoFinder("select llistaCorreu \nfrom \nes.caib.seycon.ng.model.LlistaCorreuEntity as llistaCorreu \nleft join  llistaCorreu.domini as dominiCorreu \nwhere \nllistaCorreu.nom = :nom and \n(( :codiDomini is null and dominiCorreu is null) or \n ( :codiDomini is not null and  dominiCorreu is not null and  dominiCorreu.codi = :codiDomini))")
	public es.caib.seycon.ng.model.LlistaCorreuEntity findByNomAndCodiDomini(
		java.lang.String nom, 
		java.lang.String codiDomini) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
