package com.soffid.iam.model;

import java.util.Date;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_LUNIND")
public class LuceneIndexEntity {
	@Column(name = "LIP_ID")
	@Nullable @Identifier Long id;
	
	@Column(name = "LIP_NAME")
	String name;
	
	@Column(name = "LIP_TEN_ID")
	TenantEntity tenant;
	
	@Column(name = "LIP_TIMSTA")
	Date timestamp;
	
	@DaoFinder
	LuceneIndexEntity findByName(String name) {return null;}
}


