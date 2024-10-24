package com.soffid.scimquery.expr;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;

public class ComparisonExpression extends AbstractExpression {

	private static final long serialVersionUID = 5932617296223973952L;

	String attribute;
	
	String operator;
	
	Object value;

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}


	@Override
	public boolean evaluate(Object object) throws EvalException {
		Object v;
		try {
			v = getReference(object, attribute);
		} catch (EvalException e) {
			return true; // Hibernate attribute
		}
		if (v instanceof Collection<?>) 
		{
			for (Object v2: (Collection<?>)v)
			{
				if (v2 != null && compare (v2))
					return true;
			}
			return false;
		}
		else
			return v != null && compare(v);
	}

	/**
	 * Evaluates a comparison
	 * 
	 * @param v2 the bean value
	 * @return true if the been value (left side) matches the right side value
	 * @throws EvalException
	 */
	private boolean compare(Object v2) throws EvalException {
		if (v2 == null)
			return false;
		else if (v2 instanceof Long)
			return compareLong ((Long) v2, Long.parseLong(value.toString()));
		else if (v2 instanceof Double)
			return compareDouble ((Double) v2, Double.parseDouble(value.toString()));
		else if (v2 instanceof Date)
		{
			DateTimeFormatter df = ISODateTimeFormat.dateTimeParser();
			return compareDate ((Date) v2, df.parseDateTime(value.toString()).toDate());
		}
		else if (v2 instanceof Calendar)
		{
			DateTimeFormatter df = ISODateTimeFormat.dateTimeParser();
			return compareDate (((Calendar) v2).getTime(), df.parseDateTime(value.toString()).toDate());
		}
		else if (v2 instanceof Boolean)
			return compareBoolean ((Boolean) v2, Boolean.parseBoolean(value.toString()));
		else
			return compareString (v2.toString(), value == null ? null: value.toString());
	}
	
	private boolean compareString(String attributeValue, String matchValue) throws EvalException 
	{
		if ("eq".equalsIgnoreCase(operator))
		{
			return attributeValue.equals((String)matchValue);
		}
		if ("eq_ci".equalsIgnoreCase(operator))
		{
			return attributeValue.equalsIgnoreCase((String)matchValue);
		}
		if ("gt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) > 0;
		}
		if ("lt".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().compareTo(matchValue) < 0;
		}
		if ("ge".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().compareTo(matchValue) >= 0;
		}
		if ("le".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().compareTo(matchValue) <= 0;
		}
		if ("ne".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().compareTo(matchValue) != 0;
		}
		if ("sw".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().toLowerCase().startsWith(matchValue.toLowerCase());
		}
		if ("ew".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().toLowerCase().endsWith(matchValue.toLowerCase());
		}
		if ("co".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().toLowerCase().contains(matchValue.toLowerCase());
		}
		if ("pr".equalsIgnoreCase(operator))
		{
			return true;
		}
		
		throw new EvalException("Invalid operator "+operator, null);
	}

	private boolean compareLong(Long attributeValue, Long matchValue) throws EvalException 
	{
		if ("eq".equalsIgnoreCase(operator) || "eq_ci".equalsIgnoreCase(operator))
		{
			return attributeValue.equals(matchValue);
		}
		if ("gt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) > 0;
		}
		if ("lt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) < 0;
		}
		if ("ge".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) >= 0;
		}
		if ("le".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) <= 0;
		}
		if ("ne".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) != 0;
		}
		if ("sw".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().startsWith(matchValue.toString());
		}
		if ("ew".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().endsWith(matchValue.toString());
		}
		if ("co".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().contains(matchValue.toString());
		}
		if ("pr".equalsIgnoreCase(operator))
		{
			return true;
		}
		
		throw new EvalException("Invalid operator "+operator, null);
	}

	private boolean compareDouble(Double attributeValue, Double matchValue) throws EvalException 
	{
		if ("eq".equalsIgnoreCase(operator) || "eq_ci".equalsIgnoreCase(operator))
		{
			return attributeValue.equals(matchValue);
		}
		if ("gt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) > 0;
		}
		if ("lt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) < 0;
		}
		if ("ge".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) >= 0;
		}
		if ("le".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) <= 0;
		}
		if ("ne".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) != 0;
		}
		if ("sw".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().startsWith(matchValue.toString());
		}
		if ("ew".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().endsWith(matchValue.toString());
		}
		if ("co".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().contains(matchValue.toString());
		}
		if ("pr".equalsIgnoreCase(operator))
		{
			return true;
		}
		
		throw new EvalException("Invalid operator "+operator, null);
	}

	private boolean compareDate(Date attributeValue, Date matchValue) throws EvalException 
	{
		if ("eq".equalsIgnoreCase(operator) || "eq_ci".equalsIgnoreCase(operator))
		{
			return attributeValue.equals(matchValue);
		}
		if ("gt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) > 0;
		}
		if ("lt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) < 0;
		}
		if ("ge".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) >= 0;
		}
		if ("le".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) <= 0;
		}
		if ("ne".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) != 0;
		}
		if ("sw".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().startsWith(matchValue.toString());
		}
		if ("ew".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().endsWith(matchValue.toString());
		}
		if ("co".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().contains(matchValue.toString());
		}
		if ("pr".equalsIgnoreCase(operator))
		{
			return true;
		}
		
		throw new EvalException("Invalid operator "+operator, null);
	}

	private boolean compareBoolean(Boolean attributeValue, Boolean matchValue) throws EvalException 
	{
		if ("eq".equalsIgnoreCase(operator) || "eq_ci".equalsIgnoreCase(operator))
		{
			return attributeValue.equals(matchValue);
		}
		if ("gt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) > 0;
		}
		if ("lt".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) < 0;
		}
		if ("ge".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) >= 0;
		}
		if ("le".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) <= 0;
		}
		if ("ne".equalsIgnoreCase(operator))
		{
			return attributeValue.compareTo(matchValue) != 0;
		}
		if ("sw".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().startsWith(matchValue.toString());
		}
		if ("ew".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().endsWith(matchValue.toString());
		}
		if ("co".equalsIgnoreCase(operator))
		{
			return attributeValue.toString().contains(matchValue.toString());
		}
		if ("pr".equalsIgnoreCase(operator))
		{
			return true;
		}
		
		throw new EvalException("Invalid operator "+operator, null);
	}

	@Override
	public void generateHSQLString(HQLQuery query) throws EvalException {
		try 
		{
			if (query.isNegativeExpression() && "pr".equalsIgnoreCase(operator)) 
			{
				EvaluationContext ctx = generateHQLNotPrStringReference(query, attribute);				
				if (ctx.objectCondition != null)
					query.getWhereString().append("(").append(ctx.objectCondition);
				query.getWhereString().append(ctx.objectName);
				query.getWhereString().append(" is not null");
				if (ctx.closeCondition != null)
					query.getWhereString().append(ctx.closeCondition);
				if (ctx.objectCondition != null)
					query.getWhereString().append(")");
			}
			else
			{
				EvaluationContext ctx = generateHQLStringReference(query, attribute);
				if (ctx.nonHQLAttributeUsed)
				{
					query.setNonHQLAttributeUsed(true);
					if (query.isNegativeExpression())
						query.getWhereString().append(" 0=1 ");
					else
						query.getWhereString().append(" 0=0 ");
				}
				else
				{
					if (ctx.objectCondition != null)
						query.getWhereString().append("(").append(ctx.objectCondition);
					
					if ("eq".equalsIgnoreCase(operator))
					{
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(" =  ");
						addParameter (query, value, ctx.hibernateClass);
					}
					else if ("eq_ci".equalsIgnoreCase(operator))
					{
						query.getWhereString().append("upper(");
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(") =  ");
						addParameter (query, value.toString().toUpperCase(), ctx.hibernateClass);
					}
					else if ("gt".equalsIgnoreCase(operator))
					{
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(" > ");
						addParameter (query, value, ctx.hibernateClass);
					}
					else if ("lt".equalsIgnoreCase(operator))
					{
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(" < ");
						addParameter (query, value, ctx.hibernateClass);
					}
					else if ("ge".equalsIgnoreCase(operator))
					{
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(" >= ");
						addParameter (query, value, ctx.hibernateClass);
					}
					else if ("le".equalsIgnoreCase(operator))
					{
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(" <= ");
						addParameter (query, value, ctx.hibernateClass);
					}
					else if ("ne".equalsIgnoreCase(operator))
					{
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(" != ");
						addParameter (query, value, ctx.hibernateClass);
					}
					else if ("sw".equalsIgnoreCase(operator))
					{
						query.getWhereString().append("upper(");
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(") like ");
						addParameter (query, escapeSql(value.toString().toUpperCase())+"%", String.class);
						query.getWhereString().append(" escape ");
						addParameter (query, "\\", String.class);
					}
					else if ("ew".equalsIgnoreCase(operator))
					{
						query.getWhereString().append("upper(");
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(") like ");
						addParameter (query, "%"+escapeSql(value.toString().toUpperCase()), String.class);
						query.getWhereString().append(" escape ");
						addParameter (query, "\\", String.class);
					}
					else if ("co".equalsIgnoreCase(operator))
					{
						query.getWhereString().append("upper(");
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(") like ");
						addParameter (query, "%"+escapeSql(value.toString().toUpperCase())+"%", String.class);
						query.getWhereString().append(" escape ");
						addParameter (query, "\\", String.class);
					}
					else if ("pr".equalsIgnoreCase(operator))
					{
						query.getWhereString().append(ctx.objectName);
						query.getWhereString().append(" is not null ");
					}
					if (ctx.objectCondition != null)
						query.getWhereString().append(")");
				}
			}
		} catch (Exception e) {
			throw new EvalException("Error evaluating attribute "+attribute, e);
		}
	}


	private String escapeSql(String upperCase) {
		return upperCase.replaceAll("\\\\", "\\\\\\\\").replaceAll("_", "\\\\_").replaceAll("%", "\\\\%");
	}

	private void addParameter(HQLQuery query, Object value2, Class<?> hibernateClass) {
		int i = query.getNextParameter();
		String param  = "p"+i;
		if (Date.class.isAssignableFrom(hibernateClass) || 
				java.sql.Date.class.isAssignableFrom(hibernateClass))
		{
			if (value2 instanceof Long)
			{
				query.getParameters().put(param, new Date(((Long) value2).longValue()));
			}
			else
			{
				DateTimeFormatter df = ISODateTimeFormat.dateTimeParser();
				Date d = df.parseDateTime(value2.toString()).toDate();
				query.getParameters().put(param, d);
			}
		}
		else if (Boolean.class.isAssignableFrom(hibernateClass))
		{
			if (value2 instanceof Boolean)
				query.getParameters().put(param, value2);
			else
				query.getParameters().put(param, Boolean.parseBoolean(value2.toString()));
		}
		else if (Long.class.isAssignableFrom(hibernateClass))
		{
			if (value2 instanceof Long)
				query.getParameters().put(param, value2);
			else
				query.getParameters().put(param, Long.parseLong(value2.toString()));
		}
		else if (Integer.class.isAssignableFrom(hibernateClass))
		{
			if (value2 instanceof Integer)
				query.getParameters().put(param, value2);
			else
				query.getParameters().put(param, Integer.parseInt(value2.toString()));
		}
		else if (String.class.isAssignableFrom(hibernateClass) && value != null && value instanceof Boolean)
		{
			if (Boolean.TRUE.equals(value2))
				query.getParameters().put(param, "S");
			else
				query.getParameters().put(param, "N");
		}
		else
		{
			query.getParameters().put(param, value2);
		}
		query.getWhereString().append(":").append(param);
	}

	private void addParameter(HQLQuery query, Object value2, String hibernateClass) throws EvalException {
		try {
			if ("boolean".equals(hibernateClass))
				addParameter(query, value2, Boolean.class);
			else if ("long".equals(hibernateClass))
				addParameter(query, value2, Long.class);
			else if ("int".equals(hibernateClass))
				addParameter(query, value2, Integer.class);
			else
				addParameter(query, value2, Class.forName(hibernateClass));
		} catch (ClassNotFoundException e) {
			throw new EvalException("Unknown class "+hibernateClass, e);
		}
	}
}
