package com.soffid.iam.init.ejb;

import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton(name="ConfigBean")
@Startup
@javax.ejb.TransactionManagement(value=javax.ejb.TransactionManagementType.CONTAINER)
@javax.ejb.TransactionAttribute(value=javax.ejb.TransactionAttributeType.SUPPORTS)
public class ApplicationBean {
	public void test() {
		
	}
}
