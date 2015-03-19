package com.soffid.iam.script;

import bsh.BshClassManager;
import bsh.NameSpace;

public class BshNamespace extends NameSpace {

	public BshNamespace(NameSpace parent, String name) {
		super(parent, name);
	}

	public BshNamespace(BshClassManager classManager, String name) {
		super(classManager, name);
	}

	public BshNamespace(NameSpace parent, BshClassManager classManager,
			String name) {
		super(parent, classManager, name);
	}

}
