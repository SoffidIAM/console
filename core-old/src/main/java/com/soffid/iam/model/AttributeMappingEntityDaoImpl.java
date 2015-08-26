//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import es.caib.seycon.ng.comu.AttributeMapping;

/**
 * DAO AttributeMappingEntity implementation
 */
public class AttributeMappingEntityDaoImpl extends com.soffid.iam.model.AttributeMappingEntityDaoBase
{

	@Override
    public void toAttributeMapping(com.soffid.iam.model.AttributeMappingEntity source, AttributeMapping target) {
		super.toAttributeMapping(source, target);
		
		target.setObjectId(source.getObject().getId());
	}

	@Override
    public void attributeMappingToEntity(AttributeMapping source, com.soffid.iam.model.AttributeMappingEntity target, boolean copyIfNull) {
		super.attributeMappingToEntity(source, target, copyIfNull);
		target.setObject(getObjectMappingEntityDao().load(source.getObjectId()));
	}
	
	
}
