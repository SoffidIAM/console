//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;

@Entity(table = "SC_GRUATT")
@Depends({AutoritzacioService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class })
public class GroupAttributeEntity {
	@Column(name = "GAT_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "GAT_VALOR", length = 1024)
	@Nullable
	public java.lang.String value;

	@Column(name = "GAT_GRU_ID", reverseAttribute = "attributes")
	public GrupEntity group;

	@Column(name = "GAT_TDA_ID")
	public TipusDadaEntity metadata;

	@Column(name = "GAT_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;

	public Object getObjectValue () { return null;}
	
	public void setObjectValue (Object value) {}

	@DaoFinder("select att from com.soffid.iam.model.GroupAttributeEntity as att "
			+ "where att.metadata.name = :name and att.value = :value ")
	public java.util.List<GroupAttributeEntity> findByNameAndValue(
			String name, String value) {
		return null;
	}

}
