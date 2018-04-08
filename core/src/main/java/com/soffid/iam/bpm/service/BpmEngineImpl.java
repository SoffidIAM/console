package com.soffid.iam.bpm.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.ZipInputStream;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.BooleanFilter;
import org.apache.lucene.queries.FilterClause;
import org.apache.lucene.queries.TermsFilter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.ConstantScoreQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.xpath.DefaultXPath;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.file.def.FileDefinition;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.exe.Comment;
import org.jbpm.graph.log.ActionLog;
import org.jbpm.graph.log.ProcessInstanceCreateLog;
import org.jbpm.graph.log.ProcessInstanceEndLog;
import org.jbpm.graph.log.TransitionLog;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.jpdl.JpdlException;
import org.jbpm.jpdl.xml.Problem;
import org.jbpm.logging.exe.LoggingInstance;
import org.jbpm.logging.log.CompositeLog;
import org.jbpm.logging.log.MessageLog;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;
import org.xml.sax.SAXException;

import com.soffid.iam.api.Group;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.bpm.api.BPMUser;
import com.soffid.iam.bpm.api.Job;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.api.ProcessLog;
import com.soffid.iam.bpm.api.TaskDefinition;
import com.soffid.iam.bpm.api.TaskInstance;
import com.soffid.iam.bpm.api.Token;
import com.soffid.iam.bpm.business.ProcessDefinitionRolesBusiness;
import com.soffid.iam.bpm.business.UserInterfaceBusiness;
import com.soffid.iam.bpm.business.VOFactory;
import com.soffid.iam.bpm.config.Configuration;
import com.soffid.iam.bpm.index.DirectoryFactory;
import com.soffid.iam.bpm.index.Indexer;
import com.soffid.iam.bpm.model.AuthenticationLog;
import com.soffid.iam.bpm.model.DBProperty;
import com.soffid.iam.bpm.model.ProcessDefinitionProperty;
import com.soffid.iam.bpm.model.TenantModuleDefinition;
import com.soffid.iam.bpm.model.UserInterface;
import com.soffid.iam.bpm.model.dal.ProcessDefinitionPropertyDal;
import com.soffid.iam.bpm.service.impl.UserContextCache;
import com.soffid.iam.bpm.utils.ColeccionesUtils;
import com.soffid.iam.bpm.utils.FechaUtils;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.ProcessHierarchyEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.service.UserService;
import com.soffid.iam.utils.Security;

import es.caib.bpm.exception.BPMErrorCodes;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.exception.InvalidConfigurationException;
import es.caib.bpm.exception.InvalidParameterException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownUserException;

public class BpmEngineImpl extends BpmEngineBase {
	/**
	 * 
	 */
	private static final String CACHE_TAG = "bpmUserContext"; //$NON-NLS-1$
	public static final String OBSERVER_ROLE = "observer"; //$NON-NLS-1$
	public static final String SUPERVISOR_ROLE = "supervisor"; //$NON-NLS-1$
	public static final String BPM_EJB_APP = "BPM_EJB"; //$NON-NLS-1$
	public static final String INITIATOR_ROLE = "initiator"; //$NON-NLS-1$
	public static final String BPM_APPLICATION_ID = "BPM"; //$NON-NLS-1$
	public static final String LUCENE_DIR_PARAM = "lucene.dir"; //$NON-NLS-1$
	private Log log;

	public BpmEngineImpl() {
		log = LogFactory.getLog(getClass());
	}

	LRUMap map = new LRUMap();

	private String getUserName() {
		if (Security.getPrincipal() == null)
			return "nobody";

		String p = null;
		p = Security.getCurrentUser();
		if (p == null)
			return "nobody"; //$NON-NLS-1$
		else
			return p;
	}

	private UserContextCache getUserContextCache()
			throws InternalErrorException, UnknownUserException, BPMException {
		String sessionId;
		String user = getUserName();

		UserContextCache cached;
		if (user == null) {
			cached = new UserContextCache();
			cached.setRoles(new String[] { "anonymous" }); //$NON-NLS-1$
			return cached;
		}
		
		cached = (UserContextCache) getSessionCacheService().getObject(
				CACHE_TAG);
		if (cached == null) {
			cached = new UserContextCache();
			UserService usuariService = getUserService();
			User userData = usuariService.getCurrentUser();

			LinkedList<String> userGroups = new LinkedList<String>();
			if (userData != null) {
				SystemEntity defaultDispatcher = getSystemEntityDao()
						.findSoffidSystem();
				userGroups.add(user);
				Collection<Group> grups = usuariService
						.getUserGroupsHierarchy(userData.getId());
				for (Group grup : grups) {
					userGroups.add(grup.getName());
				}

				Collection<RoleGrant> roles = getApplicationService()
						.findEffectiveRoleGrantByUser(userData.getId());
				for (RoleGrant role : roles) {
					String name = role.getRoleName();
					if (role.getSystem().equals(defaultDispatcher.getName()))
						userGroups.add(name);
					name = name + "@" + role.getSystem();
					userGroups.add(name);
					if (role.getDomainValue() != null) {
						name = role.getRoleName();
						name = name + "/" + role.getDomainValue();
						if (role.getSystem()
								.equals(defaultDispatcher.getName()))
							userGroups.add(name);
						name = name + "@" + role.getSystem();
						userGroups.add(name);
					}
				}

				for (String auth : Security.getAuthorizations()) {
					userGroups.add("auth:" + auth); //$NON-NLS-1$
				}
			}

			userGroups.add("tothom"); //$NON-NLS-1$
			cached.setRoles(userGroups.toArray(new String[userGroups.size()]));
			getSessionCacheService().putObject(CACHE_TAG, cached);
		}
		return cached;
	}

	/**
	 * Recupera los roles del usuario. Si el nombre del usuario es "anonymous",
	 * se le asigna el grupo "anonymous" Si el usuario es anónimo (no hace
	 * login), pero el nombre de usuario no es "anonymous" se trata como un
	 * usuario del sistema. Esto es útil para llamadas entre ejb's sin login que
	 * hacen un run-as
	 * 
	 * @param context
	 * @return
	 * @throws BPMException
	 * @throws UnknownUserException
	 * @throws InternalErrorException
	 */
	private String[] getUserGroups() throws BPMException,
			InternalErrorException, UnknownUserException {
		UserContextCache userCtx = getUserContextCache();
		return userCtx.getRoles();
	}

	private void flushContext(JbpmContext ctx) {
		if (ctx != null) {
			ctx.setActorId(null);
			try {
				ctx.close();
			} catch (Exception e) {
				log.info("Error closing BPM context", e);
			}
		}
	}

	@Override
	protected List handleFindMyProcesses() throws Exception {
		JbpmContext context = getContext();
		try {
			LinkedList<ProcessInstance> resultadoFinal = new LinkedList<ProcessInstance>();

			Session session = context.getSession();
			Query query = session.createQuery("select pi " //$NON-NLS-1$
					+ "from org.jbpm.graph.exe.ProcessInstance as pi " //$NON-NLS-1$
					+ "where pi.end is null " + "order by pi.start desc"); //$NON-NLS-1$ //$NON-NLS-2$
			for (Iterator it = query.iterate(); it.hasNext();) {
				org.jbpm.graph.exe.ProcessInstance instance = (org.jbpm.graph.exe.ProcessInstance) it
						.next();
				List logs = instance.getLoggingInstance().getLogs(
						ProcessInstanceCreateLog.class);
				if (logs.size() > 0) {
					ProcessInstanceCreateLog log = (ProcessInstanceCreateLog) logs
							.get(0);
					if (getUserName().equals(log.getActorId())) {
						resultadoFinal.add(VOFactory
								.newProcessInstance(instance));
					}
				}
			}
			return resultadoFinal;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected ProcessDefinition handleGetProcessDefinition(
			ProcessInstance process) throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.graph.exe.ProcessInstance instance = context
					.loadProcessInstance(process.getId());
			org.jbpm.graph.def.ProcessDefinition definition = instance
					.getProcessDefinition();
			return VOFactory.newProcessDefinition(definition, context);
		} finally {
			flushContext(context);
		}
	}

	public void startAuthenticationLog(org.jbpm.graph.exe.Token token) {
		LoggingInstance li = (LoggingInstance) token.getProcessInstance()
				.getInstance(LoggingInstance.class);
		if (li == null) {
			li = new LoggingInstance();
			token.getProcessInstance().addInstance(li);
		}
		AuthenticationLog log = new AuthenticationLog();
		log.setToken(token);
		log.setActorId(getUserName());
		li.startCompositeLog(log);
	}

	/**
	 * convenience method for ending a composite log. Make sure you put this in
	 * a finally block.
	 */
	public void endAuthenticationLog(org.jbpm.graph.exe.Token token) {
		LoggingInstance li = (LoggingInstance) token.getProcessInstance()
				.getInstance(LoggingInstance.class);
		if (li != null) {
			li.endCompositeLog();
		}
	}

	@Override
	protected ProcessInstance handleCancel(ProcessInstance process)
			throws Exception {
		JbpmContext context = getContext();
		;
		try {
			org.jbpm.graph.exe.ProcessInstance instance = context
					.getProcessInstance(process.getId());
			startAuthenticationLog(instance.getRootToken());
			
			org.jbpm.graph.exe.Token token = instance.getRootToken();

			cancelJobs(context, token);

			instance.end();

			for (Iterator it = instance.getTaskMgmtInstance()
					.getTaskInstances().iterator(); it.hasNext();) {
				org.jbpm.taskmgmt.exe.TaskInstance taskInstance = (org.jbpm.taskmgmt.exe.TaskInstance) it
						.next();

				if (!taskInstance.hasEnded()) {
					taskInstance.setSignalling(false);
					taskInstance.cancel();
				}
			}
			
			endAuthenticationLog(instance.getRootToken());
			context.save(instance);
			return VOFactory.newProcessInstance(instance);
		} finally {
			flushContext(context);
		}
	}

