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

import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;

@Entity(table = "SC_HOSATT")
@Depends({AutoritzacioService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class })
public class HostAttributeEntity {
	@Column(name = "HAT_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "HAT_VALOR", length = 1024)
	@Nullable
	public java.lang.String value;

	@Column(name = "HAT_MAQ_ID", reverseAttribute = "attributes")
	public MaquinaEntity host;

	@Column(name = "HAT_TDA_ID")
	public TipusDadaEntity metadata;

	@Column(name = "HAT_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;

	public Object getObjectValue () { return null;}
	
	public void setObjectValue (Object value) {}

	@DaoFinder("select att from com.soffid.iam.model.HostAttributeEntity as att "
			+ "where att.metadata.name = :name and att.value = :value ")
	public java.util.List<HostAttributeEntity> findByNameAndValue(
			String name, String value) {
		return null;
	}

}
