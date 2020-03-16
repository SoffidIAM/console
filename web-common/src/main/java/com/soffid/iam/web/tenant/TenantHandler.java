package com.soffid.iam.web.tenant;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Application;

public class TenantHandler extends FrameHandler {
	private boolean isMaster;
	private boolean canCreateTenant;
	private boolean canUpdateTenant;
	private boolean canDeleteTenant;
	private boolean canQueryTenant;

	public TenantHandler() throws InternalErrorException {
		com.soffid.iam.api.Tenant masterTenant = com.soffid.iam.ServiceLocator.instance().getTenantService().getMasterTenant();

		isMaster = com.soffid.iam.utils.Security.getCurrentTenantName().equals ( masterTenant.getName() );
		canCreateTenant = isMaster && Security.isUserInRole("tenant:create");
		canUpdateTenant = isMaster && Security.isUserInRole("tenant:update");
		canDeleteTenant = isMaster && Security.isUserInRole("tenant:delete");
		canQueryTenant = isMaster && Security.isUserInRole("tenant:query");;
		
		try
		{
			es.caib.zkib.zkiblaf.Application.setTitle(org.zkoss.util.resource
				.Labels.getLabel("seu.tenants"));
		}
		catch (Exception ex){}

	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		getNamespace().setVariable("isMaster", isMaster, true);
		getNamespace().setVariable("canCreateTenant", canCreateTenant, true);
		getNamespace().setVariable("canUpdateTenant", canUpdateTenant, true);
		getNamespace().setVariable("canDeleteTenant", canDeleteTenant, true);
		getNamespace().setVariable("canQueryTenant", canQueryTenant, true);
	}
		
	public void export() throws InternalErrorException, NamingException, CreateException, IOException {
		es.caib.zkib.datasource.DataSource ds = getForm().getDataSource();
		es.caib.zkib.jxpath.JXPathContext ctx = ds.getJXPathContext();
		Tenant tenant = (Tenant) ((DataNode) ctx.getValue("/")).getInstance();
		java.io.File f = java.io.File.createTempFile("tenant", "dump");
		java.io.OutputStream out = new java.io.FileOutputStream(f);

		com.soffid.iam.EJBLocator.getTenantService().exportTenant(tenant, out);

		out.close();

		org.zkoss.util.media.AMedia media = new org.zkoss.util.media.AMedia(tenant.getName() + ".dump", "txt",
				"binary/octet-stream", f, true);

		Filedownload.save(media, media.getName());
		f.deleteOnExit();

	}
	
	public void importTenant() throws Exception {
		org.zkoss.util.media.Media media = Fileupload.get();
		
		if (media == null)
			return;

		java.io.InputStream in;
		if (media.isBinary())
			if (media.inMemory())
				in = new java.io.ByteArrayInputStream(media.getByteData());
			else
				in = media.getStreamData();
		else if (media.inMemory())
			in = new java.io.ByteArrayInputStream(media.getStringData().getBytes());
		else
			in = new es.caib.bpm.attachment.ReaderInputStream(media.getReaderData());

		com.soffid.iam.api.Tenant t = com.soffid.iam.EJBLocator.getTenantService().importTenant(in);

		String path = es.caib.zkib.datasource.XPathUtils.createPath(getModel(), "/tenant", t);
		DataNode node = (DataNode) getModel().getValue(path);

		node.setDirty(false);
		DataTable lb = getListbox();
		lb.setSelectedIndex(lb.getModel().getSize() - 1);
	}

