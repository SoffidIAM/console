package es.caib.bpm.servei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.JbpmContext;

import es.caib.bpm.config.Configuration;
import es.caib.bpm.entity.DBProperty;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.vo.ConfigParameterVO;
import es.caib.seycon.ng.exception.InternalErrorException;

public class BpmConfigServiceImpl extends BpmConfigServiceBase {
	Stack<Object> jbpmContextStack = new Stack<Object>();
	Log log = LogFactory.getLog(getClass());

	private JbpmContext getContext() {
		JbpmContext jbpmContext = null;
		if (jbpmContextStack.isEmpty()) {
			jbpmContext = Configuration.getConfig().createJbpmContext();
			jbpmContextStack.push(jbpmContext);
		} else {
			jbpmContext = (JbpmContext) jbpmContextStack.peek();
			jbpmContextStack.push(jbpmContext);
		}
		return jbpmContext;

	}

	private void flushContext() {
		if (jbpmContextStack.isEmpty())
			return;
		Object stackElement = jbpmContextStack.pop();

		if (jbpmContextStack.isEmpty()) {
			JbpmContext jbpmContext = (JbpmContext) stackElement;
			jbpmContext.setActorId(null);
			try {
				jbpmContext.close();
			} finally {
				jbpmContext = null;
			}
		}
	}

	@Override
	protected Collection<ConfigParameterVO> handleFindAll() throws Exception {
		try {
			JbpmContext ctx = getContext();
			Session session = ctx.getSession();
			Query q = session.createQuery("from es.caib.bpm.entity.DBProperty"); //$NON-NLS-1$

			Collection<DBProperty> result = q.list();
			if (result != null)
				return entityToConfigParameterVO(result);
			else
				return new ArrayList<ConfigParameterVO>();

		} catch (Exception e) {
			log.error(e);
			throw new BPMException(e.getMessage(), e, 0);
		} finally {
			flushContext();
		}
	}

	@Override
	protected Collection<ConfigParameterVO> handleFindById(long id)
			throws Exception {
		try {
			JbpmContext ctx = getContext();
			Session session = ctx.getSession();
			Query q = session
					.createQuery("from es.caib.bpm.entity.DBProperty where id=:id"); //$NON-NLS-1$
			q.setParameter("id", id); //$NON-NLS-1$

			Collection<DBProperty> result = q.list();
			if (result != null)
				return entityToConfigParameterVO(result);
			else
				return new ArrayList<ConfigParameterVO>();

		} catch (Exception e) {
			log.error(e);
			throw new BPMException(e.getMessage(), e, 0);
		} finally {
			flushContext();
		}

	}

	@Override
	protected Collection<ConfigParameterVO> handleFindByAppKey(String app,
			String key) throws Exception {
		try{
			JbpmContext ctx=getContext();
			Session session=ctx.getSession();
			Query q=session.createQuery("from es.caib.bpm.entity.DBProperty where app=:app and key=:key"); //$NON-NLS-1$
			if(app==null || "".equals(app)) app="%"; //$NON-NLS-1$ //$NON-NLS-2$
			if(key==null || "".equals(key)) key="%"; //$NON-NLS-1$ //$NON-NLS-2$
			q.setParameter("app", app); //$NON-NLS-1$
			q.setParameter("key", key); //$NON-NLS-1$
			
			Collection<DBProperty> result=q.list();
			if(result!=null) 
				return entityToConfigParameterVO(result);
			else 
				return new ArrayList<ConfigParameterVO>();
			
		}catch (Exception e){
			log.error(e);
			throw new BPMException(e.getMessage(),e,0);
		}finally{
			flushContext();
		}
	}

	@Override
	protected ConfigParameterVO handleFindFirstByAppKey(String app, String key)
			throws Exception {
		try {
			JbpmContext ctx = getContext();
			Session session = ctx.getSession();
			Query q = session
					.createQuery("from es.caib.bpm.entity.DBProperty where app=:app and key=:key"); //$NON-NLS-1$
			if (app == null || "".equals(app)) //$NON-NLS-1$
				app = "%"; //$NON-NLS-1$
			if (key == null || "".equals(key)) //$NON-NLS-1$
				key = "%"; //$NON-NLS-1$
			q.setParameter("app", app); //$NON-NLS-1$
			q.setParameter("key", key); //$NON-NLS-1$

			List<DBProperty> results = q.list();

			DBProperty result = null;
			if (results.iterator().hasNext())
				result = (DBProperty) results.get(0);
			if (result != null)
				return entityToConfigParameterVO(result);
			else
				return null;

		} catch (Exception e) {
			log.error(e);
			throw new BPMException(e.getMessage(), e, 0);
		} finally {
			flushContext();
		}
	}

	protected void handleUpdateAll(ConfigParameterVO config) throws Exception {
	}

	@Override
	protected void handleUpdate(ConfigParameterVO config) throws Exception {
		try {
			JbpmContext ctx = getContext();
			Session session = ctx.getSession();
			DBProperty param = configParameterVOToEntity(config);
			session.saveOrUpdate(param);
		} catch (Exception e) {
			log.error(e);
			throw new BPMException(e.getMessage(), e, 0);
		} finally {
			flushContext();
		}
	}

	@Override
	protected void handleCreate(ConfigParameterVO config) throws Exception {
		try {
			JbpmContext ctx = getContext();
			Session session = ctx.getSession();
			DBProperty param = configParameterVOToEntity(config);
			session.save(param);
		} catch (Exception e) {
			log.error(e);
			throw new BPMException(e.getMessage(), e, 0);
		} finally {
			flushContext();
		}

	}

	@Override
	protected void handleDelete(ConfigParameterVO config) throws Exception {
		try {
			JbpmContext ctx = getContext();
			Session session = ctx.getSession();
			DBProperty param = configParameterVOToEntity(config);
			session.delete(param);
		} catch (Exception e) {
			log.error(e);
			throw new BPMException(e.getMessage(), e, 0);
		} finally {
			flushContext();
		}
	}

	/* Tansformacións Entity-VO / VO-Etity */
	private Collection<ConfigParameterVO> entityToConfigParameterVO(
			Collection<DBProperty> config) {
		Collection<ConfigParameterVO> out = new LinkedList<ConfigParameterVO>();
		Iterator<DBProperty> it = config.iterator();
		while (it.hasNext()) {
			DBProperty param = it.next();
			out.add(entityToConfigParameterVO(param));
		}
		return out;
	}

	private ConfigParameterVO entityToConfigParameterVO(DBProperty param) {
		ConfigParameterVO out = new ConfigParameterVO();
		out.setApp(param.getApp());
		out.setId(param.getId());
		out.setKey(param.getKey());
		out.setValue(param.getValue());
		return out;
	}

	private DBProperty configParameterVOToEntity(ConfigParameterVO param) {
		DBProperty out = new DBProperty();
		out.setApp(param.getApp());
		out.setId(param.getId());
		out.setKey(param.getKey());
		out.setValue(param.getValue());
		return out;
	}

	@Override
	protected void handleUpdateAll(Collection<ConfigParameterVO> config)
	{
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.ApplicationBootServiceBase#handleSyncServerBoot()
	 */
	@Override
	protected void handleSyncServerBoot () throws Exception
	{
		Configuration.configureForServer();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.ApplicationBootServiceBase#handleConsoleBoot()
	 */
	@Override
	protected void handleConsoleBoot () throws Exception
	{
		Configuration.configureForConsole();
	}


}
