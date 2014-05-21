package es.caib.bpm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


public class CollectionUtils {
	public static Object containsClassAttributeInCollection(Collection in,String fieldName,Object value) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Iterator<Object> it=in.iterator();
		boolean isBoolean=(value instanceof Boolean || value == boolean.class);
		String methodName=(isBoolean)?"is"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1):"get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1); //$NON-NLS-1$ //$NON-NLS-2$
		
		while(it.hasNext()){
			Object obj=it.next();
			Method m=obj.getClass().getMethod(methodName, new Class[]{});
			Object value2=m.invoke(obj,null);
			if(value!=null && value2 !=null && value.equals(value2)) return obj;
		}
		return null;
	}

	public static Collection extractField(Collection in, String fieldName,Class type) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Iterator<Object> it=in.iterator();
		boolean isBoolean=(type==Boolean.class || type==boolean.class);
		String methodName=(isBoolean)?"is"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1):"get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1); //$NON-NLS-1$ //$NON-NLS-2$
		LinkedList<Object> out=new LinkedList<Object>();
		
		while(it.hasNext()){
			Object obj=it.next();
			Method m=obj.getClass().getMethod(methodName, new Class[]{});
			Object value2=m.invoke(obj,null);
			out.add(value2);
			
		}
		return out;
	}
	
	public static Collection invokeForEach(Collection in, String methodName,Object [] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {	
		Iterator<Object> it=in.iterator();

		Class [] argsClasses=new Class[args.length];
		for(int i=0;i<args.length;i++) argsClasses[i]=args[i].getClass();
		
		LinkedList<Object> out=new LinkedList<Object>();
		
		while(it.hasNext()){
			Object obj=it.next();
			
			Method m=obj.getClass().getMethod(methodName, argsClasses);
			Object value2=m.invoke(obj,args);
			out.add(value2);
			
		}
		return out;
	}
}
