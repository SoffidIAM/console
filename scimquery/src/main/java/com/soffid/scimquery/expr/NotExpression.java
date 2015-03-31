package com.soffid.scimquery.expr;

import java.util.LinkedList;
import java.util.List;

import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;


public class NotExpression extends AbstractExpression {
	AbstractExpression subExpression = null;
	
	public AbstractExpression getSubExpression() {
		return subExpression;
	}

	public void setSubExpression(AbstractExpression subExpression) {
		this.subExpression = subExpression;
	}

	@Override
	public void generateHSQLString(HQLQuery query) throws EvalException {
		boolean first = true;
		if (subExpression != null)
		{
			query.getWhereString().append("( not ");
			subExpression.generateHSQLString(query);
			query.getWhereString().append(')');
		}
	}

	@Override
	public boolean evaluate(Object object) throws EvalException {
		if (subExpression == null)
			return false;
		else
			return ! subExpression.evaluate(object);
	}

}
