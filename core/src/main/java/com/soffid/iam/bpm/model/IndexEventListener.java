package com.soffid.iam.bpm.model;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.event.AutoFlushEventListener;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.event.def.DefaultAutoFlushEventListener;
import org.hibernate.event.def.DefaultFlushEventListener;
import org.jbpm.context.exe.VariableInstance;
import org.jbpm.graph.exe.ProcessInstance;

import com.soffid.iam.bpm.index.Indexer;

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
