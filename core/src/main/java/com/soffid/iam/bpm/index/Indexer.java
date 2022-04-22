package com.soffid.iam.bpm.index;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.context.exe.TokenVariableMap;
import org.jbpm.context.exe.VariableContainer;
import org.jbpm.context.exe.VariableInstance;
import org.jbpm.context.exe.variableinstance.ByteArrayInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.Comment;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.job.Timer;

import com.soffid.iam.bpm.config.Configuration;
import com.soffid.iam.bpm.model.DBProperty;
import com.soffid.iam.bpm.model.TenantModule;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.utils.Security;

public class Indexer {
	private Log log = LogFactory.getLog(Indexer.class);
	private static Indexer theIndexer = null;
	int maxFieldLength = 4000;
	boolean fullIndex = false;
	
	private Indexer () {
		try {
			maxFieldLength = Integer.parseInt(System.getProperty("soffid.indexer.max-field-size"));
		} catch (Exception e) {}
		String mfs = ConfigurationCache.getProperty("soffid.indexer.max-field-ize");
		if (mfs != null) {
			try {
				maxFieldLength = Integer.parseInt(System.getProperty(mfs));
			} catch (Exception e) {}
		}
	}
	public static Indexer getIndexer () {
		if (theIndexer == null)
			theIndexer = new Indexer ();
		return theIndexer;
	}
	
	private Collection<ProcessInstance> getProcesses(Session session, long then, long now, Long nextProcessId) {
		if (nextProcessId == null)
			return session
					.createQuery("select distinct pi "
						+ "from org.jbpm.logging.log.ProcessLog as pl "
						+ "join pl.token as token "
						+ "join token.processInstance as pi "
						+ "where pl.date > :then "
						+ "order by pi.id desc")
					.setTimestamp ("then", new Date(then))
					.list();
		else
			return session
					.createQuery("select distinct pi "
						+ "from org.jbpm.logging.log.ProcessLog as pl "
						+ "join pl.token as token "
						+ "join token.processInstance as pi "
						+ "where pl.date > :then and pi.id < :nextProcessId "
						+ "order by pi.id desc")
					.setTimestamp ("then", new Date(then))
					.setLong("nextProcessId", nextProcessId)
					.list();
	}

