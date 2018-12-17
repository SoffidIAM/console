package com.soffid.iam.service.impl;

import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@Service(internal=true)
@Depends({ UsuariEntity.class, CustomObjectEntity.class, GrupEntity.class,  AplicacioEntity.class} )
public class AttributeValidationService {
	public void validate ( TypeEnumeration type, @Nullable CustomObjectTypeEntity customObjectType, @Nullable Object value) {}
}
