package com.soffid.iam.bpm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;



/**
 * @author azalazar
 * Metodos de conveniencia para el uso de colecciones
 */
public class ColeccionesUtils
{	
	/**
	 * Devuelve un string para concatenar
	 * 
	 * @param coleccionEntidad
	 * @param metodo
	 * @return una coleccion con los resultados devueltos por los getter aplicados
	 * @throws Exception
	 */
/*	public static String getValorCampoElementoConcatenar(Collection coleccionEntidad) throws Exception
	{
		//declaramos
		
		String proyectosTodos = "";
		
		//recorremos
		for(Iterator iter= coleccionEntidad.iterator(); iter.hasNext();)
		{
			proyectosTodos = proyectosTodos + "," + "'" + ((Entidad)iter.next()).getIdentificadorProyecto()+ "'";
		}
		if(!proyectosTodos.equals(""))
		{
			proyectosTodos= proyectosTodos.substring(1, proyectosTodos.length());
		}
		
		//devolvemos
		return proyectosTodos;
	}*/
	
	/**
	 * Ordena una coleccion utilizando el <code>Comparator</code> especificado
	 * @param coleccion
	 * @param comparator
	 * @return
	 * @throws Exception
	 */
	public static Collection ordenar(Collection coleccion, Comparator comparator) throws Exception
	{		
		//declaramos		
		List lista= null;		
				
		//pasamos a un arraylist
		lista=  new ArrayList(coleccion);
		
		//ordenamos
		Collections.sort(lista, comparator);		
		
		//retornamos
		return lista;
	}
	
	/**
	 * Invierte el orden de la coleccion
	 * 
	 * @param coleccion
	 * @return la coleccion con el orden invertido
	 */
	public static Collection invertirOrden(Collection coleccion)
	{
		//declaramos
		Vector resultados= null;
		Vector coleccionAuxiliar= null;
		
		//inicializar
		coleccionAuxiliar= new Vector(coleccion);
		resultados= new Vector();
		
		//tomamos del primer elemento al ultimo, y los insertamos como primer elemento 
		while(coleccionAuxiliar.size()!= 0)
		{
			resultados.add(0, coleccionAuxiliar.remove(0));
		}
		
		//devolvemos
		return resultados;
	}
	
	
	/**
	 * Devuelve una coleccion de resultados de aplicar el metodo getter especificado
	 * a cada elemento de la coleccionEntidad
	 * 
	 * @param coleccionEntidad
	 * @param metodo
	 * @return una coleccion con los resultados devueltos por los getter aplicados
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 */
	public static String getValorCampoElementoToQueryIn(Collection coleccionEntidad, String metodo) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		//declaramos
		Class claseEntidad= null;
		Class[] tipos= null;
		Method getter= null;
		Object entidad= null;
		Object resultado= null;
		String cadena = "("; //$NON-NLS-1$
		
		if(coleccionEntidad.size()== 0)
		{
			cadena= cadena.concat("''"); //$NON-NLS-1$
		}
		
		//recorremos
		for(Iterator iter= coleccionEntidad.iterator(); iter.hasNext();)
		{
			//tomamos el elemento
			entidad= iter.next();
			
			//tomamos la clase
			claseEntidad= entidad.getClass();
			
			//generamos los tipos de los parametros
			tipos= new Class[0];
			
			//tomamos el metodo
			getter= claseEntidad.getMethod(metodo, tipos);
			
			//invocamos
			resultado= getter.invoke(entidad, (Object[])null);
			
			//concatenamos el toString del resultado
			cadena= cadena.concat("'" + resultado.toString() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			
			if(iter.hasNext())
			{
				cadena= cadena.concat(","); //$NON-NLS-1$
			}
		}
		//cerramos el string
		cadena= cadena.concat(")"); //$NON-NLS-1$
		
		//eliminamos las repeticiones
		//resultados= this.eliminarRepeticiones(resultados);
		
		//devolvemos
		return cadena;
	}
	
	
	/**
	 * Devuelve una coleccion de resultados de aplicar el metodo getter especificado
	 * a cada elemento de la coleccionEntidad
	 * 
	 * @param coleccionEntidad
	 * @param metodo
	 * @return una coleccion con los resultados devueltos por los getter aplicados
	 * @throws Exception
	 */
	public static Collection getValorCampoElemento(Collection coleccionEntidad, String metodo) throws Exception
	{
		//declaramos
		Collection resultados= null;
		Class claseEntidad= null;
		Class[] tipos= null;
		Method getter= null;
		Object entidad= null;
		Object resultado= null;
		
		//inicializamos
		resultados= new Vector();
		
		//recorremos
		for(Iterator iter= coleccionEntidad.iterator(); iter.hasNext();)
		{
			//tomamos el elemento
			entidad= (Object)iter.next();
			
			//tomamos la clase
			claseEntidad= entidad.getClass();
			
			//generamos los tipos de los parametros
			tipos= new Class[0];
			
			//tomamos el metodo
			getter= claseEntidad.getMethod(metodo, tipos);
			
			//invocamos
			resultado= getter.invoke(entidad, (Object[]) null);
			
			//agregamos
			resultados.add(resultado);
		}
		
		//eliminamos las repeticiones
		//resultados= this.eliminarRepeticiones(resultados);
		
		//devolvemos
		return resultados;
	}
}
