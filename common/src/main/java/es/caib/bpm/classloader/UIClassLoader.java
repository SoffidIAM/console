package es.caib.bpm.classloader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.ParentData;

/**
 * Representa un classloader para las interfaces de usuario.
 * 
 * @author Pablo Hern�n Gim�nez.
 */
public class UIClassLoader extends ClassLoader 
{
	
	public UIClassLoader clone (ClassLoader parentClassLoader)
	{
		return new UIClassLoader(processId, mapaClases, parentClassLoader);
	}
	
	private ProtectionDomain protectionDomain;
	private Long processId;
	public UIClassLoader(Long processId, Map mapaClases, ClassLoader parentClassLoader)
	{
	    super (parentClassLoader);
	    this.mapaClases= mapaClases;
	    this.processId = processId;
		Policy p = Policy.getPolicy();
	    CodeSource cs;
		try
		{
			cs = new CodeSource(new URL("http://bpm.customers.soffid.com/"+processId), new Certificate[0]);
			PermissionCollection permissions = p.getPermissions(cs);
		    this.protectionDomain = new ProtectionDomain(cs, permissions);
		}
		catch (MalformedURLException e)
		{
		}
	}

	public void cargarClases() throws ClassNotFoundException
	{
		for(Iterator it= this.mapaClases.keySet().iterator(); it.hasNext();)
		{
			String clase= it.next().toString().trim();
//			this.loadClass(clase);
		}
	}
	
	
	public InputStream getResourceAsStream(String name) {
		if (mapaClases.containsKey(name))
		{
			return new ByteArrayInputStream ((byte[]) mapaClases.get(name));
		}
		return super.getResourceAsStream(name);
	}

	public Class loadClass(String name) throws ClassNotFoundException 
	{
		String newName = name.replace('.', '/')+".class"; //$NON-NLS-1$
		Class clazz = findLoadedClass(name);
		if (clazz != null)
			return clazz;
		
		if (mapaClases.containsKey(newName))
		{
			byte[] bytesClase = (byte[])mapaClases.get(newName);
			return this.defineClass(name, bytesClase, 0, bytesClase.length, protectionDomain);
			
		}
		return getParent().loadClass(name);
	}
	
	private Map mapaClases= null;
	private static Log log= LogFactory.getLog(UIClassLoader.class);
}

