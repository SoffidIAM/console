package com.soffid.iam.model.hibernate;

import org.hibernate.transaction.JNDITransactionManagerLookup;

public class TomeeTransactionManagerLookup extends JNDITransactionManagerLookup {

	@Override
	public String getUserTransactionName() {
		return "java:/comp/UserTransaction";
	}

	@Override
	protected String getName() {
		return "java:/comp/TransactionManager";
	}

}
