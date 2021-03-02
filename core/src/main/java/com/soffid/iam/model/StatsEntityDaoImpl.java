package com.soffid.iam.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.Hibernate;

public class StatsEntityDaoImpl extends StatsEntityDaoBase {

	@Override
	protected void handlePurge(int days) throws Exception {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -days);

		String date = new SimpleDateFormat("yyyyMMddHHmm").format(c.getTime());

		// Warning. Purges data from all tenants
		getSession(false)
			.createQuery("delete from com.soffid.iam.model.StatsEntityImpl where date < :until") 
			.setParameter("until", date, Hibernate.STRING)
			.executeUpdate();
	}

}
