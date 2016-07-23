package com.soffid.iam.bpm.index;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.model.DBProperty;
import com.soffid.iam.bpm.service.BpmEngineImpl;

import es.caib.bpm.vo.ConfigParameterVO;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbpm.JbpmContext;

public class DirectoryFactory {
	private static Log logger = LogFactory.getLog(DirectoryFactory.class);
	private static Directory d;
	public static synchronized Directory getDirectory (Session s) throws IOException {
		if (d == null)
		{
			createDirectory(s);
		}
		return d;
	}

	private static void createDirectory(Session ctx) throws IOException {
		File f = getFile(ctx);
		f.mkdirs();
		logger.debug (String.format(Messages.getString("DirectoryFactory.StartingDirectory"), f.getAbsolutePath()));  //$NON-NLS-1$
		d = new org.apache.lucene.store.NIOFSDirectory(f);
	}

	private static File getFile(Session ctx) {
        ConfigParameterVO prop = null;
        
        try {
        	prop = es.caib.seycon.ng.EJBLocator.getBpmConfigService().findFirstByAppKey(BpmEngineImpl.BPM_APPLICATION_ID,BpmEngineImpl.LUCENE_DIR_PARAM);
		} catch (Exception e) {
		}

		return new File (prop.getValue());
	}
	
	public static synchronized void clearDirectory (Session ctx) throws IOException {
		if (d != null)
		{
			logger.debug (Messages.getString("DirectoryFactory.ClosingDirectory")); //$NON-NLS-1$
			d.close();
		}
		File f = getFile(ctx);
		recursiveDelete (new File[] {f});
		d = null;
		logger.debug (String.format(Messages.getString("DirectoryFactory.CleaningDirectory"), f.getAbsolutePath()));  //$NON-NLS-1$
	}

	public static synchronized void reconfigureDirectory (JbpmContext ctx) throws IOException {
		d = null;
	}
	
	private static void recursiveDelete(File[] files) {
		for (int i = 0; files != null && i < files.length; i++)
		{
			if (files[i].isDirectory())
			{
				recursiveDelete (files[i].listFiles());
			}
			files[i].delete();
		}
	}

	public static Analyzer getAnalyzer () throws IOException {
		Reader r = new InputStreamReader ( DirectoryFactory.class.getResourceAsStream("stop-words.txt"), "UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$
		Analyzer a = new StandardAnalyzer (Version.LUCENE_30, r);
		return a;
	}
}
