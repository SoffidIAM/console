/**
 * 
 */
package es.caib.seycon.ng.test;

import org.hibernate.EntityMode;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;

/**
 * @author bubu
 *
 */
public class InsertListenerTest implements PostInsertEventListener
{

	public InsertListenerTest ()
	{
		
	}
	/* (non-Javadoc)
	 * @see org.hibernate.event.PreInsertEventListener#onPreInsert(org.hibernate.event.PreInsertEvent)
	 */
	public void onPostInsert (PostInsertEvent event)
	{
		System.out.println ("ON POST INSERT");
		System.out.println ("TABLE="+event.getPersister().getEntityName());
		System.out.println ("ID="+event.getPersister().getIdentifier(event.getEntity(), EntityMode.POJO));
	}

}
