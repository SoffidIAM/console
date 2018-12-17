//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.DispatcherEntity;

@Entity(table = "SC_ACCMET", tenantFilter="system.tenant.id")
@Depends({ es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.comu.TipusDada.class})
public abstract class AccountMetadataEntity {

	@Column(name = "AME_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "AME_NAME", length = 50)
	public java.lang.String name;

	@Column(name = "AME_DIS_ID", reverseAttribute = "metaData", cascadeDelete = true)
	public DispatcherEntity system;

	@Column(name = "AME_ORDER")
	public java.lang.Long order;

	@ForeignKey(foreignColumn = "AAT_AME_ID")
	public java.util.Collection<AccountAttributeEntity> data;

	@Column(name = "AME_TYPE", length = 50)
	@Nullable
	public es.caib.seycon.ng.comu.TypeEnumeration type;

	@Column(name = "AME_SIZE")
	@Nullable
	public java.lang.Integer size;

	@Column(name = "AME_REQUIR")
	@Nullable
	public Boolean required;

	@Column(name = "AME_MULTIV")
	@Nullable
	public Boolean multiValued;

	@Description("blank separated list of url-encoded values")
	@Column(name = "AME_VALUE", length = 64000)
	@Nullable
	public String values;

	@Description("Label to display")
	@Column(name = "AME_LABEL", length = 64)
	@Nullable
	public String label;

	@Description("Administrator visibility")
	@Column(name = "AME_ADMVIS", length = 1)
	@Nullable
	public AttributeVisibilityEnum adminVisibility;

	@Description("Administrator visibility")
	@Column(name = "AME_OPEVIS", length = 1)
	@Nullable
	public AttributeVisibilityEnum operatorVisibility;

	@Description("User visibility")
	@Column(name = "AME_USEVIS", length = 1)
	@Nullable
	public AttributeVisibilityEnum userVisibility;

	@Description("Unique value")
	@Column (name="AME_UNIQUE", length=1)
	@Nullable
	public Boolean unique;

	@Description("Object type instances acting as value of the attribute, for custom object type attributes")
	@Column (name="TDA_COT_ID", reverseAttribute="refererncedBy", composition=true)
	@Nullable
	CustomObjectTypeEntity dataObjectType;
	/********************** DAOS ************************/
	@DaoFinder("from com.soffid.iam.model.AccountMetadataEntity where system.name = :system and name = :name\n"
			+ "and system.tenant.id=:tenantId")
	public AccountMetadataEntity findByName(String system,
			String name) {
		return null;
	}

	@DaoFinder("from com.soffid.iam.model.AccountMetadataEntity where system.name = :systemName\n"
			+ "and system.tenant.id=:tenantId "
			+ "order by order")
	public java.util.List<AccountMetadataEntity> findBySystem(
			java.lang.String systemName) {
		return null;
	}
}

@Index(entity = AccountMetadataEntity.class, unique = true, name = "SC_ATTMET_NAME_NDX", 
	columns = {"AME_DIS_ID","AME_NAME"})
class AccountMetadataNameIndex {

}