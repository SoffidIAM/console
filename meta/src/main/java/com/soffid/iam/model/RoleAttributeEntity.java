//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;

@Entity(table = "SC_ROLATT")
@Depends({AutoritzacioService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class })
public class RoleAttributeEntity {
	@Column(name = "RAT_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "RAT_VALOR", length = 1024)
	@Nullable
	public java.lang.String value;

	@Column(name = "RAT_ROL_ID", reverseAttribute = "attributes", composition = true)
	public RolEntity role;

	@Column(name = "RAT_TDA_ID")
	public TipusDadaEntity metadata;

	@Column(name = "RAT_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;

	public Object getObjectValue () { return null;}
	
	public void setObjectValue (Object value) {}

	@DaoFinder("select att from com.soffid.iam.model.RoleAttributeEntity as att "
			+ "where att.metadata.name = :name and att.value = :value ")
	public java.util.List<RoleAttributeEntity> findByNameAndValue(
			String name, String value) {
		return null;
	}

}
