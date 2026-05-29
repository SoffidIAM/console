package com.soffid.iam.model;

public class LuceneIndexPartEntityDaoImpl extends LuceneIndexPartEntityDaoBase {

	@Override
	public void create(LuceneIndexPartEntity entity) {
		super.create(entity);
		getSession().evict(entity);
	}

}