	public Long flush(Session session, long then, long now, Long nextProcessId, long max) throws IOException {
		String skip = ConfigurationCache.getProperty("soffid.indexer.skip-attribute");
		String skipArray[] = skip == null ? new String[0]: skip.split(" ");
		Arrays.sort(skipArray);
		
		if (DirectoryFactory.isEmpty(session)) {
			log.info("Index is empty. Regenerating");
			then = 0;
			nextProcessId = null;
		}
		log.debug("Indexing processes since "+DateFormat.getDateTimeInstance().format(new Date(then)));
		Collection<ProcessInstance> p = getProcesses (session, then, now, nextProcessId);
		Directory dir = DirectoryFactory.getDirectory(session);
		IndexWriter w;
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_CURRENT, DirectoryFactory.getAnalyzer());
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		try {
			w = new IndexWriter (dir, iwc);
		} catch (FileNotFoundException e) {
			iwc.setOpenMode(OpenMode.CREATE);
			w = new IndexWriter (dir, iwc);
		}
		Document d;
		try { 
			long last = 0;
			int number = 0;
			for (ProcessInstance process: p)
			{
				if (number > max) return last;
				last = process.getId();
				try {
					log.info("Indexing process "+process.getId()+" "+DateFormat.getDateTimeInstance().format(process.getStart()));
					d = generateDocument(process, skipArray);
					log.debug(String.format(Messages.getString("Indexer.DeletingDocument"), d.get("$id"))); //$NON-NLS-1$ //$NON-NLS-2$
					// Delete pre-existing document
					w.deleteDocuments(new Term ("$id", d.get("$id"))); //$NON-NLS-1$ //$NON-NLS-2$
					// Create new document
					log.debug(String.format(Messages.getString("Indexer.AddingDocument"), d.get("$id"))); //$NON-NLS-1$ //$NON-NLS-2$
					w.addDocument(d);
					log.debug(String.format(Messages.getString("Indexer.DoneDocument"), d.get("$id"))); //$NON-NLS-1$ //$NON-NLS-2$
				} catch (Throwable th) {
					log.info("Error indexing process "+process.getId(), th);
				}
			}
		} finally {
			w.close();
		}
		return null;
	}
	
	public Document generateDocument (ProcessInstance pi, String[] skipArray) throws IOException {
		String id = Long.toString(pi.getId()); 
		Document d;
		// Create new document
		d = new Document ();
		d.add(new Field ("$id",  //$NON-NLS-1$
				id,
				Field.Store.YES, Field.Index.ANALYZED));
		d.add(new Field ("$definition",  //$NON-NLS-1$
				pi.getProcessDefinition().getName(),
				Field.Store.NO, Field.Index.ANALYZED));
		StringBuffer contents = new StringBuffer();
		contents.append(pi.getProcessDefinition().getName());
		ContextInstance ci = pi.getContextInstance();
		addTokenInfo (d, ci, pi.getRootToken(), contents, skipArray);

		d.add(new Field ("$contents",  //$NON-NLS-1$
				contents.toString(),
				Field.Store.NO, Field.Index.ANALYZED));
		d.add(new Field ("$end",  //$NON-NLS-1$
				pi.getEnd() == null ? "false": "true", //$NON-NLS-1$ //$NON-NLS-2$
				Field.Store.YES, Field.Index.ANALYZED));
		
		TenantModule tm = (TenantModule) pi.getInstance(TenantModule.class);
		if (tm != null && tm.getTenantId() != null)
			d.add(new Field ("$tenant",  //$NON-NLS-1$
				tm.getTenantId().toString(), //$NON-NLS-1$ //$NON-NLS-2$
				Field.Store.YES, Field.Index.ANALYZED));
		else
			d.add(new Field ("$tenant",  //$NON-NLS-1$
				Long.toString( Security.getCurrentTenantId()), //$NON-NLS-1$ //$NON-NLS-2$
				Field.Store.YES, Field.Index.ANALYZED));
		// Afegim data d'inici i de fi
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //Formato 20100924 //$NON-NLS-1$
		try {
			d.add(new Field ("$startDate",sdf.format(pi.getStart()),Field.Store.YES,Field.Index.ANALYZED)); //$NON-NLS-1$
			if (pi.getEnd()!=null) d.add(new Field ("$endDate",sdf.format(pi.getEnd()),Field.Store.YES,Field.Index.ANALYZED)); //$NON-NLS-1$
		} catch (Throwable th) {}
		return d;
	}

	public void index(ProcessInstance pi) throws IOException {
	}

	private StringBuffer addTokenInfo(Document d, ContextInstance ci, Token token, StringBuffer contents, String[] skipArray) {
		try {
			TokenVariableMap tvm = ci.getTokenVariableMap(token);
			addTokenInfo (d, tvm, token, contents, skipArray);
		} catch (Throwable e) {
			// Error deserializing data
		}
		StringBuffer comments= new StringBuffer();
		if (token.getComments() != null)
		{
			for (Iterator it = token.getComments().iterator(); it.hasNext();)
			{
				Comment c = (Comment) it.next();
				if (c.getMessage() != null)
				{
					comments.append(c.getMessage());
					comments.append(" "); //$NON-NLS-1$
				}
			}
		}
		String prefix = token.isRoot()? "": token.getFullName()+"/"; //$NON-NLS-1$ //$NON-NLS-2$
		d.add(new Field (prefix+"comments",  //$NON-NLS-1$
				comments.toString(),
				Field.Store.NO, Field.Index.ANALYZED));
		contents.append(comments);
		Map children = token.getChildren();
		if (children != null && children.keySet() != null)
		{
			for (Iterator it = children.keySet().iterator(); it.hasNext();)
			{
				Token childToken = token.getChild((String) it.next());
				addTokenInfo (d, ci, childToken, contents, skipArray);
			}
		}
		return contents;
	}
	
	private void addTokenInfo(Document d, TokenVariableMap tvm, Token token, StringBuffer contents, String skip[]) {
		String prefix = token.isRoot()? "": token.getFullName()+"/"; //$NON-NLS-1$ //$NON-NLS-2$
		Map m = tvm.getVariableInstances();
		for (Iterator it = m.keySet().iterator(); it.hasNext(); )
		{
			String key = (String) it.next();
			if (Arrays.binarySearch(skip, key) < 0) {
				try {
					VariableInstance vi = (VariableInstance) m.get(key);
					if ( vi instanceof ByteArrayInstance && Arrays.binarySearch(skip, "binary") >= 0) {
						// Skip
					} else {
						Object value = vi.getValue();
						if (value != null) {
							String s = toString(value);
							if (s.length() < maxFieldLength) {
								d.add(new Field (prefix+key, 
										s,
										Field.Store.NO, Field.Index.ANALYZED));
								if (contents.length() < maxFieldLength) {
									contents.append(" "); //$NON-NLS-1$
									contents.append(s);
								}
							}
						}
					}
				} catch (Throwable t) {
					// Error deserializing data
				}
			}
		}
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm");
	
	private String toString(Object value) {
		if (value == null)
			return "";
		else if (value instanceof Date)
		{
			return sdf.format(value);
		}
		else if (value instanceof Calendar)
		{
			return sdf.format(value);
		}
		else if (value instanceof Map)
		{
			String s = "";
			for ( Object t: ((Map) value).entrySet())
			{
				if (s.length() > 0)
					s = s + " ";
				s = s + toString(t);
				if (s.length() > maxFieldLength) break;
			}
				
			return s;
		}
		else if (value instanceof Collection)
		{
			String s = "";
			for ( Object t: (Collection) value)
			{
				if (s.length() > 0)
					s = s + " ";
				s = s + toString(t);
				if (s.length() > maxFieldLength) break;
			}
				
			return s;
		}
		else
			return value.toString();
	}
	
	public void reindexAll ( ) throws IOException {
		Long next = null;
		Long now = System.currentTimeMillis();
		do {
			JbpmContext ctx = Configuration.getConfig().createJbpmContext();
			try {
				next = flush (ctx.getSession(), 0, now, next, 1000);
			} finally {
				ctx.close();
			}
		} while (next != null);
	}
	
}
