package com.soffid.iam.web.group;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.User;
import com.soffid.iam.service.Messages;
import com.soffid.iam.service.ejb.CustomObjectService;
import com.soffid.iam.service.ejb.GroupService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.CsvImporter;
import com.soffid.iam.web.popup.CsvParser;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.zkiblaf.Missatgebox;

public class GroupUserImporter extends CsvImporter< GroupUser > {
	private GroupService svc;
	private CrudHandler<GroupUser> handler;
	private String mainGroup;
	private DataTable dataTable;
	
	public GroupUserImporter(String name, DataTable dataTable) throws NamingException, CreateException, InternalErrorException {
		svc = EJBLocator.getGroupService();
		handler = EJBLocator.getCrudRegistryService().getHandler(GroupUser.class);
		mainGroup = name;
		this.dataTable = dataTable;
	}
	
	@Override
	protected Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		Collection<DataType> l = EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(GroupUser.class.getName(), null);
		DataType dt2 = new DataType();
		dt2.setName("info");
		dt2.setBuiltin(true);
		dt2.setNlsLabel("grups.zul.Tipus");
		dt2.setType(TypeEnumeration.STRING_TYPE);
		l.add(dt2);
		return l;
	}

	@Override
	protected GroupUser newObject() throws InternalErrorException, NamingException, CreateException {
		GroupUser o = new GroupUser();
		return o;
	}

	@Override
	protected CrudHandler<GroupUser> getCrudHandler() throws InternalErrorException, NamingException, CreateException {
		return handler;
	}

	@Override
	protected GroupUser load(GroupUser object) throws InternalErrorException {
		for (GroupUser ug: svc.findUsersGroupByUserName(object.getUser())) {
			if (ug.getGroup().equals(object.getGroup()))
				return ug;
		}
		return null;
	}

	public void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		try {
			UserService usvc = EJBLocator.getUserService();
			Collection<DataType> metadata = getMetadata();
			CrudHandler<GroupUser> handler = getCrudHandler();
			CustomObjectService svc = EJBLocator.getCustomObjectService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				GroupUser object = newObject(); 
				populate(object, metadata, m);
				if (object.getGroup() == null || object.getGroup().trim().isEmpty())
					object.setGroup(mainGroup);
				System.out.println("//////////////////////");
				System.out.println(object);
				if (Boolean.TRUE.equals(object.getPrimaryGroup()) ||
						Messages.getString("GroupServiceImpl.PrimaryGroupText").equals(object.getInfo())) {
					User u = usvc.findUserByUserName(object.getUser());
					if (u == null)
						throw new UiException("User "+object.getUser()+" does not exist");
					if (!object.getGroup().equals(u.getPrimaryGroup())) {
						updates ++;
						u.setPrimaryGroup(object.getGroup());
					}
					usvc.update(u);
				} else {
					GroupUser existing = load(object);
					if (existing != null)
					{
						boolean anyChange = populate (existing, metadata, m);
						if (anyChange) {
							handler.update(existing);
							updates ++;
						} else {
							unchanged ++;
						}
					} else {
						handler.create(object);
						inserts++;
					}
				}
			}
		} catch (UiException e) {
			throw e;
		} catch (Exception e) {
			if (m == null)
				throw new UiException(e);
			else
				throw new UiException("Error loading object "+m, e);
		}
		
		if (dataTable != null) {
			try {
				dataTable.refresh();
			} catch (Exception e) {
			}
		}
		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
	}

}
