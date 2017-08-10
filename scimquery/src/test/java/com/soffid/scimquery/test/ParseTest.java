package com.soffid.scimquery.test;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;

import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.conf.AttributeConfig;
import com.soffid.scimquery.conf.ClassConfig;
import com.soffid.scimquery.conf.Configuration;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.test.entity.AttributeEntity;
import com.soffid.scimquery.test.entity.AttributeValueEntity;
import com.soffid.scimquery.test.vo.Account;
import com.soffid.scimquery.test.vo.User;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import junit.framework.TestCase;

public class ParseTest extends TestCase {

	public void testConfig () throws UnsupportedEncodingException, JSONException, ClassNotFoundException
	{
		ClassConfig config = Configuration.getClassConfig(User.class);
		assertNotNull(config);
		System.out.println ("config for "+config.getClass().getName());
		System.out.println ("Hibernate class = "+config.getHibernateClass());
		for (String key: config.getAttributes().keySet())
		{
			System.out.println (key+" => "+config.getAttributes().get(key).getHibernateColumn());
		}
	}
	
	
	public void testParse () throws ParseException, EvalException 
	{
		Account acc = new Account();
		
		acc.setName("name");
		acc.setDescription("user");
		
		AbstractExpression e = ExpressionParser.parse("name eq \"name\"");
		
		assertTrue (e.evaluate(acc));
		

		e = ExpressionParser.parse("name eq \"name2\"");
		
		assertFalse (e.evaluate(acc));

		ExpressionParser.parse("field eq \"value\" and f2 eq \"value2\"");

		ExpressionParser.parse("((field eq \"value\") and (f2 eq \"value2\"))");
	}
	
	public void testHQL () throws UnsupportedEncodingException, ClassNotFoundException, EvalException, JSONException, ParseException, TokenMgrError
	{
		expressionTester("name eq \"name\"", User.class);

		expressionTester("name co \"x\" and description gt 1", Account.class);

		expressionTester("name co \"x\" and description gt 1 or user.name eq \"abc123\"", Account.class);

		expressionTester("owner eq \"abc123\"", Account.class);

	}


	private void expressionTester(String expr, Class cl) throws ParseException, TokenMgrError,
			EvalException, UnsupportedEncodingException,
			ClassNotFoundException, JSONException {
		System.out.println ("=======================");
		System.out.println (expr);
		System.out.println ();

		try 
		{
			ExpressionParser.parse(expr)
				.generateHSQLString(cl)
				.dump(System.out);
		}
		catch (EvalException e) {
			e.printStackTrace(System.out);
			throw e;
		}
	}

	/*
	 * Attributes special join
	 * 
	 * Account.class:
	 * 
	 *  name eq "abc" => select o from AccountEntity o where o.name = "abc"
	 *  user.name eq "abc" => select o from AccountEntity o join o.user as u where u.name = "abc"
	 *  attributes.employee eq "abc" =>
	 *  			select o from AccountEntity o 
	 *  			join o.attributes as o1 
	 *              where o1.value = "abc" and o1.attribute.name="employee" 
	 */
	public void testAttribute () throws ParseException, EvalException, UnsupportedEncodingException, ClassNotFoundException, TokenMgrError, JSONException 
	{
		ClassConfig config = new ClassConfig();
		config.setClazz(AttributeValueEntity.class.getCanonicalName());
		config.setHibernateClass(AttributeValueEntity.class.getCanonicalName());
		AttributeConfig attributeConfig = new AttributeConfig();
		attributeConfig.setVirtualAttribute(true);
		attributeConfig.setVirtualAttributeValue("numericValue");
		attributeConfig.setVirtualAttributeName("attribute.name");
		attributeConfig.setAttributeName("employee");
		config.getAttributes().put("employee", attributeConfig );

		attributeConfig = new AttributeConfig();
		attributeConfig.setVirtualAttribute(true);
		attributeConfig.setVirtualAttributeValue("value");
		attributeConfig.setVirtualAttributeName("attribute.name");
		config.setDefaultVirtualAttribute(attributeConfig);

		Configuration.registerClass(config);
		
		expressionTester("attributes.employee eq \"value\"", Account.class);

		expressionTester("attributes.employee eq \"value\" and not (attributes.hire eq \"now\")", Account.class);

		expressionTester("userColumn.nameColumn gt \"a\" and name eq \"abc\" and attributes.employee eq \"value\" and not (attributes.hire eq \"now\")", Account.class);
}
}
