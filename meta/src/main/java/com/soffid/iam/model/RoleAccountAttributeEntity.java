//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;

@Entity(table = "SC_RACATT")
@Depends({AutoritzacioService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class })
public class RoleAccountAttributeEntity {
	@Column(name = "RAA_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "RAA_VALUE", length = 1024)
	@Nullable
	public java.lang.String value;

	@Column(name = "RAA_RAC_ID", reverseAttribute = "attributes", composition = true)
	public RolAccountEntity grant;

	@Column(name = "RAA_TDA_ID")
	public TipusDadaEntity metadata;

	@Column(name = "RAA_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;

	public Object getObjectValue () { return null;}
	
	public void setObjectValue (Object value) {}

	@DaoFinder("select att from com.soffid.iam.model.RoleAccountAttributeEntity as att "
			+ "where att.metadata.name = :name and att.value = :value ")
	public java.util.List<RoleAccountAttributeEntity> findByNameAndValue(
			String name, String value) {
		return null;
	}

}
