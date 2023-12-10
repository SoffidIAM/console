package com.soffid.iam.model;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;

public class LuceneIndexEntityDaoImpl extends LuceneIndexEntityDaoBase {

	@Override
	public LuceneIndexEntity findByName(String name) {
		LuceneIndexEntity e = super.findByName(name);
		if (e == null)
			return e;
		if (e != null) {
			getSession().evict(e);
			e = super.findByName(name);
		}
		return e;
	}

	@Override
	protected void handleLock(LuceneIndexEntity entity) throws Exception {
		getSession().refresh(entity, LockMode.UPGRADE);
	}

	@Override
	protected void handleRefresh(LuceneIndexEntity entity) throws Exception {
		getSession().refresh(entity);
	}

}
