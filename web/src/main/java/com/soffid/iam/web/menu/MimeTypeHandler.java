package com.soffid.iam.web.menu;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.api.AccessTreeExecutionType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.XPathUtils;

public class MimeTypeHandler extends InputFieldUIHandler {
	
	
	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		return true;
	}

	@Override
	public void afterCreate(InputField3 field) throws Exception {
		field.setType(Type.LIST);
		
		if (field.getPage() != null) {
			DataModel model = (DataModel) field.getPage().getFellow("model");
			DataNodeCollection coll = (DataNodeCollection) model.getJXPathContext().getValue("/mimeType");
			List<String> values = new LinkedList<String>();
			for (DataNode dn: (Collection<DataNode>) coll) {
				AccessTreeExecutionType type = (AccessTreeExecutionType) dn.getInstance();
				values.add(type.getCode()+":"+type.getMimeType());
			}
			field.setValues(values);
		}
	}

	@Override
	public void onChange(InputField3 inputField3) throws Exception {
		String selected = (String) inputField3.getValue();
		selected = (String) XPathUtils.getValue(inputField3, "exec[1]/executionTypeCode");
		DataModel model = (DataModel) inputField3.getPage().getFellow("model");
		DataNodeCollection coll = (DataNodeCollection) model.getJXPathContext().getValue("/mimeType");
		List<String> values = new LinkedList<String>();
		for (DataNode dn: (Collection<DataNode>) coll) {
			AccessTreeExecutionType type = (AccessTreeExecutionType) dn.getInstance();
			if (type.getCode().equals(selected)) {
				XPathUtils.setValue(inputField3, "/exec/content", type.getTemplate());
			}
		}
	}

}
