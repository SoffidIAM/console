package com.soffid.iam.model;

import java.util.Collection;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table="SC_LUINPA")
public class LuceneIndexPartEntity {
	@Column(name = "LIP_ID")
	@Nullable @Identifier Long id;
	
	@Column(name = "LIP_LIN_ID", reverseAttribute = "parts")
	LuceneIndexEntity index;
	
	@Column(name = "LIP_NAME", length = 256)
	String name;

	@Column(name = "LIP_ORDER")
	int order;
	
	@Column(name = "LIP_TEN_ID")
	TenantEntity tenant;
	
	@Column(name = "LIP_DATA", length = 256000)
	@Nullable byte[] data;
	
	@DaoFinder("select p from com.soffid.iam.model.LuceneIndexPartEntity as p "
			+ "where p.index.id = :index "
			+ "order by name, order")
	Collection<LuceneIndexPartEntity> findByIndex( Long index) {return null;}
}
