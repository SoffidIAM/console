//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;

@Entity(table = "SC_APPATT")
@Depends({AutoritzacioService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class })
public abstract class ApplicationAttributeEntity {
	@Column(name = "AAT_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "AAT_VALOR", length = 1024)
	@Nullable
	public java.lang.String value;

	@Column(name = "AAT_APL_ID", reverseAttribute = "attributes", composition = true)
	public AplicacioEntity informationSystem;

	@Column(name = "AAT_TDA_ID")
	public TipusDadaEntity metadata;

	@Column(name = "AAT_BLOB", length = 400000)
	@Nullable
	public byte[] blobDataValue;
	
	public Object getObjectValue () { return null;}
	
	public void setObjectValue (Object value) {}

	@DaoFinder("select att from com.soffid.iam.model.ApplicationAttributeEntity as att "
			+ "where att.metadata.codi = :name and att.value = :value ")
	public java.util.List<ApplicationAttributeEntity> findByNameAndValue(
			String name, String value) {
		return null;
	}

}
