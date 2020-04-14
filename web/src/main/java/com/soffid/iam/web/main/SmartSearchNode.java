package com.soffid.iam.web.main;

import java.util.Collection;

import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.Finder;


public class SmartSearchNode extends DataNode {

	public SmartSearchNode(DataContext ctx) {
		super(ctx);
		addFinder("data", new Finder() {
			@Override
			public Object newInstance() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection find() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		}, SmartSearchEntryNode.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doInsert() throws Exception {
	}

	@Override
	protected void doUpdate() throws Exception {
	}

	@Override
	protected void doDelete() throws Exception {
	}

}
