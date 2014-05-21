package es.caib.bpm.index;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.Comment;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

import es.caib.bpm.config.Configuration;
import es.caib.bpm.entity.DBProperty;

public class Indexer {
	Logger l = Logger.getLogger(Indexer.class);
	LinkedList documents = new LinkedList ();
	private static Indexer theIndexer = null;
	private Indexer () {
		
	}
	public static Indexer getIndexer () {
		if (theIndexer == null)
			theIndexer = new Indexer ();
		return theIndexer;
	}
	
	public void enqueue (Document d) 
	{
		synchronized( documents ) {
			documents.addLast(d);
		}
	}

	public void flush(Session session) throws IOException {
		Directory dir = DirectoryFactory.getDirectory(session);
		IndexWriter w;
		try {
			w = new IndexWriter (dir, DirectoryFactory.getAnalyzer(),
				false, MaxFieldLength.LIMITED);
		} catch (FileNotFoundException e) {
			w = new IndexWriter (dir, DirectoryFactory.getAnalyzer(),
					true, MaxFieldLength.LIMITED);
		}
		Document d;
		Iterator it ;
		boolean goon ;
		try { 
			synchronized (documents) {
				it = documents.iterator();
				goon = it.hasNext();
			}
			
			while ( goon )
			{
				synchronized (documents)
				{
					d = (Document) it.next();
					goon = it.hasNext();
				}
				l.debug("Deleting document "+d.get("$id"));
				// Delete pre-existing document
				w.deleteDocuments(new Term ("$id", d.get("$id")));
				// Create new document
				l.debug("Adding document "+d.get("$id"));
				w.addDocument(d);
				synchronized (documents)
				{
					it.remove();
				}
				l.debug("Done document "+d.get("$id"));
			}
		} finally {
			w.close();
		}
	}
	
	public Document generateDocument (ProcessInstance pi) throws IOException {
		String id = Long.toString(pi.getId()); 
		Document d;
		// Create new document
		d = new Document ();
		d.add(new Field ("$id", 
				id,
				Field.Store.YES, Field.Index.ANALYZED));
		d.add(new Field ("$definition", 
				pi.getProcessDefinition().getName(),
				Field.Store.NO, Field.Index.ANALYZED));
		ContextInstance ci = pi.getContextInstance();
		StringBuffer contents = new StringBuffer();
		contents.append(pi.getProcessDefinition().getName());
		addTokenInfo (d, ci, pi.getRootToken(), contents);
		d.add(new Field ("$contents", 
				contents.toString(),
				Field.Store.NO, Field.Index.ANALYZED));
		d.add(new Field ("$end", 
				pi.getEnd() == null ? "false": "true",
				Field.Store.NO, Field.Index.ANALYZED));
		// Afegim data d'inici i de fi
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //Formato 20100924
		try {
			d.add(new Field ("$startDate",sdf.format(pi.getStart()),Field.Store.NO,Field.Index.ANALYZED));
			if (pi.getEnd()!=null) d.add(new Field ("$endDate",sdf.format(pi.getEnd()),Field.Store.NO,Field.Index.ANALYZED));
		} catch (Throwable th) {}
		return d;
	}

	public void index(ProcessInstance pi) throws IOException {
		enqueue(generateDocument(pi));
	}

	private StringBuffer addTokenInfo(Document d, ContextInstance ci, Token token, StringBuffer contents) {
		String prefix = token.isRoot()? "": token.getFullName()+"/";

		Map m = ci.getVariables(token);
		for (Iterator it = m.keySet().iterator(); it.hasNext(); )
		{
			String key = (String) it.next();
			Object value = m.get(key);
			if (value != null) {
				d.add(new Field (prefix+key, 
						value.toString(),
						Field.Store.NO, Field.Index.ANALYZED));
				contents.append(" ");
				contents.append(value.toString());
			}
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
					comments.append(" ");
				}
			}
		}
		d.add(new Field (prefix+"comments", 
				comments.toString(),
				Field.Store.NO, Field.Index.ANALYZED));
		contents.append(comments);
		Map children = token.getChildren();
		if (children != null && children.keySet() != null)
		{
			for (Iterator it = children.keySet().iterator(); it.hasNext();)
			{
				Token childToken = token.getChild((String) it.next());
				addTokenInfo (d, ci, childToken, contents);
			}
		}
		return contents;
	}
	
	public void reindexAll ( ) throws IOException {
		JbpmContext ctx = Configuration.getConfig().createJbpmContext();
		synchronized (documents) {
			documents.clear();
			DirectoryFactory.clearDirectory(ctx.getSession());
		}
		try {
			Iterator itDef = ctx.getGraphSession().findAllProcessDefinitions().iterator();
			while (itDef.hasNext())
			{
				ProcessDefinition def = (ProcessDefinition) itDef.next();
				Iterator itProc = ctx.getGraphSession().findProcessInstances(def.getId()).iterator();
				while (itProc.hasNext())
				{
					ProcessInstance pi = (ProcessInstance) itProc.next();
					try {
						Document d = generateDocument(pi);
						enqueue(d);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			ctx.close();
		}
	}
	
}
