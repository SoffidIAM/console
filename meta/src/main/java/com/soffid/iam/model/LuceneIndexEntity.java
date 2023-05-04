package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.DaoOperation;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;

@Entity(table = "SC_LUNIND")
public class LuceneIndexEntity {
	@Column(name = "LIP_ID")
	@Nullable @Identifier Long id;
	
	@Column(name = "LIP_NAME")
	String name;
	
	@Column(name = "LIP_TEN_ID")
	TenantEntity tenant;
	
	@Column(name = "LIP_TIMSTA")
	long timestamp;
	
	@DaoFinder
	LuceneIndexEntity findByName(String name) {return null;}
	
	@DaoOperation
	void lock(LuceneIndexEntity entity) {}

	@DaoOperation
	void refresh(LuceneIndexEntity entity) {}
}


@Index(entity = LuceneIndexEntity.class ,name = "SC_LUNIND_UK", columns = {"LIP_TEN_ID", "LIP_NAME"}, unique = true)
class LucenIndexUniqueKey {}
