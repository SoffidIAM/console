//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.LetterCaseEnum;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.ForeignKey;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;

@Entity (table="SC_TIPDAD", translatedName="MetaDataEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.TipusDada.class,
	CustomObjectTypeEntity.class,
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

	@Column (name="TDA_SCOPE", length=50)
	@Nullable
	public MetadataScope scope;

	@Column (name="TDA_TYPE", length=50)
	@Nullable
	public es.caib.seycon.ng.comu.TypeEnumeration type;

	@Column (name="TDA_SIZE")
	@Nullable
	public java.lang.Integer size;
	
	@Column (name="TDA_REQUIR")
	@Nullable
	public Boolean required;
	
	@Column (name="TDA_RDONLY")
	@Nullable
	public Boolean readOnly;
	
	@Column(name = "TDA_MULTIV")
	@Nullable
	public Boolean multiValued;

	@Column (name="TDA_MLTLIN")
	@Nullable
	public Boolean multiLine;
	
	@Description("Include field in quick searches")
	@Column (name="TDA_QUSECR")
	@Nullable
	public Boolean searchCriteria;
	
	@Description ("Display a maxim of rows values. For more values, a scroll bar will appear")
	@Column(name = "TDA_MVROWS")
	@Nullable
	public Integer multiValuedRows;

	@Description ("blank separated list of url-encoded values")
	@Column (name="TDA_VALUE", length=64000)
	@Nullable
	public String values;

	@Description("Label to display")
	@Column (name="TDA_LABEL", length=64)
	@Nullable
	public String label;
	
	@Nullable
	@Column (name="TDA_HINT", length=128)
	@Description("User hint")
	public String hint;
	
	@Description("NLS Label to display")
	@Column (name="TDA_NLSLAB", length=128)
	@Nullable
	public String nlsLabel;

	@Description("Bulti-in handler class")
	@Column (name="TDA_UIHANDL", length=128)
	@Nullable
	String builtinHandler;
	
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
	
	@Description("Attribute description")
	@Column (name="TDA_DESCRI", length=512)
	@Nullable
	public String description;
	
	
	@Description("Object type acting as owner of the attribute")
	@Column (name="TDA_COT_ID", reverseAttribute="attributes", composition=true)
	CustomObjectTypeEntity objectType;

	@Description("Object type instances acting as value of the attribute, for custom object type attributes")
	@Column (name="TDA_COT_VAL_ID", reverseAttribute="referencedBy", composition=true)
	@Nullable
	CustomObjectTypeEntity dataObjectType;

	
	@Column (name="TDA_VISEXP", length=2048)
	@Nullable
	String visibilityExpression;
	
	@Column (name="TDA_VALEXP", length=2048)
	@Nullable
	String validationExpression;
	
	@Column (name="TDA_LOATRI", length=4096)
	@Nullable
	String onLoadTrigger;
	
	@Column (name="TDA_CHATRI", length=4096)
	@Nullable
	String onChangeTrigger;
	
	@Column (name="TDA_FOCTRI", length=4096)
	@Nullable
	String onFocusTrigger;
	
	@Column (name="TDA_VALCLA", length=100)
	@Description("Java class to validate field")
	@Nullable
	String validator;
	
	@Column (name="TDA_ENUM", length=100)
	@Description("Java class to enumerate values")
	@Nullable
	String enumeration;
	
	@Column (name="TDA_TEN_ID")
	TenantEntity tenant;

	@Description("SCIM Expression to test if the reference object can be selected ")
	@Nullable
	@Column(name="TDA_FILEXP")
	String filterExpression;

	@Description("Built-in attribute")
	@Nullable
	@Column(name="TDA_BUILTI", defaultValue="false")
	Boolean builtin;
	
	@Description ("Uppercase / lowercase usage")
	@Nullable
	@Column(name="TDA_LETCAS", defaultValue="com.soffid.iam.api.LetterCaseEnum.MIXEDCASE")
	LetterCaseEnum letterCase;

	// ********************** DAOS ************************
	@Operation(translated="findDataTypeByName")
	@DaoFinder("from com.soffid.iam.model.MetaDataEntity where name = :name and tenant.id = :tenantId and scope='user'")
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

	@DaoFinder("from com.soffid.iam.model.MetaDataEntity tipusDada "
			+ "where (:codi is null or tipusDada.name like :codi) "
			+ "and   (tipusDada.scope = :scope or :scope is null ) and "
			+ "tipusDada.tenant.id = :tenantId")
	@Operation(translated="findDataTypesByScopeAndName")
	public java.util.List<es.caib.seycon.ng.model.TipusDadaEntity> findTipusDadesByScopeAndName(
			MetadataScope scope, java.lang.String codi) {
	 return null;
	}

	@DaoFinder("from com.soffid.iam.model.MetaDataEntity tipusDada "
			+ "where (:codi is null or tipusDada.name like :codi) "
			+ "and   (tipusDada.objectType.name = :type or :type is null ) and "
			+ "tipusDada.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.TipusDadaEntity> findByObjectTypeAndName(
			String type, java.lang.String codi) {
	 return null;
	}

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.TipusDadaEntity> findByScope(MetadataScope scope) {
	 return null;
	}
}

@Index (name="TAD_UK_CODE",	
entity=es.caib.seycon.ng.model.TipusDadaEntity.class,
columns={"TDA_TEN_ID", "TDA_SCOPE", "TDA_CODI"})
abstract class TipusDadaCodiIndex {
}


@Index (name="TDA_UK_ORDRE",	unique=false,
entity=es.caib.seycon.ng.model.TipusDadaEntity.class,
columns={"TDA_TEN_ID", "TDA_SCOPE", "TDA_ORDRE"})
abstract class TipusDadaOrdreIndex {
}

