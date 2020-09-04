//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_IMPRES", translatedName="PrinterEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.comu.Impressora.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.GrupImpressoraEntity.class,
	es.caib.seycon.ng.model.UsuariImpressoraEntity.class})
public abstract class ImpressoraEntity {

	@Column (name="IMP_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="IMP_MODEL", length=50)
	@Nullable
	public java.lang.String model;

	@Column (name="IMP_CODI", length=20, translated="name")
	public java.lang.String codi;

	@Column (name="IMP_DESCR", length=50)
	@Nullable
	public java.lang.String description;

	@Column (name="IMP_LOCAL", length=1)
	@Nullable
	public java.lang.String local;

	@Column (name="IMP_IDMAQ", translated="server")
	public es.caib.seycon.ng.model.MaquinaEntity servidor;
	
	@Column (name="IMP_TEN_ID")
	TenantEntity tenant;

	@ForeignKey (foreignColumn="GIM_IDIMP", translated="groups")
	public java.util.Collection<es.caib.seycon.ng.model.GrupImpressoraEntity> grups;

	@ForeignKey (foreignColumn="UIM_IDIMP", translated="users")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariImpressoraEntity> usuaris;

	@Operation(translated="findByName")
	@DaoFinder("from com.soffid.iam.model.PrinterEntity p "
			+ "where p.name=:name and p.tenant.id = :tenantId")
	public es.caib.seycon.ng.model.ImpressoraEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
	@Operation(translated="findPrintersByCriteria")
	@DaoFinder("select impressora "
			+ "from com.soffid.iam.model.PrinterEntity impressora "
			+ "where (:name is null or impressora.name like :name) and "
			+ "(:model is null or impressora.model like :model) and "
			+ "(:local is null or impressora.local = :local) and "
			+ "(:host is null or  impressora.server.name like :host) and "
			+ "impressora.tenant.id = :tenantId "
			+ "order by impressora.name")
	public java.util.List<es.caib.seycon.ng.model.ImpressoraEntity> findImpressoresByCriteri(
		java.lang.String model, 
		java.lang.String name, 
		java.lang.String local, 
		java.lang.String host) {
	 return null;
	}
}

@Index (name="IMP_UK_CODI",	unique=true,
entity=es.caib.seycon.ng.model.ImpressoraEntity.class,
columns={"IMP_TEN_ID", "IMP_CODI"})
abstract class ImpressoraIndex {
}

