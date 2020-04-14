package com.soffid.iam.web.config;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listbox;

import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Form;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;

public class AuthorizationHandler extends FrameHandler {
	private boolean canQueryAuthorization;
	private boolean canCreateRolAutoritzacion;
	private boolean canDeleteRolAutoritzacion;

	public AuthorizationHandler() throws Exception {

		// Autoritzacions
		canQueryAuthorization = AutoritzacionsUsuari.hasQueryAuthorization();
		canCreateRolAutoritzacion = AutoritzacionsUsuari.hasCreateAuthorizationRol();
		canDeleteRolAutoritzacion = AutoritzacionsUsuari.hasDeleteAuthorizationRol();

	}

	@Override
	public void setPage(Page page) {
		super.setPage(page);

		setVariable("canQueryAuthorization", canQueryAuthorization, true);
		setVariable("canCreateRolAutoritzacion", canCreateRolAutoritzacion, true);
		setVariable("canDeleteRolAutoritzacion", canDeleteRolAutoritzacion, true);

	}

	public void select() {
		DataTable t = (DataTable) getListbox();
		if (t.getSelectedIndex() >= 0) {
			DataModel model = getModel();
			String codiSeleccionatAbans = (String) getModel().getVariables().getVariable("descripcioSeleccionat");
			String message = String.format(org.zkoss.util.resource.Labels.getLabel("autoritzacions.VolConfirmar"),
							new Object[]{codiSeleccionatAbans});
					
			// Verifiquem si n'hi ha canvis pendents:
			if (model.isCommitPending()) {
				es.caib.zkib.zkiblaf.Missatgebox.confirmaYES_NO(message,
					org.zkoss.util.resource.Labels.getLabel("autoritzacions.ConfirmaCanvis"),
					(event) -> {
						if ("onOK".equals(event.getName())) {
							model.commit();
						} else {
							model.refresh();
						}
					});

				if( t.getSelectedIndex() >= 0)
				{
					String codiSeleccionat = (String) XPathUtils.getValue((DataSource) t, "name");
					String descripcioSeleccionat = (String) XPathUtils.getValue((DataSource) t, "description");
					String tipusDominiSeleccionat = (String) XPathUtils.getValue((DataSource) t, "typeDescription");  
					String tipusDomini = (String) XPathUtils.getValue((DataSource) t, "type");  
					String scopeSeleccionat = (String) XPathUtils.getValue((DataSource) t, "scope");
					String heretaSeleccionat = (String) XPathUtils.getValue((DataSource) t, "inherited");
					
					model.getVariables().declareVariable("queryEnabled", true);
					
					model.getVariables().declareVariable("codiAutoritzacio", codiSeleccionat);
					model.getVariables().declareVariable("descripcioSeleccionat", descripcioSeleccionat);
					model.getVariables().declareVariable("tipusDominiSeleccionat", tipusDominiSeleccionat);
					model.getVariables().declareVariable("tipusDomini", tipusDomini);
					model.getVariables().declareVariable("scopeSeleccionat", scopeSeleccionat);
					model.getVariables().declareVariable("heretaSeleccionat", heretaSeleccionat);
					
					
					// i el tipus de domini
					String s_tipusDominiSeleccionat = org.zkoss.util.resource.Labels.getLabel("autoritzacions.TipusDomini")+tipusDominiSeleccionat;
					model.getVariables().declareVariable("s_tipusDominiSeleccionat", s_tipusDominiSeleccionat);

				}
			}
		}
	}

	@Override
	public boolean insertBefore(Component newChild, Component refChild) {
		boolean x =  super.insertBefore(newChild, refChild);
		if (newChild instanceof DataModel) {
			DataModel model = (DataModel) newChild;
			model.getVariables().declareVariable("canQueryAuthorization", canQueryAuthorization);
			model.getVariables().declareVariable("canCreateRolAutoritzacion", canCreateRolAutoritzacion);
			model.getVariables().declareVariable("canDeleteRolAutoritzacion", canDeleteRolAutoritzacion);
		}
		return x;
	}
	
}
