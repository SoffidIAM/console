//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.LlistaCorreuEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;

@Entity(table = "SC_MAIATT")
@Depends({AutoritzacioService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class })
public class MailListAttributeEntity {
	@Column(name = "MAT_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "MAT_VALUE", length = 1024)
	@Nullable
	public java.lang.String value;

	@Column(name = "MAT_LCO_ID", reverseAttribute = "attributes", composition = true)
	public LlistaCorreuEntity mailList;

	@Column(name = "RAT_TDA_ID")
	public TipusDadaEntity metadata;

	@Column(name = "RAT_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;

	public Object getObjectValue () { return null;}
	
	public void setObjectValue (Object value) {}

	@DaoFinder("select att from com.soffid.iam.model.MailListAttributeEntity as att "
			+ "where att.metadata.name = :name and att.value = :value ")
	public java.util.List<MailListAttributeEntity> findByNameAndValue(
			String name, String value) {
		return null;
	}

}
