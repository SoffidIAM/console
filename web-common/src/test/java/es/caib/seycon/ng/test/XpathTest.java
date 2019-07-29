package es.caib.seycon.ng.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.JXPathContextFactory;
import es.caib.zkib.jxpath.Pointer;

public class XpathTest
{
	public static void main(String args[]) throws Exception
	{
		Map<String,List<String>> m = new java.util.HashMap<String,List<String>>();
		
		List<String> l = new LinkedList<String>();
		l.add("v1");
		l.add("v2");
		m.put("k1", l);
		m.put("k2", l);
		
		JXPathContext jp = JXPathContext.newContext(m);
		
		System.out.println("Starting");
		for (Iterator itp = jp.iteratePointers("/.[@name='k1']"); itp.hasNext(); )
//		for (Iterator itp = jp.iteratePointers("/*"); itp.hasNext(); )
		{
			Pointer p = (Pointer) itp.next();
			System.out.println(p.asPath());
			System.out.println(p.getValue());
		}
	}

}
