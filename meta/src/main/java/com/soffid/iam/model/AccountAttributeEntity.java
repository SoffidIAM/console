//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;

@Entity(table = "SC_ACCATT")
@Depends({ es.caib.seycon.ng.comu.DadaUsuari.class,
	AutoritzacioService.class,
	
		es.caib.seycon.ng.model.AuditoriaEntity.class })
public abstract class AccountAttributeEntity {
	@Column(name = "AAT_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "AAT_VALOR", length = 1024)
	@Nullable
	public java.lang.String value;

	@Column(name = "AAT_ACC_ID", reverseAttribute = "attributes", composition = true)
	public es.caib.seycon.ng.model.AccountEntity account;

	@Column(name = "AAT_AME_ID")
	@Nullable
	public AccountMetadataEntity systemMetadata;

	@Column(name = "AAT_TDA_ID")
	@Nullable
	public TipusDadaEntity metadata;

	@Column(name = "AAT_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;
	
	public Object getObjectValue () { return null;}
	
	public void setObjectValue (Object value) {}



	@DaoFinder("select att from com.soffid.iam.model.AccountAttributeEntity as att "
			+ "where att.account.name = :account and "
			+ "att.account.system.name = :system and "
			+ "(att.metadata.name = :name or att.systemMetadata.name = :name) and "
			+ "att.account.system.tenant.id=:tenantId")
	public AccountAttributeEntity findByName(
			String system,
			String account, String name) {
		return null;
	}

	@DaoFinder("select att from com.soffid.iam.model.AccountAttributeEntity as att "
			+ "where att.account.system.name = :system and "
			+ "(att.metadata.name = :name or att.systemMetadata.name = :name) "
			+ "and att.value = :value and att.account.system.tenant.id=:tenantId")
	public java.util.List<AccountAttributeEntity> findByNameAndValue(
			String system,
			String name, String value) {
		return null;
	}

	@Description ("Gets the visibility level for an attribue")
	@Operation
	public AttributeVisibilityEnum getAttributeVisibility() {
		return null;
	}
}
