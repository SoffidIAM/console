package com.soffid.iam.web.domain;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.io.FileWriter;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Filedownload;

import com.soffid.codemirror.Codemirror;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.service.ejb.UserDomainService;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;
import com.soffid.iam.web.util.RemoveOnCloseStream;

import au.com.bytecode.opencsv.CSVWriter;
import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Select;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Missatgebox;

public class UserDomainHandler extends FrameHandler implements AfterCompose {

	private boolean canCreateUserDomain;
	private boolean canUpdateUserDomain;
	private boolean canDeleteUserDomain;
	private boolean canQueryUserDomain;
	private boolean canModifyUserDomain;

	public UserDomainHandler() throws InternalErrorException {
		super();
		canCreateUserDomain = AutoritzacionsUsuari.hasCreateDominisUsuari();
		canUpdateUserDomain = AutoritzacionsUsuari.hasUpdateDominisUsuari();
		canDeleteUserDomain = AutoritzacionsUsuari.hasDeleteDominisUsuari();
		canQueryUserDomain = AutoritzacionsUsuari.hasQueryDominisUsuari();
		canModifyUserDomain = canCreateUserDomain || canUpdateUserDomain;
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		setVariable("canCreateUserDomain", canCreateUserDomain, true);
		setVariable("canUpdateUserDomain", canUpdateUserDomain, true);
		setVariable("canDeleteUserDomain", canDeleteUserDomain, true);
		setVariable("canQueryUserDomain", canQueryUserDomain, true);
		setVariable("canModifyUserDomain", canModifyUserDomain, true);
		setVariable("e_tipusDominiUsuari", UserDomainType.values, true);
	}

	public void onChangeForm(Event event) {
		DataNode registre;
		try {
			registre = (DataNode) XPathUtils.getValue(getForm(), "/");
		} catch (Exception e) {
			return;
		}
		UserDomain ud = (UserDomain) registre.getInstance();

		if (registre.isNew() && ud.getType() == null) {
			XPathUtils.setValue(getForm(), "/type", TipusDominiUsuariEnumeration.PRINCIPAL);
		}

		com.soffid.codemirror.Codemirror cm = (Codemirror) getFellow("du_script1");
		cm.setReadonly(!ud.getType().equals(TipusDominiUsuariEnumeration.SHELL));

		com.soffid.codemirror.Codemirror cm2 = (Codemirror) getFellow("du_script2");
		cm2.setReadonly(!ud.getType().equals(TipusDominiUsuariEnumeration.SHELL));

		Select lb = (Select) getFellow("lbGenerator");
		lb.setDisabled (! ud.getType().equals(TipusDominiUsuariEnumeration.SPRINGCLASS));
	}

	public void onChangeType(Event event) {
		DataNode registre = (DataNode) XPathUtils.getValue(getForm(), "/");
		UserDomain ud = (UserDomain) registre.getInstance();

		Select select = (Select) event.getTarget(); 
		TipusDominiUsuariEnumeration type = (TipusDominiUsuariEnumeration) select.getSelectedValue();
		//Missatgebox.confirmaOK_CANCEL ("sel "+tipus);
		Select lb = (Select) getFellow("lbGenerator");
		lb.setDisabled (! type.equals(TipusDominiUsuariEnumeration.SPRINGCLASS));

		com.soffid.codemirror.Codemirror cm = (Codemirror) getFellow("du_script1");
		cm.setReadonly (! type.equals(TipusDominiUsuariEnumeration.SHELL));

		com.soffid.codemirror.Codemirror cm2 = (Codemirror) getFellow("du_script2");
		cm2.setReadonly (! type.equals(TipusDominiUsuariEnumeration.SHELL));
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		try {
			com.soffid.codemirror.Codemirror cm = (Codemirror) getFellow("du_script1");
			cm.setGlobalVars(new com.soffid.iam.web.agent.ScriptEnviroment().getDomainVars());
		} catch (InternalErrorException | NamingException | CreateException | IOException e) {
		}
		try {
			com.soffid.codemirror.Codemirror cm = (Codemirror) getFellow("du_script2");
			cm.setGlobalVars(new com.soffid.iam.web.agent.ScriptEnviroment().getDomainVars());
		} catch (InternalErrorException | NamingException | CreateException | IOException e) {
		}
	}
	
