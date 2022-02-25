package com.soffid.scimquery.expr;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;

import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;


public class OrderByExpression extends AbstractExpression {
	AbstractExpression expression;
	List<String> orderBy;

	@Override
	public void generateHSQLString(HQLQuery query) throws EvalException {
		boolean first = true;
		
		expression.generateHSQLString(query);
		if (orderBy != null) {
			LinkedList<String> o = new LinkedList<String>();
			boolean attName = false;
			for (String s: orderBy) {
				try {
					if (attName && (s.equalsIgnoreCase("asc") || s.equalsIgnoreCase("desc"))) {
						String last = o.getLast();
						o.removeLast();
						o.add (last+" "+s);
						attName = false;
					} else {
						attName = true;
						EvaluationContext ctx = generateHQLStringReference(query, s);
						String p = ctx.objectName;
						if (p.startsWith(ROOT_OBJECT_NAME2+".")) {
							p = ROOT_OBJECT_NAME+"."+p.substring(ROOT_OBJECT_NAME2.length()+1);
						}
							
						o.add(p);
					}
				} catch (Exception e) {
					throw new EvalException("Error evaluating attribute "+s, e);
				}
			}
			query.setOrderBy(o);
		}
	}

	@Override
	public boolean evaluate(Object object) throws EvalException {
		return expression.evaluate(object);
	}

	public List<String> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(List<String> orderBy) {
		this.orderBy = orderBy;
	}

	public AbstractExpression getExpression() {
		return expression;
	}

	public void setExpression(AbstractExpression expression) {
		this.expression = expression;
	}

}
