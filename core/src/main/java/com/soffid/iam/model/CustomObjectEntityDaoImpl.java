package com.soffid.iam.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.CustomObject;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.Security;

public class CustomObjectEntityDaoImpl extends CustomObjectEntityDaoBase {

	@Override
	public void toCustomObject(CustomObjectEntity source, CustomObject target) {
		super.toCustomObject(source, target);
		target.setType(source.getType().getName());
		target.setAttributes(new HashMap<String, Object>());
		Map<String, Object> attributes = target.getAttributes();
		for (CustomObjectAttributeEntity att : source.getAttributes()) {
			if (att.getMetadata().getMultiValued() != null && att.getMetadata().getMultiValued().booleanValue())
			{
				LinkedList<Object> r = (LinkedList<Object>) attributes.get(att.getMetadata().getName());
				if (r == null)
				{
					r = new LinkedList<Object>();
					attributes.put(att.getMetadata().getName(), r);
				}
				r.add(att.getObjectValue());
			}
			else
			{
				attributes.put(att.getMetadata().getName(),att.getObjectValue());
			}
		}
		for (Object o: attributes.values())
		{
			if (o != null && o instanceof List) Collections.sort((List) o);
		}
	}

	@Override
	public void customObjectToEntity(CustomObject source, CustomObjectEntity target, boolean copyIfNull) {
		super.customObjectToEntity(source, target, copyIfNull);
		if (target.getType() == null ||  ! target.getType().getName().equals(source.getType()))
		{
			CustomObjectTypeEntity t = getCustomObjectTypeEntityDao().findByName(source.getType());
			if ( t == null)
				throw new RuntimeException("Unknown object type "+source.getType());
			target.setType(t);
		}
	}

	@Override
	public Collection<CustomObjectEntity> findByText(CriteriaSearchConfiguration criteria, String objectType,
			String text) {
		String[] split = text.trim().split(" +");
		Parameter[] params = new Parameter[split.length + 2];
		
		StringBuffer sb = new StringBuffer("select u "
				+ "from com.soffid.iam.model.CustomObjectEntity as u "
				+ "where u.type.name=:objectType and u.type.tenant.id = :tenantId");
		params[0] = new Parameter("tenantId", Security.getCurrentTenantId());
		params[1] = new Parameter("objectType", objectType);
		for (int i = 0; i < split.length; i++)
		{
			sb.append(" and ");
			params[i+2] = new Parameter("param"+i, "%"+split[i].toUpperCase()+"%");
			sb.append("(upper(u.name) like :param")
				.append(i)
				.append(" or upper(u.description) like :param")
				.append(i)
				.append(")");
		}
		return query(sb.toString(), params);
	}

}
