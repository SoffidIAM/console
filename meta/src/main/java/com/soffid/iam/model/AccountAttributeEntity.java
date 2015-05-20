//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_ACCATT")
@Depends({ es.caib.seycon.ng.comu.DadaUsuari.class,
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
	public AccountMetadataEntity metadata;

	@Column(name = "AAT_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;

	@DaoFinder("select att from com.soffid.iam.model.AccountAttributeEntity as att "
			+ "where att.account.name = :account and att.account.dispatcher.codi = :system and att.metadata.name = :name")
	public AccountAttributeEntity findByName(
			String system,
			String account, String name) {
		return null;
	}

	@DaoFinder("select att from com.soffid.iam.model.AccountAttributeEntity as att "
			+ "where att.account.dispatcher.codi = :system and "
			+ "att.metadata.name = :name and att.value = :value ")
	public java.util.List<AccountAttributeEntity> findByNameAndValue(
			String system,
			String name, String value) {
		return null;
	}
}
