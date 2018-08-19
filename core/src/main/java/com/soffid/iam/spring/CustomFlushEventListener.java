package com.soffid.iam.spring;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.action.CollectionRecreateAction;
import org.hibernate.action.CollectionRemoveAction;
import org.hibernate.action.CollectionUpdateAction;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.engine.ActionQueue;
import org.hibernate.engine.Cascade;
import org.hibernate.engine.CascadingAction;
import org.hibernate.engine.CollectionEntry;
import org.hibernate.engine.Collections;
import org.hibernate.engine.EntityEntry;
import org.hibernate.engine.PersistenceContext;
import org.hibernate.engine.Status;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEntityEvent;
import org.hibernate.event.FlushEntityEventListener;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.def.AbstractFlushingEventListener;
import org.hibernate.event.def.DefaultFlushEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.pretty.Printer;
import org.hibernate.util.IdentityMap;
import org.hibernate.util.LazyIterator;

public class CustomFlushEventListener extends DefaultFlushEventListener {

	private static final Log log = LogFactory.getLog(AbstractFlushingEventListener.class);
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Pre-flushing section
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/** 
	 * Coordinates the processing necessary to get things ready for executions
	 * as db calls by preping the session caches and moving the appropriate
	 * entities and collections to their respective execution queues.
	 *
	 * @param event The flush event.
	 * @throws HibernateException Error flushing caches to execution queues.
	 */
	protected void flushEverythingToExecutions(FlushEvent event) throws HibernateException {

		log.trace("flushing session");
		
		EventSource session = event.getSession();
		
		final PersistenceContext persistenceContext = session.getPersistenceContext();
		session.getInterceptor().preFlush( new LazyIterator( persistenceContext.getEntitiesByKey() ) );

		prepareEntityFlushes(session);
		// we could move this inside if we wanted to
		// tolerate collection initializations during
		// collection dirty checking:
		prepareCollectionFlushes(session);
		// now, any collections that are initialized
		// inside this block do not get updated - they
		// are ignored until the next flush
				
		persistenceContext.setFlushing(true);
		try {
			flushEntities(event);
			flushCollections(session);
		}
		finally {
			persistenceContext.setFlushing(false);
		}

		//some statistics
		if ( log.isDebugEnabled() ) {
			log.debug( "Flushed: " +
					session.getActionQueue().numberOfInsertions() + " insertions, " +
					session.getActionQueue().numberOfUpdates() + " updates, " +
					session.getActionQueue().numberOfDeletions() + " deletions to " +
					persistenceContext.getEntityEntries().size() + " objects"
				);
			log.debug( "Flushed: " +
					session.getActionQueue().numberOfCollectionCreations() + " (re)creations, " +
					session.getActionQueue().numberOfCollectionUpdates() + " updates, " +
					session.getActionQueue().numberOfCollectionRemovals() + " removals to " +
					persistenceContext.getCollectionEntries().size() + " collections"
				);
			new Printer( session.getFactory() ).toString( 
					persistenceContext.getEntitiesByKey().values().iterator(), 
					session.getEntityMode() 
				);
		}
	}

	/**
	 * process cascade save/update at the start of a flush to discover
	 * any newly referenced entity that must be passed to saveOrUpdate(),
	 * and also apply orphan delete
	 */
	private void prepareEntityFlushes(EventSource session) throws HibernateException {
		
		log.debug("processing flush-time cascades");

		Collection<Object> list = CustomSession.getDirtyEntities( session );

		if ( list == null) return;
		
		final Object anything = getAnything();
		for ( Object entity: list) {
			EntityEntry entry = session.getPersistenceContext().getEntry(entity);
			if (entry == null)
			{
				throw new HibernateException ("Dirty entity "+entity.toString()+" not found in session");
			}
			else
			{
				Status status = entry.getStatus();
				if ( status == Status.MANAGED || status == Status.SAVING ) {
					cascadeOnFlush( session, entry.getPersister(), entity, anything );
				}
			}
		}
	}
	
	private void cascadeOnFlush(EventSource session, EntityPersister persister, Object object, Object anything) 
	throws HibernateException {
		session.getPersistenceContext().incrementCascadeLevel();
		try {
			new Cascade( getCascadingAction(), Cascade.BEFORE_FLUSH, session )
			.cascade( persister, object, anything );
		}
		finally {
			session.getPersistenceContext().decrementCascadeLevel();
		}
	}
	
	protected Object getAnything() { return null; }
	
	protected CascadingAction getCascadingAction() {
		return CascadingAction.SAVE_UPDATE;
	}

	/**
	 * Initialize the flags of the CollectionEntry, including the
	 * dirty check.
	 */
	private void prepareCollectionFlushes(EventSource session) throws HibernateException {
		Collection<Object> entities = CustomSession.getDirtyEntities( session );

		// Initialize dirty flags for arrays + collections with composite elements
		// and reset reached, doupdate, etc.
		
		log.debug("dirty checking collections");

		final List list = IdentityMap.entries( session.getPersistenceContext().getCollectionEntries() );
		final int size = list.size();
		for ( int i = 0; i < size; i++ ) {
			Map.Entry e = ( Map.Entry ) list.get( i );
			CollectionEntry collectionEntry = (CollectionEntry) e.getValue();
			PersistentCollection key = (PersistentCollection) e.getKey();
			collectionEntry.preFlush( key );
		}
	}

