package com.soffid.iam.service;

import java.util.Collection;

import roles.AttributeTranslationCreate;
import roles.AttributeTranslationDelete;
import roles.AttributeTranslationQuery;
import roles.AttributeTranslationUpdate;

import com.soffid.iam.api.AttributeTranslation;
import com.soffid.iam.model.AttributeTranslationEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

@Service 
@Depends({AttributeTranslationEntity.class})
public class AttributeTranslationService {
	@Operation(grantees={AttributeTranslationCreate.class}) 
	public AttributeTranslation create (AttributeTranslation att) 
	{
		return null;
	}

	@Operation(grantees={AttributeTranslationUpdate.class}) 
	public AttributeTranslation update (AttributeTranslation att) 
	{
		return null;
	}

	@Operation(grantees={AttributeTranslationDelete.class}) 
	public void delete (AttributeTranslation att) 
	{
	}

	@Operation(grantees={AttributeTranslationQuery.class}) 
	public Collection<AttributeTranslation> findByExample (@Nullable String domain, @Nullable String column1, @Nullable String column2)
	{
		return null;
	}

	@Operation(grantees={AttributeTranslationQuery.class}) 
	public Collection<AttributeTranslation> findByColumn1 (String domain, String column1)
	{
		return null;
	}

	@Operation(grantees={AttributeTranslationQuery.class}) 
	public Collection<AttributeTranslation> findByColumn2 (String domain, String column2)
	{
		return null;
	}
	
	@Operation(grantees={AttributeTranslationQuery.class}) 
	public Collection<String> findDomains ()
	{
		return null;
	}
	

}

