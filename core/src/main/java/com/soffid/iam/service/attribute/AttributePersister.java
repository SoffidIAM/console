package com.soffid.iam.service.attribute;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.MetaDataEntityDao;
import com.soffid.iam.service.impl.AttributeValidationService;

import es.caib.seycon.ng.exception.InternalErrorException;

public abstract class AttributePersister<ENTITY,ATTRIBUTEENTITY> {
	public boolean updateAttributes (Map<String,Object> attributes, ENTITY entity) throws InternalErrorException
	{
		boolean anyChange = false;
		if (entity != null)
		{
			if (attributes == null)
				attributes = (new HashMap<String, Object>());
			
			LinkedList<ATTRIBUTEENTITY> entities = new LinkedList<ATTRIBUTEENTITY> (getEntityAttributes(entity));
			HashSet<String> keys = new HashSet<String>();
			for (String key: attributes.keySet() )
			{
				for (MetaDataEntity metadata: getMetaDataEntityDao().findByObjectTypeAndName(getMetadataScope(), key))
				{
					Object v = attributes.get(key);
					if (v == null)
					{
						// Do nothing
					}
					else if (v instanceof Collection)
					{
						Collection l = (Collection) v;
						for (Object o: (Collection) v)
						{
							if (o != null)
							{
								if (updateAttribute(entity, entities, key, metadata, o))
									anyChange = true;
							}
						}
					}
					else
					{
						if (updateAttribute(entity, entities, key, metadata, v))
							anyChange = true;
					}
				}
			}

			if (!entities.isEmpty())
			{
				getEntityAttributes(entity).removeAll(entities);
				removeAttributes(entities);
				anyChange = true;
			}
			updateEntity(entity);

			Collection<MetaDataEntity> md = getMetaDataEntityDao().findByObjectTypeAndName(getMetadataScope(), null);
			
			for ( MetaDataEntity m: md) if ( m.getBuiltin() == null || ! m.getBuiltin().booleanValue() )
			{
				Object o = attributes.get(m.getName());
				if ( o == null || "".equals(o))
				{
					if (m.getRequired() != null && m.getRequired().booleanValue())
						throw new InternalErrorException(String.format("Missing attribute %s", m.getLabel()));
				} else {
					if (m.getUnique() != null && m.getUnique().booleanValue())
					{
						Collection<String> l = (Collection<String>) ( o instanceof Collection? (Collection) o: Collections.singletonList(o) );
						for (String v: l)
						{
							List<ATTRIBUTEENTITY> p = findAttributeEntityByNameAndValue(m, v);
							if (p.size() > 1)
								throw new InternalErrorException(String.format("Already exists an object with %s %s",
										m.getLabel(), v));
						}
					}
				}
			}
		}
		return anyChange;
	}

	private boolean updateAttribute(ENTITY entity, LinkedList<ATTRIBUTEENTITY> attributes, String key,
			MetaDataEntity metadata, Object value) throws InternalErrorException {
		ATTRIBUTEENTITY aae = findAttributeEntity(attributes, key, value);
		if (aae == null)
		{
			getAttributeValidationService().validate(metadata.getType(), metadata.getDataObjectType(), value);
			aae = createNewAttribute(entity, metadata, value);
			getEntityAttributes(entity).add(aae);
			return true;
		}
		else
		{
			attributes.remove(aae);
			return false;
		}
	}
	
	protected abstract List<ATTRIBUTEENTITY> findAttributeEntityByNameAndValue(MetaDataEntity m, String v) ;
	protected abstract void updateEntity(ENTITY entity);
	protected abstract void removeAttributes(Collection<ATTRIBUTEENTITY> entities);	
	protected abstract String getMetadataScope() ;
	protected abstract Collection<ATTRIBUTEENTITY> getEntityAttributes(ENTITY entity) ;
	protected abstract ATTRIBUTEENTITY createNewAttribute(ENTITY entity, MetaDataEntity metadata, Object value) ;
	protected abstract ATTRIBUTEENTITY findAttributeEntity(LinkedList<ATTRIBUTEENTITY> entities, String key,
			Object o) ;
	protected abstract MetaDataEntityDao getMetaDataEntityDao();
	protected abstract AttributeValidationService getAttributeValidationService();


}
