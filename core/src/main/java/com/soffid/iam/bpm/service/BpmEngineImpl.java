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
import java.util.Arrays;
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
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.ZipInputStream;

import org.apache.catalina.realm.GenericPrincipal;
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
import org.apache.lucene.search.Filter;
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
import org.jbpm.graph.def.Event;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.Node.NodeType;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.Comment;
import org.jbpm.graph.exe.ExecutionContext;
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
import org.jbpm.taskmgmt.log.TaskAssignLog;
import org.json.JSONException;
import org.xml.sax.SAXException;

import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.PagedResult;
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
import com.soffid.iam.bpm.mail.Mail;
import com.soffid.iam.bpm.model.AuthenticationLog;
import com.soffid.iam.bpm.model.DBProperty;
import com.soffid.iam.bpm.model.ProcessDefinitionProperty;
import com.soffid.iam.bpm.model.TenantModule;
import com.soffid.iam.bpm.model.TenantModuleDefinition;
import com.soffid.iam.bpm.model.UserInterface;
import com.soffid.iam.bpm.model.dal.ProcessDefinitionPropertyDal;
import com.soffid.iam.bpm.service.impl.UserContextCache;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.bpm.utils.ColeccionesUtils;
import com.soffid.iam.bpm.utils.FechaUtils;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.HostEntityDao;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.model.NetworkEntityDao;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.ProcessHierarchyEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.UserService;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.bpm.classloader.UIClassLoader;
import es.caib.bpm.exception.BPMErrorCodes;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.exception.InvalidConfigurationException;
import es.caib.bpm.exception.InvalidParameterException;
import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownUserException;

public class BpmEngineImpl extends BpmEngineBase {
	private static final String UI_START_ZUL = "ui/start.zul";
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
		if (Security.getSoffidPrincipal() == null)
			return "nobody"; //$NON-NLS-1$

