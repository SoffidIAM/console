package com.soffid.iam.web.launcher;

import java.util.List;

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;

public abstract class ApplicationLauncher {
	public abstract void open( AccessTree element, AccessTreeExecution exection, List<com.soffid.iam.api.Account> accounts, boolean directLink )
		throws Exception;
}
