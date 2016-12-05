package es.caib.bpm.business;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jbpm.file.def.FileDefinition;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.instantiation.ProcessClassLoaderFactory;
import org.jbpm.util.ClassLoaderUtil;

import es.caib.bpm.classloader.UIClassLoader;
import es.caib.seycon.ng.ServiceLocator;

public class BPMClassLoaderFactory implements ProcessClassLoaderFactory, Serializable{

	HashMap<Long, WeakReference<ClassLoader> > loaders = new HashMap<Long, WeakReference<ClassLoader> >();
	
	public ClassLoader getProcessClassLoader(ProcessDefinition processDefinition) {
		ClassLoader cl = null;
		WeakReference<ClassLoader> wcl = loaders.get(processDefinition.getId());
		if (wcl != null)
			cl = wcl.get();
		if (cl == null)
		{
			FileDefinition fd = processDefinition.getFileDefinition();
			Map map = fd.getBytesMap();
			Map newMap = new HashMap();
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (key.startsWith("classes/")) {  //$NON-NLS-1$
					byte[] ba = (byte[]) map.get(key);
					if (ba != null) {
						String resource = key.substring(8);
						newMap.put(resource, ba);
					}
				}
			}
			cl = new UIClassLoader(newMap, ClassLoaderUtil.getClassLoader());
			loaders.put(processDefinition.getId(), new WeakReference<ClassLoader>(cl));
		}
		return cl;
	}

}
