package es.caib.bpm.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.event.AutoFlushEvent;
import org.hibernate.event.AutoFlushEventListener;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.event.def.DefaultAutoFlushEventListener;
import org.hibernate.event.def.DefaultFlushEventListener;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.context.exe.VariableInstance;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.job.Job;

import es.caib.bpm.index.DirectoryFactory;
import es.caib.bpm.index.Indexer;

public class IndexEventListener 
	implements 
		PreInsertEventListener, PreUpdateEventListener, FlushEventListener {
	
	FlushEventListener flushDefault = new DefaultFlushEventListener();
	AutoFlushEventListener autoflushDefault = new DefaultAutoFlushEventListener();

	private Log log = LogFactory.getLog(IndexEventListener.class);
	
	public boolean onPreInsert(PreInsertEvent event) {
		process (event.getSource(), event.getEntity());
		return false;
	}

	public boolean onPreUpdate(PreUpdateEvent event) {
		process (event.getSource(), event.getEntity());
		return false;
	}

	private void process(SessionImplementor sessionImplementor, Object entity) {
		
		if (entity instanceof ProcessInstance)
		{
			ProcessInstance pi = (ProcessInstance) entity;
			index(sessionImplementor, pi);
		}
		if (entity instanceof VariableInstance)
		{
			VariableInstance vi = (VariableInstance) entity;
			index(sessionImplementor, vi.getProcessInstance());
		}
	}

	private void index(SessionImplementor sessionImplementor, ProcessInstance processInstance) {
		IndexSession s = IndexSession.getSesion(sessionImplementor);
		s.getProcesses().add(processInstance);
	}

	public void onFlush(FlushEvent event) throws HibernateException {
		IndexSession s = IndexSession.getSesion(event.getSession());
		try {
			s.setInFlush(true);
			flushDefault.onFlush(event);
			try {
				Indexer indexer = Indexer.getIndexer();
				for ( Iterator it = s.getProcesses().iterator(); it.hasNext();)
				{
					ProcessInstance processInstance = (ProcessInstance) it.next();
					Document d = indexer.generateDocument(processInstance); 
					s.getDocuments().add( d );
					it.remove();
				}
			} catch (Throwable e) {
				log.warn(e);
			}
			Indexer indexer = Indexer.getIndexer();
			for ( Iterator it = s.getDocuments().iterator(); it.hasNext(); )
			{
				indexer.enqueue((Document) it.next());
			}
		} finally {
			s.clear(event.getSession());
		}
	}
}
