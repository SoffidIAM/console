package com.soffid.iam.common;

public interface TransactionalTask {
	Object run() throws Exception;
}