	/**
	 * 1. detect any dirty entities
	 * 2. schedule any entity updates
	 * 3. search out any reachable collections
	 */
	private void flushEntities(FlushEvent event) throws HibernateException {
		log.trace("Flushing entities and processing referenced collections");

		Collection<Object> list = CustomSession.getDirtyEntities( event.getSession());

		if ( list == null) return;
		
		// Among other things, updateReachables() will recursively load all
		// collections that are moving roles. This might cause entities to
		// be loaded.

		// So this needs to be safe from concurrent modification problems.
		// It is safe because of how IdentityMap implements entrySet()

		final EventSource source = event.getSession();
		
		for ( Object entity: list) {

			// Update the status of the object and if necessary, schedule an update

			EntityEntry entry = source.getPersistenceContext().getEntry(entity);
			if (entry == null)
			{
				throw new HibernateException ("Dirty entity "+entity.toString()+" not found in session");
			}
			else
			{
				Status status = entry.getStatus();
	
				if ( status != Status.LOADING && status != Status.GONE ) {
					FlushEntityEvent entityEvent = new FlushEntityEvent( source, entity, entry );
					FlushEntityEventListener[] listeners = source.getListeners().getFlushEntityEventListeners();
					for ( int j = 0; j < listeners.length; j++ ) {
						listeners[j].onFlushEntity(entityEvent);
					}
				}
			}
		}

		source.getActionQueue().sortActions();
	}

	/**
	 * process any unreferenced collections and then inspect all known collections,
	 * scheduling creates/removes/updates
	 */
	private void flushCollections(EventSource session) throws HibernateException {
		Collection<Object> entities = CustomSession.getDirtyEntities( session );

		log.trace("Processing unreferenced collections");

		List list = IdentityMap.entries( session.getPersistenceContext().getCollectionEntries() );
		int size = list.size();
		for ( int i = 0; i < size; i++ ) {
			Map.Entry me = ( Map.Entry ) list.get( i );
			CollectionEntry ce = (CollectionEntry) me.getValue();
			if ( !ce.isReached() && !ce.isIgnore() ) {
				PersistentCollection coll = (PersistentCollection) me.getKey();
				if (entities != null && entities.contains(coll.getOwner()))
				{
					EntityEntry entry = session.getPersistenceContext().getEntry(coll.getOwner());
					if (entry != null)
						Collections.processUnreachableCollection( coll, session );
					else
					{
						throw new HibernateException ("Dirty collection owner "+coll.getOwner().toString()+" not found in session");
					}
				}
				else
				{
					ce.setDoupdate(false);
					ce.setDorecreate(false);
					ce.setDoremove(false);
					ce.setReached(true);
				}
			}
		}

		// Schedule updates to collections:

		log.trace( "Scheduling collection removes/(re)creates/updates" );

		list = IdentityMap.entries( session.getPersistenceContext().getCollectionEntries() );
		size = list.size();
		ActionQueue actionQueue = session.getActionQueue();
		for ( int i = 0; i < size; i++ ) {
			Map.Entry me = (Map.Entry) list.get(i);
			PersistentCollection coll = (PersistentCollection) me.getKey();
			CollectionEntry ce = (CollectionEntry) me.getValue();
			
			if (  coll.getOwner() == null || (entities != null && entities.contains(  coll.getOwner() )))
//			if ( ! ce.isProcessed() )
			{
				
	
				if ( ce.isDorecreate() ) {
					session.getInterceptor().onCollectionRecreate( coll, ce.getCurrentKey() );
					actionQueue.addAction(
							new CollectionRecreateAction( 
									coll, 
									ce.getCurrentPersister(), 
									ce.getCurrentKey(), 
									session 
								)
						);
				}
				if ( ce.isDoremove() ) {
					session.getInterceptor().onCollectionRemove( coll, ce.getLoadedKey() );
					actionQueue.addAction(
							new CollectionRemoveAction( 
									coll,
									ce.getLoadedPersister(), 
									ce.getLoadedKey(), 
									ce.isSnapshotEmpty(coll), 
									session 
								)
						);
				}
				if ( ce.isDoupdate() ) {
					session.getInterceptor().onCollectionUpdate( coll, ce.getLoadedKey() );
					actionQueue.addAction(
							new CollectionUpdateAction( 
									coll, 
									ce.getLoadedPersister(), 
									ce.getLoadedKey(), 
									ce.isSnapshotEmpty(coll), 
									session 
								)
						);
				}
			} else {
				ce.setProcessed(true);
			}
		}

		actionQueue.sortCollectionActions();
		
	}

	/** Handle the given flush event.
	 *
	 * @param event The flush event to be handled.
	 * @throws HibernateException
	 */
	public void onFlush(FlushEvent event) throws HibernateException {
		final EventSource source = event.getSession();
		if ( source.getPersistenceContext().hasNonReadOnlyEntities() ) {
			
			flushEverythingToExecutions(event);
			performExecutions(source);
			postFlush(source);
		
			if ( source.getFactory().getStatistics().isStatisticsEnabled() ) {
				source.getFactory().getStatisticsImplementor().flush();
			}
			Collection<Object> l = CustomSession.getDirtyEntities(event.getSession());
			if (l != null) l.clear();
		}
	}

}