	public void onChangeDades() {
		Textbox tb = (Textbox) getForm().getFellow("form_name");
		try {
			es.caib.zkib.datasource.DataSource ds = getForm().getDataSource();
			es.caib.zkib.jxpath.JXPathContext ctx = ds.getJXPathContext();
			DataNode registre = (DataNode) ctx.getValue("/");
			tb.setDisabled(!registre.isNew());
			Collection<DataNode> permissions = (Collection<DataNode>) es.caib.zkib.datasource.XPathUtils.getValue(ds, "/permissions");
			if (registre.isNew() && permissions.isEmpty()) 
			{
				es.caib.zkib.datasource.XPathUtils.setValue(ds,
						es.caib.zkib.datasource.XPathUtils.createPath(ds, "/permissions") + "/newValue",
						"seu:tenant:show");
				es.caib.zkib.datasource.XPathUtils.setValue(ds,
						es.caib.zkib.datasource.XPathUtils.createPath(ds, "/permissions") + "/newValue",
						"tenant:query");
				es.caib.zkib.datasource.XPathUtils.setValue(ds,
						es.caib.zkib.datasource.XPathUtils.createPath(ds, "/permissions") + "/newValue",
						"tenant:create");
				es.caib.zkib.datasource.XPathUtils.setValue(ds,
						es.caib.zkib.datasource.XPathUtils.createPath(ds, "/permissions") + "/newValue",
						"tenant:update");
				es.caib.zkib.datasource.XPathUtils.setValue(ds,
						es.caib.zkib.datasource.XPathUtils.createPath(ds, "/permissions") + "/newValue",
						"tenant:delete");
				es.caib.zkib.datasource.XPathUtils.setValue(ds,
						es.caib.zkib.datasource.XPathUtils.createPath(ds, "/permissions") + "/newValue",
						"tenant:switch");
			}
		} catch (Exception e) {
			tb.setDisabled(true);
		}
	}
	
	public void nestedLogin() throws InterruptedException {
		if (getModel().isCommitPending()) {
			Messagebox.show(org.zkoss.util.resource.Labels.getLabel("dbpropertyadmin.CanvisPendents"),
					org.zkoss.util.resource.Labels.getLabel("dbpropertyadmin.Alerta"), Messagebox.OK,
					Messagebox.EXCLAMATION);
			return;
		}
		String name = (String) XPathUtils.getValue((Component) getForm(), "name");
		if (name != null) {
			LinkedList<String> effectiveRoles = new LinkedList();
			es.caib.zkib.binder.BindContext ctx = es.caib.zkib.datasource.XPathUtils.getComponentContext(getForm());
			for (String role : Security.getAuthorizations()) {
				boolean forbidden = false;
				for (Iterator<String> it = (Iterator<String>) ctx.getDataSource().getJXPathContext().iterate("/permissions/newValue");
						it.hasNext();)
				{
					String tp = it.next();
					if (role.startsWith(tp))
						forbidden = true;
				}
				if (!forbidden)
					effectiveRoles.add(role);
			}

			com.soffid.iam.common.security.SoffidPrincipal p = new com.soffid.iam.security.SoffidPrincipalImpl(
					name + "\\" + Security.getCurrentAccount(), effectiveRoles, Security.getSoffidPrincipal());
			
			Executions
				.getCurrent()
				.getDesktop()
				.getSession()
				.setAttribute(es.caib.bpm.filters.WorkflowInterceptor.SOFFID_NESTED_PRINCIPAL, p);
			Executions.sendRedirect("/index.zul");
		}
	}
	
	public void addServer(Event ev) throws Exception {
		es.caib.zkib.binder.BindContext bindCtx = es.caib.zkib.datasource.XPathUtils.getComponentContext(ev.getTarget());
		es.caib.zkib.datasource.XPathUtils.createPath(
				bindCtx.getDataSource(),
				"/server");
	}

	public void addPermission(Event ev) throws Exception {
		es.caib.zkib.binder.BindContext bindCtx = es.caib.zkib.datasource.XPathUtils.getComponentContext(ev.getTarget());
		es.caib.zkib.datasource.XPathUtils.createPath(
				bindCtx.getDataSource(),
				"/permissions");
	}
	
	public void removeValue(Event ev) {
		es.caib.zkib.binder.BindContext bindCtx = es.caib.zkib.datasource.XPathUtils.getComponentContext(ev.getTarget());
		es.caib.zkib.datasource.XPathUtils.removePath(bindCtx.getDataSource(), bindCtx.getXPath());
	}
	
}
