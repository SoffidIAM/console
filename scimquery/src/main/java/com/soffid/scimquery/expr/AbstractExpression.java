package com.soffid.scimquery.expr;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.LinkedList;

import org.json.JSONException;

import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.conf.AttributeConfig;
import com.soffid.scimquery.conf.ClassConfig;
import com.soffid.scimquery.conf.Configuration;

public abstract class AbstractExpression implements Serializable {
	private static final String ROOT_OBJECT_NAME = "o";
	private static final String ROOT_OBJECT_NAME2 = "p";
	private boolean oracleWorkaround = false;
	
	/**
	 * Generates HQL Sentence
	 * @param query
	 * 
	 * @throws EvalException
	 */
	public abstract void generateHSQLString (HQLQuery query) throws EvalException;
	
	public abstract boolean evaluate (Object object) throws EvalException;

	/** Methods for live evaluation of beans**/
	/**
	 * Gets a bean property value
	 * 
	 * @param bean the bean
	 * @param field property name
	 * @return the property value
	 * @throws EvalException
	 */
	Object getProperty (Object bean, String field) throws EvalException 
	{
		field = field.trim();
		try {
			Field f = bean.getClass().getField(field);
			return f.get(bean);
		} catch ( NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
		String fieldName = field.substring(0,1).toUpperCase()+
				field.substring(1);
		
		try 
		{
			Method m;
			m = getGetterMethod(bean, fieldName);
			
			return m.invoke(bean, new Object[0]);
		} catch (Exception e) {
			throw new EvalException ("Cannot get property "+field+" of bean "+bean.toString(),
					e);
		}
	}

	/**
	 * Gets the getter methdo for a property
	 *  
	 * @param bean the bean to get the method from
	 * @param fieldName the field name
	 * @return method
	 * @throws NoSuchMethodException
	 */
	private Method getGetterMethod(Object bean, String fieldName)
			throws NoSuchMethodException {
		fieldName = fieldName.trim();
		Method m;
		try {
			m = bean.getClass().getMethod("is"+fieldName, new Class[0]);
		} catch (NoSuchMethodException e) {
			m = bean.getClass().getMethod("get"+fieldName, new Class[0]);
		}
		return m;
	}
	
	/**
	 * Evaluates a simple scim attribute reference
	 * Multiple values can be obtained
	 *  
	 * @param object the bean to get values from
	 * @param referenceName the attribute reference
	 * @return the values
	 * @throws EvalException
	 */
	public Object getReference(Object object, String referenceName) throws EvalException {
		int i = referenceName.indexOf('.');
		if (i >= 0)
		{
			Object obj2 = getProperty(object, referenceName.substring(0, i));
			if (obj2 == null)
				return null;
			else if (obj2 instanceof Collection)
			{
				Collection<Object> col = (Collection<Object>) obj2;
				Collection<Object> col2 = new LinkedList<Object>();
				for (Object obj3: col)
				{
					col2.add(getReference(obj2, referenceName.substring(i+1)));
				}
				return col2;
			}
			else
				return getReference(obj2, referenceName.substring(i+1));
		}
		else
		{
			return getProperty(object, referenceName);
		}
	}

	/** Methods for HQL GENERATION **/
	
	/**
	 * Generates HQL Sentence attribute reference
	 * 
	 * @param query The HQL Sentence configuration
	 * @param attributeReference Attribute to translate
	 * @return the translated attribute reference
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws ClassNotFoundException
	 * @throws EvalException
	 * @throws JSONException
	 */
	public EvaluationContext generateHQLStringReference (HQLQuery query, String attributeReference) throws UnsupportedEncodingException, ClassNotFoundException, EvalException, JSONException
	{
		EvaluationContext ctx = new EvaluationContext();
		ctx.currentBean = query.getClassConfig();
		ctx.objectName = query.getRootObject();
		ctx.hibernatePath.append( query.getRootObject() );
		ctx.beanPath.append(".");
		ctx.beanPath.append(attributeReference);
		generateHQLStringReference(query, attributeReference, ctx);
		
		return ctx;
	}

	/**
	 * Inner method to translate HSQL sentence on a specific context
	 * 
	 * @param query
	 * @param attributeReference
	 * @param ctx
	 * @throws EvalException
	 * @throws UnsupportedEncodingException
	 * @throws ClassNotFoundException
	 * @throws JSONException
	 */
	public void generateHQLStringReference (HQLQuery query, String attributeReference, EvaluationContext ctx) throws EvalException, UnsupportedEncodingException, ClassNotFoundException, JSONException
	{
		for (String part: attributeReference.split("\\.+"))
		{
			part = part.trim();
			
			if (ctx.currentBean != null)
			{
				generateBeanPart(query, ctx, part);
			}
			else
			{
				generateHibernatePart(query, ctx, part);
			}
			if (ctx.nonHQLAttributeUsed)
				break;
		}
	}

	/**
	 * Generates the reference to a column based on a hibernate object
	 * 
	 * @param query Current query
	 * @param ctx Evaluation context
	 * @param part member to evaluate
	 * @throws EvalException
	 */
	private void generateHibernatePart(HQLQuery query, EvaluationContext ctx,
			String part) throws EvalException {
		String entityClass = ctx.hibernateClass;
		
		try {
			ClassConfig entityConfig = Configuration.getClassConfig(entityClass);
			if (part.startsWith("_"))
				part = part.substring(1);
			
			if (entityConfig != null && entityConfig.getAttribute(part) != null)
			{
				AttributeConfig attConfig = entityConfig.getAttribute(part);
				if (attConfig.isVirtualAttribute())
				{
					String path = ctx.partialPath+"."+part;
					String obj = query.getObjects().get(path);
					if (obj == null)
					{
						int number = query.getNextObject();
						obj = ROOT_OBJECT_NAME +number;
						query.getObjects().put(path, obj);
						query.getJoinString().append("\nleft join "+ctx.partialPath+" as "+obj);
						String obj2 = obj+"aux";
						String s[] = attConfig.getVirtualAttributeName().split("\\.");

						int i = query.getNextParameter();
						String param  = "p"+i;
						query.getParameters().put(param, part);

						query.getJoinString().append("\nleft join "+obj+"."+s[0]+
								" as "+obj2+" with "+
								obj2+"."+s[1]+"=:"+param);
					}
					ctx.partialPath = null;
					int i = query.getNextParameter();
					String obj2 = obj+"aux";
					String s[] = attConfig.getVirtualAttributeName().split("\\.");
					ctx.objectCondition = obj2+"."+s[1]+" is not null and ";
					ctx.objectName = obj+"."+attConfig.getVirtualAttributeValue();
				} else {
					flushParts(query, ctx);
					ctx.objectName = ctx.objectName+"."+part;					
					ctx.hibernateClass = attConfig.getScimType().getCanonicalName();
				}
			} else {
				flushParts(query, ctx);
				Field f = Class.forName(entityClass).getDeclaredField(part);
				if ( isPrimitive (f))
				{
					ctx.objectName = ctx.objectName+"."+part;
				}
				else 
				{
					ctx.hibernatePath.append(".").append(part);
					String obj = query.getObjects().get(ctx.hibernatePath.toString());
					if (obj == null)
					{
						int number = query.getNextObject();
						obj = ROOT_OBJECT_NAME +number;
						ctx.partialPath = ctx.hibernatePath.toString();
						ctx.partialObjectToUse = obj;
						ctx.partialJoinToAdd = "\nleft join "+ctx.objectName+"."+part+" as "+obj;
					}
					ctx.objectName = obj;
				}
				Class type = f.getType();
				ctx.hibernateClass = type.getCanonicalName();
				if (type.isAssignableFrom(Collection.class) && 
						f.getGenericType() instanceof ParameterizedType)
				{
					ParameterizedType pt = (ParameterizedType) f.getGenericType();
					Class pt0 = (Class) pt.getActualTypeArguments()[0];
					ctx.hibernateClass = pt0.getCanonicalName();
				}
			}
		} catch (NoSuchFieldException e) {
			throw new EvalException("Missing column "+part+" on object "+
					(ctx.currentBean == null ? ctx.hibernateClass :
						ctx.currentBean.getClazz())+ctx.beanPath.toString()
					, null);
		} catch (SecurityException e) {
			throw new EvalException("Missing column "+part+" on object "+
					(ctx.currentBean == null ? ctx.hibernateClass :
						ctx.currentBean.getClazz())
					, e);
		} catch (ClassNotFoundException e) {
			throw new EvalException("Missing class "+entityClass
					, e);
		} catch (UnsupportedEncodingException e) {
			throw new EvalException("Error evaluating column "+part+" on object "+
					(ctx.currentBean == null ? ctx.hibernateClass :
						ctx.currentBean.getClazz()),
					e);
		} catch (JSONException e) {
			throw new EvalException("Error evaluating column "+part+" on object "+
					(ctx.currentBean == null ? ctx.hibernateClass :
						ctx.currentBean.getClazz()),
					e);
		}
	}

	private void flushParts(HQLQuery query, EvaluationContext ctx) {
		if (ctx.partialPath != null)
		{
			String obj = ctx.partialObjectToUse;
			query.getObjects().put(ctx.partialPath, obj);
			query.getJoinString().append(ctx.partialJoinToAdd);
			ctx.partialPath = null;
		}
	}

	/**
	 * Generates the reference to a column based on a scim object
	 * 
	 * @param query Current query
	 * @param ctx Evaluation context
	 * @param part member to evaluate
	 * @throws EvalException
	 */
	private void generateBeanPart(HQLQuery query, EvaluationContext ctx,
			String part) throws EvalException, UnsupportedEncodingException,
			ClassNotFoundException, JSONException {
		flushParts(query, ctx);
		AttributeConfig attribute = ctx.currentBean.getAttribute(part);
		if (attribute == null)
		{
			ctx.hibernateClass = ctx.currentBean.getHibernateClass();
			if (ctx.hibernateClass == null)
			{
				throw new EvalException("Missing column "+part+" on object "+ctx.currentBean.getClazz()
						, null);
			}
			ctx.currentBean = null;
			generateHibernatePart(query, ctx, part);
		}
		else
		{
			ClassConfig bean = ctx.currentBean;
			String hibernateColumn = attribute.getHibernateColumn();
			if (attribute.isVirtualAttribute())
			{
				String obj = query.getObjects().get(ctx.hibernatePath.toString());
				if (obj == null)
				{
					int number = query.getNextObject();
					obj = ROOT_OBJECT_NAME2+number;
					query.getObjects().put(ctx.hibernatePath.toString(), obj);
					query.getJoinString().append("\nleft join "+ctx.objectName+"."+part+" as "+obj);
				}
				ctx.objectName = obj+"."+attribute.getAttributeName()+"=? and "+obj+"."+attribute.getVirtualAttributeValue();
				int i = query.getNextParameter();
				String param  = "p"+i;
				query.getParameters().put(param, part);
			}
			else if (hibernateColumn == null || hibernateColumn.isEmpty() || hibernateColumn.equals("-"))
			{
				ctx.objectName = null;
				ctx.nonHQLAttributeUsed  = true;
			}
			else
			{
				flushParts(query, ctx);
				ctx.hibernateClass = ctx.currentBean.getHibernateClass();
				ctx.currentBean = null;
				generateHQLStringReference(query, hibernateColumn, ctx);
				Class cl = attribute.getScimType();
				if (cl == null && bean.getBeanClass() != null)
				{
					try {
						cl = bean.getBeanClass().getDeclaredField(part).getType();
					} catch (NoSuchFieldException e) {
					} catch (SecurityException e) {
					}
				}
				if (cl != null && ! isPrimitive(cl))
				{
					ClassConfig cc = Configuration.getClassConfig(cl);
					if (cc == null)
						throw new EvalException("Missing config for class "+cl.getCanonicalName()+" for attribute "+part+" on object of type "+
								bean.getClazz(), null);
					ctx.currentBean = cc;
				}
			}
		}
	}

	/**
	 * Returns true if the object is primitive
	 * 
	 * @param f the primitive column
	 * @return true if it is a simple java object
	 */
	private boolean isPrimitive(Field f) {
		return isPrimitive (f.getType());
	}

	/**
	 * Returns true if the class is primitive
	 * 
	 * @param f the primitive column
	 * @return true if it is a simple java object
	 */
	private boolean isPrimitive(Class<?> cl) {
		if (cl.isAssignableFrom(Collection.class))
			return false;
		
		try {
			return cl.isPrimitive() ||
					cl.getPackage().getName().startsWith("java.") ||
					cl.getPackage().getName().startsWith("javax.") ||
					(Serializable.class.isAssignableFrom(cl) &&
						cl.getMethod("literals") != null &&
						cl.getMethod("names") != null &&
						cl.getMethod("fromString", String.class) != null);
		} catch (NoSuchMethodException e) {
			return false;
		} catch (SecurityException e) {
			return false;
		}
	}

	public HQLQuery generateHSQLString (Class clazz) throws EvalException, UnsupportedEncodingException, ClassNotFoundException, JSONException
	{
		ClassConfig cc = Configuration.getClassConfig(clazz);
		if (cc == null)
			throw new EvalException("No configuration found for "+clazz.getCanonicalName(), null);
		
		String hibernateClass = cc.getHibernateClass();
		
		HQLQuery query = new HQLQuery(cc);
	
		if (oracleWorkaround)
			query.setRootObject(ROOT_OBJECT_NAME2);
		else
			query.setRootObject(ROOT_OBJECT_NAME);
		
		query.getCountQueryString().append("select count(*) from ")
			.append(hibernateClass)
			.append(" as ")
			.append(ROOT_OBJECT_NAME);
		
		query.getQueryString().append("select ");
		if ( !oracleWorkaround)
			query.getQueryString().append("distinct ");
		query.getQueryString().append(ROOT_OBJECT_NAME)
			.append(" from ")
			.append(hibernateClass)
			.append(" as ")
			.append(ROOT_OBJECT_NAME);
		generateHSQLString(query);
		
		if (query.getWhereString().length() > 0 && oracleWorkaround)
		{
			StringBuffer where2 = new StringBuffer();
			where2.append ( ROOT_OBJECT_NAME)
				.append(".id in (select ")
				.append(ROOT_OBJECT_NAME2)
				.append(".id from ")
				.append(hibernateClass)
				.append(" as ")
				.append(ROOT_OBJECT_NAME2)
				.append(query.getJoinString() )
				.append(" where ");
			query.getWhereString().insert(0, where2);
			query.getWhereString().append(")");
			
			query.getJoinString().setLength(0);
			
		}
		return query;
	}

	public boolean isOracleWorkaround() {
		return oracleWorkaround;
	}

	public void setOracleWorkaround(boolean oracleWorkaround) {
		this.oracleWorkaround = oracleWorkaround;
	}

}



class EvaluationContext {
	public String objectCondition;
	boolean nonHQLAttributeUsed = false;
	ClassConfig currentBean;
	String hibernateClass;
	String objectName;
	StringBuffer hibernatePath = new StringBuffer();
	StringBuffer beanPath = new StringBuffer();
	String partialPath;
	String partialJoinToAdd;
	String partialObjectToUse;
}