	private void cancelJobs(JbpmContext context, org.jbpm.graph.exe.Token token) {
		List l = context.getJobSession().findJobsByToken(token);
		for (Iterator it = l.iterator(); it.hasNext();) {
			org.jbpm.job.Job j = (org.jbpm.job.Job) it.next();
			context.getJobSession().deleteJob(j);
		}
		
		for (org.jbpm.graph.exe.Token t: token.getChildren().values())
			cancelJobs(context, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.caib.bpm.servei.BpmEngineBase#handleSearchProcessInstances(java.lang
	 * .String, java.lang.String, java.lang.String, boolean, java.lang.String)
	 */
	@Override
	protected List handleSearchProcessInstances(String query, String processID,
			String startDate, String endDate, boolean finished)
			throws Exception {
		JbpmContext context = getContext();
		LinkedList resultado = new LinkedList();
		
		try {
			if ((processID != null) && !processID.equals("")) //$NON-NLS-1$
			{
				long processId = Long.parseLong(processID);
				com.soffid.iam.bpm.api.ProcessInstance proc = getProcess(processId);
				if (proc != null)
					resultado.add(proc);
			}

			else {
				if ((query == null) || (query.equals(""))) //$NON-NLS-1$
				{
					//					throw new ParseException(Messages.getString("BpmEngineImpl.VoidSearchParametersError")); //$NON-NLS-1$
				}

				Directory dir = DirectoryFactory.getDirectory(context
						.getSession());
				IndexReader reader = DirectoryReader.open(dir);
				IndexSearcher is;
				is = new IndexSearcher(reader);
				QueryParser qp = new QueryParser(Version.LUCENE_CURRENT,
						"$contents", //$NON-NLS-1$
						DirectoryFactory.getAnalyzer());
				org.apache.lucene.search.Query q = null;
				if (query != null && query.trim().length() > 0)
					q = qp.parse(query);
				else
					q = new MatchAllDocsQuery();

				// Verifiquem el format de les dates
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$
				String dataInici = null, dataFi = null;
				if (startDate != null && !"".equals(startDate)) { //$NON-NLS-1$
					dataInici = startDate;
				} else {
					Calendar dataIniciWF = Calendar.getInstance();
					dataIniciWF.set(2005, 1, 1); // data d'inici 1 de gener de
													// 2005
					dataInici = sdf.format(dataIniciWF.getTime());
				}
				if (endDate != null && !"".equals(endDate)) { //$NON-NLS-1$
					dataFi = endDate;
				} else {
					// Posem com a limit superior demà:
					// dataIni > hui
					if (dataInici != null) {
						try {
							Date d_dataInici = sdf.parse(dataInici);
							Calendar dema = Calendar.getInstance();
							dema.set(Calendar.HOUR_OF_DAY, 0);
							dema.set(Calendar.MINUTE, 0);
							dema.set(Calendar.SECOND, 0);
							dema.set(Calendar.MILLISECOND, 0);
							dema.add(Calendar.DATE, 1);
							if (d_dataInici.getTime() >= dema.getTimeInMillis()) {
								throw new BPMException(
										Messages.getString("BpmEngineImpl.StartDateError"), //$NON-NLS-1$
										-1);
							}
						} catch (java.text.ParseException ex) {

						}
					}
					Calendar dema = Calendar.getInstance();
					dema.add(Calendar.DATE, 2); // afegim 2 dies
					dataFi = sdf.format(dema.getTime());
				}

				TopDocs hits;
				BooleanFilter b = new BooleanFilter();
				boolean complexQuery = false;
				if (startDate != null && !"".equals(startDate)) { //$NON-NLS-1$
					TermRangeFilter fstart = new TermRangeFilter("$startDate", //$NON-NLS-1$
							new BytesRef(dataInici), 
							new BytesRef(dataFi), true, true); // inclusiu
					b.add(new FilterClause(fstart, BooleanClause.Occur.MUST));
					complexQuery = true;
				}
				if (endDate != null && !"".equals(endDate)) { //$NON-NLS-1$
					TermRangeFilter fend = new TermRangeFilter("$endDate", //$NON-NLS-1$
							new BytesRef(dataInici), 
							new BytesRef(dataFi), true, true); // inclusiu
					b.add(new FilterClause(fend, BooleanClause.Occur.MUST));
					complexQuery = true;
				}

				DocumentCollector collector = new DocumentCollector();
				collector.setResult (resultado);
    			if (!finished) {
					TermsFilter f = new TermsFilter( new Term("$end", "false") );
    				b.add(new FilterClause(f, BooleanClause.Occur.MUST));
    				complexQuery = true;
    				if (complexQuery) {
						is.search(q, b, collector);
					} else
    					is.search(q, f, collector); // Sense filtre de dates
    
    			} else {
    				if (complexQuery)
    					is.search(q, b, collector);
    				else
    					is.search(q, collector); // Sense cap filtre
    			}
				reader.close();
			}
			return resultado;
		} catch (CorruptIndexException e) {
			throw new BPMException(
					Messages.getString("BpmEngineImpl.CorruptedIndex"), e, -1); //$NON-NLS-1$
		} catch (IOException e) {
			throw new BPMException(
					Messages.getString("BpmEngineImpl.CorruptedIndex"), e, -1); //$NON-NLS-1$
		} catch (ParseException e) {
			throw new BPMException(String.format(
					Messages.getString("BpmEngineImpl.SearchParamError"), //$NON-NLS-1$
					e.getMessage()), -1);
		} catch (ArrayIndexOutOfBoundsException e) { // Problema quan obtenim
														// més de maximRes
														// resultats
														// (IndexSearcher.
														// search(xxxx,maximRes))
			throw new BPMException(
					Messages.getString("BpmEngineImpl.VeryRegFinded"), //$NON-NLS-1$
					-1);
		} catch (Exception e) {
			throw new BPMException(
					String.format(
							Messages.getString("BpmEngineImpl.Error"), e.getMessage()), -1); // Enmascarem  //$NON-NLS-1$
			// d'altres
			// excepcions
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindProcessInstances(List definitions,
			String processId, String estado, String actor, Date startDate,
			boolean finalizada) throws Exception {
		JbpmContext context = getContext();
		try {

			Query query = context.getSession().getNamedQuery(
					"searchProcessInstance"); //$NON-NLS-1$

			if (definitions != null) {
				try {
					query.setParameterList("id", ColeccionesUtils //$NON-NLS-1$
							.getValorCampoElemento(definitions, "getId")); //$NON-NLS-1$
				} catch (Exception ex) {
					throw new InvalidParameterException(
							BPMErrorCodes.PARAMETRO_INVALIDO);
				}
			}

			if (!processId.trim().equals("")) { //$NON-NLS-1$
				query.setParameter("processId", new Long(processId)); //$NON-NLS-1$
			} else {
				query.setParameter("processId", new Long(-1)); //$NON-NLS-1$
			}

			query.setParameter("estado", estado); //$NON-NLS-1$

			if (finalizada) {
				query.setParameter("finalizada", "FINALIZADA"); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				query.setParameter("finalizada", null); //$NON-NLS-1$
			}

			try {
				if (startDate != null) {
					query.setParameter("fechaDesde", //$NON-NLS-1$
							FechaUtils.establecerFechaInicioDia(startDate));
					query.setParameter("fechaHasta", //$NON-NLS-1$
							FechaUtils.establecerFechaFinDia(startDate));
				} else {
					Calendar calendar = new GregorianCalendar();
					calendar.set(2200, 1, 1);

					query.setParameter("fechaDesde", new Date(0L)); //$NON-NLS-1$
					query.setParameter("fechaHasta", calendar.getTime()); //$NON-NLS-1$
				}
			} catch (Exception ex) {
				throw new InvalidParameterException(
						BPMErrorCodes.PARAMETRO_INVALIDO);
			}

			ArrayList resultado = new ArrayList();

			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			for (Iterator it = query.iterate(); it.hasNext();) {
				org.jbpm.graph.exe.ProcessInstance p = (org.jbpm.graph.exe.ProcessInstance) it
						.next();

				try {
					if (business.isUserAuthorized(OBSERVER_ROLE,
							getUserGroups(), p.getProcessDefinition())
							|| business.isUserAuthorized(SUPERVISOR_ROLE,
									getUserGroups(), p.getProcessDefinition()))
						resultado.add(VOFactory.newProcessInstance(p));
				} catch (JbpmException e) {
					log.warn(e);
				}

			}

			return resultado;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindProcessInstances(ProcessDefinition def)
			throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);
			LinkedList<ProcessInstance> resultadoFinal = new LinkedList<ProcessInstance>();
			org.jbpm.graph.def.ProcessDefinition definition = context
					.getGraphSession().getProcessDefinition(def.getId());

			if (business.isUserAuthorized(OBSERVER_ROLE, getUserGroups(),
					definition)
					|| business.isUserAuthorized(SUPERVISOR_ROLE,
							getUserGroups(), definition)) {
				{
					for (Iterator it = context.getGraphSession()
							.findProcessInstances(definition.getId())
							.iterator(); it.hasNext();) {
						org.jbpm.graph.exe.ProcessInstance instance = (org.jbpm.graph.exe.ProcessInstance) it
								.next();
						try {
							resultadoFinal.add(VOFactory
									.newProcessInstance(instance));
						} catch (JbpmException e) {
							log.warn(e);
						}
					}
				}
			}
			return resultadoFinal;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected ProcessDefinition handleGetDefinition(ProcessInstance process)
			throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.graph.exe.ProcessInstance instance = context
					.getProcessInstance(process.getId());
			return VOFactory.newProcessDefinition(
					instance.getProcessDefinition(), context);
		} finally {
			flushContext(context);
		}
	}

	private boolean isInternalService() {
		return Security.isUserInRole("BPM_INTERNAL"); //$NON-NLS-1$
	}

	@Override
	protected void handleUpdate(ProcessInstance process) throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			org.jbpm.graph.exe.ProcessInstance pi = context
					.loadProcessInstance(process.getId());
			if (!isInternalService()
					&& pi.getRootToken().getNode().getId() != pi
							.getProcessDefinition().getStartState().getId()) {
				throw new BPMException(
						Messages.getString("BpmEngineImpl.StartedProcessError"), -1); //$NON-NLS-1$
			}
			if (isInternalService()
					|| business.isUserAuthorized(INITIATOR_ROLE,
							getUserGroups(), pi.getProcessDefinition())) {
				ContextInstance ctx = pi.getContextInstance();
				HashMap map = new HashMap();

				startAuthenticationLog(pi.getRootToken());
				if (process.getVariables() != null)
					map.putAll(process.getVariables());

				if (ctx.getVariables() != null) {
					// Borrar y modificar variables
					for (Iterator it = ctx.getVariables().keySet().iterator(); it
							.hasNext();) {
						String key = (String) it.next();
						Object value = map.get(key); // PJR canvio
														// value=map.get(key) :
														// això no feia un
														// update!
						ctx.setVariable(key, value);
						map.remove(key);
					}
				}

				// Agregar variables
				for (Iterator it = map.keySet().iterator(); it.hasNext();) {
					String key = (String) it.next();
					Object value = map.get(key);
					ctx.setVariable(key, value);
				}
				endAuthenticationLog(pi.getRootToken());
				context.save(pi);
			} else {
				throw new SecurityException(
						Messages.getString("BpmEngineImpl.NotAuthorizedMessage")); //$NON-NLS-1$
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected void handleStartProcess(ProcessInstance process) throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			org.jbpm.graph.exe.ProcessInstance pi = context
					.loadProcessInstance(process.getId());
			if (pi.getRootToken().getNode().getId() != pi
					.getProcessDefinition().getStartState().getId()) {
				throw new BPMException(
						Messages.getString("BpmEngineImpl.StartedProcessError"), -1); //$NON-NLS-1$
			}
			if (!isInternalService()
					|| business.isUserAuthorized(INITIATOR_ROLE,
							getUserGroups(), pi.getProcessDefinition())) {
				startAuthenticationLog(pi.getRootToken());
				pi.signal();
				endAuthenticationLog(pi.getRootToken());
				context.save(pi);
			} else {
				throw new SecurityException(
						Messages.getString("BpmEngineImpl.NotAuthorizedMessage")); //$NON-NLS-1$
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected ProcessInstance handleGetProcess(long id) throws Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(jbpmContext);

			org.jbpm.graph.exe.ProcessInstance process = jbpmContext
					.getProcessInstance(id);
			if (process == null)
				return null;

			org.jbpm.graph.def.ProcessDefinition definition = process
					.getProcessDefinition();

			if (!isInternalService()
					&& !business.isUserAuthorized(OBSERVER_ROLE,
							getUserGroups(), definition)
					&& !business.isUserAuthorized(SUPERVISOR_ROLE,
							getUserGroups(), definition)) {
				Collection list = process.getTaskMgmtInstance()
						.getTaskInstances();
				for (Iterator it = list.iterator(); it.hasNext();) {
					org.jbpm.taskmgmt.exe.TaskInstance ti = (org.jbpm.taskmgmt.exe.TaskInstance) it
							.next();
					if (business.canAccess(getUserGroups(), ti)) {
						return VOFactory.newProcessInstance(process);
					}
				}
				return null;
				//				throw new SecurityException(Messages.getString("BpmEngineImpl.AccesNotAuthorizedMessage")); //$NON-NLS-1$
			} else {
				return VOFactory.newProcessInstance(process);
			}
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}
	}

	private void recursiveFillTokens(org.jbpm.graph.exe.Token rootToken,
			Collection tokens) {
		com.soffid.iam.bpm.api.Token t2 = VOFactory.newToken(rootToken);
		tokens.add(t2);
		for (Iterator it = rootToken.getChildren().values().iterator(); it
				.hasNext();) {
			recursiveFillTokens((org.jbpm.graph.exe.Token) it.next(), tokens);
		}
	}

	@Override
	protected Token[] handleGetTokens(long id) throws Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			org.jbpm.graph.exe.ProcessInstance process = jbpmContext
					.getProcessInstance(id);

			Vector v = new Vector(1);
			recursiveFillTokens(process.getRootToken(), v);

			return (com.soffid.iam.bpm.api.Token[]) v
					.toArray(new com.soffid.iam.bpm.api.Token[v.size()]);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}
	}

