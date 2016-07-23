package com.soffid.iam.service.impl.bshjail;

import bsh.BshClassManager;
import bsh.NameSpace;

public class BshJailNamespace extends NameSpace {

	public BshJailNamespace(NameSpace parent, String name) {
		super(parent, name);
	}

	public BshJailNamespace(BshClassManager classManager, String name) {
		super(classManager, name);
	}

	public BshJailNamespace(NameSpace parent, BshClassManager classManager,
			String name) {
		super(parent, classManager, name);
	}

}
