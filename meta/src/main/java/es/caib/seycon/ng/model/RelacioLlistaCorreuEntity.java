//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_LCOLCO" , translatedName="EmailListContainerEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.RelacioLlistaCorreu.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class})
public abstract class RelacioLlistaCorreuEntity {

	@Column (name="LLC_IDLCO1", translated="contains")
	public es.caib.seycon.ng.model.LlistaCorreuEntity conte;

	@Column (name="LLC_IDLCO2", translated="pertains")
	public es.caib.seycon.ng.model.LlistaCorreuEntity pertany;

	@Column (name="LLC_ID")
	@Identifier
	public java.lang.Long id;

	@Operation(translated="findByContainerAndContained")
	@DaoFinder("select llistaCorreuR \n"
			+ "from com.soffid.iam.model.EmailListContainerEntity llistaCorreuR \n"
			+ "left join llistaCorreuR.pertains.domain as correuDominiPertany \n"
			+ "left join llistaCorreuR.contains.domain as correuDominiConte \n"
			+ "where \n"
			+ "llistaCorreuR.pertains.tenant.id = :tenantId and "
			+ " llistaCorreuR.pertains.name = :ownerName and \n"
			+ "llistaCorreuR.contains.name = :ownedName and \n"
			+ "((:ownedDomain is null and correuDominiConte is null) or \n"
			+ " (:ownedDomain is not null and correuDominiConte.name = :ownedDomain)) and \n"
			+ "((:ownerDomain is null and correuDominiPertany is null) or \n"
			+ " (:ownerDomain is not null and correuDominiPertany.name = :ownerDomain))")
	public es.caib.seycon.ng.model.RelacioLlistaCorreuEntity findByNomPertanyAndDominiPertanyAndNomConteAndDominiConte(
		java.lang.String ownerName, 
		java.lang.String ownerDomain, 
		java.lang.String ownedName, 
		java.lang.String ownedDomain) {
	 return null;
	}
	@Operation(translated="findByContainer")
	@DaoFinder("select llistaCorreuR \n"
			+ "from com.soffid.iam.model.EmailListContainerEntity llistaCorreuR \n"
			+ "left join llistaCorreuR.pertains.domain as correuDominiPertany \n"
			+ "where \n"
			+ " llistaCorreuR.pertains.tenant.id=:tenantId and "
			+ " llistaCorreuR.pertains.name = :ownerName and \n"
			+ "((:ownerDomain is null and correuDominiPertany is null) or \n"
			+ " (:ownerDomain is not null and correuDominiPertany.name = :ownerDomain))")
	public java.util.List<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> findCollectionByNomPertanyAndCodiDomini(
		java.lang.String ownerName, 
		java.lang.String ownerDomain) {
	 return null;
	}
	@Operation(translated="findByContained")
	@DaoFinder("select llistaCorreuR \n"
			+ "from com.soffid.iam.model.EmailListContainerEntity llistaCorreuR \n"
			+ "left join llistaCorreuR.contains.domain as mailDomain \n"
			+ "where \n"
			+ " llistaCorreuR.contains.tenant.id = :tenantId and "
			+ " llistaCorreuR.contains.name = :ownedName and \n"
			+ "((:ownedDomain is null and mailDomain is null) or \n"
			+ " (:ownedDomain is not null and mailDomain.name = :ownedDomain))")
	public java.util.List<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> findCollectionByNomConteAndCodiDomini(
		java.lang.String ownedName, 
		java.lang.String ownedDomain) {
	 return null;
	}
}