		String p = null;
		p = Security.getCurrentUser();
		if (p == null)
			return "nobody"; //$NON-NLS-1$
		else if (p.startsWith("*"))
			return p.substring(1);
		else
			return p;
	}

	private String getHolderGroup() {
		if (Security.getSoffidPrincipal() == null)
			return "nobody"; //$NON-NLS-1$

		SoffidPrincipal p = Security.getSoffidPrincipal();
		if (p != null && p instanceof SoffidPrincipal)
			return p.getHolderGroup();
		else
			return null;
	}

	private UserContextCache getUserContextCache()
			throws InternalErrorException, UnknownUserException, BPMException {
		String sessionId;
		String user = getUserName();
		String holderGroup = getHolderGroup();
		
		UserContextCache cached;
		if (user == null) {
			cached = new UserContextCache();
			return cached;
		}
		
		cached = (UserContextCache) getSessionCacheService().getObject( CACHE_TAG );
		if (cached == null) {
			cached = new UserContextCache();
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
		SoffidPrincipal principal = Security.getSoffidPrincipal();
		return principal.getGroupsAndRoles();
	}

	private void flushContext(JbpmContext ctx) {
		if (ctx != null) {
			ctx.setActorId(null);
			try {
				ctx.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
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
					+ "from org.jbpm.graph.exe.ProcessInstance as pi "
					+ "join pi.instances as instance " //$NON-NLS-1$
					+ "where (pi.end is null or pi.end > :oneWeekAgo) and instance.initiator = :initiator and "
					+ "instance.tenantId = :tenantId " 
					+ "order by pi.start desc"); //$NON-NLS-1$ //$NON-NLS-2$
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -7);
			query.setParameter("oneWeekAgo", c.getTime());
			query.setParameter("initiator", Security.getCurrentUser());
			query.setParameter("tenantId", Security.getCurrentTenantId());
			for (Iterator it = query.iterate(); it.hasNext();) {
				org.jbpm.graph.exe.ProcessInstance instance = (org.jbpm.graph.exe.ProcessInstance) it
						.next();
				if ( getProcessHierarchyEntityDao().findByChildren(instance.getId()).isEmpty())
				{
					// Does not have parent process
					ProcessInstance proc = VOFactory
							.newProcessInstance(context, getProcessHierarchyEntityDao(), instance);
					resultadoFinal.add(proc);
					if (  instance.hasEnded())
					{
						// Check if every children has ended
						for( ProcessHierarchyEntity id: getProcessHierarchyEntityDao().findByParent(instance.getId()))
						{
							org.jbpm.graph.exe.ProcessInstance child = context.getProcessInstance(id.getChildProcess());
							if (! child.hasEnded())
							{
								proc.setCurrentTask("...");
								break;
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
	protected ProcessDefinition handleGetProcessDefinition(
			ProcessInstance process) throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.graph.def.ProcessDefinition definition =
					context.getGraphSession().getProcessDefinition(process.getProcessDefinition());
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
			return VOFactory.newProcessInstance(context, getProcessHierarchyEntityDao(),instance);
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
			Date sinceStartDate, Date untilStartDate,
			Date sinceEndDate, Date untilEndDate, boolean finished)
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
				String dataInici0 = null, dataFi = null;

				TopDocs hits;
				BooleanFilter b = new BooleanFilter();
				boolean complexQuery = false;

				// Start date
				if (sinceStartDate != null && untilStartDate != null) { //$NON-NLS-1$
					TermRangeFilter fstart = new TermRangeFilter("$startDate", //$NON-NLS-1$
							new BytesRef(sdf.format(sinceStartDate)), 
							new BytesRef(sdf.format(untilStartDate)), 
							true, true); // inclusiu
					b.add(new FilterClause(fstart, BooleanClause.Occur.MUST));
					complexQuery = true;
				}
				else if (sinceStartDate != null) { //$NON-NLS-1$
					TermRangeFilter fstart = new TermRangeFilter("$startDate", //$NON-NLS-1$
							new BytesRef(sdf.format(sinceStartDate)), 
							new BytesRef("9999"), 
							true, true); // inclusiu
					b.add(new FilterClause(fstart, BooleanClause.Occur.MUST));
					complexQuery = true;
				}
				else if (untilStartDate != null) { //$NON-NLS-1$
					TermRangeFilter fstart = new TermRangeFilter("$startDate", //$NON-NLS-1$
							new BytesRef("000"), 
							new BytesRef(sdf.format(untilStartDate)),
							true, true); // inclusiu
					b.add(new FilterClause(fstart, BooleanClause.Occur.MUST));
					complexQuery = true;
				}

				// End date
				if (sinceEndDate != null && untilEndDate != null) { //$NON-NLS-1$
					TermRangeFilter fEnd = new TermRangeFilter("$endDate", //$NON-NLS-1$
							new BytesRef(sdf.format(sinceEndDate)), 
							new BytesRef(sdf.format(untilEndDate)), 
							true, true); // inclusiu
					b.add(new FilterClause(fEnd, BooleanClause.Occur.MUST));
					complexQuery = true;
				}
				else if (sinceEndDate != null) { //$NON-NLS-1$
					TermRangeFilter fEnd = new TermRangeFilter("$endDate", //$NON-NLS-1$
							new BytesRef(sdf.format(sinceEndDate)), 
							new BytesRef("9999"), 
							true, true); // inclusiu
					b.add(new FilterClause(fEnd, BooleanClause.Occur.MUST));
					complexQuery = true;
				}
				else if (untilEndDate != null) { //$NON-NLS-1$
					TermRangeFilter fEnd = new TermRangeFilter("$endDate", //$NON-NLS-1$
							new BytesRef("000"), 
							new BytesRef(sdf.format(untilEndDate)),
							true, true); // inclusiu
					b.add(new FilterClause(fEnd, BooleanClause.Occur.MUST));
					complexQuery = true;
				}

				TermsFilter fTenant = new TermsFilter( new Term("$tenant", Long.toString( Security.getCurrentTenantId() )) );
				b.add(new FilterClause(fTenant, BooleanClause.Occur.MUST));
				complexQuery = true;

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
					TenantModule tm = (TenantModule) p.getInstance(TenantModule.class);
					if (tm == null && Security.getCurrentTenantName().equals(Security.getMasterTenantName()) ||
							tm != null && tm.getTenantId() != null && Security.getCurrentTenantId() == tm.getTenantId().longValue())
					{
						if (business.isUserAuthorized(OBSERVER_ROLE,
								getUserGroups(), p.getProcessDefinition())
								|| business.isUserAuthorized(SUPERVISOR_ROLE,
										getUserGroups(), p.getProcessDefinition()))
							resultado.add(VOFactory.newProcessInstance(context, getProcessHierarchyEntityDao(),p));
					}
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
									.newProcessInstance(context, getProcessHierarchyEntityDao(),instance));
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
			if (isInternalService()
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
		return getProcessInstance2(id, false);
	}

	public ProcessInstance getProcessInstance2(long id, boolean lightweight)
			throws InternalErrorException, BPMException, UnknownUserException, Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(jbpmContext);

			org.jbpm.graph.exe.ProcessInstance process = jbpmContext
					.getProcessInstance(id);
			if (process == null)
				return null;

			TenantModule tm = (TenantModule) process.getInstance(TenantModule.class);
			if (tm == null && Security.getCurrentTenantName().equals(Security.getMasterTenantName()) ||
					tm != null && tm.getTenantId() != null && Security.getCurrentTenantId() == tm.getTenantId().longValue())
			{

				org.jbpm.graph.def.ProcessDefinition definition = process
						.getProcessDefinition();
	
				if (!isInternalService()
						&& !business.isUserAuthorized(OBSERVER_ROLE, getUserGroups(), definition)
						&& !business.isUserAuthorized(SUPERVISOR_ROLE, getUserGroups(), definition)) 
				{
					if (tm.getInitiator() != null && Security.getCurrentUser().equals(tm.getInitiator()))
						return VOFactory.newProcessInstance(jbpmContext, getProcessHierarchyEntityDao(),process);
					Collection list = process.getTaskMgmtInstance()
							.getTaskInstances();
					for (Iterator it = list.iterator(); it.hasNext();) {
						org.jbpm.taskmgmt.exe.TaskInstance ti = (org.jbpm.taskmgmt.exe.TaskInstance) it
								.next();
						if (business.canAccess(getUserGroups(), ti)) {
							return VOFactory.newProcessInstance2(jbpmContext, getProcessHierarchyEntityDao(),process, lightweight);
						}
					}
					for (ProcessHierarchyEntity parentProcess: getProcessHierarchyEntityDao().findByChildren(id))
					{
						if (getProcessInstance2(parentProcess.getParentProcess(), true) != null)
							return VOFactory.newProcessInstance2(jbpmContext, getProcessHierarchyEntityDao(), process, lightweight);
					}
					return null;
				} else {
					return VOFactory.newProcessInstance2(jbpmContext, getProcessHierarchyEntityDao(), process, lightweight);
				}
			}
			else
				return null;
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(jbpmContext);
		}
	}

	@Override
	protected ProcessInstance handleGetProcessLightweight(long id) throws Exception {
		return getProcessInstance2(id, true);
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
			parseLog(context, process, parsedLogs, process.getRootToken(), false);
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

	@Override
	protected ProcessLog[] handleGetTaskLog(TaskInstance instanceVO)
			throws Exception {
		JbpmContext context = getContext();
		try {
			if (instanceVO.isDummyTask())
				return new ProcessLog[0];

			org.jbpm.taskmgmt.exe.TaskInstance task = context.getTaskInstance(instanceVO.getId());
			LinkedList parsedLogs = new LinkedList();

			for (ProcessHierarchyEntity parent: getProcessHierarchyEntityDao().findByChildren(task.getProcessInstance().getId()))
			{
				long processId = parent.getParentProcess().longValue();
				addSubprocessLog(context, task.getProcessInstance(), parsedLogs, processId, 0);
			}

			parseLog(context, task.getProcessInstance(), parsedLogs, task.getToken(), true );
			Collections.sort(parsedLogs, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					ProcessLog l1 = (ProcessLog) arg0;
					ProcessLog l2 = (ProcessLog) arg1;
					return l1.getDate().compareTo(l2.getDate());
				}
			});
			
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
				ProcessInstance procvo = VOFactory.newProcessInstance(context, getProcessHierarchyEntityDao(),proc);
				ProcessLog log = new ProcessLog();
				log.setDate(procvo.getStart());
				log.setUser(""); //$NON-NLS-1$
				log.setProcessId(process.getId());
				log.setAction(Messages.getString("BpmEngineImpl.StartProcess")+": "+ //$NON-NLS-1$ //$NON-NLS-2$
						procvo.getId()+" - "+procvo.getDescription()); //$NON-NLS-1$
				
				parsedLogs.add(position++, log);

				LinkedList<ProcessLog> parsedLogs2 = new LinkedList<ProcessLog>();
				parseLog(context, proc, parsedLogs2, proc.getRootToken(), false);
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
						pl.setAction("> "+pl.getAction()); //$NON-NLS-1$
						parsedLogs.add(position++, pl);
					}
				}

			} catch (Exception e) {
			}
		}
	}

	private void parseLog(JbpmContext context,
			org.jbpm.graph.exe.ProcessInstance process, LinkedList parsedLogs,
			org.jbpm.graph.exe.Token t,
			boolean parentOnly) {
		Criteria criteria = null;

		
		if (t.hasParent() && parentOnly && t.getParent() != null)
			parseLog(context, process, parsedLogs, t.getParent(), parentOnly);
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
		
		if (! parentOnly)
		{
			for (Iterator it2 = t.getChildren().values().iterator(); it2.hasNext();) {
				org.jbpm.graph.exe.Token childToken = (org.jbpm.graph.exe.Token) it2
						.next();
				parseLog(context, process, parsedLogs, childToken, parentOnly);
			}
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
		} else 	if (pl instanceof TaskAssignLog) {
			TaskAssignLog tal = (TaskAssignLog) pl;
			if (tal.getTaskOldActorId() != null && 
					tal.getTaskNewActorId() != null &&
					!tal.getTaskNewActorId().equals(tal.getTaskOldActorId()))
			{
				logLine.setAction(String.format(Messages.getString("BpmEngineImpl.AssignedTo"), //$NON-NLS-1$
						tal.getTaskNewActorId())); 
				parsedLogs.add(logLine);
			}
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
			if (task.isDummyTask())
				return task;
			
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

		jbpmContext = getContext();
		try {
			storeDummyTask(task);

			session = jbpmContext.getSession();

			
			if ( task.getId() == 0)
			{
				org.jbpm.graph.exe.ProcessInstance proc = jbpmContext.getProcessInstance(task.getProcessId());
				org.jbpm.graph.exe.Token token = proc.getRootToken();
				Comment c = new Comment(getUserName(), comment);
				token.addComment(c);
				jbpmContext.save(proc);
				return task;
			}
			else
			{
				org.jbpm.taskmgmt.exe.TaskInstance ti = jbpmContext
						.getTaskInstanceForUpdate(task.getId());
				Comment c = new Comment(getUserName(), comment);
				ti.addComment(c);
				jbpmContext.save(ti);
				return VOFactory.newTaskInstance(ti);
			}
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
		
		storeDummyTask(task);

		org.jbpm.graph.exe.ProcessInstance process = context.getProcessInstance(task.getProcessId());
		if (process == null)
			throw new InternalErrorException("Unable to find process "+task.getProcessId());
		
		if (task.getId() == 0L)
		{
			org.jbpm.graph.exe.Token token = process.getRootToken();
			if (! token.getNode().getNodeType().equals( NodeType.StartState))
				throw new UserWorkflowException( String.format("Task is already finished"));
			try {
				ExecutionContext ctx = new ExecutionContext(token);
				startAuthenticationLog(token);
				HashMap map = new HashMap();
				map.putAll(task.getVariables());
				// Borrar y modificar variables
				for (Iterator it = process.getContextInstance().getVariables(token).keySet().iterator(); it
						.hasNext();) {
					String key = (String) it.next();
					Object value = map.get(key);
					process.getContextInstance().setVariable(key, value, token);
					map.remove(key);
				}
				// Agregar variables
				for (Iterator it = map.keySet().iterator(); it.hasNext();) {
					String key = (String) it.next();
					Object value = map.get(key);
					process.getContextInstance().setVariable(key, value, token);
				}
				token.signal(transitionName);
				endAuthenticationLog(token);
				context.save(process);
				task.setEnd(new Date());
				return task;
			} catch (Exception e) {
				context.setRollbackOnly();
				throw new BPMException(
						Messages.getString("BpmEngineImpl.TransitionError"), e, -1); //$NON-NLS-1$
			} finally {
				flushContext(context);
			}
		}
		else
		{
			org.jbpm.taskmgmt.exe.TaskInstance instance = context.getTaskInstance(task.getId());
			if (instance == null)
				throw new InternalErrorException("Unable to find process "+task.getId());
			if (instance.hasEnded() || instance.isCancelled())
				throw new UserWorkflowException( String.format("Task %d is already finished", instance.getId()));
			try {
				instance = doUpdate(context, task);
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
			if ( task.isDummyTask())
				throw new InternalErrorException("Cannot reserve initial task");
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
		try {
			if ( task.isDummyTask())
				throw new InternalErrorException("Cannot reserve initial task");
			org.jbpm.taskmgmt.exe.TaskInstance instance = context
					.getTaskInstance(task.getId());
			startAuthenticationLog(instance.getToken());
			instance.setActorId(username);
			
			// Send email
			Mail mailAction = new Mail();
			mailAction.setTemplate("delegate");
			ExecutionContext ctx = new ExecutionContext(instance.getToken());
			ctx.setTaskInstance(instance);
			mailAction.execute(ctx);
			
			endAuthenticationLog(instance.getToken());
			context.save(instance);
			return VOFactory.newTaskInstance(instance);
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected TaskInstance handleUpdate(TaskInstance task) throws Exception {
 		JbpmContext context = getContext();
		try {
			if (task.isDummyTask())
				storeDummyTask(task);
			else
				doUpdate(context, task);
			return task;

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
			if ( !task.isDummyTask())
			{
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
			if (task.isDummyTask())
			{
				org.jbpm.graph.def.ProcessDefinition def = context.getGraphSession().getProcessDefinition(task.getProcessDefinition());
				ProcessInstance proc = new ProcessInstance();
				proc.setComments(new LinkedList<com.soffid.iam.bpm.api.Comment>());
				proc.setDescription(def.getName());
				proc.setDummyProcess(true);
				proc.setId(0);
				proc.setProcessClassLoader(VOFactory.getClassLoader(def));
				proc.setStart(new Date());
				proc.setVariables(new HashMap<String, Object>());
				proc.setProcessDefinition(task.getProcessDefinition());
				return proc;
			}
			else if (task.getId() == 0L)
			{
				org.jbpm.graph.exe.ProcessInstance proc = context.getProcessInstance(task.getProcessId());
				return VOFactory.newProcessInstance(context, getProcessHierarchyEntityDao(),proc);
			}
			else
			{
				org.jbpm.taskmgmt.exe.TaskInstance instance = context
						.loadTaskInstance(task.getId());
				return VOFactory.newProcessInstance(context, getProcessHierarchyEntityDao(),instance.getToken()
						.getProcessInstance());
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected TaskInstance handleCancel(TaskInstance task) throws Exception {
		JbpmContext context = getContext();
		try {
			if (!task.isDummyTask())
			{
				org.jbpm.taskmgmt.exe.TaskInstance instance = context
						.getTaskInstance(task.getId());
				startAuthenticationLog(instance.getToken());
				instance.cancel();
				endAuthenticationLog(instance.getToken());
				context.save(instance);
				return VOFactory.newTaskInstance(instance);
			}
			else
				return task;
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
			if (task.isDummyTask())
			{
				org.jbpm.graph.def.ProcessDefinition pd = context.getGraphSession().getProcessDefinition(task.getProcessDefinition());
				FileDefinition fd = pd.getFileDefinition();
				if (fd.hasFile(UI_START_ZUL))
				{
					byte ba[] = (byte[]) fd.getBytes(UI_START_ZUL);
					try {
						return new String(ba, "UTF-8"); //$NON-NLS-1$
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
				}
				else
					throw new InternalErrorException("Cannot get resource "+UI_START_ZUL);
			}
			else
			{
				
				org.jbpm.taskmgmt.exe.TaskInstance instance = context
						.getTaskInstance(task.getId());
				org.jbpm.graph.def.ProcessDefinition pd = instance
						.getContextInstance().getProcessInstance()
						.getProcessDefinition();
				FileDefinition fd = pd.getFileDefinition();
				
				String url = getUIfor(context, pd, instance.getTask().getName());
				if (url != null && fd.hasFile(url)) {
					try {
						byte ba[] = (byte[]) fd.getBytes(url);
						if (ba != null)
							return new String(ba, "UTF-8"); //$NON-NLS-1$
						else
							log.warn("Cannot get resource "+url);
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
				log.warn("Cannot get resource "+name);
				return null;
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected String handleGetUI(ProcessInstance process) throws Exception {
		JbpmContext context = getContext();

		try {
			org.jbpm.graph.def.ProcessDefinition pd = context.getGraphSession().getProcessDefinition(process.getProcessDefinition());
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
		log.warn("Cannot get UI definition for "+taskName);
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
			for (Iterator it = context.getSession()
					.getNamedQuery("GraphSession.findLatestProcessDefinitionQuery2")
					.setLong("tenant", Security.getCurrentTenantId())
					.iterate();
					it.hasNext();) {
				Object[] row = (Object[]) it.next();
				String nam = (String) row[0];
				Integer version = (Integer) row[1];
				org.jbpm.graph.def.ProcessDefinition definition = 
						context.getGraphSession().findProcessDefinition(nam, version.intValue());

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
			return newProcessInstance(context, definition, start);
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

	private ProcessInstance newProcessInstance(JbpmContext context, org.jbpm.graph.def.ProcessDefinition definition,
			boolean start) throws BPMException, InternalErrorException, UnknownUserException {
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
		return VOFactory.newProcessInstance(context, getProcessHierarchyEntityDao(),pi);
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

			FileDefinition fileDefinition = definition.getFileDefinition();
			if (fileDefinition == null || ! fileDefinition.hasFile("processimage.jpg"))
				return null;
			else
				return fileDefinition.getBytes("processimage.jpg"); //$NON-NLS-1$
		} catch (NullPointerException e) {
			return null;
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected byte[] handleGetProcessDefinitionIcon(Long definitionId)
			throws Exception {
		JbpmContext context = getContext();

		byte[] resultado = null;

		try {
			org.jbpm.graph.def.ProcessDefinition definition = context
					.getGraphSession().getProcessDefinition(definitionId);

			FileDefinition fileDefinition = definition.getFileDefinition();
			if (fileDefinition == null)
				return null;
			else if (fileDefinition.hasFile("icon.svg"))
				return fileDefinition.getBytes("icon.svg"); //$NON-NLS-1$
			else if (fileDefinition.hasFile("icon.jpge"))
				return fileDefinition.getBytes("icon.jpeg"); //$NON-NLS-1$
			else if (fileDefinition.hasFile("icon.png"))
				return fileDefinition.getBytes("icon.png"); //$NON-NLS-1$
			else
				return null;
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
    		audit(def, value.equals("true")?"D": "E");
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

			Set<String> taskNames = new HashSet<String>();
			for (Node node: definition.getNodes())
			{
				if (node instanceof TaskNode)
				{
					
					Collection<Task> tasks = (Collection<Task>)((TaskNode) node).getTasks();
					if (tasks == null)
						throw new InternalErrorException("Task node "+node.getName()+" does not contain any task");
					for ( Task task: tasks)
					{
						if (taskNames.contains(task.getName()))
							throw new InternalErrorException (String.format(Messages.getString("BpmEngineImpl.duplicatedTask"),task.getName())); //$NON-NLS-1$
						taskNames.add(task.getName());
					}
				}
			}
			
			context.deployProcessDefinition(definition);

			context.getGraphSession().saveProcessDefinition(definition);

			business = new UserInterfaceBusiness(context);

			String result[] = business.procesarDefinicionUI(tempFile,
					definition);

			ProcessDefinitionPropertyDal dal = new ProcessDefinitionPropertyDal();
			SoffidPrincipal p = Security.getSoffidPrincipal();
			if (p != null)
			{
                ProcessDefinitionProperty prop = new ProcessDefinitionProperty();
                prop.setProcessDefinitionId(
                		new Long(definition.getId()));
                prop.setName("author"); //$NON-NLS-1$
                prop.setValue(p.getUserName());
                context.getSession().save(prop);
			}
            ProcessDefinitionProperty prop2 = new ProcessDefinitionProperty();
            prop2.setProcessDefinitionId(
            		new Long(definition.getId()));
            prop2.setName("deployed"); //$NON-NLS-1$
            prop2.setValue(Long.toString(System.currentTimeMillis()));
            context.getSession().save(prop2);


            // Audit deployment
    		audit(definition, "C");
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

	public void audit(org.jbpm.graph.def.ProcessDefinition definition, String action) {
		Audit auditoria = new Audit();
		auditoria.setAction(action); //$NON-NLS-1$
		auditoria.setAuthor(Security.getCurrentAccount());
		auditoria.setConfigurationParameter( definition.getName());
		auditoria.setObject("JBPM_PROCESSDEFINITON"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
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

			if (isInternalService() || business.canAccess(getUserGroups(), task)) {
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
		Principal p = Security.getSoffidPrincipal();
		String user = null;
		if (p != null)
			user = Security.getCurrentUser();
		JbpmContext myContext = Configuration.getConfig().createJbpmContext();
		myContext.setActorId(user);
		return myContext;
	}

	@Override
	protected void handleSignal(ProcessInstance instanceVO) throws Exception {
		handleSignal(instanceVO, null);
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
							} else {
								log.info("Cannot query process "+process.getId()+". Looking for parent processes");
								
								for (ProcessHierarchyEntity parentProcess: getProcessHierarchyEntityDao().findByChildren(process.getId()))
								{
									log.info("Cannot query process "+process.getId()+". Looking for parent process "+parentProcess.getParentProcess());
									if (handleGetProcess(parentProcess.getParentProcess()) != null)
									{
										v.add( VOFactory.newTaskInstance(task) );
										break;
									}
								}
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
				{
					TenantModule tm = (TenantModule) pi.getInstance(TenantModule.class);
					if (tm == null && Security.getCurrentTenantName().equals(Security.getMasterTenantName()) ||
							tm != null && tm.getTenantId() != null && Security.getCurrentTenantId() == tm.getTenantId().longValue())
						v.add(VOFactory.newJob(j));
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
	protected List handleGetAllJobs() throws Exception {
		JbpmContext jbpmContext = null;

		try {
			jbpmContext = getContext();
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(jbpmContext);

			Query q = jbpmContext.getSession().createQuery(
					"select job\n" + 
					"      from org.jbpm.job.Job as job\n" + 
					"      order by job.dueDate asc"); //$NON-NLS-1$
			List l = q.list();

			Vector v = new Vector();
			for (Iterator it = l.iterator(); it.hasNext();) {
				org.jbpm.job.Job j = (org.jbpm.job.Job) it.next();
				org.jbpm.graph.exe.ProcessInstance pi = j.getProcessInstance();
				if (business.isUserAuthorized(SUPERVISOR_ROLE, getUserGroups(),
						pi))
				{
					TenantModule tm = (TenantModule) pi.getInstance(TenantModule.class);
					if (tm == null && Security.getCurrentTenantName().equals(Security.getMasterTenantName()) ||
							tm != null && tm.getTenantId() != null && Security.getCurrentTenantId() == tm.getTenantId().longValue())
						v.add(VOFactory.newJob(j));
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
					ProcessInstance proc = handleGetProcessLightweight(processId);
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
			if ( CustomDialect.isOracle() )
				clauses.add("upper ( usuari.lastName ) like :surName"); //$NON-NLS-1$
			else
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
			f.delete();
		}
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

	@Override
	protected TaskInstance handleCreateDummyTask(long processDefinitionId) throws Exception {
		JbpmContext context = getContext();
		try {
			org.jbpm.graph.def.ProcessDefinition definition = context.getGraphSession().getProcessDefinition(processDefinitionId);
			if (definition == null)
				return null;
			
			definition = context.getGraphSession().findLatestProcessDefinition(definition.getName());

			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);
			if (!isInternalService()
					&& !business.isUserAuthorized(INITIATOR_ROLE,
							getUserGroups(), definition))
				throw new SecurityException(
						Messages.getString("BpmEngineImpl.NotAuthorizedToMakeProcess")); //$NON-NLS-1$
			
			if (definition.getFileDefinition().hasFile(UI_START_ZUL))
			{
				TaskInstance task = new TaskInstance();
				task.setActorId(Security.getCurrentUser());
				task.setBlocking(false);
				task.setCancelled(false);
				task.setCreate(new Date());
				task.setDescription(definition.getDescription());
				task.setDummyTask(true);
				task.setId(0);
				task.setName(definition.getName());
				task.setOpen(true);
				task.setPooledActors(new HashSet<String>());
				task.setPriority(0);
				task.setProcessClassLoader( VOFactory.getClassLoader(definition));
				task.setProcessDefinition(definition.getId());
				task.setProcessId(0);
				task.setProcessName(definition.getName());
				task.setSignalling(true);
				task.setStart(new Date());
				task.setVariables(new HashMap());
				List<Transition> transitions = definition.getStartState().getLeavingTransitions();
				String[] transitionNames = new String[transitions.size()];
				for (int i = 0; i < transitions.size(); i++)
					transitionNames[i] = transitions.get(i).getName();
				Arrays.sort(transitionNames);
				task.setTransitions(transitionNames);
				return task;
			}
			else
				return null;
		} finally {
			flushContext(context);
		}
	}

	private void storeDummyTask (TaskInstance task) throws InternalErrorException, BPMException, UnknownUserException
	{
		JbpmContext context = getContext();
		try {
			if (task.isDummyTask())
			{
				org.jbpm.graph.def.ProcessDefinition definition = context.getGraphSession().getProcessDefinition(task.getProcessDefinition());
				definition = context.getGraphSession().findLatestProcessDefinition(definition.getName());
				ProcessInstance pi = newProcessInstance(context, definition, false);
				org.jbpm.graph.exe.ProcessInstance proc = context.getGraphSession().getProcessInstance(pi.getId());
				startAuthenticationLog(proc.getRootToken());
				ExecutionContext ctx = new ExecutionContext(proc.getRootToken());
				for ( Object s: task.getVariables().keySet())
				{
					ctx.setVariable(s.toString(), task.getVariables().get(s));
				}
				endAuthenticationLog(proc.getRootToken());
				context.save(proc);
				task.setDummyTask(false);
				task.setProcessId(proc.getId());
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected int handleCountMyTasks() throws Exception {
		JbpmContext context = getContext();
		try {
			int count = 0;
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
				Query q = context.getSession().getNamedQuery("TaskMgmtSession.countPooledTaskInstancesByActorIds");
				q.setParameterList("actorIds", ugVector);
				q.setParameter("tenant", Security.getCurrentTenantId());
				for (Object o : q.list()) {
					if (o instanceof Integer)
						count += ((Integer) o).intValue();
					else if (o instanceof Long)
						count += ((Long) o).intValue();
				}
			}

			Query q = context.getSession().getNamedQuery("TaskMgmtSession.countTaskInstancesByActorId");
			q.setParameter("actorId", getUserName());
			q.setParameter("tenant", Security.getCurrentTenantId());
			for (Object o : q.list()) {
				if (o instanceof Integer)
					count += ((Integer) o).intValue();
				else if (o instanceof Long)
					count += ((Long) o).intValue();
			}

			return count;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected int handleCountNewTasks() throws Exception {
		JbpmContext context = getContext();
		try {
			int count = 0;
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
				Query q = context.getSession().getNamedQuery("TaskMgmtSession.countPooledNewTaskInstancesByActorIds");
				q.setParameterList("actorIds", ugVector);
				q.setParameter("tenant", Security.getCurrentTenantId());
				for (Object o : q.list()) {
					if (o instanceof Integer)
						count += ((Integer) o).intValue();
					else if (o instanceof Long)
						count += ((Long) o).intValue();
				}
			}

			Query q = context.getSession().getNamedQuery("TaskMgmtSession.countNewTaskInstancesByActorId");
			q.setParameter("actorId", getUserName());
			q.setParameter("tenant", Security.getCurrentTenantId());
			for (Object o : q.list()) {
				if (o instanceof Integer)
					count += ((Integer) o).intValue();
				else if (o instanceof Long)
					count += ((Long) o).intValue();
			}

			return count;
		} finally {
			flushContext(context);
		}
	}
	
	@Override
	protected PagedResult<com.soffid.iam.bpm.api.ProcessDefinition> handleFindProcessDefinitionByTextAndJsonQuery(
			java.lang.String text, 
			java.lang.String jsonQuery, 
			java.lang.Integer start, 
			java.lang.Integer pageSize) throws Exception {
		JbpmContext context = getContext();
		try {
			AbstractExpression expr = ExpressionParser.parse(jsonQuery);
			List <ProcessDefinition> r = new LinkedList<>();
			
			List<ProcessDefinition> s = handleFindInitiatorProcessDefinitions();
			for (ProcessDefinition def: s) {
				if (text == null || text.trim().isEmpty() || def.getName().toLowerCase().contains(text.toLowerCase())) {
					if (expr.evaluate(def))
						r.add(def);
				}
			}
			PagedResult<ProcessDefinition> pr = new PagedResult<>();
			pr.setTotalResults(r.size());
			if (start != null) {
				for (int i = 0 ; i < start.intValue(); i++)
					r.remove(0);
			}
			if (pageSize != null) {
				while (r.size() > pageSize.intValue())
					r.remove(r.size()-1);
			}
			pr.setStartIndex(start);
			pr.setItemsPerPage(pageSize);
			pr.setResources(r);
			return pr;
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected PagedResult<ProcessInstance> handleFindProcessInstanceByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final JbpmContext context = getContext();
		try {
			ProcessDefinitionRolesBusiness business = new ProcessDefinitionRolesBusiness();
			business.setContext(context);

			List<org.jbpm.graph.exe.ProcessInstance> procs = new LinkedList<>();
			if (jsonQuery == null || jsonQuery.trim().isEmpty()) {
				LinkedList<ProcessInstance> procInstances = new LinkedList<ProcessInstance>();
				if (text == null || text.isEmpty()) {
					List<org.jbpm.graph.exe.ProcessInstance> list = context.getSession().createQuery("select p from org.jbpm.graph.exe.ProcessInstance as p").list();
					for (org.jbpm.graph.exe.ProcessInstance process: list) 
					{
						ProcessInstance proc = handleGetProcess(process.getId());
						if (proc != null)
							procInstances.add(proc);
					}
				} else {
					for (Long l: findProcessInstancesByText(context, text)) {
						ProcessInstance proc = handleGetProcess(l);
						if (proc != null)
							procInstances.add( proc );
					}
				}
				PagedResult<ProcessInstance> pr = new PagedResult<>();
				pr.setTotalResults(procInstances.size());
				if (start != null) {
					for (int i = 0 ; i < start.intValue(); i++)
						procInstances.remove(0);
				}
				if (pageSize != null) {
					while (procInstances.size() > pageSize.intValue())
						procInstances.remove(procInstances.size()-1);
				}
				pr.setStartIndex(start);
				pr.setItemsPerPage(pageSize);
				pr.setResources(procInstances);
				return pr;
			} else {
				CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
				if (text != null && ! text.trim().isEmpty()) {
					config.setFirstResult(start);
					config.setMaximumResultSize(pageSize);					
				}
				ScimHelper h = new ScimHelper(ProcessInstance.class);
				h.setSession(context.getSession());
				h.setPrimaryAttributes(new String[] { "description"});
				h.setConfig(config);
				h.setGenerator((entity) -> {
					try {
						return handleGetProcess( ((org.jbpm.graph.exe.ProcessInstance) entity).getId());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}); 
				LinkedList<ProcessInstance> procInstances = new LinkedList<ProcessInstance>();
				h.search(null, jsonQuery, (Collection) procInstances); 
				if (text != null && ! text.trim().isEmpty()) {
					Set<Long> ids = new HashSet<Long>(findProcessInstancesByText(context, text));
					for (Iterator<org.jbpm.graph.exe.ProcessInstance> it = procs.iterator(); it.hasNext();) {
						org.jbpm.graph.exe.ProcessInstance proc = it.next();
						if (! ids.contains(proc.getId()) ) {
							it.remove();
						}
					}
				}
				PagedResult<ProcessInstance> pr = new PagedResult<>();
				pr.setTotalResults(procInstances.size());
				if (start != null) {
					for (int i = 0 ; i < start.intValue(); i++)
						procInstances.remove(0);
				}
				if (pageSize != null) {
					while (procInstances.size() > pageSize.intValue())
						procInstances.remove(procInstances.size()-1);
				}
				pr.setStartIndex(start);
				pr.setItemsPerPage(pageSize);
				pr.setResources(procInstances);
				return pr;
			}
		} finally {
			flushContext(context);
		}
	}

	@Override
	protected PagedResult<TaskInstance> handleFindTasksByTextAndJsonQuery(String text, String jsonQuery, Integer start,
			Integer pageSize) throws Exception {
		JbpmContext context = getContext();
		try {
			AbstractExpression expr = ExpressionParser.parse(jsonQuery);
			List <TaskInstance> r = handleFindMyTasks();
			int position = 0;
			int s = start == null ? 0 : start.intValue();
			int e = pageSize != null ? s + pageSize.intValue(): Integer.MAX_VALUE;
			for (Iterator<TaskInstance> it = r.iterator(); it.hasNext();) {
				TaskInstance ti = it.next();
				if (! expr.evaluate(ti))
					it.remove();
				else
				{
					if (position >= e || position < s ) 
						it.remove();
					position ++;
				}
			}
			
			PagedResult<TaskInstance> pr = new PagedResult<>();
			pr.setStartIndex(start);
			pr.setItemsPerPage(pageSize);
			pr.setTotalResults(position);
			pr.setResources(r);
			return pr;
		} finally {
			flushContext(context);
		}
		
	}

	protected List<Long> findProcessInstancesByText(JbpmContext context, String query)
			throws Exception {
		LinkedList<Long> resultado = new LinkedList<>();
		
		try {
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

			TermsFilter f = new TermsFilter( new Term("$end", "false") );
			BooleanFilter b = new BooleanFilter();
			b.add(new FilterClause(f, BooleanClause.Occur.MUST));

			DocumentCollector2 collector = new DocumentCollector2();
			collector.setResult (resultado);
			
			is.search(q, b, collector); // Sense cap filtre
			reader.close();
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
		} catch (ArrayIndexOutOfBoundsException e) { 
			throw new BPMException(
					Messages.getString("BpmEngineImpl.VeryRegFinded"), //$NON-NLS-1$
					-1);
		} catch (Exception e) {
			throw new BPMException(
					String.format(
							Messages.getString("BpmEngineImpl.Error"), e.getMessage()), -1); 
		}
	}

	private final class DocumentCollector2 extends Collector {
		private List<Long> result;
		private AtomicReaderContext ctx;
		private Scorer scorer;
	
		@Override
		public void setScorer(Scorer scorer) throws IOException {
			this.scorer = scorer;
		}
	
		public void setResult(List<Long> resultado) {
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
				result.add(processId);
			}
			
		}
	
		@Override
		public boolean acceptsDocsOutOfOrder() {
			return false;
		}
	}

	@Override
	protected List<ProcessDefinition> handleFindAllProcessDefinitions(boolean onlyEnabled) throws Exception {
		return findProcessDefinitions(null, onlyEnabled);
	}

}
