//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_LCOLCO" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.RelacioLlistaCorreu.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class RelacioLlistaCorreuEntity {

	@Column (name="LLC_IDLCO1")
	public es.caib.seycon.ng.model.LlistaCorreuEntity conte;

	@Column (name="LLC_IDLCO2")
	public es.caib.seycon.ng.model.LlistaCorreuEntity pertany;

	@Column (name="LLC_ID")
	@Identifier
	public java.lang.Long id;

	@DaoFinder("select llistaCorreuR \nfrom es.caib.seycon.ng.model.RelacioLlistaCorreuEntity llistaCorreuR \nleft join llistaCorreuR.pertany.domini as correuDominiPertany \nleft join llistaCorreuR.conte.domini as correuDominiConte \nwhere \nllistaCorreuR.pertany.nom = :nomPertany and \nllistaCorreuR.conte.nom = :nomConte and \n((:dominiConte is null and correuDominiConte is null) or \n(:dominiConte is not null and correuDominiConte.codi = :dominiConte)) and \n((:dominiPertany is null and correuDominiPertany is null) or \n(:dominiPertany is not null and correuDominiPertany.codi = :dominiPertany))")
	public es.caib.seycon.ng.model.RelacioLlistaCorreuEntity findByNomPertanyAndDominiPertanyAndNomConteAndDominiConte(
		java.lang.String nomPertany, 
		java.lang.String dominiPertany, 
		java.lang.String nomConte, 
		java.lang.String dominiConte) {
	 return null;
	}
	@DaoFinder("select llistaCorreuR \nfrom es.caib.seycon.ng.model.RelacioLlistaCorreuEntity llistaCorreuR left join llistaCorreuR.pertany.domini as dominiCorreu where llistaCorreuR.pertany.nom = :nomPertany and ((:codiDomini is null and dominiCorreu is null ) or (:codiDomini is not null and dominiCorreu is not null and dominiCorreu.codi = :codiDomini))")
	public java.util.List<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> findCollectionByNomPertanyAndCodiDomini(
		java.lang.String nomPertany, 
		java.lang.String codiDomini) {
	 return null;
	}
	@DaoFinder("select llistaCorreuR \nfrom es.caib.seycon.ng.model.RelacioLlistaCorreuEntity llistaCorreuR left join llistaCorreuR.conte.domini as dominiCorreu where llistaCorreuR.conte.nom = :nomConte and ((:codiDomini is null and dominiCorreu is null ) or (:codiDomini is not null and dominiCorreu is not null and dominiCorreu.codi = :codiDomini))")
	public java.util.List<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> findCollectionByNomConteAndCodiDomini(
		java.lang.String nomConte, 
		java.lang.String codiDomini) {
	 return null;
	}
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> find(
		@Nullable java.util.Collection<es.caib.seycon.ng.model.Parameter> parameters) {
	 return null;
	}
}
