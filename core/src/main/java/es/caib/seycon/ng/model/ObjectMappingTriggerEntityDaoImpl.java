package es.caib.seycon.ng.model;

import es.caib.seycon.ng.comu.AttributeMapping;
import es.caib.seycon.ng.comu.ObjectMappingTrigger;

public class ObjectMappingTriggerEntityDaoImpl extends
		ObjectMappingTriggerEntityDaoBase {

	@Override
	public void toObjectMappingTrigger(ObjectMappingTriggerEntity source,
			ObjectMappingTrigger target) {
		super.toObjectMappingTrigger(source, target);
		target.setObjectId(source.getObject().getId());
	}

	@Override
	public void objectMappingTriggerToEntity(ObjectMappingTrigger source,
			ObjectMappingTriggerEntity target, boolean copyIfNull) {
		super.objectMappingTriggerToEntity(source, target, copyIfNull);
		target.setObject(getObjectMappingEntityDao().load(source.getObjectId()));
	}

}
