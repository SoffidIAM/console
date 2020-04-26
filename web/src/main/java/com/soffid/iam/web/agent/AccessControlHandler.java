package com.soffid.iam.web.agent;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Tabpanel;

import es.caib.zkib.binder.list.ModelProxy;
import es.caib.zkib.component.DataGrid;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.Pointer;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AccessControlHandler extends Tabpanel implements AfterCompose {
	private DataModel model;
	private DataTable gridControlAccess;

	public void afterCompose() {
		model = (DataModel) getFellow("model");
		gridControlAccess = (DataTable) getFellow("gridControlAccess");
	}
	
	int generaRandomNegatiu() {
	 	java.util.Random randomGenerator = new java.util.Random();
		    int randomInt = randomGenerator.nextInt(100000);
		    return -randomInt; //negatiu
	}

	public void addNew() {
//		desktop.getPage("controlAcces").setAttribute("idCAC", idRandom);
		
		getDesktop().getPage("controlAcces").setAttribute("rol_bbdd",model.getVariables().getVariable("codiAgent"));
		Events.postEvent ("onInicia",getDesktop().getPage("controlAcces").getFellow("esquemaLlista"), this);
	}

	public void onClone(Event event) {
		if ( Boolean.TRUE.equals( getVariable("canModifyAccessControl", false))) {
			DataSource ds = gridControlAccess;
			es.caib.zkib.jxpath.JXPathContext ctx = ds.getJXPathContext();																												
			
			// Guardem el id per poder moficiar la fila que correspon
			getDesktop().getPage("controlAcces").setAttribute("idCAC", ctx.getValue("/@id"));
			getDesktop().getPage("controlAcces").setAttribute("usuariGeneric", ctx.getValue("/@genericUser"));
			getDesktop().getPage("controlAcces").setAttribute("idRol", ctx.getValue("/@roleId"));
			getDesktop().getPage("controlAcces").setAttribute("descripcioRol", ctx.getValue("/@roleDescription"));
			getDesktop().getPage("controlAcces").setAttribute("nomMaquina", ctx.getValue("/@hostName"));
			getDesktop().getPage("controlAcces").setAttribute("idMaquina", ctx.getValue("/@hostId"));
			getDesktop().getPage("controlAcces").setAttribute("maquinaGeneric", ctx.getValue("/@genericHost"));
			getDesktop().getPage("controlAcces").setAttribute("programa", ctx.getValue("/@program"));
			getDesktop().getPage("controlAcces").setAttribute("rol_bbdd", XPathUtils.getValue(this, "name"));

			Events.postEvent ("onIniciaClon",getDesktop().getPage("controlAcces").getFellow("esquemaLlista"), this);
		}
		
	}
	
	public void onRemove(Event event) {
		if ( Boolean.TRUE.equals( getVariable("canDeleteAccessControlAgent", false))) {
			JXPathContext context = gridControlAccess.getJXPathContext();
			String c_usuari = (String) context.getValue("/@genericUser");
			String c_rol = (String) context.getValue("/@roleDescription");
			String c_program = (String) context.getValue("/@program");
			String dada = c_usuari==null?c_rol:c_usuari;
			Missatgebox.confirmaOK_CANCEL(
				String.format(org.zkoss.util.resource.Labels.getLabel("agents.SegurEsborra"), new Object [] {dada,c_program}),
				org.zkoss.util.resource.Labels.getLabel("agents.Esborra"),
				(evt) -> {
						if ("onOK".equals(evt.getName())) {
							gridControlAccess.delete();
						}														
				}
   			);								
		}

	}
	

	
	public void onActualitza(Event event) throws Exception {
		// NOVA FILERA DE CONTROL D'ACCÉS
		Object[] dada = (Object[]) event.getData();
		// Datos: usuari, idUsuari, usuari_generic, rol, idRol, maquina, idMaquina, maquines_generic, programa, idFicticia
		//Missatgebox.info ("rebudes #"+dada.length+ " dades "+dada)
		ModelProxy modelProxy = (es.caib.zkib.binder.list.ModelProxy) gridControlAccess.getModel();
		DataSource ds = gridControlAccess.getDataSource(); 
		es.caib.zkib.jxpath.JXPathContext ctx =  ds.getJXPathContext();
		// codi dispatcher actual
		String v_codi = (String) XPathUtils.getValue(this, "name");
		// user: pot ésser usuari(idUsuari), usuari_generic o rol
		String v_usuari = (String) dada[0];
		Long v_idUsuari = (Long) dada[1];
		String v_usuari_generic = (String) dada[2];
		String v_rol = (String) dada[3];
		Long v_idRol = (Long) dada[4];
		// maquina: pot ésser maquina (idMaquina) o maquines_generic
		String v_maquina = (String) dada[5];
		Long v_idMaquina = (Long) dada[6];
		String v_maquines_generic = (String) dada[7];
		// programa
		String v_programa = (String) dada[8];
		
		String condicio = "[@agentName = '"+v_codi+"'"; //no tanquem ]
		if (v_idRol != null) {
			condicio += " and @roleId ='"+v_idRol+"' ";
		} else  {
			condicio += " and (@genericUser = '"+v_usuari_generic+"') ";
		}
		// maquina: pot ésser maquina (idMaquina) o maquines_generic
		if (v_idMaquina!=null) {
			condicio += " and @hostId = '"+v_idMaquina+"' ";
		} else { // genèric (comparem amb el nom de la màquina)
			condicio += "and @hostName = '"+v_maquines_generic+"' ";
			//" and @maquinaGeneric = '"+v_maquines_generic+"' ";
		}
		
		// programa
		condicio +=" and @program='"+v_programa+"']"; //tanquem ]
		
		//Missatgebox.info ("condicio =" +condicio);              			
		
		String xpath = gridControlAccess.getXPath() + condicio;
		boolean jaExisteix = true;
		try {
				Object valor = ctx.getValue(xpath);
		} catch(Exception e) {
				jaExisteix = false;
		}
		if (jaExisteix) {
			Missatgebox.error (org.zkoss.util.resource.Labels.getLabel("agents.JaExisteix"));
			return;
		} else { // Creem un de nou
  			int position = modelProxy.newInstance();
  			ds = gridControlAccess.getDataSource(); 
  			ctx =  ds.getJXPathContext(); 
  			xpath = gridControlAccess.getXPath() + modelProxy.getBind(position); 
  			Pointer pointer = ctx.createPath (xpath);
 			es.caib.zkib.jxpath.JXPathContext ctx2 = ctx.getRelativeContext(pointer);
 			ctx2.setValue("@agentName", v_codi);
			if (v_idRol != null) {
				ctx2.setValue("@roleDescription", v_rol);
				ctx2.setValue("@rolId", v_idRol);
			} else  {
				ctx2.setValue("@genericUser", v_usuari_generic);
			}
			ctx2.setValue("@genericHost", v_maquines_generic);
			ctx2.setValue("@hostName", v_maquina); // no té id serà genèric
			ctx2.setValue("@program", v_programa);  
		}

	}
	
	public void editRule(Event event) {
		if ( Boolean.TRUE.equals( getVariable("canModifyAccessControl", false))) {
			DataSource ds = gridControlAccess;
			es.caib.zkib.jxpath.JXPathContext ctx = ds.getJXPathContext();																												
			
			// Guardem el id per poder moficiar la fila que correspon
			getDesktop().getPage("controlAcces").setAttribute("idCAC", ctx.getValue("/@id"));
			getDesktop().getPage("controlAcces").setAttribute("usuariGeneric", ctx.getValue("/@genericUser"));
			getDesktop().getPage("controlAcces").setAttribute("idRol", ctx.getValue("/@roleId"));
			getDesktop().getPage("controlAcces").setAttribute("descripcioRol", ctx.getValue("/@roleDescription"));
			getDesktop().getPage("controlAcces").setAttribute("nomMaquina", ctx.getValue("/@hostName"));
			getDesktop().getPage("controlAcces").setAttribute("idMaquina", ctx.getValue("/@hostId"));
			getDesktop().getPage("controlAcces").setAttribute("maquinaGeneric", ctx.getValue("/@genericHost"));
			getDesktop().getPage("controlAcces").setAttribute("programa", ctx.getValue("/@program"));
			getDesktop().getPage("controlAcces").setAttribute("rol_bbdd", XPathUtils.getValue(this, "name"));
			Events.postEvent ("onIniciaUpdate",getDesktop().getPage("controlAcces").getFellow("esquemaLlista"), this);
		}
		
		
	}
	
	public void onUpdate (Event event) {
		Object []dada = (Object[]) event.getData();
		// Datos: usuari, idUsuari, usuari_generic, rol, idRol, maquina, idMaquina, maquines_generic, programa, id (real o ficticia)
		ListModel modelProxy = gridControlAccess.getModel();
		DataSource ds = gridControlAccess; 
		es.caib.zkib.jxpath.JXPathContext ctx =  ds.getJXPathContext();
		String v_codi = (String) XPathUtils.getValue(this, "name");
		// user: pot ésser usuari(idUsuari), usuari_generic o rol
		String v_usuari = (String) dada[0];
		Long v_idUsuari = (Long) dada[1];
		String v_usuari_generic = (String) dada[2];
		String v_rol = (String) dada[3];
		Long v_idRol = (Long) dada[4];
		// maquina: pot ésser maquina (idMaquina) o maquines_generic
		String v_maquina = (String) dada[5];
		Long v_idMaquina = (Long) dada[6];
		String v_maquines_generic = (String) dada[7];
		// programa
		String v_programa = (String) dada[8];

		ctx.setValue("@agentName", v_codi);
		if (v_idRol != null) {
			ctx.setValue("@roleDescription", v_rol);
			ctx.setValue("@rolId", v_idRol);
		} else  {
			ctx.setValue("@genericUser", v_usuari_generic);
		}
		ctx.setValue("@genericHost", v_maquines_generic);
		ctx.setValue("@hostName", v_maquina); // no té id serà genèric
		ctx.setValue("@program", v_programa);  
	}
}
