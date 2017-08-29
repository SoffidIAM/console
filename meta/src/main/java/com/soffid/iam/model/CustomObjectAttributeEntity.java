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

@Entity(table = "SC_COBATT")
@Depends({AutoritzacioService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public class CustomObjectAttributeEntity {
	@Column(name = "COA_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "COA_VALUE", length = 1024)
	@Nullable
	public java.lang.String value;

	@Column(name = "COA_COB_ID", reverseAttribute = "attributes", composition = true)
	public CustomObjectEntity customObject;

	@Column(name = "COA_TDA_ID")
	public TipusDadaEntity metadata;

	@Column(name = "COA_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;

	public Object getObjectValue () { return null;}
	
	public void setObjectValue (Object value) {}

	@DaoFinder("select att from com.soffid.iam.model.CustomObjectAttributeEntity as att "
			+ "where att.metadata.name = :name and att.value = :value and att.customObject.type.name=:type and "
			+ "att.metadata.tenant.id = :tenantId")
	public java.util.List<CustomObjectAttributeEntity> findByTypeNameAndValue(
			String type, String name, String value) {
		return null;
	}

}
