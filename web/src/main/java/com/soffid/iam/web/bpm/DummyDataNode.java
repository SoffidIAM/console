package com.soffid.iam.web.bpm;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.SimpleDataNode;

public class DummyDataNode extends SimpleDataNode {
	
	public DummyDataNode(DataContext ctx) {
		super(ctx);
	}

	protected void doInsert () throws Exception
	{
		// Nothing to do
	}

	protected void doUpdate () throws Exception
	{
		// Nothing to do
	}

	
	protected void doDelete () throws Exception
	{
		// Nothing to do
	}

}
