package es.caib.bpm.classloader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Representa un classloader para las interfaces de usuario.
 * 
 * @author Pablo Hern�n Gim�nez.
 */
public class UIClassLoader extends ClassLoader 
{
	public UIClassLoader(Map mapaClases, ClassLoader parentClassLoader)
	{
	    super (parentClassLoader);
	    this.mapaClases= mapaClases;
	}

	public void cargarClases() throws ClassNotFoundException
	{
		String clase= null;
		for(Iterator it= this.mapaClases.keySet().iterator(); it.hasNext();)
		{
			clase= it.next().toString().trim();
			
//			this.loadClass(clase);
		}
	}
	
	
	public InputStream getResourceAsStream(String name) {
//		System.out.println ("Getting resource "+name);
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
			return this.defineClass(name, bytesClase, 0, bytesClase.length);
			
		}

		return this.getClass().getClassLoader().loadClass(name);
	}
	private Map mapaClases= null;
	private static Logger log= Logger.getLogger(UIClassLoader.class);
}
