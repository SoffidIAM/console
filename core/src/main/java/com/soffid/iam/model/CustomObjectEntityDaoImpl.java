package com.soffid.iam.model;

import java.util.Collection;
import java.util.HashMap;

import com.soffid.iam.api.CustomObject;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.Security;

public class CustomObjectEntityDaoImpl extends CustomObjectEntityDaoBase {

	@Override
	public void toCustomObject(CustomObjectEntity source, CustomObject target) {
		super.toCustomObject(source, target);
		target.setType(source.getType().getName());
		target.setAttributes(new HashMap<String, Object>());
		for ( CustomObjectAttributeEntity att: source.getAttributes())
		{
			target.getAttributes().put(att.getMetadata().getName(), att.getObjectValue());
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
				+ "where u.type.name=:objectType, u.type.tenant.id = :tenantId");
		params[0] = new Parameter("tenantId", Security.getCurrentTenantId());
		params[1] = new Parameter("objectType", objectType);
		for (int i = 0; i < split.length; i++)
		{
			sb.append(" and ");
			params[i+2] = new Parameter("param"+i, "%"+split[i].toUpperCase()+"%");
			sb.append("(upper(u.userName) like :param")
				.append(i)
				.append(" or upper(u.firstName) like :param")
				.append(i)
				.append(" or upper(u.lastName) like :param")
				.append(i)
				.append(" or upper(u.middleName) like :param")
				.append(i)
				.append(")");
		}
		return query(sb.toString(), params);
	}

}
