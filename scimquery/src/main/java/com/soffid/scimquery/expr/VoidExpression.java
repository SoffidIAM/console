package com.soffid.scimquery.expr;

import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;


public class VoidExpression extends AbstractExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void generateHSQLString(HQLQuery query) throws EvalException {
	}

	@Override
	public boolean evaluate(Object object) throws EvalException {
		return true;
	}

}