	@Override
	protected ProcessLog[] handleGetProcessLog(ProcessInstance instanceVO)
			throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.graph.exe.ProcessInstance process = context
					.loadProcessInstance(instanceVO.getId());
			LinkedList parsedLogs = new LinkedList();
			parseLog(context, process, parsedLogs, process.getRootToken());
			Collections.sort(parsedLogs, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					ProcessLog l1 = (ProcessLog) arg0;
					ProcessLog l2 = (ProcessLog) arg1;
					return l1.getDate().compareTo(l2.getDate());
				}
			});
			
			for (ProcessHierarchyEntity parent: getProcessHierarchyEntityDao().findByChildren(instanceVO.getId()))
			{
				long processId = parent.getParentProcess().longValue();
				addSubprocessLog(context, process, parsedLogs, processId, 0);
			}
			for (ProcessHierarchyEntity parent: getProcessHierarchyEntityDao().findByParent(instanceVO.getId()))
			{
				long processId = parent.getChildProcess().longValue();
				addSubprocessLog(context, process, parsedLogs, processId, parsedLogs.size());
			}
			ProcessLog[] logs = (ProcessLog[]) parsedLogs
					.toArray(new ProcessLog[parsedLogs.size()]);
			return logs;
		} finally {
			flushContext(context);
		}
	}

	private void addSubprocessLog(JbpmContext context, org.jbpm.graph.exe.ProcessInstance process,
			LinkedList parsedLogs, long processId, int position) {
		org.jbpm.graph.exe.ProcessInstance proc = context.loadProcessInstance(processId);
		if (proc != null)
		{
			try {
				ProcessInstance procvo = VOFactory.newProcessInstance(proc);
				ProcessLog log = new ProcessLog();
				log.setDate(procvo.getStart());
				log.setUser("");
				log.setProcessId(process.getId());
				log.setAction(Messages.getString("BpmEngineImpl.StartProcess")+": "+
						procvo.getId()+" - "+procvo.getDescription());
				
				parsedLogs.add(position++, log);

				LinkedList<ProcessLog> parsedLogs2 = new LinkedList<ProcessLog>();
				parseLog(context, proc, parsedLogs2, proc.getRootToken());
				Collections.sort(parsedLogs2, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						ProcessLog l1 = (ProcessLog) arg0;
						ProcessLog l2 = (ProcessLog) arg1;
						return l1.getDate().compareTo(l2.getDate());
					}
				});
				
				if (parsedLogs2.size() > 0)
					log.setUser(parsedLogs2.get(0).getUser());
				
				if (position > 1)
				{
					for ( ProcessLog pl: parsedLogs2)
					{
						pl.setAction("> "+pl.getAction());
						parsedLogs.add(position++, pl);
					}
				}

			} catch (Exception e) {
			}
		}
	}

	private void parseLog(JbpmContext context,
			org.jbpm.graph.exe.ProcessInstance process, LinkedList parsedLogs,
			org.jbpm.graph.exe.Token t) {
		Criteria criteria = null;

		criteria = context.getSession().createCriteria(
				org.jbpm.logging.log.ProcessLog.class);

		criteria.add(Restrictions.eq("token", t)); //$NON-NLS-1$
		criteria.add(Restrictions.isNull("parent")); //$NON-NLS-1$
		Iterator it = criteria.list().iterator();
		while (it.hasNext()) {
			org.jbpm.logging.log.ProcessLog pl = (org.jbpm.logging.log.ProcessLog) it
					.next();
			parseLog(process, parsedLogs, pl);
		}
		for (Iterator it2 = t.getChildren().values().iterator(); it2.hasNext();) {
			org.jbpm.graph.exe.Token childToken = (org.jbpm.graph.exe.Token) it2
					.next();
			parseLog(context, process, parsedLogs, childToken);
		}

	}

	private void parseLog(org.jbpm.graph.exe.ProcessInstance process,
			LinkedList parsedLogs, org.jbpm.logging.log.ProcessLog pl) {
		ProcessLog logLine = new ProcessLog();
		logLine.setDate(pl.getDate());
		logLine.setProcessId(process.getId());
		logLine.setUser(pl.getActorId());
		Security.nestedLogin(Security.getCurrentAccount(), new String[] {Security.AUTO_USER_QUERY+Security.AUTO_ALL});
		try {
			if (pl.getActorId() != null && ! pl.getActorId().isEmpty())
				logLine.setUser(pl.getActorId()+" "+getUserService().findUserByUserName(pl.getActorId()).getFullName());
		} catch (Exception e) {
		} finally {
			Security.nestedLogoff();
		}
		StringBuffer b = new StringBuffer();
		org.jbpm.logging.log.ProcessLog pl2 = pl.getParent();
		while (pl2 != null) {
			b.append("\\ "); //$NON-NLS-1$
			pl2 = pl2.getParent();
		}
		// log.debug(b.toString()+pl.getId()+" - "+pl.getClass().getName()+": "+pl.toString());
		if (pl instanceof ProcessInstanceCreateLog) {
			logLine.setAction(Messages.getString("BpmEngineImpl.StartProcess")); //$NON-NLS-1$
			parsedLogs.add(logLine);
		} else if (pl instanceof TransitionLog) {
			TransitionLog tl = (TransitionLog) pl;
			logLine.setAction((tl.getTransition().getName() != null
					&& tl.getTransition().getName().length() > 0 ? tl
					.getTransition().getName() + ": " : //$NON-NLS-1$
					"") //$NON-NLS-1$ //$NON-NLS-2$
					+ tl.getSourceNode().getName() + " -> " //$NON-NLS-1$
					+ tl.getDestinationNode().getName());
			parsedLogs.add(logLine);
		} else if (pl instanceof ProcessInstanceEndLog) {
			logLine.setAction(Messages.getString("BpmEngineImpl.ProcessEnd")); //$NON-NLS-1$
			parsedLogs.add(logLine);
		} else if (pl instanceof MessageLog) {
			logLine.setAction(((MessageLog) pl).getMessage());
			parsedLogs.add(logLine);
		} else if (pl instanceof ActionLog) {
			ActionLog al = (ActionLog) pl;
			if (al.getException() != null) {
				logLine.setAction(String.format(
						Messages.getString("BpmEngineImpl.AutomaticTaskError"), al.getAction().getName(), //$NON-NLS-1$
						al.getException())); //$NON-NLS-1$
				parsedLogs.add(logLine);
			}
			for (Iterator it = al.getChildren().iterator(); it.hasNext();) {
				org.jbpm.logging.log.ProcessLog child = (org.jbpm.logging.log.ProcessLog) it
						.next();
				if (child != null) {
					parseLog(process, parsedLogs, child);
				}
			}
		} else if (pl instanceof CompositeLog) {
			CompositeLog cl = (CompositeLog) pl;
			for (Iterator it = cl.getChildren().iterator(); it.hasNext();) {
				org.jbpm.logging.log.ProcessLog child = (org.jbpm.logging.log.ProcessLog) it
						.next();
				if (child != null) {
					parseLog(process, parsedLogs, child);
				}
			}
		}
	}

	@Override
	protected void handleUpgradeProcess(ProcessInstance instanceVO)
			throws Exception {
		InputStream streamLectura = null;

		JbpmContext context = getContext();
		try {

			org.jbpm.graph.exe.ProcessInstance process = context
					.loadProcessInstance(instanceVO.getId());
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			if (business.isUserAuthorized(SUPERVISOR_ROLE, getUserGroups(),
					process)) {

				UserInterfaceBusiness business2 = new UserInterfaceBusiness(
						context);
				String messages[] = business2.upgradeProcess(process);
				getUserContextCache().setMessages(messages);
			}
		} catch (JpdlException ex) {
			generateUpgradeMessages(ex);
			context.setRollbackOnly();
			throw new BPMException(ex, BPMErrorCodes.ERROR_DESPLIEGUE_PROCESO);
		} catch (Exception ex) {
			generateUpgradeMessage(ex);
			context.setRollbackOnly();
			throw new BPMException(ex, BPMErrorCodes.ERROR_DESPLIEGUE_PROCESO);
		} finally {
			flushContext(context);
		}
	}

	private void generateUpgradeMessages(JpdlException ex)
			throws InternalErrorException, UnknownUserException, BPMException {
		Vector v = new Vector();
		List l = ex.getProblems();
		String message;
		for (Iterator it = l.iterator(); it.hasNext();) {
			Problem p = (Problem) it.next();
			if (p.getLine() == null) {
				message = p.getDescription();
				if (p.getException() != null)
					message = message + ": " + p.getException().toString(); //$NON-NLS-1$
			} else
				message = p.getResource() + " line " + p.getLine() + ": " //$NON-NLS-1$ //$NON-NLS-2$
						+ p.getDescription();
			v.add(message);
		}
		getUserContextCache().setMessages(
				(String[]) v.toArray(new String[v.size()]));
	}

	private void generateUpgradeMessage(Exception ex)
			throws InternalErrorException, UnknownUserException, BPMException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(out);
		ex.printStackTrace(p);
		p.close();
		getUserContextCache().setMessages(new String[] { out.toString() });
	}

	@Override
	protected List handleFindMyTasks() throws Exception {
		JbpmContext context = getContext();
		try {
			Vector<TaskInstance> resultadoFinal = new Vector<TaskInstance>();
			// u88683: solucionem problema de oracle quan n'hi ha més de 1000
			// elements
			// és una restricció ORA-01795: maximum number of expressions in a
			// list is 1000
			int groupIndex = 0;
			List tasks = new LinkedList();
			String[] userGroups = getUserGroups(); // mai null
			while (groupIndex < userGroups.length) {
				// Recuperem les tasques de 1000 en 1000
				Vector ugVector = new Vector(1000);
				for (int max = groupIndex + 1000; groupIndex < max
						&& groupIndex < userGroups.length; groupIndex++) {
					ugVector.add(userGroups[groupIndex]);
				}
				List newTasks = context.getTaskMgmtSession()
						.findPooledTaskInstances(ugVector);
				tasks.addAll(newTasks);
			}

			for (Iterator it = tasks.iterator(); it.hasNext();) {
				org.jbpm.taskmgmt.exe.TaskInstance instance = (org.jbpm.taskmgmt.exe.TaskInstance) it
						.next();
				if (instance.isOpen() && !instance.isCancelled()) {
					try {
						if (instance.getProcessInstance().hasEnded())
						{
							instance.setSignalling(false);
							instance.cancel();
						}
						else
						{
							resultadoFinal.add(VOFactory.newTaskInstance(instance));
						}
					} catch (RuntimeException e) {
						log.warn(
								String.format(
										Messages.getString("BpmEngineImpl.UnableSerializeTask"), instance.getId()), e); //$NON-NLS-1$
					}

				}
			}

			tasks = context.getTaskMgmtSession().findTaskInstances(
					getUserName());

			for (Iterator it = tasks.iterator(); it.hasNext();) {
				org.jbpm.taskmgmt.exe.TaskInstance instance = (org.jbpm.taskmgmt.exe.TaskInstance) it
						.next();
				if (instance.isOpen() && !instance.isCancelled())
					try {
						if (instance.getProcessInstance().hasEnded())
						{
							instance.setSignalling(false);
							instance.cancel();
						}
						else
						{
							resultadoFinal.add(VOFactory.newTaskInstance(instance));
						}
					} catch (RuntimeException e) {
						log.warn(
								String.format(
										Messages.getString("BpmEngineImpl.UnableSerializeTask"), instance.getId()), e); //$NON-NLS-1$
					}

			}
			
			return resultadoFinal;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindMyTasksLightweight() throws Exception {
		JbpmContext context = getContext();
		try {
			Vector resultadoFinal = new Vector();
			// u88683: solucionem problema de oracle quan n'hi ha més de 1000
			// elements
			// és una restricció ORA-01795: maximum number of expressions in a
			// list is 1000
			int groupIndex = 0;
			List tasks = new LinkedList();
			String[] userGroups = getUserGroups(); // mai null
			while (groupIndex < userGroups.length) {
				// Recuperem les tasques de 1000 en 1000
				Vector ugVector = new Vector(1000);
				for (int max = groupIndex + 1000; groupIndex < max
						&& groupIndex < userGroups.length; groupIndex++) {
					ugVector.add(userGroups[groupIndex]);
				}
				List newTasks = context.getTaskMgmtSession()
						.findPooledTaskInstances(ugVector);
				tasks.addAll(newTasks);
			}

			for (Iterator it = tasks.iterator(); it.hasNext();) {
				org.jbpm.taskmgmt.exe.TaskInstance instance = (org.jbpm.taskmgmt.exe.TaskInstance) it
						.next();
				if (instance.isOpen() && !instance.isCancelled()) {
					try {
						if (instance.getProcessInstance().hasEnded())
						{
							instance.setSignalling(false);
							instance.setEnd(new Date());
							instance.cancel();
						}
						else
						{
							resultadoFinal.add(VOFactory
									.newLightweightTaskInstance(instance));
						}
					} catch (RuntimeException e) {
						log.warn(
								String.format(
										Messages.getString("BpmEngineImpl.UnableSerializeTask"), instance.getId()), e); //$NON-NLS-1$
					}
				}
			}

			tasks = context.getTaskMgmtSession().findTaskInstances(
					getUserName());

			for (Iterator it = tasks.iterator(); it.hasNext();) {
				org.jbpm.taskmgmt.exe.TaskInstance instance = (org.jbpm.taskmgmt.exe.TaskInstance) it
						.next();
				if (instance.isOpen() && !instance.isCancelled())
					try {
						if (instance.getProcessInstance().hasEnded())
						{
							instance.setSignalling(false);
							instance.setEnd(new Date());
							instance.cancel();
						}
						else
						{
							resultadoFinal.add(VOFactory
									.newLightweightTaskInstance(instance));
						}
					} catch (RuntimeException e) {
						log.warn(
								String.format(
										Messages.getString("BpmEngineImpl.UnableSerializeTask"), instance.getId()), e); //$NON-NLS-1$
					}
			}

			return resultadoFinal;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindGroupTasks() throws Exception {
		JbpmContext context = getContext();
		try {
			AltresTasques altresTasques = new AltresTasques() {
				public List findAltresTasques(JbpmContext context,
						String usuariId, Collection altresIds) {
					Vector tasques = new Vector();

					Query q = context
							.getSession()
							.createQuery(
									"select distinct ti.id " //$NON-NLS-1$
											+ "from org.jbpm.taskmgmt.exe.TaskInstance ti " //$NON-NLS-1$
											+ "join ti.pooledActors pooledActor " //$NON-NLS-1$
											+ "where pooledActor.actorId in ( :actorIds ) " //$NON-NLS-1$
											+ "and ti.actorId is not null " //$NON-NLS-1$
											+ "and ti.actorId != :myself " //$NON-NLS-1$
											+ "and ti.isSuspended != true " //$NON-NLS-1$
											+ "and ti.isCancelled != true " //$NON-NLS-1$
											+ "and ti.isOpen = true"); //$NON-NLS-1$
					q.setParameterList("actorIds", altresIds); //$NON-NLS-1$
					q.setParameter("myself", usuariId); //$NON-NLS-1$
					for (Iterator i = q.list().iterator(); i.hasNext();) {
						Long taskInstanceId = (Long) i.next();
						org.jbpm.taskmgmt.exe.TaskInstance instance = context
								.getTaskInstance(taskInstanceId.longValue());
						try {
							if (instance == null)
								log.warn(String.format(
										Messages.getString("BpmEngineImpl.UnableLoadTask"), taskInstanceId.longValue())); //$NON-NLS-1$
							else
								tasques.add(VOFactory.newTaskInstance(instance));
						} catch (InternalErrorException e) {
							log.warn(String.format(Messages.getString("BpmEngineImpl.UnableDeserializeTask"), instance.getId()), e); //$NON-NLS-1$
						} catch (RuntimeException e) {
							log.warn(
									String.format(
											Messages.getString("BpmEngineImpl.UnableDeserializeTask"), instance.getId()), e); //$NON-NLS-1$
						}
					}
					return tasques;
				}
			};
			int groupIndex = 0;
			List tasks = new LinkedList();
			String[] userGroups = getUserGroups(); // mai null
			while (groupIndex < userGroups.length) {
				// Recuperem les tasques de 1000 en 1000
				Vector ugVector = new Vector(1000);
				for (int max = groupIndex + 1000; groupIndex < max
						&& groupIndex < userGroups.length; groupIndex++) {
					ugVector.add(userGroups[groupIndex]);
				}
				tasks.addAll(altresTasques.findAltresTasques(context,
						getUserName(), ugVector));
			}
			return tasks;

		} finally {
			flushContext(context);
		}
	}

	@Override
	protected TaskInstance handleStartTask(TaskInstance task) throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			org.jbpm.taskmgmt.exe.TaskInstance ti = context
					.loadTaskInstance(task.getId());
			if (business.canAccess(getUserGroups(), ti)) {
				startAuthenticationLog(ti.getToken());
				if (ti.getStart() == null)
					ti.start(getUserName());
				else
					ti.setActorId(getUserName());
				endAuthenticationLog(ti.getToken());
				context.save(ti);
			} else {
				throw new SecurityException(
						Messages.getString("BpmEngineImpl.NotAuthorizedMessage")); //$NON-NLS-1$
			}
			return VOFactory.newTaskInstance(ti);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected TaskInstance handleAddComment(TaskInstance task, String comment)
			throws Exception {
		JbpmContext jbpmContext = null;
		Session session = null;

		try {
			jbpmContext = getContext();

			session = jbpmContext.getSession();

			org.jbpm.taskmgmt.exe.TaskInstance ti = jbpmContext
					.getTaskInstanceForUpdate(task.getId());
			Comment c = new Comment(getUserName(), comment);
			ti.addComment(c);
			jbpmContext.save(ti);
			return VOFactory.newTaskInstance(ti);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}

	}

	@Override
	protected TaskInstance handleExecuteTask(TaskInstance task,
			String transitionName) throws Exception {
		JbpmContext context = getContext();
		;
		try {
			org.jbpm.taskmgmt.exe.TaskInstance instance = doUpdate(context,
					task);
			startAuthenticationLog(instance.getToken());
			instance.end(transitionName);
			endAuthenticationLog(instance.getToken());
			context.save(instance);
			return VOFactory.newTaskInstance(instance);
		} catch (Exception e) {
			context.setRollbackOnly();
			throw new BPMException(
					Messages.getString("BpmEngineImpl.TransitionError"), e, -1); //$NON-NLS-1$
		} finally {
			flushContext(context);
		}
	}

	private org.jbpm.taskmgmt.exe.TaskInstance doUpdate(JbpmContext context,
			com.soffid.iam.bpm.api.TaskInstance task) throws BPMException,
			InternalErrorException, UnknownUserException {
		ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
		business.setContext(context);

		org.jbpm.taskmgmt.exe.TaskInstance ti = context.loadTaskInstance(task
				.getId());
		if (ti.hasEnded())
		{
			throw new SecurityException(Messages.getString("BpmEngineImpl.TaskFinishedError")); //$NON-NLS-1$

		}
		else if (business.canAccess(getUserGroups(), ti)) {
			startAuthenticationLog(ti.getToken());
			ti.setActorId(task.getActorId());
			ti.setBlocking(task.isBlocking());
			ti.setDescription(task.getDescription());
			ti.setName(task.getName());
			ti.setDueDate(task.getDueDate());
			ti.setEnd(task.getEnd());
			ti.setPriority(task.getPriority());
			ti.setSignalling(task.isSignalling());

			Vector v = new Vector();
			v.addAll(task.getPooledActors());
			// Borrar y modificar pooled actors
			if (ti.getPooledActors() != null) {
				for (Iterator it = ti.getPooledActors().iterator(); it
						.hasNext();) {
					PooledActor actor = (PooledActor) it.next();
					String name = null;
					if (actor.getActorId() != null)
						name = actor.getActorId();
					else if (actor.getSwimlaneInstance() != null)
						name = actor.getSwimlaneInstance().getName();

					if (name != null) {
						if (!v.contains(name)) {
							it.remove();
						}
					}
				}
				// Agregar actores
				for (Iterator it = task.getPooledActors().iterator(); it
						.hasNext();) {
					String key = (String) it.next();
					PooledActor actor = new PooledActor();
					SwimlaneInstance swimlane = ti.getTaskMgmtInstance()
							.getSwimlaneInstance(key);
					if (swimlane != null) {
						actor.setSwimlaneInstance(swimlane);
					} else {
						actor.setActorId(key);
					}
					ti.getPooledActors().add(actor);
				}
			}

			HashMap map = new HashMap();
			map.putAll(task.getVariables());
			// Borrar y modificar variables
			for (Iterator it = ti.getVariables().keySet().iterator(); it
					.hasNext();) {
				String key = (String) it.next();
				Object value = map.get(key);
				ti.setVariable(key, value);
				map.remove(key);
			}
			// Agregar variables
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				Object value = map.get(key);
				ti.setVariable(key, value);
			}
			endAuthenticationLog(ti.getToken());
			context.save(ti);
			return ti;
		} else {
			throw new SecurityException(
					Messages.getString("BpmEngineImpl.NotAuthorizedMessage")); //$NON-NLS-1$
		}
	}

	@Override
	protected TaskInstance handleReserveTask(TaskInstance task)
			throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			org.jbpm.taskmgmt.exe.TaskInstance ti = context
					.loadTaskInstance(task.getId());
			startAuthenticationLog(ti.getToken());
			if (business.canAccess(getUserGroups(), ti)) {
				ti.setActorId(getUserName());
			} else {
				throw new SecurityException(
						Messages.getString("BpmEngineImpl.NotAuthorizedMessage")); //$NON-NLS-1$
			}
			endAuthenticationLog(ti.getToken());
			context.save(ti);
			return VOFactory.newTaskInstance(ti);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected TaskInstance handleDelegateTaskToUser(TaskInstance task,
			String username) throws Exception {
		JbpmContext context = getContext();
		;
		try {
			org.jbpm.taskmgmt.exe.TaskInstance instance = context
					.getTaskInstance(task.getId());
			startAuthenticationLog(instance.getToken());
			instance.setActorId(username);
			endAuthenticationLog(instance.getToken());
			context.save(instance);
			return VOFactory.newTaskInstance(instance);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected void handleUpdate(TaskInstance task) throws Exception {
		JbpmContext context = getContext();
		try {
			doUpdate(context, task);

		} finally {
			flushContext(context);
		}
	}

	@Override
	protected void handleUpdateSwimlane(TaskInstance task, String swimlane,
			String actorIds[]) throws Exception {
		JbpmContext context = getContext();
		ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
		business.setContext(context);

		try {
			org.jbpm.taskmgmt.exe.TaskInstance ti = context
					.loadTaskInstance(task.getId());
			if (business.canAccess(getUserGroups(), ti)) {
				startAuthenticationLog(ti.getToken());
				SwimlaneInstance swimlaneInstance = ti.getTaskMgmtInstance()
						.getSwimlaneInstance(swimlane);
				swimlaneInstance.setPooledActors(actorIds);
				endAuthenticationLog(ti.getToken());
				context.save(ti);
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected ProcessInstance handleGetProcessInstance(TaskInstance task)
			throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.taskmgmt.exe.TaskInstance instance = context
					.loadTaskInstance(task.getId());
			return VOFactory.newProcessInstance(instance.getToken()
					.getProcessInstance());
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected TaskInstance handleCancel(TaskInstance task) throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.taskmgmt.exe.TaskInstance instance = context
					.getTaskInstance(task.getId());
			startAuthenticationLog(instance.getToken());
			instance.cancel();
			endAuthenticationLog(instance.getToken());
			context.save(instance);
			return VOFactory.newTaskInstance(instance);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindTasks(List def, TaskDefinition task, String actor,
			Date processStartDate, Date taskCreationDate, boolean finalizada)
			throws Exception {
		JbpmContext context = getContext();
		List resultado = new Vector();
		Query query = null;
		List searchObjects = null;
		GregorianCalendar calendar = null;

		try {
			query = context.getSession().getNamedQuery("searchTasksInstance"); //$NON-NLS-1$

			if (def != null) {
				try {
					query.setParameterList("processId", ColeccionesUtils //$NON-NLS-1$
							.getValorCampoElemento(def, "getId")); //$NON-NLS-1$
				} catch (Exception e) {
					throw new InvalidParameterException(
							BPMErrorCodes.PARAMETRO_INVALIDO);
				}
			}

			query.setParameter("processInstanceId", new Long(-1)); //$NON-NLS-1$

			if (task != null) {
				query.setParameter("taskId", new Long(task.getId())); //$NON-NLS-1$
			} else {
				query.setParameter("taskId", new Long(-1)); //$NON-NLS-1$
			}

			if (finalizada) {
				query.setParameter("finalizada", "FINALIZADA"); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				query.setParameter("finalizada", null); //$NON-NLS-1$
			}

			query.setParameter("actor", actor); //$NON-NLS-1$

			try {
				if (processStartDate != null) {
					query.setParameter("fechaDesdeProceso", FechaUtils //$NON-NLS-1$
							.establecerFechaInicioDia(processStartDate));
					query.setParameter("fechaHastaProceso", //$NON-NLS-1$
							FechaUtils.establecerFechaFinDia(processStartDate));
				} else {
					calendar = new GregorianCalendar();
					calendar.set(2200, 1, 1);

					query.setParameter("fechaDesdeProceso", new Date(0L)); //$NON-NLS-1$
					query.setParameter("fechaHastaProceso", calendar.getTime()); //$NON-NLS-1$
				}

				if (taskCreationDate != null) {
					query.setParameter("fechaDesde", FechaUtils //$NON-NLS-1$
							.establecerFechaInicioDia(taskCreationDate));
					query.setParameter("fechaHasta", //$NON-NLS-1$
							FechaUtils.establecerFechaFinDia(taskCreationDate));
				} else {
					calendar = new GregorianCalendar();
					calendar.set(2200, 1, 1);

					query.setParameter("fechaDesde", new Date(0L)); //$NON-NLS-1$
					query.setParameter("fechaHasta", calendar.getTime()); //$NON-NLS-1$
				}
			} catch (Exception ex) {
				throw new InvalidParameterException(
						BPMErrorCodes.PARAMETRO_INVALIDO);
			}

			resultado = new Vector();

			for (Iterator it = query.list().iterator(); it.hasNext();) {
				org.jbpm.taskmgmt.exe.TaskInstance instance = (org.jbpm.taskmgmt.exe.TaskInstance) it
						.next();
				try {
					resultado.add(VOFactory.newTaskInstance(instance));
				} catch (RuntimeException e) {
					log.warn(
							String.format(
									Messages.getString("BpmEngineImpl.UnableSerializeTask"), instance.getId()), e); //$NON-NLS-1$
				}
			}
		} finally {
			flushContext(context);
		}

		return resultado;
	}

	@Override
	protected List handleFindTasks(List def, String processId,
			TaskDefinition task, String actor, Date processStartDate,
			Date taskCreationDate, boolean finalizada) throws Exception {
		JbpmContext context = getContext();
		List resultado = new Vector();
		Query query = null;
		List searchObjects = null;
		GregorianCalendar calendar = null;

		try {
			query = context.getSession().getNamedQuery("searchTasksInstance"); //$NON-NLS-1$

			if (def != null) {
				try {
					query.setParameterList("processId", ColeccionesUtils //$NON-NLS-1$
							.getValorCampoElemento(def, "getId")); //$NON-NLS-1$
				} catch (Exception e) {
					throw new InvalidParameterException(
							BPMErrorCodes.PARAMETRO_INVALIDO);
				}
			} else {
				def.add(new Integer(-1));
				query.setParameter("processId", def); //$NON-NLS-1$
			}

			if (!processId.trim().equals("")) { //$NON-NLS-1$
				query.setParameter("processInstanceId", new Long(processId)); //$NON-NLS-1$
			} else {
				query.setParameter("processInstanceId", new Long(-1)); //$NON-NLS-1$
			}

			if (task != null) {
				query.setParameter("taskId", new Long(task.getId())); //$NON-NLS-1$
			} else {
				query.setParameter("taskId", new Long(-1)); //$NON-NLS-1$
			}

			if (finalizada) {
				query.setParameter("finalizada", "FINALIZADA"); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				query.setParameter("finalizada", null); //$NON-NLS-1$
			}

			query.setParameter("actor", actor); //$NON-NLS-1$

			try {
				if (processStartDate != null) {
					query.setParameter("fechaDesdeProceso", FechaUtils //$NON-NLS-1$
							.establecerFechaInicioDia(processStartDate));
					query.setParameter("fechaHastaProceso", //$NON-NLS-1$
							FechaUtils.establecerFechaFinDia(processStartDate));
				} else {
					calendar = new GregorianCalendar();
					calendar.set(2200, 1, 1);

					query.setParameter("fechaDesdeProceso", new Date(0L)); //$NON-NLS-1$
					query.setParameter("fechaHastaProceso", calendar.getTime()); //$NON-NLS-1$
				}

				if (taskCreationDate != null) {
					query.setParameter("fechaDesde", FechaUtils //$NON-NLS-1$
							.establecerFechaInicioDia(taskCreationDate));
					query.setParameter("fechaHasta", //$NON-NLS-1$
							FechaUtils.establecerFechaFinDia(taskCreationDate));
				} else {
					calendar = new GregorianCalendar();
					calendar.set(2200, 1, 1);

					query.setParameter("fechaDesde", new Date(0L)); //$NON-NLS-1$
					query.setParameter("fechaHasta", calendar.getTime()); //$NON-NLS-1$
				}
			} catch (Exception ex) {
				throw new InvalidParameterException(
						BPMErrorCodes.PARAMETRO_INVALIDO);
			}

			resultado = new Vector();

			for (Iterator it = query.list().iterator(); it.hasNext();) {
				org.jbpm.taskmgmt.exe.TaskInstance instance = (org.jbpm.taskmgmt.exe.TaskInstance) it
						.next();
				try {
					resultado.add(VOFactory.newTaskInstance(instance));
				} catch (RuntimeException e) {
					log.warn(e);
				}
			}
		} finally {
			flushContext(context);
		}

		return resultado;
	}

	@Override
	protected String handleGetUI(TaskInstance task) throws Exception {
		JbpmContext context = getContext();

		try {
			org.jbpm.taskmgmt.exe.TaskInstance instance = context
					.getTaskInstance(task.getId());
			org.jbpm.graph.def.ProcessDefinition pd = instance
					.getContextInstance().getProcessInstance()
					.getProcessDefinition();
			FileDefinition fd = pd.getFileDefinition();

			String url = getUIfor(context, pd, instance.getTask().getName());
			if (url != null) {
				byte ba[] = (byte[]) fd.getBytes(url);
				try {
					if (ba != null)
						return new String(ba, "UTF-8"); //$NON-NLS-1$
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}

			String name = "ui/" + task.getName().replaceAll(" ", "[ _\\\\-.]?").replaceAll("\\{", ".") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
					+ "\\..+"; //$NON-NLS-1$
			Pattern p = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
			Map map = fd.getBytesMap();
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (p.matcher(key).matches()) {
					byte ba[] = (byte[]) map.get(key);
					try {
						if (ba != null)
							return new String(ba, "UTF-8"); //$NON-NLS-1$
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
				}
			}
			return null;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected String handleGetUI(ProcessInstance process) throws Exception {
		JbpmContext context = getContext();

		try {
			org.jbpm.graph.exe.ProcessInstance pi = context
					.getProcessInstance(process.getId());
			org.jbpm.graph.def.ProcessDefinition pd = pi.getProcessDefinition();
			FileDefinition fd = pd.getFileDefinition();

			try {
				byte b[] = fd.getBytes("ui/default.zul"); //$NON-NLS-1$
				if (b != null) {
					try {
						return new String(b, "UTF-8"); //$NON-NLS-1$
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
				}
			} catch (JbpmException e) {
				// Page does not exist
			}
			return null;
		} finally {
			flushContext(context);
		}
	}

	private String getUIfor(JbpmContext context,
			org.jbpm.graph.def.ProcessDefinition pd, String taskName) {
		Criteria busqueda = context.getSession().createCriteria(
				UserInterface.class);
		busqueda.add(Restrictions.eq("processDefinitionId", //$NON-NLS-1$
				new Long(pd.getId())));

		List resultado = busqueda.list();

		for (Iterator it = resultado.iterator(); it.hasNext();) {
			UserInterface ui = (UserInterface) it.next();
			try {
				if (ui.getTarea().equals(taskName)
						|| Pattern.matches(ui.getTarea(), taskName))
					return ui.getFileName();
			} catch (PatternSyntaxException e) {
				// Ignore
			}
		}
		return null;
	}

	@Override
	protected TaskDefinition handleGetDefinition(TaskInstance task)
			throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.taskmgmt.exe.TaskInstance instance = context
					.loadTaskInstance(task.getId());
			return VOFactory.newTaskDefinition(instance.getTask());
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleGetPendingTasks(ProcessInstance process)
			throws Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(jbpmContext);
			if (process == null)
				return null;

			org.jbpm.graph.exe.ProcessInstance instance = jbpmContext
					.getProcessInstance(process.getId());
			Vector v = new Vector();

			if (instance.getTaskMgmtInstance() != null
					&& instance.getTaskMgmtInstance().getTaskInstances() != null) {
				for (Iterator it = instance.getTaskMgmtInstance()
						.getTaskInstances().iterator(); it.hasNext();) {
					org.jbpm.taskmgmt.exe.TaskInstance task = (org.jbpm.taskmgmt.exe.TaskInstance) it
							.next();
					if (!task.hasEnded()
							&& business.canAccess(getUserGroups(), task)) {
						try {
							v.add(VOFactory.newTaskInstance(task));
						} catch (RuntimeException e) {
							log.warn(
									String.format(
											Messages.getString("BpmEngineImpl.UnableSerializeTask"), task.getId()), e); //$NON-NLS-1$
						}
					}
				}
			}
			return v;

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}
	}

	protected List findProcessDefinitionsByRole(String name, boolean onlyEnabled)
			throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);
			Vector<ProcessDefinition> resultadoFinal = new Vector();
			for (Iterator it = context.getGraphSession()
					.findLatestProcessDefinitions().iterator(); it.hasNext();) {
				org.jbpm.graph.def.ProcessDefinition definition = (org.jbpm.graph.def.ProcessDefinition) it
						.next();

				TenantModuleDefinition tm = (TenantModuleDefinition) definition.getDefinition(TenantModuleDefinition.class);
				
				if (tm.getTenantId().equals ( Security.getCurrentTenantId()) &&
					business
						.isUserAuthorized(name, getUserGroups(), definition)) {
					com.soffid.iam.bpm.api.ProcessDefinition def = VOFactory
							.newProcessDefinition(definition, context);
					if (def.isEnabled() || !onlyEnabled)
						resultadoFinal.add(def);
				}
			}
			
			Collections.sort( resultadoFinal, new Comparator<ProcessDefinition>() {
				public int compare(ProcessDefinition o1, ProcessDefinition o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			return resultadoFinal;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindProcessDefinitions(String name, boolean onlyEnabled)
			throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);
			Vector resultadoFinal = new Vector();
			for (Iterator it = context.getGraphSession()
					.findLatestProcessDefinitions().iterator(); it.hasNext();) {
				org.jbpm.graph.def.ProcessDefinition definition = (org.jbpm.graph.def.ProcessDefinition) it
						.next();

				TenantModuleDefinition tm = (TenantModuleDefinition) definition.getDefinition(TenantModuleDefinition.class);
				
				if (tm.getTenantId().equals ( Security.getCurrentTenantId()) &&
						(name == null || name.isEmpty() || name.equals(definition.getName())))
				{
					com.soffid.iam.bpm.api.ProcessDefinition def = VOFactory
							.newProcessDefinition(definition, context);
					if (def.isEnabled() || !onlyEnabled)
						resultadoFinal.add(def);
				}
			}
			return resultadoFinal;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindProcessDefinitions(String name,
			es.caib.bpm.vo.PredefinedProcessType type) throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);
			Vector resultadoFinal = new Vector();
						
			if (name == null || name.trim().isEmpty()) {
				List defs = context.getGraphSession()
						.findLatestProcessDefinitions();
				for (Iterator it = defs.iterator(); it.hasNext();) {
					org.jbpm.graph.def.ProcessDefinition definition = (org.jbpm.graph.def.ProcessDefinition) it
							.next();

					TenantModuleDefinition tm = (TenantModuleDefinition) definition
							.getDefinition(TenantModuleDefinition.class);

					if (tm.getTenantId().equals(Security.getCurrentTenantId())
							&& (name == null || name.trim().length() == 0 || name
									.equals(definition.getName()))) {
						ProcessDefinition def = VOFactory
								.newProcessDefinition(definition, context);
						if (def.isEnabled() && type.equals(def.getType())) {
							resultadoFinal.add(def);
						}
					}
				}
			} else {
				org.jbpm.graph.def.ProcessDefinition definition = context
						.getGraphSession().findLatestProcessDefinition(name);
				TenantModuleDefinition tm = (TenantModuleDefinition) definition
						.getDefinition(TenantModuleDefinition.class);

				if (tm.getTenantId().equals(Security.getCurrentTenantId())
						&& (name == null || name.trim().length() == 0 || name
								.equals(definition.getName()))) {
					com.soffid.iam.bpm.api.ProcessDefinition def = VOFactory
							.newProcessDefinition(definition, context);
					if (def.isEnabled() && type.equals(def.getType())) {
						resultadoFinal.add(def);
					}
				}
			}
			return resultadoFinal;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected ProcessInstance handleNewProcess(ProcessDefinition def)
			throws Exception {
		return newProcess(def, true);
	}

	@Override
	protected ProcessInstance handleNewProcess(ProcessDefinition def,
			boolean start) throws Exception {
		JbpmContext context = getContext();
		try {

			org.jbpm.graph.def.ProcessDefinition definition = context
					.getGraphSession().findLatestProcessDefinition(
							def.getName());
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);
			if (!isInternalService()
					&& !business.isUserAuthorized(INITIATOR_ROLE,
							getUserGroups(), definition))
				throw new SecurityException(
						Messages.getString("BpmEngineImpl.NotAuthorizedToMakeProcess")); //$NON-NLS-1$

			ProcessDefinitionProperty prop = getProcessDefinitionDisabledProperty(
					context, definition);
			if (prop != null && "true".equals(prop.getValue())) //$NON-NLS-1$
				throw new BPMException(
						Messages.getString("BpmEngineImpl.ProcessDisabled"), 2); //$NON-NLS-1$

			org.jbpm.graph.exe.ProcessInstance pi = new org.jbpm.graph.exe.ProcessInstance(
					definition);
			if (start) {
				startAuthenticationLog(pi.getRootToken());
				pi.signal();
				endAuthenticationLog(pi.getRootToken());
				context.save(pi);
			}
			return VOFactory.newProcessInstance(pi);
		} catch (Exception e) {
			context.setRollbackOnly();
			if (e instanceof BPMException)
				throw (BPMException) e;
			else
				throw new BPMException(
						Messages.getString("BpmEngineImpl.MakeProcessError"), e, -1); //$NON-NLS-1$
		} finally {
			flushContext(context);
		}
	}

	private ProcessDefinitionProperty getProcessDefinitionDisabledProperty(
			JbpmContext context, org.jbpm.graph.def.ProcessDefinition def) {
		ProcessDefinitionProperty prop;
		Query q = context
				.getSession()
				.createQuery(
						"select pdp " //$NON-NLS-1$
								+ "from com.soffid.iam.bpm.model.ProcessDefinitionProperty pdp " //$NON-NLS-1$
								+ "where pdp.name = 'disabled' and pdp.processDefinitionId=:id "); //$NON-NLS-1$
		q.setParameter("id", new Long(def.getId())); //$NON-NLS-1$
		prop = (ProcessDefinitionProperty) q.uniqueResult();
		return prop;
	}

	@Override
	protected byte[] handleGetProcessDefinitionImage(ProcessDefinition def)
			throws Exception {
		JbpmContext context = getContext();

		byte[] resultado = null;

		try {
			org.jbpm.graph.def.ProcessDefinition definition = context
					.getGraphSession().getProcessDefinition(def.getId());

			return definition.getFileDefinition().getBytes("processimage.jpg"); //$NON-NLS-1$

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected int[] handleGetCoordinates(TaskInstance task) throws Exception {
		Node node = null;
		JbpmContext context = getContext();
		try {
			org.jbpm.taskmgmt.exe.TaskInstance instance = context
					.getTaskInstance(task.getId());
			node = instance.getTask().getTaskNode();

			return getCoordinates(node);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(context);
		}
	}

	private int[] getCoordinates(Node node) throws DocumentException, SAXException {
		org.jbpm.graph.def.ProcessDefinition definition;
		String nodeName;
		XPath xPath;
		byte[] resultado;
		int result[] = new int[4];
		if (node != null) {
			definition = node.getProcessDefinition();

			resultado = definition.getFileDefinition().getBytes("gpd.xml"); //$NON-NLS-1$

			nodeName = node.getName();

			// Hacer el PARSE XML del documento
			org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();
	        reader.setFeature("http://xml.org/sax/features/validation", false); //$NON-NLS-1$
			Document doc = reader.read(new ByteArrayInputStream(resultado));

			xPath = new DefaultXPath("//node[@name='" //$NON-NLS-1$
					+ nodeName.replaceAll("'", "&apos;") + "']"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			Element domNode = (Element) xPath.selectSingleNode(doc);

			if (domNode == null) {
				result[0] = result[1] = result[2] = result[3] = 0;
				;
			} else {
				result[0] = Integer.valueOf(domNode.attribute("x").getValue()) //$NON-NLS-1$
						.intValue();
				result[1] = Integer.valueOf(domNode.attribute("y").getValue()) //$NON-NLS-1$
						.intValue();
				result[2] = Integer.valueOf(
						domNode.attribute("width").getValue()).intValue(); //$NON-NLS-1$
				result[3] = Integer.valueOf(
						domNode.attribute("height").getValue()).intValue(); //$NON-NLS-1$
			}
		}
		return result;
	}

	@Override
	protected int[] handleGetCoordinates(ProcessInstance processVO)
			throws Exception {
		Node node = null;
		JbpmContext context = getContext();
		try {
			org.jbpm.graph.exe.ProcessInstance pi = context
					.getProcessInstance(processVO.getId());
			node = pi.getRootToken().getNode();
			return getCoordinates(node);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindInitiatorProcessDefinitions() throws Exception {
		return findProcessDefinitionsByRole(INITIATOR_ROLE, true);
	}

	@Override
	protected List handleFindObserverProcessDefinitions() throws Exception {
		return findProcessDefinitionsByRole(OBSERVER_ROLE, true);
	}

	@Override
	protected List handleFindSupervisorProcessDefinitions() throws Exception {
		return findProcessDefinitionsByRole(SUPERVISOR_ROLE, true);
	}

	@Override
	protected Map handleGetUIClassesForTask(ProcessDefinition def)
			throws Exception {
		JbpmContext context = getContext();

		try {
			org.jbpm.graph.def.ProcessDefinition process = context
					.getGraphSession().getProcessDefinition(def.getId());
			FileDefinition fd = process.getFileDefinition();
			Map map = fd.getBytesMap();
			Map newMap = new HashMap();
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (key.startsWith("classes/")) { //$NON-NLS-1$
					byte[] ba = (byte[]) map.get(key);
					if (ba != null) {
						String resource = key.substring(8);
						newMap.put(resource, ba);
					}
				}
			}
			return newMap;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleFindTaskDefinitions(ProcessDefinition def)
			throws Exception {
		JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);
			Vector resultadoFinal = new Vector();
			org.jbpm.graph.def.ProcessDefinition definition = context
					.getGraphSession().getProcessDefinition(def.getId());

			if (business.isUserAuthorized(OBSERVER_ROLE, getUserGroups(),
					definition)
					|| business.isUserAuthorized(SUPERVISOR_ROLE,
							getUserGroups(), definition)) {
				{
					for (Iterator it = definition.getNodes().iterator(); it
							.hasNext();) {
						Node n = (Node) it.next();
						if (n instanceof TaskNode) {
							TaskNode tn = (TaskNode) n;
							for (Iterator it2 = tn.getTasks().iterator(); it2
									.hasNext();) {
								Task task = (Task) it2.next();

								resultadoFinal.add(VOFactory
										.newTaskDefinition(task));
							}
						}
					}
				}
			}
			return resultadoFinal;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected ProcessDefinition handleEnableProcessDefinition(
			ProcessDefinition defVO) throws Exception {
		return disableProcessDefinition(defVO, "false"); //$NON-NLS-1$
	}

	@Override
	protected ProcessDefinition handleDisableProcessDefinition(
			ProcessDefinition defVO) throws Exception {
		return disableProcessDefinition(defVO, "true"); //$NON-NLS-1$
	}

	private com.soffid.iam.bpm.api.ProcessDefinition disableProcessDefinition(
			com.soffid.iam.bpm.api.ProcessDefinition defVO, String value)
			throws BPMException, InternalErrorException, UnknownUserException {
		InputStream streamLectura = null;

		JbpmContext context = getContext();
		try {

			org.jbpm.graph.def.ProcessDefinition def = context
					.getGraphSession().loadProcessDefinition(defVO.getId());
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			if (business
					.isUserAuthorized(SUPERVISOR_ROLE, getUserGroups(), def)) {
				ProcessDefinitionProperty prop;
				prop = getProcessDefinitionDisabledProperty(context, def);
				if (prop == null) {
					prop = new ProcessDefinitionProperty();
					prop.setProcessDefinitionId(new Long(def.getId()));
					prop.setName("disabled"); //$NON-NLS-1$
				}
				prop.setValue(value);
				context.getSession().save(prop);
			}
			return VOFactory.newProcessDefinition(def, context);
		} catch (JpdlException ex) {
			generateUpgradeMessages(ex);
			context.setRollbackOnly();
			throw new BPMException(ex, BPMErrorCodes.ERROR_DESPLIEGUE_PROCESO);
		} catch (Exception ex) {
			generateUpgradeMessage(ex);
			context.setRollbackOnly();
			throw new BPMException(ex, BPMErrorCodes.ERROR_DESPLIEGUE_PROCESO);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected void handleOpenDeployParDefinitionTransfer() throws Exception {
		try {
			UserContextCache cache = getUserContextCache();
			if (cache.getOutputStream() != null) {
				cache.getOutputStream().close();
			}

			cache.setTempFile(getTempFile());

			cache.setOutputStream(new FileOutputStream(cache.getTempFile()));
		} catch (Exception ex) {
			throw new BPMException(ex, BPMErrorCodes.ERROR_ENTRADA_SALIDA);
		}
	}

	private synchronized File getTempFile() throws FileNotFoundException,
			IOException, InvalidConfigurationException {
		String jbossTemp = System.getProperty("jboss.server.temp.dir"); //$NON-NLS-1$
		if (jbossTemp == null)
			jbossTemp = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$

		File tmp = new File(new File(jbossTemp), "bpm"); //$NON-NLS-1$
		tmp.mkdirs();

		return File.createTempFile("jbpmwf", ".par", tmp); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected void handleNextDeployParDefinitionPackage(byte filePackage[],
			int length) throws Exception {
		try {
			UserContextCache cache = getUserContextCache();
			cache.getOutputStream().write(filePackage, 0, length);
		} catch (Exception ex) {
			throw new BPMException(ex, BPMErrorCodes.ERROR_ENTRADA_SALIDA);
		}
	}

	@Override
	protected void handleEndDeployParDefinitionTransfer() throws Exception {
		InputStream streamLectura = null;

		try {
			UserContextCache cache = getUserContextCache();
			cache.getOutputStream().close();

			cache.setMessages(deployProcessParDefinition(cache.getTempFile()));

			cache.getTempFile().delete();
			cache.setTempFile(null);
			cache.setOutputStream(null);
		} catch (JpdlException ex) {
			generateUpgradeMessages(ex);
			throw new BPMException(ex, BPMErrorCodes.ERROR_DESPLIEGUE_PROCESO);
		} catch (Exception ex) {
			generateUpgradeMessage(ex);
			throw new BPMException(ex, BPMErrorCodes.ERROR_DESPLIEGUE_PROCESO);
		}
	}

	private String[] deployProcessParDefinition(File tempFile) throws Exception {
		org.jbpm.graph.def.ProcessDefinition definition = null;
		InputStream streamLectura = null;
		UserInterfaceBusiness business = null;
		JbpmContext context = null;

		try {
			context = getContext();

			streamLectura = new FileInputStream(tempFile);

			definition = org.jbpm.graph.def.ProcessDefinition
					.parseParZipInputStream(new ZipInputStream(streamLectura));

			streamLectura.close();
			
			TenantModuleDefinition tmd = new TenantModuleDefinition();
			tmd.setTenantId(Security.getCurrentTenantId());
			definition.addDefinition( tmd );

			context.deployProcessDefinition(definition);

			context.getGraphSession().saveProcessDefinition(definition);

			business = new UserInterfaceBusiness(context);

			String result[] = business.procesarDefinicionUI(tempFile,
					definition);

			return result;
		} catch (Exception e) {
			context.setRollbackOnly();
			throw e;
		} finally {
			flushContext(context);
			if (streamLectura != null) {
				streamLectura.close();
			}
		}
	}

	@Override
	protected String[] handleGetDeployMessages() throws Exception {
		UserContextCache cache = getUserContextCache();
		return cache.getMessages();
	}

	@Override
	protected TaskInstance handleGetTask(long id) throws Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(jbpmContext);

			org.jbpm.taskmgmt.exe.TaskInstance task = jbpmContext
					.getTaskInstance(id);
			if (task == null)
				return null;

			if (business.canAccess(getUserGroups(), task)) {
				return VOFactory.newTaskInstance(task);
			} else {
				return null;
			}
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}
	}

	@Override
	protected JbpmConfiguration handleGetJBpmConfiguration() throws Exception {
		return Configuration.getConfig();
	}

	@Override
	protected JbpmContext handleGetContext() throws Exception {
		Principal p = Security.getPrincipal();
		String user = null;
		if (p != null)
			user = Security.getCurrentUser();
		JbpmContext myContext = Configuration.getConfig().createJbpmContext();
		myContext.setActorId(user);
		return myContext;
	}

	@Override
	protected void handleSignal(ProcessInstance instanceVO) throws Exception {
		signal(instanceVO, null);
	}

	@Override
	protected void handleSignal(ProcessInstance instanceVO,
			String transitionName) throws Exception {
		JbpmContext context = getContext();

		try {
			org.jbpm.graph.exe.ProcessInstance instance = context
					.loadProcessInstance(instanceVO.getId());
			org.jbpm.graph.def.ProcessDefinition definition = instance
					.getProcessDefinition();

			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			if (isInternalService()
					|| business.isUserAuthorized(SUPERVISOR_ROLE,
							getUserGroups(), definition)) {
				startAuthenticationLog(instance.getRootToken());
				if (transitionName != null)
					instance.signal(transitionName);
				else
					instance.signal();
				endAuthenticationLog(instance.getRootToken());
				context.save(instance);
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected boolean handleCanAdmin(ProcessInstance instanceVO)
			throws Exception {
		JbpmContext context = getContext();
		try {

			org.jbpm.graph.exe.ProcessInstance process = context
					.loadProcessInstance(instanceVO.getId());
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			return business.isUserAuthorized(SUPERVISOR_ROLE, getUserGroups(),
					process);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected List handleGetActiveJobs(ProcessInstance process)
			throws Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(jbpmContext);
			if (process == null)
				return null;

			org.jbpm.graph.exe.ProcessInstance instance = jbpmContext
					.getProcessInstance(process.getId());

			if (!business.isUserAuthorized(OBSERVER_ROLE, getUserGroups(),
					instance)
					&& !business.isUserAuthorized(SUPERVISOR_ROLE,
							getUserGroups(), instance))
				return null;

			Vector v = new Vector();

			populateJobs(jbpmContext, instance.getRootToken(), v);
			return v;

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}
	}

	private void populateJobs(JbpmContext jbpmContext,
			org.jbpm.graph.exe.Token token, Vector v) {

		List l = jbpmContext.getJobSession().findJobsByToken(token);
		for (Iterator it = l.iterator(); it.hasNext();) {
			org.jbpm.job.Job j = (org.jbpm.job.Job) it.next();
			v.add(VOFactory.newJob(j));
		}
		for (Iterator it = token.getActiveChildren().values().iterator(); it
				.hasNext();) {
			org.jbpm.graph.exe.Token childToken = (org.jbpm.graph.exe.Token) it
					.next();
			populateJobs(jbpmContext, childToken, v);
		}
	}

	@Override
	protected List handleGetActiveTasks(ProcessInstance process)
			throws Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(jbpmContext);
			if (process == null)
				return null;

			org.jbpm.graph.exe.ProcessInstance instance = jbpmContext
					.getProcessInstance(process.getId());

			Vector v = new Vector();

			boolean canObserve = business.isUserAuthorized(OBSERVER_ROLE,
					getUserGroups(), instance)
					|| business.isUserAuthorized(SUPERVISOR_ROLE,
							getUserGroups(), instance);
			if (instance.getTaskMgmtInstance() != null
					&& instance.getTaskMgmtInstance().getTaskInstances() != null) {
				for (Iterator it = instance.getTaskMgmtInstance()
						.getTaskInstances().iterator(); it.hasNext();) {
					org.jbpm.taskmgmt.exe.TaskInstance task = (org.jbpm.taskmgmt.exe.TaskInstance) it
							.next();
					if (!task.hasEnded()) {
						try {
							if (canObserve
									|| business
											.canAccess(getUserGroups(), task)) {
								v.add(VOFactory.newTaskInstance(task));
							}
						} catch (RuntimeException e) {
							log.warn(
									String.format(
											Messages.getString("BpmEngineImpl.UnableSerializeTask"), task.getId()), e); //$NON-NLS-1$
						}
					}
				}
			}
			return v;

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}
	}

	@Override
	protected List handleGetActiveJobs() throws Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(jbpmContext);

			Query q = jbpmContext.getSession().getNamedQuery("dueDateJobs"); //$NON-NLS-1$
			q.setParameter("now", new Date()); //$NON-NLS-1$
			List l = q.list();

			Vector v = new Vector();
			for (Iterator it = l.iterator(); it.hasNext();) {
				org.jbpm.job.Job j = (org.jbpm.job.Job) it.next();
				org.jbpm.graph.exe.ProcessInstance pi = j.getProcessInstance();
				if (business.isUserAuthorized(SUPERVISOR_ROLE, getUserGroups(),
						pi))
					v.add(VOFactory.newJob(j));
			}

			return v;

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}
	}

	@Override
	protected void handleResumeJob(Job jobvo) throws Exception {
		enableJob(jobvo, true);
	}

	@Override
	protected void handlePauseJob(Job jobvo) throws Exception {
		enableJob(jobvo, false);
	}

	public void enableJob(com.soffid.iam.bpm.api.Job jobvo, boolean enable)
			throws BPMException, InternalErrorException, UnknownUserException {

		JbpmContext context = getContext();

		try {
			org.jbpm.job.Job job = context.getJobSession()
					.getJob(jobvo.getId());
			if (job == null)
				throw new BPMException(
						Messages.getString("BpmEngineImpl.NotExistingTask"), -1); //$NON-NLS-1$
			org.jbpm.graph.exe.ProcessInstance instance = job
					.getProcessInstance();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			if (isInternalService()
					|| business.isUserAuthorized(SUPERVISOR_ROLE,
							getUserGroups(), instance)) {
				startAuthenticationLog(instance.getRootToken());
				job.setSuspended(!enable);
				endAuthenticationLog(instance.getRootToken());
				context.save(instance);
			}
		} finally {
			flushContext(context);
		}

	}

	@Override
	protected void handleRetryJob(Job jobvo) throws Exception {
		JbpmContext context = getContext();

		try {
			org.jbpm.job.Job job = context.getJobSession()
					.getJob(jobvo.getId());
			if (job == null)
				throw new BPMException(
						Messages.getString("BpmEngineImpl.NotExistingTask"), -1); //$NON-NLS-1$
			org.jbpm.graph.exe.ProcessInstance instance = job
					.getProcessInstance();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			if (isInternalService()
					|| business.isUserAuthorized(SUPERVISOR_ROLE,
							getUserGroups(), instance)) {
				startAuthenticationLog(instance.getRootToken());
				job.setSuspended(false);
				job.setRetries(1);
				job.setException(null);
				job.setDueDate(new Date());
				endAuthenticationLog(instance.getRootToken());
				context.save(instance);
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected void handleChangeConfiguration(Map m) throws Exception {
		JbpmContext context = getContext();
		HashSet s = new HashSet(m.keySet());
		try {
			Query q = context.getSession().createQuery("select prop " + //$NON-NLS-1$
					"from com.soffid.iam.bpm.model.DBProperty prop " + //$NON-NLS-1$
					"where prop.app='" + BPM_APPLICATION_ID + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			for (Iterator i = q.list().iterator(); i.hasNext();) {
				DBProperty prop = (DBProperty) i.next();
				String value = (String) m.get(prop.getKey());
				if (value == null)
					context.getSession().delete(prop);
				else {
					prop.setValue(value);
					context.getSession().save(prop);
					s.remove(prop.getKey());
				}
			}
			for (Iterator i = s.iterator(); i.hasNext();) {
				String key = (String) i.next();
				DBProperty prop = new DBProperty();
				prop.setApp(BPM_APPLICATION_ID);
				prop.setKey(key);
				prop.setValue((String) m.get(key));
				context.getSession().save(prop);
			}
			DirectoryFactory.reconfigureDirectory(context);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected Map handleGetConfiguration() throws Exception {
		JbpmContext context = getContext();
		HashMap m = new HashMap();

		try {
			Query q = context.getSession().createQuery("select prop " + //$NON-NLS-1$
					"from com.soffid.iam.bpm.model.DBProperty prop " + //$NON-NLS-1$
					"where prop.app='" + BPM_APPLICATION_ID + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			for (Iterator i = q.list().iterator(); i.hasNext();) {
				DBProperty prop = (DBProperty) i.next();
				m.put(prop.getKey(), prop.getValue());
			}
			return m;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected void handleReindex() throws Exception {
		Indexer.getIndexer().reindexAll();
	}

	@Override
	protected void handlePing() throws Exception {
		// Nothing to do
	}

	@Override
	protected InputStream handleGetResourceAsStream(ProcessInstance process,
			String resource) throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.graph.exe.ProcessInstance instance = context
					.loadProcessInstance(process.getId());
			org.jbpm.graph.def.ProcessDefinition definition = instance
					.getProcessDefinition();

			return definition.getFileDefinition().getInputStream(resource);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected InputStream handleGetResourceAsStream(
			ProcessDefinition processdef, String resource) throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.graph.def.ProcessDefinition definition = context
					.getGraphSession().getProcessDefinition(processdef.getId());

			return definition.getFileDefinition().getInputStream(resource);
		} finally {
			flushContext(context);
		}
	}

	private final class DocumentCollector extends Collector {
		private List result;
		private AtomicReaderContext ctx;
		private Scorer scorer;

		@Override
		public void setScorer(Scorer scorer) throws IOException {
			this.scorer = scorer;
		}

		public void setResult(List resultado) {
			this.result = resultado;
		}

		@Override
		public void setNextReader(AtomicReaderContext ctx) throws IOException {
			this.ctx = ctx;
		}

		@Override
		public void collect(int id) throws IOException {
			org.apache.lucene.document.Document d = ctx.reader().document(id);
			IndexableField f = d.getField("$id"); //$NON-NLS-1$
			if (f != null) {
				long processId = Long.parseLong(f.stringValue());
				try {
					ProcessInstance proc = handleGetProcess(processId);
					if (proc != null) {
						result.add(proc);
					}
				} catch (Exception e) {
					// Ignorar
				}
			}
			
		}

		@Override
		public boolean acceptsDocsOutOfOrder() {
			return false;
		}
	}

	private interface AltresTasques {
		List findAltresTasques(JbpmContext context, String usuariId,
				Collection altresIds);
	}

	@Override
	protected Collection<BPMUser> handleFindUsers(String userName,
			String givenName, String surName, String group) throws Exception {
		LinkedList<Parameter> p = new LinkedList<Parameter>();
		List<String> clauses = new LinkedList<String>();
		StringBuffer query = new StringBuffer();
		StringBuffer clause = new StringBuffer();
		clause.append("select usuari " + //$NON-NLS-1$
				"from com.soffid.iam.model.UserEntity usuari "
				+ "where usuari.tenant.id = :tenantId "); //$NON-NLS-1$
		p.add (new Parameter("tenantId", Security.getCurrentTenantId()));
		
		if (userName != null) {
			clauses.add("upper(usuari.userName) like :userName"); //$NON-NLS-1$
			p.add(new Parameter("userName", userName.toUpperCase())); //$NON-NLS-1$
		}
		if (givenName != null) {
			clauses.add("upper(usuari.firstName) like :givenName"); //$NON-NLS-1$
			p.add(new Parameter("givenName", givenName.toUpperCase())); //$NON-NLS-1$
		}
		if (surName != null) {
			clauses.add("upper(concat(usuari.lastName,' ',usuari.middleName)) like :surName"); //$NON-NLS-1$
			p.add(new Parameter("surName", surName.toUpperCase())); //$NON-NLS-1$
		}
		if (group != null) {
			clauses.add("upper(usuari.primaryGroup.name) like :group"); //$NON-NLS-1$
			p.add(new Parameter("group", group.toUpperCase())); //$NON-NLS-1$
		}
		for (String subClause : clauses) {
			clause.append(" and "); //$NON-NLS-1$
			clause.append(subClause);
		}
		List<UserEntity> result = getUserEntityDao().query(clause.toString(),
				p.toArray(new Parameter[p.size()]));
		if (result.isEmpty()) {
			for (Parameter param : p) {
				if (param.getValue() instanceof String)
					param.setValue("%" + param.getValue() + "%");
			}
		}
		result = getUserEntityDao().query(clause.toString(),
				p.toArray(new Parameter[p.size()]));
		return getUserEntityDao().toBPMUserList(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.caib.bpm.servei.BpmEngineBase#handleUpgradeParFile()
	 */
	@Override
	protected void handleUpgradeParFile(InputStream stream) throws Exception {
		File f = File.createTempFile("soffid", ".par"); //$NON-NLS-1$ //$NON-NLS-2$
		FileOutputStream out = new FileOutputStream(f);

		byte b[] = new byte[4096];
		do {
			int read = stream.read(b);
			if (read < 0)
				break;
			out.write(b, 0, read);
		} while (true);
		out.close();

		JbpmContext context = getContext();
		try {

			UserInterfaceBusiness uib = new UserInterfaceBusiness(context);
			String name = uib.extractName(f);
			String version = uib.extractVersion(f);
			if (name == null || version == null) {
				throw new InternalErrorException(
						Messages.getString("BpmEngineImpl.CannotParseProcess")); //$NON-NLS-1$
			} else {
				org.jbpm.graph.def.ProcessDefinition def = context
						.getGraphSession().findLatestProcessDefinition(name);
				if (def == null) {
					log.info(String
							.format("Installing new process definition %s (%s)", name, version)); //$NON-NLS-1$
					deployProcessParDefinition(f);
				} else {
					ProcessDefinitionPropertyDal dal = new ProcessDefinitionPropertyDal();
					dal.setContext(context);
					String tag = dal.getProcessDefinitionProperty(def.getId(),
							"tag"); //$NON-NLS-1$
					if (isGreater(version, tag)) {
						log.info(String
								.format("Upgrading process %s from version %s to version %s", name, tag, version)); //$NON-NLS-1$
						deployProcessParDefinition(f);
					}
				}
			}
		} finally {
			flushContext(context);
		}

		deployProcessParDefinition(f);
		f.delete();
	}

	/**
	 * @param version
	 * @param tag
	 * @return
	 */
	private boolean isGreater(String version, String tag) {
		if (version.equals(tag))
			return false;
		String versionSplit[] = version.split("[,.-_]"); //$NON-NLS-1$
		String tagSplit[] = version.split("[,.-_]"); //$NON-NLS-1$
		int i;
		for (i = 0; i < versionSplit.length && i < tagSplit.length; i++) {
			if (!versionSplit[i].equals(tagSplit[i])) {
				try {
					int versionNumber = Integer.decode(versionSplit[i]);
					int tagNumber = Integer.decode(tagSplit[i]);
					if (versionNumber > tagNumber)
						return true;
					else if (versionNumber < tagNumber)
						return false;
				} catch (NumberFormatException e) {
					return true;
				}
			}
		}
		if (versionSplit.length == tagSplit.length)
			return true;
		else if (versionSplit.length > tagSplit.length) {
			if (versionSplit[i].equals("SNAPSHOT")) //$NON-NLS-1$
				return false;
			else
				return true;
		} else {
			if (tagSplit[i].equals("SNAPSHOT")) //$NON-NLS-1$
				return true;
			else
				return false;
		}
	}
 	protected boolean handleIsUserInRole(String role) throws Exception {
 		for ( String r: getUserGroups())
 			if (r.equals(role))
				return true;
		return false;
	}

	@Override
	protected Collection<Long> handleFindChildProcesses(Long processId) throws Exception {
		LinkedList<Long> result = new LinkedList<Long>();
		for ( ProcessHierarchyEntity h: getProcessHierarchyEntityDao().findByParent(processId))
		{
			result.add(h.getChildProcess());
		}
		return result;
	}

	@Override
	protected Collection<Long> handleFindParentProceeses(Long processId) throws Exception {
		LinkedList<Long> result = new LinkedList<Long>();
		for ( ProcessHierarchyEntity h: getProcessHierarchyEntityDao().findByChildren(processId))
		{
			result.add(h.getParentProcess());
		}
		return result;
	}

	@Override
	protected void handleLinkProcesses(Long parentProcess, Long childProcess) throws Exception {
		ProcessHierarchyEntity h = getProcessHierarchyEntityDao().newProcessHierarchyEntity();
		h.setChildProcess(childProcess);
		h.setParentProcess(parentProcess);
		getProcessHierarchyEntityDao().create(h);
	}
}
