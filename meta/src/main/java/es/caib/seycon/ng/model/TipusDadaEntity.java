//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TIPDAD", translatedName="MetaDataEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.TipusDada.class,
	es.caib.seycon.ng.model.DadaUsuariEntity.class})
public abstract class TipusDadaEntity {

	@Column (name="TDA_CODI", length=25, translated="name")
	public java.lang.String codi;

	@Column (name="TDA_ORDRE", translated="order")
	public java.lang.Long ordre;

	@ForeignKey (foreignColumn="DUS_TDAID", translated="data")
	public java.util.Collection<es.caib.seycon.ng.model.DadaUsuariEntity> datos;

	@Column (name="TDA_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TDA_TYPE", length=50)
	@Nullable
	public es.caib.seycon.ng.comu.TypeEnumeration type;

	@Column (name="TDA_SIZE")
	@Nullable
	public java.lang.Integer size;
	
	@Column (name="TDA_REQUIR")
	@Nullable
	public Boolean required;
	
	@Description ("blank separated list of url-encoded values")
	@Column (name="TDA_VALUE", length=64000)
	@Nullable
	public String values;

	@Description("Label to display")
	@Column (name="TDA_LABEL", length=64)
	@Nullable
	public String label;
	
	@Description("Administrator visibility")
	@Column (name="TDA_ADMVIS", length=1)
	@Nullable
	public AttributeVisibilityEnum adminVisibility;
	
	@Description("Administrator visibility")
	@Column (name="TDA_OPEVIS", length=1)
	@Nullable
	public AttributeVisibilityEnum operatorVisibility;
	

	@Description("User visibility")
	@Column (name="TDA_USEVIS", length=1)
	@Nullable
	public AttributeVisibilityEnum userVisibility;
	
	@Description("Unique value")
	@Column (name="TDA_UNIQUE", length=1)
	@Nullable
	public Boolean unique;
	
	
	@Column (name="TDA_TEN_ID")
	TenantEntity tenant;

	/********************** DAOS ************************/
	@Operation(translated="findDataTypeByName")
	@DaoFinder("from com.soffid.iam.model.MetaDataEntity where name = :name and tenant.id = :tenantId")
	public es.caib.seycon.ng.model.TipusDadaEntity findTipusDadaByCodi(
		java.lang.String name) {
	 return null;
	}
	@Operation(translated="findDataTypesByName")
	@DaoFinder("from com.soffid.iam.model.MetaDataEntity tipusDada "
			+ "where (:name is null or tipusDada.name like :name) and "
			+ "tipusDada.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.TipusDadaEntity> findTipusDadesByCodi(
		java.lang.String name) {
	 return null;
	}
}

@Index (name="TAD_UK_CODE",	unique=true,
entity=es.caib.seycon.ng.model.TipusDadaEntity.class,
columns={"TDA_TEN_ID", "TDA_CODI"})
abstract class TipusDadaCodiIndex {
}


@Index (name="TDA_UK_ORDRE",	unique=true,
entity=es.caib.seycon.ng.model.TipusDadaEntity.class,
columns={"TDA_TEN_ID", "TDA_ORDRE"})
abstract class TipusDadaOrdreIndex {
}

