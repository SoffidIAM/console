package com.soffid.iam.model;

import java.util.Collection;

import com.soffid.iam.api.AttributeTranslation;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;

@Entity(table="SC_ATTTRA")
@Depends({
	AttributeTranslation.class
})
public class AttributeTranslationEntity {
	@Column (name="ATT_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	
	@Column (name="ATT_DOMAIN", length=450)
	public java.lang.String domain;
	
	@Column (name="ATT_COLUMN1", length=450)
	public java.lang.String column1;

	@Column (name="ATT_COLUMN2", length=450)
	@Nullable
	public java.lang.String column2;

	@Column (name="ATT_COLUMN3", length=450)
	@Nullable
	public java.lang.String column3;

	@Column (name="ATT_COLUMN4", length=450)
	@Nullable
	public java.lang.String column4;

	@Column (name="ATT_COLUMN5", length=450)
	@Nullable
	public java.lang.String column5;
	
	@Column (name="ATT_TEN_ID")
	public TenantEntity tenant;

	@DaoFinder("select ate from com.soffid.iam.model.AttributeTranslationEntity as ate "
			+ "where (ate.domain like :domain or :domain is null) and "
			+ "(ate.column1 like :column1 or :column1 is null) and"
			+ "(ate.column2 like :column2 or :column2 is null) and\n"
			+ "ate.tenant.id = :tenantId "
			+ "order by ate.domain, ate.column1")
	public Collection<AttributeTranslationEntity> findByExample (@Nullable String domain, @Nullable String column1, @Nullable String column2)
	{
		return null;
	}

	public Collection<AttributeTranslationEntity> findByColumn1 (String domain, String column1)
	{
		return null;
	}

	public Collection<AttributeTranslationEntity> findByColumn2 (String domain, String column2)
	{
		return null;
	}
}

@Index (entity=AttributeTranslationEntity.class, name="SC_ATTTRA_COL1_NDX", unique=false, columns = { "ATT_TEN_ID", "ATT_DOMAIN", "ATT_COLUMN1" })
class AttributeTranslationColumn1Index 
{
	
}

@Index (entity=AttributeTranslationEntity.class, name="SC_ATTTRA_COL2_NDX", unique=false, columns = { "ATT_TEN_ID", "ATT_DOMAIN", "ATT_COLUMN2" })
class AttributeTranslationColumn2Index 
{
	
}