	public void scriptHelp() {
		Executions.getCurrent().sendRedirect("https://confluence.soffid.com/display/SOF/Account+naming+rules+script", "_blank");
	}
	
	public void export () throws IOException, InternalErrorException, NamingException, CreateException {
		File f = File.createTempFile("export", ".csv");
		CSVWriter w = new CSVWriter( new FileWriter(f, "UTF-8"));
		w.writeNext(new String[] { 
			Labels.getLabel("dominiUsuaris.zul.Codi"),
			Labels.getLabel("dominiUsuaris.zul.Descripcia"),
			Labels.getLabel("dominiUsuaris.zul.Tipus"),
			Labels.getLabel("dominiUsuaris.zul.Generator"),
			Labels.getLabel("dominiUsuaris.zul.scriptCreate"),
			Labels.getLabel("dominiUsuaris.zul.script")
		});
		
		for ( UserDomain ud: EJBLocator.getUserDomainService().findAllUserDomain() ) {
			w.writeNext(new String[] {
					ud.getName(),
					ud.getDescription(),
					ud.getType().getValue(),
					ud.getBeanGenerator(),
					ud.getBshExprCreate(),
					ud.getBshExpr()
			});
		}
		w.close();
		
		Filedownload.save(new RemoveOnCloseStream(f), "text/csv", "account-naming-rules.csv");
	}

	public void importCsv () throws IOException, CommitException {
		getModel().commit();
		
		String[][] data = { 
				{"name",Labels.getLabel("dominiUsuaris.zul.Codi")},
				{"description",Labels.getLabel("dominiUsuaris.zul.Descripcia")},
				{"type",Labels.getLabel("dominiUsuaris.zul.Tipus")},
				{"generator", Labels.getLabel("dominiUsuaris.zul.Generator")},
				{"creationScript", Labels.getLabel("dominiUsuaris.zul.scriptCreate")},
				{"script", Labels.getLabel("dominiUsuaris.zul.script")}
		};
		
		String title = Labels.getLabel("tenant.zul.import");
		ImportCsvHandler.startWizard(title, data, this, 
				parser -> importCsv(parser));
	}

	private void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		try {
			UserDomainService udSvc = EJBLocator.getUserDomainService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				String name = m.get("name");
				String type = m.get("type");
				String description = m.get("description");
				String generator = m.get("generator");
				String creationScript = m.get("creationScript");
				String script = m.get("script");
				if (name != null && !name.trim().isEmpty())
				{
					UserDomain ud = udSvc.findUserDomainByName(name);
					if (ud != null)
					{
						if ( m.containsKey("description"))
							ud.setDescription(description);
						if ( m.containsKey("type") && type != null)
							ud.setType(TipusDominiUsuariEnumeration.fromString(type));
						if ( m.containsKey("generator"))
							ud.setBeanGenerator(generator);
						if ( m.containsKey("creationScript"))
							ud.setBshExprCreate(creationScript);
						if ( m.containsKey("script"))
							ud.setBshExpr(script);
						udSvc.update(ud);
						updates ++;
					} else {
						inserts ++;
						ud = new UserDomain();
						ud.setName(name);
						ud.setDescription(description);
						if (type != null)
							ud.setType(TipusDominiUsuariEnumeration.fromString(type));
						ud.setBeanGenerator(generator);
						ud.setBshExprCreate(creationScript);
						ud.setBshExpr(script);
						udSvc.create(ud);
					}
				}
			}
		} catch (UiException e) {
			throw e;
		} catch (Exception e) {
			if (m == null)
				throw new UiException(e);
			else
				throw new UiException("Error loading parameter "+m.get("name"), e);
		}
		
		getModel().refresh();
		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
	}

}
