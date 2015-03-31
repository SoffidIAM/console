package com.soffid.scimquery.expr;

import java.util.LinkedList;
import java.util.List;

import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;


public class AndExpression extends AbstractExpression {
	List<AbstractExpression> members = new LinkedList<AbstractExpression>();
	
	public List<AbstractExpression> getMembers() {
		return members;
	}

	public void setMembers(List<AbstractExpression> members) {
		this.members = members;
	}

	@Override
	public void generateHSQLString(HQLQuery query) throws EvalException {
		boolean first = true;
		for (AbstractExpression member: members)
		{
			if (members.size() == 1)
				;// Nothing to do
			else if (first) 
				query.getWhereString().append('(');
			else
				query.getWhereString().append(" and ");
			member.generateHSQLString(query);
			first = false;
		}
		if (members.size() > 1) 
			query.getWhereString().append(')');
	}

	@Override
	public boolean evaluate(Object object) throws EvalException {
		for ( AbstractExpression e: members)
		{
			if (! e.evaluate(object))
				return false;
		}
		return true;
	}

}
